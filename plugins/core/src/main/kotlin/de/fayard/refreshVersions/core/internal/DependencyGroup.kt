package de.fayard.refreshVersions.core.internal

import org.gradle.kotlin.dsl.IsNotADependency

@InternalRefreshVersionsApi
open class DependencyGroup(
    val group: String,
    val rule: ArtifactVersionKeyRule? = null,
    var usePlatformConstraints: Boolean = false
) : IsNotADependency {
    companion object {
        val ALL = mutableListOf<DependencyGroup>()
        val ALL_RULES: List<ArtifactVersionKeyRule>
            get() = ALL.mapNotNull { it.rule }
    }

    init {
        ALL.add(this)
    }

    fun module(module: String): String {
        assert(module.trim() == module)
        assert(module.contains(":").not())
        return "$group:$module" + if (usePlatformConstraints) "" else ":_"
    }
}
