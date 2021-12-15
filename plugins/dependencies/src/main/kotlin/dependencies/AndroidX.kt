@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused", "MemberVisibilityCanBePrivate")

import de.fayard.refreshVersions.core.DependencyGroup
import de.fayard.refreshVersions.core.DependencyNotation
import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

/**
 * Check changes from the latest AndroidX versions on
 * [this dedicated page](https://developer.android.com/jetpack/androidx/versions).
 *
 * This structure brings **organized and ready-to-use constants for AndroidX dependencies**. It was made because:
 *
 * - As of 2021 August the 23rd, AndroidX is made of **91 sub-families** of artifacts with their own version.
 * - Back in 2019 November the 18th, AndroidX was made of **187 artifacts**.
 */
@Incubating
object AndroidX : IsNotADependency {
    // LibraryGroups.kt: https://cs.android.com/androidx/platform/frameworks/support/+/androidx-master-dev:buildSrc/src/main/kotlin/androidx/build/LibraryGroups.kt;l=22?q=LibraryGroups&sq=
    // LibraryVersions.kt: https://cs.android.com/androidx/platform/frameworks/support/+/androidx-master-dev:buildSrc/src/main/kotlin/androidx/build/LibraryVersions.kt;l=22?q=LibraryVersions&sq=

    val activity = Activity

    object Activity : DependencyNotationAndGroup(group = "androidx.activity", name = "activity") {
        val compose = module("activity-compose")
        val ktx = module("activity-ktx")
    }

    // androidx.ads intentionally not included because ads are mental pollution.

    val annotation = Annotation

    object Annotation : DependencyNotationAndGroup("androidx.annotation", "annotation") {
        val experimental = module("annotation-experimental")
    }

    val appCompat = AppCompat

    object AppCompat : DependencyNotationAndGroup("androidx.appcompat", "appcompat") {
        val resources = module("appcompat-resources")
    }

    val appSearch = AppSearch

    object AppSearch : DependencyNotationAndGroup(group = "androidx.appsearch", name = "appsearch") {
        val compiler = module("appsearch-compiler")
        val localStorage = module("appsearch-local-storage")
    }

    val archCore = ArchCore

    object ArchCore : DependencyGroup(group = "androidx.arch.core") {
        val common = module("core-common")
        val runtime = module("core-runtime")
        val testing = module("core-testing")
    }

    val asyncLayoutInflater = DependencyNotation("androidx.asynclayoutinflater", "asynclayoutinflater")

    val autoFill = DependencyNotation("androidx.autofill", "autofill")

    val benchmark = Benchmark // TODO kdoc

    object Benchmark : DependencyGroup(group = "androidx.benchmark") {

        val gradlePlugin = module(name = "benchmark-gradle-plugin")

        val junit4 = module(name = "benchmark-junit4")
        val macroJunit4 = module(name = "benchmark-macro-junit4")

        val common = module(name = "benchmark-common")
    }

    val biometric = Biometric

    object Biometric : DependencyNotationAndGroup("androidx.biometric", "biometric") {
        val ktx = module("biometric-ktx")
    }

    val browser = DependencyNotation("androidx.browser", "browser")

    val camera = Camera

    object Camera : DependencyGroup(group = "androidx.camera") {
        val core = module("camera-core")
        val camera2 = module("camera-camera2")
        val extensions = module("camera-extensions")
        val lifecycle = module("camera-lifecycle")
        val view = module("camera-view")
    }

    val car = Car

    object Car : DependencyNotationAndGroup("androidx.car", "car") {
        val cluster = module("car-cluster")
    }

    val carApp = CarApp

    object CarApp : DependencyNotationAndGroup(group = "androidx.car.app", name = "app") {
        val testing = module("app-testing")
    }

    val cardView = DependencyNotation("androidx.cardview", "cardview")

    val collection = Collection

    object Collection : DependencyNotationAndGroup("androidx.collection", "collection") {
        val ktx = module("collection-ktx")
    }

    @Incubating
    val compose = Compose // TODO kdoc

    @Incubating
    object Compose : IsNotADependency {
        private const val groupPrefix = "androidx.compose"

        @Incubating
        val compiler = DependencyNotation("androidx.compose.compiler", "compiler")

        val runtime = Runtime

        object Runtime : DependencyNotationAndGroup(group = "androidx.compose.runtime", name = "runtime") {

            val dispatch = module("runtime-dispatch")
            val saveable = module("runtime-saveable")
            val savedInstanceState = module("runtime-saved-instance-state")

            val liveData = module("runtime-livedata")
            val rxJava2 = module("runtime-rxjava2")
            val rxJava3 = module("runtime-rxjava3")
        }

        val animation = Animation

        object Animation : DependencyNotationAndGroup(group = "androidx.compose.animation", name = "animation") {
            val core = module("animation-core")
        }

        val ui = Ui // TODO kdoc

        object Ui : DependencyNotationAndGroup(group = "androidx.compose.ui", name = "ui") {
            val geometry = module("ui-geometry")
            val graphics = module("ui-graphics")

            val test = module("ui-test")
            val testJunit4 = module("ui-test-junit4")
            val testManifest = module("ui-test-manifest")

            val text = module("ui-text")
            val textAndroid = module("ui-text-android")

            val tooling = module("ui-tooling")
            val toolingData = module("ui-tooling-data")
            val toolingPreview = module("ui-tooling-preview")

            val unit = module("ui-unit")
            val util = module("ui-util")
            val viewBinding = module("ui-viewbinding")
        }

        val foundation = Foundation

        object Foundation : DependencyNotationAndGroup(group = "androidx.compose.foundation", name = "foundation") {
            val layout = module("foundation-layout")

            @Deprecated(
                "Symbols moved into the main artifact in 1.0.0-alpha08",
                ReplaceWith("AndroidX.compose.foundation")
            )
            val text = module("foundation-text")
        }

        val material = Material

        object Material : DependencyNotationAndGroup(group = "androidx.compose.material", name = "material") {

            val icons = Icons

            object Icons : IsNotADependency {
                val core = module("material-icons-core")
                val extended = module("material-icons-extended")
            }

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

        object Material3 : DependencyNotationAndGroup(group = "androidx.compose.material3", name = "material3")
    }

    val concurrent = Concurrent // TODO kdoc

    object Concurrent : DependencyGroup(group = "androidx.concurrent") {
        val futures = module("concurrent-futures")
        val futuresKtx = module("concurrent-futures-ktx")
    }

    val constraintLayout = ConstraintLayout

    object ConstraintLayout : DependencyNotationAndGroup("androidx.constraintlayout", "constraintlayout") {
        val compose = module("constraintlayout-compose")
    }

    val contentPager = DependencyNotation("androidx.contentpager", "contentpager")

    val coordinatorLayout = DependencyNotation("androidx.coordinatorlayout", "coordinatorlayout")

    val core = Core // TODO kdoc

    object Core : DependencyNotationAndGroup(group = "androidx.core", name = "core") {

        val ktx = module("core-ktx")
        val role = module("core-role")

        val animation = module("core-animation")
        val animationTesting = module("core-animation-testing")

        val googleShortcuts = module("core-google-shortcuts")

        val splashscreen = module("core-splashscreen")
    }

    val cursorAdapter = DependencyNotation("androidx.cursoradapter", "cursoradapter")

    val customView = DependencyNotation("androidx.customview", "customview")

    val dataStore = DataStore

    object DataStore : DependencyNotationAndGroup(group = "androidx.datastore", name = "datastore") {

        val preferences = Preferences

        object Preferences : DependencyNotationAndGroup(group = "androidx.datastore", name = "datastore-preferences") {
            val core = module("datastore-preferences-core")
            val rxJava2 = module("datastore-preferences-rxJava2")
            val rxJava3 = module("datastore-preferences-rxJava3")
        }

        val core = module("datastore-core")
        val rxJava2 = module("datastore-rxJava2")
        val rxJava3 = module("datastore-rxJava3")
    }

    val documentFile = DependencyNotation("androidx.documentfile", "documentfile")

    val drawerLayout = DependencyNotation("androidx.drawerlayout", "drawerlayout")

    val dynamicAnimation = DynamicAnimation

    object DynamicAnimation : DependencyNotationAndGroup("androidx.dynamicanimation", "dynamicanimation") {
        val ktx = module("dynamicanimation-ktx")
    }

    val emoji = Emoji

    object Emoji : DependencyNotationAndGroup("androidx.emoji", "emoji") {
        val appCompat = module("emoji-appcompat")
        val bundled = module("emoji-bundled")
    }

    val emoji2 = Emoji2

    object Emoji2 : DependencyNotationAndGroup(group = "androidx.emoji2", name = "emoji2") {
        val views = module("emoji2-views")
        val viewsHelper = module("emoji2-views-helper")
    }

    val enterprise = Enterprise // TODO kdoc

    object Enterprise : DependencyGroup(group = "androidx.enterprise") {
        val feedback = module("enterprise-feedback")
        val feedbackTesting = module("enterprise-feedback-testing")
    }

    val exifInterface = DependencyNotation("androidx.exifinterface", "exifinterface")

    val fragment = Fragment

    object Fragment : DependencyNotationAndGroup("androidx.fragment", "fragment") {
        val ktx = DependencyNotation("androidx.fragment", "fragment-ktx")
        val testing = DependencyNotation("androidx.fragment", "fragment-testing")
    }

    val games = Games

    object Games : DependencyGroup(group = "androidx.games") {

        val framePacing = module("games-frame-pacing")
        val performanceTuner = module("games-performance-tuner")
        val activity = module("games-activity")
        val controller = module("games-controller")
        val textInput = module("games-text-input")
    }

    val gridLayout = DependencyNotation("androidx.gridlayout", "gridlayout")

    val health = Health

    object Health : DependencyGroup(group = "androidx.health") {
        val servicesClient = module("health-services-client")
    }

    val heifWriter = DependencyNotation("androidx.heifwriter", "heifwriter")

    val hilt = Hilt // TODO kdoc

    object Hilt : DependencyGroup(group = "androidx.hilt") {

        val work = module("hilt-work")
        val navigationFragment = module("hilt-navigation-fragment")
        val navigationCompose = module("hilt-navigation-compose")
        val compiler = module("hilt-compiler")

        @Deprecated(
            "Use @HiltViewModel instead of @ViewModelInject and remove this dependency. " +
                "See docs here: https://dagger.dev/hilt/view-model"
        )
        val lifecycleViewModel = module("hilt-lifecycle-viewmodel")
    }

    val interpolator = DependencyNotation("androidx.interpolator", "interpolator")

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
         * ### API reference:
         * - [androidx.leanback.preference](https://developer.android.com/reference/kotlin/androidx/leanback/preference/package-summary)
         */
        val preference = module("leanback-preference")

        /**
         * ### API reference:
         * - [androidx.leanback.paging](https://developer.android.com/reference/kotlin/androidx/leanback/paging/package-summary)
         */
        val paging = module("leanback-paging")

        /**
         * ### API reference:
         * - [androidx.leanback.tab](https://developer.android.com/reference/kotlin/androidx/leanback/tab/package-summary)
         */
        val tab = module("leanback-tab")

        val grid = module("leanback-grid")
    }

    val legacy = Legacy // TODO kdoc

    object Legacy : DependencyGroup(group = "androidx.legacy") {

        val preferenceV14 = module("legacy-preference-v14")
        val supportCoreUi = module("legacy-support-core-ui")
        val supportCoreUtils = module("legacy-support-core-utils")
        val supportV13 = module("legacy-support-v13")
        val supportV4 = module("legacy-support-v4")
    }

    val lifecycle = Lifecycle // TODO kdoc

    object Lifecycle : DependencyGroup(group = "androidx.lifecycle") {

        val runtimeKtx = module("lifecycle-runtime-ktx")
        val liveDataKtx = module("lifecycle-livedata-ktx")
        val liveDataCoreKtx = module("lifecycle-livedata-core-ktx")
        val viewModelKtx = module("lifecycle-viewmodel-ktx")

        val process = module("lifecycle-process")
        val service = module("lifecycle-service")
        val viewModelSavedState = module("lifecycle-viewmodel-savedstate")

        val viewModelCompose = module("lifecycle-viewmodel-compose")

        val runtime = module("lifecycle-runtime")
        val viewModel = module("lifecycle-viewmodel")
        val liveData = module("lifecycle-livedata")
        val liveDataCore = module("lifecycle-livedata-core")

        val common = module("lifecycle-common")
        val commonJava8 = module("lifecycle-common-java8")

        val compiler = module("lifecycle-compiler")

        val reactiveStreams = module("lifecycle-reactivestreams")
        val reactiveStreamsKtx = module("lifecycle-reactivestreams-ktx")

        @Deprecated("Replaced by more specific artifacts. Last available version is 2.2.0")
        val extensions = module("lifecycle-extensions")
    }

    val loader = DependencyNotation("androidx.loader", "loader")

    /**
     * **DEPRECATED**, [see reason here](https://developer.android.com/jetpack/androidx/releases/localbroadcastmanager)
     *
     * Consider using one of these better alternatives:
     * - `Flow` from kotlinx.coroutines (possibly in conjunction with `BroadcastChannel`)
     * - ` LiveData` from AndroidX (which can interop with `Flow` from kotlinx.coroutines with [Lifecycle.liveDataKtx])
     */
    @Deprecated("Confusing developer experience, use coroutines + Flows and/or LiveData instead.")
    val localBroadcastManager = DependencyNotation("androidx.localbroadcastmanager", "localbroadcastmanager")

    val media = DependencyNotation("androidx.media", "media")

    val media2 = Media2

    object Media2 : DependencyGroup(group = "androidx.media2") {
        val session = module("media2-session")
        val widget = module("media2-widget")
        val player = module("media2-player")
        val exoplayer = module("media2-exoplayer")

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

        val dataSource = DataSource

        object DataSource : DependencyGroup(group = group) {

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
         * Common functionality for loading data
         */
        val datasource = module("media3-datasource")

        /**
         * Common functionality used across multiple media libraries
         */
        val common = module("media3-common")
    }

    val mediaRouter = DependencyNotation("androidx.mediarouter", "mediarouter")

    val multidex = MultiDex

    object MultiDex : DependencyNotationAndGroup("androidx.multidex", "multidex") {
        val instrumentation = module("multidex-instrumentation")
    }

    val navigation = Navigation // TODO kdoc

    object Navigation : DependencyGroup(group = "androidx.navigation") {

        val fragmentKtx = module("navigation-fragment-ktx")
        val uiKtx = module("navigation-ui-ktx")

        val compose = module("navigation-compose")

        val dynamicFeaturesFragment = module("navigation-dynamic-features-fragment")

        val safeArgsGenerator = module("navigation-safe-args-generator")
        val safeArgsGradlePlugin = module("navigation-safe-args-gradle-plugin")

        val testing = module("navigation-testing")

        // All the Navigation artifacts below are transitively included in fragmentKtx and uiKtx.

        val commonKtx = module("navigation-common-ktx")
        val runtimeKtx = module("navigation-runtime-ktx")

        val fragment = module("navigation-fragment")
        val ui = module("navigation-ui")

        val runtime = module("navigation-runtime")
        val common = module("navigation-common")
    }

    val paging = Paging // TODO kdoc

    object Paging : DependencyGroup(group = "androidx.paging") {

        val commonKtx = module("paging-common-ktx")
        val runtimeKtx = module("paging-runtime-ktx")

        val compose = module("paging-compose")

        val rxJava2Ktx = module("paging-rxjava2-ktx")

        val common = module("paging-common")
        val runtime = module("paging-runtime")

        val rxJava2 = module("paging-rxjava2")
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
             * @see junitKtx
             */
            val junit = module("junit")

            /**
             * JUnit extensions APIs. Includes Kotlin extensions.
             */
            val junitKtx = module("junit-ktx")

            /**
             * [Truth](https://github.com/google/truth) extensions APIs.
             *
             * @see junitKtx
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
     * @see tracingKtx
     */
    val tracing = DependencyNotation("androidx.tracing", "tracing")

    /**
     * Write trace events to the system trace buffer. Includes Kotlin extensions.
     *
     * [Release notes](https://developer.android.com/jetpack/androidx/releases/tracing)
     *
     * ### API reference:
     * - [androidx.tracing](https://developer.android.com/reference/kotlin/androidx/tracing/package-summary)
     */
    val tracingKtx = DependencyNotation("androidx.tracing", "tracing-ktx")

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
             * Use to preview wear tiles in your own app.
             *
             * ### API reference
             * - [androidx.wear.tiles.renderer](https://developer.android.com/reference/kotlin/androidx/wear/tiles/renderer/package-summary)
             */
            val renderer = module("tiles-renderer")

            /**
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
