package de.fayard.versions

import de.fayard.versions.extensions.isRootProject
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.internal.artifacts.dependencies.AbstractDependency

internal fun Project.readDependenciesUsedInBuildSrc(): Sequence<Dependency> {
    require(isRootProject)
    val file = rootDir.resolve("buildSrc").resolve(usedDependenciesFilePath)
    if (file.exists().not()) return emptySequence()
    return file.readLines().asSequence().map { line -> line.parseDependency() }
}

/**
 * Writes used dependencies to a file so it can be used later in another rootProject.
 */
internal fun Project.writeUsedDependencies() {
    require(isRootProject)

    val file = file(usedDependenciesFilePath)
    if (file.exists().not()) {
        file.parentFile.mkdirs()
        file.createNewFile()
    }

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

private const val usedDependenciesFileName: String = "refreshVersions_used_dependencies.txt"
private const val usedDependenciesFilePath: String = "build/$usedDependenciesFileName"

private fun String.parseDependency(): Dependency = ParsedDependency(this)

private class ParsedDependency(val dependencyNotation: String) : AbstractDependency() {

    override fun getGroup() = group
    override fun getName() = name
    override fun getVersion(): String? = version

    override fun contentEquals(dependency: Dependency): Boolean = throw UnsupportedOperationException()
    override fun copy(): Dependency = ParsedDependency(dependencyNotation)

    private val group = dependencyNotation.substringBefore(':').unwrappedNullableValue()
    private val name = dependencyNotation.substringAfter(':').substringBefore(':')
    private val version = dependencyNotation.substringAfterLast(':').unwrappedNullableValue()

    private fun String.unwrappedNullableValue(): String? = if (this == "null") null else this
}
