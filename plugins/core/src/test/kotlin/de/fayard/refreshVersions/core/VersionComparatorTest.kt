package de.fayard.refreshVersions.core

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class VersionComparatorTest {

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

    //TODO: Test extensively, notably with kotlinx.coroutines versions
}
