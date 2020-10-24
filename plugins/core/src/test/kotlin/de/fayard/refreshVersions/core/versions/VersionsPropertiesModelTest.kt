package de.fayard.refreshVersions.core.versions

import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel
import de.fayard.refreshVersions.core.internal.versions.readFromText
import de.fayard.refreshVersions.core.internal.versions.toText
import de.fayard.refreshVersions.core.testResources
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
            val inputFile = it.resolve("input.properties")
            val outputFile = it.resolve("output.properties")
            val parsedModel = VersionsPropertiesModel.readFromText(inputFile.readText())
            assertEquals(
                expected = parsedModel.toText(),
                actual = outputFile.readText(),
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
        samplesDir.resolve("new-format").listFiles()!!.flatMap {
            it.listFiles()?.asList() ?: emptyList()
        }.filter {
            it.extension == "properties"
        }.also { check(it.isNotEmpty()) }.forEach {
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
}
