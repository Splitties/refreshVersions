package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.Version
import org.gradle.api.artifacts.Dependency

internal class VersionCandidatesLookupResult(
    val dependenciesUpdates: List<DependencyWithVersionCandidates>,
    val dependenciesWithHardcodedVersions: List<Dependency>,
    val dependenciesWithDynamicVersions: List<Dependency>,
    val gradleUpdates: List<Version>,
    val settingsPluginsUpdates: List<PluginWithVersionCandidates>,
    val buildSrcSettingsPluginsUpdates: List<PluginWithVersionCandidates>,
    val selfUpdatesForLegacyBootstrap: DependencyWithVersionCandidates?
)
