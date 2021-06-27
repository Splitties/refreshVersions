# Change log for refreshVersions

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

- refreshVersions will now warn you when Gradle is not up to date, and will give you the commands to run to update it for you to copy/paste and run. It works if you're using a release candidate, and also if you're using a nightly version!

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
