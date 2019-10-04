pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }

    /**
     * This `resolutionStrategy` allows plugin versions to be configured from `gradle.properties
     * The convention is simply
     *     plugin.$PLUGINID=$PLUGIN_VERSION
     * To check what happen, you can set the property
     *     resolutionStrategyConfig=verbose
     **/
    val resolutionStrategyConfig: String? by extra
    resolutionStrategy.eachPlugin {
        val property = "plugin.${requested.id.id}"
        if (extra.has(property) && resolutionStrategyConfig != "false") {
            val version = extra.get(property) as String
            if (resolutionStrategyConfig == "verbose") println("ResolutionStrategy used version=$version from property=$property")
            useVersion(version)
        }
    }
}
rootProject.name = "sample-kotlin"
includeBuild("../plugin")

