package de.fayard.refreshVersions.core

import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.ints.shouldBeExactly
import io.kotest.matchers.shouldBe
import java.io.File

class MigrationTest : StringSpec({
    val testResources: File = File(".").absoluteFile.resolve("src/test/resources")

    "Try migrating local repository".config(enabled = false) {
        val file = File("/Users/jmfayard/IdeaProjects/android/compose-samples")
        findFilesWithDependencyNotations(file).forEach {
            migrateFileIfNeeded(it)
        }
    }

    "Replace versions in maven coordinates in build files" {
        val input = """
            implementation("com.example:name:${'$'}exampleVersion")
            implementation("com.example:name:${'$'}version")
            implementation("com.example:name:${'$'}{version}")
            implementation('com.example:name:${'$'}exampleVersion')
            implementation('com.example:name:${'$'}version')
            implementation('com.example:name:1.2.3-alpha1')
            implementation('com.example:name:1.2.3-alpha-1')
            implementation('com.example:name:1.2.3.alpha.1')
            implementation('com.example:name:1.2.3-beta-1')
            implementation('com.example:name:1.2.3.beta.1')
            implementation('com.example:name:1.2.3.beta1')
            implementation('com.example:name:1.2.3-eap-1')
            implementation 'com.example:name:1.2.3.eap.1'
            implementation 'com.example:name:1.2.3.eap1'
            implementation 'com.example:name:1.2.3-native-mt'
        """.trimIndent().lines()
        val expected = """
            implementation("com.example:name:_")
            implementation("com.example:name:_")
            implementation("com.example:name:_")
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation 'com.example:name:_'
            implementation 'com.example:name:_'
            implementation 'com.example:name:_'
        """.trimIndent().lines()
        input.size shouldBeExactly expected.size
        List(input.size) { input[it] to expected[it] }
            .forAll { (input, output) ->
                withVersionPlaceholder(
                    input,
                    isBuildFile = true,
                    isInsidePluginsBlock = false
                ) shouldBe output
            }
    }

    "Ignore lines that do not contain a maven coordinate" {
        val lines = """
            plugins {
                kotlin("jvm")
            }

            version = "2.O"
            group = "de.fayard"

            repositories {
                mavenCentral()
            }
            kotlinOptions {
                jvmTarget = "1.8"
            }
            android {
                 versionName = "1.0.0"
                 ndkVersion = "27.0.1"
            }
            resolutionStrategy {
                  details.useVersion = "1.2.3"
            }
            tasks.wrapper {
                 gradleVersion = "6.9"
            }
            const val gradleLatestVersion = "6.1.0"
            jacoco {
                 toolVersion = "1.0.4"
            }
        """.trimIndent()
        lines.lines().forAll { line ->
            withVersionPlaceholder(
                line,
                isBuildFile = true,
                isInsidePluginsBlock = false
            ) shouldBe null
        }
    }

    "Replace version with underscore" {
        val input = """
            val a = "1.3"
            val b = "1.2.3"
            const val base = "io.coi:coil:${'$'}VERSION"
            implementation("com.example:name:1.2.3")
            implementation(group : "com.example" name: "name" version :"1.2.3")
            implementation('com.example:name:1.2.3')
            implementation("com.example:name:${'$'}exampleVersion")
            implementation("com.example:name:${'$'}version")
            implementation("com.example:name:${'$'}{version}")
            implementation('com.example:name:${'$'}exampleVersion')
            implementation('com.example:name:${'$'}version')
            implementation('com.example:name:1.2.3-alpha1')
            implementation('com.example:name:1.2.3-alpha-1')
            implementation('com.example:name:1.2.3.alpha.1')
            implementation('com.example:name:1.2.3-beta-1')
            implementation('com.example:name:1.2.3.beta.1')
            implementation('com.example:name:1.2.3.beta1')
            implementation('com.example:name:1.2.3-eap-1')
            implementation('com.example:name:1.2.3.eap.1')
            implementation('com.example:name:1.2.3.eap1')
            implementation('com.example:name:1.2.3.RC3')
            runtimeOnly("org.thymeleaf:thymeleaf:3.0.11.RELEASE")
            implementation('com.example:name:1.2.3.Final')
            "org.jetbrains.kotlin:kotlin-noarg:${'$'}{versions.kotlin}"
            implementation("com.example:name:${'$'}rootProject.exampleVersion")
        """.trimIndent().lines()
        val expected = """
            val a = "_"
            val b = "_"
            const val base = "io.coi:coil:_"
            implementation("com.example:name:_")
            implementation(group : "com.example" name: "name" version :"_")
            implementation('com.example:name:_')
            implementation("com.example:name:_")
            implementation("com.example:name:_")
            implementation("com.example:name:_")
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            implementation('com.example:name:_')
            runtimeOnly("org.thymeleaf:thymeleaf:_")
            implementation('com.example:name:_')
            "org.jetbrains.kotlin:kotlin-noarg:_"
            implementation("com.example:name:_")
        """.trimIndent().lines()
        input.size shouldBeExactly expected.size
        List(input.size) { input[it] to expected[it] }
            .forAll { (input, output) ->
                withVersionPlaceholder(
                    input,
                    isInsidePluginsBlock = false,
                    isBuildFile = false
                ) shouldBe output
            }
    }

    "Search for files that may contain dependency notations" {
        val expected = """
            app/feature/build.gradle.kts
            build.gradle
            buildSrc/src/main/kotlin/Dependencies.kt
            buildSrc/src/main/kotlin/Deps.kt
            buildSrc/src/main/kotlin/Libs.kt
            buildSrc/src/main/kotlin/my/package/Deps.kt
            buildSrc/src/main/kotlin/Versions.kt
            deps.gradle
            gradle/dependencies.gradle
            gradle/libraries.gradle
            libraries.groovy
            libs.gradle
        """.trimIndent().lines()
        val dir = testResources.resolve("migration.files")
        findFilesWithDependencyNotations(dir).map { it.relativeTo(dir).path }.sorted() shouldBe expected.sorted()
    }

    "Remove versions inside the plugins block" {
        val input = """
            plugins {
                id 'java'
                id 'java' version '1.4.0'
                id('java') version '1.4.0'
                id("java") version "1.4.0"
                id("java").version("1.4.0")
            }
        """.trimIndent().lines()
        val expected = """
            plugins {
                id 'java'
                id 'java'
                id('java')
                id("java")
                id("java")
            }
        """.trimIndent().lines()
        input.size shouldBeExactly expected.size
        List(input.size) { input[it] to expected[it] }
            .forAll { (input, output) ->
                withVersionPlaceholder(
                    input,
                    isInsidePluginsBlock = true,
                    isBuildFile = false
                ) shouldBe output
            }
    }

    "Detect the plugins block" {
        val detected = exampleBuildGradle.detectPluginsBlock().map { it.second }
        detected shouldBe (List(exampleBuildGradle.size) { it in 3..5 })
    }
})


private val exampleBuildGradle = """
    import static de.fayard.refreshVersions.core.Versions.versionFor

    plugins {
        id 'application'
        id 'idea'
        id 'java'
    }

    group = "de.fayard"

    repositories {
        maven {
            setUrl("../plugin/src/test/resources/maven")
        }
        mavenCentral()
        google()
    }

    dependencies {
        implementation("com.google.guava:guava:_")
        implementation("com.google.inject:guice:_")
        implementation(AndroidX.annotation)
        implementation("org.jetbrains:annotations:_")
    }
""".trimIndent().lines()

