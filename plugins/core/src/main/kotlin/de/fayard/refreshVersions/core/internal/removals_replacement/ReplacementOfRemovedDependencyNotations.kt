package de.fayard.refreshVersions.core.internal.removals_replacement

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.extensions.text.indexOfFirst
import de.fayard.refreshVersions.core.extensions.text.indexOfPrevious
import de.fayard.refreshVersions.core.internal.codeparsing.gradle.extractGradleScriptSections
import de.fayard.refreshVersions.core.internal.codeparsing.rangesOfCode

internal fun List<RemovedDependencyNotation>.replaceRemovedDependencyNotationReferencesIfAny(
    gradleBuildFileContent: String,
    isKotlinDsl: Boolean
): String? {
    val ranges = gradleBuildFileContent.extractGradleScriptSections(isKotlinDsl = isKotlinDsl)
    val mapping: Map<ModuleId.Maven, String> =
        emptyMap() //TODO: Take the first/shortest matching dpdc notation from the mapping

    val reverseOrderedTargets = flatMap { removedDependencyNotation ->
        gradleBuildFileContent.rangesOfCode(
            code = removedDependencyNotation.dependencyNotation,
            ignoreCase = true,
            sectionsRanges = ranges
        ).let { ranges ->
            if (ranges.isEmpty()) emptyList<Nothing>()
            else ranges.asReversed().map { removedDependencyNotation to it }
        }
    }.ifEmpty {
        return null
    }.sortedByDescending { (_, range) -> range.last }
    return buildString gradleBuildFileContent@{
        append(gradleBuildFileContent)
        var edited = false
        reverseOrderedTargets.forEach { (removedDependencyNotation, range) ->
            run {
                // Ensure there's "padding" around the match to avoid contains-like behavior.
                val previousChar = getOrElse(range.first - 1) { ' ' }
                val subsequentChar = getOrElse(range.last + 1) { ' ' }
                if (previousChar.isLetterOrDigit() || previousChar == '.') return@forEach
                if (subsequentChar.isLetterOrDigit() || previousChar == '.') return@forEach
            }
            //TODO: What if multiple deprecated dependency notations are on one line? Test it, at least.
            val lineStartIndex = indexOfPrevious('\n', startIndex = range.first).let {
                if (it == -1) 0 else it + 1
            }
            removedDependencyNotation.replacementMavenCoordinates?.let { moduleId ->
                val insertionIndex = indexOf('\n', startIndex = range.last).let {
                    if (it == -1) size else it + 1
                }
                val comment = buildString comment@{
                    val commentStart = "//"
                    val prefix = "moved:"
                    append(commentStart)
                    val dpdcNotationOffsetFromLineStart = range.first - lineStartIndex
                    (dpdcNotationOffsetFromLineStart - (commentStart.length + prefix.length)).coerceAtLeast(0).let {
                        append(" ".repeat(it))
                    }
                    append(prefix)
                    append(moduleId.dependencyNotation(mapping))
                    val indexOfPreviousLineBreak = this@gradleBuildFileContent.indexOf(
                        char = '\n',
                        startIndex = range.last + 1
                    )
                    val endOfPreviousLine = this@gradleBuildFileContent.substring(
                        range.last + 1,
                        indexOfPreviousLineBreak + 1
                    )
                    append(endOfPreviousLine)
                }
                insert(insertionIndex, comment)
            }
            run {
                val dependencyNotation = removedDependencyNotation.moduleId.dependencyNotation(mapping)
                replace(range.first, range.last + 1, dependencyNotation)
            }
            removedDependencyNotation.leadingCommentLines.takeUnless { it.isEmpty() }?.let { commentLines ->
                val indent = run {
                    val codeStart = indexOfFirst(startIndex = lineStartIndex) { c ->
                        c.isWhitespace().not()
                    }
                    " ".repeat(codeStart - lineStartIndex)
                }
                val text = buildString {
                    commentLines.forEach { line ->
                        append(indent)
                        appendln(line)
                    }
                }
                insert(lineStartIndex, text)
            }
            edited = true
        }
        if (edited.not()) return null
    }
}

private fun ModuleId.Maven.dependencyNotation(
    mapping: Map<ModuleId.Maven, String>
): String {
    return mapping[this] ?: "\"$group:$name:_\""
}
