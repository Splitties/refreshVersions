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

        var disableBomCheck: Boolean = false
        private val isRunningTests: Boolean by lazy {
            try {
                Class.forName("org.junit.jupiter.api.AssertEquals")
                true
            } catch (e: ClassNotFoundException) {
                false
            }
        }
    }

    init {
        assert(group.isNotBlank()) { "Group shall not be blank" }
        ALL.add(this)
    }

    fun module(module: String, isBom: Boolean = false): Module {
        assert(module.trimStart() == module) { "module=[$module] has superfluous leading whitespace" }
        assert(module.trimEnd() == module) { "module=[$module] has superfluous trailing whitespace" }
        assert(module.contains(":").not()) { "module=[$module] is invalid" }
        return Module(
            name = "$group:$module" + if (usePlatformConstraints && isBom.not()) "" else ":_",
            isBom = isBom
        )
    }

    private var haveDependencyNotationsBeenUsed = false

    operator fun Module.getValue(thisRef: Any?, property: KProperty<*>): String {
        markDependencyNotationsUsage()
        return name
    }

    inner class Module internal constructor(
        val name: String,
        val isBom: Boolean
    ) {
        @PublishedApi
        internal fun markDependencyNotationsUsage() {
            if (!isRunningTests && !disableBomCheck) {
                if (isBom && usePlatformConstraints.not() && haveDependencyNotationsBeenUsed) {
                    error("You are trying to use a BoM ($name), but dependency notations relying on it have been declared before! Declare the BoM first to fix this issue.")
                }
            }
            if (isBom) usePlatformConstraints = true
            haveDependencyNotationsBeenUsed = true
        }
    }
}
