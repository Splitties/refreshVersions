package de.fayard.refreshVersions.core.internal.versions

import de.fayard.refreshVersions.core.extensions.sequences.uniqueBy
import de.fayard.refreshVersions.core.extensions.text.substringAfterLastLineStartingWith
import de.fayard.refreshVersions.core.extensions.text.substringBetween
import java.io.File

internal fun VersionsPropertiesModel.Companion.readFrom(
    versionsPropertiesFile: File
): VersionsPropertiesModel {
    val text = synchronized(versionsPropertiesFileLock) { versionsPropertiesFile.readText() }
    return readFromText(text)
}

internal fun VersionsPropertiesModel.Companion.readFromText(
    fileContent: String
): VersionsPropertiesModel = try {
    readFromTextInternal(
        fileContent = fileContent.replace("\r", "") // For Windows
    )
} catch (e: IllegalArgumentException) {
    throw IllegalStateException(e)
}

private fun VersionsPropertiesModel.Companion.readFromTextInternal(
    fileContent: String
): VersionsPropertiesModel {
    val preHeaderContent: String
    val generatedByVersion: String
    val sectionsText: String
    when {
        fileContent.startsWith(oldFileHeader) -> {
            preHeaderContent = ""
            generatedByVersion = "0.9.7" // Might be actually older, but it doesn't matter.
            sectionsText = fileContent.substringAfter(oldFileHeader)
        }
        fileContent.isBlank() -> {
            preHeaderContent = ""
            generatedByVersion = ""
            sectionsText = ""
        }
        else -> {
            preHeaderContent = fileContent.substringBefore(headerLinesPrefix)
            generatedByVersion = try {
                fileContent.substringBetween(generatedByLineStart, "\n").also {
                    check(it.isNotBlank()) { missingGeneratedByVersionErrorMessage() }
                }
            } catch (e: NoSuchElementException) {
                throw IllegalStateException(missingGeneratedByVersionErrorMessage())
            }
            sectionsText = fileContent.substringAfterLastLineStartingWith(headerLinesPrefix)
        }
    }

    val sections = mutableListOf<VersionsPropertiesModel.Section>()

    sectionsText.insertNewLinesIfNeeded().splitToSequence("\n\n").mapNotNull { sectionText ->
        readSection(sectionText)
    }.onEach {
        sections.add(it)
    }.filterIsInstance<VersionsPropertiesModel.Section.VersionEntry>().uniqueBy(
        onDuplicate = { key ->
            throw IllegalArgumentException("The version with key $key has been found twice!")
        }
    ) {
        it.key
    }.lastOrNull() // Force iteration of the whole sequence.

    return VersionsPropertiesModel(
        preHeaderContent = preHeaderContent,
        generatedByVersion = generatedByVersion,
        sections = sections
    )
}

internal fun String.insertNewLinesIfNeeded() : String {
    var result = StringBuffer()
    var keyExpected = true
    for (line in lines()) {
        val hasKey = line.substringBefore('#').contains('=')
        if (hasKey) {
            if (!keyExpected) {
                result.append('\n')
            }
            keyExpected = false
        } else if (line.isBlank()) {
            keyExpected = true
        }
        result.append(line)
        result.append('\n')
    }
    return result.toString().trim()
}

private fun readSection(sectionText: String): VersionsPropertiesModel.Section? {
    val lines = sectionText.lines().map { it.trim() }

    val versionLineIndex = lines.indexOfFirst {
        VersionsPropertiesModel.versionKeysPrefixes.any { prefix -> it.startsWith(prefix) }
    }.also {
        if (it == -1) {
            if (sectionText.isEmpty()) return null
            return VersionsPropertiesModel.Section.Comment(lines = sectionText)
        }
    }

    val versionLine = lines[versionLineIndex]

    fun String.isAvailableUpdateComment(): Boolean {
        return startsWith("##") && "${VersionsPropertiesModel.availableComment}=" in this
    }

    val remainingLines = lines.subList(
        fromIndex = (versionLineIndex + 1).coerceAtMost(lines.size),
        toIndex = lines.size
    )

    var availableUpdatesSectionPassed = false

    val (availableUpdatesComments, trailingComments) = remainingLines.partition { line ->
        line.isAvailableUpdateComment().also { isAvailableUpdateLine ->
            if (isAvailableUpdateLine) check(availableUpdatesSectionPassed.not()) {
                "Putting custom comments between available updates comments is not supported."
            }
            availableUpdatesSectionPassed = isAvailableUpdateLine.not()
        }
    }

    val versionKey = versionLine.substringBefore('=')

    return VersionsPropertiesModel.Section.VersionEntry(
        leadingCommentLines = lines.subList(fromIndex = 0, toIndex = versionLineIndex),
        key = versionKey,
        currentVersion = versionLine.substringAfter('=', missingDelimiterValue = "").ifEmpty {
            error("Didn't find the value of the version for the following key: $versionKey")
        },
        availableUpdates = availableUpdatesComments.map { it.substringAfter('=') },
        trailingCommentLines = trailingComments
    )
}

private val oldFileHeader = """
## suppress inspection "SpellCheckingInspection" for whole file
## suppress inspection "UnusedProperty" for whole file
##
## Dependencies and Plugin versions with their available updates
## Generated by ${'$'} ./gradlew refreshVersions
## Please, don't put extra comments in that file yet, keeping them is not supported yet.
""".trimMargin()

private fun missingGeneratedByVersionErrorMessage() = "Unable to find the version of " +
        "refreshVersions that generated the versions.properties file. " +
        "Please, revert the removal."
