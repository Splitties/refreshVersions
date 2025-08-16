pluginManagement {
    includeBuild("convention-plugins")
}

plugins {
    id("com.gradle.develocity").version("4.1")
    id("de.fayard.refreshVersions") version "0.60.6" // Needed, but must match convention-plugins'.
    id("org.splitties.version-sync") version "0.2.6"
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories { mavenCentral() }
}

develocity {
    buildScan {
        termsOfUseUrl = "https://gradle.com/terms-of-service"
        termsOfUseAgree = "yes"
        publishing.onlyIf { System.getenv("GITHUB_ACTIONS")?.toBoolean() == true }
    }
}

gradle.rootProject {
    loadLocalProperties()
}

gradle.beforeProject {
    group = "de.fayard.refreshVersions"
}

include("core", "dependencies", "buildSrcLibs")
project(":core").name = "refreshVersions-core"
project(":dependencies").name = "refreshVersions"

fun Project.loadLocalProperties() {
    val localPropertiesFile = rootDir.resolve("local.properties")
    if (localPropertiesFile.exists()) {
        val localProperties = java.util.Properties()
        localProperties.load(localPropertiesFile.inputStream())
        localProperties.forEach { (k, v) -> if (k is String) project.extra.set(k, v) }
    }
}
