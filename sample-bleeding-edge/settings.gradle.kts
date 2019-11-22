import de.fayard.versions.setupVersionPlaceholdersResolving

buildscript {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
    // Didn't find a way to use classpath from composite build, so we are hardcoding a
    // version expected to be released in one of the configured repositories.
    dependencies.classpath("de.fayard:refreshVersions:0.8.3")
}
pluginManagement {
    plugins {
        id("com.gradle.enterprise").version("3.0")
    }
    repositories {
        gradlePluginPortal()
        mavenLocal()
        mavenCentral()
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
    }
}
setupVersionPlaceholdersResolving()

rootProject.name = "sample-bleeding-edge"
