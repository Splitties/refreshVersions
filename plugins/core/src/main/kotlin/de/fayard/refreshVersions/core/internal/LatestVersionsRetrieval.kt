@file:Suppress("EXPERIMENTAL_API_USAGE")

package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.StabilityLevel
import de.fayard.refreshVersions.core.extensions.stabilityLevel
import de.fayard.refreshVersions.core.extensions.versionComparator
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.invoke
import java.net.URL

internal class VersionCandidate(val stabilityLevel: StabilityLevel, val version: Version)

internal suspend fun getDependencyVersionsCandidates(
    repositories: List<MavenRepoUrl>,
    group: String,
    name: String,
    resolvedVersion: String?
): List<VersionCandidate> = Dispatchers.Default {
    val currentVersion = Version(resolvedVersion ?: "")
    //TODO: Retrieve (concurrently) the versions from each repo and put them into a list.
    // For each list where resolvedVersion is found, drop it with all the entries before.
    // Remove duplicates, keeping only the first occurrence (distinct).
    // If there's a resolvedVersion, apply the following rule:
    //   If dev, snapshot or unknown stability level versions are found in what's remaining,
    //   check they are numerically equal or greater than the resolvedVersion (if any).
    //   If they are not greater, of if they are equal but the resolvedVersion is stable, drop them.
    // Keep only the last entries from the lists if any, flattening it as a list of VersionCandidates, and return it.
    retrieveAllVersions(
        repositories = repositories,
        group = group,
        name = name
    ).sortedWith(versionComparator.reversed())
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

private suspend fun retrieveAllVersions(
    repositories: List<MavenRepoUrl>,
    group: String,
    name: String
): List<Version> = Dispatchers.Default {
    val versionsAsync = repositories.map { repo ->
        async(Dispatchers.IO) {
            val url = URL(repo.metadataUrlForArtifact(group = group, name = name))
            runCatching {
                url.readText() //TODO: Replace with cancellable network I/O
            }.getOrNull()?.let { xml ->
                parseVersionsFromMavenMetaData(xml)
            } ?: emptyList()
        }
    }
    versionsAsync.awaitAll()
}.flatten()

private fun parseVersionsFromMavenMetaData(xml: String): List<Version> {
    return xml.substringAfter("<versions>").substringBefore("</versions>")
        .split("<version>", "</version>")
        .mapNotNull { if (it.isBlank()) null else Version(it.trim()) }
}
