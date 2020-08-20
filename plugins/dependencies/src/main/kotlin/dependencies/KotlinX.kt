@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.api.Incubating

@Incubating
object KotlinX {
    val coroutines = Coroutines
    val serialization = Serialization
    val collections = Collections
    val html = Html
    val io = Io

    private const val artifactBase = "org.jetbrains.kotlinx:kotlinx"

    const val nodeJs = "$artifactBase-nodejs:_"

    object Coroutines {
        private const val artifactPrefix = "$artifactBase-coroutines"

        const val core = "$artifactPrefix-core:_"
        const val coreJs = "$artifactPrefix-core-js:_"

        @Deprecated("No longer published since version 1.3.9. Use core instead.")
        const val coreCommon = "$artifactPrefix-core-common:_"
        @Deprecated("No longer published since version 1.3.9. Use core instead.")
        const val coreNative = "$artifactPrefix-core-native:_"

        const val android = "$artifactPrefix-android:_"
        const val javaFx = "$artifactPrefix-javafx:_"
        const val swing = "$artifactPrefix-swing:_"

        const val playServices = "$artifactPrefix-play-services:_"
        const val jdk8 = "$artifactPrefix-jdk8:_"
        const val jdk9 = "$artifactPrefix-jdk9:_"
        const val slf4j = "$artifactPrefix-slf4j:_"
        const val guava = "$artifactPrefix-guava:_"

        const val reactive = "$artifactPrefix-reactive:_"
        const val reactor = "$artifactPrefix-reactor:_"
        const val rx2 = "$artifactPrefix-rx2:_"
        const val rx3 = "$artifactPrefix-rx3:_"

        const val debug = "$artifactPrefix-debug:_"
        const val test = "$artifactPrefix-test:_"
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

    object Html {
        private const val artifactPrefix = "$artifactBase-html"

        const val jvm = "$artifactPrefix-jvm:_"
        const val js = "$artifactPrefix-js:_"
    }

    object Io {
        private const val artifactPrefix = "$artifactBase-io"

        const val jvm = "$artifactPrefix-jvm:_"
    }
}
