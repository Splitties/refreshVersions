package de.fayard

import de.fayard.internal.PluginConfig
import org.gradle.api.artifacts.ComponentSelection



interface RefreshVersionsExtension {

    /** If you prefer the plugin to update the versions and check afterwards the git diff **/
    fun alwaysUpdateVersions()

    /**
     * Which versions should be included in the list of available updates?
     * See https://github.com/ben-manes/gradle-versions-plugin
     *
     * Default:
     *
     * ```kotlin
     *  rejectVersionIf {
     *    isNonStable(candidate.version)
     *  }
     *  ```
     *
     * Possible usage:
     * ```kotlin
     *  rejectVersionIf {
     *    isNonStable(candidate.version) && !isNonStable(currentVersion)
     *  }
     *  ```
     ***/
    fun rejectVersionIf(filter: ComponentFilter)

    /**
     * There is no standard on how to name stable and unstable versions
     * This version is a good starting point but you can define your own.
     * Implementation: see [PluginConfig]
     */
    fun isNonStable(version: String): Boolean

    /** Shortcut for [isNonStable(version).not()] **/
    fun isStable(version: String): Boolean

    var versionsMapping: MutableMap<String, String>

}
