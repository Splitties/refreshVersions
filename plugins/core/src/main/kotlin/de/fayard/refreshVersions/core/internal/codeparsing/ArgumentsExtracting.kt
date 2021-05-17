package de.fayard.refreshVersions.core.internal.codeparsing

import de.fayard.refreshVersions.core.internal.codeparsing.SourceCodeSection.*

internal class FunctionArgument(
    val parameterName: String?,
    val expressionWithCommentsIfAny: String
)

/**
 * That function expects syntactically correct code.
 * If there's a lack of opening and closing braces pairing,
 * or if the passed [functionCallText] lacks the parentheses,
 * it might fail in unexpected ways.
 */
internal fun extractArgumentsOfFunctionCall(
    programmingLanguage: ProgrammingLanguage,
    functionCallText: CharSequence
): List<FunctionArgument> = mutableListOf<FunctionArgument>().also { list ->
    val functionArgsText = functionCallText.toString().substringBeforeLast(')').substringAfter('(')
    val ranges = functionArgsText.findRanges(programmingLanguage)

    var parenthesesNestingLevel = 0
    var blockNestingLevel = 0

    val currentWord = StringBuilder()
    var previousWord = ""

    var currentArgumentName: String? = null
    var currentArgumentStartIndex = 0

    ranges.forEach { section ->
        val codeChunk: String = when (section.tag) {
            Comment -> return@forEach
            StringLiteral -> {
                previousWord = ""
                return@forEach
            }
            CodeChunk -> functionArgsText.substring(section.range)
        }
        codeChunk.forEachIndexed { index, c ->
            when {
                c.isLetterOrDigit() -> currentWord.append(c)
                currentWord.isNotEmpty() -> {
                    previousWord = currentWord.toString()
                    currentWord.clear()
                }
            }
            when (c) {
                '(' -> parenthesesNestingLevel++
                ')' -> parenthesesNestingLevel--
                '{' -> blockNestingLevel++
                '}' -> blockNestingLevel--
                '=' -> if (
                    programmingLanguage == ProgrammingLanguage.Kotlin &&
                    parenthesesNestingLevel == 0 && blockNestingLevel == 0
                ) {
                    val previousChar = codeChunk.elementAtOrNull(index - 1)
                    val nextChar = codeChunk.elementAtOrNull(index + 1)
                    val followsParameterName = when (previousChar) {
                        '>', '<', '=', '!' -> false
                        else -> when (nextChar) {
                            '=' -> false
                            else -> true
                        }
                    }
                    if (followsParameterName) {
                        currentArgumentName = previousWord
                        currentArgumentStartIndex = section.startIndex + index + 1
                    }
                }
                ':' -> if (
                    programmingLanguage == ProgrammingLanguage.Groovy &&
                    parenthesesNestingLevel == 0 && blockNestingLevel == 0
                ) {
                    currentArgumentName = previousWord
                    currentArgumentStartIndex = section.startIndex + index + 1
                }
                ',' -> if (parenthesesNestingLevel == 0 && blockNestingLevel == 0) {
                    list.add(
                        FunctionArgument(
                            parameterName = currentArgumentName,
                            expressionWithCommentsIfAny = functionArgsText.substring(
                                startIndex = currentArgumentStartIndex,
                                endIndex = section.startIndex + index
                            ).trim()
                        )
                    )
                    currentArgumentStartIndex = index + 1
                    currentArgumentName = null
                }
            }
            require(parenthesesNestingLevel >= 0)
            require(blockNestingLevel >= 0)
        }
    }
    require(parenthesesNestingLevel == 0)
    require(blockNestingLevel == 0)
    val lastExpressionWithCommentsIfAny = functionArgsText.substring(
        startIndex = currentArgumentStartIndex
    )
    if (
        lastExpressionWithCommentsIfAny.findRanges(
            programmingLanguage
        ).any { it.tag == CodeChunk } && lastExpressionWithCommentsIfAny.isNotBlank()
    ) {
        list.add(
            FunctionArgument(
                parameterName = currentArgumentName,
                expressionWithCommentsIfAny = lastExpressionWithCommentsIfAny.trim()
            )
        )
    }
}
