plugins {
    idea
    `build-scan`
}

group = "de.fayard"
version = "0.4.3"
defaultTasks("run")


tasks.register("publishLocally") {
    group = "Custom"
    description = "Publish the plugin locally"
    dependsOn(gradle.includedBuild("buildSrcVersions").task(":publish"))
}
tasks.register("publishPlugins") {
    group = "Custom"
    description = "Publishes this plugin to the Gradle Plugin portal."
    dependsOn(gradle.includedBuild("buildSrcVersions").task(":publishPlugins"))
}

tasks.register("checkAll") {
    group = "Custom"
    description = "Run all checks"
    dependsOn(gradle.includedBuild("buildSrcVersions").task(":validateTaskProperties"))
    dependsOn(gradle.includedBuild("buildSrcVersions").task(":check"))
    dependsOn(gradle.includedBuild("sample-kotlin").task(":buildSrcVersions"))
    dependsOn(gradle.includedBuild("sample-groovy").task(":buildSrcVersions"))
}





buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
    publishAlways()
}
