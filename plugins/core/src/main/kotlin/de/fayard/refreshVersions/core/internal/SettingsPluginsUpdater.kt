package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.extensions.gradle.isRootProject
import de.fayard.refreshVersions.core.extensions.text.forEachIndexedSkippable
import de.fayard.refreshVersions.core.extensions.text.indexOfPrevious
import de.fayard.refreshVersions.core.internal.SettingsPluginsUpdater.PluginsBlockLocation.*
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Companion.availableComment
import org.gradle.api.Project
import java.io.File

internal object SettingsPluginsUpdater {

    fun updateGradleSettingsWithAvailablePluginsUpdates(
        rootProject: Project,
        settingsPluginsUpdates: List<PluginWithVersionCandidates>,
        buildSrcSettingsPluginsUpdates: List<PluginWithVersionCandidates>
    ) {
        require(rootProject.isRootProject)
        require(rootProject.isBuildSrc.not())

        val rootProjectSettingsFile = rootProject.file("settings.gradle.kts").let { kotlinDslSettings ->
            if (kotlinDslSettings.exists()) kotlinDslSettings else {
                rootProject.file("settings.gradle").also {
                    check(it.exists())
                }
            }
        }
        val buildSrcSettingsFile = rootProject.file("buildSrc/settings.gradle.kts").let { kotlinDslSettings ->
            if (kotlinDslSettings.exists()) kotlinDslSettings else {
                rootProject.file("buildSrc/settings.gradle").takeIf {
                    it.exists()
                }
            }
        }
        updateGradleSettingsWithAvailablePluginsUpdates(
            settingsFile = rootProjectSettingsFile,
            settingsPluginsUpdates = settingsPluginsUpdates
        )
        buildSrcSettingsFile?.let {
            updateGradleSettingsWithAvailablePluginsUpdates(
                settingsFile = it,
                settingsPluginsUpdates = buildSrcSettingsPluginsUpdates
            )
        }
    }

    private fun updateGradleSettingsWithAvailablePluginsUpdates(
        settingsFile: File,
        settingsPluginsUpdates: List<PluginWithVersionCandidates>
    ) {
        val newContent = updatedGradleSettingsFileContentWithAvailablePluginsUpdates(
            fileContent = settingsFile.readText(),
            isKotlinDsl = settingsFile.name.endsWith(".kts"),
            settingsPluginsUpdates = settingsPluginsUpdates
        )
        settingsFile.writeText(newContent)
    }

    internal fun updatedGradleSettingsFileContentWithAvailablePluginsUpdates(
        fileContent: String,
        isKotlinDsl: Boolean,
        settingsPluginsUpdates: List<PluginWithVersionCandidates>
    ): String {
        val fileContentWithoutOurComments = StringBuilder(fileContent.length).apply {
            append(fileContent)
            removeCommentsAddedByUs()
        }

        val ranges = fileContentWithoutOurComments.findRanges(isKotlinDsl = isKotlinDsl)

        fileContentWithoutOurComments.findPluginBlocksRanges(
            ranges = ranges
        ).asReversed().forEach { pluginBlockRange ->
            fileContentWithoutOurComments.insertAvailableVersionComments(
                pluginBlockRange = pluginBlockRange,
                isKotlinDsl = isKotlinDsl,
                settingsPluginsUpdates = settingsPluginsUpdates
            )
        }
        return fileContentWithoutOurComments.toString()
    }

    internal object PluginBlock

    internal fun CharSequence.findPluginBlocksRanges(
        ranges: List<TaggedRange<ScriptSection>>
    ): List<TaggedRange<PluginBlock>> = mutableListOf<TaggedRange<PluginBlock>>().also { list ->
        var blockNestingLevel = 0
        var isInPluginManagement = false
        var pluginsBlockLocation: PluginsBlockLocation? = null

        /** Right after the opening brace. */
        val currentBlocksStartIndexes = mutableListOf(0)


        ranges.filter { it.tag == ScriptSection.CodeChunk }.forEach {
            val codeChunk = substring(it.startIndex, it.endIndex)

            val currentWord = StringBuilder()
            var previousWord = ""

            fun addPluginBlockRange(closingBraceIndex: Int) {
                val range = TaggedRange(
                    tag = PluginBlock,
                    startIndex = currentBlocksStartIndexes.last(),
                    endIndex = it.startIndex + closingBraceIndex
                )
                list.add(range)
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
                    '{' -> {
                        if (blockNestingLevel == 0 && previousWord == "pluginManagement") {
                            isInPluginManagement = true
                        }
                        if (blockNestingLevel == 0 && previousWord == "plugins") {
                            pluginsBlockLocation = TopLevel
                        }
                        if (isInPluginManagement && blockNestingLevel == 1 && previousWord == "plugins") {
                            pluginsBlockLocation = PluginManagement
                        }
                        currentBlocksStartIndexes.add(it.startIndex + index + 1)
                        blockNestingLevel++
                    }
                    '}' -> {
                        if (isInPluginManagement && blockNestingLevel == 1) {
                            isInPluginManagement = false
                        }
                        when (pluginsBlockLocation) {
                            TopLevel -> if (blockNestingLevel == 1) {
                                addPluginBlockRange(closingBraceIndex = index)
                                pluginsBlockLocation = null
                            }
                            PluginManagement -> if (blockNestingLevel == 2) {
                                addPluginBlockRange(closingBraceIndex = index)
                                pluginsBlockLocation = null
                            }
                        }
                        currentBlocksStartIndexes.removeAt(currentBlocksStartIndexes.lastIndex)
                        blockNestingLevel--
                    }
                }
                if (list.size == 2) return@also
            }
        }
    }

    private enum class PluginsBlockLocation {
        TopLevel,
        PluginManagement
    }

    private fun StringBuilder.insertAvailableVersionComments(
        pluginBlockRange: TaggedRange<PluginBlock>,
        isKotlinDsl: Boolean,
        settingsPluginsUpdates: List<PluginWithVersionCandidates>
    ) {

        val pluginIdRegex = if (isKotlinDsl) PluginIdRegexes.kotlinDsl else PluginIdRegexes.groovyDsl

        val pluginsUpdatesMap = settingsPluginsUpdates.associateBy { it.pluginId }
        val text = this

        pluginIdRegex.findAll(
            input = this.substring(0, pluginBlockRange.endIndex),
            startIndex = pluginBlockRange.startIndex
        ).toList().asReversed().forEach { result ->
            val idGroup = result.groups[1]!!
            val currentVersionGroup = result.groups[2]!!
            val versionCallClosingParenthesisGroup = result.groups[3]!!
            val pluginId = idGroup.value
            val pluginWithVersionCandidates = pluginsUpdatesMap[pluginId] ?: return@forEach

            if (pluginWithVersionCandidates.versionsCandidates.isEmpty()) return@forEach

            val currentVersionStartIndex = currentVersionGroup.range.first
            val currentVersionEndIndex = currentVersionGroup.range.last

            val indexOfPreviousNewLine = indexOfPrevious(
                char = '\n',
                startIndex = currentVersionStartIndex
            )
            val indexOfNextNewLine = indexOf(
                char = '\n',
                startIndex = currentVersionEndIndex
            )

            val shouldAddClosingParenthesis = versionCallClosingParenthesisGroup.let {
                it.value.isNotEmpty() && it.range.first < indexOfNextNewLine
            }

            val offsetOfCurrentVersionFromLineStart = currentVersionStartIndex - indexOfPreviousNewLine

            // The number 8 below is the length of "//// ", plus ':',
            // plus the 2 string literal delimiters.

            val availableVersionsComments = buildString {
                val padding = offsetOfCurrentVersionFromLineStart - 8
                pluginWithVersionCandidates.versionsCandidates.forEach { versionCandidate ->
                    appendln()
                    append("//// ")
                    append(availableComment.padStart((padding).coerceAtLeast(0)))
                    append(':')
                    val stringLiteralDelimiter = text[currentVersionEndIndex + 1]
                    append(stringLiteralDelimiter)
                    append(versionCandidate.value)
                    append(stringLiteralDelimiter)
                    if (shouldAddClosingParenthesis) {
                        append(versionCallClosingParenthesisGroup.value)
                    }
                }
            }
            insert(indexOfNextNewLine, availableVersionsComments)

            val currentVersionPadding = (availableComment.length + 8 - offsetOfCurrentVersionFromLineStart)
            if (currentVersionPadding > 0) {
                insert(currentVersionStartIndex - 1, "".padStart(currentVersionPadding))
            }
        }
    }

    /** Removes comments previously added by refreshVersions. */
    internal fun StringBuilder.removeCommentsAddedByUs() {
        val startOfRefreshVersionsCommentLines = "\n////"
        var startIndex = 0
        while (true) {
            val indexOfComment = indexOf(startOfRefreshVersionsCommentLines, startIndex = startIndex)
            if (indexOfComment == -1) return
            startIndex = indexOfComment
            val indexOfEndOfLine = indexOf(
                "\n",
                startIndex = indexOfComment + startOfRefreshVersionsCommentLines.length
            ).takeIf { it >= 0 }
            val endIndex = indexOfEndOfLine ?: length
            val commentContentStartIndex = indexOfComment + startOfRefreshVersionsCommentLines.length
            val commentContent = substring(commentContentStartIndex, endIndex)
            if (availableComment !in commentContent) {
                startIndex = endIndex
                continue
            }
            replace(
                /* start = */ indexOfComment,
                /* end = */ endIndex,
                /* str = */""
            )
        }
    }

    internal fun CharSequence.findRanges(
        isKotlinDsl: Boolean
    ): List<TaggedRange<ScriptSection>> = mutableListOf<TaggedRange<ScriptSection>>().also {
        val text: CharSequence = this

        @Suppress("UnnecessaryVariable")
        val canBlockCommentsBeNested = isKotlinDsl
        val isGroovyDsl = isKotlinDsl.not()

        var isNextCharacterEscaped = false
        var currentRangeStartIndex = 0

        var stringLiteralState: StringLiteralState = StringLiteralState.None

        var isInEndOfLineComment = false
        var blockCommentDepth = 0

        fun addRange(type: ScriptSection, endIndex: Int) {
            val range = TaggedRange(
                tag = type,
                startIndex = currentRangeStartIndex,
                endIndex = endIndex
            )
            it.add(range)
            currentRangeStartIndex = endIndex
        }

        fun addCodeChunk(endIndex: Int) = addRange(ScriptSection.CodeChunk, endIndex)

        fun addCommentRange(endIndex: Int) = addRange(ScriptSection.Comment, endIndex)

        fun addStringLiteralRange(endIndex: Int) {
            addRange(ScriptSection.StringLiteral, endIndex)
            stringLiteralState = StringLiteralState.None
        }

        forEachIndexedSkippable { index, c ->
            if (isInEndOfLineComment) check(blockCommentDepth == 0)
            if (blockCommentDepth > 0) check(isInEndOfLineComment.not())
            if (isNextCharacterEscaped) {
                isNextCharacterEscaped = false
                return@forEachIndexedSkippable
            }
            if (c == '\\') {
                isNextCharacterEscaped = true
                return@forEachIndexedSkippable
            }
            when {
                stringLiteralState != StringLiteralState.None -> when (stringLiteralState) {
                    StringLiteralState.Ordinary.GroovySingleQuotes -> if (c == '\'') {
                        addStringLiteralRange(endIndex = index + 1)
                    }
                    StringLiteralState.Ordinary.DoubleQuotes -> if (c == '"') {
                        addStringLiteralRange(endIndex = index + 1)
                    }
                    StringLiteralState.Raw -> if (text.startsWith(tripleQuotes, startIndex = index)) {
                        skipIteration(offset = 2)
                        addStringLiteralRange(endIndex = index + 3)
                    }
                    else -> error("sorry dollar slashy. BTW, how did you even get there??")
                }
                isInEndOfLineComment -> if (c == '\n') {
                    addCommentRange(endIndex = index + 1)
                    isInEndOfLineComment = false
                }
                blockCommentDepth > 0 -> when {
                    canBlockCommentsBeNested && text.startsWith("/*", startIndex = index) -> {
                        blockCommentDepth++
                        skipIteration(offset = 1)
                    }
                    text.startsWith("*/", startIndex = index) -> {
                        blockCommentDepth--
                        skipIteration(offset = 1)
                        if (blockCommentDepth == 0) {
                            addCommentRange(endIndex = index + 2)
                        }
                    }
                }
                else -> {
                    when {
                        text.startsWith("//", startIndex = index) -> {
                            isInEndOfLineComment = true
                            skipIteration(offset = 1)
                        }
                        text.startsWith("/*", startIndex = index) -> {
                            blockCommentDepth = 1
                            skipIteration(offset = 1)
                        }
                        text.startsWith(tripleQuotes, startIndex = index) -> {
                            stringLiteralState = StringLiteralState.Raw
                            skipIteration(offset = 2)
                        }
                        text.startsWith(singleQuote, startIndex = index) -> {
                            stringLiteralState = StringLiteralState.Ordinary.DoubleQuotes
                        }
                        isGroovyDsl && text.startsWith("'", startIndex = index) -> {
                            stringLiteralState = StringLiteralState.Ordinary.GroovySingleQuotes
                        }
                        else -> return@forEachIndexedSkippable
                    }
                    val codeChunkWouldBeEmpty = currentRangeStartIndex == index
                    if (codeChunkWouldBeEmpty.not()) {
                        addCodeChunk(endIndex = index)
                    }
                    currentRangeStartIndex = index
                }
            }
        }
        if (blockCommentDepth > 0 || isInEndOfLineComment) {
            addCommentRange(endIndex = length)
        } else {
            addCodeChunk(endIndex = length)
        }
    }

    private const val tripleQuotes = "\"\"\""
    private const val singleQuote = "\""

    internal enum class ScriptSection {
        Comment,
        StringLiteral,

        /** The code chunk will be cut-off on any string literal or comment start. */
        CodeChunk
    }

    /**
     * @property startIndex inclusive
     * @property endIndex exclusive
     */
    internal class TaggedRange<out T>(
        val tag: T,
        val startIndex: Int,
        val endIndex: Int
    )

    private object PluginIdRegexes {
        private const val kotlinStringLiteralPattern = """\"([0-9a-zA-Z\-\_.]*)\""""
        private const val groovyStringLiteralPattern = """[\"']([0-9a-zA-Z\-\_.]*)[\"']"""
        val kotlinDsl =
            """id\s*\(\s*$kotlinStringLiteralPattern\s*\)\s*\.?\s*version\s*\(?\s*$kotlinStringLiteralPattern\s*(\)?)""".toRegex()
        val groovyDsl =
            """id\s*\(?\s*$groovyStringLiteralPattern\s*\)?\s*\.?\s*version\s*\(?\s*$groovyStringLiteralPattern\s*(\)?)""".toRegex()
    }

    private sealed class StringLiteralState {
        object None : StringLiteralState()
        sealed class Ordinary : StringLiteralState() {
            object GroovySingleQuotes : Ordinary()
            object DoubleQuotes : Ordinary()

            @Deprecated("Are we sure we want to deal with that?", level = DeprecationLevel.ERROR)
            object GroovyDollarSlashy : Ordinary()
        }

        object Raw : StringLiteralState()
    }
}
