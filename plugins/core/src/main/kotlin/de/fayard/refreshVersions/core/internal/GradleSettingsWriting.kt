package de.fayard.refreshVersions.core.internal

import org.gradle.api.Project

internal fun Project.updateGradleSettings(
    selfUpdates: DependencyWithVersionCandidates
) {
    val settingsFile = file("settings.gradle.kts").let { kotlinDslSettings ->
        if (kotlinDslSettings.exists()) kotlinDslSettings else {
            file("settings.gradle").also { check(it.exists()) }
        }
    }

    val initialContent = settingsFile.readText()

    val currentVersion = selfUpdates.currentVersion
    val expectedDependencyNotation = "de.fayard.refreshVersions:refreshVersions:$currentVersion"

    val dependencyNotationIndex = initialContent.indexOf(expectedDependencyNotation)
    val dependencyDeclarationLineStart = initialContent.lastIndexOf('\n', startIndex = dependencyNotationIndex)
    val dependencyDeclarationLineEnd = initialContent.indexOf('\n', startIndex = dependencyNotationIndex)
    val declarationLine = initialContent.substring(
        startIndex = dependencyDeclarationLineStart + 1,
        endIndex = dependencyDeclarationLineEnd
    )
    val currentVersionPrefixLength = declarationLine.substringBefore(currentVersion).length

    val newContent = buildString {
        appendln(initialContent.substring(startIndex = 0, endIndex = dependencyDeclarationLineEnd))
        selfUpdates.versionsCandidates.forEach { versionCandidate ->
            val commentStart = "////"
            append(commentStart)
            append(availableComment.padStart(currentVersionPrefixLength - commentStart.length - 1))
            append(':')
            append(versionCandidate.value)
            appendln(declarationLine.substringAfter(currentVersion))
        }
        append(initialContent.substring(startIndex = dependencyDeclarationLineEnd))
    }

    settingsFile.writeText(newContent)

    //TODO: Don't fail but warn if unable to parse used version of refreshVersions
    //TODO: Skip reading & writing if no refreshVersions update available
    //TODO: Warn/inform if an update is available
    //TODO: Remove (stale) comment lines previously added by refreshVersions (they start with //// and have the `availableComment`)
}
