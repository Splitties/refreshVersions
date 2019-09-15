package de.fayard

import com.github.benmanes.gradle.versions.updates.resolutionstrategy.ComponentFilter
import de.fayard.internal.PluginConfig

interface BuildSrcVersionsExtension {

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

    /**
     * Some dependency have a meaningful name, like "guava".
     * Others are called "core" or "compiler" and don't make sense out of context.
     * Some of those meaningless names are detected by default, see [PluginConfig.MEANING_LESS_NAMES]
     * You can add your own if necessary
     ***/
    fun useFqdnFor(vararg dependencyName: String)

    /**
     * `renameLibs = Deps` if you think that Deps.kt is much better than Libs.kt
     ***/
    var renameLibs: String

    /**
     * `renameVersions = "V"` if you think that V.kt is much better than Versionskt
     ***/
    var renameVersions: String

    /**
     * Tabs or Spaces? You choose.
     * Even better, define an https://editorconfig.org file.
     * It will be used if detected.
     */
    var indent: String

    /**
     * Possible values: KOTLIN_VAL, KOTLIN_OBJECT, GROOVY_DEF, GROOVY_EXT, GRADLE_PROPERTIES
     * See https://github.com/jmfayard/buildSrcVersions/tree/master/sample-versionsOnlyMode
     **/
    var versionsOnlyMode: VersionsOnlyMode?

    /**
     * See [versionsOnlyMode]
     * Probably "gradle.properties" for GRADLE_PROPERTIES, "build.gradle" for GROOVY_DEF, ...
     */
    var versionsOnlyFile: String?


}
