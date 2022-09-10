import de.fayard.refreshVersions.core.FeatureFlag.*
import de.fayard.refreshVersions.core.StabilityLevel

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
    id("com.gradle.enterprise").version("3.8.1")
    id("de.fayard.refreshVersions")
}

refreshVersions {
    featureFlags {
        enable(LIBS)
        disable(GRADLE_UPDATES)
    }

    extraArtifactVersionKeyRules(file("refreshVersions-extra-rules.txt"))

    // ignore dependencies among a blacklist of version keys
    rejectVersionIf {
        val blacklist = listOf("version.retrofit", "version.okhttp3")
        versionKey in blacklist
    }

    // ignore dependencies among a blacklist of maven groups
    rejectVersionIf {
        val blacklist = listOf("com.squareup.retrofit", "com.squareup.okhttp3")
        moduleId.group in blacklist
    }

    // ignore all non-stable releases
    rejectVersionIf {
        candidate.stabilityLevel != StabilityLevel.Stable
    }

    // Or maybe you want to see alpha versions if you are already using an alpha version, otherwise you want to see only stable versions
    rejectVersionIf {
        candidate.stabilityLevel.isLessStableThan(current.stabilityLevel)
    }
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishOnFailure()
    }
}

rootProject.name = "sample-kotlin"
rootProject.buildFileName = "${rootProject.name}.gradle.kts"
