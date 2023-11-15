package de.fayard.refreshVersions.core.extensions.gradle

import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
import org.gradle.api.artifacts.repositories.ArtifactRepository
import org.gradle.api.initialization.Settings

@InternalRefreshVersionsApi
val Settings.isBuildSrc: Boolean
    get() = rootProject.name == "buildSrc"

internal val Settings.globalRepositories: List<ArtifactRepository>
    get() = runCatching {
        @Suppress("UnstableApiUsage")
        dependencyResolutionManagement.repositories
    }.onFailure { println(it) }.getOrDefault(emptyList())
