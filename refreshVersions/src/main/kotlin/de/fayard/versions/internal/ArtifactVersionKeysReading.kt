@file:Suppress("UNREACHABLE_CODE")

// Remove when impl complete

package de.fayard.versions.internal

import org.gradle.api.Incubating

@Incubating
abstract class ArtifactVersionKeyReader private constructor() {

    abstract fun readVersionKey(group: String, name: String): String?

    operator fun plus(other: ArtifactVersionKeyReader): ArtifactVersionKeyReader {
        val initial = this
        return object : ArtifactVersionKeyReader() {
            override fun readVersionKey(group: String, name: String): String? {
                return other.readVersionKey(group, name) ?: initial.readVersionKey(group, name)
            }
        }
    }

    companion object {

        fun fromRules(fileContent: String): ArtifactVersionKeyReader {
            val rules = parseArtifactVersionKeysRules(fileContent)
            return object : ArtifactVersionKeyReader() {
                override fun readVersionKey(group: String, name: String): String? {
                    return rules.firstOrNull { it.matches(group, name) }?.key(group, name)
                }
            }
        }
    }
}

internal fun parseArtifactVersionKeysRules(fileContent: String): List<ArtifactVersionKeyRule> {
    val minimized = fileContent
        .replace("//.*$".toRegex(), "") // Remove line comments
        .replace("\\s*\$".toRegex(), "") // Remove trailing whitespace
    //.replace("^.*$".toRegex(), "") // Remove empty lines
    val lines = minimized.lines()
    require(lines.size % 2 == 0) {
        "Every artifact version key rule is made of two lines, but an odd count of rules lines has been found."
    }
    return MutableList(lines.size / 2) { i ->
        ArtifactVersionKeyRule(
            artifactPattern = lines[i * 2],
            versionKeyPattern = lines[i * 2 + 1]
        )
    }.sortedDescending()
}
