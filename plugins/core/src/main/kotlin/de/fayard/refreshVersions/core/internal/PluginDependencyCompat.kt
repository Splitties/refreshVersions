package de.fayard.refreshVersions.core.internal

import org.gradle.api.artifacts.VersionConstraint
import org.gradle.plugin.use.PluginDependency

/**
 * The [PluginDependency] class has been introduced in Gradle 7.2, but we support Gradle 6.8,
 * so we have this compatibility class, so that we can reference its type safely even on older Gradle versions.
 */
internal data class PluginDependencyCompat(
    val pluginId: String,
    val version: VersionConstraint
) {
    constructor(pluginDependency: PluginDependency) : this(
        pluginId = pluginDependency.pluginId,
        version = pluginDependency.version
    )
}
