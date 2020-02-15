@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.api.Incubating

@Incubating
object Google {

    val android = Android
    val ar = Ar
    val firebase get() = Firebase

    const val playServicesGradlePlugin = "com.google.gms:google-services:_"

    object Android {
        private const val artifactBase = "com.google.android"

        const val material = "$artifactBase.material:material:_"

        private const val wearOsVersion = "_"

        const val wearable = "$artifactBase.wearable:wearable:$wearOsVersion"
        const val supportWearable = "$artifactBase.support:wearable:$wearOsVersion"

        val playServices = PlayServices
        val play = Play

        object PlayServices {
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
        }

        object Play {
            private const val group = "$artifactBase.play"

            const val core = "$group:core:_"
            const val coreKtx = "$group:core-ktx:_"
        }
    }

    object Ar {
        private const val baseArtifact = "com.google.ar"

        /**
         * [Android guide here](https://developers.google.com/ar/develop/java/quickstart).
         */
        const val core = "$baseArtifact:core:_"

        /**
         * [Official doc here](https://developers.google.com/ar/develop/java/sceneform).
         */
        val sceneform = Sceneform

        object Sceneform {
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
}
