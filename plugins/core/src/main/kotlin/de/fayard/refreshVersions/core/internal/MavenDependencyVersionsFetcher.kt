package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.core.internal.xor.Xor
import java.text.SimpleDateFormat
import java.util.*

internal abstract class MavenDependencyVersionsFetcher(
    moduleId: ModuleId,
    repoUrl: String
) : DependencyVersionsFetcher(
    moduleId = moduleId,
    repoUrlOrKey = repoUrl
) {

    /**
     * Shall return `Xor.First(null)` if the metadata is not found on the repo (404 or file not found).
     */
    protected abstract suspend fun attemptGettingXmlMetadata(): Xor<String?, FailureCause.CommunicationIssue>

    override suspend fun attemptGettingAvailableVersions(versionFilter: ((Version) -> Boolean)?): Result {

        val xml: String = when (val result = attemptGettingXmlMetadata()) {
            is Xor.First -> result.value ?: return Result.NotFound
            is Xor.Second -> return failure(result.value)
        }

        val allVersions = runCatching {
            parseVersionsFromMavenMetaData(xml)
        }.getOrElse {
            return failure(FailureCause.ParsingIssue(it))
        }
        return Result.Success(
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
