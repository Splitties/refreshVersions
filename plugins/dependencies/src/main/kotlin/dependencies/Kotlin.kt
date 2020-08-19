@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import dependencies.DependencyNotationAndGroup
import org.gradle.api.Incubating

@Incubating
object Kotlin {

    val stdlib = Stdlib
    val test = Test

    object Stdlib : DependencyNotationAndGroup(group = "org.jetbrains.kotlin", name = "kotlin-stdlib") {
        @JvmField val jdk7 = "$artifactPrefix-jdk7:_"
        @JvmField val jdk8 = "$artifactPrefix-jdk8:_"
        @JvmField val js = "$artifactPrefix-js:_"
        @JvmField val common = "$artifactPrefix-common:_"
    }

    object Test {
        const val annotationsCommon = "org.jetbrains.kotlin:kotlin-test-annotations-common:_"
        const val common = "org.jetbrains.kotlin:kotlin-test-common:_"
        const val js = "org.jetbrains.kotlin:kotlin-test-js:_"
        const val jsRunner = "org.jetbrains.kotlin:kotlin-test-js-runner:_"

        const val junit = "org.jetbrains.kotlin:kotlin-test-junit:_"
        const val junit5 = "org.jetbrains.kotlin:kotlin-test-junit5:_"
        const val testng = "org.jetbrains.kotlin:kotlin-test-testng:_"
    }
}
