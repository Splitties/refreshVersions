package de.fayard.refreshVersions.core.internal

internal data class Toml(
    val sections: MutableMap<TomlSection, List<TomlLine>>
) {

    override fun toString() = format()

    internal operator fun set(section: TomlSection, lines: List<TomlLine>) {
        sections[section] = lines
    }

    fun merge(
        section: TomlSection,
        newLines: List<TomlLine>
    ) {
        val existingKeys = get(section).map { it.key }.toSet() - ""
        val filteredLines = newLines.filterNot { it.key in existingKeys }
        val updateExistingLines = get(section).map { line ->
            newLines.firstOrNull { it.key == line.key } ?: line
        }
        sections[section] = updateExistingLines + filteredLines
    }

    internal operator fun get(section: TomlSection): List<TomlLine> = sections[section] ?: emptyList()

    private fun format(): String = buildString {
        initializeRoot()
        for (section in sortedSections()) {
            val lines = get(section)

            if (lines.isNotEmpty()) {
                if (section != TomlSection.Root) append("\n[$section]\n\n")
                append(lines.removeDuplicateBlanks().toText().trim())
                append("\n")
            }
        }
    }

    private fun List<TomlLine>.removeDuplicateBlanks(): List<TomlLine> {
        return filterIndexed { index, tomlLine ->
            if (index == 0) return@filterIndexed true
            val previous = this[index - 1]
            tomlLine.text.isNotBlank() || previous.text.isNotBlank()
        }
    }

    private fun sortedSections() = (TomlSection.orderedSections + sections.keys).toSet()

    private fun initializeRoot() {
        if (get(TomlSection.Root).none { it.text.isNotBlank() }) {
            this[TomlSection.Root] = listOf(
                TomlLine(TomlSection.Root, "## Generated by \$ ./gradlew refreshVersionsCatalog"),
                TomlLine.newLine,
            )
        }
    }
}
