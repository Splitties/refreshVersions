# Change log for refreshVersions

## [Unreleased]

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

The way it works is that we keep a versioned list of all the removals, and on refreshVersions upgrade, an automatic replacement will put back the hardcoded maven coordinates, using the version placeholder, and it will add our hand-written TODO/FIXME comments, along with a perfectly aligned replacement suggestion if there is any, so that moving to the newer artifact is as easy as upgrading to a newer version in the `versions.properties` file. We designed the system so that it cannot break your build, even if you were using `withVersion(…)` or other `DependencyNotation` extensions, even if you have code comments or special string literals.

Because of this change, it's important that you check the git diff after upgrading refreshVersions and running the first Gradle reload/sync/build, so you can see if there's been any changes, and if you might want to switch to any replacement dependencies.

This change will enable us to keep the built-in dependency notations updated with less effort, so we're very happy to have it ready, and fully tested.

### Bug fixes

- The kotest extensions dependencies were previously wrongly sharing their version as per refreshVersions rules. Now, they each have their independent, own version key.
- The `refreshVersionsMigrate` task wasn't inserting new entries in alphabetical order. Now it is.
- The `refreshVersionsMigrate` task wasn't migrating buildscript dependencies. Now it is.
- Repositories in declared in the `dependencyResolutionManagement` block were ignored. Now, they are taken into account.

### New dependency notations:

- Testing.kotest.framework:
    - api
    - dataset

Thanks to [Emil Kantis](https://github.com/Kantis) for [the kotest dependencies fixes](https://github.com/jmfayard/refreshVersions/pull/446)!

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
- The new `rejectVersionsIf { … }` predicate available in the `refreshVersions { … }` extension in your `settings.gradle[.kts]` file will allow you to filter any kind of versions you don't want to know about. It can be handy to filter snapshots out for example.
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
