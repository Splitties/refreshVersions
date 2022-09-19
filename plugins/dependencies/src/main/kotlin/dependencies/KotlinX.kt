@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.kotlin.dsl.IsNotADependency

object KotlinX : DependencyGroup("org.jetbrains.kotlinx") {

    /**
     * Kotlin external declarations for using the Node.js API from Kotlin code targeting JavaScript.
     *
     * Kotlin/JS Overview: [kotl.in/js](https://kotl.in/js)
     *
     * [Change log](https://github.com/Kotlin/kotlinx-nodejs/blob/master/CHANGELOG.md)
     *
     * GitHub page: [Kotlin/kotlinx-nodejs](https://github.com/Kotlin/kotlinx-nodejs)
     */
    val nodeJs = module("kotlinx-nodejs")

    /**
     * Library support for Kotlin coroutines.
     *
     * Brings structured concurrency and reactive programming with `Flow`.
     *
     * [Coroutines Guide on Kotlin's website](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html)
     *
     * Kotlin coroutines on Android: [d.android.com/kotlin/coroutines](http://d.android.com/kotlin/coroutines)
     *
     * Talks by Roman Elizarov (co-author of kotlinx.coroutines):
     * - [Structured concurrency](https://www.youtube.com/watch?v=Mj5P47F6nJg)
     * - [Asynchronous Data Streams with Kotlin Flow](https://www.youtube.com/watch?v=tYcqn48SMT8)
     *
     * [Change log](https://github.com/Kotlin/kotlinx.coroutines/blob/master/CHANGES.md)
     *
     * GitHub page: [Kotlin/kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines)
     */
    val coroutines = Coroutines

    object Coroutines : DependencyGroup(group = group), IsNotADependency {

        val bom = module("kotlinx-coroutines-bom", isBom = true)

        val core = module("kotlinx-coroutines-core")

        val android = module("kotlinx-coroutines-android")
        val javaFx = module("kotlinx-coroutines-javafx")
        val swing = module("kotlinx-coroutines-swing")

        val playServices = module("kotlinx-coroutines-play-services")
        val jdk8 = module("kotlinx-coroutines-jdk8")
        val jdk9 = module("kotlinx-coroutines-jdk9")
        val slf4j = module("kotlinx-coroutines-slf4j")
        val guava = module("kotlinx-coroutines-guava")

        val reactive = module("kotlinx-coroutines-reactive")
        val reactor = module("kotlinx-coroutines-reactor")
        val rx2 = module("kotlinx-coroutines-rx2")
        val rx3 = module("kotlinx-coroutines-rx3")

        val debug = module("kotlinx-coroutines-debug")
        val test = module("kotlinx-coroutines-test")
    }

    /**
     * Kotlin multiplatform / multi-format serialization.
     *
     * [Page on Kotlin's website](https://kotlinlang.org/docs/reference/serialization.html)
     *
     * [Kotlin Serialization Guide](https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/serialization-guide.md)
     *
     * [Change log](https://github.com/Kotlin/kotlinx.serialization/blob/master/CHANGELOG.md)
     *
     * GitHub page: [Kotlin/kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization)
     */
    val serialization = Serialization

    object Serialization : IsNotADependency {

        val bom = module("kotlinx-serialization-bom", isBom = true)

        val core = module("kotlinx-serialization-core")

        val json = Json

        object Json : DependencyNotationAndGroup(group = group, name = "kotlinx-serialization-json") {
            val okio = module("kotlinx-serialization-json-okio")
        }

        val protobuf = module("kotlinx-serialization-protobuf")
        val cbor = module("kotlinx-serialization-cbor")
        val properties = module("kotlinx-serialization-properties")
        val hocon = module("kotlinx-serialization-hocon")
    }

    val collections = Collections

    object Collections : IsNotADependency {

        /**
         * Immutable persistent collections for Kotlin.
         *
         * [Library API proposal](https://github.com/Kotlin/kotlinx.collections.immutable/blob/master/proposal.md)
         *
         * [Change log](https://github.com/Kotlin/kotlinx.collections.immutable/blob/master/CHANGELOG.md)
         *
         * GitHub page: [Kotlin/kotlinx.collections.immutable](https://github.com/Kotlin/kotlinx.collections.immutable)
         */
        val immutable = module("kotlinx-collections-immutable")

        /**
         * Immutable persistent collections for Kotlin.
         *
         * [Library API proposal](https://github.com/Kotlin/kotlinx.collections.immutable/blob/master/proposal.md)
         *
         * [Change log](https://github.com/Kotlin/kotlinx.collections.immutable/blob/master/CHANGELOG.md)
         *
         * GitHub page: [Kotlin/kotlinx.collections.immutable](https://github.com/Kotlin/kotlinx.collections.immutable)
         */
        val immutableJvmOnly = module("kotlinx-collections-immutable-jvm")
    }

    /**
     * Kotlin DSL for HTML.
     *
     * [Wiki](https://github.com/kotlin/kotlinx.html/wiki)
     *
     * [GitHub releases](https://github.com/Kotlin/kotlinx.html/releases)
     *
     * GitHub page: [Kotlin/kotlinx.html](https://github.com/Kotlin/kotlinx.html)
     */
    val html = Html

    object Html : DependencyNotationAndGroup(group = group, name = "kotlinx-html")

    /**
     * Kotlin multiplatform I/O library. (Experimental as of 2020-09-14)
     *
     * [Change log](https://github.com/Kotlin/kotlinx-io/blob/master/CHANGELOG.md)
     *
     * GitHub page: [Kotlin/kotlinx-io](https://github.com/Kotlin/kotlinx-io)
     */
    val io = Io

    object Io : IsNotADependency {

        val jvm = module("kotlinx-io-jvm")
    }

    /**
     * Lightweight library allowing to introspect basic stuff about Kotlin symbols.
     *
     * _As of version 1.0, it only supports getting names of parameters and the nullability of
     * their types._
     *
     * [Documentation (of full reflection)](https://kotlinlang.org/docs/reference/reflection.html)
     *
     * [API reference (of full reflection)](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/)
     *
     * [Change log](https://github.com/Kotlin/kotlinx-nodejs/blob/master/CHANGELOG.md)
     *
     * GitHub page: [Kotlin/kotlinx.reflect.lite](https://github.com/Kotlin/kotlinx.reflect.lite)
     */
    val reflect = Reflect

    object Reflect : IsNotADependency {
        val lite = module("kotlinx.reflect.lite")
    }

    /**
     * Pure Kotlin implementation of a generic command-line parser.
     *
     * [Change log](https://github.com/Kotlin/kotlinx-cli/blob/master/CHANGELOG.md)
     *
     * GitHub page: [Kotlin/kotlinx-cli](https://github.com/Kotlin/kotlinx-cli)
     */
    val cli = module("kotlinx-cli")


    /**
     * A multiplatform Kotlin library for working with date and time.
     *
     * [Change log](https://github.com/Kotlin/kotlinx-datetime/blob/master/CHANGELOG.md)
     *
     * GitHub page: [Kotlin/kotlinx-datetime](https://github.com/Kotlin/kotlinx-datetime)
     */
    val datetime = module("kotlinx-datetime")

    /**
     * Kotlin DataFrame is a JVM Kotlin library for in-memory data manipulation.
     *
     * [Documentation](https://kotlin.github.io/dataframe/overview.html)
     *
     * [GitHub page](https://github.com/Kotlin/dataframe/)
     *
     */
    val dataframe = DataFrame

    object DataFrame: DependencyNotationAndGroup(
        group = "org.jetbrains.kotlinx",
        name = "dataframe",
        rawRules = """
        org.jetbrains.kotlinx:dataframe(-*)
                      ^^^^^^^.^^^^^^^^^
        """.trimIndent()
    ) {

        val dataframe = module("dataframe")
        val core  = module("dataframe-core")
        val excel = module("dataframe-excel")
        val arrow = module("dataframe-arrow")
    }

    /**
     * Multidimensional array library for Kotlin.
     *
     * [API reference (Dokka)](https://kotlin.github.io/multik/multik-api/)
     *
     * [GitHub releases](https://github.com/Kotlin/multik/releases)
     *
     * GitHub page: [Kotlin/multik](https://github.com/Kotlin/multik)
     */
    val multik = Multik

    object Multik :DependencyGroup(
        group = "org.jetbrains.kotlinx",
        rawRules = """
        org.jetbrains.kotlinx:multik(-*)
                      ^^^^^^^.^^^^^^
        """.trimIndent()
    ) {
        val api = module("multik-api")
        val default = module("multik-default")
        val jvm = module("multik-jvm")
        val native = module("multik-native")
    }

    /**
     * Lincheck is a framework for testing concurrent data structures for correctness.
     *
     * [GitHub releases](https://github.com/Kotlin/kotlinx-lincheck/releases)
     *
     * GitHub page: [kotlinx-lincheck](https://github.com/Kotlin/kotlinx-lincheck)
     */
    val lincheck = Lincheck

    object Lincheck :DependencyNotationAndGroup(
        group = "org.jetbrains.kotlinx",
        name = "lincheck",
        rawRules = """
        org.jetbrains.kotlinx:lincheck(-*)
                      ^^^^^^^.^^^^^^^^
        """.trimIndent()
    ) {
        val jvm = module("lincheck-jvm")
    }

    /**
     * KotlinDL is a high-level Deep Learning API.
     *
     * Talks by Zinoviev Alexey:
     * - [Deep Learning with KotlinDL](https://www.youtube.com/watch?v=jCFZc97_XQU)
     * - [Introduction to Deep Learning with KotlinDL](https://www.youtube.com/watch?v=ruUz8uMZUVw)
     *
     * [API reference (Dokka)](https://jetbrains.github.io/KotlinDL)
     *
     * [GitHub releases](https://github.com/JetBrains/KotlinDL/releases)
     *
     * [Change log](https://github.com/JetBrains/KotlinDL/blob/master/CHANGELOG.md)
     *
     * GitHub page: [JetBrains/KotlinDL](https://github.com/JetBrains/KotlinDL)
     */
    val deeplearning = DeepLearning

    object DeepLearning : DependencyGroup(
        group = "org.jetbrains.kotlinx",
        rawRules = """
        org.jetbrains.kotlinx:kotlin-deeplearning(-*)
                      ^^^^^^^.       ^^^^^^^^^^^^
        """.trimIndent()
    ) {
        val api = module("kotlin-deeplearning-api")
        val onnx = module("kotlin-deeplearning-onnx")
        val visualization = module("kotlin-deeplearning-visualization")
    }
}
