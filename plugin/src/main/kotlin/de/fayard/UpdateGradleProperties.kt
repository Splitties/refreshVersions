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

        val file = project.file("gradle.properties")
        if (!file.exists()) file.createNewFile()

        val existingLines = file.readLines().filterNot {
            it.startsWith("plugin.") || it in  PluginConfig.PLUGIN_NFORMATION_START + PluginConfig.PLUGIN_INFORMATION_END
        }
        val newLines = sortedDependencies.map { it ->
            "plugin.${it.group}=${it.version}"
        }
        val newFileContent = PluginConfig.PLUGIN_NFORMATION_START + newLines + existingLines + PluginConfig.PLUGIN_INFORMATION_END
        file.writeText(newFileContent.joinToString(separator = "\n"))
    }
}
