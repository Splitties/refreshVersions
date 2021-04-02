package de.fayard.refreshVersions.core.internal.codeparsing.gradle

import de.fayard.refreshVersions.core.internal.TaggedRange
import de.fayard.refreshVersions.core.internal.codeparsing.SourceCodeSection

internal fun CharSequence.findPluginBlocksRanges(
    ranges: List<TaggedRange<SourceCodeSection>>
): List<TaggedRange<PluginBlock>> = mutableListOf<TaggedRange<PluginBlock>>().also { list ->
    var blockNestingLevel = 0
    var isInPluginManagement = false
    var pluginsBlockLocation: PluginsBlockLocation? = null

    /** Right after the opening brace. */
    val currentBlocksStartIndexes = mutableListOf(0)


    ranges.filter { it.tag == SourceCodeSection.CodeChunk }.forEach {
        val codeChunk = substring(it.startIndex, it.endIndex)

        val currentWord = StringBuilder()
        var previousWord = ""

        fun addPluginBlockRange(closingBraceIndex: Int) {
            val range = TaggedRange(
                tag = PluginBlock,
                startIndex = currentBlocksStartIndexes.last(),
                endIndex = it.startIndex + closingBraceIndex
            )
            list.add(range)
        }
        codeChunk.forEachIndexed { index, c ->
            when {
                c.isLetterOrDigit() -> currentWord.append(c)
                currentWord.isNotEmpty() -> {
                    previousWord = currentWord.toString()
                    currentWord.clear()
                }
            }
            when (c) {
                '{' -> {
                    if (blockNestingLevel == 0 && previousWord == "pluginManagement") {
                        isInPluginManagement = true
                    }
                    if (blockNestingLevel == 0 && previousWord == "plugins") {
                        pluginsBlockLocation = PluginsBlockLocation.TopLevel
                    }
                    if (isInPluginManagement && blockNestingLevel == 1 && previousWord == "plugins") {
                        pluginsBlockLocation = PluginsBlockLocation.PluginManagement
                    }
                    currentBlocksStartIndexes.add(it.startIndex + index + 1)
                    blockNestingLevel++
                }
                '}' -> {
                    if (isInPluginManagement && blockNestingLevel == 1) {
                        isInPluginManagement = false
                    }
                    when (pluginsBlockLocation) {
                        PluginsBlockLocation.TopLevel -> if (blockNestingLevel == 1) {
                            addPluginBlockRange(closingBraceIndex = index)
                            pluginsBlockLocation = null
                        }
                        PluginsBlockLocation.PluginManagement -> if (blockNestingLevel == 2) {
                            addPluginBlockRange(closingBraceIndex = index)
                            pluginsBlockLocation = null
                        }
                    }
                    currentBlocksStartIndexes.removeAt(currentBlocksStartIndexes.lastIndex)
                    blockNestingLevel--
                }
            }
            if (list.size == 2) return@also
        }
    }
}

internal object PluginBlock

internal enum class PluginsBlockLocation {
    TopLevel,
    PluginManagement
}
