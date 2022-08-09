package de.fayard.refreshVersions

import io.kotest.assertions.withClue
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldContainOnlyOnce
import io.kotest.matchers.string.shouldHaveMinLength
import org.junit.jupiter.api.Test

class RemovedDependencyNotationsHistoryCorrectnessTest {

    @Test
    fun `Mapping of version to removals revision should have correct format`() {
        val separator = "->"
        val fileName = "version-to-removals-revision-mapping.txt"
        val file = mainResources.resolve(fileName)
        file.useLines { lines ->
            val nonEmptyLines = lines.withIndex().sumBy { (index, line) ->
                if (line.isEmpty()) return@sumBy 0
                withClue("Line $index of $file") {
                    line shouldContainOnlyOnce separator
                    val version = line.substringBefore(separator)
                    val revision = line.substringAfter(separator).toInt()
                    revision shouldBeGreaterThan 0
                    version shouldHaveMinLength 1
                    withClue("Unexpected character in the version") {
                        version.all { it.isLetterOrDigit() || it in ".-" } shouldBe true
                    }
                }
                1
            }
            withClue("File must not be empty $file") {
                nonEmptyLines shouldBeGreaterThan 0
            }
        }
    }
}
