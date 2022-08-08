package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.extensions.gradle.moduleId
import de.fayard.refreshVersions.core.internal.VersionManagementKind.*
import de.fayard.refreshVersions.core.internal.VersionManagementKind.Match.*
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency

@InternalRefreshVersionsApi
fun Dependency.hasHardcodedVersion(
    versionMap: Map<String, String>,
    versionKeyReader: ArtifactVersionKeyReader
): Boolean = versionManagementKind(versionMap, versionKeyReader, emptySet(), emptySet()) == NoMatch

internal sealed class VersionManagementKind {
    sealed class Match : VersionManagementKind() {

        /** Matching version constraint doesn't guarantee the version catalog entry is actually used. */
        object MatchingVersionConstraintInVersionCatalog : Match()

        /** Matching version constraint doesn't guarantee the version catalog entry is actually used. */
        object MatchingPluginVersion : Match()

        object VersionPlaceholder : Match()
    }
    object NoMatch : VersionManagementKind()
}

internal fun Dependency.versionManagementKind(
    versionMap: Map<String, String>,
    versionKeyReader: ArtifactVersionKeyReader,
    versionsCatalogLibraries: Collection<MinimalExternalModuleDependency>,
    versionsCatalogPlugins: Set<PluginDependencyCompat>,
): VersionManagementKind = when {
    version == versionPlaceholder -> VersionPlaceholder
    this is ExternalDependency && versionPlaceholder in this.versionConstraint.rejectedVersions -> {
        VersionPlaceholder
    }
    name.endsWith(".gradle.plugin") -> {
        when (val moduleId = moduleId()) {
            is ModuleId.Maven -> {
                val versionFromProperty = resolveVersion(
                    properties = versionMap,
                    key = getVersionPropertyName(moduleId, versionKeyReader)
                )
                when (versionFromProperty) {
                    null -> when {
                        hasVersionInVersionCatalog(
                            versionsCatalogMapping = versionsCatalogLibraries,
                            versionsCatalogLibraries = versionsCatalogPlugins
                        ) -> MatchingVersionConstraintInVersionCatalog
                        else -> NoMatch
                    }
                    version -> MatchingPluginVersion
                    else -> NoMatch
                }
            }
            else -> NoMatch
        }
    }
    else -> when {
        hasVersionInVersionCatalog(
            versionsCatalogMapping = versionsCatalogLibraries
        ) -> MatchingVersionConstraintInVersionCatalog
        else -> NoMatch
    }
}

private fun Dependency.hasVersionInVersionCatalog(
    versionsCatalogMapping: Collection<MinimalExternalModuleDependency>,
    versionsCatalogLibraries: Set<PluginDependencyCompat> = emptySet()
): Boolean {
    if (this !is ExternalDependency) return false

    val matchingLib = versionsCatalogMapping.any {
        it.module.group == group && it.module.name == name && it.versionConstraint == versionConstraint
    }
    if (matchingLib) return true

    if (name.endsWith(".gradle.plugin").not()) return false

    val pluginId = name.substringBeforeLast(".gradle.plugin")
    return versionsCatalogLibraries.any { it.pluginId == pluginId && it.version == versionConstraint }
}
