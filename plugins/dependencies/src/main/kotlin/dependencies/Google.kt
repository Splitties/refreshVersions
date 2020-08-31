@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import dependencies.DependencyNotationAndGroup
import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

@Incubating
object Google {

    val android = Android
    val ar = Ar
    val firebase get() = Firebase

    const val playServicesGradlePlugin = "com.google.gms:google-services:_"

    val dagger = Dagger

    object Android: IsNotADependency {
        private const val artifactBase = "com.google.android"

        const val browserHelper = "com.google.androidbrowserhelper:androidbrowserhelper:_"

        const val material = "$artifactBase.material:material:_"

        private const val wearOsVersion = "_"

        const val wearable = "$artifactBase.wearable:wearable:$wearOsVersion"
        const val supportWearable = "$artifactBase.support:wearable:$wearOsVersion"

        val playServices = PlayServices
        val play = Play

        object PlayServices: IsNotADependency {
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

        object Play: IsNotADependency {
            private const val group = "$artifactBase.play"

            const val core = "$group:core:_"
            const val coreKtx = "$group:core-ktx:_"
        }
    }

    object Ar: IsNotADependency {
        private const val baseArtifact = "com.google.ar"

        /**
         * [Android guide here](https://developers.google.com/ar/develop/java/quickstart).
         */
        const val core = "$baseArtifact:core:_"

        /**
         * [Official doc here](https://developers.google.com/ar/develop/java/sceneform).
         */
        val sceneform = Sceneform

        object Sceneform: IsNotADependency {
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

    object Dagger : DependencyNotationAndGroup(group = "com.google.dagger", name = "dagger") {
        private const val group = "com.google.dagger"

        @JvmField val compiler = "$artifactPrefix-compiler:_"

        val hilt = Hilt

        val grpc = Grpc

        @JvmField val spi = "$artifactPrefix-spi:_"
        @JvmField val producers = "$artifactPrefix-producers:_"
        @JvmField val gwt = "$artifactPrefix-gwt:_"

        @Deprecated("Consider migrating to Google.dagger.hilt.android")
        @Suppress("deprecation")
        val android = Android

        object Hilt: IsNotADependency {
            val android = Android

            object Android : DependencyNotationAndGroup(group = group, name = "hilt-android") {

                @JvmField val gradlePlugin = "$artifactPrefix-gradle-plugin:_"

                @JvmField val compiler = "$artifactPrefix-compiler:_"

                @JvmField val testing = "$artifactPrefix-testing:_"
            }
        }

        @Deprecated("Consider migrating to Google.dagger.hilt.android")
        object Android : DependencyNotationAndGroup(group = group, name = "dagger-android") {
            @JvmField val processor = "$artifactPrefix-processor:_"

            /**
             * ⚠️ Requires jetifier to be enabled.
             */
            @JvmField val support = "$artifactPrefix-support:_"
        }

        object Grpc: IsNotADependency {
            val server = Server

            object Server : DependencyNotationAndGroup(group = group, name = "dagger-grpc-server") {
                @JvmField val processor = "$artifactPrefix-processor:_"
                @JvmField val annotations = "$artifactPrefix-annotations:_"
            }
        }
    }
}
