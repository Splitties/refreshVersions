package de.fayard.refreshVersions.core.internal.removals_replacement

import de.fayard.refreshVersions.core.ModuleId

internal data class RemovedDependencyNotation(
    val dependencyNotation: String,
    val moduleId: ModuleId.Maven,
    val leadingCommentLines: List<String>,
    val replacementMavenCoordinates: List<ModuleId.Maven>
) {
    constructor(
        dependencyNotation: String,
        moduleId: ModuleId.Maven,
        leadingCommentLines: List<String>,
        replacementMavenCoordinates: ModuleId.Maven
    ) : this(
        dependencyNotation = dependencyNotation,
        moduleId = moduleId,
        leadingCommentLines = leadingCommentLines,
        replacementMavenCoordinates = listOf(replacementMavenCoordinates)
    )
}
