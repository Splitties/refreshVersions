plugins {
    java
    kotlin("jvm") version "1.4-M2"
    id("org.gradle.hello-world")
}

version = "unspecified"

repositories {
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    mavenCentral()
}

useDependency()

dependencies {
    implementation(kotlin("stdlib"))
    implementation(Square.okHttp3.okHttp)
    testImplementation("junit", "junit", "4.12")
}
