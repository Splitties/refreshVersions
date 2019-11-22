import dependencies.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("de.fayard.refreshVersions").version("0.8.2")
    id("de.fayard.dependencies").version("0.3")
    id("com.louiscad.splitties").version("0.1.3")
    kotlin("jvm").version("1.3.50")
    application
}
group = "de.fayard.experimental"
version = "1.0-SNAPSHOT"

refreshVersions {
    rejectVersionIf {
        //candidateStabilityLevel() isLessStableThan StabilityLevel.Stable
        false
    }
}

dependencies {
    implementation(KotlinX.coroutines.core)
    testImplementation(Testing.kotestKtor)
    testImplementation(Testing.kotestKoin)

}

repositories {
    mavenCentral()
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
}


tasks.named<JavaExec>("run") {
    main = "de.fayard.experimental.HelloKt"
}

tasks.register("hello") {
    group = "custom"
    description = "Empty Hello World task, useful to debug build problems"
}

tasks.named<Delete>("clean") {
    group = "custom"
    description = "Delete build files"
    delete = setOf(".gradle", ".idea", "**.iml", "build", "app/build")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
