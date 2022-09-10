package de.fayard.buildSrcLibs

import com.squareup.kotlinpoet.FileSpec
import de.fayard.buildSrcLibs.internal.*
import de.fayard.refreshVersions.core.addMissingEntriesInVersionsProperties
import de.fayard.refreshVersions.core.internal.Case
import de.fayard.refreshVersions.core.internal.MEANING_LESS_NAMES
import de.fayard.refreshVersions.core.internal.OutputFile
import de.fayard.refreshVersions.core.internal.checkModeAndNames
import de.fayard.refreshVersions.core.internal.computeAliases
import de.fayard.refreshVersions.core.internal.findDependencies
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

@Suppress("UnstableApiUsage")
open class BuildSrcLibsTask : DefaultTask() {

    @TaskAction
    fun addMissingEntries() {
        addMissingEntriesInVersionsProperties(project)
    }

    @TaskAction
    fun taskActionInitializeBuildSrc() {
        OutputFile.checkWhichFilesExist()
        project.file(OutputFile.OUTPUT_DIR.path).also {
            if (it.isDirectory.not()) it.mkdirs()
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
    fun taskUpdateLibsKt() {
        val outputDir = OutputFile.OUTPUT_DIR.file

        val allDependencies = project.findDependencies()
        val resolvedUseFqdn = allDependencies.computeAliases(
            configured = emptyList(),
            byDefault = MEANING_LESS_NAMES
        )
        val deps = allDependencies.checkModeAndNames(resolvedUseFqdn, Case.snake_case)

        val libsFile: FileSpec = kotlinpoet(deps)

        libsFile.writeTo(outputDir)
        OutputFile.logFileWasModified(OutputFile.LIBS.path, OutputFile.LIBS.existed)
    }

}

