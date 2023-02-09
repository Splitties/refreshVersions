package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.DependencySelection
import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.FeatureFlag.GRADLE_UPDATES
import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.core.extensions.gradle.*
import de.fayard.refreshVersions.core.extensions.gradle.hasDynamicVersion
import de.fayard.refreshVersions.core.extensions.gradle.isRootProject
import de.fayard.refreshVersions.core.extensions.gradle.npmModuleId
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import okhttp3.OkHttpClient
import org.gradle.api.Project
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.repositories.ArtifactRepository
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.initialization.Settings
import org.gradle.util.GradleVersion

internal suspend fun lookupVersionCandidates(
    httpClient: OkHttpClient,
    project: Project,
    versionMap: Map<String, String>,
    versionKeyReader: ArtifactVersionKeyReader,
    versionsCatalogLibraries: Set<MinimalExternalModuleDependency>,
    versionsCatalogPlugins: Set<PluginDependencyCompat>
): VersionCandidatesLookupResult {

    require(project.isRootProject)

    val projects = RefreshVersionsConfigHolder.allProjects(project)

    val dependenciesFromVersionFor = UsedVersionForTracker.read()

    return lookupVersionCandidates(
        dependencyVersionsFetchers = { dependencyFilter ->
            projects.flatMap {
                it.getDependencyVersionFetchers(httpClient = httpClient, dependencyFilter = dependencyFilter).toList()
            }.plus(
                UsedPluginsTracker.read().getDependencyVersionsFetchers(httpClient) //TODO: Is this needed?
                //TODO: If so, don't we miss passing dependencies through the dependencyFilter?
            ).plus(
                dependenciesFromVersionFor.asSequence().onEach { (dependency, _) ->
                    check(dependencyFilter(dependency)) // Needed because dependencyFilter also tracks dependencies usage.
                }.getDependencyVersionsFetchers(httpClient)
            ).toSet()
        },
        lookupAvailableGradleVersions = {
            if (GRADLE_UPDATES.isEnabled) lookupAvailableGradleVersions(httpClient) else emptyList()
        },
        lookupSettingsPluginUpdates = { resultMode ->
            SettingsPluginsUpdatesFinder.getSettingsPluginUpdates(httpClient, resultMode)
        },
        versionMap = versionMap,
        versionKeyReader = versionKeyReader,
        versionsCatalogLibraries = versionsCatalogLibraries,
        versionsCatalogPlugins = versionsCatalogPlugins,
        dependenciesFromVersionFor = dependenciesFromVersionFor.map { (dependency, _) -> dependency }
    )
}

internal suspend fun lookupVersionCandidates(
    dependencyVersionsFetchers: (dependencyFilter: (Dependency) -> Boolean) -> Set<DependencyVersionsFetcher>,
    lookupAvailableGradleVersions: suspend () -> List<Version>,
    lookupSettingsPluginUpdates: suspend (VersionCandidatesResultMode) -> SettingsPluginsUpdatesFinder.UpdatesLookupResult,
    versionMap: Map<String, String>,
    versionKeyReader: ArtifactVersionKeyReader,
    versionsCatalogLibraries: Set<MinimalExternalModuleDependency>,
    versionsCatalogPlugins: Set<PluginDependencyCompat>,
    dependenciesFromVersionFor: List<Dependency>
): VersionCandidatesLookupResult {

    val dependenciesWithHardcodedVersions = mutableListOf<Dependency>()
    val dependenciesWithDynamicVersions = mutableListOf<Dependency>()
    val managedDependencies = mutableListOf<Pair<Dependency, VersionManagementKind.Match>>()

    val dependencyFilter: (Dependency) -> Boolean = { dependency ->
        val versionManagementKind = dependency.versionManagementKind(
            versionMap = versionMap,
            versionKeyReader = versionKeyReader,
            versionsCatalogLibraries = versionsCatalogLibraries,
            versionsCatalogPlugins = versionsCatalogPlugins,
            dependenciesFromVersionFor = dependenciesFromVersionFor
        )
        when (versionManagementKind) {
            is VersionManagementKind.Match -> {
                managedDependencies.add(dependency to versionManagementKind)
                true
            }
            VersionManagementKind.NoMatch -> {
                if (dependency.version != null) {
                    // null version means it's expected to be added by a BoM or a plugin, so we ignore them.
                    dependenciesWithHardcodedVersions.add(dependency)
                }
                if (dependency is ExternalDependency &&
                    dependency.versionConstraint.hasDynamicVersion()
                ) {
                    dependenciesWithDynamicVersions.add(dependency)
                }
                false
            }
        }
    }

    return coroutineScope {

        val resultMode = RefreshVersionsConfigHolder.resultMode
        val versionRejectionFilter = RefreshVersionsConfigHolder.versionRejectionFilter ?: { false }
        val fetchers = dependencyVersionsFetchers(dependencyFilter)
        val dependenciesWithVersionCandidatesAsync = fetchers.groupBy {
            it.moduleId
        }.map { (moduleId: ModuleId, versionFetchers: List<DependencyVersionsFetcher>) ->
            async {
                val propertyName = getVersionPropertyName(moduleId, versionKeyReader)
                val emptyVersion = Version("")
                val resolvedVersion = resolveVersion(
                    properties = versionMap,
                    key = propertyName
                )?.let { Version(it) } ?: emptyVersion
                val lowestVersionInCatalog = versionsCatalogLibraries.mapNotNull {
                    val matches = it.module.group == moduleId.group && it.module.name == moduleId.name
                    when {
                        matches -> it.versionConstraint.tryExtractingSimpleVersion()?.let { rawVersion ->
                            Version(rawVersion)
                        }
                        else -> null
                    }
                }.minOrNull()
                val lowestUsedVersion = minOf(resolvedVersion, lowestVersionInCatalog ?: resolvedVersion)
                val (versions, failures) = versionFetchers.getVersionCandidates(
                    currentVersion = lowestUsedVersion,
                    resultMode = resultMode
                )
                DependencyWithVersionCandidates(
                    moduleId = moduleId,
                    currentVersion = lowestUsedVersion.value,
                    versionsCandidates = { currentVersion ->
                        val selection = DependencySelection(moduleId, currentVersion, propertyName)
                        versions.filter { version ->
                            selection.candidate = version
                            version > currentVersion && versionRejectionFilter(selection).not()
                        }
                    },
                    failures = failures
                )
            }
        }

        val settingsPluginsUpdatesAsync = async { lookupSettingsPluginUpdates(resultMode) }
        val gradleUpdatesAsync = async { lookupAvailableGradleVersions() }

        val versionsCandidatesResult = dependenciesWithVersionCandidatesAsync.awaitAll().splitForTargets(
            managedDependencies = managedDependencies
        )

        return@coroutineScope VersionCandidatesLookupResult(
            dependenciesUpdatesForVersionsProperties = versionsCandidatesResult.forVersionsProperties,
            dependenciesUpdatesForVersionCatalog = versionsCandidatesResult.forVersionsCatalog,
            dependenciesWithHardcodedVersions = dependenciesWithHardcodedVersions,
            dependenciesWithDynamicVersions = dependenciesWithDynamicVersions,
            gradleUpdates = gradleUpdatesAsync.await(),
            settingsPluginsUpdates = settingsPluginsUpdatesAsync.await().settings,
            buildSrcSettingsPluginsUpdates = settingsPluginsUpdatesAsync.await().buildSrcSettings
        )
        //TODO: Check version candidates for the same key are the same, or warn the user with actionable details.
    }
}

private class VersionCandidatesResult(
    val forVersionsProperties: List<DependencyWithVersionCandidates>,
    val forVersionsCatalog: List<DependencyWithVersionCandidates>,
)

private fun List<DependencyWithVersionCandidates>.splitForTargets(
    managedDependencies: MutableList<Pair<Dependency, VersionManagementKind.Match>>
): VersionCandidatesResult {
    val forVersionsProperties = mutableListOf<DependencyWithVersionCandidates>()
    val forVersionCatalog = mutableListOf<DependencyWithVersionCandidates>()

    forEach { dependencyWithVersionCandidates ->
        managedDependencies.forEach { (dependency, versionManagementKind) ->
            if (dependency.matches(dependencyWithVersionCandidates.moduleId)) {
                val targetList = when (versionManagementKind) {
                    is VersionManagementKind.Match.VersionsCatalog -> forVersionCatalog
                    is VersionManagementKind.Match.VersionsFile -> forVersionsProperties
                }
                targetList.add(dependencyWithVersionCandidates)
            }
        }
    }
    return VersionCandidatesResult(
        forVersionsProperties = forVersionsProperties,
        forVersionsCatalog = forVersionCatalog
    )
}

private suspend fun lookupAvailableGradleVersions(httpClient: OkHttpClient): List<Version> = coroutineScope {
    val checker = GradleUpdateChecker(httpClient)
    val currentGradleVersion = GradleVersion.current()
    GradleUpdateChecker.VersionType.values().filterNot {
        it == GradleUpdateChecker.VersionType.All
    }.let { types ->
        when {
            currentGradleVersion.isSnapshot -> types
            else -> types.filterNot {
                it == GradleUpdateChecker.VersionType.ReleaseNightly ||
                    it == GradleUpdateChecker.VersionType.Nightly
            }
        }
    }.map { type ->
        async {
            checker.fetchGradleVersion(type).map { GradleVersion.version(it.version) }
        }
    }.awaitAll().flatten().filter {
        it > currentGradleVersion
    }.sorted().map { Version(it.version) }
}

internal fun Settings.getDependencyVersionFetchers(
    httpClient: OkHttpClient,
    dependencyFilter: (Dependency) -> Boolean
): Sequence<DependencyVersionsFetcher> = getDependencyVersionFetchers(
    httpClient = httpClient,
    configurations = buildscript.configurations,
    repositories = buildscript.repositories.withPluginsRepos(),
    dependencyFilter = dependencyFilter
)

private fun Project.getDependencyVersionFetchers(
    httpClient: OkHttpClient,
    dependencyFilter: (Dependency) -> Boolean
): Sequence<DependencyVersionsFetcher> = getDependencyVersionFetchers(
    httpClient = httpClient,
    configurations = buildscript.configurations,
    repositories = buildscript.repositories.withPluginsRepos(),
    dependencyFilter = dependencyFilter
).plus(
    getDependencyVersionFetchers(
        httpClient = httpClient,
        configurations = configurations,
        repositories = repositories,
        dependencyFilter = dependencyFilter
    )
)

private fun Sequence<Pair<Dependency, List<ArtifactRepository>>>.getDependencyVersionsFetchers(
    httpClient: OkHttpClient
): Sequence<DependencyVersionsFetcher> {
    return flatMap { (dependency, repositories) ->
        repositories.withGlobalRepos().filterIsInstance<MavenArtifactRepository>().mapNotNull { repo ->
            DependencyVersionsFetcher.forMaven(
                httpClient = httpClient,
                moduleId = dependency.mavenModuleId(),
                repository = repo
            )
        }.asSequence()
    }
}

private fun getDependencyVersionFetchers(
    httpClient: OkHttpClient,
    configurations: ConfigurationContainer,
    repositories: List<ArtifactRepository>,
    dependencyFilter: (Dependency) -> Boolean
): Sequence<DependencyVersionsFetcher> = getDependencyVersionFetchers(
    httpClient = httpClient,
    configurations = configurations,
    mavenRepositories = repositories.withGlobalRepos().filterIsInstance<MavenArtifactRepository>(),
    npmRegistries = run { null /*Workaround useless warning*/ }, //TODO: Support custom npm registries.
    dependencyFilter = dependencyFilter
)

private fun getDependencyVersionFetchers(
    httpClient: OkHttpClient,
    configurations: ConfigurationContainer,
    mavenRepositories: List<MavenArtifactRepository>,
    npmRegistries: List<String>?,
    dependencyFilter: (Dependency) -> Boolean
): Sequence<DependencyVersionsFetcher> = configurations.asSequence().flatMap {
    it.dependencies.asSequence().filter(dependencyFilter)
}.flatMap { dependency ->
    if (dependency::class.simpleName == "NpmDependency") {
        (npmRegistries ?: listOf("https://registry.npmjs.org/")).map { registryUrl ->
            DependencyVersionsFetcher.forNpm(
                httpClient = httpClient,
                moduleId = dependency.npmModuleId(),
                npmRegistry = registryUrl
            )
        }.asSequence()
    } else {
        mavenRepositories.mapNotNull { repo ->
            DependencyVersionsFetcher.forMaven(
                httpClient = httpClient,
                moduleId = dependency.mavenModuleId(),
                repository = repo
            )
        }.asSequence()
    }
}
