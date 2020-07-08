package de.fayard.refreshVersions.core.extensions.gradle

import org.gradle.api.artifacts.VersionConstraint

internal fun VersionConstraint.hasDynamicVersion(): Boolean {

    fun String.isVersionDynamic(): Boolean {
        if (isEmpty()) return false
        val isVersionRange = first() in "[]()" && ',' in this
        val isPlusVersion = '+' in this
        val isLatestStatus = startsWith("latest.")
        val isDynamicSnapshot = endsWith("-SNAPSHOT")
        return isVersionRange || isPlusVersion || isLatestStatus || isDynamicSnapshot
    }

    if (strictVersion.isVersionDynamic().not()) return false
    if (preferredVersion.isVersionDynamic().not()) return false
    if (requiredVersion.isVersionDynamic().not()) return false
    return true
}
