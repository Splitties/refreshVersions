pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}
//apply(from = "plugins.gradle.kts")
rootProject.name = "sample-kotlin"
includeBuild("../plugin")

