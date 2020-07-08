import de.fayard.refreshVersions.bootstrapRefreshVersionsForBuildSrc

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
            val versionFile = rootDir.parentFile.parentFile.resolve("plugins/version.txt")
            strictly(versionFile.readLines().first())
        }
    }
}

bootstrapRefreshVersionsForBuildSrc()
