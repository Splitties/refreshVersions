package de.fayard.internal

import org.gradle.api.Project

internal enum class OutputFile(var path: String, var existed: Boolean = false, val alternativePath: String? = null) {
    OUTPUTDIR("buildSrc/src/main/kotlin"),
    BUILD("buildSrc/build.gradle.kts", alternativePath = "buildSrc/build.gradle"),
    GIT_IGNORE("buildSrc/.gitignore"),
    LIBS("buildSrc/src/main/kotlin/Libs.kt"),
    VERSIONS_KT("buildSrc/src/main/kotlin/Versions.kt"),
    GRADLE_PROPERTIES("gradle.properties"),
    VERSIONS_PROPERTIES("versions.properties")
    ;

    fun fileExists(project: Project) = when {
        project.file(path).exists() -> true
        alternativePath != null -> project.file(alternativePath).exists()
        else -> false
    }

    fun logFileWasModified(delete: Boolean = false) {
        logFileWasModified(path, existed, delete)
    }

    companion object {

        fun logFileWasModified(path: String, existed: Boolean, delete: Boolean = false) {
            val ANSI_RESET = "\u001B[0m"
            val ANSI_GREEN = "\u001B[32m"
            val ANSI_RED = "\u001B[31m"
            val color = when {
                delete -> ANSI_RED
                else -> ANSI_GREEN
            }
            val status = when {
                delete  -> "        deleted:    "
                existed -> "        modified:   "
                else -> "        new file:   "
            }
            println("$color$status$path$ANSI_RESET")
        }

    }
}
