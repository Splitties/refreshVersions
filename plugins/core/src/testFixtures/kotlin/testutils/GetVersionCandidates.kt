package testutils

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.core.internal.MavenDependencyVersionsFetcher
import de.fayard.refreshVersions.core.internal.VersionCandidatesResultMode
import de.fayard.refreshVersions.core.internal.getVersionCandidates
import okhttp3.OkHttpClient

// NOTE: Errors about symbols being internal can safely be ignored here, it's an IDE issue.
//TODO: Remove comment above when https://youtrack.jetbrains.com/issue/KT-34102 is fixed.

suspend fun getVersionCandidates(
    httpClient: OkHttpClient,
    moduleId: ModuleId,
    currentVersion: Version,
    repoUrls: List<String>
): List<Version> = repoUrls.map {
    MavenDependencyVersionsFetcher(
        httpClient = httpClient,
        moduleId = moduleId,
        repoUrl = it,
        repoAuthorization = null
    )
}.getVersionCandidates(
    currentVersion = currentVersion,
    resultMode = VersionCandidatesResultMode(
        filterMode = VersionCandidatesResultMode.FilterMode.LatestByStabilityLevel,
        sortingMode = VersionCandidatesResultMode.SortingMode.ByVersion
    )
)
