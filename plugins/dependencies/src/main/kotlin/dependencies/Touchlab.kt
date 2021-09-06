@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.api.Incubating

/**
 * Multithreaded Kotlin Multiplatform Utilities
 *
 * - GitHub page: [Stately](https://github.com/touchlab/Stately)
 * - [GitHub Releases here](https://github.com/touchlab/Stately/releases)
 */
@Incubating
object Touchlab {
    val stately = Stately

    object Stately : DependencyGroup(
        group = "co.touchlab",
        rawRule = """
        co.touchlab:stately-*
                    ^^^^^^^
    """.trimIndent()
    ) {
        val common = module("stately-common")
        val concurrency = module("stately-concurrency")
        val isolate = module("stately-isolate")
        val isoCollections = module("stately-iso-collections")
    }
}

