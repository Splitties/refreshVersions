@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import de.fayard.refreshVersions.core.DependencyNotation
import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.kotlin.dsl.IsNotADependency

/**
 * Typed Functional Programming in Kotlin.
 *
 * - [Official website here](https://arrow-kt.io/)
 * - GitHub page: [arrow-kt/arrow](https://github.com/arrow-kt/arrow)
 * - [GitHub Releases here](https://github.com/arrow-kt/arrow/releases)
 */
object Arrow : DependencyGroup(
    group = "io.arrow-kt",
    rawRules = """
        io.arrow-kt:arrow-*
                    ^^^^^
    """.trimIndent()
), IsNotADependency {

    val stack = module("arrow-stack", isBom = true)

    /**
     * Functional companion to Kotlin's Standard Library.
     *
     * [Documentation](https://arrow-kt.io/docs/core/)
     */
    val core = module("arrow-core")


    /**
     * Functional Effects Framework companion to KotlinX Coroutines.
     *
     * [Documentation](https://arrow-kt.io/docs/fx/)
     */
    val fx = Fx

    object Fx : IsNotADependency {

        val coroutines = module("arrow-fx-coroutines")

        val stm = module("arrow-fx-stm")
    }


    /**
     * Deep access and transformations over immutable data.
     *
     * [Documentation](https://arrow-kt.io/docs/optics/)
     */
    val optics = Optics

    object Optics : DependencyNotationAndGroup(
        group = group,
        name = "arrow-optics"
    ) {
        val kspPlugin = module("arrow-optics-ksp-plugin")
        val reflect = module("arrow-optics-reflect")
    }


    /**
     * Pre-, post-condition, and invariant checks for your Kotlin code.
     *
     * [Documentation](https://arrow-kt.io/docs/analysis/)
     *
     * _[Additional steps are required for Android projects](https://arrow-kt.io/docs/analysis/#%CE%BBrrow-analysis--android)_
     */
    val analysis = Analysis

    object Analysis : DependencyGroup(
        group = "io.arrow-kt.analysis.kotlin",
        rawRules = """
            io.arrow-kt.analysis.kotlin:io.arrow-kt.analysis.kotlin.*
            ^^^^^^^^^^^^^^^^^^^^^^^^^^^
        """.trimIndent(),
        usePlatformConstraints = false
    ), IsNotADependency {
        val gradlePlugin = module("io.arrow-kt.analysis.kotlin.gradle.plugin")
    }
}
