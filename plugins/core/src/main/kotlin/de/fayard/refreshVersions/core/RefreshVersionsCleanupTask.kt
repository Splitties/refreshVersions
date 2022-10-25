package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.OutputFile
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.core.internal.SettingsPluginsUpdater.removeCommentsAddedByUs
import de.fayard.refreshVersions.core.internal.VersionsCatalogUpdater
import de.fayard.refreshVersions.core.internal.VersionsCatalogs
import de.fayard.refreshVersions.core.internal.VersionsCatalogs.LIBS_VERSIONS_TOML
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section
import de.fayard.refreshVersions.core.internal.versions.readFromFile
import de.fayard.refreshVersions.core.internal.versions.writeTo
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import java.io.File

open class RefreshVersionsCleanupTask : DefaultTask() {

    @Input
    @Optional
    @Option(option = "enable", description = "Enable a feature flag")
    var enableFlag: FeatureFlag? = null
        set(value) {
            field = value
            if (value != null) FeatureFlag.userSettings.put(value, true)
        }

    @Input
    @Optional
    @Option(option = "disable", description = "Disable a feature flag")
    var disableFlag: FeatureFlag? = null
        set(value) {
            field = value
            if (value != null) FeatureFlag.userSettings.put(value, false)
        }

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
        if (VersionsCatalogs.isSupported() && FeatureFlag.VERSIONS_CATALOG.isEnabled) {
            val file = File(LIBS_VERSIONS_TOML)
            if (file.exists()) {
                VersionsCatalogUpdater(file, emptyList()).cleanupComments(file)
                OutputFile.GRADLE_VERSIONS_CATALOG.logFileWasModified()
            }
        }
    }

    @TaskAction
    fun cleanupKotlinScripts() {
        if (FeatureFlag.KOTLIN_SCRIPTS.isEnabled) {
            println("NOTE: refreshVersionsCleanUp doesn't clean up Kotlin Scripts yet, see https://github.com/jmfayard/refreshVersions/issues/582")
        }
    }
}
