@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import dependencies.DependencyNotationAndGroup
import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

/**
 * Check changes from the latest AndroidX versions on
 * [this dedicated page](https://developer.android.com/jetpack/androidx/versions).
 *
 * This structure brings **organized and ready-to-use constants for AndroidX dependencies**. It was made because:
 *
 * - As of 2019 November the 11th, AndroidX is made of **70 sub-families** of artifacts with their own version.
 * - As of 2019 November the 18th, AndroidX is made of **187 artifacts**.
 */
@Incubating
object AndroidX {
    // LibraryGroups.kt: https://cs.android.com/androidx/platform/frameworks/support/+/androidx-master-dev:buildSrc/src/main/kotlin/androidx/build/LibraryGroups.kt;l=22?q=LibraryGroups&sq=
    // LibraryVersions.kt: https://cs.android.com/androidx/platform/frameworks/support/+/androidx-master-dev:buildSrc/src/main/kotlin/androidx/build/LibraryVersions.kt;l=22?q=LibraryVersions&sq=

    const val activityKtx = "androidx.activity:activity-ktx:_"
    const val activity = "androidx.activity:activity:_"

    // androidx.ads intentionally not included because ads are mental pollution.

    const val annotation = "androidx.annotation:annotation:_"
    const val annotationExperimental = "androidx.annotation:annotation-experimental:_"

    const val appCompat = "androidx.appcompat:appcompat:_"
    const val appCompatResources = "androidx.appcompat:appcompat-resources:_"

    val archCore = ArchCore

    const val asyncLayoutInflater = "androidx.asynclayoutinflater:asynclayoutinflater:_"

    const val autoFill = "androidx.autofill:autofill:_"

    val benchmark = Benchmark

    const val biometric = "androidx.biometric:biometric:_"

    const val browser = "androidx.browser:browser:_"

    val camera = Camera

    const val car = "androidx.car:car:_"
    const val carCluster = "androidx.car:car-cluster:_"

    const val cardView = "androidx.cardview:cardview:_"

    const val collectionKtx = "androidx.collection:collection-ktx:_"
    const val collection = "androidx.collection:collection:_"

    @Incubating
    val compose = Compose

    val concurrent = Concurrent

    const val constraintLayout = "androidx.constraintlayout:constraintlayout:_"
    const val constraintLayoutSolver = "androidx.constraintlayout:constraintlayout-solver:_"

    const val contentPager = "androidx.contentpager:contentpager:_"

    const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:_"

    val core = Core

    const val cursorAdapter = "androidx.cursoradapter:cursoradapter:_"

    const val customView = "androidx.customview:customview:_"

    const val documentFile = "androidx.documentfile:documentfile:_"

    const val drawerLayout = "androidx.drawerlayout:drawerlayout:_"

    const val dynamicAnimationKtx = "androidx.dynamicanimation:dynamicanimation-ktx:_"
    const val dynamicAnimation = "androidx.dynamicanimation:dynamicanimation:_"

    const val emoji = "androidx.emoji:emoji:_"
    const val emojiAppCompat = "androidx.emoji:emoji-appcompat:_"
    const val emojiBundled = "androidx.emoji:emoji-bundled:_"

    val enterprise = Enterprise

    const val exifInterface = "androidx.exifinterface:exifinterface:_"

    const val fragmentKtx = "androidx.fragment:fragment-ktx:_"
    const val fragment = "androidx.fragment:fragment:_"
    const val fragmentTesting = "androidx.fragment:fragment-testing:_"

    val gaming = Gaming

    const val gridLayout = "androidx.gridlayout:gridlayout:_"

    const val heifWriter = "androidx.heifwriter:heifwriter:_"

    val hilt = Hilt

    const val interpolator = "androidx.interpolator:interpolator:_"

    const val leanback = "androidx.leanback:leanback:_"
    const val leanbackPreference = "androidx.leanback:leanback-preference:_"

    val legacy = Legacy

    val lifecycle = Lifecycle

    const val loader = "androidx.loader:loader:_"

    /**
     * **DEPRECATED**, [see reason here](https://developer.android.com/jetpack/androidx/releases/localbroadcastmanager)
     *
     * Consider using one of these better alternatives:
     * - `Flow` from kotlinx.coroutines (possibly in conjunction with `BroadcastChannel`)
     * - ` LiveData` from AndroidX (which can interop with `Flow` from kotlinx.coroutines with [Lifecycle.liveDataKtx])
     */
    @Deprecated("Confusing developer experience, use coroutines + Flows and/or LiveData instead.")
    const val localBroadcastManager = "androidx.localbroadcastmanager:localbroadcastmanager:_"

    const val media = "androidx.media:media:_"

    val media2 = Media2

    const val mediaRouter = "androidx.mediarouter:mediarouter:_"

    const val multidex = "androidx.multidex:multidex:_"
    const val multidexInstrumentation = "androidx.multidex:multidex-instrumentation:_"

    val navigation = Navigation

    val paging = Paging

    const val paletteKtx = "androidx.palette:palette-ktx:_"
    const val palette = "androidx.palette:palette:_"

    const val percentLayout = "androidx.percentlayout:percentlayout:_"

    const val preferenceKtx = "androidx.preference:preference-ktx:_"
    const val preference = "androidx.preference:preference:_"

    const val print = "androidx.print:print:_"

    const val recommendation = "androidx.recommendation:recommendation:_"

    const val recyclerView = "androidx.recyclerview:recyclerview:_"
    const val recyclerViewSelection = "androidx.recyclerview:recyclerview-selection:_"

    const val remoteCallback = "androidx.remotecallback:remotecallback:_"
    const val remoteCallbackProcessor = "androidx.remotecallback:remotecallback-processor:_"

    val room = Room

    const val savedState = "androidx.savedstate:savedstate:_"

    val security = Security

    const val shareTarget = "androidx.sharetarget:sharetarget:_"

    val slice = Slice

    const val slidingPaneLayout = "androidx.slidingpanelayout:slidingpanelayout:_"

    const val sqliteKtx = "androidx.sqlite:sqlite-ktx:_"
    const val sqliteFramework = "androidx.sqlite:sqlite-framework:_"
    const val sqlite = "androidx.sqlite:sqlite:_"

    val startup = Startup

    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:_"

    val test = Test

    const val textClassifier = "androidx.textclassifier:textclassifier:_"

    const val tracing = "androidx.tracing:tracing:_"
    const val tracingKtx = "androidx.tracing:tracing-ktx:_"

    const val transition = "androidx.transition:transition:_"

    const val tvProvider = "androidx.tvprovider:tvprovider:_"

    @Incubating
    val ui = Ui

    const val vectorDrawable = "androidx.vectordrawable:vectordrawable:_"
    const val vectorDrawableAnimated = "androidx.vectordrawable:vectordrawable-animated:_"
    const val vectorDrawableSeekable = "androidx.vectordrawable:vectordrawable-seekable:_"

    const val versionedParcelable = "androidx.versionedparcelable:versionedparcelable:_"

    const val viewPager = "androidx.viewpager:viewpager:_"
    const val viewPager2 = "androidx.viewpager2:viewpager2:_"

    const val wear = "androidx.wear:wear:_"

    const val webkit = "androidx.webkit:webkit:_"

    val work = Work

    val window = Window

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Definition of nested objects below.  ////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    object Core : DependencyNotationAndGroup(group = "androidx.core", name = "core") {

        @JvmField val ktx = "$artifactPrefix-ktx:_"
        @JvmField val role = "$artifactPrefix-role:_"

        @JvmField val animation = "$artifactPrefix-animation:_"
        @JvmField val animationTesting = "$artifactPrefix-animation-testing:_"
    }

    object Lifecycle: IsNotADependency {
        private const val artifactPrefix = "androidx.lifecycle:lifecycle"

        const val runtimeKtx = "$artifactPrefix-runtime-ktx:_"
        const val liveDataKtx = "$artifactPrefix-livedata-ktx:_"
        const val liveDataCoreKtx = "$artifactPrefix-livedata-core-ktx:_"
        const val viewModelKtx = "$artifactPrefix-viewmodel-ktx:_"

        const val process = "$artifactPrefix-process:_"
        const val service = "$artifactPrefix-service:_"
        const val viewModelSavedState = "$artifactPrefix-viewmodel-savedstate:_"

        const val runtime = "$artifactPrefix-runtime:_"
        const val viewModel = "$artifactPrefix-viewmodel:_"
        const val liveData = "$artifactPrefix-livedata:_"
        const val liveDataCore = "$artifactPrefix-livedata-core:_"

        const val common = "$artifactPrefix-common:_"
        const val commonJava8 = "$artifactPrefix-common-java8:_"

        const val compiler = "$artifactPrefix-compiler:_"

        const val reactiveStreams = "$artifactPrefix-reactivestreams:_"
        const val reactiveStreamsKtx = "$artifactPrefix-reactivestreams-ktx:_"

        @Deprecated("Replaced by more specific artifacts. Last available version is 2.2.0")
        const val extensions = "$artifactPrefix-extensions:_"
    }

    object Startup: IsNotADependency {
        const val runtime = "androidx.startup:startup-runtime:_"
    }

    object Window : DependencyNotationAndGroup(group = "androidx.window", name = "window") {
        @JvmField val extensions = "$artifactPrefix-extensions:_"
    }

    object Security: IsNotADependency {
        private const val artifactPrefix = "androidx.security:security"

        const val crypto = "$artifactPrefix-crypto:_"
        const val cryptoKtx = "$artifactPrefix-crypto-ktx:_"

        const val identityCredential = "$artifactPrefix-identity-credential:_"
    }

    object Room: IsNotADependency {
        private const val artifactPrefix = "androidx.room:room"

        const val ktx = "$artifactPrefix-ktx:_"
        const val coroutines = "$artifactPrefix-coroutines:_"
        const val compiler = "$artifactPrefix-compiler:_"
        const val testing = "$artifactPrefix-testing:_"

        const val migration = "$artifactPrefix-migration:_"
        const val runtime = "$artifactPrefix-runtime:_"
        const val common = "$artifactPrefix-common:_"

        const val guava = "$artifactPrefix-guava:_"
        const val rxJava2 = "$artifactPrefix-rxjava2:_"
    }

    object Paging: IsNotADependency {
        private const val artifactPrefix = "androidx.paging:paging"

        const val commonKtx = "$artifactPrefix-common-ktx:_"
        const val runtimeKtx = "$artifactPrefix-runtime-ktx:_"

        const val rxJava2Ktx = "$artifactPrefix-rxjava2-ktx:_"

        const val common = "$artifactPrefix-common:_"
        const val runtime = "$artifactPrefix-runtime:_"

        const val rxJava2 = "$artifactPrefix-rxjava2:_"
    }

    object Work: IsNotADependency {
        const val runtimeKtx = "androidx.work:work-runtime-ktx:_"

        const val gcm = "androidx.work:work-gcm:_"
        const val testing = "androidx.work:work-testing:_"

        const val runtime = "androidx.work:work-runtime:_"

        const val rxJava2 = "androidx.work:work-rxjava2:_"
    }

    object Navigation: IsNotADependency {
        private const val artifactPrefix = "androidx.navigation:navigation"

        const val fragmentKtx = "$artifactPrefix-fragment-ktx:_"
        const val uiKtx = "$artifactPrefix-ui-ktx:_"

        const val safeArgsGenerator = "$artifactPrefix-safe-args-generator:_"
        const val safeArgsGradlePlugin = "$artifactPrefix-safe-args-gradle-plugin:_"

        // All the Navigation artifacts below are transitively included in fragmentKtx and uiKtx.

        const val commonKtx = "$artifactPrefix-common-ktx:_"
        const val runtimeKtx = "$artifactPrefix-runtime-ktx:_"

        const val fragment = "$artifactPrefix-fragment:_"
        const val ui = "$artifactPrefix-ui:_"

        const val runtime = "$artifactPrefix-runtime:_"
        const val common = "$artifactPrefix-common:_"
    }

    @Incubating
    object Ui: IsNotADependency {
        const val test = "androidx.ui:ui-test:_" // "Not Yet Refactored (no changes)" as of version 0.1.0-dev15.
        const val tooling = "androidx.ui:ui-tooling:_" // "Not Yet Refactored (no changes)" as of version 0.1.0-dev15.
    }

    @Incubating
    object Compose: IsNotADependency {
        private const val groupPrefix = "androidx.compose"

        val runtime = Runtime
        val animation = Animation
        val ui = Ui
        val foundation = Foundation
        val material = Material

        @Incubating
        const val compiler = "androidx.compose:compose-compiler:_" // "Not Yet Refactored (no changes)" as of version 0.1.0-dev15.

        object Runtime : DependencyNotationAndGroup(group = "$groupPrefix.runtime", name = "runtime") {

            @JvmField val dispatch = "$artifactPrefix-dispatch:_"
            @JvmField val savedInstanceState = "$artifactPrefix-saved-instance-state:_"

            @JvmField val liveData = "$artifactPrefix-livedata:_"
            @JvmField val rxJava2 = "$artifactPrefix-rxjava2:_"
        }

        object Animation : DependencyNotationAndGroup(group = "$groupPrefix.animation", name = "animation") {
            @JvmField val core = "$artifactPrefix-core:_"
        }

        object Ui : DependencyNotationAndGroup(group = "$groupPrefix.ui", name = "ui") {
            @JvmField val geometry = "$artifactPrefix-geometry:_"
            @JvmField val graphics = "$artifactPrefix-graphics:_"

            @JvmField val text = "$artifactPrefix-text:_"
            @JvmField val textAndroid = "$artifactPrefix-text-android:_"

            @JvmField val unit = "$artifactPrefix-unit:_"
            @JvmField val util = "$artifactPrefix-util:_"
        }

        object Foundation : DependencyNotationAndGroup(group = "$groupPrefix.foundation", name = "foundation") {
            @JvmField val layout = "$artifactPrefix-layout:_"
            @JvmField val text = "$artifactPrefix-text:_"
        }

        object Material : DependencyNotationAndGroup(group = "$groupPrefix.material", name = "material") {

            val icons = Icons

            object Icons: IsNotADependency {
                @JvmField val core = "$artifactPrefix-icons-core:_"
                @JvmField val extended = "$artifactPrefix-icons-extended:_"
            }
        }
    }

    object Media2: IsNotADependency {
        private const val artifactPrefix = "androidx.media2:media2"

        const val session = "$artifactPrefix-session:_"
        const val widget = "$artifactPrefix-widget:_"
        const val player = "$artifactPrefix-player:_"
        const val exoplayer = "$artifactPrefix-exoplayer:_"

        const val common = "$artifactPrefix-common:_"
    }

    object Camera: IsNotADependency {
        private const val artifactPrefix = "androidx.camera:camera"

        const val core = "$artifactPrefix-core:_"
        const val camera2 = "$artifactPrefix-camera2:_"
        const val extensions = "$artifactPrefix-extensions:_"
        const val lifecycle = "$artifactPrefix-lifecycle:_"
        const val view = "$artifactPrefix-view:_"
    }

    object Hilt: IsNotADependency {
        private const val artifactPrefix = "androidx.hilt:hilt"

        const val lifecycleViewModel = "$artifactPrefix-lifecycle-viewmodel:_"
        const val work = "$artifactPrefix-work:_"
        const val compiler = "$artifactPrefix-compiler:_"
    }

    object Enterprise: IsNotADependency {
        private const val artifactPrefix = "androidx.enterprise:enterprise"

        const val feedback = "$artifactPrefix-feedback:_"
        const val feedbackTesting = "$artifactPrefix-feedback-testing:_"
    }

    object Gaming: IsNotADependency {
        private const val artifactPrefix = "androidx.gaming:gaming"

        const val framePacing = "$artifactPrefix-frame-pacing:_"
        const val performanceTuner = "$artifactPrefix-performance-tuner:_"

    }

    object Slice: IsNotADependency {
        private const val artifactPrefix = "androidx.slice:slice"

        const val buildersKtx = "$artifactPrefix-builders-ktx:_"

        const val builders = "androidx.slice:slice-builders:_"
        const val core = "$artifactPrefix-core:_"
        const val view = "$artifactPrefix-view:_"
    }

    object Benchmark: IsNotADependency {
        private const val artifactPrefix = "androidx.benchmark:benchmark"

        const val junit4 = "$artifactPrefix-junit4:_"

        const val gradlePlugin = "$artifactPrefix-gradle-plugin:_"

        const val common = "$artifactPrefix-common:_"
    }

    object Test: IsNotADependency {
        private const val coreVersion = "_"
        private const val group = "androidx.test"

        val espresso = Espresso

        const val coreKtx = "$group:core-ktx:$coreVersion"
        const val core = "$group:core:$coreVersion"

        const val monitor = "$group:monitor:_"
        const val orchestrator = "$group:orchestrator:_"

        const val rules = "$group:rules:_"
        const val runner = "$group:runner:_"

        val ext = Ext

        object Ext: IsNotADependency {
            private const val extGroup = "androidx.test.ext"
            private const val extJunitVersion = "_"

            const val junit = "$extGroup:junit:$extJunitVersion"
            const val junitKtx = "$extGroup:junit-ktx:$extJunitVersion"

            const val truth = "$extGroup:truth:_"
        }

        const val services = "$group.services:test-services:_"

        const val jankTestHelper = "$group.janktesthelper:janktesthelper:_"
        const val uiAutomator = "$group.uiautomator:uiautomator:_"

        object Espresso: IsNotADependency {
            private const val group = "androidx.test.espresso"
            private const val artifactPrefix = "$group:espresso"

            val idling = Idling

            const val core = "$artifactPrefix-core:_"
            const val contrib = "$artifactPrefix-contrib:_"
            const val idlingResource = "$artifactPrefix-idling-resource:_"
            const val intents = "$artifactPrefix-intents:_"
            const val accessibility = "$artifactPrefix-accessibility:_"
            const val remote = "$artifactPrefix-remote:_"
            const val web = "$artifactPrefix-web:_"

            object Idling: IsNotADependency {
                private const val artifactPrefix = "$group.idling:idling"

                const val concurrent = "$artifactPrefix-concurrent:_"
                const val net = "$artifactPrefix-net:_"
            }
        }
    }

    object Concurrent: IsNotADependency {
        private const val artifactPrefix = "androidx.concurrent:concurrent"

        const val futures = "$artifactPrefix-futures:_"
        const val futuresKtx = "$artifactPrefix-futures-ktx:_"
    }

    object ArchCore: IsNotADependency {
        private const val artifactPrefix = "androidx.arch.core:core"

        const val common = "$artifactPrefix-common:_"
        const val runtime = "$artifactPrefix-runtime:_"
        const val testing = "$artifactPrefix-testing:_"
    }

    object Legacy: IsNotADependency {
        private const val artifactPrefix = "androidx.legacy:legacy"

        const val preferenceV14 = "$artifactPrefix-preference-v14:_"
        const val supportCoreUi = "$artifactPrefix-support-core-ui:_"
        const val supportCoreUtils = "$artifactPrefix-support-core-utils:_"
        const val supportV13 = "$artifactPrefix-support-v13:_"
        const val supportV4 = "$artifactPrefix-support-v4:_"
    }
}
