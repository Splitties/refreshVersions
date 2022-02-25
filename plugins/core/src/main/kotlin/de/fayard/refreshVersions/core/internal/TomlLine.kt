package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.internal.TomlLine.Kind.*
import de.fayard.refreshVersions.core.internal.TomlLine.Section.*

internal data class TomlLine(
    val section: Section,
    val text: String,
) {

    @Suppress("EnumEntryName")
    internal enum class Section { versions, libraries, bundles, plugins, others ;
        companion object {
            fun from(name: String): Section = values().firstOrNull { it.name == name } ?: others
        }
    }

    internal enum class Kind { Ignore, Delete, Libs, LibsUnderscore, LibsVersionRef, Version, Plugin, PluginVersionRef }

    val textWithoutComment = text.substringBefore("#")

    val key = textWithoutComment.substringBefore("=", missingDelimiterValue = "").trim()
    val hasKey = key.isNotBlank()

    val unparsedValue: String = if (hasKey.not()) "" else textWithoutComment.substringAfter("=").trim()

    private val quote = "\""
    fun String.unquote() = trim().removePrefix(quote).removeSuffix(quote)

    val value = if (unparsedValue.startsWith(quote)) unparsedValue.unquote() else ""

    val kind: Kind = this.guessTomlLineKind()

    val map: Map<String, String> = parseTomlMap(kind).withDefault { "" }

    val versionRef get() =  map["version.ref"]

    val module get() = "$group:$name"

    val version: String get() =
        if (section == versions) value else map["version"]!!

    val group: String get() =
        if (section == plugins) id else map["group"]!!

    val name: String get() =
        if (section == plugins) "$id.gradle.plugin" else map["name"]!!

    val id: String by  map

    override fun toString(): String = "TomlLine(section=$section, kind=$kind, key=$key, value=$value, map=$map)\n$text"
}

private fun TomlLine.parseTomlMap(kind: TomlLine.Kind): Map<String, String> {
    val splitSemicolon = value.split(":")

    val map: MutableMap<String, String> = when {
        unparsedValue.startsWith("{").not() -> mutableMapOf()
        else -> unparsedValue
            .removePrefix("{").removeSuffix("}")
            .split(",")
            .associate { entry ->
                val (key, value) = entry.split("=")
                key.unquote() to value.unquote()
            }.toMutableMap()
    }

    return when(kind) {
        Ignore -> emptyMap()
        Delete -> emptyMap()
        LibsUnderscore -> emptyMap()
        Version -> emptyMap()
        Plugin, PluginVersionRef -> when {
            value.isNotBlank() -> {
                val (id, version) = splitSemicolon
                mapOf("id" to id, "version" to version)
            }
            else -> map
        }
        Libs, LibsVersionRef -> when {
            value.isNotBlank() -> {
                val (group, name, version) = splitSemicolon
                lineMap(group, name, version, null)
            }
            else -> {
                map["module"]?.also { module ->
                    val (group, name) = module.split(":")
                    map.remove("module")
                    map["group"] = group
                    map["name"] = name
                }
                map
            }
        }
    }
}

private fun lineMap(group: String, name: String, version: String?, versionRef: String?) =
    listOfNotNull("group" to group, "name" to name, version?.let { "version" to it }, versionRef?.let { "version.ref" to it })
        .toMap()

private fun TomlLine.guessTomlLineKind(): TomlLine.Kind {
    when {
        text.contains("# available") -> return Delete
        text.startsWith("## unused") -> return Delete
        text.startsWith("## error") -> return Delete
        text.startsWith("## warning") -> return Delete
    }

    val hasVersionRef = textWithoutComment.contains("version.ref")

    return when (section) {
        bundles -> Ignore
        others -> Ignore
        versions -> when {
            hasKey -> Version
            else -> Ignore
        }
        libraries -> {
            when {
                hasKey.not() -> Ignore
                textWithoutComment.endsWith(":_\"") -> LibsUnderscore
                hasVersionRef -> LibsVersionRef
                else -> Libs
            }
        }
        plugins -> when {
            hasKey.not() -> Ignore
            hasVersionRef -> PluginVersionRef
            else -> Plugin
        }
    }
}
