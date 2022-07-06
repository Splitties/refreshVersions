package de.fayard.refreshVersions.core.internal

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalDependency

@Suppress("EnumEntryName")
@InternalRefreshVersionsApi
enum class Case(
    val convert: (String) -> String
) {
    snake_case({ it }),
    `kebab-case`({
        it.map { c ->
            if (c in ".-_") '-' else c
        }.joinToString(separator = "")
    });
}

/**
 * We don't want to use meaningless generic libs like Libs.core
 *
 * Found many inspiration for bad libs here https://developer.android.com/jetpack/androidx/migrate
 * **/
@InternalRefreshVersionsApi
val MEANING_LESS_NAMES: MutableList<String> = mutableListOf(
    "common", "core", "testing", "runtime", "extensions",
    "compiler", "migration", "db", "rules", "runner", "monitor", "loader",
    "media", "print", "io", "collection", "gradle", "android"
)

@InternalRefreshVersionsApi
fun List<Library>.computeAliases(
    configured: List<String>,
    byDefault: List<String> = MEANING_LESS_NAMES
): List<String> {
    val groups = (configured + byDefault).filter { it.contains(".") }.toSet()
    val depsFromGroups = filter { it.group in groups }.map { it.module }
    val ambiguities = groupBy { it.module }.filter { it.value.size > 1 }.map { it.key }
    return (configured + byDefault + ambiguities + depsFromGroups - groups).distinct().sorted()
}

@InternalRefreshVersionsApi
fun escapeLibsKt(name: String): String {
    val escapedChars = listOf('-', '.', ':')
    return buildString {
        for (c in name) {
            append(if (c in escapedChars) '_' else c.toLowerCase())
        }
    }
}

@InternalRefreshVersionsApi
fun Project.findDependencies(): List<Library> {
    val allDependencies = mutableListOf<Library>()
    allprojects {
        (buildscript.configurations + configurations).flatMapTo(allDependencies) { configuration ->
            configuration.allDependencies
                .filterIsInstance<ExternalDependency>()
                .mapNotNull { dependency ->
                    Library(
                        group = dependency.group ?: return@mapNotNull null,
                        module = dependency.name,
                        version = dependency.version
                    )
                }
        }
    }
    return allDependencies.distinctBy { d -> d.groupModule() }
}


@InternalRefreshVersionsApi
data class Library(
    val group: String = "",
    val module: String = "",
    val version: String? = null
) {
    fun toDependency(): Dependency = ConfigurationLessDependency(
        group = group,
        name = name,
        version = version
    )

    val name: String get() = module
    fun groupModuleUnderscore() = "$group:$module:_"
    fun groupModule() = "$group:$module"

    @Suppress("LocalVariableName")
    fun versionName(mode: VersionMode, case: Case): String {
        val name_with_underscores = escapeLibsKt(
            when (mode) {
                VersionMode.MODULE -> module
                VersionMode.GROUP -> group
                VersionMode.GROUP_MODULE -> "${group}_$module"
            }
        )
        return case.convert(name_with_underscores)
    }

    override fun toString(): String = if (version == null) "$group:$name" else "$group:$name:$version"
}

@InternalRefreshVersionsApi
class Deps(
    val libraries: List<Library>,
    val names: Map<Library, String>
)


@InternalRefreshVersionsApi
enum class VersionMode {
    GROUP, GROUP_MODULE, MODULE
}

@InternalRefreshVersionsApi
fun List<Library>.checkModeAndNames(useFdqnByDefault: List<String>, case: Case): Deps {
    val dependencies = this

    val modes: Map<Library, VersionMode> =
        dependencies.associateWith { d ->
            when {
                d.module in useFdqnByDefault -> VersionMode.GROUP_MODULE
                escapeLibsKt(d.module) in useFdqnByDefault -> VersionMode.GROUP_MODULE
                else -> VersionMode.MODULE
            }
        }.toMutableMap()

    val versionNames = dependencies.associateWith { d ->
        val mode = modes.getValue(d)
        d.versionName(mode, case)
    }
    val sortedDependencies = dependencies.sortedBy { d: Library -> d.groupModule() }
    return Deps(sortedDependencies, versionNames)
}
