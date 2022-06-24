package de.fayard.refreshVersions.core.internal.migrations

import de.fayard.refreshVersions.core.RefreshVersionsCorePlugin
import de.fayard.refreshVersions.core.internal.DependencyMapping
import de.fayard.refreshVersions.core.internal.removals_replacement.RemovedDependencyNotationsReplacementInfo
import de.fayard.refreshVersions.core.internal.removals_replacement.replaceRemovedDependencyNotationReferencesIfNeeded
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel
import de.fayard.refreshVersions.core.internal.versions.writeTo
import java.io.File

internal fun runMigrationsIfNeeded(
    projectDir: File,
    versionsPropertiesFile: File,
    versionsPropertiesModel: VersionsPropertiesModel,
    dependencyMapping: List<DependencyMapping>,
    getRemovedDependencyNotationsReplacementInfo: () -> RemovedDependencyNotationsReplacementInfo
) {
    val currentVersion = RefreshVersionsCorePlugin.currentVersion
    val lastVersion = versionsPropertiesModel.generatedByVersion
    if (currentVersion == lastVersion) {
        if (versionsPropertiesModel.dependencyNotationRemovalsRevision == null) {
            return // No revision in the versions.properties file means version alone is enough.
        }
    }

    val info = getRemovedDependencyNotationsReplacementInfo()

    if (currentVersion == lastVersion) {
        if (info.currentRevision == versionsPropertiesModel.dependencyNotationRemovalsRevision) {
            return // Handles the case of matching revisions in snapshot to snapshot upgrade.
        }
    }
    val revisionOfLastRefreshVersionsRun = info.readRevisionOfLastRefreshVersionsRun(
        lastVersion,
        versionsPropertiesModel.dependencyNotationRemovalsRevision
    )
    migrateLegacySymbolsIfNeeded(
        projectDir = projectDir,
        revisionOfLastRefreshVersionsRun = revisionOfLastRefreshVersionsRun
    )
    replaceRemovedDependencyNotationReferencesIfNeeded(
        projectDir = projectDir,
        dependencyMapping = dependencyMapping,
        revisionOfLastRefreshVersionsRun = revisionOfLastRefreshVersionsRun,
        info = info
    )
    versionsPropertiesModel.copy(
        dependencyNotationRemovalsRevision = info.currentRevision.takeIf { currentVersion.endsWith("-SNAPSHOT") }
    ).writeTo(versionsPropertiesFile)
}
