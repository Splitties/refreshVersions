package de.fayard

import de.fayard.VersionsOnlyMode.GRADLE_PROPERTIES
import de.fayard.VersionsOnlyMode.GROOVY_DEF
import de.fayard.VersionsOnlyMode.GROOVY_EXT
import de.fayard.VersionsOnlyMode.KOTLIN_VAL
import de.fayard.internal.PluginConfig
import de.fayard.internal.SingleModeResult
import de.fayard.internal.UpdateVersionsOnly.parseBuildFile
import de.fayard.internal.UpdateVersionsOnly.parseBuildFileOrNew
import de.fayard.internal.UpdateVersionsOnly.regenerateBuildFile
import io.kotlintest.matchers.withClue
import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec
import java.io.File
import java.nio.file.Files

class VersionsOnlyModeTest: FreeSpec({

    val versionOnlyFolder = testResourceFile("versionOnly").absoluteFile
    val kotlinValInput = versionOnlyFolder.resolve("singleBuildFile.txt")

    "check repos" {
        buildSrcVersionsDir.name shouldBe "buildSrcVersions"
    }


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


    val testCases = listOf(
        KOTLIN_VAL,
        GROOVY_DEF,
        GROOVY_EXT
    )
    for (mode in testCases) {
        "VersionsOnlyMode for mode = $mode" {
            val received = versionOnlyFolder.resolve("${mode}_received.txt")
            val validated = versionOnlyFolder.resolve("${mode}_validated.txt")
            kotlinValInput.copyTo(received, overwrite = true)

            val deps = listOf(
                "com.squareup.okhttp3:okhttp:2.1.0 // 2.2.0",
                "com.squareup.okio:okio:2.0.0"
            ).map { it.asDependency() }

            regenerateBuildFile(received, mode, deps)
            withClue(
                """Files differ. Run:
                    |$ diff -u  ${validated.relativeTo(buildSrcVersionsDir)} ${received.relativeTo(buildSrcVersionsDir)}
                    |""".trimMargin()
            ) {
                (received.readText() == validated.readText()).shouldBe(true)
                received.delete()
            }
        }
    }

})
