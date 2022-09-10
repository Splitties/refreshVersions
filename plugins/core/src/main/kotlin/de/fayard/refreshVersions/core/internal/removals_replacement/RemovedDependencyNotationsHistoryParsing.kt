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
    val replacementMavenCoordinates = mutableListOf<ModuleId.Maven>()
    var revisionTracking = 0
    forEach { line ->
        if (line.isEmpty()) return@forEach
        val isRevisionHeader = line.startsWith("## Revision ")
        if (isRevisionHeader) {
            val value = line.substringAfter("## Revision ").substringBefore(' ')
            require(value.toIntOrNull() == ++revisionTracking) {
                "Expected to encounter heading for revision $revisionTracking but found $value instead."
            }
            check(dependencyNotation == null)
            check(commentLines.isEmpty())
            check(replacementMavenCoordinates.isEmpty())
            return@forEach
        }
        if (revisionTracking <= (currentRevision ?: 0)) {
            return@forEach
        }
        when {
            line.startsWith("~~") && line.endsWith("~~") -> {
                check(dependencyNotation == null)
                check(commentLines.isEmpty())
                check(replacementMavenCoordinates.isEmpty())
                dependencyNotation = line.removeSurrounding("~~").also { check(it.isNotEmpty()) }
            }
            line.startsWith("//") -> {
                checkNotNull(dependencyNotation)
                check(replacementMavenCoordinates.isEmpty())
                commentLines.add(line)
            }
            line.startsWith("moved:[") -> {
                checkNotNull(dependencyNotation)
                check(replacementMavenCoordinates.isEmpty()) {
                    "Use `extra:` in place of moved when there are multiple replacing artifacts."
                }
                replacementMavenCoordinates += parseReplacementLine(line, "moved:")
            }
            line.startsWith("extra:[") -> {
                checkNotNull(dependencyNotation)
                check(replacementMavenCoordinates.isNotEmpty()) {
                    "`extra:` is meant to be used after `moved:` for extra replacement dependency notations."
                }
                val coordinates = parseReplacementLine(line, "extra:")
                check(coordinates !in replacementMavenCoordinates)
                replacementMavenCoordinates += coordinates
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
                    replacementMavenCoordinates = replacementMavenCoordinates.toList()
                )
                replacementMavenCoordinates.clear()
                dependencyNotation = null
                commentLines.clear()
                list.add(newElement)
            }
            line.startsWith("## [WIP]") -> {
                check(dependencyNotation == null)
                check(commentLines.isEmpty())
                check(replacementMavenCoordinates.isEmpty())
                return list
            }
            line.startsWith("**") -> throw IllegalArgumentException()
            else -> throw IllegalArgumentException()
        }
    }
    return list
}

private fun parseReplacementLine(
    line: String,
    prefix: String
): ModuleId.Maven = line.substringAfter(prefix).removeSurrounding("[", "]").let {
    require(it.first() != '<' && it.last() != '>' && it.none { c -> c.isWhitespace() }) {
        "Expected colon separated group and name within the brackets, " +
            "but found extra surrounding or whitespace in the moved clause."
    }
    ModuleId.Maven(
        group = it.substringBefore(':'),
        name = it.substringAfter(':')
    )
}
