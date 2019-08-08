import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.11"
    id("de.fayard.buildSrcVersions") version "0.4.0"
}

group = "de.fayard"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation("com.squareup.okhttp3:okhttp:3.12.1")
    implementation("com.squareup.okio:okio:2.0.0")

    //implementation("io.fabric8:kubernetes-client:3.1.12.fuse-730005")
    //implementation("ru.ztrap.iconics:core-ktx:1.0.3")

}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


buildSrcVersions {
    useFdqnFor.set(listOf("core-ktx"))
}
