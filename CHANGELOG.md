
# 0.2.3

- Change folder to `buildSrc/src/main/kotlin` see [separate language source files](https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:separate_language_source_files)  
- Create empty `buildSrc/settings.gradle.kts` if none exists
- Use tasks.register to optimize configuration time

# 0.2.2

- `Versions` is now in a separate file `buildSrc/src/main/java/Versions.kt` (this way it works out of the box from a groovy build.gradle file)
- Use FDQN when we detect a meaning-less names like `runner`, `core`, `test`, ...

# 0.2.1

- Version demoed at KotlinConf

# 0.1.0

- Hello world version released to plugins.gradle.org
