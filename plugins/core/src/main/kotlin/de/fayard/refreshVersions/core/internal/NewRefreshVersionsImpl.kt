package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.DependencySelection
import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.FeatureFlag.GRADLE_UPDATES
import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.core.extensions.gradle.hasDynamicVersion
import de.fayard.refreshVersions.core.extensions.gradle.isRootProject
import de.fayard.refreshVersions.core.internal.legacy.LegacyBoostrapUpdatesFinder
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import okhttp3.OkHttpClient
import org.gradle.api.Project
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.artifacts.dsl.RepositoryHandler
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.initialization.Settings
import org.gradle.util.GradleVersion

internal suspend fun lookupVersionCandidates(
    httpClient: OkHttpClient,
    project: Project,
    versionMap: Map<String, String>,
    versionKeyReader: ArtifactVersionKeyReader
): VersionCandidatesLookupResult {

    require(project.isRootProject)

    val projects = RefreshVersionsConfigHolder.allProjects(project)

    val dependenciesWithHardcodedVersions = mutableListOf<Dependency>()
    val dependenciesWithDynamicVersions = mutableListOf<Dependency>()
    val dependencyFilter: (Dependency) -> Boolean = { dependency ->
        dependency.isManageableVersion(versionMap, versionKeyReader).also { manageable ->
            if (manageable) return@also
            if (dependency.version != null) {
                // null version means it's expected to be added by a BoM or a plugin, so we ignore them.
                dependenciesWithHardcodedVersions.add(dependency)
            }
            if (dependency is ExternalDependency &&
                dependency.versionConstraint.hasDynamicVersion()
            ) {
                dependenciesWithDynamicVersions.add(dependency)
            }
        }
    }
    val dependencyVersionsFetchers: Set<DependencyVersionsFetcher> = projects.flatMap {
        it.getDependencyVersionFetchers(httpClient = httpClient, dependencyFilter = dependencyFilter)
    }.plus(
        getUsedPluginsDependencyVersionFetchers(httpClient = httpClient)
    ).toSet()

    return coroutineScope {

        val resultMode = RefreshVersionsConfigHolder.resultMode
        val versionRejectionFilter = RefreshVersionsConfigHolder.versionRejectionFilter ?: { false }
        val dependenciesWithVersionCandidatesAsync = dependencyVersionsFetchers.groupBy {
            it.moduleId
        }.map { (moduleId: ModuleId, versionFetchers: List<DependencyVersionsFetcher>) ->
            val propertyName = getVersionPropertyName(moduleId, versionKeyReader)
            val resolvedVersion = resolveVersion(
                properties = versionMap,
                key = propertyName
            )?.let { Version(it) }
            async {
                val (versions, failures) = versionFetchers.getVersionCandidates(
                    currentVersion = resolvedVersion ?: Version(""),
                    resultMode = resultMode
                )
                val currentVersion = resolvedVersion ?: versions.latestMostStable()
                val selection = DependencySelection(moduleId, currentVersion, propertyName)
                DependencyWithVersionCandidates(
                    moduleId = moduleId,
                    currentVersion = currentVersion.value,
                    versionsCandidates = versions.filterNot { version ->
                        selection.candidate = version
                        versionRejectionFilter(selection)
                    },
                    failures = failures
                )
            }
        }

        val settingsPluginsUpdatesAsync = async {
            SettingsPluginsUpdatesFinder.getSettingsPluginUpdates(httpClient, resultMode)
        }

        val selfUpdateAsync: Deferred<DependencyWithVersionCandidates>? = when {
            RefreshVersionsConfigHolder.isSetupViaPlugin -> null
            else -> async { LegacyBoostrapUpdatesFinder.getSelfUpdates(httpClient, resultMode) }
        }

        val gradleUpdatesAsync = async {
            if (GRADLE_UPDATES.isEnabled) lookupAvailableGradleVersions() else emptyList()
        }

        val dependenciesWithVersionCandidates = dependenciesWithVersionCandidatesAsync.awaitAll()

        return@coroutineScope VersionCandidatesLookupResult(
            dependenciesUpdates = dependenciesWithVersionCandidates,
            dependenciesWithHardcodedVersions = dependenciesWithHardcodedVersions,
            dependenciesWithDynamicVersions = dependenciesWithDynamicVersions,
            settingsPluginsUpdates = settingsPluginsUpdatesAsync.await().settings,
            buildSrcSettingsPluginsUpdates = settingsPluginsUpdatesAsync.await().buildSrcSettings,
            gradleUpdates = gradleUpdatesAsync.await(),
            selfUpdatesForLegacyBootstrap = selfUpdateAsync?.await()
        )
        TODO("Check version candidates for the same key are the same, or warn the user with actionable details")
    }
}

private suspend fun lookupAvailableGradleVersions(): List<Version> = coroutineScope {
    val checker = GradleUpdateChecker(RefreshVersionsConfigHolder.httpClient)
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
    repositories = buildscript.repositories,
    dependencyFilter = dependencyFilter
)

private fun Project.getDependencyVersionFetchers(
    httpClient: OkHttpClient,
    dependencyFilter: (Dependency) -> Boolean
): Sequence<DependencyVersionsFetcher> = getDependencyVersionFetchers(
    httpClient = httpClient,
    configurations = buildscript.configurations,
    repositories = buildscript.repositories,
    dependencyFilter = dependencyFilter
).plus(
    getDependencyVersionFetchers(
        httpClient = httpClient,
        configurations = configurations,
        repositories = repositories,
        dependencyFilter = dependencyFilter
    )
)

private fun getUsedPluginsDependencyVersionFetchers(
    httpClient: OkHttpClient
): Sequence<DependencyVersionsFetcher> {
    return UsedPluginsHolder.read().flatMap { (dependency, repositories) ->
        repositories.withGlobalRepos().filterIsInstance<MavenArtifactRepository>().mapNotNull { repo ->
            DependencyVersionsFetcher.forMaven(
                httpClient = httpClient,
                dependency = dependency,
                repository = repo
            )
        }.asSequence()
    }
}

private fun getDependencyVersionFetchers(
    httpClient: OkHttpClient,
    configurations: ConfigurationContainer,
    repositories: RepositoryHandler,
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
                httpClient = RefreshVersionsConfigHolder.httpClient,
                npmDependency = dependency,
                npmRegistry = registryUrl
            )
        }.asSequence()
    } else {
        mavenRepositories.mapNotNull { repo ->
            DependencyVersionsFetcher.forMaven(
                httpClient = httpClient,
                dependency = dependency,
                repository = repo
            )
        }.asSequence()
    }
}
