package de.fayard.refreshVersions.core.removals_replacement

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.internal.DependencyMapping
import de.fayard.refreshVersions.core.internal.associateShortestByMavenCoordinate
import de.fayard.refreshVersions.core.internal.removals_replacement.RemovedDependencyNotation
import de.fayard.refreshVersions.core.internal.removals_replacement.parseRemovedDependencyNotationsHistory
import de.fayard.refreshVersions.core.internal.removals_replacement.replaceRemovedDependencyNotationReferencesIfAny
import de.fayard.refreshVersions.core.testResources
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import testutils.junit.mapDynamicTest
import java.io.File

class ReplacementOfRemovedDependencyNotationsTest {

    private val testDataDir = testResources.resolve("removals-replacement")
    private val defaultHistoryFileContent = testDataDir.resolve("default-revisions-history.md").readText()
    private val mapping: Pair<Set<String>, Map<ModuleId.Maven, String>> = testResources.parentFile!!.parentFile!!.parentFile!!.parentFile!!
        .resolveSibling("dependencies")
        .resolve("src/test/resources/dependencies-mapping-validated.txt")
        .let { readDependencyMappingFromFile(it) }

    private fun readDependencyMappingFromFile(file: File) = file.useLines { lines ->
        lines.mapNotNull {
            DependencyMapping.fromLine(it)
        }.toList().let { list ->
            list.mapTo(mutableSetOf()) { it.constantName } to list.associateShortestByMavenCoordinate()
        }
    }

    @TestFactory
    fun `tests for replaceRemovedDependencyNotationReferencesIfAny`(): List<DynamicTest> {
        val sampleDirs = testDataDir.listFiles { file ->
            file.isDirectory
        }!!.asList()
        return sampleDirs.mapDynamicTest { dir ->
            `test replaceRemovedDependencyNotationReferencesIfAny`(dir)
        }
    }

    private fun `test replaceRemovedDependencyNotationReferencesIfAny`(dir: File) {
        val currentRevision = dir.name.substringAfter("-rev", missingDelimiterValue = "0").toInt()
        val revisionsHistoryFileContent = dir.resolve("revisions-history.md").takeIf {
            it.exists()
        }?.readText() ?: defaultHistoryFileContent
        val revisionsHistory = revisionsHistoryFileContent.lineSequence().parseRemovedDependencyNotationsHistory(
            currentRevision = currentRevision
        )
        val dependencyMapping = dir.resolve("dependency-mapping.txt").takeIf {
            it.exists()
        }?.let { readDependencyMappingFromFile(it) } ?: mapping
        val remainingDependencyNotations = dependencyMapping.first
        val shortestDependencyMapping = dependencyMapping.second
        listOf(
            "kt-input.build.gradle.kts" to "kt-output.build.gradle.kts",
            "gvy-input.build.gradle" to "gvy-output.build.gradle"
        ).mapNotNull { (inputFileName, outputFileName) ->
            val inputFile = dir.resolve(inputFileName).takeIf { it.exists() }
            val outputFile = dir.resolve(outputFileName).takeIf { it.exists() }
            if (inputFile == null) null else inputFile to outputFile
        }.also {
            check(it.isNotEmpty())
        }.forEach { (inputFile, outputFile) ->
            `test replaceRemovedDependencyNotationReferencesIfAny`(
                remainingDependencyNotations = { remainingDependencyNotations },
                shortestDependencyMapping = { shortestDependencyMapping },
                revisionsHistory = revisionsHistory,
                inputFile = inputFile,
                outputFile = outputFile
            )
        }
    }

    private fun `test replaceRemovedDependencyNotationReferencesIfAny`(
        remainingDependencyNotations: () -> Set<String>,
        shortestDependencyMapping: () -> Map<ModuleId.Maven, String>,
        revisionsHistory: List<RemovedDependencyNotation>,
        inputFile: File,
        outputFile: File?
    ) {
        val output = revisionsHistory.replaceRemovedDependencyNotationReferencesIfAny(
            remainingDependencyNotations = remainingDependencyNotations,
            shortestDependencyMapping = shortestDependencyMapping,
            gradleBuildFileContent = inputFile.readText(),
            isKotlinDsl = inputFile.extension == "kts"
        )
        output shouldBe outputFile?.readText()
    }
}
