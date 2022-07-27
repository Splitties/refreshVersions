package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.ArtifactVersionKeyReader
import de.fayard.refreshVersions.core.internal.ConfigurationLessDependency
import de.fayard.refreshVersions.core.internal.DependencyWithVersionCandidates
import de.fayard.refreshVersions.core.internal.Deps
import de.fayard.refreshVersions.core.internal.Library
import de.fayard.refreshVersions.core.internal.TomlLine
import de.fayard.refreshVersions.core.internal.TomlSection
import de.fayard.refreshVersions.core.internal.TomlUpdater
import de.fayard.refreshVersions.core.internal.VersionCatalogs
import io.kotest.assertions.asClue
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
        input.asClue {
            input.actual.readText() shouldBe input.expectedText
        }

        // delete actual file if successful
        input.actual.delete()
    }

    test("Folder toml-cleanup - remove refreshVersions comments") {
        val input = FolderInput("toml-cleanup")

        TomlUpdater(input.initial, input.dependenciesUpdates).cleanupComments(input.actual)
        input.actual.readText() shouldBe input.expectedText

        // check idempotent
        TomlUpdater(input.expected, input.dependenciesUpdates).cleanupComments(input.expected)
        input.asClue {
            input.actual.readText() shouldBe input.expectedText
        }

        // delete actual file if successful
        input.actual.delete()
    }

    test("Folder toml-merge-properties - modify file incrementally") {
        val input = FolderInput("toml-merge-properties")

        val toml = VersionCatalogs.parseToml(input.initial.readText())
        toml.merge(
            TomlSection.Versions, listOf(
                TomlLine(TomlSection.Versions, "groovy", "3.0.6"),
                TomlLine(TomlSection.Versions, "ktor", "2.0"),
            )
        )

        toml.merge(
            TomlSection.Libraries, listOf(
                TomlLine(TomlSection.Libraries, "my-lib", "com.mycompany:mylib:1.5"),
                TomlLine(TomlSection.Libraries, "other-lib", "com.mycompany:other:1.5"),
            )
        )

        toml.merge(
            TomlSection.Plugins, listOf(
                TomlLine(TomlSection.Plugins, "short-notation", "some.plugin.id:1.6"),
                TomlLine(TomlSection.Plugins, "ben-manes", "ben.manes:versions:1.0"),
            )
        )

        input.actual.writeText(toml.toString())
        input.asClue {
            toml.toString() shouldBe input.expectedText
        }
        // delete actual file if successful
        input.actual.delete()
    }


    val rulesDir = File(".").absoluteFile.parentFile.parentFile
        .resolve("dependencies/src/main/resources/refreshVersions-rules")
        .also { require(it.canRead()) { "Can't read foler $it" } }
    VersionCatalogs.versionsMap = mapOf(
        "version.junit.jupiter" to "42"
    )
    VersionCatalogs.versionKeyReader = ArtifactVersionKeyReader.fromRules(rulesDir.listFiles()!!.map { it.readText() })

    context("refreshVersionsCatalog") {
        val refreshVersionsCatalogInputs = listOf(
            FolderInput("refreshVersionsCatalog-versions-new"),
            FolderInput("refreshVersionsCatalog-versions-existing"),
            FolderInput("refreshVersionsCatalog-underscore-new"),
            FolderInput("refreshVersionsCatalog-underscore-existing"),
        )
        for (input in refreshVersionsCatalogInputs) {
            test("Folder ${input.folder}") {
                val withVersions = input.folder.contains("versions")

                val currentText = input.initial.readText()
                val librariesMap = input.dependenciesUpdates
                    .associate { d ->
                        val versionName = d.versionsCandidates.first().value
                        Library(d.moduleId.group!!, d.moduleId.name, d.currentVersion) to versionName
                    }
                val deps = Deps(librariesMap.keys.toList(), librariesMap)
                val plugins = input.dependenciesUpdates.mapNotNull {
                    ConfigurationLessDependency(it.moduleId as ModuleId.Maven, it.currentVersion)
                        .takeIf { it.name.endsWith(".gradle.plugin") }
                }
                val newText = VersionCatalogs.generateVersionsCatalogText(deps, currentText, withVersions, plugins)
                input.actual.writeText(newText)
                input.asClue {
                    newText shouldBe input.expectedText
                }
                input.actual.delete()
            }
        }
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

    override fun toString() =
        "Comparing from resources folder=$folder:  actual=${actual.name} and expected=${expected.name}"
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
        .filter { it.isNotBlank() }
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
