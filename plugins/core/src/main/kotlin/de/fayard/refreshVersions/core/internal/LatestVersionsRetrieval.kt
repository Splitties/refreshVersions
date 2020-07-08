@file:Suppress("EXPERIMENTAL_API_USAGE")

package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.Version
import kotlinx.coroutines.*
import java.net.URL

internal suspend fun getDependencyVersionsCandidates(
    repositories: List<MavenRepoUrl>,
    group: String,
    name: String,
    resolvedVersion: String?
): List<Version> = Dispatchers.Default {
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
    ).sortedDescending()
        .distinct()
        .takeWhile { candidate -> candidate > currentVersion }
        .fold<Version, List<Version>>(emptyList()) { acc, versionCandidate ->
            val previousStabilityLevel = acc.lastOrNull()?.stabilityLevel
                ?: return@fold acc + versionCandidate
            if (versionCandidate.stabilityLevel isMoreStableThan previousStabilityLevel) {
                acc + versionCandidate
            } else acc
        }.asReversed()
}

private suspend fun retrieveAllVersions(
    repositories: List<MavenRepoUrl>,
    group: String,
    name: String
): List<Version> = Dispatchers.Default {
    val versionsAsync = repositories.map { repo ->
        async {
            withContext(Dispatchers.IO) {
                val url = URL(repo.metadataUrlForArtifact(group = group, name = name))
                runCatching {
                    url.readText() //TODO: Replace with cancellable network I/O
                }.getOrNull()
            }?.let { xml ->
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
