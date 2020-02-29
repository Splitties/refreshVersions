package de.fayard.versions.internal

import de.fayard.versions.artifactVersionKeyReader
import de.fayard.versions.extensions.isGradlePlugin
import de.fayard.versions.extensions.moduleIdentifier
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalDependency
import java.util.Properties

@InternalRefreshVersionsApi
fun Dependency.hasHardcodedVersion(
    versionProperties: Map<String, String>,
    versionKeyReader: ArtifactVersionKeyReader
): Boolean = isManageableVersion(versionProperties, versionKeyReader).not()

@InternalRefreshVersionsApi
fun Dependency.isManageableVersion(
    versionProperties: Map<String, String>,
    versionKeyReader: ArtifactVersionKeyReader
): Boolean {
    return when {
        this is ExternalDependency && versionPlaceholder in this.versionConstraint.rejectedVersions -> true
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

@InternalRefreshVersionsApi
fun @Suppress("unused") Project.retrieveVersionKeyReader(): ArtifactVersionKeyReader {
    return artifactVersionKeyReader
}

@InternalRefreshVersionsApi
fun Project.getVersionProperties(
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
