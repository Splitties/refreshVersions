@file:JvmName("Versions")

package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.RefreshVersionsConfig
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.core.internal.getVersionPropertyName
import de.fayard.refreshVersions.core.internal.resolveVersion
import org.gradle.api.Project

@Deprecated("use versions extension", ReplaceWith("versions.versionFor(versionKey)"), level = DeprecationLevel.ERROR)
fun versionFor(versionKey: String): String {
    throw NotImplementedError("use versions.versionFor")
}

@Deprecated("use versions extension", ReplaceWith("versions.versionFor(dependencyNotation)"), level = DeprecationLevel.ERROR)
fun versionFor(dependencyNotation: CharSequence): String {
    throw NotImplementedError("use versions.versionFor")
}

@Deprecated("use versions extension", ReplaceWith("versions.versionFor(versionKey)"))
fun Project.versionFor(versionKey: String): String {
    // This function is overloaded to allow named parameter usage in Kotlin.
    // However, no check is performed here because we cannot detect if
    // the function wasn't called with named argument.
    val config = RefreshVersionsConfigHolder.getConfigForProject(this)
    return retrieveVersionFor(config = config, dependencyNotationOrVersionKey = versionKey)
}

@Deprecated("use versions extension", ReplaceWith("versions.versionFor(dependencyNotation)"))
fun Project.versionFor(dependencyNotation: CharSequence): String {
    // This function is overloaded to allow named parameter usage in Kotlin.
    // However, no check is performed here because we cannot detect if
    // the function wasn't called with named argument.
    val config = RefreshVersionsConfigHolder.getConfigForProject(this)
    return retrieveVersionFor(config = config, dependencyNotationOrVersionKey = dependencyNotation)
}

internal fun retrieveVersionFor(config: RefreshVersionsConfig, dependencyNotationOrVersionKey: CharSequence): String {
    val isDependencyNotation = ':' in dependencyNotationOrVersionKey
    val versionKey = when {
        isDependencyNotation -> {
            require(dependencyNotationOrVersionKey.endsWith(":_")) {
                "Expects a refreshVersions compatible dependency notation with the version placeholder (_)." +
                    "\n" +
                    "If the dependency is from BoM, " +
                    "pass the BoM dependency notation itself with the version placeholder."
            }
            dependencyNotationOrVersionKey.toString().let {
                getVersionPropertyName(
                    moduleId = ModuleId(
                        group = it.substringBefore(':'),
                        name = it.substringBeforeLast(':').substringAfter(':')
                    ),
                    versionKeyReader = config.versionKeyReader
                )
            }
        }
        else -> dependencyNotationOrVersionKey.toString().also {
            require(it.startsWith("version.") || it.startsWith("plugin.")) {
                "Version keys all start with 'version.' or 'plugin.'. You need to pass the full version key."
            }
        }
    }
    return resolveVersion(
        properties = config.lastlyReadVersionsMap,
        key = versionKey
    ) ?: resolveVersion(
        properties = config.readVersionsMap(),
        key = versionKey
    ) ?: config.versionsPropertiesFile.name.let { versionsFileName ->
        val errorMessage = when {
            isDependencyNotation -> "The version of the artifact $dependencyNotationOrVersionKey requested in " +
                "versionFor call wasn't found in the $versionsFileName file.\n" +
                "Expected a value for the corresponding key: $versionKey"
            else -> "The version for the key $versionKey requested in " +
                "versionFor call wasn't found in the $versionsFileName file"
        }
        error(errorMessage)
    }
}
