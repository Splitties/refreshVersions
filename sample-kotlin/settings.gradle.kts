import de.fayard.dependencies.bootstrapRefreshVersionsAndDependencies

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
    }
}

buildscript {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
    }
    dependencies.classpath("de.fayard:dependencies") {
        version {
            val versionFile = rootDir.parentFile.resolve("plugins/dependencies/version.txt")
            strictly(versionFile.readLines().first())
        }
    }
}

bootstrapRefreshVersionsAndDependencies()

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
