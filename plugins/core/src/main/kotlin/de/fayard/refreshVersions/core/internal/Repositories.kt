package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.extensions.gradle.globalRepositories
import org.gradle.api.artifacts.ArtifactRepositoryContainer
import org.gradle.api.artifacts.repositories.ArtifactRepository

fun ArtifactRepositoryContainer.withGlobalRepos(): List<ArtifactRepository> {
    val globalRepos = RefreshVersionsConfigHolder.settings.globalRepositories
    return this.takeIf { globalRepos.isEmpty() } ?: (this + globalRepos)
}
