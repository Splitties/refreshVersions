package de.fayard.refreshVersions.core.internal.problems

import java.io.File

internal data class Problem<out T>(
    val level: Level,
    val issue: T,
    val errorMessage: String = issue.toString(),
    val affectedFile: File? = null
) {

    sealed class Level {
        object Warning : Level()
        sealed class Error : Level() {
            companion object : Error()
            object Fatal : Error()
        }
    }
}
