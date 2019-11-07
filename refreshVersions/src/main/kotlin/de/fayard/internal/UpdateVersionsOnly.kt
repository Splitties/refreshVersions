package de.fayard.internal

object UpdateVersionsOnly {

    const val singleQuote = "'"
    const val doubleQuote = "\""
    const val slashslash = "//"

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

}
