package de.fayard.versions

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.specs.Specs
import org.gradle.api.tasks.TaskAction

open class RefreshVersionsPropertiesTask : DefaultTask() {

    @TaskAction
    fun taskActionRefreshVersions() {
        TODO()
    }
}



private fun Project.getLatestDependencyVersion(dependency: Dependency): String? {
    val tmpDependencyUpdateConfiguration = configurations.create("getLatestVersion") {
        dependencies.add(dependency)
        resolutionStrategy.componentSelection.all {
            TODO("Filter versions rejected by user config")
            //if (isVersionStable(candidate.version).not()) reject("Unstable version")
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
