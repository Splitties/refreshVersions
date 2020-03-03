# Issues

This is a work in progress used to discover issues with setting up refreshVersions and dependencies

See my attempts here

https://github.com/jmfayard/pentagame/commits/jmfayard-refreshVersions

https://github.com/jmfayard/k-9/commits/jmfayard-refreshVersions

# Links

refreshVersions expect dependencies with a version placeholder _
https://github.com/jmfayard/refreshVersions/issues/160

Upgrading an old project to Gradle 6 and AGP 3.5.3 #156
https://github.com/jmfayard/refreshVersions/issues/156

Configuring Gradle with "gradle.properties"
https://dev.to/jmfayard/configuring-gradle-with-gradle-properties-211k

Upgrading your build from Gradle 5.x to 6.0
https://docs.gradle.org/current/userguide/upgrading_version_5.html

Upgrading your build from Gradle 4.x to 5.0
https://docs.gradle.org/current/userguide/upgrading_version_4.html

Gradle plugin (build-scan) manual
https://docs.gradle.com/enterprise/gradle-plugin/

# How to install refreshVersions

# Update to Gradle 6

```
$ ./gradle --scan help
Deprecated Gradle features were used in this build, making it incompatible with Gradle 6.0.

$ ./gradle wrapper --gradle-version 4.10.2
$ ./gradle wrapper --gradle-version 5.6.4
$ ./gradle wrapper --gradle-version 6.2

```

Look at [the deprecations view of the generated build scan](https://gradle.com/enterprise/releases/2018.4). If there are no warnings, the Deprecations tab will not appear. Deprecated usages information requires Gradle 4.10+ and build scan plugin 1.16+.


# Edit "settings.gradle"

```groovy
import de.fayard.dependencies.DependenciesPlugin
import de.fayard.versions.RefreshVersionsSetup

pluginManagement {
    repositories {
        mavenLocal()
        gradlePluginPortal()
    }
}

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies.classpath("de.fayard:dependencies:0.5.6")
}


RefreshVersionsSetup.bootstrap(settings, DependenciesPlugin.artifactVersionKeyRules)
```

# Edit "settings.gradle.kts"

```
import de.fayard.versions.bootstrapRefreshVersions
import de.fayard.dependencies.DependenciesPlugin

buildscript {
    repositories {
        gradlePluginPortal()
    }
    dependencies.classpath("de.fayard:dependencies:0.5.6")
}

bootstrapRefreshVersions(DependenciesPlugin.artifactVersionKeyRules)
```

# Check gradle.properties

```
# gradle.properties
org.gradle.caching=true
org.gradle.parallel=true
kotlin.code.style=official

# Android
android.enableJetifier=true
android.useAndroidX=true

```

# Caveats


# Roadmap


# Issues you may encounter


Task 'refreshVersions' not found in root project 'k-9'.
https://gradle.com/s/fz6lzvzkorng6


https://gradle.com/s/aa2bpcr465xee

```
An exception occurred applying plugin request [id: 'de.fayard.refreshVersions']
> Failed to apply plugin [id 'de.fayard.refreshVersions']
   > lateinit property privateArtifactVersionKeyReader has not been initialized
```


## gradleEnterprise / build scan

```
// https://dev.to/jmfayard/the-one-gradle-trick-that-supersedes-all-the-others-5bpg
// https://docs.gradle.com/enterprise/gradle-plugin/
plugins {
   id("com.gradle.enterprise").version("3.1.1")
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

