package de.fayard.refreshVersions.core.internal

/**
 * @property startIndex inclusive
 * @property endIndex exclusive
 */
internal class TaggedRange<out T>(
    val tag: T,
    val startIndex: Int,
    val endIndex: Int
)
