[![](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/de.fayard/refreshVersions/de.fayard.refreshVersions.gradle.plugin/maven-metadata.xml.svg?label=refreshVersions)](https://plugins.gradle.org/plugin/de.fayard.refreshVersions)
![Gradle Version](https://img.shields.io/endpoint?url=https%3A%2F%2Fgradle-latest-version-8xb9v8uk09jm.runkit.sh%2F)



# Setting up

This guide will help you setting up refreshVersions in a Gradle project.

Adding the plugin is very quick and straightforward.

Setting up the dependencies so refreshVersions can show their updates is
a little longer process, but fear not, a semi-automated tool is built-in
to help you do this without breaking your build.

*Note: Only Gradle 6+ is supported at the moment, because it allows for
simpler setup.*

*Updating Gradle is usually a good idea. You get fewer bugs, more
features and more build speed, and it's as simple as this:*

`$ ./gradlew wrapper --gradle-version <GRADLE_VERSION>`

Here is the latest stable version of Gradle:

![Gradle Version](https://img.shields.io/endpoint?url=https%3A%2F%2Fgradle-latest-version-8xb9v8uk09jm.runkit.sh%2F)

# Step 1: Adding the plugin

## Bootstrap in root project

### The Settings file

RefreshVersions setup is done in Gradle's Settings file.

Note that the order matter in this file, it must be like this:

```
import com.example.something

buildscript {
   // see below
}
pluginManagement {
}
plugins {
}

bootstrapRefreshVersions()

rootProject.name = "My Project"
include(":app")
```

### Gradle Kotlin

If you're using *Gradle Kotlin DSL*, add the following snippet in your
project's root `settings.gradle.kts` file:

```kotlin
// settings.gradle.kts
import de.fayard.refreshVersions.bootstrapRefreshVersions

buildscript {
    repositories { gradlePluginPortal() }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:VERSION")
}

bootstrapRefreshVersions()
```

## Gradle Groovy

If you're using *Gradle Groovy DSL*, open `settings.gradle` file

```groovy
// settings.gradle
import de.fayard.refreshVersions.RefreshVersionsSetup

buildscript {
    repositories { gradlePluginPortal() }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:VERSION")
}

RefreshVersionsSetup.bootstrap(settings)
```


### If you have a buildSrc module

I you use the **buildSrc** module, you probably want to use refreshVersions there as well.

Here's how to do so:

If you're using *Gradle Kotlin DSL*, add the following snippet in your
the `buildSrc/settings.gradle.kts`:

```kotlin
// settings.gradle.kts
import de.fayard.refreshVersions.bootstrapRefreshVersionsForBuildSrc

buildscript {
    repositories { gradlePluginPortal() }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:VERSION")
}

bootstrapRefreshVersionsForBuildSrc()
```

If you're using *Gradle Groovy DSL*, use the same snippet as above for
your root project's `settings.gradle` file, then:


```kotlin
// settings.gradle
import de.fayard.refreshVersions.RefreshVersionsSetup

buildscript {
    repositories { gradlePluginPortal() }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:VERSION")
}

RefreshVersionsSetup.bootstrapForBuildSrc(settings)
```


### If you have a composite / included build

Included builds are not supported yet. If you need/want this feature,
please vote with a 👍 on [this issue](https://github.com/jmfayard/refreshVersions/issues/205) to
help us prioritize.

# Step 2: Migrating the dependencies versions declaration place

Run the following command on the root project:

`./gradlew migrateToRefreshVersionsDependenciesConstants --console=plain`

This Gradle task is interactive. It will walk you through every module
of your Gradle project, assisting you in replacing every hardcoded
dependency version by the version placeholder (`_`), and an entry in the
`versions.properties` file, for every configuration.

*Note that while this Gradle task cannot break your build, it is
experimental. We are aware its UX can be improved, and we have plans to
work on it before the 1.0 release.*

# Using a development version

In case you want to try new features or get a fix for a bug that affects
a project you work on, before they make it into a release, you can use a
development version.

To do so, in your `settings.gradle[.kts]` file(s), ensure the
`buildscript` has this maven repository:
`https://dl.bintray.com/jmfayard/maven`, and set a valid dev version, as
such:

```
buildscript {
    repositories {
        maven("https://dl.bintray.com/jmfayard/maven")
    }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:DEV_VERSION")
}
```

You can find the published development versions by searching in the
[recent commits on the develop branch](https://github.com/jmfayard/refreshVersions/commits/develop)

They start with "Dev version".
