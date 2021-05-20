package de.fayard.refreshVersions.core.internal

import org.gradle.api.Project
import java.io.File

@InternalRefreshVersionsApi
enum class OutputFile(var path: String, var existed: Boolean = false, val alternativePath: String? = null) {
    OUTPUT_DIR("buildSrc/src/main/kotlin"),
    BUILD("buildSrc/build.gradle.kts", alternativePath = "buildSrc/build.gradle"),
    GIT_IGNORE("buildSrc/.gitignore"),
    LIBS("buildSrc/src/main/kotlin/Libs.kt"),
    VERSIONS_KT("buildSrc/src/main/kotlin/Versions.kt"),
    GRADLE_PROPERTIES("gradle.properties"),
    VERSIONS_PROPERTIES("versions.properties")
    ;



    fun logFileWasModified(delete: Boolean = false) {
        logFileWasModified(path, existed, delete)
    }

    companion object {
        // COLORS
        private const val ANSI_RESET = "\u001B[0m"
        private const val ANSI_GREEN = "\u001B[32m"
        private const val ANSI_RED = "\u001B[31m"

        fun logFileWasModified(path: String, existed: Boolean, delete: Boolean = false) {
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

        fun checkWhichFilesExist(rootDir: File) {
            values().forEach { outputFile ->
                outputFile.existed = when {
                    rootDir.resolve(outputFile.path).exists() -> true
                    outputFile.alternativePath == null -> false
                    else -> rootDir.resolve(outputFile.alternativePath).exists()
                }
            }
        }

    }
}
