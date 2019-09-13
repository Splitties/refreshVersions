package de.fayard

import de.fayard.VersionsOnlyMode.*
import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec
import java.io.File
import java.nio.file.Files

class VersionsOnlyModeTest: FreeSpec({

    val versionOnlyFolder = testResourceFile("versionOnly")

    val kotlinValInput = versionOnlyFolder.resolve("singleBuildFile.txt")


    "Parse file" {
        val singleModeResult = parseBuildFile(kotlinValInput, KOTLIN_VAL)
        singleModeResult shouldBe SingleModeResult(6, 11, "    ")

        parseBuildFileOrNew(kotlinValInput, KOTLIN_VAL, File(".")) shouldBe Pair(kotlinValInput, singleModeResult)
    }

    val tempDir = Files.createTempDirectory("versions-only").toFile()

    "always modify gradle.properties" {
        val (file1, result1) = parseBuildFileOrNew(null, GRADLE_PROPERTIES, File("."))
        file1.name shouldBe "gradle.properties"
        result1 shouldBe SingleModeResult.NEW_FILE

        val (file2, result2) = parseBuildFileOrNew(File("gradle.properties"), GRADLE_PROPERTIES, File("."))
        file2.name shouldBe "gradle.properties"
        result2 shouldBe SingleModeResult.NEW_FILE
    }

    "Create new file if needed" {
        val (file, result) = parseBuildFileOrNew(null, KOTLIN_VAL, tempDir)
        file shouldBe tempDir.resolve("build.gradle.kts")
        result shouldBe SingleModeResult.NEW_FILE.copy(indentation = PluginConfig.SPACES4)
    }

    "Regenerate file" - {
        val testCases = listOf(
            KOTLIN_VAL,
            GROOVY_DEF,
            GROOVY_EXT,
            GRADLE_PROPERTIES
        )
        for (mode in testCases) {
            "For mode = $mode" {
                val received = versionOnlyFolder.resolve("${mode}_received.txt")
                val validated = versionOnlyFolder.resolve("${mode}_validated.txt")
                kotlinValInput.copyTo(received, overwrite = true)
                val config = BuildSrcVersionsExtensionImpl(versionsOnlyMode = mode, versionsOnlyFile = "KOTLIN_VAL_expected.txt")
                val deps = listOf(
                    "com.squareup.okhttp3:okhttp:2.1.0 // 2.2.0",
                    "com.squareup.okio:okio:2.0.0"
                ).map { it.asDependency() }
                regenerateBuildFile(received, config, deps)
                received.readText() shouldBe validated.readText()
                received.delete()
            }
        }
    }
})

fun String.asDependency(): Dependency {
    val available = this.substringAfter("// ", "").trim()
    val rest = this.substringBefore(" // ")
    val (group, name, version) = rest.split(":")
    return Dependency(
        group = group,
        version = version,
        reason = "",
        latest = available,
        projectUrl = "",
        name = name,
        escapedName = name,
        versionName = name,
        available = if (available.isBlank()) null else AvailableDependency(release = available)
    )
}
