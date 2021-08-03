# Migrate

Migrating an existing project to refreshVersions manually is tricky:

- Existing dependencies are ignored because they don't use the version placeholder `_`.
- Replacing the harcoded version with the version placeholder is not what you want, because it has the major side effect to upgrade all your dependencies to whatever is the latest version available.

Starting with refreshVersions 0.11.0, we provide an experimental interactive Gradle task made for migration.

First, ensure you are using the plugin's latest version:

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

## Migrate semi-automatically

The new task **refreshVersionsMigrate** takes care of the migration semi-automatically:

```shell
./gradlew refreshVersionsMigrate
```

You should see something like this:

```
> Task :refreshVersionsMigrate
        modified:   versions.properties
        modified:   build.gradle.kts
        modified:   gradle/libraries.gradle

To find available updates, run this:

    ./gradlew refreshVersions

```

At that point, you probably want to have a look at the `git diff` to see what changed.

As you can see:

- It generates `versions.properties` with the **current** version of all dependencies.
- It modifies `build.gradle(.kts)` files and other files like `libraries.gradle` or `buildSrc/src/main/kotlin/Libs.kt` known to contain dependency notations, so that the version placeholder `_` is used everywhere.

Please try it out and [give us here your feedback for refreshVersionsMigrate 👍🏼](https://github.com/jmfayard/refreshVersions/discussions/396).

