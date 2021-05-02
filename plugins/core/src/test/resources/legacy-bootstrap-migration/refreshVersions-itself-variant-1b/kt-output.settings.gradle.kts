import org.gradle.api.Project
import org.gradle.kotlin.dsl.extra
import org.gradle.kotlin.dsl.maven
import org.gradle.kotlin.dsl.repositories

plugins {
    id("com.gradle.enterprise").version("3.1.1")
    id("de.fayard.refreshVersions") version "€{currentVersion}"
}

refreshVersions {
    extraArtifactVersionKeyRules = emptyList()
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
