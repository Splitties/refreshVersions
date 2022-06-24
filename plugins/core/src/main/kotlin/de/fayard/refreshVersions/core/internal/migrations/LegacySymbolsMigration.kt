package de.fayard.refreshVersions.core.internal.migrations

import de.fayard.refreshVersions.core.extensions.collections.forEachReversedWithIndex
import de.fayard.refreshVersions.core.internal.codeparsing.*
import de.fayard.refreshVersions.core.internal.codeparsing.SymbolLocationFindingRule
import de.fayard.refreshVersions.core.internal.codeparsing.gradle.extractGradleScriptSections
import java.io.File

internal fun migrateLegacySymbolsIfNeeded(projectDir: File, revisionOfLastRefreshVersionsRun: Int) {
    if (revisionOfLastRefreshVersionsRun < 11) addNewArgumentToVersionForCalls(projectDir)
}

private fun addNewArgumentToVersionForCalls(projectDir: File) {
    projectDir.walk().onEnter {
        it.name != "src" && it.name != "build"
    }.filter {
        it.isFile
    }.filter {
        it.extension == "gradle"
    }.filter {
        it.name != "settings.gradle"
    }.forEach { gradleFile ->
        gradleFileContentUpdatedWithVersionForMigrated(gradleFile.readText())?.let { newContent ->
            gradleFile.writeText(newContent)
        }
    }
}

internal fun gradleFileContentUpdatedWithVersionForMigrated(
    fileContent: String
): String? {
    val ranges = fileContent.extractGradleScriptSections(isKotlinDsl = false)
    val versionForCallRule = SymbolLocationFindingRule.FunctionCall(functionName = "versionFor")
    val symbols = fileContent.findSymbolsRanges(
        ranges = ranges,
        rules = listOf(versionForCallRule)
    ).ifEmpty {
        return null
    }
    val sb = StringBuilder(fileContent)
    symbols.forEachReversedWithIndex { i, taggedRange ->
        val arguments = extractArgumentsOfFunctionCall(
            programmingLanguage = ProgrammingLanguage.Groovy,
            functionCallText = fileContent.substring(taggedRange.range)
        )
        if (arguments.size == 1) {
            val indexOfOpeningParenthesis = fileContent.rangeOfCode(
                code = "(",
                startIndex = taggedRange.startIndex,
                sectionsRanges = ranges
            )!!.first
            sb.insert(indexOfOpeningParenthesis + 1, "project, ")
        }
    }
    if (sb.length == fileContent.length) {
        return null // No change in length means we inserted nothing.
    }
    return sb.toString()
}
