import de.fayard.refreshVersions.bootstrapRefreshVersions

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
        maven(url = "https://dl.bintray.com/jmfayard/maven")
    }
}

buildscript {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
        maven(url = "https://dl.bintray.com/jmfayard/maven")
    }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions") {
        version {
            val versionFile = rootDir.parentFile.resolve("plugins/version.txt")
            strictly(versionFile.readLines().first())
        }
    }
}

bootstrapRefreshVersions()

plugins {
    id("com.gradle.enterprise").version("3.1.1")
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}
include("module1")
include("module2")
