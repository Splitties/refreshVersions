@file:JvmName("RefreshVersionsMigration")

package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.RefreshVersionsCorePlugin
import org.gradle.api.initialization.Settings

@JvmName("migrateIfNeeded")
fun Settings.migrateRefreshVersionsIfNeeded(fromVersion: String) {
    val currentVersion = RefreshVersionsCorePlugin.currentVersion
    if (currentVersion == fromVersion) return // User didn't upgrade yet, skip
    removeMigrationCall(this)
/*
TODO: Revisit/remove this once we put the version of refreshVersions in the versions.properties file.
 Note: We might need to keep an ordered list of all the versions, including dev versions for
 optimal operation.

Implementation guide for future migrations:

- Put the version we need to migrate from in an internal top-level/object variable.
- Perform any needed migration from the bootstrap if this variable is set. Must be idempotent.
- Reset that variable back to null.
- If no available version comments are there, clear the call to this function by rewriting the Gradle settings file.
- If there's still available version comments, update the migrateIfNeeded call with the current version.

The migration code must support skipping any number of refreshVersions versions,
running all migrations from the oldest to the newest before letting the bootstrap
actually run. To do so, have a List of a pair of the version and its migration to the next version,
ordered from oldest to newest. Migration will run all migrations in order,
skipping all migrations before the passed version. Also, report if passed version is unknown.
 */
}

private fun removeMigrationCall(settings: Settings) {
    val isKotlinDsl: Boolean
    val settingsFile = settings.settingsDir.resolve("settings.gradle.kts").let { kotlinDslSettings ->
        if (kotlinDslSettings.exists()) kotlinDslSettings.also { isKotlinDsl = true } else {
            settings.settingsDir.resolve("settings.gradle").also {
                check(it.exists())
                isKotlinDsl = false
            }
        }
    }
    val initialContent = settingsFile.readText()
    val newContent = withRemovedMigrationCall(
        isKotlinDsl = isKotlinDsl,
        initialContent = initialContent
    )
    settingsFile.writeText(newContent)
}

/**
 * Visible for testing.
 */
internal fun withRemovedMigrationCall(
    isKotlinDsl: Boolean,
    initialContent: String
): String {

    class ExpectedValues(
        migrationPackageName: String = "de.fayard.refreshVersions",
        migrationCallSymbol: String
    ) {
        val migrationCallImport = "import $migrationPackageName.${migrationCallSymbol.substringBefore('.')}"
        val migrationCallText = "\n$migrationCallSymbol("
    }

    val expectedValues = ExpectedValues(
        migrationCallSymbol = when {
            isKotlinDsl -> "migrateRefreshVersionsIfNeeded"
            else -> "RefreshVersionsMigration.migrateIfNeeded"
        }
    )

    val migrationImportIndex = initialContent.indexOf(expectedValues.migrationCallImport).also {
        check(it != -1) { "Migration import not found." }
    }

    val migrationCallIndex = initialContent.indexOf(expectedValues.migrationCallText).also {
        check(it != -1) { "Migration call not found." }
    }

    return buildString {
        val preImportText = initialContent.substring(startIndex = 0, endIndex = migrationImportIndex)
        append(preImportText)
        val postImportText = initialContent.substring(
            startIndex = migrationImportIndex,
            endIndex = migrationCallIndex
        ).substringAfter('\n')
        append(postImportText)
        val postCallText = initialContent.substring(startIndex = migrationCallIndex + 2)
            .substringAfter('\n').removePrefix("\n")
        appendln()
        append(postCallText)
    }
}
