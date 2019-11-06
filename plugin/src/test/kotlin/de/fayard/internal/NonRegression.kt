package de.fayard.internal

import de.fayard.OrderBy
import de.fayard.refreshVersionsDir
import de.fayard.testResourceFile
import io.kotlintest.matchers.withClue
import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec
import java.io.File

class NonRegression : FreeSpec({

    val reportsFolder = testResourceFile("reports")
    val propertiesFolder = testResourceFile("properties")
    val jsonReports = reportsFolder.walk().filter { it.extension == "json" }.toList()

    fun receivedMessage(approved: File): Pair<File, String> {
        val received = approved.resolveSibling(approved.nameWithoutExtension + "-received." + approved.extension)
        val message = """
            |Files differ. Run:
            |       diff -u  ${approved.relativeTo(refreshVersionsDir)} ${received.relativeTo(refreshVersionsDir)}
            |""".trimMargin()
        return Pair(received, message)
    }


    // Run test for each file in src/test/resources/reports
    for (json in jsonReports) {
        val name = json.name
        "For non-regression file=$name" - {
            val name = json.nameWithoutExtension + ".txt"

            println("Parsing ${json.absolutePath}")
            val dependencyGraph: List<Dependency> = parseGraph(
                PluginConfig.readGraphFromJsonFile(json),
                PluginConfig.MEANING_LESS_NAMES
            )

            "Identifiers in Properties for file=$name" {

                val propertiesFile = propertiesFolder.resolve(name)
                val (received, message) = receivedMessage(propertiesFile)
                val extension = RefreshVersionsExtensionImpl(
                    propertiesFile = propertiesFile.relativeTo(refreshVersionsDir).path
                )
                val dependencies = (dependencyGraph.map { it.copy(available = null) })
                    .sortedBeautifullyBy(OrderBy.GROUP_AND_LENGTH) { it.versionProperty }
                    .distinctBy { it.versionProperty }
                UpdateProperties(extension).generateVersionProperties(received, dependencies)

                if (propertiesFile.exists()) {
                    val receivedIdentifiers = received.readLines().map { it.substringBefore("=", "") }.filter { it.startsWith("#") || it.isBlank() }
                    val approvedIdentifiers = propertiesFile.readLines().map { it.substringBefore("=", "") }.filter { it.startsWith("#") || it.isBlank() }
                    withClue(message) {
                        receivedIdentifiers shouldBe approvedIdentifiers
                        received.delete()
                    }
                } else {
                    println("Added to non-regression file ${propertiesFile.absolutePath}")
                }
            }
        }
    }
})




