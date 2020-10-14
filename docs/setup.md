[![](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/de.fayard/refreshVersions/de.fayard.refreshVersions.gradle.plugin/maven-metadata.xml.svg?label=refreshVersions)](https://plugins.gradle.org/plugin/de.fayard.refreshVersions)
![Gradle Version](https://img.shields.io/endpoint?url=https%3A%2F%2Fgradle-latest-version-8xb9v8uk09jm.runkit.sh%2F)


This guide will help you setting up refreshVersions in a Gradle project.

# Setting up

## Update Gradle

Only Gradle 6+ is supported at the moment, because there were a lot of changes in dependencies management in Gradle 6. It also allows for a simpler setup for plugins for example.

Updating Gradle is anyway usually a good idea. You get fewer bugs, more
features and more build speed.

This how you update:

`$ ./gradlew wrapper --gradle-version {{version.gradle}}`

Note that if you are on Android, you need to update the Android Gradle Plugin to its latest stable version at the same time.

## Bootstrap in root project

### Gradle's Settings file

A Gradle project has [a Settings file](https://docs.gradle.org/current/userguide/build_lifecycle.html#sec:settings_file) called `settings.gradle`  or `settings.gradle.kts` where you must respect a certain order:

```kotlin
import com.example.something

buildscript {
   // see below
}
pluginManagement {
}
plugins {
}

// see below

rootProject.name = "My Project"
include(":app")
```

### Bootstrap refreshVersions

Here is how you configure gradle refreshVersions

=== "Kotlin"
    ```kotlin
    // settings.gradle.kts
    import de.fayard.refreshVersions.bootstrapRefreshVersions

    buildscript {
        repositories { gradlePluginPortal() }
        dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.    refreshVersions}}")
    }

    bootstrapRefreshVersions()
    ```

=== "Groovy"
    ```groovy
    // settings.gradle
    import de.fayard.refreshVersions.RefreshVersionsSetup

    buildscript {
        repositories { gradlePluginPortal() }
        dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.    refreshVersions}}")
    }

    RefreshVersionsSetup.bootstrap(settings)
    ```


### If you have a buildSrc module

I you use the **buildSrc** module, you probably want to use refreshVersions there as well.

=== "Kotlin"
    ```kotlin
    // buildSrc/settings.gradle.kts
    import de.fayard.refreshVersions.bootstrapRefreshVersionsForBuildSrc

    buildscript {
        repositories { gradlePluginPortal() }
        dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.    refreshVersions}}")
    }

    bootstrapRefreshVersionsForBuildSrc()
    ```

=== "Groovy"
    ```kotlin
    // buildSrc/settings.gradle
    import de.fayard.refreshVersions.RefreshVersionsSetup

    buildscript {
        repositories { gradlePluginPortal() }
        dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.    refreshVersions}}")
    }

    RefreshVersionsSetup.bootstrapForBuildSrc(settings)
    ```


### If you have a composite / included build

Included builds are not supported yet. If you need/want this feature,
please vote with a 👍 on [this issue](https://github.com/jmfayard/refreshVersions/issues/205) to
help us prioritize.

### Using a development version

To use a development version, you need to find the published development versions by searching in the
[recent commits on the develop branch](https://github.com/jmfayard/refreshVersions/commits/develop)

You also need to add the maven repository `https://dl.bintray.com/jmfayard/maven`

=== "Kotlin"
    ```kotlin
    // settings.gradle.kts
    import de.fayard.refreshVersions.bootstrapRefreshVersions

    buildscript {
        repositories {
            gradlePluginPortal()
            maven("https://dl.bintray.com/jmfayard/maven")
        }
        dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.    refreshVersionsDev}}")
    }

    bootstrapRefreshVersions()
    ```

=== "Groovy"
    ```groovy
    // settings.gradle
    import de.fayard.refreshVersions.RefreshVersionsSetup

    buildscript {
        repositories {
            gradlePluginPortal()
            maven { url 'https://dl.bintray.com/jmfayard/maven' }
        }
        dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.    refreshVersionsDev}}")
    }

    RefreshVersionsSetup.bootstrap(settings)
    ```

### Run $ gradle refreshVersions

At that point, you should be able to run `$ gradle refreshVersions`

It won't do much though, because refreshVersions only manage the dependencies you told it to manage. Next we will see how to migrate your project
