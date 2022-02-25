package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.internal.DependencyWithVersionCandidates
import de.fayard.refreshVersions.core.internal.TomlUpdater
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.io.File
import de.fayard.refreshVersions.core.Version as MavenVersion

class TomlUpdaterTest : FunSpec({

    val folders = listOf("toml-happy-path")

    folders.forEach { folder ->
        test("Test for folder $folder") {
            val input = FolderInput(folder)
            val expectedText = input.expected.readText()

            TomlUpdater(input.initial, input.dependenciesUpdates).updateNewVersions(input.actual)
            expectedText shouldBe input.actual.readText()

            // check idempotent
            TomlUpdater(input.expected, input.dependenciesUpdates).updateNewVersions(input.expected)
            expectedText shouldBe input.actual.readText()

            // delete actual file if successfull
            input.actual.delete()
        }
    }
})


private data class FolderInput(
    val folder: String,
    val initial: File,
    val expected: File,
    val actual: File,
    val dependenciesUpdates: List<DependencyWithVersionCandidates>
)

private fun FolderInput(folder: String): FolderInput {
    val file = File("src/test/resources/$folder")
    require(file.canRead()) { "Invalid folder ${file.absolutePath}" }
    val dependencies = file.resolve("dependencies.txt")
        .readText().lines()
        .map { line ->
            val (group, name, version, available) = line.split(":")
            DependencyWithVersionCandidates(
                moduleId = ModuleId.Maven(group, name),
                currentVersion = version,
                versionsCandidates = available.split(",").map { MavenVersion(it) },
                failures = emptyList()
            )
        }
    return FolderInput(
        folder = folder,
        initial = file.resolve("initial.libs.toml"),
        actual = file.resolve("actual.libs.toml"),
        expected = file.resolve("expected.libs.toml"),
        dependenciesUpdates = dependencies
    )
}
