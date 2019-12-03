package de.fayard.internal

import java.io.File

fun Dependency.asGradleProperty(): String {
    val pluginName = versionProperty.substringBeforeLast(".gradle.plugin", "")
    val key = when {
        pluginName.isNotBlank() -> "plugin.$pluginName"
        else -> "version.${versionProperty}"
    }
    val commentPrefix = " available="
    val spacing = PluginConfig.spaces(key.length - commentPrefix.length - 1)
    val newerVersion = newerVersion()
    val available = when {
        newerVersion == null -> ""
        version in listOf("+", "_") -> ""
        else -> "\n#$spacing#$commentPrefix$newerVersion"
    }
    val validatedVersion = when (version) {
        "+", "_" -> newerVersion() ?: version
        else -> version
    }
    return "$key=$validatedVersion$available"
}


class UpdateProperties {

    fun generateVersionProperties(file: File, dependencies: List<Dependency>) {
        PluginConfig.isAndroidProject = dependencies.any { it.group.contains("android") }
        val dependenciesLines = dependencies
            .sortedBy { d -> d.name.contains("gradle.plugin").not() }
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
