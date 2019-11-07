package de.fayard.versions

import org.gradle.api.Incubating

@Suppress("MemberVisibilityCanBePrivate")
open class RefreshVersionsPropertiesExtension {

    /**
     * If the passed [predicate] returns `true`, candidate version will be rejected.
     *
     * This predicate is mutually exclusive with [acceptVersionOnlyIf], you should use only one (the last one wins).
     *
     * Default:
     *
     * ```kotlin
     *  rejectVersionIf {
     *      candidateIsLessStableThanCurrent()
     *  }
     *  ```
     *
     * **Usage example:**
     * ```kotlin
     *  rejectVersionIf {
     *      candidateStabilityLevel() isLessStableThan StabilityLevel.Milestone
     *  }
     *  ```
     */
    fun rejectVersionIf(predicate: ComponentSelectionData.() -> Boolean) {
        rejectVersionsPredicate = predicate
        acceptVersionsPredicate = null
    }

    /**
     * Candidate version will be **rejected** unless the passed [predicate] returns `true`.
     *
     * This predicate is mutually exclusive with [rejectVersionIf], you should use only one (the last one wins).
     *
     * **Usage example:**
     * ```kotlin
     * acceptVersionOnlyIf {
     *     candidateStabilityLevel() isAtLeastAsStableAs when (candidate.group) {
     *         "org.jetbrains.kotlinx" -> when (candidate.module) {
     *             "kotlinx-coroutines" -> StabilityLevel.Alpha
     *             else -> StabilityLevel.Beta
     *         }
     *         else -> StabilityLevel.ReleaseCandidate
     *     }
     * }
     * ```
     */
    @Incubating
    fun acceptVersionOnlyIf(predicate: ComponentSelectionData.() -> Boolean) {
        acceptVersionsPredicate = predicate
        rejectVersionsPredicate = null
    }

    @Incubating
    fun alwaysUpdateStraightAway() {
        alwaysUpdateStraightAway = true
    }

    internal var alwaysUpdateStraightAway = false
        private set

    internal var rejectVersionsPredicate: (ComponentSelectionData.() -> Boolean)? = {
        candidateIsLessStableThanCurrent()
    }
        private set

    internal var acceptVersionsPredicate: (ComponentSelectionData.() -> Boolean)? = null
        private set
}
