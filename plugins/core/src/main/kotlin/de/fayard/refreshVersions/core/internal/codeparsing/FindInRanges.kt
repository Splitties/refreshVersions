package de.fayard.refreshVersions.core.internal.codeparsing

import de.fayard.refreshVersions.core.internal.TaggedRange

internal fun CharSequence.rangesOfCode(
    code: String,
    startIndex: Int = 0,
    ignoreCase: Boolean = false,
    sectionsRanges: List<TaggedRange<SourceCodeSection>>
): List<IntRange> {
    val list = rangeOfCode(
        code = code,
        startIndex = startIndex,
        ignoreCase = ignoreCase,
        sectionsRanges = sectionsRanges
    )?.let { mutableListOf(it) } ?: return emptyList()

    @Suppress("name_shadowing")
    var startIndex = list.single().last + 1
    if (startIndex > lastIndex) return list
    do {
        val nextRange = rangeOfCode(
            code = code,
            startIndex = startIndex,
            ignoreCase = ignoreCase,
            sectionsRanges = sectionsRanges
        )?.also {
            list.add(it)
            startIndex = it.last + 1
            if (startIndex > lastIndex) return list
        }
    } while (nextRange != null)
    return list
}

internal fun CharSequence.rangeOfCode(
    code: String,
    startIndex: Int = 0,
    ignoreCase: Boolean = false,
    sectionsRanges: List<TaggedRange<SourceCodeSection>>
): IntRange? = rangeOf(
    text = code,
    sectionKind = SourceCodeSection.CodeChunk,
    startIndex = startIndex,
    ignoreCase = ignoreCase,
    sectionsRanges = sectionsRanges
)

internal fun CharSequence.rangeOfStringLiteral(
    stringLiteral: String,
    startIndex: Int = 0,
    ignoreCase: Boolean = false,
    sectionsRanges: List<TaggedRange<SourceCodeSection>>
): IntRange? = rangeOf(
    text = stringLiteral,
    sectionKind = SourceCodeSection.StringLiteral,
    startIndex = startIndex,
    ignoreCase = ignoreCase,
    sectionsRanges = sectionsRanges
)

internal fun CharSequence.rangeOf(
    text: String,
    sectionKind: SourceCodeSection,
    startIndex: Int = 0,
    ignoreCase: Boolean = false,
    sectionsRanges: List<TaggedRange<SourceCodeSection>>
): IntRange? {
    sectionsRanges.forEach { section ->
        if (startIndex > section.endIndex) return@forEach
        if (section.tag != sectionKind) return@forEach
        val targetRange = if (startIndex > section.startIndex) {
            startIndex until section.endIndex
        } else section.range
        val indexOfMatch = substring(targetRange).indexOf(text, ignoreCase = ignoreCase)
        if (indexOfMatch == -1) return@forEach
        val absoluteStartIndexOfMatch = targetRange.first + indexOfMatch
        return absoluteStartIndexOfMatch until absoluteStartIndexOfMatch + text.length
    }
    return null
}

/**
 * Returns -1 if there's no import statement
 */
internal fun CharSequence.findFirstImportStatement(
    sectionsRanges: List<TaggedRange<SourceCodeSection>>
): Int {
    sectionsRanges.forEach { section ->
        if (section.tag == SourceCodeSection.CodeChunk) {
            val codeChunk = substring(section.range)
            if (codeChunk.isBlank()) return@forEach
            val startTrimmedCodeChunk = codeChunk.trimStart()
            if (startTrimmedCodeChunk.startsWith("import ")) {
                return section.startIndex + codeChunk.length - startTrimmedCodeChunk.length
            }
            return -1
        }
    }
    return -1
}

/**
 * Returns lastIndex + 1 (i.e. [CharSequence.length]) if no non-blank code chunk is found.
 */
internal fun CharSequence.indexOfFirstNonBlankCodeChunk(
    sectionsRanges: List<TaggedRange<SourceCodeSection>>
): Int {
    sectionsRanges.forEach { section ->
        if (section.tag == SourceCodeSection.CodeChunk) {
            val codeChunk = substring(section.range)
            if (codeChunk.isBlank()) return@forEach
            return section.startIndex + codeChunk.indexOfFirst { it.isWhitespace().not() }
        }
    }
    return length
}
