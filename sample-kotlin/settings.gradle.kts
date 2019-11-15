import de.fayard.versions.setupVersionPlaceholdersResolving

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
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

rootProject.name = "sample-kotlin"
includeBuild("../refreshVersions")
