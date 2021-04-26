package de.fayard.refreshVersions.core.internal.codeparsing

import de.fayard.refreshVersions.core.extensions.collections.removeLast
import de.fayard.refreshVersions.core.internal.TaggedRange
import de.fayard.refreshVersions.core.internal.codeparsing.SourceCodeSection.*

internal sealed class SymbolLocationFindingRule {

    class BlockContent(
        val blocksNames: List<String>
    ) : SymbolLocationFindingRule() {
        constructor(
            topLevelBlockName: String
        ) : this(blocksNames = listOf(topLevelBlockName))

        constructor(vararg blocksNames: String) : this(blocksNames.asList())

        init {
            require(blocksNames.none { '`' in it }) {
                "Blocks with backticks are not supported ($blocksNames)"
            }
        }
    }

    class FunctionCall(
        val parentBlocks: List<String>? = null,
        val functionName: String
    ) : SymbolLocationFindingRule() {
        init {
            require('`' !in functionName) {
                "Functions with backticks are not supported ($functionName)"
            }
            if (parentBlocks != null) require(parentBlocks.none { '`' in it }) {
                "Blocks with backticks are not supported ($parentBlocks)"
            }
        }
    }
}

internal sealed class SymbolResult {

    abstract val rule: SymbolLocationFindingRule

    class BlockContent(
        override val rule: SymbolLocationFindingRule.BlockContent,
        /** Range of the entire block, not just just its content. */
        val blockRange: IntRange
    ) : SymbolResult()

    class FunctionCall(
        override val rule: SymbolLocationFindingRule.FunctionCall
    ) : SymbolResult()
}

/**
 * That function expects syntactically correct code.
 * If there's a lack of opening and closing braces pairing, it might fail in unexpected ways.
 */
internal fun CharSequence.findSymbolRange(
    ranges: List<TaggedRange<SourceCodeSection>>,
    rule: SymbolLocationFindingRule,
    shouldTruncate: (currentList: List<TaggedRange<SymbolResult>>) -> Boolean = { false }
): TaggedRange<SymbolResult>? = findSymbolsRanges(
    ranges = ranges,
    rules = listOf(rule),
    shouldTruncate = shouldTruncate
).firstOrNull()

/**
 * That function expects syntactically correct code.
 * If there's a lack of opening and closing braces pairing, it might fail in unexpected ways.
 */
internal fun CharSequence.findSymbolsRanges(
    ranges: List<TaggedRange<SourceCodeSection>>,
    rules: List<SymbolLocationFindingRule>,
    shouldTruncate: (currentList: List<TaggedRange<SymbolResult>>) -> Boolean = { false }
): List<TaggedRange<SymbolResult>> = mutableListOf<TaggedRange<SymbolResult>>().also { list ->

    val blocksFindingRules = rules.filterIsInstance<SymbolLocationFindingRule.BlockContent>()
    val functionCallsFindingRules = rules.filterIsInstance<SymbolLocationFindingRule.FunctionCall>()

    val enclosingBlocks = mutableListOf<EnclosingBlockInfo>()
    val enclosingFunctionCalls = mutableListOf<EnclosingFunctionCallInfo>()

    val currentWord = StringBuilder()
    var previousWord = ""
    var previousWordStartIndex = -1

    ranges.forEach { section ->
        val codeChunk: String = when (section.tag) {
            Comment -> return@forEach
            StringLiteral -> {
                previousWord = ""
                return@forEach
            }
            CodeChunk -> substring(section.range)
        }


        codeChunk.forEachIndexed { index, c ->
            when {
                c.isLetterOrDigit() -> currentWord.append(c)
                currentWord.isNotEmpty() -> {
                    previousWord = currentWord.toString()
                    previousWordStartIndex = section.startIndex + index - previousWord.length
                    currentWord.clear()
                }
            }
            when (c) {
                '\n' -> previousWord = ""
                '{' -> {
                    enclosingBlocks.add(
                        EnclosingBlockInfo(
                            name = previousWord,
                            blockStartIndex = previousWordStartIndex,
                            contentStartIndex = section.startIndex + index + 1
                        )
                    )
                }
                '}' -> {
                    blocksFindingRules.forEach { rule ->
                        if (rule.matches(enclosingBlocks)) {
                            val block = enclosingBlocks.last()
                            val range = TaggedRange(
                                tag = SymbolResult.BlockContent(
                                    rule = rule,
                                    blockRange = block.blockStartIndex..section.startIndex + index
                                ),
                                startIndex = block.contentStartIndex,
                                endIndex = section.startIndex + index
                            )
                            list.add(range)
                            if (shouldTruncate(list)) return@also
                        }
                    }
                    enclosingBlocks.removeLast()
                }
                '(' -> {
                    enclosingFunctionCalls.add(
                        EnclosingFunctionCallInfo(
                            name = previousWord,
                            startIndex = previousWordStartIndex
                        )
                    )
                }
                ')' -> {
                    functionCallsFindingRules.forEach { rule ->
                        val currentFunctionCall: EnclosingFunctionCallInfo = enclosingFunctionCalls.last()
                        if (
                            rule.matches(
                                actualFunctionName = currentFunctionCall.name,
                                enclosingBlocks = enclosingBlocks
                            )
                        ) {
                            val range = TaggedRange(
                                tag = SymbolResult.FunctionCall(rule = rule),
                                startIndex = currentFunctionCall.startIndex,
                                endIndex = section.startIndex + index + 1
                            )
                            list.add(range)
                            if (shouldTruncate(list)) return@also
                        }
                    }
                    enclosingFunctionCalls.removeLast()
                }
            }
        }
    }
}

private class EnclosingBlockInfo(
    val name: String,
    val blockStartIndex: Int,
    /** Right after the opening brace. */
    val contentStartIndex: Int
)

private class EnclosingFunctionCallInfo(
    val name: String,
    val startIndex: Int
)

private fun SymbolLocationFindingRule.BlockContent.matches(enclosingBlocks: List<EnclosingBlockInfo>): Boolean {
    if (blocksNames.size != enclosingBlocks.size) return false
    for (i in (enclosingBlocks.lastIndex) downTo 0) {
        if (blocksNames[i] != enclosingBlocks[i].name) return false
    }
    return true
}

private fun SymbolLocationFindingRule.FunctionCall.matches(
    actualFunctionName: String,
    enclosingBlocks: List<EnclosingBlockInfo>
): Boolean {
    if (actualFunctionName != functionName) return false
    if (parentBlocks == null) return true
    if (parentBlocks.size != enclosingBlocks.size) return false
    for (i in (enclosingBlocks.lastIndex) downTo 0) {
        if (parentBlocks[i] != enclosingBlocks[i].name) return false
    }
    return true
}
