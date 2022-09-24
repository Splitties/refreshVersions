package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.extensions.gradle.globalRepositories
import org.gradle.api.artifacts.repositories.ArtifactRepository

fun List<ArtifactRepository>.withGlobalRepos(): List<ArtifactRepository> {
    val globalRepos = RefreshVersionsConfigHolder.settings.globalRepositories
    return when {
        globalRepos.isEmpty() -> this
        this.isEmpty() -> globalRepos
        else -> this + globalRepos
    }
}

fun List<ArtifactRepository>.withPluginsRepos(): List<ArtifactRepository> {
    val pluginManagementRepos = RefreshVersionsConfigHolder.settings.pluginManagement.repositories
    return when {
        pluginManagementRepos.isEmpty() -> this
        this.isEmpty() -> pluginManagementRepos
        else -> this + pluginManagementRepos
    }
}
