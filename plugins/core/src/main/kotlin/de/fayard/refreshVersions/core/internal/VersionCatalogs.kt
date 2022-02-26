package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.util.GradleVersion

@InternalRefreshVersionsApi
object VersionCatalogs {

    const val LIBS_VERSIONS_TOML = "gradle/libs.versions.toml"

    val NEEDS_GRADLE_VERSION: GradleVersion = GradleVersion.version("7.4")

    fun isSupported(): Boolean =
        GradleVersion.current() >= NEEDS_GRADLE_VERSION

    fun dependencyAliases(versionCatalog: VersionCatalog?): Map<ModuleId.Maven, String> {
        versionCatalog ?: return emptyMap()
        return versionCatalog.dependencyAliases.mapNotNull { alias ->
            versionCatalog.findDependency(alias)
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
        val toml = parseToml(currentText)
        toml[TomlSection.Plugins] = addPlugins(plugins)
        toml[TomlSection.Libraries] = versionsCatalogLibraries(deps, withVersions)
        return toml.toString()
    }

    private fun addPlugins(plugins: List<Dependency>): List<TomlLine> =
        plugins
            .distinctBy { d -> "${d.group}:${d.name}" }
            .map { d ->
                val pluginId = d.name.removeSuffix(".gradle.plugin")
                val map = mapOf(
                    "id" to pluginId,
                    "version" to (d.version ?: "_")
                )
                TomlLine(TomlSection.Plugins, pluginId.replace(".", "-"), map)

            }.flatMap {
                listOf(TomlLine.newLine, it)
            }

    private fun versionsCatalogLibraries(deps: Deps, withVersions: Boolean): List<TomlLine> {
        val versionsMap = RefreshVersionsConfigHolder.readVersionsMap()
        val versionKeyReader: ArtifactVersionKeyReader = RefreshVersionsConfigHolder.versionKeyReader

        return deps.libraries.flatMap { lib ->
            val versionKey = getVersionPropertyName(ModuleId.Maven(lib.group, lib.name), versionKeyReader)
            val version = versionsMap[versionKey] ?: "_"

            val value = lib.groupModule() + ":" + if (withVersions) version else "_"
            listOf(
                TomlLine.newLine,
                TomlLine(TomlSection.Libraries, deps.names[lib]!!, value)
            )
        }
    }
}
