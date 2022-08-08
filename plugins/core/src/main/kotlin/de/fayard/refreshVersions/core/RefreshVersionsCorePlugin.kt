package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.extensions.gradle.isRootProject
import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
import de.fayard.refreshVersions.core.internal.OutputFile
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.core.internal.skipConfigurationCache
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import org.slf4j.Marker
import org.slf4j.helpers.BasicMarkerFactory

open class RefreshVersionsCorePlugin : Plugin<Project> {

    @InternalRefreshVersionsApi
    companion object {
        const val GROUP = "refreshVersions"
        const val refreshVersionsUrl = "https://github.com/jmfayard/refreshVersions"

        val currentVersion: String by lazy { readCurrentPluginVersion() }
    }

    override fun apply(project: Project) {
        check(project.isRootProject) { "ERROR: de.fayard.refreshVersions.core should not be applied manually" }
        OutputFile.rootDir = project.rootDir
        if (project.isBuildSrc.not()) {
            // In the case where this runs in includedBuilds, the task configuration lambda may (will) run
            // after RefreshVersionsConfigHolder content is cleared (via its ClearStaticStateBuildService),
            // so we get the value before.
            val versionsFileName = RefreshVersionsConfigHolder.versionsPropertiesFile.name

            project.tasks.register<RefreshVersionsTask>(RefreshVersionsTask.TASK_NAME) {
                group = GROUP
                description = RefreshVersionsTask.DESCRIPTION + " and update $versionsFileName"
                skipConfigurationCache()
                if (FeatureFlag.DEPENDENCIES_DOC.isEnabled) {
                    finalizedBy("")
                }
            }

            project.tasks.register<RefreshVersionsCleanupTask>(RefreshVersionsCleanupTask.TASK_NAME) {
                group = GROUP
                description = RefreshVersionsCleanupTask.DESCRIPTION
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
}

internal fun readCurrentPluginVersion(): String =
    RefreshVersionsCorePlugin::class.java
        .getResourceAsStream("/version.txt")!!
        .bufferedReader()
        .useLines { it.first() }
