@file:JvmName("Versions")

package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.*
import de.fayard.refreshVersions.core.internal.UsedVersionForTracker
import de.fayard.refreshVersions.core.internal.getVersionForVersionFor
import de.fayard.refreshVersions.core.internal.resolveVersion
import org.gradle.api.Project

fun Project.versionFor(versionKey: String): String {
    // This function is overloaded to allow named parameter usage in Kotlin.
    // However, no check is performed here because we cannot detect if
    // the function wasn't called with named argument.
    return retrieveVersionFor(project = this, dependencyNotationOrVersionKey = versionKey)
}

fun Project.versionFor(dependencyNotation: CharSequence): String {
    // This function is overloaded to allow named parameter usage in Kotlin.
    // However, no check is performed here because we cannot detect if
    // the function wasn't called with named argument.
    return retrieveVersionFor(project = this, dependencyNotationOrVersionKey = dependencyNotation)
}

private fun retrieveVersionFor(
    project: Project,
    dependencyNotationOrVersionKey: CharSequence
): String {
    val isDependencyNotation = ':' in dependencyNotationOrVersionKey
    if (isDependencyNotation) {
        require(dependencyNotationOrVersionKey.endsWith(":_")) {
            "Expects a refreshVersions compatible dependency notation with the version placeholder (_)." +
                "\n" +
                "If the dependency is from BoM, " +
                "pass the BoM dependency notation itself with the version placeholder."
        }
        dependencyNotationOrVersionKey.toString().let {
            val moduleId = ModuleId.Maven(
                group = it.substringBefore(':'),
                name = it.substringBeforeLast(':').substringAfter(':')
            )
            return getVersionForVersionFor(
                project = project,
                moduleId = moduleId,
                versionKeyReader = RefreshVersionsConfigHolder.versionKeyReader
            ).also { version ->
                UsedVersionForTracker.noteUsedDependencyNotation(
                    project = project,
                    moduleId = moduleId,
                    version = version
                )
            }
        }
    }
    //TODO: Warn about not passing a dependency notation, suggest alternative, and mention future removal.
    val versionKey = dependencyNotationOrVersionKey.toString().also {
        require(it.startsWith("version.") || it.startsWith("plugin.")) {
            "Version keys all start with 'version.' or 'plugin.'. You need to pass the full version key."
        }
        UsedVersionForTracker.noteUsedVersionKey(project = project, versionKey = it)
    }
    return resolveVersion(
        properties = RefreshVersionsConfigHolder.lastlyReadVersionsMap,
        key = versionKey
    ) ?: resolveVersion(
        properties = RefreshVersionsConfigHolder.readVersionsMap(),
        key = versionKey
    ) ?: RefreshVersionsConfigHolder.versionsPropertiesFile.name.let { versionsFileName ->
        val errorMessage = "The version for the key $versionKey requested in " +
            "versionFor call wasn't found in the $versionsFileName file"
        error(errorMessage)
    }
}
