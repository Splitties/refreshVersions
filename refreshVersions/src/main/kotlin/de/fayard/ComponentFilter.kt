package de.fayard

import org.gradle.api.HasImplicitReceiver
import org.gradle.api.artifacts.ComponentSelection

@HasImplicitReceiver
interface ComponentFilter {
    fun reject(candidate: ComponentSelectionWithCurrent)
}

data class ComponentSelectionWithCurrent(
    val currentVersion: String,
    val selection: ComponentSelection
)
