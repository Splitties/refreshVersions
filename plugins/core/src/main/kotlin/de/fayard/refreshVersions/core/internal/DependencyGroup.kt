package de.fayard.refreshVersions.core.internal

import org.gradle.kotlin.dsl.IsNotADependency

@InternalRefreshVersionsApi
open class DependencyGroup(
    val group: String,
    val rule: ArtifactVersionKeyRule? = null,
    var usePlatformConstraints: Boolean = false
) : IsNotADependency {
    companion object {
        private val ALL = mutableListOf<DependencyGroup>()
        val ALL_RULES: List<ArtifactVersionKeyRule>
            get() = ALL.mapNotNull { it.rule }
    }

    init {
        require(group.isNotBlank()) { "Invalid group=[$group]" }
        ALL.add(this)
    }

    fun module(module: String): String {
        assert(module.trim() == module) { "module=[$module] is not trimmed properly"}
        assert(module.contains(":").not()) { "module=[$module] is invalid"}
        return "$group:$module" + if (usePlatformConstraints) "" else ":_"
    }
}
