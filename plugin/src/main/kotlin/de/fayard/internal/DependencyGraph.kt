package de.fayard.internal

data class DependencyGraph(
    val gradle: GradleConfig,
    val current: Dependencies,
    val exceeded: Dependencies,
    val outdated: Dependencies,
    val unresolved: Dependencies,
    val count: Int = 0
)

data class Dependencies(
    val dependencies: List<Dependency> = emptyList(),
    val count: Int = 0
) : List<Dependency> by dependencies

data class Dependency(
    val group: String = "",
    val version: String = "",
    val reason: String? = "",
    var latest: String? = "",
    val projectUrl: String? = "",
    val name: String = "",
    var escapedName: String = "",
    var mode: VersionMode = VersionMode.MODULE,
    val available: AvailableDependency? = null
) {
    fun groupOrVirtualGroup(): String = virtualGroup(this) ?: group

    val module: String get() = name
    val versionName: String
        get() = PluginConfig.versionKtFor(this)

    val versionProperty: String
        get() = PluginConfig.versionPropertyFor(this)

    fun maybeUpdate(update: Boolean): Dependency {
        val newerVersion = newerVersion()
        return when {
            update.not() -> this
            newerVersion == null -> this
            else -> this.copy(available = null, version = newerVersion)
        }
    }

    companion object {
        fun virtualGroup(dependency: Dependency, withVersion: Boolean = false): String? {
            // TODO: use DEFAULT_MAPPING instead of ALIGN_VERSION_GROUPS
            val virtualGroup = PluginConfig.ALIGN_VERSION_GROUPS.firstOrNull { "${dependency.group}.${dependency.module}".startsWith(it) }
            return when {
                virtualGroup == null -> null
                withVersion -> "version.$virtualGroup"
                else -> virtualGroup
            }
        }
    }
}

enum class VersionMode {
    GROUP, GROUP_MODULE, MODULE
}

data class GradleConfig(
    val current: GradleVersion,
    val nightly: GradleVersion,
    val enabled: Boolean = false,
    val releaseCandidate: GradleVersion,
    val running: GradleVersion
)

data class GradleVersion(
    val version: String = "",
    val reason: String = "",
    val isUpdateAvailable: Boolean = false,
    val isFailure: Boolean = false
)

data class AvailableDependency(
    val release: String? = "",
    val milestone: String? = "",
    val integration: String? = ""
)

