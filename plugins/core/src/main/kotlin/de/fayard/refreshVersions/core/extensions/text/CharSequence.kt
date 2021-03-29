package de.fayard.refreshVersions.core.extensions.text

internal fun CharSequence.indexOfPrevious(char: Char, startIndex: Int): Int {
    if (startIndex !in 0..lastIndex) throw IndexOutOfBoundsException(startIndex)
    for (i in startIndex downTo 0) {
        val c = this[i]
        if (c == char) return i
    }
    return -1
}

internal interface SkippableIterationScope {
    fun skipIteration(offset: Int)
}

internal inline fun CharSequence.forEachIndexedSkippable(
    action: SkippableIterationScope.(index: Int, c: Char) -> Unit
) {
    var index = 0
    val scope = object : SkippableIterationScope {
        override fun skipIteration(offset: Int) {
            index += offset
        }
    }
    while (index < length) {
        val currentIndex = index++
        val c = this[currentIndex]
        scope.action(currentIndex, c)
    }
}
