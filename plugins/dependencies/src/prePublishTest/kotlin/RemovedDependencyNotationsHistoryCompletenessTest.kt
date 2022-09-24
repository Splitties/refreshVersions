package de.fayard.refreshVersions

import io.kotest.assertions.withClue
import io.kotest.matchers.ints.shouldBeGreaterThan
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import org.opentest4j.TestAbortedException

class RemovedDependencyNotationsHistoryCompletenessTest {

    @Test
    fun `No WIP section should be left`() {
        val removalsRevisionsHistoryFile = mainResources.resolve("removals-revisions-history.md")
        val removalsRevisionsHistory = removalsRevisionsHistoryFile.readText()
        removalsRevisionsHistory.lineSequence().indexOf("## [WIP]").let {
            if (it != -1) fail {
                "Found a WIP section at line ${it + 1}. A proper revision must be committed for publishing."
            }
        }
    }

    @Test
    fun `Mapping of version to removals revision should be filled for this release`() {
        if (pluginsVersion.endsWith("-SNAPSHOT")) {
            throw TestAbortedException("Snapshots embed their revision number in their version.")
        }
        val (version, revision) = mainResources.resolve("version-to-removals-revision-mapping.txt").useLines { lines ->
            lines.filter { it.isNotEmpty() }.last()
        }.split("->")
        version shouldBe pluginsVersion
        withClue("Revision must be a proper and positive integer") {
            revision.toInt() shouldBeGreaterThan 0
        }
    }
}
