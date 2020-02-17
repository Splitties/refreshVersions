import de.fayard.versions.setupVersionPlaceholdersResolving

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}

buildscript {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies.classpath("de.fayard:refreshVersions:0.9.0")
}

setupVersionPlaceholdersResolving()

plugins {
    id("com.gradle.enterprise").version("3.1.1")
}

settings.gradle.allprojects {
    apply<de.fayard.RefreshVersionsPlugin>()
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlways()
    }
}

rootProject.name = "dependencies"
