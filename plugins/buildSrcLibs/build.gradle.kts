import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    id("gradle-plugin")
    `kotlin-dsl`
}

gradlePlugin {
    plugins {
        create("buildSrcLibs") {
            id = "de.fayard.buildSrcLibs"
            displayName = "Dependency notation generator & updates"
            description = "Generates dependency notations constants in buildSrc and " +
                    "updates the versions with gradle refreshVersions"
            tags = listOf("dependencies", "versions", "buildSrc", "kotlin", "kotlin-dsl")
            implementationClass = "de.fayard.buildSrcLibs.BuildSrcLibsPlugin"
        }
    }
}

dependencies {

    api(project(":refreshVersions-core"))
    implementation(project(":refreshVersions"))

    implementation(Square.kotlinPoet)
    implementation(KotlinX.coroutines.core)
    implementation(gradleKotlinDsl())

    testImplementation(Testing.kotest.runner.junit5)

    testImplementation(platform(notation = Testing.junit.bom))
    testImplementation(Testing.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher") {
        because("allows tests to run from IDEs that bundle older version of launcher")
    }

    testImplementation(testFixtures(project(":refreshVersions-core")))
}


kotlin {
    jvmToolchain(8)
    compilerOptions {
        apiVersion = KotlinVersion.KOTLIN_1_8 // https://docs.gradle.org/current/userguide/compatibility.html#kotlin
        freeCompilerArgs.add("-opt-in=de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi")
    }
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}
