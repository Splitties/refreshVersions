@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

@Incubating
object Kotlin {
    /**
     * Kotlin Standard Library
     *
     * [API reference](https://kotlinlang.org/api/latest/jvm/stdlib/)
     */
    val stdlib = Stdlib

    object Stdlib : DependencyNotationAndGroup(group = "org.jetbrains.kotlin", name = "kotlin-stdlib") {
        @JvmField val jdk7 = "$artifactPrefix-jdk7:_"
        @JvmField val jdk8 = "$artifactPrefix-jdk8:_"
        @JvmField val js = "$artifactPrefix-js:_"
        @JvmField val common = "$artifactPrefix-common:_"
    }

    /**
     * The `kotlin.test` library provides annotations to mark test functions,
     * and a set of utility functions for performing assertions in tests,
     * independently of the test framework being used.
     *
     * [Documentation and API reference](https://kotlinlang.org/api/latest/kotlin.test/)
     */
    val test = Test

    object Test : IsNotADependency {
        private const val artifactPrefix = "org.jetbrains.kotlin:kotlin-test"

        const val annotationsCommon = "$artifactPrefix-annotations-common:_"
        const val common = "$artifactPrefix-common:_"
        const val js = "$artifactPrefix-js:_"
        const val jsRunner = "$artifactPrefix-js-runner:_"

        const val junit = "$artifactPrefix-junit:_"
        const val junit5 = "$artifactPrefix-junit5:_"
        const val testng = "$artifactPrefix-testng:_"
    }
}
