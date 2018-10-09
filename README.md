# gradle-kotlin-dsl-libs


Pain-less gradle dependencies management with the kotlin-dsl

Blog article
---

https://blog.kotlin-academy.com/gradle-kotlin-1-7528a85f0d2c

Screencast 
---

https://github.com/jmfayard/gradle-kotlin-dsl-libs


Step 0: 
---

Use either your own project or this  sample repository

`$ git clone` https://github.com/jmfayard/sample-synclibs



Step1: Edit build.gradle(.kts)
---- 

```groovy
plugins {
  // https://plugins.gradle.org/plugin/jmfayard.github.io.gradle-kotlin-dsl-libs
  id("jmfayard.github.io.gradle-kotlin-dsl-libs") version "0.2.3"
}
```



Step2
----


`$ ./gradlew syncLibs`

`$ cat ` [buildSrc/src/main/kotlin/Libs.kt](https://github.com/jmfayard/sample-synclibs/blob/0054d42685ca50638c2e05670426b30323d7f6a8/buildSrc/src/main/java/Libs.kt)


Step3: 
----

Replace your dependency strings with Libs.xxx

```kotlin
dependencies {
  implementation(Libs.okio)
  implementation(Libs.okhttp_apache)
  implementation(Libs.rxjava)
}
```

Enjoy the deep integration of gradle kotlin dsl with your IDE





