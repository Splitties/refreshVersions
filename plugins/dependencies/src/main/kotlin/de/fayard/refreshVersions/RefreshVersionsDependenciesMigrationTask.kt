package de.fayard.refreshVersions

import de.fayard.refreshVersions.internal.promptProjectSelection
import de.fayard.refreshVersions.internal.runInteractiveMigrationToDependenciesConstants
import de.fayard.refreshVersions.core.internal.cli.AnsiColor
import kotlinx.coroutines.*
import org.gradle.api.DefaultTask
import org.gradle.api.logging.configuration.ConsoleOutput
import org.gradle.api.tasks.TaskAction

open class RefreshVersionsDependenciesMigrationTask : DefaultTask() {

    @TaskAction
    fun taskActionMigrate() {
        check(project == project.rootProject) { "This task is designed to run on root project only." }
        require(project.gradle.startParameter.consoleOutput == ConsoleOutput.Plain) {
            "Please, run the task with the option " +
                AnsiColor.MAGENTA.background +
                AnsiColor.WHITE.boldHighIntensity +
                "--console=plain" +
                AnsiColor.RESET
        }
        while (Thread.currentThread().isInterrupted.not()) {
            val selectedProject = promptProjectSelection(project)
                ?: return
            runBlocking {
                runInteractiveMigrationToDependenciesConstants(selectedProject)
            }
        }
    }
}
