package de.fayard.dependencies

import de.fayard.dependencies.internal.registerOrCreate
import de.fayard.dependencies.internal.getArtifactNameToConstantMapping
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project

open class DependenciesPlugin : Plugin<Project> {

    companion object {
        @JvmStatic
        val artifactVersionKeyRules: List<String> = listOf(
            "androidx-version-alias-rules",
            "google-version-alias-rules",
            "kotlin(x)-version-alias-rules",
            "other-version-alias-rules",
            "testing-version-alias-rules"
        ).map {
            DependenciesPlugin::class.java.getResourceAsStream("/refreshVersions-rules/$it.txt").bufferedReader()
                .readText()
        }
    }


    override fun apply(project: Project) = with(PluginConfig) {

        project.tasks.registerOrCreate<DefaultTask>(MAPPING_TASK) {
            group = "help"
            description = "Shows the mapping of Gradle dependencies and their typesafe accessors"
            doLast {
                println(getArtifactNameToConstantMapping().joinToString("\n"))
            }
        }
    }
}

