package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.extensions.gradle.isRootProject
import de.fayard.refreshVersions.core.extensions.gradle.registerOrCreate
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import org.gradle.api.Plugin
import org.gradle.api.Project

open class RefreshVersionsCorePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        check(project.isRootProject) { "ERROR: de.fayard.refreshVersions.core should not be applied manually" }
        if (project.isBuildSrc.not()) {
            project.tasks.registerOrCreate<RefreshVersionsTask>(name = "refreshVersions") {
                group = "Help"
                val versionsFileName = RefreshVersionsConfigHolder.versionsPropertiesFile.name
                description = "Search for new dependencies versions and update $versionsFileName"
            }
        }
        cleanFilesFromPreviousVersions(project)
    }

    private fun cleanFilesFromPreviousVersions(project: Project) {
        if (project.isBuildSrc) {
            project.buildDir.resolve("refreshVersions_used_dependencies.txt").delete()
            project.buildDir.resolve("refreshVersions_used_repositories_maven.txt").delete()
        } else {
            project.buildDir.resolve("refreshVersions_used_dependencies_plugins.txt").delete()
            project.buildDir.resolve("refreshVersions_used_repositories_plugins_maven.txt").delete()
        }
    }
}
