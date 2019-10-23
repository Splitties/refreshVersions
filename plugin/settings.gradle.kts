pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}
apply(from = "plugins.gradle.kts")
rootProject.name = "plugin"
