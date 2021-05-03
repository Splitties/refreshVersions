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

internal class MavenDependencyVersionsFetcher(
    private val httpClient: OkHttpClient,
    moduleId: ModuleId,
    val repoUrl: String,
    repoAuthorization: String?
) : DependencyVersionsFetcher(
    moduleId = moduleId,
    repoKey = repoUrl
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

    override suspend fun getAvailableVersions(versionFilter: ((Version) -> Boolean)?): SuccessfulResult {

        val xml = httpClient.newCall(request).await().use { response ->
            if (response.isSuccessful) {
                response.use { it.body!!.string() }
            } else throw HttpException(Response.error<Any?>(response.code, response.body!!))
        }

        val allVersions = parseVersionsFromMavenMetaData(xml)
        return SuccessfulResult(
            lastUpdateTimestampMillis = parseLastUpdatedFromMavenMetaData(xml),
            availableVersions = if (versionFilter == null) {
                allVersions
            } else {
                allVersions.filter(versionFilter)
            }
        )
    }

    companion object {
        @Suppress("SpellCheckingInspection")
        private val mavenLastUpdatedDateFormat = SimpleDateFormat("yyyyMMddHHmmss", Locale.ROOT).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }

        private fun parseLastUpdatedFromMavenMetaData(xml: String): Long = runCatching {
            val dateString = xml.substringAfter("<lastUpdated>").substringBefore("</lastUpdated>")
            return mavenLastUpdatedDateFormat.parse(dateString).time
        }.getOrDefault(0)


        private fun parseVersionsFromMavenMetaData(xml: String): List<Version> {
            return xml.substringAfter("<versions>").substringBefore("</versions>")
                .split("<version>", "</version>")
                .mapNotNull { if (it.isBlank()) null else Version(it.trim()) }
        }
    }
}
