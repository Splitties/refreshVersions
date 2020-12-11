package de.fayard.refreshVersions.core.versions

import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel
import de.fayard.refreshVersions.core.internal.versions.insertNewLinesIfNeeded
import de.fayard.refreshVersions.core.internal.versions.readFromText
import de.fayard.refreshVersions.core.internal.versions.toText
import de.fayard.refreshVersions.core.testResources
import io.kotest.matchers.shouldBe
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class VersionsPropertiesModelTest {

    private val samplesDir = testResources.resolve("versions-properties-samples")

    @Test
    fun `test parsing old format`() {
        samplesDir.resolve("old-format").listFiles()!!.filter {
            it.isDirectory
        }.also { check(it.isNotEmpty()) }.forEach {
            println("== ${it.absolutePath}")
            val inputFile = it.resolve("input.properties")
            val outputFile = it.resolve("output.properties")
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

    @Test
    fun `test new format`() {
        val checkedFilesCount = samplesDir.resolve("new-format").walkTopDown().filter {
            it.extension == "properties"
        }.onEach {
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
        }.count()
        assertTrue(checkedFilesCount >= 0, message = "No test files found!")
    }

    @Test
    fun `test new format parsing`() {
        val checkedFilesCount = samplesDir.resolve("new-format-parsing-only").walkTopDown().filter {
            it.extension == "properties"
        }.onEach {
            VersionsPropertiesModel.readFromText(it.readText())
        }.count()
        assertTrue(checkedFilesCount >= 0, message = "No test files found!")
    }

    @Test
    fun `test parsing incorrectly formatted version files fails as expected`() {
        val checkedFilesCount = samplesDir.resolve("new-format-invalid").walkTopDown().filter {
            it.extension == "properties"
        }.onEach {
            val fileContent = it.readText()
            assertFailsWith<IllegalStateException> {
                VersionsPropertiesModel.readFromText(fileContent)
            }
        }.count()
        assertTrue(checkedFilesCount >= 0, message = "No test files found!")
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
