package de.fayard

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType
import java.io.File

@Suppress("UnstableApiUsage")
open class BuildSrcVersionsTask : DefaultTask() {

    @Input
    var jsonInputPath = PluginConfig.BENMANES_REPORT_PATH

    @TaskAction
    fun taskAction() {
        val extension : BuildSrcVersionsExtension = project.extensions.getByType()
        println("Configuration: $extension")
        OutputFile.configure(extension)

        val jsonInput = project.file(jsonInputPath)

        val dependencyGraph = PluginConfig.readGraphFromJsonFile(jsonInput)

        val useFdqnByDefault = extension.useFdqnFor.map(::escapeName)

        val dependencies: List<Dependency> = parseGraph(dependencyGraph, useFdqnByDefault + PluginConfig.MEANING_LESS_NAMES)

        if (extension.versionsOnlyMode != null) {
            onSingleActionMode(dependencies.distinctBy { it.versionName }, extension)
            return
        }


        val outputDir = project.file(OutputFile.OUTPUTDIR.path).also {
            if (!it.isDirectory) it.mkdirs()
        }

        checkIfFilesExistInitially(project)

        val initializationMap = mapOf(
            OutputFile.BUILD to PluginConfig.INITIAL_BUILD_GRADLE_KTS,
            OutputFile.GIT_IGNORE to PluginConfig.INITIAL_GITIGNORE
        )

        for ((outputFile, initialContent) in initializationMap) {
            if (outputFile.existed.not()) {
                project.file(outputFile.path).writeText(initialContent)
                outputFile.logFileWasModified()
            }
        }


        val kotlinPoetry: KotlinPoetry = kotlinpoet(dependencies, dependencyGraph.gradle, extension)

        kotlinPoetry.Libs.writeTo(outputDir)
        OutputFile.LIBS.logFileWasModified()

        kotlinPoetry.Versions.writeTo(outputDir)
        OutputFile.VERSIONS.logFileWasModified()
    }


    fun onSingleActionMode(dependencies: List<Dependency>, extension: BuildSrcVersionsExtension) {

    }

    fun checkIfFilesExistInitially(project: Project) {
        for (output in OutputFile.values()) {
            output.existed = output.fileExists(project)
        }
    }


}
