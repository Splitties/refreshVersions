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
        assert(group.isNotBlank()) { "Group shall not be blank" }
        ALL.add(this)
    }

    fun module(module: String): String {
        assert(module.trimStart() == module) { "module=[$module] has superfluous leading whitespace" }
        assert(module.trimEnd() == module) { "module=[$module] has superfluous trailing whitespace" }
        assert(module.contains(":").not()) { "module=[$module] is invalid" }
        return "$group:$module" + if (usePlatformConstraints) "" else ":_"
    }
}
