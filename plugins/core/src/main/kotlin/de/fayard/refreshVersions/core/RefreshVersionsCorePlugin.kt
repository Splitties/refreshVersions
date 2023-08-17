package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.extensions.gradle.isRootProject
import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
import de.fayard.refreshVersions.core.internal.OutputFile
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.core.internal.VersionsCatalogs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register
import org.slf4j.Marker
import org.slf4j.helpers.BasicMarkerFactory

open class RefreshVersionsCorePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        check(project.isRootProject) { "ERROR: de.fayard.refreshVersions.core should not be applied manually" }
        if (project.isBuildSrc.not()) {
            OutputFile.init(project)
            val versionsFileName = RefreshVersionsConfigHolder.versionsPropertiesFile.name

            val shouldUpdateVersionCatalogs = VersionsCatalogs.isSupported() && FeatureFlag.VERSIONS_CATALOG.isEnabled
            project.tasks.register<RefreshVersionsTask>(name = "refreshVersions") {
                group = "refreshVersions"
                description = "Search for new dependencies versions and update $versionsFileName"
                if (shouldUpdateVersionCatalogs) {
                    this.defaultVersionCatalog = VersionsCatalogs.getDefault(project)
                    this.defaultVersionCatalogFile = project.file(VersionsCatalogs.LIBS_VERSIONS_TOML)
                }
                rootProjectSettingsFile = project.file("settings.gradle.kts").let { kotlinDslSettings ->
                    if (kotlinDslSettings.exists()) kotlinDslSettings else {
                        project.file("settings.gradle").also {
                            check(it.exists())
                        }
                    }
                }
                buildSrcSettingsFile = project.file("buildSrc/settings.gradle.kts").let { kotlinDslSettings ->
                    if (kotlinDslSettings.exists()) kotlinDslSettings else {
                        project.file("buildSrc/settings.gradle").takeIf {
                            it.exists()
                        }
                    }
                }
                RefreshVersionsConfigHolder.dependenciesTracker.recordBuildscriptAndRegularDependencies(project)
            }

            project.tasks.register<RefreshVersionsCleanupTask>(name = "refreshVersionsCleanup") {
                group = "refreshVersions"
                description = "Cleanup versions availability comments"
            }
        }
        cleanFilesFromPreviousVersions(project)
    }

    private fun cleanFilesFromPreviousVersions(project: Project) {
        if (project.isBuildSrc) {
            project.buildDir.resolve("refreshVersions_used_dependencies.txt").delete()
            project.buildDir.resolve("refreshVersions_used_repositories_maven.txt").delete()
        } else {
            project.buildDir.resolve("refreshVersions_used_dependencies_plugins.txt").delete()
            project.buildDir.resolve("refreshVersions_used_repositories_plugins_maven.txt").delete()
        }
    }

    @InternalRefreshVersionsApi
    object LogMarkers {
        @JvmField
        val default: Marker = BasicMarkerFactory().getMarker("refreshVersions")
    }

    @InternalRefreshVersionsApi
    companion object {
        val currentVersion by lazy {
            RefreshVersionsCorePlugin::class.java.getResourceAsStream("/version.txt")!!
                .bufferedReader()
                .useLines { it.first() }
        }
    }
}
