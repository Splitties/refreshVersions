package de.fayard.versions.internal

import de.fayard.versions.extensions.indexOfFirst
import de.fayard.versions.internal.ArtifactPatternPart.*

internal class ArtifactPatternMatcher(textPattern: String) {

    init {
        require(textPattern.count { it == ':' } <= 1)
    }

    fun matches(group: String, name: String): Boolean {
        return groupMatches(group) && nameMatches(name)
    }

    val groupPatternParts: List<ArtifactPatternPart> =
        buildArtifactPatternParts(textPattern.substringBefore(':'))

    val namePatternParts: List<ArtifactPatternPart>? = if (':' in textPattern) {
        buildArtifactPatternParts(textPattern.substringAfter(':'))
    } else null

    private fun groupMatches(group: String): Boolean = matches(
        patternParts = groupPatternParts,
        text = group,
        isGroup = true
    )

    private fun nameMatches(name: String): Boolean = namePatternParts == null || matches(
        patternParts = namePatternParts,
        text = name,
        isGroup = false
    )

    private fun matches(patternParts: List<ArtifactPatternPart>, text: String, isGroup: Boolean): Boolean {
        var index = 0
        patternParts.forEachIndexed { patternIndex, patternPart ->
            when (val newIndex = patternPart.indexOfNextPartIfMatching(text, startIndex = index)) {
                0 -> {
                    if (patternIndex == patternParts.lastIndex) return true
                    val nextIndex = patternIndex + 1
                    if (nextIndex == patternParts.lastIndex &&
                        patternParts[nextIndex] == OptionalDashedSuffixPlaceholder
                    ) {
                        check(isGroup.not())
                        return true
                    }
                    return false
                }
                -1 -> return false
                else -> index = newIndex
            }
        }
        error("Should never be reached. text: $text isGroup: $isGroup patternParts: $patternParts")
    }
}


private fun buildArtifactPatternParts(text: String): List<ArtifactPatternPart> {
    require(':' !in text)
    return mutableListOf<ArtifactPatternPart>().also { list ->
        var index = 0
        iterations@ while (index <= text.lastIndex) {
            when (val c = text[index]) {
                '.' -> list += Dot
                '-' -> list += Dash
                '?' -> {
                    val expected = "???"
                    require(text.startsWith(expected, startIndex = index))
                    index += expected.length
                    list += WordPlaceholder
                    continue@iterations
                }
                '*' -> list += SuffixPlaceholder
                '(' -> {
                    val expected = "(-*)"
                    require(text.startsWith(expected, startIndex = index))
                    index += expected.length
                    list += OptionalDashedSuffixPlaceholder
                    continue@iterations
                }
                else -> {
                    require(c.isLetterOrDigit())
                    val indexOfFirstNonWordPart = text.indexOfFirst(startIndex = index) { it.isWordPart().not() }
                    val word = if (indexOfFirstNonWordPart == -1) text.substring(startIndex = index) else {
                        text.substring(startIndex = index, endIndex = indexOfFirstNonWordPart)
                    }
                    list += Word(word)
                    index += word.length
                    continue@iterations
                }
            }
            index++
        }
    }
}

/**
 * [text] is either a group or a name from maven coordinates.
 * Returns -1 if no match.
 * Returns 0 if last part.
 *
 */
private fun ArtifactPatternPart.indexOfNextPartIfMatching(text: String, startIndex: Int): Int {
    if (this !is OptionalDashedSuffixPlaceholder) require(startIndex <= text.lastIndex)
    val nextIndex: Int = when (this) {
        is Word -> {
            if (text.startsWith(prefix = this.value, startIndex = startIndex)) {
                startIndex + this.value.length
            } else -1
        }
        Dot -> if (text[startIndex] == '.') startIndex + 1 else -1
        Dash -> if (text[startIndex] == '-') startIndex + 1 else -1
        WordPlaceholder -> {
            text.indexOfFirst(startIndex = startIndex) {
                it.isWordPart().not()
            }
        }
        SuffixPlaceholder -> 0
        OptionalDashedSuffixPlaceholder -> when {
            startIndex == text.length -> 0
            text[startIndex] == '-' && text.lastIndex > startIndex -> 0
            else -> -1
        }
    }
    return when {
        nextIndex >= text.lastIndex -> 0
        nextIndex == startIndex -> -1
        else -> nextIndex
    }
}
