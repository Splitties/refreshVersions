package de.fayard.refreshVersions.core.internal

internal sealed class TomlSection(open val name: String) {

    override fun toString() = name

    object Root: TomlSection("root")
    object Versions: TomlSection("versions")
    object Libraries: TomlSection("libraries")
    object Bundles: TomlSection("bundles")
    object Plugins: TomlSection("plugins")
    data class Custom(override val name: String) : TomlSection(name)

    companion object {
        val orderedSections = listOf(Root, Versions, Libraries, Bundles, Plugins)

        fun from(name: String): TomlSection = orderedSections.firstOrNull { it.name == name } ?: Custom(name)
    }
}
