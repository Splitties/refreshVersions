package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.Version
import org.gradle.api.artifacts.Dependency

internal class VersionCandidatesLookupResult(
    val dependenciesWithVersionsCandidates: List<DependencyWithVersionCandidates>,
    val dependenciesWithHardcodedVersions: List<Dependency>,
    val dependenciesWithDynamicVersions: List<Dependency>,
    val gradleUpdates: List<Version>,
    val selfUpdates: DependencyWithVersionCandidates
)
