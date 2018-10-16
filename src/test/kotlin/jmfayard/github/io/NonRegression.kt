package jmfayard.github.io

import io.kotlintest.fail
import io.kotlintest.specs.FunSpec
import java.io.File

class NonRegression : FunSpec({

    val reportsFolder = testResourceFile("reports")
    val namesFolder = testResourceFile("names")
    val jsonReports = reportsFolder.walk().filter { it.extension == "json" }.toList()

    // Run test for each file in src/test/resources/reports
    for (jsonFile in jsonReports) {

        test(name = "For file ${jsonFile.name}") {
            val nonregFile = namesFolder.resolve(jsonFile.nameWithoutExtension + ".txt")
            nonRegressionForFile(jsonFile, nonregFile)
        }

    }


})


fun nonRegressionForFile(json: File, nonregFile: File) {
    println("Parsing ${json.absolutePath}")
    val dependencyGraph: DependencyGraph = SyncLibsTask.readGraphFromJsonFile(json)
    val escapedNames: List<String> = SyncLibsTask.parseGraph(dependencyGraph).map { it.escapedName }


    if (nonregFile.exists()) {
        println("Comparing identifiers with ${nonregFile.absolutePath}")
        val missingNames = nonregFile.readLines() - escapedNames
        if (missingNames.isNotEmpty()) fail(
            """
              Missing identifiers for ${json.name} compared to ${nonregFile.name}:
              $missingNames""".trimIndent()
        ) else {
            println("Non-regression ok")
            nonregFile.writeText(escapedNames.joinToStringWithNewLines())
        }
    } else {
        nonregFile.writeText(escapedNames.joinToStringWithNewLines())
        println("Added to non-regression file ${nonregFile.absolutePath}")
    }
    println()

}


