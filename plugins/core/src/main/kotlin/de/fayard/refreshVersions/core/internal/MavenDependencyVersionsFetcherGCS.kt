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

internal class MavenDependencyVersionsFetcherGCS(
    moduleId: ModuleId,
    repoUrl: String
) : DependencyVersionsFetcher(
    moduleId = moduleId,
    repoKey = repoUrl
) {

    val gcs = GCSFetcher(repoUrl)

    init {
//        require(repoUrl.endsWith('/'))
    }

    override suspend fun getAvailableVersions(versionFilter: ((Version) -> Boolean)?): SuccessfulResult {
        val xml: String = gcs.getBlob("${moduleId.group!!.replace('.', '/')}/${moduleId.name}/maven-metadata.xml")

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
