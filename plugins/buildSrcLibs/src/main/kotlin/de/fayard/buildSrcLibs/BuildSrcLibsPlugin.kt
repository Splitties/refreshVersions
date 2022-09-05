package de.fayard.buildSrcLibs

import de.fayard.buildSrcLibs.BuildSrcTask.Companion.TASK_NAME_buildSrcLibs
import de.fayard.buildSrcLibs.BuildSrcTask.Companion.TASK_NAME_buildSrcVersions
import de.fayard.refreshVersions.core.RefreshVersionsCorePlugin
import de.fayard.refreshVersions.core.internal.skipConfigurationCache
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class BuildSrcLibsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        listOf(TASK_NAME_buildSrcLibs, TASK_NAME_buildSrcVersions).forEach {
            name -> registerBuildSrcTAsk(project, name)
        }
    }

    private fun registerBuildSrcTAsk(project: Project, name: String) {
        project.tasks.register<BuildSrcTask>(name) {
            group = RefreshVersionsCorePlugin.GROUP
            description = BuildSrcTask.DESCRIPTION
            outputs.upToDateWhen { false }
            skipConfigurationCache()
        }
    }
}
