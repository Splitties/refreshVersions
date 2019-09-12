pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
    resolutionStrategy.eachPlugin {
        val key = "plugin.${requested.id.id}"
        if (extra.has(key)) {
            useVersion(extra.get(key) as String)
        }
    }
}
rootProject.name = "plugin"

