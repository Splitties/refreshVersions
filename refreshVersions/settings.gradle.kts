import de.fayard.versions.setupVersionPlaceholdersResolving

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
    dependencies.classpath("de.fayard.refreshVersions:de.fayard.refreshVersions.gradle.plugin:0.8.4")
}

setupVersionPlaceholdersResolving()
rootProject.name = "refreshVersions"
