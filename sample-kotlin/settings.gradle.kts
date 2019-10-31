import de.fayard.versions.setupVersionPlaceholdersResolving

buildscript {
    repositories {
        mavenLocal() // Only necessary for testing
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies.classpath("de.fayard:plugin:0.8.2") // Didn't find a way to use classpath from composite build.
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

