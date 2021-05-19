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
    pluginManagement {
        plugins {
            id("de.fayard.refreshVersions") version "{{version.refreshVersions}}"
        }
    }

    plugins {
        id("de.fayard.refreshVersions")
    }
    ```
=== "buildSrc/settings.gradle"
    ```groovy
    pluginManagement {
        plugins {
            id 'de.fayard.refreshVersions' version '{{version.refreshVersions}}'
        }
    }

    plugins {
        id 'de.fayard.refreshVersions'
    }
    ```

### If you use Groovy DSL, i.e. build.gradle files (not kts)

**Auto-completion for dependency notations won't work out of the box.**

A workaround is to configure the plugin in the `buildSrc` module (create the directory if it doesn't exist yet):

=== "buildSrc/settings.gradle"
    ```groovy
    pluginManagement {
        plugins {
            id 'de.fayard.refreshVersions' version '{{version.refreshVersions}}'
        }
    }

    plugins {
        id 'de.fayard.refreshVersions'
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


## Earlier versions

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

Then, enable `buildSrcLibs` as such:

=== "settings.gradle.kts"
    ```kotlin
    plugins {
        // See https://jmfayard.github.io/refreshVersions
        id("de.fayard.refreshVersions") version "{{version.refreshVersions}}"
    }

    refreshVersions {
        enableBuildSrcLibs() // <-- Add this
    }
    ```
=== "settings.gradle"
    ```groovy
    plugins {
        // See https://jmfayard.github.io/refreshVersions
        id 'de.fayard.refreshVersions' version '{{version.refreshVersions}}'
    }

    refreshVersions {
        enableBuildSrcLibs() // <-- Add this
    }
    ```


Read more: [gradle buildSrcVersions](gradle-buildsrcversions.md).


## Next steps

You did it! refreshVersions is now properly setup.

Now, you might want to:

- [Migrate/opt-in existing dependency declarations](migration.md), so the `refreshVersions` task can find available updates for you.
- [Add new dependencies](add-dependencies.md).
- [Update dependencies](update-dependencies.md).
