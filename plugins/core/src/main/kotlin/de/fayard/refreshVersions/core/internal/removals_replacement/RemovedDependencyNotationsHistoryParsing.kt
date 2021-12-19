package de.fayard.refreshVersions.core.internal.removals_replacement

import de.fayard.refreshVersions.core.ModuleId
import java.io.InputStream

internal fun InputStream.parseRemovedDependencyNotationsHistory(currentRevision: Int): List<RemovedDependencyNotation> {
    return bufferedReader().lineSequence().parseRemovedDependencyNotationsHistory(currentRevision)
}

internal fun Sequence<String>.parseRemovedDependencyNotationsHistory(
    currentRevision: Int?
): List<RemovedDependencyNotation> {
    if (currentRevision != null) {
        require(currentRevision >= 0) { "currentRevision must be positive or null" }
    }
    val list = mutableListOf<RemovedDependencyNotation>()
    var dependencyNotation: String? = null
    val commentLines = mutableListOf<String>()
    var replacementMavenCoordinates: ModuleId.Maven? = null
    var revisionTracking = 0
    forEach { line ->
        if (line.isEmpty()) return@forEach
        val isRevisionHeader = line.startsWith("## Revision ")
        if (isRevisionHeader) {
            val value = line.substringAfter("## Revision ")
            require(value.toIntOrNull() == ++revisionTracking) {
                "Expected to encounter heading for revision $revisionTracking but found $value instead."
            }
            check(dependencyNotation == null)
            check(commentLines.isEmpty())
            check(replacementMavenCoordinates == null)
            return@forEach
        }
        if (revisionTracking <= (currentRevision ?: 0)) {
            return@forEach
        }
        when {
            line.startsWith("~~") && line.endsWith("~~") -> {
                check(dependencyNotation == null)
                check(commentLines.isEmpty())
                check(replacementMavenCoordinates == null)
                dependencyNotation = line.removeSurrounding("~~").also { check(it.isNotEmpty()) }
            }
            line.startsWith("//") -> {
                checkNotNull(dependencyNotation)
                check(replacementMavenCoordinates == null)
                commentLines.add(line)
            }
            line.startsWith("moved:[") -> {
                checkNotNull(dependencyNotation)
                replacementMavenCoordinates = line.substringAfter("moved:")
                    .removeSurrounding("[", "]").let {
                        require(it.first() != '<' && it.last() != '>' && it.none { c -> c.isWhitespace() }) {
                            "Expected comma separated group and name within the brackets, " +
                                "but found extra surrounding or whitespace in the moved clause."
                        }
                        ModuleId.Maven(
                            group = it.substringBefore(':'),
                            name = it.substringAfter(':')
                        )
                    }
            }
            line.startsWith("id:[") -> {
                checkNotNull(dependencyNotation)
                val newElement = RemovedDependencyNotation(
                    dependencyNotation = dependencyNotation!!,
                    moduleId = line.substringAfter("id:")
                        .removeSurrounding("[", "]").let {
                            ModuleId.Maven(
                                group = it.substringBefore(':'),
                                name = it.substringAfter(':')
                            )
                        },
                    leadingCommentLines = commentLines.toList(),
                    replacementMavenCoordinates = replacementMavenCoordinates
                )
                replacementMavenCoordinates = null
                dependencyNotation = null
                commentLines.clear()
                list.add(newElement)
            }
            line == "## [WIP]" -> {
                check(dependencyNotation == null)
                check(commentLines.isEmpty())
                check(replacementMavenCoordinates == null)
                return list
            }
            line.startsWith("**") -> throw IllegalArgumentException()
            else -> throw IllegalArgumentException()
        }
    }
    return list
}
