package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class RefreshVersionsDependenciesMappingTask: DefaultTask() {

    @InternalRefreshVersionsApi
    companion object {
        const val TASK_NAME = "refreshVersionsDependenciesMapping"
        const val DESCRIPTION = "Shows the mapping of Gradle dependencies and their typesafe accessors"
    }

    @TaskAction
    fun refreshVersionsDependenciesMapping() {
        val mapping = getArtifactNameToConstantMapping()
        println(mapping.joinToString("\n"))
    }
}
