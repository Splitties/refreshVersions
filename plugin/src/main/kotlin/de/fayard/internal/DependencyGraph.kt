package de.fayard.internal

import com.squareup.kotlinpoet.FileSpec
import de.fayard.VersionsOnlyMode

data class KotlinPoetry(
    val Libs: FileSpec,
    val Versions: FileSpec
)



data class DependencyGraph(
    val gradle: GradleConfig,
    val current: Dependencies,
    val exceeded: Dependencies,
    val outdated: Dependencies,
    val unresolved: Dependencies,
    val count: Int = 0
)

fun Dependency.gradleNotation() = "$group:$name:$version"

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
}

enum class VersionMode {
    MODULE, GROUP, GROUP_MODULE
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


data class SingleModeResult(
    val startOfBlock: Int,
    val endOfBlock: Int,
    val indentation: String
) {
    fun isBlockNotFound(): Boolean {
        return startOfBlock == -1 && endOfBlock == -1
    }

    fun isNewFile() = startOfBlock == 0 && endOfBlock == 0

    companion object {
        fun blockNotFound(versionMode: VersionsOnlyMode) = SingleModeResult(-1, -1, versionMode.defaultIndent)
        fun newFile(versionMode: VersionsOnlyMode) = SingleModeResult(0, 0, versionMode.defaultIndent)
    }
}

