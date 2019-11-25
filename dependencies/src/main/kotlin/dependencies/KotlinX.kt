@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.api.Incubating

@Incubating
object KotlinX {
    val coroutines = Coroutines
    val serialization = Serialization

    private const val artifactBase = "org.jetbrains.kotlinx:kotlinx"

    object Coroutines {
        private const val version = "_"
        private const val artifactPrefix = "$artifactBase-coroutines"

        const val core = "$artifactPrefix-core:$version"
        const val coreCommon = "$artifactPrefix-core-common:$version"
        const val coreNative = "$artifactPrefix-core-native:$version"
        const val coreJs = "$artifactPrefix-core-js:$version"
        const val android = "$artifactPrefix-android:$version"
        const val playServices = "$artifactPrefix-play-services:$version"
        const val test = "$artifactPrefix-test:$version"
    }

    object Serialization {
        private const val version = "_"
        private const val artifactPrefix = "$artifactBase-serialization"

        const val runtime = "$artifactPrefix-runtime:$version"
        const val runtimeJs = "$artifactPrefix-runtime-js:$version"
        const val runtimeCommon = "$artifactPrefix-runtime-common:$version"
        const val runtimeNative = "$artifactPrefix-runtime-native:$version"
    }
}
