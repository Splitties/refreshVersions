package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.ArtifactVersionKeyRule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import testutils.junit.dynamicTest
import kotlin.test.assertEquals

class ArtifactVersionKeyRuleTest {

    @Test
    fun `Test kotlinx libraries`() = test(
        artifactPattern = "  org.jetbrains.kotlinx:kotlinx-???(-*)".trimStart(),
        versionKeyPattern = "              ^^^^^^^.        ^^^",
        libraries = listOf(
            "org.jetbrains.kotlinx:kotlinx-coroutines-core",
            "org.jetbrains.kotlinx:kotlinx-coroutines-core-native",
            "org.jetbrains.kotlinx:kotlinx-coroutines-core-android"
        ),
        expectedKey = "kotlinx.coroutines"
    )

    @Test
    fun `Test google play ktx libraries`() = test(
        artifactPattern = "  com.google.android.play:*(-ktx)".trimStart(),
        versionKeyPattern = "    ^^^^^^^^^^^^^^^^^^^.^",
        libraries = listOf(
            "com.google.android.play:asset-delivery",
            "com.google.android.play:asset-delivery-ktx"
        ),
        expectedKey = "google.android.play.asset-delivery"
    )

    @Test
    fun `Test androidx customview library`() = test(
        artifactPattern = "  androidx.customview:customview(-*)".trimStart(),
        versionKeyPattern = "^^^^^^^^.           ^^^^^^^^^^^^^^",
        libraries = listOf(
            "androidx.customview:customview",
        ),
        expectedKey = "androidx.customview"
    )

    @Test
    fun `Test androidx customview-poolingcontainer library`() = test(
        artifactPattern = "  androidx.customview:customview(-*)".trimStart(),
        versionKeyPattern = "^^^^^^^^.           ^^^^^^^^^^^^^^",
        libraries = listOf(
            "androidx.customview:customview-poolingcontainer",
        ),
        expectedKey = "androidx.customview-poolingcontainer"
    )

    private fun test(
        artifactPattern: String,
        versionKeyPattern: String,
        libraries: List<String>,
        expectedKey: String
    ) {
        val versionKeyRule = ArtifactVersionKeyRule(
            artifactPattern = artifactPattern,
            versionKeyPattern = versionKeyPattern
        )
        libraries.forEach {
            val group = it.substringBefore(':')
            val name = it.substringAfter(':')
            assert(versionKeyRule.matches(group, name)) {
                "Artifact $it didn't match rule that created regex $versionKeyRule"
            }
            println("$versionKeyRule")
            assertEquals(expectedKey, versionKeyRule.key(group, name))
        }
    }

    @TestFactory
    fun `Rules are ordered by specificity`(): List<DynamicTest> = listOf(
        listOf(
            ArtifactVersionKeyRule(
                artifactPattern = "  androidx.recyclerview:recyclerview-selection".trimStart(),
                versionKeyPattern = "^^^^^^^^.             ^^^^^^^^^^^^^^^^^^^^^^"
            ),
            ArtifactVersionKeyRule(
                artifactPattern = "  androidx.*:*".trimStart(),
                versionKeyPattern = "^^^^^^^^^^"
            )
        ),
        listOf(
            ArtifactVersionKeyRule(
                artifactPattern = "  androidx.camera:camera-view(-*)".trimStart(),
                versionKeyPattern = "^^^^^^^^.       ^^^^^^.^^^^"
            ),
            ArtifactVersionKeyRule(
                artifactPattern = "  androidx.*:*".trimStart(),
                versionKeyPattern = "^^^^^^^^^^"
            )
        )
    ).map { expectedOrderedList ->
        dynamicTest(displayName = expectedOrderedList.first().artifactPattern) {
            assertEquals(
                expected = expectedOrderedList,
                actual = expectedOrderedList.sortedDescending()
            )
        }
    }
}
