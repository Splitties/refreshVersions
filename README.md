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
    id("de.fayard.refreshVersions") version "0.40.2"
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

It requires a `mode` option. You can run it without it to see the complete list.

If you want to use only `versions.properties` and the [built-in dependencies notations](https://jmfayard.github.io/refreshVersions/dependencies-notations/), run:

`./gradlew refreshVersionsMigrate --mode=VersionsPropertiesOnly`

If you want to [also use Gradle's Versions Catalogs](https://github.com/jmfayard/drafts/wiki/RefreshVersions-%E2%99%A5%EF%B8%8F-Gradle-Version-Catalog), run:

`./gradlew refreshVersionsMigrate --mode=VersionCatalogAndVersionProperties`

**Find available updates in `versions.properties`:**

`./gradlew refreshVersions`

**Cleanup versions availability comments:**

`./gradlew refreshVersionsCleanup`

