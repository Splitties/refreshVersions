## gradle-kotlin-dsl-libs: painless dependencies management 


# Why?

Managing dependencies is a boring, painful part of a programmer's life. 

To qualify as truly painless, my ideal dependency management system would have to

- ✅ not make me work with magic strings
- ✅ allow me to reuse the dependencies information across modules
- ✅ have good IDE support (auto-completion, jumping to the definition, quick documentation, ...)
- ✅ provide a helpful link to the website so that I can quickly look-up what this thing does, where the documentation is, what the CHANGELOG says, ...
- ✅ inform me what newer versions of that dependency are available
- ✅ do all of the above automatically
- ✅ allow me to easily update the dependency version manually
- ✅ never do that automatically

We all have tried different iterations to make working with dependencies better.

`gradle-kotlin-dsl-libs` is my attempt at checking **all** those checkboxes.

# What?

`gradle-kotlin-dsl-libs` is a Gradle plugin that you add to your builds. 

It extends the [gradle-versions-plugin](https://github.com/ben-manes/gradle-versions-plugin) that produce a [report.json file with information about all your dependencies](doc/report.json)

It then automatically generates two Kotlin files that you can use in your build: 

- [buildSrc/src/main/kotlin/Libs.kt](doc/Libs.kt)  
- [buildSrc/src/main/kotlin/Versions.kt](doc/Versions.kt) 

The IDE integration comes by the virtue of the Kotlin support in Gradle and Android Studio & IntelliJ. 

Screencast: https://www.useloom.com/share/7edceb83fd594f319356240fcce304d5

 
Blog article: [How Kotlin makes editing your Gradle build less frustrating](https://blog.kotlin-academy.com/gradle-kotlin-the-missing-piece-of-the-puzzle-7528a85f0d2c)
 

# How?

## Step 0


Use either your own project or this sample repository

`$ git clone` https://github.com/jmfayard/sample-synclibs



## Step 1: Apply the plugin

Edit your root `build.gradle(.kts)` file

```groovy
// ./build.gradle
// don't put anything before the buildscript and plugins blocks!
buildscript { 
   //...
}
plugins {
  // Find latest version at https://plugins.gradle.org/plugin/jmfayard.github.io.gradle-kotlin-dsl-libs
  id("jmfayard.github.io.gradle-kotlin-dsl-libs") version "0.2.4" // $ ./gradlew syncLibs
}
```


## Step 2: Generate the code


```
$ ./gradlew syncLibs
# give him time to find all the newer versions

$ find buildSrc -name "*.kt*"
buildSrc/build.gradle.kts
buildSrc/settings.gradle.kts
buildSrc/src/main/kotlin/Libs.kt
buildSrc/src/main/kotlin/Versions.kt

```

Repeat this step whenever you want to check if newer versions are available. 

## Step 3: Use the generated code 


Replace your dependency strings with Libs.xxx

```kotlin
dependencies {
  implementation(Libs.okio)
  implementation(Libs.okhttp_apache)
  implementation(Libs.rxjava)
}
```

Enjoy the deep integration of gradle kotlin dsl with your IDE



## Step 4: Update your dependencies

You can run again anytime the `$ ./gradlew syncLibs` task

Edit then the file: `buildSrc/src/main/kotlin/Versions.kt`  

