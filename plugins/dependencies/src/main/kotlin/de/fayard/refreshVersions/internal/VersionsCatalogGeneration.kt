package de.fayard.refreshVersions.internal

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.addMissingEntriesInVersionsProperties
import de.fayard.refreshVersions.core.internal.Case
import de.fayard.refreshVersions.core.internal.Deps
import de.fayard.refreshVersions.core.internal.Library
import de.fayard.refreshVersions.core.internal.MEANING_LESS_NAMES
import de.fayard.refreshVersions.core.internal.OutputFile
import de.fayard.refreshVersions.core.internal.UsedPluginsTracker
import de.fayard.refreshVersions.core.internal.VersionsCatalogs
import de.fayard.refreshVersions.core.internal.checkModeAndNames
import de.fayard.refreshVersions.core.internal.computeAliases
import de.fayard.refreshVersions.core.internal.findDependencies
import org.gradle.api.GradleException
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.util.GradleVersion

/**
 * Returns the mapping of the generated catalog: moduleId to dependency alias.
 */
internal fun generateVersionsCatalogFromCurrentDependencies(
    project: Project,
    keepVersionsPlaceholders: Boolean,
    copyBuiltInDependencyNotationsToCatalog: Boolean
): Map<ModuleId.Maven, String> {
    if (VersionsCatalogs.isSupported().not()) {
        throw GradleException(
            """
                |Gradle versions catalogs are not supported in ${GradleVersion.current()}
                |Upgrade Gradle with this command
                |     ./gradlew wrapper --gradle-version ${VersionsCatalogs.minimumGradleVersion.version}
            """.trimMargin()
        )
    }
    // Update versions.properties
    addMissingEntriesInVersionsProperties(project)

    // Generate LIBS_VERSIONS_TOML
    val catalog = OutputFile.GRADLE_VERSIONS_CATALOG

    val builtInDependencies = getArtifactNameToConstantMapping()

    val allDependencies: List<Library> = project.findDependencies()

    val dependenciesToUse = when {
        copyBuiltInDependencyNotationsToCatalog -> allDependencies
        else -> allDependencies.filter {
            builtInDependencies.none { builtInDependency ->
                builtInDependency.group == it.group && builtInDependency.artifact == it.name
            }
        }
    }

    val plugins = UsedPluginsTracker.usedPluginsWithoutEntryInVersionsFile +
        UsedPluginsTracker.read().map { it.first }

    val versionCatalogAliases: List<String> = dependenciesToUse.computeAliases(
        configured = emptyList(),
        byDefault = MEANING_LESS_NAMES
    )

    val deps: Deps = dependenciesToUse.checkModeAndNames(versionCatalogAliases, Case.`kebab-case`)
    val dependenciesAndNames: Map<Dependency, String> = deps.names.mapKeys { it.key.toDependency() }

    val currentText = if (catalog.existed) catalog.readText() else ""
    val newText = VersionsCatalogs.generateVersionsCatalogText(
        dependenciesAndNames = dependenciesAndNames,
        currentText = currentText,
        moveVersionsToCatalog = keepVersionsPlaceholders.not(),
        plugins = plugins
    )
    catalog.writeText(newText)
    catalog.logFileWasModified()

    val versionCatalogName = VersionsCatalogs.defaultCatalogName()

    return dependenciesAndNames.asSequence().mapNotNull { (dependency, kebabCaseAlias) ->
        ModuleId.Maven(
            group = dependency.group ?: return@mapNotNull null,
            name = dependency.name
        ) to "$versionCatalogName.${kebabCaseAlias.replace('-', '.')}"
    }.toMap()
}
