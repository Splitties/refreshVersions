import de.fayard.refreshVersions.bootstrapRefreshVersions
import de.fayard.refreshVersions.migrateRefreshVersionsIfNeeded

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:0.9.7")
////                                                      # available:0.10.0")
}

migrateRefreshVersionsIfNeeded("0.9.7") // Will be automatically removed by refreshVersions when upgraded to the latest version.

bootstrapRefreshVersions()

plugins {
    id("com.gradle.enterprise").version("3.1.1")
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishAlwaysIf(System.getenv("GITHUB_ACTIONS")?.toBoolean() == true)
    }
}

val pluginsVersion: String = file("version.txt").bufferedReader().use { it.readLine() }

gradle.beforeProject {
    version = pluginsVersion
    group = "de.fayard.refreshVersions"
    loadLocalProperties()
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
