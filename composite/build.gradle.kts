plugins {
    `build-scan`
    idea
}

group = "de.fayard"
version = "0.5.0"
defaultTasks("run")

val PLUGIN: IncludedBuild = gradle.includedBuild("plugin")
val SAMPLE_KOTLIN: IncludedBuild = gradle.includedBuild("sample-kotlin")
val SAMPLE_GROOVY: IncludedBuild = gradle.includedBuild("sample-groovy")
val SAMPLE_VERSIONS_ONLY: IncludedBuild = gradle.includedBuild("sample-versionsOnlyMode")
val REFRESH_VERSIONS = ":refreshVersions"
val BUILD_SRC_VERSIONS = ":buildSrcVersions"

tasks.register("publishLocally") {
    group = "Custom"
    description = "Publish the plugin locally"
    dependsOn(":checkAll")
    dependsOn(PLUGIN.task(":publishToMavenLocal"))
}

tasks.register("publishPlugins") {
    group = "Custom"
    description = "Publishes this plugin to the Gradle Plugin portal."
    dependsOn(":publishLocally")
    dependsOn(":checkAll")
    dependsOn(PLUGIN.task(":publishPlugins"))
}

tasks.register("pluginTests") {
    group = "Custom"
    description = "Run plugin unit tests"
    dependsOn(PLUGIN.task(":check"))
}

tasks.register("checkAll") {
    group = "Custom"
    description = "Run all checks"
    dependsOn(SAMPLE_VERSIONS_ONLY.task(REFRESH_VERSIONS))
    dependsOn(SAMPLE_KOTLIN.task(REFRESH_VERSIONS))
    dependsOn(SAMPLE_GROOVY.task(REFRESH_VERSIONS))
    dependsOn(SAMPLE_KOTLIN.task(BUILD_SRC_VERSIONS))
    dependsOn(SAMPLE_GROOVY.task(BUILD_SRC_VERSIONS))
    dependsOn(PLUGIN.task(":validateTaskProperties"))
    dependsOn(PLUGIN.task(":check"))
    dependsOn(SAMPLE_VERSIONS_ONLY.task(":checkAll"))
}


tasks.register("updateGradle") {
    group = "Custom"
    description = "Run all checks"
    dependsOn(":wrapper")
    dependsOn(PLUGIN.task(":wrapper"))
    dependsOn(SAMPLE_KOTLIN.task(":wrapper"))
    dependsOn(SAMPLE_GROOVY.task(":wrapper"))
    dependsOn(SAMPLE_VERSIONS_ONLY.task(":wrapper"))
}
tasks.withType<Wrapper> {
    gradleVersion = System.getenv("GRADLE_VERSION") ?: "5.6.1"
    distributionType = Wrapper.DistributionType.ALL
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
    publishAlways()
}
