package de.fayard.refreshVersions.core.internal

import org.gradle.api.artifacts.ArtifactRepositoryContainer
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.repositories.ArtifactRepository
import org.gradle.api.internal.artifacts.dependencies.AbstractDependency

internal object UsedPluginsHolder {

    fun clear() {
        usedPluginDependencies.clear()
    }

    fun noteUsedPluginDependency(
        dependencyNotation: String,
        repositories: ArtifactRepositoryContainer
    ) {
        synchronized(lock) {
            usedPluginDependencies += UsedPluginDependency(
                dependencyNotation = dependencyNotation,
                repositories = repositories
            )
        }
    }

    fun readDependencies(): List<Dependency> { //TODO: Replace with dependency + repos
        return usedPluginDependencies.map {
            ConfigurationLessDependency(it.dependencyNotation)
        }
    }

    fun readRepositories(): List<ArtifactRepository> { //TODO: Replace with dependency + repos
        return usedPluginDependencies.flatMap { it.repositories }
    }

    fun read(): Sequence<Pair<Dependency, ArtifactRepositoryContainer>> {
        return usedPluginDependencies.asSequence().map {
            ConfigurationLessDependency(it.dependencyNotation) to it.repositories
        }
    }

    private val lock = Any()

    private data class UsedPluginDependency(
        val dependencyNotation: String,
        val repositories: ArtifactRepositoryContainer
    )

    private val usedPluginDependencies = mutableListOf<UsedPluginDependency>()

    private class ConfigurationLessDependency(val dependencyNotation: String) : AbstractDependency() {

        override fun getGroup() = group
        override fun getName() = name
        override fun getVersion(): String? = version

        override fun contentEquals(dependency: Dependency): Boolean = throw UnsupportedOperationException()
        override fun copy(): Dependency = ConfigurationLessDependency(dependencyNotation)

        private val group = dependencyNotation.substringBefore(':').unwrappedNullableValue()
        private val name = dependencyNotation.substringAfter(':').substringBefore(':')
        private val version = dependencyNotation.substringAfterLast(':').unwrappedNullableValue()

        private fun String.unwrappedNullableValue(): String? = if (this == "null") null else this
    }
}
