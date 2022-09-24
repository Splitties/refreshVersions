package de.fayard.refreshVersions.core.internal.versions

import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section.VersionEntry

internal operator fun VersionsPropertiesModel.plus(
    newEntry: VersionEntry
): VersionsPropertiesModel {
    require(newEntry.leadingCommentLines.isEmpty())
    require(newEntry.trailingCommentLines.isEmpty())

    return this.copy(
        sections = mutableListOf<Section>().also { newList ->
            newList.addAll(sections)
            addOrUpdateEntry(newEntry, newList)
        }
    )
}

internal operator fun VersionsPropertiesModel.plus(
    newEntries: List<VersionEntry>
): VersionsPropertiesModel = this.copy(
    sections = mutableListOf<Section>().also { newList ->
        newList.addAll(sections)
        newEntries.forEach { newEntry ->
            require(newEntry.leadingCommentLines.isEmpty())
            require(newEntry.trailingCommentLines.isEmpty())
            addOrUpdateEntry(newEntry, newList)
        }
    }
)

/**
 * When the entry is added, it is added in alphabetical order relative to other entries.
 */
private fun VersionsPropertiesModel.addOrUpdateEntry(
    newEntry: VersionEntry,
    newList: MutableList<Section>
) {
    val existingEntryWithSameKey: VersionEntry?
    val targetIndex = run {
        for (i in sections.indices) {
            val element = sections[i]
            if (element !is VersionEntry || element.key < newEntry.key) continue

            existingEntryWithSameKey = if (element.key == newEntry.key) element else null
            return@run i
        }
        existingEntryWithSameKey = null
        sections.size
    }

    when (existingEntryWithSameKey) {
        null -> newList.add(index = targetIndex, element = newEntry)
        else -> newList[targetIndex] = existingEntryWithSameKey.copy(
            availableUpdates = newEntry.availableUpdates
        )
    }
}
