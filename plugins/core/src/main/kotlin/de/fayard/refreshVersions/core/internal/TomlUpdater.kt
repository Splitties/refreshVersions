package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.internal.TomlLine.Kind.*
import java.io.File
import de.fayard.refreshVersions.core.Version as MavenVersion

internal class TomlUpdater(
    private val fileContent: String,
    private val dependenciesUpdates: List<DependencyWithVersionCandidates>
) {

    private val toml = VersionCatalogs.parseToml(fileContent)

    fun updateNewVersions(actual: File) {
        if (fileContent.isBlank()) return

        toml.sections.forEach { (section, lines) ->
            toml[section] = updateNewVersions(lines)
        }
        actual.writeText(toml.toString())
    }

    fun cleanupComments(actual: File) {
        if (fileContent.isBlank()) return

        toml.sections.forEach { (section, lines) ->
            toml[section] = lines.filter { it.kind != Deletable }
        }
        actual.writeText(toml.toString())
    }

    private fun updateNewVersions(lines: List<TomlLine>): List<TomlLine> = lines.flatMap { line ->
        when (line.kind) {
            Ignore, LibsUnderscore, LibsVersionRef, PluginVersionRef -> listOf(line)
            Deletable -> emptyList()
            Version -> {
                linesForUpdate(line, findLineReferencing(line))
            }
            Libs, Plugin -> {
                val updates = dependenciesUpdates.firstOrNull { dc ->
                    dc.moduleId.name == line.name && dc.moduleId.group == line.group
                }
                linesForUpdate(line, updates)
            }
        }
    }

    private fun findLineReferencing(version: TomlLine): DependencyWithVersionCandidates? {
        val libOrPlugin = toml.sections.values.flatten().firstOrNull { line ->
            line.versionRef == version.key
        } ?: return null

        return dependenciesUpdates.firstOrNull { (moduleId) ->
            (moduleId.name == libOrPlugin.name) && (moduleId.group == libOrPlugin.group)
        }
    }

    private fun linesForUpdate(
        line: TomlLine,
        update: DependencyWithVersionCandidates?
    ): List<TomlLine> {
        val result = mutableListOf(line)
        val versions = update?.versionsCandidates ?: return result
        val version = line.version

        val isObject = line.unparsedValue.endsWith("}")

        fun suffix(v: MavenVersion) = when {
            isObject -> """ = "${v.value}" }"""
            line.section == TomlSection.Versions -> """ = "${v.value}""""
            else -> """:${v.value}""""
        }

        val nbSpaces = line.text.indexOf(version) - if (isObject) 17 else 14
        val space = " ".repeat(nbSpaces.coerceAtLeast(0))

        versions.mapTo(result) { v: MavenVersion ->
            TomlLine(
                section = line.section,
                text = "##${space}# available${suffix(v)}"
            )
        }
        return result
    }
}


internal fun TomlUpdater(
    file: File,
    dependenciesUpdates: List<DependencyWithVersionCandidates>
): TomlUpdater {
    val text: String = if (file.canRead()) file.readText() else ""
    return TomlUpdater(text, dependenciesUpdates)
}
