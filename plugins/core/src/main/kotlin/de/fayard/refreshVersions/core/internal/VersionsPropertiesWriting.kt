package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.core.extensions.gradle.toModuleIdentifier
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section.VersionEntry
import de.fayard.refreshVersions.core.internal.versions.plus
import de.fayard.refreshVersions.core.internal.versions.readFrom
import de.fayard.refreshVersions.core.internal.versions.writeTo
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
                is VersionsPropertiesModel.Section.Comment -> section
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

internal fun writeWithAddedVersions(
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
