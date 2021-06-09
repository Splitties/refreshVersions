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
}

group = "de.fayard"

repositories {
    mavenLocal()
    mavenCentral()
    google()
}

fun DependencyHandler.implementations(deps: List<String>) =
    deps.forEach { implementation(it) }

fun DependencyHandler.testImplementations(deps: List<String>) =
    deps.forEach { testImplementation(it) }


dependencies {
    api("de.fayard:included")
//    api("de.fayard:subproject")
//    api("de.fayard:subproject2")

    implementations(listOf(AndroidX.browser, AndroidX.cardView))
    implementation(AndroidX.core)
    implementation("com.google.guava:guava:15.0")
    implementation(kotlin("stdlib-jdk8"))
    implementation(kotlin("script-runtime"))

    testImplementations(listOf(KotlinX.coroutines.core, KotlinX.coroutines.jdk8))
    testImplementation(Testing.kotest.assertions.core)
    testImplementation(platform(notation = "org.junit:junit-bom:_"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher") {
        because("allows tests to run from IDEs that bundle older version of launcher")
    }

    api("org.apache.poi:poi:_")
    api("org.apache.poi:poi-ooxml:_")
}


getKotlinPluginVersion().let {
    val kotlinStdlibVersion = versions.versionFor(dependencyNotation = Kotlin.stdlib)
    check(it == kotlinStdlibVersion) {
        "Unexpected mismatch between the version of the Kotlin plugin and the stdlib. " +
            "Is the versionFor function implementation correct?" +
            "Got respectively $it and $kotlinStdlibVersion"
    }
    val kotlinVersion = versions.versionFor(versionKey = "version.kotlin")
    check(it == kotlinVersion) {
        "Unexpected mismatch between the version of the Kotlin plugin and the one from versions.properties. " +
            "Is the versionFor function implementation correct?" +
            "Got respectively $it and $kotlinStdlibVersion"
    }
}

tasks.register("run", JavaExec::class.java) {
    this.main = "de.fayard.GuavaTest"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType(JavaExec::class.java) {
    classpath = sourceSets["main"].runtimeClasspath
}

tasks.register<DefaultTask>("hello") {
    group = "Custom"
    description = "Minimal task that do nothing. Useful to debug a failing build"
}
