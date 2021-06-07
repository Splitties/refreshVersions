import Testing.JunitJupiter.api
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
    `maven-publish`
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
//    api(project(":subproject"))
//    api(project(":subproject2"))

//    api("org.apache.poi:poi:_")
//    api("org.apache.poi:poi-ooxml:_")
}
