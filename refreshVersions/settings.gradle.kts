import de.fayard.versions.bootstrapRefreshVersions

buildscript {
    repositories {
        mavenLocal() // Only necessary for testing
        gradlePluginPortal()
        mavenCentral()
    }
    dependencies.classpath("de.fayard.refreshVersions:de.fayard.refreshVersions.gradle.plugin:0.9.1")
}

bootstrapRefreshVersions(
    settingsDir.parentFile.resolve("dependencies/src/main/resources/refreshVersions-rules").listFiles()!!.map {
        it.readText()
    }
)

plugins {
    id("com.gradle.enterprise").version("3.1.1")
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlways()
    }
}

rootProject.name = "refreshVersions"
