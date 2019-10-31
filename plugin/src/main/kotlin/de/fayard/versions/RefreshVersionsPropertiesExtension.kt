package de.fayard.versions

import org.gradle.api.Incubating

open class RefreshVersionsPropertiesExtension {

    /**
     * Return true to reject a candidate.
     *
     * Default:
     *
     * ```kotlin
     *  rejectVersionIf {
     *      candidateIsLessStableThanCurrent()
     *  }
     *  ```
     *
     * Possible usage:
     * ```kotlin
     *  rejectVersionIf {
     *      TODO("Show actual example with kotlinx.coroutines and some AndroidX library using non stable releases")
     *  }
     *  ```
     */
    fun rejectVersionIf(predicate: ComponentSelectionData.() -> Boolean) {
        rejectVersionsPredicate = predicate
    }

    @Incubating
    fun alwaysUpdateStraightAway() {
        alwaysUpdateStraightAway = true
    }

    internal var alwaysUpdateStraightAway = false
        private set

    internal var rejectVersionsPredicate: ComponentSelectionData.() -> Boolean = { candidateIsLessStableThanCurrent() }
        private set
}
