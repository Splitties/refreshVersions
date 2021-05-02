package de.fayard.refreshVersions.core.internal.codeparsing

import de.fayard.refreshVersions.core.internal.TaggedRange

internal fun CharSequence.rangeOfCode(
    code: String,
    startIndex: Int = 0,
    sectionsRanges: List<TaggedRange<SourceCodeSection>>
): IntRange? {
    sectionsRanges.forEach { section ->
        if (startIndex > section.endIndex) return@forEach
        if (section.tag != SourceCodeSection.CodeChunk) return@forEach
        val indexOfMatch = substring(section.range).indexOf(code)
        if (indexOfMatch == -1) return@forEach
        val absoluteStartIndexOfMatch = section.startIndex + indexOfMatch
        return absoluteStartIndexOfMatch until absoluteStartIndexOfMatch + code.length
    }
    return null
}

internal fun CharSequence.rangeOfStringLiteral(
    stringLiteral: String,
    startIndex: Int = 0,
    sectionsRanges: List<TaggedRange<SourceCodeSection>>
): IntRange? {
    sectionsRanges.forEach { section ->
        if (startIndex > section.endIndex) return@forEach
        if (section.tag != SourceCodeSection.StringLiteral) return@forEach
        val indexOfMatch = substring(section.range).indexOf(stringLiteral)
        if (indexOfMatch == -1) return@forEach
        val absoluteStartIndexOfMatch = section.startIndex + indexOfMatch
        return absoluteStartIndexOfMatch until absoluteStartIndexOfMatch + stringLiteral.length
    }
    return null
}
