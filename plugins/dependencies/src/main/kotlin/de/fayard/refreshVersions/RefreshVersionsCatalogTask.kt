package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.addMissingEntriesInVersionsProperties
import de.fayard.refreshVersions.core.internal.Case
import de.fayard.refreshVersions.core.internal.Deps
import de.fayard.refreshVersions.core.internal.Library
import de.fayard.refreshVersions.core.internal.MEANING_LESS_NAMES
import de.fayard.refreshVersions.core.internal.OutputFile
import de.fayard.refreshVersions.core.internal.checkModeAndNames
import de.fayard.refreshVersions.core.internal.computeUseFqdnFor
import de.fayard.refreshVersions.core.internal.findDependencies
import de.fayard.refreshVersions.internal.Toml.versionsCatalog
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.tasks.TaskAction
import org.gradle.util.GradleVersion

@Suppress("UnstableApiUsage")
open class RefreshVersionsCatalogTask : DefaultTask() {

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

        val nonBuiltInDependencies = allDependencies
            .filter { it.copy(version = "_") !in builtInDependencies }

        val resolvedUseFqdn: List<String> = computeUseFqdnFor(
            libraries = nonBuiltInDependencies,
            configured = emptyList(),
            byDefault = MEANING_LESS_NAMES
        )

        val deps: Deps = nonBuiltInDependencies.checkModeAndNames(resolvedUseFqdn, Case.`kebab-case`)
        val currentText = if (catalog.existed) catalog.readText(project) else ""
        val newText = versionsCatalog(deps, currentText)
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

