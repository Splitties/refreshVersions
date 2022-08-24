package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.internal.TomlLine.Kind.*
import java.io.File
import de.fayard.refreshVersions.core.Version as MavenVersion

internal class VersionsCatalogUpdater(
    private val fileContent: String,
    private val dependenciesUpdates: List<DependencyWithVersionCandidates>
) {

    private val toml = VersionsCatalogs.parseToml(fileContent)

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
                val updates = dependenciesUpdates.firstOrNull {
                    it.moduleId.name == line.name && it.moduleId.group == line.group
                }
                linesForUpdate(line, updates)
            }
        }
    }

    private fun findLineReferencing(version: TomlLine): DependencyWithVersionCandidates? {
        val libOrPlugin = toml.sections.values.flatten().firstOrNull { line ->
            line.versionRef == version.key
        } ?: return null

        return dependenciesUpdates.firstOrNull {
            val moduleId = it.moduleId
            (moduleId.name == libOrPlugin.name) && (moduleId.group == libOrPlugin.group)
        }
    }

    private fun linesForUpdate(
        line: TomlLine,
        update: DependencyWithVersionCandidates?
    ): List<TomlLine> {
        val result = mutableListOf(line)
        val currentVersion = line.version ?: return result
        if (update == null) return result
        val availableUpdates = update.versionsCandidates(MavenVersion(currentVersion))

        val isObject = line.unparsedValue.endsWith("}")

        fun suffix(version: MavenVersion) = when {
            isObject -> """= "${version.value}" }"""
            line.section == TomlSection.Versions -> """= "${version.value}""""
            else -> """:${version.value}""""
        }

        val nbSpaces = line.text.indexOf(currentVersion) - if (isObject) 11 else 8
        val leadingSpaces = " ".repeat(nbSpaces.coerceAtLeast(0))
        val trailingSpaces = " ".repeat(
            when {
                nbSpaces >= 0 && (isObject || line.section == TomlSection.Versions) -> 1
                else -> 0
            }
        )

        availableUpdates.mapTo(result) { versionCandidate: MavenVersion ->
            TomlLine(
                section = line.section,
                text = "##${leadingSpaces}⬆$trailingSpaces${suffix(versionCandidate)}"
            )
        }
        return result
    }
}


internal fun VersionsCatalogUpdater(
    file: File,
    dependenciesUpdates: List<DependencyWithVersionCandidates>
): VersionsCatalogUpdater {
    val text: String = if (file.canRead()) file.readText() else ""
    return VersionsCatalogUpdater(text, dependenciesUpdates)
}
