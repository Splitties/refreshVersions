import de.fayard.BuildSrcVersionsExtensionImpl
import de.fayard.BuildSrcVersionsTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import de.fayard.VersionsOnlyMode

plugins {
    kotlin("jvm") version "1.3.50"
    id("de.fayard.buildSrcVersions") version "0.4.3"
}
group = "de.fayard"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8", "1.3.50"))
    implementation("com.squareup.okhttp3:okhttp:4.1.0")
    implementation("com.squareup.okio:okio:2.0.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
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
