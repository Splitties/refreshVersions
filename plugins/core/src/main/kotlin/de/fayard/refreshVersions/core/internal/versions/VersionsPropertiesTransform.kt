package de.fayard.refreshVersions.core.internal.versions

import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section
import de.fayard.refreshVersions.core.internal.versions.VersionsPropertiesModel.Section.VersionEntry



internal operator fun VersionsPropertiesModel.plus(
    newEntry: VersionEntry
): VersionsPropertiesModel {
    require(newEntry.leadingCommentLines.isEmpty())
    require(newEntry.trailingCommentLines.isEmpty())

    val targetIndex = sections.indexOfFirst {
        it is VersionEntry && it.key > newEntry.key
    }.let { if (it == -1) sections.size else it }
    return this.copy(
        sections = mutableListOf<Section>().also {
            it.addAll(sections)
            it.add(index = targetIndex, element = newEntry)
        }
    )
}
