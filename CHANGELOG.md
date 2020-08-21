# Change log for refreshVersions

<!--## Version 0.9.5 (2020-08-??)-->
## [Unreleased]

This is a **major release** that brings surface-level and internal changes, paving the way for the upcoming 1.0 release.

**The plugin setup/bootstrap has changed**, so check out the updated documentation in [Setting-up.adoc](docs/Setting-up.adoc).

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
