plugins {
    java
    kotlin("jvm")
    id("org.gradle.hello-world")
}

version = "unspecified"

repositories {
    mavenCentral()
}

useDependency()

dependencies {
    implementation(Square.okHttp3.okHttp)
    testImplementation("junit", "junit", "4.12")
    implementation("com.github.javadev:okio:1.6.0")
}
