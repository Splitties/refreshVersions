package de.fayard.refreshVersions.core.internal

//region Maven coordinates checks

internal fun Char.isAllowedInMavenGroup(): Boolean = isLetterOrDigit() || this in "._-"
internal fun Char.isAllowedInMavenName(): Boolean = isLetterOrDigit() || this in "._-"
internal fun Char.isWordPart(): Boolean = isLetterOrDigit() || this == '_'

internal fun checkGroupAndName(group: String, name: String) {
    group.indexOfFirst { it.isAllowedInMavenGroup().not() }.let { indexOfFirstInvalidChar ->
        checkOrReport(indexOfFirstInvalidChar == -1) {
            val invalidChar = group[indexOfFirstInvalidChar]
            "The given artifact group is invalid. Only letters, digits and dots (.) are allowed, but at column " +
                "${indexOfFirstInvalidChar + 1}, the following character has been found: $invalidChar"
        }
    }
    name.indexOfFirst { it.isAllowedInMavenName().not() }.let { indexOfFirstInvalidChar ->
        checkOrReport(indexOfFirstInvalidChar == -1) {
            val invalidChar = group[indexOfFirstInvalidChar]
            "The given artifact name is invalid. Only letters, digits and dots (.) are allowed, but at column " +
                "${indexOfFirstInvalidChar + 1}, the following character has been found: $invalidChar"
        }
    }
}

//endregion

//region Pattern formats checks.

internal fun checkArtifactPatternIsValid(artifactPattern: String) {
    checkOrReport(artifactPattern.count { it == ':' } <= 1) {
        "The following artifact pattern is invalid: $artifactPattern.\n" +
            "At most one column character (':') is allowed."
    }
    var i = 0
    readLoop@ while (i <= artifactPattern.lastIndex) when (val c = artifactPattern[i]) {
        '?' -> {
            val wordPlaceholder = "???"
            val indexOfVariablePart = artifactPattern.indexOf(wordPlaceholder, startIndex = i)
            checkOrReport(i == indexOfVariablePart) {
                val found = artifactPattern.substring(startIndex = i, endIndex = i + wordPlaceholder.length)
                "Expected $wordPlaceholder but found $found"
            }
            checkOrReport(artifactPattern.indexOf("????", startIndex = i) == -1) {
                "The word placeholder is 3 question marks ($wordPlaceholder) but more have been found."
            }
            i += wordPlaceholder.length
            continue@readLoop
        }
        '(' -> {
            val optionalSuffixMarker = "(-*)"
            val indexOfOptionalSuffix = artifactPattern.indexOf(optionalSuffixMarker, startIndex = i)
            checkOrReport(i == indexOfOptionalSuffix) {
                val found = artifactPattern.substring(startIndex = i)
                "Expected $optionalSuffixMarker but found $found"
            }
            checkOrReport(i + optionalSuffixMarker.length == artifactPattern.length) {
                "The optional suffix $optionalSuffixMarker should be last in the artifact pattern."
            }
            return
        }
        '*' -> { // The optionalSuffixMarker is checked before: (-*)
            if (i == artifactPattern.lastIndex) return
            checkOrReport(artifactPattern.indexOf("*:", startIndex = i) == i) {
                "The following artifact pattern is invalid: $artifactPattern.\n" +
                    "The asterisk character (*) can only appear at the end of the group " +
                    "(before the column character (:)), at the end of the pattern, or in the optional " +
                    "suffix marker (`(-*)`)."
            }
            i += 2
        }
        '.' -> {
            checkOrReport(i != artifactPattern.lastIndex) {
                "The following artifact pattern is invalid: $artifactPattern.\n" +
                    "An artifact pattern cannot end with a dot."
            }
            val nextChar = artifactPattern[i + 1]
            val specialCharsAllowedNext = "?*"
            checkOrReport(nextChar.isLetterOrDigit() || nextChar in specialCharsAllowedNext) {
                "The following artifact pattern is invalid: $artifactPattern.\n" +
                    "Only letters, digits and the following characters are allowed after a dot: " +
                    specialCharsAllowedNext
            }
            i++
        }
        '-' -> { // The optionalSuffixMarker is checked before: (-*)
            checkOrReport(i != artifactPattern.lastIndex) {
                "The following artifact pattern is invalid: $artifactPattern.\n" +
                    "An artifact pattern cannot end with a dash (-)."
            }
            val nextChar = artifactPattern[i + 1]
            val specialCharsAllowedNext = "?*"
            checkOrReport(nextChar.isLetterOrDigit() || nextChar in specialCharsAllowedNext) {
                "The following artifact pattern is invalid: $artifactPattern.\n" +
                    "Only letters, digits and the following characters are allowed after a dot: " +
                    specialCharsAllowedNext
            }
            i++
        }
        ':' -> {
            checkOrReport(i != artifactPattern.lastIndex) {
                "The following artifact pattern is invalid: $artifactPattern.\n" +
                    "An artifact pattern cannot end with a column (:)."
            }
            val nextChar = artifactPattern[i + 1]
            val specialCharsAllowedNext = "?*"
            checkOrReport(nextChar.isLetterOrDigit() || nextChar in specialCharsAllowedNext) {
                "The following artifact pattern is invalid: $artifactPattern.\n" +
                    "Only letters, digits and the following characters are allowed after a dot: " +
                    specialCharsAllowedNext
            }
            i++
        }
        else -> {
            checkOrReport(c.isWordPart()) {
                val allowedNonAlphanumericChars = ".:?(-*)"
                "The following artifact pattern is invalid: $artifactPattern.\n" +
                    "Character at column ${i + 1} ($c) is not allowed. " +
                    "Only letters, digits and the following special characters are allowed: " +
                    allowedNonAlphanumericChars
            }
            i++
        }
    }
}

internal fun checkVersionKeyPatternIsValid(aliasRule: String) {
    val allowedChars = "^. "
    val indexOfFirstInvalidChar = aliasRule.indexOfFirst { char ->
        char !in allowedChars
    }
    checkOrReport(indexOfFirstInvalidChar == -1) {
        val invalidChar = aliasRule[indexOfFirstInvalidChar]
        "The following alias rule is invalid: $aliasRule\n" +
            "Character at column ${indexOfFirstInvalidChar + 1} ($invalidChar) is not allowed. " +
            "Only the following characters are allowed: $allowedChars"
    }
    checkOrReport(".." !in aliasRule) {
        "Contiguous dots (..) is reserved for full maven coordinates keys."
    }
    checkOrReport(" ." !in aliasRule) {
        "The following alias rule is invalid: $aliasRule\n" +
            "Dot cannot be preceded by a space. Put it immediately after the ^ character."
    }
}

private inline fun checkOrReport(condition: Boolean, errorMessage: () -> String) {
    if (condition.not()) throw IllegalArgumentException(errorMessage())
}

//endregion
