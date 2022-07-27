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
        val map = parseTomlInSections(toml)
            .map { (sectionName, paragraph) ->
                val section = TomlSection.from(sectionName)
                section to paragraph.lines().map { TomlLine(section, it) }
            }.toMap()
        return Toml(map.toMutableMap())
    }

    /**
     * Returns a map where the key is the section name, and the value, the section content.
     */
    internal fun parseTomlInSections(toml: String): Map<String, String> {
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

    fun generateVersionsCatalogText(
        deps: Deps,
        currentText: String,
        withVersions: Boolean,
        plugins: List<Dependency>
    ): String {
        val versionRefMap = dependenciesWithVersionRefsMapIfAny(deps)

        val toml = parseToml(currentText)
        toml.merge(TomlSection.Plugins, addPlugins(plugins, versionRefMap))
        toml.merge(TomlSection.Libraries, versionsCatalogLibraries(deps, versionRefMap, withVersions))
        toml.merge(TomlSection.Versions, addVersions(deps, versionRefMap))
        return toml.toString()
    }

    private fun addPlugins(
        plugins: List<Dependency>,
        versionRefMap: Map<Library, TomlVersionRef?>
    ): List<TomlLine> = plugins.distinctBy { d ->
        "${d.group}:${d.name}"
    }.mapNotNull { d ->
        val version = d.version ?: return@mapNotNull null
        val lib = Library(d.group!!, d.name, version)

        val pair = if (lib in versionRefMap) {
            "version.ref" to versionRefMap[lib]!!.key
        } else {
            "version" to version
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

    private fun addVersions(
        deps: Deps,
        versionRefMap: Map<Library, TomlVersionRef?>
    ): List<TomlLine> = deps.libraries.distinctBy { lib ->
        versionRefMap[lib]?.key
    }.flatMap { lib ->
        val (versionName, versionValue) = versionRefMap[lib] ?: return@flatMap emptyList()

        val versionLine = TomlLine(TomlSection.Versions, versionName, versionValue)
        listOf(TomlLine.newLine, versionLine)
    }

    private data class TomlVersionRef(val key: String, val version: String)

    lateinit var versionsMap: Map<String, String>
    lateinit var versionKeyReader: ArtifactVersionKeyReader

    private fun dependenciesWithVersionRefsMapIfAny(deps: Deps): Map<Library, TomlVersionRef?> =
        deps.libraries.mapNotNull { lib ->
            val name = getVersionPropertyName(ModuleId.Maven(lib.group, lib.name), versionKeyReader)

            if (name.contains("..") || name.startsWith("plugin")) {
                return@mapNotNull null
            }
            val version = versionsMap[name] ?: lib.version
            lib to version?.let {
                TomlVersionRef(
                    key = name.removePrefix("version.").replace(".", "-"), // Better match TOML naming convention.
                    version = it
                )
            }
        }.toMap()

    private fun versionsCatalogLibraries(
        deps: Deps,
        versionRefMap: Map<Library, TomlVersionRef?>,
        withVersions: Boolean,
    ): List<TomlLine> {

        return deps.libraries.filterNot { lib ->
            lib.name.endsWith("gradle.plugin")
        }.flatMap { lib ->
            val line: TomlLine = if (lib in versionRefMap) {
                val versionRef: TomlVersionRef? = versionRefMap[lib]
                TomlLine(
                    section = TomlSection.Libraries,
                    key = deps.names[lib]!!,
                    map = mutableMapOf<String, String?>().apply { //TODO: Replace with buildMap later.
                        put("group", lib.group)
                        put("name", lib.name)
                        put("version.ref", versionRef?.key ?: return@apply)
                    }
                )
            } else {
                val versionKey = getVersionPropertyName(ModuleId.Maven(lib.group, lib.name), versionKeyReader)
                val version = when {
                    lib.version == null -> null
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
