package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.FeatureFlag
import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.core.extensions.gradle.moduleId
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel
import de.fayard.refreshVersions.core.internal.versions.readFromFile
import de.fayard.refreshVersions.core.internal.versions.writeWithNewEntry
import kotlinx.coroutines.runBlocking
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.artifacts.repositories.ArtifactRepository
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.invocation.Gradle
import kotlin.reflect.full.memberFunctions

internal const val versionPlaceholder = "_"

internal fun Gradle.setupVersionPlaceholdersResolving(versionsMap: Map<String, String>) {

    val versionKeyReader = RefreshVersionsConfigHolder.versionKeyReader
    var currentVersionsMap: Map<String, String> = versionsMap
    val refreshVersionsMap = { updatedMap: Map<String, String> ->
        currentVersionsMap = updatedMap
    }
    beforeProject {
        val project: Project = this@beforeProject

        fun replaceVersionPlaceholdersFromDependencies(
            configuration: Configuration,
            isFromBuildscript: Boolean
        ) {
            if (configuration.name in configurationNamesToIgnore) return

            configuration.replaceVersionPlaceholdersFromDependencies(
                project = project,
                isFromBuildscript = isFromBuildscript,
                versionKeyReader = versionKeyReader,
                initialVersionsMap = currentVersionsMap,
                refreshVersionsMap = refreshVersionsMap
            )
        }

        project.buildscript.configurations.configureEach {
            replaceVersionPlaceholdersFromDependencies(
                configuration = this,
                isFromBuildscript = true
            )
        }

        configurations.configureEach {
            replaceVersionPlaceholdersFromDependencies(
                configuration = this,
                isFromBuildscript = false
            )
        }
    }
}

private val configurationNamesToIgnore: List<String> = listOf(
    "embeddedKotlin",
    "kotlinCompilerPluginClasspath",
    "kotlinCompilerClasspath"
)

@InternalRefreshVersionsApi
fun getVersionPropertyName(
    moduleId: ModuleId,
    versionKeyReader: ArtifactVersionKeyReader
): String {

    val group = moduleId.group
    val name = moduleId.name

    //TODO: Pos pluginDependencyNotationToVersionKey ?
    return when (moduleId) {
        is ModuleId.Maven -> when {

            name == "gradle" && group == "com.android.tools.build" -> "plugin.android"
            moduleId.name.endsWith(".gradle.plugin") -> {
                name.substringBeforeLast(".gradle.plugin").let { pluginId ->
                    when {
                        pluginId.startsWith("org.jetbrains.kotlin.") -> "version.kotlin"
                        pluginId.startsWith("com.android") -> "plugin.android"
                        else -> "plugin.$pluginId"
                    }
                }
            }
            else -> {
                val versionKey = versionKeyReader.readVersionKey(
                    group = moduleId.group,
                    name = moduleId.name
                ) ?: "${moduleId.group}..${moduleId.name}"
                "version.$versionKey"
            }
        }
        is ModuleId.Npm -> {
            when (group) {
                null -> "version.npm.$name"
                else -> "version.npm.@$group/$name"
            }
        }
    }
}

internal tailrec fun resolveVersion(
    properties: Map<String, String>,
    key: String,
    redirects: Int = 0
): String? {
    if (redirects > 5) error("Up to five redirects are allowed, for readability. You should only need one.")
    val value = properties[key] ?: return null
    return if (value.isAVersionAlias()) resolveVersion(properties, value, redirects + 1) else value
}

/**
 * Expects the value of a version property (values of the map returned by [RefreshVersionsConfigHolder.readVersionsMap]).
 */
internal fun String.isAVersionAlias(): Boolean = startsWith("version.") || startsWith("plugin.")

private val lock = Any()

private fun Configuration.replaceVersionPlaceholdersFromDependencies(
    project: Project,
    isFromBuildscript: Boolean,
    versionKeyReader: ArtifactVersionKeyReader,
    initialVersionsMap: Map<String, String>,
    refreshVersionsMap: (updatedMap: Map<String, String>) -> Unit
) {
    val repositories by lazy {
        when {
            isFromBuildscript -> project.buildscript.repositories
            else -> project.repositories
        }.withGlobalRepos()
    }
    var properties = initialVersionsMap

    val dependenciesToReplace = mutableListOf<Pair<Dependency, Dependency>>()

    @Suppress("UnstableApiUsage")
    withDependencies {
        for (dependency in this) {
            if (dependency.version != versionPlaceholder) continue
            val moduleId = dependency.moduleId() ?: continue
            val propertyName = getVersionPropertyName(moduleId, versionKeyReader)
            val versionFromProperties = resolveVersion(
                properties = properties,
                key = propertyName
            ) ?: synchronized(lock) {
                RefreshVersionsConfigHolder.readVersionsMap().let { updatedMap ->
                    properties = updatedMap
                    refreshVersionsMap(updatedMap)
                }
                resolveVersion(properties, propertyName)
                    ?: `Copy previously matching version entry, if any, and get its version`(
                        versionKeyReader = versionKeyReader,
                        propertyName = propertyName,
                        versionsMap = properties,
                        moduleId = moduleId
                    )
                    ?: `Write versions candidates using latest most stable version and get it`(
                        repositories = repositories,
                        propertyName = propertyName,
                        dependency = dependency,
                        moduleId = moduleId
                    ).also {
                        RefreshVersionsConfigHolder.readVersionsMap().let { updatedMap ->
                            properties = updatedMap
                            refreshVersionsMap(updatedMap)
                        }
                    }
            }
            if (dependency is ExternalDependency) {
                dependency.version {
                    require(versionFromProperties)
                    reject(versionPlaceholder) // Remember that we're managing the version of this dependency.
                }
            } else if (moduleId is ModuleId.Npm) {
                val version = when {
                    FeatureFlag.NPM_IMPLICIT_RANGE.isEnabled && Version(versionFromProperties).isRange.not() -> {
                        "^$versionFromProperties"
                    }
                    else -> {
                        versionFromProperties
                    }
                }
                dependenciesToReplace += dependency to npmDependencyWithVersion(dependency, version)
            }
        }
        dependenciesToReplace.forEach { (old, new) ->
            remove(old)
            add(new)
        }
    }
}

/**
 * Equivalent to: dependency.copy(version = versionFromProperties)
 */
private fun npmDependencyWithVersion(
    dependency: Dependency,
    versionFromProperties: String
): Dependency {
    val copyFun =
        dependency::class.memberFunctions.first { it.name == "copy" && it.parameters.any { p -> p.name == "version" } }
    val versionParameter = copyFun.parameters.first { it.name == "version" }
    val receiverParameter = copyFun.parameters.first { it.name == null }

    return copyFun.callBy(
        mapOf(
            receiverParameter to dependency,
            versionParameter to versionFromProperties
        )
    ) as Dependency
}

@Suppress("unused") // Used in the dependencies plugin
@InternalRefreshVersionsApi
fun Project.writeCurrentVersionInProperties(
    versionKey: String,
    currentVersion: String
) {
    VersionsPropertiesModel.writeWithNewEntry(
        propertyName = versionKey,
        versionsCandidates = listOf(Version(currentVersion)),
        failures = emptyList()
    )
}

@Suppress("FunctionName")
private fun `Copy previously matching version entry, if any, and get its version`(
    versionKeyReader: ArtifactVersionKeyReader,
    propertyName: String,
    versionsMap: Map<String, String>,
    moduleId: ModuleId
): String? {
    if (moduleId !is ModuleId.Maven) return null
    val previouslyMatchingEntry = findAnyPreviouslyMatchingVersionEntry(
        versionKeyReader = versionKeyReader,
        moduleId = moduleId,
        versionsMap = versionsMap
    ) ?: return null
    VersionsPropertiesModel.writeWithNewEntry(
        propertyName = propertyName,
        versionsCandidates = List(previouslyMatchingEntry.availableUpdates.size + 1) { index ->
            when (index) {
                0 -> Version(previouslyMatchingEntry.currentVersion)
                else -> Version(previouslyMatchingEntry.availableUpdates[index -1])
            }
        },
        failures = emptyList()
    )
    return previouslyMatchingEntry.currentVersion
}

private fun findAnyPreviouslyMatchingVersionEntry(
    versionKeyReader: ArtifactVersionKeyReader,
    moduleId: ModuleId.Maven,
    versionsMap: Map<String, String>
): VersionsPropertiesModel.Section.VersionEntry? {
    val model = VersionsPropertiesModel.readFromFile()
    val versionEntries = model.sections.filterIsInstance<VersionsPropertiesModel.Section.VersionEntry>()
    val previouslyMatchingVersionKey = versionKeyReader.getRemovedDependenciesVersionsKeys()[moduleId]
    return versionEntries.find { it.key == previouslyMatchingVersionKey }
        ?: with(moduleId) {
            "version.$group..$name".takeIf { it in versionsMap }
        }?.let { defaultVersionKey ->
            versionEntries.find { it.key == defaultVersionKey }
        }
}

@Suppress("FunctionName")
private fun `Write versions candidates using latest most stable version and get it`(
    repositories: List<ArtifactRepository>,
    propertyName: String,
    dependency: Dependency,
    moduleId: ModuleId
): String {
    val dependencyVersionsFetchers = when (moduleId) {
        is ModuleId.Maven -> repositories.filterIsInstance<MavenArtifactRepository>().mapNotNull { repo ->
            DependencyVersionsFetcher.forMaven(
                httpClient = RefreshVersionsConfigHolder.httpClient,
                dependency = dependency,
                repository = repo
            )
        }
        is ModuleId.Npm -> listOf(
            DependencyVersionsFetcher.forNpm(
                httpClient = RefreshVersionsConfigHolder.httpClient,
                npmDependency = dependency,
                npmRegistry = "https://registry.npmjs.org/" //TODO: Support custom npm registries.
            )
        )
    }
    return runBlocking {
        dependencyVersionsFetchers.getVersionCandidates(
            currentVersion = Version(""),
            resultMode = RefreshVersionsConfigHolder.resultMode
        ).let { (versionCandidates, failures) ->
            if (versionCandidates.isEmpty()) {
                TODO("Handle no versions found with a precise and helfpul error message")
                //TODO: Mention to moduleId or dependency in a readable way
                // Specify the failures count
                // List all the failures, along with stacktrace from the exception/throwable, if any.
            }
            val bestStability = versionCandidates.minByOrNull { it.stabilityLevel }!!.stabilityLevel
            val versionToUse = versionCandidates.last { it.stabilityLevel == bestStability }
            VersionsPropertiesModel.writeWithNewEntry(
                propertyName = propertyName,
                versionsCandidates = versionCandidates.dropWhile { it != versionToUse },
                failures = failures
            )
            versionToUse.value
        }
    }
}
