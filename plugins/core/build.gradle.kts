import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.gradle.plugin-publish")
    `java-gradle-plugin`
    `java-test-fixtures`
    `maven-publish`
    signing
    `kotlin-dsl`
    idea
}

gradlePlugin {
    plugins {
        create("refreshVersions-core") {
            id = "de.fayard.refreshVersions-core"
            displayName = "./gradlew refreshVersions"
            description = "Painless dependencies management"
            implementationClass = "de.fayard.refreshVersions.core.RefreshVersionsCorePlugin"
        }
    }
}

pluginBundle {
    website = "https://jmfayard.github.io/refreshVersions"
    vcsUrl = "https://github.com/jmfayard/refreshVersions"
    tags = listOf("dependencies", "versions", "buildSrc", "kotlin", "kotlin-dsl")
}

signing {
    useInMemoryPgpKeys(
        propertyOrEnvOrNull("GPG_key_id"),
        propertyOrEnvOrNull("GPG_private_key") ?: return@signing,
        propertyOrEnv("GPG_private_password")
    )
    sign(publishing.publications)
}

publishing {
    setupAllPublications(project)
}

dependencies {
    compileOnly(gradleKotlinDsl())
    implementation(KotlinX.coroutines.core)
    implementation(Square.okHttp3)
    implementation(Square.okHttp3.loggingInterceptor)
    implementation(Square.retrofit2)!!.apply {
        because("It has ready to use HttpException class")
    }
    implementation(Square.moshi.kotlinReflect)

    testImplementation(Square.okHttp3.loggingInterceptor)
    testImplementation(platform(notation = Testing.junit.bom))
    testImplementation(Testing.junit.jupiter)
    testImplementation(Testing.kotest.runner.junit5)
    testImplementation(Kotlin.test.annotationsCommon)
    testImplementation(Kotlin.test.junit5)

    testFixturesApi(Square.okHttp3)
    testFixturesApi(Square.okHttp3.loggingInterceptor)
    testFixturesApi(KotlinX.coroutines.core)
    testFixturesApi(Kotlin.test.annotationsCommon)
    testFixturesApi(Kotlin.test.junit5)
}

kotlin {
    target.compilations.let {
        it.getByName("testFixtures").associateWith(it.getByName("main"))
    }
}

(components["java"] as AdhocComponentWithVariants).let { javaComponent ->
    javaComponent.withVariantsFromConfiguration(configurations["testFixturesApiElements"]) { skip() }
    javaComponent.withVariantsFromConfiguration(configurations["testFixturesRuntimeElements"]) { skip() }
}

val genResourcesDir = buildDir.resolve("generated/refreshVersions/resources")

sourceSets.main {
    resources.srcDir(genResourcesDir.path)
}

idea {
    module.generatedSourceDirs.add(genResourcesDir)
}

val copyVersionFile by tasks.registering {
    val versionFile = rootProject.file("version.txt")
    val versionFileCopy = genResourcesDir.resolve("version.txt")
    inputs.file(versionFile)
    outputs.file(versionFileCopy)
    doFirst { versionFile.copyTo(versionFileCopy, overwrite = true) }
}
tasks.processResources {
    dependsOn(copyVersionFile)
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.apiVersion = "1.4"
    kotlinOptions.freeCompilerArgs += listOf(
        "-Xinline-classes",
        "-Xmulti-platform", // Allow using expect and actual keywords.
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xopt-in=de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi"
    )
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
}
