@file:Suppress("UnstableApiUsage")

plugins {
    java
    kotlin("jvm")
}

version = "unspecified"

repositories {
    /*maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/jesigloot/maven-hello-world")
        credentials {
            username = TODO()
            password = TODO()
        }
    }*/
    mavenCentral()
}

dependencies {
    implementation(Libs.kotlin_stdlib)
    implementation(Square.retrofit2.retrofit)
    implementation(KotlinX.coroutines.core)

    //implementation("se.jsimo.hello.maven:hello-world-maven:1.0.4-9-g716f2e1")
    //implementation("se.jsimo.hello.maven:hello-world-maven:_")

    implementation(Libs.clikt)

    testImplementation(Testing.junit.jupiter.api)
    testImplementation(Testing.junit.jupiter.engine)
    testImplementation(Testing.junit.jupiter)
    testImplementation(Testing.junit.jupiter.params)
    testImplementation(Testing.kotest.assertions.core)
    testImplementation(Testing.kotest.assertions.json)
    testImplementation(Testing.kotest.assertions.jsoup)
    testImplementation(Testing.kotest.assertions.sql)
    testImplementation(Testing.kotest.core)

    testImplementation(Libs.junit)
}
