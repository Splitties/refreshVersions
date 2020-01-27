package de.fayard.versions

import org.gradle.api.initialization.Settings
import java.io.File
import java.util.*

fun Settings.bootstrapRefreshVersions() : Unit = Bootstrap.bootstrapRefreshVersions(this)

object Bootstrap {
    @JvmStatic
    fun bootstrapRefreshVersions(settings: Settings) {
        val pluginProperties = getVersionProperties(settings.rootDir.resolve("versions.properties"))
            .filter { it.key.startsWith("plugin.") }
        println("versions.properties: $pluginProperties")
        settings.pluginManagement {
            plugins {
                for ((key, value) in pluginProperties) {
                    id(key.substringAfter("plugin.")).version(value)
                }
            }
        }
    }



    private fun getVersionProperties(versionsPropertiesFile: File): Map<String, String> {
        if (versionsPropertiesFile.canRead().not()) return emptyMap()
        return mutableMapOf<String, String>().also { map ->
            // Read from versions.properties
            Properties().also { properties ->
                properties.load(versionsPropertiesFile.reader())
            }.forEach { (k, v) -> if (k is String && v is String) map[k] = v }
        }
    }
}
