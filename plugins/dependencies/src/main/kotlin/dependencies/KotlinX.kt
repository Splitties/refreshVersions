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
        const val coreJs = "$artifactPrefix-core-js:$version"

        @Deprecated("No longer published since version 1.3.9. Use core instead.")
        const val coreCommon = "$artifactPrefix-core-common:$version"
        @Deprecated("No longer published since version 1.3.9. Use core instead.")
        const val coreNative = "$artifactPrefix-core-native:$version"

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
        private const val artifactPrefix = "$artifactBase-serialization"

        const val core = "$artifactPrefix-core:_"
        const val protobuf = "$artifactPrefix-protobuf:_"
        const val cbor = "$artifactPrefix-cbor:_"
        const val properties = "$artifactPrefix-properties:_"
        //TODO: Add hocon artifact once documented.

        //region Pre v1.0.0 deprecated artifacts.
        @Deprecated("Use core instead and upgrade to version 1.0.0-RC or newer")
        const val runtime = "$artifactPrefix-runtime:_"

        @Deprecated("No longer needed")
        const val runtimeJs = "$artifactPrefix-runtime-js:_"

        @Deprecated("No longer needed")
        const val runtimeCommon = "$artifactPrefix-runtime-common:_"

        @Deprecated("No longer needed")
        const val runtimeNative = "$artifactPrefix-runtime-native:_"
        //endregion
    }

    object Collections {
        private const val immutableArtifactPrefix = "$artifactBase-collections-immutable"

        const val immutable = "$immutableArtifactPrefix:_"

        const val immutableJvmOnly = "$immutableArtifactPrefix-jvm:_"
    }
}
