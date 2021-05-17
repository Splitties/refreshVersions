import de.fayard.refreshVersions.bootstrapRefreshVersionsForBuildSrc

buildscript {
    repositories {
        gradlePluginPortal()
        //maven(url = "https://dl.bintray.com/jmfayard/maven")
    }
    dependencies.classpath("de.fayard.refreshVersions:refreshVersions:€{currentVersion}")
}

bootstrapRefreshVersionsForBuildSrc()
