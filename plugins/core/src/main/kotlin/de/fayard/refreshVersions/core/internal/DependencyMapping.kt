package de.fayard.refreshVersions.core.internal

@InternalRefreshVersionsApi
data class DependencyMapping(
    val group: String,
    val artifact: String,
    val constantName: String
) {
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
