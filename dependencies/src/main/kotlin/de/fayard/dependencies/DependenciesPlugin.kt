package de.fayard.dependencies


import de.fayard.dependencies.internal.registerOrCreate
import de.fayard.dependencies.internal.getArtifactNameToSplittiesConstantMapping
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project


open class DependenciesPlugin : Plugin<Project> {

    override fun apply(project: Project) = with(PluginConfig) {

        project.tasks.registerOrCreate<DefaultTask>(MAPPING_TASK) {
            group = "help"
            description = "Shows the mapping of Gradle dependencies and their typesafe accessors"
            doLast {
                println(getArtifactNameToSplittiesConstantMapping().joinToString("\n"))
            }
        }
    }
}

