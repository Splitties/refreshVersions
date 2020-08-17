package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.RefreshVersionsCorePlugin
import org.gradle.api.Project
import org.gradle.api.logging.Logger

internal fun Project.updateGradleSettings(
    selfUpdates: DependencyWithVersionCandidates
) {
    val settingsFile = file("settings.gradle.kts").let { kotlinDslSettings ->
        if (kotlinDslSettings.exists()) kotlinDslSettings else {
            file("settings.gradle").also { check(it.exists()) }
        }
    }

    val initialContent = settingsFile.readText()

    val newContent = getSettingsWithSelfUpdates(
        logger = logger,
        settingsFilename = settingsFile.name,
        initialContent = initialContent,
        selfUpdates = selfUpdates
    )

    settingsFile.writeText(newContent)
}

private fun getSettingsWithSelfUpdates(
    logger: Logger,
    settingsFilename: String,
    initialContent: String,
    selfUpdates: DependencyWithVersionCandidates
): String {

    if (selfUpdates.versionsCandidates.isEmpty()) {
        return initialContent
    }

    val logMarker = RefreshVersionsCorePlugin.LogMarkers.default

    logger.warn(
        logMarker,
        "A new version of refreshVersions is available. Open the $settingsFilename file to apply it."
    )

    fun logError(message: String) = logger.error(logMarker, message)

    val currentVersion = selfUpdates.currentVersion

    val dependencyNotationIndex: Int = run {
        val expectedDependencyNotation = "de.fayard.refreshVersions:refreshVersions:$currentVersion"
        initialContent.indexOf(expectedDependencyNotation).let {
            if (it != -1) it else {
                @Suppress("name_shadowing")
                val expectedDependencyNotation = "de.fayard.refreshVersions:refreshVersions-core:$currentVersion"
                initialContent.indexOf(expectedDependencyNotation).also {
                    if (it == -1) {
                        logError("Didn't find expected refreshVersions dependency declaration in " +
                                "$settingsFilename. Skipping.")
                        return initialContent
                    }
                }
            }
        }
    }

    val dependencyDeclarationLineStart = initialContent.lastIndexOf(
        char = '\n',
        startIndex = dependencyNotationIndex
    ).also {
        if (it == -1) {
            logError("Expected a new line before refreshVersions dependency declaration. Skipping.")
            return initialContent
        }
    } + 1

    val dependencyDeclarationLineEnd = initialContent.indexOf(
        char = '\n',
        startIndex = dependencyNotationIndex
    ).also {
        if (it == -1) {
            logError("Expected a new line after refreshVersions dependency declaration. Skipping.")
            return initialContent
        }
    }
    val declarationLine = initialContent.substring(
        startIndex = dependencyDeclarationLineStart,
        endIndex = dependencyDeclarationLineEnd
    )
    val currentVersionPrefixLength = declarationLine.substringBefore(currentVersion).length

    val commentStart = "////"

    val indexAfterPreviousAvailableComments = dependencyDeclarationLineEnd.let {
        var index = it
        val generatedCommentLineStart = "\n$commentStart"
        do {
            val indexOfNextLine = initialContent.indexOf(
                char = '\n',
                startIndex = index
            ).also { i ->
                check(i != -1) {
                    "Didn't find expected new line after refreshVersions dependency declaration."
                }
            }
            val indexOfNextGeneratedComment = initialContent.indexOf(
                string = generatedCommentLineStart,
                startIndex = index
            )
            index = indexOfNextLine + 1
        } while (indexOfNextLine == indexOfNextGeneratedComment)
        index
    }

    return buildString {
        appendln(initialContent.substring(startIndex = 0, endIndex = dependencyDeclarationLineEnd))
        selfUpdates.versionsCandidates.forEach { versionCandidate ->
            append(commentStart)
            append(availableComment.padStart(currentVersionPrefixLength - commentStart.length - 1))
            append(':')
            append(versionCandidate.value)
            appendln(declarationLine.substringAfter(currentVersion))
        }
        append(initialContent.substring(startIndex = indexAfterPreviousAvailableComments))
    }
}
