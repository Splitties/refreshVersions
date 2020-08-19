@file:JvmName("RefreshVersionsMigration")

package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.internal.currentVersionOfRefreshVersions
import org.gradle.api.initialization.Settings

@JvmName("migrateIfNeeded")
fun Settings.migrateRefreshVersionsIfNeeded(fromVersion: String) {
    val currentVersion = currentVersionOfRefreshVersions()
    if (currentVersion == fromVersion) return // User didn't upgrade yet, skip
/*
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
