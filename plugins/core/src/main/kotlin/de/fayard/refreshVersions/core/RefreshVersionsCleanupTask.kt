package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.OutputFile
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.core.internal.SettingsPluginsUpdater.removeCommentsAddedByUs
import de.fayard.refreshVersions.core.internal.TomlUpdater
import de.fayard.refreshVersions.core.internal.VersionCatalogs
import de.fayard.refreshVersions.core.internal.VersionCatalogs.LIBS_VERSIONS_TOML
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section
import de.fayard.refreshVersions.core.internal.versions.readFromFile
import de.fayard.refreshVersions.core.internal.versions.writeTo
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction
import java.io.File

open class RefreshVersionsCleanupTask : DefaultTask() {

    @TaskAction
    fun cleanUpVersionsProperties() {
        OutputFile.checkWhichFilesExist()
        val model = VersionsPropertiesModel.readFromFile(RefreshVersionsConfigHolder.versionsPropertiesFile)

        val sectionsWithoutAvailableUpdates = model.sections.map { section ->
            when (section) {
                is Section.Comment -> section
                is Section.VersionEntry -> section.copy(availableUpdates = emptyList())
            }
        }
        val newModel = model.copy(sections = sectionsWithoutAvailableUpdates)
        newModel.writeTo(RefreshVersionsConfigHolder.versionsPropertiesFile)
        OutputFile.VERSIONS_PROPERTIES.logFileWasModified()
    }

    @TaskAction
    fun cleanUpSettings() {
        val settingsFiles = OutputFile.settingsFiles
            .filter { it.existed }

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

        settingsFiles.forEach { it.logFileWasModified() }
    }

    @TaskAction
    fun cleanUpVersionsCatalog() {
        if (VersionCatalogs.isSupported()) {
            val file = File(LIBS_VERSIONS_TOML)
            TomlUpdater(file, emptyList()).cleanupComments(file)
            OutputFile.GRADLE_VERSIONS_CATALOG.logFileWasModified()
        }
    }
}
