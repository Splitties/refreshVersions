package de.fayard.refreshVersions.core.versions

import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel
import de.fayard.refreshVersions.core.internal.versions.readFromText
import de.fayard.refreshVersions.core.internal.versions.toText
import de.fayard.refreshVersions.core.testResources
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class VersionsPropertiesModelTest {

    private val samplesDir = testResources.resolve("versions-properties-samples")

    @Test
    fun `test parsing old format`() {
        val samplesDirs = samplesDir.resolve("old-format").listFiles()!!.filter { it.isDirectory }
        samplesDirs.forEach {
            val inputFile = it.resolve("input.properties")
            val outputFile = it.resolve("output.properties")
            val parsedModel = VersionsPropertiesModel.readFromText(inputFile.readText())
            assertEquals(
                parsedModel.toText(),
                outputFile.readText(),
                "Output of model parsed from input doesn't match the expected input!"
            )
            val reParsedModel = VersionsPropertiesModel.readFromText(parsedModel.toText())
            assertEquals(
                parsedModel,
                reParsedModel,
                "Model shouldn't change after being written and parsed again!"
            )
            assertEquals(
                parsedModel.toText(),
                reParsedModel.toText(),
                "File content shouldn't change after being parsed and written again!"
            )
        }
    }
}
