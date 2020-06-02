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
    Preview,
    Snapshot,
    Unknown;

    infix fun isMoreStableThan(other: StabilityLevel): Boolean = ordinal < other.ordinal
    infix fun isLessStableThan(other: StabilityLevel): Boolean = ordinal > other.ordinal
    infix fun isAtLeastAsStableAs(other: StabilityLevel): Boolean = isLessStableThan(other).not()
}
