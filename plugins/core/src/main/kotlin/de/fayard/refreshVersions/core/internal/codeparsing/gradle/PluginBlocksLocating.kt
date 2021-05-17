package de.fayard.refreshVersions.core.internal.codeparsing.gradle

import de.fayard.refreshVersions.core.internal.TaggedRange
import de.fayard.refreshVersions.core.internal.codeparsing.SourceCodeSection
import de.fayard.refreshVersions.core.internal.codeparsing.SymbolLocationFindingRule
import de.fayard.refreshVersions.core.internal.codeparsing.findSymbolsRanges

internal fun CharSequence.findPluginBlocksRanges(
    ranges: List<TaggedRange<SourceCodeSection>>
): List<TaggedRange<*>> = findSymbolsRanges(
    ranges = ranges,
    rules = listOf(
        SymbolLocationFindingRule.BlockContent("plugins"),
        SymbolLocationFindingRule.BlockContent("pluginManagement", "plugins")
    ),
    shouldTruncate = { currentList -> currentList.size == 2 }
)
