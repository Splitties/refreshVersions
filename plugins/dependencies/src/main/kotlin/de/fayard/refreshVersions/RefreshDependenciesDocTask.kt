package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.RefreshVersionsTask
import de.fayard.refreshVersions.core.internal.OutputFile
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class RefreshDependenciesDocTask : DefaultTask() {
    companion object {
        const val TASK_NAME = RefreshVersionsTask.TASK_NAME_refreshDocs
        const val FILE_NAME = "DEPENDENCIES.md"
        const val DESCRIPTION = "generate '$FILE_NAME' file documenting dependencies from the build"
    }

    @TaskAction
    fun refreshDependenciesDocsTask() {
        println("refreshDependenciesDocsTask")
        val allbuildDependencies = DependenciesDocGenerator.hardCoded()
        val generator = DependenciesDocGenerator(allbuildDependencies)
        val dependenciesAndDocs = generator.dependenciesAndDocs()

        generator.updateChangeLogsUrlsInFiles(dependenciesAndDocs)
        OutputFile.VERSIONS_PROPERTIES.logFileWasModified()
        OutputFile.GRADLE_VERSIONS_CATALOG.logFileWasModified()

        val markdown = generator.generateMarkdown(dependenciesAndDocs)
        OutputFile.DEPENDENCIES.writeText(markdown)
        OutputFile.DEPENDENCIES.logFileWasModified()
    }
}
