package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.ArtifactVersionKeyReader
import de.fayard.refreshVersions.core.internal.ConfigurationLessDependency
import de.fayard.refreshVersions.core.internal.DependencyWithVersionCandidates
import de.fayard.refreshVersions.core.internal.TomlLine
import de.fayard.refreshVersions.core.internal.TomlSection
import de.fayard.refreshVersions.core.internal.VersionsCatalogUpdater
import de.fayard.refreshVersions.core.internal.VersionsCatalogs
import io.kotest.assertions.asClue
import io.kotest.matchers.shouldBe
import org.gradle.api.artifacts.Dependency
import org.junit.jupiter.api.TestFactory
import testutils.junit.dynamicTest
import java.io.File
import kotlin.test.Test
import de.fayard.refreshVersions.core.Version as MavenVersion

class VersionsCatalogUpdaterTest {

    @Test
    fun `Folder toml-refreshversions - update new versions`() = testVersionsCatalogUpdater(
        inputFolderName = "toml-refreshversions"
    ) { actual: File -> updateNewVersions(actual) }

    @Test
    fun `Folder toml-cleanup - remove refreshVersions comments`() = testVersionsCatalogUpdater(
        inputFolderName = "toml-cleanup"
    ) { actual: File -> cleanupComments(actual) }

    private fun testVersionsCatalogUpdater(
        inputFolderName: String,
        action: VersionsCatalogUpdater.(actual: File) -> Unit
    ) {
        val input = FolderInput(inputFolderName)
        sequence {
            yield(input.initial to input.actual)
            yield(input.expected to input.expected) // check idempotence
        }.forEach { (initial, actual) ->
            VersionsCatalogUpdater(
                file = initial,
                dependenciesUpdates = dependencyWithVersionCandidates(input.folder)
            ).action(actual)
            input.actual.readText() shouldBe input.expectedText
        }

        // delete actual file if successful
        input.actual.delete()
    }

    @Test
    fun `Folder toml-merge-properties - modify file incrementally`() {
        val input = FolderInput("toml-merge-properties")

        val toml = VersionsCatalogs.parseToml(input.initial.readText())
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


    private val rulesDir = File(".").absoluteFile.parentFile.parentFile
        .resolve("dependencies/src/main/resources/refreshVersions-rules")
        .also { require(it.canRead()) { "Can't read folder $it" } }
    private val versionsMap = mapOf(
        "version.junit.jupiter" to "42"
    )
    private val versionKeyReader = ArtifactVersionKeyReader.fromRules(rulesDir.listFiles()!!.map { it.readText() })

    @TestFactory
    fun refreshVersionsCatalog() = testResources.resolve("refreshVersionsCatalog").listFiles()!!.mapNotNull { folder ->
        if (folder.isDirectory.not()) return@mapNotNull null
        dynamicTest(folder.name) {
            val input = FolderInput(folder)
            val withVersions = input.folder.name.contains("versions")

            val currentText = input.initial.readText()
            val dependenciesDataFile = folder.resolve("dependencies.txt").takeIf { it.exists() }
                ?: folder.parentFile.resolve("default-dependencies.txt")
            val dependenciesAndNames = dependenciesDataFile.useLines { lines ->
                lines.filter {
                    it.isNotBlank() && it.startsWith("//").not()
                }.toList()
            }.associate {
                val dependencyNotation = it.substringBefore('|').trimEnd()
                val tomlPropertyName = it.substringAfter('|').trim()
                val dependency: Dependency = ConfigurationLessDependency(dependencyNotation)
                dependency to tomlPropertyName
            }
            val plugins = dependenciesAndNames.keys.filter { it.name.endsWith(".gradle.plugin") }
            val newText = VersionsCatalogs.generateVersionsCatalogText(
                versionsMap = versionsMap,
                versionKeyReader = versionKeyReader,
                dependenciesAndNames = dependenciesAndNames,
                currentText = currentText,
                moveVersionsToCatalog = withVersions,
                plugins = plugins
            )
            input.actual.writeText(newText)
            input.asClue {
                newText shouldBe input.expectedText
            }
            input.actual.delete()
        }
    }
}

private data class FolderInput(
    val folder: File,
    val initial: File,
    val expected: File,
    val actual: File,
) {
    val expectedText = expected.readText()

    override fun toString() =
        "Comparing from resources folder=${folder.name}:  actual=${actual.name} and expected=${expected.name}"
}

private fun FolderInput(folderName: String): FolderInput = FolderInput(testResources.resolve(folderName))

private fun FolderInput(folder: File): FolderInput {
    require(folder.canRead()) { "Invalid folder ${folder.absolutePath}" }
    return FolderInput(
        folder = folder,
        initial = folder.resolve("initial.libs.toml"),
        actual = folder.resolve("actual.libs.toml"),
        expected = folder.resolve("expected.libs.toml"),
    )
}

private fun dependencyWithVersionCandidates(folder: File): List<DependencyWithVersionCandidates> {
    val file = folder.resolve("dependencies.txt")
        .takeIf { it.canRead() }
        ?: folder.parentFile.resolve("default-dependencies.txt")
            .takeIf { it.canRead() } ?: return emptyList()

    return file.useLines { lines ->
        lines.filter {
            it.isNotBlank()
        }.map { line ->
            val dependencyNotation = line.substringBefore('|').trimEnd()
            val moduleId = ModuleId.Maven(
                group = dependencyNotation.substringBefore(':'),
                name = dependencyNotation.substringAfter(':').substringBefore(':')
            )
            val versions = line.substringAfter('|').trim().split(',')
            DependencyWithVersionCandidates(
                moduleId = moduleId,
                currentVersion = dependencyNotation.substringAfterLast(':'),
                versionsCandidates = { currentVersion ->
                    versions.mapNotNull { rawVersion ->
                        MavenVersion(rawVersion.trim()).takeIf { it > currentVersion }
                    }
                },
                failures = emptyList() //TODO: Test failures
            )
        }.toList()
    }
}
