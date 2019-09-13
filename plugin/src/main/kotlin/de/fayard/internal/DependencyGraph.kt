package de.fayard.internal

import com.squareup.kotlinpoet.FileSpec

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
    var versionName: String = "",
    val available: AvailableDependency? = null
)

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

    companion object {
        val NEW_FILE = SingleModeResult(0, 0, "")
        val BLOC_NOT_FOUND = SingleModeResult(-1, -1, PluginConfig.DEFAULT_INDENT)
    }
}

