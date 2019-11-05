package de.fayard.internal

import java.io.File

class UpdateProperties() {

    fun generateVersionProperties(file: File, dependencies: List<Dependency>) = with(UpdateVersionsOnly) {
        PluginConfig.isAndroidProject = dependencies.any { it.group.contains("android") }
        val dependenciesLines = dependencies
            .sortedBy { d -> d.versionName.contains("gradle_plugin").not() }
            .map { d -> d.asGradleProperty() }

        val newLines = with(PluginConfig) {
            REFRESH_VERSIONS_START + MODULES + dependenciesLines + REFRESH_VERSIONS_END
        }

        updateGradleProperties(
            file = file,
            newLines = newLines,
            removeIf = { line -> line.wasGeneratedByPlugin() }
        )
    }

    private fun String.wasGeneratedByPlugin(): Boolean = when {
        startsWith("module.kotlin") -> true
        startsWith("module.android") -> true
        startsWith("version.") -> true
        startsWith("plugin.") -> true
        contains("# available=") -> true
        this in PluginConfig.ALL_GRADLE_PROPERTIES_LINES -> true
        else -> false
    }

    private fun updateGradleProperties(file: File, newLines: List<String>, removeIf: (String) -> Boolean) {
        if (!file.exists()) file.createNewFile()

        val existingLines = file.readLines().filterNot { line -> removeIf(line) }

        val newFileContent = existingLines + newLines
        file.writeText(newFileContent.joinToString(separator = "\n"))
    }

}
