import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("de.fayard.buildSrcVersions") version ("0.6.4") // plugin.de.fayard.buildSrcVersions

    id("com.gradle.plugin-publish")
    `java-gradle-plugin`
    `maven-publish`
    `kotlin-dsl`
    `build-scan`
}


version = "0.6.5" // plugin.de.fayard.buildSrcVersions
group = "de.fayard"


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

tasks.register<DefaultTask>("hello") {
    group = "Custom"
    description = "Minimal task that do nothing. Useful to debug a failing build"
}

publishing {
    repositories {
        maven(url = "build/repository")
    }
}

repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
}

pluginBundle {
    website = "https://github.com/jmfayard/buildSrcVersions"
    vcsUrl = "https://github.com/jmfayard/buildSrcVersions"
    tags = listOf("dependencies", "versions", "buildSrc", "kotlin", "kotlin-dsl")
}

dependencies {
    testImplementation("io.kotlintest:kotlintest-runner-junit5:3.1.9")

    // SYNC WITH plugin/src/main/kotlin/de/fayard/internal/PluginConfig.kt
    implementation("com.github.ben-manes:gradle-versions-plugin:0.25.0")

    implementation("com.squareup.okio:okio:2.1.0")
    implementation( "com.squareup.moshi:moshi:1.7.0")
    implementation("com.squareup:kotlinpoet:1.3.0")

}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<Wrapper> {
    gradleVersion = System.getenv("GRADLE_VERSION") ?: "5.6.1"
    distributionType = Wrapper.DistributionType.ALL
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

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
