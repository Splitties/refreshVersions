package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.KotlinScript
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class KotlinScriptTest : FunSpec({

    test("Update with available updates") {
        val input: FolderInput = FolderInput("kotlin-script-refreshversions", ".kts")

        val script = KotlinScript(input.initial.readText(), input.dependenciesUpdates)
        script.updateNewVersions(input.actual)
        input.actual.readText() shouldBe input.expectedText
        input.actual.delete()
    }

    test("Cleanup from available updates") {
        val input: FolderInput = FolderInput("kotlin-script-cleanup", ".kts")

        val script = KotlinScript(input.initial.readText(), input.dependenciesUpdates)
        script.cleanupComments(input.actual)
        input.actual.readText() shouldBe input.expectedText
        input.actual.delete()
    }
})