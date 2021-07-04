package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.core.internal.SettingsPluginsUpdater.removeCommentsAddedByUs
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section
import de.fayard.refreshVersions.core.internal.versions.readFrom
import de.fayard.refreshVersions.core.internal.versions.writeTo
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class RefreshVersionsCleanupTask : DefaultTask() {

    @TaskAction
    fun cleanUpVersionsProperties() {
        val model = VersionsPropertiesModel.readFrom(RefreshVersionsConfigHolder.versionsPropertiesFile)

        val sectionsWithoutAvailableUpdates = model.sections.map { section ->
            when (section) {
                is Section.Comment -> section
                is Section.VersionEntry -> section.copy(availableUpdates = emptyList())
            }
        }
        val newModel = model.copy(sections = sectionsWithoutAvailableUpdates)
        newModel.writeTo(RefreshVersionsConfigHolder.versionsPropertiesFile)
    }

    @TaskAction
    fun cleanUpSettings() {
        val settingsFiles = listOf(
            "settings.gradle",
            "settings.gradle.kts",
            "buildSrc/settings.gradle",
            "buildSrc/settings.gradle.kts"
        ).mapNotNull { path ->
            project.file(path).takeIf { it.exists() }
        }

        settingsFiles.forEach { settingsFile ->
            val initialContent = settingsFile.readText()
            val newContent = buildString {
                append(initialContent)
                removeCommentsAddedByUs()
            }
            if (initialContent.length != newContent.length) {
                settingsFile.writeText(newContent)
            }
        }
    }
}
