package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.core.internal.VersionCandidatesResultMode.FilterMode.*
import de.fayard.refreshVersions.core.internal.VersionCandidatesResultMode.SortingMode.*
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import retrofit2.HttpException
import java.io.FileNotFoundException

internal suspend fun List<DependencyVersionsFetcher>.getVersionCandidates(
    currentVersion: Version,
    resultMode: VersionCandidatesResultMode
): List<Version> {

    val results = getVersionCandidates(versionFilter = { it > currentVersion })
    return when (resultMode.filterMode) {
        AllIntermediateVersions -> when (resultMode.sortingMode) {
            is ByRepo -> results.byRepoSorting(resultMode.sortingMode).flatMap {
                it.availableVersions.asSequence()
            }
            ByVersion -> results.flatMap { it.availableVersions.asSequence() }.sorted()
        }.distinct().toList()
        LatestByStabilityLevel -> when (resultMode.sortingMode) {
            is ByRepo -> results.byRepoSorting(resultMode.sortingMode).flatMap {
                it.availableVersions.filterLatestByStabilityLevel().asSequence()
            }
            ByVersion -> results.flatMap {
                it.availableVersions.filterLatestByStabilityLevel().asSequence()
            }.sorted()
        }.distinct().toList()
        Latest -> when (resultMode.sortingMode) {
            is ByRepo -> results.byRepoSorting(resultMode.sortingMode).map {
                it.availableVersions.last()
            }.distinct().toList()
            is ByVersion -> listOf(results.flatMap { it.availableVersions.asSequence() }.sorted().last())
        }
    }
}

private fun Sequence<DependencyVersionsFetcher.SuccessfulResult>.byRepoSorting(
    sortingMode: ByRepo
): Sequence<DependencyVersionsFetcher.SuccessfulResult> = when (sortingMode) {
    ByRepo -> this
    ByRepo.LastUpdated -> sortedBy { it.lastUpdateTimestampMillis }
    ByRepo.LastVersionComparison -> sortedBy { it.availableVersions.last() }
}

private fun List<Version>.filterLatestByStabilityLevel(): List<Version> {
    return sortedDescending().fold<Version, List<Version>>(emptyList()) { acc, versionCandidate ->
        val previousStabilityLevel = acc.lastOrNull()?.stabilityLevel
            ?: return@fold acc + versionCandidate
        if (versionCandidate.stabilityLevel isMoreStableThan previousStabilityLevel) {
            acc + versionCandidate
        } else acc
    }.asReversed()
}

private suspend fun List<DependencyVersionsFetcher>.getVersionCandidates(
    versionFilter: ((Version) -> Boolean)? = null
): Sequence<DependencyVersionsFetcher.SuccessfulResult> {

    require(isNotEmpty()) { "Cannot get version candidates with an empty fetchers list." }
    val moduleId = first().moduleId
    require(all { it.moduleId == moduleId })

    return coroutineScope {
        map { fetcher ->
            async {
                runCatching {
                    fetcher.getAvailableVersions(versionFilter = versionFilter)
                }.getOrElse { e ->
                    when (e) {
                        is HttpException -> when (e.code()) {
                            403 -> when {
                                fetcher is MavenDependencyVersionsFetcher && fetcher.repoUrl.let {
                                    it.startsWith("https://dl.bintray.com") || it.startsWith("https://jcenter.bintray.com")
                                } -> null // Artifact not available on jcenter nor bintray, post "sunset" announcement.
                                else -> throw e
                            }
                            404 -> null // Normal not found result
                            401 -> null // Returned by some repositories that have optional authentication (like jitpack.io)
                            else -> throw e
                        }
                        is FileNotFoundException -> null
                        else -> throw e
                    }
                }
            }
        }
    }.awaitAll().filterNotNull().also { results ->
        if (results.isEmpty()) throw NoSuchElementException(buildString {
            append("$moduleId not found. ")
            appendln("Searched the following repositories:")
            this@getVersionCandidates.forEach { appendln("- ${it.repoKey}") }
        })
    }.distinctBy {
        it.availableVersions
    }.asSequence()
}
