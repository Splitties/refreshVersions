@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import org.gradle.kotlin.dsl.IsNotADependency

object Touchlab : IsNotADependency {

    /**
     * Stately is a state utility library to facilitate state management in Kotlin Multiplatform.
     *
     * [GitHub releases](https://github.com/touchlab/Stately/releases)
     *
     * GitHub repo: [touchlab/Stately](https://github.com/touchlab/Stately)
     */
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

