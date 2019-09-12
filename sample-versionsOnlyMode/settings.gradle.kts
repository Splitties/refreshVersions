pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
    resolutionStrategy.eachPlugin {
        val key = "plugin.${requested.id.id}"
        if (extra.has(key)) {
            val version = extra.get(key) as String
            println("Plugins: useVersion($version) for $key")
            useVersion(version)
        }
    }
}
rootProject.name = "sample-versionsOnlyMode"
includeBuild("../plugin")

