@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

@Incubating
object Google {

    const val playServicesGradlePlugin = "com.google.gms:google-services:_"

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

    object Accompanist : IsNotADependency {
        private const val artifactPrefix = "com.google.accompanist:accompanist"

        const val coil = "$artifactPrefix-coil:_"
        const val glide = "$artifactPrefix-glide:_"
        const val imageloadingCore = "$artifactPrefix-imageloading-core:_"

        val insets = Insets

        object Insets : DependencyNotationAndGroup(group = "com.google.accompanist", name = "accompanist-insets") {
            @JvmField
            val ui = "$artifactPrefix-ui:_"
        }
        const val systemuicontroller = "$artifactPrefix-systemuicontroller:_"

        const val appcompatTheme = "$artifactPrefix-appcompat-theme:_"

        val pager = Pager

        object Pager : DependencyNotationAndGroup(group = "com.google.accompanist", name = "accompanist-pager") {
            @JvmField
            val indicators = "$artifactPrefix-indicators:_"
        }

        const val flowlayout = "$artifactPrefix-flowlayout:_"
        const val swiperefresh = "$artifactPrefix-swiperefresh:_"
    }

    val android = Android

    object Android : IsNotADependency {
        private const val artifactBase = "com.google.android"

        const val browserHelper = "com.google.androidbrowserhelper:androidbrowserhelper:_"

        val material = Material

        object Material : DependencyNotationAndGroup(group = "$artifactBase.material", name = "material") {
            @JvmField val composeThemeAdapter = "$artifactBase.material:compose-theme-adapter:_"
        }

        private const val wearOsVersion = "_"

        const val wearable = "$artifactBase.wearable:wearable:$wearOsVersion"
        const val supportWearable = "$artifactBase.support:wearable:$wearOsVersion"

        val playServices = PlayServices

        object PlayServices : IsNotADependency {
            private const val artifactPrefix = "$artifactBase.gms:play-services"

            /** Google Account Login */
            const val auth = "$artifactPrefix-auth:_"

            /** Base client library and Google Actions */
            const val base = "$artifactPrefix-base:_"

            /** Google Sign In */
            const val identity = "$artifactPrefix-identity:_"

            /** Google Analytics */
            const val analytics = "$artifactPrefix-analytics:_"

            /** Google Awareness */
            const val awareness = "$artifactPrefix-awareness:_"

            /** Google Cast */
            const val cast = "$artifactPrefix-cast:_"

            /** Google Cloud Messaging */
            @Deprecated("Use Firebase Cloud Messaging instead")
            const val gcm = "$artifactPrefix-gcm:_"

            /** Google Drive */
            const val drive = "$artifactPrefix-drive:_"

            /** Google Fit */
            const val fitness = "$artifactPrefix-fitness:_"

            /** Google Location and Activity Recognition */
            const val location = "$artifactPrefix-location:_"

            // Play Services Ads intentionally not included because ads are mental pollution.

            /**
             * Google Tasks API (yet another "Future" type, because they're not using Kotlin coroutines yet).
             * We recommend to use it with [KotlinX.Coroutines.playServices].
             */
            const val tasks = "$artifactPrefix-tasks:_"

            /** Mobile Vision */
            const val vision = "$artifactPrefix-vision:_"

            /** Google Nearby */
            const val nearby = "$artifactPrefix-nearby:_"

            /** Google Panorama Viewer */
            const val panorama = "$artifactPrefix-panorama:_"

            /** Google Play Game services */
            const val games = "$artifactPrefix-games:_"

            /** SafetyNet */
            const val safetynet = "$artifactPrefix-safetynet:_"

            /** Google Pay */
            const val pay = "$artifactPrefix-wallet:_"

            /** Wear OS by Google */
            const val wearOS = "$artifactPrefix-wearable:_"

            val mlKit = MlKit

            object MlKit : IsNotADependency {
                private const val prefix = "$artifactPrefix-mlkit"


                val vision = Vision

                object Vision {

                    /**
                     * Unbundled version of [Google.MlKit.Vision.barcodeScanning]
                     *
                     * [Overview](https://developers.google.com/ml-kit/vision/barcode-scanning)
                     */
                    const val barcodeScanning = "$prefix-barcode-scanning:_"

                    /**
                     * Unbundled version of [Google.MlKit.Vision.faceDetection]
                     *
                     * [Overview](https://developers.google.com/ml-kit/vision/face-detection)
                     */
                    const val faceDetection = "$prefix-face-detection:_"

                    /**
                     * Unbundled version of [Google.MlKit.Vision.imageLabeling]
                     *
                     * [Overview](https://developers.google.com/ml-kit/vision/image-labeling)
                     */
                    const val imageLabeling = "$prefix-image-labeling:_"

                    /**
                     * [Overview](https://developers.google.com/ml-kit/vision/text-recognition)
                     */
                    const val textRecognition = "$prefix-text-recognition:_"
                }
            }
        }

        val play = Play

        object Play : IsNotADependency {
            private const val group = "$artifactBase.play"

            const val core = "$group:core:_"
            const val coreKtx = "$group:core-ktx:_"
        }
    }

    /***
     * With ARCore, build new augmented reality experiences that seamlessly blend the digital and physical worlds.
     *
     * [Official doc here](https://developers.google.com/ar)
     * [Android guide here](https://developers.google.com/ar/develop/java/quickstart).
     */
    val ar = Ar

    object Ar : IsNotADependency {
        private const val baseArtifact = "com.google.ar"

        /**
         * [Android guide here](https://developers.google.com/ar/develop/java/quickstart).
         */
        const val core = "$baseArtifact:core:_"

        /**
         * [Official doc here](https://developers.google.com/ar/develop/java/sceneform).
         */
        val sceneform = Sceneform

        object Sceneform : IsNotADependency {
            private const val artifactPrefix = "$baseArtifact.sceneform"

            const val animation = "$artifactPrefix:animation:_"
            const val assets = "$artifactPrefix:assets:_"
            const val core = "$artifactPrefix:core:_"
            const val filamentAndroid = "$artifactPrefix:filament-android:_"
            const val plugin = "$artifactPrefix:plugin:_"
            const val rendering = "$artifactPrefix:rendering:_"
            const val sceneformBase = "$artifactPrefix:sceneform-base:_"

            const val ux = "$artifactPrefix.ux:sceneform-ux:_"
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

        @JvmField val compiler = "$artifactPrefix-compiler:_"

        @JvmField val spi = "$artifactPrefix-spi:_"
        @JvmField val producers = "$artifactPrefix-producers:_"
        @JvmField val gwt = "$artifactPrefix-gwt:_"

        val hilt = Hilt

        object Hilt : IsNotADependency {
            val android = Android

            object Android : DependencyNotationAndGroup(group = group, name = "hilt-android") {

                /**
                 * Id of the plugin: "`dagger.hilt.android.plugin`"
                 */
                @JvmField
                val gradlePlugin = "$artifactPrefix-gradle-plugin:_"

                @JvmField
                @Deprecated(
                    "Renamed, no longer has the word 'android'.",
                    ReplaceWith("Google.dagger.hilt.compiler")
                )
                val compiler = "$artifactPrefix-compiler:_"

                @JvmField
                val testing = "$artifactPrefix-testing:_"
            }

            val compiler = "$group:hilt-compiler:_"
        }

        @Deprecated("Consider migrating to Google.dagger.hilt.android")
        @Suppress("deprecation")
        val android = Android

        @Deprecated("Consider migrating to Google.dagger.hilt.android")
        object Android : DependencyNotationAndGroup(group = group, name = "dagger-android") {
            @JvmField val processor = "$artifactPrefix-processor:_"

            /**
             * ⚠️ Requires jetifier to be enabled.
             */
            @JvmField val support = "$artifactPrefix-support:_"
        }

        val grpc = Grpc

        object Grpc : IsNotADependency {
            val server = Server

            object Server : DependencyNotationAndGroup(group = group, name = "dagger-grpc-server") {
                @JvmField val processor = "$artifactPrefix-processor:_"
                @JvmField val annotations = "$artifactPrefix-annotations:_"
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

    object MlKit : IsNotADependency {
        private const val group = "com.google.mlkit"

        val vision = Vision

        object Vision : IsNotADependency {

            /**
             * Bundled version of [Google.Android.PlayServices.MlKit.Vision.barcodeScanning]
             *
             * [Overview](https://developers.google.com/ml-kit/vision/barcode-scanning)
             */
            const val barcodeScanning = "$group:barcode-scanning:_"

            /**
             * [Overview](https://developers.google.com/ml-kit/vision/digital-ink-recognition)
             */
            const val digitalInkRecognition = "$group:digital-ink-recognition:_"

            /**
             * Bundled version of [Google.Android.PlayServices.MlKit.Vision.faceDetection]
             *
             * [Overview](https://developers.google.com/ml-kit/vision/face-detection)
             */
            const val faceDetection = "$group:face-detection:_"

            /**
             * Add downloading of the models instead of having to bundle them in the app for [ImageLabeling.autoMl].
             *
             * [Official documentation](https://firebase.google.com/docs/ml/android/label-images-with-automl)
             */
            const val linkFirebase = "$group:linkfirebase:_"

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
                @JvmField val autoMl = "$artifactPrefix-automl:_"

                /**
                 * [Official documentation](https://developers.google.com/ml-kit/vision/image-labeling/custom-models/android)
                 */
                @JvmField val custom = "$artifactPrefix-custom:_"
            }

            /**
             * [Overview](https://developers.google.com/ml-kit/vision/object-detection)
             */
            val objectDetection = ObjectDetection

            object ObjectDetection : DependencyNotationAndGroup(group = group, name = "object-detection") {

                /**
                 * [Official documentation](https://developers.google.com/ml-kit/vision/object-detection/custom-models/android)
                 */
                @JvmField val custom = "$artifactPrefix-custom:_"
            }

            /**
             * [Official documentation](https://developers.google.com/ml-kit/vision/pose-detection)
             */
            val poseDetection = PoseDetection

            object PoseDetection : DependencyNotationAndGroup(group = group, name = "pose-detection") {
                @JvmField val accurate = "$artifactPrefix-accurate:_"
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
            const val languageIdentification = "$group:language-id:_"

            /**
             * [Overview](https://developers.google.com/ml-kit/language/translation)
             */
            const val translate = "$group:translate:_"

            /**
             * [Overview](https://developers.google.com/ml-kit/language/smart-reply)
             */
            const val smartReply = "$group:smart-reply:_"
        }
    }
}
