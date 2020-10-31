package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.core.extensions.okhttp.await
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.HttpException
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

internal class MavenDependencyVersionsFetcherHttp(
    private val httpClient: OkHttpClient,
    moduleId: ModuleId,
    repoUrl: String,
    repoAuthorization: String?
) : MavenDependencyVersionsFetcher(
    moduleId = moduleId,
    repoUrl = repoUrl
) {

    init {
        require(repoUrl.endsWith('/'))
    }

    private val request = Request.Builder().apply {
        val metadataUrlForArtifact = moduleId.let { (group, name) ->
            "$repoUrl${group!!.replace('.', '/')}/$name/maven-metadata.xml"
        }
        url(OkHttpFileUrlHandler.rewrittenIfFileSchemeUrl(metadataUrlForArtifact))
        header(
            name = "Authorization",
            value = repoAuthorization ?: return@apply
        )
    }.build()

    override suspend fun getMetadata(): String {
        return httpClient.newCall(request).await().use { response ->
            if (response.isSuccessful) {
                response.use { it.body!!.string() }
            } else throw HttpException(Response.error<Any?>(response.code, response.body!!))
        }
    }
}
