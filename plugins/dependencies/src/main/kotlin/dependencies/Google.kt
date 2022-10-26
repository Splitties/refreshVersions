@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import de.fayard.refreshVersions.core.DependencyNotation
import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.kotlin.dsl.IsNotADependency

object Google : IsNotADependency {

    val playServicesGradlePlugin = DependencyNotation("com.google.gms", "google-services")

    /**
     * Firebase Android libraries.
     *
     * We recommend **reading the official Firebase documention** before using it as **the setup is more than
     * just adding the dependency.**
     *
     * See available libraries with link to their docs [here](https://firebase.google.com/docs/android/setup#available-libraries).
     */
    val firebase get() = Firebase

    val accompanist = Accompanist

    object Accompanist : DependencyGroup(group = "com.google.accompanist") {

        val insets = Insets

        object Insets : DependencyNotationAndGroup(group = "com.google.accompanist", name = "accompanist-insets") {
            val ui = module("accompanist-insets-ui")
        }

        val systemUiController = module("accompanist-systemuicontroller")

        val appCompatTheme = module("accompanist-appcompat-theme")

        val pager = Pager

        object Pager : DependencyNotationAndGroup(group = "com.google.accompanist", name = "accompanist-pager") {
            val indicators = module("accompanist-pager-indicators")
        }

        val flowLayout = module("accompanist-flowlayout")
        val swipeRefresh = module("accompanist-swiperefresh")

        val placeholder = Placeholder

        object Placeholder : DependencyNotationAndGroup(
            group = "com.google.accompanist",
            name = "accompanist-placeholder"
        ) {
            val material = module("accompanist-placeholder-material")
        }

        val drawablePainter = module("accompanist-drawablepainter")

        val permissions = module("accompanist-permissions")

        val navigationMaterial = module("accompanist-navigation-material")
        val navigationAnimation = module("accompanist-navigation-animation")

        val webView = module("accompanist-webview")
    }

    val ambient = Ambient

    object Ambient : IsNotADependency {

        /**
         * Cross device SDK
         *
         * The Cross device SDK simplifies the development of rich and engaging multi-device experiences.
         *
         * The Cross device SDK enables the following core functionality:
         * - Device discovery and authorization
         * - Secure connections and data transfers
         * - Multi-device sessions
         *
         * [Official webpage](https://developer.android.com/guide/topics/connectivity/cross-device-sdk/overview).
         *
         * [Official Kotlin sample on GitHub](https://github.com/android/connectivity-samples/tree/main/CrossDeviceRockPaperScissorsKotlin).
         */
        val crossDevice = DependencyNotation("com.google.ambient.crossdevice", "crossdevice")
    }

    val android = Android

    object Android : IsNotADependency {

        /**
         * Strict version checking plugin
         *
         * Use this plugin if you're not using the Google Services plugin, but still want strict version checking
         * of your dependencies.
         *
         * Guide: [Strict version matching](https://developers.google.com/android/guides/versioning#strict-version-matching)
         *
         * @see playServicesGradlePlugin
         */
        val versionMatcherPlugin = DependencyNotation("com.google.android.gms", "strict-version-matcher-plugin")

        /**
         * Open-source licenses plugin
         *
         * @see PlayServices.openSourceLicenses
         */
        val openSourceLicensesPlugin = DependencyNotation("com.google.android.gms", "oss-licenses-plugin")

        val browserHelper = DependencyNotation("com.google.androidbrowserhelper", "androidbrowserhelper")

        /**
         * Android FHIR (Fast Healthcare Interoperability Resources) SDK
         *
         * The Android [FHIR](https://www.hl7.org/fhir/) SDK is a set of Kotlin libraries for building offline-capable,
         * mobile-first healthcare applications using FHIR resources on Android.
         *
         * GitHub repo: [google/android-fhir](https://github.com/google/android-fhir)
         */
        val fhir = Fhir

        object Fhir : DependencyGroup(group = "com.google.android.fhir") {

            /**
             * Wiki page: [FHIR Engine Library](https://github.com/google/android-fhir/wiki/FHIR-Engine-Library)
             */
            val engine = module("engine")

            /**
             * Wiki page: [Structured Data Capture Library](https://github.com/google/android-fhir/wiki/Structured-Data-Capture-Library)
             */
            val dataCapture = module("data-capture")

            /**
             * Wiki page: [Workflow Library](https://github.com/google/android-fhir/wiki/Workflow-Library)
             */
            val workflow = module("workflow")
        }

        /**
         * FlexboxLayout is a library project which brings the similar capabilities of
         * [CSS Flexible Box Layout Module](https://www.w3.org/TR/css-flexbox-1) to Android.
         *
         * GitHub repo: [google/flexbox-layout](https://github.com/google/flexbox-layout)
         *
         * [GitHub Releases](https://github.com/google/flexbox-layout/releases)
         */
        val flexbox = DependencyNotation("com.google.android.flexbox", "flexbox")

        /**
         * Contains extension libraries for Google Maps for Android.
         *
         * @see Google.Android.PlayServices.maps
         */
        val maps = Maps

        object Maps : DependencyGroup(group = "com.google.maps.android") {

            /**
             * Kotlin extensions for Google Maps
             *
             * Make sure to also add a dependency on `Google.android.playServices.maps` because it is updated
             * separately from this library.
             *
             * GitHub repo: [googlemaps/android-maps-ktx](https://github.com/googlemaps/android-maps-ktx)
             *
             * @see Google.Android.PlayServices.maps
             */
            val ktx = module("maps-ktx")

            /**
             * Jetpack Compose components for the Maps SDK for Android
             *
             * Make sure to also add a dependency on `Google.android.playServices.maps` because it is updated
             * separately from this library.
             *
             * GitHub repo: [googlemaps/android-maps-compose](https://github.com/googlemaps/android-maps-compose)
             *
             * @see Google.Android.PlayServices.maps
             */
            val compose = module("maps-compose")

            /**
             * RxJava bindings for the Maps SDK for Android
             *
             * Make sure to also add a dependency on `Google.android.playServices.maps` because it is updated
             * separately from this library.
             *
             * GitHub repo: [googlemaps/android-maps-rx](https://github.com/googlemaps/android-maps-rx)
             *
             * @see Google.Android.PlayServices.maps
             * @see Google.Android.Maps.ktx
             */
            val rx = module("maps-rx")

            /**
             * Maps SDK for Android Utility Library
             *
             * Make sure to also add a dependency on `Google.android.playServices.maps` because it is updated
             * separately from this library.
             *
             * GitHub repo: [googlemaps/android-maps-utils](https://github.com/googlemaps/android-maps-utils)
             *
             * @see Google.Android.Maps.Utils.ktx
             */
            val utils = Utils

            object Utils : DependencyNotationAndGroup(group = group, name = "android-maps-utils") {

                /**
                 * Kotlin extensions for the [Google.Android.Maps.utils] library.
                 *
                 * Make sure to also add a dependency on `Google.android.maps.utils` because it is updated
                 * separately from the KTX library.
                 *
                 * GitHub repo: [googlemaps/android-maps-ktx](https://github.com/googlemaps/android-maps-ktx)
                 *
                 * @see Google.Android.Maps.utils
                 */
                val ktx = module("maps-utils-ktx")
            }

            /**
             * Places SDK for Android
             *
             * [Overview](https://developers.google.com/maps/documentation/places/android-sdk/overview)
             */
            val places = Places

            object Places : DependencyNotationAndGroup(group = "com.google.android.libraries.places", name = "places") {

                /**
                 * Kotlin extensions for the Places SDK for Android
                 *
                 * Make sure to also add a dependency on `Google.android.maps.places` because it is updated
                 * separately from this library.
                 *
                 * [Official webpage](https://developers.google.com/maps/documentation/places/android-sdk/ktx)
                 *
                 * GitHub repo: [googlemaps/android-maps-ktx](https://github.com/googlemaps/android-maps-ktx)
                 *
                 * @see Google.Android.Maps.places
                 */
                val ktx = DependencyNotation(Maps.group, "maps-ktx")

                /**
                 * RxJava bindings for the Places SDK for Android
                 *
                 * Make sure to also add a dependency on `Google.android.maps.places` because it is updated
                 * separately from this library.
                 *
                 * GitHub repo: [googlemaps/android-maps-rx](https://github.com/googlemaps/android-maps-rx)
                 *
                 * @see Google.Android.Maps.places
                 * @see Google.Android.Maps.Places.ktx
                 */
                val rx = DependencyNotation(Maps.group, "maps-rx")
            }
        }

        val material = Material

        object Material : DependencyNotationAndGroup(group = "com.google.android.material", name = "material") {
            val composeThemeAdapter = module("compose-theme-adapter")
            val composeThemeAdapter3 = module("compose-theme-adapter-3")
        }

        val play = Play

        object Play : DependencyGroup(group = "com.google.android.play") {
            val core = module("core")
            val coreKtx = module("core-ktx")
        }

        val playServices = PlayServices

        object PlayServices : DependencyGroup(group = "com.google.android.gms") {

            // Play Services Ads intentionally not included because ads are mental pollution.

            /** Google Analytics */
            val analytics = module("play-services-analytics")

            /**
             * Google App Set API
             *
             * The app set ID API returns an ID scoped to the set of apps published under
             * the same Google Play developer account.
             *
             * Guide: [Identify developer-owned apps](https://developer.android.com/training/articles/app-set-id)
             */
            val appset = module("play-services-appset")

            /** Google Account Login */
            val auth = Auth

            object Auth : DependencyNotationAndGroup(group = group, name = "play-services-auth") {

                /** Google SMS Retriever */
                val apiPhone = module("play-services-auth-api-phone")

                /** Google Block Store */
                val blockstore = module("play-services-auth-blockstore")
            }

            /** Google Awareness */
            val awareness = module("play-services-awareness")

            /** Base client library and Google Actions */
            val base = module("play-services-base")

            /**
             * Extends the base client library.
             *
             * @see PlayServices.base
             */
            val basement = module("play-services-basement")

            /**
             * Extend your app to the big screen with Google Cast.
             *
             * Guide: [Google Cast SDK](https://developers.google.com/cast)
             *
             * ## Important:
             *
             * This dependency notation is intended for Android/Google TV **Receiver applications**.
             * For sender applications, use the [Google.Android.PlayServices.Cast.framework] dependency.
             */
            val cast = Cast

            object Cast : DependencyNotationAndGroup(group = group, name = "play-services-cast") {

                /**
                 * Google Cast framework for sender apps.
                 *
                 * Guide: [Setup for Developing with the Cast Application Framework (CAF) for Android](https://developers.google.com/cast/docs/android_sender)
                 *
                 * @see AndroidX.mediaRouter
                 * @see AndroidX.appCompat
                 */
                val framework = module("play-services-cast-framework")

                /**
                 * Guide: [Android TV Receiver Overview](https://developers.google.com/cast/docs/android_tv_receiver)
                 *
                 * @see Google.Android.PlayServices.cast
                 */
                val tv = module("play-services-cast-tv")
            }

            /**
             * Chromium network stack (Cronet)
             *
             * Cronet takes advantage of multiple technologies that reduce the latency
             * and increase the throughput of the network requests that your app needs to work.
             *
             * Guide: [Perform network operations using Cronet](https://developer.android.com/guide/topics/connectivity/cronet)
             *
             * GitHub sample: [GoogleChromeLabs/cronet-sample](https://github.com/GoogleChromeLabs/cronet-sample)
             */
            val cronet = module("play-services-cronet")

            /**
             * Fast IDentity Online (FIDO) Authentication
             *
             * FIDO is a set of standards for fast, simple, strong authentication.
             * It enables app and web developers to use simple APIs to securely authenticate users.
             *
             * Guide: [FIDO Authentication](https://developers.google.com/identity/fido)
             *
             * Codelab: [Build your first WebAuthn app](https://developers.google.com/codelabs/webauthn-reauth/#0)
             */
            val fido = module("play-services-fido")

            /** Google Drive */
            val drive = module("play-services-drive")

            /** Google Fit */
            val fitness = module("play-services-fitness")

            /** Google Play Game services */
            val games = module("play-services-games")

            /** Google Cloud Messaging */
            @Deprecated("Use Firebase Cloud Messaging instead")
            val gcm = module("play-services-gcm")

            /** Google Sign In */
            val identity = module("play-services-identity")

            /** Google Play Instant APIs */
            val instantApps = module("play-services-instantapps")

            /** Google Location and Activity Recognition */
            val location = module("play-services-location")

            /** Google Maps */
            val maps = module("play-services-maps")

            val mlKit = MlKit

            object MlKit : IsNotADependency {

                val naturalLanguage = NaturalLanguage

                object NaturalLanguage : IsNotADependency {

                    /**
                     * [Overview](https://developers.google.com/ml-kit/language/identification)
                     */
                    val languageIdentification = module("play-services-mlkit-language-id")
                }

                val vision = Vision

                object Vision : IsNotADependency {

                    /**
                     * Unbundled version of [Google.MlKit.Vision.barcodeScanning]
                     *
                     * [Overview](https://developers.google.com/ml-kit/vision/barcode-scanning)
                     */
                    val barcodeScanning = module("play-services-mlkit-barcode-scanning")

                    /**
                     * Unbundled version of [Google.MlKit.Vision.faceDetection]
                     *
                     * [Overview](https://developers.google.com/ml-kit/vision/face-detection)
                     */
                    val faceDetection = module("play-services-mlkit-face-detection")

                    /**
                     * Unbundled version of [Google.MlKit.Vision.imageLabeling]
                     *
                     * [Overview](https://developers.google.com/ml-kit/vision/image-labeling)
                     */
                    val imageLabeling = ImageLabeling

                    object ImageLabeling : DependencyNotationAndGroup(
                        group = group,
                        name = "play-services-mlkit-image-labeling"
                    ) {

                        /**
                         * Unbundled version of [Google.MlKit.Vision.ImageLabeling.custom]
                         *
                         * [Overview](https://developers.google.com/ml-kit/vision/image-labeling)
                         *
                         * @see Google.MlKit.Vision.linkFirebase
                         */
                        val custom = module("play-services-mlkit-image-labeling-custom")
                    }

                    /**
                     * [Overview](https://developers.google.com/ml-kit/vision/text-recognition)
                     */
                    val textRecognition = module("play-services-mlkit-text-recognition")
                }
            }

            /** Google Nearby */
            val nearby = module("play-services-nearby")

            /**
             * Open-source Licenses
             *
             * Set of tools designed to give developers an easier way to express the open source software notices
             * of libraries used in their apps.
             *
             * Guide: [Include open source notices](https://developers.google.com/android/guides/opensource)
             *
             * GitHub page: [google/play-services-plugins](https://github.com/google/play-services-plugins)
             *
             * @see openSourceLicensesPlugin
             */
            val openSourceLicenses = module("play-services-oss-licenses")

            /**
             * Screen lock quality check
             *
             * [Usage](https://developer.android.com/work/versions/android-10#screen_lock_quality_check)
             */
            val passwordComplexity = module("play-services-password-complexity")

            /** Google Panorama Viewer */
            val panorama = module("play-services-panorama")

            /** Google Pay for Passes */
            val pay = module("play-services-pay")

            /**
             * reCAPTCHA Enterprise for app instrumentation
             *
             * Guide: [Integrate reCAPTCHA Enterprise with Android apps](https://cloud.google.com/recaptcha-enterprise/docs/instrument-android-apps)
             *
             * ### API reference:
             * - [com.google.android.gms.recaptcha](https://developers.google.com/android/reference/com/google/android/gms/recaptcha/package-summary)
             */
            val recaptcha = module("play-services-recaptcha")

            /** SafetyNet */
            val safetynet = module("play-services-safetynet")

            /**
             * Google Tag Manager
             *
             * The Google Tag Manager interface can be used to implement and manage measurement tags and pixels
             * in mobile applications without having to rebuild and resubmit application binaries to app marketplaces.
             *
             * Guides: [Google Tag Manager](https://developers.google.com/tag-platform/tag-manager/android/v5#next_steps)
             */
            val tagmanager = module("play-services-tagmanager")

            /**
             * Google Tasks API (yet another "Future" type, because they're not using Kotlin coroutines yet).
             * We recommend using it with [KotlinX.Coroutines.playServices].
             */
            val tasks = module("play-services-tasks")

            /** Mobile Vision */
            val vision = module("play-services-vision")

            /** Google Pay for Payments */
            val wallet = module("play-services-wallet")

            /** Wear OS by Google */
            val wearOS = module("play-services-wearable")
        }

        val wearable = DependencyNotation("com.google.android.wearable", "wearable")
        val supportWearable = DependencyNotation("com.google.android.support", "wearable")
    }

    /***
     * With ARCore, build new augmented reality experiences that seamlessly blend the digital and physical worlds.
     *
     * [Official doc here](https://developers.google.com/ar)
     * [Android guide here](https://developers.google.com/ar/develop/java/quickstart).
     */
    val ar = Ar

    object Ar : DependencyGroup(group = "com.google.ar") {
        private const val baseArtifact = "com.google.ar"

        /**
         * [Android guide here](https://developers.google.com/ar/develop/java/quickstart).
         */
        val core = module("core")

        /**
         * [Official doc here](https://developers.google.com/ar/develop/java/sceneform).
         */
        val sceneform = Sceneform

        object Sceneform : DependencyGroup(group = "com.google.ar.sceneform") {

            val animation = module("animation")
            val assets = module("assets")
            val core = module("core")
            val filamentAndroid = module("filament-android")
            val plugin = module("plugin")
            val rendering = module("rendering")
            val sceneformBase = module("sceneform-base")

            val ux = DependencyNotation("com.google.ar.sceneform.ux", "sceneform-ux")
        }
    }

    /**
     * A fast dependency injector for Android and Java.
     *
     * [User documentation here](https://dagger.dev/dev-guide/)
     * [Dagger API here](https://dagger.dev/api/latest/)
     * GitHub page: [google/dagger](https://github.com/google/dagger)
     */
    val dagger = Dagger

    object Dagger : DependencyNotationAndGroup(group = "com.google.dagger", name = "dagger") {

        val compiler = module("dagger-compiler")

        val spi = module("dagger-spi")
        val producers = module("dagger-producers")
        val gwt = module("dagger-gwt")

        val hilt = Hilt

        object Hilt : IsNotADependency {
            val android = Android

            object Android : DependencyNotationAndGroup(group = group, name = "hilt-android") {

                /**
                 * Id of the plugin: "`dagger.hilt.android.plugin`"
                 */
                val gradlePlugin = module("hilt-android-gradle-plugin")

                @Deprecated(
                    "Renamed, no longer has the word 'android'.",
                    ReplaceWith("Google.dagger.hilt.compiler")
                )
                val compiler = module("hilt-android-compiler")

                val testing = module("hilt-android-testing")
            }

            val compiler = module("hilt-compiler")
        }

        @Deprecated("Consider migrating to Google.dagger.hilt.android")
        @Suppress("deprecation")
        val android = Android

        @Deprecated("Consider migrating to Google.dagger.hilt.android")
        object Android : DependencyNotationAndGroup(group = group, name = "dagger-android") {
            val processor = module("dagger-android-processor")

            /**
             * ⚠️ Requires jetifier to be enabled.
             */
            val support = module("dagger-android-support")
        }

        val grpc = Grpc

        object Grpc : IsNotADependency {
            val server = Server

            object Server : DependencyNotationAndGroup(group = group, name = "dagger-grpc-server") {
                val processor = module("dagger-grpc-server-processor")
                val annotations = module("dagger-grpc-server-annotations")
            }
        }
    }

    val horologist = Horologist

    /**
     * Horologist is a group of libraries that aim to supplement
     * Wear OS developers with features that are commonly required
     * by developers but not yet available.
     *
     * - [Official website here](https://google.github.io/horologist/)
     * - GitHub page: https://github.com/google/horologist
     */
    object Horologist : DependencyGroup(
        group = "com.google.android.horologist",
        rawRules = """
            com.google.android.horologist:*
                ^^^^^^^        ^^^^^^^^^^
        """.trimIndent()
    ) {
        val audio = Audio

        object Audio : DependencyNotationAndGroup(group = "com.google.android.horologist", name = "horologist-audio") {
            val ui = module("horologist-audio-ui")
        }

        val composables = module("horologist-composables")

        val compose = Compose

        object Compose : IsNotADependency {
            val layout = module("horologist-compose-layout")
            val tools = module("horologist-compose-tools")
        }

        val dataLayer = module("horologist-datalayer")

        val media = Media

        object Media : DependencyNotationAndGroup(group = "com.google.android.horologist", name = "horologist-media") {
            val data = module("horologist-media-data")
            val ui = module("horologist-media-ui")
        }

        val media3 = Media3

        object Media3 : IsNotADependency {
            val backend = module("horologist-media3-backend")
        }

        val networkAwareness = module("horologist-network-awareness")

        val tiles = module("horologist-tiles")

    }

    /**
     * Machine learning for mobile developers
     *
     * Official website: [developers.google.com/ml-kit](https://developers.google.com/ml-kit)
     *
     * These artifacts have bundled models.
     */
    val mlKit = MlKit

    object MlKit : DependencyGroup(group = "com.google.mlkit") {

        /**
         * Guide: [Reduce the size of your ML Kit Android app's APKs](https://developers.google.com/ml-kit/tips/reduce-app-size)
         */
        val playStoreDynamicFeatureSupport = module("playstore-dynamic-feature-support")

        val vision = Vision

        object Vision : IsNotADependency {

            /**
             * Bundled version of [Google.Android.PlayServices.MlKit.Vision.barcodeScanning]
             *
             * [Overview](https://developers.google.com/ml-kit/vision/barcode-scanning)
             */
            val barcodeScanning = module("barcode-scanning")

            /**
             * [Overview](https://developers.google.com/ml-kit/vision/digital-ink-recognition)
             */
            val digitalInkRecognition = module("digital-ink-recognition")

            /**
             * Bundled version of [Google.Android.PlayServices.MlKit.Vision.faceDetection]
             *
             * [Overview](https://developers.google.com/ml-kit/vision/face-detection)
             */
            val faceDetection = module("face-detection")

            /**
             * Add downloading of the models instead of having to bundle them in the app.
             *
             * [Official documentation](https://firebase.google.com/docs/ml/android/label-images-with-automl)
             */
            val linkFirebase = module("linkfirebase")

            /**
             * Bundled version of [Google.Android.PlayServices.MlKit.Vision.imageLabeling]
             *
             * [Overview](https://developers.google.com/ml-kit/vision/image-labeling)
             */
            val imageLabeling = ImageLabeling

            object ImageLabeling : DependencyNotationAndGroup(group = group, name = "image-labeling") {

                /**
                 * Bundled version of [Google.Android.PlayServices.MlKit.Vision.ImageLabeling.custom]
                 *
                 * [Official documentation](https://developers.google.com/ml-kit/vision/image-labeling/custom-models/android)
                 *
                 * @see Google.MlKit.Vision.linkFirebase
                 */
                val custom = module("image-labeling-custom")
            }

            /**
             * [Overview](https://developers.google.com/ml-kit/vision/object-detection)
             */
            val objectDetection = ObjectDetection

            object ObjectDetection : DependencyNotationAndGroup(group = group, name = "object-detection") {

                /**
                 * [Official documentation](https://developers.google.com/ml-kit/vision/object-detection/custom-models/android)
                 *
                 * @see Google.MlKit.Vision.linkFirebase
                 */
                val custom = module("object-detection-custom")
            }

            /**
             * [Official documentation](https://developers.google.com/ml-kit/vision/pose-detection)
             */
            val poseDetection = PoseDetection

            object PoseDetection : DependencyNotationAndGroup(group = group, name = "pose-detection") {
                val accurate = module("pose-detection-accurate")
            }

            /**
             * [Official documentation](https://developers.google.com/ml-kit/vision/selfie-segmentation)
             */
            val selfieSegmentation = module("segmentation-selfie")

            /**
             * Bundled version of [Google.Android.PlayServices.MlKit.Vision.textRecognition]
             *
             * [Overview](https://developers.google.com/ml-kit/vision/text-recognition/v2)
             */
            val textRecognition = TextRecognition

            object TextRecognition : DependencyNotationAndGroup(group = group, name = "text-recognition") {
                val chinese = module("text-recognition-chinese")
                val devanagari = module("text-recognition-devanagari")
                val japanese = module("text-recognition-japanese")
                val korean = module("text-recognition-korean")
            }
        }

        val naturalLanguage = NaturalLanguage

        object NaturalLanguage : IsNotADependency {

            /**
             * Bundled version of [Google.Android.PlayServices.MlKit.NaturalLanguage.languageIdentification]
             *
             * [Overview](https://developers.google.com/ml-kit/language/identification)
             */
            val languageIdentification = module("language-id")

            /**
             * [Overview](https://developers.google.com/ml-kit/language/translation)
             */
            val translate = module("translate")

            /**
             * [Overview](https://developers.google.com/ml-kit/language/smart-reply)
             *
             * ## Important:
             * You also need to disable compression of `tflite` files in the **app-level** `build.gradle[.kts]` file:
             *
             * ```kts
             * android {
             *     // ...
             *     aaptOptions {
             *         noCompress("tflite")
             *     }
             * }
             * ```
             */
            val smartReply = module("smart-reply")

            /**
             * [Overview](https://developers.google.com/ml-kit/language/entity-extraction)
             */
            val entityExtraction = module("entity-extraction")
        }
    }

    /**
     * Official website: [google.github.io/modernstorage](https://google.github.io/modernstorage/)
     *
     * GitHub repo: [google/modernstorage](https://github.com/google/modernstorage)
     */
    val modernStorage = ModernStorage

    object ModernStorage : DependencyGroup(group = "com.google.modernstorage") {

        val bom = module("modernstorage-bom", isBom = true)

        /**
         * For storage permissions checking
         *
         * Guide: [Permissions](https://google.github.io/modernstorage/permissions/)
         */
        val permissions = module("modernstorage-permissions")

        /**
         * For storage interactions using Okio FileSystem API
         *
         * Guide: [Storage Interactions](https://google.github.io/modernstorage/storage/)
         */
        val storage = module("modernstorage-storage")

        /**
         * Provides an `ActivityResultContract` to launch the Photo Picker intent when available
         * on device, or rely on the existing file picker using the `ACTION_OPEN_DOCUMENT` intent.
         *
         * Guide: [Photo Picker](https://google.github.io/modernstorage/photopicker/)
         */
        val photoPicker = module("modernstorage-photopicker")
    }

    /**
     * Oboe is a C++ library that makes it easy to build high-performance audio apps on Android.
     *
     * GitHub repo: [google/oboe](https://github.com/google/oboe)
     */
    val oboe = DependencyNotation("com.google.oboe", "oboe")
}
