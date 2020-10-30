package de.fayard.refreshVersions.core.extensions.text

internal fun String.substringBetween(
    prefix: String,
    suffix: String
): String {

    val startIndex = indexOf(prefix).also {
        if (it == -1) throw NoSuchElementException("Didn't find the passed prefix into the given String")
    } + prefix.length

    val endIndex = indexOf(suffix, startIndex = startIndex).also {
        if (it == -1) throw NoSuchElementException("Didn't find the passed suffix into the given String")
    }

    return substring(startIndex = startIndex, endIndex = endIndex)
}

internal fun String.substringAfterLastLineStartingWith(
    linePrefix: String,
    missingDelimiterValue: String = this
): String {
    val indexOfLastLineStartingWith = lastIndexOf(linePrefix).also {
        if (it == -1) return missingDelimiterValue
    }
    val indexOfLineAfter = indexOf('\n', startIndex = indexOfLastLineStartingWith).also {
        if (it == -1) return missingDelimiterValue
    }
    return substring(startIndex = indexOfLineAfter + 1)
}
