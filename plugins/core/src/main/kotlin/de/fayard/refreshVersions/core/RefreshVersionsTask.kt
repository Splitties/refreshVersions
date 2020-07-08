package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.extensions.gradle.hasDynamicVersion
import de.fayard.refreshVersions.core.extensions.gradle.moduleIdentifier
import de.fayard.refreshVersions.core.extensions.gradle.toModuleId
import de.fayard.refreshVersions.core.internal.*
import de.fayard.refreshVersions.core.internal.DependencyWithVersionCandidates
import de.fayard.refreshVersions.core.internal.MavenRepoUrl
import de.fayard.refreshVersions.core.internal.getDependencyVersionsCandidates
import de.fayard.refreshVersions.core.internal.resolveVersion
import de.fayard.refreshVersions.core.internal.updateVersionsProperties
import kotlinx.coroutines.*
import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.tasks.TaskAction

open class RefreshVersionsTask : DefaultTask() {

    @TaskAction
    fun taskActionRefreshVersions() {

        val projects = RefreshVersionsConfigHolder.allProjects(project)
        val allConfigurations: Set<Configuration> = projects.flatMap {
            it.buildscript.configurations.asSequence() + it.configurations
        }.toSet()

        val allDependencies = (UsedPluginsHolder.readDependencies().asSequence() +
                allConfigurations.asSequence().flatMap {
                    it.dependencies.asSequence().filterIsInstance<ExternalDependency>()
                })
            .distinctBy { it.group + ':' + it.name + ':' + it.version }

        //TODO: Filter using known grouping strategies to only use the main artifact to resolve latest version, this
        // will reduce the number of repositories lookups, improving performance a little more.

        val allRepositories = (
                UsedPluginsHolder.readRepositories().asSequence() +
                projects.asSequence()
                    .flatMap { it.buildscript.repositories.asSequence() + it.repositories }
                )
            .filterIsInstance<MavenArtifactRepository>()
            .map { MavenRepoUrl(it.url.toString()) }
            .distinct()
            .toList()

        val result: VersionCandidatesLookupResult = runBlocking {
            de.fayard.refreshVersions.core.internal.lookupVersionCandidates(
                httpClient = RefreshVersionsConfigHolder.httpClient,
                project = project,
                versionProperties = RefreshVersionsConfigHolder.readVersionProperties(),
                versionKeyReader = RefreshVersionsConfigHolder.versionKeyReader
            )
            /*lookupVersionCandidates(
                allDependencies = allDependencies,
                versionProperties = RefreshVersionsConfigHolder.readVersionProperties(),
                versionKeyReader = RefreshVersionsConfigHolder.versionKeyReader,
                allRepositories = allRepositories
            )*/
        }
        project.rootProject.updateVersionsProperties(result.dependenciesWithVersionsCandidates)

        warnAboutHarcodedVersionsIfAny(result.dependenciesWithHardcodedVersions)
        warnAboutDynamicVersionsIfAny(result.dependenciesWithDynamicVersions)
    }

    private suspend fun lookupVersionCandidates(
        allDependencies: Sequence<Dependency>,
        versionProperties: Map<String, String>,
        versionKeyReader: ArtifactVersionKeyReader,
        allRepositories: List<MavenRepoUrl>
    ): VersionCandidatesLookupResult = coroutineScope {
        val dependenciesWithHardcodedVersions = mutableListOf<Dependency>()
        val dependenciesWithDynamicVersions = mutableListOf<Dependency>()
        val dependencyWithVersionCandidates = allDependencies.mapNotNull { dependency ->

            //TODO: Show status and progress.

            if (dependency.isManageableVersion(versionProperties, versionKeyReader).not()) {
                if (dependency.version != null) {
                    // null version means it's expected to be added by a BoM or a plugin, so we ignore them.
                    dependenciesWithHardcodedVersions.add(dependency)
                }
                if (dependency is ExternalDependency &&
                    dependency.versionConstraint.hasDynamicVersion()
                ) {
                    dependenciesWithDynamicVersions.add(dependency)
                }
                return@mapNotNull null
            }
            val moduleIdentifier = dependency.moduleIdentifier ?: return@mapNotNull null
            val group = dependency.group ?: return@mapNotNull null
            val resolvedVersion = resolveVersion(
                properties = versionProperties,
                key = getVersionPropertyName(moduleIdentifier, versionKeyReader)
            )
            async {
                val versionCandidates = getDependencyVersionsCandidates(
                    repositories = allRepositories,
                    group = group,
                    name = dependency.name,
                    resolvedVersion = resolvedVersion
                )
                DependencyWithVersionCandidates(
                    moduleId = moduleIdentifier.toModuleId(),
                    currentVersion = resolvedVersion ?: versionCandidates.let {
                        if (it.isEmpty()) throw IllegalStateException(
                            "Unable to find a version candidate for the following artifact:\n" +
                                    "$group:$name\n" +
                                    "Please, check this artifact exists in the configured repositories."
                        )
                        it.first().value
                    },
                    versionsCandidates = if (resolvedVersion != null) versionCandidates else {
                        versionCandidates.drop(1)
                    }
                )
            }
        }.toList().awaitAll()
        VersionCandidatesLookupResult(
            dependenciesWithVersionsCandidates = dependencyWithVersionCandidates,
            dependenciesWithHardcodedVersions = dependenciesWithHardcodedVersions,
            dependenciesWithDynamicVersions = dependenciesWithDynamicVersions
        )
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

    private fun warnAboutHarcodedVersionsIfAny(dependenciesWithHardcodedVersions: List<Dependency>) {
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
                    |If you intentionally want to keep hardcoded versions so a module has a different version of a
                    |dependency than the rest of the project, you can safely ignore this warning for these artifacts,
                    |but keep in mind refreshVersions will not show available updates for these.
                    |
                    |Note that a migration task is planned in a future version of refreshVersions.
                    |
                    |See https://github.com/jmfayard/refreshVersions/issues/160""".trimMargin()
            )
            //TODO: Replace issue link above with stable link to explanation in documentation.
        }
    }
}
