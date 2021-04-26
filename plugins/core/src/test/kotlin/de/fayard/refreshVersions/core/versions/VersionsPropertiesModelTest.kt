package de.fayard.refreshVersions.core.versions

import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel
import de.fayard.refreshVersions.core.internal.versions.insertNewLinesIfNeeded
import de.fayard.refreshVersions.core.internal.versions.readFromText
import de.fayard.refreshVersions.core.internal.versions.toText
import de.fayard.refreshVersions.core.testResources
import extensions.junit.mapDynamicTest
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class VersionsPropertiesModelTest {

    private val samplesDir = testResources.resolve("versions-properties-samples")

    @TestFactory
    fun `test parsing old format`(): List<DynamicTest> {
        val samplesDirs = samplesDir.resolve("old-format").listFiles()!!.filter {
            it.isDirectory
        }.also { check(it.isNotEmpty()) }
        return samplesDirs.mapDynamicTest { dir ->
            val inputFile = dir.resolve("input.properties")
            val outputFile = dir.resolve("output.properties")
            val parsedModel = VersionsPropertiesModel.readFromText(inputFile.readText())
            assertEquals(
                expected = outputFile.readText(),
                actual = parsedModel.toText(),
                message = "Output of model parsed from input should match the expected input!"
            )
            val reParsedModel = VersionsPropertiesModel.readFromText(parsedModel.toText())
            assertEquals(
                expected = parsedModel,
                actual = reParsedModel,
                message = "Model shouldn't change after being written and parsed again!"
            )
            assertEquals(
                expected = parsedModel.toText(),
                actual = reParsedModel.toText(),
                message = "File content shouldn't change after being parsed and written again!"
            )
        }
    }

    @TestFactory
    fun `test new format`(): List<DynamicTest> {
        val sampleFiles = samplesDir.resolve("new-format").walkTopDown().filter {
            it.extension == "properties"
        }.toList().also { check(it.isNotEmpty()) { "No test files found!" } }
        return sampleFiles.mapDynamicTest {
            val fileContent = it.readText()
            val parsedModel = VersionsPropertiesModel.readFromText(fileContent)
            val reParsedModel = VersionsPropertiesModel.readFromText(parsedModel.toText())
            assertEquals(
                expected = fileContent,
                actual = parsedModel.toText(),
                message = "Parsing and writing back should yield the same result!"
            )
            assertEquals(
                expected = parsedModel.toText(),
                actual = reParsedModel.toText(),
                message = "File content shouldn't change after being parsed and written again!"
            )
            assertEquals(
                expected = parsedModel,
                actual = reParsedModel,
                message = "Model shouldn't change after being written and parsed again!"
            )
        }
    }

    @TestFactory
    fun `test new format parsing`(): List<DynamicTest> {
        val sampleFiles = samplesDir.resolve("new-format-parsing-only").walkTopDown().filter {
            it.extension == "properties"
        }.toList().also { check(it.isNotEmpty()) { "No test files found!" } }
        return sampleFiles.mapDynamicTest {
            VersionsPropertiesModel.readFromText(it.readText())
        }
    }

    @TestFactory
    fun `test parsing incorrectly formatted version files fails as expected`(): List<DynamicTest> {
        val sampleFiles = samplesDir.resolve("new-format-invalid").walkTopDown().filter {
            it.extension == "properties"
        }.toList().also { check(it.isNotEmpty()) { "No test files found!" } }
        return sampleFiles.mapDynamicTest {
            val fileContent = it.readText()
            assertFailsWith<IllegalStateException> {
                VersionsPropertiesModel.readFromText(fileContent)
            }
        }
    }

    @Test
    fun `insert new lines if necessaary`() {
        val input = """
            plugin.android=4.1.0
            #ok
            plugin.com.osacky.doctor=0.6.2
            """.trimIndent()
        val expected = """
            plugin.android=4.1.0
            #ok

            plugin.com.osacky.doctor=0.6.2
            """.trimIndent()
        input.insertNewLinesIfNeeded() shouldBe expected
    }
}
