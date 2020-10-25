package de.fayard.refreshVersions.core.internal.versions

import de.fayard.refreshVersions.core.RefreshVersionsCorePlugin
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.core.extensions.gradle.toModuleIdentifier
import de.fayard.refreshVersions.core.internal.DependencyWithVersionCandidates
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.core.internal.getVersionPropertyName
import de.fayard.refreshVersions.core.internal.isAVersionAlias
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Companion.availableComment
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section.Comment
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section.VersionEntry
import java.io.File

internal fun VersionsPropertiesModel.Companion.writeWithNewVersions(
    dependenciesWithLastVersion: List<DependencyWithVersionCandidates>
) {
    val versionKeyReader = RefreshVersionsConfigHolder.versionKeyReader

    val candidatesMap = dependenciesWithLastVersion.associateBy {
        getVersionPropertyName(it.moduleId.toModuleIdentifier(), versionKeyReader)
    }

    update { model ->
        model.copy(
            sections = model.sections.map { section ->
                when (section) {
                    is Comment -> section
                    is VersionEntry -> {

                        if (section.currentVersion.isAVersionAlias()) return@map section

                        val versionsCandidates = candidatesMap[section.key]?.versionsCandidates
                            ?: return@map section
                        section.copy(availableUpdates = versionsCandidates.map { it.value })
                    }
                }
            }
        )
    }
}

internal fun VersionsPropertiesModel.Companion.writeWithNewEntry(
    propertyName: String,
    versionsCandidates: List<Version>
) {
    VersionsPropertiesModel.update { model ->
        model + VersionEntry(
            key = propertyName,
            currentVersion = versionsCandidates.first().value,
            availableUpdates = versionsCandidates.drop(1).map { it.value }
        )
    }
}

internal fun VersionsPropertiesModel.writeTo(versionsPropertiesFile: File) {
    val finalModel = this.copy(
        generatedByVersion = pluginVersion
    )
    versionsPropertiesFile.writeText(finalModel.toText())
}

private val pluginVersion by lazy {
    RefreshVersionsCorePlugin::class.java.getResourceAsStream("/version.txt")
        .bufferedReader()
        .useLines { it.first() }
}

/**
 * [transform] is crossinline to enforce synchronous execution of (no suspension points).
 */
private inline fun VersionsPropertiesModel.Companion.update(
    versionsPropertiesFile: File = RefreshVersionsConfigHolder.versionsPropertiesFile,
    crossinline transform: (model: VersionsPropertiesModel) -> VersionsPropertiesModel
) {
    require(versionsPropertiesFile.name == "versions.properties")
    synchronized(updateLock) {
        val newModel = transform(VersionsPropertiesModel.readFrom(versionsPropertiesFile))
        newModel.writeTo(versionsPropertiesFile)
    }
}

private val updateLock = Any()

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
