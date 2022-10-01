package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.core.internal.sortWith
import io.kotest.assertions.withClue
import io.kotest.matchers.collections.shouldBeSorted
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory
import testutils.junit.dynamicTest
import testutils.junit.mapDynamicTest
import java.io.File

class VersionComparatorTest {

    @Test
    fun `same version with qualifier should be newer`() {
        Version("10.0.1-whatever") shouldBeGreaterThan Version("10.0.1")
    }

    @Test
    fun testSplittiesVersions() {
        val devVersion = Version("3.0.0-dev-056")
        val alphaVersion = Version("3.0.0-alpha06")
        println(devVersion > alphaVersion)
        println(devVersion < alphaVersion)
    }

    @Test
    fun testSomeVersions() {
        val rc = Version("1.10.0-RC1")
        val stable = Version("1.10.0")
        Assertions.assertTrue(stable > rc)
    }

    @TestFactory
    fun testStabilityLevel(): List<DynamicTest> {
        val targetDir = testResources.resolve(
            relative = "versions-stability-level"
        )

        fun checkVersions(expectedStabilityLevel: StabilityLevel) {
            val fileName = "${expectedStabilityLevel.name}.txt"
            val versions = readVersionsWithIntactOrder(targetDir.resolve(fileName))
            for (version in versions) {
                if (version.stabilityLevel == expectedStabilityLevel) continue
                withClue("The stability level of version $version is wrong") {
                    version.stabilityLevel shouldBe expectedStabilityLevel
                }
            }
        }
        return StabilityLevel.values().map { stabilityLevel ->
            dynamicTest(stabilityLevel.name) { checkVersions(stabilityLevel) }
        }
    }

    @TestFactory
    fun `test versions sorting`() = testResources.resolve(
        relative = "versions-comparison"
    ).listFiles()!!.asList().mapDynamicTest(name = { it.nameWithoutExtension }) { file ->
        val orderedVersions = readVersionsWithIntactOrder(file)
        orderedVersions.shouldBeSorted()
    }

    @TestFactory
    fun `test versions sorting through the sortWith function`() = testResources.resolve(
        relative = "versions-comparison"
    ).listFiles()!!.asList().mapDynamicTest(name = { it.nameWithoutExtension }) { file ->
        val orderedVersions = readVersionsWithIntactOrder(file)
        val fetcherResult = DependencyVersionsFetcher.Result.Success(
            lastUpdateTimestampMillis = 0L,
            availableVersions = orderedVersions.toList()
        )
        val actualList = listOf(fetcherResult).sortWith(RefreshVersionsConfigHolder.resultMode).first
        actualList shouldBe orderedVersions
    }

    private fun readVersionsWithIntactOrder(file: File): List<Version> = file.useLines { lines ->
        lines.filter {
            it.isNotBlank() && it.startsWith("//").not()
        }.map {
            Version(it)
        }.toList()
    }.also { check(it.isNotEmpty()) }
}
