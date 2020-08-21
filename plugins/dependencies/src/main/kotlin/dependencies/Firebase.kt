@file:Suppress("PackageDirectoryMismatch", "ObjectPropertyName", "SpellCheckingInspection")

import org.gradle.api.Incubating

/**
 * Firebase Android libraries.
 *
 * We recommend **reading the official Firebase documention** before using it as **the setup is more than
 * just adding the dependency.**
 *
 * See available libraries with link to their docs [here](https://firebase.google.com/docs/android/setup#available-libraries).
 */
@Incubating
interface Firebase {

    companion object : Firebase by FirebaseImpl(isBom = true) {

        /**
         * Usage example:
         * ```
         * implementation(platform(Firebase.bom)
         * implementation(Firebase.authentication)
         * implementation(Firebase.cloudMessaging)
         * ```
         */
        const val bom = "com.google.firebase:firebase-bom:_"

        val `no-BoM`: Firebase by lazy { FirebaseImpl(isBom = false) }

        private const val artifactPrefix = "com.google.firebase:firebase"

        const val appDistributionGradlePlugin = "$artifactPrefix-appdistribution-gradle:_"

        const val performanceMonitoringGradlePlugin = "com.google.firebase:perf-plugin:_"
    }

    // AdMob intentionally not included because ads are mental pollution.

    val analytics: String

    val appIndexing: String

    val authentication: String

    val cloudFirestore: String
    val cloudFirestoreKtx: String

    val cloudFunctions: String
    val cloudFunctionsKtx: String

    val cloudMessaging: String

    val cloudStorage: String
    val cloudStorageKtx: String

    val crashlytics: String
    val crashlyticsNdk: String
    val crashlyticsGradlePlugin: String

    val dynamicLinks: String

    val inAppMessaging: String
    val inAppMessagingKtx: String
    val inAppMessagingDisplay: String
    val inAppMessagingDisplayKtx: String

    val mlKit: MlKit

    val performanceMonitoring: String

    val realtimeDatabase: String
    val realtimeDatabaseKtx: String

    val remoteConfig: String
    val remoteConfigKtx: String

    interface MlKit {
        val vision: String
        val naturalLanguage: String
        val models: Models

        interface Models {
            val vision: Vision
            val naturalLanguage: NaturalLanguage
            val custom: String

            interface Vision {
                val imageLabelling: String
                val objectDetectionAndTracking: String
                val faceDetection: String
                val barcodeScanning: String
                val autoMl: String
            }

            interface NaturalLanguage {
                val languageIdentification: String
                val translate: String
                val smartReply: String
            }
        }
    }
}

internal class FirebaseImpl(isBom: Boolean) : Firebase {

    private val suffix = if (isBom) "" else ":_"
    private val artifactPrefix = "com.google.firebase:firebase"

    override val analytics = "$artifactPrefix-analytics$suffix"
    override val appIndexing = "$artifactPrefix-appindexing$suffix"
    override val authentication = "$artifactPrefix-auth$suffix"
    override val cloudFirestore = "$artifactPrefix-firestore$suffix"
    override val cloudFirestoreKtx = "$artifactPrefix-firestore-ktx$suffix"
    override val cloudFunctions = "$artifactPrefix-functions$suffix"
    override val cloudFunctionsKtx = "$artifactPrefix-functions-ktx$suffix"
    override val cloudMessaging = "$artifactPrefix-messaging$suffix"
    override val cloudStorage = "$artifactPrefix-storage$suffix"
    override val cloudStorageKtx = "$artifactPrefix-storage-ktx$suffix"
    override val crashlytics = "$artifactPrefix-crashlytics$suffix"
    override val crashlyticsNdk = "$artifactPrefix-crashlytics-ndk$suffix"
    override val crashlyticsGradlePlugin = "$artifactPrefix-crashlytics-gradle$suffix"
    override val dynamicLinks = "$artifactPrefix-dynamic-links$suffix"
    override val inAppMessaging = "$artifactPrefix-inappmessaging$suffix"
    override val inAppMessagingKtx = "$artifactPrefix-inappmessaging-ktx$suffix"
    override val inAppMessagingDisplay = "$artifactPrefix-inappmessaging-display$suffix"
    override val inAppMessagingDisplayKtx = "$artifactPrefix-inappmessaging-display-ktx$suffix"

    override val mlKit: Firebase.MlKit = object : Firebase.MlKit {
        private val mlArtifactPrefix = "$artifactPrefix-ml"
        override val vision = "$mlArtifactPrefix-vision$suffix"
        override val naturalLanguage = "$mlArtifactPrefix-natural-language$suffix"
        override val models: Firebase.MlKit.Models = object : Firebase.MlKit.Models {
            override val vision: Firebase.MlKit.Models.Vision = object : Firebase.MlKit.Models.Vision {
                private val artifactPrefix = "$mlArtifactPrefix-vision"

                override val imageLabelling = "$artifactPrefix-image-label-model$suffix"
                override val objectDetectionAndTracking = "$artifactPrefix-object-detection-model$suffix"
                override val faceDetection = "$artifactPrefix-face-model$suffix"
                override val barcodeScanning = "$artifactPrefix-barcode-model$suffix"
                override val autoMl = "$artifactPrefix-automl$suffix"
            }
            override val naturalLanguage: Firebase.MlKit.Models.NaturalLanguage = object : Firebase.MlKit.Models.NaturalLanguage {
                private val artifactPrefix = "$mlArtifactPrefix-natural-language"

                override val languageIdentification = "$artifactPrefix-language-id-model$suffix"
                override val translate = "$artifactPrefix-translate-model$suffix"
                override val smartReply = "$artifactPrefix-smart-reply-model$suffix"
            }

            override val custom: String = "$mlArtifactPrefix-model-interpreter$suffix"
        }
    }

    override val performanceMonitoring = "$artifactPrefix-perf$suffix"
    override val realtimeDatabase = "$artifactPrefix-database$suffix"
    override val realtimeDatabaseKtx = "$artifactPrefix-database-ktx$suffix"
    override val remoteConfig = "$artifactPrefix-config$suffix"
    override val remoteConfigKtx = "$artifactPrefix-config-ktx$suffix"
}
