package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.internal.TomlLine.Kind.*
import java.io.File
import de.fayard.refreshVersions.core.Version as MavenVersion

internal class TomlUpdater(val toml: String, val dependenciesUpdates: List<DependencyWithVersionCandidates>) {
    val sectionsMap = Toml.parseTomlInSection(toml)

    val sections = sectionsMap.mapValues { (key, text) ->
        val section = TomlLine.Section.from(key)
        text.lines().map { TomlLine(section, it) }
    }

    fun updateNewVersions(actual: File) {
        if (toml.isBlank()) return

        val newSectionsText = sections.mapValues { (key, lines) ->
            updateNewVersions(lines).joinToString(separator = "\n") { it.text }
        }
        actual.writeText(Toml.tomlSectionsToString(newSectionsText))
    }

    fun updateNewVersions(lines: List<TomlLine>): List<TomlLine> {
        return lines.flatMap { line ->
            val noop = listOf(line)
            when (line.kind) {
                Ignore, LibsUnderscore, LibsVersionRef, PluginVersionRef -> noop
                Delete -> emptyList()
                Version -> {
                    linesForUpdate(line, findLineReferencing(line))
                }
                Libs, Plugin -> {
                    val update = dependenciesUpdates.firstOrNull { dc ->
                        dc.moduleId.name == line.name && dc.moduleId.group == line.group
                    }
                    linesForUpdate(line, update)
                }
            }
        }
    }

    fun findLineReferencing(version: TomlLine): DependencyWithVersionCandidates? {
        val libOrPlugin = sections.values.flatten().firstOrNull { line ->
            println(line)
            line.versionRef == version.key
        }?: return null

        return dependenciesUpdates.firstOrNull { (moduleId) ->
            val nameOk = moduleId.name == libOrPlugin.name
            nameOk && moduleId.group == libOrPlugin.group
        }
    }

    fun linesForUpdate(line: TomlLine, update: DependencyWithVersionCandidates?): List<TomlLine> {
        val result = mutableListOf(line)
        val versions = update?.versionsCandidates ?: return result
        val version = line.version

        val isObject = line.unparsedValue.endsWith("}")

        fun suffix(v: MavenVersion) = when {
            isObject -> """ = "${v.value}" }"""
            line.section == TomlLine.Section.versions ->  """ = "${v.value}""""
            else -> """:${v.value}""""
        }

        val nbSpaces = line.text.indexOf(version ?: "oops") -
                if (isObject) 17 else 14
        val space = List(Math.max(0, nbSpaces)) { " " }.joinToString("")

        versions.mapTo(result) { v: MavenVersion ->
            TomlLine(line.section, "##${space}# available${suffix(v)}")
        }
        return result
    }
}


internal fun TomlUpdater(file: File, dependenciesUpdates: List<DependencyWithVersionCandidates>): TomlUpdater {
    val text: String = if (file.canRead()) file.readText() else ""
    return TomlUpdater(text, dependenciesUpdates)
}
