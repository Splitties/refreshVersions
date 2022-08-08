package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version

internal class DependencyWithVersionCandidates(
    val moduleId: ModuleId,
    val currentVersion: String, // TODO: Ensure VersionsCatalogUpdater can have the data it needs, and remove this.
    val versionsCandidates: (currentVersion: Version) -> List<Version>,
    val failures: List<DependencyVersionsFetcher.Result.Failure>
)
