package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.internal.VersionsCatalogs.LIBS_VERSIONS_TOML
import java.io.File

@InternalRefreshVersionsApi
enum class OutputFile(
    val path: String,
    var existed: Boolean = false,
    val alternativePath: String? = null
) {
    OUTPUT_DIR("buildSrc/src/main/kotlin"),
    BUILD("buildSrc/build.gradle.kts", alternativePath = "buildSrc/build.gradle"),
    GIT_IGNORE("buildSrc/.gitignore"),
    LIBS("buildSrc/src/main/kotlin/Libs.kt"),
    VERSIONS_KT("buildSrc/src/main/kotlin/Versions.kt"),
    VERSIONS_PROPERTIES("versions.properties"),
    SETTINGS_GRADLE("settings.gradle"),
    SETTINGS_GRADLE_KTS("settings.gradle.kts"),
    BUILD_SETTINGS_GRADLE("build/settings.gradle"),
    BUILD_SETTINGS_GRADLE_KTS("build/settings.gradle.kts"),
    GRADLE_VERSIONS_CATALOG(LIBS_VERSIONS_TOML),
    ;

    val file get() = rootDir.resolve(path)

    val alternativeFile: File?
        get() = alternativePath
            ?.let { rootDir.resolve(it) }
            ?.takeIf { it.canRead() }

    fun readText() =  when {
            file.canRead() -> file.readText()
            alternativeFile != null -> alternativeFile!!.readText()
            else -> {
                println("${ANSI_RED}Cannot read file $path ${alternativePath ?: ""} $ANSI_RESET")
                error("File not found $this")
            }
        }

    fun writeText(text: String, mustExists: Boolean = false) = when {
        !mustExists -> file.writeText(text)
        file.exists() -> file.writeText(text)
        alternativeFile != null -> alternativeFile!!.writeText(text)
        else -> {
            println("${ANSI_RED}Cannot write file $path ${alternativePath ?: ""} $ANSI_RESET")
            error("File not found $this")
        }
    }


    fun logFileWasModified(delete: Boolean = false) {
        logFileWasModified(path, existed, delete)
    }

    companion object {
        lateinit var rootDir: File

        val settingsFiles = listOf(SETTINGS_GRADLE, SETTINGS_GRADLE_KTS, BUILD_SETTINGS_GRADLE, BUILD_SETTINGS_GRADLE_KTS)

        // COLORS
        private const val ANSI_RESET = "\u001B[0m"
        private const val ANSI_GREEN = "\u001B[32m"
        private const val ANSI_RED = "\u001B[31m"
        private const val ANSI_BLUE = "\u001B[34m"

        fun logFileWasModified(path: String, existed: Boolean, delete: Boolean = false) {
            val color = when {
                existed -> ANSI_BLUE
                delete -> ANSI_RED
                else -> ANSI_GREEN
            }
            val status = when {
                delete -> "        deleted:    "
                existed -> "        modified:   "
                else -> "        new file:   "
            }
            println("$color$status$path$ANSI_RESET")
        }

        fun checkWhichFilesExist() {
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
