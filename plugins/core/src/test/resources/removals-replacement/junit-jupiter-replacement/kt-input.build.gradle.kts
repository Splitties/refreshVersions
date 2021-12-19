plugins {
    java
    kotlin("jvm") version "1.4-M2"
}

version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation(Libs.kotlin_stdlib)
    implementation(Square.retrofit2.retrofit)
    implementation(KotlinX.coroutines.core)

    implementation(Libs.clikt)
    implementation(Ktor.client.auth)
    implementation(Ktor.client.authBasic)
    implementation(Ktor.client.core)
    implementation(Ktor.client.cio)
    implementation(Ktor.client.json)
    implementation(Ktor.client.okHttp)
    implementation(Ktor.client.serialization)
    implementation(Ktor.client.logging)
    implementation(Ktor.client.encoding)
    implementation(Ktor.client.websockets)
    implementation(Ktor.http)
    implementation(Ktor.features.htmlBuilder)
    implementation(Ktor.features.auth)
    implementation(Ktor.network.network)
    implementation(Ktor.network.tls)
    implementation(Ktor.server.core)
    implementation(Ktor.utils)

    testImplementation(Testing.junit.api)
    testImplementation(Testing.junit.engine)
    testImplementation(Testing.junit)
    testImplementation(Testing.junit.params)
    testImplementation(Testing.kotest.assertions.core)
    testImplementation(Testing.kotest.assertions.json)
    testImplementation(Testing.kotest.assertions.jsoup)
    testImplementation(Testing.kotest.assertions.sql)
    testImplementation(Testing.kotest.core)
    testImplementation(Testing.kotest.extensions.http)
    testImplementation(Testing.kotest.extensions.koin)
    testImplementation(Testing.kotest.extensions.mockServer)
    testImplementation(Testing.kotest.plugins.piTest)
    testImplementation(Testing.kotest.property)
    testImplementation(Testing.kotest.runner.junit4)
    testImplementation(Testing.mockK.common)
    testImplementation(Testing.mockK)
    testImplementation(Testing.mockito.core)
    testImplementation(Testing.mockito.errorProne)
    testImplementation(Testing.mockito.junitJupiter)
    testImplementation(Testing.mockito.kotlin)
    testImplementation(Testing.mockito.inline)

    testImplementation(Libs.junit)
}
