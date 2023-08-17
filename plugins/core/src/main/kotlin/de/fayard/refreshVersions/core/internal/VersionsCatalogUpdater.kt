package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.internal.TomlLine.Kind.*
import java.io.File
import de.fayard.refreshVersions.core.Version as MavenVersion

internal class VersionsCatalogUpdater(
    private val fileContent: String,
    private val dependenciesUpdates: List<DependencyWithVersionCandidates>
) {

    private val toml = VersionsCatalogs.parseToml(fileContent)

    fun updateNewVersions(versionsCatalogTomlFile: File): Boolean {
        require(versionsCatalogTomlFile.extension == "toml")
        if (fileContent.isBlank()) return false

        toml.sections.forEach { (section, lines) ->
            toml[section] = updateNewVersions(lines)
        }
        val newContent = toml.toString()
        if (versionsCatalogTomlFile.canRead() && newContent == versionsCatalogTomlFile.readText()) return false
        versionsCatalogTomlFile.writeText(newContent)
        return true
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
                linesForUpdates(line, findLineReferencing(line))
            }
            Libs, Plugin -> {
                val updates = dependenciesUpdates.firstOrNull {
                    it.moduleId.name == line.name && it.moduleId.group == line.group
                }
                linesForUpdates(line, updates)
            }
        }
    }

    private fun findLineReferencing(version: TomlLine): DependencyWithVersionCandidates? {
        return toml.sections.values.asSequence().flatten().filter { line ->
            line.versionRef == version.key
        }.mapNotNull { libOrPlugin ->
            dependenciesUpdates.firstOrNull {
                val moduleId = it.moduleId
                (moduleId.name == libOrPlugin.name) && (moduleId.group == libOrPlugin.group)
            }
        }.firstOrNull()
    }

    private fun linesForUpdates(
        initialLine: TomlLine,
        versionCandidates: DependencyWithVersionCandidates?
    ): List<TomlLine> {
        val currentVersion = initialLine.version ?: return listOf(initialLine)
        if (versionCandidates == null) return listOf(initialLine)
        val availableUpdates = versionCandidates.versionsCandidates(MavenVersion(currentVersion))

        val isObject = initialLine.unparsedValue.endsWith("}")

        val commentPrefix = "##"
        val availableSymbol = "⬆"
        val versionPrefix = when {
            isObject || initialLine.section == TomlSection.Versions -> """= """"
            else -> """:"""
        }
        val versionSuffix = when {
            isObject -> """" }"""
            else -> "\""
        }
        val leadingSpace: String
        val trailingSpace: String
        sequence {
            yield(commentPrefix)
            yield(availableSymbol)
            yield(versionPrefix)
        }.sumOf { it.length }.let {
            it + 1 // Because the length of availableSymbol (⬆) is 1, but it takes the width of 2 chars in the IDE.
        }.let {
            val indexOfCurrentVersion = initialLine.text.indexOf(currentVersion)
            val gap = indexOfCurrentVersion - it
            leadingSpace = " ".repeat((gap - 1).coerceAtLeast(0))
            trailingSpace = if (gap > 0 && versionPrefix.endsWith(' ').not()) " " else ""
        }
        return availableUpdates.mapTo(mutableListOf(initialLine)) { versionCandidate: MavenVersion ->
            val version = versionCandidate.value
            TomlLine(
                section = initialLine.section,
                text = "$commentPrefix$leadingSpace$availableSymbol$trailingSpace$versionPrefix$version$versionSuffix"
            )
        }
    }
}


internal fun VersionsCatalogUpdater(
    file: File,
    dependenciesUpdates: List<DependencyWithVersionCandidates>
): VersionsCatalogUpdater {
    val text: String = if (file.canRead()) file.readText() else ""
    return VersionsCatalogUpdater(text, dependenciesUpdates)
}
