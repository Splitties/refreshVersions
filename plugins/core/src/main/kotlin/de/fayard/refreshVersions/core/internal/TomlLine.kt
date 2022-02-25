package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.internal.TomlLine.Kind.*
import de.fayard.refreshVersions.core.internal.TomlLine.Section.*

data class TomlLine(
    val section: Section,
    val text: String,
) {

    @Suppress("EnumEntryName")
    enum class Section { versions, libraries, bundles, plugins, others }

    enum class Kind { Ignore, Available, Libs, LibsUnderscore, LibsVersionRef, Version, Plugin, PluginVersionRef }

    val textWithoutComment = text.substringBefore("#")

    val key = textWithoutComment.substringBefore("=", missingDelimiterValue = "").trim()
    val hasKey = key.isNotBlank()

    val unparsedValue: String = if (hasKey.not()) "" else textWithoutComment.substringAfter("=").trim()

    private val quote = "\""
    fun String.unquote() = trim().removePrefix(quote).removeSuffix(quote)

    val value = if (unparsedValue.startsWith(quote)) unparsedValue.unquote() else ""

    val kind: Kind = this.guessTomlLineKind()

    val map: Map<String, String> = parseTomlMap(kind)

    val versionRef get() =  map["version.ref"]
    val module get() = "$group:$name"
    val version: String? by map
    val group: String? by map
    val name: String? by map
    val id: String? by  map

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
        Available -> emptyMap()
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
    val hasVersionRef = textWithoutComment.contains("version.ref")
    val containsAvailable = text.contains("# available")

    return when (section) {
        bundles -> Ignore
        others -> Ignore
        versions -> when {
            containsAvailable -> Available
            hasKey -> Version
            else -> Ignore
        }
        libraries -> {
            when {
                containsAvailable -> Available
                hasKey.not() -> Ignore
                textWithoutComment.endsWith(":_\"") -> LibsUnderscore
                hasVersionRef -> LibsVersionRef
                else -> Libs
            }
        }
        plugins -> when {
            containsAvailable -> Available
            hasKey.not() -> Ignore
            hasVersionRef -> PluginVersionRef
            else -> Plugin
        }
    }
}
