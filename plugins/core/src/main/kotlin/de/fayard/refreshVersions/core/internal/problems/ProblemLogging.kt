package de.fayard.refreshVersions.core.internal.problems

import org.gradle.api.logging.LogLevel
import org.gradle.api.logging.Logger

internal fun Logger.log(problem: Problem<Any>) {
    val logLevel = when (problem.level) {
        Problem.Level.Warning -> LogLevel.WARN
        Problem.Level.Error -> LogLevel.ERROR
        Problem.Level.Error.Fatal -> LogLevel.ERROR
    }
    val levelLetter = when (problem.level) {
        Problem.Level.Warning -> 'w'
        is Problem.Level.Error -> 'e'
    }
    val affectedFile = problem.affectedFile
    val message = problem.errorMessage
    when (affectedFile) {
        null -> log(logLevel, message)
        else -> {
            // This log level format is recognized by IntelliJ IDEA.
            log(logLevel, "$levelLetter: ${affectedFile.path}:\n$message")
        }
    }
}
