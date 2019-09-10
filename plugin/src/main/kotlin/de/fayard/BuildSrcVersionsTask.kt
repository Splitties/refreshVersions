package de.fayard

import org.gradle.api.Action
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType

@Suppress("UnstableApiUsage")
open class BuildSrcVersionsTask : DefaultTask() {

    fun configure(action: Action<BuildSrcVersionsExtension>) {
        this.extension = BuildSrcVersionsExtensionImpl()
        action.execute(this.extension!!)
    }

    @Input @Optional
    var extension: BuildSrcVersionsExtension? = null

    @TaskAction
    fun taskAction() {
        val extension : BuildSrcVersionsExtension = extension ?: project.extensions.getByType()
        if (extension.indent == PluginConfig.DEFAULT_INDENT) {
            extension.indent = EditorConfig.findIndentForKotlin(project.file("buildSrc/src/main/kotlin")) ?: "  "
        }
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
            val gradleDeps = listOf(
                Dependency(version = dependencyGraph.gradle.running.version, group = PluginConfig.GRADLE_CURRENT_VERSION, versionName = PluginConfig.GRADLE_CURRENT_VERSION),
                Dependency(version = dependencyGraph.gradle.current.version, group = PluginConfig.GRADLE_LATEST_VERSION, versionName = PluginConfig.GRADLE_LATEST_VERSION)
            )
            onSingleActionMode(dependencies + gradleDeps, extension)
            return
        }

        val outputDir = project.file(OutputFile.OUTPUTDIR.path)

        if (generatesAll) {
            checkIfFilesExistInitiallyAndCreateThem(project)
        }

        val sortedDependencies = when {
            OutputFile.VERSIONS.existed -> dependencies
            else -> dependencies.sortedByDescending { it.versionName.length }
        }

        val kotlinPoetry: KotlinPoetry = kotlinpoet(sortedDependencies, dependencyGraph.gradle, extension)

        if (generatesAll) {
            kotlinPoetry.Libs.writeTo(outputDir)
            OutputFile.logFileWasModified(OutputFile.LIBS.path, OutputFile.LIBS.existed)
        }

        kotlinPoetry.Versions.writeTo(outputDir)
        OutputFile.logFileWasModified(OutputFile.VERSIONS.path, OutputFile.VERSIONS.existed)

        val file = extension.versionsOnlyFile?.let { project.file(it) }
        if (file != null && generatesAll.not()) {
            project.file(OutputFile.VERSIONS.path).renameTo(file)
            OutputFile.logFileWasModified(file.relativeTo(project.projectDir).path, existed = true)
        }
    }


    fun onSingleActionMode(dependencies: List<Dependency>, extension: BuildSrcVersionsExtension) {
        val file = extension.versionsOnlyFile?.let { project.file(it) }
        val projectUseKotlin = project.file("build.gradle.kts").exists()
        regenerateBuildFile(file, extension, dependencies, projectUseKotlin)
        if (file != null)  OutputFile.logFileWasModified(file.relativeTo(project.projectDir).path, existed = true)

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
                OutputFile.logFileWasModified(outputFile.path, outputFile.existed)
            }
        }
    }


}
