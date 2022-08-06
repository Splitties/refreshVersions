package de.fayard.refreshVersions.core.internal

data class PrefixRule(
    val versionName: String,
    val mavenGroup: String,
    val prefix: String? = null
) {
    fun toRawRules() = toArtifactVersionKeyRule().toString()

    internal fun toArtifactVersionKeyRule(): ArtifactVersionKeyRule {
        checkValidVersion(versionName)
        checkValidMavenGroup(mavenGroup)
        return ArtifactVersionKeyRule(getArtifactPattern(), getVersionKeyPattern())
    }

    internal companion object {
        fun checkValidVersion(versionName: String) {
            check(versionName == versionName.trim()) { "PrefixRule.versionName contains spaces: got '$versionName'"}
            check(versionName.startsWith("version").not()) { "PrefixRule.versionName should not start with version" }
            check(regexVersion.matches(versionName)) { "PrefixRule.versionName must match regex $regexVersion and not start with 'version.' but I got '$versionName'" }
        }
        private val regexVersion = "[a-z][a-z0-9.-]+[a-z0-9]".toRegex()

        fun checkValidMavenGroup(mavenGroup: String) {
            val isKo = mavenGroup.contains(":") ||
                mavenGroup.contains("..") ||
                mavenGroup.length < 5
            check(isKo.not()) { "PrefixRule.mavenGroup is invalid: got '$mavenGroup'"}
        }
    }

    private fun getArtifactPattern() = when {
        prefix == null -> "$mavenGroup:*"
        else -> "$mavenGroup:$prefix(-*)"
    }

    private fun getVersionKeyPattern(): String {
        val invalid = -1..-1
        val pattern = getArtifactPattern()
        val words = versionName.split(".", " ", "-")
        var start = 0
        val wordsToIndexes = words.associateWith { word ->
            val index = pattern.indexOf(word, start)
            if (index == -1) {
                invalid
            } else {
                start = index + word.length
                IntRange(start = index, endInclusive = index+word.length-1)
            }
        }
        val separatorMap = words.associate { word ->
            val index = pattern.indexOf(word) + word.length
            val patternChar = pattern.substringAfter(word).firstOrNull() ?: ' '
            val versionChar = versionName.substringAfter(word).firstOrNull() ?: ' '
            val separator = when {
                versionName.endsWith(word) -> ' '
                versionChar == '.' && patternChar != '.'  -> '.'
                else -> '^'
            }
            index to separator
        }
        val notFound = wordsToIndexes.filterValues { it == invalid }.keys
        check(notFound.isEmpty()) { "PrefixRule.versionName contains words not found in the pattern: $notFound for pattern='$pattern'"}
        val selected: List<IntRange> = wordsToIndexes.values.toList()

        val result = List(pattern.length) { index ->
            val isSelected = selected.any { range -> index in range }
            val isSeparator = index in separatorMap

            when {
                isSelected -> '^'
                isSeparator -> separatorMap.getValue(index)
                else -> ' '
            }
        }
        return result.joinToString("").trimEnd()
    }
}
