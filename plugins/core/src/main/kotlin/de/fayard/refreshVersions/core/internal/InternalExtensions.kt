package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.extensions.isBuildSrc
import de.fayard.refreshVersions.core.extensions.isGradlePlugin
import de.fayard.refreshVersions.core.extensions.moduleIdentifier
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.initialization.Settings
import java.io.File

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
fun Settings.defaultVersionsPropertiesFile(): File {
    val relativePath = "versions.properties".let { if (settings.isBuildSrc) "../$it" else it }
    return settings.rootDir.resolve(relativePath)
}
