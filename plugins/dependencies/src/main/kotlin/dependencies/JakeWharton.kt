/*
 * Copyright 2019 Louis Cognault Ayeva Derman. Use of this source code is governed by the Apache 2.0 license.
 */

@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.api.Incubating

@Incubating
object JakeWharton {
    const val timber = "com.jakewharton.timber:timber:_"

    val retrofit2 = Retrofit2

    object Retrofit2 {
        private const val artifactPrefix = "com.jakewharton.retrofit:retrofit2"

        val converter = Converter

        object Converter {

            /**
             * GitHub page: [JakeWharton/retrofit2-kotlinx-serialization-converter](https://github.com/JakeWharton/retrofit2-kotlinx-serialization-converter)
             */
            const val kotlinxSerialization = "$artifactPrefix-kotlinx-serialization-converter:_"
        }
    }
}
