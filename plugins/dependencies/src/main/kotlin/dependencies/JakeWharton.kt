/*
 * Copyright 2019 Louis Cognault Ayeva Derman. Use of this source code is governed by the Apache 2.0 license.
 */

@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.api.Incubating

@Incubating
object JakeWharton {

    /**
     * A logger with a small, extensible API which provides utility on top of Android's normal Log class.
     *
     * GitHub page: [JakeWharton/timber](https://github.com/JakeWharton/timber)
     */
    const val timber = "com.jakewharton.timber:timber:_"

    val retrofit2 = Retrofit2

    /**
     * A Kotlin DSL and Java/Kotlin builder API for constructing HTML-like tables which can be rendered to text.
     *
     * GitHub page: [JakeWharton/picnic](https://github.com/JakeWharton/picnic)
     */
    const val picnic = "com.jakewharton.picnic:picnic:_"

    object Retrofit2 {
        private const val artifactPrefix = "com.jakewharton.retrofit:retrofit2"

        val converter = Converter

        object Converter {

            /**
             * A Retrofit 2 `Converter.Factory` for [Kotlin serialization](https://github.com/Kotlin/kotlinx.serialization/).
             *
             * GitHub page: [JakeWharton/retrofit2-kotlinx-serialization-converter](https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter)
             */
            const val kotlinxSerialization = "$artifactPrefix-kotlinx-serialization-converter:_"
        }
    }
}
