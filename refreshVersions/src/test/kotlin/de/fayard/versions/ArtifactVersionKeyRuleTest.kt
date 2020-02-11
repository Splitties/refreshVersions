package de.fayard.versions

import de.fayard.versions.internal.ArtifactVersionKeyRule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ArtifactVersionKeyRuleTest {

    @Test
    fun `Test kotlinx libraries`() {
        val kotlinxArtifacts: List<String> = listOf(
            "org.jetbrains.kotlinx:kotlinx-coroutines-core",
            "org.jetbrains.kotlinx:kotlinx-coroutines-core-native",
            "org.jetbrains.kotlinx:kotlinx-coroutines-core-android"
        )
        val versionKeyRule = ArtifactVersionKeyRule(
            artifactPattern = "org.jetbrains.kotlinx:kotlinx-*",
            versionKeyPattern = "              ^^^^^^"
        )
        kotlinxArtifacts.forEach {
            val group = it.substringBefore(':')
            val name = it.substringAfter(':')
            assert(versionKeyRule.matches(group, name)) { it }
        }
    }
}
