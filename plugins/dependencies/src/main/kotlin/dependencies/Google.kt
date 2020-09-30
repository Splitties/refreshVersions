@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import dependencies.DependencyNotationAndGroup
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

    val android = Android

    object Android : IsNotADependency {
        private const val artifactBase = "com.google.android"

        const val browserHelper = "com.google.androidbrowserhelper:androidbrowserhelper:_"

        const val material = "$artifactBase.material:material:_"

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

                    @RequiresOptIn(
                        message = "This library has a know issue on some/many devices where it'll never work. " +
                                "Use the bundled version to avoid that issue: Google.mlKit.vision.barcodeScanning " +
                                "(costs 2MB in the app but should work)" +
                                "See the issue reported back in 2018 here: https://issuetracker.google.com/issues/80454351"
                    )
                    annotation class PlayServicesBarcodeScanningUnreliableOnSomeDevices

                    /**
                     * **WARNING**:
                     *
                     * This library has a know issue on some/many devices where it'll never work.
                     * Use the bundled version to avoid that issue: [Google.MlKit.Vision.barcodeScanning]
                     * (costs 2MB in the app but should work)See the issue reported back in 2018 here: https://issuetracker.google.com/issues/80454351
                     */
                    @PlayServicesBarcodeScanningUnreliableOnSomeDevices
                    const val barcodeScanning = "$prefix-barcode-scanning:_"

                    /**
                     * Unbundled version of [Google.MlKit.Vision.faceDetection]
                     */
                    const val faceDetection = "$prefix-face-detection:_"

                    /**
                     * Unbundled version of [Google.MlKit.Vision.imageLabeling]
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
        private const val group = "com.google.dagger"

        @JvmField val compiler = "$artifactPrefix-compiler:_"

        @JvmField val spi = "$artifactPrefix-spi:_"
        @JvmField val producers = "$artifactPrefix-producers:_"
        @JvmField val gwt = "$artifactPrefix-gwt:_"

        val hilt = Hilt

        object Hilt : IsNotADependency {
            val android = Android

            object Android : DependencyNotationAndGroup(group = group, name = "hilt-android") {

                @JvmField val gradlePlugin = "$artifactPrefix-gradle-plugin:_"

                @JvmField val compiler = "$artifactPrefix-compiler:_"

                @JvmField val testing = "$artifactPrefix-testing:_"
            }
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
             * [Overview](https://developers.google.com/ml-kit/vision/barcode-scanning)
             *
             * If
             */
            const val barcodeScanning = "$group:barcode-scanning:_"

            /**
             * [Overview](https://developers.google.com/ml-kit/vision/digital-ink-recognition)
             */
            const val digitalInkRecognition = "$group:digital-ink-recognition:_"

            /**
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
