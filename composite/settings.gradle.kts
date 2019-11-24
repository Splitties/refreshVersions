rootProject.name = "gradle-dependencies-plugins"
includeBuild("../refreshVersions")
includeBuild("../sample-kotlin")
includeBuild("../sample-groovy")
includeBuild("../dependencies")

// ../sample-android should be opened in Android Studio
//Adding
//   includeBuild("../sample-android")
// would fail with the error:
//    New Gradle Sync is not supported due to containing Kotlin modules
