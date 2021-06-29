# gradle refreshVersions

> Life is too short to google for dependencies and versions

### TL;DR

```kotlin
// settings.gradle(.kts)
plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.11.0"
}

// Migrate project
./gradlew refreshVersionsMigrate

// Find available updates in versions.properties
./gradlew refreshVersions
```

### Documentation is at [jmfayard.github.io/refreshVersions](https://jmfayard.github.io/refreshVersions/)

[![](https://raw.githubusercontent.com/jmfayard/refreshVersions/main/docs/img/screencast.png)](http://www.youtube.com/watch?v=VhYERonB8co "Gradle refreshVersions")
