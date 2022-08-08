package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.ModuleId
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
        val allMavenDependencies: List<ModuleId.Maven> = DependenciesDocGenerator.hardCoded()
        val dependenciesAndDocs: List<DocumentedDependency> = DependenciesDocGenerator.dependenciesAndDocs(allMavenDependencies)

        DependenciesDocGenerator.updateChangeLogsUrlsInFiles(dependenciesAndDocs)
        OutputFile.VERSIONS_PROPERTIES.logFileWasModified()
        OutputFile.GRADLE_VERSIONS_CATALOG.logFileWasModified()

        val markdown = DependenciesDocGenerator.generateMarkdown(dependenciesAndDocs)
        OutputFile.DEPENDENCIES.writeText(markdown)
        OutputFile.DEPENDENCIES.logFileWasModified()
    }
}
