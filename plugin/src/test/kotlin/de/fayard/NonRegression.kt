package de.fayard

import de.fayard.internal.BuildSrcVersionsExtensionImpl
import de.fayard.internal.Dependency
import de.fayard.internal.PluginConfig
import de.fayard.internal.UpdateGradleProperties
import de.fayard.internal.parseGraph
import de.fayard.internal.sortedBeautifullyBy
import io.kotlintest.matchers.collections.shouldBeEmpty
import io.kotlintest.matchers.withClue
import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec
import java.io.File

class NonRegression : FreeSpec({

    val reportsFolder = testResourceFile("reports")
    val libsFolder = testResourceFile("libs")
    val versionsFolder = testResourceFile("versions")
    val propertiesFolder = testResourceFile("properties")
    val jsonReports = reportsFolder.walk().filter { it.extension == "json" }.toList()

    fun receivedMessage(approved: File): Pair<File, String> {
        val received = approved.resolveSibling(approved.nameWithoutExtension + "-received." + approved.extension)
        val message = """
            |Files differ. Run:
            |       diff -u  ${approved.relativeTo(buildSrcVersionsDir)} ${received.relativeTo(buildSrcVersionsDir)}
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

            "Identifiers in Libs for file=$name" {
                val libsFile = libsFolder.resolve(name)
                val libsIdentifiers: List<String> = dependencyGraph.map { it.escapedName }.sorted().distinct()
                val (received, message) = receivedMessage(libsFile)
                received.writeText(libsIdentifiers.joinToStringWithNewLines())
                if (libsFile.exists()) {
                    withClue(message) {
                        (libsFile.readLines() - libsIdentifiers).shouldBeEmpty()
                        received.delete()
                    }
                } else {
                    println("Added to non-regression file ${libsFile.absolutePath}")
                }
            }

            "Identifiers in Versions for file=$name" {
                val versionsFile = versionsFolder.resolve(name)
                val versionsIdentifiers = dependencyGraph.map { it.versionName }.sorted().distinct()
                val (received, message) = receivedMessage(versionsFile)
                received.writeText(versionsIdentifiers.joinToStringWithNewLines())
                if (versionsFile.exists()) {
                    withClue(message) {
                        (versionsFile.readLines() - versionsIdentifiers).shouldBeEmpty()
                        received.delete()
                    }
                } else {
                    println("Added to non-regression file ${versionsFile.absolutePath}")
                }
            }

            "Identifiers in Properties for file=$name" {

                val propertiesFile = propertiesFolder.resolve(name)
                val (received, message) = receivedMessage(propertiesFile)
                val extension = BuildSrcVersionsExtensionImpl(
                    versionsOnlyMode = VersionsOnlyMode.GRADLE_PROPERTIES,
                    versionsOnlyFile = propertiesFile.relativeTo(buildSrcVersionsDir).path
                )
                val dependencies = (dependencyGraph.map { it.copy(available = null) })
                    .sortedBeautifullyBy(OrderBy.GROUP_AND_LENGTH) { it.versionProperty }
                    .distinctBy { it.versionProperty }
                UpdateGradleProperties(extension).generateVersionProperties(received, dependencies)

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




