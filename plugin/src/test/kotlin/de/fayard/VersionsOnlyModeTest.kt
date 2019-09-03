package de.fayard

import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec

class VersionsOnlyModeTest: FreeSpec({

    val samFolder = testResourceFile("sam")

    val kotlinValInput = samFolder.resolve("singleBuildFile.txt")


    "Parse file" {
        val singleModeResult = parseBuildFile(kotlinValInput)
        singleModeResult shouldBe SingleModeResult(6, 11, "    ")
    }

    "Regenerate file" - {
        val testCases = listOf(
            VersionsOnlyMode.KOTLIN_VAL,
            VersionsOnlyMode.GROOVY_DEF,
            VersionsOnlyMode.GROOVY_EXT
        )
        for (mode in testCases) {
            "For mode = $mode" {
                val received = samFolder.resolve("${mode}_received.txt")
                val validated = samFolder.resolve("${mode}_validated.txt")
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
