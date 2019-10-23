import java.util.Properties

/**
 * Included in "settings.gradle.kts" via
 *    apply(from = "plugins.gradle.kts")
 *
 * See the Gradle guide on plugin management
 * https://docs.gradle.org/current/userguide/plugins.html#sec:plugin_management
 ***/

pluginManagement {

    /**
     * This `resolutionStrategy` allows plugin versions to be configured from
     *     `versions.properties
     * The convention is simply
     *     plugin.$PLUGINID=$PLUGIN_VERSION
     * To check what happen, you can set the property
     *     resolutionStrategyConfig=verbose
     **/
    val resolutionStrategyConfig: String? by extra
    if (resolutionStrategyConfig == "false" || file("versions.properties").canRead().not()) return@pluginManagement
    val androidPluginIds = listOf("com.android.application", "com.android.library")
    val kotlinPluginIds = listOf("org.jetbrains.kotlin.android", "org.jetbrains.kotlin.kapt", "kotlin-android-extensions")
    @Suppress("UNCHECKED_CAST")
    val properties: Map<String, String> = Properties().apply {
        load(file("versions.properties").reader())
    } as Map<String, String>
    require("module.kotlin" in properties && "module.android" in properties) { "version.properties MUST contain module.android=... and module.kotlin=...." }
    resolutionStrategy.eachPlugin {
        val pluginId = requested.id.id
        val version = properties["plugin.$pluginId"]
        val message = when {
            pluginId in kotlinPluginIds -> {
                val module = "org.jetbrains.kotlin:kotlin-gradle-plugin:${properties["module.kotlin"]}"
                useModule(module)
                "ResolutionStrategy used module=$module for plugin=$pluginId"
            }
            pluginId in androidPluginIds -> {
                val module = "com.android.tools.build:gradle:${properties["module.android"]}"
                useModule(module)
                "ResolutionStrategy used module=$module for plugin=$pluginId"
            }
            version != null -> {
                useVersion(version)
                "ResolutionStrategy used version=$version for plugin=$pluginId"
            }
            else -> "ResolutionStrategy did not find a version for $pluginId"
        }
        if (resolutionStrategyConfig == "verbose") println(message)
    }
}
