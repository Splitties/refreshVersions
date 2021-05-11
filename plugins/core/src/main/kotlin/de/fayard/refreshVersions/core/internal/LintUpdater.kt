package de.fayard.refreshVersions.core.internal

import org.gradle.api.initialization.Settings

// Disable a warning in Android Studio that AGP has version "_"
fun configureLintOnAndroidProjects(settings: Settings, versionsMap: Map<String, String>) {
    if ("plugin.android" !in versionsMap) return
    val file = settings.rootDir.resolve("lint.xml")
    val currentLintXml = when {
        file.canRead() -> file.readText()
        else -> ""
    }
    val newLintXml = updateLintXml(currentLintXml)
    if (newLintXml !== currentLintXml) {
        file.writeText(newLintXml)
    }
}

fun updateLintXml(input: String): String {
    val alreadyAdded = input.contains("GradleDependency") && input.contains("GradlePluginVersion")
    if (alreadyAdded) return input
    val ignoreRules = """
        |    <issue id="GradleDependency" severity="ignore" />
        |    <issue id="GradlePluginVersion" severity="ignore" />
    """.trimMargin()

    val prefix = when {
        input.isBlank() -> """
            <?xml version="1.0" encoding="UTF-8"?>
            <lint>
        """.trimIndent()
        else -> input.substringBefore("</lint>").trimEnd()
    }
    val postfix = "</lint>"
    return """
        |$prefix
        |$ignoreRules
        |$postfix
    """.trimMargin()
}
