package de.fayard.refreshVersions.core.internal.versions

internal data class VersionsPropertiesModel(
    val preHeaderContent: String,
    val generatedByVersion: String,
    val sections: List<Section>
) {
    companion object;

    sealed class Section {

        data class Comment(val lines: String) : Section()

        data class VersionEntry(
            val leadingCommentLines: List<String>,
            val metadataCommentLines: List<String>,
            val key: String,
            val currentVersion: String,
            val availableUpdates: String,
            val trailingCommentLines: List<String>
        ) : Section()
    }
}
