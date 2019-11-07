package de.fayard

import com.github.benmanes.gradle.versions.updates.resolutionstrategy.ComponentFilter
import de.fayard.internal.PluginConfig

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

    /**
     * Some dependency have a meaningful name, like "guava".
     * Others are called "core" or "compiler" and don't make sense out of context.
     * Some of those meaningless names are detected by default, see [PluginConfig.MEANING_LESS_NAMES]
     * You can add your own if necessary
     ***/
    fun useFqdnFor(vararg dependencyName: String)

    // TODO: remove
    var alignVersionsForGroups: MutableList<String>
    // TODO: do something with it
    var versionsMapping: MutableMap<String, String>

    /**
     * See [versionsOnlyMode]
     * Probably "gradle.properties" for GRADLE_PROPERTIES, "build.gradle" for GROOVY_DEF, ...
     */
    var propertiesFile: String?

    /**
     * orderBy = OrderBy.GROUP_AND_ALPHABETICAL to override the default behavior
     * https://github.com/jmfayard/buildSrcVersions/issues/65 **/
    var orderBy: OrderBy

}

enum class OrderBy {
    GROUP_AND_LENGTH, GROUP_AND_ALPHABETICAL
}
