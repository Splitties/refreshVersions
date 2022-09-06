package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.internal.TomlLine.Kind.*
import org.gradle.api.artifacts.Dependency

/**
 * Despite TOML supporting braces in its syntax, they must open and close on
 * the same line, so having a per-line model is fine.
 */
internal data class TomlLine(
    val section: TomlSection,
    val text: String,
) {

    internal enum class Kind { Ignore, Deletable, Libs, LibsUnderscore, LibsVersionRef, Version, Plugin, PluginVersionRef }
    //TODO: Maybe convert to sealed class/interface?

    val textWithoutComment = text.substringBefore("#")

    val key = textWithoutComment.substringBefore("=", missingDelimiterValue = "").trim()
    val hasKey = key.isNotBlank()

    val unparsedValue: String = if (hasKey.not()) "" else textWithoutComment.substringAfter("=").trim()

    fun String.unquote() = trim().removeSurrounding("\"")

    val value = if (unparsedValue.startsWith('"')) unparsedValue.unquote() else ""

    val kind: Kind = this.guessTomlLineKind()

    val map: Map<String, String> = parseTomlMap(kind).withDefault { "" }

    val versionRef get() = map["version.ref"]

    val module get() = "$group:$name"

    val version: String? get() = if (section == TomlSection.Versions) value else map["version"]

    val group: String get() = if (section == TomlSection.Plugins) id else map["group"]!!

    val name: String get() = if (section == TomlSection.Plugins) "$id.gradle.plugin" else map["name"]!!

    val id: String by map

    override fun toString(): String = "TomlLine(section=$section, kind=$kind, key=$key, value=$value, map=$map)\n$text"

    internal companion object {
        val newLine = TomlLine(TomlSection.Custom("blank"), "")
    }
}

internal fun List<TomlLine>.toText(): String = joinToString("\n", postfix = "\n", prefix = "\n") { it.text }

internal fun TomlLine(
    section: TomlSection,
    key: String,
    value: String
): TomlLine = TomlLine(section = section, text = """$key = "$value"""")

internal fun TomlLine(
    section: TomlSection,
    key: String,
    dependency: Dependency
): TomlLine = when (dependency.version) {
    null -> TomlLine(
        section = section,
        text = """$key = { module = "${dependency.group}:${dependency.name}" }"""
    )
    else -> TomlLine(
        section = section,
        key = key,
        value = """${dependency.group}:${dependency.name}:${dependency.version}"""
    )
}

internal fun TomlLine(
    section: TomlSection,
    key: String,
    map: Map<String, String?>
): TomlLine {
    require((map.keys - validKeys).isEmpty()) { "Map $map has invalid keys. Valid: $validKeys" }
    val formatMap = map.entries
        .joinToString(", ") { (key, value) -> """$key = "$value"""" }
    return TomlLine(section, "$key = { $formatMap }")
}

private val validKeys = listOf("module", "group", "name", "version.ref", "version", "id")

private fun TomlLine.parseTomlMap(kind: TomlLine.Kind): Map<String, String> {
    val splitByColon = value.split(":")

    val map: MutableMap<String, String> = when {
        unparsedValue.startsWith('{').not() -> mutableMapOf()
        else -> unparsedValue
            .removeSurrounding("{", "}")
            .split(",")
            .associate { entry ->
                val (key, value) = entry.split("=")
                key.unquote() to value.unquote()
            }.toMutableMap()
    }

    return when (kind) {
        Ignore -> emptyMap()
        Deletable -> emptyMap()
        LibsUnderscore -> emptyMap()
        Version -> emptyMap()
        Plugin, PluginVersionRef -> when {
            value.isNotBlank() -> mutableMapOf<String, String>().apply { //TODO: Replace with buildMap later.
                this["id"] = splitByColon.first()
                splitByColon.getOrNull(1)?.also { version ->
                    this["version"] = version
                }
            }
            else -> map
        }
        Libs, LibsVersionRef -> when {
            value.isNotBlank() -> {
                val (group, name) = splitByColon
                val version = splitByColon.getOrNull(2)
                lineMap(group = group, name = name, version = version)
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

private fun lineMap(
    group: String,
    name: String,
    version: String?,
) = listOfNotNull(
    "group" to group,
    "name" to name,
    version?.let { "version" to it },
).toMap()

private fun TomlLine.guessTomlLineKind(): TomlLine.Kind {
    if (text.startsWith("##")) return Deletable

    val hasVersionRef = textWithoutComment.contains("version.ref")

    return when (section) {
        is TomlSection.Custom -> Ignore
        TomlSection.Root -> Ignore
        TomlSection.Bundles -> Ignore
        TomlSection.Versions -> when {
            hasKey -> Version
            else -> Ignore
        }
        TomlSection.Libraries -> when {
            hasKey.not() -> Ignore
            textWithoutComment.endsWith(":_\"") -> LibsUnderscore
            hasVersionRef -> LibsVersionRef
            else -> Libs
        }
        TomlSection.Plugins -> when {
            hasKey.not() -> Ignore
            hasVersionRef -> PluginVersionRef
            else -> Plugin
        }
    }
}
