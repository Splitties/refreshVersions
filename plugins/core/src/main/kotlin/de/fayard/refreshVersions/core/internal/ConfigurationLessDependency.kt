package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId
import org.gradle.api.artifacts.Dependency
import org.gradle.api.internal.artifacts.dependencies.AbstractDependency

internal class ConfigurationLessDependency private constructor(
    private val group: String,
    private val name: String,
    private val version: String
) : AbstractDependency() {

    constructor(
        moduleId: ModuleId.Maven,
        version: String
    ) : this(
        group = moduleId.group,
        name = moduleId.name,
        version = version
    )

    constructor(dependencyNotationWithVersion: String) : this(
        group = dependencyNotationWithVersion.substringBefore(':'),
        name = dependencyNotationWithVersion.substringAfter(':').substringBefore(':'),
        version = dependencyNotationWithVersion.substringAfterLast(':')
    )

    override fun getGroup() = group
    override fun getName() = name
    override fun getVersion(): String = version

    override fun contentEquals(dependency: Dependency): Boolean = throw UnsupportedOperationException()

    override fun copy(): Dependency = ConfigurationLessDependency(
        group = group,
        name = name,
        version = version
    )

    override fun toString() = "$group:$name:$version"
}
