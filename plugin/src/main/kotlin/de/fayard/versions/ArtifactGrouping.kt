package de.fayard.versions

import org.gradle.api.Incubating

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
    GroupFirstPartAndNameTwoFirstParts
}

internal class ArtifactGroupingRule(
    val artifactNamesStartingWith: String,
    val groupNaming: ArtifactGroupNaming
) {
    init {
        require(artifactNamesStartingWith.count { it == ':' } <= 1)
    }
}
