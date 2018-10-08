package jmfayard.github.io

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

