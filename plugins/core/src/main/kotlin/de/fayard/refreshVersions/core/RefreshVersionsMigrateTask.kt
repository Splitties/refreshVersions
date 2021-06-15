package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
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


@InternalRefreshVersionsApi
fun migrateFileIfNeeded(file: File) {
    val oldContent = file.readText()
    val newContent = oldContent.lines()
        .detectPluginsBlock()
        .joinToString(separator = "\n") { (line, isPlugin) ->
            replaceVersionWithUndercore(line, isPlugin) ?: line
        }
    if (newContent != oldContent) {
        println("$ANSI_BLUE  modified: $file$ANSI_RESET")
        file.writeText(newContent)
    }
}

private const val ANSI_RESET = "\u001B[0m"
private const val ANSI_BLUE = "\u001B[34m"

@Language("RegExp")
private val underscoreRegex =
    "(['\":])(?:\\\$\\{?\\w+ersion}?|\\\$\\w*VERSION|\\\$\\{?(?:versions|rootProject)\\.\\w+}?|(?:\\d+\\.){1,2}\\d+)(?:[.-]?(?:alpha|beta|rc|eap|ALPHA|BETA|RC|EAP|RELEASE|Final|M)[-.]?\\d*)?([\"'])".toRegex()

private val pluginVersionRegex =
    "[. ]version[. (]['\"](\\d+\\.){1,2}\\d+['\"]\\)?".toRegex()

private val underscoreBlackList = setOf(
    "jvmTarget", "versionName",
    "useVersion", "gradleVersion",
    "gradleLatestVersion", "toolVersion",
    "ndkVersion", "force",
    "targetCompatibility", "sourceCompatibility"
)

@InternalRefreshVersionsApi
fun replaceVersionWithUndercore(line: String, inPluginsBlock: Boolean = false): String? = when {
    inPluginsBlock -> line.replace(pluginVersionRegex, "")
    line.trimStart().startsWith("version") -> null
    underscoreBlackList.any { line.contains(it) } -> null
    underscoreRegex.containsMatchIn(line) -> line.replace(underscoreRegex, "\$1_\$2")
    else -> null
}

@InternalRefreshVersionsApi
fun findFilesWithDependencyNotations(fromDir: File): List<File> {
    require(fromDir.isDirectory()) { "Expected a directory, got ${fromDir.absolutePath}" }
    val expectedNames = listOf("build", "build.gradle", "deps", "dependencies", "libs", "libraries", "versions")
    val expectedExtesions = listOf("gradle", "kts", "groovy", "kt")
    return fromDir.walkBottomUp()
        .filter {
            it.extension in expectedExtesions && it.nameWithoutExtension.toLowerCase() in expectedNames
        }
        .toList()
}


@InternalRefreshVersionsApi
fun List<String>.detectPluginsBlock(): List<Pair<String, Boolean>> {
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
