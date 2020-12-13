package de.fayard.buildSrcLibs

import org.gradle.api.Plugin
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.register

class BuildSrcLibsPlugin : Plugin<Settings> {

    override fun apply(settings: Settings) {
        settings.apply(plugin = "de.fayard.refreshVersions")
        settings.gradle.rootProject {
            project.tasks.register<BuildSrcLibsTask>(
                name = "buildSrcLibs"
            ) {
                group = "help"
                description = "Update buildSrc/src/main/kotlin/Libs.kt"
                outputs.upToDateWhen { false }
            }
        }
    }
}
