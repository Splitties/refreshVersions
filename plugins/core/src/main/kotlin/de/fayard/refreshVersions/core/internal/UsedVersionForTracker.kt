package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
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

    fun read(): Sequence<Pair<Dependency, ArtifactRepositoryContainer>> {
        return usedInVersionsFor.asSequence().map {
            ConfigurationLessDependency(
                it.moduleId,
                it.version
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
        synchronized(lock) {
            val holder: Holder = if (project.isBuildSrc) {
                if (buildSrcHolder?.project != project) {
                    buildSrcHolder = Holder(project)
                }
                buildSrcHolder!!
            } else {
                if (projectHolder?.project != project) {
                    projectHolder = Holder(project)
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

    private class Holder(val project: Project) {
        val usedInVersionsFor = mutableListOf<VersionForUsage>()
        val usedVersionKeys = mutableListOf<String>()
    }
}
