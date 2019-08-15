
## Unreleased


# 0.4.1

- #47 plugins { buildSrcVersions } allows to update the plugin itself
- Update to Kotlinpoet 1.3.0

# 0.4.0

- IDE integration works again in Android 3.5 (bug #123032843)
- Update to gradle-versions-plugin 0.22
- Upgradle to Gradle 5.5.1
- #43 by runningcode: Remove whitespace at the end of lines which do not include a comment. 
- #41 by lebeshev: use fully qualified name for: "library", "generator", "annotations" (breaking change)
- #42 do not create buildSrc/settings.gradle.kts, check if buildSrc/build.gradle exists before creating buildSrc/build.gradle.kts
- Don't include version which include "-eap-"
- Upgraded plugin to a Gradle composite project allowing for a better developer workflow #31
- Updated CONTRIBUTING.md accordingly
- Introduced samples `sample-groovy` and `sample-kotlin` inside the project
- Handle case where a dependency has no version #23
- To update Gradle itself, see issue #19
- Use `mavenCentral()` by default in `buildSrc/build.gradle.kts`
- Fix `Versions.kt` not compiling when the version name is very large #36
- Do not recreate `buildSrc/.gitignore` if the user removes it #37

 

# 0.3.2

- Invalid Comment linebreak #32
- Don't inform about exceeded version number #29 

# 0.3.1

- Allows to configure a set of dependencies for which to use the FDQN #25 

```kotlin
buildSrcVersions {
    useFdqnFor.set(listOf("core-kt", "core-ktx"))
}
```

# 0.3.0

**Rebranding**: the repository, the plugin and the task are now called buildSrcVersions!!

Why? and What? is described in details here https://github.com/jmfayard/buildSrcVersions/issues/26


- Clean-up task group/description/output
- [Fix #561 use non-breaking spaces in line comments](https://github.com/square/kotlinpoet/issues/561) 
- Extrace-space at the end of Versions.kt properties #24
 



# 0.2.6 Polish Versions.kt (see issue #14)

- remove misleading "up-to-date" comment 
- avoid pulling in RC and alpha versions to the available tag
- remove the website info, we'd like absolutely nothing in the versions file apart from the name, the version number
- edit KDOC to document that the plugin update the comment but YOU edit the version



# 0.2.5

- #10 Detect when multiple dependencies from the same group use the same version 
- #8 Order dependencies by group


# 0.2.4

- #4 SyncLibs generate wrong output file
- #1 Make it slightly easier to manually update the version 
- #3 Add buildSrc/.gitignore

 

# 0.2.3

- Change folder to `buildSrc/src/main/kotlin` see [separate language source files](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:separate_language_source_files)  
- Create empty `buildSrc/settings.gradle.kts` if none exists

# 0.2.2

- `Versions` is now in a separate file `buildSrc/src/main/java/Versions.kt` (this way it works out of the box from a groovy build.gradle file)
- Use FDQN when we detect a meaning-less names like `runner`, `core`, `test`, ...

# 0.2.1

- Version demoed at KotlinConf

# 0.1.0

- Hello world version released to plugins.gradle.org
