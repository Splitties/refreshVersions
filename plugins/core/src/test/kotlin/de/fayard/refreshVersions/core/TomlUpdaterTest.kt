package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.DependencyWithVersionCandidates
import de.fayard.refreshVersions.core.internal.TomlUpdater
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.io.File
import de.fayard.refreshVersions.core.Version as MavenVersion

class TomlUpdaterTest : FunSpec({

    test("Folder toml-refreshversions - update new versions") {
        val input = FolderInput("toml-refreshversions")

        TomlUpdater(input.initial, input.dependenciesUpdates).updateNewVersions(input.actual)
        input.actual.readText() shouldBe input.expectedText

        // check idempotent
        TomlUpdater(input.expected, input.dependenciesUpdates).updateNewVersions(input.expected)
        input.actual.readText()  shouldBe input.expectedText

        // delete actual file if successful
        input.actual.delete()
    }

    test("Folder toml-cleanup - remove refreshVersions comments") {
        val input = FolderInput("toml-cleanup")

        TomlUpdater(input.initial, input.dependenciesUpdates).cleanupComments(input.actual)
        input.actual.readText() shouldBe input.expectedText

        // check idempotent
        TomlUpdater(input.expected, input.dependenciesUpdates).cleanupComments(input.expected)
        input.actual.readText()  shouldBe input.expectedText

        // delete actual file if successful
        input.actual.delete()
    }
})


private data class FolderInput(
    val folder: String,
    val initial: File,
    val expected: File,
    val actual: File,
    val dependenciesUpdates: List<DependencyWithVersionCandidates>
) {
    val expectedText = expected.readText()
}

private fun FolderInput(folderName: String): FolderInput {
    val folder = testResources.resolve(folderName)
    require(folder.canRead()) { "Invalid folder ${folder.absolutePath}" }
    val dependencies = dependencyWithVersionCandidates(folder)
    return FolderInput(
        folder = folderName,
        initial = folder.resolve("initial.libs.toml"),
        actual = folder.resolve("actual.libs.toml"),
        expected = folder.resolve("expected.libs.toml"),
        dependenciesUpdates = dependencies
    )
}

private fun dependencyWithVersionCandidates(folder: File): List<DependencyWithVersionCandidates> {
    val file = folder.resolve("dependencies.txt")
        .takeIf { it.canRead() }
        ?: return emptyList()

    val dependencies = file.readText()
        .lines()
        .map { line ->
            val (group, name, version, available) = line.split(":")
            DependencyWithVersionCandidates(
                moduleId = ModuleId.Maven(group, name),
                currentVersion = version,
                versionsCandidates = available.split(",").map { MavenVersion(it) },
                failures = emptyList()
            )
        }
    return dependencies
}
