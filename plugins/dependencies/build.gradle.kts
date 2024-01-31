import org.jetbrains.kotlin.gradle.dsl.KotlinVersion

plugins {
    id("gradle-plugin")
    `kotlin-dsl`
    `jvm-test-suite`
    idea
}

gradlePlugin {
    plugins {
        create("refreshVersions") {
            id = "de.fayard.refreshVersions"
            displayName = "Typesafe Gradle Dependencies"
            description = "Common Gradle dependencies - See gradle refreshVersions"
            tags = listOf("dependencies", "versions", "buildSrc", "kotlin", "kotlin-dsl")
            implementationClass = "de.fayard.refreshVersions.RefreshVersionsPlugin"
        }
    }
}

dependencies {
    testImplementation(Testing.kotest.runner.junit5)

    testImplementation(platform(notation = Testing.junit.bom))
    testImplementation(Testing.junit.jupiter)
    testRuntimeOnly("org.junit.platform:junit-platform-launcher") {
        because("allows tests to run from IDEs that bundle older version of launcher")
    }

    testImplementation(testFixtures(project(":refreshVersions-core")))

    implementation(gradleKotlinDsl())
    api(project(":refreshVersions-core"))
    implementation(KotlinX.coroutines.core)
}

val genResourcesDir = layout.buildDirectory.dir("generated/refreshVersions/resources")

sourceSets.main {
    resources.srcDir(genResourcesDir.get().asFile.path)
}

idea {
    module.generatedSourceDirs.add(genResourcesDir.get().asFile)
}

val copyDependencyNotationsRemovalsRevisionNumber by tasks.registering {
    val versionFile = rootProject.file("version.txt")
    val removalsRevisionHistoryFile = file("src/main/resources/removals-revisions-history.md")
    val snapshotDependencyNotationsRemovalsRevisionNumberFile = genResourcesDir.get().file("snapshot-dpdc-rm-rev.txt").asFile
    val versionToRemovalsMappingFile = file("src/main/resources/version-to-removals-revision-mapping.txt")


    inputs.files(versionFile, removalsRevisionHistoryFile)
    outputs.files(snapshotDependencyNotationsRemovalsRevisionNumberFile, versionToRemovalsMappingFile)

    doFirst {
        val version = versionFile.useLines { it.first() }
        val removalsRevision: Int? = removalsRevisionHistoryFile.useLines { lines ->
            lines.lastOrNull { it.startsWith("## ") }?.takeUnless { it.startsWith("## [WIP]") }
        }?.substringAfter("## Revision ")?.substringBefore(' ')?.toInt()
        if (version.endsWith("-SNAPSHOT")) {
            snapshotDependencyNotationsRemovalsRevisionNumberFile.let {
                when (removalsRevision) {
                    null -> it.delete()
                    else -> it.writeText(removalsRevision.toString())
                }
            }
        } else {
            snapshotDependencyNotationsRemovalsRevisionNumberFile.delete()
            val expectedPrefix = "$version->"
            val mappingLine = "$expectedPrefix$removalsRevision"
            val mappingFileContent = versionToRemovalsMappingFile.readText()
            val existingMapping = mappingFileContent.lineSequence().firstOrNull {
                it.startsWith(expectedPrefix)
            }
            if (existingMapping != null) {
                check(existingMapping == mappingLine)
            } else {
                check(mappingFileContent.endsWith('\n') || mappingFileContent.isEmpty())
                val isInCi = System.getenv("CI") == "true"
                check(isInCi.not()) {
                    "$versionToRemovalsMappingFile shall be updated before publishing."
                }
                versionToRemovalsMappingFile.appendText("$mappingLine\n")
            }
        }
    }
}

tasks.processResources.configure {
    dependsOn(copyDependencyNotationsRemovalsRevisionNumber)
}

afterEvaluate {
    tasks.named("sourcesJar").configure {
        dependsOn(copyDependencyNotationsRemovalsRevisionNumber)
    }
}

@Suppress("UnstableApiUsage")
val prePublishTest = testing.suites.create<JvmTestSuite>("prePublishTest") {
    useJUnitJupiter()
    dependencies {
        implementation(project())
        implementation(testFixtures(project(":refreshVersions-core")))
        implementation(Testing.kotest.assertions.core)
    }
}

kotlin {
    target.compilations.let {
        it.getByName("prePublishTest").associateWith(it.getByName("main"))
    }
}

tasks.check {
    dependsOn(prePublishTest)
}

tasks.withType<AbstractPublishToMaven>().configureEach {
    dependsOn(prePublishTest)
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
