package de.fayard

import com.louiscad.splitties.de.fayard.dependencies.internal.DependencyMapping
import com.louiscad.splitties.de.fayard.dependencies.internal.getArtifactNameToSplittiesConstantMapping
import io.kotlintest.matchers.haveSize
import io.kotlintest.matchers.withClue
import io.kotlintest.should
import io.kotlintest.specs.FreeSpec
import java.io.File
import java.util.*

val resources: File = File(".").absoluteFile.resolve("src/test/resources")

class NonRegression: FreeSpec({

    "We should never remove a property" {
        val existingProperties = resources.resolve("dependencies-mapping-validated.properties")
        val receivedProperties = resources.resolve("dependencies-mapping-received.properties")

        val existingMapping = existingProperties.readLines().mapNotNull { DependencyMapping.fromLine(it) }
        val receivedMapping = getArtifactNameToSplittiesConstantMapping()
        receivedProperties.writeText(receivedMapping.joinToString("\n"))

        val breakingChanges = existingMapping - receivedMapping
        withClue("diff -u ${existingProperties.absolutePath}  ${receivedProperties.absolutePath}") {
            breakingChanges should haveSize(0)
        }
        receivedProperties.copyTo(existingProperties, overwrite = true)
        receivedProperties.deleteOnExit()
    }


})

