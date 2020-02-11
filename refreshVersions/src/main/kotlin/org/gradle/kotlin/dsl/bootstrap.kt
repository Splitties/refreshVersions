package org.gradle.kotlin.dsl

import de.fayard.versions.setupVersionPlaceholdersResolving
import org.gradle.api.initialization.Settings

/**
 * Boostrap refreshVersion in settings.gradle.kts
 *
```kotlin
// settings.gradle.kts
buildscript {
    dependencies.classpath("de.fayard:refreshVersions:VERSION")
}

settings.bootstrapRefreshVersions()
```

 */
fun Settings.bootstrapRefreshVersions() : Unit =
    settings.setupVersionPlaceholdersResolving()
