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

buildSrcVersions {
    indent = "\t"
    versionsOnlyMode = VersionsOnlyMode.KOTLIN_VAL
    versionsOnlyFile = "build.gradle.kts"
}
