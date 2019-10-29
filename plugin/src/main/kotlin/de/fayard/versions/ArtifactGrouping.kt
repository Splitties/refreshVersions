package de.fayard.versions

import org.gradle.api.Incubating

/**
 * We assume each part of the "group" is dot separated (`.`), and each part of the name is dash separated (`-`).
 */
@Incubating
enum class ArtifactGroupNaming {
    GroupOnly,
    GroupLastPart,
    GroupFirstTwoParts,
    GroupFirstThreeParts,
    GroupAndNameFirstPart,
    GroupLastPartAndNameSecondPart,
    GroupFirstPartAndNameTwoFirstParts
}

@Incubating
class ArtifactGroupingRule(
    val artifactNamesStartingWith: String,
    val groupNaming: ArtifactGroupNaming
) {
    init {
        require(artifactNamesStartingWith.count { it == ':' } <= 1)
    }
}
