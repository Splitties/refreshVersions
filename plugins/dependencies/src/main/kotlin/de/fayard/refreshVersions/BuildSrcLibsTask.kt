package de.fayard.refreshVersions

import com.squareup.kotlinpoet.FileSpec
import de.fayard.refreshVersions.core.internal.ArtifactVersionKeyReader
import de.fayard.refreshVersions.internal.Dependency
import de.fayard.refreshVersions.internal.OutputFile
import de.fayard.refreshVersions.internal.PluginConfig
import de.fayard.refreshVersions.internal.checkModeAndNames
import de.fayard.refreshVersions.internal.kotlinpoet
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
open class BuildSrcLibsTask : DefaultTask() {

    @TaskAction
    fun taskActionInitializeBuildSrc() {

        project.file(OutputFile.OUTPUT_DIR.path).also {
            if (it.isDirectory.not()) it.mkdirs()
        }
        for (output in OutputFile.values()) {
            output.existed = output.fileExists(project)
        }
        val initializationMap = mapOf(
            OutputFile.BUILD to PluginConfig.INITIAL_BUILD_GRADLE_KTS,
            OutputFile.GIT_IGNORE to PluginConfig.INITIAL_GITIGNORE
        )
        for ((outputFile, initialContent) in initializationMap) {
            if (outputFile.existed.not()) {
                project.file(outputFile.path).writeText(initialContent)
                OutputFile.logFileWasModified(outputFile.path, outputFile.existed)
            }
        }
        OutputFile.VERSIONS_KT.run {
            if (existed) {
                project.file(path).delete()
                logFileWasModified(delete = true)
            }
        }
    }

    @TaskAction
    fun initVersionsProperties() {
        require(project == project.rootProject) { "Expected a rootProject but got $project" }
        val  configurationsWithHardcodedDependencies = project.findHardcodedDependencies()

        val versionsProperties = RefreshVersionsConfigHolder.readVersionsMap()
        val versionKeyReader = RefreshVersionsConfigHolder.versionKeyReader
        val newEntries: Map<String, ExternalDependency> =
            findMissingEntries(configurationsWithHardcodedDependencies, versionsProperties, versionKeyReader)

        writeMissingEntriesInVersionProperties(newEntries)
        OutputFile.VERSIONS_PROPERTIES.logFileWasModified()
        Thread.sleep(1000)
    }

    @TaskAction
    fun taskUpdateLibsKt() {
        val outputDir = project.file(OutputFile.OUTPUT_DIR.path)

        val allDependencies = findDependencies()
        val resolvedUseFqdn = PluginConfig.computeUseFqdnFor(allDependencies, emptyList(), PluginConfig.MEANING_LESS_NAMES)
        val deps = allDependencies.checkModeAndNames(resolvedUseFqdn)

        val libsFile: FileSpec = kotlinpoet(deps)

        libsFile.writeTo(outputDir)
        OutputFile.logFileWasModified(OutputFile.LIBS.path, OutputFile.LIBS.existed)
    }

    private fun findDependencies(): List<Dependency> {
        val allDependencies = mutableListOf<Dependency>()
        project.allprojects {
            (configurations + buildscript.configurations)
                .flatMapTo(allDependencies) { configuration ->
                    configuration.allDependencies
                        .filterIsInstance<ExternalDependency>()
                        .filter {
                            @Suppress("SENSELESS_COMPARISON")
                            it.group != null
                        }
                        .map { dependency ->
                            Dependency(dependency.group, dependency.name, dependency.version ?: "none")
                        }
                }
        }
        return allDependencies.distinctBy { d -> d.groupModule() }
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
    versionsProperties: Map<String, String>,
    versionKeyReader: ArtifactVersionKeyReader
): Map<String, ExternalDependency> {

    val dependencyMap = configurations.flatMap { configuration ->
        configuration.dependencies
            .filterIsInstance<ExternalDependency>()
            .filter { it.hasHardcodedVersion(versionsProperties, versionKeyReader) && it.version != null }
            .map { dependency: ExternalDependency ->
                val versionKey = getVersionPropertyName(dependency.module, versionKeyReader)
                versionKey to dependency
            }
    }
    val newEntries = dependencyMap
        .groupBy({it.first}, {it.second})
        .filter { entry -> entry.key !in versionsProperties }
        .mapValues { entry -> entry.value.maxBy { it.version!! }!! }

    return newEntries
}
