package de.fayard.refreshVersions.migration.upgrade

import de.fayard.refreshVersions.withRemovedMigrationCall
import de.fayard.testResources
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class RefreshVersionsUpgradeMigrationTest {

    private val resources = testResources.resolve("migration/upgrade")

    @Test
    fun `test removal of migration call for Gradle Kotlin DSL`() {
        val actual = withRemovedMigrationCall(
            isKotlinDsl = true,
            initialContent = resources.resolve("settings.gradle.kts.before.txt").readText()
        )
        val expected = resources.resolve("settings.gradle.kts.after.txt").readText()
        Assertions.assertEquals(expected, actual)
    }

    @Test
    fun `test removal of migration call for Gradle Groovy DSL`() {
        val actual = withRemovedMigrationCall(
            isKotlinDsl = false,
            initialContent = resources.resolve("settings.gradle.before.txt").readText()
        )
        val expected = resources.resolve("settings.gradle.after.txt").readText()
        Assertions.assertEquals(expected, actual)
    }
}
