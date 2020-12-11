# Setup

This guide will help you set up refreshVersions in a Gradle project.

## Update Gradle (if needed)

Only Gradle 6.3+ is supported at the moment, because there were a lot of changes in dependencies management in Gradle 6, and other compatibility concerns. It also allows for a simpler setup for plugins for example.

Updating Gradle is anyway usually a good idea. You get fewer bugs, more
features, and faster builds.

Run this command to update:

```shell
./gradlew wrapper --gradle-version {{version.gradle}}
```

You should also try to update the Gradle plugins present in your build to the latest version. For example on an Android project, do update the version of the Gradle Android Plugin.

The Gradle documentation has detailed migration guide if you are stuck:

- From Gradle 6+: https://docs.gradle.org/current/userguide/upgrading_version_6.html
- From Gradle 5.x: https://docs.gradle.org/current/userguide/upgrading_version_5.html
- From Gradle 4.x: https://docs.gradle.org/current/userguide/upgrading_version_4.html


## About Gradle's Settings file

For refreshVersions to be able to work for all the dependencies in your project, including for the ones in the `buildscript`'s `classpath`, it needs to be setup in the Gradle settings.

A Gradle project has [a Settings file](https://docs.gradle.org/current/userguide/build_lifecycle.html#sec:settings_file) called `settings.gradle`  or `settings.gradle.kts` where you must respect a certain order (otherwise, the build breaks).

The order is:
1. imports, if any.
2. The `pluginManagement` block, if any.
3. The `buildscript` block, if any. (We will use it)
4. The `plugins` block, if any settings plugins are applied.
5. Logic for Gradle settings (any other code).

See the example snippet below:

```kotlin
import com.example.something // Imports at the top, as usual.

pluginManagement {} // Optional

buildscript {
    // We will setup refreshVersions here, see below.
}

plugins {} // Optional

// Then you can have other code after the blocks above,
// we will bootstrap refreshVersions here.

rootProject.name = "My Project" // Optional, defaults to parent dir's name.
include(":app") // If the project has modules/subprojects to declare.
```

## Bootstrap refreshVersions

Here is how to configure gradle refreshVersions:

=== "settings.gradle.kts"
```kotlin
import de.fayard.refreshVersions.bootstrapRefreshVersions

buildscript {
    repositories { gradlePluginPortal() }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.refreshVersions}}")
}

bootstrapRefreshVersions()
```

=== "settings.gradle"
```groovy
import de.fayard.refreshVersions.RefreshVersionsSetup

buildscript {
    repositories { gradlePluginPortal() }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.refreshVersions}}")
}

RefreshVersionsSetup.bootstrap(settings)
```


### If you upgrade from the plugin buildSrcVersions

Before refreshVersions, [there was the plugin buildSrcVersions](https://dev.to/jmfayard/better-dependency-management-in-android-studio-3-5-with-gradle-buildsrcversions-34e9)

If your project is using it, remove all its configuration from the top `build.gradle[.kts]` file

=== "build.gradle.kts"
```diff
-plugins {
-    id("de.fayard.buildSrcVersions") version "0.3.2"
-}

-buildSrcVersions {
-    someOption = "somevalue"
-}
```

The task `buildSrcVersions` is still available.

Read more: [gradle buildSrcVersions]({{link.site}}/gradle-buildsrcversions).

### If you have a buildSrc module

If you use the **buildSrc** module and have dependencies declared in the `buildSrc/build.gradle[.kts]` file, you probably want to use refreshVersions there as well. For that, an extra special setup is required.

=== "buildSrc/settings.gradle.kts"
```kotlin
import de.fayard.refreshVersions.bootstrapRefreshVersionsForBuildSrc

buildscript {
    repositories { gradlePluginPortal() }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.refreshVersions}}")
}

bootstrapRefreshVersionsForBuildSrc()
```

=== "buildSrc/settings.gradle"
```groovy
import de.fayard.refreshVersions.RefreshVersionsSetup

buildscript {
    repositories { gradlePluginPortal() }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.refreshVersions}}")
}

RefreshVersionsSetup.bootstrapForBuildSrc(settings)
```


### If you have a composite/included build

Sharing used versions with included builds is not supported at the moment.

If you need/want this feature, please vote with a 👍 on [this issue]({{link.issues}}/205), subscribe to it, and tell us about your use case, to help us prioritize.

### If you want to use a development version

To use a development version (for example to test an unreleased new feature), you need to find the published development versions by searching in the
[recent commits on the develop branch]({{link.github}}/commits/develop) (they start with "Dev version").

You also need to add the maven repository `https://dl.bintray.com/jmfayard/maven` as shown below:

=== "settings.gradle.kts"
```kotlin
import de.fayard.refreshVersions.bootstrapRefreshVersions

buildscript {
    repositories {
        gradlePluginPortal()
        maven("https://dl.bintray.com/jmfayard/maven")
    }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.refreshVersionsDev}}")
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
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:{{version.refreshVersionsDev}}")
}

RefreshVersionsSetup.bootstrap(settings)
```

## Next steps

You did it! refreshVersions is now properly setup.

Now, you might want to:

- [Migrate/opt-in existing dependency declarations]({{link.site}}/migration), so the `refreshVersions` task can find available updates for you.
- [Add new dependencies]({{link.site}}/add-dependencies).
- [Update dependencies]({{link.site}}/update-dependencies).
