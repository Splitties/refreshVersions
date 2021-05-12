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



## Add the plugin

Here is how to configure gradle refreshVersions:

=== "settings.gradle.kts"
    ```kotlin
    plugins {
        // See https://jmfayard.github.io/refreshVersions
        id("de.fayard.refreshVersions") version "{{version.refreshVersions}}"
    }
    ```
=== "settings.gradle"
    ```groovy
    plugins {
        // See https://jmfayard.github.io/refreshVersions
        id 'de.fayard.refreshVersions' version '{{version.refreshVersions}}'
    }
    ```


### If you have a buildSrc module

If you use the **buildSrc** module and have dependencies declared in the `buildSrc/build.gradle[.kts]` file, you probably want to use refreshVersions there as well. The setup is the same:

=== "buildSrc/settings.gradle.kts"
    ```kotlin
    plugins {
        id("de.fayard.refreshVersions") version "{{version.refreshVersions}}"
    }
    ```
=== "buildSrc/settings.gradle"
    ```groovy
    plugins {
        id 'de.fayard.refreshVersions' version '{{version.refreshVersions}}'
    }
    ```

### If you use Groovy DSL, i.e. build.gradle files (not kts)

**Auto-completion for dependency notations won't work out of the box.**

A workaround is to configure the plugin in the `buildSrc` module (create the directory if it doesn't exist yet):

=== "buildSrc/settings.gradle"
```groovy
plugins {
    id 'de.fayard.refreshVersions' version '{{version.refreshVersions}}'
}
```


### If you have a composite/included build

Sharing used versions with included builds is not supported at the moment.

If you need/want this feature, please vote with a 👍 on [this issue]({{link.issues}}/205), subscribe to it, and tell us about your use case, to help us prioritize.

### If you want to use a development version

Follow [issue 340: Continuous Deployment]({{link.issues}}/340)

## Configure the plugin

There is no required configuration!

There are some options which can be configured in the `refreshVersions { }` block.

If you are curious about what are the available options, you can use auto-complete (you can also type `this.` before to filter the results).

<img width="854" src="https://user-images.githubusercontent.com/459464/117489731-41322200-af6e-11eb-8e5d-f3ba0e7b6070.png">

<!--
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
-->

## Earlier versions

<!--
### refreshVersions 0.9.x and earlier

There is an
Here is how refreshVersions was configured in 0.9.x and earlier versions

=== "settings.gradle.kts"
    ```kotlin
    import de.fayard.refreshVersions.bootstrapRefreshVersions

    buildscript {
        repositories { gradlePluginPortal() }
        dependencies.classpath("de.fayard.refreshVersions:refreshVersions:0.9.7")
    }

    bootstrapRefreshVersions()
    ```
=== "settings.gradle"
    ```groovy
    import de.fayard.refreshVersions.RefreshVersionsSetup

    buildscript {
        repositories { gradlePluginPortal() }
        dependencies.classpath("de.fayard.refreshVersions:refreshVersions:0.9.7")
    }

    RefreshVersionsSetup.bootstrap(settings)
    ```
-->

### If you are upgrading from the buildSrcVersions plugin

Before refreshVersions, [there was the plugin buildSrcVersions](https://dev.to/jmfayard/better-dependency-management-in-android-studio-3-5-with-gradle-buildsrcversions-34e9).

If your project is using it, remove all its configuration from the top `build.gradle[.kts]` file to avoid any clashes between the two plugins:

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

Read more: [gradle buildSrcVersions](gradle-buildsrcversions.md).


## Next steps

You did it! refreshVersions is now properly setup.

Now, you might want to:

- [Migrate/opt-in existing dependency declarations](migration.md), so the `refreshVersions` task can find available updates for you.
- [Add new dependencies](add-dependencies.md).
- [Update dependencies](update-dependencies.md).
