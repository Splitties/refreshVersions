@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.api.Incubating

@Incubating
object KotlinX {
    val coroutines = Coroutines
    val serialization = Serialization
    val collections = Collections

    private const val artifactBase = "org.jetbrains.kotlinx:kotlinx"

    object Coroutines {
        private const val version = "_"
        private const val artifactPrefix = "$artifactBase-coroutines"

        const val core = "$artifactPrefix-core:$version"
        const val coreCommon = "$artifactPrefix-core-common:$version"
        const val coreNative = "$artifactPrefix-core-native:$version"
        const val coreJs = "$artifactPrefix-core-js:$version"

        const val android = "$artifactPrefix-android:$version"
        const val javaFx = "$artifactPrefix-javafx:$version"
        const val swing = "$artifactPrefix-swing:$version"

        const val playServices = "$artifactPrefix-play-services:$version"
        const val jdk8 = "$artifactPrefix-jdk8:$version"
        const val slf4j = "$artifactPrefix-slf4j:$version"
        const val guava = "$artifactPrefix-guava:$version"

        const val reactive = "$artifactPrefix-reactive:$version"
        const val reactor = "$artifactPrefix-reactor:$version"
        const val rx2 = "$artifactPrefix-rx2:$version"

        const val debug = "$artifactPrefix-debug:$version"
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

    object Collections {
        private const val immutableArtifactPrefix = "$artifactBase-collections-immutable"

        const val immutable = "$immutableArtifactPrefix:_"

        const val immutableJvmOnly = "$immutableArtifactPrefix-jvm:_"
    }
}
