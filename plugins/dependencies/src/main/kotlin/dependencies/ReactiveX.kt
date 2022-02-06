@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused", "MemberVisibilityCanBePrivate")

import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.kotlin.dsl.IsNotADependency

/**
 * An API for asynchronous programming with observable streams.
 *
 * Official website: [ReactiveX](https://reactivex.io)
 */
object ReactiveX : IsNotADependency {

    /**
     * RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and
     * event-based programs using observable sequences for the Java VM.
     *
     * As of February 28, 2021, The RxJava 2.x branch and version is end-of-life (EOL).
     *
     * GitHub page: [ReactiveX/RxJava/tree/2.x](https://github.com/ReactiveX/RxJava/tree/2.x)
     */
    val rxJava2 = RxJava2

    /**
     * RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and
     * event-based programs using observable sequences for the Java VM.
     *
     * As of February 28, 2021, The RxJava 2.x branch and version is end-of-life (EOL).
     *
     * GitHub page: [ReactiveX/RxJava/tree/2.x](https://github.com/ReactiveX/RxJava/tree/2.x)
     */
    object RxJava2 : DependencyNotationAndGroup(group = "io.reactivex.rxjava2", name = "rxjava") {

        /**
         * RxJava bindings for Android.
         *
         * GitHub page: [ReactiveX/RxAndroid/tree/2.x](https://github.com/ReactiveX/RxAndroid/tree/2.x)
         */
        val rxAndroid = module("rxandroid")

        /**
         * RxJava bindings for Kotlin.
         *
         * GitHub page: [ReactiveX/RxKotlin/tree/2.x](https://github.com/ReactiveX/RxKotlin/tree/2.x)
         */
        val rxKotlin = module("rxkotlin")
    }

    /**
     * RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and
     * event-based programs using observable sequences for the Java VM.
     *
     * GitHub page: [ReactiveX/RxJava](https://github.com/ReactiveX/RxJava)
     */
    val rxJava3 = RxJava3

    /**
     * RxJava – Reactive Extensions for the JVM – a library for composing asynchronous and
     * event-based programs using observable sequences for the Java VM.
     *
     * GitHub page: [ReactiveX/RxJava](https://github.com/ReactiveX/RxJava)
     */
    object RxJava3 : DependencyNotationAndGroup(group = "io.reactivex.rxjava3", name = "rxjava") {

        /**
         * RxJava bindings for Android.
         *
         * GitHub page: [ReactiveX/RxAndroid](https://github.com/ReactiveX/RxAndroid)
         */
        val rxAndroid = module("rxandroid")

        /**
         * RxJava bindings for Kotlin.
         *
         * GitHub page: [ReactiveX/RxKotlin](https://github.com/ReactiveX/RxKotlin)
         */
        val rxKotlin = module("rxkotlin")
    }
}
