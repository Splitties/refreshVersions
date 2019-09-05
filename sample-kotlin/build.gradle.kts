import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
plugins {
    kotlin("jvm") version "1.3.11"
    id("de.fayard.buildSrcVersions") version "0.4.3"
}

group = "de.fayard"

repositories {
    maven {
        setUrl("../plugin/src/test/resources/maven")
    }
}

dependencies {
    implementation("com.google.guava:guava:15.0")
    implementation("com.google.inject:guice:2.0")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


buildSrcVersions {
    useFdqnFor = mutableListOf()
    renameLibs = "Libs"
    renameVersions = "Versions"
    indent = "  "
    rejectedVersionKeywords("alpha", "beta", "rc", "cr", "m", "preview", "eap")
}
