pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
        maven(url = "https://dl.bintray.com/jmfayard/maven")
    }

    val versionFile = rootDir.parentFile.resolve("plugins/version.txt")
    val pluginsVersion = versionFile.readLines().first()


        @Suppress("UnstableApiUsage")
    plugins {
                id("de.fayard.refreshVersions").version(pluginsVersion)

                id("com.example.zero").version("1.0.0")
            }
    }

plugins {
        id("com.example.one") version "0.1"
        id("com.example.two") version "1.2.0-beta02"
        id("de.fayard.refreshVersions")
    }

rootProject.name = "Whatever"
