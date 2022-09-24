package de.fayard.refreshVersions.core.internal.versions

import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.RefreshVersionsCorePlugin
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.core.internal.DependencyWithVersionCandidates
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.core.internal.getVersionPropertyName
import de.fayard.refreshVersions.core.internal.isAVersionAlias
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Companion.availableComment
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Companion.isUsingVersionRejectionHeader
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section.Comment
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section.VersionEntry
import org.gradle.api.artifacts.Dependency
import java.io.File

internal fun VersionsPropertiesModel.Companion.writeWithNewEntries(newEntries: Map<String, Dependency>) {
    update { model ->
        model + newEntries.map { (key, d: Dependency) ->
            VersionEntry(
                key = key,
                currentVersion = d.version!!,
                availableUpdates = emptyList()
            )
        }.sortedBy { it.key }
    }
}

internal fun VersionsPropertiesModel.Companion.writeWithNewVersions(
    dependenciesWithLastVersion: List<DependencyWithVersionCandidates>
) {
    val versionKeyReader = RefreshVersionsConfigHolder.versionKeyReader

    val candidatesMap = dependenciesWithLastVersion.associateBy {
        getVersionPropertyName(it.moduleId, versionKeyReader)
    }

    update { model ->
        model.copy(
            sections = model.sections.map { section ->
                when (section) {
                    is Comment -> section
                    is VersionEntry -> {
                        if (section.currentVersion.isAVersionAlias()) return@map section
                        when (val data = candidatesMap[section.key]) {
                            null -> section.asUnused(isUnused = true)
                            else -> section.copy(
                                availableUpdates = data.versionsCandidates(Version(section.currentVersion)).map { it.value },
                            ).asUnused(isUnused = false).withFailures(data.failures)
                        }
                    }
                }
            }
        )
    }
}

private fun VersionEntry.asUnused(isUnused: Boolean): VersionEntry {
    val wasMarkedAsUnused = this.leadingCommentLines.any {
        it.contains(VersionsPropertiesModel.unusedEntryComment)
    }
    if (isUnused == wasMarkedAsUnused) return this
    return when {
        isUnused -> copy(leadingCommentLines = leadingCommentLines + VersionsPropertiesModel.unusedEntryComment)
        else -> copy(leadingCommentLines = leadingCommentLines - VersionsPropertiesModel.unusedEntryComment)
    }
}

private fun VersionEntry.withFailures(failures: List<DependencyVersionsFetcher.Result.Failure>): VersionEntry {
    val hasExistingFailureComments = this.leadingCommentLines.any {
        it.startsWith(VersionsPropertiesModel.failureComment)
    }
    if (hasExistingFailureComments.not() && failures.isEmpty()) return this

    val cleanedUpLeadingCommentLines: List<String> = this.leadingCommentLines.let { commentsList ->
        when {
            hasExistingFailureComments -> commentsList.filterNot { commentLine ->
                commentLine.startsWith(VersionsPropertiesModel.failureComment)
            }
            else -> commentsList
        }
    }
    if (failures.isEmpty()) {
        return copy(leadingCommentLines = cleanedUpLeadingCommentLines)
    }
    val newLeadingCommentLines = cleanedUpLeadingCommentLines + failures.map {
        VersionsPropertiesModel.failureComment(it)
    }
    return copy(leadingCommentLines = newLeadingCommentLines)
}

internal fun VersionsPropertiesModel.Companion.writeWithNewEntry(
    propertyName: String,
    versionsCandidates: List<Version>,
    failures: List<DependencyVersionsFetcher.Result.Failure>
) {
    VersionsPropertiesModel.update { model ->
        model + VersionEntry(
            key = propertyName,
            currentVersion = versionsCandidates.first().value,
            availableUpdates = versionsCandidates.drop(1).map { it.value }
        ).withFailures(failures)
    }
}

internal fun VersionsPropertiesModel.writeTo(versionsPropertiesFile: File) {
    val finalModel = this.copy(
        generatedByVersion = RefreshVersionsCorePlugin.currentVersion
    )
    versionsPropertiesFile.writeText(finalModel.toText())
}

/**
 * [transform] is crossinline to enforce synchronous execution of (no suspension points).
 */
private inline fun VersionsPropertiesModel.Companion.update(
    versionsPropertiesFile: File = RefreshVersionsConfigHolder.versionsPropertiesFile,
    crossinline transform: (model: VersionsPropertiesModel) -> VersionsPropertiesModel
) {
    require(versionsPropertiesFile.name == "versions.properties")
    synchronized(versionsPropertiesFileLock) {
        val newModel = transform(VersionsPropertiesModel.readFromFile(versionsPropertiesFile))
        newModel.writeTo(versionsPropertiesFile)
    }
}

internal val versionsPropertiesFileLock = Any()

internal fun VersionsPropertiesModel.toText(): String = buildString {
    append(preHeaderContent)
    appendLine(
        VersionsPropertiesModel.versionsPropertiesHeader(
            version = generatedByVersion,
            dependencyNotationRemovalsRevision = dependencyNotationRemovalsRevision
        )
    )
    if (RefreshVersionsConfigHolder.isUsingVersionRejection) {
        appendLine(isUsingVersionRejectionHeader)
    }
    if (sections.isEmpty()) return@buildString
    appendLine()
    val sb = StringBuilder()
    sections.joinTo(buffer = this, separator = "\n") { it.toText(sb) }

    // Ensure a single empty line at end of file.
    replace(indexOfLast { it.isWhitespace().not() } + 1, length, "\n")
}

private fun VersionsPropertiesModel.Section.toText(
    builder: StringBuilder
): CharSequence = when (this) {
    is Comment -> builder.apply { clear(); appendLine(lines) }
    is VersionEntry -> builder.apply {
        clear()
        leadingCommentLines.forEach { appendLine(it) }

        val paddedKey = key.padStart(availableComment.length + 2)
        val currentVersionLine = "$paddedKey=$currentVersion"
        appendLine(currentVersionLine)
        availableUpdates.forEach { versionCandidate ->
            append("##"); append(availableComment.padStart(key.length - 2))
            append('='); appendLine(versionCandidate)
        }

        trailingCommentLines.forEach { appendLine(it) }
    }
}
