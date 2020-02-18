package de.fayard.versions.internal

import de.fayard.versions.artifactVersionKeyReader
import de.fayard.versions.extensions.isBuildSrc
import de.fayard.versions.extensions.isGradlePlugin
import de.fayard.versions.extensions.isRootProject
import de.fayard.versions.extensions.moduleIdentifier
import kotlinx.coroutines.runBlocking
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.ModuleIdentifier
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import java.io.File
import java.util.Properties

internal const val versionPlaceholder = "_"
internal const val becauseRefreshVersions = "refreshVersions"

internal fun @Suppress("unused") Project.retrieveVersionKeyReader(): ArtifactVersionKeyReader {
    return artifactVersionKeyReader
}

internal fun Project.setupVersionPlaceholdersResolving() {
    require(this.isRootProject)
    val versionKeyReader = retrieveVersionKeyReader()
    var properties: Map<String, String> = project.getVersionProperties()
    allprojects {
        val project: Project = this

        configurations.all {
            val configuration: Configuration = this

            if (configuration.name in configurationNamesToIgnore) return@all

            @Suppress("UnstableApiUsage")
            withDependencies {
                val dependencies = filterIsInstance<ModuleDependency>()

                val dependenciesToReplace = dependencies.filter { it.version == versionPlaceholder }
                removeAll(dependenciesToReplace)

                if (dependencies.any { it.hasHardcodedVersion() }) {
                    val warnFor = (dependencies - dependenciesToReplace).take(3).map { it.name }
                    logger.warn(""":${project.name}:${configuration.name} found hardcoded dependencies versions $warnFor   See https://github.com/jmfayard/refreshVersions/issues/160 """)
                }

                for (dependency in dependenciesToReplace) {
                    val moduleIdentifier = dependency.moduleIdentifier
                        ?: error("Didn't find a group for the following dependency: $dependency")
                    val propertyName = getVersionPropertyName(moduleIdentifier, versionKeyReader)
                    val versionFromProperties = resolveVersion(properties, propertyName)
                        ?: synchronized(lock) {
                            properties = project.getVersionProperties() // Refresh properties
                            resolveVersion(properties, propertyName)
                                ?: `Write versions candidates using latest most stable version and get it`(
                                    versionsPropertiesFile = versionsPropertiesFile(),
                                    repositories = repositories
                                        .filterIsInstance<MavenArtifactRepository>()
                                        .map { MavenRepoUrl(it.url.toString()) },
                                    propertyName = propertyName,
                                    group = moduleIdentifier.group,
                                    name = moduleIdentifier.name
                                )
                        }
                    val dependencyNotation = "${dependency.group}:${dependency.name}:$versionFromProperties"
                    add(project.dependencies.create(dependencyNotation).also { it.because(becauseRefreshVersions) })
                }
            }
        }
    }
}

private val configurationNamesToIgnore: List<String> = listOf(
    "embeddedKotlin",
    "kotlinCompilerPluginClasspath",
    "kotlinCompilerClasspath"
)

private fun ModuleDependency.hasHardcodedVersion(): Boolean = version != null && version != versionPlaceholder

internal fun getVersionPropertyName(
    moduleIdentifier: ModuleIdentifier,
    versionKeyReader: ArtifactVersionKeyReader
): String {
    //TODO: Reconsider the TODO below because we don't care about settings.gradle(.kts) buildscript for plugins since
    // it can alias to any version property.

    //TODO: What about the plugins? Should we use a custom text-based file format to allow early configuration?
    // If we go down that road, what about invalidation? Also, would that invalidate the whole build or can we do
    // better? Or would we have to hack to have the needed invalidation to happen?

    val group = moduleIdentifier.group
    val name = moduleIdentifier.name
    val versionKey: String = versionKeyReader.readVersionKey(group = group, name = name) ?: when {
        name == "gradle" && group == "com.android.tools.build" -> return "plugin.android"
        moduleIdentifier.isGradlePlugin -> {
            val pluginId = name.substringBeforeLast(".gradle.plugin")
            return when {
                pluginId.startsWith("org.jetbrains.kotlin") -> "version.kotlin"
                pluginId.startsWith("com.android") -> "plugin.android"
                else -> "plugin.$pluginId"
            }
        }
        else -> "$group..$name"
    }
    return "version.$versionKey"
}

internal fun Project.getVersionProperties(
    includeProjectProperties: Boolean = true
): Map<String, String> {
    return mutableMapOf<String, String>().also { map ->
        // Read from versions.properties
        Properties().also { properties ->
            properties.load(versionsPropertiesFile().reader())
        }.forEach { (k, v) -> if (k is String && v is String) map[k] = v }
        // Overwrite with relevant project properties
        if (includeProjectProperties) properties.forEach { (k, v) ->
            if (v is String) {
                if (v.startsWith("version.") || v.startsWith("plugin.")) {
                    map[k] = v
                }
            }
        }
    }
}

internal tailrec fun resolveVersion(properties: Map<String, String>, key: String, redirects: Int = 0): String? {
    if (redirects > 5) error("Up to five redirects are allowed, for readability. You should only need one.")
    val value = properties[key] ?: return null
    return if (value.isAVersionAlias()) resolveVersion(properties, value, redirects + 1) else value
}

/**
 * Expects the value of a version property (values of the map returned by [getVersionProperties]).
 */
internal fun String.isAVersionAlias(): Boolean = startsWith("version.") || startsWith("plugin.")

private val lock = Any()

private fun Project.versionsPropertiesFile(): File {
    val relativePath = "versions.properties".let { if (project.isBuildSrc) "../$it" else it }
    return rootProject.file(relativePath)
}

@Suppress("FunctionName")
private fun `Write versions candidates using latest most stable version and get it`(
    versionsPropertiesFile: File,
    repositories: List<MavenRepoUrl>,
    propertyName: String,
    group: String,
    name: String
): String = runBlocking {
    val versionCandidates = getDependencyVersionsCandidates(
        repositories = repositories,
        group = group,
        name = name,
        resolvedVersion = null
    )
    if (versionCandidates.isEmpty()) {
        throw IllegalStateException("Unable to find a version candidate for the following artifact:\n" +
            "$group:$name\n" +
            "Please, check this artifact exists in the configured repositories.")
    }
    writeWithAddedVersions(versionsPropertiesFile, propertyName, versionCandidates)
    versionCandidates.first().version.value
}
