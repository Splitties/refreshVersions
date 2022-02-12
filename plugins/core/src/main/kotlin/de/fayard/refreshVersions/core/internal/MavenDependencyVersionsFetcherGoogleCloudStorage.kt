package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.internal.xor.Xor
import org.gradle.internal.resource.transport.gcp.gcs.GcsClient
import org.gradle.internal.resource.transport.gcp.gcs.GcsConnectionProperties
import java.io.IOException
import java.io.InputStream
import java.lang.reflect.InvocationTargetException
import java.net.URI

internal class MavenDependencyVersionsFetcherGoogleCloudStorage(
    moduleId: ModuleId,
    private val repoUrl: String
) : MavenDependencyVersionsFetcher(
    moduleId = moduleId,
    repoUrl = repoUrl
) {

    private val uri: URI = with(moduleId) {
        URI("$repoUrl/${group!!.replace('.', '/')}/$name/maven-metadata.xml")
    }
    private val repoPath = repoUrl.substringAfter("gcs://").substringAfter("/")

    override suspend fun attemptGettingXmlMetadata(): Xor<String?, FailureCause.CommunicationIssue> = runCatching {
        val connectionProperties = GcsConnectionProperties::class.java.getDeclaredConstructor().let {
            it.isAccessible = true
            it.newInstance()
        }
        val gcsClient = GcsClient.create(connectionProperties)
        return try {
            // The public function "getResource" is not found at runtime, so we have to use this one instead:
            val storageObject: InputStream? = gcsClient.javaClass.declaredMethods.firstOrNull {
                it.name == "getResourceStream"
            }?.let {
                it.isAccessible = true
                try {
                    it.invoke(gcsClient, uri) as InputStream?
                } catch (e: InvocationTargetException) {
                    throw e.cause ?: return@let null
                }
            }
            val fileContent = storageObject?.use {
                it.bufferedReader().readText()
            }
            Xor.First(fileContent)
        } catch (e: Exception) {
            // Because of classloader mismatches, we have to do this class name matching,
            // and can't use symbols specific to the GoogleJsonResponseException class.
            val statusCode = when (e.javaClass.name) {
                "com.google.api.client.googleapis.json.GoogleJsonResponseException" -> {
                    e.message?.substringBefore(' ')?.toIntOrNull() ?: throw e
                }
                else -> throw e
            }
            if (statusCode == 404) {
                Xor.First(null)
            } else {
                val failure = FailureCause.CommunicationIssue.HttpResponse(
                    statusCode = statusCode,
                    exception = IOException(errorMessage(), e)
                )
                Xor.Second(failure)
            }
        }
    }.getOrElse {
        Xor.Second(FailureCause.CommunicationIssue.NetworkIssue(it as? IOException ?: IOException(it)))
    }

    private fun errorMessage(): String {
        val path: String = with(moduleId) {
            "$repoPath/${group!!.replace('.', '/')}/$name/maven-metadata.xml"
        }
        val bucketName = repoUrl.substringAfter("gcs://").substringBefore("/")
        return "Unable to load '$path' from Google Cloud Storage bucket '$bucketName'."
    }
}
