- Run task `:checkAll` and verify output
- Search in the whole project for: plugin.de.fayard.buildSrcVersions  
- Update all versions`plugin/build.gradle.kts`
- Run task `:publishPlugins` 
- Check plugin portal https://plugins.gradle.org/plugin/de.fayard.buildSrcVersions
- Update CHANGELOG
- Create a GitHub release https://github.com/jmfayard/buildSrcVersions/releases/new
- Try the plugin in sample projects
- Update `:plugin_version:` in `README.adoc`
- Search for `plugin.de.fayard.refreshVersions` everywhere to update the version
- Otherwise update the README
- Update Plugin Configuration https://github.com/jmfayard/buildSrcVersions/issues/53

Don't hard-code a version in the articles and tickets, instead use this template:


[![pluginVersion](https://img.shields.io/maven-metadata/v/https/plugins.gradle.org/m2/de.fayard/buildSrcVersions/de.fayard.buildSrcVersions.gradle.plugin/maven-metadata.xml.svg?label=gradlePluginPortal)](https://plugins.gradle.org/plugin/de.fayard.buildSrcVersions)

```kotlin
plugins {
  id("de.fayard.buildSrcVersions") version "$VERSION"
}
/**
 * Use ./gradlew refreshVersions to find available updates
 * See https://github.com/jmfayard/buildSrcVersions/issues/77
 
* Use ./gradlew buildSrcVersions to generate buildSrc/src/main/Libs.kt
 * See https://github.com/jmfayard/buildSrcVersions/issues/88
 */
buildSrcVersions {
    // See configuration options at https://github.com/jmfayard/buildSrcVersions/issues/53    
}
```

The configuration options are described in [#53 Plugin Configuration](https://github.com/jmfayard/buildSrcVersions/issues/53)

You can find the latest `$VERSION` at https://plugins.gradle.org/plugin/de.fayard.buildSrcVersions
