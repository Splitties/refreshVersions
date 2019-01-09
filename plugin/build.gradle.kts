import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    `maven-publish`
    `build-scan`
    id("com.gradle.plugin-publish") version "0.10.0"
}

group = "de.fayard"
version = "0.3.2"

gradlePlugin {
    plugins {
        create("buildSrcVersions") {
            id = "de.fayard.buildSrcVersions"
            displayName = "buildSrcVersions"
            description = "Painless dependencies management"
            implementationClass = "de.fayard.BuildSrcVersionsPlugin"
        }
    }
}

publishing {
    repositories {
        maven(url = "build/repository")
    }
}

repositories {
    mavenCentral()
    jcenter()
}

pluginBundle {
    website = "https://github.com/jmfayard/buildSrcVersions"
    vcsUrl = "https://github.com/jmfayard/buildSrcVersions"
    tags = listOf("dependencies", "versions", "buildSrc", "kotlin", "kotlin-dsl")
}
dependencies {
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.1.9")

    implementation("com.github.ben-manes:gradle-versions-plugin:0.20.0")

    implementation("com.squareup.okio:okio:2.1.0")
    implementation( "com.squareup.moshi:moshi:1.7.0")
    implementation("com.squareup:kotlinpoet:1.0.0")

}


tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

buildScan {
    setTermsOfServiceUrl("https://gradle.com/terms-of-service")
    setTermsOfServiceAgree("yes")
    publishAlways()
}
