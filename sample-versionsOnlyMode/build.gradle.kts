import de.fayard.BuildSrcVersionsTask
import de.fayard.VersionsOnlyMode
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.lovedev.greeting.kotlin")
    id("de.fayard.buildSrcVersions")
    id("ch.tutteli.kotlin.utils")
    id("nebula.kotlin")
    kotlin("jvm")
    `build-scan`
}
group = "de.fayard"

buildSrcVersions {
    // See configuration options at https://github.com/jmfayard/buildSrcVersions/issues/53
    indent = "     "
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
    implementation("com.wealthfront:magellan:_")
    implementation("com.wealthfront:magellan-rx:+")
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

VersionsOnlyMode.values().forEach { mode ->
    if (mode == VersionsOnlyMode.GRADLE_PROPERTIES) {
        tasks.register<DefaultTask>(mode.name)
        return@forEach
    }

    tasks.register<BuildSrcVersionsTask>(mode.name) {
        description = "buildSrcVersion - $mode"
        group = "Custom"
        dependsOn(":copyReport")
        val filename = mode.name + "." + mode.suggestedFilename().substringAfter(".")
        configure {
            versionsOnlyFile = filename
            versionsOnlyMode = mode
        }
    }
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
