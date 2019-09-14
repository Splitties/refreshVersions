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
