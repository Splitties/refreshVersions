package de.fayard.refreshVersions.core

import org.gradle.api.Incubating

class DependencySelection internal constructor(
    @Incubating
    val moduleId: ModuleId,
    val current: Version,
    val versionKey: String,
    var candidate: Version
)
