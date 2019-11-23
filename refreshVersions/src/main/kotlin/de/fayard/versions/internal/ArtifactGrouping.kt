package de.fayard.versions.internal

/**
 * We assume each part of the "group" is dot separated (`.`), and each part of the name is dash separated (`-`).
 */
internal enum class ArtifactGroupNaming {
    GroupOnly,
    GroupLastPart,
    GroupFirstTwoParts,
    GroupFirstThreeParts,
    GroupAndNameFirstPart,
    GroupLastPartAndNameSecondPart,
    GroupFirstPartAndName
}

internal class ArtifactGroupingRule(
    val artifactNamesStartingWith: String,
    val groupNaming: ArtifactGroupNaming
) {
    init {
        require(artifactNamesStartingWith.count { it == ':' } <= 1)
    }
}
