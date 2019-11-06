pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}
apply(from = "gradle/plugins.gradle.kts")
rootProject.name = "sample-kotlin"
includeBuild("../plugin")

