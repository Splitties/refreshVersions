package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId

@InternalRefreshVersionsApi
abstract class ArtifactVersionKeyReader private constructor(
    val getRemovedDependenciesVersionsKeys: () -> Map<ModuleId.Maven, String>
) {

    abstract fun readVersionKey(group: String, name: String): String?

    companion object {

        fun fromRules(
            filesContent: List<String>,
            getRemovedDependenciesVersionsKeys: () -> Map<ModuleId.Maven, String> = { emptyMap() }
        ): ArtifactVersionKeyReader {
            val rules = filesContent.flatMap { parseArtifactVersionKeysRules(it) }.sortedDescending()
            return object : ArtifactVersionKeyReader(getRemovedDependenciesVersionsKeys) {
                override fun readVersionKey(group: String, name: String): String? {
                    return rules.firstOrNull { it.matches(group, name) }?.key(group, name)
                }
            }
        }
    }
}

internal fun parseArtifactVersionKeysRules(fileContent: String): List<ArtifactVersionKeyRule> {
    val lines = fileContent.lineSequence()
        .map {
            val indexOfLineComment = it.indexOf("//")
            if (indexOfLineComment == -1) it else it.substring(startIndex = 0, endIndex = indexOfLineComment)
        }
        .filter { it.isNotBlank() }
        .map { it.trimEnd() }
        .toList()
    require(lines.size % 2 == 0) {
        "Every artifact version key rule is made of two lines, but an odd count of rules lines has been found."
    }
    return MutableList(lines.size / 2) { i ->
        ArtifactVersionKeyRule(
            artifactPattern = lines[i * 2],
            versionKeyPattern = lines[i * 2 + 1]
        )
    }
}
