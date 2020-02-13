@file:Suppress("UNREACHABLE_CODE")

// Remove when impl complete

package de.fayard.versions.internal

internal fun parseArtifactVersionKeysRules(fileContent: String): List<ArtifactVersionKeyRule> {
    val minimized = fileContent
        .replace("//.*$".toRegex(), "") // Remove line comments
        .replace("\\s*\$".toRegex(), "") // Remove trailing whitespace
    //.replace("^.*$".toRegex(), "") // Remove empty lines
    val lines = minimized.lines()
    require(lines.size % 2 == 0) {
        "Every artifact version key rule is made of two lines, but an odd count of rules lines has been found."
    }
    return List(lines.size / 2) { i ->
        ArtifactVersionKeyRule(
            artifactPattern = lines[i * 2],
            versionKeyPattern = lines[i * 2 + 1]
        )
    }
}

private fun checkArtifactVersionKeysRulesFiles(fileContent: String) {
    var currentIndex = 0
    val lastIndex = fileContent.lastIndex
    var blockCommentNestingLevel = 0
    while (currentIndex <= lastIndex) {
        TODO()
    }
}
