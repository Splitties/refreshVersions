import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.gradle.plugin-publish")
    `java-gradle-plugin`
    `maven-publish`
    `kotlin-dsl`
    `build-scan`
}


version = file("plugins_version.txt").readLines().first()
group = "de.fayard"


gradlePlugin {
    plugins {
        create("refreshVersions") {
            id = "de.fayard.refreshVersions"
            displayName = "./gradlew refreshVersions"
            description = "Painless dependencies management"
            implementationClass = "de.fayard.RefreshVersionsPlugin"
        }
    }
}

tasks.register<DefaultTask>("hello") {
    group = "Custom"
    description = "Minimal task that do nothing. Useful to debug a failing build"
}

repositories {
    mavenLocal()
    jcenter()
    mavenCentral()
}

pluginBundle {
    website = "https://builtwithgradle.netlify.com/"
    vcsUrl = "https://github.com/jmfayard/gradle-dependencies-plugins"
    tags = listOf("dependencies", "versions", "buildSrc", "kotlin", "kotlin-dsl")
}

dependencies {

    testImplementation(platform(notation = "org.junit:junit-bom:_"))
    testImplementation("org.junit:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher") {
        because("allows tests to run from IDEs that bundle older version of launcher")
    }

    testImplementation("io.kotlintest:kotlintest-runner-junit5:_")
    implementation(gradleKotlinDsl())

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:_")
}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs = listOf("-Xinline-classes")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
    publishAlways()
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
