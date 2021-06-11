import de.fayard.refreshVersions.core.FeatureFlag.GRADLE_UPDATES
import de.fayard.refreshVersions.core.FeatureFlag.LIBS

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
    id("com.gradle.enterprise").version("3.6.2")
    id("de.fayard.refreshVersions")
}

refreshVersions {
    featureFlags {
        enable(LIBS)
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

includeBuild("included") {
//    dependencySubstitution {
//        substitute(module("de.fayard:included")).with(project(":"))
//        substitute(module("de.fayard:subproject2")).with(project(":subproject2"))
//        substitute(module("de.fayard:subproject")).with(project(":subproject"))
//    }
}

rootProject.name = "sample-include-build"
