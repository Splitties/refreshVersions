package de.fayard

import de.fayard.UpdateVersionsOnly.regenerateBuildFile
import de.fayard.VersionsOnlyMode.GRADLE_PROPERTIES
import de.fayard.VersionsOnlyMode.KOTLIN_OBJECT
import org.gradle.api.Action
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.kotlin.dsl.getByType
import java.io.File

@Suppress("UnstableApiUsage")
open class BuildSrcVersionsTask : DefaultTask() {

    fun configure(action: Action<BuildSrcVersionsExtension>) {
        this._extension = BuildSrcVersionsExtensionImpl()
        action.execute(this._extension!!)
    }


    @TaskAction
    fun initializeBuildSrc() {
        val extension: BuildSrcVersionsExtensionImpl = extension()
        if (extension.shouldInitializeBuildSrc().not()) return

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


    @TaskAction
    fun updateBuildSrc() {
        val extension: BuildSrcVersionsExtensionImpl = extension()
        val (dependencyGraph: DependencyGraph, dependencies: List<Dependency>) = parsedGradleVersionsPluginReport
        val outputDir = project.file(OutputFile.OUTPUTDIR.path)
        val shouldGenerateLibsKt = when(extension.versionsOnlyMode) {
            null -> true
            KOTLIN_OBJECT -> false
            else -> return
        }

        val kotlinPoetry: KotlinPoetry = kotlinpoet(dependencies, dependencyGraph.gradle, extension)

        if (shouldGenerateLibsKt) {
            kotlinPoetry.Libs.writeTo(outputDir)
            OutputFile.logFileWasModified(OutputFile.LIBS.path, OutputFile.LIBS.existed)
        }

        kotlinPoetry.Versions.writeTo(outputDir)
        OutputFile.logFileWasModified(OutputFile.VERSIONS.path, OutputFile.VERSIONS.existed)

        val renamedVersionsKt: File? = when(extension.versionsOnlyMode to extension.versionsOnlyFile) {
            null to null -> null
            KOTLIN_OBJECT to null -> null
            else -> project.file(extension.versionsOnlyFile!!)
        }

        if (renamedVersionsKt != null) {
            project.file(OutputFile.VERSIONS.path).renameTo(renamedVersionsKt)
            OutputFile.logFileWasModified(renamedVersionsKt.relativeTo(project.projectDir).path, existed = true)
        }
    }

    @TaskAction
    fun versionsOnlyMode() {
        val extension: BuildSrcVersionsExtensionImpl = extension()

        val versionsOnlyMode = when(val mode = extension.versionsOnlyMode) {
            null, KOTLIN_OBJECT -> return
            else -> mode
        }

        val dependencies = parsedGradleVersionsPluginReport.second
            .sortedBeautifullyBy { it.versionName }
            .distinctBy { it.versionName }

        if (versionsOnlyMode == GRADLE_PROPERTIES) {
            UpdateGradleProperties(extension, dependencies).generateVersionProperties(project, dependencies)
            OutputFile.GRADLE_PROPERTIES.logFileWasModified()

        } else {
            val file = extension.versionsOnlyFile?.let { project.file(it) }
            val projectUseKotlin = project.file("build.gradle.kts").exists()
            regenerateBuildFile(file, versionsOnlyMode, dependencies, projectUseKotlin)
            if (file != null) OutputFile.logFileWasModified(file.relativeTo(project.projectDir).path, existed = true)
        }
    }



    @TaskAction
    fun generateProjectProperties() {
        if (PluginConfig.supportSettingPluginVersions().not()) return

        val extension: BuildSrcVersionsExtension = extension()
        UpdateGradleProperties(extension, parsedGradleVersionsPluginReport.second).generateProjectProperties(project)
    }


    val parsedGradleVersionsPluginReport: Pair<DependencyGraph, List<Dependency>>  by lazy {
        val extension: BuildSrcVersionsExtensionImpl = extension()

        println(
            """
                |Plugin configuration: $extension
                |See documentation at ${PluginConfig.issue53PluginConfiguration}
                |
            """.trimMargin()
        )
        OutputFile.configure(extension)

        val jsonInput = project.file(PluginConfig.BENMANES_REPORT_PATH)

        val dependencyGraph = PluginConfig.readGraphFromJsonFile(jsonInput)

        val useFdqnByDefault = extension.useFqqnFor.map(::escapeName)

        val dependencies: List<Dependency> = parseGraph(dependencyGraph, useFdqnByDefault + PluginConfig.MEANING_LESS_NAMES)
        Pair(dependencyGraph, dependencies)
    }


    @Input @Optional @Transient
    var _extension: BuildSrcVersionsExtension? = null

    private fun extension(): BuildSrcVersionsExtensionImpl {
        val extension: BuildSrcVersionsExtension = _extension ?: project.extensions.getByType()
        if (extension.indent == PluginConfig.DEFAULT_INDENT) {
            extension.indent = EditorConfig.findIndentForKotlin(project.file("buildSrc/src/main/kotlin")) ?: "  "
        }
        return extension as BuildSrcVersionsExtensionImpl
    }


    fun BuildSrcVersionsExtension.shouldInitializeBuildSrc() = when(versionsOnlyMode) {
        null -> true
        KOTLIN_OBJECT -> false
        else -> false
    }

}
