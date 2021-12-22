@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import de.fayard.refreshVersions.core.DependencyNotation
import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

@Incubating
object Google {

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

        @Deprecated("Moved to COIL.compose", ReplaceWith("COIL.compose"))
        val coil = module("accompanist-coil")

        val glide = module("accompanist-glide")
        val imageloadingCore = module("accompanist-imageloading-core")

        val insets = Insets

        object Insets : DependencyNotationAndGroup(group = "com.google.accompanist", name = "accompanist-insets") {
            val ui = module("accompanist-insets-ui")
        }
        val systemuicontroller = module("accompanist-systemuicontroller")

        val appcompatTheme = module("accompanist-appcompat-theme")

        val pager = Pager

        object Pager : DependencyNotationAndGroup(group = "com.google.accompanist", name = "accompanist-pager") {
            val indicators = module("accompanist-pager-indicators")
        }

        val flowlayout = module("accompanist-flowlayout")
        val swiperefresh = module("accompanist-swiperefresh")
    }

    val android = Android

    object Android : IsNotADependency {
        private const val artifactBase = "com.google.android"

        val browserHelper = DependencyNotation("com.google.androidbrowserhelper", "androidbrowserhelper")

        val material = Material

        object Material : DependencyNotationAndGroup(group = "com.google.android.material", name = "material") {
            val composeThemeAdapter = module("compose-theme-adapter")
        }

        val wearable = DependencyNotation("com.google.android.wearable", "wearable")
        val supportWearable = DependencyNotation("com.google.android.support", "wearable")

        val playServices = PlayServices

        object PlayServices : DependencyGroup(group = "com.google.android.gms") {

            /** Google Account Login */
            val auth = module("play-services-auth")

            /** Base client library and Google Actions */
            val base = module("play-services-base")

            /** Google Sign In */
            val identity = module("play-services-identity")

            /** Google Analytics */
            val analytics = module("play-services-analytics")

            /** Google Awareness */
            val awareness = module("play-services-awareness")

            /** Google Cast */
            val cast = module("play-services-cast")

            /** Google Cloud Messaging */
            @Deprecated("Use Firebase Cloud Messaging instead")
            val gcm = module("play-services-gcm")

            /** Google Drive */
            val drive = module("play-services-drive")

            /** Google Fit */
            val fitness = module("play-services-fitness")

            /** Google Location and Activity Recognition */
            val location = module("play-services-location")

            /** Google Maps */
            val maps = module("play-services-maps")

            // Play Services Ads intentionally not included because ads are mental pollution.

            /**
             * Google Tasks API (yet another "Future" type, because they're not using Kotlin coroutines yet).
             * We recommend to use it with [KotlinX.Coroutines.playServices].
             */
            val tasks = module("play-services-tasks")

            /** Mobile Vision */
            val vision = module("play-services-vision")

            /** Google Nearby */
            val nearby = module("play-services-nearby")

            /** Google Panorama Viewer */
            val panorama = module("play-services-panorama")

            /** Google Play Game services */
            val games = module("play-services-games")

            /** SafetyNet */
            val safetynet = module("play-services-safetynet")

            /** Google Pay */
            val pay = module("play-services-wallet")

            /** Wear OS by Google */
            val wearOS = module("play-services-wearable")

            val mlKit = MlKit

            object MlKit : IsNotADependency {

                val vision = Vision

                object Vision {

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
                    val imageLabeling = module("play-services-mlkit-image-labeling")

                    /**
                     * [Overview](https://developers.google.com/ml-kit/vision/text-recognition)
                     */
                    val textRecognition = module("play-services-mlkit-text-recognition")
                }
            }
        }

        val play = Play

        object Play : DependencyGroup(group = "com.google.android.play") {
            val core = module("core")
            val coreKtx = module("core-ktx")
        }
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

    /**
     * Machine learning for mobile developers
     *
     * Official website: [developers.google.com/ml-kit](https://developers.google.com/ml-kit)
     *
     * These artifacts have bundled models.
     */
    val mlKit = MlKit

    object MlKit : DependencyGroup(group = "com.google.mlkit") {

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
             * Add downloading of the models instead of having to bundle them in the app for [ImageLabeling.autoMl].
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
                 * [ML Kit documentation](https://developers.google.com/ml-kit/vision/image-labeling/automl/android)
                 *
                 * [Firebase documentation](https://firebase.google.com/docs/ml/android/label-images-with-automl)
                 */
                val autoMl = module("image-labeling-automl")

                /**
                 * [Official documentation](https://developers.google.com/ml-kit/vision/image-labeling/custom-models/android)
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

            @Deprecated(
                message = "There's no bundled version of ML Kit text recognition. Use the Play Services based one.",
                level = DeprecationLevel.ERROR,
                replaceWith = ReplaceWith("Google.android.playServices.mlKit.vision.textRecognition")
            )
            val textRecognition: Nothing
                get() = throw UnsupportedOperationException(
                    "Use Google.android.playServices.mlKit.vision.textRecognition instead."
                )
        }

        val naturalLanguage = NaturalLanguage

        object NaturalLanguage : IsNotADependency {

            /**
             * [Overview](https://developers.google.com/ml-kit/language/identification)
             */
            val languageIdentification = module("language-id")

            /**
             * [Overview](https://developers.google.com/ml-kit/language/translation)
             */
            val translate = module("translate")

            /**
             * [Overview](https://developers.google.com/ml-kit/language/smart-reply)
             */
            val smartReply = module("smart-reply")
        }
    }
}
