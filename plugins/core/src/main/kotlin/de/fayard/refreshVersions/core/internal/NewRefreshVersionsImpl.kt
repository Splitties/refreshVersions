package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.*
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
        val dependenciesWithVersionCandidatesAsync = dependencyVersionsFetchers.groupBy {
            it.moduleId
        }.map { (moduleId: ModuleId, versionFetchers: List<DependencyVersionsFetcher>) ->
            val resolvedVersion = resolveVersion(
                properties = versionMap,
                key = getVersionPropertyName(moduleId, versionKeyReader)
            ) ?: error("Couldn't resolve version for $moduleId")
            async {
                DependencyWithVersionCandidates(
                    moduleId = moduleId,
                    currentVersion = resolvedVersion,
                    versionsCandidates = versionFetchers.getVersionCandidates(
                        currentVersion = Version(resolvedVersion),
                        resultMode = resultMode
                    )
                )
            }
        }

        val selfUpdateAsync: Deferred<DependencyWithVersionCandidates>? = when {
            RefreshVersionsConfigHolder.isSetupViaPlugin -> null
            else -> async { LegacyBoostrapUpdatesFinder.getSelfUpdates(httpClient, resultMode) }
        }

        val gradleUpdatesAsync = async {
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

        val dependenciesWithVersionCandidates = dependenciesWithVersionCandidatesAsync.awaitAll()

        return@coroutineScope VersionCandidatesLookupResult(
            dependenciesWithVersionsCandidates = dependenciesWithVersionCandidates,
            dependenciesWithHardcodedVersions = dependenciesWithHardcodedVersions,
            dependenciesWithDynamicVersions = dependenciesWithDynamicVersions,
            gradleUpdates = gradleUpdatesAsync.await(),
            selfUpdatesForLegacyBootstrap = selfUpdateAsync?.await()
        )
        TODO("Check version candidates for the same key are the same, or warn the user with actionable details")
    }
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
        repositories.filterIsInstance<MavenArtifactRepository>().mapNotNull { repo ->
            DependencyVersionsFetcher(
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
): Sequence<DependencyVersionsFetcher> = configurations.asSequence().flatMap {
    it.dependencies.asSequence().filter(dependencyFilter)
}.flatMap { dependency ->
    repositories.filterIsInstance<MavenArtifactRepository>().mapNotNull { repo ->
        DependencyVersionsFetcher(
            httpClient = httpClient,
            dependency = dependency,
            repository = repo
        )
    }.asSequence()
}
