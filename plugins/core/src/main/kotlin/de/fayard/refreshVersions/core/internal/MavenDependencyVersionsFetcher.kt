package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version
import java.text.SimpleDateFormat
import java.util.*

internal abstract class MavenDependencyVersionsFetcher(
    moduleId: ModuleId,
    repoUrl: String
) : DependencyVersionsFetcher(
    moduleId = moduleId,
    repoKey = repoUrl
) {
    protected abstract suspend fun getXmlMetadataOrNull(): String?

    override suspend fun getAvailableVersionsOrNull(versionFilter: ((Version) -> Boolean)?): SuccessfulResult? {

        val xml = getXmlMetadataOrNull() ?: return null

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
