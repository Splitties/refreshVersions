# What is refreshVersions?

**refreshVersions** helps **Gradle** users with the **tedious manual work** usually involved in adding and updating **dependencies and their versions**.

[![](https://raw.githubusercontent.com/jmfayard/refreshVersions/main/docs/img/screencast.png)](http://www.youtube.com/watch?v=VhYERonB8co "Gradle refreshVersions")

## Documentation

- [Start here](https://splitties.github.io/refreshVersions/)
- [Setup refreshVersions](https://splitties.github.io/refreshVersions/setup/)
- [Migrate your project](https://splitties.github.io/refreshVersions/migrate/)
- [Find Available Dependencies Updates](https://splitties.github.io/refreshVersions/update-dependencies/)
- [Add Dependencies](https://splitties.github.io/refreshVersions/add-dependencies/)
- [Explore built-in Dependencies Notations](https://splitties.github.io/refreshVersions/dependency-notations/)
- [Schedule the RefreshVersionsBot](https://splitties.github.io/refreshVersions/refreshversions-bot/)
- [Use the buildSrc](https://splitties.github.io/refreshVersions/gradle-buildsrcversions/)
- [Changelog](https://splitties.github.io/refreshVersions/CHANGELOG/)

[**See documentation at https://splitties.github.io/refreshVersions**](https://splitties.github.io/refreshVersions/)



## Setup

```kotlin
// settings.gradle(.kts)
plugins {
    // See https://splitties.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.60.3"
}

refreshVersions { // Optional: configure the plugin
    // ...
}
```

[Read the friendly documentation](https://splitties.github.io/refreshVersions/setup/)


## Usage

Make sure the project is correctly set up (see just above).

**Migrate project:**

The `refreshVersionsMigrate` task can help you migrate your project in a few minutes, or less.

In version 0.50.0, support for Gradle's Versions Catalogs was added ([see discussion thread here](https://github.com/Splitties/refreshVersions/discussions/592)), so a `--mode` option is now required.

Run it without it to see the complete list and the full description of each mode:

```shell
./gradlew refreshVersionsMigrate
```

<details>
<summary><i><strong>Examples</strong> (click to expand)</i></summary>

If you want to use only `versions.properties` and the [built-in dependencies notations](https://splitties.github.io/refreshVersions/dependency-notations/), run:

`./gradlew refreshVersionsMigrate --mode=VersionsPropertiesOnly`

To also use a versions catalog for non-built-in dependency notations, run:

`./gradlew refreshVersionsMigrate --mode=VersionCatalogAndVersionProperties`

</details>

**Find available updates in `versions.properties` and the default versions catalog, if any:**

`./gradlew refreshVersions`

**Cleanup versions availability comments:**

`./gradlew refreshVersionsCleanup`
