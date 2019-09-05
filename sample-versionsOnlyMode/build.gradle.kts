import de.fayard.BuildSrcVersionsExtensionImpl
import de.fayard.BuildSrcVersionsTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import de.fayard.VersionsOnlyMode

plugins {
    kotlin("jvm") version "1.3.50"
    id("de.fayard.buildSrcVersions") version "0.5.0"
}
group = "de.fayard"

repositories {
    maven {
        setUrl("../plugin/src/test/resources/maven")
    }
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

VersionsOnlyMode.values().forEach { mode ->
    tasks.register<BuildSrcVersionsTask>(mode.name) {
        description = "buildSrcVersion - $mode"
        group = "Custom"
        val filename = mode.name + "." + mode.suggestedFilename().substringAfter(".")
        extension = BuildSrcVersionsExtensionImpl(
            versionsOnlyMode = mode,
            versionsOnlyFile = filename
        )
    }
}

tasks.register<DefaultTask>("checkAll") {
    description = "versionsOnlyMode - check all modes"
    group = "Custom"
    dependsOn(VersionsOnlyMode.values().map { it.name })
}
