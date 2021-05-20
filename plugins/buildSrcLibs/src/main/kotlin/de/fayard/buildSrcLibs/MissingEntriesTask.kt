package de.fayard.buildSrcLibs

import de.fayard.refreshVersions.core.internal.OutputFile
import de.fayard.refreshVersions.core.internal.ArtifactVersionKeyReader
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.core.internal.getVersionPropertyName
import de.fayard.refreshVersions.core.internal.hasHardcodedVersion
import de.fayard.refreshVersions.core.internal.versions.writeMissingEntriesInVersionProperties
import de.fayard.refreshVersions.internal.countDependenciesWithHardcodedVersions
import de.fayard.refreshVersions.internal.shouldBeIgnored
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.tasks.TaskAction

@Suppress("UnstableApiUsage")
open class MissingEntriesTask : DefaultTask() {


    @TaskAction
    fun initVersionsProperties() {
        require(project == project.rootProject) { "Expected a rootProject but got $project" }
        OutputFile.checkWhichFilesExist(project.rootDir)
        val configurationsWithHardcodedDependencies = project.findHardcodedDependencies()

        val versionsMap = RefreshVersionsConfigHolder.readVersionsMap()
        val versionKeyReader = RefreshVersionsConfigHolder.versionKeyReader
        val newEntries: Map<String, ExternalDependency> = findMissingEntries(
            configurations = configurationsWithHardcodedDependencies,
            versionsMap = versionsMap,
            versionKeyReader = versionKeyReader
        )

        writeMissingEntriesInVersionProperties(newEntries)
        OutputFile.VERSIONS_PROPERTIES.logFileWasModified()
        Thread.sleep(1000)
    }

}

internal fun Project.findHardcodedDependencies(): List<Configuration> {
    val versionsMap = RefreshVersionsConfigHolder.readVersionsMap()
    val projectsWithHardcodedDependenciesVersions: List<Project> = rootProject.allprojects.filter {
        it.countDependenciesWithHardcodedVersions(versionsMap) > 0
    }

    return projectsWithHardcodedDependenciesVersions.flatMap { project ->
        project.configurations.filterNot { configuration ->
            configuration.shouldBeIgnored() || 0 == configuration.countDependenciesWithHardcodedVersions(
                versionsMap = versionsMap,
                versionKeyReader = RefreshVersionsConfigHolder.versionKeyReader
            )
        }
    }
}


internal fun findMissingEntries(
    configurations: List<Configuration>,
    versionsMap: Map<String, String>,
    versionKeyReader: ArtifactVersionKeyReader
): Map<String, ExternalDependency> {

    val dependencyMap = configurations.flatMap { configuration ->
        configuration.dependencies
            .filterIsInstance<ExternalDependency>()
            .filter { it.hasHardcodedVersion(versionsMap, versionKeyReader) && it.version != null }
            .map { dependency: ExternalDependency ->
                val versionKey = getVersionPropertyName(dependency.module, versionKeyReader)
                versionKey to dependency
            }
    }
    val newEntries = dependencyMap
        .groupBy({ it.first }, { it.second })
        .filter { entry -> entry.key !in versionsMap }
        .mapValues { entry -> entry.value.maxBy { it.version!! }!! }

    return newEntries
}

