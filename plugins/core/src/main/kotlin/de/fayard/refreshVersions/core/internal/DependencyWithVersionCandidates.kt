package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version

internal data class DependencyWithVersionCandidates(
    val moduleId: ModuleId,
    val currentVersion: String,
    val versionsCandidates: List<Version>
)
