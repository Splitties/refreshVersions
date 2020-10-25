package de.fayard.refreshVersions.core.internal.versions

import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.core.extensions.gradle.toModuleIdentifier
import de.fayard.refreshVersions.core.internal.DependencyWithVersionCandidates
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.core.internal.getVersionPropertyName
import de.fayard.refreshVersions.core.internal.isAVersionAlias
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Companion.availableComment
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section.Comment
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section.VersionEntry
import org.gradle.api.Project
import java.io.File

internal fun Project.updateVersionsProperties(
    dependenciesWithLastVersion: List<DependencyWithVersionCandidates>
) {
    val versionKeyReader = RefreshVersionsConfigHolder.versionKeyReader
    val file = RefreshVersionsConfigHolder.versionsPropertiesFile

    val model = VersionsPropertiesModel.readFrom(file)
    val updates = dependenciesWithLastVersion.associateBy { (moduleId, _, _) ->
        getVersionPropertyName(moduleId.toModuleIdentifier(), versionKeyReader)
    }

    val newModel = model.copy(
        sections = model.sections.map { section ->
            when (section) {
                is Comment -> section
                is VersionEntry -> {
                    if (section.currentVersion.isAVersionAlias()) return@map section
                    val update = updates[section.key] ?: return@map section
                    section.copy(
                        availableUpdates = update.versionsCandidates.map { it.value }
                    )
                }
            }
        }
    )
    newModel.writeTo(file)
}

internal fun VersionsPropertiesModel.Companion.writeWithNewEntry(
    versionsFile: File,
    propertyName: String,
    versionsCandidates: List<Version>
) {
    val model = VersionsPropertiesModel.readFrom(versionsFile)

    val newModel = model + VersionEntry(
        key = propertyName,
        currentVersion = versionsCandidates.first().value,
        availableUpdates = versionsCandidates.drop(1).map { it.value }
    )
    newModel.writeTo(versionsFile)
}

internal fun VersionsPropertiesModel.writeTo(versionsPropertiesFile: File) {
    val finalModel = this.copy(
        generatedByVersion = "0.9.8-SNAPSHOT" //TODO: Get actual version. Use symlink to have it in resources.
    )
    versionsPropertiesFile.writeText(finalModel.toText())
}

internal fun VersionsPropertiesModel.toText(): String = buildString {
    append(preHeaderContent)
    appendln(VersionsPropertiesModel.versionsPropertiesHeader(version = generatedByVersion))
    if (sections.isEmpty()) return@buildString
    appendln()
    val sb = StringBuilder()
    sections.joinTo(buffer = this, separator = "\n") { it.toText(sb) }
}

private fun VersionsPropertiesModel.Section.toText(
    builder: StringBuilder
): CharSequence = when (this) {
    is Comment -> builder.apply { clear(); appendln(lines) }
    is VersionEntry -> builder.apply {
        clear()
        leadingCommentLines.forEach { appendln(it) }

        val paddedKey = key.padStart(availableComment.length + 2)
        val currentVersionLine = "$paddedKey=$currentVersion"
        appendln(currentVersionLine)
        availableUpdates.forEach { versionCandidate ->
            append("##"); append(availableComment.padStart(key.length - 2))
            append('='); appendln(versionCandidate)
        }

        trailingCommentLines.forEach { appendln(it) }
    }
}
