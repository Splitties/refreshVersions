package de.fayard.versions.internal

import de.fayard.versions.extensions.isRootProject
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.initialization.Settings
import org.gradle.api.internal.artifacts.dependencies.AbstractDependency
import java.lang.Appendable

internal fun Project.readPluginsAndBuildSrcDependencies(): Sequence<Dependency> {
    require(isRootProject)
    val files = sequenceOf(
        rootDir.resolve("buildSrc").resolve(getUsedDependenciesFilePath(Type.Project)),
        rootDir.resolve("buildSrc").resolve(getUsedDependenciesFilePath(Type.PluginManagement)),
        rootDir.resolve(getUsedDependenciesFilePath(Type.PluginManagement))
    )
    return files.flatMap { file ->
        if (file.exists().not()) emptySequence() else {
            file.readLines().asSequence().map { line -> line.parseDependency() }
        }
    }
}

/**
 * Writes used dependencies to a file so it can be used later in another rootProject.
 */
internal fun Project.writeUsedDependencies() {
    require(isRootProject)

    val file = file(getUsedDependenciesFilePath(Type.Project))
    if (file.exists().not()) {
        file.parentFile.mkdirs()
        file.createNewFile()
    }

    val allDependencies = configurations.asSequence()
        .flatMap { it.allDependencies.asSequence() }
        .distinctBy { it.group + ':' + it.name }

    file.bufferedWriter().use { writer ->
        allDependencies.forEach { dependency ->
            writer.appendDependency(
                group = dependency.group,
                name = dependency.name,
                version = dependency.version
            )
        }
    }
}

internal fun Settings.clearUsedPluginsList() {
    val file = settings.rootDir.resolve(getUsedDependenciesFilePath(Type.PluginManagement))
    file.delete()
}

/** Expects a Gradle plugins dependency (artifact style, not plugin id) with its version. */
internal fun Settings.noteUsedPluginDependency(dependencyNotation: String) {
    synchronized(lock) {
        val file = settings.rootDir.resolve(getUsedDependenciesFilePath(Type.PluginManagement))
        val newContent = buildString {
            if (file.exists()) append(file.readText())
            appendln(dependencyNotation)
        }
        file.writeText(newContent)
    }
}

private val lock = Any()

private fun Appendable.appendDependency(group: String?, name: String, version: String?) {
    append(group); append(':'); append(name); append(':'); append(version); appendln()
}

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

    init {
        because(becauseRefreshVersions)
    }

    private fun String.unwrappedNullableValue(): String? = if (this == "null") null else this
}

private fun getUsedDependenciesFileName(type: Type): String {
    return "refreshVersions_used_dependencies${type.qualifier}.txt"
}

private fun getUsedDependenciesFilePath(type: Type): String {
    return "build/${getUsedDependenciesFileName(type)}"
}
