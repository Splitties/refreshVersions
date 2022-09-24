import de.fayard.refreshVersions.core.FeatureFlag.*
import de.fayard.refreshVersions.core.ModuleId

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
    id("com.gradle.enterprise").version("3.8")
    id("de.fayard.refreshVersions")
}

refreshVersions {
    featureFlags {
        enable(LIBS)
        disable(GRADLE_UPDATES)
        disable(NPM_IMPLICIT_RANGE)
    }
    rejectVersionIf {
        moduleId is ModuleId.Npm && moduleId.name == "react" && candidate.stabilityLevel.isLessStableThan(current.stabilityLevel)
    }
}

gradleEnterprise {
    buildScan {
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
    }
}

rootProject.name = "sample-kotlin-js"
