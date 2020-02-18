package de.fayard.versions

import de.fayard.versions.extensions.isGradlePlugin
import de.fayard.versions.extensions.moduleIdentifier
import de.fayard.versions.internal.*
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

open class RefreshVersionsPropertiesTask : DefaultTask() {

    /*
    @Suppress("UnstableApiUsage")
    @Option(description = "Update all versions, I will check git diff afterwards")
    @Optional
    var update: Boolean = false
    */

    @TaskAction
    fun taskActionRefreshVersions() {

        val allConfigurations: Set<Configuration> = project.allprojects.flatMap { it.configurations }.toSet()

        val allDependencies = (
            project.readPluginsAndBuildSrcDependencies() +
                allConfigurations.asSequence().flatMap { it.allDependencies.asSequence() }
            )
            .distinctBy { it.group + ':' + it.name + ':' + it.version }

        //TODO: Filter using known grouping strategies to only use the main artifact to resolve latest version, this
        // will reduce the number of repositories lookups, improving performance a little more.

        val allRepositories = project.allprojects.asSequence()
            .flatMap { it.buildscript.repositories.asSequence() + it.repositories }
            .filterIsInstance<MavenArtifactRepository>()
            .map { MavenRepoUrl(it.url.toString()) }
            .plus(project.readPluginsAndBuildSrcRepositories())
            .distinct()
            .toList()

        val versionProperties: Map<String, String> = project.getVersionProperties()

        val versionKeyReader = project.retrieveVersionKeyReader()

        val dependenciesWithVersionCandidates: List<Pair<Dependency, List<VersionCandidate>>> = runBlocking {
            allDependencies.mapNotNull { dependency ->

                //TODO: Show status and progress.

                // TODO: I think this should be done for all dependencies!!
                if (dependency.isManageableVersion(versionProperties, versionKeyReader).not()) {
                    return@mapNotNull null //TODO: Keep aside to report hardcoded versions and version ranges,
                    //todo... see this issue: https://github.com/jmfayard/buildSrcVersions/issues/126
                }
                val group = dependency.group ?: return@mapNotNull null
                val resolvedVersion = resolveVersion(
                    properties = versionProperties,
                    key = dependency.moduleIdentifier?.let {
                        getVersionPropertyName(it, versionKeyReader)
                    } ?: return@mapNotNull null
                )
                async {
                    dependency to getDependencyVersionsCandidates(
                        repositories = allRepositories,
                        group = group,
                        name = dependency.name,
                        resolvedVersion = resolvedVersion
                    )
                }
            }.toList().awaitAll()
        }
        project.rootProject.updateVersionsProperties(dependenciesWithVersionCandidates)
    }

    private fun Dependency.isManageableVersion(
        versionProperties: Map<String, String>,
        versionKeyReader: ArtifactVersionKeyReader
    ): Boolean {
        return when {
            version == versionPlaceholder -> true
            @Suppress("UnstableApiUsage")
            reason == becauseRefreshVersions -> true
            moduleIdentifier?.isGradlePlugin == true -> {
                val versionFromProperty = versionProperties[getVersionPropertyName(moduleIdentifier!!, versionKeyReader)]
                    ?: return false
                versionFromProperty.isAVersionAlias().not()
            }
            else -> false
        }
    }
}
