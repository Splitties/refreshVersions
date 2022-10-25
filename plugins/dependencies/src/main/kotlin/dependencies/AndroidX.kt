@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused", "MemberVisibilityCanBePrivate")

import de.fayard.refreshVersions.MultiplatformLibrary
import de.fayard.refreshVersions.core.DependencyGroup
import de.fayard.refreshVersions.core.DependencyNotation
import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.kotlin.dsl.IsNotADependency

/**
 * Check changes from the latest AndroidX versions on
 * [this dedicated page](https://developer.android.com/jetpack/androidx/versions).
 *
 * This structure brings **organized and ready-to-use constants for AndroidX dependencies**. It was made because:
 *
 * - As of 2021 August the 23rd, AndroidX is made of **91 subfamilies** of artifacts with their own version.
 * - Back in 2019 November the 18th, AndroidX was made of **187 artifacts**.
 */
object AndroidX : IsNotADependency {
    // LibraryGroups.kt: https://github.com/androidx/androidx/blob/androidx-main/buildSrc/public/src/main/kotlin/androidx/build/LibraryGroups.kt
    // LibraryVersions.kt: https://github.com/androidx/androidx/blob/androidx-main/buildSrc/public/src/main/kotlin/androidx/build/LibraryVersions.kt

    /**
     * Provides the base Activity subclass and the relevant hooks to build a composable structure on top.
     *
     * Guide: [Introduction to Activities](https://developer.android.com/guide/components/activities/intro-activities)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/activity)
     *
     * ### API reference:
     * - [androidx.activity](https://developer.android.com/reference/kotlin/androidx/activity/package-summary)
     * - [androidx.activity.contextaware](https://developer.android.com/reference/kotlin/androidx/activity/contextaware/package-summary)
     * - [androidx.activity.result](https://developer.android.com/reference/kotlin/androidx/activity/result/package-summary)
     * - [androidx.activity.result.contract](https://developer.android.com/reference/kotlin/androidx/activity/result/contract/package-summary)
     *
     * @see AndroidX.Activity.compose
     * @see AndroidX.Activity.ktx
     */
    val activity = Activity

    object Activity : DependencyNotationAndGroup(group = "androidx.activity", name = "activity") {

        /**
         * Compose integration with Activity.
         *
         * ### API reference:
         * - [androidx.activity.compose](https://developer.android.com/reference/kotlin/androidx/activity/compose/package-summary)
         */
        val compose = module("activity-compose")

        /**
         * Kotlin extensions
         *
         * ### API reference:
         * - [androidx.activity](https://developer.android.com/reference/kotlin/androidx/activity/package-summary)
         * - [androidx.activity.contextaware](https://developer.android.com/reference/kotlin/androidx/activity/contextaware/package-summary)
         * - [androidx.activity.result](https://developer.android.com/reference/kotlin/androidx/activity/result/package-summary)
         * - [androidx.activity.result.contract](https://developer.android.com/reference/kotlin/androidx/activity/result/contract/package-summary)
         *
         * @see AndroidX.Activity.compose
         */
        val ktx = module("activity-ktx")
    }

    // androidx.ads intentionally not included because ads are mental pollution.

    /**
     * Expose metadata that helps tools and other developers understand your app's code.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/annotation)
     *
     * ### API reference:
     * - [androidx.annotation](https://developer.android.com/reference/kotlin/androidx/annotation/package-summary)
     */
    val annotation = Annotation

    object Annotation : DependencyNotationAndGroup("androidx.annotation", "annotation") {

        /**
         * Java annotation for use on unstable Android API surfaces. When used in conjunction with the Experimental
         * annotation lint checks, this annotation provides functional parity with Kotlin's Experimental annotation.
         *
         * ### API reference:
         * - [androidx.annotation.experimental](https://developer.android.com/reference/kotlin/androidx/annotation/experimental/package-summary)
         */
        val experimental = module("annotation-experimental")
    }

    /**
     * Allows access to new APIs on older API versions of the platform (many using Material Design).
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/appcompat)
     *
     * ### API reference:
     * - [androidx.appcompat.app](https://developer.android.com/reference/kotlin/androidx/appcompat/app/package-summary)
     * - [androidx.appcompat.graphics.drawable](https://developer.android.com/reference/kotlin/androidx/appcompat/graphics/drawable/package-summary)
     * - [androidx.appcompat.view](https://developer.android.com/reference/kotlin/androidx/appcompat/view/package-summary)
     * - [androidx.appcompat.widget](https://developer.android.com/reference/kotlin/androidx/appcompat/widget/package-summary)
     */
    val appCompat = AppCompat

    object AppCompat : DependencyNotationAndGroup("androidx.appcompat", "appcompat") {

        /**
         * For loading and tinting drawables on older versions of the platform.
         *
         * ### API reference:
         * - [androidx.appcompat.content.res](https://developer.android.com/reference/kotlin/androidx/appcompat/content/res/package-summary)
         * - [androidx.appcompat.graphics.drawable](https://developer.android.com/reference/kotlin/androidx/appcompat/graphics/drawable/package-summary)
         * - [androidx.appcompat.widget](https://developer.android.com/reference/kotlin/androidx/appcompat/widget/package-summary)
         */
        val resources = module("appcompat-resources")
    }

    /**
     * AppSearch is an on-device search library for managing locally stored structured data, with APIs for indexing data
     * and retrieving data using full-text search. Use it to build custom in-app search capabilities for your users.
     *
     * Guide: [AppSearch](https://developer.android.com/guide/topics/search/appsearch)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/appsearch)
     *
     * ### API reference:
     * - [androidx.appsearch.annotation](https://developer.android.com/reference/kotlin/androidx/appsearch/annotation/package-summary)
     * - [androidx.appsearch.app](https://developer.android.com/reference/kotlin/androidx/appsearch/app/package-summary)
     * - [androidx.appsearch.exceptions](https://developer.android.com/reference/kotlin/androidx/appsearch/exceptions/package-summary)
     */
    val appSearch = AppSearch

    object AppSearch : DependencyNotationAndGroup(group = "androidx.appsearch", name = "appsearch") {

        /** Compiler for classes annotated with @androidx.appsearch.annotation.Document */
        val compiler = module("appsearch-compiler")

        /**
         * An implementation of AppSearchSession which uses local app storage and a local copy of the search library.
         *
         * ### API reference:
         * - [androidx.appsearch.localstorage](https://developer.android.com/reference/kotlin/androidx/appsearch/localstorage/package-summary)
         *
         * @see AndroidX.AppSearch.platformStorage
         */
        val localStorage = module("appsearch-local-storage")

        /**
         * An implementation of AppSearchSession which uses the AppSearch service on Android 12+.
         *
         * ### API reference:
         * - [androidx.appsearch.platformstorage](https://developer.android.com/reference/kotlin/androidx/appsearch/platformstorage/package-summary)
         */
        val platformStorage = module("appsearch-platform-storage")

        /**
         * AppSearch Builtin Types
         *
         * Contains AppSearch Document classes and builders for a variety of common objects based on
         * http://schema.org. Data interchange with the system, and other apps, as well as
         * structured parameters for semantic intents should use these built-in types as appropriate.
         *
         * ### API reference:
         * - [androidx.appsearch.builtintypes](https://developer.android.com/reference/androidx/appsearch/builtintypes/package-summary)
         */
        val builtInTypes = module("appsearch-builtin-types")
    }

    /**
     * Helper for other arch dependencies, including JUnit test rules that can be used with LiveData.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/arch-core)
     */
    val archCore = ArchCore

    object ArchCore : DependencyGroup(group = "androidx.arch.core") {

        /**
         * Android Arch-Common
         *
         * ### API reference:
         * - [androidx.arch.core.util](https://developer.android.com/reference/kotlin/androidx/arch/core/util/package-summary)
         */
        val common = module("core-common")

        /** Android Arch-Runtime */
        val runtime = module("core-runtime")

        /**
         * Android Core-Testing
         *
         * ### API reference:
         * - [androidx.arch.core.executor.testing](https://developer.android.com/reference/kotlin/androidx/arch/core/executor/testing/package-summary)
         */
        val testing = module("core-testing")
    }

    /**
     * Inflate layouts asynchronously to avoid jank in the UI.
     *
     * [Release Notes](https://developer.android.com/jetpack/androidx/releases/asynclayoutinflater)
     *
     * ### API reference:
     * - [androidx.asynclayoutinflater.view](https://developer.android.com/reference/kotlin/androidx/asynclayoutinflater/view/package-summary)
     */
    val asyncLayoutInflater = AsyncLayoutInflater

    object AsyncLayoutInflater : DependencyNotationAndGroup(
        group = "androidx.asynclayoutinflater",
        name = "asynclayoutinflater"
    ) {
        /**
         * A thread-safe LayoutInflater Factory that provides compatibility between AsyncLayoutInflater and AppCompat.
         *
         * ### API reference:
         * - [androidx.asynclayoutinflater.appcompat](https://developer.android.com/reference/androidx/asynclayoutinflater/appcompat/package-summary)
         */
        val appcompat = module("asynclayoutinflater-appcompat")
    }

    /**
     * Improve autofill accuracy via extending hints.
     *
     * Guide: [Autofill framework](hhttps://developer.android.com/guide/topics/text/autofill)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/autofill)
     *
     * ### API reference:
     * - [androidx.autofill](https://developer.android.com/reference/kotlin/androidx/autofill/package-summary)
     * - [androidx.autofill.inline](https://developer.android.com/reference/kotlin/androidx/autofill/inline/package-summary)
     * - [androidx.autofill.inline.common](https://developer.android.com/reference/kotlin/androidx/autofill/inline/common/package-summary)
     * - [androidx.autofill.inline.v1](https://developer.android.com/reference/kotlin/androidx/autofill/inline/v1/package-summary)
     */
    val autoFill = DependencyNotation("androidx.autofill", "autofill")

    /**
     * Accurately measure your code's performance within Android Studio.
     *
     * Guide: [Introduction to the Jetpack Benchmark library](https://developer.android.com/studio/profile/benchmark)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/benchmark)
     */
    val benchmark = Benchmark

    object Benchmark : DependencyGroup(group = "androidx.benchmark") {

        /** Android Benchmark Gradle Plugin */
        val gradlePlugin = module(name = "benchmark-gradle-plugin")

        /**
         * Android Benchmark - JUnit4.
         *
         * ### API reference:
         * - [androidx.benchmark.junit4](https://developer.android.com/reference/kotlin/androidx/benchmark/junit4/package-summary)
         */
        val junit4 = module(name = "benchmark-junit4")

        /**
         * Android Benchmark - Macrobenchmark.
         *
         * ### API reference:
         * - [androidx.benchmark.macro](https://developer.android.com/reference/kotlin/androidx/benchmark/macro/package-summary)
         *
         * @see AndroidX.Benchmark.macroJunit4
         */
        val macro = module(name = "benchmark-macro")

        /**
         * Android Benchmark - Macrobenchmark JUnit4.
         *
         * ### API reference:
         * - [androidx.benchmark.macro](https://developer.android.com/reference/kotlin/androidx/benchmark/macro/package-summary)
         * - [androidx.benchmark.macro.junit4](https://developer.android.com/reference/kotlin/androidx/benchmark/macro/junit4/package-summary)
         */
        val macroJunit4 = module(name = "benchmark-macro-junit4")

        /**
         * Android Benchmark - Common.
         *
         * ### API reference:
         * - [androidx.benchmark](https://developer.android.com/reference/kotlin/androidx/benchmark/package-summary)
         * - [androidx.benchmark.perfetto](https://developer.android.com/reference/kotlin/androidx/benchmark/perfetto/package-summary)
         */
        val common = module(name = "benchmark-common")
    }

    /**
     * Authenticate with biometrics or device credentials, and perform cryptographic operations.
     *
     * Guide: [Show a biometric authentication dialog](https://developer.android.com/training/sign-in/biometric-auth)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/biometric)
     *
     * ### API reference:
     * - [androidx.biometric](https://developer.android.com/reference/kotlin/androidx/biometric/package-summary)
     * - [androidx.biometric.auth](https://developer.android.com/reference/kotlin/androidx/biometric/auth/package-summary)
     *
     * @see AndroidX.Biometric.ktx
     */
    val biometric = Biometric

    object Biometric : DependencyNotationAndGroup("androidx.biometric", "biometric") {

        /** Kotlin extensions */
        val ktx = module("biometric-ktx")
    }

    /**
     * Android Support Custom Tabs.
     *
     * Guide: [Custom Tabs](https://developer.chrome.com/docs/android/custom-tabs/)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/browser)
     *
     * ### API reference:
     * - [androidx.browser.browseractions](https://developer.android.com/reference/kotlin/androidx/browser/browseractions/package-summary)
     * - [androidx.browser.customtabs](https://developer.android.com/reference/androidx/browser/customtabs/package-summary)
     * - [androidx.browser.trusted](https://developer.android.com/reference/androidx/browser/trusted/package-summary)
     * - [androidx.browser.trusted.sharing](https://developer.android.com/reference/kotlin/androidx/browser/trusted/sharing/package-summary)
     * - [androidx.browser.trusted.splashscreens](https://developer.android.com/reference/kotlin/androidx/browser/trusted/splashscreens/package-summary)
     */
    val browser = DependencyNotation("androidx.browser", "browser")

    /**
     * CameraX is an addition to Jetpack that makes it easier to add camera capabilities to your app.
     *
     * The library provides a number of compatibility fixes and workarounds to help make the developer experience
     * consistent across many devices.
     *
     * Guide: [CameraX overview](https://developer.android.com/training/camerax)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/camera)
     */
    val camera = Camera

    object Camera : DependencyGroup(group = "androidx.camera") {

        /**
         * Core components for the Jetpack Camera Library.
         *
         * ### API reference:
         * - [androidx.camera.core](https://developer.android.com/reference/kotlin/androidx/camera/core/package-summary)
         */
        val core = module("camera-core")

        /**
         * Camera2 implementation and extensions for the Jetpack Camera Library.
         *
         * ### API reference:
         * - [androidx.camera.camera2](https://developer.android.com/reference/kotlin/androidx/camera/camera2/package-summary)
         * - [androidx.camera.camera2.interop](https://developer.android.com/reference/kotlin/androidx/camera/camera2/interop/package-summary)
         */
        val camera2 = module("camera-camera2")

        /**
         * OEM Extensions for the Jetpack Camera Library, a library providing interfaces to integrate with OEM specific camera features.
         *
         * ### API reference:
         * - [androidx.camera.extensions](https://developer.android.com/reference/kotlin/androidx/camera/extensions/package-summary)
         * - [androidx.camera.extensions.internal](https://developer.android.com/reference/kotlin/androidx/camera/extensions/internal/package-summary)
         * - [androidx.camera.extensions.internal.compat.quirk](https://developer.android.com/reference/kotlin/androidx/camera/extensions/internal/compat/quirk/package-summary)
         * - [androidx.camera.extensions.internal.compat.workaround](https://developer.android.com/reference/kotlin/androidx/camera/extensions/internal/compat/workaround/package-summary)
         * - [androidx.camera.extensions.internal.sessionprocessor](https://developer.android.com/reference/kotlin/androidx/camera/extensions/internal/sessionprocessor/package-summary)
         */
        val extensions = module("camera-extensions")

        /**
         * Lifecycle components for the Jetpack Camera Library.
         *
         * ### API reference:
         * - [androidx.camera.lifecycle](https://developer.android.com/reference/kotlin/androidx/camera/lifecycle/package-summary)
         */
        val lifecycle = module("camera-lifecycle")

        /**
         * MLKit vision components for the Jetpack Camera Library,
         * a library providing a seamless integration that enables
         * camera driven computer vision features across all of Android.
         *
         * ### API reference:
         * - [androidx.camera.mlkit.vision](https://developer.android.com/reference/kotlin/androidx/camera/mlkit/vision/package-summary)
         */
        val mlKitVision = module("camera-mlkit-vision")

        /**
         * Video components for the Jetpack Camera Library.
         *
         * ### API reference:
         * - [androidx.camera.video](https://developer.android.com/reference/kotlin/androidx/camera/video/package-summary)
         * - [androidx.camera.video.impl](https://developer.android.com/reference/kotlin/androidx/camera/video/impl/package-summary)
         * - [androidx.camera.video.internal](https://developer.android.com/reference/kotlin/androidx/camera/video/internal/package-summary)
         * - [androidx.camera.video.internal.compat](https://developer.android.com/reference/kotlin/androidx/camera/video/internal/compat/package-summary)
         * - [androidx.camera.video.internal.compat.quirk](https://developer.android.com/reference/kotlin/androidx/camera/video/internal/compat/quirk/package-summary)
         * - [androidx.camera.video.internal.config](https://developer.android.com/reference/kotlin/androidx/camera/video/internal/config/package-summary)
         * - [androidx.camera.video.internal.encoder](https://developer.android.com/reference/kotlin/androidx/camera/video/internal/encoder/package-summary)
         * - [androidx.camera.video.internal.utils](https://developer.android.com/reference/kotlin/androidx/camera/video/internal/utils/package-summary)
         * - [androidx.camera.video.internal.workaround](https://developer.android.com/reference/kotlin/androidx/camera/video/internal/workaround/package-summary)
         */
        val video = module("camera-video")

        /**
         * UI tools for the Jetpack Camera Library.
         *
         * ### API reference:
         * - [androidx.camera.view](https://developer.android.com/reference/kotlin/androidx/camera/view/package-summary)
         * - [androidx.camera.view.internal.compat.quirk](https://developer.android.com/reference/kotlin/androidx/camera/view/internal/compat/quirk/package-summary)
         * - [androidx.camera.view.transform](https://developer.android.com/reference/kotlin/androidx/camera/view/transform/package-summary)
         * - [androidx.camera.view.video](https://developer.android.com/reference/kotlin/androidx/camera/view/video/package-summary)
         */
        val view = module("camera-view")
    }

    /**
     * Build navigation, parking, and charging apps for Android Auto.
     *
     * Guide: [Android for Cars overview](https://developer.android.com/training/cars)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/car-app)
     *
     * ### API reference:
     * - [androidx.car.app](https://developer.android.com/reference/kotlin/androidx/car/app/package-summary)
     * - [androidx.car.app.annotations](https://developer.android.com/reference/kotlin/androidx/car/app/annotations/package-summary)
     * - [androidx.car.app.connection](https://developer.android.com/reference/kotlin/androidx/car/app/connection/package-summary)
     * - [androidx.car.app.constraints](https://developer.android.com/reference/kotlin/androidx/car/app/constraints/package-summary)
     * - [androidx.car.app.hardware](https://developer.android.com/reference/kotlin/androidx/car/app/hardware/package-summary)
     * - [androidx.car.app.hardware.common](https://developer.android.com/reference/kotlin/androidx/car/app/hardware/common/package-summary)
     * - [androidx.car.app.managers](https://developer.android.com/reference/kotlin/androidx/car/app/managers/package-summary)
     * - [androidx.car.app.model](https://developer.android.com/reference/kotlin/androidx/car/app/model/package-summary)
     * - [androidx.car.app.model.signin](https://developer.android.com/reference/kotlin/androidx/car/app/model/signin/package-summary)
     * - [androidx.car.app.navigation](https://developer.android.com/reference/kotlin/androidx/car/app/navigation/package-summary)
     * - [androidx.car.app.navigation.model](https://developer.android.com/reference/kotlin/androidx/car/app/navigation/model/package-summary)
     * - [androidx.car.app.notification](https://developer.android.com/reference/kotlin/androidx/car/app/notification/package-summary)
     * - [androidx.car.app.serialization](https://developer.android.com/reference/kotlin/androidx/car/app/serialization/package-summary)
     * - [androidx.car.app.validation](https://developer.android.com/reference/kotlin/androidx/car/app/validation/package-summary)
     * - [androidx.car.app.versioning](https://developer.android.com/reference/kotlin/androidx/car/app/versioning/package-summary)
     */
    val carApp = CarApp

    object CarApp : DependencyNotationAndGroup(group = "androidx.car.app", name = "app") {

        /**
         * Automotive OS specific functionality to build navigation, parking, and charging apps for cars.
         *
         * ### API reference:
         * - [androidx.car.app.activity](https://developer.android.com/reference/kotlin/androidx/car/app/activity/package-summary)
         * - [androidx.car.app.activity.renderer.surface](https://developer.android.com/reference/kotlin/androidx/car/app/activity/renderer/surface/package-summary)
         * - [androidx.car.app.hardware](https://developer.android.com/reference/kotlin/androidx/car/app/hardware/package-summary)
         * - [androidx.car.app.hardware.common](https://developer.android.com/reference/kotlin/androidx/car/app/hardware/common/package-summary)
         * - [androidx.car.app.hardware.info](https://developer.android.com/reference/kotlin/androidx/car/app/hardware/info/package-summary)
         */
        val automotive = module("app-automotive")

        /**
         * Android Auto Projected specific funationality to build navigation, parking, and charging apps for cars.
         *
         * ### API reference:
         * - [androidx.car.app.hardware](https://developer.android.com/reference/kotlin/androidx/car/app/hardware/package-summary)
         * - [androidx.car.app.hardware.common](https://developer.android.com/reference/kotlin/androidx/car/app/hardware/common/package-summary)
         * - [androidx.car.app.hardware.info](https://developer.android.com/reference/kotlin/androidx/car/app/hardware/info/package-summary)
         */
        val projected = module("app-projected")

        /**
         * Utilities to test car apps.
         *
         * ### API reference:
         * - [androidx.car.app.testing](https://developer.android.com/reference/kotlin/androidx/car/app/testing/package-summary)
         * - [androidx.car.app.testing.navigation](https://developer.android.com/reference/kotlin/androidx/car/app/testing/navigation/package-summary)
         */
        val testing = module("app-testing")
    }

    /**
     * Implement the Material Design card pattern with round corners and drop shadows.
     *
     * Guide: [Create a Card-Based Layout](https://developer.android.com/guide/topics/ui/layout/cardview)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/cardview)
     *
     * ### API reference:
     * - [androidx.cardview.widget](https://developer.android.com/reference/kotlin/androidx/cardview/widget/package-summary)
     */
    val cardView = DependencyNotation("androidx.cardview", "cardview")

    /**
     * Reduce the memory impact of existing and new collections that are small.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/collection)
     *
     * ### API reference:
     * - [androidx.collection](https://developer.android.com/reference/kotlin/androidx/collection/package-summary)
     *
     * @see AndroidX.Collection.ktx
     */
    @MultiplatformLibrary
    val collection = Collection

    object Collection : DependencyNotationAndGroup("androidx.collection", "collection") {

        /** Kotlin extensions */
        val ktx = module("collection-ktx")
    }

    /**
     * Define your UI programmatically with composable functions that describe its shape and data dependencies.
     *
     * Guides:
     * - [Overview](https://developer.android.com/jetpack/compose/)
     * - [Why adopt Compose](https://developer.android.com/jetpack/compose/why-adopt)
     * - [Get started with Jetpack Compose](https://developer.android.com/jetpack/compose/documentation)
     * - [Jetpack Compose Tutorial](https://developer.android.com/jetpack/compose/tutorial)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/compose)
     */
    val compose = Compose

    object Compose : DependencyGroup(group = "androidx.compose") {

        val bom = module("compose-bom", isBom = true)

        /**
         * Transform @Composable functions and enable optimizations with a Kotlin compiler plugin.
         *
         * [Release notes](https://developer.android.com/jetpack/androidx/releases/compose-compiler)
         */
        val compiler = module(
            group = "androidx.compose.compiler",
            name = "compiler",
            usePlatformConstraints = false // Not included in the BoM, and not used as a regular dependency.
        )

        /**
         * Fundamental building blocks of Compose's programming model and state management,
         * and core runtime for the Compose Compiler Plugin to target.
         *
         * [Release notes](https://developer.android.com/jetpack/androidx/releases/compose-runtime)
         *
         * ### API reference:
         * - [androidx.compose.runtime](https://developer.android.com/reference/kotlin/androidx/compose/runtime/package-summary)
         * - [androidx.compose.runtime.collection](https://developer.android.com/reference/kotlin/androidx/compose/runtime/collection/package-summary)
         * - [androidx.compose.runtime.internal](https://developer.android.com/reference/kotlin/androidx/compose/runtime/internal/package-summary)
         * - [androidx.compose.runtime.saveable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/saveable/package-summary)
         * - [androidx.compose.runtime.snapshots](https://developer.android.com/reference/kotlin/androidx/compose/runtime/snapshots/package-summary)
         * - [androidx.compose.runtime.tooling](https://developer.android.com/reference/kotlin/androidx/compose/runtime/tooling/package-summary)
         */
        val runtime = Runtime

        object Runtime : DependencyNotationAndGroup(
            platformConstrainsDelegateGroup = Compose,
            group = "androidx.compose.runtime",
            name = "runtime"
        ) {

            val dispatch = module("runtime-dispatch")

            /**
             * Compose components that allow saving and restoring the local ui state.
             *
             * ### API reference:
             * - [androidx.compose.runtime.saveable](https://developer.android.com/reference/kotlin/androidx/compose/runtime/saveable/package-summary)
             */
            val saveable = module("runtime-saveable")

            /**
             * Compose integration with LiveData.
             *
             * ### API reference:
             * - [androidx.compose.runtime.livedata](https://developer.android.com/reference/kotlin/androidx/compose/runtime/livedata/package-summary)
             */
            val liveData = module("runtime-livedata")

            /**
             * Compose integration with RxJava 2.
             *
             * ### API reference:
             * - [androidx.compose.runtime.rxjava2](https://developer.android.com/reference/kotlin/androidx/compose/runtime/rxjava2/package-summary)
             */
            val rxJava2 = module("runtime-rxjava2")

            /**
             * Compose integration with RxJava 3.
             *
             * ### API reference:
             * - [androidx.compose.runtime.rxjava3](https://developer.android.com/reference/kotlin/androidx/compose/runtime/rxjava3/package-summary)
             */
            val rxJava3 = module("runtime-rxjava3")

            /**
             * Additional tracing in Compose.
             *
             * ### API reference:
             * - [androidx.compose.runtime.tracing](https://developer.android.com/reference/kotlin/androidx/compose/runtime/tracing/package-summary)
             */
            val tracing = module("runtime-tracing")
        }

        /**
         * Build animations in their Jetpack Compose applications to enrich the user experience.
         *
         * Guide: [Animation](https://developer.android.com/jetpack/compose/animation)
         *
         * [Release notes](https://developer.android.com/jetpack/androidx/releases/compose-animation)
         *
         * ### API reference:
         * - [androidx.compose.animation](https://developer.android.com/reference/kotlin/androidx/compose/animation/package-summary)
         * - [androidx.compose.animation.core](https://developer.android.com/reference/kotlin/androidx/compose/animation/core/package-summary)
         */
        val animation = Animation

        object Animation : DependencyNotationAndGroup(
            platformConstrainsDelegateGroup = Compose,
            group = "androidx.compose.animation",
            name = "animation"
        ) {

            /**
             * Animation engine and animation primitives that are the building blocks of the Compose animation library.
             *
             * Included in the main [AndroidX.Compose.animation] artifact.
             *
             * ## API reference:
             * - [androidx.compose.animation.core](https://developer.android.com/reference/kotlin/androidx/compose/animation/core/package-summary)
             */
            val core = module("animation-core")

            /**
             * Compose Animation Graphics Library for using animated-vector resources in Compose
             *
             * ## API reference:
             * - [androidx.compose.animation.graphics](https://developer.android.com/reference/kotlin/androidx/compose/animation/graphics/package-summary)
             * - [androidx.compose.animation.res](https://developer.android.com/reference/kotlin/androidx/compose/animation/res/package-summary)
             * - [androidx.compose.animation.vector](https://developer.android.com/reference/kotlin/androidx/compose/animation/vector/package-summary)
             */
            val graphics = module("animation-graphics")
        }

        /**
         * Fundamental components of compose UI needed to interact with the device, including layout, drawing, and input.
         *
         * [Release notes](https://developer.android.com/jetpack/androidx/releases/compose-ui)
         *
         * ## API reference:
         * - [androidx.compose.ui](https://developer.android.com/reference/kotlin/androidx/compose/ui/package-summary)
         * - [androidx.compose.ui.autofill](https://developer.android.com/reference/kotlin/androidx/compose/ui/autofill/package-summary)
         * - [androidx.compose.ui.draw](https://developer.android.com/reference/kotlin/androidx/compose/ui/draw/package-summary)
         * - [androidx.compose.ui.focus](https://developer.android.com/reference/kotlin/androidx/compose/ui/focus/package-summary)
         * - [androidx.compose.ui.geometry](https://developer.android.com/reference/kotlin/androidx/compose/ui/geometry/package-summary)
         * - [androidx.compose.ui.graphics](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/package-summary)
         * - [androidx.compose.ui.graphics.colorspace](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/colorspace/package-summary)
         * - [androidx.compose.ui.graphics.drawscope](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/drawscope/package-summary)
         * - [androidx.compose.ui.graphics.painter](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/painter/package-summary)
         * - [androidx.compose.ui.graphics.vector](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/vector/package-summary)
         * - [androidx.compose.ui.hapticfeedback](https://developer.android.com/reference/kotlin/androidx/compose/ui/hapticfeedback/package-summary)
         * - [androidx.compose.ui.input](https://developer.android.com/reference/kotlin/androidx/compose/ui/input/package-summary)
         * - [androidx.compose.ui.input.key](https://developer.android.com/reference/kotlin/androidx/compose/ui/input/key/package-summary)
         * - [androidx.compose.ui.input.nestedscroll](https://developer.android.com/reference/kotlin/androidx/compose/ui/input/nestedscroll/package-summary)
         * - [androidx.compose.ui.input.pointer](https://developer.android.com/reference/kotlin/androidx/compose/ui/input/pointer/package-summary)
         * - [androidx.compose.ui.input.pointer.util](https://developer.android.com/reference/kotlin/androidx/compose/ui/input/pointer/util/package-summary)
         * - [androidx.compose.ui.layout](https://developer.android.com/reference/kotlin/androidx/compose/ui/layout/package-summary)
         * - [androidx.compose.ui.modifier](https://developer.android.com/reference/kotlin/androidx/compose/ui/modifier/package-summary)
         * - [androidx.compose.ui.node](https://developer.android.com/reference/kotlin/androidx/compose/ui/node/package-summary)
         * - [androidx.compose.ui.platform](https://developer.android.com/reference/kotlin/androidx/compose/ui/platform/package-summary)
         * - [androidx.compose.ui.res](https://developer.android.com/reference/kotlin/androidx/compose/ui/res/package-summary)
         * - [androidx.compose.ui.semantics](https://developer.android.com/reference/kotlin/androidx/compose/ui/semantics/package-summary)
         * - [androidx.compose.ui.state](https://developer.android.com/reference/kotlin/androidx/compose/ui/state/package-summary)
         * - [androidx.compose.ui.text](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/package-summary)
         * - [androidx.compose.ui.text.android](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/android/package-summary)
         * - [androidx.compose.ui.text.font](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/font/package-summary)
         * - [androidx.compose.ui.text.input](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/input/package-summary)
         * - [androidx.compose.ui.text.intl](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/intl/package-summary)
         * - [androidx.compose.ui.text.platform.extensions](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/platform/extensions/package-summary)
         * - [androidx.compose.ui.text.style](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/style/package-summary)
         * - [androidx.compose.ui.tooling](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/package-summary)
         * - [androidx.compose.ui.tooling.data](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/data/package-summary)
         * - [androidx.compose.ui.tooling.preview](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/preview/package-summary)
         * - [androidx.compose.ui.tooling.preview.datasource](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/preview/datasource/package-summary)
         * - [androidx.compose.ui.unit](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/package-summary)
         * - [androidx.compose.ui.util](https://developer.android.com/reference/kotlin/androidx/compose/ui/util/package-summary)
         * - [androidx.compose.ui.viewinterop](https://developer.android.com/reference/kotlin/androidx/compose/ui/viewinterop/package-summary)
         * - [androidx.compose.ui.window](https://developer.android.com/reference/kotlin/androidx/compose/ui/window/package-summary)
         */
        val ui = Ui

        object Ui : DependencyNotationAndGroup(
            platformConstrainsDelegateGroup = Compose,
            group = "androidx.compose.ui",
            name = "ui"
        ) {

            /**
             * Compose classes related to dimensions without units
             *
             * ### API reference:
             * - [androidx.compose.ui.geometry](https://developer.android.com/reference/kotlin/androidx/compose/ui/geometry/package-summary)
             */
            val geometry = module("ui-geometry")

            /**
             * Compose graphics
             *
             * ### API reference:
             * - [androidx.compose.ui.graphics](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/package-summary)
             * - [androidx.compose.ui.graphics.colorspace](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/colorspace/package-summary)
             * - [androidx.compose.ui.graphics.drawscope](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/drawscope/package-summary)
             * - [androidx.compose.ui.graphics.painter](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/painter/package-summary)
             * - [androidx.compose.ui.graphics.vector](https://developer.android.com/reference/kotlin/androidx/compose/ui/graphics/vector/package-summary)
             */
            val graphics = module("ui-graphics")

            /**
             * Compose testing library
             *
             * ### API reference:
             * - [androidx.compose.ui.test](https://developer.android.com/reference/kotlin/androidx/compose/ui/test/package-summary)
             */
            val test = module("ui-test")

            /**
             * Compose testing integration with JUnit4
             *
             * ### API reference:
             * - [androidx.compose.ui.test.junit4](https://developer.android.com/reference/kotlin/androidx/compose/ui/test/junit4/package-summary)
             * - [androidx.compose.ui.test.junit4.android](https://developer.android.com/reference/kotlin/androidx/compose/ui/test/junit4/android/package-summary)
             */
            val testJunit4 = module("ui-test-junit4")

            /**
             * Compose testing library that should be added as a debugImplementation dependency to
             * add properties to the debug manifest necessary for testing an application.
             */
            val testManifest = module("ui-test-manifest")

            /**
             * Compose Text primitives and utilities
             *
             * ### API reference:
             * - [androidx.compose.ui.text](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/package-summary)
             * - [androidx.compose.ui.text.android](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/android/package-summary)
             * - [androidx.compose.ui.text.font](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/font/package-summary)
             * - [androidx.compose.ui.text.input](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/input/package-summary)
             * - [androidx.compose.ui.text.intl](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/intl/package-summary)
             * - [androidx.compose.ui.text.platform.extensions](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/platform/extensions/package-summary)
             * - [androidx.compose.ui.text.style](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/style/package-summary)
             */
            val text = Text

            object Text : DependencyNotationAndGroup(
                platformConstrainsDelegateGroup = Compose,
                group = group,
                name = "ui-text"
            ) {

                /**
                 * Compose Downloadable Fonts integration for Google Fonts.
                 *
                 * ### API reference:
                 * - [androidx.compose.ui.text.googlefonts](https://developer.android.com/reference/kotlin/androidx/compose/ui/text/googlefonts/package-summary)
                 */
                val googleFonts = module("ui-text-google-fonts")
            }

            /**
             * Compose tooling library. This library exposes information to our tools for better IDE support.
             *
             * ### API reference:
             * - [androidx.compose.ui.tooling](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/package-summary)
             */
            val tooling = module("ui-tooling")

            /**
             * 	Compose tooling library data. This library provides data about compose for different tooling purposes.
             *
             * ### API reference:
             * - [androidx.compose.ui.tooling.data](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/data/package-summary)
             */
            val toolingData = module("ui-tooling-data")

            /**
             * Compose tooling library API. This library provides the API required to declare @Preview composables in user apps.
             *
             * ### API reference:
             * - [androidx.compose.ui.tooling.preview](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/preview/package-summary)
             * - [androidx.compose.ui.tooling.preview.datasource](https://developer.android.com/reference/kotlin/androidx/compose/ui/tooling/preview/datasource/package-summary)
             */
            val toolingPreview = module("ui-tooling-preview")

            /**
             * Compose classes for simple units.
             *
             * ### API reference:
             * - [androidx.compose.ui.unit](https://developer.android.com/reference/kotlin/androidx/compose/ui/unit/package-summary)
             */
            val unit = module("ui-unit")

            /**
             * Internal Compose utilities used by other modules.
             *
             * ### API reference:
             * - [androidx.compose.ui.util](https://developer.android.com/reference/kotlin/androidx/compose/ui/util/package-summary)
             */
            val util = module("ui-util")

            /**
             * Compose integration with ViewBinding
             */
            val viewBinding = module("ui-viewbinding")
        }

        /**
         * Write Jetpack Compose applications with ready to use building blocks and extend foundation to
         * build your own design system pieces.
         *
         * Guide: [Layouts in Compose](https://developer.android.com/jetpack/compose/layouts)
         *
         * [Release notes](https://developer.android.com/jetpack/androidx/releases/compose-foundation)
         *
         * ## API reference:
         * - [androidx.compose.foundation](https://developer.android.com/reference/kotlin/androidx/compose/foundation/package-summary)
         * - [androidx.compose.foundation.gestures](https://developer.android.com/reference/kotlin/androidx/compose/foundation/gestures/package-summary)
         * - [androidx.compose.foundation.interaction](https://developer.android.com/reference/kotlin/androidx/compose/foundation/interaction/package-summary)
         * - [androidx.compose.foundation.layout](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/package-summary)
         * - [androidx.compose.foundation.lazy](https://developer.android.com/reference/kotlin/androidx/compose/foundation/lazy/package-summary)
         * - [androidx.compose.foundation.relocation](https://developer.android.com/reference/kotlin/androidx/compose/foundation/relocation/package-summary)
         * - [androidx.compose.foundation.selection](https://developer.android.com/reference/kotlin/androidx/compose/foundation/selection/package-summary)
         * - [androidx.compose.foundation.shape](https://developer.android.com/reference/kotlin/androidx/compose/foundation/shape/package-summary)
         * - [androidx.compose.foundation.text](https://developer.android.com/reference/kotlin/androidx/compose/foundation/text/package-summary)
         * - [androidx.compose.foundation.text.selection](https://developer.android.com/reference/kotlin/androidx/compose/foundation/text/selection/package-summary)
         */
        val foundation = Foundation

        object Foundation : DependencyNotationAndGroup(
            platformConstrainsDelegateGroup = Compose,
            group = "androidx.compose.foundation",
            name = "foundation"
        ) {

            /**
             * Compose layout implementations
             *
             * Guide: [Layouts in Compose](https://developer.android.com/jetpack/compose/layouts)
             *
             * ## API reference:
             * - [androidx.compose.foundation.layout](https://developer.android.com/reference/kotlin/androidx/compose/foundation/layout/package-summary)
             */
            val layout = module("foundation-layout")
        }

        /**
         * Build Jetpack Compose UIs with ready to use Material Design Components.
         *
         * This is the higher level entry point of Compose, designed to provide components that match those described at
         * [material.io](https://material.io).
         *
         * Guide: [Material Theming in Compose](https://developer.android.com/jetpack/compose/themes/material)
         *
         * ### API reference:
         * - [androidx.compose.material](https://developer.android.com/reference/kotlin/androidx/compose/material/package-summary)
         * - [androidx.compose.material.icons](https://developer.android.com/reference/kotlin/androidx/compose/material/icons/package-summary)
         */
        val material = Material

        object Material : DependencyNotationAndGroup(
            platformConstrainsDelegateGroup = Compose,
            group = "androidx.compose.material",
            name = "material"
        ) {

            /**
             * Material icons
             *
             * ### API reference
             * - [androidx.compose.material.icons](https://developer.android.com/reference/kotlin/androidx/compose/material/icons/package-summary)
             */
            val icons = Icons

            object Icons : IsNotADependency {

                /**
                 * Contains the most commonly used set of Material icons.
                 *
                 * Included in the main [AndroidX.Compose.material] artifact.
                 *
                 * The [AndroidX.Compose.Material.Icons.extended] library contains the full set of Material Icons.
                 */
                val core = module("material-icons-core")

                /**
                 * Contains the full set of Material Icons.
                 *
                 * Due to the very large size of this library, make sure to use R8/Proguard to strip unused icons if
                 * you are including this library as a direct dependency. Alternatively you can make a
                 * local copy (by copy and pasting) the icon(s) you wish to keep,
                 * or using Android Studio's 'Import vector asset' feature.
                 */
                val extended = module("material-icons-extended")
            }

            /**
             * Material ripple used to build interactive components.
             *
             * ### API reference
             * - [androidx.compose.material.ripple](https://developer.android.com/reference/kotlin/androidx/compose/material/ripple/package-summary)
             */
            val ripple = module("material-ripple")
        }

        /**
         * Build Jetpack Compose UIs with Material Design 3 Components, the next evolution of Material Design.
         * Material 3 includes updated theming and components and Material You personalization features like
         * dynamic color, and is designed to be cohesive with the new Android 12 visual style and system UI.
         *
         * [Release notes](https://developer.android.com/jetpack/androidx/releases/compose-material3)
         *
         * ### API reference:
         * - [androidx.compose.material3](https://developer.android.com/reference/kotlin/androidx/compose/material3/package-summary)
         */
        val material3 = Material3

        object Material3 : DependencyNotationAndGroup(
            platformConstrainsDelegateGroup = Compose,
            group = "androidx.compose.material3",
            name = "material3"
        ) {

            /**
             * Provides window size classes for building responsive UIs
             *
             * ### API reference:
             * - [androidx.compose.material3.windowsizeclass](https://developer.android.com/reference/kotlin/androidx/compose/material3/windowsizeclass/package-summary)
             */
            val windowSizeClass = module("material3-window-size-class")
        }
    }

    /**
     * Move tasks off the main thread with coroutines and take advantage of ListenableFuture, includes Kotlin extensions.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/concurrent)
     */
    val concurrent = Concurrent

    object Concurrent : DependencyGroup(group = "androidx.concurrent") {

        /**
         * Androidx implementation of Guava's ListenableFuture.
         *
         * ### API reference:
         * - [androidx.concurrent.futures](https://developer.android.com/reference/kotlin/androidx/concurrent/futures/package-summary)
         *
         * @see AndroidX.Concurrent.futuresKtx
         */
        val futures = module("concurrent-futures")

        /**
         * Kotlin Extensions for Androidx implementation of Guava's ListenableFuture.
         *
         * ### API reference:
         * - [androidx.concurrent.futures](https://developer.android.com/reference/kotlin/androidx/concurrent/futures/package-summary)
         */
        val futuresKtx = module("concurrent-futures-ktx")
    }

    /**
     * Position and size widgets in a flexible way with relative positioning.
     *
     * Guide: [Build a Responsive UI with ConstraintLayout](https://developer.android.com/training/constraint-layout)
     *
     * GitHub page: [androidx/constraintlayout](https://github.com/androidx/constraintlayout)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/constraintlayout)
     *
     * ## API reference:
     * - [androidx.constraintlayout](https://developer.android.com/reference/androidx/constraintlayout/packages)
     */
    val constraintLayout = ConstraintLayout

    object ConstraintLayout : DependencyNotationAndGroup("androidx.constraintlayout", "constraintlayout") {

        /**
         * Jetpack Compose support
         *
         * Guide: [ConstraintLayout in Compose](https://developer.android.com/jetpack/compose/layouts/constraintlayout)
         */
        val compose = module("constraintlayout-compose")
    }

    /**
     * Load and page across ContentProvider data in a background thread.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/contentpager)
     *
     * ## API reference:
     * - [androidx.contentpager.content](https://developer.android.com/reference/kotlin/androidx/contentpager/content/package-summary)
     */
    val contentPager = DependencyNotation("androidx.contentpager", "contentpager")

    /**
     * Position top-level application widgets, such as AppBarLayout and FloatingActionButton.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/coordinatorlayout)
     *
     * ## API reference:
     * - [androidx.coordinatorlayout.widget](https://developer.android.com/reference/kotlin/androidx/coordinatorlayout/widget/package-summary)
     */
    val coordinatorLayout = DependencyNotation("androidx.coordinatorlayout", "coordinatorlayout")

    /**
     * Target the latest platform features and APIs while also supporting older devices.
     *
     * Guide: [Animate movement using spring physics](https://developer.android.com/guide/topics/graphics/spring-animation)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/core)
     *
     * ## API reference:
     * - [androidx.core.accessibilityservice](https://developer.android.com/reference/kotlin/androidx/core/accessibilityservice/package-summary)
     * - [androidx.core.app](https://developer.android.com/reference/kotlin/androidx/core/app/package-summary)
     * - [androidx.core.content](https://developer.android.com/reference/kotlin/androidx/core/content/package-summary)
     * - [androidx.core.content.pm](https://developer.android.com/reference/kotlin/androidx/core/content/pm/package-summary)
     * - [androidx.core.content.res](https://developer.android.com/reference/kotlin/androidx/core/content/res/package-summary)
     * - [androidx.core.database](https://developer.android.com/reference/kotlin/androidx/core/database/package-summary)
     * - [androidx.core.database.sqlite](https://developer.android.com/reference/kotlin/androidx/core/database/sqlite/package-summary)
     * - [androidx.core.graphics](https://developer.android.com/reference/kotlin/androidx/core/graphics/package-summary)
     * - [androidx.core.graphics.drawable](https://developer.android.com/reference/kotlin/androidx/core/graphics/drawable/package-summary)
     * - [androidx.core.hardware.display](https://developer.android.com/reference/kotlin/androidx/core/hardware/display/package-summary)
     * - [androidx.core.hardware.fingerprint](https://developer.android.com/reference/kotlin/androidx/core/hardware/fingerprint/package-summary)
     * - [androidx.core.location](https://developer.android.com/reference/kotlin/androidx/core/location/package-summary)
     * - [androidx.core.math](https://developer.android.com/reference/kotlin/androidx/core/math/package-summary)
     * - [androidx.core.net](https://developer.android.com/reference/kotlin/androidx/core/net/package-summary)
     * - [androidx.core.os](https://developer.android.com/reference/kotlin/androidx/core/os/package-summary)
     * - [androidx.core.provider](https://developer.android.com/reference/kotlin/androidx/core/provider/package-summary)
     * - [androidx.core.telephony](https://developer.android.com/reference/kotlin/androidx/core/telephony/package-summary)
     * - [androidx.core.text](https://developer.android.com/reference/kotlin/androidx/core/text/package-summary)
     * - [androidx.core.util](https://developer.android.com/reference/kotlin/androidx/core/util/package-summary)
     * - [androidx.core.view](https://developer.android.com/reference/kotlin/androidx/core/view/package-summary)
     * - [androidx.core.view.accessibility](https://developer.android.com/reference/kotlin/androidx/core/view/accessibility/package-summary)
     * - [androidx.core.view.animation](https://developer.android.com/reference/kotlin/androidx/core/view/animation/package-summary)
     * - [androidx.core.view.inputmethod](https://developer.android.com/reference/kotlin/androidx/core/view/inputmethod/package-summary)
     * - [androidx.core.widget](https://developer.android.com/reference/kotlin/androidx/core/widget/package-summary)
     *
     * @see AndroidX.Core.ktx
     */
    val core = Core

    object Core : DependencyNotationAndGroup(group = "androidx.core", name = "core") {

        /**
         * Kotlin extensions
         *
         * ## API reference:
         * - [androidx.core.content](https://developer.android.com/reference/kotlin/androidx/core/content/package-summary)
         * - [androidx.core.database](https://developer.android.com/reference/kotlin/androidx/core/database/package-summary)
         * - [androidx.core.graphics](https://developer.android.com/reference/kotlin/androidx/core/graphics/package-summary)
         * - [androidx.core.location](https://developer.android.com/reference/kotlin/androidx/core/location/package-summary)
         * - [androidx.core.net](https://developer.android.com/reference/kotlin/androidx/core/net/package-summary)
         * - [androidx.core.os](https://developer.android.com/reference/kotlin/androidx/core/os/package-summary)
         * - [androidx.core.text](https://developer.android.com/reference/kotlin/androidx/core/text/package-summary)
         * - [androidx.core.transition](https://developer.android.com/reference/kotlin/androidx/core/transition/package-summary)
         * - [androidx.core.util](https://developer.android.com/reference/kotlin/androidx/core/util/package-summary)
         * - [androidx.core.view](https://developer.android.com/reference/kotlin/androidx/core/view/package-summary)
         * - [androidx.core.widget](https://developer.android.com/reference/kotlin/androidx/core/widget/package-summary)
         */
        val ktx = module("core-ktx")

        /**
         * Public API surface for apps to use UWB (ultra-wideband) on supported devices.
         *
         * [Release notes](https://developer.android.com/jetpack/androidx/releases/core-uwb)
         *
         * ## API reference:
         * - [androidx.core.uwb](https://developer.android.com/reference/kotlin/androidx/core/uwb/package-summary)
         * - [androidx.core.uwb.exceptions](https://developer.android.com/reference/kotlin/androidx/core/uwb/exceptions/package-summary)
         */
        val uwb = Uwb

        object Uwb : DependencyNotationAndGroup(
            group = "androidx.core.uwb",
            name = "uwb"
        ) {
            /** 	RxJava3 integration for UWB module */
            val rxJava3 = module(name = "uwb-rxjava3")
        }

        /**
         * To use RoleManagerCompat
         *
         * ## API reference:
         * - [androidx.core.role](https://developer.android.com/reference/kotlin/androidx/core/role/package-summary)
         */
        val role = module("core-role")

        /**
         * This library provides functionalities for creating and manipulating animations for API 14 and above.
         *
         * ### API reference:
         * - [androidx.core.animation](https://developer.android.com/reference/kotlin/androidx/core/animation/package-summary)
         */
        val animation = module("core-animation")

        /**
         * This library provides functionalities for testing animations for API 14 and above.
         *
         * ### API reference:
         * - [androidx.core.animation](https://developer.android.com/reference/kotlin/androidx/core/animation/package-summary)
         */
        val animationTesting = module("core-animation-testing")

        /**
         * To use ShortcutManagerCompat to donate shortcuts to be used by Google
         */
        val googleShortcuts = module("core-google-shortcuts")

        /**
         * To enable APIs that query the performance characteristics of GMS devices.
         *
         * This library makes it easy for developers to make UI and feature choices based on
         * Android Performance Class level for GMS devices.
         *
         * ### API reference:
         * - [androidx.core.performance](https://developer.android.com/reference/kotlin/androidx/core/performance/package-summary)
         */
        val performance = module("core-performance")

        /**
         * This library provides the compatibility APIs for SplashScreen and helper method to enable a
         * splashscreen on devices prior Android 12.
         */
        val splashscreen = module("core-splashscreen")

        /** AndroidX RemoteViews Support */
        val remoteViews = module("core-remoteviews")
    }

    /**
     * Expose Cursor data to a ListView widget.
     *
     * Guide: [Content providers](https://developer.android.com/guide/topics/providers/content-providers)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/cursoradapter)
     *
     * ## API reference:
     * - [androidx.cursoradapter.widget](https://developer.android.com/reference/kotlin/androidx/cursoradapter/widget/package-summary)
     */
    val cursorAdapter = DependencyNotation("androidx.cursoradapter", "cursoradapter")

    /**
     * Implement custom views.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/customview)
     *
     * ## API reference:
     * - [androidx.customview.view](https://developer.android.com/reference/kotlin/androidx/customview/view/package-summary)
     * - [androidx.customview.widget](https://developer.android.com/reference/kotlin/androidx/customview/widget/package-summary)
     */
    val customView = CustomView

    object CustomView : DependencyNotationAndGroup("androidx.customview", "customview") {

        /**
         * Utilities for listening to the lifecycle of containers that manage their child Views' lifecycle,
         * such as RecyclerView
         *
         * ## API reference:
         * - [androidx.customview.poolingcontainer](https://developer.android.com/reference/kotlin/androidx/customview/poolingcontainer/package-summary)
         */
        val poolingContainer = module("customview-poolingcontainer")
    }

    /**
     * Store data asynchronously, consistently, and transactionally, overcoming some of the drawbacks of SharedPreferences.
     *
     * This artifact is for Typed DataStore (Typed API surface, such as Proto).
     *
     * See [AndroidX.DataStore.preferences] if you want a SharedPreferences like API.
     *
     * Guide: [DataStore](https://developer.android.com/topic/libraries/architecture/datastore)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/datastore)
     *
     * ## API reference:
     * - [androidx.datastore](https://developer.android.com/reference/kotlin/androidx/datastore/package-summary)
     * - [androidx.datastore.core](https://developer.android.com/reference/kotlin/androidx/datastore/core/package-summary)
     * - [androidx.datastore.core.handlers](https://developer.android.com/reference/kotlin/androidx/datastore/core/handlers/package-summary)
     * - [androidx.datastore.migrations](https://developer.android.com/reference/kotlin/androidx/datastore/migrations/package-summary)
     */
    val dataStore = DataStore

    object DataStore : DependencyNotationAndGroup(group = "androidx.datastore", name = "datastore") {

        /**
         * Preferences DataStore (SharedPreferences like APIs).
         *
         * Use [AndroidX.dataStore] for Typed DataStore (Typed API surface, such as Proto).
         *
         * ## API reference:
         * - [androidx.datastore.preferences](https://developer.android.com/reference/kotlin/androidx/datastore/preferences/package-summary)
         * - [androidx.datastore.preferences.core](https://developer.android.com/reference/kotlin/androidx/datastore/preferences/core/package-summary)
         */
        val preferences = Preferences

        object Preferences : DependencyNotationAndGroup(group = "androidx.datastore", name = "datastore-preferences") {

            /**
             * Android independent artifact
             *
             * ## API reference:
             * - [androidx.datastore.preferences.core](https://developer.android.com/reference/kotlin/androidx/datastore/preferences/core/package-summary)
             */
            @MultiplatformLibrary
            val core = module("datastore-preferences-core")

            /**
             * RxJava 2 support
             *
             * ## API reference:
             * - [androidx.datastore.preferences.rxjava2](https://developer.android.com/reference/kotlin/androidx/datastore/preferences/rxjava2/package-summary)
             */
            val rxJava2 = module("datastore-preferences-rxJava2")

            /**
             * RxJava 3 support
             *
             * ## API reference:
             * - [androidx.datastore.preferences.rxjava3](https://developer.android.com/reference/kotlin/androidx/datastore/preferences/rxjava3/package-summary)
             */
            val rxJava3 = module("datastore-preferences-rxJava3")
        }

        /**
         * Android independent artifact
         *
         * ## API reference:
         * - [androidx.datastore.core](https://developer.android.com/reference/kotlin/androidx/datastore/core/package-summary)
         * - [androidx.datastore.core.handlers](https://developer.android.com/reference/kotlin/androidx/datastore/core/handlers/package-summary)
         */
        @MultiplatformLibrary
        val core = Core

        object Core : DependencyNotationAndGroup(
            group = group,
            name = "datastore-core"
        ) {

            /**
             * Android DataStore Core Okio contains APIs to use datastore-core in multiplatform via okio.
             */
            @MultiplatformLibrary
            val okio = module("datastore-core-okio")
        }

        /**
         * RxJava 2 support
         *
         * ## API reference:
         * - [androidx.datastore.rxjava2](https://developer.android.com/reference/kotlin/androidx/datastore/rxjava2/package-summary)
         */
        val rxJava2 = module("datastore-rxJava2")

        /**
         * RxJava 3 support
         *
         * ## API reference:
         * - [androidx.datastore.rxjava3](https://developer.android.com/reference/kotlin/androidx/datastore/rxjava3/package-summary)
         */
        val rxJava3 = module("datastore-rxJava3")
    }

    /**
     * View a file document.
     *
     * Guide: [Open files using storage access framework](https://developer.android.com/guide/topics/providers/document-provider)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/documentfile)
     *
     * ## API reference:
     * - [androidx.documentfile.provider](https://developer.android.com/reference/kotlin/androidx/documentfile/provider/package-summary)
     */
    val documentFile = DependencyNotation("androidx.documentfile", "documentfile")

    /**
     * Accept drag-and-drop data from another app or within an app, and show a consistent drop target affordance.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/draganddrop)
     *
     * ## API reference:
     * - [androidx.draganddrop](https://developer.android.com/reference/kotlin/androidx/draganddrop/package-summary)
     */
    val dragAndDrop = DependencyNotation("androidx.draganddrop", "draganddrop")

    /**
     * Implement a Material Design drawer widget.
     *
     * Guide: [Update UI components with NavigationUI -> Add a navigation drawer](https://developer.android.com/guide/navigation/navigation-ui#add_a_navigation_drawer)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/drawerlayout)
     *
     * ## API reference:
     * - [androidx.drawerlayout.widget](https://developer.android.com/reference/kotlin/androidx/drawerlayout/widget/package-summary)
     */
    val drawerLayout = DependencyNotation("androidx.drawerlayout", "drawerlayout")

    /**
     * Create smooth animations with a physics-based animation API.
     *
     * Guide: [Animate movement using spring physics](https://developer.android.com/guide/topics/graphics/spring-animation)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/dynamicanimation)
     *
     * ## API reference:
     * - [androidx.dynamicanimation.animation](https://developer.android.com/reference/kotlin/androidx/dynamicanimation/animation/package-summary)
     *
     * @see AndroidX.DynamicAnimation.ktx
     */
    val dynamicAnimation = DynamicAnimation

    object DynamicAnimation : DependencyNotationAndGroup("androidx.dynamicanimation", "dynamicanimation") {

        /** Kotlin extensions */
        val ktx = module("dynamicanimation-ktx")
    }

    /**
     * Display emoji in current and older devices.
     *
     * Guide: [Emoji Compatibility](https://developer.android.com/guide/topics/ui/look-and-feel/emoji-compat)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/emoji)
     *
     * ### API reference:
     * - [androidx.emoji.widget](https://developer.android.com/reference/kotlin/androidx/emoji/widget/package-summary)
     * - [androidx.emoji.text](https://developer.android.com/reference/kotlin/androidx/emoji/text/package-summary)
     *
     * @see AndroidX.emoji2
     */
    @Deprecated(
        "Deprecated in favor of Emoji2, because it simplifies backward-compativility with lower versions of Android",
        ReplaceWith("AndroidX.emoji2")
    )
    val emoji = Emoji

    object Emoji : DependencyNotationAndGroup("androidx.emoji", "emoji") {

        @Deprecated("Deprecated in favor of Emoji2, which is already included in AppCompat 1.4.0+")
        val appCompat = module("emoji-appcompat")

        /**
         * ### API reference:
         * - [androidx.emoji.bundled](https://developer.android.com/reference/kotlin/androidx/emoji/bundled/package-summary)
         */
        @Deprecated(
            "Deprecated in favor of Emoji2, because it simplifies backward-compativility with lower versions of Android",
            ReplaceWith("AndroidX.emoji2.bundled")
        )
        val bundled = module("emoji-bundled")
    }

    /**
     * Display emoji in current and older devices.
     *
     * Guide: [Support modern emoji](https://developer.android.com/guide/topics/ui/look-and-feel/emoji2)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/emoji2)
     *
     * ### API reference:
     * - [androidx.emoji2.text](https://developer.android.com/reference/kotlin/androidx/emoji2/text/package-summary)
     * - [androidx.emoji2.widget](https://developer.android.com/reference/kotlin/androidx/emoji2/widget/package-summary)
     * - [androidx.emoji2.viewsintegration](https://developer.android.com/reference/kotlin/androidx/emoji2/viewsintegration/package-summary)
     */
    val emoji2 = Emoji2

    object Emoji2 : DependencyNotationAndGroup(group = "androidx.emoji2", name = "emoji2") {

        /**
         * Support emoji without AppCompat
         *
         * Guide: [Support modern emoji -> Support emoji without AppCompat](https://developer.android.com/guide/topics/ui/look-and-feel/emoji2#support-without-appcompat)
         *
         * ### API reference:
         * - [androidx.emoji2.widget](https://developer.android.com/reference/kotlin/androidx/emoji2/widget/package-summary)
         */
        val views = module("emoji2-views")

        /**
         * Add custom views for apps without AppCompat
         *
         * Guide: [Support modern emoji -> Add custom views for apps without AppCompat](https://developer.android.com/guide/topics/ui/look-and-feel/emoji2#custom-views-no-appcompat)
         *
         * ### API reference:
         * - [androidx.emoji2.viewsintegration](https://developer.android.com/reference/kotlin/androidx/emoji2/viewsintegration/package-summary)
         */
        val viewsHelper = module("emoji2-views-helper")

        /**
         * Support bundled fonts with emoji2
         *
         * Guide: [Support modern emoji -> Support bundled fonts with emoji2](https://developer.android.com/guide/topics/ui/look-and-feel/emoji2#support-bundled-fonts)
         *
         * ### API reference:
         * - [androidx.emoji2.bundled](https://developer.android.com/reference/kotlin/androidx/emoji2/bundled/package-summary)
         */
        val bundled = module("emoji2-bundled")
    }

    /**
     * Create enterprise-ready applications.
     *
     * Guide: [Build for Enterprise -> Developer overview](https://developer.android.com/work/overview)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/enterprise)
     */
    val enterprise = Enterprise

    object Enterprise : DependencyGroup(group = "androidx.enterprise") {

        /**
         * Allow sending app feedback to EMMs (Enterprise Mobile Management providers)
         *
         * Guide: [Build for Enterprise -> App feedback -> Send app feedback to EMMs](https://developer.android.com/work/app-feedback/overview)
         *
         * ### API reference:
         * - [androidx.enterprise.feedback](https://developer.android.com/reference/kotlin/androidx/enterprise/feedback/package-summary)
         */
        val feedback = module("enterprise-feedback")

        /**
         * For testing enterprise feedback in isolation
         *
         * Guide: [Build for Enterprise -> App feedback -> Test app feedback](https://developer.android.com/work/app-feedback/testing)
         */
        val feedbackTesting = module("enterprise-feedback-testing")
    }

    /**
     * Read and write image file EXIF tags.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/exifinterface)
     *
     * ### API reference:
     * - [androidx.exifinterface.media](https://developer.android.com/reference/kotlin/androidx/exifinterface/media/package-summary)
     */
    val exifInterface = DependencyNotation("androidx.exifinterface", "exifinterface")

    /**
     * Segment your app into multiple, independent screens that are hosted within an Activity.
     *
     * Guide: [Fragments](https://developer.android.com/guide/fragments)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/fragment)
     *
     * ### API reference:
     * - [androidx.fragment.app](https://developer.android.com/reference/kotlin/androidx/fragment/app/package-summary)
     *
     * @see AndroidX.Fragment.ktx
     */
    val fragment = Fragment

    object Fragment : DependencyNotationAndGroup("androidx.fragment", "fragment") {

        /** Kotlin extensions */
        val ktx = DependencyNotation("androidx.fragment", "fragment-ktx")

        /**
         * Testing Fragments in isolation.
         *
         * ### API reference:
         * - [androidx.fragment.app.testing](https://developer.android.com/reference/kotlin/androidx/fragment/app/testing/package-summary)
         * - [androidx.fragment.app.strictmode](https://developer.android.com/reference/kotlin/androidx/fragment/app/strictmode/package-summary)
         */
        val testing = DependencyNotation("androidx.fragment", "fragment-testing")
    }

    /**
     * The android games library mirrors the Android Game SDK which is available as a binary download.
     *
     * You can use the androidx library instead of manually downloading and integrating the Android Game SDK into your build.
     *
     * For more information about the Android Game SDK, see the [SDK documentation](https://developer.android.com/games/sdk)
     * and the [SDK release notes](https://developer.android.com/games/sdk/release-notes).
     *
     * [Setup instructions & release notes](https://developer.android.com/jetpack/androidx/releases/games)
     *
     * Guide: [Android Game Development Kit](https://developer.android.com/games/agdk)
     */
    val games = Games

    object Games : DependencyGroup(group = "androidx.games") {

        val framePacing = module("games-frame-pacing")
        val performanceTuner = module("games-performance-tuner")
        val activity = module("games-activity")
        val controller = module("games-controller")
        val textInput = module("games-text-input")
    }

    /**
     * Build layouts for remote surfaces using a Jetpack Compose-style API.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/glance)
     *
     * ### API reference:
     * - [androidx.glance](https://developer.android.com/reference/kotlin/androidx/glance/package-summary)
     * - [androidx.glance.action](https://developer.android.com/reference/kotlin/androidx/glance/action/package-summary)
     * - [androidx.glance.layout](https://developer.android.com/reference/kotlin/androidx/glance/layout/package-summary)
     * - [androidx.glance.state](https://developer.android.com/reference/kotlin/androidx/glance/state/package-summary)
     * - [androidx.glance.text](https://developer.android.com/reference/kotlin/androidx/glance/text/package-summary)
     * - [androidx.glance.unit](https://developer.android.com/reference/kotlin/androidx/glance/unit/package-summary)
     */
    val glance = Glance

    object Glance : DependencyNotationAndGroup("androidx.glance", "glance") {

        /**
         * Glance-appwidgets allows developers to build layouts for Android AppWidgets using a Jetpack Compose-style API.
         *
         * ### API reference:
         * - [androidx.glance.appwidget](https://developer.android.com/reference/kotlin/androidx/glance/appwidget/package-summary)
         * - [androidx.glance.appwidget.action](https://developer.android.com/reference/kotlin/androidx/glance/appwidget/action/package-summary)
         * - [androidx.glance.appwidget.lazy](https://developer.android.com/reference/kotlin/androidx/glance/appwidget/lazy/package-summary)
         * - [androidx.glance.appwidget.state](https://developer.android.com/reference/kotlin/androidx/glance/appwidget/state/package-summary)
         * - [androidx.glance.appwidget.unit](https://developer.android.com/reference/kotlin/androidx/glance/appwidget/unit/package-summary)
         */
        val appWidget = module("glance-appwidget")

        /**
         * Glance allows developers to build layouts for Wear Tiles using a Jetpack Compose-style API.
         *
         * ### API reference:
         * - [androidx.glance.wear.tiles](https://developer.android.com/reference/kotlin/androidx/glance/wear/tiles/package-summary)
         * - [androidx.glance.wear.tiles.curved](https://developer.android.com/reference/kotlin/androidx/glance/wear/tiles/curved/package-summary)
         */
        val wearTiles = module("glance-wear-tiles")
    }

    /**
     * Leverage graphics facilities across multiple Android platform releases.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/graphics)
     */
    val graphics = Graphics

    object Graphics : DependencyGroup(
        group = "androidx.graphics"
    ) {
        /**
         * ### API reference:
         * - [androidx.graphics.lowlatency](https://developer.android.com/reference/kotlin/androidx/graphics/lowlatency/package-summary)
         * - [androidx.graphics.opengl](https://developer.android.com/reference/kotlin/androidx/graphics/opengl/package-summary)
         * - [androidx.graphics.opengl.egl](https://developer.android.com/reference/kotlin/androidx/graphics/opengl/egl/package-summary)
         * - [androidx.graphics.surface](https://developer.android.com/reference/kotlin/androidx/graphics/surface/package-summary)
         */
        val core = module("graphics-core")
    }

    /**
     * Implement a grid layout.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/gridlayout)
     *
     * ### API reference:
     * - [androidx.gridlayout.widget](https://developer.android.com/reference/kotlin/androidx/gridlayout/widget/package-summary)
     */
    val gridLayout = DependencyNotation("androidx.gridlayout", "gridlayout")

    /**
     * Create performant health applications in a platform-agnostic way.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/health)
     */
    val health = Health

    object Health : DependencyGroup(group = "androidx.health") {

        val connect = Connect

        object Connect : DependencyGroup(group = "androidx.health.connect") {

            /**
             * Read or write user's health and fitness records.
             *
             * ### API reference:
             * - [androidx.health.connect.client](https://developer.android.com/reference/kotlin/androidx/health/connect/client/package-summary)
             * - [androidx.health.connect.client.aggregate](https://developer.android.com/reference/kotlin/androidx/health/connect/client/aggregate/package-summary)
             * - [androidx.health.connect.client.changes](https://developer.android.com/reference/kotlin/androidx/health/connect/client/changes/package-summary)
             * - [androidx.health.connect.client.metadata](https://developer.android.com/reference/kotlin/androidx/health/connect/client/metadata/package-summary)
             * - [androidx.health.connect.client.permission](https://developer.android.com/reference/kotlin/androidx/health/connect/client/permission/package-summary)
             * - [androidx.health.connect.client.records](https://developer.android.com/reference/kotlin/androidx/health/connect/client/records/package-summary)
             * - [androidx.health.connect.client.request](https://developer.android.com/reference/kotlin/androidx/health/connect/client/request/package-summary)
             * - [androidx.health.connect.client.response](https://developer.android.com/reference/kotlin/androidx/health/connect/client/response/package-summary)
             * - [androidx.health.connect.client.time](https://developer.android.com/reference/kotlin/androidx/health/connect/client/time/package-summary)
             * - [androidx.health.platform.client](https://developer.android.com/reference/kotlin/androidx/health/platform/client/package-summary)
             * - [androidx.health.platform.client.changes](https://developer.android.com/reference/kotlin/androidx/health/platform/client/changes/package-summary)
             * - [androidx.health.platform.client.error](https://developer.android.com/reference/kotlin/androidx/health/platform/client/error/package-summary)
             * - [androidx.health.platform.client.permission](https://developer.android.com/reference/kotlin/androidx/health/platform/client/permission/package-summary)
             * - [androidx.health.platform.client.request](https://developer.android.com/reference/kotlin/androidx/health/platform/client/request/package-summary)
             * - [androidx.health.platform.client.response](https://developer.android.com/reference/kotlin/androidx/health/platform/client/response/package-summary)
             */
            val client = module("connect-client")
        }

        /**
         * ### API reference:
         * - [androidx.health.services.client](https://developer.android.com/reference/kotlin/androidx/health/services/client/package-summary)
         * - [androidx.health.services.client.data](https://developer.android.com/reference/kotlin/androidx/health/services/client/data/package-summary)
         */
        val servicesClient = module("health-services-client")
    }

    /**
     * Encode an image or image collection in HEIF format using the available codecs on the Android device.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/heifwriter)
     *
     * ### API reference:
     * - [androidx.heifwriter](https://developer.android.com/reference/kotlin/androidx/heifwriter/package-summary)
     */
    val heifWriter = DependencyNotation("androidx.heifwriter", "heifwriter")

    /**
     * Extend the functionality of [Dagger Hilt](https://dagger.dev/hilt/) to enable dependency injection of certain classes from the androidx libraries.
     *
     * Guide: [Dependency injection with Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/hilt)
     *
     * @see Google.Dagger.Hilt.Android.gradlePlugin
     * @see Google.Dagger.Hilt.android
     * @see Google.Dagger.Hilt.compiler
     */
    val hilt = Hilt

    object Hilt : DependencyGroup(group = "androidx.hilt") {

        /**
         * Inject WorkManager with Hilt.
         *
         * Guide: [Hilt and Jetpack integrations -> Inject WorkManager with Hilt](https://developer.android.com/training/dependency-injection/hilt-jetpack#workmanager)
         *
         * ### API reference:
         * - [androidx.hilt.work](https://developer.android.com/reference/kotlin/androidx/hilt/work/package-summary)
         */
        val work = module("hilt-work")

        /**
         * Integration with the Jetpack navigation library
         *
         * Guide: [Hilt and Jetpack integrations -> Integration with the Jetpack navigation library](https://developer.android.com/training/dependency-injection/hilt-jetpack#viewmodel-navigation)
         *
         * ### API reference:
         * - [androidx.hilt.navigation.fragment](https://developer.android.com/reference/kotlin/androidx/hilt/navigation/fragment/package-summary)
         * - [androidx.hilt.navigation](https://developer.android.com/reference/kotlin/androidx/hilt/navigation/package-summary)
         */
        val navigationFragment = module("hilt-navigation-fragment")

        /**
         * Integration with navigation for Jetpack Compose.
         *
         * Guide: [Compose and other libraries -> Hilt and Navigation](https://developer.android.com/jetpack/compose/libraries#hilt-navigation)
         *
         * ### API reference:
         * - [androidx.hilt.navigation.compose](https://developer.android.com/reference/kotlin/androidx/hilt/navigation/compose/package-summary)
         * - [androidx.hilt.navigation](https://developer.android.com/reference/kotlin/androidx/hilt/navigation/package-summary)
         */
        val navigationCompose = module("hilt-navigation-compose")

        /** Annotation processor */
        val compiler = module("hilt-compiler")
    }

    /**
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/input)
     */
    val input = Input

    object Input : DependencyGroup(
        group = "androidx.input"
    ) {
        /**
         * Reduce the latency of input interactions by predicting future MotionEvents.
         *
         * ### API reference:
         * - [androidx.input.motionprediction](https://developer.android.com/reference/kotlin/androidx/input/motionprediction/package-summary)
         */
        val motionPrediction = module("input-motionprediction")
    }

    /**
     * Use animation interpolators on older platforms.
     *
     * Guide: [Property Animation Overview -> Use Interpolators](https://developer.android.com/guide/topics/graphics/prop-animation#interpolators)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/interpolator)
     *
     * ### API reference:
     * - [androidx.interpolator.view.animation](https://developer.android.com/reference/kotlin/androidx/interpolator/view/animation/package-summary)
     */
    val interpolator = DependencyNotation("androidx.interpolator", "interpolator")

    /**
     * Javascript Engine is a static library you can add to your Android application in order to evaluate JavaScript.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/javascriptengine)
     *
     * ### API reference:
     * - [androidx.javascriptengine](https://developer.android.com/reference/kotlin/androidx/javascriptengine/package-summary)
     */
    val javascriptEngine = DependencyNotation("androidx.javascriptengine", "javascriptengine")

    /**
     * Write apps for Android TV devices using dpad-friendly widgets and template fragments.
     *
     * The initial release targets foldable devices,
     * but future versions will extend to more display types and window features.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/leanback)
     *
     * Guide: [Get started with TV apps](https://developer.android.com/training/tv/start/start)
     *
     * ### API reference:
     * - [androidx.leanback.app](https://developer.android.com/reference/kotlin/androidx/leanback/app/package-summary)
     * - [androidx.leanback.database](https://developer.android.com/reference/kotlin/androidx/leanback/database/package-summary)
     * - [androidx.leanback.graphics](https://developer.android.com/reference/kotlin/androidx/leanback/graphics/package-summary)
     * - [androidx.leanback.media](https://developer.android.com/reference/kotlin/androidx/leanback/media/package-summary)
     * - [androidx.leanback.system](https://developer.android.com/reference/kotlin/androidx/leanback/system/package-summary)
     * - [androidx.leanback.widget](https://developer.android.com/reference/kotlin/androidx/leanback/widget/package-summary)
     * - [androidx.leanback.widget.picker](https://developer.android.com/reference/kotlin/androidx/leanback/widget/picker/package-summary)
     */
    val leanback = Leanback

    object Leanback : DependencyNotationAndGroup(group = "androidx.leanback", name = "leanback") {

        /**
         * Add-on that provides a settings UI for TV apps.
         *
         * ### API reference:
         * - [androidx.leanback.preference](https://developer.android.com/reference/kotlin/androidx/leanback/preference/package-summary)
         */
        val preference = module("leanback-preference")

        /**
         * Add-on that simplifies adding paging support to a RecyclerView Adapter.
         *
         * ### API reference:
         * - [androidx.leanback.paging](https://developer.android.com/reference/kotlin/androidx/leanback/paging/package-summary)
         */
        val paging = module("leanback-paging")

        /**
         * Add-on that provides customized TabLayout to be used as the top navigation bar.
         *
         * ### API reference:
         * - [androidx.leanback.tab](https://developer.android.com/reference/kotlin/androidx/leanback/tab/package-summary)
         */
        val tab = module("leanback-tab")

        /**
         * The base [leanback] library already depends on this library.
         *
         * Use this one if you want to just use the grid view components of leanback.
         *
         * Contains the following grid related classes and interfaces:
         * - `BaseGridView`
         * - `FacetProvider`
         * - `FacetProviderAdapter`
         * - `GridLayoutManager`
         * - `HorizontalGridView`
         * - `ItemAlignmentFacet`
         * - `OnChildLaidOutListener`
         * - `OnChildSelectedListener`
         * - `OnChildViewHolderSelectedListener`
         * - `VerticalGridView`
         * - `ViewHolderTask`
         * - `Visibility`
         *
         * ### API reference:
         * - [androidx.leanback.widget](https://developer.android.com/reference/kotlin/androidx/leanback/widget/package-summary)
         */
        val grid = module("leanback-grid")
    }

    /**
     * Lifecycle-aware components perform actions in response to a change in the lifecycle status of another component,
     * such as activities and fragments.
     *
     * These components help you produce better-organized, and often lighter-weight code, that is easier to maintain.
     *
     * Guide: [Handling Lifecycles with Lifecycle-Aware Components](https://developer.android.com/topic/libraries/architecture/lifecycle)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/lifecycle)
     *
     * ### API reference:
     * - [androidx.lifecycle](https://developer.android.com/reference/kotlin/androidx/lifecycle/package-summary)
     */
    val lifecycle = Lifecycle

    object Lifecycle : DependencyGroup(group = "androidx.lifecycle") {

        /** LiveData, with Kotlin extensions. */
        val liveDataKtx = module("lifecycle-livedata-ktx")

        /** ViewModel, with Kotlin extensions. */
        val viewModelKtx = module("lifecycle-viewmodel-ktx")

        /** ProcessLifecycleOwner provides a lifecycle for the whole application process. */
        val process = module("lifecycle-process")

        /** Helpers for implementing LifecycleOwner in a Service */
        val service = module("lifecycle-service")

        /** Saved state module for ViewModel */
        val viewModelSavedState = module("lifecycle-viewmodel-savedstate")

        /**
         * ViewModel utilities for Compose
         *
         * ### API reference:
         * - [androidx.lifecycle.viewmodel.compose](https://developer.android.com/reference/kotlin/androidx/lifecycle/viewmodel/compose/package-summary)
         */
        val viewModelCompose = module("lifecycle-viewmodel-compose")

        /**
         * Lifecyclew only (without ViewModel or LiveData), includes only Java APIs.
         *
         * @see AndroidX.Lifecycle.Runtime.ktx
         */
        val runtime = Runtime

        object Runtime : DependencyNotationAndGroup(
            group = group,
            name = "lifecycle-runtime"
        ) {
            /** Lifecycle only (without ViewModel or LiveData), with Kotlin extensions. */
            val ktx = module("lifecycle-runtime-ktx")

            /** Compose integration with Lifecycle. */
            val compose = module("lifecycle-runtime-compose")

            /**
             * Provides a `TestlifecycleOwner` that implements `LifecycleOwner` and
             * provides a thread safe mutable `Lifcycle`.
             *
             * ### API reference:
             * - [androidx.lifecycle.testing](https://developer.android.com/reference/kotlin/androidx/lifecycle/testing/package-summary)
             */
            val testing = module("lifecycle-runtime-testing")
        }

        /**
         * ViewModel, includes only Java APIs.
         *
         * @see AndroidX.Lifecycle.viewModelKtx
         */
        val viewModel = module("lifecycle-viewmodel")

        /**
         * LiveData, includes only Java APIs.
         *
         * @see AndroidX.Lifecycle.liveDataKtx
         */
        val liveData = module("lifecycle-livedata")

        val common = module("lifecycle-common")

        /** Alternative to [compiler] if using Java 8 or newer. */
        val commonJava8 = module("lifecycle-common-java8")

        /** Annotation processor. */
        @Deprecated("@OnLifecycleEvent was deprecated. LifecycleEventObserver or DefaultLifecycleObserver should be used instead.")
        val compiler = module("lifecycle-compiler")

        /**
         * ReactiveStreams support for LiveData, includes only Java APIs.
         *
         * @see AndroidX.Lifecycle.reactiveStreamsKtx
         */
        val reactiveStreams = module("lifecycle-reactivestreams")

        /** ReactiveStreams support for LiveData, with Kotlin extensions. */
        val reactiveStreamsKtx = module("lifecycle-reactivestreams-ktx")

        @Deprecated("Replaced by more specific artifacts. Last available version is 2.2.0")
        val extensions = module("lifecycle-extensions")
    }

    /**
     * Load data for your UI that survives configuration changes.
     *
     * **NOTE: This library is obsolete. New code should not need this library. Use ViewModel and SharedFlow or LiveData instead.**
     *
     * Guide: [Loaders](https://developer.android.com/guide/components/loaders)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/loader)
     *
     * ### API reference:
     * - [androidx.loader.app](https://developer.android.com/reference/kotlin/androidx/loader/app/package-summary)
     * - [androidx.loader.content](https://developer.android.com/reference/kotlin/androidx/loader/content/package-summary)
     */
    val loader = DependencyNotation("androidx.loader", "loader")

    /**
     * **DEPRECATED**, [see reason here](https://developer.android.com/jetpack/androidx/releases/localbroadcastmanager)
     *
     * Consider using one of these better alternatives:
     * - `Flow`, `SharedFlow`, and `StateFlow` from kotlinx.coroutines
     * - ` LiveData` from AndroidX (which can interop with `Flow` from kotlinx.coroutines with [Lifecycle.liveDataKtx])
     */
    @Deprecated("Confusing developer experience, use coroutines + Flows and/or LiveData instead.")
    val localBroadcastManager = DependencyNotation("androidx.localbroadcastmanager", "localbroadcastmanager")

    /**
     * Share media contents and controls with other apps. Superseded by media2.
     *
     * Guide: [Media app architecture overview](https://developer.android.com/guide/topics/media-apps/media-apps-overview)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/media)
     *
     * ### API reference:
     * - [androidx.media](https://developer.android.com/reference/kotlin/androidx/media/package-summary)
     * - [androidx.media.app](https://developer.android.com/reference/kotlin/androidx/media/app/package-summary)
     * - [androidx.media.session](https://developer.android.com/reference/kotlin/androidx/media/session/package-summary)
     * - [androidx.media.utils](https://developer.android.com/reference/kotlin/androidx/media/utils/package-summary)
     *
     * @see AndroidX.media2
     * @see AndroidX.media3
     */
    val media = DependencyNotation("androidx.media", "media")

    /**
     * Share media contents and controls with other apps.
     *
     * Guide: [Media app architecture overview](https://developer.android.com/guide/topics/media-apps/media-apps-overview)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/media2)
     *
     * ### API reference:
     * - [androidx.media2.common](https://developer.android.com/reference/kotlin/androidx/media2/common/package-summary)
     *
     * @see AndroidX.media3
     */
    val media2 = Media2

    object Media2 : DependencyGroup(group = "androidx.media2") {

        /**
         * Interacting with MediaSessions
         *
         * ### API reference:
         * - [androidx.media2.session](https://developer.android.com/reference/kotlin/androidx/media2/session/package-summary)
         */
        val session = module("media2-session")

        /**
         * UI widgets for VideoView and MediaControlView
         *
         * ### API reference:
         * - [androidx.media2.widget](https://developer.android.com/reference/kotlin/androidx/media2/widget/package-summary)
         */
        val widget = module("media2-widget")

        /**
         * Implementation of a SessionPlayer
         *
         * ### API reference:
         * - [androidx.media2.player](https://developer.android.com/reference/kotlin/androidx/media2/player/package-summary)
         */
        val player = module("media2-player")

        /**
         * Repackaged ExoPlayer for 'media2' artifact
         *
         * @see AndroidX.Media3.exoPlayer
         */
        val exoplayer = module("media2-exoplayer")

        /**
         * Common APIs, included in other Media2 artifacts.
         *
         * ### API reference:
         * - [androidx.media2.common](https://developer.android.com/reference/kotlin/androidx/media2/common/package-summary)
         */
        val common = module("media2-common")
    }

    /**
     * Support libraries for media use cases.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/media3)
     *
     * GitHub page: [androidx/media](https://github.com/androidx/media)
     */
    val media3 = Media3

    object Media3 : DependencyGroup(group = "androidx.media3") {

        /**
         * Media playback using ExoPlayer.
         */
        val exoPlayer = ExoPlayer

        object ExoPlayer : DependencyNotationAndGroup(group = group, name = "media3-exoplayer") {

            /**
             * For DASH playback support with ExoPlayer
             */
            val dash = module("media3-exoplayer-dash")

            /**
             * For HLS playback support with ExoPlayer
             */
            val hls = module("media3-exoplayer-hls")

            /**
             * For RTSP playback support with ExoPlayer
             */
            val rtsp = module("media3-exoplayer-rtsp")

            /**
             * For ad insertion using the Interactive Media Ads SDK with ExoPlayer
             */
            val ima = module("media3-exoplayer-ima")

            /**
             * For scheduling background operations using Jetpack Work's WorkManager with ExoPlayer
             */
            val workmanager = module("media3-exoplayer-workmanager")
        }

        /**
         * Common functionality for loading data
         */
        val dataSource = DataSource

        object DataSource : DependencyNotationAndGroup(group = group, name = "media3-datasource") {

            /**
             * For loading data using the Cronet network stack
             */
            val cronet = module("media3-datasource-cronet")

            /**
             * For loading data using the OkHttp network stack
             */
            val okhttp = module("media3-datasource-okhttp")

            /**
             * For loading data using librtmp
             */
            val rtmp = module("media3-datasource-rtmp")
        }


        /**
         * For building media playback UIs
         */
        val ui = Ui

        object Ui : DependencyNotationAndGroup(group = group, name = "media3-ui") {

            /**
             * For building media playback UIs for Android TV using the Jetpack Leanback library
             */
            val leanback = module("media3-ui-leanback")
        }

        /**
         * For exposing and controlling media sessions
         */
        val session = module("media3-session")

        /**
         * For extracting data from media containers
         */
        val extractor = module("media3-extractor")

        /**
         * For integrating with Cast
         */
        val cast = module("media3-cast")

        /**
         * For transforming media files
         */
        val transformer = module("media3-transformer")

        /**
         * Utilities for testing media components (including ExoPlayer components)
         */
        val testUtils = TestUtils

        object TestUtils : DependencyNotationAndGroup(group = group, name = "media3-test-utils") {

            /**
             * Utilities for testing media components (including ExoPlayer components) via Robolectric
             */
            val robolectric = module("media3-test-utils-robolectric")
        }

        /**
         * Common functionality for media database components
         */
        val database = module("media3-database")

        /**
         * Common functionality for media decoders
         */
        val decoder = module("media3-decoder")


        /**
         * Common functionality used across multiple media libraries
         */
        val common = module("media3-common")
    }

    /**
     * Enable media display and playback on remote receiver devices using a common user interface.
     *
     * Guide: [MediaRouter overview](https://developer.android.com/guide/topics/media/mediarouter)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/mediarouter)
     *
     * ### API reference:
     * - [androidx.mediarouter.app](https://developer.android.com/reference/kotlin/androidx/mediarouter/app/package-summary)
     * - [androidx.mediarouter.media](https://developer.android.com/reference/kotlin/androidx/mediarouter/media/package-summary)
     */
    val mediaRouter = DependencyNotation("androidx.mediarouter", "mediarouter")

    /**
     * Library for tracking and reporting various runtime metrics for applications.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/metrics)
     */
    val metrics = Metrics

    object Metrics : DependencyGroup(group = "androidx.metrics") {

        /**
         * ### API reference:
         * - [androidx.metrics.performance](https://developer.android.com/reference/kotlin/androidx/metrics/performance/package-summary)
         */
        val performance = module("metrics-performance")
    }

    /**
     * Deploy applications with multiple dex files on pre-Android 5 devices.
     *
     * Guide: [Enable multidex for apps with over 64K methods](https://developer.android.com/studio/build/multidex)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/multidex)
     */
    val multidex = MultiDex

    object MultiDex : DependencyNotationAndGroup("androidx.multidex", "multidex") {
        val instrumentation = module("multidex-instrumentation")
    }

    /**
     * Navigation is a framework for navigating between 'destinations' within an Android application that
     * provides a consistent API whether destinations are implemented as Fragments, Activities,
     * or other components.
     *
     * Guide: [Navigation](https://developer.android.com/guide/navigation)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/navigation)
     *
     * ### API reference:
     * - [androidx.navigation](https://developer.android.com/reference/kotlin/androidx/navigation/package-summary)
     */
    val navigation = Navigation

    object Navigation : DependencyGroup(group = "androidx.navigation") {

        /**
         * Support for Fragment destinations, with Kotlin extensions.
         *
         * ### API reference:
         * - [androidx.navigation.fragment](https://developer.android.com/reference/kotlin/androidx/navigation/fragment/package-summary)
         */
        val fragmentKtx = module("navigation-fragment-ktx")

        /**
         * NavigationUI, with Kotlin extensions.
         *
         * ### API reference:
         * - [androidx.navigation.ui](https://developer.android.com/reference/kotlin/androidx/navigation/ui/package-summary)
         */
        val uiKtx = module("navigation-ui-ktx")

        /**
         * Jetpack Compose integration
         *
         * Guide: [Navigating with Compose](https://developer.android.com/jetpack/compose/navigation)
         *
         * ### API reference:
         * - [androidx.navigation.compose](https://developer.android.com/reference/kotlin/androidx/navigation/compose/package-summary)
         */
        val compose = module("navigation-compose")

        /**
         * Feature module support
         *
         * ### API reference:
         * - [androidx.navigation.dynamicfeatures.fragment](https://developer.android.com/reference/kotlin/androidx/navigation/dynamicfeatures/fragment/package-summary)
         * - [androidx.navigation.dynamicfeatures.ui](https://developer.android.com/reference/kotlin/androidx/navigation/dynamicfeatures/ui/package-summary)
         */
        val dynamicFeaturesFragment = module("navigation-dynamic-features-fragment")

        val safeArgsGenerator = module("navigation-safe-args-generator")
        val safeArgsGradlePlugin = module("navigation-safe-args-gradle-plugin")

        /**
         * Testing Navigation
         *
         * ### API reference:
         * - [androidx.navigation](https://developer.android.com/reference/kotlin/androidx/navigation/package-summary)
         */
        val testing = module("navigation-testing")

        // All the Navigation artifacts below are transitively included in fragmentKtx and uiKtx.

        /** Included in other Navigation KTX and Compose artifacts. */
        val commonKtx = module("navigation-common-ktx")

        /** Included in other Navigation KTX and Compose artifacts. */
        val runtimeKtx = module("navigation-runtime-ktx")

        /**
         * Support for Fragment destinations, includes only Java APIs.
         *
         * ### API reference:
         * - [androidx.navigation.fragment](https://developer.android.com/reference/kotlin/androidx/navigation/fragment/package-summary)
         *
         * @see AndroidX.Navigation.fragmentKtx
         */
        val fragment = module("navigation-fragment")

        /**
         * NavigationUI, includes only Java APIs.
         *
         * ### API reference:
         * - [androidx.navigation.ui](https://developer.android.com/reference/androidx/navigation/ui/package-summary)
         *
         * @see AndroidX.Navigation.uiKtx
         */
        val ui = module("navigation-ui")

        /** Included in other Navigation artifacts. */
        val runtime = module("navigation-runtime")

        /** Included in other Navigation artifacts. */
        val common = module("navigation-common")
    }

    /**
     * The Paging Library makes it easier for you to load data gradually and gracefully within your app's RecyclerView,
     * LazyColumn, or LazyRow.
     *
     * Guide: [Paging library overview](https://developer.android.com/topic/libraries/architecture/paging/v3-overview)
     *
     * Codelab: [d.android.com/codelabs/android-paging](https://developer.android.com/codelabs/android-paging)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/paging)
     *
     * ### API reference:
     * - [androidx.paging](https://developer.android.com/reference/kotlin/androidx/paging/package-summary)
     */
    val paging = Paging

    object Paging : DependencyGroup(group = "androidx.paging") {

        /** Kotlin extensions, without Android dependencies, for tests */
        val commonKtx = module("paging-common-ktx")

        /** Runtime with Kotlin extensions */
        val runtimeKtx = module("paging-runtime-ktx")

        /**
         * Jetpack Compose integration
         *
         * ### API reference:
         * - [androidx.paging.compose](https://developer.android.com/reference/kotlin/androidx/paging/compose/package-summary)
         */
        val compose = module("paging-compose")

        /**
         * Test artifact for Paging implementation
         *
         * ### API reference:
         * - [androidx.paging.testing](https://developer.android.com/reference/androidx/paging/testing/package-summary)
         */
        val testing = module("paging-testing")

        /**
         * RxJava2 support with Kotlin extensions
         *
         * ### API reference:
         * - [androidx.paging.rxjava2](https://developer.android.com/reference/kotlin/androidx/paging/rxjava2/package-summary)
         */
        val rxJava2Ktx = module("paging-rxjava2-ktx")

        /** Guava ListenableFuture support */
        val guava = module("paging-guava")

        /**
         * Without Android dependencies, for tests
         *
         * @see AndroidX.Paging.commonKtx
         */
        val common = module("paging-common")

        /**
         * Runtime
         *
         * @see AndroidX.Paging.runtimeKtx
         */
        val runtime = module("paging-runtime")

        /**
         * RxJava2 support
         *
         * ### API reference:
         * - [androidx.paging.rxjava2](https://developer.android.com/reference/kotlin/androidx/paging/rxjava2/package-summary)
         *
         * @see AndroidX.Paging.rxJava2Ktx
         */
        val rxJava2 = module("paging-rxjava2")

        /** RxJava3 support
         *
         * ### API reference:
         * - [androidx.paging.rxjava3](https://developer.android.com/reference/kotlin/androidx/paging/rxjava3/package-summary)
         */
        val rxJava3 = module("paging-rxjava3")
    }

    /**
     * Extract representative color palettes from images.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/palette)
     *
     * ### API reference:
     * - [androidx.palette.graphics](https://developer.android.com/reference/kotlin/androidx/palette/graphics/package-summary)
     *
     * @see AndroidX.Palette.ktx
     */
    val palette = Palette

    object Palette : DependencyNotationAndGroup("androidx.palette", "palette") {

        /** Kotlin extensions */
        val ktx = module("palette-ktx")
    }

    /**
     * Build interactive settings screens without needing to interact with device storage or manage the UI.
     *
     * Guide: [Settings](https://developer.android.com/guide/topics/ui/settings)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/preference)
     *
     * ### API reference:
     * - [androidx.preference](https://developer.android.com/jetpack/androidx/releases/preference)
     *
     * @see AndroidX.Preference.ktx
     */
    val preference = Preference

    object Preference : DependencyNotationAndGroup("androidx.preference", "preference") {

        /** Kotlin extensions */
        val ktx = module("preference-ktx")
    }

    /**
     * Print photos, docs, and other graphics and images from your app.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/print)
     *
     * ### API reference:
     * - [androidx.print](https://developer.android.com/reference/kotlin/androidx/print/package-summary)
     */
    val print = DependencyNotation("androidx.print", "print")

    /**
     * Promote content to the Android TV Launcher home screen.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/recommendation)
     *
     * ### API reference:
     * - [androidx.recommendation.app](https://developer.android.com/reference/kotlin/androidx/recommendation/app/package-summary)
     */
    val recommendation = DependencyNotation("androidx.recommendation", "recommendation")

    /**
     * Display large sets of data in your UI while minimizing memory usage.
     *
     * Guide: [Create dynamic lists with RecyclerView](https://developer.android.com/guide/topics/ui/layout/recyclerview)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/recyclerview)
     *
     * ### API reference:
     * - [androidx.recyclerview.widget](https://developer.android.com/reference/kotlin/androidx/recyclerview/widget/package-summary)
     */
    val recyclerView = RecyclerView

    object RecyclerView : DependencyNotationAndGroup("androidx.recyclerview", "recyclerview") {

        /**
         * For control over item selection of both touch and mouse driven selection
         *
         * ### API reference:
         * - [androidx.recyclerview.selection](https://developer.android.com/reference/kotlin/androidx/recyclerview/selection/package-summary)
         */
        val selection = module("recyclerview-selection")
    }

    /**
     * Create a wrapper that makes it easier for developers to provide a PendingIntent.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/remotecallback)
     *
     * ### API reference:
     * - [androidx.remotecallback](https://developer.android.com/reference/kotlin/androidx/remotecallback/package-summary)
     */
    val remoteCallback = RemoteCallback

    object RemoteCallback : DependencyNotationAndGroup("androidx.remotecallback", "remotecallback") {
        val processor = module("remotecallback-processor")
    }

    /**
     * The Room persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
     *
     * Guide: [Save data in a local database using Room](https://developer.android.com/training/data-storage/room)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/room)
     *
     * ### API reference:
     * - [androidx.room](https://developer.android.com/reference/kotlin/androidx/room/package-summary)
     *
     * @see CashApp.sqlDelight
     */
    val room = Room

    object Room : DependencyGroup(group = "androidx.room") {

        /**
         * Kotlin Extensions and Coroutines support for Room
         */
        val ktx = module("room-ktx")

        /**
         * Paging 3 Integration
         */
        val paging = Paging

        object Paging : DependencyNotationAndGroup(
            group = group,
            name = "room-paging"
        ) {
            val guava = module("room-paging-guava")
            val rxJava2 = module("room-paging-rxjava2")
            val rxJava3 = module("room-paging-rxjava3")
        }

        /**
         * Room compiler, compatible with ksp, kapt, and Java annotation processors.
         */
        val compiler = module("room-compiler")

        /**
         * Test helpers
         *
         * ### API reference:
         * - [androidx.room.testing](https://developer.android.com/reference/kotlin/androidx/room/testing/package-summary)
         * - [androidx.room.migration](https://developer.android.com/reference/kotlin/androidx/room/migration/package-summary)
         */
        val testing = module("room-testing")

        /**
         * This is the main Room artifact. Use it for Java-only apps.
         *
         * @see AndroidX.Room.ktx
         */
        val runtime = module("room-runtime")

        /**
         * Includes annotations and APIs that don't depend on Android.
         * Useful if you need to annotate classes that are shared with other platforms,
         * or to put your Room models in a JVM library that doesn't need to use Android
         * specific APIs.
         */
        val common = module("room-common")

        /**
         * Guava support for Room, including Optional and ListenableFuture
         */
        val guava = module("room-guava")

        /**
         * RxJava2 support for Room
         *
         * _Note: There's no online API reference left for this RxJava2 artifact, but the API surface should be the same
         * as for [rxJava3], so you can look at [androidx.room.rxjava3](https://developer.android.com/reference/kotlin/androidx/room/rxjava3/package-summary)
         * instead._
         */
        val rxJava2 = module("room-rxjava2")

        /**
         * RxJava3 support for Room
         *
         * ### API reference:
         * - [androidx.room.rxjava3](https://developer.android.com/reference/kotlin/androidx/room/rxjava3/package-summary)
         */
        val rxJava3 = module("room-rxjava3")
    }

    /**
     * Write pluggable components that save the UI state when a process dies, and restore it when the process restarts.
     *
     * Guide: [Saved State module for ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel-savedstate)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/savedstate)
     *
     * ### API reference:
     * - [androidx.savedstate](https://developer.android.com/reference/kotlin/androidx/savedstate/package-summary)
     *
     * @see AndroidX.SavedState.ktx
     */
    val savedState = SavedState

    object SavedState : DependencyNotationAndGroup("androidx.savedstate", "savedstate") {

        /** Kotlin extensions */
        val ktx = module("savedstate-ktx")
    }

    /**
     * Safely manage keys and encrypt files and sharedpreferences.
     *
     * Guide: [Work with data more securely](https://developer.android.com/topic/security/data)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/security)
     *
     * ### API reference:
     * - [androidx.security.crypto](https://developer.android.com/reference/kotlin/androidx/security/crypto/package-summary)
     */
    val security = Security

    object Security : DependencyGroup(group = "androidx.security") {

        /**
         * ### API reference:
         * - [androidx.security.crypto](https://developer.android.com/reference/kotlin/androidx/security/crypto/package-summary)
         */
        val crypto = module("security-crypto")

        /**
         * ### API reference:
         * - [androidx.security.crypto](https://developer.android.com/reference/kotlin/androidx/security/crypto/package-summary)
         */
        val cryptoKtx = module("security-crypto-ktx")

        /**
         * App Authentication APIs
         *
         * ### API reference:
         * - [androidx.security.app.authenticator](https://developer.android.com/reference/kotlin/androidx/security/app/authenticator/package-summary)
         */
        val appAuthenticator = module("security-app-authenticator")

        /**
         * App Authentication API testing
         *
         * ### API reference:
         * - [androidx.security.app.authenticator](https://developer.android.com/reference/kotlin/androidx/security/app/authenticator/package-summary)
         */
        val appAuthenticatorTesting = module("security-app-authenticator-testing")

        /**
         * Identity Credential APIs
         *
         * ### API reference:
         * - [androidx.security.identity](https://developer.android.com/reference/kotlin/androidx/security/identity/package-summary)
         */
        val identityCredential = module("security-identity-credential")
    }

    /**
     * Provide backwards compatibility for using shortcuts as direct share targets.
     *
     * Guide: [Receiving simple data from other apps -> Using AndroidX to provide both Sharing Shortcuts and ChooserTargets](https://developer.android.com/training/sharing/receive#androidx-compat-library)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/sharetarget)
     */
    val shareTarget = DependencyNotation("androidx.sharetarget", "sharetarget")

    /**
     * Display templated UI elements outside your app.
     *
     * Guide: [Slices](https://developer.android.com/guide/slices)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/slice)
     *
     * ### API reference:
     * - [androidx.slice](https://developer.android.com/reference/kotlin/androidx/slice/package-summary)
     */
    val slice = Slice

    object Slice : DependencyGroup(group = "androidx.slice") {

        /**
         * ### API reference:
         * - [androidx.slice.builders](https://developer.android.com/reference/kotlin/androidx/slice/builders/package-summary)
         */
        val buildersKtx = module("slice-builders-ktx")

        /**
         * ### API reference:
         * - [androidx.slice.builders](https://developer.android.com/reference/kotlin/androidx/slice/builders/package-summary)
         *
         * @see buildersKtx
         */
        val builders = module("slice-builders")

        /**
         * ### API reference:
         * - [androidx.slice.core](https://developer.android.com/reference/kotlin/androidx/slice/core/package-summary)
         */
        val core = module("slice-core")

        /**
         * ### API reference:
         * - [androidx.slice.widget](https://developer.android.com/reference/kotlin/androidx/slice/widget/package-summary)
         */
        val view = module("slice-view")
    }

    /**
     * Implement a sliding pane UI pattern.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/slidingpanelayout)
     *
     * ### API reference:
     * - [androidx.slidingpanelayout.widget](https://developer.android.com/reference/kotlin/androidx/slidingpanelayout/widget/package-summary)
     */
    val slidingPaneLayout = DependencyNotation("androidx.slidingpanelayout", "slidingpanelayout")

    /**
     * The `androidx.sqlite` library contains abstract interfaces along with basic implementations
     * which can be used to build your own libraries that access SQLite.
     *
     * You might want to consider using the [AndroidX.Room] library, which provides
     * an abstraction layer over SQLite to allow for more robust database access while
     * harnessing the full power of SQLite, or [CashApp.sqlDelight] which generates Kotlin
     * multiplatform code from compile-time checked SQL queries.
     *
     * Guide: [Save data using SQLite](https://developer.android.com/training/data-storage/sqlite)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/sqlite)
     *
     * ### API reference:
     * - [androidx.sqlite.db](https://developer.android.com/reference/kotlin/androidx/sqlite/db/package-summary)
     *
     * @see AndroidX.Sqlite.ktx
     */
    val sqlite = Sqlite

    object Sqlite : DependencyNotationAndGroup("androidx.sqlite", "sqlite") {

        /** Kotlin extensions */
        val ktx = module("sqlite-ktx")

        /**
         * Implementation of the AndroidX SQLite interfaces via the Android framework APIs.
         *
         * ### API reference:
         * - [androidx.sqlite.db.framework](https://developer.android.com/reference/kotlin/androidx/sqlite/db/framework/package-summary)
         */
        val framework = module("sqlite-framework")
    }

    /**
     * Implement a straightforward, performant way to initialize components at app startup.
     *
     * User guide: [App Startup](https://developer.android.com/topic/libraries/app-startup)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/startup)
     *
     * ### API reference:
     * - [androidx.startup](https://developer.android.com/reference/kotlin/androidx/startup/package-summary)
     */
    val startup = Startup

    object Startup : DependencyGroup(group = "androidx.startup") {
        val runtime = module("startup-runtime")
    }

    /**
     * Implement the swipe-to-refresh UI pattern.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/swiperefreshlayout)
     *
     * ### API reference:
     * - [androidx.swiperefreshlayout.widget](https://developer.android.com/reference/kotlin/androidx/swiperefreshlayout/widget/package-summary)
     */
    val swipeRefreshLayout = DependencyNotation("androidx.swiperefreshlayout", "swiperefreshlayout")

    /**
     * Testing in Android.
     *
     * Guide: [Test apps on Android](https://developer.android.com/training/testing)
     *
     * [Samples and codelabs](https://developer.android.com/training/testing/additional-resources#samples)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/test)
     *
     * ### API reference:
     * - [androidx.test](https://developer.android.com/reference/kotlin/androidx/test/packages)
     */
    val test = Test

    object Test : DependencyGroup(group = "androidx.test") {

        /**
         * androidx.test.code APIs. Includes Kotlin extensions.
         */
        val coreKtx = module("core-ktx")

        /**
         * androidx.test.code APIs.
         *
         * @see coreKtx
         */
        val core = module("core")

        val monitor = module("monitor")

        /**
         * Android test orchestrator.
         */
        val orchestrator = module("orchestrator")

        val rules = module("rules")
        val runner = module("runner")

        val ext = Ext

        object Ext : DependencyGroup(group = "androidx.test.ext") {

            /**
             * JUnit extensions APIs.
             *
             * @see AndroidX.Test.Ext.JUnit.ktx
             */
            val junit = JUnit

            object JUnit : DependencyNotationAndGroup(group = group, name = "junit") {

                /**
                 * JUnit extensions APIs. Includes Kotlin extensions.
                 */
                val ktx = module("junit-ktx")

                /**
                 * Run GTest tests on Android devices
                 */
                val gTest = module("junit-gtest")
            }

            /**
             * [Truth](https://github.com/google/truth) extensions APIs.
             *
             * @see AndroidX.Test.Ext.JUnit.ktx
             */
            val truth = module("truth")
        }

        val services = DependencyNotation("androidx.test.services", "test-services")

        /**
         * UI Automator is a UI testing framework suitable for cross-app
         * functional UI testing across system and installed apps.
         *
         * Guides:
         * - [Test UI for multiple apps](https://developer.android.com/training/testing/ui-testing/uiautomator-testing)
         * - [UI Automator](https://developer.android.com/training/testing/ui-automator)
         *
         * ### API reference:
         * - [androidx.test.uiautomator](https://developer.android.com/reference/kotlin/androidx/test/uiautomator/package-summary)
         */
        val uiAutomator = DependencyNotation("androidx.test.uiautomator", "uiautomator")

        /**
         * Use Espresso to write concise, beautiful, and reliable Android UI tests.
         *
         * Guide: [Espresso](https://developer.android.com/training/testing/espresso)
         */
        val espresso = Espresso

        object Espresso : DependencyGroup(group = "androidx.test.espresso") {

            val core = module("espresso-core")

            val device = module("espresso-device")

            /**
             * Guide: [Espresso lists: Interact with recycler view list items](https://developer.android.com/training/testing/espresso/lists#recycler-view-list-items)
             */
            val contrib = module("espresso-contrib")

            /**
             * An idling resource represents an asynchronous operation whose results affect subsequent operations in
             * a UI test. By registering idling resources with Espresso, you can validate these asynchronous operations
             * more reliably when testing your app.
             *
             * Guide: [Espresso idling resources](https://developer.android.com/training/testing/espresso/idling-resource)
             */
            val idlingResource = module("espresso-idling-resource")

            /**
             * Espresso-Intents is an extension to Espresso, which enables validation and stubbing of
             * intents sent out by the application under test. It’s like Mockito, but for Android Intents.
             *
             * If your app delegates functionality to other apps or the platform, you can use Espresso-Intents to
             * focus on your own app's logic while assuming that other apps or the platform will function correctly.
             * With Espresso-Intents, you can match and validate your outgoing intents or even provide
             * stub responses in place of actual intent responses.
             *
             * Guide: [Espresso-Intents](https://developer.android.com/training/testing/espresso/intents)
             */
            val intents = module("espresso-intents")

            /**
             * Testing for accessibility lets you experience your app from the perspective of your entire user base,
             * including users with accessibility needs. This form of testing can reveal opportunities to
             * make your app more powerful and versatile.
             *
             * Guide: [Accessibility checking](https://developer.android.com/training/testing/espresso/accessibility-checking)
             */
            val accessibility = module("espresso-accessibility")

            /**
             * As your app grows, you might find it useful to place some of your app components in
             * a process other than your app's main process. To test app components in these
             * non-default processes, you can use the functionality of Multiprocess Espresso.
             * This tool, available on Android 8.0 (API level 26) and higher, allows you to
             * seamlessly test your app's UI interactions that cross your app's process boundaries
             * while maintaining Espresso's synchronization guarantees.
             *
             * Guide: [Multiprocess Espresso](https://developer.android.com/training/testing/espresso/multiprocess)
             */
            val remote = module("espresso-remote")

            /**
             * Espresso-Web is an entry point to work with Android WebView UI components.
             *
             * Guide: [Espresso Web](https://developer.android.com/training/testing/espresso/web)
             */
            val web = module("espresso-web")

            val idling = Idling

            object Idling : DependencyGroup(group = "androidx.test.espresso.idling") {
                val concurrent = module("idling-concurrent")
                val net = module("idling-net")
            }
        }
    }

    /**
     * Identifies conversations, links, selections, and other similar constructs in text.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/textclassifier)
     *
     * ### API reference:
     * - [androidx.textclassifier](https://developer.android.com/reference/kotlin/androidx/textclassifier/package-summary)
     */
    val textClassifier = DependencyNotation("androidx.textclassifier", "textclassifier")

    /**
     * Write trace events to the system trace buffer.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/tracing)
     *
     * ### API reference:
     * - [androidx.tracing](https://developer.android.com/reference/kotlin/androidx/tracing/package-summary)
     *
     * @see AndroidX.Tracing.ktx
     */
    val tracing = Tracing

    object Tracing : DependencyNotationAndGroup("androidx.tracing", "tracing") {

        /** Kotlin extensions */
        val ktx = module("tracing-ktx")

        /**
         * AndroidX Tracing: Perfetto SDK
         *
         * ### API reference:
         * - [androidx.tracing.perfetto](https://developer.android.com/reference/androidx/tracing/perfetto/package-summary)
         */
        val perfetto = module("tracing-perfetto")
    }

    /**
     * Animate motion in the UI with starting and ending layouts.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/transition)
     *
     * ### API reference:
     * - [androidx.transition](https://developer.android.com/reference/kotlin/androidx/transition/package-summary)
     *
     * @see transitionKtx
     */
    val transition = DependencyNotation("androidx.transition", "transition")

    /**
     * Animate motion in the UI with starting and ending layouts. Includes Kotlin extensions.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/transition)
     *
     * ### API reference:
     * - [androidx.transition](https://developer.android.com/reference/kotlin/androidx/transition/package-summary)
     */
    val transitionKtx = DependencyNotation("androidx.transition", "transition-ktx")

    /**
     * Provides developers with Compose and Material design functionalities in order to write applications for TV.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/tv)
     */
    val tv = Tv

    object Tv : DependencyGroup(group = "androidx.tv") {
        /**
         * This library makes it easier for developers to write Jetpack Compose applications for
         * TV devices by providing functionality to support TV specific devices sizes,
         * shapes and d-pad navigation supported components. It builds upon the Jetpack Compose libraries.
         *
         * ### API reference:
         * - [androidx.tv.foundation](https://developer.android.com/reference/androidx/tv/foundation/package-summary)
         * - [androidx.tv.foundation.lazy.grid](https://developer.android.com/reference/androidx/tv/foundation/lazy/grid/package-summary)
         * - [androidx.tv.foundation.lazy.list](https://developer.android.com/reference/androidx/tv/foundation/lazy/list/package-summary)
         */
        val foundation = module("tv-foundation")

        /**
         * Build TV applications using controls that adhere to Material Design Language.
         *
         * ### API reference:
         * - [androidx.tv.material](https://developer.android.com/reference/androidx/tv/material/package-summary)
         * - [androidx.tv.material.carousel](https://developer.android.com/reference/androidx/tv/material/carousel/package-summary)
         * - [androidx.tv.material.immersivelist](https://developer.android.com/reference/androidx/tv/material/immersivelist/package-summary)
         */
        val material = module("tv-material")
    }

    /**
     * Provide Android TV channels.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/tvprovider)
     *
     * ### API reference:
     * - [androidx.tvprovider.media.tv](https://developer.android.com/reference/kotlin/androidx/tvprovider/media/tv/package-summary)
     */
    val tvProvider = DependencyNotation("androidx.tvprovider", "tvprovider")

    /**
     * Render vector graphics.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/vectordrawable)
     *
     * ### API reference:
     * - [androidx.vectordrawable.graphics.drawable](https://developer.android.com/reference/kotlin/androidx/vectordrawable/graphics/drawable/package-summary)
     */
    val vectorDrawable = VectorDrawable

    object VectorDrawable : DependencyNotationAndGroup("androidx.vectordrawable", "vectordrawable") {

        /**
         * Adds ability to animate properties of a VectorDrawable.
         * Useful for illustration purposes or state changes in response to user events.
         */
        val animated = module("vectordrawable-animated")

        /**
         * Adds a seekable alternative to [animated].
         */
        val seekable = module("vectordrawable-seekable")
    }

    /**
     * Provides a stable and compact binary serialization format that can be passed across processes or persisted safely.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/versionedparcelable)
     *
     * ### API reference:
     * - [androidx.versionedparcelable](https://developer.android.com/reference/kotlin/androidx/versionedparcelable/package-summary)
     */
    val versionedParcelable = DependencyNotation("androidx.versionedparcelable", "versionedparcelable")

    /**
     * Display Views or Fragments in a swipeable format.
     *
     * Guide: [Create swipe views with tabs using ViewPager](https://developer.android.com/guide/navigation/navigation-swipe-view)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/viewpager2)
     *
     * ### API reference:
     * - [androidx.viewpager.widget](https://developer.android.com/reference/kotlin/androidx/viewpager/widget/package-summary)
     *
     * @see viewPager2
     */
    val viewPager = DependencyNotation("androidx.viewpager", "viewpager")

    /**
     * Display Views or Fragments in a swipeable format.
     *
     * Guide: [Create swipe views with tabs using ViewPager2](https://developer.android.com/guide/navigation/navigation-swipe-view-2)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/viewpager2)
     *
     * ### API reference:
     * - [androidx.viewpager2.adapter](https://developer.android.com/reference/kotlin/androidx/viewpager2/adapter/package-summary)
     * - [androidx.viewpager2.widget](https://developer.android.com/reference/kotlin/androidx/viewpager2/widget/package-summary)
     */
    val viewPager2 = DependencyNotation("androidx.viewpager2", "viewpager2")

    /**
     * Wear OS base Jetpack library.
     *
     * Wear OS home page: [d.android.com/wear](https://developer.android.com/wear)
     *
     * Guide: [Get started with Wear OS](https://developer.android.com/training/wearables)
     *
     * Samples: [github.com/android/wear-os-samples](https://github.com/android/wear-os-samples)
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/wear)
     *
     * ### API reference:
     * - [androidx.wear.activity](https://developer.android.com/reference/kotlin/androidx/wear/activity/package-summary)
     * - [androidx.wear.ambient](https://developer.android.com/reference/kotlin/androidx/wear/ambient/package-summary)
     * - [androidx.wear.utils](https://developer.android.com/reference/kotlin/androidx/wear/utils/package-summary)
     * - [androidx.wear.widget](https://developer.android.com/reference/kotlin/androidx/wear/widget/package-summary)
     * - [androidx.wear.widget.drawer](https://developer.android.com/reference/kotlin/androidx/wear/widget/drawer/package-summary)
     */
    val wear = Wear

    object Wear : DependencyNotationAndGroup(group = "androidx.wear", name = "wear") {

        /**
         * Add support for wearable specific inputs.
         *
         * ### API reference:
         * - [androidx.wear.input](https://developer.android.com/reference/kotlin/androidx/wear/input/package-summary)
         */
        val input = module("wear-input")

        /**
         * Test utilities for [input].
         *
         * ### API reference:
         * - [androidx.wear.input.testing](https://developer.android.com/reference/kotlin/androidx/wear/input/testing/package-summary)
         */
        val inputTesting = module("wear-input-testing")

        /**
         * Use to implement wear ongoing activities.
         *
         * Codelab: [d.android.com/codelabs/ongoing-activity](https://developer.android.com/codelabs/ongoing-activity)
         *
         * ### API reference:
         * - [androidx.wear.ongoing](https://developer.android.com/reference/kotlin/androidx/wear/ongoing/package-summary)
         */
        val ongoing = module("wear-ongoing")

        /**
         * Use to implement support for interactions from the Wearables to Phones.
         *
         * ### API reference:
         * - [androidx.wear.phone.interactions](https://developer.android.com/reference/kotlin/androidx/wear/phone/interactions/package-summary)
         * - [androidx.wear.phone.interactions.authentication](https://developer.android.com/reference/kotlin/androidx/wear/phone/interactions/authentication/package-summary)
         * - [androidx.wear.phone.interactions.notifications](https://developer.android.com/reference/kotlin/androidx/wear/phone/interactions/notifications/package-summary)
         */
        val phoneInteractions = module("wear-phone-interactions")

        /**
         * Use to implement support for interactions between the Wearables and Phones.
         *
         * ### API reference:
         * - [androidx.wear.remote.interactions](https://developer.android.com/reference/kotlin/androidx/wear/remote/interactions/package-summary)
         */
        val remoteInteractions = module("wear-remote-interactions")

        /**
         * Write Jetpack Compose applications for Wearable devices by providing functionality to support
         * wearable specific devices, sizes, shapes and navigation gestures.
         *
         * Codelab: [d.android.com/codelabs/compose-for-wear-os](https://developer.android.com/codelabs/compose-for-wear-os)
         *
         * [Release notes](https://developer.android.com/jetpack/androidx/releases/wear-compose)
         *
         * ### API reference:
         * - [androidx.window.layout](https://developer.android.com/reference/kotlin/androidx/window/layout/package-summary)
         */
        val compose = Compose

        object Compose : DependencyGroup(group = "androidx.wear.compose") {

            /**
             * ### API reference:
             * - [androidx.wear.compose.foundation](https://developer.android.com/reference/kotlin/androidx/wear/compose/foundation/package-summary)
             */
            val foundation = module("compose-foundation")

            /**
             * _NOTE: DO NOT INCLUDE a dependency on `Androidx.compose.material`.
             * `Androidx.wear.compose.material` is designed as a replacement,
             * not an addition to `Androidx.compose.material`._
             *
             * _If there are features from that you feel are missing from
             * `androidx.wear.compose:compose-material`, please [file an issue](https://issuetracker.google.com/issues/new?component=1077552&template=1598429)
             * to let the AndroidX Wear team know._
             *
             * ### API reference:
             * - [androidx.wear.compose.material](https://developer.android.com/reference/kotlin/androidx/wear/compose/material/package-summary)
             */
            val material = module("compose-material")

            /**
             * Integration between Wear Compose and Androidx Navigation libraries.
             *
             * ### API reference:
             * - [androidx.wear.compose.navigation](https://developer.android.com/reference/kotlin/androidx/wear/compose/navigation/package-summary)
             */
            val navigation = module("compose-navigation")
        }

        /**
         * Use this dependency to implement Wear OS Tiles.
         *
         * [Release notes](https://developer.android.com/jetpack/androidx/releases/wear-tiles)
         *
         * Codelab: [d.android.com/codelabs/wear-tiles](https://developer.android.com/codelabs/wear-tiles)
         *
         * ### API reference
         * - [androidx.wear.tiles](https://developer.android.com/reference/kotlin/androidx/wear/tiles/package-summary)
         */
        val tiles = Tiles

        object Tiles : DependencyNotationAndGroup(group = "androidx.wear.tiles", name = "tiles") {

            /**
             * Material components library for Android Wear Tiles.
             */
            val material = module("tiles-material")

            /**
             * Android Wear Tiles Renderer components.
             * These components can be used to parse and render an already constructed Wear Tile.
             *
             * Use to preview wear tiles in your own app.
             *
             * ### API reference
             * - [androidx.wear.tiles.renderer](https://developer.android.com/reference/kotlin/androidx/wear/tiles/renderer/package-summary)
             */
            val renderer = module("tiles-renderer")

            /**
             * Android Wear Tiles Testing Utilities.
             *
             * Use to test wear tiles.
             *
             * ### API reference
             * - [androidx.wear.tiles.testing](https://developer.android.com/reference/kotlin/androidx/wear/tiles/testing/package-summary)
             */
            val testing = module("tiles-testing")
        }

        /**
         * Use this dependency to implement Watch Faces for WearOS
         *
         * [Release notes](https://developer.android.com/jetpack/androidx/releases/wear-watchface)
         *
         * ### API reference
         * - [androidx.wear.watchface](https://developer.android.com/reference/kotlin/androidx/wear/watchface/package-summary)
         * - [androidx.wear.watchface.style](https://developer.android.com/reference/kotlin/androidx/wear/watchface/style/package-summary)
         */
        val watchFace = WatchFace

        object WatchFace : DependencyNotationAndGroup(group = "androidx.wear.watchface", name = "watchface") {

            /**
             * Use these dependencies to implement Watch Faces complications for WearOS
             *
             * Codelab: [d.android.com/codelabs/data-providers](https://developer.android.com/codelabs/data-providers)
             *
             * ### API reference
             * - [androidx.wear.watchface.complications](https://developer.android.com/reference/kotlin/androidx/wear/watchface/complications/package-summary)
             * - [androidx.wear.watchface.complications.data](https://developer.android.com/reference/kotlin/androidx/wear/watchface/complications/data/package-summary)
             * - [androidx.wear.watchface.complications.datasource](https://developer.android.com/reference/kotlin/androidx/wear/watchface/complications/datasource/package-summary)
             * - [androidx.wear.watchface.complications.datasource.rendering](https://developer.android.com/reference/kotlin/androidx/wear/watchface/complications/rendering/package-summary)
             */
            val complications = Complications

            object Complications : DependencyGroup(group = group) {

                /**
                 * Support for rendering complications on the watch face.
                 *
                 * ### API reference
                 * - [androidx.wear.watchface.complications.rendering](https://developer.android.com/reference/kotlin/androidx/wear/watchface/complications/rendering/package-summary)
                 */
                val rendering = module("watchface-complications-rendering")

                /**
                 * Use this dependency to implement Watch Faces complications for WearOS in Java-only.
                 * Use [dataSourceKtx] to also get Kotlin extensions.
                 *
                 * ### API reference
                 * - [androidx.wear.watchface.complications](https://developer.android.com/reference/kotlin/androidx/wear/watchface/complications/package-summary)
                 * - [androidx.wear.watchface.complications.data](https://developer.android.com/reference/kotlin/androidx/wear/watchface/complications/data/package-summary)
                 * - [androidx.wear.watchface.complications.datasource](https://developer.android.com/reference/kotlin/androidx/wear/watchface/complications/datasource/package-summary)
                 * - [androidx.wear.watchface.complications.datasource.rendering](https://developer.android.com/reference/kotlin/androidx/wear/watchface/complications/rendering/package-summary)
                 */
                val dataSource = module("watchface-complications-data-source")

                /**
                 * Use this dependency to implement Watch Faces complications for WearOS in Kotlin.
                 *
                 * ### API reference
                 * - [androidx.wear.watchface.complications](https://developer.android.com/reference/kotlin/androidx/wear/watchface/complications/package-summary)
                 * - [androidx.wear.watchface.complications.data](https://developer.android.com/reference/kotlin/androidx/wear/watchface/complications/data/package-summary)
                 * - [androidx.wear.watchface.complications.datasource](https://developer.android.com/reference/kotlin/androidx/wear/watchface/complications/datasource/package-summary)
                 * - [androidx.wear.watchface.complications.datasource.rendering](https://developer.android.com/reference/kotlin/androidx/wear/watchface/complications/rendering/package-summary)
                 */
                val dataSourceKtx = module("watchface-complications-data-source-ktx")
            }

            /**
             * Use this dependency to implement a watchface style and complication editor.
             *
             * ### API reference
             * - [androidx.wear.watchface.editor](https://developer.android.com/reference/kotlin/androidx/wear/watchface/editor/package-summary)
             * - [androidx.wear.watchface.client](https://developer.android.com/reference/kotlin/androidx/wear/watchface/client/package-summary)
             */
            val editor = module("watchface-editor")
        }
    }

    /**
     * Work with modern WebView APIs on Android 5 and above.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/webkit)
     *
     * Guide: [Managing WebView objects](https://developer.android.com/guide/webapps/managing-webview)
     *
     * ### API reference:
     * - [androidx.webkit](https://developer.android.com/reference/kotlin/androidx/webkit/package-summary)
     */
    val webkit = DependencyNotation(group = "androidx.webkit", name = "webkit")

    /**
     * The Jetpack WindowManager library enables application developers to support new
     * device form factors and multi-window environments. The library provides a common
     * API surface for API versions 14 and later.
     *
     * The initial release targets foldable devices,
     * but future versions will extend to more display types and window features.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/window)
     *
     * ### API reference:
     * - [androidx.window.layout](https://developer.android.com/reference/kotlin/androidx/window/layout/package-summary)
     */
    val window = Window

    object Window : DependencyNotationAndGroup(group = "androidx.window", name = "window") {

        /**
         * ### API reference:
         * - [androidx.window.testing.layout](https://developer.android.com/reference/kotlin/androidx/window/testing/layout/package-summary)
         */
        val testing = module("window-testing")

        /**
         * For Java-friendly APIs to register and unregister callbacks
         *
         * ### API reference:
         * - [androidx.window.java.layout](https://developer.android.com/reference/kotlin/androidx/window/java/layout/package-summary)
         */
        val java = module("window-java")

        /**
         * ### API reference:
         * - [androidx.window.rxjava2.layout](https://developer.android.com/reference/kotlin/androidx/window/rxjava2/layout/package-summary)
         */
        val rxJava2 = module("window-rxjava2")

        /**
         * ### API reference:
         * - [androidx.window.rxjava3.layout](https://developer.android.com/reference/kotlin/androidx/window/rxjava3/layout/package-summary)
         */
        val rxJava3 = module("window-rxjava3")
    }

    /**
     * The WorkManager API makes it easy to schedule deferrable, asynchronous tasks that must be run reliably.
     * These APIs let you create a task and hand it off to WorkManager to run when the work constraints are met.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/work)
     *
     * Guide: [Schedule tasks with WorkManager](https://developer.android.com/topic/libraries/architecture/workmanager)
     *
     * ### API reference:
     * - [androidx.work](https://developer.android.com/reference/kotlin/androidx/work/package-summary)
     */
    val work = Work

    object Work : DependencyGroup(group = "androidx.work") {

        /** Kotlin + coroutines */
        val runtimeKtx = module("work-runtime-ktx")

        /** Multiprocess support (optional) */
        val multiprocess = module("work-multiprocess")

        /** GCMNetworkManager support (optional) */
        val gcm = module("work-gcm")

        /**
         * Test helpers
         *
         * ### API reference:
         * - [androidx.work.testing](https://developer.android.com/reference/kotlin/androidx/work/testing/package-summary)
         */
        val testing = module("work-testing")

        /** Java only. Use [runtimeKtx] for Kotlin + coroutines support. */
        val runtime = module("work-runtime")

        /** RxJava2 support (optional) */
        val rxJava2 = module("work-rxjava2")

        /** RxJava3 support (optional) */
        val rxJava3 = module("work-rxjava3")
    }
}
