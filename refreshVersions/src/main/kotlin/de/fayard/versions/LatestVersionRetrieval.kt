package de.fayard.versions

import de.fayard.versions.extensions.stabilityLevel
import de.fayard.versions.extensions.versionComparator
import org.gradle.api.Project
import java.net.URL

internal class VersionCandidate(val stabilityLevel: StabilityLevel, val version: Version)

internal fun Project.getDependencyVersionsCandidates(
    extension: RefreshVersionsPropertiesExtension,
    repositories: List<MavenRepoUrl>,
    group: String,
    name: String,
    resolvedVersion: String?
): List<VersionCandidate> {
    val currentVersion = Version(resolvedVersion ?: "")
    return repositories.asSequence().map {
        URL(it.metadataUrlForArtifact(group = group, name = name))
    }.mapNotNull { url ->
        runCatching {
            url.readText()
        }.getOrNull()?.let { xml ->
            parseVersionsFromMavenMetaData(xml)
        }
    }.flatten()
        .sortedWith(versionComparator.reversed())
        .distinct()
        .takeWhile { candidate -> candidate > currentVersion }
        .fold<Version, List<VersionCandidate>>(emptyList()) { acc, candidateVersion ->
            val previousStabilityLevel = acc.lastOrNull()?.stabilityLevel
                ?: return@fold acc + VersionCandidate(candidateVersion.stabilityLevel(), candidateVersion)
            if (candidateVersion.stabilityLevel() isMoreStableThan previousStabilityLevel) {
                acc + VersionCandidate(candidateVersion.stabilityLevel(), candidateVersion)
            } else acc
        }.asReversed()
}

private operator fun Version.compareTo(currentVersion: Version): Int {
    return versionComparator.compare(this, currentVersion)
}

private fun parseVersionsFromMavenMetaData(xml: String): List<Version> {
    return xml.substringAfter("<versions>").substringBefore("</versions>")
        .split("<version>", "</version>")
        .mapNotNull { if (it.isBlank()) null else Version(it.trim()) }
}
