package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.extensions.gradle.isRootProject
import org.gradle.api.Project
import java.io.File

internal object SettingsPluginsUpdater {

    fun updateGradleSettingsWithAvailablePluginsUpdates(
        rootProject: Project,
        settingsPluginsUpdates: List<PluginWithVersionCandidates>,
        buildSrcSettingsPluginsUpdates: List<PluginWithVersionCandidates>
    ) {
        require(rootProject.isRootProject)
        require(rootProject.isBuildSrc.not())

        val rootProjectSettingsFile = rootProject.file("settings.gradle.kts").let { kotlinDslSettings ->
            if (kotlinDslSettings.exists()) kotlinDslSettings else {
                rootProject.file("settings.gradle").also {
                    check(it.exists())
                }
            }
        }
        val buildSrcSettingsFile = rootProject.file("buildSrc/settings.gradle.kts").let { kotlinDslSettings ->
            if (kotlinDslSettings.exists()) kotlinDslSettings else {
                rootProject.file("buildSrc/settings.gradle").takeIf {
                    it.exists()
                }
            }
        }
        updateGradleSettingsWithAvailablePluginsUpdates(
            settingsFile = rootProjectSettingsFile,
            settingsPluginsUpdates = settingsPluginsUpdates
        )
        buildSrcSettingsFile?.let {
            updateGradleSettingsWithAvailablePluginsUpdates(
                settingsFile = it,
                settingsPluginsUpdates = buildSrcSettingsPluginsUpdates
            )
        }
    }

    private fun updateGradleSettingsWithAvailablePluginsUpdates(
        settingsFile: File,
        settingsPluginsUpdates: List<PluginWithVersionCandidates>
    ) {
        val newContent = updatedGradleSettingsFileContentWithAvailablePluginsUpdates(
            fileContent = settingsFile.readText(),
            isKotlinDsl = settingsFile.name.endsWith(".kts"),
            settingsPluginsUpdates = settingsPluginsUpdates
        )
        settingsFile.writeText(newContent)
    }

    internal fun updatedGradleSettingsFileContentWithAvailablePluginsUpdates(
        fileContent: String,
        isKotlinDsl: Boolean,
        settingsPluginsUpdates: List<PluginWithVersionCandidates>
    ): String {
        TODO("Implement, using code in LegacyBootstrapUpdater as an inspiration source.")
    }
}
