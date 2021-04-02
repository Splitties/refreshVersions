package de.fayard.refreshVersions.core.internal.codeparsing

import de.fayard.refreshVersions.core.internal.TaggedRange

internal class BlockFindingRule<T>(
    val blocksNames: List<String>,
    val tag: T
) {
    constructor(
        topLevelBlockName: String,
        tag: T
    ) : this(blocksNames = listOf(topLevelBlockName), tag = tag)
}

/**
 * That function expects syntactically correct code.
 * If there's a lack of opening and closing braces pairing, it might fail in unexpected ways.
 */
internal fun <T> CharSequence.findBlocksRanges(
    ranges: List<TaggedRange<SourceCodeSection>>,
    rules: List<BlockFindingRule<T>>,
    shouldTruncate: (currentList: List<TaggedRange<T>>) -> Boolean = { false }
): List<TaggedRange<T>> = mutableListOf<TaggedRange<T>>().also { list ->
    var blockNestingLevel = 0

    val enclosingBlocks = mutableListOf<EnclosingBlockInfo>()

    ranges.filter { it.tag == SourceCodeSection.CodeChunk }.forEach {
        val codeChunk = substring(it.startIndex, it.endIndex)

        val currentWord = StringBuilder()
        var previousWord = ""

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
                    enclosingBlocks.add(
                        EnclosingBlockInfo(
                            name = previousWord,
                            startIndex = it.startIndex + index + 1
                        )
                    )
                    blockNestingLevel++
                }
                '}' -> {
                    rules.forEach { rule ->
                        if (rule.matches(enclosingBlocks)) {
                            val range = TaggedRange(
                                tag = rule.tag,
                                startIndex = enclosingBlocks.last().startIndex,
                                endIndex = it.startIndex + index
                            )
                            list.add(range)
                            if (shouldTruncate(list)) return@also
                        }
                    }
                    enclosingBlocks.removeLast()
                    blockNestingLevel--
                }
            }
        }
    }
}

private class EnclosingBlockInfo(
    val name: String,
    /** Right after the opening brace. */
    val startIndex: Int
)

private fun <T> BlockFindingRule<T>.matches(enclosingBlocks: List<EnclosingBlockInfo>): Boolean {
    if (blocksNames.size != enclosingBlocks.size) return false
    for (i in (enclosingBlocks.lastIndex) downTo 0) {
        if (blocksNames[i] != enclosingBlocks[i].name) return false
    }
    return true
}

private fun MutableList<*>.removeLast() = removeAt(lastIndex)
