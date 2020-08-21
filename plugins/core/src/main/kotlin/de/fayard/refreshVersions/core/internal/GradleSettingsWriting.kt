package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.RefreshVersionsCorePlugin
import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import java.io.File

internal fun Project.updateGradleSettingsIncludingForBuildSrc(
    selfUpdates: DependencyWithVersionCandidates
) {
    updateGradleSettings(selfUpdates)
    RefreshVersionsConfigHolder.buildSrc?.updateGradleSettings(selfUpdates)
}

private fun Project.updateGradleSettings(
    selfUpdates: DependencyWithVersionCandidates
) {
    val isKotlinDsl: Boolean
    val settingsFile = file("settings.gradle.kts").let { kotlinDslSettings ->
        if (kotlinDslSettings.exists()) kotlinDslSettings.also { isKotlinDsl = true } else {
            file("settings.gradle").also {
                check(it.exists())
                isKotlinDsl = false
            }
        }
    }

    val newContent = getSettingsWithSelfUpdates(
        logger = logger,
        settingsFile = settingsFile,
        initialContent = settingsFile.readText(),
        selfUpdates = selfUpdates
    ).let { text ->
        getSettingsWithMigrationCall(
            logger = logger,
            settingsFile = settingsFile,
            isBuildSrc = isBuildSrc,
            initialContent = text,
            isKotlinDsl = isKotlinDsl,
            selfUpdates = selfUpdates
        )
    }

    settingsFile.writeText(newContent)
}

private fun getSettingsWithSelfUpdates(
    logger: Logger,
    settingsFile: File,
    initialContent: String,
    selfUpdates: DependencyWithVersionCandidates
): String {

    if (selfUpdates.versionsCandidates.isEmpty()) {
        return initialContent
    }

    val settingsFilename = settingsFile.name

    val logMarker = RefreshVersionsCorePlugin.LogMarkers.default

    logger.warn(
        logMarker,
        "A new version of refreshVersions is available. Open the $settingsFilename file to apply it."
    )

    fun logError(message: String) = logger.error(logMarker, "e: ${settingsFile.path}:\n$message")

    val currentVersion = selfUpdates.currentVersion

    val dependencyNotationIndex: Int = run {
        val expectedDependencyNotation = "de.fayard.refreshVersions:refreshVersions:$currentVersion"
        initialContent.indexOf(expectedDependencyNotation).let {
            if (it != -1) it else {
                @Suppress("name_shadowing")
                val expectedDependencyNotation = "de.fayard.refreshVersions:refreshVersions-core:$currentVersion"
                initialContent.indexOf(expectedDependencyNotation).also { i ->
                    if (i == -1) {
                        logError("Didn't find expected refreshVersions dependency declaration. Skipping.")
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

private fun getSettingsWithMigrationCall(
    logger: Logger,
    settingsFile: File,
    isBuildSrc: Boolean,
    isKotlinDsl: Boolean,
    initialContent: String,
    selfUpdates: DependencyWithVersionCandidates
): String {

    if (selfUpdates.versionsCandidates.isEmpty()) {
        return initialContent
    }

    val logMarker = RefreshVersionsCorePlugin.LogMarkers.default

    val currentVersion = selfUpdates.currentVersion

    class ExpectedValues(
        bootstrapPackageName: String = "de.fayard.refreshVersions",
        migrationPackageName: String = bootstrapPackageName,
        bootstrapSymbol: String,
        migrationCallSymbol: String
    ) {
        val bootstrapImport = "import $bootstrapPackageName.${bootstrapSymbol.substringBefore('.')}"
        val bootstrapText = "\n$bootstrapSymbol("

        val migrationCallImport = "import $migrationPackageName.${migrationCallSymbol.substringBefore('.')}"
        val migrationCallText = "\n$migrationCallSymbol("
    }

    val expectedValues = ExpectedValues(
        bootstrapSymbol = when {
            isBuildSrc -> when {
                isKotlinDsl -> "bootstrapRefreshVersionsForBuildSrc"
                else -> "RefreshVersionsSetup.bootstrapForBuildSrc"
            }
            else -> when {
                isKotlinDsl -> "bootstrapRefreshVersions"
                else -> "RefreshVersionsSetup.bootstrap"
            }
        },
        migrationCallSymbol = when {
            isKotlinDsl -> "migrateRefreshVersionsIfNeeded"
            else -> "RefreshVersionsMigration.migrateIfNeeded"
        }
    )

    fun logError(message: String) = logger.error(logMarker, "e: ${settingsFile.path}:\n$message")

    val bootstrapImportIndex = initialContent.indexOf(expectedValues.bootstrapImport).also { i ->
        if (i == -1) {
            logError("Didn't find expected import for refreshVersions bootstrap.")
            return initialContent
        }
    }

    val hasMigrationImport = initialContent.indexOf(expectedValues.migrationCallImport) != -1

    val bootstrapIndex = initialContent.indexOf(expectedValues.bootstrapText).also { i ->
        if (i == -1) {
            logError("Didn't find expected refreshVersions bootstrap.")
            return initialContent
        }
    }
    val migrationCallIndex = initialContent.indexOf(expectedValues.migrationCallText)

    val hasMigrationCall = migrationCallIndex != -1

    val startIndexForMigrationCall: Int = when {
        hasMigrationCall -> migrationCallIndex.also { i ->
            check(i < bootstrapIndex) { "refreshVersions' migration call must be before bootstrap" }
        }
        else -> bootstrapIndex
    }

    return buildString {
        if (hasMigrationImport) {
            append(initialContent.substring(startIndex = 0, endIndex = startIndexForMigrationCall))
        } else {
            val bootstrapEndOfLineIndex = initialContent.indexOf(
                char = '\n',
                startIndex = bootstrapImportIndex
            ).also { i -> check(i != -1) { "Didn't find expected end of line after import." } }

            appendln(initialContent.substring(startIndex = 0, endIndex = bootstrapEndOfLineIndex))
            append(expectedValues.migrationCallImport)
            append(
                initialContent.substring(
                    startIndex = bootstrapEndOfLineIndex,
                    endIndex = startIndexForMigrationCall
                )
            )
        }

        val migrationCallComment =
            "Will be automatically removed by refreshVersions when upgraded to the latest version."

        append("""${expectedValues.migrationCallText}"$currentVersion") // $migrationCallComment""")

        if (hasMigrationCall) {
            val previousMigrationCallEndOfLineIndex = initialContent.indexOf(
                char = '\n',
                startIndex = migrationCallIndex + 1
            ).also { i ->
                check(i != -1) { "Didn't find expected end of line after previous migration call." }
            }
            append(initialContent.substring(startIndex = previousMigrationCallEndOfLineIndex))
        } else {
            appendln()
            append(initialContent.substring(startIndex = bootstrapIndex))
        }
    }
}
