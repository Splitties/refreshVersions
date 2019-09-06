pluginManagement {
    repositories {
        val localGradleRepo = "/Users/jmfayard/try/gradle-versions-plugin/build/repository"
        if (File(localGradleRepo).exists()) maven { setUrl(localGradleRepo) }
        gradlePluginPortal()
    }
}
rootProject.name = "sample-versionsOnlyMode"
includeBuild("../plugin")

