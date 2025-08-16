plugins {
    id("de.fayard.refreshVersions") version "0.60.6"
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

refreshVersions {
    versionsPropertiesFile = rootDir.parentFile.resolve("versions.properties")
}
