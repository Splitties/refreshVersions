package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.legacy.LegacyBootstrapMigrator
import testutils.junit.mapDynamicTest
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.io.File
import kotlin.test.assertEquals

class LegacyBootstrapMigratorTest {

    private val testDataDir = testResources.resolve("legacy-bootstrap-migration")

    @TestFactory
    fun `tests for LegacyBootstrapMigrator`(): List<DynamicTest> {
        val sampleDirs = testDataDir.listFiles { file ->
            file.isDirectory
        }!!.asList()
        return sampleDirs.mapDynamicTest { dir ->
            `test LegacyBootstrapMigrator`(dir)
        }
    }

    private fun `test LegacyBootstrapMigrator`(dir: File) {
        class TestEntry(
            val inputFile: File,
            val expectedOutputFile: File
        )
        listOf(
            TestEntry(
                inputFile = dir.resolve("gvy-input.settings.gradle"),
                expectedOutputFile = dir.resolve("gvy-output.settings.gradle")
            ),
            TestEntry(
                inputFile = dir.resolve("kt-input.settings.gradle.kts"),
                expectedOutputFile = dir.resolve("kt-output.settings.gradle.kts")
            )
        ).flatMap {
            listOf(
                it,
                TestEntry(
                    inputFile = it.inputFile.resolveSibling(
                        relative = "buildSrc-${it.inputFile.name}"
                    ),
                    expectedOutputFile = it.expectedOutputFile.resolveSibling(
                        relative = "buildSrc-${it.expectedOutputFile.name}"
                    )
                )
            )
        }.filter {
            it.inputFile.exists()
        }.also { list ->
            check(list.isNotEmpty())
        }.forEach { testEntry ->
            val inputFile = testEntry.inputFile
            val expectedOutputFile = testEntry.expectedOutputFile
            val isBuildSrc = testEntry.inputFile.name.startsWith("buildSrc-")
            val currentVersion = RefreshVersionsCorePlugin.currentVersion
            val inputText = inputFile.readText().replace("€{currentVersion}", currentVersion)

            val expectedOutput = expectedOutputFile.readText().replace("€{currentVersion}", currentVersion)
            val actualOutput = LegacyBootstrapMigrator(
                isBuildSrc = isBuildSrc,
                isKotlinDsl = inputFile.extension == "kts"
            ).gradleSettingsFileContentUpdatedWithPluginsDslSetup(settingsFileContent = inputText)

            assertEquals(
                expected = expectedOutput,
                actual = actualOutput
            )
        }
    }
}
