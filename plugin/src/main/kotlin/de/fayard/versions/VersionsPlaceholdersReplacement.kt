package de.fayard.versions

import de.fayard.versions.ArtifactGroupNaming.*
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ModuleIdentifier
import org.gradle.api.artifacts.ModuleVersionSelector

internal const val versionPlaceholder = "_"

internal fun Project.setupVersionPlaceholdersResolvingForConfiguration(configuration: Configuration) {
    configuration.resolutionStrategy.eachDependency {
        if (requested.version != versionPlaceholder) return@eachDependency
        useVersion(getVersionFromPropertiesForModule(requested))
    }
}

private fun Project.getVersionFromPropertiesForModule(module: ModuleVersionSelector): String {
    val moduleIdentifier: ModuleIdentifier = try {
        @Suppress("UnstableApiUsage")
        (module.module)
    } catch (e: Throwable) { // Guard against possible API changes.
        println(e)
        object : ModuleIdentifier {
            override fun getGroup(): String = module.group
            override fun getName(): String = module.name
        }
    }
    val propertyName = getVersionPropertyName(moduleIdentifier)
    val version = property(propertyName)
    return version as String
}

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
        GroupFirstPartAndNameTwoFirstParts -> {
            val groupFirstPart = group.substringBefore('.')
            val nameFirstPart = name.substringBefore('-')
            val nameSecondPart = name.substringAfter('-').substringBefore('-')
            "$groupFirstPart.$nameFirstPart-$nameSecondPart"
        }
        null -> "$group..$name"
    }
    return "version.$versionKey"
}

private fun ModuleIdentifier.findArtifactGroupingRule(): ArtifactGroupingRule? {
    if (forceFullyQualifiedName(this)) return null
    val fullArtifactName = "$group:$name"
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
    "androidx.media:media-widget" to GroupFirstPartAndNameTwoFirstParts,
    "androidx.test:core" to GroupAndNameFirstPart, // Rest of androidx.test share the same version.
    "androidx.test.ext:junit" to GroupAndNameFirstPart,
    "androidx.test.ext:truth" to GroupFirstTwoParts, // Same version as the rest of androidx.test.
    "androidx.test.services" to GroupFirstTwoParts, // Same version as the rest of androidx.test.
    "androidx.test.espresso.idling" to GroupFirstThreeParts, // Same version as other androidx.test.espresso artifacts.
    "com.louiscad.splitties:splitties" to GroupLastPart,
    "com.squareup.retrofit2" to GroupLastPart,
    "com.squareup.okhttp3" to GroupLastPart,
    "com.squareup.moshi" to GroupLastPart,
    "com.squareup.sqldelight" to GroupLastPart,
    "org.robolectric" to GroupLastPart
).map { (artifactNamesStartingWith, groupNaming) ->
    ArtifactGroupingRule(
        artifactNamesStartingWith = artifactNamesStartingWith,
        groupNaming = groupNaming
    )
}.sortedByDescending { it.artifactNamesStartingWith.length }.toList()
