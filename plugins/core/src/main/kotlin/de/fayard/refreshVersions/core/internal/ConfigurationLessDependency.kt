package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.artifacts.VersionConstraint

internal data class ConfigurationLessDependency(
    private val group: String?,
    private val name: String,
    private val version: String?,
    val versionConstraint: VersionConstraint? = null,
    val isNpm: Boolean = false
) : Dependency {

    constructor(
        moduleId: ModuleId.Maven,
        version: String
    ) : this(
        group = moduleId.group,
        name = moduleId.name,
        version = version
    )

    constructor(
        otherDependency: Dependency
    ) : this(
        group = otherDependency.group,
        name = otherDependency.name,
        version = otherDependency.version,
        versionConstraint = if (otherDependency is ExternalDependency) otherDependency.versionConstraint else null,
        isNpm = otherDependency::class.simpleName == "NpmDependency"
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

    override fun contentEquals(dependency: Dependency): Boolean = when (dependency) {
        is ConfigurationLessDependency -> dependency == this
        else -> dependency.contentEquals(this)
    }

    override fun copy(): Dependency = copy(group = group)
    override fun getReason(): String? = null
    override fun because(reason: String?) = throw IllegalAccessException("Not editable")

    override fun toString(): String = if (version == null) "$group:$name" else "$group:$name:$version"
}
