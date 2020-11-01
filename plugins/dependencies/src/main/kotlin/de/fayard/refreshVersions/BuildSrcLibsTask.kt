package de.fayard.refreshVersions

import com.squareup.kotlinpoet.FileSpec
import de.fayard.refreshVersions.internal.Dependency
import de.fayard.internal.OutputFile
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

        project.file(OutputFile.OUTPUTDIR.path).also {
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

        val newEntries = findMissingEntries(configurationsWithHardcodedDependencies)

        writeMissingEntriesInVersionProperties(newEntries)
        OutputFile.VERSIONS_PROPERTIES.logFileWasModified()
        Thread.sleep(1000)
    }



    @TaskAction
    fun taskUpdateLibsKt() {
        val outputDir = project.file(OutputFile.OUTPUTDIR.path)

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
            val projectName = name
            // println("Configurations: " + configurations.map { it.name })
            allDependencies += (configurations + buildscript.configurations)
                //.filter { it.name in PluginConfig.knownConfigurations }
                .flatMap {
                    val configurationName = "$projectName:${it.name}"
                    it.allDependencies.filterIsInstance<ExternalDependency>()
                        .mapNotNull {
                            when {
                                it.group == null -> null
                                else -> Dependency(it.group, it.name, it.version ?: "none")
                            }
                        }
                }
        }
        return allDependencies.distinctBy { d -> d.groupModule() }
    }
}

internal fun Project.findHardcodedDependencies(): List<Pair<Project, Configuration>> {
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
        }.map { configuration -> project to configuration }
    }
}


internal fun findMissingEntries(configurations: List<Pair<Project, Configuration>>): Map<String, ExternalDependency> {

    val versionsProperties = RefreshVersionsConfigHolder.readVersionsMap()

    val versionKeyReader = RefreshVersionsConfigHolder.versionKeyReader

    val dependencyMap = configurations.flatMap { (project, configuration) ->
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
