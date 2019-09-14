package de.fayard.internal

import de.fayard.VersionsOnlyMode
import java.io.File

object UpdateVersionsOnly {

    const val singleQuote = "'"
    const val doubleQuote = "\""
    const val slashslash = "//"
    const val newline = "\n"

    fun Dependency.asGradleProperty(): String {
        val escapedVersionName = versionName.replace(":", ".").replace("_", ".").replace("-", ".")
        val pluginName = escapedVersionName.substringBeforeLast(".gradle.plugin", "")
        val key = when {
            pluginName.isNotBlank() -> "plugin.$pluginName"
            else -> "version.${escapedVersionName}"
        }
        val commentPrefix = " available="
        val spacing = PluginConfig.spaces(key.length - commentPrefix.length)
        val available = when (val v = newerVersion()) {
            null -> ""
            else -> "\n$spacing#$commentPrefix$v"
        }
        return "$key=$version$available"
    }

    fun parseBuildFileOrNew(versionsOnlyFile: File?, versionsOnlyMode: VersionsOnlyMode, fromDir: File): Pair<File, SingleModeResult> = when {
        versionsOnlyMode == VersionsOnlyMode.GRADLE_PROPERTIES -> {
            val file = versionsOnlyFile ?: fromDir.resolve("gradle.properties")
            if (file.canRead().not()) file.createNewFile()
            Pair(file, SingleModeResult.NEW_FILE)
        }
        versionsOnlyFile == null || versionsOnlyFile.canRead().not() -> Pair(
            fromDir.resolve(versionsOnlyMode.suggestedFilename()),
            SingleModeResult.NEW_FILE.copy(indentation = versionsOnlyMode.defaultIndent)
        )
        else -> Pair(
            versionsOnlyFile,
            parseBuildFile(versionsOnlyFile, versionsOnlyMode) ?: SingleModeResult.BLOC_NOT_FOUND
        )
    }

    fun parseBuildFile(versionsOnlyFile: File?, versionsOnlyMode: VersionsOnlyMode): SingleModeResult? {
        if (versionsOnlyFile == null || versionsOnlyFile.canRead().not()) {
            return null
        }

        val lines = versionsOnlyFile.readLines()
        val startOfBlock = lines.indexOfFirst {
            it.trim().endsWith(PluginConfig.VERSIONS_ONLY_START)
        }
        val endOfBlock = lines.indexOfFirst {
            it.trim().endsWith(PluginConfig.VERSIONS_ONLY_END)
        }
        val noBlockFound = startOfBlock == -1 || endOfBlock == -1
        return when {
            noBlockFound -> null
            else -> {
                var indent = lines[endOfBlock].substringBefore(versionsOnlyMode.comment, missingDelimiterValue = PluginConfig.SPACES4)
                if (versionsOnlyMode == VersionsOnlyMode.GRADLE_PROPERTIES) indent = ""
                SingleModeResult(startOfBlock, endOfBlock, indent)
            }
        }
    }

    fun regenerateBuildFile(
        versionsOnlyFile: File?,
        versionsOnlyMode: VersionsOnlyMode,
        sortedDependencies: List<Dependency>,
        projectUseKotlin: Boolean = true
    ) {
        val (file, parseResult) = parseBuildFileOrNew(versionsOnlyFile, versionsOnlyMode, File("."))
        val (startOfBlock, endOfBlock, indent) = parseResult

        val newBlock = regenerateBlock(versionsOnlyMode, sortedDependencies, indent)

        if (parseResult != SingleModeResult.BLOC_NOT_FOUND) {
            val lines = file.readLines()
            val newLines = lines.subList(0, startOfBlock) + newBlock + lines.subList(endOfBlock + 1, lines.size)
            file.writeText(newLines.joinWithNewlines() + "\n")

        } else {
            val groovyOrKotlin = if (projectUseKotlin) "VersionsOnlyMode.${versionsOnlyMode}" else "\"${versionsOnlyMode}\""
            println(
                """
        |            
        |== 📋 copy-paste needed! 📋 ==
        |
        |Copy-paste the snippet below:
        |
        |${newBlock.joinWithNewlines()}
        |
        |in the file you configure with something like:
        | 
        |// build.gradle(.kts) 
        |buildSrcVersions {
        |    versionsOnlyMode = $groovyOrKotlin
        |    versionsOnlyFile = "${versionsOnlyMode.suggestedFilename()}"            
        |}
        |
        |See ${PluginConfig.issue54VersionOnlyMode}
        |        """.trimMargin()
            )
            println()
        }
    }

    fun regenerateBlock(mode: VersionsOnlyMode, dependencies: List<Dependency>, indent: String): List<String> {
        val comment = mode.comment
        val result = mutableListOf<String>()
        var (before, after) = mode.beforeAfter()

        result += PluginConfig.VERSIONS_ONLY_INTRO.map { "$indent$comment $it" }

        before?.run {
            result += "$indent$before"
        }

        result += dependencies.map { versionOnly(it, mode, indent) }

        after?.run {
            result += "$indent$after"
        }

        result += "$indent$comment ${PluginConfig.VERSIONS_ONLY_END}"

        return result
    }

    fun versionOnly(d: Dependency, mode: VersionsOnlyMode, indent: String): String {
        val available = d.versionInformation()
            .replace(doubleQuote, mode.quote)
            .replace(slashslash, mode.comment)
            .replace(newline, "")
        val postIndent = if (indent.isNotBlank()) indent else PluginConfig.SPACES4

        return when (mode) {
            VersionsOnlyMode.KOTLIN_OBJECT -> throw IllegalStateException("$mode should not be handled here")
            VersionsOnlyMode.GRADLE_PROPERTIES -> d.asGradleProperty()
            VersionsOnlyMode.KOTLIN_VAL -> """${indent}val ${d.versionName} = "${d.version}"$available"""
            VersionsOnlyMode.GROOVY_DEF -> """${indent}def ${d.versionName} = '${d.version}'$available"""
            VersionsOnlyMode.GROOVY_EXT -> """${indent}${postIndent}${d.versionName} = '${d.version}'$available"""
        }.trimEnd()
    }


    fun List<String>.joinWithNewlines(): String =
        this.joinToString(separator = "\n")
}
