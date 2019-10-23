import com.louiscad.splitties.AndroidX
import com.louiscad.splitties.KotlinX
import com.louiscad.splitties.Testing
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("de.fayard.refreshVersions")
    kotlin("jvm")
    id("com.louiscad.splitties")
    `build-scan`
    id("org.nosphere.gradle.github.actions")
}

group = "de.fayard"

refreshVersions {
    // See configuration options at https://github.com/jmfayard/buildSrcVersions/issues/53
    propertiesFile = "versions.properties"
    alwaysUpdateVersions()
    useFqdnFor("guice", "mongo-java-driver")
    rejectVersionIf {
        isNonStable(candidate.version) && isNonStable(currentVersion).not()
    }
}

repositories {
    maven {
        setUrl("../plugin/src/test/resources/maven")
    }
    mavenCentral()
}

fun DependencyHandler.implementations(deps: List<String>) =
    deps.forEach { implementation(it) }

fun DependencyHandler.testImplementations(deps: List<String>) =
    deps.forEach { testImplementation(it) }

dependencies {
    implementations(listOf(AndroidX.browser, AndroidX.cardView))
    testImplementations(listOf(KotlinX.coroutines.core, KotlinX.coroutines.coreCommon))
    testImplementations(listOf(Testing.kotestRunner, Testing.kotestExtensions))
    implementation("com.google.guava:guava:15.0")
    implementation("com.google.inject:guice:2.0")
    implementation("com.squareup.okhttp3:okhttp:3.10.0")
    implementation("com.squareup.okhttp3:okhttp-urlconnection:3.10.0")
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.mongodb:mongo-java-driver:3.11.0")

}

tasks.register("run", JavaExec::class.java) {
    this.main = "de.fayard.GuavaTest"
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType(JavaExec::class.java) {
    classpath = sourceSets["main"].runtimeClasspath
}

// How to update Gradle itself? https://github.com/jmfayard/buildSrcVersions/issues/19
tasks.withType<Wrapper> {
    gradleVersion = findProperty("gradleLatestVersion") as? String ?: gradle.gradleVersion
    distributionType = Wrapper.DistributionType.ALL
}




buildScan {
    setTermsOfServiceUrl("https://gradle.com/terms-of-service")
    setTermsOfServiceAgree("yes")
}

tasks.register<DefaultTask>("hello") {
    group = "Custom"
    description = "Minimal task that do nothing. Useful to debug a failing build"
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
