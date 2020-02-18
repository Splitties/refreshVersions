import de.fayard.versions.bootstrapRefreshVersions

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}
buildscript {
    repositories {
        mavenLocal() // Only necessary for testing
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies.classpath("de.fayard.refreshVersions:de.fayard.refreshVersions.gradle.plugin:0.9.1")
}

bootstrapRefreshVersions()

rootProject.name = "refreshVersions"
