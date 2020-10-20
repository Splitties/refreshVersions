package de.fayard.refreshVersions.core.internal.versions

import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Companion.availableComment
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section.Comment
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section.VersionEntry

internal fun VersionsPropertiesModel.toText(): String = buildString {
    append(preHeaderContent)
    if (preHeaderContent.isNotEmpty()) appendln()
    appendln(VersionsPropertiesModel.versionsPropertiesHeader(version = generatedByVersion))
    appendln()
    val sb = StringBuilder()
    sections.joinTo(buffer = this, separator = "\n") { it.toText(sb) }
}

private fun VersionsPropertiesModel.Section.toText(
    builder: StringBuilder
): CharSequence = when (this) {
    is Comment -> lines
    is VersionEntry -> builder.apply {
        clear()
        leadingCommentLines.forEach { appendln(it) }

        val paddedKey = key.padStart(availableComment.length + 2)
        val currentVersionLine = "$paddedKey=$currentVersion"
        appendln(currentVersionLine)
        availableUpdates.forEach { versionCandidate ->
            append("##"); append(availableComment.padStart(key.length - 2))
            append('='); appendln(versionCandidate)
        }

        trailingCommentLines.forEach { appendln(it) }
    }
}
