import de.fayard.refreshVersions.core.FeatureFlag.*

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
    }
}

plugins {
    id("com.gradle.enterprise").version("3.1.1")
    id("de.fayard.refreshVersions")
}

refreshVersions {

    featureFlags {
        enable(BUILD_SRC_LIBS)
        disable(GRADLE_UPDATES)
    }

    extraArtifactVersionKeyRules(file("refreshVersions-extra-rules.txt"))
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

rootProject.name = "sample-kotlin"
