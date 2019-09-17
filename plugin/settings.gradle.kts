pluginManagement {
    val resolutionStrategyConfig: String? by extra
    resolutionStrategy.eachPlugin {
        val property = "plugin.${requested.id.id}".replace("-", ".")
        if (extra.has(property) && resolutionStrategyConfig != "false") {
            val version = extra.get(property) as String
            useVersion(version)
            if (resolutionStrategyConfig == "verbose") println("ResolutionStrategy used version=$version from property=$property")
        }
    }
}
rootProject.name = "plugin"

