package de.fayard.refreshVersions.core.internal

internal sealed class TomlSection(val name: String) {

    override fun toString() = name

    object Root: TomlSection("root")
    object Versions: TomlSection("versions")
    object Plugins: TomlSection("plugins")
    object Bundles: TomlSection("bundles")
    object Libraries: TomlSection("libraries")
    class Custom(name: String) : TomlSection(name)

    companion object {
        val sectionOrder = listOf(Root, Bundles, Plugins, Versions, Libraries)

        fun from(name: String): TomlSection =
            sectionOrder
                .firstOrNull { it.name == name }
                ?: Custom(name)
    }
}