pluginManagement {
    includeBuild("convention-plugins")
}

plugins {
    id("com.gradle.enterprise").version("3.8.1")
    id("de.fayard.refreshVersions") version "0.60.3"
    id("org.splitties.version-sync") version "0.2.6"
}

dependencyResolutionManagement {
    @Suppress("UnstableApiUsage")
    repositories { mavenCentral() }
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlwaysIf(System.getenv("GITHUB_ACTIONS")?.toBoolean() == true)
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
