# Change log for refreshVersions

## Version 0.60.6 (2025-08-15)

### Bug fix

Replaced usage of internal Gradle API that was removed/hidden in Gradle 9.0.0 and caused the builds of "consumer" projects to fail on Gradle upgrade.

Thanks to [AlexanderBartash](https://github.com/AlexanderBartash) for [their contribution](https://github.com/Splitties/refreshVersions/pull/736), and to the people that pinged me in issue [#735](https://github.com/Splitties/refreshVersions/issues/735)!

## Version 0.60.5 (2024-02-05)

### New dependency notation

- `AndroidX.wear.watchFace.complications.data`

### Other change

Change the version key of all watchface complication dependencies to be centralized.

## Version 0.60.4 (2024-01-31)

### Potentially breaking change

We now support only Gradle 8+. Use Gradle 8+ or stick to version 0.60.3 if you can't yet.

### Bug fix

Update the priority of artifact version key rules so that the longest match takes precedence, rather than the final key length.
That should fix the keys of `androidx.wear.compose` libraries.


## Version 0.60.3 (2023-09-27)

### Bug fix

Fix a bug where version keys would be marked as unused in some projects.

### New dependency notations

<details>
<summary><strong>Click to expand (2) </strong></summary>

- `AndroidX.bluetooth`
- `AndroidX.bluetooth.testing`
</details>


## Version 0.60.2 (2023-08-29)

### Bug fix

Some version keys were wrongly marked as unused following version 0.60.0. That's now fixed.
Thanks to the reporters and people that helped in reproducing the issue!

### Removed dependency notations

Several Accompanist libraries were deprecated following [this announcement](https://medium.com/androiddevelopers/an-update-on-jetpack-compose-accompanist-libraries-august-2023-ac4cbbf059f1).
As a result, we updated removed them from refreshVersions.
As usual, it won't break projects because we update the dependency notations with the equivalent string literal.
Also, relevant inline comments with links will be added to assist migration.

### New dependency notations

<details>
<summary><strong>Click to expand (18) </strong></summary>

- `AndroidX.core.performance.playServices`
- `AndroidX.core.performance.testing`
- `CashApp.sqlDelight.drivers.android`
- `CashApp.sqlDelight.extensions.androidPaging3`
- `CashApp.sqlDelight.extensions.coroutines`
- `CashApp.sqlDelight.gradlePlugin`
- `CashApp.sqlDelight.dialects.hSql`
- `CashApp.sqlDelight.drivers.jdbc`
- `CashApp.sqlDelight.dialects.mySql`
- `CashApp.sqlDelight.drivers.native`
- `CashApp.sqlDelight.dialects.postgreSql`
- `CashApp.sqlDelight.extensions.rxJava2`
- `CashApp.sqlDelight.extensions.rxJava3`
- `CashApp.sqlDelight.drivers.jdbcSqlite`
- `CashApp.sqlDelight.drivers.sqlJs`
- `CashApp.sqlDelight.drivers.webWorker`
- `Google.accompanist.adaptive`
- `Google.accompanist.testHarness`
</details>

## Version 0.60.1 (2023-08-24)

### Bug fix

- Fix crash on projects that had no version catalog.

### New dependency notations:

<details>
<summary><strong>Click to expand (25) </strong></summary>

- `Google.android.play.appUpdateKtx`
- `Google.android.play.appUpdate`
- `Google.android.play.assetDeliveryKtx`
- `Google.android.play.assetDelivery`
- `Google.android.play.featureDeliveryKtx`
- `Google.android.play.featureDelivery`
- `Google.android.play.reviewKtx`
- `Google.android.play.review`
- `Google.horologist.annotations`
- `Google.horologist.auth.composables`
- `Google.horologist.auth.data.phone`
- `Google.horologist.auth.data.watchOAuth`
- `Google.horologist.auth.data`
- `Google.horologist.auth.ui`
- `Google.horologist.compose.material`
- `Google.horologist.dataLayer.grpc`
- `Google.horologist.dataLayer.phone`
- `Google.horologist.dataLayer.watch`
- `Google.horologist.healthComposables`
- `Google.horologist.media3.audioOffload`
- `Google.horologist.media3.logging`
- `Google.horologist.media3.outputSwitcher`
- `Google.horologist.networkAwareness.db`
- `Google.horologist.networkAwareness.okHttp`
- `Google.horologist.networkAwareness.ui`
</details>

## Version 0.60.0 (2023-08-17)

### Full configuration cache support for the `refreshVersions` task

Now, running the `refreshVersions` task repeatedly will be much faster, because it now supports
configuration cache fully! That also means that it's ready for Gradle 9 when it'll be released.

### Bug fixes

- Fix "The root project is not yet available for build." error that would show up when using configuration cache in a project having `refreshVersions` configured for the `buildSrc` too.
- For projects using refreshVersions in their `buildSrc` too, a log would claim that new files were created after running the `refreshVersions` task, when they were only modified, or not touched at all. We fixed it.
- When no files were modified after running the `refreshVersions` task, there would still be a log saying that `versions.properties` and `libs.versions.toml` (if any) were updated or created. Now, it's only shown if there are actual changes.
- When running the `refreshVersions` task, it would always claim that some versions were hardcoded as it counted project dependencies. We are now filtering those out, and we updated the wording to reflect that they might not be actually hardcoded, as is the case when versions come from a Gradle plugin.
- Fix alignment of available update for versions catalogs for keys that are 3 characters long.

### New dependency notations:

<details>
<summary><strong>Click to expand (57) </strong></summary>

- `AndroidX.appSearch.debugView`
- `AndroidX.appSearch.ktx`
- `AndroidX.benchmark.baselineProfileGradlePlugin`
- `AndroidX.camera.viewFinder`
- `AndroidX.core.i18n`
- `AndroidX.core.locationAltitude`
- `AndroidX.core.telecom`
- `AndroidX.core.testing`
- `AndroidX.credentials.playServicesAuth`
- `AndroidX.credentials`
- `AndroidX.emoji2.picker`
- `AndroidX.glance.material3`
- `AndroidX.glance.material`
- `AndroidX.graphics.path`
- `AndroidX.graphics.shapes`
- `AndroidX.media3.container`
- `AndroidX.media3.effect`
- `AndroidX.media3.muxer`
- `AndroidX.mediaRouter.testing`
- `AndroidX.privacySandbox.plugins.library`
- `AndroidX.privacySandbox.sdkRuntime.client`
- `AndroidX.privacySandbox.sdkRuntime.core`
- `AndroidX.privacySandbox.tools.apiCompiler`
- `AndroidX.privacySandbox.tools.apiGenerator`
- `AndroidX.privacySandbox.tools.apiPackager`
- `AndroidX.privacySandbox.tools.core`
- `AndroidX.privacySandbox.tools`
- `AndroidX.privacySandbox.ui.client`
- `AndroidX.privacySandbox.ui.core`
- `AndroidX.privacySandbox.ui.provider`
- `AndroidX.room.gradlePlugin`
- `AndroidX.tracing.perfetto.handshake`
- `AndroidX.wear.compose.material3`
- `AndroidX.wear.compose.uiTooling`
- `AndroidX.wear.protoLayout.expression`
- `AndroidX.wear.protoLayout.material`
- `AndroidX.wear.protoLayout.renderer`
- `AndroidX.wear.protoLayout`
- `AndroidX.window.extensions.core`
- `Google.android.playServices.deviceToDeviceInteractions`
- `Google.android.playServices.gamesV2.nativeC`
- `Google.android.playServices.gamesV2`
- `Google.android.playServices.matter`
- `Google.android.playServices.mlKit.naturalLanguage.smartReply`
- `Google.android.playServices.mlKit.vision.barcodeScanning.codeScanner`
- `Google.android.playServices.mlKit.vision.textRecognition.chinese`
- `Google.android.playServices.mlKit.vision.textRecognition.devanagari`
- `Google.android.playServices.mlKit.vision.textRecognition.japanese`
- `Google.android.playServices.mlKit.vision.textRecognition.korean`
- `Google.android.playServices.tfLite.accelerationService`
- `Google.android.playServices.tfLite.gpu`
- `Google.android.playServices.tfLite.java`
- `Google.android.playServices.tfLite.support`
- `Google.android.playServices.threadNetwork`
- `Google.mlKit.vision.entityExtraction`
- `Google.mlKit.vision.faceMeshDetection`
- `Kotlin.gradlePlugin`

</details>

## Version 0.51.0 (2022-10-25)

### Support Jetpack Compose BoM

Yesterday, Google released a BoM for Jetpack Compose.
Today we're introducing first-class support for it.

To take advantage of it, you just need to add the dependency on `platform(AndroidX.compose.bom)`.
Just like `Firebase.bom`, make sure you add it **before** any dependent dependency:

```kts
dependencies {
    implementation(platform(AndroidX.compose.bom)) // Add this FIRST
    implementation(AndroidX.compose.material3) // Related dependencies AFTER
    implementation(AndroidX.compose.material3.windowSizeClass) // Same as above
}
```

In case you need to use a pre-release version (alpha, beta, rc…), use the `withVersionPlaceholder()` function as such:

```kts
dependencies {
    implementation(platform(AndroidX.compose.bom)) // Enables the BoM and depends on it
    implementation(AndroidX.compose.icons.extended) // Uses version defined in the BoM
    implementation(AndroidX.compose.material3.withVersionPlaceholder()) // Separate version in versions.properties
}
```

```kts
// Add Jetpack Compose to a project in seconds with refreshVersions.
// NEW: The Compose BoM released on Android Dev Summit is supported!

// No need to search for the versions, refreshVersions will do it for you!
// It will add the latest most stable version, and will even add the updates
// as comments in the versions.properties file (auto-created on first use).

android {
    buildFeatures.compose = true
    composeOptions {
        // Version and updates are in versions.properties
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.compiler)
    }
}

dependencies {
    // Version and updates of the BoM are in versions.properties too.
    implementation(platform(AndroidX.compose.bom)) // Enables the BoM automatically
    implementation(AndroidX.compose.runtime)                   // Version from the BoM
    implementation(AndroidX.compose.icons.extended)            // Version from the BoM

    // What if you need a specific alpha/beta/rc version?
    // withVersionPlaceholder() detaches the dependency from the BoM.
    // Version and updates will therefore be in versions.properties
    implementation(AndroidX.compose.material3.withVersionPlaceholder()) // Not from BoM
}
```

### Fix StabilityLevel calculation for number-less pre-versions

Since last changes in the Version class, versions like 1.7.20-RC and 1.7.20-Beta would be marked as stable instead of respectively ReleaseCandidate and Beta because the logic expected a number in all cases.

The number is now optional for all pre-versions, except milestones.

To prevent future recurrence of such regression, this commit
also adds tests that assert the expected stability level of
many known versions from various libraries.

### New dependency notations:

<details>
<summary><strong>Click to expand (11) </strong></summary>

- `AndroidX.asyncLayoutInflater.appcompat`
- `AndroidX.compose.bom`
- `AndroidX.dataStore.core.okio`
- `AndroidX.graphics.core`
- `AndroidX.input.motionPrediction`
- `AndroidX.paging.testing`
- `AndroidX.test.espresso.device`
- `AndroidX.tv.foundation`
- `AndroidX.tv.material`
- `Firebase.dynamicModuleSupport`
- `Google.firebase.dynamicModuleSupport`

</details>

## Version 0.50.2 (2022-09-24)

### Minor change

Disable the old `refreshVersionsDependenciesMapping` and `migrateToRefreshVersionsDependenciesConstants` Gradle tasks.

### Fixes

- `versionFor` could not work as it should have if it was used in multiple modules with different values: the `refreshVersions` task would only display the updates of some of them, and mark the other ones as unused. This has been fixed.
- Running the `refreshVersionsCleanup` task would log that the `gradle/libs.versions.toml` file was modified even if it wasn't, and didn't exist in the first place. This is now fixed.
- Version ordering now recognizes random qualifiers instead of marking with an unknown stability level.

### New dependency notations:

<details>
<summary><strong>Click to expand (15) </strong></summary>

- `AndroidX.media3.dataSource`
- `ApolloGraphQL.adapters`
- `ApolloGraphQL.api`
- `ApolloGraphQL.ast`
- `ApolloGraphQL.httpCache`
- `ApolloGraphQL.idlingResource`
- `ApolloGraphQL.mockserver`
- `ApolloGraphQL.normalizedCacheSqlite`
- `ApolloGraphQL.normalizedCache`
- `ApolloGraphQL.runtime`
- `ApolloGraphQL.testingSupport`
- `Google.horologist.dataLayer`
- `KotlinX.serialization.bom`
- `KotlinX.serialization.json.okio`
- `Spring.boot.web`

</details>

### Credits

Thanks to all the folks that contributed in this release!

- [Jacob Kwitkoski](https://github.com/thederputy)
- [Florian Levis](https://github.com/Gounlaf)
- [Seokjae Lee](https://github.com/doljae)
- [solonovamax](https://github.com/solonovamax)
- [Louis CAD](https://github.com/LouisCAD)
- [Jean-Michel Fayard](https://github.com/jmfayard)

## Version 0.50.1 (2022-09-11)

### Fixes

- Fix the "Extension of type 'VersionCatalogsExtension' does not exist." error.
- Versions Catalog migration only worked properly if you ran it twice. This has now been fixed!

## Version 0.50.0 (2022-09-09)

### Support for Versions Catalogs! 🎉

**Versions Catalogs** are Gradle 7.4+ solution for storing dependencies and versions in a centralized file.
Gradle will automatically recognize the `gradle/libs.versions.toml` file… and so will refreshVersions!

It is similar in spirit to the `versions.properties` file, **and we are happy to add support for it**:

- `./gradlew refreshVersions` will now add available updates as comments inside the `gradle/libs.versions.toml` file.
- `./gradlew refreshVersionsMigrate --mode=VersionCatalogAndVersionProperties` will **generate a versions catalog and migrate your build to use it** if you don't have one already.

Currently, we only support the default versions catalog. If you need support for multiple versions catalogs, add your 👍 [on this issue](https://github.com/jmfayard/refreshVersions/issues/596).

This is a big and new feature, so feel free to [provide feedback in this thread](https://github.com/jmfayard/refreshVersions/discussions/592), and report issues with the right info if there's no existing one [here](https://github.com/jmfayard/refreshVersions/issues).

### Better support for `versionFor`, and Jetpack Compose!

#### Before

`versionFor` was helpful when you need to access a version located in the `versions.properties` file, but if there was no dependency using it, you'd never see any updates.
For projects/modules using Jetpack Compose from Google, that meant you'd never see any updates for the compiler, and you'd need to look it up yourself.
Also, the version entry would be marked as unused, or would be the wrong one if you shared it with other Compose artifacts since [the compiler now has its own versioning track](https://android-developers.googleblog.com/2022/06/independent-versioning-of-Jetpack-Compose-libraries.html).

#### Now

Now, passing a dependency notation such as `AndroidX.compose.compiler` to `versionFor` is exactly the same as if you used the dependency somewhere in the project:

You'll get all the updates, and if the version is not yet specified in the `versions.properties` file, as usual, refreshVersions will try to find the latest most stable version available, plus it will also add the available comments for any newer, less stable version.
That makes it even easier to start a project with Jetpack Compose!

With this in a `build.gradle.kts` file:

```kts
android {
    composeOptions {
        kotlinCompilerExtensionVersion = versionFor(AndroidX.compose.compiler) // Kotlin DSL
        kotlinCompilerExtensionVersion = versionFor(project, AndroidX.compose.compiler) // Groovy DSL
    }
}
```

_`AndroidX.compose.compiler` is equivalent to `"androidx.compose.compiler:compiler:_"`._

You'll get that in the `versions.properties` file if you were on version `1.3.0-rc01`:

```properties
version.androidx.compose.compiler=1.3.0-rc01
##                    # available=1.3.0-rc02
##                    # available=1.3.0
```

### New page for all the built-in dependency notations 📕

To make it easy to start new projects, new modules, or using a common library, we spent a lot of time adding built-in dependency notations for Kotlin, kotlinx, AndroidX, and more.

However, it wasn't so easy to know that they exist.
That's why we made [a dedicated page where you can find them all](https://jmfayard.github.io/refreshVersions/dependency-notations/)! Let us know how helpful it is to you!

### Fixes

- `rejectVersionIf { … }` had an issue: its removal would not be taken into account until the Gradle daemon would be killed. This now behaves correctly.
- We were ignoring repositories defined in `pluginManagement { … }`, which might have led to Gradle plugin updates being missed by refreshVersions. Now, we look up these repositories as well.

### New dependency notations:

<details>
<summary><strong>Click to expand (145) </strong></summary>

- `AndroidX.appSearch.builtInTypes`
- `AndroidX.compose.runtime.tracing`
- `AndroidX.core.uwb`
- `AndroidX.core.uwb.rxJava3`
- `AndroidX.health.connect.client`
- `AndroidX.javascriptEngine`
- `AndroidX.lifecycle.runtime.compose`
- `AndroidX.room.paging.guava`
- `AndroidX.room.paging.rxJava2`
- `AndroidX.room.paging.rxJava3`
- `AndroidX.tracing.perfetto`
- `CashApp.molecule.gradlePlugin`
- `CashApp.molecule.runtime`
- `CashApp.molecule.test`
- `Google.accompanist.drawablePainter`
- `Google.accompanist.flowLayout`
- `Google.accompanist.navigationAnimation`
- `Google.accompanist.navigationMaterial`
- `Google.accompanist.permissions`
- `Google.accompanist.placeholder`
- `Google.accompanist.placeholder.material`
- `Google.accompanist.webView`
- `Google.ambient.crossDevice`
- `Google.horologist.audio`
- `Google.horologist.audio.ui`
- `Google.horologist.composables`
- `Google.horologist.compose.layout`
- `Google.horologist.compose.tools`
- `Google.horologist.media`
- `Google.horologist.media.data`
- `Google.horologist.media.ui`
- `Google.horologist.media3.backend`
- `Google.horologist.networkAwareness`
- `Google.horologist.tiles`
- `Square.okHttp3.mockWebServer3`
- `Square.okHttp3.mockWebServer3.junit4`
- `Square.okHttp3.mockWebServer3.junit5`
- `Square.okHttp3.android`
- `Square.okHttp3.brotli`
- `Square.okHttp3.coroutines`
- `Square.okHttp3.dnsOverHttps`
- `Square.okHttp3.sse`
- `Square.okHttp3.tls`
- `Square.okHttp3.urlConnection`
- `Arrow.core`
- `Arrow.fx.coroutines`
- `Arrow.fx.stm`
- `Arrow.optics`
- `Arrow.optics.kspPlugin`
- `Arrow.optics.reflect`
- `Arrow.stack`
- `Arrow.analysis.gradlePlugin`
- `Koin.navigation`
- `Testing.kotest.extensions.property.arbs`
- `Testing.kotest.extensions.property.datetime`
- `Ktor.client.contentNegotiation`
- `Ktor.client.contentNegotiationTests`
- `Ktor.client.gson`
- `Ktor.client.jackson`
- `Ktor.client.java`
- `Ktor.client.okHttp`
- `Ktor.client.resources`
- `Ktor.plugins.events`
- `Ktor.plugins.http`
- `Ktor.plugins.http.cio`
- `Ktor.plugins.io`
- `Ktor.plugins.network`
- `Ktor.plugins.networkTls`
- `Ktor.plugins.networkTlsCertificates`
- `Ktor.plugins.resources`
- `Ktor.plugins.serialization`
- `Ktor.plugins.serialization.gson`
- `Ktor.plugins.serialization.jackson`
- `Ktor.plugins.serialization.kotlinx`
- `Ktor.plugins.serialization.kotlinx.cbor`
- `Ktor.plugins.serialization.kotlinx.json`
- `Ktor.plugins.serialization.kotlinx.tests`
- `Ktor.plugins.serialization.kotlinx.xml`
- `Ktor.server.auth`
- `Ktor.server.auth.jwt`
- `Ktor.server.auth.ldap`
- `Ktor.server.autoHeadResponse`
- `Ktor.server.cachingHeaders`
- `Ktor.server.callId`
- `Ktor.server.callLogging`
- `Ktor.server.cio`
- `Ktor.server.compression`
- `Ktor.server.conditionalHeaders`
- `Ktor.server.contentNegotiation`
- `Ktor.server.cors`
- `Ktor.server.dataConversion`
- `Ktor.server.defaultHeaders`
- `Ktor.server.doubleReceive`
- `Ktor.server.forwardedHeader`
- `Ktor.server.freeMarker`
- `Ktor.server.hostCommon`
- `Ktor.server.hsts`
- `Ktor.server.htmlBuilder`
- `Ktor.server.httpRedirect`
- `Ktor.server.httpRedirect`
- `Ktor.server.jte`
- `Ktor.server.locations`
- `Ktor.server.methodOverride`
- `Ktor.server.metrics`
- `Ktor.server.metricsMicrometer`
- `Ktor.server.mustache`
- `Ktor.server.partialContent`
- `Ktor.server.pebble`
- `Ktor.server.resources`
- `Ktor.server.sessions`
- `Ktor.server.statusPages`
- `Ktor.server.testSuites`
- `Ktor.server.thymeleaf`
- `Ktor.server.velocity`
- `Ktor.server.webjars`
- `Ktor.server.websockets`
- `Ktor.server`
- `Ktor.plugins.websocketSerialization`
- `Ktor.plugins.websockets`
- `Testing.assertj.core`
- `Testing.assertj.db`
- `Testing.assertj.guava`
- `Testing.assertj.jodaTime`
- `Testing.assertj.swing`
- `Testing.hamcrest`
- `Testing.hamcrest.core`
- `Testing.hamcrest.library`
- `JetBrains.exposed.core`
- `JetBrains.exposed.dao`
- `JetBrains.exposed.jdbc`
- `KotlinX.dataframe`
- `KotlinX.dataframe.arrow`
- `KotlinX.dataframe.core`
- `KotlinX.dataframe.excel`
- `KotlinX.dataframe.dataframe`
- `KotlinX.deeplearning.api`
- `KotlinX.deeplearning.onnx`
- `KotlinX.deeplearning.visualization`
- `KotlinX.lincheck`
- `KotlinX.lincheck.jvm`
- `KotlinX.multik.api`
- `KotlinX.multik.default`
- `KotlinX.multik.jvm`
- `KotlinX.multik.native`
- `Spring.boms.springCloud`

</details>

### Credits

Thanks to all the folks that contributed in updating the built-in dependency notations!

- [Jacob Kwitkoski](https://github.com/thederputy) for `CashApp.molecule`
- [Rémi Latapy](https://github.com/rlatapy-luna) for `Google.accompanist`
- [Yuri Schimke](https://github.com/rlatapy-luna) for `Google.horologist` and `Square.okHttp3`
- [Brady Aiello](https://github.com/brady-aiello) for `Ktor`
- [Kamalesh](https://github.com/imashnake0) for `JetBrains.exposed`, `Testing.assertj`, `Testing.hamcrest`
- [doljae](https://github.com/doljae) for ` Spring.boms.springCloud`
- [Johan Reitan](https://github.com/joharei) for `KotlinX.multik`, `KotlinX.lincheck`, `KotlinX.deeplearning`
- [Ryan Fonzi](https://github.com/RFonzi) for `Arrow`

And thanks to the GitHub sponsors of the maintainers [Louis CAD](https://github.com/LouisCAD) and [Jean-Michel Fayard](https://github.com/jmfayard) who didn't count the hours spent on this project since 2018-2019!

_We hope you save a lot of time thanks to this project and can therefore leave work early, or pursue more valuable tasks and projects. 😉_

**If you're not a sponsor yet, please consider becoming one**, as a company, as an individual, or even both, it means a lot to us!
Just **click the heart** button at the top of the GitHub repo webpage, follow the steps, and your heart. Thank you!

## Version 0.40.2 (2022-06-01)

### Fixes

- Fix a memory leak that led the Gradle daemon to eventually suffer from a lot of GC overhead before running out of memory when a Gradle plugin had its version managed by refreshVersions.
We've done extensive tests to ensure we fixed this for good, and the CI now checks for leaks in the Gradle daemon to prevent future recurrence.
Note that if you were using Gradle configuration cache, you were likely to encounter this issue less often.
- Fix the `AndroidX.wear.tiles.material` dependency notation that was pointing to wrong coordinates because of a typo.
The test that was designed to catch this has been fixed to prevent future recurrence.

Thanks to [@yacine-ser](https://github.com/yacine-ser) for raising [this memory leak issue](https://github.com/jmfayard/refreshVersions/issues/536) along with a hint on the culprit, it was very helpful in reproducing and fixing the leak.

### New dependency notations:

- AndroidX.camera.mlKitVision
- AndroidX.compose.material3.windowSizeClass
- AndroidX.compose.ui.text.googleFonts
- AndroidX.core.performance
- AndroidX.customView.poolingContainer
- AndroidX.health.connectClient
- AndroidX.metrics.performance
- AndroidX.test.ext.junit.gTest
- AndroidX.test.ext.junit.ktx (was previously AndroidX.test.ext.junitKtx)
- AndroidX.wear.watchFace.complications.rendering
- Google.android.material.composeThemeAdapter3

## Version 0.40.1 (2022-02-06)

### Fixes

- Improved support of Gradle configuration cache. In the previous release, if you ran the `refreshVersions` task twice in a row with configuration cache enabled, the second run would fail, even in warning mode. It's now fixed.
- To make refreshVersions compatible with GCS (Google Cloud Storage) hosted maven repositories (which can be helpful for company/team internal libraries), we were using the official library from Google. Unfortunately, this led to dependency hell to some of our users because it then requires a specific version of Guava, which could be different from the one required by other plugins, and the one from refreshVersions would prevail. So, to resolve these problems, we replaced our implementation with one that reuses Gradle's built-in `GcsClient`, and we're very happy with that because it also has the benefit of reducing the total size of refreshVersions when we include its dependencies. Note that this is using internal Gradle APIs, but we've seen that they didn't change in 5 years, and the code path is executed only if you have gcs backed repositories in your project.

### New dependency notations:

- AndroidX.glance.wearTiles
- AndroidX.wear.tiles.material
- Google.android.maps
    - compose
    - utils
    - utils.ktx
    - ktx
    - rx
    - places
        - ktx
        - rx
- ReactiveX
    - rxJava2
        - rxAndroid
        - rxKotlin
    - rxJava3
        - rxAndroid
        - rxKotlin
- JakeWharton
    - rxBinding3
        - appcompat
        - core
        - drawerLayout
        - leanback
        - material
        - recyclerview
        - slidingPaneLayout
        - swipeRefreshLayout
        - viewPager2
        - viewPager
    - rxBinding4
        - appcompat
        - core
        - drawerLayout
        - leanback
        - material
        - recyclerview
        - slidingPaneLayout
        - swipeRefreshLayout
        - viewPager2
        - viewPager
    - rxRelay2
    - rxRelay3

### Credits

Thanks to [Mike Gray](https://github.com/mgray88) for the contribution in adding ReactiveX, RxBinding, and RxRelay dependency notations!

## Version 0.40.0 (2022-01-24)

### New features

- We are very happy to announce that **refreshVersions now supports [Gradle configuration cache](https://docs.gradle.org/7.3.3/userguide/configuration_cache.html)!** Gradle configuration is all about saving time, which is also the mission of refreshVersions, albeit in a different situation, so it made a lot of sense to not step on that feature Gradle engineers worked hard on. _Note that the `refreshVersions` task itself is not compatible with configuration cache because it's impossible with the current Gradle APIs._
- **Error tolerance**: The `refreshVersions` task will no longer fail if there's a problem getting versions from a repository. Now, it will add contextual comments in the `versions.properties` file, so you know what failed, and don't get fully blocked next time jcenter or another repository undergoes an outage. This should also help if you're running the `refreshVersions` task through an unstable internet connection and some network calls fail because of that.

### New dependency notations:

- Google.modernStorage:
    - bom
    - permissions
    - photoPicker
    - storage
- Google.android.openSourceLicensesPlugin
- Google.android.playServices:
    - appset
    - auth.apiPhone
    - auth.blockstore
    - basement
    - cronet
    - fido
    - openSourceLicenses
    - passwordComplexity
    - recaptcha
    - tagmanager
- Google.android.versionMatcherPlugin

### Credits

Thanks to [Kamalesh](https://github.com/imashnake0) for the contribution in adding dependency notations!

## Version 0.30.2 (2022-01-09)

### Bug fixes

- We are now shutting down the thread-pool from kotlinx.coroutines that we're using when the build finishes (`Dispatchers.shutdown()`). We believe it caused memory leaks in the Gradle Daemon, and this change, made possible since kotlinx.coroutines 1.6.0 should fix the last memory leak cause.
- The versions of dependencies from the `androidx.test` family started diverging, so we changed replaced the `version.androidx.test` version key with more specific ones. The migration will be done automatically on upgrade of refreshVersions, without upgrading the versions of `androidx.tests` dependencies themselves.

### New feature

- We now support fetching updates on plain-text http repositories. We evaluated the security risks, and for the case of refreshVersions itself, there is none that is significant (the worst possibility is crashing the build under attack), and if you're not using plain-text http repositories in the first place in your project, this doesn't apply at all anyway. We added support for this because some of our users need it for local maven repositories.

### New dependency notations:

- Android.billingClient
- Android.billingClient.ktx
- Android.installReferrer
- Android.tools:
    - desugarJdkLibs
    - r8
    - build.gradlePlugin
- CashApp.licenseeGradlePlugin
- CashApp.sqlDelight:
    - extensions:
        - androidPaging3
        - androidPaging
        - rxJava3
        - rxJava2
    - drivers.sqlJs
- Google.android.fhir:
    - dataCapture
    - engine
    - workflow
- Google.android.flexbox
- Google.android.playServices:
    - cast:
        - framework
        - tv
    - instantApps
    - maps
    - mlKit.vision.imageLabeling.custom
    - mlKit.naturalLanguage.languageIdentification
    - pay
    - wallet
- Google.mlKit:
    - playStoreDynamicFeatureSupport
    - naturalLanguage.entityExtraction
    - vision:
        - selfieSegmentation
        - textRecognition
            - chinese
            - devanagari
            - japanese
            - korean
- Google.modernStorage:
    - fileSystem
    - mediaStore
- Google.oboe
- Ktor.client.darwin
- Kotlin.scriptRuntime
- KotlinX.serialization.hocon
- Square.logcat
- Square.moshi.adapters
- Touchlab.kermit
    - bugsnagTest
    - bugsnag
    - crashlyticsTest
    - crashlytics
    - gradlePlugin
    - test

### Other changes

We also removed many obsolete dependency notations from refreshVersions. Just like for the 0.30.0 release, this should not break your build as we implemented a robust replacement mechanism that also inserts migration guidance comments. ✨

### Credits

Thanks to [Brady Aiello](https://github.com/brady-aiello), [Mike Gray](https://github.com/mgray88), and [Kamalesh](https://github.com/imashnake0) for their contributions in adding dependency notations!

## Version 0.30.1 (2021-12-21)

### Bug fixes

- Fix Gradle sync for projects using buildSrc ([#474](https://github.com/jmfayard/refreshVersions/issues/474))
- Support buildSrc modules that have no plugin declared

## Version 0.30.0 (2021-12-19)

### Potentially breaking change

We raised the minimum supported Gradle version to 6.8, because we started to use Kotlin 1.4 features, and Gradle pins the stdlib version. Since at the time of writing, the latest Gradle version is 7.3, we believe it won't actually block any of our users. We have [a short section about updating Gradle on our website here](https://jmfayard.github.io/refreshVersions/setup/#update-gradle-if-needed), feel free to check it out if it can help you.

### New features

#### Repositories declared in `dependencyResolutionManagement` are now supported (Gradle 7+)

Since Gradle 7, you can declare repositories of the entire Gradle project (including all subprojects and their buildscript)
in the `dependencyResolutionManagement` block in the `settings.gradle[.kts]` file.
Unfortunately, refreshVersions didn't support it, so, unless you also kept repositories declared with `allprojects`, or per project, you would end up with all version entries in the `versions.properties` file marked as unused after running the `refreshVersions` task, and you'd not see the newer updates.

This release resolves this issue, and we are eager to use it in our projects ourselves.

#### Update on built-in dependency notations

Sometimes, libraries get deprecated, or the maintainers change the maven coordinates.
When it happens, this fact is unfortunately not included in the `maven-metadata.xml` files, or any other standard metadata. That means tools like refreshVersions will believe you're on the latest versions, when you're not, because it lacks the necessary information.

One example is Google that changed the maven coordinates of all their AndroidX Wear Watchface artifacts several weeks ago.

It took us time to catch-up with this change because we wanted to design a generic mechanism for this recurrent problem, and provide the best experience for you, and ourselves.

From now on, we have the ability to remove old or deprecated built-in dependency notations in refreshVersions, and doing so will not break your builds, nor will it change the dependencies of your project. However, it'll help you notice the deprecation, and it'll help you switch to the replacement dependencies, if any.

The way it works is that we keep a versioned list of all the removals, and on refreshVersions upgrade, an automatic replacement will put back the hardcoded maven coordinates, using the version placeholder, and it will add our handwritten TODO/FIXME comments, along with a perfectly aligned replacement suggestion if there is any, so that moving to the newer artifact is as easy as upgrading to a newer version in the `versions.properties` file. We designed the system so that it cannot break your build, even if you were using `withVersion(…)` or other `DependencyNotation` extensions, even if you have code comments or special string literals.

It also supports the case where we just move a dependency notation to another place, or change its name, without changing the maven coordinates.

Because of this change, it's important that you check the git diff after upgrading refreshVersions and running the first Gradle reload/sync/build, so you can see if there's been any changes, and if you might want to switch to any replacement dependencies.

This change will enable us to keep the built-in dependency notations updated with less effort, so we're very happy to have it ready, and fully tested.

We've already started to take advantage of it to clean up all the discontinued artifacts we found in AndroidX.

### Bug fixes

- Repositories declared in the `dependencyResolutionManagement` block were ignored. Now, they are taken into account.
- The kotest extensions dependencies were previously wrongly sharing their version as per refreshVersions rules. Now, they each have their independent, own version key.
- The `refreshVersionsMigrate` task wasn't inserting new entries in alphabetical order. Now it is.
- The `refreshVersionsMigrate` task wasn't migrating buildscript dependencies. Now it is.

### New dependency notations:

- AndroidX.appSearch.platformStorage
- AndroidX.benchmark.macro
- AndroidX.camera.video
- AndroidX.carApp:
    - automotive
    - projected
- AndroidX.compose:
    - animation.graphics
    - material3
- AndroidX.core.remoteViews
- AndroidX.dragAndDrop
- AndroidX.emoji2.bundled
- AndroidX.glance.appWidget
- AndroidX.glance
- AndroidX.leanback.grid
- AndroidX.lifecycle.runtimeTesting
- AndroidX.media3:
    - cast
    - common
    - database
    - datasource:
        - cronet
        - okhttp
        - rtmp
    - decoder
    - exoPlayer:
        - dash
        - hls
        - ima
        - rtsp
        - workmanager
    - extractor
    - session
    - testUtils.robolectric
    - testUtils
    - transformer
    - ui.leanback
    - ui
- AndroidX.multidex.instrumentation
- AndroidX.paging.guava
- AndroidX.room:
    - paging
    - rxJava3
- AndroidX.wear.tiles.testing
- AndroidX.wear.watchFace:
    - complications.dataSourceKtx
    - complications.dataSource
    - editor
- KotlinX.coroutines.bom
- Square.moshi.adapters
- Testing.junit:
    - bom
    - jupiter: _(moved from Testing.junit)_
        - api _(moved from Testing.junit.api)_
        - engine _(moved from Testing.junit.engine)_
        - migrationSupport _(moved from Testing.junit.migrationSupport)_
        - params _(moved from Testing.junit.params)_
- Testing.kotest.framework:
    - api
    - dataset

Thanks to [Emil Kantis](https://github.com/Kantis) for [the kotest dependencies fixes](https://github.com/jmfayard/refreshVersions/pull/446)!
Thanks to [Kamalesh](https://github.com/imashnake0) for [the help in updating AndroidX dependency notations](https://github.com/jmfayard/refreshVersions/pull/460#issuecomment-995768730)!
Thanks to [Simon Marquis](https://github.com/SimonMarquis) for [adding Square.moshi.adapters](https://github.com/jmfayard/refreshVersions/pull/462), and [helping contributors using Windows](https://github.com/jmfayard/refreshVersions/pull/464)!

## Version 0.23.0 (2021-09-28)

### Bug fix

Fix a bug that broke standalone buildSrc builds.

## Version 0.22.0 (2021-09-27)

### Bug fix

Fix a regression that brought a `KotlinNullPointerException` in the build. We apologize for the issue. Thanks to Marcin and Craig for [the report](https://github.com/jmfayard/refreshVersions/issues/442).

### New dependency notations:

- AndroidX.compose.ui.toolingPreview
- Chucker
- KotlinX.cli
- KotlinX.datetime

Thanks to [Filip Czaplicki](https://github.com/starsep), who contributed to the new dependency notations!

## Version 0.21.0 (2021-09-07)

### New feature

We now support npm dependencies for Kotlin/JS!

Just put the version placeholder (`_`) in place of the version, and you're good to go.

The version keys follow a simple naming scheme where their id is prefixed with `npm`, here are two examples:
- `version.npm.react=17.0.2`
- `version.npm.@googlemaps/js-api-loader=1.12.2`

Special thanks to [NikkyAI](https://github.com/NikkyAI) who authored the feature, and pair-programmed with us to refine it!

### Improvements

Before this release, when we added new dependency notations and shorter version keys, it could lead to an unwanted upgrade of the dependency in the project upgrading refreshVersions. With this release, we make sure to copy the same version if we add or change the version key, and it will also work if we decide to remove one. This ensures that upgrading refreshVersions will not be able to affect your application or library.

Thanks to [Brady Aiello](https://github.com/brady-aiello) from [Touchlab](https://touchlab.co/) for helping out via pair-programming!

### New dependency notations:

- Koin (new group with several dependencies)
- Touchlab.stately (new group with several dependencies)
- RussHWolf.multiplatformSettings (new group with several dependencies)

Thanks to [Brady Aiello](https://github.com/brady-aiello) again, who contributed to these new dependency notations!

## Version 0.20.0 (2021-08-23)

### Announcement

We are now ready to accept dependency notation contributions for high-quality and popular dependencies from the Kotlin ecosystem!

Look for [issues with the `Dependency notations` and `up-for-grabs` tags](https://github.com/jmfayard/refreshVersions/issues?q=is%3Aopen+label%3A%22Dependency+notations%22+label%3A%22up-for-grabs%22) to find one you can directly contribute to, or submit a new issue to ask for a new one to be added. We updated the contributing guide on the website, it now has [a guide dedicated to it here](docs/contributing/submitting-prs/dependency-notations-updates.md).

### New features

- refreshVersions will now add `## unused` comments on top of unused entries in the `versions.properties` file after you run the `refreshVersions` task, so you know which ones are obsolete and can be removed.
- The new `rejectVersionIf { … }` predicate available in the `refreshVersions { … }` extension in your `settings.gradle[.kts]` file will allow you to filter any kind of versions you don't want to know about. It can be handy to filter snapshots out for example.
- Most of our dependency notations now provide more flexibility with 3 new extension functions: `withVersionPlaceholder()`, `withVersion(…)`, and `withoutVersion()`.

### Change (potentially breaking)

- If you use a BoM from the built-in dependency notations, it must always appear before dependencies from the group it controls, or you'll see an error message that will fail the Gradle build. We do this because we can't switch on usage of the BoM for linked dependencies that have already been evaluated by Gradle.

### Improvements

The `refreshVersionsMigrate` will now use the built-in dependency notations if they match existing dependencies.

### New dependency notations:

- AndroidX:
    - benchmark.macroJunit4
    - core.splashscreen
    - games:
        - activity
        - controller
        - framePacing
        - performanceTuner
        - textInput
    - navigation.testing
    - wear.compose:
        - foundation
        - material
    - window
      - java
      - rxJava2
      - rxJava3
      - testing
- COIL:
    - compose
    - composeBase
- Firebase:
    - cloudMessagingDirectBoot
    - Firebase.mlModelDownloaderKtx
    - Firebase.mlModelDownloader
- Kodein.di (new group with many dependencies)
- Kotlin.test (which is compatible with multiplatform projects since Kotlin 1.5)
- KotlinX.html (compatible with multiplatform projects)
- Ktor.features.serialization
- Http4k (new group with many dependencies)
- Splitties.alertdialogMaterial
- Square
    - okHttp3.bom
    - okHttp3 (shortcut to existing okHttp3.okHttp)
    - retrofit2.converter.wire
    - retrofit2 (shortcut to existing retrofit2.retrofit)
- Spring (new group with many dependencies)


## Version 0.11.0 (2021-08-03)

### New feature

Add task **refreshVersionsMigrate** that adds all missing entries in versions.properties and try to migrate the `build.gradle(.kts)` and other known files like `libraries.gradle` so that the version placeholder `_` is used everywhere. Please [try it out and give us your feedback for refreshVersionsMigrate](https://github.com/jmfayard/refreshVersions/discussions/396)

### Fixes

- Plugins org.jetbrains.kotlinx.benchmark should not use key version.kotlin

### New dependency notations

- Ktor.features.serialization
- AndroidX.navigation.testing
- Testing.kotestExtensions who replaces Testing.kotest.extensions in Kotest >= 4.5.0


## Version 0.10.1 (2021-06-10)

### New features

- In Android projects, if you used the version placeholder (`_`) directly in `build.gradle(.kts)` files, Android lint would trigger an unwanted warning, or error in the case of the Android build tools (aka. AGP, the Android Gradle Plugin). To avoid this inconvenience, running the refreshVersions task will now automatically check if it's running on an Android project, and in such cases, will edit (safely) the `lint.xml` file, creating it if needed, and add the needed rules to have these specific warnings and errors ignored.

### Fixes

- Add missing version placeholder (`_`) for the `Google.android.material.composeThemeAdapter` dependency notation.
- Fix a bug that prevented from using the correct version of `org.jetbrains.kotlinx.benchmark` and any other Gradle plugin with an id starting with `org.jetbrains.kotlinx` because it matched over `org.jetbrains.kotlin` as well. We are now matching on `org.jetbrains.kotlin.` to avoid this issue.

### New dependency notations:

- AndroidX:
    - emoji2
        - views-helper
        - views
    - health.servicesClient
    - security.appAuthenticatorTesting
- Google.accompanist.insets.ui


## Version 0.10.0 (2021-05-13)

### New features

- There's a new `versionFor` function that takes a dependency notation, or a version key, and returns the corresponding version that is in the `versions.properties` file. For example, if you use Jetpack Compose, you can leverage it to set `kotlinCompilerExtensionVersion` with `versionFor(AndroidX.compose.ui)`. Groovy DSL users can find it in the `Versions` class.
- Support updates of settings plugins in `settings.gradle.kts` and `settings.gradle` files (including refreshVersions itself).
- Support getting versions from [Google Cloud Storage](https://cloud.google.com/storage) backed [repositories](https://docs.gradle.org/current/userguide/declaring_repositories.html#sec:gcs-repositories). This can be helpful if you need to update private artifacts hosted there. Thanks to [NikkyAI](https://github.com/NikkyAI) for the contribution!

### Changes

- Setting up refreshVersions has been significantly simplified: Now, it's simply a plugin that must be applied in the `settings.gradle.kts` or `settings.gradle` file. _Note that if you want to apply it to `buildSrc` as well, there's a gotcha regarding defining the version._ The best thing is that on upgrade, **refreshVersions will automatically replace the old & verbose bootstrap with the new plugin setup**, and that works for buildSrc special case as well. We made many tests to ensure that this logic is reliable, doesn't break any code, doesn't remove important comments, and doesn't affect custom configuration in any way.
- Bintray sunset resiliency: If refreshVersions encounters an HTTP 403 response on a bintray repository or on jcenter, it will consider the artifact to not be in this repository instead of crashing (but you can/should still remove bintray repo declarations anyway since it has been sunset).
- Missing short version key rules have been added for many dependency notations groups in the `Testing` object, and for `Orchid`.

### Potentially breaking changes

- The fix of the `Square.sqlDelight.coroutinesExtensions` dependency notation can lead to such an error: `Failed to resolve: coroutines-extensions-1.4.4-_`. If you get a similar error on upgrade, it's because you applied a fix like that one: `Square.sqlDelight.coroutinesExtensions + ":_"`. You now can (must) remove it.
- A bunch of new version key rules have been added, which means you might have changes of version keys, and because we currently don't have a migration facility for those, it might cause unwanted upgrades. Consequently, especially for Android projects, we recommend checking/verifying the changes made by refreshVersions after the first Gradle sync/reload/build that follows the upgrade.

### Fixes

- Authentication for maven repositories should now work correctly. Should, because it can only work using internal Gradle APIs for the time being (though there's a safeguard to not crash if the API changes). Thanks to [Mayank Kharbanda](https://github.com/mayankmkh) for the PR!
- Custom `extraArtifactVersionKeyRules` could not be taken into account if there was an overlapping rule already present in refreshVersions, even if it was more specific. That ordering issue has now been fixed, the most specific rule will now always be the one applied.
- If you had issues with some recent AndroidX artifacts, and their version key, they should all be fixed now, and there's all the latest dependency notations.

### New dependency notations

- AndroidX:
    - activity.ktx
    - activity.compose
    - appSearch
    - appSearch.compiler
    - appSearch.localStorage
    - biometricKtx
    - carApp
    - carApp.testing
    - compose:
        - material.ripple
        - runtime.rxJava3
        - runtime.saveable
        - ui:
            - test
            - testJunit4
            - testManifest
            - tooling
            - toolingData
            - viewBinding
    - constraintLayoutCompose
    - core.googleShortcuts
    - dataStore:
        - core
        - rxJava2
        - rxJava3
        - preferences:
            - core
            - rxJava2
            - rxJava3
    - hilt.navigationCompose
    - hilt.navigationFragment
    - leanback.paging
    - leanback.preference
    - leanback.tab
    - lifecycle.viewModelCompose
    - navigation.compose
    - navigation.dynamicFeaturesFragment
    - paging.compose
    - paging.rxJava3
    - savedStateKtx
    - security.appAuthenticator
    - transitionKtx
    - wear:
        - complications.data
        - complications.provider
        - input
        - inputTesting
        - ongoing
        - phoneInteractions
        - remoteInteractions
        - watchFace
        - watchFace.guava
        - watchFace.client
        - watchFace.clientGuava
        - watchFace.complicationsRendering
        - watchFace.data
        - watchFace.editor
        - watchFace.editorGuava
        - watchFace.style
        - tiles:
            - proto
            - renderer
    - work.multiprocess
    - work.rxJava3
- Firebase:
    - analyticsKtx
    - authenticationKtx
    - cloudMessagingKtx
    - crashlyticsKtx
    - dynamicLinksKtx
    - performanceMonitoringKtx
- Google:
    - accompanist:
        - appcompatTheme
        - coil
        - flowlayout
        - glide
        - imageloadingCore
        - insets
        - pager.indicators
        - pager
        - swiperefresh
        - systemuicontroller
    - android.material.composeThemeAdapter
    - dagger.hilt.compiler
- Testing.kotest.assertions.kotlinxDateTime

### Special mentions

Thanks to all the folks that joined [Louis CAD](https://github.com/LouisCAD) in pair-programming sessions:

- [![](https://github.com/waah42.png?size=18) Waqas Ahmed](https://github.com/waah42)
- [![](https://github.com/borsini.png?size=18) Benjamin Orsini](https://github.com/borsini)
- [![](https://github.com/ZahraHeydari.png?size=18) Zahra Heydari](https://github.com/ZahraHeydari)
- [![](https://github.com/ruffCode.png?size=18) Alexi Bre](https://github.com/ruffCode)
- …and of course the original author and maintainer [![](https://github.com/jmfayard.png?size=18) Jean-Michel Fayard](https://github.com/jmfayard)

These were critical to ensure thorough testing, and great quality, all while keeping motivation to keep going.

We're very grateful for your time and help, and we think our users will be as well. 🙏

Also, thanks to all the folks that reported issues. It was very helpful to prioritize on our side.


## Version 0.9.7 (2020-10-16)

### Fixes

- Running the `refreshVersions` task twice or more would fail with "executor rejected" as an error message, until the Gradle daemon is killed. This has now been fixed. (Issue #263)
- The `refreshVersions` task was failing after the latest Gradle release candidate was superseded by the stable release because the API would return an empty JSON object after this, which we didn't expect.
- (Minor) We fixed a typo in a diagnostic task name (`refreshVersionsDependenciesMapping`)


## Version 0.9.6 (2020-10-12)

### Fixes

- This release **fixes a major memory leak** that would make Gradle Daemons unusable after several builds (or Gradle syncs/imports). _We are very sorry for that issue, and the annoyance it might have caused. We experienced it too as users, and that's why we are bringing the fix now._ We **took the necessary measures to avoid future recurrence** of such memory leaks.
- Fix of a bug that'd make the first Gradle sync  after adding a dependency fail.

### Breaking change
- If you were using Jetpack Compose, the compiler dependency had its maven coordinates changed in version 1.0.0-alpha04. We updated the `AndroidX.compose.compiler` dependency notation, which means it now works only for Compose 1.0.0-alpha04 and more future versions.

### New dependency notations

- Google.android.playServices.mlKit
- Google.mlKit
- KotlinX.serialization.json

### Deprecated

Firebase ML Kit has been rebranded to Google ML Kit along with API and feature changes [since 2020-08-14 update](https://firebase.google.com/support/release-notes/android#2020-08-14), so we deprecated the `Firebase.mlKit` dependencies and introduced new ones in `Google.android.playServices.mlKit` and `Google.mlKit`.

### New features

- refreshVersions will now warn you when Gradle is not up-to-date, and will give you the commands to run to update it for you to copy/paste and run. It works if you're using a release candidate, and also if you're using a nightly version!


## Version 0.9.5 (2020-08-21)

This is a **major release** that brings surface-level and internal changes, paving the way for the upcoming 1.0 release.

**The plugin setup/bootstrap has changed**, so check out the updated documentation in [Setup](https://jmfayard.github.io/refreshVersions/setup/).

### New features

- Self update discovery. **RefreshVersions will check for its own updates**, and add available comments in the `settings.gradle[.kts]` file if needed for easy upgrade. This allows you to get future improvements conveniently.
- Support for `buildscript` dependencies. It now works just like regular dependencies.
- First class support for `buildSrc`
- Support maven repositories with basic authentication (aka. credentials with username and password)
- Going forward, **refreshVersions will be able to auto-migrate** any breaking changes a new version would introduce in your `versions.properties`, `build.gradle` and `build.gradle.kts` files in. This version of refreshVersions integrates the facility to
let future versions of refreshVersions that migration is needed, and from which version. This is a very important change that ensures you can keep your projects updated with the least effort possible.

### New dependency notations

- Kotlin.stdlib (for the base version of the stdlib)
- KotlinX:
  - html
  - Coroutines:
    - reactive
    - reactor
    - rx2
    - rx3
  - Serialization:
    - core (replaces runtime artifacts since 1.0.0-RC)
    - protobuf
    - cbor
    - properties
  - io
  - reflect.lite
  - nodeJs
- [COIL](https://coil-kt.github.io/coil/), a **Co**routine **I**mage **L**oader for Android.
- Square:
  - LeakCanary
  - KotlinPoet
  - Wire
  - SqlDelight
  - Moshi
  - Picasso
  - okio
  - Retrofit2:
    - Adapter:
      - java8   (renamed from retrofitJava8)
      - rxJava1 (renamed from retrofitRxJava1)
      - rxJava2 (renamed from retrofitRxJava2)
      - rxJava3
- KoTest (in the `Testing` object)
- CashApp:
  - sqlDelight (alias to Square.sqlDelight)
  - turbine
  - Copper
- Google:
  - Dagger (including hilt for Android)
  - Android:
    - browserHelper
- JakeWharton:
  - picnic
  - wormholeGradlePlugin
  - confundusGradlePlugin
  - moshi.shimo
- AndroidX:
  - Gaming
  - Hilt
  - startup
  - tracing
  - vectorDrawableSeekable
  - Window
  - Core:
    - animation
    - animationTesting
  - Security:
    - cryptoKtx
    - identityCredential
  - Compose:
    - Runtime:
      - dispatch
      - savedInstance
      - liveData
      - rxJava2
    - Animation
    - Ui
    - Foundation
    - Material
  - Media2:
    - lifecycle
  - Concurrent:
    - futuresKtx

### Dependency notations renamed

Several dependencies notations have been renamed in this release (compared to version 0.9.4).

If you were using one of the following, you'll need to migrate these usages.

We recommend to **use "Replace in Path"** in IntelliJ or Android Studio, filtering for the `*.gradle.kts` or `*.gradle` file extensions to do these replacements with ease.

_Note that for future versions, refreshVersions will be able to do this automatically._

Here's the list of renamed dependency notations:

- `AndroidX.coreKtx` -> `AndroidX.core.ktx`
- `AndroidX.coreRole` -> `AndroidX.core.role`
- `Square.retrofit2.adapter.retrofitJava8` -> `Square.retrofit2.adapter.java8`
- `Square.retrofit2.adapter.retrofitRxJava1` -> `Square.retrofit2.adapter.rxJava1`
- `Square.retrofit2.adapter.retrofitRxJava2` -> `Square.retrofit2.adapter.rxJava2`
- `Testing.junit.junitJupiter` -> `Testing.junit`
- `Testing.mockK.mockK` -> `Testing.mockK`

### Changes
- Make the `refreshVersions` task cancellable during network requests.
- Now, only http 404 and 401 responses from repositories will be silent.
- Server errors (i.e. all but http 404 and 401 responses) are now reported.
- Network failures are now reported.
- There is now a connection timeout (10 seconds per request for now)
- An error is reported if a dependency wasn't found in any of the configured repositories.
- All the searched repositories are now listed if a dependency wasn't found in any of them.
- Only declared repositories are now looked up. (Before, refreshVersions would search all dependencies in all repositories of all modules and their buildscript, regardless of which module was declaring them.)
- Dependency notation in `Ktor` no longer uses the `native` suffixed artifacts (because Kotlin 1.4 drops them, as the main ones become multiplatform)

### Fixes
- Version sorting would crash if a version had a long number in it. This has now been fixed, any length of digit sequence is now supported in versions.
- Fix maven coordinates of several dependency notations
- Don't crash on repositories that are not https or file or have non password credentials.
- The `AndroidX.test.ext.jankTestHelper` notation and few other ones in `Firebase.mlKit` had wrong maven coordinates. This has been fixed, and tests have been added to prevent it from happening again on any dependency notation we provide.

## Previous releases

[Change log](https://github.com/jmfayard/refreshVersions/blob/820a65589ce2e81124789f365a36ababc06bc9e3/CHANGELOG.md)
