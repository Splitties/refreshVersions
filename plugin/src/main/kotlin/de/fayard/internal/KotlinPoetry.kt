package de.fayard.internal


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

fun Dependency.newerVersion(): String? =
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
    val androidx = dependencies.map { it.group }.distinct().filter { it.startsWith("androidx.") }
    val resolvedUseFqdn = PluginConfig.computeUseFqdnFor(dependencies, useFdqn + androidx, PluginConfig.MEANING_LESS_NAMES)
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
