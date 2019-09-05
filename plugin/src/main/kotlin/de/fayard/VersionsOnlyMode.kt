package de.fayard

import java.io.File

fun BuildSrcVersionsExtension.useBuildSrc() = versionsOnlyMode != null

enum class VersionsOnlyMode {
    KOTLIN_VAL,
    KOTLIN_OBJECT,
    GROOVY_DEF,
    GROOVY_EXT;

    fun useSingleQuote(): Boolean =
        this == GROOVY_DEF || this == GROOVY_EXT

    fun suggestedFilename(): String = when(this) {
        KOTLIN_VAL -> "build.gradle.kts"
        KOTLIN_OBJECT -> "Versions.kt"
        GROOVY_DEF -> "build.gradle"
        GROOVY_EXT -> "build.gradle"
    }
}


fun parseBuildFile(versionsOnlyFile : File?): SingleModeResult? {
    return when {
        versionsOnlyFile == null -> null
        versionsOnlyFile.canRead().not() -> null
        else -> {
            val lines = versionsOnlyFile.readLines()
            val startOfBlock = lines.indexOfFirst {
                it.trim().endsWith(PluginConfig.VERSIONS_ONLY_START)
            }
            val endOfBlock = lines.indexOfFirst {
                it.trim().endsWith(PluginConfig.VERSIONS_ONLY_END)
            }
            if (startOfBlock == -1 || endOfBlock == -1) {
                null
            } else {
                val indent = lines[endOfBlock].substringBefore("// ", missingDelimiterValue = "    ")
                SingleModeResult(startOfBlock, endOfBlock, indent)

            }
        }
    }
}

fun regenerateBuildFile(versionsOnlyFile: File?, extension: BuildSrcVersionsExtension, dependencies: List<Dependency>) {
    val versionsOnlyMode = extension.versionsOnlyMode ?: return
    val parseResult = parseBuildFile(versionsOnlyFile) ?: SingleModeResult.DEFAULT
    val (startOfBlock, endOfBlock, indent) = parseResult

    val newBlock = regenerateBlock(versionsOnlyMode, dependencies, indent)

    if (versionsOnlyFile != null && parseResult != SingleModeResult.DEFAULT) {
        val lines = versionsOnlyFile.readLines()
        val newLines = lines.subList(0, startOfBlock) + newBlock + lines.subList(endOfBlock + 1, lines.size)
        versionsOnlyFile.writeText(newLines.joinToString(separator = "\n", postfix = "\n"))
    } else {
        println("""
            
== 📋 copy-paste needed! 📋 ==

Copy-paste the snippet below:

${newBlock.joinToString(separator = "\n")}

in the file you configure with something like:
 
// build.gradle(.kts) 
buildSrcVersions {
    versionsOnlyMode = VersionsOnlyMode.${versionsOnlyMode}
    versionsOnlyFile = "${versionsOnlyMode.suggestedFilename()}"            
}

See ${PluginConfig.issue54VersionOnlyMode}
        """
        )
        println()
    }
}

fun regenerateBlock(mode: VersionsOnlyMode, dependencies: List<Dependency>, indent: String): List<String> {
    val result = mutableListOf<String>()
    result += PluginConfig.VERSIONS_ONLY_INTRO.map { "$indent// $it" }
    if (mode == VersionsOnlyMode.GROOVY_EXT) result += "${indent}ext {"
    result += dependencies.map { versionOnly(it, mode, indent) }
    if (mode == VersionsOnlyMode.GROOVY_EXT) result += "${indent}}"
    result += "$indent// ${PluginConfig.VERSIONS_ONLY_END}"
    return result
}

fun versionOnly(d: Dependency, versionsOnlyMode: VersionsOnlyMode, indent: String): String {
    var available = d.versionInformation()
    if (versionsOnlyMode.useSingleQuote()) available = available.replace('"', '\'')
    return when(versionsOnlyMode) {
        VersionsOnlyMode.KOTLIN_VAL -> """${indent}val ${d.versionName} = "${d.version}"$available"""
        VersionsOnlyMode.GROOVY_DEF -> """${indent}def ${d.versionName} = '${d.version}'$available"""
        VersionsOnlyMode.GROOVY_EXT -> """${indent}${indent}${d.versionName} = '${d.version}'$available"""
        else -> TODO("Not implemented $versionsOnlyMode")
    }
}
