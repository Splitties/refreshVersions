package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.addMissingEntriesInVersionsProperties
import de.fayard.refreshVersions.core.internal.Case
import de.fayard.refreshVersions.core.internal.Deps
import de.fayard.refreshVersions.core.internal.Library
import de.fayard.refreshVersions.core.internal.MEANING_LESS_NAMES
import de.fayard.refreshVersions.core.internal.OutputFile
import de.fayard.refreshVersions.core.internal.checkModeAndNames
import de.fayard.refreshVersions.core.internal.computeAliases
import de.fayard.refreshVersions.core.internal.findDependencies
import de.fayard.refreshVersions.core.internal.VersionCatalogs.versionsCatalog
import de.fayard.refreshVersions.core.internal.UsedPluginsHolder
import de.fayard.refreshVersions.core.internal.VersionCatalogs
import de.fayard.refreshVersions.core.internal.VersionCatalogs.LIBS_VERSIONS_TOML
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.gradle.util.GradleVersion

@Suppress("UnstableApiUsage")
open class RefreshVersionsCatalogTask : DefaultTask() {

    @Input
    @Option(option = "versions", description = "Add the versions in $LIBS_VERSIONS_TOML")
    var withVersions: Boolean = false

    @Input
    @Option(option = "all", description = "Add all libraries in $LIBS_VERSIONS_TOML")
    var withAllLibraries: Boolean = false

    @TaskAction
    fun refreshVersionsCatalogAction() {
        // Check Gradle version
        if (VersionCatalogs.isSupported().not()) {
            throw GradleException(
                """
                |Gradle versions catalogs are not supported in ${GradleVersion.current()}
                |Upgrade Gradle with this command
                |     ./gradlew wrapper --gradle-version ${VersionCatalogs.NEEDS_GRADLE_VERSION.version}
            """.trimMargin()
            )
        }

        // Update versions.properties
        addMissingEntriesInVersionsProperties(project)

        // Generate LIBS_VERSIONS_TOML
        val catalog = OutputFile.GRADLE_VERSIONS_CATALOG

        val builtInDependencies = getArtifactNameToConstantMapping()
            .map { Library(it.group, it.artifact, "_") }

        val allDependencies: List<Library> = project.findDependencies()

        val dependenciesToUse = if (withAllLibraries) {
            allDependencies
        } else {
            allDependencies.filter { it.copy(version = "_") !in builtInDependencies }
        }

        val plugins = UsedPluginsHolder.usedPluginsWithoutEntryInVersionsFile +
                UsedPluginsHolder.read().map { it.first }

        val versionCatalogAliases: List<String> = dependenciesToUse.computeAliases(
            configured = emptyList(),
            byDefault = MEANING_LESS_NAMES
        )

        val deps: Deps = dependenciesToUse.checkModeAndNames(versionCatalogAliases, Case.`kebab-case`)

        val currentText = if (catalog.existed) catalog.readText() else ""
        val newText = versionsCatalog(deps, currentText, withVersions, plugins)
        catalog.writeText(newText)
        catalog.logFileWasModified()

        println("""
            You can now automatically migrate your build.gradle/build.gradle.kts file with the command:
            
                $ANSI_GREEN./gradlew refreshVersionsMigrate$ANSI_RESET
        """.trimIndent())
    }
}

