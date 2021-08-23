/*
 * Copyright 2019 Louis Cognault Ayeva Derman. Use of this source code is governed by the Apache 2.0 license.
 */

@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import de.fayard.refreshVersions.core.DependencyNotation
import org.gradle.api.Incubating

@Incubating
object JakeWharton {

    /**
     * A logger with a small, extensible API which provides utility on top of Android's normal Log class.
     *
     * GitHub page: [JakeWharton/timber](https://github.com/JakeWharton/timber)
     */
    val timber = DependencyNotation("com.jakewharton.timber", "timber")

    /**
     * A Kotlin DSL and Java/Kotlin builder API for constructing HTML-like tables which can be rendered to text.
     *
     * GitHub page: [JakeWharton/picnic](https://github.com/JakeWharton/picnic)
     */
    val picnic = DependencyNotation("com.jakewharton.picnic", "picnic")

    /**
     * A Kotlin compiler plugin which brings Kotlin/JS's `unsafeCast` to Kotlin/JVM.
     *
     * GitHub page: [JakeWharton/confundus](https://github.com/JakeWharton/confundus)
     */
    val confundusGradlePlugin = DependencyNotation("com.jakewharton.confundus", "confundus-gradle")

    /**
     * A time-traveling bytecode rewriter which adds future APIs to `android.jar` which can be desugared to all API levels by D8 and R8.
     *
     * GitHub page: [JakeWharton/wormhole](https://github.com/JakeWharton/wormhole)
     */
    val wormholeGradlePlugin = DependencyNotation("com.jakewharton.wormhole", "wormhole-gradle")

    /**
     * A type-safe HTTP client for Android and the JVM
     *
     * GitHub page: [square/retrofit](https://github.com/square/retrofit)
     */
    val retrofit2 = Retrofit2

    object Retrofit2 : DependencyGroup("com.jakewharton.retrofit") {

        val converter = Converter

        object Converter  {

            /**
             * A Retrofit 2 `Converter.Factory` for [Kotlin serialization](https://github.com/Kotlin/kotlinx.serialization/).
             *
             * GitHub page: [JakeWharton/retrofit2-kotlinx-serialization-converter](https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter)
             */
            val kotlinxSerialization = module("retrofit2-kotlinx-serialization-converter")
        }
    }

    val moshi = Moshi

    object Moshi: DependencyGroup("com.jakewharton.moshi") {

        /**
         * Shimo is a `JsonAdapter.Factory` for [Square.moshi] which randomizes the order of keys
         * when serializing objects to JSON and when deserializing objects from JSON.
         *
         * GitHub page: [JakeWharton/shimo](https://github.com/JakeWharton/shimo)
         */
        val shimo = module("shimo")
    }
}
