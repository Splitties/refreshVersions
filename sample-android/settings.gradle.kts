import de.fayard.versions.RefreshVersionsSetup

buildscript {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
    dependencies.classpath("de.fayard:refreshVersions:0.9.0")
}

pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenLocal()
    }
}
RefreshVersionsSetup.bootstrap(settings)

rootProject.name = "sample-android"
include(":app")
