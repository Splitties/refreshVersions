import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.gradle.plugin-publish")
    `java-gradle-plugin`
    `maven-publish`
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("refreshVersions") {
            id = "de.fayard.refreshVersions"
            displayName = "Typesafe Gradle Dependencies"
            description = "Common Gradle dependencies - See gradle refreshVersions"
            implementationClass = "de.fayard.dependencies.DependenciesPlugin"
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
    testImplementation(Testing.kotest.runner.junit5)

    testImplementation(platform(notation = "org.junit:junit-bom:_"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher") {
        because("allows tests to run from IDEs that bundle older version of launcher")
    }

    testImplementation(testFixtures(project(":refreshVersions-core")))

    implementation(gradleKotlinDsl())
    api(project(":refreshVersions-core"))
    implementation(KotlinX.coroutines.core)
}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs += listOf(
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
