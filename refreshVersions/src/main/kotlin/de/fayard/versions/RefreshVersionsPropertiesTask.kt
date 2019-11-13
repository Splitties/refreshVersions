package de.fayard.versions

import de.fayard.versions.extensions.isGradlePlugin
import de.fayard.versions.extensions.moduleIdentifier
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.internal.artifacts.dependencies.AbstractDependency
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

open class RefreshVersionsPropertiesTask : DefaultTask() {

    @Suppress("UnstableApiUsage")
    @Option(description = "Update all versions, I will check git diff afterwards")
    @Optional
    var update: Boolean = false

    @TaskAction
    fun taskActionRefreshVersions() {

        val allConfigurations: Set<Configuration> = project.allprojects.flatMap {
            it.buildscript.configurations + it.configurations
        }.toSet()

        val buildSrcDependencies: Sequence<Dependency> = project.file("buildSrc")
            .resolve(usedDependenciesFilePath)
            .let { file ->
                if (file.exists().not()) return@let emptySequence<Dependency>()
                file.readLines().asSequence().map { line -> line.parseDependency() }
            }

        val allDependencies = (allConfigurations.asSequence()
            .flatMap { it.allDependencies.asSequence() } + buildSrcDependencies)
            .distinctBy { it.group + ':' + it.name + ':' + it.version }

        //TODO: Filter using known grouping strategies to only use the main artifact to resolve latest version, this
        // will reduce the number of repositories lookups, improving performance.

        val initialRepositories = project.rootProject.repositories
        project.allprojects.flatMap { it.buildscript.repositories + it.repositories }.toSet().let { allRepositories ->
            project.rootProject.repositories.addAll(allRepositories)
        }
        try {
            val extension = project.rootProject.extensions.getByType<RefreshVersionsPropertiesExtension>()

            val versionProperties: Map<String, String> = project.getVersionProperties()

            val dependenciesWithLastVersion: List<Pair<Dependency, String?>>
            dependenciesWithLastVersion = allDependencies.mapNotNull { dependency ->

                println("Dependency ${dependency.group}:${dependency.name}:${dependency.version}")
                //TODO: Replace line above with optional diagnostic option, or show status in progress.

                if (dependency.isManageableVersion(versionProperties).not()) {
                    return@mapNotNull null //TODO: Keep aside to report hardcoded versions and version ranges,
                    //todo... see this issue: https://github.com/jmfayard/buildSrcVersions/issues/126
                }

                val latestVersion = project.rootProject.getLatestDependencyVersion(
                    extension = extension,
                    dependency = dependency,
                    resolvedVersion = resolveVersion(
                        properties = versionProperties,
                        key = dependency.moduleIdentifier?.getVersionPropertyName() ?: return@mapNotNull null
                    )
                )

                return@mapNotNull dependency to latestVersion
            }.toList()
            project.rootProject.updateVersionsProperties(dependenciesWithLastVersion)
        } finally {
            project.rootProject.repositories.let {
                it.clear()
                it.addAll(initialRepositories)
            }
        }
    }

    private fun Dependency.isManageableVersion(versionProperties: Map<String, String>): Boolean {
        return when {
            version == versionPlaceholder -> true
            moduleIdentifier?.isGradlePlugin == true -> {
                val versionFromProperty = versionProperties[moduleIdentifier!!.getVersionPropertyName()]
                    ?: return false
                versionFromProperty.isAVersionAlias().not()
            }
            else -> false
        }
    }
}

private fun Project.getLatestDependencyVersion(
    extension: RefreshVersionsPropertiesExtension,
    dependency: Dependency,
    resolvedVersion: String?
): String? {
    val tmpDependencyUpdateConfiguration = configurations.create("getLatestVersion") {
        resolutionStrategy.componentSelection.all {
            val componentSelectionData = ComponentSelectionData(
                currentVersion = resolvedVersion ?: "",
                candidate = candidate
            )
            extension.rejectVersionsPredicate?.let { rejectPredicate ->
                if (rejectPredicate(componentSelectionData)) {
                    println("Rejected version: ${candidate.version}")
                    reject("Rejected in rejectVersionsIf { ... }")
                }
            }
            extension.acceptVersionsPredicate?.let { acceptPredicate ->
                if (acceptPredicate(componentSelectionData).not()) {
                    println("Rejected version: ${candidate.version}")
                    reject("Not accepted in acceptVersionOnlyIf { ... }")
                }
            }
        }
        resolutionStrategy.eachDependency {
            if (requested.version != null) useVersion("+")
        }
    }
    if (dependency is ParsedDependency) dependencies {
        // Directly using a ParsedDependency leads to a ResolveException, so we use dependencyNotation.
        tmpDependencyUpdateConfiguration(dependency.dependencyNotation)
    } else tmpDependencyUpdateConfiguration.dependencies.add(dependency)
    try {
        return tmpDependencyUpdateConfiguration
            .resolvedConfiguration
            .firstLevelModuleDependencies
            .singleOrNull()
            ?.moduleVersion
    } finally {
        configurations.remove(tmpDependencyUpdateConfiguration)
    }
}

private abstract class ParsedDependency(val dependencyNotation: String) : AbstractDependency()

private fun String.parseDependency(): Dependency = object : ParsedDependency(this) {

    private val group = substringBefore(':').unwrappedNullableValue()
    private val name = substringAfter(':').substringBefore(':')
    private val version = substringAfterLast(':').unwrappedNullableValue()

    override fun getGroup() = group
    override fun getName() = name
    override fun getVersion(): String? = version

    override fun contentEquals(dependency: Dependency): Boolean = throw UnsupportedOperationException()
    override fun copy(): Dependency = parseDependency()

    private fun String.unwrappedNullableValue(): String? = if (this == "null") null else this
}
