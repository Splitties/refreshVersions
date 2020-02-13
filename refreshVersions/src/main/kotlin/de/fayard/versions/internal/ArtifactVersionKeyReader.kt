package de.fayard.versions.internal

import de.fayard.versions.extensions.indexOfFirst

internal class ArtifactVersionKeyReader(
    private val artifactPatternMatcher: ArtifactPatternMatcher,
    textPattern: String
) {

    init {
        require(textPattern.count { it == ':' } == 1)
    }

    fun key(group: String, name: String): String {
        TODO()
    }

    private val groupParts = buildArtifactParts(textPattern.substringBefore(':'))
    private val nameParts = buildArtifactParts(textPattern.substringAfter(':'))
}

private fun buildArtifactParts(text: String): List<ArtifactPart> {
    require(':' !in text)
    return mutableListOf<ArtifactPart>().also { list ->
        var index = 0
        iterations@ while (index <= text.lastIndex) {
            when (val c = text[index]) {
                '.' -> list += ArtifactPart.Dot
                '-' -> list += ArtifactPart.Dash
                else -> {
                    require(c.isLetterOrDigit())
                    val indexOfFirstNonWordPart = text.indexOfFirst(startIndex = index) { it.isWordPart().not() }
                    val word = if (indexOfFirstNonWordPart == -1) text.substring(startIndex = index) else {
                        text.substring(startIndex = index, endIndex = indexOfFirstNonWordPart)
                    }
                    list += ArtifactPart.Word(word)
                    index += word.length
                    continue@iterations
                }
            }
            index++
        }
    }
}

/*

org.jetbrains.kotlinx:kotlinx-???(-*)
              ^^^^^^^.        ^^^
org.jetbrains.kotlinx:kotlinx-coroutines-core-android


## Iterating on line 3


 */
