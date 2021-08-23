@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

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
    val activityKtx = DependencyNotation("androidx.activity", "activity-ktx")

    // androidx.ads intentionally not included because ads are mental pollution.

    val annotation = DependencyNotation("androidx.annotation", "annotation")
    val annotationExperimental = DependencyNotation("androidx.annotation", "annotation-experimental")

    val appCompat = DependencyNotation("androidx.appcompat", "appcompat")
    val appCompatResources = DependencyNotation("androidx.appcompat", "appcompat-resources")

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

    val biometric = DependencyNotation("androidx.biometric", "biometric")
    val biometricKtx = DependencyNotation("androidx.biometric", "biometric-ktx")

    val browser = DependencyNotation("androidx.browser", "browser")

    val camera = Camera

    object Camera : DependencyGroup(group = "androidx.camera") {
        val core = module("camera-core")
        val camera2 = module("camera-camera2")
        val extensions = module("camera-extensions")
        val lifecycle = module("camera-lifecycle")
        val view = module("camera-view")
    }

    val car = DependencyNotation("androidx.car", "car")

    val carApp = CarApp

    object CarApp : DependencyNotationAndGroup(group = "androidx.car.app", name = "app") {
        val testing = module("app-testing")
    }

    val carCluster = DependencyNotation("androidx.car", "car-cluster")

    val cardView = DependencyNotation("androidx.cardview", "cardview")

    val collectionKtx = DependencyNotation("androidx.collection", "collection-ktx")
    val collection = DependencyNotation("androidx.collection", "collection")

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
    }

    val concurrent = Concurrent // TODO kdoc

    object Concurrent : DependencyGroup(group = "androidx.concurrent") {
        val futures = module("concurrent-futures")
        val futuresKtx = module("concurrent-futures-ktx")
    }

    val constraintLayout = DependencyNotation("androidx.constraintlayout", "constraintlayout")
    val constraintLayoutCompose = DependencyNotation("androidx.constraintlayout", "constraintlayout-compose")
    val constraintLayoutSolver = DependencyNotation("androidx.constraintlayout", "constraintlayout-solver")

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

    val dynamicAnimationKtx = DependencyNotation("androidx.dynamicanimation", "dynamicanimation-ktx")
    val dynamicAnimation = DependencyNotation("androidx.dynamicanimation", "dynamicanimation")

    val emoji = DependencyNotation("androidx.emoji", "emoji")
    val emojiAppCompat = DependencyNotation("androidx.emoji", "emoji-appcompat")
    val emojiBundled = DependencyNotation("androidx.emoji", "emoji-bundled")

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

    val fragmentKtx = DependencyNotation("androidx.fragment", "fragment-ktx")
    val fragment = DependencyNotation("androidx.fragment", "fragment")
    val fragmentTesting = DependencyNotation("androidx.fragment", "fragment-testing")

    val games = Games

    object Games : DependencyGroup(group = "androidx.games") {

        val framePacing = module("games-frame-pacing")
        val performanceTuner = module("games-performance-tuner")
        val activity = module("games-activity")
        val controller = module("games-controller")
        val textInput = module("games-text-input")
    }

    val gaming = Gaming // TODO kdoc

    object Gaming : DependencyGroup(group = "androidx.gaming") {

        @Deprecated("Group was renamed", ReplaceWith("AndroidX.games.framePacing"))
        val framePacing = module("gaming-frame-pacing")

        @Deprecated("Group was renamed", ReplaceWith("AndroidX.games.performanceTuner"))
        val performanceTuner = module("gaming-performance-tuner")

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

    val leanback = Leanback

    object Leanback : DependencyNotationAndGroup(group = "androidx.leanback", name = "leanback") {
        val preference = module("leanback-preference")
        val paging = module("leanback-paging")
        val tab = module("leanback-tab")
    }

    @Deprecated("Dependency notation moved", ReplaceWith("AndroidX.leanback.preference"))
    val leanbackPreference = DependencyNotation("androidx.leanback", "leanback-preference")

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

    val mediaRouter = DependencyNotation("androidx.mediarouter", "mediarouter")

    val multidex = DependencyNotation("androidx.multidex", "multidex")
    val multidexInstrumentation = DependencyNotation("androidx.multidex", "multidex-instrumentation")

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

    val paletteKtx = DependencyNotation("androidx.palette", "palette-ktx")
    val palette = DependencyNotation("androidx.palette", "palette")

    val percentLayout = DependencyNotation("androidx.percentlayout", "percentlayout")

    val preferenceKtx = DependencyNotation("androidx.preference", "preference-ktx")
    val preference = DependencyNotation("androidx.preference", "preference")

    val print = DependencyNotation("androidx.print", "print")

    val recommendation = DependencyNotation("androidx.recommendation", "recommendation")

    val recyclerView = DependencyNotation("androidx.recyclerview", "recyclerview")
    val recyclerViewSelection = DependencyNotation("androidx.recyclerview", "recyclerview-selection")

    val remoteCallback = DependencyNotation("androidx.remotecallback", "remotecallback")
    val remoteCallbackProcessor = DependencyNotation("androidx.remotecallback", "remotecallback-processor")

    val room = Room // TODO kdoc

    object Room : DependencyGroup(group = "androidx.room") {

        val ktx = module("room-ktx")

        @Deprecated("Replaced by Room KTX", ReplaceWith("ktx"))
        val coroutines = module("room-coroutines")
        val compiler = module("room-compiler")
        val testing = module("room-testing")

        val migration = module("room-migration")
        val runtime = module("room-runtime")
        val common = module("room-common")

        val guava = module("room-guava")
        val rxJava2 = module("room-rxjava2")
    }

    val savedState = DependencyNotation("androidx.savedstate", "savedstate")
    val savedStateKtx = DependencyNotation("androidx.savedstate", "savedstate-ktx")

    val security = Security // TODO kdoc

    object Security : DependencyGroup(group = "androidx.security") {

        val crypto = module("security-crypto")
        val cryptoKtx = module("security-crypto-ktx")

        val appAuthenticator = module("security-app-authenticator")
        val appAuthenticatorTesting = module("security-app-authenticator-testing")

        val identityCredential = module("security-identity-credential")
    }

    val shareTarget = DependencyNotation("androidx.sharetarget", "sharetarget")

    val slice = Slice // TODO kdoc

    object Slice : DependencyGroup(group = "androidx.slice") {

        val buildersKtx = module("slice-builders-ktx")
        val builders = module("slice-builders")

        val core = module("slice-core")
        val view = module("slice-view")
    }

    val slidingPaneLayout = DependencyNotation("androidx.slidingpanelayout", "slidingpanelayout")

    val sqliteKtx = DependencyNotation("androidx.sqlite", "sqlite-ktx")
    val sqliteFramework = DependencyNotation("androidx.sqlite", "sqlite-framework")
    val sqlite = DependencyNotation("androidx.sqlite", "sqlite")

    val startup = Startup // TODO kdoc

    object Startup : DependencyGroup(group = "androidx.startup") {
        val runtime = module("startup-runtime")
    }

    val swipeRefreshLayout = DependencyNotation("androidx.swiperefreshlayout", "swiperefreshlayout")

    val test = Test // TODO kdoc

    object Test : DependencyGroup(group = "androidx.test") {

        val coreKtx = module("core-ktx")
        val core = module("core")

        val monitor = module("monitor")
        val orchestrator = module("orchestrator")

        val rules = module("rules")
        val runner = module("runner")

        val ext = Ext

        object Ext : DependencyGroup(group = "androidx.test.ext") {
            val junit = module("junit")
            val junitKtx = module("junit-ktx")

            val truth = module("truth")
        }

        val services = DependencyNotation("androidx.test.services", "test-services")

        val jankTestHelper = DependencyNotation("androidx.test.janktesthelper", "janktesthelper")
        val uiAutomator = DependencyNotation("androidx.test.uiautomator", "uiautomator")

        val espresso = Espresso

        object Espresso : DependencyGroup(group = "androidx.test.espresso") {

            val core = module("espresso-core")
            val contrib = module("espresso-contrib")
            val idlingResource = module("espresso-idling-resource")
            val intents = module("espresso-intents")
            val accessibility = module("espresso-accessibility")
            val remote = module("espresso-remote")
            val web = module("espresso-web")

            val idling = Idling

            object Idling : DependencyGroup(group = "androidx.test.espresso.idling") {
                val concurrent = module("idling-concurrent")
                val net = module("idling-net")
            }
        }
    }

    val textClassifier = DependencyNotation("androidx.textclassifier", "textclassifier")

    val tracing = DependencyNotation("androidx.tracing", "tracing")
    val tracingKtx = DependencyNotation("androidx.tracing", "tracing-ktx")

    val transition = DependencyNotation("androidx.transition", "transition")
    val transitionKtx = DependencyNotation("androidx.transition", "transition-ktx")

    val tvProvider = DependencyNotation("androidx.tvprovider", "tvprovider")

    @Incubating
    val ui = Ui // TODO kdoc

    @Incubating
    object Ui : IsNotADependency {
        const val test = "androidx.ui:ui-test:_" // "Not Yet Refactored (no changes)" as of version 0.1.0-dev15.
        const val tooling = "androidx.ui:ui-tooling:_" // "Not Yet Refactored (no changes)" as of version 0.1.0-dev15.
    }

    val vectorDrawable = DependencyNotation("androidx.vectordrawable", "vectordrawable")
    val vectorDrawableAnimated = DependencyNotation("androidx.vectordrawable", "vectordrawable-animated")
    val vectorDrawableSeekable = DependencyNotation("androidx.vectordrawable", "vectordrawable-seekable")

    val versionedParcelable = DependencyNotation("androidx.versionedparcelable", "versionedparcelable")

    val viewPager = DependencyNotation("androidx.viewpager", "viewpager")
    val viewPager2 = DependencyNotation("androidx.viewpager2", "viewpager2")

    val wear = Wear

    object Wear : DependencyNotationAndGroup(group = "androidx.wear", name = "wear") {

        val input = module("wear-input")
        val inputTesting = module("wear-input-testing")

        val ongoing = module("wear-ongoing")

        val phoneInteractions = module("wear-phone-interactions")
        val remoteInteractions = module("wear-remote-interactions")

        val complications = Complications

        object Complications : DependencyGroup(group = group) {
            val data = module("wear-complications-data")
            val provider = module("wear-complications-provider")
        }

        val compose = Compose

        object Compose : DependencyGroup(group = "androidx.wear.compose") {
            val foundation = module("compose-foundation")
            val material = module("compose-material")
        }

        val tiles = Tiles

        object Tiles : DependencyNotationAndGroup(group = "androidx.wear.tiles", name = "tiles") {
            val proto = module("tiles-proto")
            val renderer = module("tiles-renderer")
        }

        val watchFace = WatchFace

        object WatchFace : DependencyNotationAndGroup(group = "androidx.wear", name = "wear-watchface") {
            val client = module("wear-watchface-client")
            val clientGuava = module("wear-watchface-client-guava")

            val complicationsRendering = module("wear-watchface-complications-rendering")

            val data = module("wear-watchface-data")

            val editor = module("wear-watchface-editor")
            val editorGuava = module("wear-watchface-editor-guava")

            val guava = module("wear-watchface-guava")

            val style = module("wear-watchface-style")
        }
    }

    val webkit = DependencyNotation(group = "androidx.webkit", name = "webkit")

    val window = Window // TODO kdoc

    object Window : DependencyNotationAndGroup(group = "androidx.window", name = "window") {
        val testing = module("window-testing")

        /** For Java-friendly APIs to register and unregister callbacks */
        val java = module("window-java")
        val rxJava2 = module("window-rxjava2")
        val rxJava3 = module("window-rxjava3")
    }

    val work = Work // TODO kdoc

    object Work : DependencyGroup(group = "androidx.work") {
        val runtimeKtx = module("work-runtime-ktx")

        val multiprocess = module("work-multiprocess")
        val gcm = module("work-gcm")
        val testing = module("work-testing")

        val runtime = module("work-runtime")

        val rxJava2 = module("work-rxjava2")
        val rxJava3 = module("work-rxjava3")
    }
}
