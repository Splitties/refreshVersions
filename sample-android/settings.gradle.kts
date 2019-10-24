pluginManagement {
    repositories {
        google()
        //mavenLocal()
        gradlePluginPortal()
    }
}
apply(from = "plugins.gradle.kts")
rootProject.name = "sample-android"
//includeBuild("../plugin")
include(":app")
