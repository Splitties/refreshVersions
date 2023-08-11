package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.extensions.gradle.moduleId
import de.fayard.refreshVersions.core.extensions.gradle.npmModuleId
import de.fayard.refreshVersions.core.extensions.gradle.tryExtractingSimpleVersion
import de.fayard.refreshVersions.core.internal.VersionManagementKind.Match
import de.fayard.refreshVersions.core.internal.VersionManagementKind.NoMatch
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency

@InternalRefreshVersionsApi
fun Dependency.hasHardcodedVersion( //TODO: Remove code calling this and remove this.
    versionMap: Map<String, String>,
    versionKeyReader: ArtifactVersionKeyReader
): Boolean = versionManagementKind(
    versionMap = versionMap,
    versionKeyReader = versionKeyReader,
    versionsCatalogLibraries = emptySet(),
    versionsCatalogPlugins = emptySet(),
    dependenciesFromVersionFor = emptyList()
) == NoMatch

internal sealed class VersionManagementKind {
    sealed class Match : VersionManagementKind() {

        sealed class VersionsCatalog : Match() {
            /** Matching version constraint doesn't guarantee the version catalog entry is actually used. */
            object MatchingVersionConstraint : VersionsCatalog()
        }

        /** The versions.properties file for now, possibly versions catalogs in the future. */
        sealed class VersionsFile : Match() {

            /** Matching version constraint doesn't guarantee the version catalog entry is actually used. */
            object MatchingPluginVersion : VersionsFile()

            object VersionPlaceholder : VersionsFile()

            object UsedInVersionFor : VersionsFile()
        }

    }
    object NoMatch : VersionManagementKind()
}

internal fun Dependency.versionManagementKind(
    versionMap: Map<String, String>,
    versionKeyReader: ArtifactVersionKeyReader,
    versionsCatalogLibraries: Collection<MinimalExternalModuleDependency>,
    versionsCatalogPlugins: Set<PluginDependencyCompat>,
    dependenciesFromVersionFor: List<Dependency>,
): VersionManagementKind = when {
    this in dependenciesFromVersionFor -> Match.VersionsFile.UsedInVersionFor
    version == versionPlaceholder -> Match.VersionsFile.VersionPlaceholder
    this is ExternalDependency && versionPlaceholder in this.versionConstraint.rejectedVersions -> {
        Match.VersionsFile.VersionPlaceholder
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
                            versionsCatalogLibraries = versionsCatalogLibraries,
                            versionsCatalogPlugins = versionsCatalogPlugins
                        ) -> Match.VersionsCatalog.MatchingVersionConstraint
                        else -> NoMatch
                    }
                    version -> Match.VersionsFile.MatchingPluginVersion
                    else -> NoMatch
                }
            }
            else -> NoMatch
        }
    }
    else -> when {
        hasVersionInVersionCatalog(
            versionsCatalogLibraries = versionsCatalogLibraries
        ) -> Match.VersionsCatalog.MatchingVersionConstraint
        else -> NoMatch
    }
}

private fun Dependency.hasVersionInVersionCatalog(
    versionsCatalogLibraries: Collection<MinimalExternalModuleDependency>,
    versionsCatalogPlugins: Set<PluginDependencyCompat> = emptySet()
): Boolean {
    when {
        this::class.simpleName == "NpmDependency" -> {
            return versionsCatalogLibraries.any {
                val moduleId = npmModuleId()
                it.module.group == (moduleId.group ?: "<unscoped>") && it.module.name == moduleId.name
                    && it.versionConstraint.tryExtractingSimpleVersion() == version
            }
        }

        this is ExternalDependency -> {
            val matchingLib = versionsCatalogLibraries.any {
                it.module.group == group && it.module.name == name
                    && it.versionConstraint == versionConstraint
            }
            if (matchingLib) return true

            if (name.endsWith(".gradle.plugin").not()) return false

            val pluginId = name.substringBeforeLast(".gradle.plugin")
            return versionsCatalogPlugins.any { it.pluginId == pluginId && it.version == versionConstraint }
        }

        else -> return false
    }
}
