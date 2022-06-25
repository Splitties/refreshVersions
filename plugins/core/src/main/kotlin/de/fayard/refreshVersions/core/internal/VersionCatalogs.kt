package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.util.GradleVersion

@InternalRefreshVersionsApi
object VersionCatalogs {

    const val LIBS_VERSIONS_TOML = "gradle/libs.versions.toml"

    val minimumGradleVersion: GradleVersion = GradleVersion.version("7.4")

    fun isSupported(): Boolean = GradleVersion.current() >= minimumGradleVersion

    fun dependencyAliases(versionCatalog: VersionCatalog?): Map<ModuleId.Maven, String> {
        versionCatalog ?: return emptyMap()
        return versionCatalog.libraryAliases.mapNotNull { alias ->
            versionCatalog.findLibrary(alias)
                .orElse(null)
                ?.orNull
                ?.let { dependency: MinimalExternalModuleDependency ->
                    ModuleId.Maven(dependency.module.group, dependency.module.name) to "libs.$alias"
                }
        }.toMap()
    }

    internal fun parseToml(toml: String): Toml {
        val map = parseTomlInSection(toml)
            .map { (sectionName, paragraph) ->
                val section = TomlSection.from(sectionName)
                section to paragraph.lines().map { TomlLine(section, it) }
            }.toMap()
        return Toml(map.toMutableMap())
    }

    fun parseTomlInSection(toml: String): Map<String, String> {
        val result = mutableMapOf<String, StringBuilder>()
        result["root"] = StringBuilder()
        var current: StringBuilder = result["root"]!!
        val lines = toml.lines()
        for ((index, line) in lines.withIndex()) {
            val trimmed = line.trim()
            val isSectionHeader = trimmed.startsWith("[") && trimmed.endsWith("]")
            if (isSectionHeader) {
                val sectionName = trimmed.removePrefix("[").removeSuffix("]")
                result[sectionName] = StringBuilder()
                current = result[sectionName]!!
            } else {
                current.append(line)
                if (index != lines.lastIndex) current.append("\n")
            }
        }
        return result.mapValues { it.value.toString() }
    }

    fun versionsCatalog(deps: Deps, currentText: String, withVersions: Boolean, plugins: List<Dependency>): String {
        val map = dependenciesMap(deps)

        val toml = parseToml(currentText)
        toml.merge(TomlSection.Plugins, addPlugins(plugins, map))
        toml.merge(TomlSection.Libraries, versionsCatalogLibraries(deps, map, withVersions))
        toml.merge(TomlSection.Versions, addVersions(deps, map))
        return toml.toString()
    }

    private fun addPlugins(plugins: List<Dependency>, map: Map<Library, Pair<String, String>>): List<TomlLine> {
        return plugins
            .distinctBy { d -> "${d.group}:${d.name}" }
            .map { d ->
                val lib = Library(d.group!!, d.name, d.version!!)

                val pair = if (lib in map) {
                    "version.ref" to map[lib]!!.first
                } else {
                    "version" to (d.version ?: "_")
                }

                val pluginId = d.name.removeSuffix(".gradle.plugin")
                TomlLine(
                    TomlSection.Plugins,
                    pluginId.replace(".", "-"),
                    mapOf("id" to pluginId, pair)
                )

            }.flatMap {
                listOf(TomlLine.newLine, it)
            }
    }

    private fun addVersions(deps: Deps, map: Map<Library, Pair<String, String>>): List<TomlLine> {
        return deps.libraries
            .distinctBy { lib -> map[lib]?.first }
            .flatMap { lib ->
                val (versionName, versionValue) = map[lib] ?: return@flatMap emptyList()

                val versionLine = TomlLine(TomlSection.Versions, versionName, versionValue)
                listOf(TomlLine.newLine, versionLine)
            }
    }

    private fun dependenciesMap(deps: Deps): Map<Library, Pair<String, String>> {
        val versionsMap = RefreshVersionsConfigHolder.readVersionsMap()
        val versionKeyReader: ArtifactVersionKeyReader = RefreshVersionsConfigHolder.versionKeyReader

        return deps.libraries.mapNotNull { lib ->
            val name = getVersionPropertyName(ModuleId.Maven(lib.group, lib.name), versionKeyReader)

            if (name.contains("..") || name.startsWith("plugin")) {
                return@mapNotNull  null
            }
            val tomlName = name.removePrefix("version.").replace(".", "-")

            lib to Pair(tomlName, versionsMap[name] ?: lib.version)
        }.toMap()
    }

    private fun versionsCatalogLibraries(
        deps: Deps,
        map: Map<Library, Pair<String, String>>,
        withVersions: Boolean,
    ): List<TomlLine> {
        val versionsMap = RefreshVersionsConfigHolder.readVersionsMap()
        val versionKeyReader: ArtifactVersionKeyReader = RefreshVersionsConfigHolder.versionKeyReader

        return deps.libraries
            .filterNot { lib -> lib.name.endsWith("gradle.plugin") }
            .flatMap { lib ->
            val line: TomlLine = if (lib in map) {
                val versionName = map[lib]!!.first
                val refVersion = mapOf("group" to lib.group, "name" to lib.name, "version.ref" to versionName)
                TomlLine(TomlSection.Libraries, deps.names[lib]!!, refVersion)
            } else {
                val versionKey = getVersionPropertyName(ModuleId.Maven(lib.group, lib.name), versionKeyReader)
                val version = when {
                    lib.version == "none" -> "none"
                    withVersions.not() -> "_"
                    versionKey in versionsMap -> versionsMap[versionKey]!!
                    else -> "_"
                }
                val value = lib.copy(version = version).toDependency()
                TomlLine(TomlSection.Libraries, deps.names[lib]!!, value)
            }

            listOf(TomlLine.newLine, line)
        }
    }
}
