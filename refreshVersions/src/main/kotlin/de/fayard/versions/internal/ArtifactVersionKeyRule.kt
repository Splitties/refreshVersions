package de.fayard.versions.internal

/**
 * The rules are case sensitive.
 */
internal class ArtifactVersionKeyRule(
    private val artifactPattern: String,
    private val versionKeyPattern: String
) : Comparable<ArtifactVersionKeyRule> {

    init {
        checkArtifactPatternIsValid(artifactPattern)
        checkVersionKeyPatternIsValid(versionKeyPattern)
        require(artifactPattern.length >= versionKeyPattern.length) {
            "Version key pattern cannot exceed the length of its artifact pattern!"
        }
    }

    fun matches(group: String, name: String): Boolean {
        checkGroupAndName(group, name)
        return artifactPatternMatcher.matches(group, name)
    }

    fun key(group: String, name: String): String {
        require(matches(group, name)) {
            "Cannot get the key as this rule doesn't match the given artifact maven coordinates.\n" +
                "artifactPattern: $artifactPattern\n" +
                "group: $group name: $name"
        }
        TODO()
    }

    override fun compareTo(other: ArtifactVersionKeyRule) = comparator.compare(this, other)

    private val artifactPatternMatcher = ArtifactPatternMatcher(textPattern = artifactPattern)

    private val versionKeySignificantCharsLength = versionKeyPattern.count { it != ' ' }

    companion object {
        private val comparator: Comparator<ArtifactVersionKeyRule> = compareBy<ArtifactVersionKeyRule> {
            it.versionKeySignificantCharsLength
        }.thenBy { it.artifactPattern }.thenBy { it.versionKeyPattern }
    }
}
