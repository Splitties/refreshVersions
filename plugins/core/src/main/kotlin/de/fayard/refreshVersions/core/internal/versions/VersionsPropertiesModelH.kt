package de.fayard.refreshVersions.core.internal.versions

internal expect class VersionsPropertiesModel {
    val preHeaderContent: String
    val generatedByVersion: String
    val sections: List<Section>

    sealed class Section {
        class Comment : Section {
            val lines: String
        }

        class VersionEntry : Section {
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
