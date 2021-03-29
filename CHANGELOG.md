# Change log for refreshVersions

## Unreleased

- Support updates of settings plugins in `settings.gradle.kts` and `settings.gradle` files (including refreshVersions itself).

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
- If you were using Jetpack Compose, the compiler dependency had its maven coordinates changed in version 1.0.0-alpha04. We updated the `AndroidX.compose.compiler` dependency constant, which means it now works only for Compose 1.0.0-alpha04 and more future versions.

### New dependency constants

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

### New dependency constants

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

### Dependency constants renamed

Several dependencies constants have been renamed in this release (compared to version 0.9.4).

If you were using one of the following, you'll need to migrate these usages.

We recommend to **use "Replace in Path"** in IntelliJ or Android Studio, filtering for the `*.gradle.kts` or `*.gradle` file extensions to do these replacements with ease.

_Note that for future versions, refreshVersions will be able to do this automatically._

Here's the list of renamed dependency constants:

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
- Dependency constants in `Ktor` no longer uses the `native` suffixed artifacts (because Kotlin 1.4 drops them, as the main ones become multiplatform)

### Fixes
- Version sorting would crash if a version had a long number in it. This has now been fixed, any length of digit sequence is now supported in versions.
- Fix maven coordinates of several dependency constants
- Don't crash on repositories that are not https or file or have non password credentials.
- The `AndroidX.test.ext.jankTestHelper` constant and few other ones in `Firebase.mlKit` had wrong maven coordinates. This has been fixed, and tests have been added to prevent it from happening again on any dependency constant we provide.

## Previous releases

[Change log](https://github.com/jmfayard/refreshVersions/blob/820a65589ce2e81124789f365a36ababc06bc9e3/CHANGELOG.md)
