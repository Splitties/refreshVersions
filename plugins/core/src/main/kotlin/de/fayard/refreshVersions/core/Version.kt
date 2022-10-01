package de.fayard.refreshVersions.core

import org.gradle.api.Incubating
import java.math.BigInteger

@Incubating
data class Version(val value: String) : Comparable<Version> {

    /**
     * Check order is important. From least stable to most stable, then unknown
     */
    val stabilityLevel: StabilityLevel by lazy(LazyThreadSafetyMode.NONE) {
        value.findStabilityLevel(fullVersion = true) ?: StabilityLevel.Stable // Probably non-stability related suffixes.
    }

    private fun String.findStabilityLevel(fullVersion: Boolean): StabilityLevel? {
        fun String.matches(
            kind: String,
            requireNumber: Boolean = false,
            ignoreCase: Boolean = true
        ) = isStabilityLevel(
            stabilityLevelMarker = kind,
            ignoreCase = ignoreCase,
            requireNumber = requireNumber,
            isFragment = fullVersion.not(),
            version = this
        )
        return when {
            "SNAPSHOT" in this -> StabilityLevel.Snapshot
            matches("preview") -> StabilityLevel.Preview
            matches("dev") -> StabilityLevel.Development
            matches("alpha") -> StabilityLevel.Alpha
            matches("beta") -> StabilityLevel.Beta
            matches("eap") -> StabilityLevel.EarlyAccessProgram
            matches("M", requireNumber = true) -> StabilityLevel.Milestone
            matches("RC") -> StabilityLevel.ReleaseCandidate
            isDefinitelyStable(this) -> StabilityLevel.Stable
            else -> null
        }
    }

    val isRange: Boolean by lazy(LazyThreadSafetyMode.NONE) {
        value.isRange()
    }

    override fun compareTo(other: Version): Int = versionComparator.compare(this, other)

    private val comparableList: List<Comparable<*>> by lazy(LazyThreadSafetyMode.NONE) { toComparableList() }

    companion object {

        //region Version comparison logic details (private symbols only)

        private val npmRangeCharsRegex = "[<=>^~*]|\\.x".toRegex()

        private val versionComparator: Comparator<Version> = object : Comparator<Version> {

            // Usage of this comparator is likely to get discontinued in favor of distinct per-repo ordered results.
            //
            // Still this was made as an attempt to sort the versions, but it didn't follow the rules that Gradle defined,
            // because I wasn't aware of them, but here's the documentation link for version ordering, for reference:
            // https://docs.gradle.org/current/userguide/single_versions.html#version_ordering

            override fun compare(o1: Version, o2: Version): Int {
                if (o1 == o2) return 0

                val v1 = o1.comparableList
                val v2 = o2.comparableList
                val lastCommonIndex = minOf(v1.lastIndex, v2.lastIndex)
                for (i in 0..lastCommonIndex) {
                    val e1 = v1[i]
                    val e2 = v2[i]
                    when (e1) {
                        is BigInteger -> {
                            if (e2 is BigInteger) {
                                val comparison = e1.compareTo(e2)
                                if (comparison != 0) return comparison
                            } else {
                                check(e2 is StabilityLevel || e2 is String)
                                return +1
                            }
                        }
                        is String -> {
                            if (e2 is String) {
                                val comparison = e1.compareTo(e2)
                                if (comparison != 0) return comparison
                            } else if (e2 is BigInteger) {
                                return -1
                            } else {
                                check(e2 is StabilityLevel)
                                return -1
                            }
                        }
                        else -> {
                            check(e1 is StabilityLevel)
                            if (e2 is StabilityLevel) {
                                val comparison = reverseStabilityComparator.compare(e1, e2)
                                if (comparison != 0) return comparison
                            } else if (e2 is BigInteger) {
                                return -1
                            } else {
                                check(e2 is String)
                                return +1
                            }
                        }
                    }
                }
                return when {
                    v1.lastIndex > lastCommonIndex -> {
                        val e1 = v1[lastCommonIndex + 1]
                        if (e1 is BigInteger || e1 is String) {
                            +1
                        } else {
                            check(e1 is StabilityLevel)
                            -1
                        }
                    }
                    v2.lastIndex > lastCommonIndex -> {
                        val e2 = v2[lastCommonIndex + 1]
                        if (e2 is BigInteger || e2 is String) {
                            -1
                        } else {
                            check(e2 is StabilityLevel)
                            +1
                        }
                    }
                    else -> o1.value.compareTo(o2.value)
                }
            }
        }

        private val reverseStabilityComparator: Comparator<StabilityLevel> = compareByDescending { it }

        private val knownStableKeywords = listOf("RELEASE", "FINAL", "GA")
        private val digitsOnlyBasedVersionNumberRegex = "^[0-9,.v-]+$".toRegex()

        private fun isDefinitelyStable(version: String): Boolean {
            val uppercaseVersion = version.toUpperCase()
            val hasStableKeyword = knownStableKeywords.any { it in uppercaseVersion }
            return hasStableKeyword || digitsOnlyBasedVersionNumberRegex.matches(version)
        }

        private fun isStabilityLevel(
            stabilityLevelMarker: String,
            ignoreCase: Boolean = true,
            requireNumber: Boolean,
            isFragment: Boolean,
            version: String
        ): Boolean = when (val indexOfStabilityLevelMarker = version.indexOf(
            string = stabilityLevelMarker,
            ignoreCase = ignoreCase
        )) {
            -1 -> false
            else -> version.getOrNull(
                index = indexOfStabilityLevelMarker + stabilityLevelMarker.length
            )?.let { it.isDigit() || it == '-' } ?: isFragment || requireNumber.not()
        }

        private fun String.isRange(): Boolean {
            if (isEmpty()) return false
            val npmOperators = "^~*"
            val yarnOperators = "<>="
            val firstCharOperators = npmOperators + yarnOperators
            return when {
                first() in firstCharOperators -> true
                " - " in this -> true // yarn hyphen range
                " || " in this -> true // yarn union
                ".x" in this -> true // x ranges
                else -> false
            }
        }

        private fun String.rangeComponents(): List<Version> {
            return this
                .replace(npmRangeCharsRegex, "")
                .split(" - ", " || ", " ")
                .filter { it.isNotBlank() }
                .map { Version(it) }
        }

        private fun Version.toComparableList(): List<Comparable<*>> {
            if (this.isRange) {
                val lowerBound: Version = value.rangeComponents().minOrNull()
                    ?: error("no lower version bound found in range: '$value'")
                return lowerBound.toComparableList()
            }
            return value.withoutKnownStableKeywords().split(".", "-").flatMap {
                it.toBigIntegerOrNull()?.let { number -> listOf(number) }
                    ?: it.findStabilityLevel(fullVersion = false)?.let { level ->
                        val indexOfLastNonDigit = it.indexOfLast { c -> c.isDigit().not() }
                        if (indexOfLastNonDigit == -1 || indexOfLastNonDigit == it.lastIndex) listOf(level)
                        else listOf(level, it.substring(startIndex = indexOfLastNonDigit + 1).toBigInteger())
                    } ?: listOf(it)
            }
        }

        private fun String.withoutKnownStableKeywords(): String {
            var result: String = this
            for (suffix in knownStableKeywords) {
                result = result.replace(suffix, "")
            }
            return result
        }

        //endregion
    }
}
