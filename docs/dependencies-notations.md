---
title: Built-in Dependency Notations
---
# Built-in Dependency Notations

[**refreshVersions**](https://github.com/jmfayard/refreshVersions) provides **1152** Dependency Notations in **25** groups and **233** subgroups

**Built-in Dependency Notations** are maven coordinates of popular libraries,
discoverable as for example `KotlinX.coroutines.core` in IntelliJ IDEA,
who will be configured in the `versions.properties` file with the latest available version
after the first Gradle sync.
They drastically cut the time it takes to add popular libraries to your Gradle build.

[**See: Adding a Dependency Notation**](add-dependencies.md)

---

Below is the list of all available dependency notations.

Use the table of contents to jump to the group you are interested in.

Hover 🐁 on a dependency notation to see its `Triple(KotlinName, MavenCoordinate, VersionKey)`.


## [Android.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Android.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Android</b></td><td><span title="Android.billingClient
com.android.billingclient:billing:_
version.android.billingclient" style="text-decoration: underline;" >billingClient</span>&nbsp; - <span title="Android.installReferrer
com.android.installreferrer:installreferrer:_
version.NO-RULE" style="text-decoration: underline;" >installReferrer</span>&nbsp;</td></tr>
<tr><td><b>Android.billingClient</b></td><td><span title="Android.billingClient.ktx
com.android.billingclient:billing-ktx:_
version.android.billingclient" style="text-decoration: underline;" >ktx</span>&nbsp;</td></tr>
<tr><td><b>Android.tools</b></td><td><span title="Android.tools.desugarJdkLibs
com.android.tools:desugar_jdk_libs:_
version.android.tools.desugar_jdk_libs" style="text-decoration: underline;" >desugarJdkLibs</span>&nbsp; - <span title="Android.tools.r8
com.android.tools:r8:_
version.android.tools.r8" style="text-decoration: underline;" >r8</span>&nbsp;</td></tr>
<tr><td><b>Android.tools.build</b></td><td><span title="Android.tools.build.gradlePlugin
com.android.tools.build:gradle:_
version.NO-RULE" style="text-decoration: underline;" >gradlePlugin</span>&nbsp;</td></tr>
                </table>
## [AndroidX.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/AndroidX.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>AndroidX</b></td><td><span title="AndroidX.activity
androidx.activity:activity:_
version.androidx.activity" style="text-decoration: underline;" >activity</span>&nbsp; - <span title="AndroidX.annotation
androidx.annotation:annotation:_
version.androidx.annotation" style="text-decoration: underline;" >annotation</span>&nbsp; - <span title="AndroidX.appCompat
androidx.appcompat:appcompat:_
version.androidx.appcompat" style="text-decoration: underline;" >appCompat</span>&nbsp; - <span title="AndroidX.appSearch
androidx.appsearch:appsearch:_
version.androidx.appsearch" style="text-decoration: underline;" >appSearch</span>&nbsp; - <span title="AndroidX.asyncLayoutInflater
androidx.asynclayoutinflater:asynclayoutinflater:_
version.androidx.asynclayoutinflater" style="text-decoration: underline;" >asyncLayoutInflater</span>&nbsp; - <span title="AndroidX.autoFill
androidx.autofill:autofill:_
version.androidx.autofill" style="text-decoration: underline;" >autoFill</span>&nbsp; - <span title="AndroidX.biometric
androidx.biometric:biometric:_
version.androidx.biometric" style="text-decoration: underline;" >biometric</span>&nbsp; - <span title="AndroidX.browser
androidx.browser:browser:_
version.androidx.browser" style="text-decoration: underline;" >browser</span>&nbsp; - <span title="AndroidX.carApp
androidx.car.app:app:_
version.androidx.car.app" style="text-decoration: underline;" >carApp</span>&nbsp; - <span title="AndroidX.cardView
androidx.cardview:cardview:_
version.androidx.cardview" style="text-decoration: underline;" >cardView</span>&nbsp; - <span title="AndroidX.collection
androidx.collection:collection:_
version.androidx.collection" style="text-decoration: underline;" >collection</span>&nbsp; - <span title="AndroidX.constraintLayout
androidx.constraintlayout:constraintlayout:_
version.androidx.constraintlayout" style="text-decoration: underline;" >constraintLayout</span>&nbsp; - <span title="AndroidX.contentPager
androidx.contentpager:contentpager:_
version.androidx.contentpager" style="text-decoration: underline;" >contentPager</span>&nbsp; - <span title="AndroidX.coordinatorLayout
androidx.coordinatorlayout:coordinatorlayout:_
version.androidx.coordinatorlayout" style="text-decoration: underline;" >coordinatorLayout</span>&nbsp; - <span title="AndroidX.core
androidx.core:core:_
version.androidx.core" style="text-decoration: underline;" >core</span>&nbsp; - <span title="AndroidX.cursorAdapter
androidx.cursoradapter:cursoradapter:_
version.androidx.cursoradapter" style="text-decoration: underline;" >cursorAdapter</span>&nbsp; - <span title="AndroidX.customView
androidx.customview:customview:_
version.androidx.customview" style="text-decoration: underline;" >customView</span>&nbsp; - <span title="AndroidX.dataStore
androidx.datastore:datastore:_
version.androidx.datastore" style="text-decoration: underline;" >dataStore</span>&nbsp; - <span title="AndroidX.documentFile
androidx.documentfile:documentfile:_
version.androidx.documentfile" style="text-decoration: underline;" >documentFile</span>&nbsp; - <span title="AndroidX.dragAndDrop
androidx.draganddrop:draganddrop:_
version.androidx.draganddrop" style="text-decoration: underline;" >dragAndDrop</span>&nbsp; - <span title="AndroidX.drawerLayout
androidx.drawerlayout:drawerlayout:_
version.androidx.drawerlayout" style="text-decoration: underline;" >drawerLayout</span>&nbsp; - <span title="AndroidX.dynamicAnimation
androidx.dynamicanimation:dynamicanimation:_
version.androidx.dynamicanimation" style="text-decoration: underline;" >dynamicAnimation</span>&nbsp; - <span title="AndroidX.emoji
androidx.emoji:emoji:_
version.androidx.emoji" style="text-decoration: underline;" >emoji</span>&nbsp; - <span title="AndroidX.emoji2
androidx.emoji2:emoji2:_
version.androidx.emoji2" style="text-decoration: underline;" >emoji2</span>&nbsp; - <span title="AndroidX.exifInterface
androidx.exifinterface:exifinterface:_
version.androidx.exifinterface" style="text-decoration: underline;" >exifInterface</span>&nbsp; - <span title="AndroidX.fragment
androidx.fragment:fragment:_
version.androidx.fragment" style="text-decoration: underline;" >fragment</span>&nbsp; - <span title="AndroidX.glance
androidx.glance:glance:_
version.androidx.glance" style="text-decoration: underline;" >glance</span>&nbsp; - <span title="AndroidX.gridLayout
androidx.gridlayout:gridlayout:_
version.androidx.gridlayout" style="text-decoration: underline;" >gridLayout</span>&nbsp; - <span title="AndroidX.heifWriter
androidx.heifwriter:heifwriter:_
version.androidx.heifwriter" style="text-decoration: underline;" >heifWriter</span>&nbsp; - <span title="AndroidX.interpolator
androidx.interpolator:interpolator:_
version.androidx.interpolator" style="text-decoration: underline;" >interpolator</span>&nbsp; - <span title="AndroidX.javascriptEngine
androidx.javascriptengine:javascriptengine:_
version.androidx.javascriptengine" style="text-decoration: underline;" >javascriptEngine</span>&nbsp; - <span title="AndroidX.leanback
androidx.leanback:leanback:_
version.androidx.leanback" style="text-decoration: underline;" >leanback</span>&nbsp; - <span title="AndroidX.loader
androidx.loader:loader:_
version.androidx.loader" style="text-decoration: underline;" >loader</span>&nbsp; - <span title="AndroidX.localBroadcastManager
androidx.localbroadcastmanager:localbroadcastmanager:_
version.androidx.localbroadcastmanager" style="text-decoration: underline;" >localBroadcastManager</span>&nbsp; - <span title="AndroidX.media
androidx.media:media:_
version.androidx.media" style="text-decoration: underline;" >media</span>&nbsp; - <span title="AndroidX.mediaRouter
androidx.mediarouter:mediarouter:_
version.androidx.mediarouter" style="text-decoration: underline;" >mediaRouter</span>&nbsp; - <span title="AndroidX.multidex
androidx.multidex:multidex:_
version.androidx.multidex" style="text-decoration: underline;" >multidex</span>&nbsp; - <span title="AndroidX.palette
androidx.palette:palette:_
version.androidx.palette" style="text-decoration: underline;" >palette</span>&nbsp; - <span title="AndroidX.preference
androidx.preference:preference:_
version.androidx.preference" style="text-decoration: underline;" >preference</span>&nbsp; - <span title="AndroidX.print
androidx.print:print:_
version.androidx.print" style="text-decoration: underline;" >print</span>&nbsp; - <span title="AndroidX.recommendation
androidx.recommendation:recommendation:_
version.androidx.recommendation" style="text-decoration: underline;" >recommendation</span>&nbsp; - <span title="AndroidX.recyclerView
androidx.recyclerview:recyclerview:_
version.androidx.recyclerview" style="text-decoration: underline;" >recyclerView</span>&nbsp; - <span title="AndroidX.remoteCallback
androidx.remotecallback:remotecallback:_
version.androidx.remotecallback" style="text-decoration: underline;" >remoteCallback</span>&nbsp; - <span title="AndroidX.savedState
androidx.savedstate:savedstate:_
version.androidx.savedstate" style="text-decoration: underline;" >savedState</span>&nbsp; - <span title="AndroidX.shareTarget
androidx.sharetarget:sharetarget:_
version.androidx.sharetarget" style="text-decoration: underline;" >shareTarget</span>&nbsp; - <span title="AndroidX.slidingPaneLayout
androidx.slidingpanelayout:slidingpanelayout:_
version.androidx.slidingpanelayout" style="text-decoration: underline;" >slidingPaneLayout</span>&nbsp; - <span title="AndroidX.sqlite
androidx.sqlite:sqlite:_
version.androidx.sqlite" style="text-decoration: underline;" >sqlite</span>&nbsp; - <span title="AndroidX.swipeRefreshLayout
androidx.swiperefreshlayout:swiperefreshlayout:_
version.androidx.swiperefreshlayout" style="text-decoration: underline;" >swipeRefreshLayout</span>&nbsp; - <span title="AndroidX.textClassifier
androidx.textclassifier:textclassifier:_
version.androidx.textclassifier" style="text-decoration: underline;" >textClassifier</span>&nbsp; - <span title="AndroidX.tracing
androidx.tracing:tracing:_
version.androidx.tracing" style="text-decoration: underline;" >tracing</span>&nbsp; - <span title="AndroidX.transitionKtx
androidx.transition:transition-ktx:_
version.androidx.transition" style="text-decoration: underline;" >transitionKtx</span>&nbsp; - <span title="AndroidX.transition
androidx.transition:transition:_
version.androidx.transition" style="text-decoration: underline;" >transition</span>&nbsp; - <span title="AndroidX.tvProvider
androidx.tvprovider:tvprovider:_
version.androidx.tvprovider" style="text-decoration: underline;" >tvProvider</span>&nbsp; - <span title="AndroidX.vectorDrawable
androidx.vectordrawable:vectordrawable:_
version.androidx.vectordrawable" style="text-decoration: underline;" >vectorDrawable</span>&nbsp; - <span title="AndroidX.versionedParcelable
androidx.versionedparcelable:versionedparcelable:_
version.androidx.versionedparcelable" style="text-decoration: underline;" >versionedParcelable</span>&nbsp; - <span title="AndroidX.viewPager
androidx.viewpager:viewpager:_
version.androidx.viewpager" style="text-decoration: underline;" >viewPager</span>&nbsp; - <span title="AndroidX.viewPager2
androidx.viewpager2:viewpager2:_
version.androidx.viewpager2" style="text-decoration: underline;" >viewPager2</span>&nbsp; - <span title="AndroidX.wear
androidx.wear:wear:_
version.androidx.wear" style="text-decoration: underline;" >wear</span>&nbsp; - <span title="AndroidX.webkit
androidx.webkit:webkit:_
version.androidx.webkit" style="text-decoration: underline;" >webkit</span>&nbsp; - <span title="AndroidX.window
androidx.window:window:_
version.androidx.window" style="text-decoration: underline;" >window</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.activity</b></td><td><span title="AndroidX.activity.compose
androidx.activity:activity-compose:_
version.androidx.activity" style="text-decoration: underline;" >compose</span>&nbsp; - <span title="AndroidX.activity.ktx
androidx.activity:activity-ktx:_
version.androidx.activity" style="text-decoration: underline;" >ktx</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.annotation</b></td><td><span title="AndroidX.annotation.experimental
androidx.annotation:annotation-experimental:_
version.androidx.annotation" style="text-decoration: underline;" >experimental</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.appCompat</b></td><td><span title="AndroidX.appCompat.resources
androidx.appcompat:appcompat-resources:_
version.androidx.appcompat" style="text-decoration: underline;" >resources</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.appSearch</b></td><td><span title="AndroidX.appSearch.builtInTypes
androidx.appsearch:appsearch-builtin-types:_
version.androidx.appsearch" style="text-decoration: underline;" >builtInTypes</span>&nbsp; - <span title="AndroidX.appSearch.compiler
androidx.appsearch:appsearch-compiler:_
version.androidx.appsearch" style="text-decoration: underline;" >compiler</span>&nbsp; - <span title="AndroidX.appSearch.localStorage
androidx.appsearch:appsearch-local-storage:_
version.androidx.appsearch" style="text-decoration: underline;" >localStorage</span>&nbsp; - <span title="AndroidX.appSearch.platformStorage
androidx.appsearch:appsearch-platform-storage:_
version.androidx.appsearch" style="text-decoration: underline;" >platformStorage</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.archCore</b></td><td><span title="AndroidX.archCore.common
androidx.arch.core:core-common:_
version.androidx.arch.core" style="text-decoration: underline;" >common</span>&nbsp; - <span title="AndroidX.archCore.runtime
androidx.arch.core:core-runtime:_
version.androidx.arch.core" style="text-decoration: underline;" >runtime</span>&nbsp; - <span title="AndroidX.archCore.testing
androidx.arch.core:core-testing:_
version.androidx.arch.core" style="text-decoration: underline;" >testing</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.benchmark</b></td><td><span title="AndroidX.benchmark.common
androidx.benchmark:benchmark-common:_
version.androidx.benchmark" style="text-decoration: underline;" >common</span>&nbsp; - <span title="AndroidX.benchmark.gradlePlugin
androidx.benchmark:benchmark-gradle-plugin:_
version.androidx.benchmark" style="text-decoration: underline;" >gradlePlugin</span>&nbsp; - <span title="AndroidX.benchmark.junit4
androidx.benchmark:benchmark-junit4:_
version.androidx.benchmark" style="text-decoration: underline;" >junit4</span>&nbsp; - <span title="AndroidX.benchmark.macroJunit4
androidx.benchmark:benchmark-macro-junit4:_
version.androidx.benchmark" style="text-decoration: underline;" >macroJunit4</span>&nbsp; - <span title="AndroidX.benchmark.macro
androidx.benchmark:benchmark-macro:_
version.androidx.benchmark" style="text-decoration: underline;" >macro</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.biometric</b></td><td><span title="AndroidX.biometric.ktx
androidx.biometric:biometric-ktx:_
version.androidx.biometric-ktx" style="text-decoration: underline;" >ktx</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.camera</b></td><td><span title="AndroidX.camera.camera2
androidx.camera:camera-camera2:_
version.androidx.camera" style="text-decoration: underline;" >camera2</span>&nbsp; - <span title="AndroidX.camera.core
androidx.camera:camera-core:_
version.androidx.camera" style="text-decoration: underline;" >core</span>&nbsp; - <span title="AndroidX.camera.extensions
androidx.camera:camera-extensions:_
version.androidx.camera.extensions" style="text-decoration: underline;" >extensions</span>&nbsp; - <span title="AndroidX.camera.lifecycle
androidx.camera:camera-lifecycle:_
version.androidx.camera" style="text-decoration: underline;" >lifecycle</span>&nbsp; - <span title="AndroidX.camera.mlKitVision
androidx.camera:camera-mlkit-vision:_
version.androidx.camera" style="text-decoration: underline;" >mlKitVision</span>&nbsp; - <span title="AndroidX.camera.video
androidx.camera:camera-video:_
version.androidx.camera" style="text-decoration: underline;" >video</span>&nbsp; - <span title="AndroidX.camera.view
androidx.camera:camera-view:_
version.androidx.camera.view" style="text-decoration: underline;" >view</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.carApp</b></td><td><span title="AndroidX.carApp.automotive
androidx.car.app:app-automotive:_
version.androidx.car.app" style="text-decoration: underline;" >automotive</span>&nbsp; - <span title="AndroidX.carApp.projected
androidx.car.app:app-projected:_
version.androidx.car.app" style="text-decoration: underline;" >projected</span>&nbsp; - <span title="AndroidX.carApp.testing
androidx.car.app:app-testing:_
version.androidx.car.app-testing" style="text-decoration: underline;" >testing</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.collection</b></td><td><span title="AndroidX.collection.ktx
androidx.collection:collection-ktx:_
version.androidx.collection" style="text-decoration: underline;" >ktx</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.compose</b></td><td><span title="AndroidX.compose.animation
androidx.compose.animation:animation:_
version.androidx.compose.animation" style="text-decoration: underline;" >animation</span>&nbsp; - <span title="AndroidX.compose.compiler
androidx.compose.compiler:compiler:_
version.androidx.compose.compiler" style="text-decoration: underline;" >compiler</span>&nbsp; - <span title="AndroidX.compose.foundation
androidx.compose.foundation:foundation:_
version.androidx.compose.foundation" style="text-decoration: underline;" >foundation</span>&nbsp; - <span title="AndroidX.compose.material
androidx.compose.material:material:_
version.androidx.compose.material" style="text-decoration: underline;" >material</span>&nbsp; - <span title="AndroidX.compose.material3
androidx.compose.material3:material3:_
version.androidx.compose.material3" style="text-decoration: underline;" >material3</span>&nbsp; - <span title="AndroidX.compose.runtime
androidx.compose.runtime:runtime:_
version.androidx.compose.runtime" style="text-decoration: underline;" >runtime</span>&nbsp; - <span title="AndroidX.compose.ui
androidx.compose.ui:ui:_
version.androidx.compose.ui" style="text-decoration: underline;" >ui</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.compose.animation</b></td><td><span title="AndroidX.compose.animation.core
androidx.compose.animation:animation-core:_
version.androidx.compose.animation" style="text-decoration: underline;" >core</span>&nbsp; - <span title="AndroidX.compose.animation.graphics
androidx.compose.animation:animation-graphics:_
version.androidx.compose.animation" style="text-decoration: underline;" >graphics</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.compose.foundation</b></td><td><span title="AndroidX.compose.foundation.layout
androidx.compose.foundation:foundation-layout:_
version.androidx.compose.foundation" style="text-decoration: underline;" >layout</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.compose.material</b></td><td><span title="AndroidX.compose.material.ripple
androidx.compose.material:material-ripple:_
version.androidx.compose.material" style="text-decoration: underline;" >ripple</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.compose.material.icons</b></td><td><span title="AndroidX.compose.material.icons.core
androidx.compose.material:material-icons-core:_
version.androidx.compose.material" style="text-decoration: underline;" >core</span>&nbsp; - <span title="AndroidX.compose.material.icons.extended
androidx.compose.material:material-icons-extended:_
version.androidx.compose.material" style="text-decoration: underline;" >extended</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.compose.material3</b></td><td><span title="AndroidX.compose.material3.windowSizeClass
androidx.compose.material3:material3-window-size-class:_
version.androidx.compose.material3" style="text-decoration: underline;" >windowSizeClass</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.compose.runtime</b></td><td><span title="AndroidX.compose.runtime.dispatch
androidx.compose.runtime:runtime-dispatch:_
version.androidx.compose.runtime" style="text-decoration: underline;" >dispatch</span>&nbsp; - <span title="AndroidX.compose.runtime.liveData
androidx.compose.runtime:runtime-livedata:_
version.androidx.compose.runtime" style="text-decoration: underline;" >liveData</span>&nbsp; - <span title="AndroidX.compose.runtime.rxJava2
androidx.compose.runtime:runtime-rxjava2:_
version.androidx.compose.runtime" style="text-decoration: underline;" >rxJava2</span>&nbsp; - <span title="AndroidX.compose.runtime.rxJava3
androidx.compose.runtime:runtime-rxjava3:_
version.androidx.compose.runtime" style="text-decoration: underline;" >rxJava3</span>&nbsp; - <span title="AndroidX.compose.runtime.saveable
androidx.compose.runtime:runtime-saveable:_
version.androidx.compose.runtime" style="text-decoration: underline;" >saveable</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.compose.ui</b></td><td><span title="AndroidX.compose.ui.geometry
androidx.compose.ui:ui-geometry:_
version.androidx.compose.ui" style="text-decoration: underline;" >geometry</span>&nbsp; - <span title="AndroidX.compose.ui.graphics
androidx.compose.ui:ui-graphics:_
version.androidx.compose.ui" style="text-decoration: underline;" >graphics</span>&nbsp; - <span title="AndroidX.compose.ui.testJunit4
androidx.compose.ui:ui-test-junit4:_
version.androidx.compose.ui" style="text-decoration: underline;" >testJunit4</span>&nbsp; - <span title="AndroidX.compose.ui.testManifest
androidx.compose.ui:ui-test-manifest:_
version.androidx.compose.ui" style="text-decoration: underline;" >testManifest</span>&nbsp; - <span title="AndroidX.compose.ui.test
androidx.compose.ui:ui-test:_
version.androidx.compose.ui" style="text-decoration: underline;" >test</span>&nbsp; - <span title="AndroidX.compose.ui.text
androidx.compose.ui:ui-text:_
version.androidx.compose.ui" style="text-decoration: underline;" >text</span>&nbsp; - <span title="AndroidX.compose.ui.toolingData
androidx.compose.ui:ui-tooling-data:_
version.androidx.compose.ui" style="text-decoration: underline;" >toolingData</span>&nbsp; - <span title="AndroidX.compose.ui.toolingPreview
androidx.compose.ui:ui-tooling-preview:_
version.androidx.compose.ui" style="text-decoration: underline;" >toolingPreview</span>&nbsp; - <span title="AndroidX.compose.ui.tooling
androidx.compose.ui:ui-tooling:_
version.androidx.compose.ui" style="text-decoration: underline;" >tooling</span>&nbsp; - <span title="AndroidX.compose.ui.unit
androidx.compose.ui:ui-unit:_
version.androidx.compose.ui" style="text-decoration: underline;" >unit</span>&nbsp; - <span title="AndroidX.compose.ui.util
androidx.compose.ui:ui-util:_
version.androidx.compose.ui" style="text-decoration: underline;" >util</span>&nbsp; - <span title="AndroidX.compose.ui.viewBinding
androidx.compose.ui:ui-viewbinding:_
version.androidx.compose.ui" style="text-decoration: underline;" >viewBinding</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.compose.ui.text</b></td><td><span title="AndroidX.compose.ui.text.googleFonts
androidx.compose.ui:ui-text-google-fonts:_
version.androidx.compose.ui" style="text-decoration: underline;" >googleFonts</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.concurrent</b></td><td><span title="AndroidX.concurrent.futuresKtx
androidx.concurrent:concurrent-futures-ktx:_
version.androidx.concurrent" style="text-decoration: underline;" >futuresKtx</span>&nbsp; - <span title="AndroidX.concurrent.futures
androidx.concurrent:concurrent-futures:_
version.androidx.concurrent" style="text-decoration: underline;" >futures</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.constraintLayout</b></td><td><span title="AndroidX.constraintLayout.compose
androidx.constraintlayout:constraintlayout-compose:_
version.androidx.constraintlayout-compose" style="text-decoration: underline;" >compose</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.core</b></td><td><span title="AndroidX.core.animationTesting
androidx.core:core-animation-testing:_
version.androidx.core-animation" style="text-decoration: underline;" >animationTesting</span>&nbsp; - <span title="AndroidX.core.animation
androidx.core:core-animation:_
version.androidx.core-animation" style="text-decoration: underline;" >animation</span>&nbsp; - <span title="AndroidX.core.googleShortcuts
androidx.core:core-google-shortcuts:_
version.androidx.core-google-shortcuts" style="text-decoration: underline;" >googleShortcuts</span>&nbsp; - <span title="AndroidX.core.ktx
androidx.core:core-ktx:_
version.androidx.core" style="text-decoration: underline;" >ktx</span>&nbsp; - <span title="AndroidX.core.performance
androidx.core:core-performance:_
version.androidx.core-performance" style="text-decoration: underline;" >performance</span>&nbsp; - <span title="AndroidX.core.remoteViews
androidx.core:core-remoteviews:_
version.androidx.core" style="text-decoration: underline;" >remoteViews</span>&nbsp; - <span title="AndroidX.core.role
androidx.core:core-role:_
version.androidx.core-role" style="text-decoration: underline;" >role</span>&nbsp; - <span title="AndroidX.core.splashscreen
androidx.core:core-splashscreen:_
version.androidx.core-splashscreen" style="text-decoration: underline;" >splashscreen</span>&nbsp; - <span title="AndroidX.core.uwb
androidx.core.uwb:uwb:_
version.androidx.core.uwb" style="text-decoration: underline;" >uwb</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.core.uwb</b></td><td><span title="AndroidX.core.uwb.rxJava3
androidx.core.uwb:uwb-rxjava3:_
version.androidx.core.uwb" style="text-decoration: underline;" >rxJava3</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.customView</b></td><td><span title="AndroidX.customView.poolingContainer
androidx.customview:customview-poolingcontainer:_
version.androidx.customview-poolingcontainer" style="text-decoration: underline;" >poolingContainer</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.dataStore</b></td><td><span title="AndroidX.dataStore.core
androidx.datastore:datastore-core:_
version.androidx.datastore" style="text-decoration: underline;" >core</span>&nbsp; - <span title="AndroidX.dataStore.preferences
androidx.datastore:datastore-preferences:_
version.androidx.datastore" style="text-decoration: underline;" >preferences</span>&nbsp; - <span title="AndroidX.dataStore.rxJava2
androidx.datastore:datastore-rxJava2:_
version.androidx.datastore" style="text-decoration: underline;" >rxJava2</span>&nbsp; - <span title="AndroidX.dataStore.rxJava3
androidx.datastore:datastore-rxJava3:_
version.androidx.datastore" style="text-decoration: underline;" >rxJava3</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.dataStore.preferences</b></td><td><span title="AndroidX.dataStore.preferences.core
androidx.datastore:datastore-preferences-core:_
version.androidx.datastore" style="text-decoration: underline;" >core</span>&nbsp; - <span title="AndroidX.dataStore.preferences.rxJava2
androidx.datastore:datastore-preferences-rxJava2:_
version.androidx.datastore" style="text-decoration: underline;" >rxJava2</span>&nbsp; - <span title="AndroidX.dataStore.preferences.rxJava3
androidx.datastore:datastore-preferences-rxJava3:_
version.androidx.datastore" style="text-decoration: underline;" >rxJava3</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.dynamicAnimation</b></td><td><span title="AndroidX.dynamicAnimation.ktx
androidx.dynamicanimation:dynamicanimation-ktx:_
version.androidx.dynamicanimation-ktx" style="text-decoration: underline;" >ktx</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.emoji</b></td><td><span title="AndroidX.emoji.appCompat
androidx.emoji:emoji-appcompat:_
version.androidx.emoji" style="text-decoration: underline;" >appCompat</span>&nbsp; - <span title="AndroidX.emoji.bundled
androidx.emoji:emoji-bundled:_
version.androidx.emoji" style="text-decoration: underline;" >bundled</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.emoji2</b></td><td><span title="AndroidX.emoji2.bundled
androidx.emoji2:emoji2-bundled:_
version.androidx.emoji2" style="text-decoration: underline;" >bundled</span>&nbsp; - <span title="AndroidX.emoji2.viewsHelper
androidx.emoji2:emoji2-views-helper:_
version.androidx.emoji2" style="text-decoration: underline;" >viewsHelper</span>&nbsp; - <span title="AndroidX.emoji2.views
androidx.emoji2:emoji2-views:_
version.androidx.emoji2" style="text-decoration: underline;" >views</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.enterprise</b></td><td><span title="AndroidX.enterprise.feedbackTesting
androidx.enterprise:enterprise-feedback-testing:_
version.androidx.enterprise" style="text-decoration: underline;" >feedbackTesting</span>&nbsp; - <span title="AndroidX.enterprise.feedback
androidx.enterprise:enterprise-feedback:_
version.androidx.enterprise" style="text-decoration: underline;" >feedback</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.fragment</b></td><td><span title="AndroidX.fragment.ktx
androidx.fragment:fragment-ktx:_
version.androidx.fragment" style="text-decoration: underline;" >ktx</span>&nbsp; - <span title="AndroidX.fragment.testing
androidx.fragment:fragment-testing:_
version.androidx.fragment" style="text-decoration: underline;" >testing</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.games</b></td><td><span title="AndroidX.games.activity
androidx.games:games-activity:_
version.androidx.games-activity" style="text-decoration: underline;" >activity</span>&nbsp; - <span title="AndroidX.games.controller
androidx.games:games-controller:_
version.androidx.games-controller" style="text-decoration: underline;" >controller</span>&nbsp; - <span title="AndroidX.games.framePacing
androidx.games:games-frame-pacing:_
version.androidx.games-frame-pacing" style="text-decoration: underline;" >framePacing</span>&nbsp; - <span title="AndroidX.games.performanceTuner
androidx.games:games-performance-tuner:_
version.androidx.games-performance-tuner" style="text-decoration: underline;" >performanceTuner</span>&nbsp; - <span title="AndroidX.games.textInput
androidx.games:games-text-input:_
version.androidx.games-text-input" style="text-decoration: underline;" >textInput</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.glance</b></td><td><span title="AndroidX.glance.appWidget
androidx.glance:glance-appwidget:_
version.androidx.glance" style="text-decoration: underline;" >appWidget</span>&nbsp; - <span title="AndroidX.glance.wearTiles
androidx.glance:glance-wear-tiles:_
version.androidx.glance" style="text-decoration: underline;" >wearTiles</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.health</b></td><td><span title="AndroidX.health.servicesClient
androidx.health:health-services-client:_
version.androidx.health-services-client" style="text-decoration: underline;" >servicesClient</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.health.connect</b></td><td><span title="AndroidX.health.connect.client
androidx.health.connect:connect-client:_
version.androidx.health.connect" style="text-decoration: underline;" >client</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.hilt</b></td><td><span title="AndroidX.hilt.compiler
androidx.hilt:hilt-compiler:_
version.androidx.hilt" style="text-decoration: underline;" >compiler</span>&nbsp; - <span title="AndroidX.hilt.navigationCompose
androidx.hilt:hilt-navigation-compose:_
version.androidx.hilt-navigation-compose" style="text-decoration: underline;" >navigationCompose</span>&nbsp; - <span title="AndroidX.hilt.navigationFragment
androidx.hilt:hilt-navigation-fragment:_
version.androidx.hilt" style="text-decoration: underline;" >navigationFragment</span>&nbsp; - <span title="AndroidX.hilt.work
androidx.hilt:hilt-work:_
version.androidx.hilt" style="text-decoration: underline;" >work</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.leanback</b></td><td><span title="AndroidX.leanback.grid
androidx.leanback:leanback-grid:_
version.androidx.leanback-grid" style="text-decoration: underline;" >grid</span>&nbsp; - <span title="AndroidX.leanback.paging
androidx.leanback:leanback-paging:_
version.androidx.leanback-paging" style="text-decoration: underline;" >paging</span>&nbsp; - <span title="AndroidX.leanback.preference
androidx.leanback:leanback-preference:_
version.androidx.leanback" style="text-decoration: underline;" >preference</span>&nbsp; - <span title="AndroidX.leanback.tab
androidx.leanback:leanback-tab:_
version.androidx.leanback-tab" style="text-decoration: underline;" >tab</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.lifecycle</b></td><td><span title="AndroidX.lifecycle.commonJava8
androidx.lifecycle:lifecycle-common-java8:_
version.androidx.lifecycle" style="text-decoration: underline;" >commonJava8</span>&nbsp; - <span title="AndroidX.lifecycle.common
androidx.lifecycle:lifecycle-common:_
version.androidx.lifecycle" style="text-decoration: underline;" >common</span>&nbsp; - <span title="AndroidX.lifecycle.compiler
androidx.lifecycle:lifecycle-compiler:_
version.androidx.lifecycle" style="text-decoration: underline;" >compiler</span>&nbsp; - <span title="AndroidX.lifecycle.extensions
androidx.lifecycle:lifecycle-extensions:_
version.androidx.lifecycle-extensions" style="text-decoration: underline;" >extensions</span>&nbsp; - <span title="AndroidX.lifecycle.liveDataKtx
androidx.lifecycle:lifecycle-livedata-ktx:_
version.androidx.lifecycle" style="text-decoration: underline;" >liveDataKtx</span>&nbsp; - <span title="AndroidX.lifecycle.liveData
androidx.lifecycle:lifecycle-livedata:_
version.androidx.lifecycle" style="text-decoration: underline;" >liveData</span>&nbsp; - <span title="AndroidX.lifecycle.process
androidx.lifecycle:lifecycle-process:_
version.androidx.lifecycle" style="text-decoration: underline;" >process</span>&nbsp; - <span title="AndroidX.lifecycle.reactiveStreamsKtx
androidx.lifecycle:lifecycle-reactivestreams-ktx:_
version.androidx.lifecycle" style="text-decoration: underline;" >reactiveStreamsKtx</span>&nbsp; - <span title="AndroidX.lifecycle.reactiveStreams
androidx.lifecycle:lifecycle-reactivestreams:_
version.androidx.lifecycle" style="text-decoration: underline;" >reactiveStreams</span>&nbsp; - <span title="AndroidX.lifecycle.runtime
androidx.lifecycle:lifecycle-runtime:_
version.androidx.lifecycle" style="text-decoration: underline;" >runtime</span>&nbsp; - <span title="AndroidX.lifecycle.service
androidx.lifecycle:lifecycle-service:_
version.androidx.lifecycle" style="text-decoration: underline;" >service</span>&nbsp; - <span title="AndroidX.lifecycle.viewModelCompose
androidx.lifecycle:lifecycle-viewmodel-compose:_
version.androidx.lifecycle-viewmodel-compose" style="text-decoration: underline;" >viewModelCompose</span>&nbsp; - <span title="AndroidX.lifecycle.viewModelKtx
androidx.lifecycle:lifecycle-viewmodel-ktx:_
version.androidx.lifecycle" style="text-decoration: underline;" >viewModelKtx</span>&nbsp; - <span title="AndroidX.lifecycle.viewModelSavedState
androidx.lifecycle:lifecycle-viewmodel-savedstate:_
version.androidx.lifecycle" style="text-decoration: underline;" >viewModelSavedState</span>&nbsp; - <span title="AndroidX.lifecycle.viewModel
androidx.lifecycle:lifecycle-viewmodel:_
version.androidx.lifecycle" style="text-decoration: underline;" >viewModel</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.lifecycle.runtime</b></td><td><span title="AndroidX.lifecycle.runtime.compose
androidx.lifecycle:lifecycle-runtime-compose:_
version.androidx.lifecycle" style="text-decoration: underline;" >compose</span>&nbsp; - <span title="AndroidX.lifecycle.runtime.ktx
androidx.lifecycle:lifecycle-runtime-ktx:_
version.androidx.lifecycle" style="text-decoration: underline;" >ktx</span>&nbsp; - <span title="AndroidX.lifecycle.runtime.testing
androidx.lifecycle:lifecycle-runtime-testing:_
version.androidx.lifecycle" style="text-decoration: underline;" >testing</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.media2</b></td><td><span title="AndroidX.media2.common
androidx.media2:media2-common:_
version.androidx.media2" style="text-decoration: underline;" >common</span>&nbsp; - <span title="AndroidX.media2.exoplayer
androidx.media2:media2-exoplayer:_
version.androidx.media2" style="text-decoration: underline;" >exoplayer</span>&nbsp; - <span title="AndroidX.media2.player
androidx.media2:media2-player:_
version.androidx.media2" style="text-decoration: underline;" >player</span>&nbsp; - <span title="AndroidX.media2.session
androidx.media2:media2-session:_
version.androidx.media2" style="text-decoration: underline;" >session</span>&nbsp; - <span title="AndroidX.media2.widget
androidx.media2:media2-widget:_
version.androidx.media2" style="text-decoration: underline;" >widget</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.media3</b></td><td><span title="AndroidX.media3.cast
androidx.media3:media3-cast:_
version.androidx.media3" style="text-decoration: underline;" >cast</span>&nbsp; - <span title="AndroidX.media3.common
androidx.media3:media3-common:_
version.androidx.media3" style="text-decoration: underline;" >common</span>&nbsp; - <span title="AndroidX.media3.database
androidx.media3:media3-database:_
version.androidx.media3" style="text-decoration: underline;" >database</span>&nbsp; - <span title="AndroidX.media3.datasource
androidx.media3:media3-datasource:_
version.androidx.media3" style="text-decoration: underline;" >datasource</span>&nbsp; - <span title="AndroidX.media3.decoder
androidx.media3:media3-decoder:_
version.androidx.media3" style="text-decoration: underline;" >decoder</span>&nbsp; - <span title="AndroidX.media3.exoPlayer
androidx.media3:media3-exoplayer:_
version.androidx.media3" style="text-decoration: underline;" >exoPlayer</span>&nbsp; - <span title="AndroidX.media3.extractor
androidx.media3:media3-extractor:_
version.androidx.media3" style="text-decoration: underline;" >extractor</span>&nbsp; - <span title="AndroidX.media3.session
androidx.media3:media3-session:_
version.androidx.media3" style="text-decoration: underline;" >session</span>&nbsp; - <span title="AndroidX.media3.testUtils
androidx.media3:media3-test-utils:_
version.androidx.media3" style="text-decoration: underline;" >testUtils</span>&nbsp; - <span title="AndroidX.media3.transformer
androidx.media3:media3-transformer:_
version.androidx.media3" style="text-decoration: underline;" >transformer</span>&nbsp; - <span title="AndroidX.media3.ui
androidx.media3:media3-ui:_
version.androidx.media3" style="text-decoration: underline;" >ui</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.media3.dataSource</b></td><td><span title="AndroidX.media3.dataSource.cronet
androidx.media3:media3-datasource-cronet:_
version.androidx.media3" style="text-decoration: underline;" >cronet</span>&nbsp; - <span title="AndroidX.media3.dataSource.okhttp
androidx.media3:media3-datasource-okhttp:_
version.androidx.media3" style="text-decoration: underline;" >okhttp</span>&nbsp; - <span title="AndroidX.media3.dataSource.rtmp
androidx.media3:media3-datasource-rtmp:_
version.androidx.media3" style="text-decoration: underline;" >rtmp</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.media3.exoPlayer</b></td><td><span title="AndroidX.media3.exoPlayer.dash
androidx.media3:media3-exoplayer-dash:_
version.androidx.media3" style="text-decoration: underline;" >dash</span>&nbsp; - <span title="AndroidX.media3.exoPlayer.hls
androidx.media3:media3-exoplayer-hls:_
version.androidx.media3" style="text-decoration: underline;" >hls</span>&nbsp; - <span title="AndroidX.media3.exoPlayer.ima
androidx.media3:media3-exoplayer-ima:_
version.androidx.media3" style="text-decoration: underline;" >ima</span>&nbsp; - <span title="AndroidX.media3.exoPlayer.rtsp
androidx.media3:media3-exoplayer-rtsp:_
version.androidx.media3" style="text-decoration: underline;" >rtsp</span>&nbsp; - <span title="AndroidX.media3.exoPlayer.workmanager
androidx.media3:media3-exoplayer-workmanager:_
version.androidx.media3" style="text-decoration: underline;" >workmanager</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.media3.testUtils</b></td><td><span title="AndroidX.media3.testUtils.robolectric
androidx.media3:media3-test-utils-robolectric:_
version.androidx.media3" style="text-decoration: underline;" >robolectric</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.media3.ui</b></td><td><span title="AndroidX.media3.ui.leanback
androidx.media3:media3-ui-leanback:_
version.androidx.media3" style="text-decoration: underline;" >leanback</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.metrics</b></td><td><span title="AndroidX.metrics.performance
androidx.metrics:metrics-performance:_
version.androidx.metrics" style="text-decoration: underline;" >performance</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.multidex</b></td><td><span title="AndroidX.multidex.instrumentation
androidx.multidex:multidex-instrumentation:_
version.androidx.multidex" style="text-decoration: underline;" >instrumentation</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.navigation</b></td><td><span title="AndroidX.navigation.commonKtx
androidx.navigation:navigation-common-ktx:_
version.androidx.navigation" style="text-decoration: underline;" >commonKtx</span>&nbsp; - <span title="AndroidX.navigation.common
androidx.navigation:navigation-common:_
version.androidx.navigation" style="text-decoration: underline;" >common</span>&nbsp; - <span title="AndroidX.navigation.compose
androidx.navigation:navigation-compose:_
version.androidx.navigation" style="text-decoration: underline;" >compose</span>&nbsp; - <span title="AndroidX.navigation.dynamicFeaturesFragment
androidx.navigation:navigation-dynamic-features-fragment:_
version.androidx.navigation" style="text-decoration: underline;" >dynamicFeaturesFragment</span>&nbsp; - <span title="AndroidX.navigation.fragmentKtx
androidx.navigation:navigation-fragment-ktx:_
version.androidx.navigation" style="text-decoration: underline;" >fragmentKtx</span>&nbsp; - <span title="AndroidX.navigation.fragment
androidx.navigation:navigation-fragment:_
version.androidx.navigation" style="text-decoration: underline;" >fragment</span>&nbsp; - <span title="AndroidX.navigation.runtimeKtx
androidx.navigation:navigation-runtime-ktx:_
version.androidx.navigation" style="text-decoration: underline;" >runtimeKtx</span>&nbsp; - <span title="AndroidX.navigation.runtime
androidx.navigation:navigation-runtime:_
version.androidx.navigation" style="text-decoration: underline;" >runtime</span>&nbsp; - <span title="AndroidX.navigation.safeArgsGenerator
androidx.navigation:navigation-safe-args-generator:_
version.androidx.navigation" style="text-decoration: underline;" >safeArgsGenerator</span>&nbsp; - <span title="AndroidX.navigation.safeArgsGradlePlugin
androidx.navigation:navigation-safe-args-gradle-plugin:_
version.androidx.navigation" style="text-decoration: underline;" >safeArgsGradlePlugin</span>&nbsp; - <span title="AndroidX.navigation.testing
androidx.navigation:navigation-testing:_
version.androidx.navigation" style="text-decoration: underline;" >testing</span>&nbsp; - <span title="AndroidX.navigation.uiKtx
androidx.navigation:navigation-ui-ktx:_
version.androidx.navigation" style="text-decoration: underline;" >uiKtx</span>&nbsp; - <span title="AndroidX.navigation.ui
androidx.navigation:navigation-ui:_
version.androidx.navigation" style="text-decoration: underline;" >ui</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.paging</b></td><td><span title="AndroidX.paging.commonKtx
androidx.paging:paging-common-ktx:_
version.androidx.paging" style="text-decoration: underline;" >commonKtx</span>&nbsp; - <span title="AndroidX.paging.common
androidx.paging:paging-common:_
version.androidx.paging" style="text-decoration: underline;" >common</span>&nbsp; - <span title="AndroidX.paging.compose
androidx.paging:paging-compose:_
version.androidx.paging-compose" style="text-decoration: underline;" >compose</span>&nbsp; - <span title="AndroidX.paging.guava
androidx.paging:paging-guava:_
version.androidx.paging" style="text-decoration: underline;" >guava</span>&nbsp; - <span title="AndroidX.paging.runtimeKtx
androidx.paging:paging-runtime-ktx:_
version.androidx.paging" style="text-decoration: underline;" >runtimeKtx</span>&nbsp; - <span title="AndroidX.paging.runtime
androidx.paging:paging-runtime:_
version.androidx.paging" style="text-decoration: underline;" >runtime</span>&nbsp; - <span title="AndroidX.paging.rxJava2Ktx
androidx.paging:paging-rxjava2-ktx:_
version.androidx.paging" style="text-decoration: underline;" >rxJava2Ktx</span>&nbsp; - <span title="AndroidX.paging.rxJava2
androidx.paging:paging-rxjava2:_
version.androidx.paging" style="text-decoration: underline;" >rxJava2</span>&nbsp; - <span title="AndroidX.paging.rxJava3
androidx.paging:paging-rxjava3:_
version.androidx.paging" style="text-decoration: underline;" >rxJava3</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.palette</b></td><td><span title="AndroidX.palette.ktx
androidx.palette:palette-ktx:_
version.androidx.palette" style="text-decoration: underline;" >ktx</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.preference</b></td><td><span title="AndroidX.preference.ktx
androidx.preference:preference-ktx:_
version.androidx.preference" style="text-decoration: underline;" >ktx</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.recyclerView</b></td><td><span title="AndroidX.recyclerView.selection
androidx.recyclerview:recyclerview-selection:_
version.androidx.recyclerview-selection" style="text-decoration: underline;" >selection</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.remoteCallback</b></td><td><span title="AndroidX.remoteCallback.processor
androidx.remotecallback:remotecallback-processor:_
version.androidx.remotecallback" style="text-decoration: underline;" >processor</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.room</b></td><td><span title="AndroidX.room.common
androidx.room:room-common:_
version.androidx.room" style="text-decoration: underline;" >common</span>&nbsp; - <span title="AndroidX.room.compiler
androidx.room:room-compiler:_
version.androidx.room" style="text-decoration: underline;" >compiler</span>&nbsp; - <span title="AndroidX.room.guava
androidx.room:room-guava:_
version.androidx.room" style="text-decoration: underline;" >guava</span>&nbsp; - <span title="AndroidX.room.ktx
androidx.room:room-ktx:_
version.androidx.room" style="text-decoration: underline;" >ktx</span>&nbsp; - <span title="AndroidX.room.paging
androidx.room:room-paging:_
version.androidx.room" style="text-decoration: underline;" >paging</span>&nbsp; - <span title="AndroidX.room.runtime
androidx.room:room-runtime:_
version.androidx.room" style="text-decoration: underline;" >runtime</span>&nbsp; - <span title="AndroidX.room.rxJava2
androidx.room:room-rxjava2:_
version.androidx.room" style="text-decoration: underline;" >rxJava2</span>&nbsp; - <span title="AndroidX.room.rxJava3
androidx.room:room-rxjava3:_
version.androidx.room" style="text-decoration: underline;" >rxJava3</span>&nbsp; - <span title="AndroidX.room.testing
androidx.room:room-testing:_
version.androidx.room" style="text-decoration: underline;" >testing</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.room.paging</b></td><td><span title="AndroidX.room.paging.guava
androidx.room:room-paging-guava:_
version.androidx.room" style="text-decoration: underline;" >guava</span>&nbsp; - <span title="AndroidX.room.paging.rxJava2
androidx.room:room-paging-rxjava2:_
version.androidx.room" style="text-decoration: underline;" >rxJava2</span>&nbsp; - <span title="AndroidX.room.paging.rxJava3
androidx.room:room-paging-rxjava3:_
version.androidx.room" style="text-decoration: underline;" >rxJava3</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.savedState</b></td><td><span title="AndroidX.savedState.ktx
androidx.savedstate:savedstate-ktx:_
version.androidx.savedstate" style="text-decoration: underline;" >ktx</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.security</b></td><td><span title="AndroidX.security.appAuthenticatorTesting
androidx.security:security-app-authenticator-testing:_
version.androidx.security-app-authenticator-testing" style="text-decoration: underline;" >appAuthenticatorTesting</span>&nbsp; - <span title="AndroidX.security.appAuthenticator
androidx.security:security-app-authenticator:_
version.androidx.security-app-authenticator" style="text-decoration: underline;" >appAuthenticator</span>&nbsp; - <span title="AndroidX.security.cryptoKtx
androidx.security:security-crypto-ktx:_
version.androidx.security-crypto" style="text-decoration: underline;" >cryptoKtx</span>&nbsp; - <span title="AndroidX.security.crypto
androidx.security:security-crypto:_
version.androidx.security-crypto" style="text-decoration: underline;" >crypto</span>&nbsp; - <span title="AndroidX.security.identityCredential
androidx.security:security-identity-credential:_
version.androidx.security-identity-credential" style="text-decoration: underline;" >identityCredential</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.slice</b></td><td><span title="AndroidX.slice.buildersKtx
androidx.slice:slice-builders-ktx:_
version.androidx.slice-builders-ktx" style="text-decoration: underline;" >buildersKtx</span>&nbsp; - <span title="AndroidX.slice.builders
androidx.slice:slice-builders:_
version.androidx.slice" style="text-decoration: underline;" >builders</span>&nbsp; - <span title="AndroidX.slice.core
androidx.slice:slice-core:_
version.androidx.slice" style="text-decoration: underline;" >core</span>&nbsp; - <span title="AndroidX.slice.view
androidx.slice:slice-view:_
version.androidx.slice" style="text-decoration: underline;" >view</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.sqlite</b></td><td><span title="AndroidX.sqlite.framework
androidx.sqlite:sqlite-framework:_
version.androidx.sqlite" style="text-decoration: underline;" >framework</span>&nbsp; - <span title="AndroidX.sqlite.ktx
androidx.sqlite:sqlite-ktx:_
version.androidx.sqlite" style="text-decoration: underline;" >ktx</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.startup</b></td><td><span title="AndroidX.startup.runtime
androidx.startup:startup-runtime:_
version.androidx.startup" style="text-decoration: underline;" >runtime</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.test</b></td><td><span title="AndroidX.test.coreKtx
androidx.test:core-ktx:_
version.androidx.test.core" style="text-decoration: underline;" >coreKtx</span>&nbsp; - <span title="AndroidX.test.core
androidx.test:core:_
version.androidx.test.core" style="text-decoration: underline;" >core</span>&nbsp; - <span title="AndroidX.test.monitor
androidx.test:monitor:_
version.androidx.test.monitor" style="text-decoration: underline;" >monitor</span>&nbsp; - <span title="AndroidX.test.orchestrator
androidx.test:orchestrator:_
version.androidx.test.orchestrator" style="text-decoration: underline;" >orchestrator</span>&nbsp; - <span title="AndroidX.test.rules
androidx.test:rules:_
version.androidx.test.rules" style="text-decoration: underline;" >rules</span>&nbsp; - <span title="AndroidX.test.runner
androidx.test:runner:_
version.androidx.test.runner" style="text-decoration: underline;" >runner</span>&nbsp; - <span title="AndroidX.test.services
androidx.test.services:test-services:_
version.androidx.test.services" style="text-decoration: underline;" >services</span>&nbsp; - <span title="AndroidX.test.uiAutomator
androidx.test.uiautomator:uiautomator:_
version.androidx.test.uiautomator" style="text-decoration: underline;" >uiAutomator</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.test.espresso</b></td><td><span title="AndroidX.test.espresso.accessibility
androidx.test.espresso:espresso-accessibility:_
version.androidx.test.espresso" style="text-decoration: underline;" >accessibility</span>&nbsp; - <span title="AndroidX.test.espresso.contrib
androidx.test.espresso:espresso-contrib:_
version.androidx.test.espresso" style="text-decoration: underline;" >contrib</span>&nbsp; - <span title="AndroidX.test.espresso.core
androidx.test.espresso:espresso-core:_
version.androidx.test.espresso" style="text-decoration: underline;" >core</span>&nbsp; - <span title="AndroidX.test.espresso.idlingResource
androidx.test.espresso:espresso-idling-resource:_
version.androidx.test.espresso" style="text-decoration: underline;" >idlingResource</span>&nbsp; - <span title="AndroidX.test.espresso.intents
androidx.test.espresso:espresso-intents:_
version.androidx.test.espresso" style="text-decoration: underline;" >intents</span>&nbsp; - <span title="AndroidX.test.espresso.remote
androidx.test.espresso:espresso-remote:_
version.androidx.test.espresso" style="text-decoration: underline;" >remote</span>&nbsp; - <span title="AndroidX.test.espresso.web
androidx.test.espresso:espresso-web:_
version.androidx.test.espresso" style="text-decoration: underline;" >web</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.test.espresso.idling</b></td><td><span title="AndroidX.test.espresso.idling.concurrent
androidx.test.espresso.idling:idling-concurrent:_
version.androidx.test.espresso.idling" style="text-decoration: underline;" >concurrent</span>&nbsp; - <span title="AndroidX.test.espresso.idling.net
androidx.test.espresso.idling:idling-net:_
version.androidx.test.espresso.idling" style="text-decoration: underline;" >net</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.test.ext</b></td><td><span title="AndroidX.test.ext.junit
androidx.test.ext:junit:_
version.androidx.test.ext.junit" style="text-decoration: underline;" >junit</span>&nbsp; - <span title="AndroidX.test.ext.truth
androidx.test.ext:truth:_
version.androidx.test.ext.truth" style="text-decoration: underline;" >truth</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.test.ext.junit</b></td><td><span title="AndroidX.test.ext.junit.gTest
androidx.test.ext:junit-gtest:_
version.androidx.test.ext.junit-gtest" style="text-decoration: underline;" >gTest</span>&nbsp; - <span title="AndroidX.test.ext.junit.ktx
androidx.test.ext:junit-ktx:_
version.androidx.test.ext.junit" style="text-decoration: underline;" >ktx</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.tracing</b></td><td><span title="AndroidX.tracing.ktx
androidx.tracing:tracing-ktx:_
version.androidx.tracing" style="text-decoration: underline;" >ktx</span>&nbsp; - <span title="AndroidX.tracing.perfetto
androidx.tracing:tracing-perfetto:_
version.androidx.tracing-perfetto" style="text-decoration: underline;" >perfetto</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.vectorDrawable</b></td><td><span title="AndroidX.vectorDrawable.animated
androidx.vectordrawable:vectordrawable-animated:_
version.androidx.vectordrawable-animated" style="text-decoration: underline;" >animated</span>&nbsp; - <span title="AndroidX.vectorDrawable.seekable
androidx.vectordrawable:vectordrawable-seekable:_
version.androidx.vectordrawable-seekable" style="text-decoration: underline;" >seekable</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.wear</b></td><td><span title="AndroidX.wear.inputTesting
androidx.wear:wear-input-testing:_
version.androidx.wear-input" style="text-decoration: underline;" >inputTesting</span>&nbsp; - <span title="AndroidX.wear.input
androidx.wear:wear-input:_
version.androidx.wear-input" style="text-decoration: underline;" >input</span>&nbsp; - <span title="AndroidX.wear.ongoing
androidx.wear:wear-ongoing:_
version.androidx.wear-ongoing" style="text-decoration: underline;" >ongoing</span>&nbsp; - <span title="AndroidX.wear.phoneInteractions
androidx.wear:wear-phone-interactions:_
version.androidx.wear-phone-interactions" style="text-decoration: underline;" >phoneInteractions</span>&nbsp; - <span title="AndroidX.wear.remoteInteractions
androidx.wear:wear-remote-interactions:_
version.androidx.wear-remote-interactions" style="text-decoration: underline;" >remoteInteractions</span>&nbsp; - <span title="AndroidX.wear.tiles
androidx.wear.tiles:tiles:_
version.androidx.wear.tiles" style="text-decoration: underline;" >tiles</span>&nbsp; - <span title="AndroidX.wear.watchFace
androidx.wear.watchface:watchface:_
version.androidx.wear.watchface" style="text-decoration: underline;" >watchFace</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.wear.compose</b></td><td><span title="AndroidX.wear.compose.foundation
androidx.wear.compose:compose-foundation:_
version.androidx.wear.compose.compose-foundation" style="text-decoration: underline;" >foundation</span>&nbsp; - <span title="AndroidX.wear.compose.material
androidx.wear.compose:compose-material:_
version.androidx.wear.compose.compose-material" style="text-decoration: underline;" >material</span>&nbsp; - <span title="AndroidX.wear.compose.navigation
androidx.wear.compose:compose-navigation:_
version.androidx.wear.compose.compose-navigation" style="text-decoration: underline;" >navigation</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.wear.tiles</b></td><td><span title="AndroidX.wear.tiles.material
androidx.wear.tiles:tiles-material:_
version.androidx.wear.tiles" style="text-decoration: underline;" >material</span>&nbsp; - <span title="AndroidX.wear.tiles.renderer
androidx.wear.tiles:tiles-renderer:_
version.androidx.wear.tiles" style="text-decoration: underline;" >renderer</span>&nbsp; - <span title="AndroidX.wear.tiles.testing
androidx.wear.tiles:tiles-testing:_
version.androidx.wear.tiles" style="text-decoration: underline;" >testing</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.wear.watchFace</b></td><td><span title="AndroidX.wear.watchFace.editor
androidx.wear.watchface:watchface-editor:_
version.androidx.wear.watchface-editor" style="text-decoration: underline;" >editor</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.wear.watchFace.complications</b></td><td><span title="AndroidX.wear.watchFace.complications.dataSourceKtx
androidx.wear.watchface:watchface-complications-data-source-ktx:_
version.androidx.wear.watchface-complications-data-source-ktx" style="text-decoration: underline;" >dataSourceKtx</span>&nbsp; - <span title="AndroidX.wear.watchFace.complications.dataSource
androidx.wear.watchface:watchface-complications-data-source:_
version.androidx.wear.watchface-complications-data-source" style="text-decoration: underline;" >dataSource</span>&nbsp; - <span title="AndroidX.wear.watchFace.complications.rendering
androidx.wear.watchface:watchface-complications-rendering:_
version.androidx.wear.watchface-complications-rendering" style="text-decoration: underline;" >rendering</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.window</b></td><td><span title="AndroidX.window.java
androidx.window:window-java:_
version.androidx.window" style="text-decoration: underline;" >java</span>&nbsp; - <span title="AndroidX.window.rxJava2
androidx.window:window-rxjava2:_
version.androidx.window" style="text-decoration: underline;" >rxJava2</span>&nbsp; - <span title="AndroidX.window.rxJava3
androidx.window:window-rxjava3:_
version.androidx.window" style="text-decoration: underline;" >rxJava3</span>&nbsp; - <span title="AndroidX.window.testing
androidx.window:window-testing:_
version.androidx.window" style="text-decoration: underline;" >testing</span>&nbsp;</td></tr>
<tr><td><b>AndroidX.work</b></td><td><span title="AndroidX.work.gcm
androidx.work:work-gcm:_
version.androidx.work" style="text-decoration: underline;" >gcm</span>&nbsp; - <span title="AndroidX.work.multiprocess
androidx.work:work-multiprocess:_
version.androidx.work" style="text-decoration: underline;" >multiprocess</span>&nbsp; - <span title="AndroidX.work.runtimeKtx
androidx.work:work-runtime-ktx:_
version.androidx.work" style="text-decoration: underline;" >runtimeKtx</span>&nbsp; - <span title="AndroidX.work.runtime
androidx.work:work-runtime:_
version.androidx.work" style="text-decoration: underline;" >runtime</span>&nbsp; - <span title="AndroidX.work.rxJava2
androidx.work:work-rxjava2:_
version.androidx.work" style="text-decoration: underline;" >rxJava2</span>&nbsp; - <span title="AndroidX.work.rxJava3
androidx.work:work-rxjava3:_
version.androidx.work" style="text-decoration: underline;" >rxJava3</span>&nbsp; - <span title="AndroidX.work.testing
androidx.work:work-testing:_
version.androidx.work" style="text-decoration: underline;" >testing</span>&nbsp;</td></tr>
                </table>
## [Arrow.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Arrow.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Arrow</b></td><td><span title="Arrow.core
io.arrow-kt:arrow-core:_
version.arrow" style="text-decoration: underline;" >core</span>&nbsp; - <span title="Arrow.optics
io.arrow-kt:arrow-optics:_
version.arrow" style="text-decoration: underline;" >optics</span>&nbsp; - <span title="Arrow.stack
io.arrow-kt:arrow-stack:_
version.arrow" style="text-decoration: underline;" >stack</span>&nbsp;</td></tr>
<tr><td><b>Arrow.analysis</b></td><td><span title="Arrow.analysis.gradlePlugin
io.arrow-kt.analysis.kotlin:io.arrow-kt.analysis.kotlin.gradle.plugin:_
version.io.arrow-kt.analysis.kotlin" style="text-decoration: underline;" >gradlePlugin</span>&nbsp;</td></tr>
<tr><td><b>Arrow.fx</b></td><td><span title="Arrow.fx.coroutines
io.arrow-kt:arrow-fx-coroutines:_
version.arrow" style="text-decoration: underline;" >coroutines</span>&nbsp; - <span title="Arrow.fx.stm
io.arrow-kt:arrow-fx-stm:_
version.arrow" style="text-decoration: underline;" >stm</span>&nbsp;</td></tr>
<tr><td><b>Arrow.optics</b></td><td><span title="Arrow.optics.kspPlugin
io.arrow-kt:arrow-optics-ksp-plugin:_
version.arrow" style="text-decoration: underline;" >kspPlugin</span>&nbsp; - <span title="Arrow.optics.reflect
io.arrow-kt:arrow-optics-reflect:_
version.arrow" style="text-decoration: underline;" >reflect</span>&nbsp;</td></tr>
                </table>
## [COIL.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/COIL.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>COIL</b></td><td><span title="COIL.base
io.coil-kt:coil-base:_
version.coil-kt" style="text-decoration: underline;" >base</span>&nbsp; - <span title="COIL.composeBase
io.coil-kt:coil-compose-base:_
version.coil-kt" style="text-decoration: underline;" >composeBase</span>&nbsp; - <span title="COIL.compose
io.coil-kt:coil-compose:_
version.coil-kt" style="text-decoration: underline;" >compose</span>&nbsp; - <span title="COIL.gif
io.coil-kt:coil-gif:_
version.coil-kt" style="text-decoration: underline;" >gif</span>&nbsp; - <span title="COIL.svg
io.coil-kt:coil-svg:_
version.coil-kt" style="text-decoration: underline;" >svg</span>&nbsp; - <span title="COIL.video
io.coil-kt:coil-video:_
version.coil-kt" style="text-decoration: underline;" >video</span>&nbsp; - <span title="COIL
io.coil-kt:coil:_
version.coil-kt" style="text-decoration: underline;" >COIL</span>&nbsp;</td></tr>
                </table>
## [CashApp.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/CashApp.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>CashApp</b></td><td><span title="CashApp.licenseeGradlePlugin
app.cash.licensee:licensee-gradle-plugin:_
version.app.cash.licensee" style="text-decoration: underline;" >licenseeGradlePlugin</span>&nbsp; - <span title="CashApp.turbine
app.cash.turbine:turbine:_
version.app.cash.turbine" style="text-decoration: underline;" >turbine</span>&nbsp;</td></tr>
<tr><td><b>CashApp.copper</b></td><td><span title="CashApp.copper.flow
app.cash.copper:copper-flow:_
version.app.cash.copper" style="text-decoration: underline;" >flow</span>&nbsp; - <span title="CashApp.copper.rx2
app.cash.copper:copper-rx2:_
version.app.cash.copper" style="text-decoration: underline;" >rx2</span>&nbsp; - <span title="CashApp.copper.rx3
app.cash.copper:copper-rx3:_
version.app.cash.copper" style="text-decoration: underline;" >rx3</span>&nbsp;</td></tr>
<tr><td><b>CashApp.sqlDelight</b></td><td><span title="CashApp.sqlDelight.gradlePlugin
com.squareup.sqldelight:gradle-plugin:_
version.sqldelight" style="text-decoration: underline;" >gradlePlugin</span>&nbsp;</td></tr>
<tr><td><b>CashApp.sqlDelight.drivers</b></td><td><span title="CashApp.sqlDelight.drivers.android
com.squareup.sqldelight:android-driver:_
version.sqldelight" style="text-decoration: underline;" >android</span>&nbsp; - <span title="CashApp.sqlDelight.drivers.jdbc
com.squareup.sqldelight:jdbc-driver:_
version.sqldelight" style="text-decoration: underline;" >jdbc</span>&nbsp; - <span title="CashApp.sqlDelight.drivers.native
com.squareup.sqldelight:native-driver:_
version.sqldelight" style="text-decoration: underline;" >native</span>&nbsp; - <span title="CashApp.sqlDelight.drivers.jdbcSqlite
com.squareup.sqldelight:sqlite-driver:_
version.sqldelight" style="text-decoration: underline;" >jdbcSqlite</span>&nbsp; - <span title="CashApp.sqlDelight.drivers.sqlJs
com.squareup.sqldelight:sqljs-driver:_
version.sqldelight" style="text-decoration: underline;" >sqlJs</span>&nbsp;</td></tr>
<tr><td><b>CashApp.sqlDelight.extensions</b></td><td><span title="CashApp.sqlDelight.extensions.androidPaging
com.squareup.sqldelight:android-paging-extensions:_
version.sqldelight" style="text-decoration: underline;" >androidPaging</span>&nbsp; - <span title="CashApp.sqlDelight.extensions.androidPaging3
com.squareup.sqldelight:android-paging3-extensions:_
version.sqldelight" style="text-decoration: underline;" >androidPaging3</span>&nbsp; - <span title="CashApp.sqlDelight.extensions.coroutines
com.squareup.sqldelight:coroutines-extensions:_
version.sqldelight" style="text-decoration: underline;" >coroutines</span>&nbsp; - <span title="CashApp.sqlDelight.extensions.rxJava2
com.squareup.sqldelight:rxjava2-extensions:_
version.sqldelight" style="text-decoration: underline;" >rxJava2</span>&nbsp; - <span title="CashApp.sqlDelight.extensions.rxJava3
com.squareup.sqldelight:rxjava3-extensions:_
version.sqldelight" style="text-decoration: underline;" >rxJava3</span>&nbsp;</td></tr>
                </table>
## [Chucker.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Chucker.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Chucker</b></td><td><span title="Chucker.libraryNoOp
com.github.chuckerteam.chucker:library-no-op:_
version.chucker" style="text-decoration: underline;" >libraryNoOp</span>&nbsp; - <span title="Chucker.library
com.github.chuckerteam.chucker:library:_
version.chucker" style="text-decoration: underline;" >library</span>&nbsp;</td></tr>
                </table>
## [Firebase.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Firebase.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Firebase</b></td><td><span title="Firebase.analyticsKtx
com.google.firebase:firebase-analytics-ktx:_
version.firebase-analytics-ktx" style="text-decoration: underline;" >analyticsKtx</span>&nbsp; - <span title="Firebase.analytics
com.google.firebase:firebase-analytics:_
version.firebase-analytics" style="text-decoration: underline;" >analytics</span>&nbsp; - <span title="Firebase.appDistributionGradlePlugin
com.google.firebase:firebase-appdistribution-gradle:_
version.firebase-appdistribution-gradle" style="text-decoration: underline;" >appDistributionGradlePlugin</span>&nbsp; - <span title="Firebase.appIndexing
com.google.firebase:firebase-appindexing:_
version.firebase-appindexing" style="text-decoration: underline;" >appIndexing</span>&nbsp; - <span title="Firebase.authenticationKtx
com.google.firebase:firebase-auth-ktx:_
version.firebase-auth-ktx" style="text-decoration: underline;" >authenticationKtx</span>&nbsp; - <span title="Firebase.authentication
com.google.firebase:firebase-auth:_
version.firebase-auth" style="text-decoration: underline;" >authentication</span>&nbsp; - <span title="Firebase.bom
com.google.firebase:firebase-bom:_
version.firebase-bom" style="text-decoration: underline;" >bom</span>&nbsp; - <span title="Firebase.remoteConfigKtx
com.google.firebase:firebase-config-ktx:_
version.firebase-config-ktx" style="text-decoration: underline;" >remoteConfigKtx</span>&nbsp; - <span title="Firebase.remoteConfig
com.google.firebase:firebase-config:_
version.firebase-config" style="text-decoration: underline;" >remoteConfig</span>&nbsp; - <span title="Firebase.crashlyticsGradlePlugin
com.google.firebase:firebase-crashlytics-gradle:_
version.firebase-crashlytics-gradle" style="text-decoration: underline;" >crashlyticsGradlePlugin</span>&nbsp; - <span title="Firebase.crashlyticsKtx
com.google.firebase:firebase-crashlytics-ktx:_
version.firebase-crashlytics-ktx" style="text-decoration: underline;" >crashlyticsKtx</span>&nbsp; - <span title="Firebase.crashlyticsNdk
com.google.firebase:firebase-crashlytics-ndk:_
version.firebase-crashlytics-ndk" style="text-decoration: underline;" >crashlyticsNdk</span>&nbsp; - <span title="Firebase.crashlytics
com.google.firebase:firebase-crashlytics:_
version.firebase-crashlytics" style="text-decoration: underline;" >crashlytics</span>&nbsp; - <span title="Firebase.realtimeDatabaseKtx
com.google.firebase:firebase-database-ktx:_
version.firebase-database-ktx" style="text-decoration: underline;" >realtimeDatabaseKtx</span>&nbsp; - <span title="Firebase.realtimeDatabase
com.google.firebase:firebase-database:_
version.firebase-database" style="text-decoration: underline;" >realtimeDatabase</span>&nbsp; - <span title="Firebase.dynamicLinksKtx
com.google.firebase:firebase-dynamic-links-ktx:_
version.firebase-dynamic-links-ktx" style="text-decoration: underline;" >dynamicLinksKtx</span>&nbsp; - <span title="Firebase.dynamicLinks
com.google.firebase:firebase-dynamic-links:_
version.firebase-dynamic-links" style="text-decoration: underline;" >dynamicLinks</span>&nbsp; - <span title="Firebase.cloudFirestoreKtx
com.google.firebase:firebase-firestore-ktx:_
version.firebase-firestore-ktx" style="text-decoration: underline;" >cloudFirestoreKtx</span>&nbsp; - <span title="Firebase.cloudFirestore
com.google.firebase:firebase-firestore:_
version.firebase-firestore" style="text-decoration: underline;" >cloudFirestore</span>&nbsp; - <span title="Firebase.cloudFunctionsKtx
com.google.firebase:firebase-functions-ktx:_
version.firebase-functions-ktx" style="text-decoration: underline;" >cloudFunctionsKtx</span>&nbsp; - <span title="Firebase.cloudFunctions
com.google.firebase:firebase-functions:_
version.firebase-functions" style="text-decoration: underline;" >cloudFunctions</span>&nbsp; - <span title="Firebase.inAppMessagingDisplayKtx
com.google.firebase:firebase-inappmessaging-display-ktx:_
version.firebase-inappmessaging-display-ktx" style="text-decoration: underline;" >inAppMessagingDisplayKtx</span>&nbsp; - <span title="Firebase.inAppMessagingDisplay
com.google.firebase:firebase-inappmessaging-display:_
version.firebase-inappmessaging-display" style="text-decoration: underline;" >inAppMessagingDisplay</span>&nbsp; - <span title="Firebase.inAppMessagingKtx
com.google.firebase:firebase-inappmessaging-ktx:_
version.firebase-inappmessaging-ktx" style="text-decoration: underline;" >inAppMessagingKtx</span>&nbsp; - <span title="Firebase.inAppMessaging
com.google.firebase:firebase-inappmessaging:_
version.firebase-inappmessaging" style="text-decoration: underline;" >inAppMessaging</span>&nbsp; - <span title="Firebase.cloudMessagingDirectBoot
com.google.firebase:firebase-messaging-directboot:_
version.firebase-messaging-directboot" style="text-decoration: underline;" >cloudMessagingDirectBoot</span>&nbsp; - <span title="Firebase.cloudMessagingKtx
com.google.firebase:firebase-messaging-ktx:_
version.firebase-messaging-ktx" style="text-decoration: underline;" >cloudMessagingKtx</span>&nbsp; - <span title="Firebase.cloudMessaging
com.google.firebase:firebase-messaging:_
version.firebase-messaging" style="text-decoration: underline;" >cloudMessaging</span>&nbsp; - <span title="Firebase.mlModelDownloaderKtx
com.google.firebase:firebase-ml-modeldownloader-ktx:_
version.firebase-ml-modeldownloader-ktx" style="text-decoration: underline;" >mlModelDownloaderKtx</span>&nbsp; - <span title="Firebase.mlModelDownloader
com.google.firebase:firebase-ml-modeldownloader:_
version.firebase-ml-modeldownloader" style="text-decoration: underline;" >mlModelDownloader</span>&nbsp; - <span title="Firebase.performanceMonitoringKtx
com.google.firebase:firebase-perf-ktx:_
version.firebase-perf-ktx" style="text-decoration: underline;" >performanceMonitoringKtx</span>&nbsp; - <span title="Firebase.performanceMonitoring
com.google.firebase:firebase-perf:_
version.firebase-perf" style="text-decoration: underline;" >performanceMonitoring</span>&nbsp; - <span title="Firebase.cloudStorageKtx
com.google.firebase:firebase-storage-ktx:_
version.firebase-storage-ktx" style="text-decoration: underline;" >cloudStorageKtx</span>&nbsp; - <span title="Firebase.cloudStorage
com.google.firebase:firebase-storage:_
version.firebase-storage" style="text-decoration: underline;" >cloudStorage</span>&nbsp; - <span title="Firebase.performanceMonitoringGradlePlugin
com.google.firebase:perf-plugin:_
version.NO-RULE" style="text-decoration: underline;" >performanceMonitoringGradlePlugin</span>&nbsp;</td></tr>
                </table>
## [Google.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Google.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Google</b></td><td><span title="Google.dagger
com.google.dagger:dagger:_
version.google.dagger" style="text-decoration: underline;" >dagger</span>&nbsp; - <span title="Google.playServicesGradlePlugin
com.google.gms:google-services:_
version.NO-RULE" style="text-decoration: underline;" >playServicesGradlePlugin</span>&nbsp; - <span title="Google.oboe
com.google.oboe:oboe:_
version.google.oboe" style="text-decoration: underline;" >oboe</span>&nbsp;</td></tr>
<tr><td><b>Google.accompanist</b></td><td><span title="Google.accompanist.appCompatTheme
com.google.accompanist:accompanist-appcompat-theme:_
version.google.accompanist" style="text-decoration: underline;" >appCompatTheme</span>&nbsp; - <span title="Google.accompanist.drawablePainter
com.google.accompanist:accompanist-drawablepainter:_
version.google.accompanist" style="text-decoration: underline;" >drawablePainter</span>&nbsp; - <span title="Google.accompanist.flowLayout
com.google.accompanist:accompanist-flowlayout:_
version.google.accompanist" style="text-decoration: underline;" >flowLayout</span>&nbsp; - <span title="Google.accompanist.insets
com.google.accompanist:accompanist-insets:_
version.google.accompanist" style="text-decoration: underline;" >insets</span>&nbsp; - <span title="Google.accompanist.navigationAnimation
com.google.accompanist:accompanist-navigation-animation:_
version.google.accompanist" style="text-decoration: underline;" >navigationAnimation</span>&nbsp; - <span title="Google.accompanist.navigationMaterial
com.google.accompanist:accompanist-navigation-material:_
version.google.accompanist" style="text-decoration: underline;" >navigationMaterial</span>&nbsp; - <span title="Google.accompanist.pager
com.google.accompanist:accompanist-pager:_
version.google.accompanist" style="text-decoration: underline;" >pager</span>&nbsp; - <span title="Google.accompanist.permissions
com.google.accompanist:accompanist-permissions:_
version.google.accompanist" style="text-decoration: underline;" >permissions</span>&nbsp; - <span title="Google.accompanist.placeholder
com.google.accompanist:accompanist-placeholder:_
version.google.accompanist" style="text-decoration: underline;" >placeholder</span>&nbsp; - <span title="Google.accompanist.swipeRefresh
com.google.accompanist:accompanist-swiperefresh:_
version.google.accompanist" style="text-decoration: underline;" >swipeRefresh</span>&nbsp; - <span title="Google.accompanist.systemUiController
com.google.accompanist:accompanist-systemuicontroller:_
version.google.accompanist" style="text-decoration: underline;" >systemUiController</span>&nbsp; - <span title="Google.accompanist.webView
com.google.accompanist:accompanist-webview:_
version.google.accompanist" style="text-decoration: underline;" >webView</span>&nbsp;</td></tr>
<tr><td><b>Google.accompanist.insets</b></td><td><span title="Google.accompanist.insets.ui
com.google.accompanist:accompanist-insets-ui:_
version.google.accompanist" style="text-decoration: underline;" >ui</span>&nbsp;</td></tr>
<tr><td><b>Google.accompanist.pager</b></td><td><span title="Google.accompanist.pager.indicators
com.google.accompanist:accompanist-pager-indicators:_
version.google.accompanist" style="text-decoration: underline;" >indicators</span>&nbsp;</td></tr>
<tr><td><b>Google.accompanist.placeholder</b></td><td><span title="Google.accompanist.placeholder.material
com.google.accompanist:accompanist-placeholder-material:_
version.google.accompanist" style="text-decoration: underline;" >material</span>&nbsp;</td></tr>
<tr><td><b>Google.android</b></td><td><span title="Google.android.flexbox
com.google.android.flexbox:flexbox:_
version.google.android.flexbox" style="text-decoration: underline;" >flexbox</span>&nbsp; - <span title="Google.android.openSourceLicensesPlugin
com.google.android.gms:oss-licenses-plugin:_
version.NO-RULE" style="text-decoration: underline;" >openSourceLicensesPlugin</span>&nbsp; - <span title="Google.android.versionMatcherPlugin
com.google.android.gms:strict-version-matcher-plugin:_
version.NO-RULE" style="text-decoration: underline;" >versionMatcherPlugin</span>&nbsp; - <span title="Google.android.material
com.google.android.material:material:_
version.google.android.material" style="text-decoration: underline;" >material</span>&nbsp; - <span title="Google.android.supportWearable
com.google.android.support:wearable:_
version.google.android.wearable" style="text-decoration: underline;" >supportWearable</span>&nbsp; - <span title="Google.android.wearable
com.google.android.wearable:wearable:_
version.google.android.wearable" style="text-decoration: underline;" >wearable</span>&nbsp; - <span title="Google.android.browserHelper
com.google.androidbrowserhelper:androidbrowserhelper:_
version.google.androidbrowserhelper" style="text-decoration: underline;" >browserHelper</span>&nbsp;</td></tr>
<tr><td><b>Google.android.fhir</b></td><td><span title="Google.android.fhir.dataCapture
com.google.android.fhir:data-capture:_
version.google.android.fhir.data-capture" style="text-decoration: underline;" >dataCapture</span>&nbsp; - <span title="Google.android.fhir.engine
com.google.android.fhir:engine:_
version.google.android.fhir.engine" style="text-decoration: underline;" >engine</span>&nbsp; - <span title="Google.android.fhir.workflow
com.google.android.fhir:workflow:_
version.google.android.fhir.workflow" style="text-decoration: underline;" >workflow</span>&nbsp;</td></tr>
<tr><td><b>Google.android.maps</b></td><td><span title="Google.android.maps.places
com.google.android.libraries.places:places:_
version.google.android.places" style="text-decoration: underline;" >places</span>&nbsp; - <span title="Google.android.maps.utils
com.google.maps.android:android-maps-utils:_
version.google.android.maps-utils" style="text-decoration: underline;" >utils</span>&nbsp; - <span title="Google.android.maps.compose
com.google.maps.android:maps-compose:_
version.google.android.maps-compose" style="text-decoration: underline;" >compose</span>&nbsp; - <span title="Google.android.maps.ktx
com.google.maps.android:maps-ktx:_
version.google.android.maps-ktx" style="text-decoration: underline;" >ktx</span>&nbsp; - <span title="Google.android.maps.rx
com.google.maps.android:maps-rx:_
version.google.android.maps-rx" style="text-decoration: underline;" >rx</span>&nbsp;</td></tr>
<tr><td><b>Google.android.maps.places</b></td><td><span title="Google.android.maps.places.ktx
com.google.maps.android:maps-ktx:_
version.google.android.maps-ktx" style="text-decoration: underline;" >ktx</span>&nbsp; - <span title="Google.android.maps.places.rx
com.google.maps.android:maps-rx:_
version.google.android.maps-rx" style="text-decoration: underline;" >rx</span>&nbsp;</td></tr>
<tr><td><b>Google.android.maps.utils</b></td><td><span title="Google.android.maps.utils.ktx
com.google.maps.android:maps-utils-ktx:_
version.google.android.maps-ktx" style="text-decoration: underline;" >ktx</span>&nbsp;</td></tr>
<tr><td><b>Google.android.material</b></td><td><span title="Google.android.material.composeThemeAdapter3
com.google.android.material:compose-theme-adapter-3:_
version.google.android.material.compose-theme-adapter-3" style="text-decoration: underline;" >composeThemeAdapter3</span>&nbsp; - <span title="Google.android.material.composeThemeAdapter
com.google.android.material:compose-theme-adapter:_
version.google.android.material.compose-theme-adapter" style="text-decoration: underline;" >composeThemeAdapter</span>&nbsp;</td></tr>
<tr><td><b>Google.android.play</b></td><td><span title="Google.android.play.coreKtx
com.google.android.play:core-ktx:_
version.NO-RULE" style="text-decoration: underline;" >coreKtx</span>&nbsp; - <span title="Google.android.play.core
com.google.android.play:core:_
version.NO-RULE" style="text-decoration: underline;" >core</span>&nbsp;</td></tr>
<tr><td><b>Google.android.playServices</b></td><td><span title="Google.android.playServices.analytics
com.google.android.gms:play-services-analytics:_
version.google.android.play-services-analytics" style="text-decoration: underline;" >analytics</span>&nbsp; - <span title="Google.android.playServices.appset
com.google.android.gms:play-services-appset:_
version.google.android.play-services-appset" style="text-decoration: underline;" >appset</span>&nbsp; - <span title="Google.android.playServices.auth
com.google.android.gms:play-services-auth:_
version.google.android.play-services-auth" style="text-decoration: underline;" >auth</span>&nbsp; - <span title="Google.android.playServices.awareness
com.google.android.gms:play-services-awareness:_
version.google.android.play-services-awareness" style="text-decoration: underline;" >awareness</span>&nbsp; - <span title="Google.android.playServices.base
com.google.android.gms:play-services-base:_
version.google.android.play-services-base" style="text-decoration: underline;" >base</span>&nbsp; - <span title="Google.android.playServices.basement
com.google.android.gms:play-services-basement:_
version.google.android.play-services-basement" style="text-decoration: underline;" >basement</span>&nbsp; - <span title="Google.android.playServices.cast
com.google.android.gms:play-services-cast:_
version.google.android.play-services-cast" style="text-decoration: underline;" >cast</span>&nbsp; - <span title="Google.android.playServices.cronet
com.google.android.gms:play-services-cronet:_
version.google.android.play-services-cronet" style="text-decoration: underline;" >cronet</span>&nbsp; - <span title="Google.android.playServices.drive
com.google.android.gms:play-services-drive:_
version.google.android.play-services-drive" style="text-decoration: underline;" >drive</span>&nbsp; - <span title="Google.android.playServices.fido
com.google.android.gms:play-services-fido:_
version.google.android.play-services-fido" style="text-decoration: underline;" >fido</span>&nbsp; - <span title="Google.android.playServices.fitness
com.google.android.gms:play-services-fitness:_
version.google.android.play-services-fitness" style="text-decoration: underline;" >fitness</span>&nbsp; - <span title="Google.android.playServices.games
com.google.android.gms:play-services-games:_
version.google.android.play-services-games" style="text-decoration: underline;" >games</span>&nbsp; - <span title="Google.android.playServices.gcm
com.google.android.gms:play-services-gcm:_
version.google.android.play-services-gcm" style="text-decoration: underline;" >gcm</span>&nbsp; - <span title="Google.android.playServices.identity
com.google.android.gms:play-services-identity:_
version.google.android.play-services-identity" style="text-decoration: underline;" >identity</span>&nbsp; - <span title="Google.android.playServices.instantApps
com.google.android.gms:play-services-instantapps:_
version.google.android.play-services-instantapps" style="text-decoration: underline;" >instantApps</span>&nbsp; - <span title="Google.android.playServices.location
com.google.android.gms:play-services-location:_
version.google.android.play-services-location" style="text-decoration: underline;" >location</span>&nbsp; - <span title="Google.android.playServices.maps
com.google.android.gms:play-services-maps:_
version.google.android.play-services-maps" style="text-decoration: underline;" >maps</span>&nbsp; - <span title="Google.android.playServices.nearby
com.google.android.gms:play-services-nearby:_
version.google.android.play-services-nearby" style="text-decoration: underline;" >nearby</span>&nbsp; - <span title="Google.android.playServices.openSourceLicenses
com.google.android.gms:play-services-oss-licenses:_
version.google.android.play-services-oss-licenses" style="text-decoration: underline;" >openSourceLicenses</span>&nbsp; - <span title="Google.android.playServices.panorama
com.google.android.gms:play-services-panorama:_
version.google.android.play-services-panorama" style="text-decoration: underline;" >panorama</span>&nbsp; - <span title="Google.android.playServices.passwordComplexity
com.google.android.gms:play-services-password-complexity:_
version.google.android.play-services-password-complexity" style="text-decoration: underline;" >passwordComplexity</span>&nbsp; - <span title="Google.android.playServices.pay
com.google.android.gms:play-services-pay:_
version.google.android.play-services-pay" style="text-decoration: underline;" >pay</span>&nbsp; - <span title="Google.android.playServices.recaptcha
com.google.android.gms:play-services-recaptcha:_
version.google.android.play-services-recaptcha" style="text-decoration: underline;" >recaptcha</span>&nbsp; - <span title="Google.android.playServices.safetynet
com.google.android.gms:play-services-safetynet:_
version.google.android.play-services-safetynet" style="text-decoration: underline;" >safetynet</span>&nbsp; - <span title="Google.android.playServices.tagmanager
com.google.android.gms:play-services-tagmanager:_
version.google.android.play-services-tagmanager" style="text-decoration: underline;" >tagmanager</span>&nbsp; - <span title="Google.android.playServices.tasks
com.google.android.gms:play-services-tasks:_
version.google.android.play-services-tasks" style="text-decoration: underline;" >tasks</span>&nbsp; - <span title="Google.android.playServices.vision
com.google.android.gms:play-services-vision:_
version.google.android.play-services-vision" style="text-decoration: underline;" >vision</span>&nbsp; - <span title="Google.android.playServices.wallet
com.google.android.gms:play-services-wallet:_
version.google.android.play-services-wallet" style="text-decoration: underline;" >wallet</span>&nbsp; - <span title="Google.android.playServices.wearOS
com.google.android.gms:play-services-wearable:_
version.google.android.play-services-wearable" style="text-decoration: underline;" >wearOS</span>&nbsp;</td></tr>
<tr><td><b>Google.android.playServices.auth</b></td><td><span title="Google.android.playServices.auth.apiPhone
com.google.android.gms:play-services-auth-api-phone:_
version.google.android.play-services-auth-api-phone" style="text-decoration: underline;" >apiPhone</span>&nbsp; - <span title="Google.android.playServices.auth.blockstore
com.google.android.gms:play-services-auth-blockstore:_
version.google.android.play-services-auth-blockstore" style="text-decoration: underline;" >blockstore</span>&nbsp;</td></tr>
<tr><td><b>Google.android.playServices.cast</b></td><td><span title="Google.android.playServices.cast.framework
com.google.android.gms:play-services-cast-framework:_
version.google.android.play-services-cast-framework" style="text-decoration: underline;" >framework</span>&nbsp; - <span title="Google.android.playServices.cast.tv
com.google.android.gms:play-services-cast-tv:_
version.google.android.play-services-cast-tv" style="text-decoration: underline;" >tv</span>&nbsp;</td></tr>
<tr><td><b>Google.android.playServices.mlKit.naturalLanguage</b></td><td><span title="Google.android.playServices.mlKit.naturalLanguage.languageIdentification
com.google.android.gms:play-services-mlkit-language-id:_
version.google.android.play-services-mlkit-language-id" style="text-decoration: underline;" >languageIdentification</span>&nbsp;</td></tr>
<tr><td><b>Google.android.playServices.mlKit.vision</b></td><td><span title="Google.android.playServices.mlKit.vision.barcodeScanning
com.google.android.gms:play-services-mlkit-barcode-scanning:_
version.google.android.play-services-mlkit-barcode-scanning" style="text-decoration: underline;" >barcodeScanning</span>&nbsp; - <span title="Google.android.playServices.mlKit.vision.faceDetection
com.google.android.gms:play-services-mlkit-face-detection:_
version.google.android.play-services-mlkit-face-detection" style="text-decoration: underline;" >faceDetection</span>&nbsp; - <span title="Google.android.playServices.mlKit.vision.imageLabeling
com.google.android.gms:play-services-mlkit-image-labeling:_
version.google.android.play-services-mlkit-image-labeling" style="text-decoration: underline;" >imageLabeling</span>&nbsp; - <span title="Google.android.playServices.mlKit.vision.textRecognition
com.google.android.gms:play-services-mlkit-text-recognition:_
version.google.android.play-services-mlkit-text-recognition" style="text-decoration: underline;" >textRecognition</span>&nbsp;</td></tr>
<tr><td><b>Google.android.playServices.mlKit.vision.imageLabeling</b></td><td><span title="Google.android.playServices.mlKit.vision.imageLabeling.custom
com.google.android.gms:play-services-mlkit-image-labeling-custom:_
version.google.android.play-services-mlkit-image-labeling-custom" style="text-decoration: underline;" >custom</span>&nbsp;</td></tr>
<tr><td><b>Google.ar</b></td><td><span title="Google.ar.core
com.google.ar:core:_
version.NO-RULE" style="text-decoration: underline;" >core</span>&nbsp;</td></tr>
<tr><td><b>Google.ar.sceneform</b></td><td><span title="Google.ar.sceneform.animation
com.google.ar.sceneform:animation:_
version.NO-RULE" style="text-decoration: underline;" >animation</span>&nbsp; - <span title="Google.ar.sceneform.assets
com.google.ar.sceneform:assets:_
version.NO-RULE" style="text-decoration: underline;" >assets</span>&nbsp; - <span title="Google.ar.sceneform.core
com.google.ar.sceneform:core:_
version.NO-RULE" style="text-decoration: underline;" >core</span>&nbsp; - <span title="Google.ar.sceneform.filamentAndroid
com.google.ar.sceneform:filament-android:_
version.NO-RULE" style="text-decoration: underline;" >filamentAndroid</span>&nbsp; - <span title="Google.ar.sceneform.plugin
com.google.ar.sceneform:plugin:_
version.NO-RULE" style="text-decoration: underline;" >plugin</span>&nbsp; - <span title="Google.ar.sceneform.rendering
com.google.ar.sceneform:rendering:_
version.NO-RULE" style="text-decoration: underline;" >rendering</span>&nbsp; - <span title="Google.ar.sceneform.sceneformBase
com.google.ar.sceneform:sceneform-base:_
version.NO-RULE" style="text-decoration: underline;" >sceneformBase</span>&nbsp; - <span title="Google.ar.sceneform.ux
com.google.ar.sceneform.ux:sceneform-ux:_
version.NO-RULE" style="text-decoration: underline;" >ux</span>&nbsp;</td></tr>
<tr><td><b>Google.dagger</b></td><td><span title="Google.dagger.android
com.google.dagger:dagger-android:_
version.google.dagger" style="text-decoration: underline;" >android</span>&nbsp; - <span title="Google.dagger.compiler
com.google.dagger:dagger-compiler:_
version.google.dagger" style="text-decoration: underline;" >compiler</span>&nbsp; - <span title="Google.dagger.gwt
com.google.dagger:dagger-gwt:_
version.google.dagger" style="text-decoration: underline;" >gwt</span>&nbsp; - <span title="Google.dagger.producers
com.google.dagger:dagger-producers:_
version.google.dagger" style="text-decoration: underline;" >producers</span>&nbsp; - <span title="Google.dagger.spi
com.google.dagger:dagger-spi:_
version.google.dagger" style="text-decoration: underline;" >spi</span>&nbsp;</td></tr>
<tr><td><b>Google.dagger.android</b></td><td><span title="Google.dagger.android.processor
com.google.dagger:dagger-android-processor:_
version.google.dagger" style="text-decoration: underline;" >processor</span>&nbsp; - <span title="Google.dagger.android.support
com.google.dagger:dagger-android-support:_
version.google.dagger" style="text-decoration: underline;" >support</span>&nbsp;</td></tr>
<tr><td><b>Google.dagger.grpc</b></td><td><span title="Google.dagger.grpc.server
com.google.dagger:dagger-grpc-server:_
version.google.dagger" style="text-decoration: underline;" >server</span>&nbsp;</td></tr>
<tr><td><b>Google.dagger.grpc.server</b></td><td><span title="Google.dagger.grpc.server.annotations
com.google.dagger:dagger-grpc-server-annotations:_
version.google.dagger" style="text-decoration: underline;" >annotations</span>&nbsp; - <span title="Google.dagger.grpc.server.processor
com.google.dagger:dagger-grpc-server-processor:_
version.google.dagger" style="text-decoration: underline;" >processor</span>&nbsp;</td></tr>
<tr><td><b>Google.dagger.hilt</b></td><td><span title="Google.dagger.hilt.android
com.google.dagger:hilt-android:_
version.google.dagger" style="text-decoration: underline;" >android</span>&nbsp; - <span title="Google.dagger.hilt.compiler
com.google.dagger:hilt-compiler:_
version.google.dagger" style="text-decoration: underline;" >compiler</span>&nbsp;</td></tr>
<tr><td><b>Google.dagger.hilt.android</b></td><td><span title="Google.dagger.hilt.android.compiler
com.google.dagger:hilt-android-compiler:_
version.google.dagger" style="text-decoration: underline;" >compiler</span>&nbsp; - <span title="Google.dagger.hilt.android.gradlePlugin
com.google.dagger:hilt-android-gradle-plugin:_
version.google.dagger" style="text-decoration: underline;" >gradlePlugin</span>&nbsp; - <span title="Google.dagger.hilt.android.testing
com.google.dagger:hilt-android-testing:_
version.google.dagger" style="text-decoration: underline;" >testing</span>&nbsp;</td></tr>
<tr><td><b>Google.firebase</b></td><td><span title="Google.firebase.analyticsKtx
com.google.firebase:firebase-analytics-ktx:_
version.firebase-analytics-ktx" style="text-decoration: underline;" >analyticsKtx</span>&nbsp; - <span title="Google.firebase.analytics
com.google.firebase:firebase-analytics:_
version.firebase-analytics" style="text-decoration: underline;" >analytics</span>&nbsp; - <span title="Google.firebase.appDistributionGradlePlugin
com.google.firebase:firebase-appdistribution-gradle:_
version.firebase-appdistribution-gradle" style="text-decoration: underline;" >appDistributionGradlePlugin</span>&nbsp; - <span title="Google.firebase.appIndexing
com.google.firebase:firebase-appindexing:_
version.firebase-appindexing" style="text-decoration: underline;" >appIndexing</span>&nbsp; - <span title="Google.firebase.authenticationKtx
com.google.firebase:firebase-auth-ktx:_
version.firebase-auth-ktx" style="text-decoration: underline;" >authenticationKtx</span>&nbsp; - <span title="Google.firebase.authentication
com.google.firebase:firebase-auth:_
version.firebase-auth" style="text-decoration: underline;" >authentication</span>&nbsp; - <span title="Google.firebase.bom
com.google.firebase:firebase-bom:_
version.firebase-bom" style="text-decoration: underline;" >bom</span>&nbsp; - <span title="Google.firebase.remoteConfigKtx
com.google.firebase:firebase-config-ktx:_
version.firebase-config-ktx" style="text-decoration: underline;" >remoteConfigKtx</span>&nbsp; - <span title="Google.firebase.remoteConfig
com.google.firebase:firebase-config:_
version.firebase-config" style="text-decoration: underline;" >remoteConfig</span>&nbsp; - <span title="Google.firebase.crashlyticsGradlePlugin
com.google.firebase:firebase-crashlytics-gradle:_
version.firebase-crashlytics-gradle" style="text-decoration: underline;" >crashlyticsGradlePlugin</span>&nbsp; - <span title="Google.firebase.crashlyticsKtx
com.google.firebase:firebase-crashlytics-ktx:_
version.firebase-crashlytics-ktx" style="text-decoration: underline;" >crashlyticsKtx</span>&nbsp; - <span title="Google.firebase.crashlyticsNdk
com.google.firebase:firebase-crashlytics-ndk:_
version.firebase-crashlytics-ndk" style="text-decoration: underline;" >crashlyticsNdk</span>&nbsp; - <span title="Google.firebase.crashlytics
com.google.firebase:firebase-crashlytics:_
version.firebase-crashlytics" style="text-decoration: underline;" >crashlytics</span>&nbsp; - <span title="Google.firebase.realtimeDatabaseKtx
com.google.firebase:firebase-database-ktx:_
version.firebase-database-ktx" style="text-decoration: underline;" >realtimeDatabaseKtx</span>&nbsp; - <span title="Google.firebase.realtimeDatabase
com.google.firebase:firebase-database:_
version.firebase-database" style="text-decoration: underline;" >realtimeDatabase</span>&nbsp; - <span title="Google.firebase.dynamicLinksKtx
com.google.firebase:firebase-dynamic-links-ktx:_
version.firebase-dynamic-links-ktx" style="text-decoration: underline;" >dynamicLinksKtx</span>&nbsp; - <span title="Google.firebase.dynamicLinks
com.google.firebase:firebase-dynamic-links:_
version.firebase-dynamic-links" style="text-decoration: underline;" >dynamicLinks</span>&nbsp; - <span title="Google.firebase.cloudFirestoreKtx
com.google.firebase:firebase-firestore-ktx:_
version.firebase-firestore-ktx" style="text-decoration: underline;" >cloudFirestoreKtx</span>&nbsp; - <span title="Google.firebase.cloudFirestore
com.google.firebase:firebase-firestore:_
version.firebase-firestore" style="text-decoration: underline;" >cloudFirestore</span>&nbsp; - <span title="Google.firebase.cloudFunctionsKtx
com.google.firebase:firebase-functions-ktx:_
version.firebase-functions-ktx" style="text-decoration: underline;" >cloudFunctionsKtx</span>&nbsp; - <span title="Google.firebase.cloudFunctions
com.google.firebase:firebase-functions:_
version.firebase-functions" style="text-decoration: underline;" >cloudFunctions</span>&nbsp; - <span title="Google.firebase.inAppMessagingDisplayKtx
com.google.firebase:firebase-inappmessaging-display-ktx:_
version.firebase-inappmessaging-display-ktx" style="text-decoration: underline;" >inAppMessagingDisplayKtx</span>&nbsp; - <span title="Google.firebase.inAppMessagingDisplay
com.google.firebase:firebase-inappmessaging-display:_
version.firebase-inappmessaging-display" style="text-decoration: underline;" >inAppMessagingDisplay</span>&nbsp; - <span title="Google.firebase.inAppMessagingKtx
com.google.firebase:firebase-inappmessaging-ktx:_
version.firebase-inappmessaging-ktx" style="text-decoration: underline;" >inAppMessagingKtx</span>&nbsp; - <span title="Google.firebase.inAppMessaging
com.google.firebase:firebase-inappmessaging:_
version.firebase-inappmessaging" style="text-decoration: underline;" >inAppMessaging</span>&nbsp; - <span title="Google.firebase.cloudMessagingDirectBoot
com.google.firebase:firebase-messaging-directboot:_
version.firebase-messaging-directboot" style="text-decoration: underline;" >cloudMessagingDirectBoot</span>&nbsp; - <span title="Google.firebase.cloudMessagingKtx
com.google.firebase:firebase-messaging-ktx:_
version.firebase-messaging-ktx" style="text-decoration: underline;" >cloudMessagingKtx</span>&nbsp; - <span title="Google.firebase.cloudMessaging
com.google.firebase:firebase-messaging:_
version.firebase-messaging" style="text-decoration: underline;" >cloudMessaging</span>&nbsp; - <span title="Google.firebase.mlModelDownloaderKtx
com.google.firebase:firebase-ml-modeldownloader-ktx:_
version.firebase-ml-modeldownloader-ktx" style="text-decoration: underline;" >mlModelDownloaderKtx</span>&nbsp; - <span title="Google.firebase.mlModelDownloader
com.google.firebase:firebase-ml-modeldownloader:_
version.firebase-ml-modeldownloader" style="text-decoration: underline;" >mlModelDownloader</span>&nbsp; - <span title="Google.firebase.performanceMonitoringKtx
com.google.firebase:firebase-perf-ktx:_
version.firebase-perf-ktx" style="text-decoration: underline;" >performanceMonitoringKtx</span>&nbsp; - <span title="Google.firebase.performanceMonitoring
com.google.firebase:firebase-perf:_
version.firebase-perf" style="text-decoration: underline;" >performanceMonitoring</span>&nbsp; - <span title="Google.firebase.cloudStorageKtx
com.google.firebase:firebase-storage-ktx:_
version.firebase-storage-ktx" style="text-decoration: underline;" >cloudStorageKtx</span>&nbsp; - <span title="Google.firebase.cloudStorage
com.google.firebase:firebase-storage:_
version.firebase-storage" style="text-decoration: underline;" >cloudStorage</span>&nbsp; - <span title="Google.firebase.performanceMonitoringGradlePlugin
com.google.firebase:perf-plugin:_
version.NO-RULE" style="text-decoration: underline;" >performanceMonitoringGradlePlugin</span>&nbsp;</td></tr>
<tr><td><b>Google.horologist</b></td><td><span title="Google.horologist.audio
com.google.android.horologist:horologist-audio:_
version.google.horologist" style="text-decoration: underline;" >audio</span>&nbsp; - <span title="Google.horologist.composables
com.google.android.horologist:horologist-composables:_
version.google.horologist" style="text-decoration: underline;" >composables</span>&nbsp; - <span title="Google.horologist.media
com.google.android.horologist:horologist-media:_
version.google.horologist" style="text-decoration: underline;" >media</span>&nbsp; - <span title="Google.horologist.networkAwareness
com.google.android.horologist:horologist-network-awareness:_
version.google.horologist" style="text-decoration: underline;" >networkAwareness</span>&nbsp; - <span title="Google.horologist.tiles
com.google.android.horologist:horologist-tiles:_
version.google.horologist" style="text-decoration: underline;" >tiles</span>&nbsp;</td></tr>
<tr><td><b>Google.horologist.audio</b></td><td><span title="Google.horologist.audio.ui
com.google.android.horologist:horologist-audio-ui:_
version.google.horologist" style="text-decoration: underline;" >ui</span>&nbsp;</td></tr>
<tr><td><b>Google.horologist.compose</b></td><td><span title="Google.horologist.compose.layout
com.google.android.horologist:horologist-compose-layout:_
version.google.horologist" style="text-decoration: underline;" >layout</span>&nbsp; - <span title="Google.horologist.compose.tools
com.google.android.horologist:horologist-compose-tools:_
version.google.horologist" style="text-decoration: underline;" >tools</span>&nbsp;</td></tr>
<tr><td><b>Google.horologist.media</b></td><td><span title="Google.horologist.media.data
com.google.android.horologist:horologist-media-data:_
version.google.horologist" style="text-decoration: underline;" >data</span>&nbsp; - <span title="Google.horologist.media.ui
com.google.android.horologist:horologist-media-ui:_
version.google.horologist" style="text-decoration: underline;" >ui</span>&nbsp;</td></tr>
<tr><td><b>Google.horologist.media3</b></td><td><span title="Google.horologist.media3.backend
com.google.android.horologist:horologist-media3-backend:_
version.google.horologist" style="text-decoration: underline;" >backend</span>&nbsp;</td></tr>
<tr><td><b>Google.mlKit</b></td><td><span title="Google.mlKit.playStoreDynamicFeatureSupport
com.google.mlkit:playstore-dynamic-feature-support:_
version.google.mlkit.playstore-dynamic-feature-support" style="text-decoration: underline;" >playStoreDynamicFeatureSupport</span>&nbsp;</td></tr>
<tr><td><b>Google.mlKit.naturalLanguage</b></td><td><span title="Google.mlKit.naturalLanguage.entityExtraction
com.google.mlkit:entity-extraction:_
version.google.mlkit.entity-extraction" style="text-decoration: underline;" >entityExtraction</span>&nbsp; - <span title="Google.mlKit.naturalLanguage.languageIdentification
com.google.mlkit:language-id:_
version.google.mlkit.language-id" style="text-decoration: underline;" >languageIdentification</span>&nbsp; - <span title="Google.mlKit.naturalLanguage.smartReply
com.google.mlkit:smart-reply:_
version.google.mlkit.smart-reply" style="text-decoration: underline;" >smartReply</span>&nbsp; - <span title="Google.mlKit.naturalLanguage.translate
com.google.mlkit:translate:_
version.google.mlkit.translate" style="text-decoration: underline;" >translate</span>&nbsp;</td></tr>
<tr><td><b>Google.mlKit.vision</b></td><td><span title="Google.mlKit.vision.barcodeScanning
com.google.mlkit:barcode-scanning:_
version.google.mlkit.barcode-scanning" style="text-decoration: underline;" >barcodeScanning</span>&nbsp; - <span title="Google.mlKit.vision.digitalInkRecognition
com.google.mlkit:digital-ink-recognition:_
version.google.mlkit.digital-ink-recognition" style="text-decoration: underline;" >digitalInkRecognition</span>&nbsp; - <span title="Google.mlKit.vision.faceDetection
com.google.mlkit:face-detection:_
version.google.mlkit.face-detection" style="text-decoration: underline;" >faceDetection</span>&nbsp; - <span title="Google.mlKit.vision.imageLabeling
com.google.mlkit:image-labeling:_
version.google.mlkit.image-labeling" style="text-decoration: underline;" >imageLabeling</span>&nbsp; - <span title="Google.mlKit.vision.linkFirebase
com.google.mlkit:linkfirebase:_
version.google.mlkit.linkfirebase" style="text-decoration: underline;" >linkFirebase</span>&nbsp; - <span title="Google.mlKit.vision.objectDetection
com.google.mlkit:object-detection:_
version.google.mlkit.object-detection" style="text-decoration: underline;" >objectDetection</span>&nbsp; - <span title="Google.mlKit.vision.poseDetection
com.google.mlkit:pose-detection:_
version.google.mlkit.pose-detection" style="text-decoration: underline;" >poseDetection</span>&nbsp; - <span title="Google.mlKit.vision.selfieSegmentation
com.google.mlkit:segmentation-selfie:_
version.google.mlkit.segmentation-selfie" style="text-decoration: underline;" >selfieSegmentation</span>&nbsp; - <span title="Google.mlKit.vision.textRecognition
com.google.mlkit:text-recognition:_
version.google.mlkit.text-recognition" style="text-decoration: underline;" >textRecognition</span>&nbsp;</td></tr>
<tr><td><b>Google.mlKit.vision.imageLabeling</b></td><td><span title="Google.mlKit.vision.imageLabeling.custom
com.google.mlkit:image-labeling-custom:_
version.google.mlkit.image-labeling-custom" style="text-decoration: underline;" >custom</span>&nbsp;</td></tr>
<tr><td><b>Google.mlKit.vision.objectDetection</b></td><td><span title="Google.mlKit.vision.objectDetection.custom
com.google.mlkit:object-detection-custom:_
version.google.mlkit.object-detection-custom" style="text-decoration: underline;" >custom</span>&nbsp;</td></tr>
<tr><td><b>Google.mlKit.vision.poseDetection</b></td><td><span title="Google.mlKit.vision.poseDetection.accurate
com.google.mlkit:pose-detection-accurate:_
version.google.mlkit.pose-detection-accurate" style="text-decoration: underline;" >accurate</span>&nbsp;</td></tr>
<tr><td><b>Google.mlKit.vision.textRecognition</b></td><td><span title="Google.mlKit.vision.textRecognition.chinese
com.google.mlkit:text-recognition-chinese:_
version.google.mlkit.text-recognition-chinese" style="text-decoration: underline;" >chinese</span>&nbsp; - <span title="Google.mlKit.vision.textRecognition.devanagari
com.google.mlkit:text-recognition-devanagari:_
version.google.mlkit.text-recognition-devanagari" style="text-decoration: underline;" >devanagari</span>&nbsp; - <span title="Google.mlKit.vision.textRecognition.japanese
com.google.mlkit:text-recognition-japanese:_
version.google.mlkit.text-recognition-japanese" style="text-decoration: underline;" >japanese</span>&nbsp; - <span title="Google.mlKit.vision.textRecognition.korean
com.google.mlkit:text-recognition-korean:_
version.google.mlkit.text-recognition-korean" style="text-decoration: underline;" >korean</span>&nbsp;</td></tr>
<tr><td><b>Google.modernStorage</b></td><td><span title="Google.modernStorage.bom
com.google.modernstorage:modernstorage-bom:_
version.google.modernstorage" style="text-decoration: underline;" >bom</span>&nbsp; - <span title="Google.modernStorage.permissions
com.google.modernstorage:modernstorage-permissions:_
version.google.modernstorage" style="text-decoration: underline;" >permissions</span>&nbsp; - <span title="Google.modernStorage.photoPicker
com.google.modernstorage:modernstorage-photopicker:_
version.google.modernstorage" style="text-decoration: underline;" >photoPicker</span>&nbsp; - <span title="Google.modernStorage.storage
com.google.modernstorage:modernstorage-storage:_
version.google.modernstorage" style="text-decoration: underline;" >storage</span>&nbsp;</td></tr>
                </table>
## [Http4k.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Http4k.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Http4k</b></td><td><span title="Http4k.aws
org.http4k:http4k-aws:_
version.http4k" style="text-decoration: underline;" >aws</span>&nbsp; - <span title="Http4k.bom
org.http4k:http4k-bom:_
version.http4k" style="text-decoration: underline;" >bom</span>&nbsp; - <span title="Http4k.cloudnative
org.http4k:http4k-cloudnative:_
version.http4k" style="text-decoration: underline;" >cloudnative</span>&nbsp; - <span title="Http4k.contract
org.http4k:http4k-contract:_
version.http4k" style="text-decoration: underline;" >contract</span>&nbsp; - <span title="Http4k.core
org.http4k:http4k-core:_
version.http4k" style="text-decoration: underline;" >core</span>&nbsp; - <span title="Http4k.graphql
org.http4k:http4k-graphql:_
version.http4k" style="text-decoration: underline;" >graphql</span>&nbsp; - <span title="Http4k.incubator
org.http4k:http4k-incubator:_
version.http4k" style="text-decoration: underline;" >incubator</span>&nbsp; - <span title="Http4k.jsonrpc
org.http4k:http4k-jsonrpc:_
version.http4k" style="text-decoration: underline;" >jsonrpc</span>&nbsp; - <span title="Http4k.metricsMicrometer
org.http4k:http4k-metrics-micrometer:_
version.http4k" style="text-decoration: underline;" >metricsMicrometer</span>&nbsp; - <span title="Http4k.multipart
org.http4k:http4k-multipart:_
version.http4k" style="text-decoration: underline;" >multipart</span>&nbsp; - <span title="Http4k.opentelemetry
org.http4k:http4k-opentelemetry:_
version.http4k" style="text-decoration: underline;" >opentelemetry</span>&nbsp; - <span title="Http4k.realtimeCore
org.http4k:http4k-realtime-core:_
version.http4k" style="text-decoration: underline;" >realtimeCore</span>&nbsp; - <span title="Http4k.resilience4j
org.http4k:http4k-resilience4j:_
version.http4k" style="text-decoration: underline;" >resilience4j</span>&nbsp; - <span title="Http4k.securityOauth
org.http4k:http4k-security-oauth:_
version.http4k" style="text-decoration: underline;" >securityOauth</span>&nbsp;</td></tr>
<tr><td><b>Http4k.client</b></td><td><span title="Http4k.client.apacheAsync
org.http4k:http4k-client-apache-async:_
version.http4k" style="text-decoration: underline;" >apacheAsync</span>&nbsp; - <span title="Http4k.client.apache4Async
org.http4k:http4k-client-apache4-async:_
version.http4k" style="text-decoration: underline;" >apache4Async</span>&nbsp; - <span title="Http4k.client.apache4
org.http4k:http4k-client-apache4:_
version.http4k" style="text-decoration: underline;" >apache4</span>&nbsp; - <span title="Http4k.client.apache
org.http4k:http4k-client-apache:_
version.http4k" style="text-decoration: underline;" >apache</span>&nbsp; - <span title="Http4k.client.jetty
org.http4k:http4k-client-jetty:_
version.http4k" style="text-decoration: underline;" >jetty</span>&nbsp; - <span title="Http4k.client.okhttp
org.http4k:http4k-client-okhttp:_
version.http4k" style="text-decoration: underline;" >okhttp</span>&nbsp; - <span title="Http4k.client.websocket
org.http4k:http4k-client-websocket:_
version.http4k" style="text-decoration: underline;" >websocket</span>&nbsp;</td></tr>
<tr><td><b>Http4k.format</b></td><td><span title="Http4k.format.argo
org.http4k:http4k-format-argo:_
version.http4k" style="text-decoration: underline;" >argo</span>&nbsp; - <span title="Http4k.format.core
org.http4k:http4k-format-core:_
version.http4k" style="text-decoration: underline;" >core</span>&nbsp; - <span title="Http4k.format.gson
org.http4k:http4k-format-gson:_
version.http4k" style="text-decoration: underline;" >gson</span>&nbsp; - <span title="Http4k.format.jacksonXml
org.http4k:http4k-format-jackson-xml:_
version.http4k" style="text-decoration: underline;" >jacksonXml</span>&nbsp; - <span title="Http4k.format.jacksonYaml
org.http4k:http4k-format-jackson-yaml:_
version.http4k" style="text-decoration: underline;" >jacksonYaml</span>&nbsp; - <span title="Http4k.format.jackson
org.http4k:http4k-format-jackson:_
version.http4k" style="text-decoration: underline;" >jackson</span>&nbsp; - <span title="Http4k.format.klaxon
org.http4k:http4k-format-klaxon:_
version.http4k" style="text-decoration: underline;" >klaxon</span>&nbsp; - <span title="Http4k.format.kotlinxSerialization
org.http4k:http4k-format-kotlinx-serialization:_
version.http4k" style="text-decoration: underline;" >kotlinxSerialization</span>&nbsp; - <span title="Http4k.format.moshi
org.http4k:http4k-format-moshi:_
version.http4k" style="text-decoration: underline;" >moshi</span>&nbsp; - <span title="Http4k.format.xml
org.http4k:http4k-format-xml:_
version.http4k" style="text-decoration: underline;" >xml</span>&nbsp;</td></tr>
<tr><td><b>Http4k.server</b></td><td><span title="Http4k.server.apache4
org.http4k:http4k-server-apache4:_
version.http4k" style="text-decoration: underline;" >apache4</span>&nbsp; - <span title="Http4k.server.apache
org.http4k:http4k-server-apache:_
version.http4k" style="text-decoration: underline;" >apache</span>&nbsp; - <span title="Http4k.server.jetty
org.http4k:http4k-server-jetty:_
version.http4k" style="text-decoration: underline;" >jetty</span>&nbsp; - <span title="Http4k.server.ktorcio
org.http4k:http4k-server-ktorcio:_
version.http4k" style="text-decoration: underline;" >ktorcio</span>&nbsp; - <span title="Http4k.server.ktornetty
org.http4k:http4k-server-ktornetty:_
version.http4k" style="text-decoration: underline;" >ktornetty</span>&nbsp; - <span title="Http4k.server.netty
org.http4k:http4k-server-netty:_
version.http4k" style="text-decoration: underline;" >netty</span>&nbsp; - <span title="Http4k.server.ratpack
org.http4k:http4k-server-ratpack:_
version.http4k" style="text-decoration: underline;" >ratpack</span>&nbsp; - <span title="Http4k.server.undertow
org.http4k:http4k-server-undertow:_
version.http4k" style="text-decoration: underline;" >undertow</span>&nbsp;</td></tr>
<tr><td><b>Http4k.serverless</b></td><td><span title="Http4k.serverless.alibaba
org.http4k:http4k-serverless-alibaba:_
version.http4k" style="text-decoration: underline;" >alibaba</span>&nbsp; - <span title="Http4k.serverless.azure
org.http4k:http4k-serverless-azure:_
version.http4k" style="text-decoration: underline;" >azure</span>&nbsp; - <span title="Http4k.serverless.gcf
org.http4k:http4k-serverless-gcf:_
version.http4k" style="text-decoration: underline;" >gcf</span>&nbsp; - <span title="Http4k.serverless.lambdaRuntime
org.http4k:http4k-serverless-lambda-runtime:_
version.http4k" style="text-decoration: underline;" >lambdaRuntime</span>&nbsp; - <span title="Http4k.serverless.lambda
org.http4k:http4k-serverless-lambda:_
version.http4k" style="text-decoration: underline;" >lambda</span>&nbsp; - <span title="Http4k.serverless.openwhisk
org.http4k:http4k-serverless-openwhisk:_
version.http4k" style="text-decoration: underline;" >openwhisk</span>&nbsp; - <span title="Http4k.serverless.tencent
org.http4k:http4k-serverless-tencent:_
version.http4k" style="text-decoration: underline;" >tencent</span>&nbsp;</td></tr>
<tr><td><b>Http4k.template</b></td><td><span title="Http4k.template.core
org.http4k:http4k-template-core:_
version.http4k" style="text-decoration: underline;" >core</span>&nbsp; - <span title="Http4k.template.dust
org.http4k:http4k-template-dust:_
version.http4k" style="text-decoration: underline;" >dust</span>&nbsp; - <span title="Http4k.template.freemarker
org.http4k:http4k-template-freemarker:_
version.http4k" style="text-decoration: underline;" >freemarker</span>&nbsp; - <span title="Http4k.template.handlebars
org.http4k:http4k-template-handlebars:_
version.http4k" style="text-decoration: underline;" >handlebars</span>&nbsp; - <span title="Http4k.template.jade4j
org.http4k:http4k-template-jade4j:_
version.http4k" style="text-decoration: underline;" >jade4j</span>&nbsp; - <span title="Http4k.template.pebble
org.http4k:http4k-template-pebble:_
version.http4k" style="text-decoration: underline;" >pebble</span>&nbsp; - <span title="Http4k.template.thymeleaf
org.http4k:http4k-template-thymeleaf:_
version.http4k" style="text-decoration: underline;" >thymeleaf</span>&nbsp;</td></tr>
<tr><td><b>Http4k.testing</b></td><td><span title="Http4k.testing.approval
org.http4k:http4k-testing-approval:_
version.http4k" style="text-decoration: underline;" >approval</span>&nbsp; - <span title="Http4k.testing.chaos
org.http4k:http4k-testing-chaos:_
version.http4k" style="text-decoration: underline;" >chaos</span>&nbsp; - <span title="Http4k.testing.hamkrest
org.http4k:http4k-testing-hamkrest:_
version.http4k" style="text-decoration: underline;" >hamkrest</span>&nbsp; - <span title="Http4k.testing.kotest
org.http4k:http4k-testing-kotest:_
version.http4k" style="text-decoration: underline;" >kotest</span>&nbsp; - <span title="Http4k.testing.servirtium
org.http4k:http4k-testing-servirtium:_
version.http4k" style="text-decoration: underline;" >servirtium</span>&nbsp; - <span title="Http4k.testing.strikt
org.http4k:http4k-testing-strikt:_
version.http4k" style="text-decoration: underline;" >strikt</span>&nbsp; - <span title="Http4k.testing.webdriver
org.http4k:http4k-testing-webdriver:_
version.http4k" style="text-decoration: underline;" >webdriver</span>&nbsp;</td></tr>
                </table>
## [JakeWharton.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/JakeWharton.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>JakeWharton</b></td><td><span title="JakeWharton.confundusGradlePlugin
com.jakewharton.confundus:confundus-gradle:_
version.jakewharton.confundus" style="text-decoration: underline;" >confundusGradlePlugin</span>&nbsp; - <span title="JakeWharton.picnic
com.jakewharton.picnic:picnic:_
version.jakewharton.picnic" style="text-decoration: underline;" >picnic</span>&nbsp; - <span title="JakeWharton.rxBinding3
com.jakewharton.rxbinding3:rxbinding:_
version.jakewharton.rxbinding3" style="text-decoration: underline;" >rxBinding3</span>&nbsp; - <span title="JakeWharton.rxBinding4
com.jakewharton.rxbinding4:rxbinding:_
version.jakewharton.rxbinding4" style="text-decoration: underline;" >rxBinding4</span>&nbsp; - <span title="JakeWharton.rxRelay2
com.jakewharton.rxrelay2:rxrelay:_
version.jakewharton.rxrelay2" style="text-decoration: underline;" >rxRelay2</span>&nbsp; - <span title="JakeWharton.rxRelay3
com.jakewharton.rxrelay3:rxrelay:_
version.jakewharton.rxrelay3" style="text-decoration: underline;" >rxRelay3</span>&nbsp; - <span title="JakeWharton.timber
com.jakewharton.timber:timber:_
version.jakewharton.timber" style="text-decoration: underline;" >timber</span>&nbsp; - <span title="JakeWharton.wormholeGradlePlugin
com.jakewharton.wormhole:wormhole-gradle:_
version.jakewharton.wormhole" style="text-decoration: underline;" >wormholeGradlePlugin</span>&nbsp;</td></tr>
<tr><td><b>JakeWharton.moshi</b></td><td><span title="JakeWharton.moshi.shimo
com.jakewharton.moshi:shimo:_
version.jakewharton.moshi.shimo" style="text-decoration: underline;" >shimo</span>&nbsp;</td></tr>
<tr><td><b>JakeWharton.retrofit2.converter</b></td><td><span title="JakeWharton.retrofit2.converter.kotlinxSerialization
com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:_
version.jakewharton.retrofit2-kotlinx-serialization-converter" style="text-decoration: underline;" >kotlinxSerialization</span>&nbsp;</td></tr>
<tr><td><b>JakeWharton.rxBinding3</b></td><td><span title="JakeWharton.rxBinding3.appcompat
com.jakewharton.rxbinding3:rxbinding-appcompat:_
version.jakewharton.rxbinding3" style="text-decoration: underline;" >appcompat</span>&nbsp; - <span title="JakeWharton.rxBinding3.core
com.jakewharton.rxbinding3:rxbinding-core:_
version.jakewharton.rxbinding3" style="text-decoration: underline;" >core</span>&nbsp; - <span title="JakeWharton.rxBinding3.drawerLayout
com.jakewharton.rxbinding3:rxbinding-drawerlayout:_
version.jakewharton.rxbinding3" style="text-decoration: underline;" >drawerLayout</span>&nbsp; - <span title="JakeWharton.rxBinding3.leanback
com.jakewharton.rxbinding3:rxbinding-leanback:_
version.jakewharton.rxbinding3" style="text-decoration: underline;" >leanback</span>&nbsp; - <span title="JakeWharton.rxBinding3.material
com.jakewharton.rxbinding3:rxbinding-material:_
version.jakewharton.rxbinding3" style="text-decoration: underline;" >material</span>&nbsp; - <span title="JakeWharton.rxBinding3.recyclerview
com.jakewharton.rxbinding3:rxbinding-recyclerview:_
version.jakewharton.rxbinding3" style="text-decoration: underline;" >recyclerview</span>&nbsp; - <span title="JakeWharton.rxBinding3.slidingPaneLayout
com.jakewharton.rxbinding3:rxbinding-slidingpanelayout:_
version.jakewharton.rxbinding3" style="text-decoration: underline;" >slidingPaneLayout</span>&nbsp; - <span title="JakeWharton.rxBinding3.swipeRefreshLayout
com.jakewharton.rxbinding3:rxbinding-swiperefreshlayout:_
version.jakewharton.rxbinding3" style="text-decoration: underline;" >swipeRefreshLayout</span>&nbsp; - <span title="JakeWharton.rxBinding3.viewPager2
com.jakewharton.rxbinding3:rxbinding-viewpager2:_
version.jakewharton.rxbinding3" style="text-decoration: underline;" >viewPager2</span>&nbsp; - <span title="JakeWharton.rxBinding3.viewPager
com.jakewharton.rxbinding3:rxbinding-viewpager:_
version.jakewharton.rxbinding3" style="text-decoration: underline;" >viewPager</span>&nbsp;</td></tr>
<tr><td><b>JakeWharton.rxBinding4</b></td><td><span title="JakeWharton.rxBinding4.appcompat
com.jakewharton.rxbinding4:rxbinding-appcompat:_
version.jakewharton.rxbinding4" style="text-decoration: underline;" >appcompat</span>&nbsp; - <span title="JakeWharton.rxBinding4.core
com.jakewharton.rxbinding4:rxbinding-core:_
version.jakewharton.rxbinding4" style="text-decoration: underline;" >core</span>&nbsp; - <span title="JakeWharton.rxBinding4.drawerLayout
com.jakewharton.rxbinding4:rxbinding-drawerlayout:_
version.jakewharton.rxbinding4" style="text-decoration: underline;" >drawerLayout</span>&nbsp; - <span title="JakeWharton.rxBinding4.leanback
com.jakewharton.rxbinding4:rxbinding-leanback:_
version.jakewharton.rxbinding4" style="text-decoration: underline;" >leanback</span>&nbsp; - <span title="JakeWharton.rxBinding4.material
com.jakewharton.rxbinding4:rxbinding-material:_
version.jakewharton.rxbinding4" style="text-decoration: underline;" >material</span>&nbsp; - <span title="JakeWharton.rxBinding4.recyclerview
com.jakewharton.rxbinding4:rxbinding-recyclerview:_
version.jakewharton.rxbinding4" style="text-decoration: underline;" >recyclerview</span>&nbsp; - <span title="JakeWharton.rxBinding4.slidingPaneLayout
com.jakewharton.rxbinding4:rxbinding-slidingpanelayout:_
version.jakewharton.rxbinding4" style="text-decoration: underline;" >slidingPaneLayout</span>&nbsp; - <span title="JakeWharton.rxBinding4.swipeRefreshLayout
com.jakewharton.rxbinding4:rxbinding-swiperefreshlayout:_
version.jakewharton.rxbinding4" style="text-decoration: underline;" >swipeRefreshLayout</span>&nbsp; - <span title="JakeWharton.rxBinding4.viewPager2
com.jakewharton.rxbinding4:rxbinding-viewpager2:_
version.jakewharton.rxbinding4" style="text-decoration: underline;" >viewPager2</span>&nbsp; - <span title="JakeWharton.rxBinding4.viewPager
com.jakewharton.rxbinding4:rxbinding-viewpager:_
version.jakewharton.rxbinding4" style="text-decoration: underline;" >viewPager</span>&nbsp;</td></tr>
                </table>
## [JetBrains.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/JetBrains.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>JetBrains.exposed</b></td><td><span title="JetBrains.exposed.core
org.jetbrains.exposed:exposed-core:_
version.jetbrains.exposed" style="text-decoration: underline;" >core</span>&nbsp; - <span title="JetBrains.exposed.dao
org.jetbrains.exposed:exposed-dao:_
version.jetbrains.exposed" style="text-decoration: underline;" >dao</span>&nbsp; - <span title="JetBrains.exposed.jdbc
org.jetbrains.exposed:exposed-jdbc:_
version.jetbrains.exposed" style="text-decoration: underline;" >jdbc</span>&nbsp;</td></tr>
<tr><td><b>JetBrains.ktor</b></td><td><span title="JetBrains.ktor.server
io.ktor:ktor-server:_
version.ktor" style="text-decoration: underline;" >server</span>&nbsp; - <span title="JetBrains.ktor.testDispatcher
io.ktor:ktor-test-dispatcher:_
version.ktor" style="text-decoration: underline;" >testDispatcher</span>&nbsp; - <span title="JetBrains.ktor.utils
io.ktor:ktor-utils:_
version.ktor" style="text-decoration: underline;" >utils</span>&nbsp;</td></tr>
<tr><td><b>JetBrains.ktor.client</b></td><td><span title="JetBrains.ktor.client.android
io.ktor:ktor-client-android:_
version.ktor" style="text-decoration: underline;" >android</span>&nbsp; - <span title="JetBrains.ktor.client.apache
io.ktor:ktor-client-apache:_
version.ktor" style="text-decoration: underline;" >apache</span>&nbsp; - <span title="JetBrains.ktor.client.auth
io.ktor:ktor-client-auth:_
version.ktor" style="text-decoration: underline;" >auth</span>&nbsp; - <span title="JetBrains.ktor.client.cio
io.ktor:ktor-client-cio:_
version.ktor" style="text-decoration: underline;" >cio</span>&nbsp; - <span title="JetBrains.ktor.client.contentNegotiationTests
io.ktor:ktor-client-content-negotiation-tests:_
version.ktor" style="text-decoration: underline;" >contentNegotiationTests</span>&nbsp; - <span title="JetBrains.ktor.client.contentNegotiation
io.ktor:ktor-client-content-negotiation:_
version.ktor" style="text-decoration: underline;" >contentNegotiation</span>&nbsp; - <span title="JetBrains.ktor.client.core
io.ktor:ktor-client-core:_
version.ktor" style="text-decoration: underline;" >core</span>&nbsp; - <span title="JetBrains.ktor.client.curl
io.ktor:ktor-client-curl:_
version.ktor" style="text-decoration: underline;" >curl</span>&nbsp; - <span title="JetBrains.ktor.client.darwin
io.ktor:ktor-client-darwin:_
version.ktor" style="text-decoration: underline;" >darwin</span>&nbsp; - <span title="JetBrains.ktor.client.encoding
io.ktor:ktor-client-encoding:_
version.ktor" style="text-decoration: underline;" >encoding</span>&nbsp; - <span title="JetBrains.ktor.client.gson
io.ktor:ktor-client-gson:_
version.ktor" style="text-decoration: underline;" >gson</span>&nbsp; - <span title="JetBrains.ktor.client.jackson
io.ktor:ktor-client-jackson:_
version.ktor" style="text-decoration: underline;" >jackson</span>&nbsp; - <span title="JetBrains.ktor.client.java
io.ktor:ktor-client-java:_
version.ktor" style="text-decoration: underline;" >java</span>&nbsp; - <span title="JetBrains.ktor.client.jetty
io.ktor:ktor-client-jetty:_
version.ktor" style="text-decoration: underline;" >jetty</span>&nbsp; - <span title="JetBrains.ktor.client.jsonTests
io.ktor:ktor-client-json-tests:_
version.ktor" style="text-decoration: underline;" >jsonTests</span>&nbsp; - <span title="JetBrains.ktor.client.json
io.ktor:ktor-client-json:_
version.ktor" style="text-decoration: underline;" >json</span>&nbsp; - <span title="JetBrains.ktor.client.logging
io.ktor:ktor-client-logging:_
version.ktor" style="text-decoration: underline;" >logging</span>&nbsp; - <span title="JetBrains.ktor.client.mock
io.ktor:ktor-client-mock:_
version.ktor" style="text-decoration: underline;" >mock</span>&nbsp; - <span title="JetBrains.ktor.client.okhttp
io.ktor:ktor-client-okhttp:_
version.ktor" style="text-decoration: underline;" >okhttp</span>&nbsp; - <span title="JetBrains.ktor.client.resources
io.ktor:ktor-client-resources:_
version.ktor" style="text-decoration: underline;" >resources</span>&nbsp; - <span title="JetBrains.ktor.client.serialization
io.ktor:ktor-client-serialization:_
version.ktor" style="text-decoration: underline;" >serialization</span>&nbsp; - <span title="JetBrains.ktor.client.tests
io.ktor:ktor-client-tests:_
version.ktor" style="text-decoration: underline;" >tests</span>&nbsp;</td></tr>
<tr><td><b>JetBrains.ktor.plugins</b></td><td><span title="JetBrains.ktor.plugins.events
io.ktor:ktor-events:_
version.ktor" style="text-decoration: underline;" >events</span>&nbsp; - <span title="JetBrains.ktor.plugins.httpCio
io.ktor:ktor-http-cio:_
version.ktor" style="text-decoration: underline;" >httpCio</span>&nbsp; - <span title="JetBrains.ktor.plugins.http
io.ktor:ktor-http:_
version.ktor" style="text-decoration: underline;" >http</span>&nbsp; - <span title="JetBrains.ktor.plugins.io
io.ktor:ktor-io:_
version.ktor" style="text-decoration: underline;" >io</span>&nbsp; - <span title="JetBrains.ktor.plugins.networkTlsCertificates
io.ktor:ktor-network-tls-certificates:_
version.ktor" style="text-decoration: underline;" >networkTlsCertificates</span>&nbsp; - <span title="JetBrains.ktor.plugins.networkTls
io.ktor:ktor-network-tls:_
version.ktor" style="text-decoration: underline;" >networkTls</span>&nbsp; - <span title="JetBrains.ktor.plugins.network
io.ktor:ktor-network:_
version.ktor" style="text-decoration: underline;" >network</span>&nbsp; - <span title="JetBrains.ktor.plugins.resources
io.ktor:ktor-resources:_
version.ktor" style="text-decoration: underline;" >resources</span>&nbsp; - <span title="JetBrains.ktor.plugins.websocketSerialization
io.ktor:ktor-websocket-serialization:_
version.ktor" style="text-decoration: underline;" >websocketSerialization</span>&nbsp; - <span title="JetBrains.ktor.plugins.websockets
io.ktor:ktor-websockets:_
version.ktor" style="text-decoration: underline;" >websockets</span>&nbsp;</td></tr>
<tr><td><b>JetBrains.ktor.plugins.serialization</b></td><td><span title="JetBrains.ktor.plugins.serialization.gson
io.ktor:ktor-serialization-gson:_
version.ktor" style="text-decoration: underline;" >gson</span>&nbsp; - <span title="JetBrains.ktor.plugins.serialization.jackson
io.ktor:ktor-serialization-jackson:_
version.ktor" style="text-decoration: underline;" >jackson</span>&nbsp; - <span title="JetBrains.ktor.plugins.serialization.kotlinxCbor
io.ktor:ktor-serialization-kotlinx-cbor:_
version.ktor" style="text-decoration: underline;" >kotlinxCbor</span>&nbsp; - <span title="JetBrains.ktor.plugins.serialization.kotlinxJson
io.ktor:ktor-serialization-kotlinx-json:_
version.ktor" style="text-decoration: underline;" >kotlinxJson</span>&nbsp; - <span title="JetBrains.ktor.plugins.serialization.kotlinxTests
io.ktor:ktor-serialization-kotlinx-tests:_
version.ktor" style="text-decoration: underline;" >kotlinxTests</span>&nbsp; - <span title="JetBrains.ktor.plugins.serialization.kotlinxXml
io.ktor:ktor-serialization-kotlinx-xml:_
version.ktor" style="text-decoration: underline;" >kotlinxXml</span>&nbsp; - <span title="JetBrains.ktor.plugins.serialization.kotlinx
io.ktor:ktor-serialization-kotlinx:_
version.ktor" style="text-decoration: underline;" >kotlinx</span>&nbsp; - <span title="JetBrains.ktor.plugins.serialization.serialization
io.ktor:ktor-serialization:_
version.ktor" style="text-decoration: underline;" >serialization</span>&nbsp;</td></tr>
<tr><td><b>JetBrains.ktor.server</b></td><td><span title="JetBrains.ktor.server.authJwt
io.ktor:ktor-server-auth-jwt:_
version.ktor" style="text-decoration: underline;" >authJwt</span>&nbsp; - <span title="JetBrains.ktor.server.authLdap
io.ktor:ktor-server-auth-ldap:_
version.ktor" style="text-decoration: underline;" >authLdap</span>&nbsp; - <span title="JetBrains.ktor.server.auth
io.ktor:ktor-server-auth:_
version.ktor" style="text-decoration: underline;" >auth</span>&nbsp; - <span title="JetBrains.ktor.server.autoHeadResponse
io.ktor:ktor-server-auto-head-response:_
version.ktor" style="text-decoration: underline;" >autoHeadResponse</span>&nbsp; - <span title="JetBrains.ktor.server.cachingHeaders
io.ktor:ktor-server-caching-headers:_
version.ktor" style="text-decoration: underline;" >cachingHeaders</span>&nbsp; - <span title="JetBrains.ktor.server.callId
io.ktor:ktor-server-call-id:_
version.ktor" style="text-decoration: underline;" >callId</span>&nbsp; - <span title="JetBrains.ktor.server.callLogging
io.ktor:ktor-server-call-logging:_
version.ktor" style="text-decoration: underline;" >callLogging</span>&nbsp; - <span title="JetBrains.ktor.server.cio
io.ktor:ktor-server-cio:_
version.ktor" style="text-decoration: underline;" >cio</span>&nbsp; - <span title="JetBrains.ktor.server.compression
io.ktor:ktor-server-compression:_
version.ktor" style="text-decoration: underline;" >compression</span>&nbsp; - <span title="JetBrains.ktor.server.conditionalHeaders
io.ktor:ktor-server-conditional-headers:_
version.ktor" style="text-decoration: underline;" >conditionalHeaders</span>&nbsp; - <span title="JetBrains.ktor.server.contentNegotiation
io.ktor:ktor-server-content-negotiation:_
version.ktor" style="text-decoration: underline;" >contentNegotiation</span>&nbsp; - <span title="JetBrains.ktor.server.core
io.ktor:ktor-server-core:_
version.ktor" style="text-decoration: underline;" >core</span>&nbsp; - <span title="JetBrains.ktor.server.cors
io.ktor:ktor-server-cors:_
version.ktor" style="text-decoration: underline;" >cors</span>&nbsp; - <span title="JetBrains.ktor.server.dataConversion
io.ktor:ktor-server-data-conversion:_
version.ktor" style="text-decoration: underline;" >dataConversion</span>&nbsp; - <span title="JetBrains.ktor.server.defaultHeaders
io.ktor:ktor-server-default-headers:_
version.ktor" style="text-decoration: underline;" >defaultHeaders</span>&nbsp; - <span title="JetBrains.ktor.server.doubleReceive
io.ktor:ktor-server-double-receive:_
version.ktor" style="text-decoration: underline;" >doubleReceive</span>&nbsp; - <span title="JetBrains.ktor.server.forwardedHeader
io.ktor:ktor-server-forwarded-header:_
version.ktor" style="text-decoration: underline;" >forwardedHeader</span>&nbsp; - <span title="JetBrains.ktor.server.freeMarker
io.ktor:ktor-server-freemarker:_
version.ktor" style="text-decoration: underline;" >freeMarker</span>&nbsp; - <span title="JetBrains.ktor.server.hostCommon
io.ktor:ktor-server-host-common:_
version.ktor" style="text-decoration: underline;" >hostCommon</span>&nbsp; - <span title="JetBrains.ktor.server.hsts
io.ktor:ktor-server-hsts:_
version.ktor" style="text-decoration: underline;" >hsts</span>&nbsp; - <span title="JetBrains.ktor.server.htmlBuilder
io.ktor:ktor-server-html-builder:_
version.ktor" style="text-decoration: underline;" >htmlBuilder</span>&nbsp; - <span title="JetBrains.ktor.server.httpRedirect
io.ktor:ktor-server-http-redirect:_
version.ktor" style="text-decoration: underline;" >httpRedirect</span>&nbsp; - <span title="JetBrains.ktor.server.jetty
io.ktor:ktor-server-jetty:_
version.ktor" style="text-decoration: underline;" >jetty</span>&nbsp; - <span title="JetBrains.ktor.server.jte
io.ktor:ktor-server-jte:_
version.ktor" style="text-decoration: underline;" >jte</span>&nbsp; - <span title="JetBrains.ktor.server.locations
io.ktor:ktor-server-locations:_
version.ktor" style="text-decoration: underline;" >locations</span>&nbsp; - <span title="JetBrains.ktor.server.methodOverride
io.ktor:ktor-server-method-override:_
version.ktor" style="text-decoration: underline;" >methodOverride</span>&nbsp; - <span title="JetBrains.ktor.server.metricsMicrometer
io.ktor:ktor-server-metrics-micrometer:_
version.ktor" style="text-decoration: underline;" >metricsMicrometer</span>&nbsp; - <span title="JetBrains.ktor.server.metrics
io.ktor:ktor-server-metrics:_
version.ktor" style="text-decoration: underline;" >metrics</span>&nbsp; - <span title="JetBrains.ktor.server.mustache
io.ktor:ktor-server-mustache:_
version.ktor" style="text-decoration: underline;" >mustache</span>&nbsp; - <span title="JetBrains.ktor.server.netty
io.ktor:ktor-server-netty:_
version.ktor" style="text-decoration: underline;" >netty</span>&nbsp; - <span title="JetBrains.ktor.server.partialContent
io.ktor:ktor-server-partial-content:_
version.ktor" style="text-decoration: underline;" >partialContent</span>&nbsp; - <span title="JetBrains.ktor.server.pebble
io.ktor:ktor-server-pebble:_
version.ktor" style="text-decoration: underline;" >pebble</span>&nbsp; - <span title="JetBrains.ktor.server.resources
io.ktor:ktor-server-resources:_
version.ktor" style="text-decoration: underline;" >resources</span>&nbsp; - <span title="JetBrains.ktor.server.servlet
io.ktor:ktor-server-servlet:_
version.ktor" style="text-decoration: underline;" >servlet</span>&nbsp; - <span title="JetBrains.ktor.server.sessions
io.ktor:ktor-server-sessions:_
version.ktor" style="text-decoration: underline;" >sessions</span>&nbsp; - <span title="JetBrains.ktor.server.statusPages
io.ktor:ktor-server-status-pages:_
version.ktor" style="text-decoration: underline;" >statusPages</span>&nbsp; - <span title="JetBrains.ktor.server.testHost
io.ktor:ktor-server-test-host:_
version.ktor" style="text-decoration: underline;" >testHost</span>&nbsp; - <span title="JetBrains.ktor.server.testSuites
io.ktor:ktor-server-test-suites:_
version.ktor" style="text-decoration: underline;" >testSuites</span>&nbsp; - <span title="JetBrains.ktor.server.thymeleaf
io.ktor:ktor-server-thymeleaf:_
version.ktor" style="text-decoration: underline;" >thymeleaf</span>&nbsp; - <span title="JetBrains.ktor.server.tomcat
io.ktor:ktor-server-tomcat:_
version.ktor" style="text-decoration: underline;" >tomcat</span>&nbsp; - <span title="JetBrains.ktor.server.velocity
io.ktor:ktor-server-velocity:_
version.ktor" style="text-decoration: underline;" >velocity</span>&nbsp; - <span title="JetBrains.ktor.server.webjars
io.ktor:ktor-server-webjars:_
version.ktor" style="text-decoration: underline;" >webjars</span>&nbsp; - <span title="JetBrains.ktor.server.websockets
io.ktor:ktor-server-websockets:_
version.ktor" style="text-decoration: underline;" >websockets</span>&nbsp;</td></tr>
                </table>
## [Kodein.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Kodein.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Kodein.di</b></td><td><span title="Kodein.di.configurableJS
org.kodein.di:kodein-di-conf-js:_
version.kodein.di" style="text-decoration: underline;" >configurableJS</span>&nbsp; - <span title="Kodein.di.configurableJvm
org.kodein.di:kodein-di-conf-jvm:_
version.kodein.di" style="text-decoration: underline;" >configurableJvm</span>&nbsp; - <span title="Kodein.di.androidCore
org.kodein.di:kodein-di-framework-android-core:_
version.kodein.di" style="text-decoration: underline;" >androidCore</span>&nbsp; - <span title="Kodein.di.androidSupport
org.kodein.di:kodein-di-framework-android-support:_
version.kodein.di" style="text-decoration: underline;" >androidSupport</span>&nbsp; - <span title="Kodein.di.androidx
org.kodein.di:kodein-di-framework-android-x:_
version.kodein.di" style="text-decoration: underline;" >androidx</span>&nbsp; - <span title="Kodein.di.ktor
org.kodein.di:kodein-di-framework-ktor-server-jvm:_
version.kodein.di" style="text-decoration: underline;" >ktor</span>&nbsp; - <span title="Kodein.di.tornadofx
org.kodein.di:kodein-di-framework-tornadofx-jvm:_
version.kodein.di" style="text-decoration: underline;" >tornadofx</span>&nbsp; - <span title="Kodein.di.js
org.kodein.di:kodein-di-js:_
version.kodein.di" style="text-decoration: underline;" >js</span>&nbsp; - <span title="Kodein.di.jsr330
org.kodein.di:kodein-di-jxinject-jvm:_
version.kodein.di" style="text-decoration: underline;" >jsr330</span>&nbsp;</td></tr>
                </table>
## [Koin.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Koin.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Koin</b></td><td><span title="Koin.androidCompat
io.insert-koin:koin-android-compat:_
version.koin" style="text-decoration: underline;" >androidCompat</span>&nbsp; - <span title="Koin.android
io.insert-koin:koin-android:_
version.koin" style="text-decoration: underline;" >android</span>&nbsp; - <span title="Koin.compose
io.insert-koin:koin-androidx-compose:_
version.koin" style="text-decoration: underline;" >compose</span>&nbsp; - <span title="Koin.navigation
io.insert-koin:koin-androidx-navigation:_
version.koin" style="text-decoration: underline;" >navigation</span>&nbsp; - <span title="Koin.workManager
io.insert-koin:koin-androidx-workmanager:_
version.koin" style="text-decoration: underline;" >workManager</span>&nbsp; - <span title="Koin.core
io.insert-koin:koin-core:_
version.koin" style="text-decoration: underline;" >core</span>&nbsp; - <span title="Koin.ktor
io.insert-koin:koin-ktor:_
version.koin" style="text-decoration: underline;" >ktor</span>&nbsp; - <span title="Koin.slf4j
io.insert-koin:koin-logger-slf4j:_
version.koin" style="text-decoration: underline;" >slf4j</span>&nbsp; - <span title="Koin.junit4
io.insert-koin:koin-test-junit4:_
version.koin" style="text-decoration: underline;" >junit4</span>&nbsp; - <span title="Koin.junit5
io.insert-koin:koin-test-junit5:_
version.koin" style="text-decoration: underline;" >junit5</span>&nbsp; - <span title="Koin.test
io.insert-koin:koin-test:_
version.koin" style="text-decoration: underline;" >test</span>&nbsp;</td></tr>
                </table>
## [Kotlin.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Kotlin.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Kotlin</b></td><td><span title="Kotlin.scriptRuntime
org.jetbrains.kotlin:kotlin-script-runtime:_
version.kotlin" style="text-decoration: underline;" >scriptRuntime</span>&nbsp; - <span title="Kotlin.stdlib
org.jetbrains.kotlin:kotlin-stdlib:_
version.kotlin" style="text-decoration: underline;" >stdlib</span>&nbsp; - <span title="Kotlin.test
org.jetbrains.kotlin:kotlin-test:_
version.kotlin" style="text-decoration: underline;" >test</span>&nbsp;</td></tr>
<tr><td><b>Kotlin.stdlib</b></td><td><span title="Kotlin.stdlib.common
org.jetbrains.kotlin:kotlin-stdlib-common:_
version.kotlin" style="text-decoration: underline;" >common</span>&nbsp; - <span title="Kotlin.stdlib.jdk7
org.jetbrains.kotlin:kotlin-stdlib-jdk7:_
version.kotlin" style="text-decoration: underline;" >jdk7</span>&nbsp; - <span title="Kotlin.stdlib.jdk8
org.jetbrains.kotlin:kotlin-stdlib-jdk8:_
version.kotlin" style="text-decoration: underline;" >jdk8</span>&nbsp; - <span title="Kotlin.stdlib.js
org.jetbrains.kotlin:kotlin-stdlib-js:_
version.kotlin" style="text-decoration: underline;" >js</span>&nbsp;</td></tr>
<tr><td><b>Kotlin.test</b></td><td><span title="Kotlin.test.annotationsCommon
org.jetbrains.kotlin:kotlin-test-annotations-common:_
version.kotlin" style="text-decoration: underline;" >annotationsCommon</span>&nbsp; - <span title="Kotlin.test.common
org.jetbrains.kotlin:kotlin-test-common:_
version.kotlin" style="text-decoration: underline;" >common</span>&nbsp; - <span title="Kotlin.test.jsRunner
org.jetbrains.kotlin:kotlin-test-js-runner:_
version.kotlin" style="text-decoration: underline;" >jsRunner</span>&nbsp; - <span title="Kotlin.test.js
org.jetbrains.kotlin:kotlin-test-js:_
version.kotlin" style="text-decoration: underline;" >js</span>&nbsp; - <span title="Kotlin.test.junit5
org.jetbrains.kotlin:kotlin-test-junit5:_
version.kotlin" style="text-decoration: underline;" >junit5</span>&nbsp; - <span title="Kotlin.test.junit
org.jetbrains.kotlin:kotlin-test-junit:_
version.kotlin" style="text-decoration: underline;" >junit</span>&nbsp; - <span title="Kotlin.test.testng
org.jetbrains.kotlin:kotlin-test-testng:_
version.kotlin" style="text-decoration: underline;" >testng</span>&nbsp;</td></tr>
                </table>
## [KotlinX.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/KotlinX.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>KotlinX</b></td><td><span title="KotlinX.dataframe
org.jetbrains.kotlinx:dataframe:_
version.kotlinx.dataframe" style="text-decoration: underline;" >dataframe</span>&nbsp; - <span title="KotlinX.cli
org.jetbrains.kotlinx:kotlinx-cli:_
version.kotlinx.cli" style="text-decoration: underline;" >cli</span>&nbsp; - <span title="KotlinX.datetime
org.jetbrains.kotlinx:kotlinx-datetime:_
version.kotlinx.datetime" style="text-decoration: underline;" >datetime</span>&nbsp; - <span title="KotlinX.html
org.jetbrains.kotlinx:kotlinx-html:_
version.kotlinx.html" style="text-decoration: underline;" >html</span>&nbsp; - <span title="KotlinX.nodeJs
org.jetbrains.kotlinx:kotlinx-nodejs:_
version.kotlinx.nodejs" style="text-decoration: underline;" >nodeJs</span>&nbsp; - <span title="KotlinX.lincheck
org.jetbrains.kotlinx:lincheck:_
version.kotlinx.lincheck" style="text-decoration: underline;" >lincheck</span>&nbsp;</td></tr>
<tr><td><b>KotlinX.collections</b></td><td><span title="KotlinX.collections.immutableJvmOnly
org.jetbrains.kotlinx:kotlinx-collections-immutable-jvm:_
version.kotlinx.collections.immutable" style="text-decoration: underline;" >immutableJvmOnly</span>&nbsp; - <span title="KotlinX.collections.immutable
org.jetbrains.kotlinx:kotlinx-collections-immutable:_
version.kotlinx.collections.immutable" style="text-decoration: underline;" >immutable</span>&nbsp;</td></tr>
<tr><td><b>KotlinX.coroutines</b></td><td><span title="KotlinX.coroutines.android
org.jetbrains.kotlinx:kotlinx-coroutines-android:_
version.kotlinx.coroutines" style="text-decoration: underline;" >android</span>&nbsp; - <span title="KotlinX.coroutines.bom
org.jetbrains.kotlinx:kotlinx-coroutines-bom:_
version.kotlinx.coroutines" style="text-decoration: underline;" >bom</span>&nbsp; - <span title="KotlinX.coroutines.core
org.jetbrains.kotlinx:kotlinx-coroutines-core:_
version.kotlinx.coroutines" style="text-decoration: underline;" >core</span>&nbsp; - <span title="KotlinX.coroutines.debug
org.jetbrains.kotlinx:kotlinx-coroutines-debug:_
version.kotlinx.coroutines" style="text-decoration: underline;" >debug</span>&nbsp; - <span title="KotlinX.coroutines.guava
org.jetbrains.kotlinx:kotlinx-coroutines-guava:_
version.kotlinx.coroutines" style="text-decoration: underline;" >guava</span>&nbsp; - <span title="KotlinX.coroutines.javaFx
org.jetbrains.kotlinx:kotlinx-coroutines-javafx:_
version.kotlinx.coroutines" style="text-decoration: underline;" >javaFx</span>&nbsp; - <span title="KotlinX.coroutines.jdk8
org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:_
version.kotlinx.coroutines" style="text-decoration: underline;" >jdk8</span>&nbsp; - <span title="KotlinX.coroutines.jdk9
org.jetbrains.kotlinx:kotlinx-coroutines-jdk9:_
version.kotlinx.coroutines" style="text-decoration: underline;" >jdk9</span>&nbsp; - <span title="KotlinX.coroutines.playServices
org.jetbrains.kotlinx:kotlinx-coroutines-play-services:_
version.kotlinx.coroutines" style="text-decoration: underline;" >playServices</span>&nbsp; - <span title="KotlinX.coroutines.reactive
org.jetbrains.kotlinx:kotlinx-coroutines-reactive:_
version.kotlinx.coroutines" style="text-decoration: underline;" >reactive</span>&nbsp; - <span title="KotlinX.coroutines.reactor
org.jetbrains.kotlinx:kotlinx-coroutines-reactor:_
version.kotlinx.coroutines" style="text-decoration: underline;" >reactor</span>&nbsp; - <span title="KotlinX.coroutines.rx2
org.jetbrains.kotlinx:kotlinx-coroutines-rx2:_
version.kotlinx.coroutines" style="text-decoration: underline;" >rx2</span>&nbsp; - <span title="KotlinX.coroutines.rx3
org.jetbrains.kotlinx:kotlinx-coroutines-rx3:_
version.kotlinx.coroutines" style="text-decoration: underline;" >rx3</span>&nbsp; - <span title="KotlinX.coroutines.slf4j
org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:_
version.kotlinx.coroutines" style="text-decoration: underline;" >slf4j</span>&nbsp; - <span title="KotlinX.coroutines.swing
org.jetbrains.kotlinx:kotlinx-coroutines-swing:_
version.kotlinx.coroutines" style="text-decoration: underline;" >swing</span>&nbsp; - <span title="KotlinX.coroutines.test
org.jetbrains.kotlinx:kotlinx-coroutines-test:_
version.kotlinx.coroutines" style="text-decoration: underline;" >test</span>&nbsp;</td></tr>
<tr><td><b>KotlinX.dataframe</b></td><td><span title="KotlinX.dataframe.arrow
org.jetbrains.kotlinx:dataframe-arrow:_
version.kotlinx.dataframe" style="text-decoration: underline;" >arrow</span>&nbsp; - <span title="KotlinX.dataframe.core
org.jetbrains.kotlinx:dataframe-core:_
version.kotlinx.dataframe" style="text-decoration: underline;" >core</span>&nbsp; - <span title="KotlinX.dataframe.excel
org.jetbrains.kotlinx:dataframe-excel:_
version.kotlinx.dataframe" style="text-decoration: underline;" >excel</span>&nbsp; - <span title="KotlinX.dataframe.dataframe
org.jetbrains.kotlinx:dataframe:_
version.kotlinx.dataframe" style="text-decoration: underline;" >dataframe</span>&nbsp;</td></tr>
<tr><td><b>KotlinX.deeplearning</b></td><td><span title="KotlinX.deeplearning.api
org.jetbrains.kotlinx:kotlin-deeplearning-api:_
version.kotlinx.deeplearning" style="text-decoration: underline;" >api</span>&nbsp; - <span title="KotlinX.deeplearning.onnx
org.jetbrains.kotlinx:kotlin-deeplearning-onnx:_
version.kotlinx.deeplearning" style="text-decoration: underline;" >onnx</span>&nbsp; - <span title="KotlinX.deeplearning.visualization
org.jetbrains.kotlinx:kotlin-deeplearning-visualization:_
version.kotlinx.deeplearning" style="text-decoration: underline;" >visualization</span>&nbsp;</td></tr>
<tr><td><b>KotlinX.io</b></td><td><span title="KotlinX.io.jvm
org.jetbrains.kotlinx:kotlinx-io-jvm:_
version.kotlinx.io" style="text-decoration: underline;" >jvm</span>&nbsp;</td></tr>
<tr><td><b>KotlinX.lincheck</b></td><td><span title="KotlinX.lincheck.jvm
org.jetbrains.kotlinx:lincheck-jvm:_
version.kotlinx.lincheck" style="text-decoration: underline;" >jvm</span>&nbsp;</td></tr>
<tr><td><b>KotlinX.multik</b></td><td><span title="KotlinX.multik.api
org.jetbrains.kotlinx:multik-api:_
version.kotlinx.multik" style="text-decoration: underline;" >api</span>&nbsp; - <span title="KotlinX.multik.default
org.jetbrains.kotlinx:multik-default:_
version.kotlinx.multik" style="text-decoration: underline;" >default</span>&nbsp; - <span title="KotlinX.multik.jvm
org.jetbrains.kotlinx:multik-jvm:_
version.kotlinx.multik" style="text-decoration: underline;" >jvm</span>&nbsp; - <span title="KotlinX.multik.native
org.jetbrains.kotlinx:multik-native:_
version.kotlinx.multik" style="text-decoration: underline;" >native</span>&nbsp;</td></tr>
<tr><td><b>KotlinX.reflect</b></td><td><span title="KotlinX.reflect.lite
org.jetbrains.kotlinx:kotlinx.reflect.lite:_
version.kotlinx.reflect.lite" style="text-decoration: underline;" >lite</span>&nbsp;</td></tr>
<tr><td><b>KotlinX.serialization</b></td><td><span title="KotlinX.serialization.cbor
org.jetbrains.kotlinx:kotlinx-serialization-cbor:_
version.kotlinx.serialization" style="text-decoration: underline;" >cbor</span>&nbsp; - <span title="KotlinX.serialization.core
org.jetbrains.kotlinx:kotlinx-serialization-core:_
version.kotlinx.serialization" style="text-decoration: underline;" >core</span>&nbsp; - <span title="KotlinX.serialization.hocon
org.jetbrains.kotlinx:kotlinx-serialization-hocon:_
version.kotlinx.serialization" style="text-decoration: underline;" >hocon</span>&nbsp; - <span title="KotlinX.serialization.json
org.jetbrains.kotlinx:kotlinx-serialization-json:_
version.kotlinx.serialization" style="text-decoration: underline;" >json</span>&nbsp; - <span title="KotlinX.serialization.properties
org.jetbrains.kotlinx:kotlinx-serialization-properties:_
version.kotlinx.serialization" style="text-decoration: underline;" >properties</span>&nbsp; - <span title="KotlinX.serialization.protobuf
org.jetbrains.kotlinx:kotlinx-serialization-protobuf:_
version.kotlinx.serialization" style="text-decoration: underline;" >protobuf</span>&nbsp;</td></tr>
                </table>
## [Ktor.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Ktor.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Ktor</b></td><td><span title="Ktor.server
io.ktor:ktor-server:_
version.ktor" style="text-decoration: underline;" >server</span>&nbsp; - <span title="Ktor.testDispatcher
io.ktor:ktor-test-dispatcher:_
version.ktor" style="text-decoration: underline;" >testDispatcher</span>&nbsp; - <span title="Ktor.utils
io.ktor:ktor-utils:_
version.ktor" style="text-decoration: underline;" >utils</span>&nbsp;</td></tr>
<tr><td><b>Ktor.client</b></td><td><span title="Ktor.client.android
io.ktor:ktor-client-android:_
version.ktor" style="text-decoration: underline;" >android</span>&nbsp; - <span title="Ktor.client.apache
io.ktor:ktor-client-apache:_
version.ktor" style="text-decoration: underline;" >apache</span>&nbsp; - <span title="Ktor.client.auth
io.ktor:ktor-client-auth:_
version.ktor" style="text-decoration: underline;" >auth</span>&nbsp; - <span title="Ktor.client.cio
io.ktor:ktor-client-cio:_
version.ktor" style="text-decoration: underline;" >cio</span>&nbsp; - <span title="Ktor.client.contentNegotiationTests
io.ktor:ktor-client-content-negotiation-tests:_
version.ktor" style="text-decoration: underline;" >contentNegotiationTests</span>&nbsp; - <span title="Ktor.client.contentNegotiation
io.ktor:ktor-client-content-negotiation:_
version.ktor" style="text-decoration: underline;" >contentNegotiation</span>&nbsp; - <span title="Ktor.client.core
io.ktor:ktor-client-core:_
version.ktor" style="text-decoration: underline;" >core</span>&nbsp; - <span title="Ktor.client.curl
io.ktor:ktor-client-curl:_
version.ktor" style="text-decoration: underline;" >curl</span>&nbsp; - <span title="Ktor.client.darwin
io.ktor:ktor-client-darwin:_
version.ktor" style="text-decoration: underline;" >darwin</span>&nbsp; - <span title="Ktor.client.encoding
io.ktor:ktor-client-encoding:_
version.ktor" style="text-decoration: underline;" >encoding</span>&nbsp; - <span title="Ktor.client.gson
io.ktor:ktor-client-gson:_
version.ktor" style="text-decoration: underline;" >gson</span>&nbsp; - <span title="Ktor.client.jackson
io.ktor:ktor-client-jackson:_
version.ktor" style="text-decoration: underline;" >jackson</span>&nbsp; - <span title="Ktor.client.java
io.ktor:ktor-client-java:_
version.ktor" style="text-decoration: underline;" >java</span>&nbsp; - <span title="Ktor.client.jetty
io.ktor:ktor-client-jetty:_
version.ktor" style="text-decoration: underline;" >jetty</span>&nbsp; - <span title="Ktor.client.jsonTests
io.ktor:ktor-client-json-tests:_
version.ktor" style="text-decoration: underline;" >jsonTests</span>&nbsp; - <span title="Ktor.client.json
io.ktor:ktor-client-json:_
version.ktor" style="text-decoration: underline;" >json</span>&nbsp; - <span title="Ktor.client.logging
io.ktor:ktor-client-logging:_
version.ktor" style="text-decoration: underline;" >logging</span>&nbsp; - <span title="Ktor.client.mock
io.ktor:ktor-client-mock:_
version.ktor" style="text-decoration: underline;" >mock</span>&nbsp; - <span title="Ktor.client.okhttp
io.ktor:ktor-client-okhttp:_
version.ktor" style="text-decoration: underline;" >okhttp</span>&nbsp; - <span title="Ktor.client.resources
io.ktor:ktor-client-resources:_
version.ktor" style="text-decoration: underline;" >resources</span>&nbsp; - <span title="Ktor.client.serialization
io.ktor:ktor-client-serialization:_
version.ktor" style="text-decoration: underline;" >serialization</span>&nbsp; - <span title="Ktor.client.tests
io.ktor:ktor-client-tests:_
version.ktor" style="text-decoration: underline;" >tests</span>&nbsp;</td></tr>
<tr><td><b>Ktor.plugins</b></td><td><span title="Ktor.plugins.events
io.ktor:ktor-events:_
version.ktor" style="text-decoration: underline;" >events</span>&nbsp; - <span title="Ktor.plugins.httpCio
io.ktor:ktor-http-cio:_
version.ktor" style="text-decoration: underline;" >httpCio</span>&nbsp; - <span title="Ktor.plugins.http
io.ktor:ktor-http:_
version.ktor" style="text-decoration: underline;" >http</span>&nbsp; - <span title="Ktor.plugins.io
io.ktor:ktor-io:_
version.ktor" style="text-decoration: underline;" >io</span>&nbsp; - <span title="Ktor.plugins.networkTlsCertificates
io.ktor:ktor-network-tls-certificates:_
version.ktor" style="text-decoration: underline;" >networkTlsCertificates</span>&nbsp; - <span title="Ktor.plugins.networkTls
io.ktor:ktor-network-tls:_
version.ktor" style="text-decoration: underline;" >networkTls</span>&nbsp; - <span title="Ktor.plugins.network
io.ktor:ktor-network:_
version.ktor" style="text-decoration: underline;" >network</span>&nbsp; - <span title="Ktor.plugins.resources
io.ktor:ktor-resources:_
version.ktor" style="text-decoration: underline;" >resources</span>&nbsp; - <span title="Ktor.plugins.websocketSerialization
io.ktor:ktor-websocket-serialization:_
version.ktor" style="text-decoration: underline;" >websocketSerialization</span>&nbsp; - <span title="Ktor.plugins.websockets
io.ktor:ktor-websockets:_
version.ktor" style="text-decoration: underline;" >websockets</span>&nbsp;</td></tr>
<tr><td><b>Ktor.plugins.serialization</b></td><td><span title="Ktor.plugins.serialization.gson
io.ktor:ktor-serialization-gson:_
version.ktor" style="text-decoration: underline;" >gson</span>&nbsp; - <span title="Ktor.plugins.serialization.jackson
io.ktor:ktor-serialization-jackson:_
version.ktor" style="text-decoration: underline;" >jackson</span>&nbsp; - <span title="Ktor.plugins.serialization.kotlinxCbor
io.ktor:ktor-serialization-kotlinx-cbor:_
version.ktor" style="text-decoration: underline;" >kotlinxCbor</span>&nbsp; - <span title="Ktor.plugins.serialization.kotlinxJson
io.ktor:ktor-serialization-kotlinx-json:_
version.ktor" style="text-decoration: underline;" >kotlinxJson</span>&nbsp; - <span title="Ktor.plugins.serialization.kotlinxTests
io.ktor:ktor-serialization-kotlinx-tests:_
version.ktor" style="text-decoration: underline;" >kotlinxTests</span>&nbsp; - <span title="Ktor.plugins.serialization.kotlinxXml
io.ktor:ktor-serialization-kotlinx-xml:_
version.ktor" style="text-decoration: underline;" >kotlinxXml</span>&nbsp; - <span title="Ktor.plugins.serialization.kotlinx
io.ktor:ktor-serialization-kotlinx:_
version.ktor" style="text-decoration: underline;" >kotlinx</span>&nbsp; - <span title="Ktor.plugins.serialization.serialization
io.ktor:ktor-serialization:_
version.ktor" style="text-decoration: underline;" >serialization</span>&nbsp;</td></tr>
<tr><td><b>Ktor.server</b></td><td><span title="Ktor.server.authJwt
io.ktor:ktor-server-auth-jwt:_
version.ktor" style="text-decoration: underline;" >authJwt</span>&nbsp; - <span title="Ktor.server.authLdap
io.ktor:ktor-server-auth-ldap:_
version.ktor" style="text-decoration: underline;" >authLdap</span>&nbsp; - <span title="Ktor.server.auth
io.ktor:ktor-server-auth:_
version.ktor" style="text-decoration: underline;" >auth</span>&nbsp; - <span title="Ktor.server.autoHeadResponse
io.ktor:ktor-server-auto-head-response:_
version.ktor" style="text-decoration: underline;" >autoHeadResponse</span>&nbsp; - <span title="Ktor.server.cachingHeaders
io.ktor:ktor-server-caching-headers:_
version.ktor" style="text-decoration: underline;" >cachingHeaders</span>&nbsp; - <span title="Ktor.server.callId
io.ktor:ktor-server-call-id:_
version.ktor" style="text-decoration: underline;" >callId</span>&nbsp; - <span title="Ktor.server.callLogging
io.ktor:ktor-server-call-logging:_
version.ktor" style="text-decoration: underline;" >callLogging</span>&nbsp; - <span title="Ktor.server.cio
io.ktor:ktor-server-cio:_
version.ktor" style="text-decoration: underline;" >cio</span>&nbsp; - <span title="Ktor.server.compression
io.ktor:ktor-server-compression:_
version.ktor" style="text-decoration: underline;" >compression</span>&nbsp; - <span title="Ktor.server.conditionalHeaders
io.ktor:ktor-server-conditional-headers:_
version.ktor" style="text-decoration: underline;" >conditionalHeaders</span>&nbsp; - <span title="Ktor.server.contentNegotiation
io.ktor:ktor-server-content-negotiation:_
version.ktor" style="text-decoration: underline;" >contentNegotiation</span>&nbsp; - <span title="Ktor.server.core
io.ktor:ktor-server-core:_
version.ktor" style="text-decoration: underline;" >core</span>&nbsp; - <span title="Ktor.server.cors
io.ktor:ktor-server-cors:_
version.ktor" style="text-decoration: underline;" >cors</span>&nbsp; - <span title="Ktor.server.dataConversion
io.ktor:ktor-server-data-conversion:_
version.ktor" style="text-decoration: underline;" >dataConversion</span>&nbsp; - <span title="Ktor.server.defaultHeaders
io.ktor:ktor-server-default-headers:_
version.ktor" style="text-decoration: underline;" >defaultHeaders</span>&nbsp; - <span title="Ktor.server.doubleReceive
io.ktor:ktor-server-double-receive:_
version.ktor" style="text-decoration: underline;" >doubleReceive</span>&nbsp; - <span title="Ktor.server.forwardedHeader
io.ktor:ktor-server-forwarded-header:_
version.ktor" style="text-decoration: underline;" >forwardedHeader</span>&nbsp; - <span title="Ktor.server.freeMarker
io.ktor:ktor-server-freemarker:_
version.ktor" style="text-decoration: underline;" >freeMarker</span>&nbsp; - <span title="Ktor.server.hostCommon
io.ktor:ktor-server-host-common:_
version.ktor" style="text-decoration: underline;" >hostCommon</span>&nbsp; - <span title="Ktor.server.hsts
io.ktor:ktor-server-hsts:_
version.ktor" style="text-decoration: underline;" >hsts</span>&nbsp; - <span title="Ktor.server.htmlBuilder
io.ktor:ktor-server-html-builder:_
version.ktor" style="text-decoration: underline;" >htmlBuilder</span>&nbsp; - <span title="Ktor.server.httpRedirect
io.ktor:ktor-server-http-redirect:_
version.ktor" style="text-decoration: underline;" >httpRedirect</span>&nbsp; - <span title="Ktor.server.jetty
io.ktor:ktor-server-jetty:_
version.ktor" style="text-decoration: underline;" >jetty</span>&nbsp; - <span title="Ktor.server.jte
io.ktor:ktor-server-jte:_
version.ktor" style="text-decoration: underline;" >jte</span>&nbsp; - <span title="Ktor.server.locations
io.ktor:ktor-server-locations:_
version.ktor" style="text-decoration: underline;" >locations</span>&nbsp; - <span title="Ktor.server.methodOverride
io.ktor:ktor-server-method-override:_
version.ktor" style="text-decoration: underline;" >methodOverride</span>&nbsp; - <span title="Ktor.server.metricsMicrometer
io.ktor:ktor-server-metrics-micrometer:_
version.ktor" style="text-decoration: underline;" >metricsMicrometer</span>&nbsp; - <span title="Ktor.server.metrics
io.ktor:ktor-server-metrics:_
version.ktor" style="text-decoration: underline;" >metrics</span>&nbsp; - <span title="Ktor.server.mustache
io.ktor:ktor-server-mustache:_
version.ktor" style="text-decoration: underline;" >mustache</span>&nbsp; - <span title="Ktor.server.netty
io.ktor:ktor-server-netty:_
version.ktor" style="text-decoration: underline;" >netty</span>&nbsp; - <span title="Ktor.server.partialContent
io.ktor:ktor-server-partial-content:_
version.ktor" style="text-decoration: underline;" >partialContent</span>&nbsp; - <span title="Ktor.server.pebble
io.ktor:ktor-server-pebble:_
version.ktor" style="text-decoration: underline;" >pebble</span>&nbsp; - <span title="Ktor.server.resources
io.ktor:ktor-server-resources:_
version.ktor" style="text-decoration: underline;" >resources</span>&nbsp; - <span title="Ktor.server.servlet
io.ktor:ktor-server-servlet:_
version.ktor" style="text-decoration: underline;" >servlet</span>&nbsp; - <span title="Ktor.server.sessions
io.ktor:ktor-server-sessions:_
version.ktor" style="text-decoration: underline;" >sessions</span>&nbsp; - <span title="Ktor.server.statusPages
io.ktor:ktor-server-status-pages:_
version.ktor" style="text-decoration: underline;" >statusPages</span>&nbsp; - <span title="Ktor.server.testHost
io.ktor:ktor-server-test-host:_
version.ktor" style="text-decoration: underline;" >testHost</span>&nbsp; - <span title="Ktor.server.testSuites
io.ktor:ktor-server-test-suites:_
version.ktor" style="text-decoration: underline;" >testSuites</span>&nbsp; - <span title="Ktor.server.thymeleaf
io.ktor:ktor-server-thymeleaf:_
version.ktor" style="text-decoration: underline;" >thymeleaf</span>&nbsp; - <span title="Ktor.server.tomcat
io.ktor:ktor-server-tomcat:_
version.ktor" style="text-decoration: underline;" >tomcat</span>&nbsp; - <span title="Ktor.server.velocity
io.ktor:ktor-server-velocity:_
version.ktor" style="text-decoration: underline;" >velocity</span>&nbsp; - <span title="Ktor.server.webjars
io.ktor:ktor-server-webjars:_
version.ktor" style="text-decoration: underline;" >webjars</span>&nbsp; - <span title="Ktor.server.websockets
io.ktor:ktor-server-websockets:_
version.ktor" style="text-decoration: underline;" >websockets</span>&nbsp;</td></tr>
                </table>
## [Orchid.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Orchid.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Orchid</b></td><td><span title="Orchid.core
io.github.javaeden.orchid:OrchidCore:_
version.orchid" style="text-decoration: underline;" >core</span>&nbsp; - <span title="Orchid.test
io.github.javaeden.orchid:OrchidTest:_
version.orchid" style="text-decoration: underline;" >test</span>&nbsp;</td></tr>
<tr><td><b>Orchid.bundles</b></td><td><span title="Orchid.bundles.all
io.github.javaeden.orchid:OrchidAll:_
version.orchid" style="text-decoration: underline;" >all</span>&nbsp; - <span title="Orchid.bundles.blog
io.github.javaeden.orchid:OrchidBlog:_
version.orchid" style="text-decoration: underline;" >blog</span>&nbsp; - <span title="Orchid.bundles.docs
io.github.javaeden.orchid:OrchidDocs:_
version.orchid" style="text-decoration: underline;" >docs</span>&nbsp; - <span title="Orchid.bundles.languagePack
io.github.javaeden.orchid:OrchidLanguagePack:_
version.orchid" style="text-decoration: underline;" >languagePack</span>&nbsp;</td></tr>
<tr><td><b>Orchid.plugins</b></td><td><span title="Orchid.plugins.asciidoc
io.github.javaeden.orchid:OrchidAsciidoc:_
version.orchid" style="text-decoration: underline;" >asciidoc</span>&nbsp; - <span title="Orchid.plugins.azure
io.github.javaeden.orchid:OrchidAzure:_
version.orchid" style="text-decoration: underline;" >azure</span>&nbsp; - <span title="Orchid.plugins.bible
io.github.javaeden.orchid:OrchidBible:_
version.orchid" style="text-decoration: underline;" >bible</span>&nbsp; - <span title="Orchid.plugins.bitbucket
io.github.javaeden.orchid:OrchidBitbucket:_
version.orchid" style="text-decoration: underline;" >bitbucket</span>&nbsp; - <span title="Orchid.plugins.changelog
io.github.javaeden.orchid:OrchidChangelog:_
version.orchid" style="text-decoration: underline;" >changelog</span>&nbsp; - <span title="Orchid.plugins.diagrams
io.github.javaeden.orchid:OrchidDiagrams:_
version.orchid" style="text-decoration: underline;" >diagrams</span>&nbsp; - <span title="Orchid.plugins.forms
io.github.javaeden.orchid:OrchidForms:_
version.orchid" style="text-decoration: underline;" >forms</span>&nbsp; - <span title="Orchid.plugins.github
io.github.javaeden.orchid:OrchidGithub:_
version.orchid" style="text-decoration: underline;" >github</span>&nbsp; - <span title="Orchid.plugins.gitlab
io.github.javaeden.orchid:OrchidGitlab:_
version.orchid" style="text-decoration: underline;" >gitlab</span>&nbsp; - <span title="Orchid.plugins.groovydoc
io.github.javaeden.orchid:OrchidGroovydoc:_
version.orchid" style="text-decoration: underline;" >groovydoc</span>&nbsp; - <span title="Orchid.plugins.javadoc
io.github.javaeden.orchid:OrchidJavadoc:_
version.orchid" style="text-decoration: underline;" >javadoc</span>&nbsp; - <span title="Orchid.plugins.kss
io.github.javaeden.orchid:OrchidKSS:_
version.orchid" style="text-decoration: underline;" >kss</span>&nbsp; - <span title="Orchid.plugins.kotlindoc
io.github.javaeden.orchid:OrchidKotlindoc:_
version.orchid" style="text-decoration: underline;" >kotlindoc</span>&nbsp; - <span title="Orchid.plugins.netlify
io.github.javaeden.orchid:OrchidNetlify:_
version.orchid" style="text-decoration: underline;" >netlify</span>&nbsp; - <span title="Orchid.plugins.netlifyCMS
io.github.javaeden.orchid:OrchidNetlifyCMS:_
version.orchid" style="text-decoration: underline;" >netlifyCMS</span>&nbsp; - <span title="Orchid.plugins.pages
io.github.javaeden.orchid:OrchidPages:_
version.orchid" style="text-decoration: underline;" >pages</span>&nbsp; - <span title="Orchid.plugins.pluginDocs
io.github.javaeden.orchid:OrchidPluginDocs:_
version.orchid" style="text-decoration: underline;" >pluginDocs</span>&nbsp; - <span title="Orchid.plugins.posts
io.github.javaeden.orchid:OrchidPosts:_
version.orchid" style="text-decoration: underline;" >posts</span>&nbsp; - <span title="Orchid.plugins.presentations
io.github.javaeden.orchid:OrchidPresentations:_
version.orchid" style="text-decoration: underline;" >presentations</span>&nbsp; - <span title="Orchid.plugins.search
io.github.javaeden.orchid:OrchidSearch:_
version.orchid" style="text-decoration: underline;" >search</span>&nbsp; - <span title="Orchid.plugins.sourceDoc
io.github.javaeden.orchid:OrchidSourceDoc:_
version.orchid" style="text-decoration: underline;" >sourceDoc</span>&nbsp; - <span title="Orchid.plugins.swagger
io.github.javaeden.orchid:OrchidSwagger:_
version.orchid" style="text-decoration: underline;" >swagger</span>&nbsp; - <span title="Orchid.plugins.swiftdoc
io.github.javaeden.orchid:OrchidSwiftdoc:_
version.orchid" style="text-decoration: underline;" >swiftdoc</span>&nbsp; - <span title="Orchid.plugins.syntaxHighlighter
io.github.javaeden.orchid:OrchidSyntaxHighlighter:_
version.orchid" style="text-decoration: underline;" >syntaxHighlighter</span>&nbsp; - <span title="Orchid.plugins.taxonomies
io.github.javaeden.orchid:OrchidTaxonomies:_
version.orchid" style="text-decoration: underline;" >taxonomies</span>&nbsp; - <span title="Orchid.plugins.wiki
io.github.javaeden.orchid:OrchidWiki:_
version.orchid" style="text-decoration: underline;" >wiki</span>&nbsp; - <span title="Orchid.plugins.writersBlocks
io.github.javaeden.orchid:OrchidWritersBlocks:_
version.orchid" style="text-decoration: underline;" >writersBlocks</span>&nbsp;</td></tr>
<tr><td><b>Orchid.themes</b></td><td><span title="Orchid.themes.bsDoc
io.github.javaeden.orchid:OrchidBsDoc:_
version.orchid" style="text-decoration: underline;" >bsDoc</span>&nbsp; - <span title="Orchid.themes.copper
io.github.javaeden.orchid:OrchidCopper:_
version.orchid" style="text-decoration: underline;" >copper</span>&nbsp; - <span title="Orchid.themes.editorial
io.github.javaeden.orchid:OrchidEditorial:_
version.orchid" style="text-decoration: underline;" >editorial</span>&nbsp; - <span title="Orchid.themes.futureImperfect
io.github.javaeden.orchid:OrchidFutureImperfect:_
version.orchid" style="text-decoration: underline;" >futureImperfect</span>&nbsp;</td></tr>
                </table>
## [ReactiveX.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/ReactiveX.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>ReactiveX</b></td><td><span title="ReactiveX.rxJava2
io.reactivex.rxjava2:rxjava:_
version.rxjava2.rxjava" style="text-decoration: underline;" >rxJava2</span>&nbsp; - <span title="ReactiveX.rxJava3
io.reactivex.rxjava3:rxjava:_
version.rxjava3.rxjava" style="text-decoration: underline;" >rxJava3</span>&nbsp;</td></tr>
<tr><td><b>ReactiveX.rxJava2</b></td><td><span title="ReactiveX.rxJava2.rxAndroid
io.reactivex.rxjava2:rxandroid:_
version.rxjava2.rxandroid" style="text-decoration: underline;" >rxAndroid</span>&nbsp; - <span title="ReactiveX.rxJava2.rxKotlin
io.reactivex.rxjava2:rxkotlin:_
version.rxjava2.rxkotlin" style="text-decoration: underline;" >rxKotlin</span>&nbsp;</td></tr>
<tr><td><b>ReactiveX.rxJava3</b></td><td><span title="ReactiveX.rxJava3.rxAndroid
io.reactivex.rxjava3:rxandroid:_
version.rxjava3.rxandroid" style="text-decoration: underline;" >rxAndroid</span>&nbsp; - <span title="ReactiveX.rxJava3.rxKotlin
io.reactivex.rxjava3:rxkotlin:_
version.rxjava3.rxkotlin" style="text-decoration: underline;" >rxKotlin</span>&nbsp;</td></tr>
                </table>
## [RickClephas.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/RickClephas.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>RickClephas.nativeCoroutines</b></td><td><span title="RickClephas.nativeCoroutines.annotations
com.rickclephas.kmp:kmp-nativecoroutines-annotations:_
version.rickclephas.nativecoroutines" style="text-decoration: underline;" >annotations</span>&nbsp; - <span title="RickClephas.nativeCoroutines.compilerEmbeddable
com.rickclephas.kmp:kmp-nativecoroutines-compiler-embeddable:_
version.rickclephas.nativecoroutines" style="text-decoration: underline;" >compilerEmbeddable</span>&nbsp; - <span title="RickClephas.nativeCoroutines.compiler
com.rickclephas.kmp:kmp-nativecoroutines-compiler:_
version.rickclephas.nativecoroutines" style="text-decoration: underline;" >compiler</span>&nbsp; - <span title="RickClephas.nativeCoroutines.core
com.rickclephas.kmp:kmp-nativecoroutines-core:_
version.rickclephas.nativecoroutines" style="text-decoration: underline;" >core</span>&nbsp; - <span title="RickClephas.nativeCoroutines.gradlePlugin
com.rickclephas.kmp:kmp-nativecoroutines-gradle-plugin:_
version.rickclephas.nativecoroutines" style="text-decoration: underline;" >gradlePlugin</span>&nbsp;</td></tr>
                </table>
## [RussHWolf.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/RussHWolf.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>RussHWolf</b></td><td><span title="RussHWolf.multiplatformSettings
com.russhwolf:multiplatform-settings:_
version.multiplatform-settings" style="text-decoration: underline;" >multiplatformSettings</span>&nbsp;</td></tr>
<tr><td><b>RussHWolf.multiplatformSettings</b></td><td><span title="RussHWolf.multiplatformSettings.coroutinesNativeMt
com.russhwolf:multiplatform-settings-coroutines-native-mt:_
version.multiplatform-settings" style="text-decoration: underline;" >coroutinesNativeMt</span>&nbsp; - <span title="RussHWolf.multiplatformSettings.coroutines
com.russhwolf:multiplatform-settings-coroutines:_
version.multiplatform-settings" style="text-decoration: underline;" >coroutines</span>&nbsp; - <span title="RussHWolf.multiplatformSettings.dataStore
com.russhwolf:multiplatform-settings-datastore:_
version.multiplatform-settings" style="text-decoration: underline;" >dataStore</span>&nbsp; - <span title="RussHWolf.multiplatformSettings.noArg
com.russhwolf:multiplatform-settings-no-arg:_
version.multiplatform-settings" style="text-decoration: underline;" >noArg</span>&nbsp; - <span title="RussHWolf.multiplatformSettings.serialization
com.russhwolf:multiplatform-settings-serialization:_
version.multiplatform-settings" style="text-decoration: underline;" >serialization</span>&nbsp; - <span title="RussHWolf.multiplatformSettings.test
com.russhwolf:multiplatform-settings-test:_
version.multiplatform-settings" style="text-decoration: underline;" >test</span>&nbsp; - <span title="RussHWolf.multiplatformSettings.settings
com.russhwolf:multiplatform-settings:_
version.multiplatform-settings" style="text-decoration: underline;" >settings</span>&nbsp;</td></tr>
                </table>
## [Splitties.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Splitties.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Splitties</b></td><td><span title="Splitties.activities
com.louiscad.splitties:splitties-activities:_
version.splitties" style="text-decoration: underline;" >activities</span>&nbsp; - <span title="Splitties.alertdialogAppcompatCoroutines
com.louiscad.splitties:splitties-alertdialog-appcompat-coroutines:_
version.splitties" style="text-decoration: underline;" >alertdialogAppcompatCoroutines</span>&nbsp; - <span title="Splitties.alertdialogAppcompat
com.louiscad.splitties:splitties-alertdialog-appcompat:_
version.splitties" style="text-decoration: underline;" >alertdialogAppcompat</span>&nbsp; - <span title="Splitties.alertdialogMaterial
com.louiscad.splitties:splitties-alertdialog-material:_
version.splitties" style="text-decoration: underline;" >alertdialogMaterial</span>&nbsp; - <span title="Splitties.alertdialog
com.louiscad.splitties:splitties-alertdialog:_
version.splitties" style="text-decoration: underline;" >alertdialog</span>&nbsp; - <span title="Splitties.appctx
com.louiscad.splitties:splitties-appctx:_
version.splitties" style="text-decoration: underline;" >appctx</span>&nbsp; - <span title="Splitties.archLifecycle
com.louiscad.splitties:splitties-arch-lifecycle:_
version.splitties" style="text-decoration: underline;" >archLifecycle</span>&nbsp; - <span title="Splitties.archRoom
com.louiscad.splitties:splitties-arch-room:_
version.splitties" style="text-decoration: underline;" >archRoom</span>&nbsp; - <span title="Splitties.bitflags
com.louiscad.splitties:splitties-bitflags:_
version.splitties" style="text-decoration: underline;" >bitflags</span>&nbsp; - <span title="Splitties.bundle
com.louiscad.splitties:splitties-bundle:_
version.splitties" style="text-decoration: underline;" >bundle</span>&nbsp; - <span title="Splitties.checkedlazy
com.louiscad.splitties:splitties-checkedlazy:_
version.splitties" style="text-decoration: underline;" >checkedlazy</span>&nbsp; - <span title="Splitties.collections
com.louiscad.splitties:splitties-collections:_
version.splitties" style="text-decoration: underline;" >collections</span>&nbsp; - <span title="Splitties.coroutines
com.louiscad.splitties:splitties-coroutines:_
version.splitties" style="text-decoration: underline;" >coroutines</span>&nbsp; - <span title="Splitties.dimensions
com.louiscad.splitties:splitties-dimensions:_
version.splitties" style="text-decoration: underline;" >dimensions</span>&nbsp; - <span title="Splitties.exceptions
com.louiscad.splitties:splitties-exceptions:_
version.splitties" style="text-decoration: underline;" >exceptions</span>&nbsp; - <span title="Splitties.fragmentargs
com.louiscad.splitties:splitties-fragmentargs:_
version.splitties" style="text-decoration: underline;" >fragmentargs</span>&nbsp; - <span title="Splitties.fragments
com.louiscad.splitties:splitties-fragments:_
version.splitties" style="text-decoration: underline;" >fragments</span>&nbsp; - <span title="Splitties.initprovider
com.louiscad.splitties:splitties-initprovider:_
version.splitties" style="text-decoration: underline;" >initprovider</span>&nbsp; - <span title="Splitties.intents
com.louiscad.splitties:splitties-intents:_
version.splitties" style="text-decoration: underline;" >intents</span>&nbsp; - <span title="Splitties.lifecycleCoroutines
com.louiscad.splitties:splitties-lifecycle-coroutines:_
version.splitties" style="text-decoration: underline;" >lifecycleCoroutines</span>&nbsp; - <span title="Splitties.mainhandler
com.louiscad.splitties:splitties-mainhandler:_
version.splitties" style="text-decoration: underline;" >mainhandler</span>&nbsp; - <span title="Splitties.mainthread
com.louiscad.splitties:splitties-mainthread:_
version.splitties" style="text-decoration: underline;" >mainthread</span>&nbsp; - <span title="Splitties.materialColors
com.louiscad.splitties:splitties-material-colors:_
version.splitties" style="text-decoration: underline;" >materialColors</span>&nbsp; - <span title="Splitties.materialLists
com.louiscad.splitties:splitties-material-lists:_
version.splitties" style="text-decoration: underline;" >materialLists</span>&nbsp; - <span title="Splitties.permissions
com.louiscad.splitties:splitties-permissions:_
version.splitties" style="text-decoration: underline;" >permissions</span>&nbsp; - <span title="Splitties.preferences
com.louiscad.splitties:splitties-preferences:_
version.splitties" style="text-decoration: underline;" >preferences</span>&nbsp; - <span title="Splitties.resources
com.louiscad.splitties:splitties-resources:_
version.splitties" style="text-decoration: underline;" >resources</span>&nbsp; - <span title="Splitties.snackbar
com.louiscad.splitties:splitties-snackbar:_
version.splitties" style="text-decoration: underline;" >snackbar</span>&nbsp; - <span title="Splitties.stethoInit
com.louiscad.splitties:splitties-stetho-init:_
version.splitties" style="text-decoration: underline;" >stethoInit</span>&nbsp; - <span title="Splitties.systemservices
com.louiscad.splitties:splitties-systemservices:_
version.splitties" style="text-decoration: underline;" >systemservices</span>&nbsp; - <span title="Splitties.toast
com.louiscad.splitties:splitties-toast:_
version.splitties" style="text-decoration: underline;" >toast</span>&nbsp; - <span title="Splitties.typesaferecyclerview
com.louiscad.splitties:splitties-typesaferecyclerview:_
version.splitties" style="text-decoration: underline;" >typesaferecyclerview</span>&nbsp; - <span title="Splitties.viewsAppcompat
com.louiscad.splitties:splitties-views-appcompat:_
version.splitties" style="text-decoration: underline;" >viewsAppcompat</span>&nbsp; - <span title="Splitties.viewsCardview
com.louiscad.splitties:splitties-views-cardview:_
version.splitties" style="text-decoration: underline;" >viewsCardview</span>&nbsp; - <span title="Splitties.viewsCoroutinesMaterial
com.louiscad.splitties:splitties-views-coroutines-material:_
version.splitties" style="text-decoration: underline;" >viewsCoroutinesMaterial</span>&nbsp; - <span title="Splitties.viewsCoroutines
com.louiscad.splitties:splitties-views-coroutines:_
version.splitties" style="text-decoration: underline;" >viewsCoroutines</span>&nbsp; - <span title="Splitties.viewsDslAppcompat
com.louiscad.splitties:splitties-views-dsl-appcompat:_
version.splitties" style="text-decoration: underline;" >viewsDslAppcompat</span>&nbsp; - <span title="Splitties.viewsDslConstraintlayout
com.louiscad.splitties:splitties-views-dsl-constraintlayout:_
version.splitties" style="text-decoration: underline;" >viewsDslConstraintlayout</span>&nbsp; - <span title="Splitties.viewsDslCoordinatorlayout
com.louiscad.splitties:splitties-views-dsl-coordinatorlayout:_
version.splitties" style="text-decoration: underline;" >viewsDslCoordinatorlayout</span>&nbsp; - <span title="Splitties.viewsDslMaterial
com.louiscad.splitties:splitties-views-dsl-material:_
version.splitties" style="text-decoration: underline;" >viewsDslMaterial</span>&nbsp; - <span title="Splitties.viewsDslRecyclerview
com.louiscad.splitties:splitties-views-dsl-recyclerview:_
version.splitties" style="text-decoration: underline;" >viewsDslRecyclerview</span>&nbsp; - <span title="Splitties.viewsDsl
com.louiscad.splitties:splitties-views-dsl:_
version.splitties" style="text-decoration: underline;" >viewsDsl</span>&nbsp; - <span title="Splitties.viewsMaterial
com.louiscad.splitties:splitties-views-material:_
version.splitties" style="text-decoration: underline;" >viewsMaterial</span>&nbsp; - <span title="Splitties.viewsRecyclerview
com.louiscad.splitties:splitties-views-recyclerview:_
version.splitties" style="text-decoration: underline;" >viewsRecyclerview</span>&nbsp; - <span title="Splitties.viewsSelectableAppcompat
com.louiscad.splitties:splitties-views-selectable-appcompat:_
version.splitties" style="text-decoration: underline;" >viewsSelectableAppcompat</span>&nbsp; - <span title="Splitties.viewsSelectableConstraintlayout
com.louiscad.splitties:splitties-views-selectable-constraintlayout:_
version.splitties" style="text-decoration: underline;" >viewsSelectableConstraintlayout</span>&nbsp; - <span title="Splitties.viewsSelectable
com.louiscad.splitties:splitties-views-selectable:_
version.splitties" style="text-decoration: underline;" >viewsSelectable</span>&nbsp; - <span title="Splitties.views
com.louiscad.splitties:splitties-views:_
version.splitties" style="text-decoration: underline;" >views</span>&nbsp;</td></tr>
<tr><td><b>Splitties.pack</b></td><td><span title="Splitties.pack.appCompatWithViewsDsl
com.louiscad.splitties:splitties-fun-pack-android-appcompat-with-views-dsl:_
version.splitties" style="text-decoration: underline;" >appCompatWithViewsDsl</span>&nbsp; - <span title="Splitties.pack.appCompat
com.louiscad.splitties:splitties-fun-pack-android-appcompat:_
version.splitties" style="text-decoration: underline;" >appCompat</span>&nbsp; - <span title="Splitties.pack.androidBaseWithViewsDsl
com.louiscad.splitties:splitties-fun-pack-android-base-with-views-dsl:_
version.splitties" style="text-decoration: underline;" >androidBaseWithViewsDsl</span>&nbsp; - <span title="Splitties.pack.androidBase
com.louiscad.splitties:splitties-fun-pack-android-base:_
version.splitties" style="text-decoration: underline;" >androidBase</span>&nbsp; - <span title="Splitties.pack.androidMdcWithViewsDsl
com.louiscad.splitties:splitties-fun-pack-android-material-components-with-views-dsl:_
version.splitties" style="text-decoration: underline;" >androidMdcWithViewsDsl</span>&nbsp; - <span title="Splitties.pack.androidMdc
com.louiscad.splitties:splitties-fun-pack-android-material-components:_
version.splitties" style="text-decoration: underline;" >androidMdc</span>&nbsp;</td></tr>
                </table>
## [Spring.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Spring.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Spring</b></td><td><span title="Spring.rabbitTest
org.springframework.amqp:spring-rabbit-test:_
version.NO-RULE" style="text-decoration: underline;" >rabbitTest</span>&nbsp; - <span title="Spring.batchTest
org.springframework.batch:spring-batch-test:_
version.NO-RULE" style="text-decoration: underline;" >batchTest</span>&nbsp; - <span title="Spring.geode
org.springframework.geode:spring-geode-starter:_
version.NO-RULE" style="text-decoration: underline;" >geode</span>&nbsp; - <span title="Spring.kafkaTest
org.springframework.kafka:spring-kafka-test:_
version.NO-RULE" style="text-decoration: underline;" >kafkaTest</span>&nbsp; - <span title="Spring.kafka
org.springframework.kafka:spring-kafka:_
version.NO-RULE" style="text-decoration: underline;" >kafka</span>&nbsp; - <span title="Spring.springRestdocsWebtestclient
org.springframework.restdocs:spring-restdocs-webtestclient:_
version.NO-RULE" style="text-decoration: underline;" >springRestdocsWebtestclient</span>&nbsp;</td></tr>
<tr><td><b>Spring.boms</b></td><td><span title="Spring.boms.dependencies
org.springframework.boot:spring-boot-dependencies:_
version.NO-RULE" style="text-decoration: underline;" >dependencies</span>&nbsp; - <span title="Spring.boms.springCloud
org.springframework.cloud:spring-cloud-dependencies:_
version.NO-RULE" style="text-decoration: underline;" >springCloud</span>&nbsp; - <span title="Spring.boms.geode
org.springframework.geode:spring-geode-bom:_
version.NO-RULE" style="text-decoration: underline;" >geode</span>&nbsp;</td></tr>
<tr><td><b>Spring.boot</b></td><td><span title="Spring.boot.configurationProcessor
org.springframework.boot:spring-boot-configuration-processor:_
version.NO-RULE" style="text-decoration: underline;" >configurationProcessor</span>&nbsp; - <span title="Spring.boot.devTools
org.springframework.boot:spring-boot-devtools:_
version.NO-RULE" style="text-decoration: underline;" >devTools</span>&nbsp; - <span title="Spring.boot.activemq
org.springframework.boot:spring-boot-starter-activemq:_
version.NO-RULE" style="text-decoration: underline;" >activemq</span>&nbsp; - <span title="Spring.boot.actuator
org.springframework.boot:spring-boot-starter-actuator:_
version.NO-RULE" style="text-decoration: underline;" >actuator</span>&nbsp; - <span title="Spring.boot.amqp
org.springframework.boot:spring-boot-starter-amqp:_
version.NO-RULE" style="text-decoration: underline;" >amqp</span>&nbsp; - <span title="Spring.boot.artemis
org.springframework.boot:spring-boot-starter-artemis:_
version.NO-RULE" style="text-decoration: underline;" >artemis</span>&nbsp; - <span title="Spring.boot.batch
org.springframework.boot:spring-boot-starter-batch:_
version.NO-RULE" style="text-decoration: underline;" >batch</span>&nbsp; - <span title="Spring.boot.cache
org.springframework.boot:spring-boot-starter-cache:_
version.NO-RULE" style="text-decoration: underline;" >cache</span>&nbsp; - <span title="Spring.boot.freemarker
org.springframework.boot:spring-boot-starter-freemarker:_
version.NO-RULE" style="text-decoration: underline;" >freemarker</span>&nbsp; - <span title="Spring.boot.groovyTemplates
org.springframework.boot:spring-boot-starter-groovy-templates:_
version.NO-RULE" style="text-decoration: underline;" >groovyTemplates</span>&nbsp; - <span title="Spring.boot.hateoas
org.springframework.boot:spring-boot-starter-hateoas:_
version.NO-RULE" style="text-decoration: underline;" >hateoas</span>&nbsp; - <span title="Spring.boot.integration
org.springframework.boot:spring-boot-starter-integration:_
version.NO-RULE" style="text-decoration: underline;" >integration</span>&nbsp; - <span title="Spring.boot.jdbc
org.springframework.boot:spring-boot-starter-jdbc:_
version.NO-RULE" style="text-decoration: underline;" >jdbc</span>&nbsp; - <span title="Spring.boot.jersey
org.springframework.boot:spring-boot-starter-jersey:_
version.NO-RULE" style="text-decoration: underline;" >jersey</span>&nbsp; - <span title="Spring.boot.jooq
org.springframework.boot:spring-boot-starter-jooq:_
version.NO-RULE" style="text-decoration: underline;" >jooq</span>&nbsp; - <span title="Spring.boot.mail
org.springframework.boot:spring-boot-starter-mail:_
version.NO-RULE" style="text-decoration: underline;" >mail</span>&nbsp; - <span title="Spring.boot.mustache
org.springframework.boot:spring-boot-starter-mustache:_
version.NO-RULE" style="text-decoration: underline;" >mustache</span>&nbsp; - <span title="Spring.boot.oauth2Client
org.springframework.boot:spring-boot-starter-oauth2-client:_
version.NO-RULE" style="text-decoration: underline;" >oauth2Client</span>&nbsp; - <span title="Spring.boot.oauth2ResourceServer
org.springframework.boot:spring-boot-starter-oauth2-resource-server:_
version.NO-RULE" style="text-decoration: underline;" >oauth2ResourceServer</span>&nbsp; - <span title="Spring.boot.quartz
org.springframework.boot:spring-boot-starter-quartz:_
version.NO-RULE" style="text-decoration: underline;" >quartz</span>&nbsp; - <span title="Spring.boot.rsocket
org.springframework.boot:spring-boot-starter-rsocket:_
version.NO-RULE" style="text-decoration: underline;" >rsocket</span>&nbsp; - <span title="Spring.boot.security
org.springframework.boot:spring-boot-starter-security:_
version.NO-RULE" style="text-decoration: underline;" >security</span>&nbsp; - <span title="Spring.boot.test
org.springframework.boot:spring-boot-starter-test:_
version.NO-RULE" style="text-decoration: underline;" >test</span>&nbsp; - <span title="Spring.boot.thymeleaf
org.springframework.boot:spring-boot-starter-thymeleaf:_
version.NO-RULE" style="text-decoration: underline;" >thymeleaf</span>&nbsp; - <span title="Spring.boot.validation
org.springframework.boot:spring-boot-starter-validation:_
version.NO-RULE" style="text-decoration: underline;" >validation</span>&nbsp; - <span title="Spring.boot.webServices
org.springframework.boot:spring-boot-starter-web-services:_
version.NO-RULE" style="text-decoration: underline;" >webServices</span>&nbsp; - <span title="Spring.boot.webflux
org.springframework.boot:spring-boot-starter-webflux:_
version.NO-RULE" style="text-decoration: underline;" >webflux</span>&nbsp; - <span title="Spring.boot.websocket
org.springframework.boot:spring-boot-starter-websocket:_
version.NO-RULE" style="text-decoration: underline;" >websocket</span>&nbsp;</td></tr>
<tr><td><b>Spring.boot.data</b></td><td><span title="Spring.boot.data.cassandraReactive
org.springframework.boot:spring-boot-starter-data-cassandra-reactive:_
version.NO-RULE" style="text-decoration: underline;" >cassandraReactive</span>&nbsp; - <span title="Spring.boot.data.cassandra
org.springframework.boot:spring-boot-starter-data-cassandra:_
version.NO-RULE" style="text-decoration: underline;" >cassandra</span>&nbsp; - <span title="Spring.boot.data.couchbase_reactive
org.springframework.boot:spring-boot-starter-data-couchbase-reactive:_
version.NO-RULE" style="text-decoration: underline;" >couchbase_reactive</span>&nbsp; - <span title="Spring.boot.data.couchbase
org.springframework.boot:spring-boot-starter-data-couchbase:_
version.NO-RULE" style="text-decoration: underline;" >couchbase</span>&nbsp; - <span title="Spring.boot.data.elasticsearch
org.springframework.boot:spring-boot-starter-data-elasticsearch:_
version.NO-RULE" style="text-decoration: underline;" >elasticsearch</span>&nbsp; - <span title="Spring.boot.data.jdbc
org.springframework.boot:spring-boot-starter-data-jdbc:_
version.NO-RULE" style="text-decoration: underline;" >jdbc</span>&nbsp; - <span title="Spring.boot.data.jpa
org.springframework.boot:spring-boot-starter-data-jpa:_
version.NO-RULE" style="text-decoration: underline;" >jpa</span>&nbsp; - <span title="Spring.boot.data.ldap
org.springframework.boot:spring-boot-starter-data-ldap:_
version.NO-RULE" style="text-decoration: underline;" >ldap</span>&nbsp; - <span title="Spring.boot.data.mongodbReactive
org.springframework.boot:spring-boot-starter-data-mongodb-reactive:_
version.NO-RULE" style="text-decoration: underline;" >mongodbReactive</span>&nbsp; - <span title="Spring.boot.data.mongodb
org.springframework.boot:spring-boot-starter-data-mongodb:_
version.NO-RULE" style="text-decoration: underline;" >mongodb</span>&nbsp; - <span title="Spring.boot.data.neo4j
org.springframework.boot:spring-boot-starter-data-neo4j:_
version.NO-RULE" style="text-decoration: underline;" >neo4j</span>&nbsp; - <span title="Spring.boot.data.r2dbc
org.springframework.boot:spring-boot-starter-data-r2dbc:_
version.NO-RULE" style="text-decoration: underline;" >r2dbc</span>&nbsp; - <span title="Spring.boot.data.redis_reactive
org.springframework.boot:spring-boot-starter-data-redis-reactive:_
version.NO-RULE" style="text-decoration: underline;" >redis_reactive</span>&nbsp; - <span title="Spring.boot.data.redis
org.springframework.boot:spring-boot-starter-data-redis:_
version.NO-RULE" style="text-decoration: underline;" >redis</span>&nbsp; - <span title="Spring.boot.data.rest
org.springframework.boot:spring-boot-starter-data-rest:_
version.NO-RULE" style="text-decoration: underline;" >rest</span>&nbsp; - <span title="Spring.boot.data.solr
org.springframework.boot:spring-boot-starter-data-solr:_
version.NO-RULE" style="text-decoration: underline;" >solr</span>&nbsp;</td></tr>
<tr><td><b>Spring.cloud</b></td><td><span title="Spring.cloud.bus
org.springframework.cloud:spring-cloud-bus:_
version.NO-RULE" style="text-decoration: underline;" >bus</span>&nbsp; - <span title="Spring.cloud.cloudfoundry_discovery
org.springframework.cloud:spring-cloud-cloudfoundry-discovery:_
version.NO-RULE" style="text-decoration: underline;" >cloudfoundry_discovery</span>&nbsp; - <span title="Spring.cloud.config_server
org.springframework.cloud:spring-cloud-config-server:_
version.NO-RULE" style="text-decoration: underline;" >config_server</span>&nbsp; - <span title="Spring.cloud.function_web
org.springframework.cloud:spring-cloud-function-web:_
version.NO-RULE" style="text-decoration: underline;" >function_web</span>&nbsp; - <span title="Spring.cloud.gcp_starter_pubsub
org.springframework.cloud:spring-cloud-gcp-starter-pubsub:_
version.NO-RULE" style="text-decoration: underline;" >gcp_starter_pubsub</span>&nbsp; - <span title="Spring.cloud.gcp_starter_storage
org.springframework.cloud:spring-cloud-gcp-starter-storage:_
version.NO-RULE" style="text-decoration: underline;" >gcp_starter_storage</span>&nbsp; - <span title="Spring.cloud.gcp_starter
org.springframework.cloud:spring-cloud-gcp-starter:_
version.NO-RULE" style="text-decoration: underline;" >gcp_starter</span>&nbsp; - <span title="Spring.cloud.circuitbreakerReactorResilience4J
org.springframework.cloud:spring-cloud-starter-circuitbreaker-reactor-resilience4j:_
version.NO-RULE" style="text-decoration: underline;" >circuitbreakerReactorResilience4J</span>&nbsp; - <span title="Spring.cloud.config
org.springframework.cloud:spring-cloud-starter-config:_
version.NO-RULE" style="text-decoration: underline;" >config</span>&nbsp; - <span title="Spring.cloud.consulConfig
org.springframework.cloud:spring-cloud-starter-consul-config:_
version.NO-RULE" style="text-decoration: underline;" >consulConfig</span>&nbsp; - <span title="Spring.cloud.consulDiscovery
org.springframework.cloud:spring-cloud-starter-consul-discovery:_
version.NO-RULE" style="text-decoration: underline;" >consulDiscovery</span>&nbsp; - <span title="Spring.cloud.contractStubRunner
org.springframework.cloud:spring-cloud-starter-contract-stub-runner:_
version.NO-RULE" style="text-decoration: underline;" >contractStubRunner</span>&nbsp; - <span title="Spring.cloud.contractVerifier
org.springframework.cloud:spring-cloud-starter-contract-verifier:_
version.NO-RULE" style="text-decoration: underline;" >contractVerifier</span>&nbsp; - <span title="Spring.cloud.gateway
org.springframework.cloud:spring-cloud-starter-gateway:_
version.NO-RULE" style="text-decoration: underline;" >gateway</span>&nbsp; - <span title="Spring.cloud.loadbalancer
org.springframework.cloud:spring-cloud-starter-loadbalancer:_
version.NO-RULE" style="text-decoration: underline;" >loadbalancer</span>&nbsp; - <span title="Spring.cloud.oauth2
org.springframework.cloud:spring-cloud-starter-oauth2:_
version.NO-RULE" style="text-decoration: underline;" >oauth2</span>&nbsp; - <span title="Spring.cloud.openServiceBroker
org.springframework.cloud:spring-cloud-starter-open-service-broker:_
version.NO-RULE" style="text-decoration: underline;" >openServiceBroker</span>&nbsp; - <span title="Spring.cloud.openfeign
org.springframework.cloud:spring-cloud-starter-openfeign:_
version.NO-RULE" style="text-decoration: underline;" >openfeign</span>&nbsp; - <span title="Spring.cloud.security
org.springframework.cloud:spring-cloud-starter-security:_
version.NO-RULE" style="text-decoration: underline;" >security</span>&nbsp; - <span title="Spring.cloud.sleuth
org.springframework.cloud:spring-cloud-starter-sleuth:_
version.NO-RULE" style="text-decoration: underline;" >sleuth</span>&nbsp; - <span title="Spring.cloud.task
org.springframework.cloud:spring-cloud-starter-task:_
version.NO-RULE" style="text-decoration: underline;" >task</span>&nbsp; - <span title="Spring.cloud.vault_config
org.springframework.cloud:spring-cloud-starter-vault-config:_
version.NO-RULE" style="text-decoration: underline;" >vault_config</span>&nbsp; - <span title="Spring.cloud.zipkin
org.springframework.cloud:spring-cloud-starter-zipkin:_
version.NO-RULE" style="text-decoration: underline;" >zipkin</span>&nbsp; - <span title="Spring.cloud.zookeeperConfig
org.springframework.cloud:spring-cloud-starter-zookeeper-config:_
version.NO-RULE" style="text-decoration: underline;" >zookeeperConfig</span>&nbsp; - <span title="Spring.cloud.zookeeperDiscovery
org.springframework.cloud:spring-cloud-starter-zookeeper-discovery:_
version.NO-RULE" style="text-decoration: underline;" >zookeeperDiscovery</span>&nbsp; - <span title="Spring.cloud.starter
org.springframework.cloud:spring-cloud-starter:_
version.NO-RULE" style="text-decoration: underline;" >starter</span>&nbsp; - <span title="Spring.cloud.streamBinderRabbit
org.springframework.cloud:spring-cloud-stream-binder-rabbit:_
version.NO-RULE" style="text-decoration: underline;" >streamBinderRabbit</span>&nbsp;</td></tr>
<tr><td><b>Spring.cloud.aws</b></td><td><span title="Spring.cloud.aws.jdbc
org.springframework.cloud:spring-cloud-starter-aws-jdbc:_
version.NO-RULE" style="text-decoration: underline;" >jdbc</span>&nbsp; - <span title="Spring.cloud.aws.messaging
org.springframework.cloud:spring-cloud-starter-aws-messaging:_
version.NO-RULE" style="text-decoration: underline;" >messaging</span>&nbsp; - <span title="Spring.cloud.aws.aws
org.springframework.cloud:spring-cloud-starter-aws:_
version.NO-RULE" style="text-decoration: underline;" >aws</span>&nbsp;</td></tr>
<tr><td><b>Spring.cloud.netflix</b></td><td><span title="Spring.cloud.netflix.eurekaClient
org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:_
version.NO-RULE" style="text-decoration: underline;" >eurekaClient</span>&nbsp; - <span title="Spring.cloud.netflix.eurekaServer
org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:_
version.NO-RULE" style="text-decoration: underline;" >eurekaServer</span>&nbsp; - <span title="Spring.cloud.netflix.hystrixDashboard
org.springframework.cloud:spring-cloud-starter-netflix-hystrix-dashboard:_
version.NO-RULE" style="text-decoration: underline;" >hystrixDashboard</span>&nbsp; - <span title="Spring.cloud.netflix.hystrix
org.springframework.cloud:spring-cloud-starter-netflix-hystrix:_
version.NO-RULE" style="text-decoration: underline;" >hystrix</span>&nbsp; - <span title="Spring.cloud.netflix.ribbon
org.springframework.cloud:spring-cloud-starter-netflix-ribbon:_
version.NO-RULE" style="text-decoration: underline;" >ribbon</span>&nbsp; - <span title="Spring.cloud.netflix.turbineStream
org.springframework.cloud:spring-cloud-starter-netflix-turbine-stream:_
version.NO-RULE" style="text-decoration: underline;" >turbineStream</span>&nbsp; - <span title="Spring.cloud.netflix.turbine
org.springframework.cloud:spring-cloud-starter-netflix-turbine:_
version.NO-RULE" style="text-decoration: underline;" >turbine</span>&nbsp; - <span title="Spring.cloud.netflix.zuul
org.springframework.cloud:spring-cloud-starter-netflix-zuul:_
version.NO-RULE" style="text-decoration: underline;" >zuul</span>&nbsp;</td></tr>
<tr><td><b>Spring.cloud.stream</b></td><td><span title="Spring.cloud.stream.binderKafkaStreams
org.springframework.cloud:spring-cloud-stream-binder-kafka-streams:_
version.NO-RULE" style="text-decoration: underline;" >binderKafkaStreams</span>&nbsp; - <span title="Spring.cloud.stream.binderKafka
org.springframework.cloud:spring-cloud-stream-binder-kafka:_
version.NO-RULE" style="text-decoration: underline;" >binderKafka</span>&nbsp; - <span title="Spring.cloud.stream.stream
org.springframework.cloud:spring-cloud-stream:_
version.NO-RULE" style="text-decoration: underline;" >stream</span>&nbsp;</td></tr>
<tr><td><b>Spring.data</b></td><td><span title="Spring.data.halExplorer
org.springframework.data:spring-data-rest-hal-explorer:_
version.NO-RULE" style="text-decoration: underline;" >halExplorer</span>&nbsp;</td></tr>
<tr><td><b>Spring.integration</b></td><td><span title="Spring.integration.amqp
org.springframework.integration:spring-integration-amqp:_
version.NO-RULE" style="text-decoration: underline;" >amqp</span>&nbsp; - <span title="Spring.integration.gemfire
org.springframework.integration:spring-integration-gemfire:_
version.NO-RULE" style="text-decoration: underline;" >gemfire</span>&nbsp; - <span title="Spring.integration.jdbc
org.springframework.integration:spring-integration-jdbc:_
version.NO-RULE" style="text-decoration: underline;" >jdbc</span>&nbsp; - <span title="Spring.integration.jms
org.springframework.integration:spring-integration-jms:_
version.NO-RULE" style="text-decoration: underline;" >jms</span>&nbsp; - <span title="Spring.integration.jpa
org.springframework.integration:spring-integration-jpa:_
version.NO-RULE" style="text-decoration: underline;" >jpa</span>&nbsp; - <span title="Spring.integration.kafka
org.springframework.integration:spring-integration-kafka:_
version.NO-RULE" style="text-decoration: underline;" >kafka</span>&nbsp; - <span title="Spring.integration.mail
org.springframework.integration:spring-integration-mail:_
version.NO-RULE" style="text-decoration: underline;" >mail</span>&nbsp; - <span title="Spring.integration.mongodb
org.springframework.integration:spring-integration-mongodb:_
version.NO-RULE" style="text-decoration: underline;" >mongodb</span>&nbsp; - <span title="Spring.integration.r2dbc
org.springframework.integration:spring-integration-r2dbc:_
version.NO-RULE" style="text-decoration: underline;" >r2dbc</span>&nbsp; - <span title="Spring.integration.redis
org.springframework.integration:spring-integration-redis:_
version.NO-RULE" style="text-decoration: underline;" >redis</span>&nbsp; - <span title="Spring.integration.rsocket
org.springframework.integration:spring-integration-rsocket:_
version.NO-RULE" style="text-decoration: underline;" >rsocket</span>&nbsp; - <span title="Spring.integration.security
org.springframework.integration:spring-integration-security:_
version.NO-RULE" style="text-decoration: underline;" >security</span>&nbsp; - <span title="Spring.integration.stomp
org.springframework.integration:spring-integration-stomp:_
version.NO-RULE" style="text-decoration: underline;" >stomp</span>&nbsp; - <span title="Spring.integration.test
org.springframework.integration:spring-integration-test:_
version.NO-RULE" style="text-decoration: underline;" >test</span>&nbsp; - <span title="Spring.integration.webflux
org.springframework.integration:spring-integration-webflux:_
version.NO-RULE" style="text-decoration: underline;" >webflux</span>&nbsp; - <span title="Spring.integration.websocket
org.springframework.integration:spring-integration-websocket:_
version.NO-RULE" style="text-decoration: underline;" >websocket</span>&nbsp; - <span title="Spring.integration.ws
org.springframework.integration:spring-integration-ws:_
version.NO-RULE" style="text-decoration: underline;" >ws</span>&nbsp;</td></tr>
<tr><td><b>Spring.reactor</b></td><td><span title="Spring.reactor.test
io.projectreactor:reactor-test:_
version.NO-RULE" style="text-decoration: underline;" >test</span>&nbsp; - <span title="Spring.reactor.kotlin
io.projectreactor.kotlin:reactor-kotlin-extensions:_
version.NO-RULE" style="text-decoration: underline;" >kotlin</span>&nbsp;</td></tr>
<tr><td><b>Spring.security</b></td><td><span title="Spring.security.spring_security_messaging
org.springframework.security:spring-security-messaging:_
version.NO-RULE" style="text-decoration: underline;" >spring_security_messaging</span>&nbsp; - <span title="Spring.security.spring_security_rsocket
org.springframework.security:spring-security-rsocket:_
version.NO-RULE" style="text-decoration: underline;" >spring_security_rsocket</span>&nbsp; - <span title="Spring.security.spring_security_test
org.springframework.security:spring-security-test:_
version.NO-RULE" style="text-decoration: underline;" >spring_security_test</span>&nbsp;</td></tr>
<tr><td><b>Spring.session</b></td><td><span title="Spring.session.dataRedis
org.springframework.session:spring-session-data-redis:_
version.NO-RULE" style="text-decoration: underline;" >dataRedis</span>&nbsp; - <span title="Spring.session.jdbc
org.springframework.session:spring-session-jdbc:_
version.NO-RULE" style="text-decoration: underline;" >jdbc</span>&nbsp;</td></tr>
<tr><td><b>Spring.springCloud</b></td><td><span title="Spring.springCloud.circuitBreaker
io.pivotal.spring.cloud:spring-cloud-services-starter-circuit-breaker:_
version.NO-RULE" style="text-decoration: underline;" >circuitBreaker</span>&nbsp; - <span title="Spring.springCloud.configClient
io.pivotal.spring.cloud:spring-cloud-services-starter-config-client:_
version.NO-RULE" style="text-decoration: underline;" >configClient</span>&nbsp; - <span title="Spring.springCloud.serviceRegistry
io.pivotal.spring.cloud:spring-cloud-services-starter-service-registry:_
version.NO-RULE" style="text-decoration: underline;" >serviceRegistry</span>&nbsp;</td></tr>
                </table>
## [Square.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Square.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Square</b></td><td><span title="Square.kotlinPoet
com.squareup:kotlinpoet:_
version.kotlinpoet" style="text-decoration: underline;" >kotlinPoet</span>&nbsp; - <span title="Square.logcat
com.squareup.logcat:logcat:_
version.logcat" style="text-decoration: underline;" >logcat</span>&nbsp; - <span title="Square.moshi
com.squareup.moshi:moshi:_
version.moshi" style="text-decoration: underline;" >moshi</span>&nbsp; - <span title="Square.okHttp3
com.squareup.okhttp3:okhttp:_
version.okhttp3" style="text-decoration: underline;" >okHttp3</span>&nbsp; - <span title="Square.okio
com.squareup.okio:okio:_
version.okio" style="text-decoration: underline;" >okio</span>&nbsp; - <span title="Square.picasso
com.squareup.picasso:picasso:_
version.picasso" style="text-decoration: underline;" >picasso</span>&nbsp; - <span title="Square.retrofit2
com.squareup.retrofit2:retrofit:_
version.retrofit2" style="text-decoration: underline;" >retrofit2</span>&nbsp;</td></tr>
<tr><td><b>Square.kotlinPoet</b></td><td><span title="Square.kotlinPoet.metadataSpecs
com.squareup:kotlinpoet-metadata-specs:_
version.kotlinpoet" style="text-decoration: underline;" >metadataSpecs</span>&nbsp; - <span title="Square.kotlinPoet.metadata
com.squareup:kotlinpoet-metadata:_
version.kotlinpoet" style="text-decoration: underline;" >metadata</span>&nbsp;</td></tr>
<tr><td><b>Square.leakCanary</b></td><td><span title="Square.leakCanary.androidInstrumentation
com.squareup.leakcanary:leakcanary-android-instrumentation:_
version.leakcanary" style="text-decoration: underline;" >androidInstrumentation</span>&nbsp; - <span title="Square.leakCanary.androidProcess
com.squareup.leakcanary:leakcanary-android-process:_
version.leakcanary" style="text-decoration: underline;" >androidProcess</span>&nbsp; - <span title="Square.leakCanary.android
com.squareup.leakcanary:leakcanary-android:_
version.leakcanary" style="text-decoration: underline;" >android</span>&nbsp; - <span title="Square.leakCanary.deobfuscationGradlePlugin
com.squareup.leakcanary:leakcanary-deobfuscation-gradle-plugin:_
version.leakcanary" style="text-decoration: underline;" >deobfuscationGradlePlugin</span>&nbsp; - <span title="Square.leakCanary.objectWatcher
com.squareup.leakcanary:leakcanary-object-watcher:_
version.leakcanary" style="text-decoration: underline;" >objectWatcher</span>&nbsp; - <span title="Square.leakCanary.plumber
com.squareup.leakcanary:plumber-android:_
version.leakcanary" style="text-decoration: underline;" >plumber</span>&nbsp; - <span title="Square.leakCanary.shark
com.squareup.leakcanary:shark:_
version.leakcanary" style="text-decoration: underline;" >shark</span>&nbsp;</td></tr>
<tr><td><b>Square.leakCanary.objectWatcher</b></td><td><span title="Square.leakCanary.objectWatcher.android
com.squareup.leakcanary:leakcanary-object-watcher-android:_
version.leakcanary" style="text-decoration: underline;" >android</span>&nbsp;</td></tr>
<tr><td><b>Square.leakCanary.shark</b></td><td><span title="Square.leakCanary.shark.android
com.squareup.leakcanary:shark-android:_
version.leakcanary" style="text-decoration: underline;" >android</span>&nbsp; - <span title="Square.leakCanary.shark.cli
com.squareup.leakcanary:shark-cli:_
version.leakcanary" style="text-decoration: underline;" >cli</span>&nbsp; - <span title="Square.leakCanary.shark.graph
com.squareup.leakcanary:shark-graph:_
version.leakcanary" style="text-decoration: underline;" >graph</span>&nbsp; - <span title="Square.leakCanary.shark.hprof
com.squareup.leakcanary:shark-hprof:_
version.leakcanary" style="text-decoration: underline;" >hprof</span>&nbsp;</td></tr>
<tr><td><b>Square.moshi</b></td><td><span title="Square.moshi.adapters
com.squareup.moshi:moshi-adapters:_
version.moshi" style="text-decoration: underline;" >adapters</span>&nbsp; - <span title="Square.moshi.kotlinCodegen
com.squareup.moshi:moshi-kotlin-codegen:_
version.moshi" style="text-decoration: underline;" >kotlinCodegen</span>&nbsp; - <span title="Square.moshi.kotlinReflect
com.squareup.moshi:moshi-kotlin:_
version.moshi" style="text-decoration: underline;" >kotlinReflect</span>&nbsp; - <span title="Square.moshi.javaReflect
com.squareup.moshi:moshi:_
version.moshi" style="text-decoration: underline;" >javaReflect</span>&nbsp;</td></tr>
<tr><td><b>Square.okHttp3</b></td><td><span title="Square.okHttp3.loggingInterceptor
com.squareup.okhttp3:logging-interceptor:_
version.okhttp3" style="text-decoration: underline;" >loggingInterceptor</span>&nbsp; - <span title="Square.okHttp3.mockWebServer3Junit4
com.squareup.okhttp3:mockwebserver3-junit4:_
version.okhttp3" style="text-decoration: underline;" >mockWebServer3Junit4</span>&nbsp; - <span title="Square.okHttp3.mockWebServer3Junit5
com.squareup.okhttp3:mockwebserver3-junit5:_
version.okhttp3" style="text-decoration: underline;" >mockWebServer3Junit5</span>&nbsp; - <span title="Square.okHttp3.mockWebServer3
com.squareup.okhttp3:mockwebserver3:_
version.okhttp3" style="text-decoration: underline;" >mockWebServer3</span>&nbsp; - <span title="Square.okHttp3.mockWebServer
com.squareup.okhttp3:mockwebserver:_
version.okhttp3" style="text-decoration: underline;" >mockWebServer</span>&nbsp; - <span title="Square.okHttp3.android
com.squareup.okhttp3:okhttp-android:_
version.okhttp3" style="text-decoration: underline;" >android</span>&nbsp; - <span title="Square.okHttp3.bom
com.squareup.okhttp3:okhttp-bom:_
version.okhttp3" style="text-decoration: underline;" >bom</span>&nbsp; - <span title="Square.okHttp3.brotli
com.squareup.okhttp3:okhttp-brotli:_
version.okhttp3" style="text-decoration: underline;" >brotli</span>&nbsp; - <span title="Square.okHttp3.coroutines
com.squareup.okhttp3:okhttp-coroutines:_
version.okhttp3" style="text-decoration: underline;" >coroutines</span>&nbsp; - <span title="Square.okHttp3.dnsOverHttps
com.squareup.okhttp3:okhttp-dnsoverhttps:_
version.okhttp3" style="text-decoration: underline;" >dnsOverHttps</span>&nbsp; - <span title="Square.okHttp3.sse
com.squareup.okhttp3:okhttp-sse:_
version.okhttp3" style="text-decoration: underline;" >sse</span>&nbsp; - <span title="Square.okHttp3.tls
com.squareup.okhttp3:okhttp-tls:_
version.okhttp3" style="text-decoration: underline;" >tls</span>&nbsp; - <span title="Square.okHttp3.urlConnection
com.squareup.okhttp3:okhttp-urlconnection:_
version.okhttp3" style="text-decoration: underline;" >urlConnection</span>&nbsp; - <span title="Square.okHttp3.okHttp
com.squareup.okhttp3:okhttp:_
version.okhttp3" style="text-decoration: underline;" >okHttp</span>&nbsp;</td></tr>
<tr><td><b>Square.picasso</b></td><td><span title="Square.picasso.pollexor
com.squareup.picasso:picasso-pollexor:_
version.picasso" style="text-decoration: underline;" >pollexor</span>&nbsp;</td></tr>
<tr><td><b>Square.retrofit2</b></td><td><span title="Square.retrofit2.mock
com.squareup.retrofit2:retrofit-mock:_
version.retrofit2" style="text-decoration: underline;" >mock</span>&nbsp; - <span title="Square.retrofit2.retrofit
com.squareup.retrofit2:retrofit:_
version.retrofit2" style="text-decoration: underline;" >retrofit</span>&nbsp;</td></tr>
<tr><td><b>Square.retrofit2.adapter</b></td><td><span title="Square.retrofit2.adapter.java8
com.squareup.retrofit2:adapter-java8:_
version.retrofit2" style="text-decoration: underline;" >java8</span>&nbsp; - <span title="Square.retrofit2.adapter.rxJava2
com.squareup.retrofit2:adapter-rxjava2:_
version.retrofit2" style="text-decoration: underline;" >rxJava2</span>&nbsp; - <span title="Square.retrofit2.adapter.rxJava3
com.squareup.retrofit2:adapter-rxjava3:_
version.retrofit2" style="text-decoration: underline;" >rxJava3</span>&nbsp; - <span title="Square.retrofit2.adapter.rxJava1
com.squareup.retrofit2:adapter-rxjava:_
version.retrofit2" style="text-decoration: underline;" >rxJava1</span>&nbsp;</td></tr>
<tr><td><b>Square.retrofit2.converter</b></td><td><span title="Square.retrofit2.converter.gson
com.squareup.retrofit2:converter-gson:_
version.retrofit2" style="text-decoration: underline;" >gson</span>&nbsp; - <span title="Square.retrofit2.converter.jackson
com.squareup.retrofit2:converter-jackson:_
version.retrofit2" style="text-decoration: underline;" >jackson</span>&nbsp; - <span title="Square.retrofit2.converter.moshi
com.squareup.retrofit2:converter-moshi:_
version.retrofit2" style="text-decoration: underline;" >moshi</span>&nbsp; - <span title="Square.retrofit2.converter.scalars
com.squareup.retrofit2:converter-scalars:_
version.retrofit2" style="text-decoration: underline;" >scalars</span>&nbsp; - <span title="Square.retrofit2.converter.simpleXml
com.squareup.retrofit2:converter-simplexml:_
version.retrofit2" style="text-decoration: underline;" >simpleXml</span>&nbsp; - <span title="Square.retrofit2.converter.wire
com.squareup.retrofit2:converter-wire:_
version.retrofit2" style="text-decoration: underline;" >wire</span>&nbsp;</td></tr>
<tr><td><b>Square.sqlDelight</b></td><td><span title="Square.sqlDelight.gradlePlugin
com.squareup.sqldelight:gradle-plugin:_
version.sqldelight" style="text-decoration: underline;" >gradlePlugin</span>&nbsp;</td></tr>
<tr><td><b>Square.sqlDelight.drivers</b></td><td><span title="Square.sqlDelight.drivers.android
com.squareup.sqldelight:android-driver:_
version.sqldelight" style="text-decoration: underline;" >android</span>&nbsp; - <span title="Square.sqlDelight.drivers.jdbc
com.squareup.sqldelight:jdbc-driver:_
version.sqldelight" style="text-decoration: underline;" >jdbc</span>&nbsp; - <span title="Square.sqlDelight.drivers.native
com.squareup.sqldelight:native-driver:_
version.sqldelight" style="text-decoration: underline;" >native</span>&nbsp; - <span title="Square.sqlDelight.drivers.jdbcSqlite
com.squareup.sqldelight:sqlite-driver:_
version.sqldelight" style="text-decoration: underline;" >jdbcSqlite</span>&nbsp; - <span title="Square.sqlDelight.drivers.sqlJs
com.squareup.sqldelight:sqljs-driver:_
version.sqldelight" style="text-decoration: underline;" >sqlJs</span>&nbsp;</td></tr>
<tr><td><b>Square.sqlDelight.extensions</b></td><td><span title="Square.sqlDelight.extensions.androidPaging
com.squareup.sqldelight:android-paging-extensions:_
version.sqldelight" style="text-decoration: underline;" >androidPaging</span>&nbsp; - <span title="Square.sqlDelight.extensions.androidPaging3
com.squareup.sqldelight:android-paging3-extensions:_
version.sqldelight" style="text-decoration: underline;" >androidPaging3</span>&nbsp; - <span title="Square.sqlDelight.extensions.coroutines
com.squareup.sqldelight:coroutines-extensions:_
version.sqldelight" style="text-decoration: underline;" >coroutines</span>&nbsp; - <span title="Square.sqlDelight.extensions.rxJava2
com.squareup.sqldelight:rxjava2-extensions:_
version.sqldelight" style="text-decoration: underline;" >rxJava2</span>&nbsp; - <span title="Square.sqlDelight.extensions.rxJava3
com.squareup.sqldelight:rxjava3-extensions:_
version.sqldelight" style="text-decoration: underline;" >rxJava3</span>&nbsp;</td></tr>
<tr><td><b>Square.wire</b></td><td><span title="Square.wire.gradlePlugin
com.squareup.wire:wire-gradle-plugin:_
version.wire" style="text-decoration: underline;" >gradlePlugin</span>&nbsp; - <span title="Square.wire.runtime
com.squareup.wire:wire-runtime:_
version.wire" style="text-decoration: underline;" >runtime</span>&nbsp;</td></tr>
<tr><td><b>Square.wire.grpc</b></td><td><span title="Square.wire.grpc.client
com.squareup.wire:wire-grpc-client:_
version.wire" style="text-decoration: underline;" >client</span>&nbsp;</td></tr>
                </table>
## [Testing.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Testing.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Testing</b></td><td><span title="Testing.mockK
io.mockk:mockk:_
version.mockk" style="text-decoration: underline;" >mockK</span>&nbsp; - <span title="Testing.junit4
junit:junit:_
version.junit.junit" style="text-decoration: underline;" >junit4</span>&nbsp; - <span title="Testing.hamcrest
org.hamcrest:hamcrest:_
version.hamcrest" style="text-decoration: underline;" >hamcrest</span>&nbsp; - <span title="Testing.robolectric
org.robolectric:robolectric:_
version.robolectric" style="text-decoration: underline;" >robolectric</span>&nbsp;</td></tr>
<tr><td><b>Testing.assertj</b></td><td><span title="Testing.assertj.core
org.assertj:assertj-core:_
version.assertj.core" style="text-decoration: underline;" >core</span>&nbsp; - <span title="Testing.assertj.db
org.assertj:assertj-db:_
version.assertj.db" style="text-decoration: underline;" >db</span>&nbsp; - <span title="Testing.assertj.guava
org.assertj:assertj-guava:_
version.assertj.guava" style="text-decoration: underline;" >guava</span>&nbsp; - <span title="Testing.assertj.jodaTime
org.assertj:assertj-joda-time:_
version.assertj.joda-time" style="text-decoration: underline;" >jodaTime</span>&nbsp; - <span title="Testing.assertj.swing
org.assertj:assertj-swing:_
version.assertj.swing" style="text-decoration: underline;" >swing</span>&nbsp;</td></tr>
<tr><td><b>Testing.hamcrest</b></td><td><span title="Testing.hamcrest.core
org.hamcrest:hamcrest-core:_
version.hamcrest" style="text-decoration: underline;" >core</span>&nbsp; - <span title="Testing.hamcrest.library
org.hamcrest:hamcrest-library:_
version.hamcrest" style="text-decoration: underline;" >library</span>&nbsp;</td></tr>
<tr><td><b>Testing.junit</b></td><td><span title="Testing.junit.bom
org.junit:junit-bom:_
version.junit" style="text-decoration: underline;" >bom</span>&nbsp; - <span title="Testing.junit.jupiter
org.junit.jupiter:junit-jupiter:_
version.junit.jupiter" style="text-decoration: underline;" >jupiter</span>&nbsp;</td></tr>
<tr><td><b>Testing.junit.jupiter</b></td><td><span title="Testing.junit.jupiter.api
org.junit.jupiter:junit-jupiter-api:_
version.junit.jupiter" style="text-decoration: underline;" >api</span>&nbsp; - <span title="Testing.junit.jupiter.engine
org.junit.jupiter:junit-jupiter-engine:_
version.junit.jupiter" style="text-decoration: underline;" >engine</span>&nbsp; - <span title="Testing.junit.jupiter.migrationSupport
org.junit.jupiter:junit-jupiter-migrationsupport:_
version.junit.jupiter" style="text-decoration: underline;" >migrationSupport</span>&nbsp; - <span title="Testing.junit.jupiter.params
org.junit.jupiter:junit-jupiter-params:_
version.junit.jupiter" style="text-decoration: underline;" >params</span>&nbsp;</td></tr>
<tr><td><b>Testing.kotest</b></td><td><span title="Testing.kotest.core
io.kotest:kotest-core:_
version.kotest" style="text-decoration: underline;" >core</span>&nbsp; - <span title="Testing.kotest.propertyArrow
io.kotest:kotest-property-arrow:_
version.kotest" style="text-decoration: underline;" >propertyArrow</span>&nbsp; - <span title="Testing.kotest.property
io.kotest:kotest-property:_
version.kotest" style="text-decoration: underline;" >property</span>&nbsp;</td></tr>
<tr><td><b>Testing.kotest.assertions</b></td><td><span title="Testing.kotest.assertions.arrow
io.kotest:kotest-assertions-arrow:_
version.kotest" style="text-decoration: underline;" >arrow</span>&nbsp; - <span title="Testing.kotest.assertions.compiler
io.kotest:kotest-assertions-compiler:_
version.kotest" style="text-decoration: underline;" >compiler</span>&nbsp; - <span title="Testing.kotest.assertions.core
io.kotest:kotest-assertions-core:_
version.kotest" style="text-decoration: underline;" >core</span>&nbsp; - <span title="Testing.kotest.assertions.json
io.kotest:kotest-assertions-json:_
version.kotest" style="text-decoration: underline;" >json</span>&nbsp; - <span title="Testing.kotest.assertions.jsoup
io.kotest:kotest-assertions-jsoup:_
version.kotest" style="text-decoration: underline;" >jsoup</span>&nbsp; - <span title="Testing.kotest.assertions.klock
io.kotest:kotest-assertions-klock:_
version.kotest" style="text-decoration: underline;" >klock</span>&nbsp; - <span title="Testing.kotest.assertions.konform
io.kotest:kotest-assertions-konform:_
version.kotest" style="text-decoration: underline;" >konform</span>&nbsp; - <span title="Testing.kotest.assertions.kotlinxDateTime
io.kotest:kotest-assertions-kotlinx-time:_
version.kotest" style="text-decoration: underline;" >kotlinxDateTime</span>&nbsp; - <span title="Testing.kotest.assertions.ktor
io.kotest:kotest-assertions-ktor:_
version.kotest" style="text-decoration: underline;" >ktor</span>&nbsp; - <span title="Testing.kotest.assertions.sql
io.kotest:kotest-assertions-sql:_
version.kotest" style="text-decoration: underline;" >sql</span>&nbsp;</td></tr>
<tr><td><b>Testing.kotest.extensions</b></td><td><span title="Testing.kotest.extensions.allure
io.kotest.extensions:kotest-extensions-allure:_
version.kotest.extensions.allure" style="text-decoration: underline;" >allure</span>&nbsp; - <span title="Testing.kotest.extensions.embeddedKafka
io.kotest.extensions:kotest-extensions-embedded-kafka:_
version.kotest.extensions.embedded-kafka" style="text-decoration: underline;" >embeddedKafka</span>&nbsp; - <span title="Testing.kotest.extensions.gherkin
io.kotest.extensions:kotest-extensions-gherkin:_
version.kotest.extensions.gherkin" style="text-decoration: underline;" >gherkin</span>&nbsp; - <span title="Testing.kotest.extensions.koin
io.kotest.extensions:kotest-extensions-koin:_
version.kotest.extensions.koin" style="text-decoration: underline;" >koin</span>&nbsp; - <span title="Testing.kotest.extensions.mockServer
io.kotest.extensions:kotest-extensions-mockserver:_
version.kotest.extensions.mockserver" style="text-decoration: underline;" >mockServer</span>&nbsp; - <span title="Testing.kotest.extensions.pitest
io.kotest.extensions:kotest-extensions-pitest:_
version.kotest.extensions.pitest" style="text-decoration: underline;" >pitest</span>&nbsp; - <span title="Testing.kotest.extensions.robolectric
io.kotest.extensions:kotest-extensions-robolectric:_
version.kotest.extensions.robolectric" style="text-decoration: underline;" >robolectric</span>&nbsp; - <span title="Testing.kotest.extensions.spring
io.kotest.extensions:kotest-extensions-spring:_
version.kotest.extensions.spring" style="text-decoration: underline;" >spring</span>&nbsp; - <span title="Testing.kotest.extensions.testContainers
io.kotest.extensions:kotest-extensions-testcontainers:_
version.kotest.extensions.testcontainers" style="text-decoration: underline;" >testContainers</span>&nbsp; - <span title="Testing.kotest.extensions.wiremock
io.kotest.extensions:kotest-extensions-wiremock:_
version.kotest.extensions.wiremock" style="text-decoration: underline;" >wiremock</span>&nbsp;</td></tr>
<tr><td><b>Testing.kotest.extensions.property</b></td><td><span title="Testing.kotest.extensions.property.arbs
io.kotest.extensions:kotest-property-arbs:_
version.kotest.extensions.property-arbs" style="text-decoration: underline;" >arbs</span>&nbsp; - <span title="Testing.kotest.extensions.property.datetime
io.kotest.extensions:kotest-property-datetime:_
version.kotest.extensions.property-datetime" style="text-decoration: underline;" >datetime</span>&nbsp;</td></tr>
<tr><td><b>Testing.kotest.framework</b></td><td><span title="Testing.kotest.framework.api
io.kotest:kotest-framework-api:_
version.kotest" style="text-decoration: underline;" >api</span>&nbsp; - <span title="Testing.kotest.framework.datatest
io.kotest:kotest-framework-datatest:_
version.kotest" style="text-decoration: underline;" >datatest</span>&nbsp;</td></tr>
<tr><td><b>Testing.kotest.plugins</b></td><td><span title="Testing.kotest.plugins.piTest
io.kotest:kotest-plugins-pitest:_
version.kotest" style="text-decoration: underline;" >piTest</span>&nbsp;</td></tr>
<tr><td><b>Testing.kotest.runner</b></td><td><span title="Testing.kotest.runner.junit4
io.kotest:kotest-runner-junit4:_
version.kotest" style="text-decoration: underline;" >junit4</span>&nbsp; - <span title="Testing.kotest.runner.junit5
io.kotest:kotest-runner-junit5:_
version.kotest" style="text-decoration: underline;" >junit5</span>&nbsp;</td></tr>
<tr><td><b>Testing.mockK</b></td><td><span title="Testing.mockK.android
io.mockk:mockk-android:_
version.mockk" style="text-decoration: underline;" >android</span>&nbsp; - <span title="Testing.mockK.common
io.mockk:mockk-common:_
version.mockk" style="text-decoration: underline;" >common</span>&nbsp;</td></tr>
<tr><td><b>Testing.mockito</b></td><td><span title="Testing.mockito.kotlin
com.nhaarman.mockitokotlin2:mockito-kotlin:_
version.NO-RULE" style="text-decoration: underline;" >kotlin</span>&nbsp; - <span title="Testing.mockito.android
org.mockito:mockito-android:_
version.mockito" style="text-decoration: underline;" >android</span>&nbsp; - <span title="Testing.mockito.core
org.mockito:mockito-core:_
version.mockito" style="text-decoration: underline;" >core</span>&nbsp; - <span title="Testing.mockito.errorProne
org.mockito:mockito-errorprone:_
version.mockito" style="text-decoration: underline;" >errorProne</span>&nbsp; - <span title="Testing.mockito.inline
org.mockito:mockito-inline:_
version.mockito" style="text-decoration: underline;" >inline</span>&nbsp; - <span title="Testing.mockito.junitJupiter
org.mockito:mockito-junit-jupiter:_
version.mockito" style="text-decoration: underline;" >junitJupiter</span>&nbsp;</td></tr>
<tr><td><b>Testing.spek.dsl</b></td><td><span title="Testing.spek.dsl.js
org.spekframework.spek2:spek-dsl-js:_
version.spek" style="text-decoration: underline;" >js</span>&nbsp; - <span title="Testing.spek.dsl.jvm
org.spekframework.spek2:spek-dsl-jvm:_
version.spek" style="text-decoration: underline;" >jvm</span>&nbsp; - <span title="Testing.spek.dsl.metadata
org.spekframework.spek2:spek-dsl-metadata:_
version.spek" style="text-decoration: underline;" >metadata</span>&nbsp;</td></tr>
<tr><td><b>Testing.spek.dsl.native</b></td><td><span title="Testing.spek.dsl.native.linux
org.spekframework.spek2:spek-dsl-native-linux:_
version.spek" style="text-decoration: underline;" >linux</span>&nbsp; - <span title="Testing.spek.dsl.native.macos
org.spekframework.spek2:spek-dsl-native-macos:_
version.spek" style="text-decoration: underline;" >macos</span>&nbsp; - <span title="Testing.spek.dsl.native.windows
org.spekframework.spek2:spek-dsl-native-windows:_
version.spek" style="text-decoration: underline;" >windows</span>&nbsp;</td></tr>
<tr><td><b>Testing.spek.runner</b></td><td><span title="Testing.spek.runner.junit5
org.spekframework.spek2:spek-runner-junit5:_
version.spek" style="text-decoration: underline;" >junit5</span>&nbsp;</td></tr>
<tr><td><b>Testing.spek.runtime</b></td><td><span title="Testing.spek.runtime.jvm
org.spekframework.spek2:spek-runtime-jvm:_
version.spek" style="text-decoration: underline;" >jvm</span>&nbsp; - <span title="Testing.spek.runtime.metadata
org.spekframework.spek2:spek-runtime-metadata:_
version.spek" style="text-decoration: underline;" >metadata</span>&nbsp;</td></tr>
<tr><td><b>Testing.strikt</b></td><td><span title="Testing.strikt.arrow
io.strikt:strikt-arrow:_
version.strikt" style="text-decoration: underline;" >arrow</span>&nbsp; - <span title="Testing.strikt.bom
io.strikt:strikt-bom:_
version.strikt" style="text-decoration: underline;" >bom</span>&nbsp; - <span title="Testing.strikt.core
io.strikt:strikt-core:_
version.strikt" style="text-decoration: underline;" >core</span>&nbsp; - <span title="Testing.strikt.gradle
io.strikt:strikt-gradle:_
version.strikt" style="text-decoration: underline;" >gradle</span>&nbsp; - <span title="Testing.strikt.jackson
io.strikt:strikt-jackson:_
version.strikt" style="text-decoration: underline;" >jackson</span>&nbsp; - <span title="Testing.strikt.javaTime
io.strikt:strikt-java-time:_
version.strikt" style="text-decoration: underline;" >javaTime</span>&nbsp; - <span title="Testing.strikt.mockk
io.strikt:strikt-mockk:_
version.strikt" style="text-decoration: underline;" >mockk</span>&nbsp; - <span title="Testing.strikt.protobuf
io.strikt:strikt-protobuf:_
version.strikt" style="text-decoration: underline;" >protobuf</span>&nbsp; - <span title="Testing.strikt.spring
io.strikt:strikt-spring:_
version.strikt" style="text-decoration: underline;" >spring</span>&nbsp;</td></tr>
                </table>
## [Touchlab.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Touchlab.kt)

<table style="width: 100%; table-layout:fixed;">
                <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
                <tr><td><b>Touchlab</b></td><td><span title="Touchlab.kermit
co.touchlab:kermit:_
version.kermit" style="text-decoration: underline;" >kermit</span>&nbsp;</td></tr>
<tr><td><b>Touchlab.kermit</b></td><td><span title="Touchlab.kermit.bugsnagTest
co.touchlab:kermit-bugsnag-test:_
version.kermit" style="text-decoration: underline;" >bugsnagTest</span>&nbsp; - <span title="Touchlab.kermit.bugsnag
co.touchlab:kermit-bugsnag:_
version.kermit" style="text-decoration: underline;" >bugsnag</span>&nbsp; - <span title="Touchlab.kermit.crashlyticsTest
co.touchlab:kermit-crashlytics-test:_
version.kermit" style="text-decoration: underline;" >crashlyticsTest</span>&nbsp; - <span title="Touchlab.kermit.crashlytics
co.touchlab:kermit-crashlytics:_
version.kermit" style="text-decoration: underline;" >crashlytics</span>&nbsp; - <span title="Touchlab.kermit.gradlePlugin
co.touchlab:kermit-gradle-plugin:_
version.kermit" style="text-decoration: underline;" >gradlePlugin</span>&nbsp; - <span title="Touchlab.kermit.test
co.touchlab:kermit-test:_
version.kermit" style="text-decoration: underline;" >test</span>&nbsp;</td></tr>
<tr><td><b>Touchlab.stately</b></td><td><span title="Touchlab.stately.common
co.touchlab:stately-common:_
version.stately" style="text-decoration: underline;" >common</span>&nbsp; - <span title="Touchlab.stately.concurrency
co.touchlab:stately-concurrency:_
version.stately" style="text-decoration: underline;" >concurrency</span>&nbsp; - <span title="Touchlab.stately.isoCollections
co.touchlab:stately-iso-collections:_
version.stately" style="text-decoration: underline;" >isoCollections</span>&nbsp; - <span title="Touchlab.stately.isolate
co.touchlab:stately-isolate:_
version.stately" style="text-decoration: underline;" >isolate</span>&nbsp;</td></tr>
                </table>
