package de.fayard.refreshVersions.core.extensions.ranges

internal operator fun IntRange.contains(other: IntRange): Boolean {
    return contains(other.first) && contains(other.last)
}


/**
 * EXPECTS A SORTED LIST.
 * Doesn't check for that, so can produce unexpected output if the passed list is not sorted.
 *
 * This function is NOT an operator to avoid ambiguity with minus on [Iterable],
 * since [IntRange] is one.
 */
internal infix fun IntRange.minus(sortedRanges: List<IntRange>): List<IntRange> {
    if (sortedRanges.isEmpty()) return listOf(this)
    return List(sortedRanges.size + 1) { index ->
        when (index) {
            0 -> first until sortedRanges[index].first
            sortedRanges.size -> (sortedRanges.last().last + 1)..last
            else -> (sortedRanges[index -1].last + 1) until sortedRanges[index].first
        }.let { if (it.isEmpty()) IntRange.EMPTY else it }
    }
}
