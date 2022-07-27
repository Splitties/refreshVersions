package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId
import org.gradle.api.artifacts.Dependency
import org.gradle.api.internal.artifacts.dependencies.AbstractDependency

internal class ConfigurationLessDependency(
    private val group: String,
    private val name: String,
    private val version: String?
) : AbstractDependency() {

    constructor(
        moduleId: ModuleId.Maven,
        version: String
    ) : this(
        group = moduleId.group,
        name = moduleId.name,
        version = version
    )

    companion object {
        operator fun invoke(dependencyNotation: String): ConfigurationLessDependency {
            val beforeFirstColon = dependencyNotation.substringBefore(':')
            val afterFirstColon = dependencyNotation.substringAfter(':')
            val name = afterFirstColon.substringBefore(':')
            val version = if (afterFirstColon == name) null else afterFirstColon.substringAfterLast(':')
            return ConfigurationLessDependency(
                group = beforeFirstColon,
                name = name,
                version = version
            )
        }
    }

    override fun getGroup() = group
    override fun getName() = name
    override fun getVersion() = version

    override fun contentEquals(dependency: Dependency): Boolean = throw UnsupportedOperationException()

    override fun copy(): Dependency = ConfigurationLessDependency(
        group = group,
        name = name,
        version = version
    )

    override fun toString(): String = if (version == null) "$group:$name" else "$group:$name:$version"
}
