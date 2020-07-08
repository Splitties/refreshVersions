package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.StabilityLevel.*
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class StabilityLevelTest {

    @Test
    fun `Test stability level comparisons`() {
        assertTrue(StabilityLevel.values().minBy { it } == Stable)

        testStabilityLevels(lessStable = Unknown, mostStable = Snapshot)
        testStabilityLevels(lessStable = Snapshot, mostStable = Preview)
        testStabilityLevels(lessStable = Preview, mostStable = Development)
        testStabilityLevels(lessStable = Development, mostStable = Alpha)
        testStabilityLevels(lessStable = Alpha, mostStable = Beta)
        testStabilityLevels(lessStable = Beta, mostStable = EarlyAccessProgram)
        testStabilityLevels(lessStable = EarlyAccessProgram, mostStable = Milestone)
        testStabilityLevels(lessStable = Milestone, mostStable = ReleaseCandidate)
        testStabilityLevels(lessStable = ReleaseCandidate, mostStable = Stable)

        @Suppress("RemoveRedundantQualifierName")
        StabilityLevel.values().forEach { Stable isAtLeastAsStableAs it }
    }

    private fun testStabilityLevels(lessStable: StabilityLevel, mostStable: StabilityLevel) {
        assertTrue(lessStable isLessStableThan mostStable)
        assertTrue(mostStable isMoreStableThan  lessStable)
        assertTrue(mostStable isAtLeastAsStableAs lessStable)
        assertTrue(mostStable < lessStable)

        assertFalse(mostStable isLessStableThan lessStable)
        assertFalse(lessStable isMoreStableThan  mostStable)
        assertFalse(lessStable isAtLeastAsStableAs mostStable)
    }
}
