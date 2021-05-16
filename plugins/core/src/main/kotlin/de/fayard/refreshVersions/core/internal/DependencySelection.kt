package de.fayard.refreshVersions.core

import org.gradle.api.HasImplicitReceiver

@HasImplicitReceiver
interface DependencyFilter {
    fun reject(selection: DependencySelection): Boolean
}

data class DependencySelection(
    val moduleId: ModuleId,
    val current: Version,
    val versionKey: String,
    var candidate: Version
)
