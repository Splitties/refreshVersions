package de.fayard.refreshVersions.core.versions

import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel
import de.fayard.refreshVersions.core.internal.versions.readFromText
import de.fayard.refreshVersions.core.internal.versions.toText
import de.fayard.refreshVersions.core.testResources
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.ints.shouldBePositive
import io.kotest.matchers.shouldBe
import kotlin.test.Test

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

            outputFile.readText() shouldBe parsedModel.toText()

            val reParsedModel = VersionsPropertiesModel.readFromText(parsedModel.toText())

            reParsedModel shouldBe parsedModel
            reParsedModel.toText() shouldBe parsedModel.toText()
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

            parsedModel.toText() shouldBe fileContent
            reParsedModel.toText() shouldBe fileContent
            reParsedModel shouldBe parsedModel
        }
    }

    @Test
    fun `test parsing incorrectly formatted version files fails as expected`() {
        val checkedFilesCount = samplesDir.resolve("new-format-invalid").walkTopDown().filter {
            it.extension == "properties"
        }.onEach {
            val fileContent = it.readText()
            shouldThrow<IllegalStateException> {
                VersionsPropertiesModel.readFromText(fileContent)
            }
        }.count()
        checkedFilesCount.shouldBePositive()
    }
}
