import de.fayard.versions.setupVersionPlaceholdersResolving

buildscript {
    repositories {
        mavenLocal() // Only necessary for testing
        gradlePluginPortal()
        mavenCentral()
    }
    // Didn't find a way to use classpath from composite build, so we are hardcoding a
    // version expected to be released in one of the configured repositories.
    dependencies.classpath("de.fayard:plugin:0.8.2")
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
    }
}

setupVersionPlaceholdersResolving()

rootProject.name = "sample-kotlin"
includeBuild("../plugin")

