package de.fayard.refreshVersions.core.internal

internal sealed class TomlSection(open val name: String) {

    override fun toString() = name

    object Root: TomlSection("root")
    object Versions: TomlSection("versions")
    object Plugins: TomlSection("plugins")
    object Bundles: TomlSection("bundles")
    object Libraries: TomlSection("libraries")
    data class Custom(override val name: String) : TomlSection(name)

    companion object {
        val orderedSections = listOf(Root, Bundles, Plugins, Versions, Libraries)

        fun from(name: String): TomlSection = orderedSections.firstOrNull { it.name == name } ?: Custom(name)
    }
}
