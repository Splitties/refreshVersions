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

    @Suppress("UnstableApiUsage")
    @Option(description = "Update all versions, I will check git diff afterwards")
    @Optional
    var update: Boolean = false

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

        var areSomeVersionsNotManageable = false

        val dependenciesWithVersionCandidates: List<Pair<Dependency, List<VersionCandidate>>> = runBlocking {
            allDependencies.mapNotNull { dependency ->

                //TODO: Show status and progress.

                if (dependency.isManageableVersion(versionProperties).not()) {
                    areSomeVersionsNotManageable = true
                    //TODO: Keep aside to report hardcoded versions and version ranges that
                    // are not used. See this issue: https://github.com/jmfayard/refreshVersions/issues/126
                }
                val group = dependency.group ?: return@mapNotNull null
                val resolvedVersion = resolveVersion(
                    properties = versionProperties,
                    key = dependency.moduleIdentifier?.getVersionPropertyName() ?: return@mapNotNull null
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

        if (areSomeVersionsNotManageable) {
            println("Some dependencies lack the version placeholder (the underscore character: '_').")
            println("Their key have been added to versions.properties, but to use it and benefit from easy " +
                "versions upgrading, you'll need to replace the hardcoded version to the version placeholder " +
                "(underscore character).")
            println()
            println("Semi-automatically migrating these is planned. Please, subscribe to the following issue:")
            println("https://github.com/jmfayard/refreshVersions/issues/126")
        }
    }

    private fun Dependency.isManageableVersion(versionProperties: Map<String, String>): Boolean {
        return when {
            version == versionPlaceholder -> true
            @Suppress("UnstableApiUsage")
            reason == becauseRefreshVersions -> true
            moduleIdentifier?.isGradlePlugin == true -> {
                val versionFromProperty = versionProperties[moduleIdentifier!!.getVersionPropertyName()]
                    ?: return false
                versionFromProperty.isAVersionAlias().not()
            }
            else -> false
        }
    }
}
