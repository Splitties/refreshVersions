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

plugins {
    id("com.gradle.enterprise").version("3.1.1")
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

rootProject.name = "sample-kotlin"
includeBuild("../refreshVersions")
includeBuild("../dependencies")
