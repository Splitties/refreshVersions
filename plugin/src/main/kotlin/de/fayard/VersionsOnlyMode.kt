package de.fayard

import java.io.File

fun BuildSrcVersionsExtension.useBuildSrc() = versionsOnlyMode != null

enum class VersionsOnlyMode(val example: String) {
    KOTLIN_VAL("""
        val okhttp = "3.2.0" // available: "3.3.1"
        val com_squareup_moshi = "1.8.0"
    """.trimIndent()),
    KOTLIN_OBJECT("""
    object {
        val okhttp = "3.2.0" // available: "3.3.1"
        val com_squareup_moshi = "1.8.0"
    }         
    """.trimIndent()),
    GROOVY_DEF("""
        def okhttp = '3.2.0' // available: '3.3.1'
        def com_squareup_moshi = '1.8.0'
    """.trimIndent()),
    GROOVY_EXT("""
        ext {
            okhttp = '3.2.0' // available: '3.3.1'
            com_squareup_moshi = '1.8.0'
        }
    """.trimIndent())
    ;
    companion object
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
    val (startOfBlock, endOfBlock, indent) = parseBuildFile(versionsOnlyFile) ?: return
    checkNotNull(versionsOnlyFile)
    val lines = versionsOnlyFile.readLines()

    val newBlock = regenerateBlock(extension, dependencies, indent)
    val newLines = lines.subList(0, startOfBlock) + newBlock + lines.subList(endOfBlock + 1, lines.size)
    versionsOnlyFile.writeText(newLines.joinToString(separator = "\n", postfix = "\n"))
}

fun regenerateBlock(extension: BuildSrcVersionsExtension, dependencies: List<Dependency>, indent: String): List<String> {
    val result = mutableListOf<String>()
    result += PluginConfig.VERSIONS_ONLY_INTRO.map { "$indent// $it" }
    result += dependencies.map { versionOnly(it, extension, indent) }
    result += "$indent// ${PluginConfig.VERSIONS_ONLY_END}"
    return result
}

fun versionOnly(d: Dependency, extension: BuildSrcVersionsExtension, indent: String): String {
    return """${indent}val ${d.versionName} = "${d.version}"${d.versionInformation()}""".trimEnd()
}
