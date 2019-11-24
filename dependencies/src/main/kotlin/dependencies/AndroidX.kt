package dependencies

/**
 * The actual dependency version comes from `gradle.properties`
 * from either `version.androidx.$GROUP=xxx` or `version.androidx.$GROUP...$NAME=xxx`
 **/

object AndroidX {
    const val annotation = "androidx.annotation:annotation:$placeholderVersion"
    const val appCompat = "androidx.appcompat:appcompat:$placeholderVersion"
    const val asyncLayoutInflater = "androidx.asynclayoutinflater:asynclayoutinflater:$placeholderVersion"
    const val browser = "androidx.browser:browser:$placeholderVersion"
    const val car = "androidx.car:car:$placeholderVersion"
    const val cardView = "androidx.cardview:cardview:$placeholderVersion"
    const val collection = "androidx.collection:collection:$placeholderVersion"
    const val collectionKtx = "androidx.collection:collection-ktx:$placeholderVersion"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:$placeholderVersion"
    const val constraintLayoutSolver =
        "androidx.constraintlayout:constraintlayout-solver:$placeholderVersion"
    const val contentPager = "androidx.contentpager:contentpager:$placeholderVersion"
    const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:$placeholderVersion"
    const val core = "androidx.core:core:$placeholderVersion"
    const val coreKtx = "androidx.core:core-ktx:$placeholderVersion"
    const val cursorAdapter = "androidx.cursoradapter:cursoradapter:$placeholderVersion"
    const val customView = "androidx.customview:customview:$placeholderVersion"
    const val documentFile = "androidx.documentfile:documentfile:$placeholderVersion"
    const val drawerLayout = "androidx.drawerlayout:drawerlayout:$placeholderVersion"
    const val dynamicAnimation = "androidx.dynamicanimation:dynamicanimation:$placeholderVersion"
    const val emoji = "androidx.emoji:emoji:$placeholderVersion"
    const val emojiAppCompat = "androidx.emoji:emoji-appcompat:$placeholderVersion"
    const val emojiBundler = "androidx.emoji:emoji-bundled:$placeholderVersion"
    const val exifInterface = "androidx.exifinterface:exifinterface:$placeholderVersion"
    const val fragment = "androidx.fragment:fragment:$placeholderVersion"
    const val fragmentKtx = "androidx.fragment:fragment-ktx:$placeholderVersion"
    const val gridLayout = "androidx.gridlayout:gridlayout:$placeholderVersion"
    const val heifWriter = "androidx.heifwriter:heifwriter:$placeholderVersion"
    const val interpolator = "androidx.interpolator:interpolator:$placeholderVersion"
    const val leanback = "androidx.leanback:leanback:$placeholderVersion"
    const val leanbackPreference =
        "androidx.leanback:leanback-preference:$placeholderVersion"
    const val loader = "androidx.loader:loader:$placeholderVersion"
    const val localBroadcastManager =
        "androidx.localbroadcastmanager:localbroadcastmanager:$placeholderVersion"
    const val media = "androidx.media:media:$placeholderVersion"
    const val mediaWidget = "androidx.media-widget:media-widget:$placeholderVersion"
    const val media2 = "androidx.media2:media2:$placeholderVersion"
    const val mediaRouter = "androidx.mediarouter:mediarouter:$placeholderVersion"
    const val multidex = "androidx.multidex:multidex:$placeholderVersion"
    const val multidexInstrumentation =
        "androidx.multidex:multidex-instrumentation:$placeholderVersion"
    const val palette = "androidx.palette:palette:$placeholderVersion"
    const val paletteKtx = "androidx.palette:palette-ktx:$placeholderVersion"
    const val percentLayout = "androidx.percentlayout:percentlayout:$placeholderVersion"
    const val preference = "androidx.preference:preference:$placeholderVersion"
    const val preferenceKtx = "androidx.preference:preference-ktx:$placeholderVersion"
    const val print = "androidx.print:print:$placeholderVersion"
    const val recommendation = "androidx.recommendation:recommendation:$placeholderVersion"
    const val recyclerView = "androidx.recyclerview:recyclerview:$placeholderVersion"
    const val recyclerViewSelection =
        "androidx.recyclerview:recyclerview-selection:$placeholderVersion"
    const val slidingPaneLayout = "androidx.slidingpanelayout:slidingpanelayout:$placeholderVersion"
    const val sqlite = "androidx.sqlite:sqlite:$placeholderVersion"
    const val sqliteFramework = "androidx.sqlite:sqlite-framework:$placeholderVersion"
    const val sqliteKtx = "androidx.sqlite:sqlite-ktx:$placeholderVersion"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:$placeholderVersion"
    const val transition = "androidx.transition:transition:$placeholderVersion"
    const val tvProvider = "androidx.tvprovider:tvprovider:$placeholderVersion"
    const val vectorDrawable =
        "androidx.vectordrawable:vectordrawable:$placeholderVersion"
    const val vectorDrawableAnimated =
        "androidx.vectordrawable:vectordrawable-animated:$placeholderVersion"
    const val versionedParcelable = "androidx.versionedparcelable:versionedparcelable:$placeholderVersion"
    const val viewPager = "androidx.viewpager:viewpager:$placeholderVersion"
    const val wear = "androidx.wear:wear:$placeholderVersion"
    const val webkit = "androidx.webkit:webkit:$placeholderVersion"


    /**
     * The actual dependency version comes from `gradle.properties`
     * Either `version.androidx.lifecycle` or `version.androidx.lifecycle...$NAME`
     **/
    val lifecycle = Lifecycle
    object Lifecycle {
        const val common = "androidx.lifecycle:lifecycle-common:$placeholderVersion"
        const val commonJava8 = "androidx.lifecycle:lifecycle-common-java8:$placeholderVersion"
        const val compiler = "androidx.lifecycle:lifecycle-compiler:$placeholderVersion"
        const val extensions = "androidx.lifecycle:lifecycle-extensions:$placeholderVersion"
        const val liveData = "androidx.lifecycle:lifecycle-livedata:$placeholderVersion"
        const val liveDataCore = "androidx.lifecycle:lifecycle-livedata-core:$placeholderVersion"
        const val process = "androidx.lifecycle:lifecycle-process:$placeholderVersion"
        const val reactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams:$placeholderVersion"
        const val reactiveStreamsKtx =
            "androidx.lifecycle:lifecycle-reactivestreams-ktx:$placeholderVersion"
        const val runtime = "androidx.lifecycle:lifecycle-runtime:$placeholderVersion"
        const val service = "androidx.lifecycle:lifecycle-service:$placeholderVersion"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel:$placeholderVersion"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$placeholderVersion"
    }

    /**
     * The actual dependency version comes from `gradle.properties`
     * from either `version.androidx.room` or `version.androidx.room...$NAME`
     **/
    val room = Room

    object Room {
        private const val artifact = "androidx.room:room"
        const val common = "$artifact-common:$placeholderVersion"
        const val compiler = "$artifact-compiler:$placeholderVersion"
        const val guava = "$artifact-guava:$placeholderVersion"
        const val migration = "$artifact-migration:$placeholderVersion"
        const val runtime = "$artifact-runtime:$placeholderVersion"
        const val rxJava2 = "$artifact-rxjava2:$placeholderVersion"
        const val testing = "$artifact-testing:$placeholderVersion"
    }


    /**
     * The actual dependency version comes from `gradle.properties`
     * from `version.androidx.paging=xxx` or `version.androidx.paging..$NAME=xxx`
     **/
    val paging = Paging
    object Paging {
        const val common = "androidx.paging:paging-common:$placeholderVersion"
        const val runtime = "androidx.paging:paging-runtime:$placeholderVersion"
        const val rxJava2 = "androidx.paging:paging-rxjava2:$placeholderVersion"
    }

    /**
     * The actual dependency version comes from `gradle.properties`
     * from `version.androidx.work=xxx` or `version.androidx.work..$NAME=xxx`
     **/
    val work = Work
    object Work {
        const val runtime = "androidx.work:work-runtime:$placeholderVersion"
        const val runtimeKtx = "androidx.work:work-runtime-ktx:$placeholderVersion"
        const val testing = "androidx.work:work-testing:$placeholderVersion"
    }

    /**
     * The actual dependency version comes from `gradle.properties`
     * from `version.androidx.navigation=xxx` or `version.androidx.navigation..$NAME=xxx`
     **/
    val navigation = Navigation
    object Navigation {
        private const val artifactPrefix = "androidx.navigation:navigation"
        const val common = "$artifactPrefix-common:$placeholderVersion"
        const val commonKtx = "$artifactPrefix-common-ktx:$placeholderVersion"
        const val fragment = "$artifactPrefix-fragment:$placeholderVersion"
        const val fragmentKtx = "$artifactPrefix-fragment-ktx:$placeholderVersion"
        const val runtime = "$artifactPrefix-runtime:$placeholderVersion"
        const val runtimeKtx = "$artifactPrefix-runtime-ktx:$placeholderVersion"
        const val ui = "$artifactPrefix-ui:$placeholderVersion"
        const val uiKtx = "$artifactPrefix-ui-ktx:$placeholderVersion"
        const val safeArgsGenerator = "$artifactPrefix-safe-args-generator:$placeholderVersion"
        const val safeArgsGradlePlugin = "$artifactPrefix-safe-args-gradle-plugin:$placeholderVersion"
    }

    /**
     * The actual dependency version comes from `gradle.properties`
     * from `version.androidx.slice=xxx` or `version.androidx.slice..$NAME=xxx`
     **/
    val slice = Slice
    object Slice {
        const val builders = "androidx.slice:slice-builders:$placeholderVersion"
        const val buildersKtx = "androidx.slice:slice-builders-ktx:$placeholderVersion"
        const val core = "androidx.slice:slice-core:$placeholderVersion"
        const val view = "androidx.slice:slice-view:$placeholderVersion"
    }

    /**
     * The actual dependency version comes from `gradle.properties`
     * from `version.androidx.arch.core=xxx` or `version.androidx.arch.core..$NAME=xxx`
     **/
    val archCore = ArchCore
    object ArchCore {
        const val common = "androidx.arch.core:core-common:$placeholderVersion"
        const val runtime = "androidx.arch.core:core-runtime:$placeholderVersion"
        const val testing = "androidx.arch.core:core-testing:$placeholderVersion"
    }

    /**
     * The actual dependency version comes from `gradle.properties`
     * from `version.androidx.test=xxx` or `version.androidx.test..$NAME=xxx`
     **/
    val test = Test
    object Test {
        const val core = "androidx.test:core:$placeholderVersion"
        const val coreKtx = "androidx.test:core-ktx:$placeholderVersion"
        const val monitor = "androidx.test:monitor:$placeholderVersion"
        const val orchestrator = "androidx.test:orchestrator:$placeholderVersion"
        const val rules = "androidx.test:rules:$placeholderVersion"
        const val runner = "androidx.test:runner:$placeholderVersion"


        /**
         * The actual dependency version comes from `gradle.properties`
         * from `version.androidx.test=xxx` or `version.androidx.test..$NAME=xxx`
         **/
        val ext = Ext
        object Ext {
            const val junit = "androidx.test.ext:junit:$placeholderVersion"
            const val junitKtx = "androidx.test.ext:junit-ktx:$placeholderVersion"
            const val truth = "androidx.test.ext:truth:$placeholderVersion"
        }

        const val jankTestHelper = "androidx.test.jank:janktesthelper:$placeholderVersion"
        const val jankTestHelperV23 = "androidx.test.jank:janktesthelper-v23:$placeholderVersion"

        const val services = "androidx.test.services:test-services:$placeholderVersion"

        const val uiAutomator = "androidx.test.uiautomator:uiautomator:$placeholderVersion"
        const val uiAutomatorV18 = "androidx.test.uiautomator:uiautomator-v18:$placeholderVersion"

        /**
         * The actual dependency version comes from `gradle.properties`
         * from `version.androidx.test.espresso=xxx` or `version.androidx.test.espresso..$NAME=xxx`
         **/
        val espresso = Espresso
        object Espresso {
            const val core = "androidx.test.espresso:espresso-core:$placeholderVersion"
            const val contrib = "androidx.test.espresso:espresso-contrib:$placeholderVersion"
            const val idlingResource =
                "androidx.test.espresso:espresso-idling-resource:$placeholderVersion"
            const val intents = "androidx.test.espresso:espresso-intents:$placeholderVersion"
            const val accessibility =
                "androidx.test.espresso:espresso-accessibility:$placeholderVersion"
            const val remote = "androidx.test.espresso:espresso-remote:$placeholderVersion"
            const val web = "androidx.test.espresso:espresso-web:$placeholderVersion"


            /**
             * The actual dependency version comes from `gradle.properties`
             * from `version.androidx.test.espresso.idling=xxx` or `version.androidx.test.espresso.idling..$NAME=xxx`
             **/
            val idling = Idling
            object Idling {
                const val concurrent =
                    "androidx.test.espresso.idling:idling-concurrent:$placeholderVersion"
                const val net = "androidx.test.espresso.idling:idling-net:$placeholderVersion"
            }
        }
    }

    /**
     * The actual dependency version comes from `gradle.properties`
     * from `version.androidx.legacy=xxx` or `version.androidx.legacy..$NAME=xxx`
     **/
    val legacy = Legacy
    object Legacy {
        const val preferenceV14 = "androidx.legacy:legacy-preference-v14:$placeholderVersion"
        const val supportCoreUi = "androidx.legacy:legacy-support-core-ui:$placeholderVersion"
        const val supportCoreUtils = "androidx.legacy:legacy-support-core-utils:$placeholderVersion"
        const val supportV13 = "androidx.legacy:legacy-support-v13:$placeholderVersion"
        const val supportV4 = "androidx.legacy:legacy-support-v4:$placeholderVersion"
    }
}
