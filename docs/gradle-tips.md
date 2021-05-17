# Gradle tips and tricks

More information to make your build great again!

## Switch to the Kotlin DSL

Groovy was there first in Gradle, but consider switching to Kotlin if you have not done so already. The cryptic error
messages will go away, and the IDE support is far superior. Hello auto-complete!

**<a href="https://dev.to/jmfayard/how-kotlin-makes-editing-your-gradle-build-less-frustrating-232l">How Kotlin makes
editing your Gradle build less frustrating</a>**

## Consider using the Gradle build scan

Given the range of information it gives you about your build, it's a no-brainer to use the build scan if you are working
an open-source project or have a Gradle Enterprise account. For a company project, understands the trade off of having
this information potentially shared by someone outside.

It has to be configured in `settings.gradle[.kts]`

```kotlin
// https://dev.to/jmfayard/the-one-gradle-trick-that-supersedes-all-the-others-5bpg
plugins {
    id("com.gradle.enterprise").version(VERSION)
}

gradleEnterprise {
    buildScan {
        // Accept the license agreement for com.gradle.build-scan plugin
        termsOfServiceUrl = "https://gradle.com/terms-of-service"
        termsOfServiceAgree = "yes"
        publishOnFailure()
    }
}
```

Find VERSION at https://plugins.gradle.org/plugin/com.gradle.enterprise

[**Build scan - the one Gradle trick that supersedes all the others**](https://dev.to/jmfayard/the-one-gradle-trick-that-supersedes-all-the-others-5bpg)

## Configure Gradle with gradle.properties

You need to put some magic property with some magic value in gradle.properties, but which one?

[**Configure Gradle with Gradle Properties**](https://dev.to/jmfayard/configuring-gradle-with-gradle-properties-211k)

## Set-up GitHub Actions with Gradle

A simple workflow to get you up and running with continuous integration.

- copy-paste `.github/worrkflows/runOnGitHub.yml`
- create a Gradle task called `runOnGitHub`
- create a pull request, and you are good to go!

[**How do I set up GitHub Actions for my Gradle or Android project?**](https://dev.to/jmfayard/how-do-i-setup-github-actions-for-my-gradle-or-android-project-3eal)

More complex workflows can be found in the
repositories [refreshVersions](https://github.com/jmfayard/refreshVersions/tree/main/.github/workflows)
and [Splitties](https://github.com/LouisCAD/Splitties/tree/main/.github/workflows)

## Gradle Settings

A Gradle project has [a Settings file](https://docs.gradle.org/current/userguide/build_lifecycle.html#sec:settings_file)
called `settings.gradle`  or `settings.gradle.kts` where you must respect a certain order (otherwise, the build breaks).

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

plugins { // Optional
    id("de.fayard.refreshVersions") version "0.10.0"
    // other plugins like the Gradle Entreprise plugin go here
}

refreshVersions { // Optional configuration

}

// Then you can have other code after the blocks above,

rootProject.name = "My Project" // Optional, defaults to parent dir's name.
include(":app") // If the project has modules/subprojects to declare.
```
