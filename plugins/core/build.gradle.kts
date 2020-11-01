import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.gradle.plugin-publish")
    `java-gradle-plugin`
    `java-test-fixtures`
    `maven-publish`
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
    website = "https://builtwithgradle.netlify.com/"
    vcsUrl = "https://github.com/jmfayard/refreshVersions"
    tags = listOf("dependencies", "versions", "buildSrc", "kotlin", "kotlin-dsl")
}

publishing {
    setupAllPublications(project)
}

dependencies {
    implementation(gradleKotlinDsl())
    implementation(KotlinX.coroutines.core)
    implementation(Square.okHttp3.okHttp)
    implementation(Square.okHttp3.loggingInterceptor)
    implementation(Square.retrofit2.retrofit) {
        because("It has ready to use HttpException class")
    }
    implementation(Square.moshi.kotlinReflect)

    testImplementation(Square.okHttp3.loggingInterceptor)
    testImplementation(platform(notation = "org.junit:junit-bom:_"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation(Testing.kotest.runner.junit5)
    testImplementation(Kotlin.test.annotationsCommon)
    testImplementation(Kotlin.test.junit5)

    testFixturesApi(Square.okHttp3.okHttp)
    testFixturesApi(Square.okHttp3.loggingInterceptor)
    testFixturesApi(KotlinX.coroutines.core)
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
    doFirst { versionFile.copyTo(versionFileCopy) }
}

tasks.withType<KotlinCompile> {
    dependsOn(copyVersionFile)
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs += listOf(
        "-Xinline-classes",
        "-Xmulti-platform", // Allow using expect and actual keywords.
        "-Xopt-in=kotlin.RequiresOptIn",
        "-Xopt-in=de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi"
    )
}

tasks.withType<Test> {
    useJUnitPlatform()
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    withSourcesJar()
}

kotlinDslPluginOptions {
    experimentalWarning.set(false)
}
