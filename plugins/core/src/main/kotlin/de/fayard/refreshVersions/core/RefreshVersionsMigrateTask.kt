package de.fayard.refreshVersions.core

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import org.intellij.lang.annotations.Language
import java.io.File

open class RefreshVersionsMigrateTask : DefaultTask() {

    @TaskAction
    fun refreshVersionsMissingEntries() {
        addMissingEntriesInVersionsProperties(project)
    }

    @TaskAction
    fun migrateBuild() {
        findFilesWithDependencyNotations(project.rootDir).forEach { buildFile ->
            migrateFileIfNeeded(buildFile)
        }
    }
}

//TODO: Don't rely on a regex to extract the version so we detect absolutely any version string literal.
//TODO: Use CharSequence.findSymbolsRanges(…) to find the plugins block.
//TODO: Use CharSequence.findSymbolsRanges(…) to find the dependencies blocks.
//TODO: Use CharSequence.findSymbolsRanges(…) to find the implementation/api/etc calls.
// Might imply a function name matcher to support dynamic configuration names.
// Also, what about string based configuration names like: "iosApi"("blabla:blabla:1.0.0")?
//TODO: Use CharSequence.findRanges(…) to find the string literals to lookup for maven coordinates and versions.
//TODO: Replace versions with underscore in the Gradle Versions Catalog files.

internal fun migrateFileIfNeeded(file: File) {
    val oldContent = file.readText()
    val newContent = oldContent.lines()
        .detectPluginsBlock()
        .joinToString(separator = "\n") { (line, isPlugin) ->
            replaceVersionWithUnderscore(line, isPlugin) ?: line
        }
    if (newContent != oldContent) {
        println("$ANSI_BLUE  modified: $file$ANSI_RESET")
        file.writeText(newContent)
    }
}

private const val ANSI_RESET = "\u001B[0m"
private const val ANSI_BLUE = "\u001B[34m"

@Language("RegExp")
private val versionRegex =
    "(['\":])(?:\\d+\\.){1,2}\\d+(?:[.-]?(?:alpha|beta|rc|eap|ALPHA|BETA|RC|EAP|RELEASE|Final|M)[-.]?\\d*)?([\"'])".toRegex()

@Language("RegExp")
private val variableRegex =
    "(['\":])(?:\\\$\\{?\\w+ersion}?|\\\$\\w*VERSION|\\\$\\{?(?:versions|rootProject)\\.\\w+}?)(?:[-.]?\\d*)?([\"'])".toRegex()

private val pluginVersionRegex =
    "[. ]version[. (]['\"](\\d+\\.){1,2}\\d+['\"]\\)?".toRegex()

private val underscoreBlackList = setOf(
    "jvmTarget", "versionName",
    "useVersion", "gradleVersion",
    "gradleLatestVersion", "toolVersion",
    "ndkVersion", "force",
    "targetCompatibility", "sourceCompatibility"
)

internal fun replaceVersionWithUnderscore(line: String, inPluginsBlock: Boolean = false): String? = when {
    inPluginsBlock -> line.replace(pluginVersionRegex, "")
    line.trimStart().startsWith("version") -> null
    underscoreBlackList.any { line.contains(it) } -> null
    versionRegex.containsMatchIn(line) -> line.replace(versionRegex, "\$1_\$2")
    variableRegex.containsMatchIn(line) -> line.replace(variableRegex, "\$1_\$2")
    else -> null
}

internal fun findFilesWithDependencyNotations(fromDir: File): List<File> {
    require(fromDir.isDirectory()) { "Expected a directory, got ${fromDir.absolutePath}" }
    val expectedNames = listOf("build", "build.gradle", "deps", "dependencies", "libs", "libraries", "versions")
    val expectedExtensions = listOf("gradle", "kts", "groovy", "kt")
    return fromDir.walkBottomUp().filter {
        it.extension in expectedExtensions && it.nameWithoutExtension.toLowerCase() in expectedNames
    }.toList()
}

/**
 * Takes a list of lines as input.
 * Returns the same lines, along with a boolean indicating if that line is inside a plugins block.
 */
internal fun List<String>.detectPluginsBlock(): List<Pair<String, Boolean>> {
    val result = mutableListOf<Pair<String, Boolean>>()
    var inBlock = false
    for (line in this) {
        if (line.replace("\\s+".toRegex(), " ").contains("plugins {")) {
            result += line to false
            inBlock = true
        } else if (inBlock && line.contains('}')) {
            result += line to false
            inBlock = false
        } else if (inBlock) {
            result += line to true
        } else {
            result += line to false
        }
    }
    return result
}
