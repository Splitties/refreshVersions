package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.*
import de.fayard.refreshVersions.core.extensions.gradle.hasDynamicVersion
import de.fayard.refreshVersions.core.extensions.gradle.isRootProject
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

internal suspend fun lookupVersionCandidates(
    httpClient: OkHttpClient,
    project: Project,
    versionProperties: Map<String, String>,
    versionKeyReader: ArtifactVersionKeyReader
): VersionCandidatesLookupResult {

    require(project.isRootProject)

    val projects = RefreshVersionsConfigHolder.allProjects(project)

    val dependenciesWithHardcodedVersions = mutableListOf<Dependency>()
    val dependenciesWithDynamicVersions = mutableListOf<Dependency>()
    val dependencyFilter: (Dependency) -> Boolean = { dependency ->
        dependency.isManageableVersion(versionProperties, versionKeyReader).also { manageable ->
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

    val dependenciesWithVersionCandidates = coroutineScope {
        val resultMode = RefreshVersionsConfigHolder.resultMode
        dependencyVersionsFetchers.groupBy {
            it.moduleId
        }.map { (moduleId: ModuleId, versionFetchers: List<DependencyVersionsFetcher>) ->
            val resolvedVersion = resolveVersion(
                properties = versionProperties,
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

        }.awaitAll()
    }
    return VersionCandidatesLookupResult(
        dependenciesWithVersionsCandidates = dependenciesWithVersionCandidates,
        dependenciesWithHardcodedVersions = dependenciesWithHardcodedVersions,
        dependenciesWithDynamicVersions = dependenciesWithDynamicVersions
    )
    TODO("Check version candidates for the same key are the same, or warn the user with actionable details")
}

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
