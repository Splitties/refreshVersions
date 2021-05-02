package de.fayard.refreshVersions.core.internal.codeparsing

import de.fayard.refreshVersions.core.extensions.text.forEachIndexedSkippable
import de.fayard.refreshVersions.core.internal.TaggedRange

/**
Returns an ordered list of tagged ranges that span code comments,
string literals, and the rest, separately.

The returned list covers the entire passed CharSequence.
 */
internal fun CharSequence.findRanges(
    programmingLanguage: ProgrammingLanguage
): List<TaggedRange<SourceCodeSection>> = mutableListOf<TaggedRange<SourceCodeSection>>().also {
    val text: CharSequence = this

    val canBlockCommentsBeNested = programmingLanguage == ProgrammingLanguage.Kotlin
    val isGroovy = programmingLanguage == ProgrammingLanguage.Groovy

    var isNextCharacterEscaped = false
    var currentRangeStartIndex = 0

    var stringLiteralState: StringLiteralState = StringLiteralState.None

    var isInEndOfLineComment = false
    var blockCommentDepth = 0

    fun addRange(type: SourceCodeSection, endIndex: Int) {
        val range = TaggedRange(
            tag = type,
            startIndex = currentRangeStartIndex,
            endIndex = endIndex
        )
        it.add(range)
        currentRangeStartIndex = endIndex
    }

    fun addCodeChunk(endIndex: Int) = addRange(SourceCodeSection.CodeChunk, endIndex)

    fun addCommentRange(endIndex: Int) = addRange(SourceCodeSection.Comment, endIndex)

    fun addStringLiteralRange(endIndex: Int) {
        addRange(SourceCodeSection.StringLiteral, endIndex)
        stringLiteralState = StringLiteralState.None
    }

    forEachIndexedSkippable { index, c ->
        if (isInEndOfLineComment) check(blockCommentDepth == 0)
        if (blockCommentDepth > 0) check(isInEndOfLineComment.not())
        if (isNextCharacterEscaped) {
            isNextCharacterEscaped = false
            return@forEachIndexedSkippable
        }
        if (c == '\\') {
            isNextCharacterEscaped = true
            return@forEachIndexedSkippable
        }
        when {
            stringLiteralState != StringLiteralState.None -> when (stringLiteralState) {
                StringLiteralState.Ordinary.GroovySingleQuotes -> if (c == '\'') {
                    addStringLiteralRange(endIndex = index + 1)
                }
                StringLiteralState.Ordinary.DoubleQuotes -> if (c == '"') {
                    addStringLiteralRange(endIndex = index + 1)
                }
                StringLiteralState.Raw -> if (text.startsWith(tripleQuotes, startIndex = index)) {
                    skipIteration(offset = 2)
                    addStringLiteralRange(endIndex = index + 3)
                }
                else -> error("sorry dollar slashy. BTW, how did you even get there??")
            }
            isInEndOfLineComment -> if (c == '\n') {
                addCommentRange(endIndex = index + 1)
                isInEndOfLineComment = false
            }
            blockCommentDepth > 0 -> when {
                canBlockCommentsBeNested && text.startsWith("/*", startIndex = index) -> {
                    blockCommentDepth++
                    skipIteration(offset = 1)
                }
                text.startsWith("*/", startIndex = index) -> {
                    blockCommentDepth--
                    skipIteration(offset = 1)
                    if (blockCommentDepth == 0) {
                        addCommentRange(endIndex = index + 2)
                    }
                }
            }
            else -> {
                when {
                    text.startsWith("//", startIndex = index) -> {
                        isInEndOfLineComment = true
                        skipIteration(offset = 1)
                    }
                    text.startsWith("/*", startIndex = index) -> {
                        blockCommentDepth = 1
                        skipIteration(offset = 1)
                    }
                    text.startsWith(tripleQuotes, startIndex = index) -> {
                        stringLiteralState = StringLiteralState.Raw
                        skipIteration(offset = 2)
                    }
                    text.startsWith(singleQuote, startIndex = index) -> {
                        stringLiteralState = StringLiteralState.Ordinary.DoubleQuotes
                    }
                    isGroovy && text.startsWith("'", startIndex = index) -> {
                        stringLiteralState = StringLiteralState.Ordinary.GroovySingleQuotes
                    }
                    else -> return@forEachIndexedSkippable
                }
                val codeChunkWouldBeEmpty = currentRangeStartIndex == index
                if (codeChunkWouldBeEmpty.not()) {
                    addCodeChunk(endIndex = index)
                }
                currentRangeStartIndex = index
            }
        }
    }
    if (blockCommentDepth > 0 || isInEndOfLineComment) {
        addCommentRange(endIndex = length)
    } else {
        addCodeChunk(endIndex = length)
    }
}

private sealed class StringLiteralState {
    object None : StringLiteralState()
    sealed class Ordinary : StringLiteralState() {
        object GroovySingleQuotes : Ordinary()
        object DoubleQuotes : Ordinary()

        @Deprecated("Are we sure we want to deal with that?", level = DeprecationLevel.ERROR)
        object GroovyDollarSlashy : Ordinary()
    }

    object Raw : StringLiteralState()
}

private const val tripleQuotes = "\"\"\""
private const val singleQuote = "\""
