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

internal fun Sequence<String>.parseRemovedDependencyNotationsHistory(currentRevision: Int): List<RemovedDependencyNotation> {
    val list = mutableListOf<RemovedDependencyNotation>()
    var dependencyNotation: String? = null
    val commentLines = mutableListOf<String>()
    var replacementMavenCoordinates: ModuleId.Maven? = null
    filter { line ->
        line.isNotEmpty()
    }.dropWhile { line ->
        line != "## Revision ${currentRevision + 1}"
    }.filter { line ->
        line.startsWith("## Revision ").not()
    }.forEach { line ->
        when {
            line.startsWith("~~") && line.endsWith("~~") -> {
                check(commentLines.isEmpty())
                check(dependencyNotation == null)
                dependencyNotation = line.removeSurrounding("~~")
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
