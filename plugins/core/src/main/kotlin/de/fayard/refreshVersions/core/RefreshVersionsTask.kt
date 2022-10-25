package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.*
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder.settings
import de.fayard.refreshVersions.core.internal.VersionsCatalogs.LIBS_VERSIONS_TOML
import de.fayard.refreshVersions.core.internal.problems.log
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel
import de.fayard.refreshVersions.core.internal.versions.writeWithNewVersions
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.gradle.util.GradleVersion

/**
 *
 * $ ./gradlew help --task refreshVersions
 * Description
 * Search for new dependencies versions and update versions.properties
 *
 * OPTIONS
 *     --enable <FEATURE_FLAG>
 *     --disable <FEATURE_FLAG>
 */
open class RefreshVersionsTask : DefaultTask() {


    @Input @Optional
    @Option(option = "enable", description = "Enable a feature flag")
    var enableFlag: FeatureFlag? = null
        set(value) {
            field = value
            if (value != null) FeatureFlag.userSettings.put(value, true)
        }

    @Input @Optional
    @Option(option = "disable", description = "Disable a feature flag")
    var disableFlag: FeatureFlag? = null
        set(value) {
            field = value
            if (value != null) FeatureFlag.userSettings.put(value, false)
        }

    @TaskAction
    fun taskActionRefreshVersions() {
        OutputFile.checkWhichFilesExist()

        if (FeatureFlag.userSettings.isNotEmpty()) {
            logger.lifecycle("Feature flags: " + FeatureFlag.userSettings)
        }
        //TODO: Filter using known grouping strategies to only use the main artifact to resolve latest version, this
        // will reduce the number of repositories lookups, improving performance a little more.

        val shouldUpdateVersionCatalogs = VersionsCatalogs.isSupported() && FeatureFlag.VERSIONS_CATALOG.isEnabled


        val versionsCatalogLibraries: Set<MinimalExternalModuleDependency>
        val versionsCatalogPlugins: Set<PluginDependencyCompat>
        if (shouldUpdateVersionCatalogs) {
            val versionCatalog = VersionsCatalogs.getDefault(project)
            versionsCatalogLibraries = VersionsCatalogs.libraries(versionCatalog)
            versionsCatalogPlugins = VersionsCatalogs.plugins(versionCatalog)
        } else {
            versionsCatalogLibraries = emptySet()
            versionsCatalogPlugins = emptySet()
        }


        runBlocking {
            val lintUpdatingProblemsAsync = async {
                configureLintIfRunningOnAnAndroidProject(settings, RefreshVersionsConfigHolder.readVersionsMap())
            }
            val result = RefreshVersionsConfigHolder.withHttpClient({ message ->
                logger.info(message)
            }) { httpClient ->
                lookupVersionCandidates(
                    httpClient = httpClient,
                    project = project,
                    versionMap = RefreshVersionsConfigHolder.readVersionsMap(),
                    versionKeyReader = RefreshVersionsConfigHolder.versionKeyReader,
                    versionsCatalogLibraries = versionsCatalogLibraries,
                    versionsCatalogPlugins = versionsCatalogPlugins
                )
            }
            VersionsPropertiesModel.writeWithNewVersions(result.dependenciesUpdatesForVersionsProperties)
            SettingsPluginsUpdater.updateGradleSettingsWithAvailablePluginsUpdates(
                rootProject = project,
                settingsPluginsUpdates = result.settingsPluginsUpdates,
                buildSrcSettingsPluginsUpdates = result.buildSrcSettingsPluginsUpdates
            )

            warnAboutRefreshVersionsIfSettingIfAny()
            warnAboutHardcodedVersionsIfAny(result.dependenciesWithHardcodedVersions)
            warnAboutDynamicVersionsIfAny(result.dependenciesWithDynamicVersions)
            warnAboutGradleUpdateAvailableIfAny(result.gradleUpdates)
            lintUpdatingProblemsAsync.await().forEach { problem ->
                logger.log(problem)
            }
            OutputFile.VERSIONS_PROPERTIES.logFileWasModified()

            if (shouldUpdateVersionCatalogs) {
                val libsToml = project.file(LIBS_VERSIONS_TOML)
                if (libsToml.canRead()) {
                    VersionsCatalogUpdater(
                        file = libsToml,
                        dependenciesUpdates = result.dependenciesUpdatesForVersionCatalog
                    ).updateNewVersions(libsToml)
                    OutputFile.GRADLE_VERSIONS_CATALOG.logFileWasModified()
                }
            }
        }
        if (FeatureFlag.KOTLIN_SCRIPTS.isEnabled) {
            println("NOTE: refreshVersions support for Kotlin Scripts isn't implemented yet, see https://github.com/jmfayard/refreshVersions/issues/582")
        }
    }

    private fun warnAboutRefreshVersionsIfSettingIfAny() {
        if (RefreshVersionsConfigHolder.isUsingVersionRejection) {
            logger.warn("NOTE: Some versions are filtered by the rejectVersionsIf predicate. See the settings.gradle.kts file.")
        }
    }

    private fun warnAboutGradleUpdateAvailableIfAny(gradleUpdates: List<Version>) {
        if (gradleUpdates.isEmpty()) return
        val currentGradleVersion = GradleVersion.current()
        val message = buildString {
            appendLine("The Gradle version used in this project is not up to date.")
            append("To update from version ${currentGradleVersion.version}, run ")
            if (gradleUpdates.size == 1) {
                appendLine("this command:")
            } else {
                appendLine("one of these commands:")
            }
            gradleUpdates.forEach { version ->
                appendLine("./gradlew wrapper --gradle-version ${version.value}")
            }
            appendLine()
            appendLine("Be sure to read the migration guides to have a smooth upgrade process.")
            appendLine("Note that you can replace with a specific intermediate version if needed.")
        }
        logger.warn(message)
    }

    private fun warnAboutDynamicVersionsIfAny(dependenciesWithDynamicVersions: List<Dependency>) {
        if (dependenciesWithDynamicVersions.isNotEmpty()) {
            //TODO: Suggest running a diagnosis task to list the dynamic versions.
            logger.error(
                """Found ${dependenciesWithDynamicVersions.count()} dynamic dependencies versions!
                    |This makes builds unreproducible, and can make you use unstable versions unknowingly.
                    |
                    |The simple fix is to replace the dynamic version by the version placeholder, i.e. the underscore (_)
                    |
                    |Another solution if that fits your needs is to specify a non dynamic strict or preferred version constraint.
                    |
                    |If you're not convinced to stop using dynamic versions, here's an article for you:
                    |https://blog.danlew.net/2015/09/09/dont-use-dynamic-versions-for-your-dependencies/
                    """.trimMargin()
            )
        }
    }

    private fun warnAboutHardcodedVersionsIfAny(dependenciesWithHardcodedVersions: List<Dependency>) {
        if (dependenciesWithHardcodedVersions.isNotEmpty()) {
            //TODO: Suggest running a diagnosis task to list the hardcoded versions.
            val warnFor = (dependenciesWithHardcodedVersions).take(3).map {
                "${it.group}:${it.name}:${it.version}"
            }
            val versionsFileName = RefreshVersionsConfigHolder.versionsPropertiesFile.name
            logger.warn(
                """Found ${dependenciesWithHardcodedVersions.count()} hardcoded dependencies versions.
                    |
                    |$warnFor...
                    |
                    |To ensure single source of truth, refreshVersions only works with version placeholders,
                    |that is the explicit way of marking the version is not there (but in the $versionsFileName file).
                    |
                    |To migrate your project, run
                    |   ./gradlew refreshVersionsMigrate
                    |
                    |See https://jmfayard.github.io/refreshVersions/migrate/""".trimMargin()
            )
            //TODO: Replace issue link above with stable link to explanation in documentation.
        }
    }
}
