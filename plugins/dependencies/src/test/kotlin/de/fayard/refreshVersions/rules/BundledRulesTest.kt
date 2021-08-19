package de.fayard.refreshVersions.rules

import de.fayard.refreshVersions.core.internal.ArtifactVersionKeyReader
import de.fayard.refreshVersions.mainResources
import org.junit.jupiter.api.*
import testutils.junit.dynamicTest

@Suppress("UnstableApiUsage")
class BundledRulesTest {

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
