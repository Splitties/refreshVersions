pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        //mavenLocal()
    }
}
apply(from = "gradle/plugins.gradle.kts")
rootProject.name = "sample-android"
include(":app")
