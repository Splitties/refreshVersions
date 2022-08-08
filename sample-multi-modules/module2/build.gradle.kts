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
    testImplementation(Testing.mockK.common)
    testImplementation(Testing.mockK)
    testImplementation(Testing.mockito.core)
    testImplementation(Testing.mockito.errorProne)
    testImplementation(Testing.mockito.junitJupiter)
    testImplementation(Testing.mockito.kotlin)
    testImplementation(Testing.mockito.inline)

}
