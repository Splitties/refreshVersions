# gradle-kotlin-dsl-libs


Pain-less gradle dependencies management with the kotlin-dsl

<big>**[Screencast Demonstration](https://www.useloom.com/share/7defb57f3a85412e9e1a68f987e3d9b3)**</big>



Step1: Edit build.gradle(.kts)
---- 

```groovy
plugins {
  // https://plugins.gradle.org/plugin/jmfayard.github.io.gradle-kotlin-dsl-libs
  id("jmfayard.github.io.gradle-kotlin-dsl-libs") version "0.2.2"
}
```

Alternatively, use the "old" syntax

```groovy
// https://plugins.gradle.org/plugin/jmfayard.github.io.gradle-kotlin-dsl-libs
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "jmfayard.github.io:gradle-kotlin-dsl-libs:0.2.2"
  }
}

apply plugin: "jmfayard.github.io.gradle-kotlin-dsl-libs"

```



Step2
----


`$ ./gradlew syncLibs`

`$ cat ` [buildSrc/src/main/java/Libs.kt](https://github.com/jmfayard/sample-synclibs/blob/0054d42685ca50638c2e05670426b30323d7f6a8/buildSrc/src/main/java/Libs.kt)


Step3: 
----

Replace your dependency strings with Libs.xxx

```kotlin
dependencies {
  compile(Libs.okio)
  compile(Libs.okhttp_apache)
  compile(Libs.rxjava)
}
```

Enjoy the deep integration of gradle kotlin dsl with your IDE

https://www.useloom.com/share/7defb57f3a85412e9e1a68f987e3d9b3




