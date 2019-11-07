package de.fayard.versions

import de.fayard.versions.extensions.isGradlePlugin
import de.fayard.versions.extensions.moduleIdentifier
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.specs.Specs
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
        val allDependencies = allConfigurations.asSequence()
            .flatMap { it.allDependencies.asSequence() }
            .distinctBy { it.group + it.name }

        //TODO: Filter using known grouping strategies to only use the main artifact to resolve latest version, this
        // will reduce the number of repositories lookups, improving performance.

        val initialRepositories = project.rootProject.repositories
        project.allprojects.flatMap { it.buildscript.repositories + it.repositories }.toSet().let { allRepositories ->
            project.rootProject.repositories.addAll(allRepositories)
        }
        try {
            val extension = project.rootProject.extensions.getByType<RefreshVersionsPropertiesExtension>()

            val versionProperties: Map<String, String> = project.getVersionProperties()
            val dependenciesWithUpdate: List<Pair<Dependency, String?>> = allDependencies.mapNotNull { dependency ->

                println("Dependency ${dependency.group}:${dependency.name}:${dependency.version}")

                val usedVersion = dependency.version.takeIf {
                    dependency.isManageableVersion(versionProperties)
                } ?: return@mapNotNull null //TODO: Keep aside to report hardcoded versions and version ranges,
                //todo... see this issue: https://github.com/jmfayard/buildSrcVersions/issues/126

                val latestVersion = project.rootProject.getLatestDependencyVersion(extension, dependency)

                return@mapNotNull dependency to (if (usedVersion == latestVersion) null else latestVersion)
            }.toList()
            project.rootProject.updateVersionsProperties(dependenciesWithUpdate)
        } finally {
            project.rootProject.repositories.let {
                it.clear()
                it.addAll(initialRepositories)
            }
        }
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

private fun Project.getLatestDependencyVersion(
    extension: RefreshVersionsPropertiesExtension,
    dependency: Dependency
): String? {
    val tmpDependencyUpdateConfiguration = configurations.create("getLatestVersion") {
        dependencies.add(dependency)
        resolutionStrategy.componentSelection.all {
            val componentSelectionData = ComponentSelectionData(
                currentVersion = dependency.version ?: "",
                candidate = candidate
            )
            extension.rejectVersionsPredicate?.let { rejectPredicate ->
                if (rejectPredicate(componentSelectionData)) {
                    reject("Rejected in rejectVersionsIf { ... }")
                }
            }
            extension.acceptVersionsPredicate?.let { acceptPredicate ->
                if (acceptPredicate(componentSelectionData).not()) {
                    reject("Not accepted in acceptVersionOnlyIf { ... }")
                }
            }
        }
        resolutionStrategy.eachDependency {
            if (requested.version != null) useVersion("+")
        }
    }
    try {
        val lenientConfiguration = tmpDependencyUpdateConfiguration.resolvedConfiguration.lenientConfiguration
        return lenientConfiguration.getFirstLevelModuleDependencies(Specs.SATISFIES_ALL).singleOrNull()?.moduleVersion
    } finally {
        configurations.remove(tmpDependencyUpdateConfiguration)
    }
}
