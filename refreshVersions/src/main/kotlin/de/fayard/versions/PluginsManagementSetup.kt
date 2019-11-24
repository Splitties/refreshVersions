package de.fayard.versions

import de.fayard.versions.extensions.isBuildSrc
import de.fayard.versions.internal.resolveVersion
import de.fayard.versions.internal.writeUsedRepositories
import org.gradle.api.initialization.Settings
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
fun Settings.setupVersionPlaceholdersResolving() {
    val thisPluginVersion = getPluginVersion(this)
    pluginManagement {
        writeUsedRepositories(settings)
        val relativePath = "versions.properties".let { if (isBuildSrc) "../$it" else it }
        val versionProperties = rootDir.resolve(relativePath)
        if (versionProperties.exists().not()) return@pluginManagement
        @Suppress("UNCHECKED_CAST")
        val properties: Map<String, String> = Properties().apply {
            load(versionProperties.reader())
        } as Map<String, String>
        resolutionStrategy.eachPlugin {
            val pluginId = requested.id.id
            if (pluginId == "de.fayard.refreshVersions") {
                useVersion(thisPluginVersion)
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

private fun getPluginVersion(settings: Settings): String = runCatching {
    @Suppress("UnstableApiUsage")
    settings.buildscript.configurations.getByName("classpath").allDependencies.single {
        it.group == "de.fayard.refreshVersions" && it.name == "de.fayard.refreshVersions.gradle.plugin"
    }.version
}.onFailure {
    println(it)
    it.printStackTrace()
}.getOrNull() ?: "latest.release"
