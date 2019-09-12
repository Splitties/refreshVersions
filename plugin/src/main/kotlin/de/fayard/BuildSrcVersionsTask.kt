package de.fayard

import org.gradle.api.Action
import org.gradle.api.DefaultTask
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.internal.artifacts.dependencies.DefaultExternalModuleDependency
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.withType

@Suppress("UnstableApiUsage")
open class BuildSrcVersionsTask : DefaultTask() {

    init {
        group = "Help"
        description = "Update buildSrc/src/main/kotlin/{Versions.kt,Libs.kt}"
        dependsOn(":dependencyUpdates")
        outputs.upToDateWhen { false }
    }

    fun configure(action: Action<BuildSrcVersionsExtension>) {
        this.extension = BuildSrcVersionsExtensionImpl()
        action.execute(this.extension!!)
    }

    @Input @Optional @Transient
    var extension: BuildSrcVersionsExtension? = null

    @TaskAction
    fun taskAction() {
        generateProjectProperties()

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

        val kotlinPoetry: KotlinPoetry = kotlinpoet(dependencies, dependencyGraph.gradle, extension)

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

    private fun generateProjectProperties() {
        if (PluginConfig.supportSettingPluginVersions().not()) return
        val dependencies: List<DefaultExternalModuleDependency> = project.allprojects.flatMap {
            val classpath: Configuration = it.buildscript.configurations.named("classpath").get()
            classpath.allDependencies.withType()
        }
        val sortedDependencies = dependencies
            .sortedBeautifullyBy { it.group }
            .distinctBy { it.group }

        val file = project.file("gradle.properties")
        if (!file.exists()) file.createNewFile()

        val existingLines = file.readLines().filterNot {
            it.startsWith("plugin.") || it in  PluginConfig.PLUGIN_NFORMATION_START + PluginConfig.PLUGIN_INFORMATION_END
        }
        val newLines = sortedDependencies.map { it ->
            "plugin.${it.group}=${it.version}"
        }
        val newFileContent = PluginConfig.PLUGIN_NFORMATION_START + newLines + existingLines + PluginConfig.PLUGIN_INFORMATION_END
        file.writeText(newFileContent.joinToString(separator = "\n"))
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
