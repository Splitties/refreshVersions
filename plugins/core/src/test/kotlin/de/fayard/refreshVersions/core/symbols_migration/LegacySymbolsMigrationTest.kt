package de.fayard.refreshVersions.core.symbols_migration

import de.fayard.refreshVersions.core.internal.migrations.gradleFileContentUpdatedWithVersionForMigrated
import de.fayard.refreshVersions.core.testResources
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import testutils.junit.mapDynamicTest

class LegacySymbolsMigrationTest {

    @TestFactory
    fun `test gradleFileContentUpdatedWithVersionForMigrated`(): List<DynamicTest> {
        return testResources.resolve(
            relative = "symbols_migration/versionFor"
        ).listFiles()!!.asList().mapDynamicTest { dir ->
            val inputFile = dir.resolve("input.build.gradle")
            val outputFile = dir.resolve("output.build.gradle")
            val input = inputFile.readText()
            val output = outputFile.readText().takeUnless { it == input }
            gradleFileContentUpdatedWithVersionForMigrated(
                fileContent = input
            ) shouldBe output
        }
    }
}
