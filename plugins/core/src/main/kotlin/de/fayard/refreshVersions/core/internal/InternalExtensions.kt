package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.extensions.gradle.isGradlePlugin
import de.fayard.refreshVersions.core.extensions.gradle.moduleIdentifier
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalDependency

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
