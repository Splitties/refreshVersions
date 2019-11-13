package de.fayard.versions

import de.fayard.versions.extensions.isRootProject
import org.gradle.api.Project

internal const val usedDependenciesFileName: String = "refreshVersions_used_dependencies.txt"
internal const val usedDependenciesFilePath: String = "build/$usedDependenciesFileName"

/**
 * Writes used dependencies to a file so it can be used later in another rootProject.
 */
internal fun Project.writeUsedDependencies() {
    require(isRootProject)

    val file = file(usedDependenciesFilePath)
    if (file.exists().not()) file.createNewFile()

    val allDependencies = configurations.asSequence()
        .flatMap { it.allDependencies.asSequence() }
        .distinctBy { it.group + ':' + it.name }

    file.bufferedWriter().use { writer ->
        allDependencies.forEach { dependency ->
            writer.append(dependency.group)
            writer.append(':')
            writer.append(dependency.name)
            writer.append(':')
            writer.append(dependency.version)
            writer.appendln()
        }
    }
}
