@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

@Incubating
object KotlinX {
    /**
     * kotlin/kotlinx.coroutines - Asynchronous programming and more
     *
     * - [Coroutines Guide](https://kotlinlang.org/docs/reference/coroutines/coroutines-guide.html)
     * - [GitHub kotlin/kotlinx.coroutines](https://github.com/Kotlin/kotlinx.coroutines)
     * - [Changelog](https://github.com/Kotlin/kotlinx.coroutines/releases)
     * - [Kotlin coroutines on Android](https://developer.android.com/kotlin/coroutines)
     */
    val coroutines = Coroutines

    /**
     * Kotlin/kotlinx.serialization : Kotlin multiplatform / multi-format serialization
     *
     * - [GitHub](https://github.com/Kotlin/kotlinx.serialization)
     * - [Kotlin Serialization Guide](https://github.com/Kotlin/kotlinx.serialization/blob/master/docs/serialization-guide.md)
     * - [CHANGELOG](https://github.com/Kotlin/kotlinx.serialization/blob/master/CHANGELOG.md)
     */
    val serialization = Serialization

    /**
     * Kotlin/kotlinx.collections.immutables - Immutable persistent collections for Kotlin
     *
     * - [GitHub](https://github.com/Kotlin/kotlinx.collections.immutable)
     * - [Changelog](https://github.com/Kotlin/kotlinx.collections.immutable/blob/master/CHANGELOG.md)
     * - [Library API proposal](https://github.com/Kotlin/kotlinx.collections.immutable/blob/master/proposal.md)
     */
    val collections = Collections

    /**
     * Kotlin/kotlinx.html Kotlin DSL for HTML
     *
     * - [GitHub](https://github.com/Kotlin/kotlinx.html)
     * - [Wiki](https://github.com/kotlin/kotlinx.html/wiki)
     * - [releases](https://github.com/Kotlin/kotlinx.html/releases)
     */
    val html = Html

    /**
     * Kotlin/kotlinx-io : Kotlin multiplatform I/O library
     *
     * - [GitHub](https://github.com/Kotlin/kotlinx-io)
     * - [Changelog](https://github.com/Kotlin/kotlinx-io/blob/master/CHANGELOG.md)
     */
    val io = Io

    private const val artifactBase = "org.jetbrains.kotlinx:kotlinx"

    /**
     * Kotlin/kotlinx-nodejs : Kotlin external declarations for using the Node.js API from Kotlin code targeting JavaScript
     *
     * - [GitHub](https://github.com/Kotlin/kotlinx-nodejs)
     * - [Kotlin/JS Overview](https://kotlinlang.org/docs/reference/js-overview.html)
     */
    const val nodeJs = "$artifactBase-nodejs:_"

    /**
     * Kotlin/kotlinx.reflect.lite - Lightweight library allowing to introspect basic stuff about Kotlin symbols
     *
     * - [GitHub](https://github.com/Kotlin/kotlinx.reflect.lite)
     * - https://kotlinlang.org/docs/reference/reflection.html
     * - https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/
     */
    val reflect = Reflect

    object Coroutines: IsNotADependency {
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

    object Serialization: IsNotADependency {
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

    object Collections: IsNotADependency {
        private const val immutableArtifactPrefix = "$artifactBase-collections-immutable"

        const val immutable = "$immutableArtifactPrefix:_"

        const val immutableJvmOnly = "$immutableArtifactPrefix-jvm:_"
    }

    object Html: IsNotADependency {
        private const val artifactPrefix = "$artifactBase-html"

        const val jvm = "$artifactPrefix-jvm:_"
        const val js = "$artifactPrefix-js:_"
    }

    object Io: IsNotADependency {
        private const val artifactPrefix = "$artifactBase-io"

        const val jvm = "$artifactPrefix-jvm:_"
    }

    object Reflect: IsNotADependency {
        const val lite = "$artifactBase.reflect.lite:_"
    }
}
