package de.fayard.refreshVersions.core.internal.codeparsing.gradle

import de.fayard.refreshVersions.core.internal.TaggedRange
import de.fayard.refreshVersions.core.internal.codeparsing.BlockFindingRule
import de.fayard.refreshVersions.core.internal.codeparsing.SourceCodeSection
import de.fayard.refreshVersions.core.internal.codeparsing.findBlocksRanges

internal fun CharSequence.findPluginBlocksRanges(
    ranges: List<TaggedRange<SourceCodeSection>>
): List<TaggedRange<PluginsBlockLocation>> = findBlocksRanges(
    ranges = ranges,
    rules = listOf(
       BlockFindingRule(
           topLevelBlockName = "plugins",
           tag = PluginsBlockLocation.TopLevel
       ),
        BlockFindingRule(
            blocksNames = listOf("pluginManagement", "plugins"),
            tag = PluginsBlockLocation.PluginManagement
        )
    ),
    shouldTruncate = { currentList -> currentList.size == 2 }
)

internal enum class PluginsBlockLocation {
    TopLevel,
    PluginManagement
}
