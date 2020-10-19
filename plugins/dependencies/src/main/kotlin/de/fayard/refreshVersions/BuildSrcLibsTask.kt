package de.fayard.refreshVersions

import com.squareup.kotlinpoet.FileSpec
import de.fayard.internal.Dependency
import de.fayard.internal.OutputFile
import de.fayard.internal.PluginConfig
import de.fayard.internal.checkModeAndNames
import de.fayard.internal.kotlinpoet
import org.gradle.api.DefaultTask
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
        OutputFile.VERSIONS_PROPERTIES.logFileWasModified()
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


    fun findDependencies(): List<Dependency> {
        val allDependencies = mutableListOf<Dependency>()
        project.allprojects {
            val projectName = name
            // println("Configurations: " + configurations.map { it.name })
            allDependencies += (configurations + buildscript.configurations)
                //.filter { it.name in PluginConfig.knownConfigurations }
                .flatMap {
                    val configurationName = "$projectName:${it.name}"
                    it.allDependencies.filterIsInstance<ExternalDependency>()
                        .map {
                            Dependency(it.group!!, it.name, it.version ?: "none")
                        }
                }
        }
        return allDependencies.distinctBy { d -> d.groupModule() }
    }
}
