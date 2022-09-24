package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.extensions.gradle.isRootProject
import org.gradle.api.Project
import org.gradle.api.artifacts.ArtifactRepositoryContainer
import org.gradle.api.artifacts.Dependency
import org.gradle.api.initialization.Settings

internal object UsedVersionForTracker {

    fun clearFor(settings: Settings) {
        if (settings.isBuildSrc) buildSrcHolder = null else projectHolder = null
    }

    fun noteUsedDependencyNotation(
        project: Project,
        moduleId: ModuleId.Maven,
        version: String
    ) {
        editHolder(project) { holder ->
            holder.usedInVersionsFor += VersionForUsage(
                moduleId = moduleId,
                version = version,
                repositories = project.repositories
            )
        }
    }

    fun noteUsedVersionKey(project: Project, versionKey: String) {
        editHolder(project) { holder ->
            holder.usedVersionKeys += versionKey
        }
    }

    fun read(): List<Pair<Dependency, ArtifactRepositoryContainer>> {
        return usedInVersionsFor.map {
            ConfigurationLessDependency(
                moduleId = it.moduleId,
                version = it.version
            ) to it.repositories
        }
    }

    fun readVersionKeys(): List<String> {
        return listOfNotNull(projectHolder, buildSrcHolder).flatMap { it.usedVersionKeys }
    }

    private val usedInVersionsFor: List<VersionForUsage>
        get() = listOfNotNull(projectHolder, buildSrcHolder).flatMap { it.usedInVersionsFor }

    private val lock = Any()

    private inline fun editHolder(project: Project, block: (Holder) -> Unit) {
        val rootProject = project.rootProject
        synchronized(lock) {
            val holder: Holder = if (rootProject.isBuildSrc) {
                if (buildSrcHolder?.rootProject != rootProject) {
                    buildSrcHolder = Holder(rootProject)
                }
                buildSrcHolder!!
            } else {
                if (projectHolder?.rootProject != rootProject) {
                    projectHolder = Holder(rootProject)
                }
                projectHolder!!
            }
            block(holder)
        }
    }

    private var projectHolder: Holder? = null
    private var buildSrcHolder: Holder? = null

    private data class VersionForUsage(
        val moduleId: ModuleId.Maven,
        val version: String,
        val repositories: ArtifactRepositoryContainer
    )

    private class Holder(val rootProject: Project) {
        init {
            require(rootProject.isRootProject)
        }

        val usedInVersionsFor = mutableListOf<VersionForUsage>()
        val usedVersionKeys = mutableListOf<String>()
    }
}
