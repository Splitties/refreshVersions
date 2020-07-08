package de.fayard.refreshVersions.core.internal

import org.gradle.api.artifacts.Dependency

internal class VersionCandidatesLookupResult(
    val dependenciesWithVersionsCandidates: List<DependencyWithVersionCandidates>,
    val dependenciesWithHardcodedVersions: List<Dependency>,
    val dependenciesWithDynamicVersions: List<Dependency>
)
