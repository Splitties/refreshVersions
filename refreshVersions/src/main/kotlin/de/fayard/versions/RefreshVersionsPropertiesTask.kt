package de.fayard.versions

import de.fayard.versions.extensions.isGradlePlugin
import de.fayard.versions.extensions.moduleIdentifier
import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.gradle.kotlin.dsl.getByType

open class RefreshVersionsPropertiesTask : DefaultTask() {

    @Suppress("UnstableApiUsage")
    @Option(description = "Update all versions, I will check git diff afterwards")
    @Optional
    var update: Boolean = false

    @TaskAction
    fun taskActionRefreshVersions() {

        val allConfigurations: Set<Configuration> = project.allprojects.flatMap {
            it.buildscript.configurations + it.configurations
        }.toSet()

        val allDependencies = (
            project.readDependenciesUsedInBuildSrc() +
                allConfigurations.asSequence().flatMap { it.allDependencies.asSequence() }
            )
            .distinctBy { it.group + ':' + it.name + ':' + it.version }

        //TODO: Filter using known grouping strategies to only use the main artifact to resolve latest version, this
        // will reduce the number of repositories lookups, improving performance.

        val allRepositories = project.allprojects.asSequence()
            .flatMap { it.buildscript.repositories.asSequence() + it.repositories }
            .filterIsInstance<MavenArtifactRepository>()
            .map { MavenRepoUrl(it.url.toString()) }
            .plus(project.readExtraUsedRepositories())
            .distinct()
            .toList()

        val extension = project.rootProject.extensions.getByType<RefreshVersionsPropertiesExtension>()

        val versionProperties: Map<String, String> = project.getVersionProperties()

        val dependenciesWithLastVersion: List<Pair<Dependency, List<VersionCandidate>>>
        dependenciesWithLastVersion = allDependencies.mapNotNull { dependency ->

            println("Dependency ${dependency.group}:${dependency.name}:${dependency.version}")
            //TODO: Replace line above with optional diagnostic option, or show status in progress.

            if (dependency.isManageableVersion(versionProperties).not()) {
                return@mapNotNull null //TODO: Keep aside to report hardcoded versions and version ranges,
                //todo... see this issue: https://github.com/jmfayard/buildSrcVersions/issues/126
            }

            val versionCandidates = project.rootProject.getDependencyVersionsCandidates(
                extension = extension,
                repositories = allRepositories,
                group = dependency.group ?: return@mapNotNull null,
                name = dependency.name,
                resolvedVersion = resolveVersion(
                    properties = versionProperties,
                    key = dependency.moduleIdentifier?.getVersionPropertyName() ?: return@mapNotNull null
                )
            )

            return@mapNotNull dependency to versionCandidates
        }.toList()
        project.rootProject.updateVersionsProperties(dependenciesWithLastVersion)
    }

    private fun Dependency.isManageableVersion(versionProperties: Map<String, String>): Boolean {
        return when {
            version == versionPlaceholder -> true
            moduleIdentifier?.isGradlePlugin == true -> {
                val versionFromProperty = versionProperties[moduleIdentifier!!.getVersionPropertyName()]
                    ?: return false
                versionFromProperty.isAVersionAlias().not()
            }
            else -> false
        }
    }
}
