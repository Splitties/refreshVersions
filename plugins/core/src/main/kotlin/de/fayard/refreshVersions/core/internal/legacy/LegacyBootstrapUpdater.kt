package de.fayard.refreshVersions.core.internal.legacy

import de.fayard.refreshVersions.core.RefreshVersionsCorePlugin
import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.extensions.gradle.isRootProject
import de.fayard.refreshVersions.core.internal.DependencyWithVersionCandidates
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Companion.availableComment
import org.gradle.api.Project
import org.gradle.api.logging.Logger
import java.io.File

internal object LegacyBootstrapUpdater {

    fun updateGradleSettingsWithUpdates(
        rootProject: Project,
        selfUpdates: DependencyWithVersionCandidates
    ) {
        require(rootProject.isRootProject)
        require(rootProject.isBuildSrc.not())
        rootProject.updateGradleSettings(selfUpdates)
        RefreshVersionsConfigHolder.buildSrc?.updateGradleSettings(selfUpdates)
    }

    class ExpectedValues private constructor(
        bootstrapPackageName: String = "de.fayard.refreshVersions",
        migrationPackageName: String = bootstrapPackageName,
        val bootstrapSymbol: String,
        val migrationCallSymbol: String
    ) {
        constructor(
            isBuildSrc: Boolean,
            isKotlinDsl: Boolean
        ) : this(
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

        val bootstrapImport = "import $bootstrapPackageName.${bootstrapSymbol.substringBefore('.')}"

        val migrationCallImport = "import $migrationPackageName.${migrationCallSymbol.substringBefore('.')}"
    }
}

private fun Project.updateGradleSettings(
    selfUpdates: DependencyWithVersionCandidates
) {

    if (selfUpdates.versionsCandidates.isEmpty()) {
        return // Because we only deal with self-updates for now.
        // Remove that quick exit condition when/if we support showing updates from settings.gradle[.kts].
    }

    val settingsFile = file("settings.gradle.kts").let { kotlinDslSettings ->
        when {
            kotlinDslSettings.exists() -> kotlinDslSettings
            else -> file("settings.gradle").also { check(it.exists()) }
        }
    }

    val newContent = getSettingsWithSelfUpdates(
        logger = logger,
        settingsFile = settingsFile,
        isBuildSrc = isBuildSrc,
        initialContent = settingsFile.readText(),
        selfUpdates = selfUpdates
    )

    settingsFile.writeText(newContent)
}

private fun getSettingsWithSelfUpdates(
    logger: Logger,
    settingsFile: File,
    isBuildSrc: Boolean,
    initialContent: String,
    selfUpdates: DependencyWithVersionCandidates
): String {

    if (selfUpdates.versionsCandidates.isEmpty()) {
        return initialContent
    }

    val settingsFilenameToDisplay = settingsFile.name.let {
        if (isBuildSrc) "buildSrc/$it" else it
    }

    val logMarker = RefreshVersionsCorePlugin.LogMarkers.default

    fun logWarning(message: String) = logger.warn(logMarker, "w: ${settingsFile.path}:\n$message")
    fun logError(message: String) = logger.error(logMarker, "e: ${settingsFile.path}:\n$message")

    logWarning("A new version of refreshVersions is available.\n" +
            "Open the $settingsFilenameToDisplay file to apply the update.")

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
