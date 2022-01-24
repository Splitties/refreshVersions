# What is refreshVersions? [jmfayard.github.io/refreshVersions](https://jmfayard.github.io/refreshVersions/)

**refreshVersions** helps **Gradle** users with the **tedious manual work** usually involved in adding and updating **dependencies and their versions**.

## Usage

### Setup

```kotlin
// settings.gradle(.kts)
plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.40.0"
}

refreshVersions { // Optional: configure the plugin
    // ...
}
```

**Migrate project:**

`./gradlew refreshVersionsMigrate`

**Find available updates in `versions.properties`:**

`./gradlew refreshVersions`

**Cleanup versions availability comments:**

`./gradlew refreshVersionsCleanup`

### Documentation is at [jmfayard.github.io/refreshVersions](https://jmfayard.github.io/refreshVersions/)

[![](https://raw.githubusercontent.com/jmfayard/refreshVersions/main/docs/img/screencast.png)](http://www.youtube.com/watch?v=VhYERonB8co "Gradle refreshVersions")
