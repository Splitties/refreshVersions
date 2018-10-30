
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
