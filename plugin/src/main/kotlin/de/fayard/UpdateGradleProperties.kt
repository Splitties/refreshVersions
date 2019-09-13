package de.fayard

import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency
import org.gradle.kotlin.dsl.withType

data class UpdateGradleProperties(
    val extension: BuildSrcVersionsExtension,
    val dependencies: List<Dependency>
) {

    fun generateProjectProperties(project: Project) {
        val dependencies: List<DefaultExternalModuleDependency> = project.allprojects.flatMap {
            val classpath: Configuration = it.buildscript.configurations.named("classpath").get()
            classpath.allDependencies.withType()
        }

        val sortedDependencies = dependencies
            .sortedBeautifullyBy { it.group }
            .distinctBy { it.group }

        val newLines = PluginConfig.PLUGIN_NFORMATION_START +
            sortedDependencies.map { it -> "plugin.${it.group}=${it.version}" } +
            PluginConfig.PLUGIN_INFORMATION_END

        updateGradleProperties(
            project = project,
            newLines = newLines,
            removeIf = { line ->
                line.startsWith("plugin.") || line in PluginConfig.PLUGIN_NFORMATION_START + PluginConfig.PLUGIN_INFORMATION_END
            })
    }

    fun generateVersionProperties(project: Project, dependencies: List<Dependency>) {
        val newLines = with(UpdateVersionsOnly) {
            dependencies.map { d: Dependency -> d.asGradleProperty() }
        }

        updateGradleProperties(
            project = project,
            newLines = newLines,
            removeIf = { line ->
                line.startsWith("version.") //|| line in  PluginConfig.PLUGIN_NFORMATION_START + PluginConfig.PLUGIN_INFORMATION_END
            })
    }
}


fun updateGradleProperties(project: Project, newLines: List<String>, removeIf: (String) -> Boolean) {
    val file = project.file("gradle.properties")
    if (!file.exists()) file.createNewFile()

    val existingLines = file.readLines().filterNot { line -> removeIf(line) }

    val newFileContent = newLines + existingLines
    file.writeText(newFileContent.joinToString(separator = "\n"))
}
