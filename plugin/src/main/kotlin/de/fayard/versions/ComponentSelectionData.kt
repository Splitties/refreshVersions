package de.fayard.versions

import org.gradle.api.Incubating
import org.gradle.api.artifacts.component.ModuleComponentIdentifier

@Incubating
data class ComponentSelectionData(
    val currentVersion: String,
    val candidate: ModuleComponentIdentifier
)
