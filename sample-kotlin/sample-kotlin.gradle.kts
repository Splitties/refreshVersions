import de.fayard.refreshVersions.core.versionFor
import org.jetbrains.kotlin.gradle.plugin.getKotlinPluginVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
    dependencies {
        classpath("org.gradle:gradle-hello-world-plugin:_")
    }
}

plugins {
    kotlin("jvm")
    id("org.jetbrains.kotlinx.benchmark")
}

group = "de.fayard"

val testGcs = providers.gradleProperty("refreshVersions.testGcs").forUseAtConfigurationTime().orNull == "true"

repositories {
    mavenLocal()
    mavenCentral()
    google()
    if (testGcs) maven(url = "gcs://refreshversions-testing/maven")
}



dependencies {
    if (testGcs) implementation("com.example:dummy-library-for-testing:_")
    implementation(AndroidX.core)
    testImplementation(KotlinX.coroutines.core)
    testImplementation(KotlinX.coroutines.jdk8)
    testImplementation(Testing.kotest.runner.junit4)
    testImplementation("junit:junit:4.12")
    implementation("com.google.guava:guava:15.0")
    implementation("com.google.inject:guice:2.0")
    implementation("com.squareup.okhttp3:okhttp:3.10.0")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:3.10.0")
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.mongodb:mongo-java-driver:3.11.0")
    implementation(kotlin("script-runtime"))



    // logging
    implementation(platform("org.apache.logging.log4j:log4j-bom:2.17.2"))
    runtimeOnly("org.apache.logging.log4j:log4j-slf4j-impl")
    runtimeOnly("org.apache.logging.log4j:log4j-jul")
}

/*
Reproducing steps for https://github.com/jmfayard/refreshVersions/issues/541

$ ./gradlew compileKotlin
> Could not resolve all files for configuration ':compileClasspath'.
> Could not find org.apache.poi:poi:_.

See stacktrace here https://scans.gradle.com/s/77ijrlpzd2kby/failure#1
 */
val apachePoi = "org.apache.poi:poi:_"
val apachePoiXml = "org.apache.poi:poi-ooxml:_"
dependencies {
    api(apachePoi)
    api(apachePoiXml)
}
configurations.all {
    resolutionStrategy.force(apachePoiXml)
    resolutionStrategy.force(apachePoi)
}

getKotlinPluginVersion().let {
    val kotlinStdlibVersion = versionFor(dependencyNotation = Kotlin.stdlib)
    check(it == kotlinStdlibVersion) {
        "Unexpected mismatch between the version of the Kotlin plugin and the stdlib. " +
            "Is the versionFor function implementation correct?" +
            "Got respectively $it and $kotlinStdlibVersion"
    }
    val kotlinVersion = versionFor(versionKey = "version.kotlin")
    check(it == kotlinVersion) {
        "Unexpected mismatch between the version of the Kotlin plugin and the one from versions.properties. " +
            "Is the versionFor function implementation correct?" +
            "Got respectively $it and $kotlinStdlibVersion"
    }
}

tasks.register<JavaExec>("run") {
    mainClass.set("de.fayard.GuavaTest")
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<JavaExec>().configureEach {
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.register<DefaultTask>("hello") {
    group = "Custom"
    description = "Minimal task that do nothing. Useful to debug a failing build"
}
