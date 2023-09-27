package de.fayard.refreshVersions.core.internal.dependencies

import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.extensions.gradle.mavenModuleId
import de.fayard.refreshVersions.core.extensions.gradle.npmModuleId
import de.fayard.refreshVersions.core.internal.ConfigurationLessDependency
import de.fayard.refreshVersions.core.internal.forMaven
import de.fayard.refreshVersions.core.internal.forNpm
import de.fayard.refreshVersions.core.internal.withGlobalRepos
import de.fayard.refreshVersions.core.internal.withPluginsRepos
import okhttp3.OkHttpClient
import org.gradle.api.Project
import org.gradle.api.artifacts.ConfigurationContainer
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ProjectDependency
import org.gradle.api.artifacts.repositories.ArtifactRepository
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.initialization.Settings

internal class DependenciesTracker {

    fun recordBuildscriptAndRegularDependencies(buildSrc: Settings) {
        val npmRegistries = listOf("https://registry.npmjs.org/")
        buildSrc.gradle.afterProject {
            recordBuildscriptAndRegularDependencies(npmRegistries)
        }
    }

    fun recordBuildscriptAndRegularDependencies(rootProject: Project) {
        require(rootProject.isBuildSrc.not())
        val npmRegistries = listOf("https://registry.npmjs.org/")
        //TODO: Support custom npm registries.
        // See https://kotlinlang.org/docs/js-project-setup.html#additional-yarn-features-yarnrc
        // and https://yarnpkg.com/getting-started/migration#update-your-configuration-to-the-new-settings
        // and also https://yarnpkg.com/configuration/yarnrc#npmRegistryServer
        rootProject.allprojects {
            afterEvaluate {
                recordBuildscriptAndRegularDependencies(npmRegistries)
            }
        }
    }

    private fun Project.recordBuildscriptAndRegularDependencies(npmRegistries: List<String>) {
        //TODO: For buildSrc, filter out:
        // - org.gradle.kotlin.kotlin-dsl:org.gradle.kotlin.kotlin-dsl.gradle.plugin
        // - org.jetbrains.kotlin:kotlin-reflect
        // - org.jetbrains.kotlin:kotlin-stdlib-jdk8
        //TODO: Filter out some configurations like:
        // - migration-env (SqlDelight)
        // - compiler-env (SqlDelight)
        // - sqlite-3-18-dialect and alike (SqlDelight)
        val extracted = extractDependencies(
            configurations = buildscript.configurations,
            repositories = buildscript.repositories.withPluginsRepos(),
            npmRegistries = npmRegistries
        ) + extractDependencies(
            configurations = configurations,
            repositories = repositories,
            npmRegistries = npmRegistries
        )
        extracted.forEach { (dependency, repo) ->
            dependenciesWithRepos.getOrPut(dependency) { mutableSetOf() }.addAll(repo)
        }
    }

    fun dependencyVersionsFetchers(
        httpClient: OkHttpClient,
        dependencyFilter: (Dependency) -> Boolean
    ): Set<DependencyVersionsFetcher> = dependenciesWithRepos.flatMapTo(mutableSetOf()) { (dependency, repos) ->
        if (dependencyFilter(dependency).not()) return@flatMapTo emptySet()
        repos.mapNotNullTo(mutableSetOf()) { repo ->
            when (repo) {
                is Repo.Maven -> DependencyVersionsFetcher.forMaven(
                    httpClient = httpClient,
                    moduleId = dependency.mavenModuleId(),
                    repository = repo
                )

                is Repo.Npm -> DependencyVersionsFetcher.forNpm(
                    httpClient = httpClient,
                    moduleId = dependency.npmModuleId(),
                    npmRegistry = repo.npmRegistry
                )
            }
        }
    }

    private val dependenciesWithRepos = mutableMapOf<ConfigurationLessDependency, MutableSet<Repo>>()

    private fun extractDependencies(
        configurations: ConfigurationContainer,
        repositories: List<ArtifactRepository>,
        npmRegistries: List<String>,
    ): Sequence<Pair<ConfigurationLessDependency, List<Repo>>> {
        val mavenRepositories = repositories.withGlobalRepos().filterIsInstance<MavenArtifactRepository>()
        return configurations.asSequence().flatMap {
            it.dependencies.asSequence()
        }.mapNotNull { rawDependency ->
            if (rawDependency is ProjectDependency) return@mapNotNull null
            val dependency = ConfigurationLessDependency(rawDependency)
            dependency to when {
                dependency.isNpm -> npmRegistries.map { Repo.Npm(it) }
                else -> mavenRepositories.map { Repo.Maven(it) }
            }
        }
    }
}
