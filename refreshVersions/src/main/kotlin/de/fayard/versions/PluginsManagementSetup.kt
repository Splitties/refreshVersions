package de.fayard.versions

import de.fayard.internal.PluginConfig
import org.gradle.kotlin.dsl.SettingsScriptApi
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.provideDelegate
import java.util.Properties

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
fun SettingsScriptApi.setupVersionPlaceholdersResolving() {
    pluginManagement {
        val resolutionStrategyConfig: String? by extra // Allows disabling the resolutionStrategy if ever needed.
        val versionProperties = file("versions.properties")
        if (resolutionStrategyConfig == "false" || versionProperties.canRead().not()) return@pluginManagement

        @Suppress("UNCHECKED_CAST")
        val properties: Map<String, String> = Properties().apply {
            load(versionProperties.reader())
        } as Map<String, String>
        resolutionStrategy.eachPlugin {
            val pluginId = requested.id.id
            if (pluginId == "de.fayard.refreshVersions") {
                useVersion(PluginConfig.PLUGIN_VERSION)
                return@eachPlugin
            }
            val pluginNamespace = requested.id.namespace ?: ""
            val versionKey = when {
                pluginNamespace.startsWith("org.jetbrains.kotlin") -> "version.kotlin"
                pluginNamespace.startsWith("com.android") -> "plugin.android"
                else -> "plugin.$pluginId"
            }
            val version = resolveVersion(properties, versionKey) ?: return@eachPlugin
            when {
                pluginNamespace.startsWith("com.android") -> useModule("com.android.tools.build:gradle:$version")
                pluginId == "io.fabric" -> useModule("io.fabric.tools:gradle:$version")
                else -> useVersion(version)
            }
        }
    }
}
