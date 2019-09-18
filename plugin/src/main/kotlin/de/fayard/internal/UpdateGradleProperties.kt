package de.fayard.internal

import de.fayard.BuildSrcVersionsExtension
import org.gradle.api.Project

data class UpdateGradleProperties(
    val extension: BuildSrcVersionsExtension
) {

    fun generateVersionProperties(project: Project, dependencies: List<Dependency>) = with(UpdateVersionsOnly) {
        val dependenciesLines = dependencies
            .sortedBy { d -> d.versionName.contains("gradle_plugin").not() }
            .map { d -> d.asGradleProperty() }

        val newLines = with(PluginConfig) {
            REFRESH_VERSIONS_START + dependenciesLines + REFRESH_VERSIONS_END
        }

        updateGradleProperties(
            project = project,
            newLines = newLines,
            removeIf = { line -> line.wasGeneratedByPlugin() }
        )
    }

    fun String.wasGeneratedByPlugin(): Boolean = when {
        startsWith("version.") -> true
        startsWith("plugin.") -> true
        contains("# available=") -> true
        this in PluginConfig.ALL_GRADLE_PROPERTIES_LINES -> true
        else -> false
    }

    fun updateGradleProperties(project: Project, newLines: List<String>, removeIf: (String) -> Boolean) {
        val file = project.file("gradle.properties")
        if (!file.exists()) file.createNewFile()

        val existingLines = file.readLines().filterNot { line -> removeIf(line) }

        val newFileContent = existingLines + newLines
        file.writeText(newFileContent.joinToString(separator = "\n"))
    }

}
