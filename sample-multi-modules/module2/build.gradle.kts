plugins {
    java
    kotlin("jvm") version "1.4-M2"
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
    maven("https://dl.bintray.com/kotlin/kotlin-eap")
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(Square.retrofit2.retrofit)
    //implementation("se.jsimo.hello.maven:hello-world-maven:1.0.4-9-g716f2e1")
    //implementation("se.jsimo.hello.maven:hello-world-maven:_")
    testImplementation("junit", "junit", "4.12")
}
