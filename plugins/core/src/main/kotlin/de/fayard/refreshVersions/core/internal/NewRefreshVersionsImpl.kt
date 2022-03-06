package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.DependencySelection
import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.FeatureFlag.GRADLE_UPDATES
import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version
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
import org.gradle.api.initialization.Settings
import org.gradle.util.GradleVersion

internal suspend fun lookupVersionCandidates(
    httpClient: OkHttpClient,
    project: Project,
    versionMap: Map<String, String>,
    versionKeyReader: ArtifactVersionKeyReader,
    versionsCatalogMapping: Set<ModuleId.Maven>
): VersionCandidatesLookupResult {

    require(project.isRootProject)

    val projects = RefreshVersionsConfigHolder.allProjects(project)
    val (kotlinScriptDependencies, kotlinScriptRepos) = kotlinScriptDependenciesAndRepo()
    println("kotlinScriptDependencies=$kotlinScriptDependencies kotlinScriptRepos=$kotlinScriptRepos")

    val dependenciesWithHardcodedVersions = mutableListOf<Dependency>()
    val dependenciesWithDynamicVersions = mutableListOf<Dependency>()
    val kotlinScriptUpdates = mutableListOf<DependencyWithVersionCandidates>()
    val dependencyFilter: (Dependency) -> Boolean = { dependency ->
        dependency.isManageableVersion(versionMap, versionKeyReader, versionsCatalogMapping).also { manageable ->
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
    ).plus(
        kotlinScriptDependencies.flatMap { moduleId ->
            kotlinScriptRepos.map { repoUrl ->
                MavenDependencyVersionsFetcherHttp(httpClient, moduleId, repoUrl, null)
            }
        }.also { println(it.joinToString("\n")) }
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
                ).also {
                    if (moduleId in kotlinScriptDependencies) {
                        kotlinScriptUpdates += it
                    }
                }
            }
        }

        val settingsPluginsUpdatesAsync = async {
            SettingsPluginsUpdatesFinder.getSettingsPluginUpdates(httpClient, resultMode)
        }

        val gradleUpdatesAsync = async {
            if (GRADLE_UPDATES.isEnabled) lookupAvailableGradleVersions(httpClient) else emptyList()
        }

        val dependenciesWithVersionCandidates = dependenciesWithVersionCandidatesAsync.awaitAll()

        val dependenciesFromVersionsCatalog = versionsCatalogMapping.map(ModuleId.Maven::toDependency)

        return@coroutineScope VersionCandidatesLookupResult(
            dependenciesUpdates = dependenciesWithVersionCandidates,
            dependenciesWithHardcodedVersions = dependenciesWithHardcodedVersions - dependenciesFromVersionsCatalog,
            dependenciesWithDynamicVersions = dependenciesWithDynamicVersions,
            gradleUpdates = gradleUpdatesAsync.await(),
            settingsPluginsUpdates = settingsPluginsUpdatesAsync.await().settings,
            buildSrcSettingsPluginsUpdates = settingsPluginsUpdatesAsync.await().buildSrcSettings,
            kotlinScriptUpdates = kotlinScriptUpdates,
        )
        TODO("Check version candidates for the same key are the same, or warn the user with actionable details")
    }
}

private fun ModuleId.toDependency() =
    UsedPluginsHolder.ConfigurationLessDependency("$group:$name:_")


fun kotlinScriptDependenciesAndRepo(): Pair<List<ModuleId.Maven>, List<String>> {
    val regex = """@file:DependsOn\("(.+):(.+):(.+)"\).*""".toRegex()
    val dependencies = """
    @file:DependsOn("org.freemarker:freemarker:2.3.30")
    @file:DependsOn("no.api.freemarker:freemarker-java8:2.0.0")
    """.trimIndent()
        .lines()
        .mapNotNull { line ->
            val (group, name) = regex.matchEntire(line)?.destructured ?: return@mapNotNull null
            ModuleId.Maven(group, name)
        }
    return Pair(dependencies, listOf("https://repo.maven.apache.org/maven2/"))
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
                httpClient = httpClient,
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
