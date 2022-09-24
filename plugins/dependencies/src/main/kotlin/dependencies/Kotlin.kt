@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import de.fayard.refreshVersions.core.DependencyNotationAndGroup

object Kotlin : DependencyGroup(group = "org.jetbrains.kotlin") {
    /**
     * Kotlin Standard Library
     *
     * [API reference](https://kotlinlang.org/api/latest/jvm/stdlib/)
     */
    val stdlib = Stdlib

    object Stdlib : DependencyNotationAndGroup(group = group, name = "kotlin-stdlib") {
        val jdk7 = module("kotlin-stdlib-jdk7")
        val jdk8 = module("kotlin-stdlib-jdk8")
        val js = module("kotlin-stdlib-js")
        val common = module("kotlin-stdlib-common")
    }

    /**
     * The `kotlin.test` library provides annotations to mark test functions,
     * and a set of utility functions for performing assertions in tests,
     * independently of the test framework being used.
     *
     * [Documentation and API reference](https://kotlinlang.org/api/latest/kotlin.test/)
     */
    val test = Test

    object Test : DependencyNotationAndGroup(group = group, name = "kotlin-test") {

        val annotationsCommon = module("kotlin-test-annotations-common")
        val common = module("kotlin-test-common")
        val js = module("kotlin-test-js")
        val jsRunner = module("kotlin-test-js-runner")

        val junit = module("kotlin-test-junit")
        val junit5 = module("kotlin-test-junit5")
        val testng = module("kotlin-test-testng")
    }

    /** Kotlin Script Runtime */
    val scriptRuntime = module("kotlin-script-runtime")
}
