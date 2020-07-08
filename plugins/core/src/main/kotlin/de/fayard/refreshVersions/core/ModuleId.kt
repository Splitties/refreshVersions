package de.fayard.refreshVersions.core

import org.gradle.api.Incubating

@Incubating
data class ModuleId(
    val group: String?,
    val name: String
)
