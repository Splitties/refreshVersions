package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.RefreshVersionsCorePlugin
import de.fayard.refreshVersions.core.Version
import okhttp3.OkHttpClient

internal object LegacyBoostrapUpdatesFinder {

    suspend fun getSelfUpdates(
        httpClient: OkHttpClient,
        resultMode: VersionCandidatesResultMode
    ): DependencyWithVersionCandidates {
        val moduleId = ModuleId(group = "de.fayard.refreshVersions", name = "refreshVersions")

        val versionsFetchers = RefreshVersionsConfigHolder.settings.getDependencyVersionFetchers(
            httpClient = httpClient,
            dependencyFilter = { dependency ->
                dependency.group == moduleId.group && dependency.name == moduleId.name
            }
        ).toList()

        val currentVersion = RefreshVersionsCorePlugin.currentVersion

        return DependencyWithVersionCandidates(
            moduleId = moduleId,
            currentVersion = currentVersion,
            versionsCandidates = versionsFetchers.getVersionCandidates(
                currentVersion = Version(currentVersion),
                resultMode = resultMode
            )
        )
    }
}
