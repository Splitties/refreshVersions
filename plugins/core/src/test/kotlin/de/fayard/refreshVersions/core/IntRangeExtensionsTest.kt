package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.extensions.ranges.minus
import kotlin.test.Test
import kotlin.test.assertEquals

class IntRangeExtensionsTest {

    @Test
    fun `test IntRange#minus`() {
        assertEquals(
            expected = listOf(0..1, 6..6, 9..10),
            actual = (0..10) minus listOf(2..5, 7..8)
        )
        assertEquals(
            expected = listOf(0..9, 21..100),
            actual = (0..100) minus listOf(10..20)
        )
        assertEquals(
            expected = listOf(IntRange.EMPTY, 21..100),
            actual = (0..100) minus listOf(0..20)
        )
        assertEquals(
            expected = listOf(IntRange.EMPTY, IntRange.EMPTY),
            actual = (0..100) minus listOf(0..100)
        )
        assertEquals(
            expected = listOf(0..0, 100..100),
            actual = (0..100) minus listOf(1..99)
        )
    }
}
