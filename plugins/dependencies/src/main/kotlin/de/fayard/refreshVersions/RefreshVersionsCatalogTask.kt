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
import de.fayard.refreshVersions.core.internal.Toml.versionsCatalog
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
    @Option(option = "versions", description = "Add the versions in gradle/libs.versions.toml")
    var withVersions: Boolean = false

    @Input
    @Option(option = "all", description = "Add all libraries in gradle/libs.versions.toml")
    var withAllLibraries: Boolean = false

    @TaskAction
    fun refreshVersionsCatalogAction() {
        // Check Gradle version
        if (currentGradleVersion < versionWithVersionsCatalog) {
            throw GradleException(
                """
                |Gradle versions catalogs are not supported in $currentGradleVersion
                |Upgrade Gradle with this command
                |     ./gradlew wrapper --gradle-version ${versionWithVersionsCatalog.version}
            """.trimMargin()
            )
        }

        // Update versions.properties
        addMissingEntriesInVersionsProperties(project)

        // Generate gradle/libs.versions.toml
        val catalog = OutputFile.GRADLE_VERSIONS_CATALOG

        val builtInDependencies = getArtifactNameToConstantMapping()
            .map { Library(it.group, it.artifact, "_") }
            .toSet()

        val allDependencies: List<Library> = project.findDependencies()

        val dependenciesToUse = if (withAllLibraries) {
            allDependencies
        } else {
            allDependencies.filter { it.copy(version = "_") !in builtInDependencies }
        }

        val versionCatalogAliases: List<String> = dependenciesToUse.computeAliases(
            configured = emptyList(),
            byDefault = MEANING_LESS_NAMES
        )

        val deps: Deps = dependenciesToUse.checkModeAndNames(versionCatalogAliases, Case.`kebab-case`)

        val currentText = if (catalog.existed) catalog.readText(project) else ""
        val newText = versionsCatalog(deps, currentText, withVersions)
        catalog.writeText(newText, project)
        catalog.logFileWasModified()

        println("""
            You can now automatically migrate your build.gradle/build.gradle.kts file with the command:
            
                $ANSI_GREEN./gradlew refreshVersionsMigrate$ANSI_RESET
        """.trimIndent())
    }

    companion object {
        val currentGradleVersion: GradleVersion = GradleVersion.current()
        val versionWithVersionsCatalog: GradleVersion = GradleVersion.version("7.4")
    }
}

