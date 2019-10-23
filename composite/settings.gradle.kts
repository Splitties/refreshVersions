rootProject.name = "buildSrcVersions-composite"
includeBuild("../plugin")
includeBuild("../sample-kotlin")
includeBuild("../sample-groovy")

// ../sample-android should be opened in Android Studio
//Adding
//   includeBuild("../sample-android")
// would fail with the error:
//    New Gradle Sync is not supported due to containing Kotlin modules
