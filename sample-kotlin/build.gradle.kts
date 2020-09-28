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
}

group = "de.fayard"

repositories {
    mavenLocal()
    mavenCentral()
    google()
    jcenter()
    maven(url = "https://dl.bintray.com/kotlin/kotlin-eap/")
}

fun DependencyHandler.implementations(deps: List<String>) =
    deps.forEach { implementation(it) }

fun DependencyHandler.testImplementations(deps: List<String>) =
    deps.forEach { testImplementation(it) }

dependencies {
    implementations(listOf(AndroidX.browser, AndroidX.cardView))
    implementation(AndroidX.core)
    testImplementations(listOf(KotlinX.coroutines.core, KotlinX.coroutines.jdk8))
    testImplementation(Testing.kotest.runner.junit4)
    testImplementation("junit:junit:4.12")
    implementation("com.google.guava:guava:15.0")
    implementation("com.google.inject:guice:2.0")
    implementation("com.squareup.okhttp3:okhttp:3.10.0")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:3.10.0")
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.mongodb:mongo-java-driver:3.11.0")
    implementation(kotlin("script-runtime"))

    api("org.apache.poi:poi:_")
    api("org.apache.poi:poi-ooxml:_")
}

tasks.register("run", JavaExec::class.java) {
    this.main = "de.fayard.GuavaTest"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType(JavaExec::class.java) {
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.register<DefaultTask>("hello") {
    group = "Custom"
    description = "Minimal task that do nothing. Useful to debug a failing build"
}
