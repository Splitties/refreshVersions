package de.fayard.refreshVersions.core.extensions.collections

/**
 * Iterates the receiver [List] using an index instead of an [Iterator] like [forEachIndexed] would
 * do, but in reverse order (from last index down to zero). Using this function saves an [Iterator]
 * allocation, which is good for immutable lists or usages confined to a single thread like
 * UI thread only use. However, this method will not detect concurrent modification, which might be a feature,
 * as it allows to remove the current element, and previous elements freely.
 *
 * @param action the action to invoke on each list element.
 */
internal inline fun <T> List<T>.forEachReversedWithIndex(action: (Int, T) -> Unit) {
    for (i in lastIndex downTo 0) {
        action(i, get(i))
    }
}
