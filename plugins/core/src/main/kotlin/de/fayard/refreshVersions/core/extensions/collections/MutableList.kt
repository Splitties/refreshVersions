package de.fayard.refreshVersions.core.extensions.collections

internal fun <T> MutableList<T>.removeLast() = removeAt(lastIndex)
