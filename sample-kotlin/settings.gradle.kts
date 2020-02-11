import de.fayard.versions.setupVersionPlaceholdersResolving

pluginManagement {
    repositories {
        mavenLocal()
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
    dependencies.classpath("de.fayard:refreshVersions:0.8.6")
}

setupVersionPlaceholdersResolving()

rootProject.name = "sample-kotlin"
includeBuild("../refreshVersions")
includeBuild("../dependencies")
