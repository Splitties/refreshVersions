import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("de.fayard.buildSrcVersions")
    kotlin("jvm")
    `build-scan`
}

group = "de.fayard"


tasks.register<DefaultTask>("hello") {
    group = "Custom"
}
repositories {
    maven {
        setUrl("../plugin/src/test/resources/maven")
    }
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:15.0")
    implementation("com.google.inject:guice:2.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

// How to update Gradle itself? https://github.com/jmfayard/buildSrcVersions/issues/19
tasks.withType<Wrapper> {
    gradleVersion = Versions.gradleLatestVersion
    distributionType = Wrapper.DistributionType.ALL
}

buildSrcVersions {
    useFqdnFor("dependency")
    renameLibs = "Libs"
    renameVersions = "Versions"
//    indent = "  "
    rejectVersionIf {
        isNonStable(candidate.version) && isNonStable(currentVersion).not()
    }
}


buildScan {
    setTermsOfServiceUrl("https://gradle.com/terms-of-service")
    setTermsOfServiceAgree("yes")
}
