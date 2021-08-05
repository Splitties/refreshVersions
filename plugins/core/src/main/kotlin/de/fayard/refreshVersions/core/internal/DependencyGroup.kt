package de.fayard.refreshVersions.core.internal

import org.gradle.kotlin.dsl.IsNotADependency
import kotlin.reflect.KProperty

@InternalRefreshVersionsApi
open class DependencyGroup(
    val group: String,
    rawRule: String? = null,
    var usePlatformConstraints: Boolean = false
) : IsNotADependency {

    val rule: ArtifactVersionKeyRule? = rawRule?.let {
        val lines = it.lines()
        assert(lines.size == 2) {
            "2 lines were expected, but ${lines.size} were found: $it"
        }
        ArtifactVersionKeyRule(
            artifactPattern = lines.first(),
            versionKeyPattern = lines.last()
        )
    }


    companion object {
        private val ALL = mutableListOf<DependencyGroup>()
        val ALL_RULES: List<ArtifactVersionKeyRule>
            get() = ALL.mapNotNull { it.rule }
    }

    init {
        assert(group.isNotBlank()) { "Group shall not be blank" }
        ALL.add(this)
    }

    fun module(module: String): Module {
        assert(module.trimStart() == module) { "module=[$module] has superfluous leading whitespace" }
        assert(module.trimEnd() == module) { "module=[$module] has superfluous trailing whitespace" }
        assert(module.contains(":").not()) { "module=[$module] is invalid" }
        return Module("$group:$module" + if (usePlatformConstraints) "" else ":_")
    }

    @Suppress("nothing_to_inline") // Must be inline for Kotlin 1.4 to optimize unused params.
    inline operator fun Module.getValue(thisRef: Any?, property: KProperty<*>): String = name

    inner class Module internal constructor(val name: String)
}
