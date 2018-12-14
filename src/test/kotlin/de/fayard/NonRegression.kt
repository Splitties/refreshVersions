package de.fayard

import io.kotlintest.fail
import io.kotlintest.specs.FreeSpec
import java.io.File

class NonRegression : FreeSpec({

    val reportsFolder = testResourceFile("reports")
    val libsFolder = testResourceFile("libs")
    val versionsFolder = testResourceFile("versions")
    val jsonReports = reportsFolder.walk().filter { it.extension == "json" }.toList()


    "Files from resources folder" - {

        // Run test for each file in src/test/resources/reports
        for (jsonFile in jsonReports) {

            "For file ${jsonFile.name}" {
                val name = jsonFile.nameWithoutExtension + ".txt"
                nonRegressionForFile(jsonFile, libsFolder.resolve(name), versionsFolder.resolve(name))
            }

        }
    }



})





fun nonRegressionForFile(json: File, libsFile: File, versionsFile: File) {
    println("Parsing ${json.absolutePath}")
    val dependencyGraph: List<Dependency> = BuildSrcVersionsTask.parseGraph(
        BuildSrcVersionsTask.readGraphFromJsonFile(
            json
        )
    )
    val libsIdentifiers: List<String> = dependencyGraph.map { it.escapedName }.sorted().distinct()
    val versionsIdentifiers = dependencyGraph.map { it.versionName }.sorted().distinct()


    val missingLibs = if (libsFile.exists()) {
        libsFile.readLines() - libsIdentifiers
    } else {
        emptyList()
    }
    val missingVersions = if (versionsFile.exists()) {
        versionsFile.readLines() - versionsIdentifiers
    } else {
        emptyList()
    }

    println("Comparing identifiers with ${libsFile.absolutePath}")
    if (missingLibs.isNotEmpty()) {
        fail(
            """
              Missing identifiers for ${json.name} compared to ${libsFile.name}:
              $missingLibs""".trimIndent()
        )
    } else {
        libsFile.writeText(libsIdentifiers.joinToStringWithNewLines())
        println("Added to non-regression file ${libsFile.absolutePath}")
    }

    if (missingVersions.isNotEmpty()) {
        fail(
            """
              Missing identifiers for ${json.name} compared to ${libsFile.name}:
              $missingVersions""".trimIndent()
        )
    } else {
        versionsFile.writeText(versionsIdentifiers.joinToStringWithNewLines())
        println("Added to non-regression file ${versionsFile.absolutePath}")
    }

    println()

}


