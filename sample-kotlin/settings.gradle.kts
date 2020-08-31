
pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
        maven(url = "https://dl.bintray.com/jmfayard/maven")
    }
    val refreshVersionsVersion = File("../plugins/version.txt").readLines().first()
    plugins {
        id("de.fayard.refreshVersions.settings").version(refreshVersionsVersion)
        id("de.fayard.refreshVersions.core").version(refreshVersionsVersion)
        id("de.fayard.refreshVersions").version(refreshVersionsVersion)
    }
}

plugins {
    id("com.gradle.enterprise").version("3.1.1")
    id("de.fayard.refreshVersions.settings")
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

rootProject.name = "sample-kotlin"
