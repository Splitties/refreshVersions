package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.legacy.LegacyBootstrapMigrator
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
        return sampleDirs.map { dir ->
            DynamicTest.dynamicTest(dir.name) {
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
                    `test LegacyBootstrapMigrator`(
                        inputFile = testEntry.inputFile,
                        expectedOutputFile = testEntry.expectedOutputFile,
                        isBuildSrc = testEntry.inputFile.name.startsWith("buildSrc-")
                    )
                }
            }
        }
    }

    private fun `test LegacyBootstrapMigrator`(
        inputFile: File,
        expectedOutputFile: File,
        isBuildSrc: Boolean
    ) {
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
