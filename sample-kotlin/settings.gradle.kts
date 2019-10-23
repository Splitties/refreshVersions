import java.util.Properties

pluginManagement {
    repositories {
        gradlePluginPortal()
    }

    /**
     * This `resolutionStrategy` allows plugin versions to be configured from
     *     `versions.properties
     * The convention is simply
     *     plugin.$PLUGINID=$PLUGIN_VERSION
     * To check what happen, you can set the property
     *     resolutionStrategyConfig=verbose
     **/
    val resolutionStrategyConfig: String? by extra
    if (resolutionStrategyConfig == "false") return@pluginManagement

    @Suppress("UNCHECKED_CAST")
    val properties: Map<String, String> = Properties().apply {
        load(file("versions.properties").reader())
    } as Map<String, String>

    resolutionStrategy.eachPlugin {
        val property = "plugin.${requested.id.id}"
        val version = properties[property] ?: return@eachPlugin
        if (resolutionStrategyConfig == "verbose") println("ResolutionStrategy used version=$version from property=$property")
        useVersion(version)
    }
}
rootProject.name = "sample-kotlin"
includeBuild("../plugin")

