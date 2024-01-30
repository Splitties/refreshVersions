package de.fayard.refreshVersions.core.internal

/**
 * The rules are case-sensitive.
 */
@InternalRefreshVersionsApi
abstract class ArtifactVersionKeyRule protected constructor(
    val artifactPattern: String,
    val versionKeyPattern: String
) : Comparable<ArtifactVersionKeyRule> {

    init {
        checkArtifactPatternIsValid(artifactPattern)
        checkVersionKeyPatternIsValid(versionKeyPattern)
        require(artifactPattern.length >= versionKeyPattern.length) {
            "Version key pattern cannot exceed the length of its artifact pattern!"
        }
    }

    abstract fun matches(group: String, name: String): Boolean

    abstract fun key(group: String, name: String): String

    fun onlyExactMatches(): Boolean = artifactPattern.none { it == '*' }

    final override fun compareTo(other: ArtifactVersionKeyRule) = comparator.compare(this, other)

    private val versionKeySignificantCharsLength = artifactPattern.count {
        it !in " ()*"
    }

    fun text(): String = """
        $artifactPattern
        $versionKeyPattern
    """.trimIndent()

    override fun toString(): String = text()

    companion object {

        operator fun invoke(
            artifactPattern: String,
            versionKeyPattern: String
        ): ArtifactVersionKeyRule = ArtifactVersionKeyRuleRegexImpl(artifactPattern, versionKeyPattern)

        private val comparator: Comparator<ArtifactVersionKeyRule> = compareBy<ArtifactVersionKeyRule> {
            it.versionKeySignificantCharsLength
        }.thenBy { it.artifactPattern }.thenBy { it.versionKeyPattern }
    }
}

private class ArtifactVersionKeyRuleRegexImpl(
    artifactPattern: String,
    versionKeyPattern: String
) : ArtifactVersionKeyRule(artifactPattern, versionKeyPattern) {

    override fun matches(group: String, name: String): Boolean {
        checkGroupAndName(group, name)
        val artifactIdentifier = "$group:$name"
        return artifactRegex.matches(artifactIdentifier)
    }

    override fun key(group: String, name: String): String {
        require(matches(group, name)) {
            "Cannot get the key as this rule doesn't match the given artifact maven coordinates.\n" +
                "artifactPattern: $artifactPattern\n" +
                "group: $group name: $name"
        }
        val artifactIdentifier = "$group:$name"
        val matchResult = artifactRegex.matchEntire(artifactIdentifier)!!
        val dotGroups = matchResult.groups.dotGroups()
        return buildString {
            matchResult.groups.forEachIndexed { index, group ->
                if (index == 0) return@forEachIndexed // First group is the full match, which we don't need.
                if (group in dotGroups) append('.')
                else append(group?.value ?: "")
            }
        }
    }

    override fun toString(): String = buildString {
        appendLine("Rule:")
        appendLine(super.toString())
        appendLine("Regex pattern:")
        append(artifactRegex.pattern)
    }

    private val artifactRegex: Regex = createCapturingRegex(artifactPattern, versionKeyPattern)

}

private fun MatchGroupCollection.dotGroups(): List<MatchGroup> {
    var dotIndex = 0
    return mutableListOf<MatchGroup>().also { list ->
        try {
            do {
                val group = this["dot$dotIndex"]
                group?.let { list.add(it) }
                dotIndex++
            } while (group != null)
        } catch (noGroupWithName: IllegalArgumentException) {
            // iteration is done, ignore and let the function return.
        }
    }
}

private const val dotSurrogate = '='
private const val openingParenthesisSurrogate = '{'
private const val closingParenthesisSurrogate = '}'

private val orderedReplacementsForRegex = listOf(
    dotSurrogate.toString() to "\\.",
    "???" to "[a-zA-Z0-9_]+?",
    "*" to "[a-zA-Z0-9_\\-\\.]+?",
    openingParenthesisSurrogate.toString() to "(",
    closingParenthesisSurrogate.toString() to ")"
)

private fun createCapturingRegex(
    artifactPattern: String,
    versionKeyPattern: String
): Regex {
    @Suppress("name_shadowing")
    val artifactPattern = artifactPattern // Replace some pattern parts to prevent clashes when replacing.
        .replace('.', dotSurrogate) // Avoid clash with dot used in our regexes.
    val paddedVersionPattern = versionKeyPattern.padEnd(length = artifactPattern.length)
    val regexPatternBuilder = StringBuilder() //TODO: Reuse it while avoiding concurrency issues without perfs drop.
    var isCapturing = false
    var dotsCount = 0
    for (i in paddedVersionPattern.lastIndex downTo 0) {
        val versionPatternChar = paddedVersionPattern[i]
        if (isCapturing.not()) {
            if (versionPatternChar == '^') {
                regexPatternBuilder.insert(0, closingParenthesisSurrogate)
                isCapturing = true
            }
        } else if (versionPatternChar != '^') {
            regexPatternBuilder.insert(0, openingParenthesisSurrogate)
            isCapturing = false
        }
        when (versionPatternChar) {
            '.' -> {
                regexPatternBuilder.insert(0, "(?<dot$dotsCount>${artifactPattern[i]})")
                dotsCount++
            }
            else -> when (val artifactPatternChar = artifactPattern[i]) {
                ')' -> {
                    regexPatternBuilder.insert(0, '?')
                    regexPatternBuilder.insert(0, closingParenthesisSurrogate)
                }
                '(' -> {
                    val isOptionalDashedSuffix = regexPatternBuilder.let { it[0] == '*' && it[1] == '-' }
                    if (isOptionalDashedSuffix) {
                        regexPatternBuilder.setRange(0, 2, "-[a-zA-Z0-9_\\-\\.]+?".reversed())
                    }
                    regexPatternBuilder.insert(0, ':')
                    regexPatternBuilder.insert(0, '?')
                    regexPatternBuilder.insert(0, openingParenthesisSurrogate)
                }
                else -> regexPatternBuilder.insert(0, artifactPatternChar)
            }
        }
    }
    if (isCapturing) {
        regexPatternBuilder.insert(0, openingParenthesisSurrogate)
        isCapturing = false
    }
    val replaceIndexes = mutableListOf<Int>() //TODO: Reuse it while avoiding concurrency issues without prefs drop.
    orderedReplacementsForRegex.forEach { (patternPart, regexPart) ->
        replaceIndexes.clear()
        // Get all indexes first, and replace after to avoid infinite replacements with dot still present after.
        var lastFoundIndex = -1
        while (true) {
            lastFoundIndex = regexPatternBuilder.indexOf(patternPart, startIndex = lastFoundIndex + 1)
            if (lastFoundIndex == -1) break else replaceIndexes.add(lastFoundIndex)
        }
        replaceIndexes.reverse() // Avoids shifting that would cause overlapping of replacements.
        replaceIndexes.forEach { index ->
            regexPatternBuilder.replace(index, index + patternPart.length, regexPart)
        }
    }
    regexPatternBuilder.append('$')
    return Regex(regexPatternBuilder.toString())
}
