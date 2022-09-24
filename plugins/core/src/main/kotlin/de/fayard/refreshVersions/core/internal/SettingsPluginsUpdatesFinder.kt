package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.core.extensions.gradle.mavenModuleId
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import okhttp3.OkHttpClient
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.initialization.Settings

internal object SettingsPluginsUpdatesFinder {

    class UpdatesLookupResult(
        val settings: List<PluginWithVersionCandidates>,
        val buildSrcSettings: List<PluginWithVersionCandidates>
    )

    suspend fun getSettingsPluginUpdates(
        httpClient: OkHttpClient,
        mode: VersionCandidatesResultMode
    ): UpdatesLookupResult {

        val rootProjectSettings = RefreshVersionsConfigHolder.settings
        val buildSrcSettings = RefreshVersionsConfigHolder.buildSrcSettings

        val rootProjectSettingsPlugins = rootProjectSettings.getPluginsList()
        val buildSrcSettingsPlugins = buildSrcSettings?.let { settings ->
            settings.getPluginsList() + rootProjectSettingsPlugins.filter {
                settings.pluginManager.hasPlugin(it.id)
            }
        }

        return coroutineScope {

            rootProjectSettingsPlugins.flatMap { dependency ->
                rootProjectSettings.pluginManagement.repositories.asSequence()
                    .filterIsInstance<MavenArtifactRepository>()
                    .mapNotNull { repo ->
                        val fetcher = DependencyVersionsFetcher.forMaven(
                            httpClient = httpClient,
                            moduleId = dependency.mavenModuleId(),
                            repository = repo
                        )
                            ?: return@mapNotNull null
                        dependency to fetcher
                    }
            }.plus(
                buildSrcSettingsPlugins?.flatMap { dependency ->
                    buildSrcSettings.pluginManagement.repositories.asSequence()
                        .filterIsInstance<MavenArtifactRepository>()
                        .mapNotNull { repo ->
                            val fetcher = DependencyVersionsFetcher.forMaven(
                                httpClient = httpClient,
                                moduleId = dependency.mavenModuleId(),
                                repository = repo
                            )
                                ?: return@mapNotNull null
                            dependency to fetcher
                        }
                } ?: emptySequence()
            ).distinctBy { (_, versionsFetcher) ->
                versionsFetcher
            }.groupBy { (_, versionsFetcher) ->
                versionsFetcher.moduleId
            }.mapNotNull { (moduleId: ModuleId, listOfDependencyToVersionsFetcher) ->
                Triple(
                    first = moduleId,
                    second = listOfDependencyToVersionsFetcher.firstOrNull()?.first?.version
                        ?: return@mapNotNull null,
                    third = listOfDependencyToVersionsFetcher.map { (_, versionsFetcher) -> versionsFetcher }
                )
            }.mapNotNull { (moduleId: ModuleId, currentVersion, versionsFetchers) ->
                val pluginId = moduleId.group ?: return@mapNotNull null
                async {
                    val (versions, failures) = versionsFetchers.getVersionCandidates(
                        currentVersion = Version(currentVersion),
                        resultMode = mode
                    )
                    PluginWithVersionCandidates(
                        pluginId = pluginId,
                        currentVersion = currentVersion,
                        versionsCandidates = versions,
                        failures = failures
                    )
                }
            }.awaitAll().let { pluginsWithVersionCandidates ->
                UpdatesLookupResult(
                    settings = pluginsWithVersionCandidates.filter {
                        rootProjectSettings.pluginManager.hasPlugin(it.pluginId)
                    },
                    buildSrcSettings = buildSrcSettings?.let { settings ->
                        pluginsWithVersionCandidates.filter {
                            settings.pluginManager.hasPlugin(it.pluginId)
                        }
                    } ?: emptyList()
                )
            }
        }
    }

    private const val pluginSuffix = ".gradle.plugin"

    private val Dependency.id get() = group!!

    private fun Settings.getPluginsList(): Sequence<Dependency> {
        return buildscript.configurations.getByName("classpath").dependencies.asSequence().filter {
            it.name.endsWith(pluginSuffix) &&
                    it.group == it.name.substringBefore(pluginSuffix) &&
                    pluginManager.hasPlugin(it.group!!)
        }
    }
}
