package de.fayard.refreshVersions.core.internal.removals_replacement

import de.fayard.refreshVersions.core.ModuleId

internal data class RemovedDependencyNotation(
    val dependencyNotation: String,
    val moduleId: ModuleId.Maven,
    val leadingCommentLines: List<String>,
    val replacementMavenCoordinates: ModuleId.Maven?
)
