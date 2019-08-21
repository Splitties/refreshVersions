plugins {
    idea
    `build-scan`
}

group = "de.fayard"
version = "0.4.0"
defaultTasks("run")

tasks.register("publishLocally") {
    group = "Custom"
    description = "Publish the plugin locally"
    dependsOn(gradle.includedBuild("buildSrcVersions-plugin").task(":publish"))
}

tasks.register("publishPlugins") {
    group = "Custom"
    description = "Publishes this plugin to the Gradle Plugin portal."
    dependsOn(gradle.includedBuild("buildSrcVersions-plugin").task(":publish"))
}

tasks.register("checkAll") {
    group = "Custom"
    description = "Run all checks"
    dependsOn(gradle.includedBuild("buildSrcVersions-plugin").task(":validateTaskProperties"))
    dependsOn(gradle.includedBuild("buildSrcVersions-plugin").task(":check"))
    dependsOn(gradle.includedBuild("sample-kotlin").task(":buildSrcVersions"))
    dependsOn(gradle.includedBuild("sample-groovy").task(":buildSrcVersions"))
}





buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
    publishAlways()
}
