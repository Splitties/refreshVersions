@file:JvmName("RefreshVersionsCoreSetup")

package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.extensions.isBuildSrc
import de.fayard.refreshVersions.core.internal.ArtifactVersionKeyReader
import de.fayard.refreshVersions.core.internal.clearUsedPluginsList
import de.fayard.refreshVersions.core.internal.getVersionProperties
import de.fayard.refreshVersions.core.internal.noteUsedPluginDependency
import de.fayard.refreshVersions.core.internal.resolveVersion
import de.fayard.refreshVersions.core.internal.setupVersionPlaceholdersResolving
import de.fayard.refreshVersions.core.internal.writeUsedRepositories
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.apply
import java.util.Properties

/**
 * Boostrap refreshVersions **only** (without the dependencies plugin).
 *
 * Supports both Kotlin and Groovy Gradle DSL.
 *
 * // **`settings.gradle.kts`**
 * ```kotlin
 * import de.fayard.refreshVersions.core.bootstrap
 *
 * buildscript {
 *     dependencies.classpath("de.fayard.refreshVersions:refreshVersions:VERSION")
 * }
 *
 * settings.bootstrapRefreshVersionsCore()
 * ```
 *
 * // **`settings.gradle`**
 * ```groovy
 * import de.fayard.refreshVersions.core.RefreshVersionsCoreSetup
 * buildscript {
 *     dependencies.classpath("de.fayard:refreshVersions:VERSION")
 * }
 *
 * RefreshVersionsCoreSetup.bootstrap(settings)
 * ```
 */
@JvmOverloads
@JvmName("bootstrap")
fun Settings.bootstrapRefreshVersionsCore(artifactVersionKeyRules: List<String> = emptyList()) {
    setupRefreshVersions(settings, artifactVersionKeyRules)
}

@Suppress("DeprecatedCallableAddReplaceWith") // ReplaceWith has a bug that removes the line of code in this case...
@Deprecated("Replace with bootstrapRefreshVersionsCore")
fun Settings.setupVersionPlaceholdersResolving(): Unit = bootstrapRefreshVersionsCore()


internal val artifactVersionKeyReader: ArtifactVersionKeyReader get() = privateArtifactVersionKeyReader

/**
 * Sets up a resolution strategy for the plugins that does the following:
 * For each plugin, tries to find the corresponding version declared in `versions.properties`, and uses it.
 *
 * The expected property key is the id of the plugin, prefixed with `plugin.` (e.g. `plugin.io.fabric`), excepted for
 * the Kotlin and Android gradle plugins where `version.kotlin` and `plugin.android` are used.
 *
 * Note that the properties can alias to another one from `version.properties` (hard limit is 5 property redirects),
 * you just need to put the key of the property you want to use (e.g. `plugin.some-plugin=plugin.android`).
 *
 * This function also sets up the module for the Android and Fabric (Crashlytics) Gradle plugins, so you can avoid the
 * buildscript classpath configuration boilerplate.
 */
private fun setupRefreshVersions(settings: Settings, artifactVersionKeyRules: List<String>) {
    settings.clearUsedPluginsList()

    val relativePath = "versions.properties".let { if (settings.isBuildSrc) "../$it" else it }
    val versionProperties = settings.rootDir.resolve(relativePath)
    versionProperties.createNewFile() // Creates the file if it doesn't exist yet

    privateArtifactVersionKeyReader = ArtifactVersionKeyReader.fromRules(filesContent = artifactVersionKeyRules)

    @Suppress("unchecked_cast")
    setupPluginsVersionsResolution(
        settings = settings,
        properties = Properties().apply { load(versionProperties.reader()) } as Map<String, String>
    )

    settings.gradle.setupVersionPlaceholdersResolving(versionProperties = settings.getVersionProperties())

    settings.gradle.rootProject {
        apply<RefreshVersionsCorePlugin>()
    }
}

private lateinit var privateArtifactVersionKeyReader: ArtifactVersionKeyReader

private fun setupPluginsVersionsResolution(
    settings: Settings,
    properties: Map<String, String>
) {
    settings.pluginManagement {
        writeUsedRepositories(settings)
        resolutionStrategy.eachPlugin {
            val pluginId = requested.id.id
            if (pluginId == "de.fayard.refreshVersions") {
                return@eachPlugin // Already in the buildscript with a defined version that will be used.
            }
            val pluginNamespace = requested.id.namespace ?: ""
            val versionKey = when {
                pluginNamespace.startsWith("org.jetbrains.kotlin") -> "version.kotlin"
                pluginNamespace.startsWith("com.android") -> "plugin.android"
                else -> "plugin.$pluginId"
            }
            val version = resolveVersion(properties, versionKey) ?: return@eachPlugin
            when {
                pluginNamespace.startsWith("com.android") -> {
                    val dependencyNotation = "com.android.tools.build:gradle:$version"
                    settings.noteUsedPluginDependency(dependencyNotation)
                    useModule(dependencyNotation)
                }
                pluginId == "io.fabric" -> {
                    val dependencyNotation = "io.fabric.tools:gradle:$version"
                    settings.noteUsedPluginDependency(dependencyNotation)
                    useModule(dependencyNotation)
                }
                else -> {
                    settings.noteUsedPluginDependency("$pluginId:$pluginId.gradle.plugin:$version")
                    useVersion(version)
                }
            }
        }
    }
}
