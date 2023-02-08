import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.gradle.plugin-publish")
    `java-gradle-plugin`
    `maven-publish`
    signing
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
            implementationClass = "de.fayard.refreshVersions.RefreshVersionsPlugin"
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

val genResourcesDir = buildDir.resolve("generated/refreshVersions/resources")

sourceSets.main {
    resources.srcDir(genResourcesDir.path)
}

idea {
    module.generatedSourceDirs.add(genResourcesDir)
}

val copyDependencyNotationsRemovalsRevisionNumber by tasks.registering {
    val versionFile = rootProject.file("version.txt")
    val removalsRevisionHistoryFile = file("src/main/resources/removals-revisions-history.md")
    val snapshotDependencyNotationsRemovalsRevisionNumberFile = genResourcesDir.resolve("snapshot-dpdc-rm-rev.txt")
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

tasks.processResources {
    dependsOn(copyDependencyNotationsRemovalsRevisionNumber)
}

@Suppress("UnstableApiUsage")
val prePublishTest = testing.suites.create<JvmTestSuite>("prePublishTest") {
    useJUnitJupiter()
    dependencies {
        implementation(project(":refreshVersions"))
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

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
    kotlinOptions.apiVersion = "1.4"
    kotlinOptions.freeCompilerArgs += listOf(
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
