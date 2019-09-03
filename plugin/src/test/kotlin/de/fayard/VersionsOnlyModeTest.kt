package de.fayard

import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec

class VersionsOnlyModeTest: FreeSpec({

    val samFolder = testResourceFile("sam")

    val kotlinValInput = samFolder.resolve("KOTLIN_VAL_input.txt")
    val kotlinValReceived = samFolder.resolve("KOTLIN_VAL_received.txt")
    val kotlinValValidated = samFolder.resolve("KOTLIN_VAL_validated.txt")

    "Parse file" {
        val singleModeResult = parseBuildFile(kotlinValInput)
        singleModeResult shouldBe SingleModeResult(6, 11, "    ")
    }

    "Regenerate file" {
        kotlinValInput.copyTo(kotlinValReceived, overwrite = true)
        val config = BuildSrcVersionsExtensionImpl(versionsOnlyMode = VersionsOnlyMode.KOTLIN_VAL, versionsOnlyFile = "KOTLIN_VAL_expected.txt")
        val deps = listOf(
            "com.squareup.okhttp3:okhttp:2.1.0 // 2.2.0",
            "com.squareup.okio:okio:2.0.0"
        ).map { it.asDependency() }
        regenerateBuildFile(kotlinValReceived, config, deps)
        kotlinValReceived.readText() shouldBe kotlinValValidated.readText()
        kotlinValReceived.delete()
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
