@file:Suppress("PackageDirectoryMismatch", "ObjectPropertyName", "SpellCheckingInspection")

import de.fayard.refreshVersions.core.internal.DependencyGroup
import org.gradle.api.Incubating
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
@Incubating
object Firebase : DependencyGroup(
    group = "com.google.firebase",
    usePlatformConstraints = true
) {
    val bom by module("firebase-bom", isBom = true)

    val appDistributionGradlePlugin by module("firebase-appdistribution-gradle", usePlatformConstraints = false)
    val performanceMonitoringGradlePlugin by module("perf-plugin", usePlatformConstraints = false)

    // AdMob intentionally not included because ads are mental pollution.

    val analytics by module("firebase-analytics")
    val analyticsKtx by module("firebase-analytics-ktx")

    val appIndexing by module("firebase-appindexing")

    val authentication by module("firebase-auth")
    val authenticationKtx by module("firebase-auth-ktx")

    val cloudFirestore by module("firebase-firestore")
    val cloudFirestoreKtx by module("firebase-firestore-ktx")

    val cloudFunctions by module("firebase-functions")
    val cloudFunctionsKtx by module("firebase-functions-ktx")

    val cloudMessaging by module("firebase-messaging")
    val cloudMessagingKtx by module("firebase-messaging-ktx")
    val cloudMessagingDirectBoot by module("firebase-messaging-directboot")

    val cloudStorage by module("firebase-storage")
    val cloudStorageKtx by module("firebase-storage-ktx")
    val crashlytics by module("firebase-crashlytics")
    val crashlyticsKtx by module("firebase-crashlytics-ktx")
    val crashlyticsNdk by module("firebase-crashlytics-ndk")
    val crashlyticsGradlePlugin by module("firebase-crashlytics-gradle", usePlatformConstraints = false)

    val dynamicLinks by module("firebase-dynamic-links")
    val dynamicLinksKtx by module("firebase-dynamic-links-ktx")

    val inAppMessaging by module("firebase-inappmessaging")
    val inAppMessagingKtx by module("firebase-inappmessaging-ktx")
    val inAppMessagingDisplay by module("firebase-inappmessaging-display")
    val inAppMessagingDisplayKtx by module("firebase-inappmessaging-display-ktx")

    val mlModelDownloader by module("firebase-ml-modeldownloader")
    val mlModelDownloaderKtx by module("firebase-ml-modeldownloader-ktx")

    val performanceMonitoring by module("firebase-perf")
    val performanceMonitoringKtx by module("firebase-perf-ktx")

    val realtimeDatabase by module("firebase-database")
    val realtimeDatabaseKtx by module("firebase-database-ktx")

    val remoteConfig by module("firebase-config")
    val remoteConfigKtx by module("firebase-config-ktx")

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
        val vision by module("firebase-ml-vision")

        @Deprecated(deprecationMessage)
        val naturalLanguage by module("firebase-ml-natural-language", usePlatformConstraints = false)

        @Deprecated(deprecationMessage)
        val models = Models

        object Models : IsNotADependency {

            @Deprecated("Replaced with the Firebase ML model downloader", ReplaceWith("Firebase.mlModelDownloaderKtx"))
            val custom by module("firebase-ml-model-interpreter", usePlatformConstraints = false)

            @Deprecated(deprecationMessage)
            val vision = Vision

            object Vision : IsNotADependency {

                @Deprecated(deprecationMessage)
                val imageLabelling by module("firebase-ml-vision-image-label-model", usePlatformConstraints = false)

                @Deprecated(deprecationMessage)
                val objectDetectionAndTracking by module("firebase-ml-vision-object-detection-model", usePlatformConstraints = false)

                @Deprecated(deprecationMessage)
                val faceDetection by module("firebase-ml-vision-face-model", usePlatformConstraints = false)

                @Deprecated(deprecationMessage)
                val barcodeScanning by module("firebase-ml-vision-barcode-model", usePlatformConstraints = false)

                @Deprecated(deprecationMessage)
                val autoMl by module("firebase-ml-vision-automl", usePlatformConstraints = false)
            }

            @Deprecated(deprecationMessage)
            val naturalLanguage = NaturalLanguage

            object NaturalLanguage : IsNotADependency {

                @Deprecated(deprecationMessage)
                val languageIdentification by module("firebase-ml-natural-language-language-id-model", usePlatformConstraints = false)

                @Deprecated(deprecationMessage)
                val translate by module("firebase-ml-natural-language-translate-model", usePlatformConstraints = false)

                @Deprecated(deprecationMessage)
                val smartReply by module("firebase-ml-natural-language-smart-reply-model", usePlatformConstraints = false)
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
