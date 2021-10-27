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
): IntRange?  = rangeOf(
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
