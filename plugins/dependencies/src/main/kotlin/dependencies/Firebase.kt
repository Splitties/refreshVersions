@file:Suppress("PackageDirectoryMismatch", "ObjectPropertyName", "SpellCheckingInspection")

import de.fayard.refreshVersions.core.DependencyGroup

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

    /**
     * [Doc page](https://firebase.google.com/docs/android/learn-more#dynamic-feature-modules)
     */
    val dynamicModuleSupport = module("firebase-dynamic-module-support", usePlatformConstraints = false)

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
}
