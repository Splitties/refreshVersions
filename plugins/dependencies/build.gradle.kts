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
    testImplementation("io.kotlintest:kotlintest-runner-junit5:_")

    testImplementation(platform(notation = "org.junit:junit-bom:_"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher") {
        because("allows tests to run from IDEs that bundle older version of launcher")
    }

    implementation(gradleKotlinDsl())
    api(project(":refreshVersions-core"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:_")
}


tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.freeCompilerArgs += listOf(
        "-Xuse-experimental=kotlin.Experimental",
        "-Xuse-experimental=de.fayard.versions.internal.InternalRefreshVersionsApi"
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
