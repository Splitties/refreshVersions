package de.fayard.versions

import de.fayard.internal.UpdateProperties
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ModuleIdentifier
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
            val dependenciesToUpdate: Sequence<Pair<Dependency, String>> = allDependencies.mapNotNull { dependency ->
                val latestVersion =
                    project.rootProject.getLatestDependencyVersion(extension, dependency) ?: return@mapNotNull null
                val usedVersion = dependency.version //TODO: Resolve version placeholders
                if (usedVersion == latestVersion) null else dependency to latestVersion
            }
            dependenciesToUpdate.onEach { (dependency: Dependency, latestVersion: String) ->
                println("Dependency ${dependency.group}:${dependency.name}:${dependency.version} -> $latestVersion")
            }.toList().let {
                //TODO: Write updates to gradle.properties without overwriting unrelated properties, comments and structure.
                val updateGradleProperties = UpdateProperties()
                val propertiesFile = project.file("versions.properties")
                val existed = propertiesFile.canRead()
                it.first().first.moduleIdentifier?.getVersionPropertyName() // TODO: Replace with actual code
                updateGradleProperties.generateVersionProperties(propertiesFile, TODO())
            }
        } finally {
            project.rootProject.repositories.let {
                it.clear()
                it.addAll(initialRepositories)
            }
        }
    }
}

private val Dependency.moduleIdentifier: ModuleIdentifier?
    get() {
        val group = group ?: return null
        val name = name ?: return null
        return object : ModuleIdentifier {
            override fun getGroup(): String = group
            override fun getName(): String = name
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
