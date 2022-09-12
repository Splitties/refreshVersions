# What is refreshVersions?

**refreshVersions** helps **Gradle** users with the **tedious manual work** usually involved in adding and updating **dependencies and their versions**.

[![](https://raw.githubusercontent.com/jmfayard/refreshVersions/main/docs/img/screencast.png)](http://www.youtube.com/watch?v=VhYERonB8co "Gradle refreshVersions")

## Documentation

- [Start here](https://jmfayard.github.io/refreshVersions/)
- [Setup refreshVersions](https://jmfayard.github.io/refreshVersions/setup/)
- [Migrate your project](https://jmfayard.github.io/refreshVersions/migrate/)
- [Find Available Dependencies Updates](https://jmfayard.github.io/refreshVersions/update-dependencies/)
- [Add Dependencies](https://jmfayard.github.io/refreshVersions/add-dependencies/)
- [Explore Dependencies Notations](https://jmfayard.github.io/refreshVersions/dependencies-notations/)
- [Schedule the RefreshVersionsBot](https://jmfayard.github.io/refreshVersions/refreshversions-bot/)
- [Use the buildSrc](https://jmfayard.github.io/refreshVersions/gradle-buildsrcversions/)
- [Changelog](https://jmfayard.github.io/refreshVersions/CHANGELOG/)

[**See documentation at https://jmfayard.github.io/refreshVersions**](https://jmfayard.github.io/refreshVersions/)



## Setup

```kotlin
// settings.gradle(.kts)
plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.50.0"
}

refreshVersions { // Optional: configure the plugin
    // ...
}
```

[Read the friendly documentation](https://jmfayard.github.io/refreshVersions/setup/)


## Usage

Make sure the project is correctly set up (see just above).

**Migrate project:**

The `refreshVersionsMigrate` task can help you migrate your project in a few minutes, or less.

In version 0.50.0, support for Gradle's Versions Catalogs was added ([see discussion thread here](https://github.com/jmfayard/refreshVersions/discussions/592)), so a `--mode` option is now required.

Run it without it to see the complete list and the full description of each mode:

```shell
./gradlew refreshVersionsMigrate
```

<details>
<summary><i><strong>Examples</strong> (click to expand)</i></summary>

If you want to use only `versions.properties` and the [built-in dependencies notations](https://jmfayard.github.io/refreshVersions/dependencies-notations/), run:

`./gradlew refreshVersionsMigrate --mode=VersionsPropertiesOnly`

To also use a versions catalog for non-built-in dependency notations, run:

`./gradlew refreshVersionsMigrate --mode=VersionCatalogAndVersionProperties`

</details>

**Find available updates in `versions.properties` and the default versions catalog, if any:**

`./gradlew refreshVersions`

**Cleanup versions availability comments:**

`./gradlew refreshVersionsCleanup`
