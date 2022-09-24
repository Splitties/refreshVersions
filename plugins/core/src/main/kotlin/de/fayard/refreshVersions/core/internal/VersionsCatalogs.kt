package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.FeatureFlag
import de.fayard.refreshVersions.core.ModuleId
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.MinimalExternalModuleDependency
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.findByType
import org.gradle.util.GradleVersion

@InternalRefreshVersionsApi
object VersionsCatalogs {

    const val LIBS_VERSIONS_TOML = "gradle/libs.versions.toml" // Unrelated to the catalog name, never changes.

    val minimumGradleVersion: GradleVersion = GradleVersion.version("7.4")

    fun isSupported(): Boolean = GradleVersion.current() >= minimumGradleVersion

    fun defaultCatalogName(): String = try {
        @Suppress("UnstableApiUsage")
        RefreshVersionsConfigHolder.settings.dependencyResolutionManagement.defaultLibrariesExtensionName.get()
    } catch (t: Throwable) {
        System.err.println(t)
        "libs"
    }

    fun getDefault(project: Project): VersionCatalog? {
        val versionCatalogs = project.extensions.findByType<VersionCatalogsExtension>()
        return versionCatalogs?.find(defaultCatalogName())?.orElse(null)
    }

    internal fun libraries(versionCatalog: VersionCatalog?): Set<MinimalExternalModuleDependency> {
        if (versionCatalog == null) return emptySet()
        val aliases = versionCatalog.libraryAliases
        return aliases.mapTo(LinkedHashSet(aliases.size)) { alias ->
            versionCatalog.findLibrary(alias).get().get()
        }
    }

    internal fun plugins(versionCatalog: VersionCatalog?): Set<PluginDependencyCompat> {
        if (versionCatalog == null) return emptySet()
        val aliases = versionCatalog.pluginAliases
        return aliases.mapTo(LinkedHashSet(aliases.size)) { alias ->
            PluginDependencyCompat(versionCatalog.findPlugin(alias).get().get())
        }
    }

    fun dependencyAliases(versionCatalog: VersionCatalog?): Map<ModuleId.Maven, String> = when {
        FeatureFlag.VERSIONS_CATALOG.isNotEnabled -> emptyMap()
        versionCatalog == null -> emptyMap()
        else -> versionCatalog.libraryAliases.mapNotNull { alias ->
            versionCatalog.findLibrary(alias)
                .orElse(null)
                ?.orNull
                ?.let { dependency: MinimalExternalModuleDependency ->
                    ModuleId.Maven(
                        group = dependency.module.group,
                        name = dependency.module.name
                    ) to "${versionCatalog.name}.$alias"
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
        versionsMap: Map<String, String> = RefreshVersionsConfigHolder.readVersionsMap(),
        versionKeyReader: ArtifactVersionKeyReader = RefreshVersionsConfigHolder.versionKeyReader,
        dependenciesAndNames: Map<Dependency, String>,
        currentText: String,
        moveVersionsToCatalog: Boolean,
        plugins: List<Dependency>
    ): String {
        val dependencies = dependenciesAndNames.keys.toList()
        val versionRefMap = dependenciesWithVersionRefsMapIfAny(
            versionsMap = versionsMap,
            versionKeyReader = versionKeyReader,
            dependencies = dependencies
        )

        val toml = parseToml(currentText)
        toml.merge(TomlSection.Plugins, addPlugins(plugins, versionRefMap))
        toml.merge(
            section = TomlSection.Libraries,
            newLines = versionsCatalogLibraries(
                versionsMap = versionsMap,
                versionKeyReader = versionKeyReader,
                dependenciesAndNames = dependenciesAndNames,
                versionRefMap = versionRefMap,
                moveVersionsToCatalog = moveVersionsToCatalog
            )
        )
        toml.merge(TomlSection.Versions, addVersions(dependenciesAndNames, versionRefMap))
        return toml.toString()
    }

    private fun addPlugins(
        plugins: List<Dependency>,
        versionRefMap: Map<Dependency, TomlVersionRef?>
    ): List<TomlLine> = plugins.distinctBy { d ->
        "${d.group}:${d.name}"
    }.mapNotNull { d ->
        val version = d.version ?: return@mapNotNull null

        val pair = if (d in versionRefMap) {
            "version.ref" to versionRefMap.getValue(d)!!.key
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
        dependenciesAndNames: Map<Dependency, String>,
        versionRefMap: Map<Dependency, TomlVersionRef?>
    ): List<TomlLine> = dependenciesAndNames.keys.distinctBy { dependency ->
        versionRefMap[dependency]?.key
    }.flatMap { dependency ->
        val (versionName, versionValue) = versionRefMap[dependency] ?: return@flatMap emptyList()

        val versionLine = TomlLine(TomlSection.Versions, versionName, versionValue)
        listOf(TomlLine.newLine, versionLine)
    }

    private data class TomlVersionRef(val key: String, val version: String)

    private fun dependenciesWithVersionRefsMapIfAny(
        versionsMap: Map<String, String>,
        versionKeyReader: ArtifactVersionKeyReader,
        dependencies: List<Dependency>
    ): Map<Dependency, TomlVersionRef?> = dependencies.mapNotNull { dependency ->
        val group = dependency.group ?: return@mapNotNull null
        if (dependency.version == null) return@mapNotNull null

        val name = getVersionPropertyName(ModuleId.Maven(group, dependency.name), versionKeyReader)

        if (name.contains("..") || name.startsWith("plugin")) {
            return@mapNotNull null
        }
        val version = versionsMap[name] ?: dependency.version
        val versionRef = version?.let {
            TomlVersionRef(
                key = name.removePrefix("version.").replace(".", "-"), // Better match TOML naming convention.
                version = it
            )
        }
        dependency to versionRef
    }.toMap()

    private fun versionsCatalogLibraries(
        versionsMap: Map<String, String>,
        versionKeyReader: ArtifactVersionKeyReader,
        dependenciesAndNames: Map<Dependency, String>,
        versionRefMap: Map<Dependency, TomlVersionRef?>,
        moveVersionsToCatalog: Boolean,
    ): List<TomlLine> = dependenciesAndNames.keys.filterNot { dependency ->
        dependency.name.endsWith("gradle.plugin") && dependency.group != null
    }.flatMap { dependency: Dependency ->
        val group = dependency.group!!
        val line: TomlLine = if (dependency in versionRefMap) {
            val versionRef: TomlVersionRef? = versionRefMap[dependency]
            TomlLine(
                section = TomlSection.Libraries,
                key = dependenciesAndNames.getValue(dependency),
                map = mutableMapOf<String, String?>().apply { //TODO: Replace with buildMap later.
                    put("group", dependency.group)
                    put("name", dependency.name)
                    put("version.ref", versionRef?.key ?: return@apply)
                }
            )
        } else {
            val version = when {
                dependency.version == null -> null
                moveVersionsToCatalog.not() -> "_"
                else -> when (
                    val versionKey = getVersionPropertyName(
                        moduleId = ModuleId.Maven(group, dependency.name),
                        versionKeyReader = versionKeyReader
                    )
                ) {
                    in versionsMap -> versionsMap[versionKey]!!
                    else -> dependency.version
                }
            }
            TomlLine(
                section = TomlSection.Libraries,
                key = dependenciesAndNames.getValue(dependency),
                dependency = ConfigurationLessDependency(
                    group = group,
                    name = dependency.name,
                    version = version
                )
            )
        }

        listOf(TomlLine.newLine, line)
    }
}
