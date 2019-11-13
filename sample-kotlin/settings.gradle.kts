import de.fayard.versions.setupVersionPlaceholdersResolving

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

buildscript {
    repositories {
        mavenLocal() // Only necessary for testing
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies.classpath("de.fayard.refreshVersions:de.fayard.refreshVersions.gradle.plugin:0.8.3")
}

setupVersionPlaceholdersResolving()

rootProject.name = "sample-kotlin"
includeBuild("../refreshVersions")
