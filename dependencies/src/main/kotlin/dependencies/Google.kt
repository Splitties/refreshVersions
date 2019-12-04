@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.api.Incubating

@Incubating
object Google {

    val android = Android

    object Android {
        private const val artifactBase = "com.google.android"

        const val material = "$artifactBase.material:material:_"

        private const val wearOsVersion = "_"

        const val wearable = "$artifactBase.wearable:wearable:$wearOsVersion"
        const val supportWearable = "$artifactBase.support:wearable:$wearOsVersion"

        val playServices = PlayServices

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
    }
}
