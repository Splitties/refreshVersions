@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

@Incubating
object KotlinX {

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

    val collections = Collections

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

    /**
     * Kotlin multiplatform I/O library. (Experimental as of 2020-09-14)
     *
     * [Change log](https://github.com/Kotlin/kotlinx-io/blob/master/CHANGELOG.md)
     *
     * GitHub page: [Kotlin/kotlinx-io](https://github.com/Kotlin/kotlinx-io)
     */
    val io = Io

    private const val artifactBase = "org.jetbrains.kotlinx:kotlinx"

    /**
     * Kotlin external declarations for using the Node.js API from Kotlin code targeting JavaScript.
     *
     * Kotlin/JS Overview: [kotl.in/js](https://kotl.in/js)
     *
     * [Change log](https://github.com/Kotlin/kotlinx-nodejs/blob/master/CHANGELOG.md)
     *
     * GitHub page: [Kotlin/kotlinx-nodejs](https://github.com/Kotlin/kotlinx-nodejs)
     */
    const val nodeJs = "$artifactBase-nodejs:_"

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

    object Coroutines : IsNotADependency {
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

    object Serialization : IsNotADependency {
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

    object Collections : IsNotADependency {
        private const val immutableArtifactPrefix = "$artifactBase-collections-immutable"

        /**
         * Immutable persistent collections for Kotlin.
         *
         * [Library API proposal](https://github.com/Kotlin/kotlinx.collections.immutable/blob/master/proposal.md)
         *
         * [Change log](https://github.com/Kotlin/kotlinx.collections.immutable/blob/master/CHANGELOG.md)
         *
         * GitHub page: [Kotlin/kotlinx.collections.immutable](https://github.com/Kotlin/kotlinx.collections.immutable)
         */
        const val immutable = "$immutableArtifactPrefix:_"

        /**
         * Immutable persistent collections for Kotlin.
         *
         * [Library API proposal](https://github.com/Kotlin/kotlinx.collections.immutable/blob/master/proposal.md)
         *
         * [Change log](https://github.com/Kotlin/kotlinx.collections.immutable/blob/master/CHANGELOG.md)
         *
         * GitHub page: [Kotlin/kotlinx.collections.immutable](https://github.com/Kotlin/kotlinx.collections.immutable)
         */
        const val immutableJvmOnly = "$immutableArtifactPrefix-jvm:_"
    }

    object Html : IsNotADependency {
        private const val artifactPrefix = "$artifactBase-html"

        const val jvm = "$artifactPrefix-jvm:_"
        const val js = "$artifactPrefix-js:_"
    }

    object Io : IsNotADependency {
        private const val artifactPrefix = "$artifactBase-io"

        const val jvm = "$artifactPrefix-jvm:_"
    }

    object Reflect : IsNotADependency {
        const val lite = "$artifactBase.reflect.lite:_"
    }
}
