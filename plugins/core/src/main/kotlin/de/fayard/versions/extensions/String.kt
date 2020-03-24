package de.fayard.versions.extensions

internal inline fun String.indexOfFirst(startIndex: Int, predicate: (Char) -> Boolean): Int {
    for (index in startIndex..lastIndex) {
        if (predicate(this[index])) return index
    }
    return -1
}
