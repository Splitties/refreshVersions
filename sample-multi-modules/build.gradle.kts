tasks.wrapper {
    val versionFile = rootDir.parentFile.resolve("plugins/gradle-version.txt")
    gradleVersion = versionFile.readLines().first()
}
