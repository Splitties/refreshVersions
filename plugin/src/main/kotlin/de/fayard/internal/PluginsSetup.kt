package de.fayard.internal

import org.gradle.api.GradleException
import org.gradle.api.Project

object PluginsSetup {
    const val PLUGIN_GRADLE_KTS = "plugins.gradle.kts"
    const val SETTINGS_GRADLE_KTS = "settings.gradle.kts"
    const val OK = "✔ \uD83C\uDD97"

    const val INCLUDE_PLUGINS_GRADLE_KTS = """
pluginManagement {
    repositories {
        google()  // for Android projects only
        jcenter() // if you use plugins not published on the gradlePluginPortal()
        gradlePluginPortal()
    }
}
apply(from = "plugins.gradle.kts")
// rootProject.name = xxx
// include(":app")
"""

    fun pluginFileContent(): String {
        return this::class.java.getResourceAsStream("/$PLUGIN_GRADLE_KTS").reader().readText()
    }

    fun copyPluginsGradleKtsIfNeeded(project: Project) {
        println("copyPluginsGradleKtsIfNeeded")
        val file = project.rootProject.file(PLUGIN_GRADLE_KTS)
        val settingsFile = project.rootProject.file(SETTINGS_GRADLE_KTS)
        if (settingsFile.canRead() && file.canRead().not()) {
            file.writeText(pluginFileContent())
            println("$OK Created file ./$PLUGIN_GRADLE_KTS")
            throw GradleException(
                """
                |Action required: Please include $PLUGIN_GRADLE_KTS file in $SETTINGS_GRADLE_KTS
                |    // ./$SETTINGS_GRADLE_KTS
                |    $INCLUDE_PLUGINS_GRADLE_KTS
                | """.trimMargin()
            )
        }

    }
}
