import de.fayard.versions.setupVersionPlaceholdersResolving

buildscript {
    repositories {
        mavenLocal() // Only necessary for testing
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies.classpath("de.fayard.refreshVersions:de.fayard.refreshVersions.gradle.plugin:0.8.2")
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

setupVersionPlaceholdersResolving()

rootProject.name = "sample-kotlin"
includeBuild("../refreshVersions")
