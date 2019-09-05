plugins {
    idea
    `build-scan`
}

group = "de.fayard"
version = "0.5.0"
defaultTasks("run")

val PLUGIN: IncludedBuild = gradle.includedBuild("buildSrcVersions-plugin")
val SAMPLE_KOTLIN: IncludedBuild = gradle.includedBuild("sample-kotlin")
val SAMPLE_GROOVY: IncludedBuild = gradle.includedBuild("sample-groovy")
val SAMPLE_VERSIONS_ONLY: IncludedBuild = gradle.includedBuild("sample-versionsOnlyMode")

tasks.register("publishLocally") {
    group = "Custom"
    description = "Publish the plugin locally"
    dependsOn(PLUGIN.task(":publish"))
}

tasks.register("publishPlugins") {
    group = "Custom"
    description = "Publishes this plugin to the Gradle Plugin portal."
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
    dependsOn(PLUGIN.task(":validateTaskProperties"))
    dependsOn(PLUGIN.task(":check"))
    dependsOn(SAMPLE_KOTLIN.task(":buildSrcVersions"))
    dependsOn(SAMPLE_GROOVY.task(":buildSrcVersions"))
    dependsOn(SAMPLE_VERSIONS_ONLY.task(":checkAll"))
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
    publishAlways()
}
