package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId

@InternalRefreshVersionsApi
data class DependencyMapping(
    val moduleId: ModuleId.Maven,
    val constantName: String
) {

    constructor(
        group: String,
        artifact: String,
        constantName: String
    ) : this(
        moduleId = ModuleId.Maven(group, artifact),
        constantName = constantName
    )

    val group get() = moduleId.group
    val artifact get() = moduleId.name

    companion object {
        fun fromLine(line: String): DependencyMapping? {
            if (line.isEmpty()) return null
            val (key, constantName) = line.split("=").takeIf { it.size == 2 } ?: return null
            val (group, artifact) = key.split("..").takeIf { it.size == 2 } ?: return null
            return DependencyMapping(group, artifact, constantName)
        }
    }

    override fun toString(): String = string

    private val string by lazy(LazyThreadSafetyMode.NONE) { "$group..$artifact=$constantName" }
}

@InternalRefreshVersionsApi
fun List<DependencyMapping>.associateShortestByMavenCoordinate(): Map<ModuleId.Maven, String> {
    return this.sortedByDescending { mapping ->
        mapping.constantName.length
    }.associate { mapping ->
        mapping.moduleId to mapping.constantName
    }
}
