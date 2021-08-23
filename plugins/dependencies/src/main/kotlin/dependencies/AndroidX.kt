@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyNotationAndGroup
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
object AndroidX : IsNotADependency {
    // LibraryGroups.kt: https://cs.android.com/androidx/platform/frameworks/support/+/androidx-master-dev:buildSrc/src/main/kotlin/androidx/build/LibraryGroups.kt;l=22?q=LibraryGroups&sq=
    // LibraryVersions.kt: https://cs.android.com/androidx/platform/frameworks/support/+/androidx-master-dev:buildSrc/src/main/kotlin/androidx/build/LibraryVersions.kt;l=22?q=LibraryVersions&sq=

    val activity = Activity

    object Activity : DependencyNotationAndGroup(group = "androidx.activity", name = "activity") {
        @JvmField val compose = "$artifactPrefix-compose:_"
        @JvmField val ktx = "$artifactPrefix-ktx:_"
    }
    const val activityKtx = "androidx.activity:activity-ktx:_"

    // androidx.ads intentionally not included because ads are mental pollution.

    const val annotation = "androidx.annotation:annotation:_"
    const val annotationExperimental = "androidx.annotation:annotation-experimental:_"

    const val appCompat = "androidx.appcompat:appcompat:_"
    const val appCompatResources = "androidx.appcompat:appcompat-resources:_"

    val appSearch = AppSearch

    object AppSearch : DependencyNotationAndGroup(group = "androidx.appsearch", name = "appsearch") {

        @JvmField
        val compiler = "$artifactPrefix-compiler:_"
        @JvmField
        val localStorage = "$artifactPrefix-local-storage:_"
    }

    val archCore = ArchCore

    object ArchCore : IsNotADependency {
        private const val artifactPrefix = "androidx.arch.core:core"

        const val common = "$artifactPrefix-common:_"
        const val runtime = "$artifactPrefix-runtime:_"
        const val testing = "$artifactPrefix-testing:_"
    }

    const val asyncLayoutInflater = "androidx.asynclayoutinflater:asynclayoutinflater:_"

    const val autoFill = "androidx.autofill:autofill:_"

    val benchmark = Benchmark // TODO kdoc

    object Benchmark : IsNotADependency {
        private const val artifactPrefix = "androidx.benchmark:benchmark"

        const val junit4 = "$artifactPrefix-junit4:_"

        const val gradlePlugin = "$artifactPrefix-gradle-plugin:_"

        const val common = "$artifactPrefix-common:_"
    }

    const val biometric = "androidx.biometric:biometric:_"
    const val biometricKtx = "androidx.biometric:biometric-ktx:_"

    const val browser = "androidx.browser:browser:_"

    val camera = Camera

    object Camera : IsNotADependency {
        private const val artifactPrefix = "androidx.camera:camera"

        const val core = "$artifactPrefix-core:_"
        const val camera2 = "$artifactPrefix-camera2:_"
        const val extensions = "$artifactPrefix-extensions:_"
        const val lifecycle = "$artifactPrefix-lifecycle:_"
        const val view = "$artifactPrefix-view:_"
    }

    const val car = "androidx.car:car:_"

    val carApp = CarApp

    object CarApp : DependencyNotationAndGroup(group = "androidx.car.app", name = "app") {

        @JvmField
        val testing = "$artifactPrefix-testing:_"
    }

    const val carCluster = "androidx.car:car-cluster:_"

    const val cardView = "androidx.cardview:cardview:_"

    const val collectionKtx = "androidx.collection:collection-ktx:_"
    const val collection = "androidx.collection:collection:_"

    @Incubating
    val compose = Compose // TODO kdoc

    @Incubating
    object Compose : IsNotADependency {
        private const val groupPrefix = "androidx.compose"

        @Incubating
        const val compiler = "androidx.compose.compiler:compiler:_"

        val runtime = Runtime

        object Runtime : DependencyNotationAndGroup(group = "$groupPrefix.runtime", name = "runtime") {

            @JvmField
            val dispatch = "$artifactPrefix-dispatch:_"
            @JvmField
            val saveable = "$artifactPrefix-saveable:_"
            @JvmField
            val savedInstanceState = "$artifactPrefix-saved-instance-state:_"

            @JvmField
            val liveData = "$artifactPrefix-livedata:_"
            @JvmField
            val rxJava2 = "$artifactPrefix-rxjava2:_"
            @JvmField
            val rxJava3 = "$artifactPrefix-rxjava3:_"
        }

        val animation = Animation

        object Animation : DependencyNotationAndGroup(group = "$groupPrefix.animation", name = "animation") {
            @JvmField
            val core = "$artifactPrefix-core:_"
        }

        val ui = Ui // TODO kdoc

        object Ui : DependencyNotationAndGroup(group = "$groupPrefix.ui", name = "ui") {
            @JvmField
            val geometry = "$artifactPrefix-geometry:_"
            @JvmField
            val graphics = "$artifactPrefix-graphics:_"

            @JvmField
            val test = "$artifactPrefix-test:_"
            @JvmField
            val testJunit4 = "$artifactPrefix-test-junit4:_"
            @JvmField
            val testManifest = "$artifactPrefix-test-manifest:_"

            @JvmField
            val text = "$artifactPrefix-text:_"
            @JvmField
            val textAndroid = "$artifactPrefix-text-android:_"

            @JvmField
            val tooling = "$artifactPrefix-tooling:_"
            @JvmField
            val toolingData = "$artifactPrefix-tooling-data:_"

            @JvmField
            val unit = "$artifactPrefix-unit:_"
            @JvmField
            val util = "$artifactPrefix-util:_"
            @JvmField
            val viewBinding = "$artifactPrefix-viewbinding:_"
        }

        val foundation = Foundation

        object Foundation : DependencyNotationAndGroup(group = "$groupPrefix.foundation", name = "foundation") {
            @JvmField
            val layout = "$artifactPrefix-layout:_"

            @JvmField
            @Deprecated(
                "Symbols moved into the main artifact in 1.0.0-alpha08",
                ReplaceWith("AndroidX.compose.foundation")
            )
            val text = "$artifactPrefix-text:_"
        }

        val material = Material

        object Material : DependencyNotationAndGroup(group = "$groupPrefix.material", name = "material") {

            val icons = Icons

            object Icons : IsNotADependency {
                @JvmField
                val core = "$artifactPrefix-icons-core:_"
                @JvmField
                val extended = "$artifactPrefix-icons-extended:_"
            }

            @JvmField
            val ripple = "$artifactPrefix-ripple:_"
        }
    }

    val concurrent = Concurrent // TODO kdoc

    object Concurrent : IsNotADependency {
        private const val artifactPrefix = "androidx.concurrent:concurrent"

        const val futures = "$artifactPrefix-futures:_"
        const val futuresKtx = "$artifactPrefix-futures-ktx:_"
    }

    const val constraintLayout = "androidx.constraintlayout:constraintlayout:_"
    const val constraintLayoutCompose = "androidx.constraintlayout:constraintlayout-compose:_"
    const val constraintLayoutSolver = "androidx.constraintlayout:constraintlayout-solver:_"

    const val contentPager = "androidx.contentpager:contentpager:_"

    const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:_"

    val core = Core // TODO kdoc

    object Core : DependencyNotationAndGroup(group = "androidx.core", name = "core") {

        @JvmField
        val ktx = "$artifactPrefix-ktx:_"
        @JvmField
        val role = "$artifactPrefix-role:_"

        @JvmField
        val animation = "$artifactPrefix-animation:_"
        @JvmField
        val animationTesting = "$artifactPrefix-animation-testing:_"

        @JvmField
        val googleShortcuts = "$artifactPrefix-google-shortcuts:_"
    }

    const val cursorAdapter = "androidx.cursoradapter:cursoradapter:_"

    const val customView = "androidx.customview:customview:_"

    val dataStore = DataStore

    object DataStore : DependencyNotationAndGroup(group = "androidx.datastore", name = "datastore") {

        val preferences = Preferences

        object Preferences : DependencyNotationAndGroup(group = "androidx.datastore", name = "datastore-preferences") {
            @JvmField
            val core = "$artifactPrefix-core:_"
            @JvmField
            val rxJava2 = "$artifactPrefix-rxJava2:_"
            @JvmField
            val rxJava3 = "$artifactPrefix-rxJava3:_"
        }

        @JvmField
        val core = "$artifactPrefix-core:_"
        @JvmField
        val rxJava2 = "$artifactPrefix-rxJava2:_"
        @JvmField
        val rxJava3 = "$artifactPrefix-rxJava3:_"
    }

    const val documentFile = "androidx.documentfile:documentfile:_"

    const val drawerLayout = "androidx.drawerlayout:drawerlayout:_"

    const val dynamicAnimationKtx = "androidx.dynamicanimation:dynamicanimation-ktx:_"
    const val dynamicAnimation = "androidx.dynamicanimation:dynamicanimation:_"

    const val emoji = "androidx.emoji:emoji:_"
    const val emojiAppCompat = "androidx.emoji:emoji-appcompat:_"
    const val emojiBundled = "androidx.emoji:emoji-bundled:_"

    val emoji2 = Emoji2

    object Emoji2 : DependencyNotationAndGroup(group = "androidx.emoji2", name = "emoji2") {
        @JvmField
        val views = "$artifactPrefix-views:_"
        @JvmField
        val viewsHelper = "$artifactPrefix-views-helper:_"
    }

    val enterprise = Enterprise // TODO kdoc

    object Enterprise : IsNotADependency {
        private const val artifactPrefix = "androidx.enterprise:enterprise"

        const val feedback = "$artifactPrefix-feedback:_"
        const val feedbackTesting = "$artifactPrefix-feedback-testing:_"
    }

    const val exifInterface = "androidx.exifinterface:exifinterface:_"

    const val fragmentKtx = "androidx.fragment:fragment-ktx:_"
    const val fragment = "androidx.fragment:fragment:_"
    const val fragmentTesting = "androidx.fragment:fragment-testing:_"

    val gaming = Gaming // TODO kdoc

    object Gaming : IsNotADependency {
        private const val artifactPrefix = "androidx.gaming:gaming"

        const val framePacing = "$artifactPrefix-frame-pacing:_"
        const val performanceTuner = "$artifactPrefix-performance-tuner:_"

    }

    const val gridLayout = "androidx.gridlayout:gridlayout:_"

    val health = Health

    object Health : IsNotADependency {
        private const val group = "androidx.health"

        const val servicesClient = "$group:health-services-client:_"
    }

    const val heifWriter = "androidx.heifwriter:heifwriter:_"

    val hilt = Hilt // TODO kdoc

    object Hilt : IsNotADependency {
        private const val artifactPrefix = "androidx.hilt:hilt"

        const val work = "$artifactPrefix-work:_"
        const val navigationFragment = "$artifactPrefix-navigation-fragment:_"
        const val navigationCompose = "$artifactPrefix-navigation-compose:_"
        const val compiler = "$artifactPrefix-compiler:_"

        @Deprecated(
            "Use @HiltViewModel instead of @ViewModelInject and remove this dependency. " +
                "See docs here: https://dagger.dev/hilt/view-model"
        )
        const val lifecycleViewModel = "$artifactPrefix-lifecycle-viewmodel:_"
    }

    const val interpolator = "androidx.interpolator:interpolator:_"

    val leanback = Leanback

    object Leanback : DependencyNotationAndGroup(group = "androidx.leanback", name = "leanback") {
        @JvmField
        val preference = "$artifactPrefix-preference:_"
        @JvmField
        val paging = "$artifactPrefix-paging:_"
        @JvmField
        val tab = "$artifactPrefix-tab:_"
    }

    @Deprecated("Dependency notation moved", ReplaceWith("AndroidX.leanback.preference"))
    const val leanbackPreference = "androidx.leanback:leanback-preference:_"

    val legacy = Legacy // TODO kdoc

    object Legacy : IsNotADependency {
        private const val artifactPrefix = "androidx.legacy:legacy"

        const val preferenceV14 = "$artifactPrefix-preference-v14:_"
        const val supportCoreUi = "$artifactPrefix-support-core-ui:_"
        const val supportCoreUtils = "$artifactPrefix-support-core-utils:_"
        const val supportV13 = "$artifactPrefix-support-v13:_"
        const val supportV4 = "$artifactPrefix-support-v4:_"
    }

    val lifecycle = Lifecycle // TODO kdoc

    object Lifecycle : IsNotADependency {
        private const val artifactPrefix = "androidx.lifecycle:lifecycle"

        const val runtimeKtx = "$artifactPrefix-runtime-ktx:_"
        const val liveDataKtx = "$artifactPrefix-livedata-ktx:_"
        const val liveDataCoreKtx = "$artifactPrefix-livedata-core-ktx:_"
        const val viewModelKtx = "$artifactPrefix-viewmodel-ktx:_"

        const val process = "$artifactPrefix-process:_"
        const val service = "$artifactPrefix-service:_"
        const val viewModelSavedState = "$artifactPrefix-viewmodel-savedstate:_"

        const val viewModelCompose = "$artifactPrefix-viewmodel-compose:_"

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

    object Media2 : IsNotADependency {
        private const val artifactPrefix = "androidx.media2:media2"

        const val session = "$artifactPrefix-session:_"
        const val widget = "$artifactPrefix-widget:_"
        const val player = "$artifactPrefix-player:_"
        const val exoplayer = "$artifactPrefix-exoplayer:_"

        const val common = "$artifactPrefix-common:_"
    }

    const val mediaRouter = "androidx.mediarouter:mediarouter:_"

    const val multidex = "androidx.multidex:multidex:_"
    const val multidexInstrumentation = "androidx.multidex:multidex-instrumentation:_"

    val navigation = Navigation // TODO kdoc

    object Navigation : IsNotADependency {
        private const val artifactPrefix = "androidx.navigation:navigation"

        const val fragmentKtx = "$artifactPrefix-fragment-ktx:_"
        const val uiKtx = "$artifactPrefix-ui-ktx:_"

        const val compose = "$artifactPrefix-compose:_"

        const val dynamicFeaturesFragment = "$artifactPrefix-dynamic-features-fragment:_"

        const val safeArgsGenerator = "$artifactPrefix-safe-args-generator:_"
        const val safeArgsGradlePlugin = "$artifactPrefix-safe-args-gradle-plugin:_"

        const val testing = "$artifactPrefix-testing:_"

        // All the Navigation artifacts below are transitively included in fragmentKtx and uiKtx.

        const val commonKtx = "$artifactPrefix-common-ktx:_"
        const val runtimeKtx = "$artifactPrefix-runtime-ktx:_"

        const val fragment = "$artifactPrefix-fragment:_"
        const val ui = "$artifactPrefix-ui:_"

        const val runtime = "$artifactPrefix-runtime:_"
        const val common = "$artifactPrefix-common:_"
    }

    val paging = Paging // TODO kdoc

    object Paging : IsNotADependency {
        private const val artifactPrefix = "androidx.paging:paging"

        const val commonKtx = "$artifactPrefix-common-ktx:_"
        const val runtimeKtx = "$artifactPrefix-runtime-ktx:_"

        const val compose = "$artifactPrefix-compose:_"

        const val rxJava2Ktx = "$artifactPrefix-rxjava2-ktx:_"

        const val common = "$artifactPrefix-common:_"
        const val runtime = "$artifactPrefix-runtime:_"

        const val rxJava2 = "$artifactPrefix-rxjava2:_"
        const val rxJava3 = "$artifactPrefix-rxjava3:_"
    }

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

    val room = Room // TODO kdoc

    object Room : IsNotADependency {
        private const val artifactPrefix = "androidx.room:room"

        const val ktx = "$artifactPrefix-ktx:_"

        @Deprecated("Replaced by Room KTX", ReplaceWith("ktx"))
        const val coroutines = "$artifactPrefix-coroutines:_"
        const val compiler = "$artifactPrefix-compiler:_"
        const val testing = "$artifactPrefix-testing:_"

        const val migration = "$artifactPrefix-migration:_"
        const val runtime = "$artifactPrefix-runtime:_"
        const val common = "$artifactPrefix-common:_"

        const val guava = "$artifactPrefix-guava:_"
        const val rxJava2 = "$artifactPrefix-rxjava2:_"
    }

    const val savedState = "androidx.savedstate:savedstate:_"
    const val savedStateKtx = "androidx.savedstate:savedstate-ktx:_"

    val security = Security // TODO kdoc

    object Security : IsNotADependency {
        private const val artifactPrefix = "androidx.security:security"

        const val crypto = "$artifactPrefix-crypto:_"
        const val cryptoKtx = "$artifactPrefix-crypto-ktx:_"

        const val appAuthenticator = "$artifactPrefix-app-authenticator:_"
        const val appAuthenticatorTesting = "$artifactPrefix-app-authenticator-testing:_"

        const val identityCredential = "$artifactPrefix-identity-credential:_"
    }

    const val shareTarget = "androidx.sharetarget:sharetarget:_"

    val slice = Slice // TODO kdoc

    object Slice : IsNotADependency {
        private const val artifactPrefix = "androidx.slice:slice"

        const val buildersKtx = "$artifactPrefix-builders-ktx:_"

        const val builders = "androidx.slice:slice-builders:_"
        const val core = "$artifactPrefix-core:_"
        const val view = "$artifactPrefix-view:_"
    }

    const val slidingPaneLayout = "androidx.slidingpanelayout:slidingpanelayout:_"

    const val sqliteKtx = "androidx.sqlite:sqlite-ktx:_"
    const val sqliteFramework = "androidx.sqlite:sqlite-framework:_"
    const val sqlite = "androidx.sqlite:sqlite:_"

    val startup = Startup // TODO kdoc

    object Startup : IsNotADependency {
        const val runtime = "androidx.startup:startup-runtime:_"
    }

    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:_"

    val test = Test // TODO kdoc

    object Test : IsNotADependency {
        private const val coreVersion = "_"
        private const val group = "androidx.test"

        const val coreKtx = "$group:core-ktx:$coreVersion"
        const val core = "$group:core:$coreVersion"

        const val monitor = "$group:monitor:_"
        const val orchestrator = "$group:orchestrator:_"

        const val rules = "$group:rules:_"
        const val runner = "$group:runner:_"

        val ext = Ext

        object Ext : IsNotADependency {
            private const val extGroup = "androidx.test.ext"
            private const val extJunitVersion = "_"

            const val junit = "$extGroup:junit:$extJunitVersion"
            const val junitKtx = "$extGroup:junit-ktx:$extJunitVersion"

            const val truth = "$extGroup:truth:_"
        }

        const val services = "$group.services:test-services:_"

        const val jankTestHelper = "$group.janktesthelper:janktesthelper:_"
        const val uiAutomator = "$group.uiautomator:uiautomator:_"

        val espresso = Espresso

        object Espresso : IsNotADependency {
            private const val group = "androidx.test.espresso"
            private const val artifactPrefix = "$group:espresso"

            const val core = "$artifactPrefix-core:_"
            const val contrib = "$artifactPrefix-contrib:_"
            const val idlingResource = "$artifactPrefix-idling-resource:_"
            const val intents = "$artifactPrefix-intents:_"
            const val accessibility = "$artifactPrefix-accessibility:_"
            const val remote = "$artifactPrefix-remote:_"
            const val web = "$artifactPrefix-web:_"

            val idling = Idling

            object Idling : IsNotADependency {
                private const val artifactPrefix = "$group.idling:idling"

                const val concurrent = "$artifactPrefix-concurrent:_"
                const val net = "$artifactPrefix-net:_"
            }
        }
    }

    const val textClassifier = "androidx.textclassifier:textclassifier:_"

    const val tracing = "androidx.tracing:tracing:_"
    const val tracingKtx = "androidx.tracing:tracing-ktx:_"

    const val transition = "androidx.transition:transition:_"
    const val transitionKtx = "androidx.transition:transition-ktx:_"

    const val tvProvider = "androidx.tvprovider:tvprovider:_"

    @Incubating
    val ui = Ui // TODO kdoc

    @Incubating
    object Ui : IsNotADependency {
        const val test = "androidx.ui:ui-test:_" // "Not Yet Refactored (no changes)" as of version 0.1.0-dev15.
        const val tooling = "androidx.ui:ui-tooling:_" // "Not Yet Refactored (no changes)" as of version 0.1.0-dev15.
    }

    const val vectorDrawable = "androidx.vectordrawable:vectordrawable:_"
    const val vectorDrawableAnimated = "androidx.vectordrawable:vectordrawable-animated:_"
    const val vectorDrawableSeekable = "androidx.vectordrawable:vectordrawable-seekable:_"

    const val versionedParcelable = "androidx.versionedparcelable:versionedparcelable:_"

    const val viewPager = "androidx.viewpager:viewpager:_"
    const val viewPager2 = "androidx.viewpager2:viewpager2:_"

    val wear = Wear

    object Wear : DependencyNotationAndGroup(group = "androidx.wear", name = "wear") {
        @JvmField
        val input = "$artifactPrefix-input:_"
        @JvmField
        val inputTesting = "$artifactPrefix-input-testing:_"

        @JvmField
        val ongoing = "$artifactPrefix-ongoing:_"

        @JvmField
        val phoneInteractions = "$artifactPrefix-phone-interactions:_"
        @JvmField
        val remoteInteractions = "$artifactPrefix-remote-interactions:_"

        val complications = Complications

        object Complications {
            private const val artifactPrefix = "androidx.wear:wear-complications"

            const val data = "$artifactPrefix-data:_"
            const val provider = "$artifactPrefix-provider:_"
        }

        val tiles = Tiles

        object Tiles : DependencyNotationAndGroup(group = "androidx.wear.tiles", name = "tiles") {
            @JvmField
            val proto = "$artifactPrefix-proto:_"
            @JvmField
            val renderer = "$artifactPrefix-renderer:_"
        }

        val watchFace = WatchFace

        object WatchFace : DependencyNotationAndGroup(group = "androidx.wear", name = "wear-watchface") {
            @JvmField
            val client = "$artifactPrefix-client:_"
            @JvmField
            val clientGuava = "$artifactPrefix-client-guava:_"
            @JvmField
            val complicationsRendering = "$artifactPrefix-complications-rendering:_"
            @JvmField
            val data = "$artifactPrefix-data:_"
            @JvmField
            val editor = "$artifactPrefix-editor:_"
            @JvmField
            val editorGuava = "$artifactPrefix-editor-guava:_"
            @JvmField
            val guava = "$artifactPrefix-guava:_"
            @JvmField
            val style = "$artifactPrefix-style:_"
        }
    }

    const val webkit = "androidx.webkit:webkit:_"

    val window = Window // TODO kdoc

    object Window : DependencyNotationAndGroup(group = "androidx.window", name = "window") {
        @JvmField
        val extensions = "$artifactPrefix-extensions:_"
    }

    val work = Work // TODO kdoc

    object Work : IsNotADependency {
        const val runtimeKtx = "androidx.work:work-runtime-ktx:_"

        const val multiprocess = "androidx.work:work-multiprocess:_"
        const val gcm = "androidx.work:work-gcm:_"
        const val testing = "androidx.work:work-testing:_"

        const val runtime = "androidx.work:work-runtime:_"

        const val rxJava2 = "androidx.work:work-rxjava2:_"
        const val rxJava3 = "androidx.work:work-rxjava3:_"
    }
}
