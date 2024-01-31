package de.fayard.refreshVersions.core.internal.versions

private interface VersionsPropertiesModelH {
    val preHeaderContent: String
    val generatedByVersion: String
    val dependencyNotationRemovalsRevision: Int?
    val sections: List<Section>

    sealed interface Section {
        interface Comment : Section {
            val lines: String
        }

        interface VersionEntry : Section {
            val leadingCommentLines: List<String>
            val key: String
            val currentVersion: String
            val availableUpdates: List<String>
            val trailingCommentLines: List<String>

            /** Filtered view of [leadingCommentLines]. */
            val metadataLines: List<String>
        }
    }

    companion object
}
