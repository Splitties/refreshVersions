package de.fayard.refreshVersions.core.removals_replacement

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
                revisionsHistory,
                inputFile = inputFile,
                outputFile = outputFile
            )
        }
    }

    private fun `test replaceRemovedDependencyNotationReferencesIfAny`(
        revisionsHistory: List<RemovedDependencyNotation>,
        inputFile: File,
        outputFile: File?
    ) {
        val output = revisionsHistory.replaceRemovedDependencyNotationReferencesIfAny(
            gradleBuildFileContent = inputFile.readText(),
            isKotlinDsl = inputFile.extension == "kts"
        )
        output shouldBe outputFile?.readText()
    }
}
