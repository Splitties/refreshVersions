This guide will help you setting up refreshVersions in a Gradle project.

## Setup

[![](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/de.fayard/refreshVersions/de.fayard.refreshVersions.gradle.plugin/maven-metadata.xml.svg?label=refreshVersions)](https://plugins.gradle.org/plugin/de.fayard.refreshVersions)
![Gradle Version](https://img.shields.io/endpoint?url=https%3A%2F%2Fgradle-latest-version-8xb9v8uk09jm.runkit.sh%2F)



### Update Gradle

Only Gradle 6+ is supported at the moment, because there were a lot of changes in dependencies management in Gradle 6. It also allows for a simpler setup for plugins for example.

Updating Gradle is anyway usually a good idea. You get fewer bugs, more
features, and faster builds.

Run this command to update:

```shell
./gradlew wrapper --gradle-version {{version.gradle}}
```

Note that for Android projects, you need to update the Android Gradle Plugin to its latest stable version at the same time.

### Gradle's Settings file

A Gradle project has [a Settings file](https://docs.gradle.org/current/userguide/build_lifecycle.html#sec:settings_file) called `settings.gradle`  or `settings.gradle.kts` where you must respect a certain order.

The order is:
1. imports, if any.
2. The `buildscript` block, if any. (We will use it)
3. The `pluginManagement` block, if any.
4. The `plugins` block, if any settings plugins are applied.
5. Logic for Gradle settings (any other code).

See the example snippet below:

```kotlin
import com.example.something // Imports at the top, as usual.

buildscript {
   // We will setup refreshVersions here, see below.
}
pluginManagement {} // Optional
plugins {} // Optional

// Then you can have other code after the blocks above,
// we will bootstrap refreshVersions here.

rootProject.name = "My Project" // Optional, defaults to parent dir's name.
include(":app") // If the project has modules/subprojects to declare.
```

### Bootstrap refreshVersions

Here is how you configure gradle refreshVersions

=== "settings.gradle.kts"
    ```kotlin
    import de.fayard.refreshVersions.bootstrapRefreshVersions

    buildscript {
        repositories { gradlePluginPortal() }
        dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.    refreshVersions}}")
    }

    bootstrapRefreshVersions()
    ```

=== "settings.gradle"
    ```groovy
    import de.fayard.refreshVersions.RefreshVersionsSetup

    buildscript {
        repositories { gradlePluginPortal() }
        dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.    refreshVersions}}")
    }

    RefreshVersionsSetup.bootstrap(settings)
    ```


### If you have a buildSrc module

I you use the **buildSrc** module, you probably want to use refreshVersions there as well.

=== "buildSrc/settings.gradle.kts"
    ```kotlin
    import de.fayard.refreshVersions.bootstrapRefreshVersionsForBuildSrc

    buildscript {
        repositories { gradlePluginPortal() }
        dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.    refreshVersions}}")
    }

    bootstrapRefreshVersionsForBuildSrc()
    ```

=== "buildSrc/settings.gradle"
    ```kotlin
    import de.fayard.refreshVersions.RefreshVersionsSetup

    buildscript {
        repositories { gradlePluginPortal() }
        dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.    refreshVersions}}")
    }

    RefreshVersionsSetup.bootstrapForBuildSrc(settings)
    ```


### If you have a composite/included build

Sharing used versions with included builds is not supported at the moment.

If you need/want this feature, please vote with a 👍 on [this issue]({{link.issues}}/205), subscribe to it, and tell us about your use case, to help us prioritize.

### Using a development version

To use a development version, you need to find the published development versions by searching in the
[recent commits on the develop branch]({{link.github}}/commits/develop)

You also need to add the maven repository `https://dl.bintray.com/jmfayard/maven`

=== "settings.gradle.kts"
    ```kotlin
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

=== "settings.gradle"
    ```groovy
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
