package de.fayard.buildSrcLibs

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

class BuildSrcLibsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        project.tasks.register<BuildSrcLibsTask>(
            name = "buildSrcLibs"
        ) {
            group = "help"
            description = "Update buildSrc/src/main/kotlin/Libs.kt"
            outputs.upToDateWhen { false }
        }
    }
}
