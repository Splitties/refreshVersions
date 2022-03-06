package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.DependencyWithVersionCandidates
import de.fayard.refreshVersions.core.internal.TomlLine
import de.fayard.refreshVersions.core.internal.TomlSection
import de.fayard.refreshVersions.core.internal.TomlUpdater
import de.fayard.refreshVersions.core.internal.VersionCatalogs
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import java.io.File
import de.fayard.refreshVersions.core.Version as MavenVersion

class TomlUpdaterTest : FunSpec({

    test("Folder toml-refreshversions - update new versions") {
        val input = FolderInput("toml-refreshversions", ".libs.toml")

        TomlUpdater(input.initial, input.dependenciesUpdates).updateNewVersions(input.actual)
        input.actual.readText() shouldBe input.expectedText

        // check idempotent
        TomlUpdater(input.expected, input.dependenciesUpdates).updateNewVersions(input.expected)
        input.actual.readText()  shouldBe input.expectedText

        // delete actual file if successful
        input.actual.delete()
    }

    test("Folder toml-cleanup - remove refreshVersions comments") {
        val input = FolderInput("toml-cleanup", ".libs.toml")

        TomlUpdater(input.initial, input.dependenciesUpdates).cleanupComments(input.actual)
        input.actual.readText() shouldBe input.expectedText

        // check idempotent
        TomlUpdater(input.expected, input.dependenciesUpdates).cleanupComments(input.expected)
        input.actual.readText()  shouldBe input.expectedText

        // delete actual file if successful
        input.actual.delete()
    }

    test("Folder toml-merge-properties - modify file incrementally") {
        val input = FolderInput("toml-merge-properties", ".libs.toml")

        val toml = VersionCatalogs.parseToml(input.initial.readText())
        toml.merge(TomlSection.Versions, listOf(
            TomlLine(TomlSection.Versions, "groovy", "3.0.6"),
            TomlLine(TomlSection.Versions, "ktor", "2.0"),
        ))

        toml.merge(TomlSection.Libraries, listOf(
            TomlLine(TomlSection.Libraries, "my-lib", "com.mycompany:mylib:1.5"),
            TomlLine(TomlSection.Libraries, "other-lib", "com.mycompany:other:1.5"),
        ))

        toml.merge(TomlSection.Plugins, listOf(
            TomlLine(TomlSection.Plugins, "short-notation", "some.plugin.id:1.6"),
            TomlLine(TomlSection.Plugins, "ben-manes", "ben.manes:versions:1.0"),
        ))

        input.actual.writeText(toml.toString())
        toml.toString() shouldBe input.expectedText
        // delete actual file if successful
        input.actual.delete()
    }
})


internal data class FolderInput(
    val folder: String,
    val initial: File,
    val expected: File,
    val actual: File,
    val dependenciesUpdates: List<DependencyWithVersionCandidates>
) {
    val expectedText = expected.readText()
}

internal fun FolderInput(folderName: String, suffix: String): FolderInput {
    val folder = testResources.resolve(folderName)
    require(folder.canRead()) { "Invalid folder ${folder.absolutePath}" }
    val dependencies = dependencyWithVersionCandidates(folder)
    return FolderInput(
        folder = folderName,
        initial = folder.resolve("initial$suffix"),
        actual = folder.resolve("actual$suffix"),
        expected = folder.resolve("expected$suffix"),
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
