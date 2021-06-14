package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.extensions.gradle.isRootProject
import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import org.slf4j.Marker
import org.slf4j.helpers.BasicMarkerFactory

open class RefreshVersionsCorePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        check(project.isRootProject) { "ERROR: de.fayard.refreshVersions.core should not be applied manually" }
        if (project.isBuildSrc.not()) {
            // In the case where this runs in includedBuilds, the task configuration lambda may (will) run
            // after the `Gradle.buildFinished { … }` callback is executed, which means the
            // RefreshVersionsConfigHolder content will be cleared, so we get the value before.
            val versionsFileName = RefreshVersionsConfigHolder.versionsPropertiesFile.name

            project.tasks.register<RefreshVersionsTask>(name = "refreshVersions") {
                group = "refreshVersions"
                description = "Search for new dependencies versions and update $versionsFileName"
            }

            project.tasks.register<RefreshVersionsCleanupTask>(name = "refreshVersionsCleanup") {
                group = "refreshVersions"
                description = "Cleanup versions availability comments"
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

    @InternalRefreshVersionsApi
    object LogMarkers {
        @JvmField
        val default: Marker = BasicMarkerFactory().getMarker("refreshVersions")
    }

    @InternalRefreshVersionsApi
    companion object {
        val currentVersion by lazy {
            RefreshVersionsCorePlugin::class.java.getResourceAsStream("/version.txt")!!
                .bufferedReader()
                .useLines { it.first() }
        }
    }
}
