import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.gradle.plugin-publish")
    `java-gradle-plugin`
    `maven-publish`
    signing
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("buildSrcLibs") {
            id = "de.fayard.buildSrcLibs"
            displayName = "Dependency notation generator & updates"
            description = "Generates dependency notations constants in buildSrc and " +
                    "updates the versions with gradle refreshVersions"
            implementationClass = "de.fayard.buildSrcLibs.BuildSrcLibsPlugin"
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

    api(project(":refreshVersions-core"))
    implementation(project(":refreshVersions"))

    implementation(Square.kotlinPoet)
    implementation(KotlinX.coroutines.core)
    implementation(gradleKotlinDsl())

    testImplementation(Testing.kotest.runner.junit5)

    testImplementation(platform(notation = "org.junit:junit-bom:_"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher") {
        because("allows tests to run from IDEs that bundle older version of launcher")
    }

    testImplementation(testFixtures(project(":refreshVersions-core")))
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
