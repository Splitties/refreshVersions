package de.fayard

import de.fayard.VersionsOnlyMode.GRADLE_PROPERTIES
import de.fayard.VersionsOnlyMode.GROOVY_DEF
import de.fayard.VersionsOnlyMode.GROOVY_EXT
import de.fayard.VersionsOnlyMode.KOTLIN_OBJECT
import de.fayard.VersionsOnlyMode.KOTLIN_VAL
import java.io.File

fun BuildSrcVersionsExtension.useBuildSrc() = versionsOnlyMode != null

enum class VersionsOnlyMode {
    KOTLIN_VAL,
    KOTLIN_OBJECT,
    GROOVY_DEF,
    GROOVY_EXT,
    GRADLE_PROPERTIES;

    val quote: String get() = when(this) {
        KOTLIN_VAL,
        KOTLIN_OBJECT -> doubleQuote
        GROOVY_DEF,
        GROOVY_EXT -> singleQuote
        GRADLE_PROPERTIES -> ""
    }

    fun suggestedFilename(): String = when(this) {
        KOTLIN_VAL -> "build.gradle.kts"
        KOTLIN_OBJECT -> "Versions.kt"
        GROOVY_DEF -> "build.gradle"
        GROOVY_EXT -> "build.gradle"
        GRADLE_PROPERTIES -> "gradle.properties"
    }

    val comment: String get() = when(this) {
        GRADLE_PROPERTIES -> "#"
        KOTLIN_VAL,
        GROOVY_DEF,
        KOTLIN_OBJECT,
        GROOVY_EXT -> slashslash
    }

    fun beforeAfter(): Pair<String?, String?> = when(this) {
        GROOVY_EXT -> Pair("ext {", "}")
        KOTLIN_VAL,
        KOTLIN_OBJECT,
        GROOVY_DEF,
        GRADLE_PROPERTIES -> Pair(null, null)
    }
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
    return when {
        startOfBlock == -1 -> null
        endOfBlock == -1 -> null
        else -> {
            var indent = lines[endOfBlock].substringBefore(versionsOnlyMode.comment, missingDelimiterValue = "    ")
            if (versionsOnlyMode == GRADLE_PROPERTIES) indent = ""
            SingleModeResult(startOfBlock, endOfBlock, indent)
        }
    }
}

fun regenerateBuildFile(versionsOnlyFile: File?, extension: BuildSrcVersionsExtension, dependencies: List<Dependency>, projectUseKotlin: Boolean = true) {
    val versionsOnlyMode = extension.versionsOnlyMode ?: return
    val parseResult = parseBuildFile(versionsOnlyFile, versionsOnlyMode) ?: SingleModeResult.DEFAULT
    val (startOfBlock, endOfBlock, indent) = parseResult

    val sortedDependencies = dependencies
        .distinctBy { d -> d.versionName }
        .sortedBy { d -> d.versionName }
        .sortedBy { d -> d.versionName.length }

    val newBlock = regenerateBlock(versionsOnlyMode, sortedDependencies, indent)

    if (versionsOnlyFile != null && parseResult != SingleModeResult.DEFAULT) {
        val lines = versionsOnlyFile.readLines()
        val newLines = lines.subList(0, startOfBlock) + newBlock + lines.subList(endOfBlock + 1, lines.size)
        versionsOnlyFile.writeText(newLines.joinWithNewlines() + "\n")
    } else {
        val groovyOrKotlin = if (projectUseKotlin) "VersionsOnlyMode.${versionsOnlyMode}" else "\"${versionsOnlyMode}\""
        println("""
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
        |        """.trimMargin())
        println()
    }
}

fun regenerateBlock(mode: VersionsOnlyMode, dependencies: List<Dependency>, indent: String): List<String> {
    val comment = mode.comment
    val result = mutableListOf<String>()
    var (before, after) = mode.beforeAfter()
    if (mode == GRADLE_PROPERTIES) {
        after = dependencies.availableGradleProperties()
    }

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

fun List<Dependency>.availableGradleProperties(): String? {
    val availables = this.mapNotNull { d: Dependency ->
        d.newerVersion()?.run {
            "#${d.versionName}=$this"
        }
    }
    return when {
        availables.isEmpty() -> null
        else -> "\n## Available updates ##\n" + availables.joinWithNewlines()
    }

}

fun versionOnly(d: Dependency, mode: VersionsOnlyMode, indent: String): String {
    val available = d.versionInformation()
        .replace(doubleQuote, mode.quote)
        .replace(slashslash, mode.comment)
        .replace(newline, "")

    return when(mode) {
        KOTLIN_OBJECT -> throw IllegalStateException("KOTLIN_OBJECT should not be handled here")
        KOTLIN_VAL -> """${indent}val ${d.versionName} = "${d.version}"$available"""
        GROOVY_DEF -> """${indent}def ${d.versionName} = '${d.version}'$available"""
        GROOVY_EXT -> """${indent}${indent}${d.versionName} = '${d.version}'$available"""
        GRADLE_PROPERTIES -> "${d.versionName}=${d.version}"
    }
}

private val singleQuote = "'"
private val doubleQuote = "\""
private val slashslash = "//"
private val newline = "\n"

fun List<String>.joinWithNewlines() : String =
    this.joinToString(separator = "\n")
