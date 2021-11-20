package de.fayard.refreshVersions.core.extensions.gradle

import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
import org.gradle.api.artifacts.repositories.ArtifactRepository
import org.gradle.api.initialization.Settings
import org.gradle.util.GradleVersion

@InternalRefreshVersionsApi
val Settings.isBuildSrc: Boolean
    get() = rootProject.name == "buildSrc"

internal val Settings.globalRepositories: List<ArtifactRepository>
    get() = when {
        GradleVersion.current().baseVersion >= gradle7 -> runCatching {
            @Suppress("UnstableApiUsage")
            dependencyResolutionManagement.repositories
        }.onFailure { println(it) }.getOrDefault(emptyList())
        else -> emptyList()
    }

private val gradle7 = GradleVersion.version("7.0")
