import de.fayard.refreshVersions.core.FeatureFlag

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
    }

    val versionFile = rootDir.parentFile.resolve("plugins/version.txt")
    val pluginsVersion = versionFile.readLines().first()

    @Suppress("UnstableApiUsage")
    plugins {
        id("de.fayard.refreshVersions").version(pluginsVersion)
    }
}

plugins {
    id("com.gradle.enterprise").version("3.8")
////                        # available:"3.8.1")
////                        # available:"3.9")
////                        # available:"3.10")
////                        # available:"3.10.1")
////                        # available:"3.10.2")
////                        # available:"3.10.3")
    id("de.fayard.refreshVersions")
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

refreshVersions {
    enableBuildSrcLibs()
    featureFlags {
        enable(FeatureFlag.DEPENDENCIES_DOC)
    }
}

include("module1")
include("module2")
