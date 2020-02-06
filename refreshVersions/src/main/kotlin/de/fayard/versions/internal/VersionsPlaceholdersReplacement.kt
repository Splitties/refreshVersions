package de.fayard.versions.internal

import de.fayard.versions.internal.ArtifactGroupNaming.*
import de.fayard.versions.extensions.isBuildSrc
import de.fayard.versions.extensions.isGradlePlugin
import de.fayard.versions.extensions.isRootProject
import de.fayard.versions.extensions.moduleIdentifier
import kotlinx.coroutines.runBlocking
import org.gradle.api.Project
import org.gradle.api.artifacts.ModuleDependency
import org.gradle.api.artifacts.ModuleIdentifier
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import java.io.File
import java.util.Properties

internal const val versionPlaceholder = "_"
internal const val becauseRefreshVersions = "refreshVersions"

internal fun Project.setupVersionPlaceholdersResolving() {
    require(this.isRootProject)
    var properties: Map<String, String> = project.getVersionProperties()
    allprojects {
        configurations.all {
            @Suppress("UnstableApiUsage")
            withDependencies {
                val dependenciesToReplace = filter { it is ModuleDependency && it.version == versionPlaceholder }
                removeAll(dependenciesToReplace)
                for (dependency in dependenciesToReplace) {
                    val moduleIdentifier = dependency.moduleIdentifier
                        ?: error("Didn't find a group for the following dependency: $dependency")
                    val propertyName = moduleIdentifier.getVersionPropertyName()
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

internal fun ModuleIdentifier.getVersionPropertyName(): String {
    //TODO: Reconsider the TODO below because we don't care about settings.gradle(.kts) buildscript for plugins since
    // it can alias to any version property.

    //TODO: Allow customizing the artifact grouping rules, including resetting the default ones.
    // What about the plugins? Should we use a custom text-based file format to allow early configuration?
    // If we go down that road, what about invalidation? Also, would that invalidate the whole build or can we do
    // better? Or would we have to hack to have the needed invalidation to happen?
    return getVersionPropertyName(this)
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
    writeWithAddedVersions(versionsPropertiesFile, propertyName, versionCandidates)
    versionCandidates.first().version.value
}

@JvmName("_getVersionPropertyName")
private fun getVersionPropertyName(moduleIdentifier: ModuleIdentifier): String {
    val group = moduleIdentifier.group
    val name = moduleIdentifier.name
    val versionKey: String = when (moduleIdentifier.findArtifactGroupingRule()?.groupNaming) {
        GroupOnly -> group
        GroupLastPart -> group.substringAfterLast('.')
        GroupFirstTwoParts -> {
            val groupFirstPart = group.substringBefore('.')
            val groupSecondPart = group.substringAfter('.').substringBefore('.')
            "$groupFirstPart.$groupSecondPart"
        }
        GroupFirstThreeParts -> {
            val groupFirstPart = group.substringBefore('.')
            val groupSecondPart = group.substringAfter('.').substringBefore('.')
            val groupThirdPart = group.substringAfter('.').substringAfter('.').substringBefore('.')
            "$groupFirstPart.$groupSecondPart.$groupThirdPart"
        }
        GroupAndNameFirstPart -> "$group.${name.substringBefore('-')}"
        GroupLastPartAndNameSecondPart -> {
            val groupLastPart = group.substringAfterLast('.')
            val nameSecondPart = name.substringAfter('-').substringBefore('-')
            "$groupLastPart.$nameSecondPart"
        }
        GroupFirstPartAndName -> {
            val groupFirstPart = group.substringBefore('.')
            "$groupFirstPart.$name"
        }
        null -> when {
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
    }
    return "version.$versionKey"
}

private fun ModuleIdentifier.findArtifactGroupingRule(): ArtifactGroupingRule? {
    if (forceFullyQualifiedName(this)) return null
    val fullArtifactName = "$group:$name"
    //TODO: Make the rules user-editable
    return artifactsGroupingRules.find { fullArtifactName.startsWith(it.artifactNamesStartingWith) }
}

private fun forceFullyQualifiedName(moduleIdentifier: ModuleIdentifier): Boolean {
    val group = moduleIdentifier.group
    val name = moduleIdentifier.name
    if (group.startsWith("androidx.") && group != "androidx.legacy") {
        val indexOfV = name.indexOf("-v")
        if (indexOfV != -1 &&
            indexOfV < name.lastIndex &&
            name.substring(indexOfV + 1, name.lastIndex).all { it.isDigit() }
        ) return true // AndroidX artifacts ending in "v18" or other "v${someApiLevel}" have standalone version number.
    }
    return false
}

@Suppress("SpellCheckingInspection")
private val artifactsGroupingRules: List<ArtifactGroupingRule> = sequenceOf(
    "org.jetbrains.kotlin:kotlin" to GroupLastPart,
    "org.jetbrains.kotlinx:kotlinx" to GroupLastPartAndNameSecondPart,
    "androidx." to GroupOnly,
    "androidx.camera:camera-extensions" to GroupFirstPartAndName,
    "androidx.camera:camera-view" to GroupFirstPartAndName,
    "androidx.car:car-cluster" to GroupFirstPartAndName,
    "androidx.core:core-role" to GroupFirstPartAndName,
    "androidx.dynamicanimation:dynamicanimation-ktx" to GroupFirstPartAndName,
    "androidx.media:media-widget" to GroupFirstPartAndName,
    "androidx.slice:slice-builders-ktx" to GroupFirstPartAndName,
    "androidx.test:core" to GroupAndNameFirstPart, // Rest of androidx.test share the same version.
    "androidx.test.ext:junit" to GroupAndNameFirstPart,
    "androidx.test.ext:truth" to GroupFirstTwoParts, // Same version as the rest of androidx.test.
    "androidx.test.services" to GroupFirstTwoParts, // Same version as the rest of androidx.test.
    "androidx.test.espresso.idling" to GroupFirstThreeParts, // Same version as other androidx.test.espresso artifacts.
    "com.louiscad.splitties:splitties" to GroupLastPart,
    "com.google.ar" to GroupFirstThreeParts,
    "com.squareup.retrofit2" to GroupLastPart,
    "com.squareup.okhttp3" to GroupLastPart,
    "com.squareup.moshi" to GroupLastPart,
    "com.squareup.sqldelight" to GroupLastPart,
    "org.robolectric" to GroupLastPart,
    "io.kotlintest" to GroupOnly
).map { (artifactNamesStartingWith, groupNaming) ->
    ArtifactGroupingRule(
        artifactNamesStartingWith = artifactNamesStartingWith,
        groupNaming = groupNaming
    )
}.sortedByDescending { it.artifactNamesStartingWith.length }.toList()
