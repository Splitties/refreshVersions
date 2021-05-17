package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.Version

internal data class PluginWithVersionCandidates(
    val pluginId: String,
    val currentVersion: String,
    val versionsCandidates: List<Version>
)
