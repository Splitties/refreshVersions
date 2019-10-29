package de.fayard.versions

fun ComponentSelectionData.candidateIsLessStableThanCurrent(): Boolean {
    return candidateVersion.stabilityLevel().isAtLeastAsStableAs(currentlyUsedVersion.stabilityLevel()).not()
}

private val ComponentSelectionData.candidateVersion: Version inline get() = Version(candidate.version)
private val ComponentSelectionData.currentlyUsedVersion: Version inline get() = Version(currentVersion)

private inline class Version(val value: String)

private fun Version.stabilityLevel(): StabilityLevel = when {
    isStable() -> StabilityLevel.Stable
    "rc" in value -> StabilityLevel.ReleaseCandidate
    isMilestone() -> StabilityLevel.Milestone
    "eap" in value -> StabilityLevel.EarlyAccessProgram
    "beta" in value -> StabilityLevel.Beta
    "alpha" in value -> StabilityLevel.Alpha
    "dev" in value -> StabilityLevel.Development
    else -> StabilityLevel.Unknown
}

//TODO: Allow to get if release stability level between Stable, RC, M(ilestone), eap, beta, alpha, dev and unknown,
// then allow setting default level and per group/artifact exceptions. Maybe through comments in gradle.properties?
private fun Version.isStable(): Boolean {
    val version = value
    //TODO: cache list and regex for improved efficiency.
    val hasStableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+$".toRegex()
    return hasStableKeyword || regex.matches(version)
}

private fun Version.isMilestone(): Boolean {
    val version = value
    return when (val indexOfM = version.indexOfLast { it == 'M' }) {
        -1 -> false
        version.lastIndex -> false
        else -> version.substring(startIndex = indexOfM + 1).all { it.isDigit() }
    }
}
