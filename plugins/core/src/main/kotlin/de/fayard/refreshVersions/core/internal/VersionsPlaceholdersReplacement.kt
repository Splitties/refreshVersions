package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.Version
import de.fayard.refreshVersions.core.extensions.gradle.isGradlePlugin
import de.fayard.refreshVersions.core.extensions.gradle.moduleId
import de.fayard.refreshVersions.core.extensions.gradle.toModuleIdentifier
import de.fayard.refreshVersions.core.internal.versions.writeWithAddedVersions
import kotlinx.coroutines.runBlocking
import org.gradle.api.Project
import org.gradle.api.artifacts.*
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.invocation.Gradle
import java.io.File

internal const val versionPlaceholder = "_"

internal fun Gradle.setupVersionPlaceholdersResolving(versionProperties: Map<String, String>) {

    val versionKeyReader = RefreshVersionsConfigHolder.versionKeyReader
    var properties: Map<String, String> = versionProperties
    val refreshProperties = { updatedProperties: Map<String, String> ->
        properties = updatedProperties
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
                initialProperties = properties,
                refreshProperties = refreshProperties
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
): String = getVersionPropertyName(
    moduleIdentifier = moduleId.toModuleIdentifier(),
    versionKeyReader = versionKeyReader
)

@InternalRefreshVersionsApi
fun getVersionPropertyName(
    moduleIdentifier: ModuleIdentifier,
    versionKeyReader: ArtifactVersionKeyReader
): String {

    val group = moduleIdentifier.group
    val name = moduleIdentifier.name

    return when {
        name == "gradle" && group == "com.android.tools.build" -> "plugin.android"
        moduleIdentifier.isGradlePlugin -> {
            name.substringBeforeLast(".gradle.plugin").let { pluginId ->
                when {
                    pluginId.startsWith("org.jetbrains.kotlin") -> "version.kotlin"
                    pluginId.startsWith("com.android") -> "plugin.android"
                    else -> "plugin.$pluginId"
                }
            }
        }
        else -> {
            val versionKey = versionKeyReader.readVersionKey(group = group, name = name) ?: "$group..$name"
            "version.$versionKey"
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
 * Expects the value of a version property (values of the map returned by [RefreshVersionsConfigHolder.readVersionProperties]).
 */
internal fun String.isAVersionAlias(): Boolean = startsWith("version.") || startsWith("plugin.")

private val lock = Any()

private fun Configuration.replaceVersionPlaceholdersFromDependencies(
    project: Project,
    isFromBuildscript: Boolean,
    versionKeyReader: ArtifactVersionKeyReader,
    initialProperties: Map<String, String>,
    refreshProperties: (updatedProperties: Map<String, String>) -> Unit
) {

    val repositories = if (isFromBuildscript) project.buildscript.repositories else project.repositories
    var properties = initialProperties
    @Suppress("UnstableApiUsage")
    withDependencies {
        for (dependency in this) {
            if (dependency !is ExternalDependency) continue
            if (dependency.version != versionPlaceholder) continue
            val moduleId = dependency.moduleId
            val propertyName = getVersionPropertyName(moduleId, versionKeyReader)
            val versionFromProperties = resolveVersion(
                properties = properties,
                key = propertyName
            ) ?: synchronized(lock) {
                RefreshVersionsConfigHolder.readVersionProperties().let { updatedProperties ->
                    properties = updatedProperties
                    refreshProperties(updatedProperties)
                }
                resolveVersion(properties, propertyName)
                    ?: `Write versions candidates using latest most stable version and get it`(
                        versionsPropertiesFile = RefreshVersionsConfigHolder.versionsPropertiesFile,
                        repositories = repositories,
                        propertyName = propertyName,
                        dependency = dependency
                    ).also {
                        RefreshVersionsConfigHolder.readVersionProperties().let { updatedProperties ->
                            properties = updatedProperties
                            refreshProperties(updatedProperties)
                        }
                    }
            }
            dependency.version {
                require(versionFromProperties)
                reject(versionPlaceholder) // Remember that we're managing the version of this dependency.
            }
        }
    }
}

@Suppress("unused") // Used in the dependencies plugin
@InternalRefreshVersionsApi
fun Project.writeCurrentVersionInProperties(
    versionKey: String,
    currentVersion: String
) {
    writeWithAddedVersions(
        versionsFile = RefreshVersionsConfigHolder.versionsPropertiesFile,
        propertyName = versionKey,
        versionsCandidates = listOf(Version(currentVersion))
    )
}

@Suppress("FunctionName")
private fun `Write versions candidates using latest most stable version and get it`(
    versionsPropertiesFile: File,
    repositories: ArtifactRepositoryContainer,
    propertyName: String,
    dependency: ExternalDependency
): String = runBlocking {
    val dependencyVersionsFetchers = repositories.filterIsInstance<MavenArtifactRepository>()
        .mapNotNull { repo ->
            DependencyVersionsFetcher(
                httpClient = RefreshVersionsConfigHolder.httpClient,
                dependency = dependency,
                repository = repo
            )
        }
    dependencyVersionsFetchers.getVersionCandidates(
        currentVersion = Version(""),
        resultMode = RefreshVersionsConfigHolder.resultMode
    ).let { versionCandidates ->
        val bestStability = versionCandidates.minBy { it.stabilityLevel }!!.stabilityLevel
        val versionToUse = versionCandidates.last { it.stabilityLevel == bestStability }
        writeWithAddedVersions(
            versionsFile = versionsPropertiesFile,
            propertyName = propertyName,
            versionsCandidates = versionCandidates.dropWhile { it != versionToUse }
        )
        versionToUse.value
    }
}
