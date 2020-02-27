package de.fayard.dependencies

import de.fayard.dependencies.internal.promptProjectSelection
import de.fayard.dependencies.internal.runInteractiveMigrationToDependenciesConstants
import kotlinx.coroutines.*
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

open class RefreshVersionsDependenciesMigrationTask : DefaultTask() {

    @TaskAction
    fun taskActionMigrate() {
        check(project == project.rootProject) { "This task is designed to run on root project only." }
        while (Thread.currentThread().isInterrupted.not()) {
            val selectedProject = promptProjectSelection(project) ?: return
            runBlocking {
                runInteractiveMigrationToDependenciesConstants(selectedProject)
            }
        }
    }
}
