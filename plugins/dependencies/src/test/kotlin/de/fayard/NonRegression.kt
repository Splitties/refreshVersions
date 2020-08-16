package de.fayard

import de.fayard.refreshVersions.internal.DependencyMapping
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import io.kotlintest.fail
import io.kotlintest.matchers.boolean.shouldBeFalse
import io.kotlintest.matchers.haveSize
import io.kotlintest.matchers.withClue
import io.kotlintest.should
import io.kotlintest.specs.FreeSpec
import java.io.File

val testResources: File = File(".").absoluteFile.resolve("src/test/resources")

class NonRegression: FreeSpec({

    "We should never remove a property" {
        val existingProperties = testResources.resolve("dependencies-mapping-validated.txt")
        val receivedProperties = testResources.resolve("dependencies-mapping-received.txt")

        val existingMapping = existingProperties.readLines().mapNotNull { DependencyMapping.fromLine(it) }
        val receivedMapping = getArtifactNameToConstantMapping()
        receivedProperties.writeText(receivedMapping.joinToString(separator = "\n", postfix = "\n"))

        val breakingChanges = existingMapping - receivedMapping
        withClue("diff -u ${existingProperties.absolutePath}  ${receivedProperties.absolutePath}") {
            breakingChanges should haveSize(0)
        }
        receivedProperties.copyTo(existingProperties, overwrite = true)
        receivedProperties.deleteOnExit()
    }

    "Dependencies should not be in the `dependencies` package" {
        getArtifactNameToConstantMapping().forEach {
            if (it.constantName.startsWith("dependencies.")) {
                fail("This dependency should not be in the dependencies package: ${it.constantName}")
            }
            it.constantName.startsWith("dependencies.").shouldBeFalse()
        }
    }

})

