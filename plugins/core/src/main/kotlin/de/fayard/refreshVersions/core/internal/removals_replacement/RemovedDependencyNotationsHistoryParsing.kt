package de.fayard.refreshVersions.core.internal.removals_replacement

import de.fayard.refreshVersions.core.ModuleId
import java.io.InputStream

internal data class RemovedDependencyNotation(
    val dependencyNotation: String,
    val moduleId: ModuleId.Maven,
    val leadingCommentLines: List<String>,
    val replacementMavenCoordinates: ModuleId.Maven?
)

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
            return@forEach
        }
        if (revisionTracking <= (currentRevision ?: 0)) {
            return@forEach
        }
        when {
            line.startsWith("~~") && line.endsWith("~~") -> {
                check(commentLines.isEmpty())
                check(dependencyNotation == null)
                dependencyNotation = line.removeSurrounding("~~").also { check(it.isNotEmpty()) }
            }
            line.startsWith("//") -> {
                checkNotNull(dependencyNotation)
                commentLines.add(line)
            }
            line.startsWith("moved:[") -> {
                replacementMavenCoordinates = line.substringAfter("moved:")
                    .removeSurrounding("[", "]").let {
                        ModuleId.Maven(
                            group = it.substringBefore(".."),
                            name = it.substringAfter("..")
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
                                group = it.substringBefore(".."),
                                name = it.substringAfter("..")
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
        }
    }
    return list
}
