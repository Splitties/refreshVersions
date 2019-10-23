package de.fayard.internal

import de.fayard.BuildSrcVersionsExtension
import org.gradle.api.Project

internal enum class OutputFile(var path: String, var existed: Boolean = false, val alternativePath: String? = null) {
    OUTPUTDIR("buildSrc/src/main/kotlin"),
    BUILD("buildSrc/build.gradle.kts", alternativePath = "buildSrc/build.gradle"),
    GIT_IGNORE("buildSrc/.gitignore"),
    LIBS("buildSrc/src/main/kotlin/Libs.kt"),
    VERSIONS("buildSrc/src/main/kotlin/Versions.kt"),
    GRADLE_PROPERTIES("gradle.properties");

    fun fileExists(project: Project) = when {
        project.file(path).exists() -> true
        alternativePath != null -> project.file(alternativePath).exists()
        else -> false
    }

    fun logFileWasModified() {
        logFileWasModified(path, existed)
    }

    companion object {

        fun logFileWasModified(path: String, existed: Boolean) {
            val ANSI_RESET = "\u001B[0m"
            val ANSI_GREEN = "\u001B[32m"

            val status = if (existed) {
                "        modified:   "
            } else {
                "        new file:   "
            }
            println("$ANSI_GREEN$status$path$ANSI_RESET")
        }

    }
}
