package de.fayard.versions

import org.gradle.api.Incubating

@Incubating
enum class StabilityLevel {
    Stable,
    ReleaseCandidate,
    Milestone,
    EarlyAccessProgram,
    Beta,
    Alpha,
    Development,
    Unknown;

    fun isAtLeastAsStableAs(other: StabilityLevel): Boolean = ordinal <= other.ordinal
}
