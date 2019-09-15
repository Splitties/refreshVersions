package de.fayard

import de.fayard.VersionsOnlyMode.GRADLE_PROPERTIES
import de.fayard.VersionsOnlyMode.KOTLIN_OBJECT
import de.fayard.internal.BuildSrcVersionsExtensionImpl
import de.fayard.internal.Dependency
import de.fayard.internal.DependencyGraph
import de.fayard.internal.EditorConfig
import de.fayard.internal.KotlinPoetry
import de.fayard.internal.OutputFile
import de.fayard.internal.PluginConfig
import de.fayard.internal.UpdateGradleProperties
import de.fayard.internal.UpdateVersionsOnly.regenerateBuildFile
import de.fayard.internal.kotlinpoet
import de.fayard.internal.parseGraph
import de.fayard.internal.sortedBeautifullyBy
import org.gradle.api.Action
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.gradle.kotlin.dsl.getByType
import java.io.File

@Suppress("UnstableApiUsage")
open class BuildSrcVersionsTask : DefaultTask() {

    fun configure(action: Action<BuildSrcVersionsExtension>) {
        this._extension = BuildSrcVersionsExtensionImpl()
        action.execute(this._extension!!)
    }

    @Input
    @Option(description = "Update all versions, I will check git diff afterwards")
    var update: Boolean = false

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
        val outputDir = project.file(OutputFile.OUTPUTDIR.path)
        val shouldGenerateLibsKt = when(extension.versionsOnlyMode) {
            null -> true
            KOTLIN_OBJECT -> false
            else -> return
        }

        val kotlinPoetry: KotlinPoetry = kotlinpoet(parsedDependencies, dependencyGraph.gradle, extension)

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
        val updateGradleProperties = UpdateGradleProperties(extension, parsedDependencies)

        val versionsOnlyMode = when(val mode = extension.versionsOnlyMode) {
            null, KOTLIN_OBJECT -> return
            else -> mode
        }

        val dependencies = (parsedDependencies + PluginConfig.gradleVersionsPlugin)
            .sortedBeautifullyBy { it.versionName }
            .distinctBy { it.versionName }

        if (versionsOnlyMode == GRADLE_PROPERTIES) {
            updateGradleProperties.generateVersionProperties(project, dependencies)
            OutputFile.GRADLE_PROPERTIES.logFileWasModified()

        } else {
            val file = extension.versionsOnlyFile?.let { project.file(it) }
            val projectUseKotlin = project.file("build.gradle.kts").exists()
            regenerateBuildFile(file, versionsOnlyMode, dependencies, projectUseKotlin)
            if (file != null) OutputFile.logFileWasModified(file.relativeTo(project.projectDir).path, existed = true)
        }
    }

    private val dependencyGraph: DependencyGraph by lazy {
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

        return@lazy PluginConfig.readGraphFromJsonFile(jsonInput)
    }

    private val parsedDependencies: List<Dependency> by lazy {
        val useFdqnByDefault = extension().useFqqnFor.map { PluginConfig.escapeVersionsKt(it) }
        parseGraph(dependencyGraph, useFdqnByDefault + PluginConfig.MEANING_LESS_NAMES)
            .map { d -> d.maybeUpdate(update) }
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
