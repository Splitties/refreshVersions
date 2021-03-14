package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.PluginWithVersionCandidates
import de.fayard.refreshVersions.core.internal.SettingsPluginsUpdater
import extensions.java.util.loadAndGetAsMap
import extensions.kotlin.collections.subListAfter
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.file.shouldExist
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import java.io.File
import java.util.Properties
import kotlin.test.Ignore
import kotlin.test.assertEquals

class SettingsPluginUpdaterTest {

    private val testDataDir = testResources.resolve("gradle-settings-files-with-plugins")

    private val pluginsVersions: Map<String, List<String>> = testDataDir.resolve("plugins-versions").also {
        it.shouldExist()
    }.listFiles { file ->
        file.extension == "txt"
    }!!.asList().also { filesList ->
        filesList.shouldNotBeEmpty()
    }.associate { file ->
        file.nameWithoutExtension to file.useLines { lines ->
            lines.filter { line -> line.isNotBlank() }.toList()
        }
    }

    private val sampleDirs = testDataDir.resolve("samples").listFiles { file ->
        file.isDirectory
    }!!.asList()

    private val unitTestsSampleDataDir = testDataDir.resolve("unit-tests-sample-data")
        .resolve("SettingsPluginsUpdater")


    @TestFactory
    @Ignore //TODO: Un-ignore once SettingsPluginsUpdater is fully implemented.
    fun `files editing tests`(): List<DynamicTest> = sampleDirs.map { dirOfSample ->
        DynamicTest.dynamicTest(dirOfSample.name) {
            `test editing files`(dirOfSample)
        }
    }

    private fun `test editing files`(dirOfSample: File) {
        require(dirOfSample.isDirectory)
        val currentPluginsVersions = Properties().loadAndGetAsMap(
            file = dirOfSample.resolve("current-plugins.properties")
        )
        `test editing files`(
            currentPluginsVersions = currentPluginsVersions,
            inputFile = dirOfSample.resolve("gvy-input.settings.gradle"),
            outputFile = dirOfSample.resolve("gvy-output.settings.gradle")
        )
        `test editing files`(
            currentPluginsVersions = currentPluginsVersions,
            inputFile = dirOfSample.resolve("kt-input.settings.gradle.kts"),
            outputFile = dirOfSample.resolve("kt-output.settings.gradle.kts")
        )
    }

    private fun `test editing files`(
        currentPluginsVersions: Map<String, String>,
        inputFile: File,
        outputFile: File
    ) {
        val actualOutput = SettingsPluginsUpdater.updatedGradleSettingsFileContentWithAvailablePluginsUpdates(
            fileContent = inputFile.readText(),
            isKotlinDsl = inputFile.name.endsWith(".kts"),
            settingsPluginsUpdates = currentPluginsVersions.map { (pluginId, currentVersion) ->
                PluginWithVersionCandidates(
                    pluginId = pluginId,
                    currentVersion = currentVersion,
                    versionsCandidates = pluginsVersions[pluginId]!!.subListAfter(currentVersion).map { Version(it) }
                )
            }
        )
        assertEquals(
            expected = outputFile.readText(),
            actual = actualOutput
        )
    }

    @TestFactory
    fun `tests for StringBuilder#removeCommentsAddedByUs`(): List<DynamicTest> {
        val samplesDirs = unitTestsSampleDataDir.resolve("removeCommentsAddedByUs").listFiles { file ->
            file.isDirectory
        }!!.asList()
        return samplesDirs.map { dir ->
            DynamicTest.dynamicTest(dir.name) {
                `test StringBuilder#removeCommentsAddedByUs`(
                    inputFile = dir.resolve("gvy-with-comments.settings.gradle"),
                    expectedOutputFile = dir.resolve("gvy-without-comments.settings.gradle")
                )
                `test StringBuilder#removeCommentsAddedByUs`(
                    inputFile = dir.resolve("kt-with-comments.settings.gradle.kts"),
                    expectedOutputFile = dir.resolve("kt-without-comments.settings.gradle.kts")
                )
            }
        }
    }

    private fun `test StringBuilder#removeCommentsAddedByUs`(
        inputFile: File,
        expectedOutputFile: File
    ) {
        val actualOutput = buildString {
            append(inputFile.readText())
            with(SettingsPluginsUpdater) {
                removeCommentsAddedByUs()
            }
        }
        assertEquals(
            expected = expectedOutputFile.readText(),
            actual = actualOutput
        )
    }
}
