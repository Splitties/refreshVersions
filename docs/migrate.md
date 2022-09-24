# Migrate

Migrating an existing project to refreshVersions manually is tricky:

- Existing dependencies are ignored because they don't use the version placeholder `_`.
- Replacing the hardcoded version with the version placeholder is not what you want, because it has the major side effect to upgrade all your dependencies to whatever is the latest version available.

That's why we made a Gradle task specifically for migration.

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

The task **refreshVersionsMigrate** takes care of the migration semi-automatically:

```shell
./gradlew refreshVersionsMigrate
```

Running the task just like that without any parameters will lead to a failure. The error message will tell that the option `--mode` is required, and it will list all the possible values.

You should see something like this:

```
> Task :refreshVersionsMigrate
        modified:   versions.properties
        modified:   build.gradle.kts
        modified:   gradle/libraries.gradle

To find available updates, run this:

    ./gradlew refreshVersions

```

At that point, you probably want to have a look at the `git diff` to see what changed and fix/tweak things if needed.

As you can see, depending on the mode you selected, it edits some files in a given way.

For the `VersionsPropertiesOnly` mode:

- It generates `versions.properties` with the **current** version of all dependencies.
- It modifies `build.gradle(.kts)` files and other files like `libraries.gradle` or `buildSrc/src/main/kotlin/Libs.kt` known to contain dependency notations, so that the version placeholder `_` is used everywhere.

For the `VersionCatalogOnly` mode:

- It generates `gradle/libs.versions.toml` with the **current** version of all dependencies.
- It modifies `build.gradle(.kts)` files, so that dependency notations everywhere.

For the other modes, you'll see a combination of the above.

If anything isn't working well for you, please, [give us here your feedback for refreshVersionsMigrate 👍🏼](https://github.com/jmfayard/refreshVersions/discussions/396).
