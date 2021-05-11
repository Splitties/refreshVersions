package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.ArtifactVersionKeyRule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ArtifactVersionKeyRuleTest {

    @Test
    fun `Test kotlinx libraries`() {
        val versionKeyRule = ArtifactVersionKeyRule(
            artifactPattern = "  org.jetbrains.kotlinx:kotlinx-???(-*)".trimStart(),
            versionKeyPattern = "              ^^^^^^^.        ^^^"
        )
        kotlinxArtifacts.forEach {
            val group = it.substringBefore(':')
            val name = it.substringAfter(':')
            assert(versionKeyRule.matches(group, name)) { it }
            val expectedKey = "kotlinx.coroutines"
            assertEquals(expectedKey, versionKeyRule.key(group, name))
        }
        println("Yo")
        //TODO: Rework or remove these tests (which overlap more complete tests in dependencies plugin)
    }

    private val kotlinxArtifacts: List<String> = listOf(
        "org.jetbrains.kotlinx:kotlinx-coroutines-core",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core-native",
        "org.jetbrains.kotlinx:kotlinx-coroutines-core-android"
    )
}
