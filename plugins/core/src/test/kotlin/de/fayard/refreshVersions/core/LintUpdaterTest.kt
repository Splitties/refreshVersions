package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.updateLintXml
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.TestFactory
import testutils.junit.mapDynamicTest
import java.io.File

class LintUpdaterTest {

    private val testDataDir = testResources.resolve("lint-updater")

    private val dirs = testDataDir.listFiles { file ->
        file.isDirectory
    }!!.asList()

    @TestFactory
    fun `lint updater`() = dirs.mapDynamicTest { dirOfSample ->
        `test lint updater`(dirOfSample)
    }

    private fun `test lint updater`(directory: File) {
        require(directory.isDirectory)
        val input = directory.resolve("input.xml").readText()
        val expected = directory.resolve("expected.xml").readText()
        updateLintXml(input).trim() shouldBe expected.trim()
    }
}

