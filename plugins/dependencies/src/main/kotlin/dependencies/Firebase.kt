@file:Suppress("PackageDirectoryMismatch", "ObjectPropertyName", "SpellCheckingInspection")

import de.fayard.refreshVersions.core.DependencyGroup
import org.gradle.kotlin.dsl.IsNotADependency

/**
 * Firebase Android libraries.
 *
 * We recommend **reading the official Firebase documention** before using it as **the setup is more than
 * just adding the dependency.**
 *
 * See available libraries with link to their docs [here](https://firebase.google.com/docs/android/setup#available-libraries).

 * Usage example:
 * ```
 * implementation(platform(Firebase.bom))
 * implementation(Firebase.authenticationKtx)
 * implementation(Firebase.cloudMessagingKtx)
 * ```
 */
object Firebase : DependencyGroup(
    group = "com.google.firebase",
    usePlatformConstraints = true
) {
    val bom = module("firebase-bom", isBom = true)

    val appDistributionGradlePlugin = module("firebase-appdistribution-gradle", usePlatformConstraints = false)
    val performanceMonitoringGradlePlugin = module("perf-plugin", usePlatformConstraints = false)

    // AdMob intentionally not included because ads are mental pollution.

    val analytics = module("firebase-analytics")
    val analyticsKtx = module("firebase-analytics-ktx")

    val appIndexing = module("firebase-appindexing")

    val authentication = module("firebase-auth")
    val authenticationKtx = module("firebase-auth-ktx")

    val cloudFirestore = module("firebase-firestore")
    val cloudFirestoreKtx = module("firebase-firestore-ktx")

    val cloudFunctions = module("firebase-functions")
    val cloudFunctionsKtx = module("firebase-functions-ktx")

    val cloudMessaging = module("firebase-messaging")
    val cloudMessagingKtx = module("firebase-messaging-ktx")
    val cloudMessagingDirectBoot = module("firebase-messaging-directboot")

    val cloudStorage = module("firebase-storage")
    val cloudStorageKtx = module("firebase-storage-ktx")
    val crashlytics = module("firebase-crashlytics")
    val crashlyticsKtx = module("firebase-crashlytics-ktx")
    val crashlyticsNdk = module("firebase-crashlytics-ndk")
    val crashlyticsGradlePlugin = module("firebase-crashlytics-gradle", usePlatformConstraints = false)

    val dynamicLinks = module("firebase-dynamic-links")
    val dynamicLinksKtx = module("firebase-dynamic-links-ktx")

    val inAppMessaging = module("firebase-inappmessaging")
    val inAppMessagingKtx = module("firebase-inappmessaging-ktx")
    val inAppMessagingDisplay = module("firebase-inappmessaging-display")
    val inAppMessagingDisplayKtx = module("firebase-inappmessaging-display-ktx")

    val mlModelDownloader = module("firebase-ml-modeldownloader")
    val mlModelDownloaderKtx = module("firebase-ml-modeldownloader-ktx")

    val performanceMonitoring = module("firebase-perf")
    val performanceMonitoringKtx = module("firebase-perf-ktx")

    val realtimeDatabase = module("firebase-database")
    val realtimeDatabaseKtx = module("firebase-database-ktx")

    val remoteConfig = module("firebase-config")
    val remoteConfigKtx = module("firebase-config-ktx")

    @Deprecated("Use Google ML Kit and Firebase ML model donwloader instead. " +
        "Find new dependency notations in Google.mlKit and Google.android.playServices.mlKit, " +
        "plus Firebase.mlModelDownloaderKtx or Firebase.mlModelDownloader." +
        "See migration page: https://developers.google.com/ml-kit/migration/android")
    val mlKit = MlKit

    object MlKit : IsNotADependency {
        private const val deprecationMessage = "Use Google ML Kit instead. " +
            "Find new dependency notations in Google.mlKit and Google.android.playServices.mlKit. " +
            "See migration page: https://developers.google.com/ml-kit/migration/android"

        @Deprecated(deprecationMessage)
        val vision = module("firebase-ml-vision")

        @Deprecated(deprecationMessage)
        val naturalLanguage = module("firebase-ml-natural-language", usePlatformConstraints = false)

        @Deprecated(deprecationMessage)
        val models = Models

        object Models : IsNotADependency {

            @Deprecated("Replaced with the Firebase ML model downloader", ReplaceWith("Firebase.mlModelDownloaderKtx"))
            val custom = module("firebase-ml-model-interpreter", usePlatformConstraints = false)

            @Deprecated(deprecationMessage)
            val vision = Vision

            object Vision : IsNotADependency {

                @Deprecated(deprecationMessage)
                val imageLabelling = module("firebase-ml-vision-image-label-model", usePlatformConstraints = false)

                @Deprecated(deprecationMessage)
                val objectDetectionAndTracking = module("firebase-ml-vision-object-detection-model", usePlatformConstraints = false)

                @Deprecated(deprecationMessage)
                val faceDetection = module("firebase-ml-vision-face-model", usePlatformConstraints = false)

                @Deprecated(deprecationMessage)
                val barcodeScanning = module("firebase-ml-vision-barcode-model", usePlatformConstraints = false)

                @Deprecated(deprecationMessage)
                val autoMl = module("firebase-ml-vision-automl", usePlatformConstraints = false)
            }

            @Deprecated(deprecationMessage)
            val naturalLanguage = NaturalLanguage

            object NaturalLanguage : IsNotADependency {

                @Deprecated(deprecationMessage)
                val languageIdentification = module("firebase-ml-natural-language-language-id-model", usePlatformConstraints = false)

                @Deprecated(deprecationMessage)
                val translate = module("firebase-ml-natural-language-translate-model", usePlatformConstraints = false)

                @Deprecated(deprecationMessage)
                val smartReply = module("firebase-ml-natural-language-smart-reply-model", usePlatformConstraints = false)
            }
        }
    }

    @Deprecated("No longer supported.")
    val `no-BoM`: OldNoBom get() = OldNoBom

    object OldNoBom : IsNotADependency {
    @Deprecated("The Crashlytics Gradle plugin dependency notation moved", ReplaceWith("Firebase.crashlyticsGradlePlugin"))
        val crashlyticsGradlePlugin = "com.google.firebase:firebase-crashlytics-gradle:_"
    }
}
