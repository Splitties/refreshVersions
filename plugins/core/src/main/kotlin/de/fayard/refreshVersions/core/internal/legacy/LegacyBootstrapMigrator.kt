package de.fayard.refreshVersions.core.internal.legacy

import de.fayard.refreshVersions.core.RefreshVersionsCorePlugin
import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.extensions.ranges.contains
import de.fayard.refreshVersions.core.extensions.ranges.minus
import de.fayard.refreshVersions.core.extensions.text.indexOfPrevious
import de.fayard.refreshVersions.core.extensions.text.substringUpTo
import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
import de.fayard.refreshVersions.core.internal.codeparsing.ProgrammingLanguage
import de.fayard.refreshVersions.core.internal.codeparsing.SourceCodeSection
import de.fayard.refreshVersions.core.internal.codeparsing.SymbolLocationFindingRule
import de.fayard.refreshVersions.core.internal.codeparsing.SymbolResult
import de.fayard.refreshVersions.core.internal.codeparsing.extractArgumentsOfFunctionCall
import de.fayard.refreshVersions.core.internal.codeparsing.findSymbolRange
import de.fayard.refreshVersions.core.internal.codeparsing.findSymbolsRanges
import de.fayard.refreshVersions.core.internal.codeparsing.gradle.extractGradleScriptSections
import de.fayard.refreshVersions.core.internal.codeparsing.rangeOfCode
import de.fayard.refreshVersions.core.internal.codeparsing.rangeOfStringLiteral
import org.gradle.api.initialization.Settings
import java.io.File

@InternalRefreshVersionsApi
class LegacyBootstrapMigrator(
    private val isBuildSrc: Boolean,
    private val isKotlinDsl: Boolean
) {

    companion object {
        fun Settings.replaceBootstrapWithPluginsDslSetup() {
            val settingsFile = rootDir.resolve("settings.gradle.kts").let { kotlinDslSettings ->
                when {
                    kotlinDslSettings.exists() -> kotlinDslSettings
                    else -> rootDir.resolve("settings.gradle").also { check(it.exists()) }
                }
            }
            LegacyBootstrapMigrator(
                isBuildSrc = isBuildSrc,
                isKotlinDsl = settingsFile.extension == "kts"
            ).replaceBootstrapWithPluginsDslSetup(settingsFile = settingsFile)
        }
    }


    private fun replaceBootstrapWithPluginsDslSetup(settingsFile: File) {
        val newContent = gradleSettingsFileContentUpdatedWithPluginsDslSetup(
            settingsFileContent = settingsFile.readText()
        )
        settingsFile.writeText(newContent)
    }

    internal fun gradleSettingsFileContentUpdatedWithPluginsDslSetup(
        settingsFileContent: String
    ): String = settingsFileContent
        .withPluginDeclaration()
        .withBoostrapParametersMovedToExtension()
        .withLegacyBootstrapSymbolsRemoved()
        .withAtMostOneBlankLineAroundPluginsAndRefreshVersionsBlocks()
        .withPluginVersionForBuildSrc()

    private fun String.withPluginDeclaration(): String {
        val pluginsBlockRule = SymbolLocationFindingRule.BlockContent("plugins")
        val ranges = extractGradleScriptSections(isKotlinDsl = isKotlinDsl)
        val symbolsRanges = findSymbolsRanges(
            ranges = ranges,
            rules = listOf(
                pluginsBlockRule,
                bootstrapFunctionFindingRule(
                    isBuildSrc = isBuildSrc,
                    isKotlinDsl = isKotlinDsl
                )
            )
        )
        val currentVersion = RefreshVersionsCorePlugin.currentVersion
        val versionSuffix = when {
            isBuildSrc -> ""
            else -> """ version "$currentVersion""""
        }
        symbolsRanges.firstOrNull { it.tag.rule == pluginsBlockRule }?.let { pluginsBlockRange ->
            return buildString {
                append(this@withPluginDeclaration)
                insert(pluginsBlockRange.endIndex, buildString {
                    appendln("""    id("de.fayard.refreshVersions")$versionSuffix""")
                })
            }
        }
        val bootstrapFunctionCallRange = symbolsRanges.single()
        return buildString {
            append(this@withPluginDeclaration)
            insert(bootstrapFunctionCallRange.startIndex, buildString {
                appendln(
                    """
                    plugins {
                        id("de.fayard.refreshVersions")$versionSuffix
                    }
                    """.trimIndent()
                )
            })
        }
    }

    private fun String.withPluginVersionForBuildSrc(): String {
        if (isBuildSrc.not()) return this
        val text = this
        val targetBlockRule = SymbolLocationFindingRule.BlockContent("pluginManagement", "plugins")
        val enclosingBlockRule = SymbolLocationFindingRule.BlockContent("pluginManagement")
        val buildScriptBlockRule = SymbolLocationFindingRule.BlockContent("buildscript")
        val pluginsBlockRule = SymbolLocationFindingRule.BlockContent("plugins")
        val symbolsRanges = findSymbolsRanges(
            ranges = extractGradleScriptSections(isKotlinDsl = isKotlinDsl),
            rules = listOf(targetBlockRule, enclosingBlockRule, buildScriptBlockRule, pluginsBlockRule)
        )
        symbolsRanges.firstOrNull { it.tag.rule == targetBlockRule }?.let { targetBlockRange ->
            val currentVersion = RefreshVersionsCorePlugin.currentVersion
            return buildString {
                append(text)
                insert(targetBlockRange.endIndex, buildString {
                    appendln("""    id("de.fayard.refreshVersions") version "$currentVersion"""")
                    append("    ")
                })
            }
        }
        symbolsRanges.firstOrNull { it.tag.rule == enclosingBlockRule }?.let { enclosingBlockRange ->
            return buildString {
                append(text)
                insert(enclosingBlockRange.endIndex, "    plugins {\n    }\n")
            }.withPluginVersionForBuildSrc()
        }

        val topmostBlockTaggedRange = symbolsRanges.firstOrNull {
            it.tag.rule == buildScriptBlockRule
        } ?: symbolsRanges.single { it.tag.rule == pluginsBlockRule }
        val topmostBlockStartIndex = (topmostBlockTaggedRange.tag as SymbolResult.BlockContent).blockRange.first

        return buildString {
            append(text)
            insert(topmostBlockStartIndex, "pluginManagement {\n}\n\n")
        }.withPluginVersionForBuildSrc()
    }

    private fun String.withBoostrapParametersMovedToExtension(): String {
        if (isBuildSrc) return this // Because buildSrc bootstrap doesn't take parameters.
        val ranges = extractGradleScriptSections(isKotlinDsl = isKotlinDsl)
        val bootstrapCallRange = findSymbolRange(
            ranges = ranges,
            rule = bootstrapFunctionFindingRule(
                isBuildSrc = isBuildSrc,
                isKotlinDsl = isKotlinDsl
            )
        )!!

        val arguments = extractArgumentsOfFunctionCall(
            programmingLanguage = when {
                isKotlinDsl -> ProgrammingLanguage.Kotlin
                else -> ProgrammingLanguage.Groovy
            },
            functionCallText = substring(bootstrapCallRange.range)
        ).let {
            when {
                isKotlinDsl -> it
                else -> {
                    // First parameter is Gradle settings (receiver in Kotlin), ignore it.
                    it.subList(fromIndex = 1, toIndex = it.size)
                }
            }
        }

        if (arguments.isEmpty()) return this

        val extraArtifactVersionKeyRulesArg = arguments.find {
            it.parameterName == "extraArtifactVersionKeyRules"
        } ?: arguments.first().takeIf { it.parameterName == null }

        val versionsPropertiesFileArg = arguments.find {
            it.parameterName == "versionsPropertiesFile"
        } ?: arguments.elementAtOrNull(1)?.takeIf { it.parameterName == null }

        val pluginsBlockContentRange = findSymbolRange(
            ranges,
            rule = SymbolLocationFindingRule.BlockContent("plugins")
        )!!

        return buildString {
            append(this@withBoostrapParametersMovedToExtension)
            insert(pluginsBlockContentRange.endIndex + 1, buildString {
                appendln()
                appendln()
                appendln("refreshVersions {")
                extraArtifactVersionKeyRulesArg?.let {
                    append("    ")
                    append("extraArtifactVersionKeyRules = ")
                    append(it.expressionWithCommentsIfAny)
                    if (versionsPropertiesFileArg != null) appendln()
                }
                versionsPropertiesFileArg?.let {
                    append("    ")
                    append("versionsPropertiesFile = ")
                    append(it.expressionWithCommentsIfAny)
                }
                appendln()
                append('}')
            })
        }
    }

    private fun String.withLegacyBootstrapSymbolsRemoved(): String {
        val ranges = extractGradleScriptSections(isKotlinDsl = isKotlinDsl)

        val buildscriptBlockRule = SymbolLocationFindingRule.BlockContent("buildscript")
        val buildscriptRepositoriesBlockRule = SymbolLocationFindingRule.BlockContent(
            "buildscript", "repositories"
        )
        val bootstrapFunctionRule = bootstrapFunctionFindingRule(isBuildSrc = isBuildSrc, isKotlinDsl = isKotlinDsl)
        val migrationFunctionRule = migrationFunctionFindingRule(isBuildSrc = isBuildSrc, isKotlinDsl = isKotlinDsl)

        val symbols = findSymbolsRanges(
            ranges = ranges,
            rules = listOf(
                buildscriptBlockRule, buildscriptRepositoriesBlockRule,
                bootstrapFunctionRule, migrationFunctionRule
            )
        )

        val rangesToRemove = mutableListOf<IntRange>()

        val expectedValues = LegacyBootstrapUpdater.ExpectedValues(isBuildSrc, isKotlinDsl)

        fun removeFunctionCallIfAny(
            importText: String,
            functionRule: SymbolLocationFindingRule,
            functionCallSymbol: String
        ) {
            rangeOfCode(
                code = importText,
                sectionsRanges = ranges
            )?.let { importRange ->
                rangesToRemove += importRange.first..indexOf('\n', startIndex = importRange.last)
                rangesToRemove += symbols.single { it.tag.rule == functionRule }.range
                if (isKotlinDsl.not()) {
                    rangesToRemove += rangeOfCode(
                        code = functionCallSymbol.substringUpTo('.'),
                        sectionsRanges = ranges
                    )!!
                }
            }
        }

        removeFunctionCallIfAny(
            importText = expectedValues.migrationCallImport,
            functionRule = migrationFunctionRule,
            functionCallSymbol = expectedValues.migrationCallSymbol
        )
        removeFunctionCallIfAny(
            importText = expectedValues.bootstrapImport,
            functionRule = bootstrapFunctionRule,
            functionCallSymbol = expectedValues.bootstrapSymbol
        )

        val buildscriptRepositoriesBlockRanges = symbols.filter { it.tag.rule == buildscriptRepositoriesBlockRule }
        val currentVersion = RefreshVersionsCorePlugin.currentVersion
        val expectedDependencyNotation = "de.fayard.refreshVersions:refreshVersions:$currentVersion"
        symbols.filter { it.tag.rule == buildscriptBlockRule }.forEach { buildscriptBlockRange ->
            val repositoriesRanges = buildscriptRepositoriesBlockRanges.filter {
                it.range in buildscriptBlockRange.range
            }
            val nonRepoRanges = buildscriptBlockRange.range minus repositoriesRanges.map { it.range }
            val doesntJustSetupRefreshVersions = nonRepoRanges.any { range ->
                ranges.any {
                    it.tag == SourceCodeSection.StringLiteral &&
                            it.range in range &&
                            expectedDependencyNotation !in substring(it.range)
                }
            }
            if (doesntJustSetupRefreshVersions) {
                rangeOfStringLiteral(
                    stringLiteral = expectedDependencyNotation,
                    startIndex = buildscriptBlockRange.startIndex,
                    sectionsRanges = ranges
                )?.let { range ->
                    rangesToRemove += indexOfPrevious(
                        char = '\n',
                        startIndex = range.first
                    ) until indexOf(
                        char = '\n',
                        startIndex = range.last
                    )
                }
            } else {
                rangesToRemove += (buildscriptBlockRange.tag as SymbolResult.BlockContent).blockRange
            }
        }

        rangesToRemove.sortByDescending { it.first }
        return buildString(this.length) {
            append(this@withLegacyBootstrapSymbolsRemoved)
            rangesToRemove.forEach { range ->
                replace(
                    /* start */ range.first,
                    /* end */ range.last + 1,
                    /* str */ ""
                )
            }
        }
    }

    private fun String.withAtMostOneBlankLineAroundPluginsAndRefreshVersionsBlocks(): String {
        val text = this
        return buildString {
            append(text)
            findSymbolRange(
                ranges = extractGradleScriptSections(isKotlinDsl = isKotlinDsl),
                rule = SymbolLocationFindingRule.BlockContent("refreshVersions")
            )?.let { refreshVersionsBlockRange ->
                replaceSurroundingExtraBlankLinesWithOneLineBreak(
                    targetRange = (refreshVersionsBlockRange.tag as SymbolResult.BlockContent).blockRange
                )
            }
            findSymbolRange(
                ranges = extractGradleScriptSections(isKotlinDsl = isKotlinDsl),
                rule = SymbolLocationFindingRule.BlockContent("plugins")
            )!!.let { pluginsBlockRange ->
                replaceSurroundingExtraBlankLinesWithOneLineBreak(
                    targetRange = (pluginsBlockRange.tag as SymbolResult.BlockContent).blockRange
                )
            }
        }
    }

    private fun StringBuilder.replaceSurroundingExtraBlankLinesWithOneLineBreak(
        targetRange: IntRange
    ) {
        fun StringBuilder.replaceTrailingExtraBlankLinesWithLineBreak() {
            val index = targetRange.last + 1
            substring(index, length).indexOfFirst { it.isWhitespace().not() }.let { i ->
                val endOfFile = i == -1
                val endIndex = if (endOfFile) length else index + i
                val replacement = if (endOfFile) "\n" else "\n\n"
                replace(index, endIndex, replacement)
            }
        }

        fun StringBuilder.replaceLeadingExtraBlankLinesWithLineBreak() {
            val index = targetRange.first
            substring(0, index).indexOfLast { it.isWhitespace().not() }.let { i ->
                val startOfFile = i == -1
                val startIndex = if (startOfFile) 0 else i + 1
                val replacement = if (startOfFile) "" else "\n\n"
                replace(startIndex, index, replacement)
            }
        }
        replaceTrailingExtraBlankLinesWithLineBreak()
        replaceLeadingExtraBlankLinesWithLineBreak()
    }

    private fun bootstrapFunctionFindingRule(
        isBuildSrc: Boolean,
        isKotlinDsl: Boolean
    ): SymbolLocationFindingRule.FunctionCall {
        val expectedValues = LegacyBootstrapUpdater.ExpectedValues(
            isBuildSrc = isBuildSrc,
            isKotlinDsl = isKotlinDsl
        )
        return SymbolLocationFindingRule.FunctionCall(
            functionName = expectedValues.bootstrapSymbol.let {
                if (isKotlinDsl) it else it.substringAfter('.')
            }
        )
    }

    private fun migrationFunctionFindingRule(
        isBuildSrc: Boolean,
        isKotlinDsl: Boolean
    ): SymbolLocationFindingRule.FunctionCall {
        val expectedValues = LegacyBootstrapUpdater.ExpectedValues(
            isBuildSrc = isBuildSrc,
            isKotlinDsl = isKotlinDsl
        )
        return SymbolLocationFindingRule.FunctionCall(
            functionName = expectedValues.migrationCallSymbol.let {
                if (isKotlinDsl) it else it.substringAfter('.')
            }
        )
    }
}

