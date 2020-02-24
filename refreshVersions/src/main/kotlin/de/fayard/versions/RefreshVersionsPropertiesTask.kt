package de.fayard.versions

import de.fayard.versions.extensions.hasDynamicVersion
import de.fayard.versions.extensions.isGradlePlugin
import de.fayard.versions.extensions.moduleIdentifier
import de.fayard.versions.internal.*
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.runBlocking
import org.gradle.api.DefaultTask
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

open class RefreshVersionsPropertiesTask : DefaultTask() {

    /*
    @Suppress("UnstableApiUsage")
    @Option(description = "Update all versions, I will check git diff afterwards")
    @Optional
    var update: Boolean = false
    */

    @TaskAction
    fun taskActionRefreshVersions() {

        val allConfigurations: Set<Configuration> =
            project.allprojects.flatMap { it.configurations }.toSet()

        val allDependencies = (
            project.readPluginsAndBuildSrcDependencies() +
                allConfigurations.asSequence().flatMap {
                    it.dependencies.asSequence().filterIsInstance<ExternalDependency>()
                }
            )
            .distinctBy { it.group + ':' + it.name + ':' + it.version }

        //TODO: Filter using known grouping strategies to only use the main artifact to resolve latest version, this
        // will reduce the number of repositories lookups, improving performance a little more.

        val allRepositories = project.allprojects.asSequence()
            .flatMap { it.buildscript.repositories.asSequence() + it.repositories }
            .filterIsInstance<MavenArtifactRepository>()
            .map { MavenRepoUrl(it.url.toString()) }
            .plus(project.readPluginsAndBuildSrcRepositories())
            .distinct()
            .toList()

        val versionProperties: Map<String, String> = project.getVersionProperties()

        val versionKeyReader = project.retrieveVersionKeyReader()

        val dependenciesWithHardcodedVersions = mutableListOf<Dependency>()
        val dependenciesWithDynamicVersions = mutableListOf<Dependency>()

        val dependenciesWithVersionCandidates: List<DependencyWithVersionCandidates> = runBlocking {
            allDependencies.mapNotNull { dependency ->

                //TODO: Show status and progress.

                if (dependency.isManageableVersion(versionProperties, versionKeyReader).not()) {
                    if (dependency.version != null) {
                        // null version means it's expected to be added by a BoM or a plugin, so we ignore them.
                        dependenciesWithHardcodedVersions.add(dependency)
                    }
                    if (dependency is ExternalDependency && dependency.versionConstraint
                            .hasDynamicVersion()
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
                        moduleIdentifier = moduleIdentifier,
                        currentVersion = resolvedVersion ?: versionCandidates.let {
                            if (it.isEmpty()) throw IllegalStateException(
                                "Unable to find a version candidate for the following artifact:\n" +
                                    "$group:$name\n" +
                                    "Please, check this artifact exists in the configured repositories."
                            )
                            it.first().version.value
                        },
                        versionsCandidates = if (resolvedVersion != null) versionCandidates else {
                            versionCandidates.drop(1)
                        }
                    )
                }
            }.toList().awaitAll()
        }
        project.rootProject.updateVersionsProperties(dependenciesWithVersionCandidates)
        if (dependenciesWithHardcodedVersions.isNotEmpty()) {
            //TODO: Suggest running a diagnosis task to list the hardcoded versions.
            val warnFor = (dependenciesWithHardcodedVersions).take(3).map {
                "${it.group}:${it.name}:${it.version}"
            }
            logger.warn(
                """Found ${dependenciesWithHardcodedVersions.count()} hardcoded dependencies versions.
                |
                |$warnFor...
                |
                |To ensure single source of truth, refreshVersions only works with version placeholders,
                |that is the explicit way of marking the version is not there (but in the versions.properties file).
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

    private fun Dependency.isManageableVersion(
        versionProperties: Map<String, String>,
        versionKeyReader: ArtifactVersionKeyReader
    ): Boolean {
        return when {
            this is ExternalDependency && versionPlaceholder in this.versionConstraint
                .rejectedVersions -> true
            version == versionPlaceholder -> true
            moduleIdentifier?.isGradlePlugin == true -> {
                val versionFromProperty =
                    versionProperties[getVersionPropertyName(moduleIdentifier!!, versionKeyReader)]
                        ?: return false
                versionFromProperty.isAVersionAlias().not()
            }
            else -> false
        }
    }
}
