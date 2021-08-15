package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.ArtifactVersionKeyRule
import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
import org.gradle.kotlin.dsl.IsNotADependency

abstract class DependencyGroup(
    group: String,
    rawRule: String? = null,
    usePlatformConstraints: Boolean = false
) : IsNotADependency, AbstractDependencyGroup(
    group = group,
    rawRule = rawRule,
    usePlatformConstraints = usePlatformConstraints
)

@OptIn(PrivateForImplementation::class)
abstract class DependencyNotationAndGroup(
    group: String,
    name: String,
    rawRule: String? = null,
    usePlatformConstraints: Boolean = false
) : AbstractDependencyGroup(
    group = group,
    rawRule = rawRule,
    usePlatformConstraints = usePlatformConstraints
), DependencyNotation by object : DependencyNotationImpl(group = group, name = name) {
    override fun shouldUsePlatformConstraints(): Boolean = false
} {
    val artifactPrefix: String get() = withoutVersion()
}

@RequiresOptIn
private annotation class PrivateForImplementation

interface DependencyNotation : CharSequence {
    fun withVersionPlaceholder(): String
    fun withVersion(version: String): String
    fun withoutVersion(): String
    operator fun invoke(version: String?): String

    override fun toString(): String

    @PrivateForImplementation
    val externalImplementationGuard: Nothing
}

@OptIn(PrivateForImplementation::class)
private abstract class DependencyNotationImpl(
    group: String,
    name: String
) : DependencyNotation {

    protected abstract fun shouldUsePlatformConstraints(): Boolean

    override fun withVersionPlaceholder(): String = this(version = "_")
    override fun withVersion(version: String): String = this(version = version)
    override fun withoutVersion(): String = this(version = null)

    override operator fun invoke(version: String?): String = when (version) {
        null -> artifactPrefix
        else -> "$artifactPrefix:$version"
    }

    @PrivateForImplementation
    protected open val artifactPrefix = "$group:$name"

    private val backingString: String by lazy(LazyThreadSafetyMode.PUBLICATION) {
        artifactPrefix + if (shouldUsePlatformConstraints()) "" else ":_"
    }

    final override val length get() = backingString.length
    final override fun get(index: Int) = backingString[index]
    final override fun subSequence(
        startIndex: Int,
        endIndex: Int
    ) = backingString.subSequence(startIndex = startIndex, endIndex = endIndex)
    final override fun toString(): String = backingString

    @PrivateForImplementation
    override val externalImplementationGuard: Nothing get() = throw IllegalAccessException()
}


@OptIn(PrivateForImplementation::class)
sealed class AbstractDependencyGroup(
    val group: String,
    rawRule: String? = null,
    @InternalRefreshVersionsApi
    var usePlatformConstraints: Boolean = false
) {

    private val rule: ArtifactVersionKeyRule? = rawRule?.let {
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
        private val ALL = mutableListOf<AbstractDependencyGroup>()

        @InternalRefreshVersionsApi
        val ALL_RULES: List<ArtifactVersionKeyRule>
            get() = ALL.mapNotNull { it.rule }

        @InternalRefreshVersionsApi
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

    fun module(
        name: String,
        isBom: Boolean = false,
        usePlatformConstraints: Boolean? = if (isBom) false else null
    ): DependencyNotation {
        assert(name.trimStart() == name) { "module($name) has superfluous leading whitespace" }
        assert(name.trimEnd() == name) { "module($name) has superfluous trailing whitespace" }
        assert(name.contains(":").not()) { "module($name) is invalid" }
        return object : DependencyNotationImpl(
            group = group,
            name = name
        ) {

            override fun shouldUsePlatformConstraints(): Boolean {
                markDependencyNotationsUsage()
                return usePlatformConstraints ?: this@AbstractDependencyGroup.usePlatformConstraints
            }

            private fun markDependencyNotationsUsage() {
                if (isRunningTests || disableBomCheck) return

                val isGroupUsingPlatformConstraints = this@AbstractDependencyGroup.usePlatformConstraints

                if (isBom) {
                    if (isGroupUsingPlatformConstraints.not() &&
                        haveDependencyNotationsWithPlatformConstraintsBeenUsed
                    ) {
                        error(
                            "You are trying to use a BoM ($artifactPrefix), " +
                                "but dependency notations relying on it have been declared before! " +
                                "Declare the BoM first to fix this issue."
                        )
                    }
                    this@AbstractDependencyGroup.usePlatformConstraints = true
                } else if (isGroupUsingPlatformConstraints) {
                    haveDependencyNotationsWithPlatformConstraintsBeenUsed = true
                }
            }
        }
    }

    private var haveDependencyNotationsWithPlatformConstraintsBeenUsed = false
}
