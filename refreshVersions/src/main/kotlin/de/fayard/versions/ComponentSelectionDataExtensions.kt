package de.fayard.versions

import de.fayard.versions.extensions.stabilityLevel
import org.gradle.api.Incubating

fun ComponentSelectionData.candidateIsLessStableThanCurrent(): Boolean {
    return candidateVersion.stabilityLevel().isLessStableThan(currentlyUsedVersion.stabilityLevel())
}

@Incubating
fun ComponentSelectionData.currentStabilityLevel(): StabilityLevel = currentlyUsedVersion.stabilityLevel()

@Incubating
fun ComponentSelectionData.candidateStabilityLevel(): StabilityLevel = candidateVersion.stabilityLevel()


private val ComponentSelectionData.candidateVersion: Version inline get() = Version(candidate.version)
private val ComponentSelectionData.currentlyUsedVersion: Version inline get() = Version(currentVersion)
