package de.fayard.versions.internal

import de.fayard.versions.extensions.isBuildSrc
import de.fayard.versions.extensions.isGradlePlugin
import de.fayard.versions.extensions.moduleIdentifier
import de.fayard.versions.extensions.stabilityLevel
import kotlinx.coroutines.runBlocking
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.artifacts.ModuleIdentifier
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import java.io.File

internal const val versionPlaceholder = "_"

internal fun Gradle.setupVersionPlaceholdersResolving(versionProperties: Map<String, String>) {

    val versionKeyReader = retrieveVersionKeyReader()
    var properties: Map<String, String> = versionProperties
    val refreshProperties = { updatedProperties: Map<String, String> ->
        properties = updatedProperties
    }
    beforeProject {
        val project: Project = this@beforeProject

        fun replaceVersionPlaceholdersFromDependencies(configuration: Configuration) {
            if (configuration.name in configurationNamesToIgnore) return

            configuration.replaceVersionPlaceholdersFromDependencies(
                project = project,
                versionKeyReader = versionKeyReader,
                initialProperties = properties,
                refreshProperties = refreshProperties
            )
        }

        project.buildscript.configurations.configureEach {
            replaceVersionPlaceholdersFromDependencies(configuration = this)
        }

        configurations.configureEach {
            replaceVersionPlaceholdersFromDependencies(configuration = this)
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

internal fun Settings.versionsPropertiesFile(): File {
    val relativePath = "versions.properties".let { if (isBuildSrc) "../$it" else it }
    return rootDir.resolve(relativePath).also {
        it.createNewFile() // Creates the file if it doesn't exist yet
    }
}

internal fun Project.versionsPropertiesFile(): File {
    val relativePath = "versions.properties".let { if (project.isBuildSrc) "../$it" else it }
    return rootProject.file(relativePath).also {
        it.createNewFile() // Creates the file if it doesn't exist yet
    }
}

private fun Configuration.replaceVersionPlaceholdersFromDependencies(
    project: Project,
    versionKeyReader: ArtifactVersionKeyReader,
    initialProperties: Map<String, String>,
    refreshProperties: (updatedProperties: Map<String, String>) -> Unit
) {
    var properties = initialProperties
    @Suppress("UnstableApiUsage")
    withDependencies {
        for (dependency in this) {
            if (dependency !is ExternalDependency) continue
            if (dependency.version != versionPlaceholder) continue
            val moduleIdentifier = dependency.moduleIdentifier
                ?: error("Didn't find a group for the following dependency: $dependency")
            val propertyName = getVersionPropertyName(moduleIdentifier, versionKeyReader)
            val versionFromProperties = resolveVersion(properties, propertyName) ?: synchronized(lock) {
                project.getVersionProperties().let { updatedProperties ->
                    properties = updatedProperties
                    refreshProperties(updatedProperties)
                }
                resolveVersion(properties, propertyName)
                    ?: `Write versions candidates using latest most stable version and get it`(
                        versionsPropertiesFile = project.versionsPropertiesFile(),
                        repositories = (project.repositories + project.buildscript.repositories)
                            .filterIsInstance<MavenArtifactRepository>()
                            .map { MavenRepoUrl(it.url.toString()) },
                        propertyName = propertyName,
                        group = moduleIdentifier.group,
                        name = moduleIdentifier.name
                    )
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
    val versionsPropertiesFile = versionsPropertiesFile()
    val version = Version(currentVersion)
    val candidate = VersionCandidate(version.stabilityLevel(), version)
    writeWithAddedVersions(
        versionsFile = versionsPropertiesFile,
        propertyName = versionKey,
        versionsCandidates = listOf(candidate)
    )
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
        throw IllegalStateException(
            "Unable to find a version candidate for the following artifact:\n" +
                "$group:$name\n" +
                "Please, check this artifact exists in the configured repositories.\n" +
                "Searched the following repositories:" +
                repositories.joinToString(separator = "\n") { "- " + it.url }
        )
    }
    writeWithAddedVersions(versionsPropertiesFile, propertyName, versionCandidates)
    versionCandidates.first().version.value
}
