pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
        maven(url = "https://dl.bintray.com/kotlin/kotlin-eap")
        maven(url = "https://dl.bintray.com/jmfayard/maven")
    }

    val versionFile = rootDir.parentFile.resolve("plugins/version.txt")
    val pluginsVersion = versionFile.readLines().first()

    /*
    I'm big comment 1
     */
    // Hey, I'm user comment 0
    @Suppress("UnstableApiUsage")
    plugins /**/ {
        // Hey, I'm user comment 1
        id("de.fayard.refreshVersions").version(pluginsVersion)
        /*
        /*
        I'm big comment 2
        */*/
        // Hey, I'm user comment 2
        id ( "com.example.zero" ) . version (
    "1.0.0"
)
        // Hey, I'm user comment 3
    }
    // Hey, I'm user comment 4
}
// Hey, I'm user comment 5

// Hey, I'm user comment 6
plugins {
    // Hey, I'm user comment 7
    id(
        "com.example.one"
    ).version(
    "0.1"
)
    // Hey, I'm user comment 8
    id("com.example.two") version "0.4"
    // Hey, I'm user comment 9
    id("de.fayard.refreshVersions")
    // Hey, I'm user comment 10
}
// Hey, I'm user comment 11

rootProject.name = "Whatever"
