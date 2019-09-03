package de.fayard

import java.io.File

fun BuildSrcVersionsExtension.useBuildSrc() = versionsOnlyMode != null

enum class VersionsOnlyMode {
    KOTLIN_VAL,
    KOTLIN_OBJECT,
    GROOVY_DEF,
    GROOVY_EXT;

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
    val (startOfBlock, endOfBlock, indent) = parseBuildFile(versionsOnlyFile) ?: return
    checkNotNull(versionsOnlyFile)
    val lines = versionsOnlyFile.readLines()

    val newBlock = regenerateBlock(versionsOnlyMode, dependencies, indent)
    val newLines = lines.subList(0, startOfBlock) + newBlock + lines.subList(endOfBlock + 1, lines.size)
    versionsOnlyFile.writeText(newLines.joinToString(separator = "\n", postfix = "\n"))
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

fun versionOnly(d: Dependency, versionsOnlyMode: VersionsOnlyMode, indent: String): String = when(versionsOnlyMode) {
    VersionsOnlyMode.KOTLIN_VAL -> """${indent}val ${d.versionName} = "${d.version}"${d.versionInformation()}"""
    VersionsOnlyMode.GROOVY_DEF -> """${indent}def ${d.versionName} = "${d.version}"${d.versionInformation()}"""
    VersionsOnlyMode.GROOVY_EXT -> """${indent}${indent}${d.versionName} = "${d.version}"${d.versionInformation()}"""
    else -> TODO("Not implemented $versionsOnlyMode")
}
