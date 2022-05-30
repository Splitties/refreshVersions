package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import org.gradle.api.artifacts.ArtifactRepositoryContainer
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.initialization.Settings
import org.gradle.api.internal.artifacts.dependencies.AbstractDependency

internal object UsedPluginsTracker {

    fun clearFor(settings: Settings) {
        if (settings.isBuildSrc) buildSrcHolder = null else projectHolder = null
    }

    fun noteUsedPluginDependency(
        settings: Settings,
        dependencyNotation: String,
        repositories: ArtifactRepositoryContainer
    ) {
        editHolder(settings) { holder ->
            holder.usedPluginDependencies += UsedPluginDependency(
                dependencyNotation = dependencyNotation,
                repositories = repositories
            )
        }
    }

    fun read(): Sequence<Pair<Dependency, ArtifactRepositoryContainer>> {
        return usedPluginDependencies.asSequence().map {
            ConfigurationLessDependency(it.dependencyNotation) to it.repositories
        }
    }

    fun pluginHasNoEntryInVersionsFile(settings: Settings, dependency: ExternalDependency) {
        editHolder(settings) { holder ->
            holder.usedPluginsWithoutEntryInVersionsFile.add(dependency)
        }
    }

    val usedPluginsWithoutEntryInVersionsFile: List<ExternalDependency>
        get() = listOfNotNull(projectHolder, buildSrcHolder).flatMap { it.usedPluginsWithoutEntryInVersionsFile }

    private val usedPluginDependencies: List<UsedPluginDependency>
        get() = listOfNotNull(projectHolder, buildSrcHolder).flatMap { it.usedPluginDependencies }

    private val lock = Any()

    private inline fun editHolder(settings: Settings, block: (Holder) -> Unit) {
        synchronized(lock) {
            val holder: Holder = if (settings.isBuildSrc) {
                if (buildSrcHolder?.settings != settings) {
                    buildSrcHolder = Holder(settings)
                }
                buildSrcHolder!!
            } else {
                if (projectHolder?.settings != settings) {
                    projectHolder = Holder(settings)
                }
                projectHolder!!
            }
            block(holder)
        }
    }

    private var projectHolder: Holder? = null
    private var buildSrcHolder: Holder? = null

    private data class UsedPluginDependency(
        val dependencyNotation: String,
        val repositories: ArtifactRepositoryContainer
    )

    private class Holder(val settings: Settings) {
        val usedPluginDependencies = mutableListOf<UsedPluginDependency>()
        val usedPluginsWithoutEntryInVersionsFile = mutableListOf<ExternalDependency>()
    }

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

        override fun toString() = "$group:$name:$version"
    }
}
