package de.fayard.refreshVersions.rules

import de.fayard.refreshVersions.core.internal.ArtifactVersionKeyReader
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import testutils.junit.dynamicTest
import java.io.File

@Suppress("UnstableApiUsage")
class BundledRulesTest {

    private val mainResources: File = File(".").absoluteFile.resolve("src/main/resources")
    private val rulesDir = mainResources.resolve("refreshVersions-rules")
    private val versionKeyReader = ArtifactVersionKeyReader.fromRules(rulesDir.listFiles()!!.map { it.readText() })

    @TestFactory
    fun `test bundled version key rules against dependencies constants`(): List<DynamicTest> {
        return bundledRules.map { (expectedKey, moduleIdentifiers) ->
            dynamicTest(displayName = expectedKey) {
                moduleIdentifiers.forEach { moduleIdentifier ->
                    Assertions.assertEquals(
                        expectedKey,
                        versionKeyReader.readVersionKey(moduleIdentifier.group, moduleIdentifier.name)
                    )
                }
            }
        }
    }

    @Test
    @Disabled("Not implemented yet")
    fun `check all dependencies constants have version key rules`() {
        TODO()
    }
}
