pluginManagement {
    repositories {
        mavenLocal()
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
    id("com.gradle.enterprise").version("3.1.1")
////                        # available:"3.2")
////                        # available:"3.2.1")
////                        # available:"3.3")
////                        # available:"3.3.1")
////                        # available:"3.3.2")
////                        # available:"3.3.3")
////                        # available:"3.3.4")
////                        # available:"3.4")
////                        # available:"3.4.1")
////                        # available:"3.5")
////                        # available:"3.5.1")
////                        # available:"3.5.2")
////                        # available:"3.6")
////                        # available:"3.6.1")
////                        # available:"3.6.2")
////                        # available:"3.6.3")
////                        # available:"3.6.4")
////                        # available:"3.7")
////                        # available:"3.7.1")
////                        # available:"3.7.2")
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
}

include("module1")
include("module2")
