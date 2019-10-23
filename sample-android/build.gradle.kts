import de.fayard.OrderBy
//TODO: find another solution to this problem https://gradle.com/s/7rm5h3bk6fzzq

plugins {
    id("de.fayard.refreshVersions")
    `build-scan`
}
group = "de.fayard"

subprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
    }
}

refreshVersions {
    // See configuration options at https://github.com/jmfayard/buildSrcVersions/issues/53
    orderBy = OrderBy.GROUP_AND_ALPHABETICAL
    propertiesFile = "versions.properties"
    //alignVersionsForGroups = listOf()
}

tasks.register<DefaultTask>("hello") {
    group = "Custom"
}

buildScan {
    setTermsOfServiceUrl("https://gradle.com/terms-of-service")
    setTermsOfServiceAgree("yes")
    publishAlways()
}
