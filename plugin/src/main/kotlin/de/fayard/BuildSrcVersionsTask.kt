package de.fayard

import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType

@Suppress("UnstableApiUsage")
open class BuildSrcVersionsTask : DefaultTask() {

    @Input @Optional
    var extension: BuildSrcVersionsExtension? = null

    @TaskAction
    fun taskAction() {
        val extension : BuildSrcVersionsExtension = extension ?: project.extensions.getByType()
        println("""
            |Plugin configuration: $extension
            |See documentation at ${PluginConfig.issue53PluginConfiguration}
            |
        """.trimMargin())
        OutputFile.configure(extension)

        val jsonInput = project.file(PluginConfig.BENMANES_REPORT_PATH)

        val dependencyGraph = PluginConfig.readGraphFromJsonFile(jsonInput)

        val useFdqnByDefault = extension.useFdqnFor.map(::escapeName)

        val dependencies: List<Dependency> = parseGraph(dependencyGraph, useFdqnByDefault + PluginConfig.MEANING_LESS_NAMES)

        val generatesAll = extension.versionsOnlyMode != VersionsOnlyMode.KOTLIN_OBJECT

        if (generatesAll && extension.versionsOnlyMode != null) {
            onSingleActionMode(dependencies, extension)
            return
        }

        val outputDir = project.file(OutputFile.OUTPUTDIR.path)

        if (generatesAll) {
            checkIfFilesExistInitiallyAndCreateThem(project)
        }

        val kotlinPoetry: KotlinPoetry = kotlinpoet(dependencies, dependencyGraph.gradle, extension)

        if (generatesAll) {
            kotlinPoetry.Libs.writeTo(outputDir)
            OutputFile.LIBS.logFileWasModified()
        }

        kotlinPoetry.Versions.writeTo(outputDir)
        OutputFile.VERSIONS.logFileWasModified()

        val file = extension.versionsOnlyFile?.let { project.file(it) }
        if (file != null && generatesAll.not()) {
            project.file(OutputFile.VERSIONS.path).renameTo(file)
            println("File $file updated")
        }
    }


    fun onSingleActionMode(dependenciesWithDupes: List<Dependency>, extension: BuildSrcVersionsExtension) {
        val dependencies = dependenciesWithDupes.distinctBy { it.versionName }
        val file = extension.versionsOnlyFile?.let { project.file(it) }
        regenerateBuildFile(file, extension, dependencies)
    }

    fun checkIfFilesExistInitiallyAndCreateThem(project: Project) {
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
                outputFile.logFileWasModified()
            }
        }
    }


}
