import de.fayard.OrderBy
import de.fayard.VersionsOnlyMode
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.lovedev.greeting.kotlin")
    id("de.fayard.refreshVersions")
    id("ch.tutteli.kotlin.utils")
    id("nebula.kotlin")
    kotlin("jvm")
    `build-scan`
}
group = "de.fayard"

buildSrcVersions {
    // See configuration options at https://github.com/jmfayard/buildSrcVersions/issues/53
    orderBy = OrderBy.GROUP_AND_ALPHABETICAL
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
    implementation("com.wealthfront:magellan:+")
    implementation("com.wealthfront:magellan-rx:+")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.0.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.13.0")
    implementation("com.jakewharton.timber:timber:4.7.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.withType<Wrapper> {
    gradleVersion = System.getenv("GRADLE_VERSION") ?: "5.6.1"
    distributionType = Wrapper.DistributionType.ALL
}

tasks.register<Copy>("copyReport") {
    from(".")
    include("report.json")
    into("build/dependencyUpdates")
}

tasks.register<DefaultTask>("hello") {
    group = "Custom"
}

tasks.register<DefaultTask>("checkAll") {
    description = "versionsOnlyMode - check all modes"
    group = "Custom"
    dependsOn(VersionsOnlyMode.values().map { it.name })
}

buildScan {
    setTermsOfServiceUrl("https://gradle.com/terms-of-service")
    setTermsOfServiceAgree("yes")
}

// How to update Gradle itself? https://github.com/jmfayard/buildSrcVersions/issues/19
tasks.withType<Wrapper> {
    gradleVersion = findProperty("gradleLatestVersion") as? String ?: gradle.gradleVersion
    distributionType = Wrapper.DistributionType.ALL
}
