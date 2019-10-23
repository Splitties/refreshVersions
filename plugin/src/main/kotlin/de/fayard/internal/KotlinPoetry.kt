package de.fayard.internal

import de.fayard.OrderBy
import de.fayard.OrderBy.GROUP_AND_ALPHABETICAL
import de.fayard.OrderBy.GROUP_AND_LENGTH


// https://github.com/jmfayard/buildSrcVersions/issues/65
fun List<Dependency>.sortedBeautifullyBy(orderBy: OrderBy, selection: (Dependency) -> String?) : List<Dependency> {
    val unsorted = this.filterNot { selection(it) == null }
        .sortedBy { selection(it)!! }
    return when(orderBy) {
        GROUP_AND_LENGTH -> unsorted.sortedByDescending { selection(it)!!.length }.sortedBy { it.mode }
        GROUP_AND_ALPHABETICAL -> unsorted.sortedBy { it.mode }
    }
}

fun Dependency.versionInformation(): String {
    val newerVersion = newerVersion()
    val comment = when {
        version == "none" -> "// No version. See buildSrcVersions#23"
        newerVersion == null -> ""
        else -> """ // available: "$newerVersion""""
    }
    val addNewLine = comment.length + versionName.length + version.length > 70

    return if (addNewLine) "\n$comment" else comment
}

fun Dependency.newerVersion(): String?  =
    when {
        available == null -> null
        available.release.isNullOrBlank().not() -> available.release
        available.milestone.isNullOrBlank().not() -> available.milestone
        available.integration.isNullOrBlank().not() -> available.integration
        else -> null
    }?.trim()


fun parseGraph(
    graph: DependencyGraph,
    useFdqn: List<String>
): List<Dependency> {
    val dependencies: List<Dependency> = graph.current + graph.exceeded + graph.outdated + graph.unresolved
    val resolvedUseFqdn = PluginConfig.computeUseFqdnFor(dependencies, useFdqn, PluginConfig.MEANING_LESS_NAMES)
    return dependencies.checkModeAndNames(resolvedUseFqdn).findCommonVersions()
}

fun List<Dependency>.checkModeAndNames(useFdqnByDefault: List<String>): List<Dependency> {
    for (d: Dependency in this) {
        d.mode = when {
            d.name in useFdqnByDefault -> VersionMode.GROUP_MODULE
            PluginConfig.escapeVersionsKt(d.name) in useFdqnByDefault -> VersionMode.GROUP_MODULE
            else -> VersionMode.MODULE
        }
        d.escapedName = PluginConfig.escapeVersionsKt(
            when (d.mode) {
                VersionMode.MODULE -> d.name
                VersionMode.GROUP -> d.groupOrVirtualGroup()
                VersionMode.GROUP_MODULE -> "${d.group}_${d.name}"
            }
        )
    }
    return this
}


fun List<Dependency>.orderDependencies(): List<Dependency> {
    return this.sortedBy { it.gradleNotation() }
}


fun List<Dependency>.findCommonVersions(): List<Dependency> {
    val map = groupBy { d: Dependency -> d.groupOrVirtualGroup() }
    for (deps in map.values) {
        val sameVersions = deps.map { it.version }.distinct().size == 1
        val hasVirtualGroup = deps.any { it.groupOrVirtualGroup() != it.group }
        if (sameVersions && (hasVirtualGroup || deps.size > 1)) {
            deps.forEach { d -> d.mode = VersionMode.GROUP }
        }
    }
    return this
}
