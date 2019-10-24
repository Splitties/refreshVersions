pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}
apply(from = "gradle/plugins.gradle.kts")
rootProject.name = "plugin"
