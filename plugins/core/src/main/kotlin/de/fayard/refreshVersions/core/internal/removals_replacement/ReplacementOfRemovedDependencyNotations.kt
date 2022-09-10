package de.fayard.refreshVersions.core.internal.removals_replacement

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.extensions.collections.forEachReversedWithIndex
import de.fayard.refreshVersions.core.extensions.ranges.contains
import de.fayard.refreshVersions.core.extensions.text.indexOfFirst
import de.fayard.refreshVersions.core.extensions.text.indexOfPrevious
import de.fayard.refreshVersions.core.extensions.text.substringUntilEndOfLine
import de.fayard.refreshVersions.core.internal.DependencyMapping
import de.fayard.refreshVersions.core.internal.TaggedRange
import de.fayard.refreshVersions.core.internal.associateShortestByMavenCoordinate
import de.fayard.refreshVersions.core.internal.codeparsing.*
import de.fayard.refreshVersions.core.internal.codeparsing.SourceCodeSection
import de.fayard.refreshVersions.core.internal.codeparsing.findFirstImportStatement
import de.fayard.refreshVersions.core.internal.codeparsing.gradle.extractGradleScriptSections
import de.fayard.refreshVersions.core.internal.codeparsing.rangeOfCode
import de.fayard.refreshVersions.core.internal.codeparsing.rangesOfCode
import java.io.File

internal fun replaceRemovedDependencyNotationReferencesIfNeeded(
    projectDir: File,
    dependencyMapping: List<DependencyMapping>,
    revisionOfLastRefreshVersionsRun: Int,
    info: RemovedDependencyNotationsReplacementInfo
) {

    val history = info.removalsListingResource.parseRemovedDependencyNotationsHistory(
        currentRevision = revisionOfLastRefreshVersionsRun
    )

    if (history.isEmpty()) return

    val shortestDependencyMapping: Map<ModuleId.Maven, String> by lazy {
        dependencyMapping.associateShortestByMavenCoordinate()
    }
    val remainingDependencyNotations: Set<String> by lazy {
        dependencyMapping.mapTo(mutableSetOf()) { it.constantName }
    }

    projectDir.walk().onEnter {
        it.name != "src" && it.name != "build"
    }.filter {
        it.isFile
    }.filter {
        it.name.endsWith("gradle.kts") || it.extension == "gradle"
    }.filter {
        it.name != "settings.gradle" && it.name != "settings.gradle.kts"
    }.forEach { gradleFile ->
        history.replaceRemovedDependencyNotationReferencesIfAny(
            remainingDependencyNotations = { remainingDependencyNotations },
            shortestDependencyMapping = { shortestDependencyMapping },
            gradleBuildFileContent = gradleFile.readText(),
            isKotlinDsl = gradleFile.extension == "kts"
        )?.let { newContent ->
            gradleFile.writeText(newContent)
        }
    }
}

internal fun List<RemovedDependencyNotation>.replaceRemovedDependencyNotationReferencesIfAny(
    remainingDependencyNotations: () -> Set<String>,
    shortestDependencyMapping: () -> Map<ModuleId.Maven, String>,
    gradleBuildFileContent: String,
    isKotlinDsl: Boolean
): String? {
    val ranges = gradleBuildFileContent.extractGradleScriptSections(isKotlinDsl = isKotlinDsl)

    val reverseOrderedTargets: List<Pair<RemovedDependencyNotation, IntRange>> = flatMap { removedDependencyNotation ->
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
    }.toMutableList().apply {
        val comparator = compareBy<Pair<RemovedDependencyNotation, IntRange>> { (_, range) ->
            range.last
        }.thenBy { (removedDependencyNotation, _) ->
            removedDependencyNotation.dependencyNotation.length
        }

        sortWith(comparator)
        forEachReversedWithIndex { i, (removedDependencyNotation, range) ->
            getOrNull(i + 1)?.let { (_, previousRange) ->
                // Removes overlapped ranges that need to be replaced.
                if (range in previousRange) {
                    removeAt(i)
                    return@forEachReversedWithIndex
                }
            }
            val previousChar = gradleBuildFileContent.getOrElse(range.first - 1) { ' ' }
            val subsequentChar = gradleBuildFileContent.getOrElse(range.last + 1) { ' ' }
            // Ensure there's "padding" around the match to avoid contains-like behavior.
            if (previousChar == '.' || previousChar.isLetterOrDigit() || subsequentChar.isLetterOrDigit()) {
                removeAt(i)
                return@forEachReversedWithIndex
            }
            if (subsequentChar == '.') {
                // Ensure we are not replacing a still existing dependency notation.
                // Handles the case where a removed one is contained in a remaining one.
                val line = gradleBuildFileContent.substringUntilEndOfLine(range.first)
                remainingDependencyNotations().forEach {
                    if (removedDependencyNotation.dependencyNotation in it) {
                        if (line.startsWith(it, ignoreCase = true)) {
                            removeAt(i)
                            return@forEachReversedWithIndex
                        }
                    }
                }
            }
        }
        reverse()
    }
    return buildString gradleBuildFileContent@{
        append(gradleBuildFileContent)
        var edited = false
        var shallImportDependencyNotationClass = false
        reverseOrderedTargets.forEach { (removedDependencyNotation, range) ->
            //TODO: What if multiple deprecated dependency notations are on one line? Test it, at least.
            val lineStartIndex = indexOfPrevious('\n', startIndex = range.first).let {
                if (it == -1) 0 else it + 1
            }
            val indexOfPreviousLineBreak = this@gradleBuildFileContent.indexOf(
                char = '\n',
                startIndex = range.last + 1
            )
            val endOfPreviousLine = this@gradleBuildFileContent.substring(
                range.last + 1,
                indexOfPreviousLineBreak + 1
            )
            val hasCallOnTheDependencyNotation = endOfPreviousLine.trimStart().firstOrNull().let {
                it == '.' || it == '('
            }
            removedDependencyNotation.replacementMavenCoordinates.forEachReversedWithIndex { index, moduleId ->
                val insertionIndex = indexOf('\n', startIndex = range.last).let {
                    if (it == -1) size else it + 1
                }
                val comment = buildString comment@{
                    val commentStart = "//"
                    append(commentStart)
                    if (index == 0) {
                        val prefix = "moved:"
                        val dpdcNotationOffsetFromLineStart = range.first - lineStartIndex
                        (dpdcNotationOffsetFromLineStart - (commentStart.length + prefix.length)).coerceAtLeast(0).let {
                            append(" ".repeat(it))
                        }
                        append(prefix)
                    } else {
                        val preDependencyNotationText = gradleBuildFileContent.substring(lineStartIndex, range.first)
                        append(preDependencyNotationText)
                    }

                    val dependencyNotation = moduleId.dependencyNotation(
                        mapping = shortestDependencyMapping(),
                        ensureWrappedInParsedDependencyNotation = hasCallOnTheDependencyNotation
                    )
                    if (dependencyNotation.startsWith(dpdcNotationParse)) {
                        shallImportDependencyNotationClass = true
                    }
                    append(dependencyNotation)
                    append(endOfPreviousLine)
                }
                insert(insertionIndex, comment)
            }
            run {
                val dependencyNotation = removedDependencyNotation.moduleId.dependencyNotation(
                    mapping = shortestDependencyMapping(),
                    ensureWrappedInParsedDependencyNotation = hasCallOnTheDependencyNotation
                )
                if (dependencyNotation.startsWith(dpdcNotationParse)) {
                    shallImportDependencyNotationClass = true
                }
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
                        appendLine(line)
                    }
                }
                insert(lineStartIndex, text)
            }
            edited = true
        }
        if (edited.not()) return null
        if (shallImportDependencyNotationClass) {
            // gradleBuildFileContent and ranges don't match where we will be inserting,
            // but it's fine because the code we edited should come after what we're looking for,
            // which means the indexes will match for that case, as we're dealing with build files
            // that shall compile.
            insertImportForDependencyNotationClassIfNeeded(
                gradleBuildFileContent, ranges
            )
        }
    }
}

private fun ModuleId.Maven.dependencyNotation(
    mapping: Map<ModuleId.Maven, String>,
    ensureWrappedInParsedDependencyNotation: Boolean
): String {
    return mapping[this] ?: when {
        ensureWrappedInParsedDependencyNotation -> "$dpdcNotationParse(\"$group:$name\")"
        else -> "\"$group:$name:_\""
    }
}

private const val dpdcNotationParse = "DependencyNotation.parse"

private const val dpdcNotationClassImport = "import de.fayard.refreshVersions.core.DependencyNotation"

private fun StringBuilder.insertImportForDependencyNotationClassIfNeeded(
    gradleBuildFileContent: String,
    ranges: List<TaggedRange<SourceCodeSection>>
) {
    gradleBuildFileContent.rangeOfCode(
        code = dpdcNotationClassImport,
        sectionsRanges = ranges
    )?.let { return }
    val otherImportIndex = gradleBuildFileContent.findFirstImportStatement(ranges)
    if (otherImportIndex != -1) {
        insert(otherImportIndex, "$dpdcNotationClassImport\n")
    } else {
        val insertionIndex = gradleBuildFileContent.indexOfFirstNonBlankCodeChunk(ranges)
        insert(insertionIndex, "$dpdcNotationClassImport\n\n")
    }
}
