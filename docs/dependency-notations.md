---
title: Built-in Dependency Notations
---
# Built-in Dependency Notations

[**refreshVersions**](https://github.com/jmfayard/refreshVersions) provides **1180** Dependency Notations in **26** groups and **249** subgroups

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
    <tr><td><b>Android</b></td><td>
        <span
            title="Android.billingClient&#10;com.android.billingclient:billing:_&#10;version.android.billingclient"
            style="text-decoration: underline;">
            billingClient
        </span>
        - 
        <span
            title="Android.installReferrer&#10;com.android.installreferrer:installreferrer:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            installReferrer
        </span>
            
    </td></tr>
            
    <tr><td><b>Android.billingClient</b></td><td>
        <span
            title="Android.billingClient.ktx&#10;com.android.billingclient:billing-ktx:_&#10;version.android.billingclient"
            style="text-decoration: underline;">
            ktx
        </span>
            
    </td></tr>
            
    <tr><td><b>Android.tools</b></td><td>
        <span
            title="Android.tools.desugarJdkLibs&#10;com.android.tools:desugar_jdk_libs:_&#10;version.android.tools.desugar_jdk_libs"
            style="text-decoration: underline;">
            desugarJdkLibs
        </span>
        - 
        <span
            title="Android.tools.r8&#10;com.android.tools:r8:_&#10;version.android.tools.r8"
            style="text-decoration: underline;">
            r8
        </span>
            
    </td></tr>
            
    <tr><td><b>Android.tools.build</b></td><td>
        <span
            title="Android.tools.build.gradlePlugin&#10;com.android.tools.build:gradle:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            gradlePlugin
        </span>
            
    </td></tr>
            
</table>
            

## [AndroidX.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/AndroidX.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>AndroidX</b></td><td>
        <span
            title="AndroidX.activity&#10;androidx.activity:activity:_&#10;version.androidx.activity"
            style="text-decoration: underline;">
            activity
        </span>
        - 
        <span
            title="AndroidX.annotation&#10;androidx.annotation:annotation:_&#10;version.androidx.annotation"
            style="text-decoration: underline;">
            annotation
        </span>
        - 
        <span
            title="AndroidX.appCompat&#10;androidx.appcompat:appcompat:_&#10;version.androidx.appcompat"
            style="text-decoration: underline;">
            appCompat
        </span>
        - 
        <span
            title="AndroidX.appSearch&#10;androidx.appsearch:appsearch:_&#10;version.androidx.appsearch"
            style="text-decoration: underline;">
            appSearch
        </span>
        - 
        <span
            title="AndroidX.asyncLayoutInflater&#10;androidx.asynclayoutinflater:asynclayoutinflater:_&#10;version.androidx.asynclayoutinflater"
            style="text-decoration: underline;">
            asyncLayoutInflater
        </span>
        - 
        <span
            title="AndroidX.autoFill&#10;androidx.autofill:autofill:_&#10;version.androidx.autofill"
            style="text-decoration: underline;">
            autoFill
        </span>
        - 
        <span
            title="AndroidX.biometric&#10;androidx.biometric:biometric:_&#10;version.androidx.biometric"
            style="text-decoration: underline;">
            biometric
        </span>
        - 
        <span
            title="AndroidX.browser&#10;androidx.browser:browser:_&#10;version.androidx.browser"
            style="text-decoration: underline;">
            browser
        </span>
        - 
        <span
            title="AndroidX.carApp&#10;androidx.car.app:app:_&#10;version.androidx.car.app"
            style="text-decoration: underline;">
            carApp
        </span>
        - 
        <span
            title="AndroidX.cardView&#10;androidx.cardview:cardview:_&#10;version.androidx.cardview"
            style="text-decoration: underline;">
            cardView
        </span>
        - 
        <span
            title="AndroidX.collection&#10;androidx.collection:collection:_&#10;version.androidx.collection"
            style="text-decoration: underline;">
            collection
        </span>
        - 
        <span
            title="AndroidX.constraintLayout&#10;androidx.constraintlayout:constraintlayout:_&#10;version.androidx.constraintlayout"
            style="text-decoration: underline;">
            constraintLayout
        </span>
        - 
        <span
            title="AndroidX.contentPager&#10;androidx.contentpager:contentpager:_&#10;version.androidx.contentpager"
            style="text-decoration: underline;">
            contentPager
        </span>
        - 
        <span
            title="AndroidX.coordinatorLayout&#10;androidx.coordinatorlayout:coordinatorlayout:_&#10;version.androidx.coordinatorlayout"
            style="text-decoration: underline;">
            coordinatorLayout
        </span>
        - 
        <span
            title="AndroidX.core&#10;androidx.core:core:_&#10;version.androidx.core"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="AndroidX.cursorAdapter&#10;androidx.cursoradapter:cursoradapter:_&#10;version.androidx.cursoradapter"
            style="text-decoration: underline;">
            cursorAdapter
        </span>
        - 
        <span
            title="AndroidX.customView&#10;androidx.customview:customview:_&#10;version.androidx.customview"
            style="text-decoration: underline;">
            customView
        </span>
        - 
        <span
            title="AndroidX.dataStore&#10;androidx.datastore:datastore:_&#10;version.androidx.datastore"
            style="text-decoration: underline;">
            dataStore
        </span>
        - 
        <span
            title="AndroidX.documentFile&#10;androidx.documentfile:documentfile:_&#10;version.androidx.documentfile"
            style="text-decoration: underline;">
            documentFile
        </span>
        - 
        <span
            title="AndroidX.dragAndDrop&#10;androidx.draganddrop:draganddrop:_&#10;version.androidx.draganddrop"
            style="text-decoration: underline;">
            dragAndDrop
        </span>
        - 
        <span
            title="AndroidX.drawerLayout&#10;androidx.drawerlayout:drawerlayout:_&#10;version.androidx.drawerlayout"
            style="text-decoration: underline;">
            drawerLayout
        </span>
        - 
        <span
            title="AndroidX.dynamicAnimation&#10;androidx.dynamicanimation:dynamicanimation:_&#10;version.androidx.dynamicanimation"
            style="text-decoration: underline;">
            dynamicAnimation
        </span>
        - 
        <span
            title="AndroidX.emoji&#10;androidx.emoji:emoji:_&#10;version.androidx.emoji"
            style="text-decoration: underline;">
            emoji
        </span>
        - 
        <span
            title="AndroidX.emoji2&#10;androidx.emoji2:emoji2:_&#10;version.androidx.emoji2"
            style="text-decoration: underline;">
            emoji2
        </span>
        - 
        <span
            title="AndroidX.exifInterface&#10;androidx.exifinterface:exifinterface:_&#10;version.androidx.exifinterface"
            style="text-decoration: underline;">
            exifInterface
        </span>
        - 
        <span
            title="AndroidX.fragment&#10;androidx.fragment:fragment:_&#10;version.androidx.fragment"
            style="text-decoration: underline;">
            fragment
        </span>
        - 
        <span
            title="AndroidX.glance&#10;androidx.glance:glance:_&#10;version.androidx.glance"
            style="text-decoration: underline;">
            glance
        </span>
        - 
        <span
            title="AndroidX.gridLayout&#10;androidx.gridlayout:gridlayout:_&#10;version.androidx.gridlayout"
            style="text-decoration: underline;">
            gridLayout
        </span>
        - 
        <span
            title="AndroidX.heifWriter&#10;androidx.heifwriter:heifwriter:_&#10;version.androidx.heifwriter"
            style="text-decoration: underline;">
            heifWriter
        </span>
        - 
        <span
            title="AndroidX.interpolator&#10;androidx.interpolator:interpolator:_&#10;version.androidx.interpolator"
            style="text-decoration: underline;">
            interpolator
        </span>
        - 
        <span
            title="AndroidX.javascriptEngine&#10;androidx.javascriptengine:javascriptengine:_&#10;version.androidx.javascriptengine"
            style="text-decoration: underline;">
            javascriptEngine
        </span>
        - 
        <span
            title="AndroidX.leanback&#10;androidx.leanback:leanback:_&#10;version.androidx.leanback"
            style="text-decoration: underline;">
            leanback
        </span>
        - 
        <span
            title="AndroidX.loader&#10;androidx.loader:loader:_&#10;version.androidx.loader"
            style="text-decoration: underline;">
            loader
        </span>
        - 
        <span
            title="AndroidX.localBroadcastManager&#10;androidx.localbroadcastmanager:localbroadcastmanager:_&#10;version.androidx.localbroadcastmanager"
            style="text-decoration: underline;">
            localBroadcastManager
        </span>
        - 
        <span
            title="AndroidX.media&#10;androidx.media:media:_&#10;version.androidx.media"
            style="text-decoration: underline;">
            media
        </span>
        - 
        <span
            title="AndroidX.mediaRouter&#10;androidx.mediarouter:mediarouter:_&#10;version.androidx.mediarouter"
            style="text-decoration: underline;">
            mediaRouter
        </span>
        - 
        <span
            title="AndroidX.multidex&#10;androidx.multidex:multidex:_&#10;version.androidx.multidex"
            style="text-decoration: underline;">
            multidex
        </span>
        - 
        <span
            title="AndroidX.palette&#10;androidx.palette:palette:_&#10;version.androidx.palette"
            style="text-decoration: underline;">
            palette
        </span>
        - 
        <span
            title="AndroidX.preference&#10;androidx.preference:preference:_&#10;version.androidx.preference"
            style="text-decoration: underline;">
            preference
        </span>
        - 
        <span
            title="AndroidX.print&#10;androidx.print:print:_&#10;version.androidx.print"
            style="text-decoration: underline;">
            print
        </span>
        - 
        <span
            title="AndroidX.recommendation&#10;androidx.recommendation:recommendation:_&#10;version.androidx.recommendation"
            style="text-decoration: underline;">
            recommendation
        </span>
        - 
        <span
            title="AndroidX.recyclerView&#10;androidx.recyclerview:recyclerview:_&#10;version.androidx.recyclerview"
            style="text-decoration: underline;">
            recyclerView
        </span>
        - 
        <span
            title="AndroidX.remoteCallback&#10;androidx.remotecallback:remotecallback:_&#10;version.androidx.remotecallback"
            style="text-decoration: underline;">
            remoteCallback
        </span>
        - 
        <span
            title="AndroidX.savedState&#10;androidx.savedstate:savedstate:_&#10;version.androidx.savedstate"
            style="text-decoration: underline;">
            savedState
        </span>
        - 
        <span
            title="AndroidX.shareTarget&#10;androidx.sharetarget:sharetarget:_&#10;version.androidx.sharetarget"
            style="text-decoration: underline;">
            shareTarget
        </span>
        - 
        <span
            title="AndroidX.slidingPaneLayout&#10;androidx.slidingpanelayout:slidingpanelayout:_&#10;version.androidx.slidingpanelayout"
            style="text-decoration: underline;">
            slidingPaneLayout
        </span>
        - 
        <span
            title="AndroidX.sqlite&#10;androidx.sqlite:sqlite:_&#10;version.androidx.sqlite"
            style="text-decoration: underline;">
            sqlite
        </span>
        - 
        <span
            title="AndroidX.swipeRefreshLayout&#10;androidx.swiperefreshlayout:swiperefreshlayout:_&#10;version.androidx.swiperefreshlayout"
            style="text-decoration: underline;">
            swipeRefreshLayout
        </span>
        - 
        <span
            title="AndroidX.textClassifier&#10;androidx.textclassifier:textclassifier:_&#10;version.androidx.textclassifier"
            style="text-decoration: underline;">
            textClassifier
        </span>
        - 
        <span
            title="AndroidX.tracing&#10;androidx.tracing:tracing:_&#10;version.androidx.tracing"
            style="text-decoration: underline;">
            tracing
        </span>
        - 
        <span
            title="AndroidX.transitionKtx&#10;androidx.transition:transition-ktx:_&#10;version.androidx.transition"
            style="text-decoration: underline;">
            transitionKtx
        </span>
        - 
        <span
            title="AndroidX.transition&#10;androidx.transition:transition:_&#10;version.androidx.transition"
            style="text-decoration: underline;">
            transition
        </span>
        - 
        <span
            title="AndroidX.tvProvider&#10;androidx.tvprovider:tvprovider:_&#10;version.androidx.tvprovider"
            style="text-decoration: underline;">
            tvProvider
        </span>
        - 
        <span
            title="AndroidX.vectorDrawable&#10;androidx.vectordrawable:vectordrawable:_&#10;version.androidx.vectordrawable"
            style="text-decoration: underline;">
            vectorDrawable
        </span>
        - 
        <span
            title="AndroidX.versionedParcelable&#10;androidx.versionedparcelable:versionedparcelable:_&#10;version.androidx.versionedparcelable"
            style="text-decoration: underline;">
            versionedParcelable
        </span>
        - 
        <span
            title="AndroidX.viewPager&#10;androidx.viewpager:viewpager:_&#10;version.androidx.viewpager"
            style="text-decoration: underline;">
            viewPager
        </span>
        - 
        <span
            title="AndroidX.viewPager2&#10;androidx.viewpager2:viewpager2:_&#10;version.androidx.viewpager2"
            style="text-decoration: underline;">
            viewPager2
        </span>
        - 
        <span
            title="AndroidX.wear&#10;androidx.wear:wear:_&#10;version.androidx.wear"
            style="text-decoration: underline;">
            wear
        </span>
        - 
        <span
            title="AndroidX.webkit&#10;androidx.webkit:webkit:_&#10;version.androidx.webkit"
            style="text-decoration: underline;">
            webkit
        </span>
        - 
        <span
            title="AndroidX.window&#10;androidx.window:window:_&#10;version.androidx.window"
            style="text-decoration: underline;">
            window
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.activity</b></td><td>
        <span
            title="AndroidX.activity.compose&#10;androidx.activity:activity-compose:_&#10;version.androidx.activity"
            style="text-decoration: underline;">
            compose
        </span>
        - 
        <span
            title="AndroidX.activity.ktx&#10;androidx.activity:activity-ktx:_&#10;version.androidx.activity"
            style="text-decoration: underline;">
            ktx
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.annotation</b></td><td>
        <span
            title="AndroidX.annotation.experimental&#10;androidx.annotation:annotation-experimental:_&#10;version.androidx.annotation"
            style="text-decoration: underline;">
            experimental
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.appCompat</b></td><td>
        <span
            title="AndroidX.appCompat.resources&#10;androidx.appcompat:appcompat-resources:_&#10;version.androidx.appcompat"
            style="text-decoration: underline;">
            resources
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.appSearch</b></td><td>
        <span
            title="AndroidX.appSearch.builtInTypes&#10;androidx.appsearch:appsearch-builtin-types:_&#10;version.androidx.appsearch"
            style="text-decoration: underline;">
            builtInTypes
        </span>
        - 
        <span
            title="AndroidX.appSearch.compiler&#10;androidx.appsearch:appsearch-compiler:_&#10;version.androidx.appsearch"
            style="text-decoration: underline;">
            compiler
        </span>
        - 
        <span
            title="AndroidX.appSearch.localStorage&#10;androidx.appsearch:appsearch-local-storage:_&#10;version.androidx.appsearch"
            style="text-decoration: underline;">
            localStorage
        </span>
        - 
        <span
            title="AndroidX.appSearch.platformStorage&#10;androidx.appsearch:appsearch-platform-storage:_&#10;version.androidx.appsearch"
            style="text-decoration: underline;">
            platformStorage
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.archCore</b></td><td>
        <span
            title="AndroidX.archCore.common&#10;androidx.arch.core:core-common:_&#10;version.androidx.arch.core"
            style="text-decoration: underline;">
            common
        </span>
        - 
        <span
            title="AndroidX.archCore.runtime&#10;androidx.arch.core:core-runtime:_&#10;version.androidx.arch.core"
            style="text-decoration: underline;">
            runtime
        </span>
        - 
        <span
            title="AndroidX.archCore.testing&#10;androidx.arch.core:core-testing:_&#10;version.androidx.arch.core"
            style="text-decoration: underline;">
            testing
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.asyncLayoutInflater</b></td><td>
        <span
            title="AndroidX.asyncLayoutInflater.appcompat&#10;androidx.asynclayoutinflater:asynclayoutinflater-appcompat:_&#10;version.androidx.asynclayoutinflater"
            style="text-decoration: underline;">
            appcompat
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.benchmark</b></td><td>
        <span
            title="AndroidX.benchmark.common&#10;androidx.benchmark:benchmark-common:_&#10;version.androidx.benchmark"
            style="text-decoration: underline;">
            common
        </span>
        - 
        <span
            title="AndroidX.benchmark.gradlePlugin&#10;androidx.benchmark:benchmark-gradle-plugin:_&#10;version.androidx.benchmark"
            style="text-decoration: underline;">
            gradlePlugin
        </span>
        - 
        <span
            title="AndroidX.benchmark.junit4&#10;androidx.benchmark:benchmark-junit4:_&#10;version.androidx.benchmark"
            style="text-decoration: underline;">
            junit4
        </span>
        - 
        <span
            title="AndroidX.benchmark.macroJunit4&#10;androidx.benchmark:benchmark-macro-junit4:_&#10;version.androidx.benchmark"
            style="text-decoration: underline;">
            macroJunit4
        </span>
        - 
        <span
            title="AndroidX.benchmark.macro&#10;androidx.benchmark:benchmark-macro:_&#10;version.androidx.benchmark"
            style="text-decoration: underline;">
            macro
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.biometric</b></td><td>
        <span
            title="AndroidX.biometric.ktx&#10;androidx.biometric:biometric-ktx:_&#10;version.androidx.biometric-ktx"
            style="text-decoration: underline;">
            ktx
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.camera</b></td><td>
        <span
            title="AndroidX.camera.camera2&#10;androidx.camera:camera-camera2:_&#10;version.androidx.camera"
            style="text-decoration: underline;">
            camera2
        </span>
        - 
        <span
            title="AndroidX.camera.core&#10;androidx.camera:camera-core:_&#10;version.androidx.camera"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="AndroidX.camera.extensions&#10;androidx.camera:camera-extensions:_&#10;version.androidx.camera.extensions"
            style="text-decoration: underline;">
            extensions
        </span>
        - 
        <span
            title="AndroidX.camera.lifecycle&#10;androidx.camera:camera-lifecycle:_&#10;version.androidx.camera"
            style="text-decoration: underline;">
            lifecycle
        </span>
        - 
        <span
            title="AndroidX.camera.mlKitVision&#10;androidx.camera:camera-mlkit-vision:_&#10;version.androidx.camera"
            style="text-decoration: underline;">
            mlKitVision
        </span>
        - 
        <span
            title="AndroidX.camera.video&#10;androidx.camera:camera-video:_&#10;version.androidx.camera"
            style="text-decoration: underline;">
            video
        </span>
        - 
        <span
            title="AndroidX.camera.view&#10;androidx.camera:camera-view:_&#10;version.androidx.camera.view"
            style="text-decoration: underline;">
            view
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.carApp</b></td><td>
        <span
            title="AndroidX.carApp.automotive&#10;androidx.car.app:app-automotive:_&#10;version.androidx.car.app"
            style="text-decoration: underline;">
            automotive
        </span>
        - 
        <span
            title="AndroidX.carApp.projected&#10;androidx.car.app:app-projected:_&#10;version.androidx.car.app"
            style="text-decoration: underline;">
            projected
        </span>
        - 
        <span
            title="AndroidX.carApp.testing&#10;androidx.car.app:app-testing:_&#10;version.androidx.car.app-testing"
            style="text-decoration: underline;">
            testing
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.collection</b></td><td>
        <span
            title="AndroidX.collection.ktx&#10;androidx.collection:collection-ktx:_&#10;version.androidx.collection"
            style="text-decoration: underline;">
            ktx
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.compose</b></td><td>
        <span
            title="AndroidX.compose.bom&#10;androidx.compose:compose-bom:_&#10;version.androidx.compose"
            style="text-decoration: underline;">
            bom
        </span>
        - 
        <span
            title="AndroidX.compose.animation&#10;androidx.compose.animation:animation:_&#10;version.androidx.compose.animation"
            style="text-decoration: underline;">
            animation
        </span>
        - 
        <span
            title="AndroidX.compose.compiler&#10;androidx.compose.compiler:compiler:_&#10;version.androidx.compose.compiler"
            style="text-decoration: underline;">
            compiler
        </span>
        - 
        <span
            title="AndroidX.compose.foundation&#10;androidx.compose.foundation:foundation:_&#10;version.androidx.compose.foundation"
            style="text-decoration: underline;">
            foundation
        </span>
        - 
        <span
            title="AndroidX.compose.material&#10;androidx.compose.material:material:_&#10;version.androidx.compose.material"
            style="text-decoration: underline;">
            material
        </span>
        - 
        <span
            title="AndroidX.compose.material3&#10;androidx.compose.material3:material3:_&#10;version.androidx.compose.material3"
            style="text-decoration: underline;">
            material3
        </span>
        - 
        <span
            title="AndroidX.compose.runtime&#10;androidx.compose.runtime:runtime:_&#10;version.androidx.compose.runtime"
            style="text-decoration: underline;">
            runtime
        </span>
        - 
        <span
            title="AndroidX.compose.ui&#10;androidx.compose.ui:ui:_&#10;version.androidx.compose.ui"
            style="text-decoration: underline;">
            ui
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.compose.animation</b></td><td>
        <span
            title="AndroidX.compose.animation.core&#10;androidx.compose.animation:animation-core:_&#10;version.androidx.compose.animation"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="AndroidX.compose.animation.graphics&#10;androidx.compose.animation:animation-graphics:_&#10;version.androidx.compose.animation"
            style="text-decoration: underline;">
            graphics
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.compose.foundation</b></td><td>
        <span
            title="AndroidX.compose.foundation.layout&#10;androidx.compose.foundation:foundation-layout:_&#10;version.androidx.compose.foundation"
            style="text-decoration: underline;">
            layout
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.compose.material</b></td><td>
        <span
            title="AndroidX.compose.material.ripple&#10;androidx.compose.material:material-ripple:_&#10;version.androidx.compose.material"
            style="text-decoration: underline;">
            ripple
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.compose.material.icons</b></td><td>
        <span
            title="AndroidX.compose.material.icons.core&#10;androidx.compose.material:material-icons-core:_&#10;version.androidx.compose.material"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="AndroidX.compose.material.icons.extended&#10;androidx.compose.material:material-icons-extended:_&#10;version.androidx.compose.material"
            style="text-decoration: underline;">
            extended
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.compose.material3</b></td><td>
        <span
            title="AndroidX.compose.material3.windowSizeClass&#10;androidx.compose.material3:material3-window-size-class:_&#10;version.androidx.compose.material3"
            style="text-decoration: underline;">
            windowSizeClass
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.compose.runtime</b></td><td>
        <span
            title="AndroidX.compose.runtime.dispatch&#10;androidx.compose.runtime:runtime-dispatch:_&#10;version.androidx.compose.runtime"
            style="text-decoration: underline;">
            dispatch
        </span>
        - 
        <span
            title="AndroidX.compose.runtime.liveData&#10;androidx.compose.runtime:runtime-livedata:_&#10;version.androidx.compose.runtime"
            style="text-decoration: underline;">
            liveData
        </span>
        - 
        <span
            title="AndroidX.compose.runtime.rxJava2&#10;androidx.compose.runtime:runtime-rxjava2:_&#10;version.androidx.compose.runtime"
            style="text-decoration: underline;">
            rxJava2
        </span>
        - 
        <span
            title="AndroidX.compose.runtime.rxJava3&#10;androidx.compose.runtime:runtime-rxjava3:_&#10;version.androidx.compose.runtime"
            style="text-decoration: underline;">
            rxJava3
        </span>
        - 
        <span
            title="AndroidX.compose.runtime.saveable&#10;androidx.compose.runtime:runtime-saveable:_&#10;version.androidx.compose.runtime"
            style="text-decoration: underline;">
            saveable
        </span>
        - 
        <span
            title="AndroidX.compose.runtime.tracing&#10;androidx.compose.runtime:runtime-tracing:_&#10;version.androidx.compose.runtime"
            style="text-decoration: underline;">
            tracing
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.compose.ui</b></td><td>
        <span
            title="AndroidX.compose.ui.geometry&#10;androidx.compose.ui:ui-geometry:_&#10;version.androidx.compose.ui"
            style="text-decoration: underline;">
            geometry
        </span>
        - 
        <span
            title="AndroidX.compose.ui.graphics&#10;androidx.compose.ui:ui-graphics:_&#10;version.androidx.compose.ui"
            style="text-decoration: underline;">
            graphics
        </span>
        - 
        <span
            title="AndroidX.compose.ui.testJunit4&#10;androidx.compose.ui:ui-test-junit4:_&#10;version.androidx.compose.ui"
            style="text-decoration: underline;">
            testJunit4
        </span>
        - 
        <span
            title="AndroidX.compose.ui.testManifest&#10;androidx.compose.ui:ui-test-manifest:_&#10;version.androidx.compose.ui"
            style="text-decoration: underline;">
            testManifest
        </span>
        - 
        <span
            title="AndroidX.compose.ui.test&#10;androidx.compose.ui:ui-test:_&#10;version.androidx.compose.ui"
            style="text-decoration: underline;">
            test
        </span>
        - 
        <span
            title="AndroidX.compose.ui.text&#10;androidx.compose.ui:ui-text:_&#10;version.androidx.compose.ui"
            style="text-decoration: underline;">
            text
        </span>
        - 
        <span
            title="AndroidX.compose.ui.toolingData&#10;androidx.compose.ui:ui-tooling-data:_&#10;version.androidx.compose.ui"
            style="text-decoration: underline;">
            toolingData
        </span>
        - 
        <span
            title="AndroidX.compose.ui.toolingPreview&#10;androidx.compose.ui:ui-tooling-preview:_&#10;version.androidx.compose.ui"
            style="text-decoration: underline;">
            toolingPreview
        </span>
        - 
        <span
            title="AndroidX.compose.ui.tooling&#10;androidx.compose.ui:ui-tooling:_&#10;version.androidx.compose.ui"
            style="text-decoration: underline;">
            tooling
        </span>
        - 
        <span
            title="AndroidX.compose.ui.unit&#10;androidx.compose.ui:ui-unit:_&#10;version.androidx.compose.ui"
            style="text-decoration: underline;">
            unit
        </span>
        - 
        <span
            title="AndroidX.compose.ui.util&#10;androidx.compose.ui:ui-util:_&#10;version.androidx.compose.ui"
            style="text-decoration: underline;">
            util
        </span>
        - 
        <span
            title="AndroidX.compose.ui.viewBinding&#10;androidx.compose.ui:ui-viewbinding:_&#10;version.androidx.compose.ui"
            style="text-decoration: underline;">
            viewBinding
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.compose.ui.text</b></td><td>
        <span
            title="AndroidX.compose.ui.text.googleFonts&#10;androidx.compose.ui:ui-text-google-fonts:_&#10;version.androidx.compose.ui"
            style="text-decoration: underline;">
            googleFonts
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.concurrent</b></td><td>
        <span
            title="AndroidX.concurrent.futuresKtx&#10;androidx.concurrent:concurrent-futures-ktx:_&#10;version.androidx.concurrent"
            style="text-decoration: underline;">
            futuresKtx
        </span>
        - 
        <span
            title="AndroidX.concurrent.futures&#10;androidx.concurrent:concurrent-futures:_&#10;version.androidx.concurrent"
            style="text-decoration: underline;">
            futures
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.constraintLayout</b></td><td>
        <span
            title="AndroidX.constraintLayout.compose&#10;androidx.constraintlayout:constraintlayout-compose:_&#10;version.androidx.constraintlayout-compose"
            style="text-decoration: underline;">
            compose
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.core</b></td><td>
        <span
            title="AndroidX.core.animationTesting&#10;androidx.core:core-animation-testing:_&#10;version.androidx.core-animation"
            style="text-decoration: underline;">
            animationTesting
        </span>
        - 
        <span
            title="AndroidX.core.animation&#10;androidx.core:core-animation:_&#10;version.androidx.core-animation"
            style="text-decoration: underline;">
            animation
        </span>
        - 
        <span
            title="AndroidX.core.googleShortcuts&#10;androidx.core:core-google-shortcuts:_&#10;version.androidx.core-google-shortcuts"
            style="text-decoration: underline;">
            googleShortcuts
        </span>
        - 
        <span
            title="AndroidX.core.ktx&#10;androidx.core:core-ktx:_&#10;version.androidx.core"
            style="text-decoration: underline;">
            ktx
        </span>
        - 
        <span
            title="AndroidX.core.performance&#10;androidx.core:core-performance:_&#10;version.androidx.core-performance"
            style="text-decoration: underline;">
            performance
        </span>
        - 
        <span
            title="AndroidX.core.remoteViews&#10;androidx.core:core-remoteviews:_&#10;version.androidx.core"
            style="text-decoration: underline;">
            remoteViews
        </span>
        - 
        <span
            title="AndroidX.core.role&#10;androidx.core:core-role:_&#10;version.androidx.core-role"
            style="text-decoration: underline;">
            role
        </span>
        - 
        <span
            title="AndroidX.core.splashscreen&#10;androidx.core:core-splashscreen:_&#10;version.androidx.core-splashscreen"
            style="text-decoration: underline;">
            splashscreen
        </span>
        - 
        <span
            title="AndroidX.core.uwb&#10;androidx.core.uwb:uwb:_&#10;version.androidx.core.uwb"
            style="text-decoration: underline;">
            uwb
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.core.uwb</b></td><td>
        <span
            title="AndroidX.core.uwb.rxJava3&#10;androidx.core.uwb:uwb-rxjava3:_&#10;version.androidx.core.uwb"
            style="text-decoration: underline;">
            rxJava3
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.customView</b></td><td>
        <span
            title="AndroidX.customView.poolingContainer&#10;androidx.customview:customview-poolingcontainer:_&#10;version.androidx.customview-poolingcontainer"
            style="text-decoration: underline;">
            poolingContainer
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.dataStore</b></td><td>
        <span
            title="AndroidX.dataStore.core&#10;androidx.datastore:datastore-core:_&#10;version.androidx.datastore"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="AndroidX.dataStore.preferences&#10;androidx.datastore:datastore-preferences:_&#10;version.androidx.datastore"
            style="text-decoration: underline;">
            preferences
        </span>
        - 
        <span
            title="AndroidX.dataStore.rxJava2&#10;androidx.datastore:datastore-rxJava2:_&#10;version.androidx.datastore"
            style="text-decoration: underline;">
            rxJava2
        </span>
        - 
        <span
            title="AndroidX.dataStore.rxJava3&#10;androidx.datastore:datastore-rxJava3:_&#10;version.androidx.datastore"
            style="text-decoration: underline;">
            rxJava3
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.dataStore.core</b></td><td>
        <span
            title="AndroidX.dataStore.core.okio&#10;androidx.datastore:datastore-core-okio:_&#10;version.androidx.datastore"
            style="text-decoration: underline;">
            okio
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.dataStore.preferences</b></td><td>
        <span
            title="AndroidX.dataStore.preferences.core&#10;androidx.datastore:datastore-preferences-core:_&#10;version.androidx.datastore"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="AndroidX.dataStore.preferences.rxJava2&#10;androidx.datastore:datastore-preferences-rxJava2:_&#10;version.androidx.datastore"
            style="text-decoration: underline;">
            rxJava2
        </span>
        - 
        <span
            title="AndroidX.dataStore.preferences.rxJava3&#10;androidx.datastore:datastore-preferences-rxJava3:_&#10;version.androidx.datastore"
            style="text-decoration: underline;">
            rxJava3
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.dynamicAnimation</b></td><td>
        <span
            title="AndroidX.dynamicAnimation.ktx&#10;androidx.dynamicanimation:dynamicanimation-ktx:_&#10;version.androidx.dynamicanimation-ktx"
            style="text-decoration: underline;">
            ktx
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.emoji</b></td><td>
        <span
            title="AndroidX.emoji.appCompat&#10;androidx.emoji:emoji-appcompat:_&#10;version.androidx.emoji"
            style="text-decoration: underline;">
            appCompat
        </span>
        - 
        <span
            title="AndroidX.emoji.bundled&#10;androidx.emoji:emoji-bundled:_&#10;version.androidx.emoji"
            style="text-decoration: underline;">
            bundled
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.emoji2</b></td><td>
        <span
            title="AndroidX.emoji2.bundled&#10;androidx.emoji2:emoji2-bundled:_&#10;version.androidx.emoji2"
            style="text-decoration: underline;">
            bundled
        </span>
        - 
        <span
            title="AndroidX.emoji2.viewsHelper&#10;androidx.emoji2:emoji2-views-helper:_&#10;version.androidx.emoji2"
            style="text-decoration: underline;">
            viewsHelper
        </span>
        - 
        <span
            title="AndroidX.emoji2.views&#10;androidx.emoji2:emoji2-views:_&#10;version.androidx.emoji2"
            style="text-decoration: underline;">
            views
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.enterprise</b></td><td>
        <span
            title="AndroidX.enterprise.feedbackTesting&#10;androidx.enterprise:enterprise-feedback-testing:_&#10;version.androidx.enterprise"
            style="text-decoration: underline;">
            feedbackTesting
        </span>
        - 
        <span
            title="AndroidX.enterprise.feedback&#10;androidx.enterprise:enterprise-feedback:_&#10;version.androidx.enterprise"
            style="text-decoration: underline;">
            feedback
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.fragment</b></td><td>
        <span
            title="AndroidX.fragment.ktx&#10;androidx.fragment:fragment-ktx:_&#10;version.androidx.fragment"
            style="text-decoration: underline;">
            ktx
        </span>
        - 
        <span
            title="AndroidX.fragment.testing&#10;androidx.fragment:fragment-testing:_&#10;version.androidx.fragment"
            style="text-decoration: underline;">
            testing
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.games</b></td><td>
        <span
            title="AndroidX.games.activity&#10;androidx.games:games-activity:_&#10;version.androidx.games-activity"
            style="text-decoration: underline;">
            activity
        </span>
        - 
        <span
            title="AndroidX.games.controller&#10;androidx.games:games-controller:_&#10;version.androidx.games-controller"
            style="text-decoration: underline;">
            controller
        </span>
        - 
        <span
            title="AndroidX.games.framePacing&#10;androidx.games:games-frame-pacing:_&#10;version.androidx.games-frame-pacing"
            style="text-decoration: underline;">
            framePacing
        </span>
        - 
        <span
            title="AndroidX.games.performanceTuner&#10;androidx.games:games-performance-tuner:_&#10;version.androidx.games-performance-tuner"
            style="text-decoration: underline;">
            performanceTuner
        </span>
        - 
        <span
            title="AndroidX.games.textInput&#10;androidx.games:games-text-input:_&#10;version.androidx.games-text-input"
            style="text-decoration: underline;">
            textInput
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.glance</b></td><td>
        <span
            title="AndroidX.glance.appWidget&#10;androidx.glance:glance-appwidget:_&#10;version.androidx.glance"
            style="text-decoration: underline;">
            appWidget
        </span>
        - 
        <span
            title="AndroidX.glance.wearTiles&#10;androidx.glance:glance-wear-tiles:_&#10;version.androidx.glance"
            style="text-decoration: underline;">
            wearTiles
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.graphics</b></td><td>
        <span
            title="AndroidX.graphics.core&#10;androidx.graphics:graphics-core:_&#10;version.androidx.graphics"
            style="text-decoration: underline;">
            core
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.health</b></td><td>
        <span
            title="AndroidX.health.servicesClient&#10;androidx.health:health-services-client:_&#10;version.androidx.health-services-client"
            style="text-decoration: underline;">
            servicesClient
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.health.connect</b></td><td>
        <span
            title="AndroidX.health.connect.client&#10;androidx.health.connect:connect-client:_&#10;version.androidx.health.connect"
            style="text-decoration: underline;">
            client
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.hilt</b></td><td>
        <span
            title="AndroidX.hilt.compiler&#10;androidx.hilt:hilt-compiler:_&#10;version.androidx.hilt"
            style="text-decoration: underline;">
            compiler
        </span>
        - 
        <span
            title="AndroidX.hilt.navigationCompose&#10;androidx.hilt:hilt-navigation-compose:_&#10;version.androidx.hilt-navigation-compose"
            style="text-decoration: underline;">
            navigationCompose
        </span>
        - 
        <span
            title="AndroidX.hilt.navigationFragment&#10;androidx.hilt:hilt-navigation-fragment:_&#10;version.androidx.hilt"
            style="text-decoration: underline;">
            navigationFragment
        </span>
        - 
        <span
            title="AndroidX.hilt.work&#10;androidx.hilt:hilt-work:_&#10;version.androidx.hilt"
            style="text-decoration: underline;">
            work
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.input</b></td><td>
        <span
            title="AndroidX.input.motionPrediction&#10;androidx.input:input-motionprediction:_&#10;version.androidx.input"
            style="text-decoration: underline;">
            motionPrediction
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.leanback</b></td><td>
        <span
            title="AndroidX.leanback.grid&#10;androidx.leanback:leanback-grid:_&#10;version.androidx.leanback-grid"
            style="text-decoration: underline;">
            grid
        </span>
        - 
        <span
            title="AndroidX.leanback.paging&#10;androidx.leanback:leanback-paging:_&#10;version.androidx.leanback-paging"
            style="text-decoration: underline;">
            paging
        </span>
        - 
        <span
            title="AndroidX.leanback.preference&#10;androidx.leanback:leanback-preference:_&#10;version.androidx.leanback"
            style="text-decoration: underline;">
            preference
        </span>
        - 
        <span
            title="AndroidX.leanback.tab&#10;androidx.leanback:leanback-tab:_&#10;version.androidx.leanback-tab"
            style="text-decoration: underline;">
            tab
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.lifecycle</b></td><td>
        <span
            title="AndroidX.lifecycle.commonJava8&#10;androidx.lifecycle:lifecycle-common-java8:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            commonJava8
        </span>
        - 
        <span
            title="AndroidX.lifecycle.common&#10;androidx.lifecycle:lifecycle-common:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            common
        </span>
        - 
        <span
            title="AndroidX.lifecycle.compiler&#10;androidx.lifecycle:lifecycle-compiler:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            compiler
        </span>
        - 
        <span
            title="AndroidX.lifecycle.extensions&#10;androidx.lifecycle:lifecycle-extensions:_&#10;version.androidx.lifecycle-extensions"
            style="text-decoration: underline;">
            extensions
        </span>
        - 
        <span
            title="AndroidX.lifecycle.liveDataKtx&#10;androidx.lifecycle:lifecycle-livedata-ktx:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            liveDataKtx
        </span>
        - 
        <span
            title="AndroidX.lifecycle.liveData&#10;androidx.lifecycle:lifecycle-livedata:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            liveData
        </span>
        - 
        <span
            title="AndroidX.lifecycle.process&#10;androidx.lifecycle:lifecycle-process:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            process
        </span>
        - 
        <span
            title="AndroidX.lifecycle.reactiveStreamsKtx&#10;androidx.lifecycle:lifecycle-reactivestreams-ktx:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            reactiveStreamsKtx
        </span>
        - 
        <span
            title="AndroidX.lifecycle.reactiveStreams&#10;androidx.lifecycle:lifecycle-reactivestreams:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            reactiveStreams
        </span>
        - 
        <span
            title="AndroidX.lifecycle.runtime&#10;androidx.lifecycle:lifecycle-runtime:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            runtime
        </span>
        - 
        <span
            title="AndroidX.lifecycle.service&#10;androidx.lifecycle:lifecycle-service:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            service
        </span>
        - 
        <span
            title="AndroidX.lifecycle.viewModelCompose&#10;androidx.lifecycle:lifecycle-viewmodel-compose:_&#10;version.androidx.lifecycle-viewmodel-compose"
            style="text-decoration: underline;">
            viewModelCompose
        </span>
        - 
        <span
            title="AndroidX.lifecycle.viewModelKtx&#10;androidx.lifecycle:lifecycle-viewmodel-ktx:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            viewModelKtx
        </span>
        - 
        <span
            title="AndroidX.lifecycle.viewModelSavedState&#10;androidx.lifecycle:lifecycle-viewmodel-savedstate:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            viewModelSavedState
        </span>
        - 
        <span
            title="AndroidX.lifecycle.viewModel&#10;androidx.lifecycle:lifecycle-viewmodel:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            viewModel
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.lifecycle.runtime</b></td><td>
        <span
            title="AndroidX.lifecycle.runtime.compose&#10;androidx.lifecycle:lifecycle-runtime-compose:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            compose
        </span>
        - 
        <span
            title="AndroidX.lifecycle.runtime.ktx&#10;androidx.lifecycle:lifecycle-runtime-ktx:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            ktx
        </span>
        - 
        <span
            title="AndroidX.lifecycle.runtime.testing&#10;androidx.lifecycle:lifecycle-runtime-testing:_&#10;version.androidx.lifecycle"
            style="text-decoration: underline;">
            testing
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.media2</b></td><td>
        <span
            title="AndroidX.media2.common&#10;androidx.media2:media2-common:_&#10;version.androidx.media2"
            style="text-decoration: underline;">
            common
        </span>
        - 
        <span
            title="AndroidX.media2.exoplayer&#10;androidx.media2:media2-exoplayer:_&#10;version.androidx.media2"
            style="text-decoration: underline;">
            exoplayer
        </span>
        - 
        <span
            title="AndroidX.media2.player&#10;androidx.media2:media2-player:_&#10;version.androidx.media2"
            style="text-decoration: underline;">
            player
        </span>
        - 
        <span
            title="AndroidX.media2.session&#10;androidx.media2:media2-session:_&#10;version.androidx.media2"
            style="text-decoration: underline;">
            session
        </span>
        - 
        <span
            title="AndroidX.media2.widget&#10;androidx.media2:media2-widget:_&#10;version.androidx.media2"
            style="text-decoration: underline;">
            widget
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.media3</b></td><td>
        <span
            title="AndroidX.media3.cast&#10;androidx.media3:media3-cast:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            cast
        </span>
        - 
        <span
            title="AndroidX.media3.common&#10;androidx.media3:media3-common:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            common
        </span>
        - 
        <span
            title="AndroidX.media3.database&#10;androidx.media3:media3-database:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            database
        </span>
        - 
        <span
            title="AndroidX.media3.dataSource&#10;androidx.media3:media3-datasource:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            dataSource
        </span>
        - 
        <span
            title="AndroidX.media3.decoder&#10;androidx.media3:media3-decoder:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            decoder
        </span>
        - 
        <span
            title="AndroidX.media3.exoPlayer&#10;androidx.media3:media3-exoplayer:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            exoPlayer
        </span>
        - 
        <span
            title="AndroidX.media3.extractor&#10;androidx.media3:media3-extractor:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            extractor
        </span>
        - 
        <span
            title="AndroidX.media3.session&#10;androidx.media3:media3-session:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            session
        </span>
        - 
        <span
            title="AndroidX.media3.testUtils&#10;androidx.media3:media3-test-utils:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            testUtils
        </span>
        - 
        <span
            title="AndroidX.media3.transformer&#10;androidx.media3:media3-transformer:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            transformer
        </span>
        - 
        <span
            title="AndroidX.media3.ui&#10;androidx.media3:media3-ui:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            ui
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.media3.dataSource</b></td><td>
        <span
            title="AndroidX.media3.dataSource.cronet&#10;androidx.media3:media3-datasource-cronet:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            cronet
        </span>
        - 
        <span
            title="AndroidX.media3.dataSource.okhttp&#10;androidx.media3:media3-datasource-okhttp:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            okhttp
        </span>
        - 
        <span
            title="AndroidX.media3.dataSource.rtmp&#10;androidx.media3:media3-datasource-rtmp:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            rtmp
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.media3.exoPlayer</b></td><td>
        <span
            title="AndroidX.media3.exoPlayer.dash&#10;androidx.media3:media3-exoplayer-dash:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            dash
        </span>
        - 
        <span
            title="AndroidX.media3.exoPlayer.hls&#10;androidx.media3:media3-exoplayer-hls:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            hls
        </span>
        - 
        <span
            title="AndroidX.media3.exoPlayer.ima&#10;androidx.media3:media3-exoplayer-ima:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            ima
        </span>
        - 
        <span
            title="AndroidX.media3.exoPlayer.rtsp&#10;androidx.media3:media3-exoplayer-rtsp:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            rtsp
        </span>
        - 
        <span
            title="AndroidX.media3.exoPlayer.workmanager&#10;androidx.media3:media3-exoplayer-workmanager:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            workmanager
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.media3.testUtils</b></td><td>
        <span
            title="AndroidX.media3.testUtils.robolectric&#10;androidx.media3:media3-test-utils-robolectric:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            robolectric
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.media3.ui</b></td><td>
        <span
            title="AndroidX.media3.ui.leanback&#10;androidx.media3:media3-ui-leanback:_&#10;version.androidx.media3"
            style="text-decoration: underline;">
            leanback
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.metrics</b></td><td>
        <span
            title="AndroidX.metrics.performance&#10;androidx.metrics:metrics-performance:_&#10;version.androidx.metrics"
            style="text-decoration: underline;">
            performance
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.multidex</b></td><td>
        <span
            title="AndroidX.multidex.instrumentation&#10;androidx.multidex:multidex-instrumentation:_&#10;version.androidx.multidex"
            style="text-decoration: underline;">
            instrumentation
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.navigation</b></td><td>
        <span
            title="AndroidX.navigation.commonKtx&#10;androidx.navigation:navigation-common-ktx:_&#10;version.androidx.navigation"
            style="text-decoration: underline;">
            commonKtx
        </span>
        - 
        <span
            title="AndroidX.navigation.common&#10;androidx.navigation:navigation-common:_&#10;version.androidx.navigation"
            style="text-decoration: underline;">
            common
        </span>
        - 
        <span
            title="AndroidX.navigation.compose&#10;androidx.navigation:navigation-compose:_&#10;version.androidx.navigation"
            style="text-decoration: underline;">
            compose
        </span>
        - 
        <span
            title="AndroidX.navigation.dynamicFeaturesFragment&#10;androidx.navigation:navigation-dynamic-features-fragment:_&#10;version.androidx.navigation"
            style="text-decoration: underline;">
            dynamicFeaturesFragment
        </span>
        - 
        <span
            title="AndroidX.navigation.fragmentKtx&#10;androidx.navigation:navigation-fragment-ktx:_&#10;version.androidx.navigation"
            style="text-decoration: underline;">
            fragmentKtx
        </span>
        - 
        <span
            title="AndroidX.navigation.fragment&#10;androidx.navigation:navigation-fragment:_&#10;version.androidx.navigation"
            style="text-decoration: underline;">
            fragment
        </span>
        - 
        <span
            title="AndroidX.navigation.runtimeKtx&#10;androidx.navigation:navigation-runtime-ktx:_&#10;version.androidx.navigation"
            style="text-decoration: underline;">
            runtimeKtx
        </span>
        - 
        <span
            title="AndroidX.navigation.runtime&#10;androidx.navigation:navigation-runtime:_&#10;version.androidx.navigation"
            style="text-decoration: underline;">
            runtime
        </span>
        - 
        <span
            title="AndroidX.navigation.safeArgsGenerator&#10;androidx.navigation:navigation-safe-args-generator:_&#10;version.androidx.navigation"
            style="text-decoration: underline;">
            safeArgsGenerator
        </span>
        - 
        <span
            title="AndroidX.navigation.safeArgsGradlePlugin&#10;androidx.navigation:navigation-safe-args-gradle-plugin:_&#10;version.androidx.navigation"
            style="text-decoration: underline;">
            safeArgsGradlePlugin
        </span>
        - 
        <span
            title="AndroidX.navigation.testing&#10;androidx.navigation:navigation-testing:_&#10;version.androidx.navigation"
            style="text-decoration: underline;">
            testing
        </span>
        - 
        <span
            title="AndroidX.navigation.uiKtx&#10;androidx.navigation:navigation-ui-ktx:_&#10;version.androidx.navigation"
            style="text-decoration: underline;">
            uiKtx
        </span>
        - 
        <span
            title="AndroidX.navigation.ui&#10;androidx.navigation:navigation-ui:_&#10;version.androidx.navigation"
            style="text-decoration: underline;">
            ui
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.paging</b></td><td>
        <span
            title="AndroidX.paging.commonKtx&#10;androidx.paging:paging-common-ktx:_&#10;version.androidx.paging"
            style="text-decoration: underline;">
            commonKtx
        </span>
        - 
        <span
            title="AndroidX.paging.common&#10;androidx.paging:paging-common:_&#10;version.androidx.paging"
            style="text-decoration: underline;">
            common
        </span>
        - 
        <span
            title="AndroidX.paging.compose&#10;androidx.paging:paging-compose:_&#10;version.androidx.paging-compose"
            style="text-decoration: underline;">
            compose
        </span>
        - 
        <span
            title="AndroidX.paging.guava&#10;androidx.paging:paging-guava:_&#10;version.androidx.paging"
            style="text-decoration: underline;">
            guava
        </span>
        - 
        <span
            title="AndroidX.paging.runtimeKtx&#10;androidx.paging:paging-runtime-ktx:_&#10;version.androidx.paging"
            style="text-decoration: underline;">
            runtimeKtx
        </span>
        - 
        <span
            title="AndroidX.paging.runtime&#10;androidx.paging:paging-runtime:_&#10;version.androidx.paging"
            style="text-decoration: underline;">
            runtime
        </span>
        - 
        <span
            title="AndroidX.paging.rxJava2Ktx&#10;androidx.paging:paging-rxjava2-ktx:_&#10;version.androidx.paging"
            style="text-decoration: underline;">
            rxJava2Ktx
        </span>
        - 
        <span
            title="AndroidX.paging.rxJava2&#10;androidx.paging:paging-rxjava2:_&#10;version.androidx.paging"
            style="text-decoration: underline;">
            rxJava2
        </span>
        - 
        <span
            title="AndroidX.paging.rxJava3&#10;androidx.paging:paging-rxjava3:_&#10;version.androidx.paging"
            style="text-decoration: underline;">
            rxJava3
        </span>
        - 
        <span
            title="AndroidX.paging.testing&#10;androidx.paging:paging-testing:_&#10;version.androidx.paging"
            style="text-decoration: underline;">
            testing
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.palette</b></td><td>
        <span
            title="AndroidX.palette.ktx&#10;androidx.palette:palette-ktx:_&#10;version.androidx.palette"
            style="text-decoration: underline;">
            ktx
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.preference</b></td><td>
        <span
            title="AndroidX.preference.ktx&#10;androidx.preference:preference-ktx:_&#10;version.androidx.preference"
            style="text-decoration: underline;">
            ktx
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.recyclerView</b></td><td>
        <span
            title="AndroidX.recyclerView.selection&#10;androidx.recyclerview:recyclerview-selection:_&#10;version.androidx.recyclerview-selection"
            style="text-decoration: underline;">
            selection
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.remoteCallback</b></td><td>
        <span
            title="AndroidX.remoteCallback.processor&#10;androidx.remotecallback:remotecallback-processor:_&#10;version.androidx.remotecallback"
            style="text-decoration: underline;">
            processor
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.room</b></td><td>
        <span
            title="AndroidX.room.common&#10;androidx.room:room-common:_&#10;version.androidx.room"
            style="text-decoration: underline;">
            common
        </span>
        - 
        <span
            title="AndroidX.room.compiler&#10;androidx.room:room-compiler:_&#10;version.androidx.room"
            style="text-decoration: underline;">
            compiler
        </span>
        - 
        <span
            title="AndroidX.room.guava&#10;androidx.room:room-guava:_&#10;version.androidx.room"
            style="text-decoration: underline;">
            guava
        </span>
        - 
        <span
            title="AndroidX.room.ktx&#10;androidx.room:room-ktx:_&#10;version.androidx.room"
            style="text-decoration: underline;">
            ktx
        </span>
        - 
        <span
            title="AndroidX.room.paging&#10;androidx.room:room-paging:_&#10;version.androidx.room"
            style="text-decoration: underline;">
            paging
        </span>
        - 
        <span
            title="AndroidX.room.runtime&#10;androidx.room:room-runtime:_&#10;version.androidx.room"
            style="text-decoration: underline;">
            runtime
        </span>
        - 
        <span
            title="AndroidX.room.rxJava2&#10;androidx.room:room-rxjava2:_&#10;version.androidx.room"
            style="text-decoration: underline;">
            rxJava2
        </span>
        - 
        <span
            title="AndroidX.room.rxJava3&#10;androidx.room:room-rxjava3:_&#10;version.androidx.room"
            style="text-decoration: underline;">
            rxJava3
        </span>
        - 
        <span
            title="AndroidX.room.testing&#10;androidx.room:room-testing:_&#10;version.androidx.room"
            style="text-decoration: underline;">
            testing
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.room.paging</b></td><td>
        <span
            title="AndroidX.room.paging.guava&#10;androidx.room:room-paging-guava:_&#10;version.androidx.room"
            style="text-decoration: underline;">
            guava
        </span>
        - 
        <span
            title="AndroidX.room.paging.rxJava2&#10;androidx.room:room-paging-rxjava2:_&#10;version.androidx.room"
            style="text-decoration: underline;">
            rxJava2
        </span>
        - 
        <span
            title="AndroidX.room.paging.rxJava3&#10;androidx.room:room-paging-rxjava3:_&#10;version.androidx.room"
            style="text-decoration: underline;">
            rxJava3
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.savedState</b></td><td>
        <span
            title="AndroidX.savedState.ktx&#10;androidx.savedstate:savedstate-ktx:_&#10;version.androidx.savedstate"
            style="text-decoration: underline;">
            ktx
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.security</b></td><td>
        <span
            title="AndroidX.security.appAuthenticatorTesting&#10;androidx.security:security-app-authenticator-testing:_&#10;version.androidx.security-app-authenticator-testing"
            style="text-decoration: underline;">
            appAuthenticatorTesting
        </span>
        - 
        <span
            title="AndroidX.security.appAuthenticator&#10;androidx.security:security-app-authenticator:_&#10;version.androidx.security-app-authenticator"
            style="text-decoration: underline;">
            appAuthenticator
        </span>
        - 
        <span
            title="AndroidX.security.cryptoKtx&#10;androidx.security:security-crypto-ktx:_&#10;version.androidx.security-crypto"
            style="text-decoration: underline;">
            cryptoKtx
        </span>
        - 
        <span
            title="AndroidX.security.crypto&#10;androidx.security:security-crypto:_&#10;version.androidx.security-crypto"
            style="text-decoration: underline;">
            crypto
        </span>
        - 
        <span
            title="AndroidX.security.identityCredential&#10;androidx.security:security-identity-credential:_&#10;version.androidx.security-identity-credential"
            style="text-decoration: underline;">
            identityCredential
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.slice</b></td><td>
        <span
            title="AndroidX.slice.buildersKtx&#10;androidx.slice:slice-builders-ktx:_&#10;version.androidx.slice-builders-ktx"
            style="text-decoration: underline;">
            buildersKtx
        </span>
        - 
        <span
            title="AndroidX.slice.builders&#10;androidx.slice:slice-builders:_&#10;version.androidx.slice"
            style="text-decoration: underline;">
            builders
        </span>
        - 
        <span
            title="AndroidX.slice.core&#10;androidx.slice:slice-core:_&#10;version.androidx.slice"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="AndroidX.slice.view&#10;androidx.slice:slice-view:_&#10;version.androidx.slice"
            style="text-decoration: underline;">
            view
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.sqlite</b></td><td>
        <span
            title="AndroidX.sqlite.framework&#10;androidx.sqlite:sqlite-framework:_&#10;version.androidx.sqlite"
            style="text-decoration: underline;">
            framework
        </span>
        - 
        <span
            title="AndroidX.sqlite.ktx&#10;androidx.sqlite:sqlite-ktx:_&#10;version.androidx.sqlite"
            style="text-decoration: underline;">
            ktx
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.startup</b></td><td>
        <span
            title="AndroidX.startup.runtime&#10;androidx.startup:startup-runtime:_&#10;version.androidx.startup"
            style="text-decoration: underline;">
            runtime
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.test</b></td><td>
        <span
            title="AndroidX.test.coreKtx&#10;androidx.test:core-ktx:_&#10;version.androidx.test.core"
            style="text-decoration: underline;">
            coreKtx
        </span>
        - 
        <span
            title="AndroidX.test.core&#10;androidx.test:core:_&#10;version.androidx.test.core"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="AndroidX.test.monitor&#10;androidx.test:monitor:_&#10;version.androidx.test.monitor"
            style="text-decoration: underline;">
            monitor
        </span>
        - 
        <span
            title="AndroidX.test.orchestrator&#10;androidx.test:orchestrator:_&#10;version.androidx.test.orchestrator"
            style="text-decoration: underline;">
            orchestrator
        </span>
        - 
        <span
            title="AndroidX.test.rules&#10;androidx.test:rules:_&#10;version.androidx.test.rules"
            style="text-decoration: underline;">
            rules
        </span>
        - 
        <span
            title="AndroidX.test.runner&#10;androidx.test:runner:_&#10;version.androidx.test.runner"
            style="text-decoration: underline;">
            runner
        </span>
        - 
        <span
            title="AndroidX.test.services&#10;androidx.test.services:test-services:_&#10;version.androidx.test.services"
            style="text-decoration: underline;">
            services
        </span>
        - 
        <span
            title="AndroidX.test.uiAutomator&#10;androidx.test.uiautomator:uiautomator:_&#10;version.androidx.test.uiautomator"
            style="text-decoration: underline;">
            uiAutomator
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.test.espresso</b></td><td>
        <span
            title="AndroidX.test.espresso.accessibility&#10;androidx.test.espresso:espresso-accessibility:_&#10;version.androidx.test.espresso"
            style="text-decoration: underline;">
            accessibility
        </span>
        - 
        <span
            title="AndroidX.test.espresso.contrib&#10;androidx.test.espresso:espresso-contrib:_&#10;version.androidx.test.espresso"
            style="text-decoration: underline;">
            contrib
        </span>
        - 
        <span
            title="AndroidX.test.espresso.core&#10;androidx.test.espresso:espresso-core:_&#10;version.androidx.test.espresso"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="AndroidX.test.espresso.device&#10;androidx.test.espresso:espresso-device:_&#10;version.androidx.test.espresso-device"
            style="text-decoration: underline;">
            device
        </span>
        - 
        <span
            title="AndroidX.test.espresso.idlingResource&#10;androidx.test.espresso:espresso-idling-resource:_&#10;version.androidx.test.espresso"
            style="text-decoration: underline;">
            idlingResource
        </span>
        - 
        <span
            title="AndroidX.test.espresso.intents&#10;androidx.test.espresso:espresso-intents:_&#10;version.androidx.test.espresso"
            style="text-decoration: underline;">
            intents
        </span>
        - 
        <span
            title="AndroidX.test.espresso.remote&#10;androidx.test.espresso:espresso-remote:_&#10;version.androidx.test.espresso"
            style="text-decoration: underline;">
            remote
        </span>
        - 
        <span
            title="AndroidX.test.espresso.web&#10;androidx.test.espresso:espresso-web:_&#10;version.androidx.test.espresso"
            style="text-decoration: underline;">
            web
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.test.espresso.idling</b></td><td>
        <span
            title="AndroidX.test.espresso.idling.concurrent&#10;androidx.test.espresso.idling:idling-concurrent:_&#10;version.androidx.test.espresso.idling"
            style="text-decoration: underline;">
            concurrent
        </span>
        - 
        <span
            title="AndroidX.test.espresso.idling.net&#10;androidx.test.espresso.idling:idling-net:_&#10;version.androidx.test.espresso.idling"
            style="text-decoration: underline;">
            net
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.test.ext</b></td><td>
        <span
            title="AndroidX.test.ext.junit&#10;androidx.test.ext:junit:_&#10;version.androidx.test.ext.junit"
            style="text-decoration: underline;">
            junit
        </span>
        - 
        <span
            title="AndroidX.test.ext.truth&#10;androidx.test.ext:truth:_&#10;version.androidx.test.ext.truth"
            style="text-decoration: underline;">
            truth
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.test.ext.junit</b></td><td>
        <span
            title="AndroidX.test.ext.junit.gTest&#10;androidx.test.ext:junit-gtest:_&#10;version.androidx.test.ext.junit-gtest"
            style="text-decoration: underline;">
            gTest
        </span>
        - 
        <span
            title="AndroidX.test.ext.junit.ktx&#10;androidx.test.ext:junit-ktx:_&#10;version.androidx.test.ext.junit"
            style="text-decoration: underline;">
            ktx
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.tracing</b></td><td>
        <span
            title="AndroidX.tracing.ktx&#10;androidx.tracing:tracing-ktx:_&#10;version.androidx.tracing"
            style="text-decoration: underline;">
            ktx
        </span>
        - 
        <span
            title="AndroidX.tracing.perfetto&#10;androidx.tracing:tracing-perfetto:_&#10;version.androidx.tracing-perfetto"
            style="text-decoration: underline;">
            perfetto
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.tv</b></td><td>
        <span
            title="AndroidX.tv.foundation&#10;androidx.tv:tv-foundation:_&#10;version.androidx.tv"
            style="text-decoration: underline;">
            foundation
        </span>
        - 
        <span
            title="AndroidX.tv.material&#10;androidx.tv:tv-material:_&#10;version.androidx.tv"
            style="text-decoration: underline;">
            material
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.vectorDrawable</b></td><td>
        <span
            title="AndroidX.vectorDrawable.animated&#10;androidx.vectordrawable:vectordrawable-animated:_&#10;version.androidx.vectordrawable-animated"
            style="text-decoration: underline;">
            animated
        </span>
        - 
        <span
            title="AndroidX.vectorDrawable.seekable&#10;androidx.vectordrawable:vectordrawable-seekable:_&#10;version.androidx.vectordrawable-seekable"
            style="text-decoration: underline;">
            seekable
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.wear</b></td><td>
        <span
            title="AndroidX.wear.inputTesting&#10;androidx.wear:wear-input-testing:_&#10;version.androidx.wear-input"
            style="text-decoration: underline;">
            inputTesting
        </span>
        - 
        <span
            title="AndroidX.wear.input&#10;androidx.wear:wear-input:_&#10;version.androidx.wear-input"
            style="text-decoration: underline;">
            input
        </span>
        - 
        <span
            title="AndroidX.wear.ongoing&#10;androidx.wear:wear-ongoing:_&#10;version.androidx.wear-ongoing"
            style="text-decoration: underline;">
            ongoing
        </span>
        - 
        <span
            title="AndroidX.wear.phoneInteractions&#10;androidx.wear:wear-phone-interactions:_&#10;version.androidx.wear-phone-interactions"
            style="text-decoration: underline;">
            phoneInteractions
        </span>
        - 
        <span
            title="AndroidX.wear.remoteInteractions&#10;androidx.wear:wear-remote-interactions:_&#10;version.androidx.wear-remote-interactions"
            style="text-decoration: underline;">
            remoteInteractions
        </span>
        - 
        <span
            title="AndroidX.wear.tiles&#10;androidx.wear.tiles:tiles:_&#10;version.androidx.wear.tiles"
            style="text-decoration: underline;">
            tiles
        </span>
        - 
        <span
            title="AndroidX.wear.watchFace&#10;androidx.wear.watchface:watchface:_&#10;version.androidx.wear.watchface"
            style="text-decoration: underline;">
            watchFace
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.wear.compose</b></td><td>
        <span
            title="AndroidX.wear.compose.foundation&#10;androidx.wear.compose:compose-foundation:_&#10;version.androidx.wear.compose.compose-foundation"
            style="text-decoration: underline;">
            foundation
        </span>
        - 
        <span
            title="AndroidX.wear.compose.material&#10;androidx.wear.compose:compose-material:_&#10;version.androidx.wear.compose.compose-material"
            style="text-decoration: underline;">
            material
        </span>
        - 
        <span
            title="AndroidX.wear.compose.navigation&#10;androidx.wear.compose:compose-navigation:_&#10;version.androidx.wear.compose.compose-navigation"
            style="text-decoration: underline;">
            navigation
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.wear.tiles</b></td><td>
        <span
            title="AndroidX.wear.tiles.material&#10;androidx.wear.tiles:tiles-material:_&#10;version.androidx.wear.tiles"
            style="text-decoration: underline;">
            material
        </span>
        - 
        <span
            title="AndroidX.wear.tiles.renderer&#10;androidx.wear.tiles:tiles-renderer:_&#10;version.androidx.wear.tiles"
            style="text-decoration: underline;">
            renderer
        </span>
        - 
        <span
            title="AndroidX.wear.tiles.testing&#10;androidx.wear.tiles:tiles-testing:_&#10;version.androidx.wear.tiles"
            style="text-decoration: underline;">
            testing
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.wear.watchFace</b></td><td>
        <span
            title="AndroidX.wear.watchFace.editor&#10;androidx.wear.watchface:watchface-editor:_&#10;version.androidx.wear.watchface-editor"
            style="text-decoration: underline;">
            editor
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.wear.watchFace.complications</b></td><td>
        <span
            title="AndroidX.wear.watchFace.complications.dataSourceKtx&#10;androidx.wear.watchface:watchface-complications-data-source-ktx:_&#10;version.androidx.wear.watchface-complications-data-source-ktx"
            style="text-decoration: underline;">
            dataSourceKtx
        </span>
        - 
        <span
            title="AndroidX.wear.watchFace.complications.dataSource&#10;androidx.wear.watchface:watchface-complications-data-source:_&#10;version.androidx.wear.watchface-complications-data-source"
            style="text-decoration: underline;">
            dataSource
        </span>
        - 
        <span
            title="AndroidX.wear.watchFace.complications.rendering&#10;androidx.wear.watchface:watchface-complications-rendering:_&#10;version.androidx.wear.watchface-complications-rendering"
            style="text-decoration: underline;">
            rendering
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.window</b></td><td>
        <span
            title="AndroidX.window.java&#10;androidx.window:window-java:_&#10;version.androidx.window"
            style="text-decoration: underline;">
            java
        </span>
        - 
        <span
            title="AndroidX.window.rxJava2&#10;androidx.window:window-rxjava2:_&#10;version.androidx.window"
            style="text-decoration: underline;">
            rxJava2
        </span>
        - 
        <span
            title="AndroidX.window.rxJava3&#10;androidx.window:window-rxjava3:_&#10;version.androidx.window"
            style="text-decoration: underline;">
            rxJava3
        </span>
        - 
        <span
            title="AndroidX.window.testing&#10;androidx.window:window-testing:_&#10;version.androidx.window"
            style="text-decoration: underline;">
            testing
        </span>
            
    </td></tr>
            
    <tr><td><b>AndroidX.work</b></td><td>
        <span
            title="AndroidX.work.gcm&#10;androidx.work:work-gcm:_&#10;version.androidx.work"
            style="text-decoration: underline;">
            gcm
        </span>
        - 
        <span
            title="AndroidX.work.multiprocess&#10;androidx.work:work-multiprocess:_&#10;version.androidx.work"
            style="text-decoration: underline;">
            multiprocess
        </span>
        - 
        <span
            title="AndroidX.work.runtimeKtx&#10;androidx.work:work-runtime-ktx:_&#10;version.androidx.work"
            style="text-decoration: underline;">
            runtimeKtx
        </span>
        - 
        <span
            title="AndroidX.work.runtime&#10;androidx.work:work-runtime:_&#10;version.androidx.work"
            style="text-decoration: underline;">
            runtime
        </span>
        - 
        <span
            title="AndroidX.work.rxJava2&#10;androidx.work:work-rxjava2:_&#10;version.androidx.work"
            style="text-decoration: underline;">
            rxJava2
        </span>
        - 
        <span
            title="AndroidX.work.rxJava3&#10;androidx.work:work-rxjava3:_&#10;version.androidx.work"
            style="text-decoration: underline;">
            rxJava3
        </span>
        - 
        <span
            title="AndroidX.work.testing&#10;androidx.work:work-testing:_&#10;version.androidx.work"
            style="text-decoration: underline;">
            testing
        </span>
            
    </td></tr>
            
</table>
            

## [ApolloGraphQL.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/ApolloGraphQL.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>ApolloGraphQL</b></td><td>
        <span
            title="ApolloGraphQL.adapters&#10;com.apollographql.apollo3:apollo-adapters:_&#10;version.apollographql"
            style="text-decoration: underline;">
            adapters
        </span>
        - 
        <span
            title="ApolloGraphQL.api&#10;com.apollographql.apollo3:apollo-api:_&#10;version.apollographql"
            style="text-decoration: underline;">
            api
        </span>
        - 
        <span
            title="ApolloGraphQL.ast&#10;com.apollographql.apollo3:apollo-ast:_&#10;version.apollographql"
            style="text-decoration: underline;">
            ast
        </span>
        - 
        <span
            title="ApolloGraphQL.httpCache&#10;com.apollographql.apollo3:apollo-http-cache:_&#10;version.apollographql"
            style="text-decoration: underline;">
            httpCache
        </span>
        - 
        <span
            title="ApolloGraphQL.idlingResource&#10;com.apollographql.apollo3:apollo-idling-resource:_&#10;version.apollographql"
            style="text-decoration: underline;">
            idlingResource
        </span>
        - 
        <span
            title="ApolloGraphQL.mockserver&#10;com.apollographql.apollo3:apollo-mockserver:_&#10;version.apollographql"
            style="text-decoration: underline;">
            mockserver
        </span>
        - 
        <span
            title="ApolloGraphQL.normalizedCacheSqlite&#10;com.apollographql.apollo3:apollo-normalized-cache-sqlite:_&#10;version.apollographql"
            style="text-decoration: underline;">
            normalizedCacheSqlite
        </span>
        - 
        <span
            title="ApolloGraphQL.normalizedCache&#10;com.apollographql.apollo3:apollo-normalized-cache:_&#10;version.apollographql"
            style="text-decoration: underline;">
            normalizedCache
        </span>
        - 
        <span
            title="ApolloGraphQL.runtime&#10;com.apollographql.apollo3:apollo-runtime:_&#10;version.apollographql"
            style="text-decoration: underline;">
            runtime
        </span>
        - 
        <span
            title="ApolloGraphQL.testingSupport&#10;com.apollographql.apollo3:apollo-testing-support:_&#10;version.apollographql"
            style="text-decoration: underline;">
            testingSupport
        </span>
            
    </td></tr>
            
</table>
            

## [Arrow.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Arrow.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>Arrow</b></td><td>
        <span
            title="Arrow.core&#10;io.arrow-kt:arrow-core:_&#10;version.arrow"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="Arrow.optics&#10;io.arrow-kt:arrow-optics:_&#10;version.arrow"
            style="text-decoration: underline;">
            optics
        </span>
        - 
        <span
            title="Arrow.stack&#10;io.arrow-kt:arrow-stack:_&#10;version.arrow"
            style="text-decoration: underline;">
            stack
        </span>
            
    </td></tr>
            
    <tr><td><b>Arrow.analysis</b></td><td>
        <span
            title="Arrow.analysis.gradlePlugin&#10;io.arrow-kt.analysis.kotlin:io.arrow-kt.analysis.kotlin.gradle.plugin:_&#10;version.io.arrow-kt.analysis.kotlin"
            style="text-decoration: underline;">
            gradlePlugin
        </span>
            
    </td></tr>
            
    <tr><td><b>Arrow.fx</b></td><td>
        <span
            title="Arrow.fx.coroutines&#10;io.arrow-kt:arrow-fx-coroutines:_&#10;version.arrow"
            style="text-decoration: underline;">
            coroutines
        </span>
        - 
        <span
            title="Arrow.fx.stm&#10;io.arrow-kt:arrow-fx-stm:_&#10;version.arrow"
            style="text-decoration: underline;">
            stm
        </span>
            
    </td></tr>
            
    <tr><td><b>Arrow.optics</b></td><td>
        <span
            title="Arrow.optics.kspPlugin&#10;io.arrow-kt:arrow-optics-ksp-plugin:_&#10;version.arrow"
            style="text-decoration: underline;">
            kspPlugin
        </span>
        - 
        <span
            title="Arrow.optics.reflect&#10;io.arrow-kt:arrow-optics-reflect:_&#10;version.arrow"
            style="text-decoration: underline;">
            reflect
        </span>
            
    </td></tr>
            
</table>
            

## [COIL.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/COIL.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>COIL</b></td><td>
        <span
            title="COIL.base&#10;io.coil-kt:coil-base:_&#10;version.coil-kt"
            style="text-decoration: underline;">
            base
        </span>
        - 
        <span
            title="COIL.composeBase&#10;io.coil-kt:coil-compose-base:_&#10;version.coil-kt"
            style="text-decoration: underline;">
            composeBase
        </span>
        - 
        <span
            title="COIL.compose&#10;io.coil-kt:coil-compose:_&#10;version.coil-kt"
            style="text-decoration: underline;">
            compose
        </span>
        - 
        <span
            title="COIL.gif&#10;io.coil-kt:coil-gif:_&#10;version.coil-kt"
            style="text-decoration: underline;">
            gif
        </span>
        - 
        <span
            title="COIL.svg&#10;io.coil-kt:coil-svg:_&#10;version.coil-kt"
            style="text-decoration: underline;">
            svg
        </span>
        - 
        <span
            title="COIL.video&#10;io.coil-kt:coil-video:_&#10;version.coil-kt"
            style="text-decoration: underline;">
            video
        </span>
        - 
        <span
            title="COIL&#10;io.coil-kt:coil:_&#10;version.coil-kt"
            style="text-decoration: underline;">
            COIL
        </span>
            
    </td></tr>
            
</table>
            

## [CashApp.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/CashApp.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>CashApp</b></td><td>
        <span
            title="CashApp.licenseeGradlePlugin&#10;app.cash.licensee:licensee-gradle-plugin:_&#10;version.app.cash.licensee"
            style="text-decoration: underline;">
            licenseeGradlePlugin
        </span>
        - 
        <span
            title="CashApp.turbine&#10;app.cash.turbine:turbine:_&#10;version.app.cash.turbine"
            style="text-decoration: underline;">
            turbine
        </span>
            
    </td></tr>
            
    <tr><td><b>CashApp.copper</b></td><td>
        <span
            title="CashApp.copper.flow&#10;app.cash.copper:copper-flow:_&#10;version.app.cash.copper"
            style="text-decoration: underline;">
            flow
        </span>
        - 
        <span
            title="CashApp.copper.rx2&#10;app.cash.copper:copper-rx2:_&#10;version.app.cash.copper"
            style="text-decoration: underline;">
            rx2
        </span>
        - 
        <span
            title="CashApp.copper.rx3&#10;app.cash.copper:copper-rx3:_&#10;version.app.cash.copper"
            style="text-decoration: underline;">
            rx3
        </span>
            
    </td></tr>
            
    <tr><td><b>CashApp.molecule</b></td><td>
        <span
            title="CashApp.molecule.gradlePlugin&#10;app.cash.molecule:molecule-gradle-plugin:_&#10;version.app.cash.molecule"
            style="text-decoration: underline;">
            gradlePlugin
        </span>
        - 
        <span
            title="CashApp.molecule.runtime&#10;app.cash.molecule:molecule-runtime:_&#10;version.app.cash.molecule"
            style="text-decoration: underline;">
            runtime
        </span>
            
    </td></tr>
            
    <tr><td><b>CashApp.sqlDelight</b></td><td>
        <span
            title="CashApp.sqlDelight.gradlePlugin&#10;com.squareup.sqldelight:gradle-plugin:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            gradlePlugin
        </span>
            
    </td></tr>
            
    <tr><td><b>CashApp.sqlDelight.drivers</b></td><td>
        <span
            title="CashApp.sqlDelight.drivers.android&#10;com.squareup.sqldelight:android-driver:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            android
        </span>
        - 
        <span
            title="CashApp.sqlDelight.drivers.jdbc&#10;com.squareup.sqldelight:jdbc-driver:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            jdbc
        </span>
        - 
        <span
            title="CashApp.sqlDelight.drivers.native&#10;com.squareup.sqldelight:native-driver:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            native
        </span>
        - 
        <span
            title="CashApp.sqlDelight.drivers.jdbcSqlite&#10;com.squareup.sqldelight:sqlite-driver:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            jdbcSqlite
        </span>
        - 
        <span
            title="CashApp.sqlDelight.drivers.sqlJs&#10;com.squareup.sqldelight:sqljs-driver:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            sqlJs
        </span>
            
    </td></tr>
            
    <tr><td><b>CashApp.sqlDelight.extensions</b></td><td>
        <span
            title="CashApp.sqlDelight.extensions.androidPaging&#10;com.squareup.sqldelight:android-paging-extensions:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            androidPaging
        </span>
        - 
        <span
            title="CashApp.sqlDelight.extensions.androidPaging3&#10;com.squareup.sqldelight:android-paging3-extensions:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            androidPaging3
        </span>
        - 
        <span
            title="CashApp.sqlDelight.extensions.coroutines&#10;com.squareup.sqldelight:coroutines-extensions:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            coroutines
        </span>
        - 
        <span
            title="CashApp.sqlDelight.extensions.rxJava2&#10;com.squareup.sqldelight:rxjava2-extensions:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            rxJava2
        </span>
        - 
        <span
            title="CashApp.sqlDelight.extensions.rxJava3&#10;com.squareup.sqldelight:rxjava3-extensions:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            rxJava3
        </span>
            
    </td></tr>
            
</table>
            

## [Chucker.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Chucker.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>Chucker</b></td><td>
        <span
            title="Chucker.libraryNoOp&#10;com.github.chuckerteam.chucker:library-no-op:_&#10;version.chucker"
            style="text-decoration: underline;">
            libraryNoOp
        </span>
        - 
        <span
            title="Chucker.library&#10;com.github.chuckerteam.chucker:library:_&#10;version.chucker"
            style="text-decoration: underline;">
            library
        </span>
            
    </td></tr>
            
</table>
            

## [Firebase.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Firebase.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>Firebase</b></td><td>
        <span
            title="Firebase.analyticsKtx&#10;com.google.firebase:firebase-analytics-ktx:_&#10;version.firebase-analytics-ktx"
            style="text-decoration: underline;">
            analyticsKtx
        </span>
        - 
        <span
            title="Firebase.analytics&#10;com.google.firebase:firebase-analytics:_&#10;version.firebase-analytics"
            style="text-decoration: underline;">
            analytics
        </span>
        - 
        <span
            title="Firebase.appDistributionGradlePlugin&#10;com.google.firebase:firebase-appdistribution-gradle:_&#10;version.firebase-appdistribution-gradle"
            style="text-decoration: underline;">
            appDistributionGradlePlugin
        </span>
        - 
        <span
            title="Firebase.appIndexing&#10;com.google.firebase:firebase-appindexing:_&#10;version.firebase-appindexing"
            style="text-decoration: underline;">
            appIndexing
        </span>
        - 
        <span
            title="Firebase.authenticationKtx&#10;com.google.firebase:firebase-auth-ktx:_&#10;version.firebase-auth-ktx"
            style="text-decoration: underline;">
            authenticationKtx
        </span>
        - 
        <span
            title="Firebase.authentication&#10;com.google.firebase:firebase-auth:_&#10;version.firebase-auth"
            style="text-decoration: underline;">
            authentication
        </span>
        - 
        <span
            title="Firebase.bom&#10;com.google.firebase:firebase-bom:_&#10;version.firebase-bom"
            style="text-decoration: underline;">
            bom
        </span>
        - 
        <span
            title="Firebase.remoteConfigKtx&#10;com.google.firebase:firebase-config-ktx:_&#10;version.firebase-config-ktx"
            style="text-decoration: underline;">
            remoteConfigKtx
        </span>
        - 
        <span
            title="Firebase.remoteConfig&#10;com.google.firebase:firebase-config:_&#10;version.firebase-config"
            style="text-decoration: underline;">
            remoteConfig
        </span>
        - 
        <span
            title="Firebase.crashlyticsGradlePlugin&#10;com.google.firebase:firebase-crashlytics-gradle:_&#10;version.firebase-crashlytics-gradle"
            style="text-decoration: underline;">
            crashlyticsGradlePlugin
        </span>
        - 
        <span
            title="Firebase.crashlyticsKtx&#10;com.google.firebase:firebase-crashlytics-ktx:_&#10;version.firebase-crashlytics-ktx"
            style="text-decoration: underline;">
            crashlyticsKtx
        </span>
        - 
        <span
            title="Firebase.crashlyticsNdk&#10;com.google.firebase:firebase-crashlytics-ndk:_&#10;version.firebase-crashlytics-ndk"
            style="text-decoration: underline;">
            crashlyticsNdk
        </span>
        - 
        <span
            title="Firebase.crashlytics&#10;com.google.firebase:firebase-crashlytics:_&#10;version.firebase-crashlytics"
            style="text-decoration: underline;">
            crashlytics
        </span>
        - 
        <span
            title="Firebase.realtimeDatabaseKtx&#10;com.google.firebase:firebase-database-ktx:_&#10;version.firebase-database-ktx"
            style="text-decoration: underline;">
            realtimeDatabaseKtx
        </span>
        - 
        <span
            title="Firebase.realtimeDatabase&#10;com.google.firebase:firebase-database:_&#10;version.firebase-database"
            style="text-decoration: underline;">
            realtimeDatabase
        </span>
        - 
        <span
            title="Firebase.dynamicLinksKtx&#10;com.google.firebase:firebase-dynamic-links-ktx:_&#10;version.firebase-dynamic-links-ktx"
            style="text-decoration: underline;">
            dynamicLinksKtx
        </span>
        - 
        <span
            title="Firebase.dynamicLinks&#10;com.google.firebase:firebase-dynamic-links:_&#10;version.firebase-dynamic-links"
            style="text-decoration: underline;">
            dynamicLinks
        </span>
        - 
        <span
            title="Firebase.dynamicModuleSupport&#10;com.google.firebase:firebase-dynamic-module-support:_&#10;version.firebase-dynamic-module-support"
            style="text-decoration: underline;">
            dynamicModuleSupport
        </span>
        - 
        <span
            title="Firebase.cloudFirestoreKtx&#10;com.google.firebase:firebase-firestore-ktx:_&#10;version.firebase-firestore-ktx"
            style="text-decoration: underline;">
            cloudFirestoreKtx
        </span>
        - 
        <span
            title="Firebase.cloudFirestore&#10;com.google.firebase:firebase-firestore:_&#10;version.firebase-firestore"
            style="text-decoration: underline;">
            cloudFirestore
        </span>
        - 
        <span
            title="Firebase.cloudFunctionsKtx&#10;com.google.firebase:firebase-functions-ktx:_&#10;version.firebase-functions-ktx"
            style="text-decoration: underline;">
            cloudFunctionsKtx
        </span>
        - 
        <span
            title="Firebase.cloudFunctions&#10;com.google.firebase:firebase-functions:_&#10;version.firebase-functions"
            style="text-decoration: underline;">
            cloudFunctions
        </span>
        - 
        <span
            title="Firebase.inAppMessagingDisplayKtx&#10;com.google.firebase:firebase-inappmessaging-display-ktx:_&#10;version.firebase-inappmessaging-display-ktx"
            style="text-decoration: underline;">
            inAppMessagingDisplayKtx
        </span>
        - 
        <span
            title="Firebase.inAppMessagingDisplay&#10;com.google.firebase:firebase-inappmessaging-display:_&#10;version.firebase-inappmessaging-display"
            style="text-decoration: underline;">
            inAppMessagingDisplay
        </span>
        - 
        <span
            title="Firebase.inAppMessagingKtx&#10;com.google.firebase:firebase-inappmessaging-ktx:_&#10;version.firebase-inappmessaging-ktx"
            style="text-decoration: underline;">
            inAppMessagingKtx
        </span>
        - 
        <span
            title="Firebase.inAppMessaging&#10;com.google.firebase:firebase-inappmessaging:_&#10;version.firebase-inappmessaging"
            style="text-decoration: underline;">
            inAppMessaging
        </span>
        - 
        <span
            title="Firebase.cloudMessagingDirectBoot&#10;com.google.firebase:firebase-messaging-directboot:_&#10;version.firebase-messaging-directboot"
            style="text-decoration: underline;">
            cloudMessagingDirectBoot
        </span>
        - 
        <span
            title="Firebase.cloudMessagingKtx&#10;com.google.firebase:firebase-messaging-ktx:_&#10;version.firebase-messaging-ktx"
            style="text-decoration: underline;">
            cloudMessagingKtx
        </span>
        - 
        <span
            title="Firebase.cloudMessaging&#10;com.google.firebase:firebase-messaging:_&#10;version.firebase-messaging"
            style="text-decoration: underline;">
            cloudMessaging
        </span>
        - 
        <span
            title="Firebase.mlModelDownloaderKtx&#10;com.google.firebase:firebase-ml-modeldownloader-ktx:_&#10;version.firebase-ml-modeldownloader-ktx"
            style="text-decoration: underline;">
            mlModelDownloaderKtx
        </span>
        - 
        <span
            title="Firebase.mlModelDownloader&#10;com.google.firebase:firebase-ml-modeldownloader:_&#10;version.firebase-ml-modeldownloader"
            style="text-decoration: underline;">
            mlModelDownloader
        </span>
        - 
        <span
            title="Firebase.performanceMonitoringKtx&#10;com.google.firebase:firebase-perf-ktx:_&#10;version.firebase-perf-ktx"
            style="text-decoration: underline;">
            performanceMonitoringKtx
        </span>
        - 
        <span
            title="Firebase.performanceMonitoring&#10;com.google.firebase:firebase-perf:_&#10;version.firebase-perf"
            style="text-decoration: underline;">
            performanceMonitoring
        </span>
        - 
        <span
            title="Firebase.cloudStorageKtx&#10;com.google.firebase:firebase-storage-ktx:_&#10;version.firebase-storage-ktx"
            style="text-decoration: underline;">
            cloudStorageKtx
        </span>
        - 
        <span
            title="Firebase.cloudStorage&#10;com.google.firebase:firebase-storage:_&#10;version.firebase-storage"
            style="text-decoration: underline;">
            cloudStorage
        </span>
        - 
        <span
            title="Firebase.performanceMonitoringGradlePlugin&#10;com.google.firebase:perf-plugin:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            performanceMonitoringGradlePlugin
        </span>
            
    </td></tr>
            
</table>
            

## [Google.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Google.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>Google</b></td><td>
        <span
            title="Google.dagger&#10;com.google.dagger:dagger:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            dagger
        </span>
        - 
        <span
            title="Google.playServicesGradlePlugin&#10;com.google.gms:google-services:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            playServicesGradlePlugin
        </span>
        - 
        <span
            title="Google.oboe&#10;com.google.oboe:oboe:_&#10;version.google.oboe"
            style="text-decoration: underline;">
            oboe
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.accompanist</b></td><td>
        <span
            title="Google.accompanist.appCompatTheme&#10;com.google.accompanist:accompanist-appcompat-theme:_&#10;version.google.accompanist"
            style="text-decoration: underline;">
            appCompatTheme
        </span>
        - 
        <span
            title="Google.accompanist.drawablePainter&#10;com.google.accompanist:accompanist-drawablepainter:_&#10;version.google.accompanist"
            style="text-decoration: underline;">
            drawablePainter
        </span>
        - 
        <span
            title="Google.accompanist.flowLayout&#10;com.google.accompanist:accompanist-flowlayout:_&#10;version.google.accompanist"
            style="text-decoration: underline;">
            flowLayout
        </span>
        - 
        <span
            title="Google.accompanist.insets&#10;com.google.accompanist:accompanist-insets:_&#10;version.google.accompanist"
            style="text-decoration: underline;">
            insets
        </span>
        - 
        <span
            title="Google.accompanist.navigationAnimation&#10;com.google.accompanist:accompanist-navigation-animation:_&#10;version.google.accompanist"
            style="text-decoration: underline;">
            navigationAnimation
        </span>
        - 
        <span
            title="Google.accompanist.navigationMaterial&#10;com.google.accompanist:accompanist-navigation-material:_&#10;version.google.accompanist"
            style="text-decoration: underline;">
            navigationMaterial
        </span>
        - 
        <span
            title="Google.accompanist.pager&#10;com.google.accompanist:accompanist-pager:_&#10;version.google.accompanist"
            style="text-decoration: underline;">
            pager
        </span>
        - 
        <span
            title="Google.accompanist.permissions&#10;com.google.accompanist:accompanist-permissions:_&#10;version.google.accompanist"
            style="text-decoration: underline;">
            permissions
        </span>
        - 
        <span
            title="Google.accompanist.placeholder&#10;com.google.accompanist:accompanist-placeholder:_&#10;version.google.accompanist"
            style="text-decoration: underline;">
            placeholder
        </span>
        - 
        <span
            title="Google.accompanist.swipeRefresh&#10;com.google.accompanist:accompanist-swiperefresh:_&#10;version.google.accompanist"
            style="text-decoration: underline;">
            swipeRefresh
        </span>
        - 
        <span
            title="Google.accompanist.systemUiController&#10;com.google.accompanist:accompanist-systemuicontroller:_&#10;version.google.accompanist"
            style="text-decoration: underline;">
            systemUiController
        </span>
        - 
        <span
            title="Google.accompanist.webView&#10;com.google.accompanist:accompanist-webview:_&#10;version.google.accompanist"
            style="text-decoration: underline;">
            webView
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.accompanist.insets</b></td><td>
        <span
            title="Google.accompanist.insets.ui&#10;com.google.accompanist:accompanist-insets-ui:_&#10;version.google.accompanist"
            style="text-decoration: underline;">
            ui
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.accompanist.pager</b></td><td>
        <span
            title="Google.accompanist.pager.indicators&#10;com.google.accompanist:accompanist-pager-indicators:_&#10;version.google.accompanist"
            style="text-decoration: underline;">
            indicators
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.accompanist.placeholder</b></td><td>
        <span
            title="Google.accompanist.placeholder.material&#10;com.google.accompanist:accompanist-placeholder-material:_&#10;version.google.accompanist"
            style="text-decoration: underline;">
            material
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.ambient</b></td><td>
        <span
            title="Google.ambient.crossDevice&#10;com.google.ambient.crossdevice:crossdevice:_&#10;version.google.ambient.crossdevice"
            style="text-decoration: underline;">
            crossDevice
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.android</b></td><td>
        <span
            title="Google.android.flexbox&#10;com.google.android.flexbox:flexbox:_&#10;version.google.android.flexbox"
            style="text-decoration: underline;">
            flexbox
        </span>
        - 
        <span
            title="Google.android.openSourceLicensesPlugin&#10;com.google.android.gms:oss-licenses-plugin:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            openSourceLicensesPlugin
        </span>
        - 
        <span
            title="Google.android.versionMatcherPlugin&#10;com.google.android.gms:strict-version-matcher-plugin:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            versionMatcherPlugin
        </span>
        - 
        <span
            title="Google.android.material&#10;com.google.android.material:material:_&#10;version.google.android.material"
            style="text-decoration: underline;">
            material
        </span>
        - 
        <span
            title="Google.android.supportWearable&#10;com.google.android.support:wearable:_&#10;version.google.android.wearable"
            style="text-decoration: underline;">
            supportWearable
        </span>
        - 
        <span
            title="Google.android.wearable&#10;com.google.android.wearable:wearable:_&#10;version.google.android.wearable"
            style="text-decoration: underline;">
            wearable
        </span>
        - 
        <span
            title="Google.android.browserHelper&#10;com.google.androidbrowserhelper:androidbrowserhelper:_&#10;version.google.androidbrowserhelper"
            style="text-decoration: underline;">
            browserHelper
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.android.fhir</b></td><td>
        <span
            title="Google.android.fhir.dataCapture&#10;com.google.android.fhir:data-capture:_&#10;version.google.android.fhir.data-capture"
            style="text-decoration: underline;">
            dataCapture
        </span>
        - 
        <span
            title="Google.android.fhir.engine&#10;com.google.android.fhir:engine:_&#10;version.google.android.fhir.engine"
            style="text-decoration: underline;">
            engine
        </span>
        - 
        <span
            title="Google.android.fhir.workflow&#10;com.google.android.fhir:workflow:_&#10;version.google.android.fhir.workflow"
            style="text-decoration: underline;">
            workflow
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.android.maps</b></td><td>
        <span
            title="Google.android.maps.places&#10;com.google.android.libraries.places:places:_&#10;version.google.android.places"
            style="text-decoration: underline;">
            places
        </span>
        - 
        <span
            title="Google.android.maps.utils&#10;com.google.maps.android:android-maps-utils:_&#10;version.google.android.maps-utils"
            style="text-decoration: underline;">
            utils
        </span>
        - 
        <span
            title="Google.android.maps.compose&#10;com.google.maps.android:maps-compose:_&#10;version.google.android.maps-compose"
            style="text-decoration: underline;">
            compose
        </span>
        - 
        <span
            title="Google.android.maps.ktx&#10;com.google.maps.android:maps-ktx:_&#10;version.google.android.maps-ktx"
            style="text-decoration: underline;">
            ktx
        </span>
        - 
        <span
            title="Google.android.maps.rx&#10;com.google.maps.android:maps-rx:_&#10;version.google.android.maps-rx"
            style="text-decoration: underline;">
            rx
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.android.maps.places</b></td><td>
        <span
            title="Google.android.maps.places.ktx&#10;com.google.maps.android:maps-ktx:_&#10;version.google.android.maps-ktx"
            style="text-decoration: underline;">
            ktx
        </span>
        - 
        <span
            title="Google.android.maps.places.rx&#10;com.google.maps.android:maps-rx:_&#10;version.google.android.maps-rx"
            style="text-decoration: underline;">
            rx
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.android.maps.utils</b></td><td>
        <span
            title="Google.android.maps.utils.ktx&#10;com.google.maps.android:maps-utils-ktx:_&#10;version.google.android.maps-ktx"
            style="text-decoration: underline;">
            ktx
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.android.material</b></td><td>
        <span
            title="Google.android.material.composeThemeAdapter3&#10;com.google.android.material:compose-theme-adapter-3:_&#10;version.google.android.material.compose-theme-adapter-3"
            style="text-decoration: underline;">
            composeThemeAdapter3
        </span>
        - 
        <span
            title="Google.android.material.composeThemeAdapter&#10;com.google.android.material:compose-theme-adapter:_&#10;version.google.android.material.compose-theme-adapter"
            style="text-decoration: underline;">
            composeThemeAdapter
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.android.play</b></td><td>
        <span
            title="Google.android.play.coreKtx&#10;com.google.android.play:core-ktx:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            coreKtx
        </span>
        - 
        <span
            title="Google.android.play.core&#10;com.google.android.play:core:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            core
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.android.playServices</b></td><td>
        <span
            title="Google.android.playServices.analytics&#10;com.google.android.gms:play-services-analytics:_&#10;version.google.android.play-services-analytics"
            style="text-decoration: underline;">
            analytics
        </span>
        - 
        <span
            title="Google.android.playServices.appset&#10;com.google.android.gms:play-services-appset:_&#10;version.google.android.play-services-appset"
            style="text-decoration: underline;">
            appset
        </span>
        - 
        <span
            title="Google.android.playServices.auth&#10;com.google.android.gms:play-services-auth:_&#10;version.google.android.play-services-auth"
            style="text-decoration: underline;">
            auth
        </span>
        - 
        <span
            title="Google.android.playServices.awareness&#10;com.google.android.gms:play-services-awareness:_&#10;version.google.android.play-services-awareness"
            style="text-decoration: underline;">
            awareness
        </span>
        - 
        <span
            title="Google.android.playServices.base&#10;com.google.android.gms:play-services-base:_&#10;version.google.android.play-services-base"
            style="text-decoration: underline;">
            base
        </span>
        - 
        <span
            title="Google.android.playServices.basement&#10;com.google.android.gms:play-services-basement:_&#10;version.google.android.play-services-basement"
            style="text-decoration: underline;">
            basement
        </span>
        - 
        <span
            title="Google.android.playServices.cast&#10;com.google.android.gms:play-services-cast:_&#10;version.google.android.play-services-cast"
            style="text-decoration: underline;">
            cast
        </span>
        - 
        <span
            title="Google.android.playServices.cronet&#10;com.google.android.gms:play-services-cronet:_&#10;version.google.android.play-services-cronet"
            style="text-decoration: underline;">
            cronet
        </span>
        - 
        <span
            title="Google.android.playServices.drive&#10;com.google.android.gms:play-services-drive:_&#10;version.google.android.play-services-drive"
            style="text-decoration: underline;">
            drive
        </span>
        - 
        <span
            title="Google.android.playServices.fido&#10;com.google.android.gms:play-services-fido:_&#10;version.google.android.play-services-fido"
            style="text-decoration: underline;">
            fido
        </span>
        - 
        <span
            title="Google.android.playServices.fitness&#10;com.google.android.gms:play-services-fitness:_&#10;version.google.android.play-services-fitness"
            style="text-decoration: underline;">
            fitness
        </span>
        - 
        <span
            title="Google.android.playServices.games&#10;com.google.android.gms:play-services-games:_&#10;version.google.android.play-services-games"
            style="text-decoration: underline;">
            games
        </span>
        - 
        <span
            title="Google.android.playServices.gcm&#10;com.google.android.gms:play-services-gcm:_&#10;version.google.android.play-services-gcm"
            style="text-decoration: underline;">
            gcm
        </span>
        - 
        <span
            title="Google.android.playServices.identity&#10;com.google.android.gms:play-services-identity:_&#10;version.google.android.play-services-identity"
            style="text-decoration: underline;">
            identity
        </span>
        - 
        <span
            title="Google.android.playServices.instantApps&#10;com.google.android.gms:play-services-instantapps:_&#10;version.google.android.play-services-instantapps"
            style="text-decoration: underline;">
            instantApps
        </span>
        - 
        <span
            title="Google.android.playServices.location&#10;com.google.android.gms:play-services-location:_&#10;version.google.android.play-services-location"
            style="text-decoration: underline;">
            location
        </span>
        - 
        <span
            title="Google.android.playServices.maps&#10;com.google.android.gms:play-services-maps:_&#10;version.google.android.play-services-maps"
            style="text-decoration: underline;">
            maps
        </span>
        - 
        <span
            title="Google.android.playServices.nearby&#10;com.google.android.gms:play-services-nearby:_&#10;version.google.android.play-services-nearby"
            style="text-decoration: underline;">
            nearby
        </span>
        - 
        <span
            title="Google.android.playServices.openSourceLicenses&#10;com.google.android.gms:play-services-oss-licenses:_&#10;version.google.android.play-services-oss-licenses"
            style="text-decoration: underline;">
            openSourceLicenses
        </span>
        - 
        <span
            title="Google.android.playServices.panorama&#10;com.google.android.gms:play-services-panorama:_&#10;version.google.android.play-services-panorama"
            style="text-decoration: underline;">
            panorama
        </span>
        - 
        <span
            title="Google.android.playServices.passwordComplexity&#10;com.google.android.gms:play-services-password-complexity:_&#10;version.google.android.play-services-password-complexity"
            style="text-decoration: underline;">
            passwordComplexity
        </span>
        - 
        <span
            title="Google.android.playServices.pay&#10;com.google.android.gms:play-services-pay:_&#10;version.google.android.play-services-pay"
            style="text-decoration: underline;">
            pay
        </span>
        - 
        <span
            title="Google.android.playServices.recaptcha&#10;com.google.android.gms:play-services-recaptcha:_&#10;version.google.android.play-services-recaptcha"
            style="text-decoration: underline;">
            recaptcha
        </span>
        - 
        <span
            title="Google.android.playServices.safetynet&#10;com.google.android.gms:play-services-safetynet:_&#10;version.google.android.play-services-safetynet"
            style="text-decoration: underline;">
            safetynet
        </span>
        - 
        <span
            title="Google.android.playServices.tagmanager&#10;com.google.android.gms:play-services-tagmanager:_&#10;version.google.android.play-services-tagmanager"
            style="text-decoration: underline;">
            tagmanager
        </span>
        - 
        <span
            title="Google.android.playServices.tasks&#10;com.google.android.gms:play-services-tasks:_&#10;version.google.android.play-services-tasks"
            style="text-decoration: underline;">
            tasks
        </span>
        - 
        <span
            title="Google.android.playServices.vision&#10;com.google.android.gms:play-services-vision:_&#10;version.google.android.play-services-vision"
            style="text-decoration: underline;">
            vision
        </span>
        - 
        <span
            title="Google.android.playServices.wallet&#10;com.google.android.gms:play-services-wallet:_&#10;version.google.android.play-services-wallet"
            style="text-decoration: underline;">
            wallet
        </span>
        - 
        <span
            title="Google.android.playServices.wearOS&#10;com.google.android.gms:play-services-wearable:_&#10;version.google.android.play-services-wearable"
            style="text-decoration: underline;">
            wearOS
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.android.playServices.auth</b></td><td>
        <span
            title="Google.android.playServices.auth.apiPhone&#10;com.google.android.gms:play-services-auth-api-phone:_&#10;version.google.android.play-services-auth-api-phone"
            style="text-decoration: underline;">
            apiPhone
        </span>
        - 
        <span
            title="Google.android.playServices.auth.blockstore&#10;com.google.android.gms:play-services-auth-blockstore:_&#10;version.google.android.play-services-auth-blockstore"
            style="text-decoration: underline;">
            blockstore
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.android.playServices.cast</b></td><td>
        <span
            title="Google.android.playServices.cast.framework&#10;com.google.android.gms:play-services-cast-framework:_&#10;version.google.android.play-services-cast-framework"
            style="text-decoration: underline;">
            framework
        </span>
        - 
        <span
            title="Google.android.playServices.cast.tv&#10;com.google.android.gms:play-services-cast-tv:_&#10;version.google.android.play-services-cast-tv"
            style="text-decoration: underline;">
            tv
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.android.playServices.mlKit.naturalLanguage</b></td><td>
        <span
            title="Google.android.playServices.mlKit.naturalLanguage.languageIdentification&#10;com.google.android.gms:play-services-mlkit-language-id:_&#10;version.google.android.play-services-mlkit-language-id"
            style="text-decoration: underline;">
            languageIdentification
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.android.playServices.mlKit.vision</b></td><td>
        <span
            title="Google.android.playServices.mlKit.vision.barcodeScanning&#10;com.google.android.gms:play-services-mlkit-barcode-scanning:_&#10;version.google.android.play-services-mlkit-barcode-scanning"
            style="text-decoration: underline;">
            barcodeScanning
        </span>
        - 
        <span
            title="Google.android.playServices.mlKit.vision.faceDetection&#10;com.google.android.gms:play-services-mlkit-face-detection:_&#10;version.google.android.play-services-mlkit-face-detection"
            style="text-decoration: underline;">
            faceDetection
        </span>
        - 
        <span
            title="Google.android.playServices.mlKit.vision.imageLabeling&#10;com.google.android.gms:play-services-mlkit-image-labeling:_&#10;version.google.android.play-services-mlkit-image-labeling"
            style="text-decoration: underline;">
            imageLabeling
        </span>
        - 
        <span
            title="Google.android.playServices.mlKit.vision.textRecognition&#10;com.google.android.gms:play-services-mlkit-text-recognition:_&#10;version.google.android.play-services-mlkit-text-recognition"
            style="text-decoration: underline;">
            textRecognition
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.android.playServices.mlKit.vision.imageLabeling</b></td><td>
        <span
            title="Google.android.playServices.mlKit.vision.imageLabeling.custom&#10;com.google.android.gms:play-services-mlkit-image-labeling-custom:_&#10;version.google.android.play-services-mlkit-image-labeling-custom"
            style="text-decoration: underline;">
            custom
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.ar</b></td><td>
        <span
            title="Google.ar.core&#10;com.google.ar:core:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            core
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.ar.sceneform</b></td><td>
        <span
            title="Google.ar.sceneform.animation&#10;com.google.ar.sceneform:animation:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            animation
        </span>
        - 
        <span
            title="Google.ar.sceneform.assets&#10;com.google.ar.sceneform:assets:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            assets
        </span>
        - 
        <span
            title="Google.ar.sceneform.core&#10;com.google.ar.sceneform:core:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="Google.ar.sceneform.filamentAndroid&#10;com.google.ar.sceneform:filament-android:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            filamentAndroid
        </span>
        - 
        <span
            title="Google.ar.sceneform.plugin&#10;com.google.ar.sceneform:plugin:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            plugin
        </span>
        - 
        <span
            title="Google.ar.sceneform.rendering&#10;com.google.ar.sceneform:rendering:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            rendering
        </span>
        - 
        <span
            title="Google.ar.sceneform.sceneformBase&#10;com.google.ar.sceneform:sceneform-base:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            sceneformBase
        </span>
        - 
        <span
            title="Google.ar.sceneform.ux&#10;com.google.ar.sceneform.ux:sceneform-ux:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            ux
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.dagger</b></td><td>
        <span
            title="Google.dagger.android&#10;com.google.dagger:dagger-android:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            android
        </span>
        - 
        <span
            title="Google.dagger.compiler&#10;com.google.dagger:dagger-compiler:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            compiler
        </span>
        - 
        <span
            title="Google.dagger.gwt&#10;com.google.dagger:dagger-gwt:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            gwt
        </span>
        - 
        <span
            title="Google.dagger.producers&#10;com.google.dagger:dagger-producers:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            producers
        </span>
        - 
        <span
            title="Google.dagger.spi&#10;com.google.dagger:dagger-spi:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            spi
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.dagger.android</b></td><td>
        <span
            title="Google.dagger.android.processor&#10;com.google.dagger:dagger-android-processor:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            processor
        </span>
        - 
        <span
            title="Google.dagger.android.support&#10;com.google.dagger:dagger-android-support:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            support
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.dagger.grpc</b></td><td>
        <span
            title="Google.dagger.grpc.server&#10;com.google.dagger:dagger-grpc-server:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            server
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.dagger.grpc.server</b></td><td>
        <span
            title="Google.dagger.grpc.server.annotations&#10;com.google.dagger:dagger-grpc-server-annotations:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            annotations
        </span>
        - 
        <span
            title="Google.dagger.grpc.server.processor&#10;com.google.dagger:dagger-grpc-server-processor:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            processor
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.dagger.hilt</b></td><td>
        <span
            title="Google.dagger.hilt.android&#10;com.google.dagger:hilt-android:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            android
        </span>
        - 
        <span
            title="Google.dagger.hilt.compiler&#10;com.google.dagger:hilt-compiler:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            compiler
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.dagger.hilt.android</b></td><td>
        <span
            title="Google.dagger.hilt.android.compiler&#10;com.google.dagger:hilt-android-compiler:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            compiler
        </span>
        - 
        <span
            title="Google.dagger.hilt.android.gradlePlugin&#10;com.google.dagger:hilt-android-gradle-plugin:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            gradlePlugin
        </span>
        - 
        <span
            title="Google.dagger.hilt.android.testing&#10;com.google.dagger:hilt-android-testing:_&#10;version.google.dagger"
            style="text-decoration: underline;">
            testing
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.firebase</b></td><td>
        <span
            title="Google.firebase.analyticsKtx&#10;com.google.firebase:firebase-analytics-ktx:_&#10;version.firebase-analytics-ktx"
            style="text-decoration: underline;">
            analyticsKtx
        </span>
        - 
        <span
            title="Google.firebase.analytics&#10;com.google.firebase:firebase-analytics:_&#10;version.firebase-analytics"
            style="text-decoration: underline;">
            analytics
        </span>
        - 
        <span
            title="Google.firebase.appDistributionGradlePlugin&#10;com.google.firebase:firebase-appdistribution-gradle:_&#10;version.firebase-appdistribution-gradle"
            style="text-decoration: underline;">
            appDistributionGradlePlugin
        </span>
        - 
        <span
            title="Google.firebase.appIndexing&#10;com.google.firebase:firebase-appindexing:_&#10;version.firebase-appindexing"
            style="text-decoration: underline;">
            appIndexing
        </span>
        - 
        <span
            title="Google.firebase.authenticationKtx&#10;com.google.firebase:firebase-auth-ktx:_&#10;version.firebase-auth-ktx"
            style="text-decoration: underline;">
            authenticationKtx
        </span>
        - 
        <span
            title="Google.firebase.authentication&#10;com.google.firebase:firebase-auth:_&#10;version.firebase-auth"
            style="text-decoration: underline;">
            authentication
        </span>
        - 
        <span
            title="Google.firebase.bom&#10;com.google.firebase:firebase-bom:_&#10;version.firebase-bom"
            style="text-decoration: underline;">
            bom
        </span>
        - 
        <span
            title="Google.firebase.remoteConfigKtx&#10;com.google.firebase:firebase-config-ktx:_&#10;version.firebase-config-ktx"
            style="text-decoration: underline;">
            remoteConfigKtx
        </span>
        - 
        <span
            title="Google.firebase.remoteConfig&#10;com.google.firebase:firebase-config:_&#10;version.firebase-config"
            style="text-decoration: underline;">
            remoteConfig
        </span>
        - 
        <span
            title="Google.firebase.crashlyticsGradlePlugin&#10;com.google.firebase:firebase-crashlytics-gradle:_&#10;version.firebase-crashlytics-gradle"
            style="text-decoration: underline;">
            crashlyticsGradlePlugin
        </span>
        - 
        <span
            title="Google.firebase.crashlyticsKtx&#10;com.google.firebase:firebase-crashlytics-ktx:_&#10;version.firebase-crashlytics-ktx"
            style="text-decoration: underline;">
            crashlyticsKtx
        </span>
        - 
        <span
            title="Google.firebase.crashlyticsNdk&#10;com.google.firebase:firebase-crashlytics-ndk:_&#10;version.firebase-crashlytics-ndk"
            style="text-decoration: underline;">
            crashlyticsNdk
        </span>
        - 
        <span
            title="Google.firebase.crashlytics&#10;com.google.firebase:firebase-crashlytics:_&#10;version.firebase-crashlytics"
            style="text-decoration: underline;">
            crashlytics
        </span>
        - 
        <span
            title="Google.firebase.realtimeDatabaseKtx&#10;com.google.firebase:firebase-database-ktx:_&#10;version.firebase-database-ktx"
            style="text-decoration: underline;">
            realtimeDatabaseKtx
        </span>
        - 
        <span
            title="Google.firebase.realtimeDatabase&#10;com.google.firebase:firebase-database:_&#10;version.firebase-database"
            style="text-decoration: underline;">
            realtimeDatabase
        </span>
        - 
        <span
            title="Google.firebase.dynamicLinksKtx&#10;com.google.firebase:firebase-dynamic-links-ktx:_&#10;version.firebase-dynamic-links-ktx"
            style="text-decoration: underline;">
            dynamicLinksKtx
        </span>
        - 
        <span
            title="Google.firebase.dynamicLinks&#10;com.google.firebase:firebase-dynamic-links:_&#10;version.firebase-dynamic-links"
            style="text-decoration: underline;">
            dynamicLinks
        </span>
        - 
        <span
            title="Google.firebase.dynamicModuleSupport&#10;com.google.firebase:firebase-dynamic-module-support:_&#10;version.firebase-dynamic-module-support"
            style="text-decoration: underline;">
            dynamicModuleSupport
        </span>
        - 
        <span
            title="Google.firebase.cloudFirestoreKtx&#10;com.google.firebase:firebase-firestore-ktx:_&#10;version.firebase-firestore-ktx"
            style="text-decoration: underline;">
            cloudFirestoreKtx
        </span>
        - 
        <span
            title="Google.firebase.cloudFirestore&#10;com.google.firebase:firebase-firestore:_&#10;version.firebase-firestore"
            style="text-decoration: underline;">
            cloudFirestore
        </span>
        - 
        <span
            title="Google.firebase.cloudFunctionsKtx&#10;com.google.firebase:firebase-functions-ktx:_&#10;version.firebase-functions-ktx"
            style="text-decoration: underline;">
            cloudFunctionsKtx
        </span>
        - 
        <span
            title="Google.firebase.cloudFunctions&#10;com.google.firebase:firebase-functions:_&#10;version.firebase-functions"
            style="text-decoration: underline;">
            cloudFunctions
        </span>
        - 
        <span
            title="Google.firebase.inAppMessagingDisplayKtx&#10;com.google.firebase:firebase-inappmessaging-display-ktx:_&#10;version.firebase-inappmessaging-display-ktx"
            style="text-decoration: underline;">
            inAppMessagingDisplayKtx
        </span>
        - 
        <span
            title="Google.firebase.inAppMessagingDisplay&#10;com.google.firebase:firebase-inappmessaging-display:_&#10;version.firebase-inappmessaging-display"
            style="text-decoration: underline;">
            inAppMessagingDisplay
        </span>
        - 
        <span
            title="Google.firebase.inAppMessagingKtx&#10;com.google.firebase:firebase-inappmessaging-ktx:_&#10;version.firebase-inappmessaging-ktx"
            style="text-decoration: underline;">
            inAppMessagingKtx
        </span>
        - 
        <span
            title="Google.firebase.inAppMessaging&#10;com.google.firebase:firebase-inappmessaging:_&#10;version.firebase-inappmessaging"
            style="text-decoration: underline;">
            inAppMessaging
        </span>
        - 
        <span
            title="Google.firebase.cloudMessagingDirectBoot&#10;com.google.firebase:firebase-messaging-directboot:_&#10;version.firebase-messaging-directboot"
            style="text-decoration: underline;">
            cloudMessagingDirectBoot
        </span>
        - 
        <span
            title="Google.firebase.cloudMessagingKtx&#10;com.google.firebase:firebase-messaging-ktx:_&#10;version.firebase-messaging-ktx"
            style="text-decoration: underline;">
            cloudMessagingKtx
        </span>
        - 
        <span
            title="Google.firebase.cloudMessaging&#10;com.google.firebase:firebase-messaging:_&#10;version.firebase-messaging"
            style="text-decoration: underline;">
            cloudMessaging
        </span>
        - 
        <span
            title="Google.firebase.mlModelDownloaderKtx&#10;com.google.firebase:firebase-ml-modeldownloader-ktx:_&#10;version.firebase-ml-modeldownloader-ktx"
            style="text-decoration: underline;">
            mlModelDownloaderKtx
        </span>
        - 
        <span
            title="Google.firebase.mlModelDownloader&#10;com.google.firebase:firebase-ml-modeldownloader:_&#10;version.firebase-ml-modeldownloader"
            style="text-decoration: underline;">
            mlModelDownloader
        </span>
        - 
        <span
            title="Google.firebase.performanceMonitoringKtx&#10;com.google.firebase:firebase-perf-ktx:_&#10;version.firebase-perf-ktx"
            style="text-decoration: underline;">
            performanceMonitoringKtx
        </span>
        - 
        <span
            title="Google.firebase.performanceMonitoring&#10;com.google.firebase:firebase-perf:_&#10;version.firebase-perf"
            style="text-decoration: underline;">
            performanceMonitoring
        </span>
        - 
        <span
            title="Google.firebase.cloudStorageKtx&#10;com.google.firebase:firebase-storage-ktx:_&#10;version.firebase-storage-ktx"
            style="text-decoration: underline;">
            cloudStorageKtx
        </span>
        - 
        <span
            title="Google.firebase.cloudStorage&#10;com.google.firebase:firebase-storage:_&#10;version.firebase-storage"
            style="text-decoration: underline;">
            cloudStorage
        </span>
        - 
        <span
            title="Google.firebase.performanceMonitoringGradlePlugin&#10;com.google.firebase:perf-plugin:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            performanceMonitoringGradlePlugin
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.horologist</b></td><td>
        <span
            title="Google.horologist.audio&#10;com.google.android.horologist:horologist-audio:_&#10;version.google.horologist"
            style="text-decoration: underline;">
            audio
        </span>
        - 
        <span
            title="Google.horologist.composables&#10;com.google.android.horologist:horologist-composables:_&#10;version.google.horologist"
            style="text-decoration: underline;">
            composables
        </span>
        - 
        <span
            title="Google.horologist.dataLayer&#10;com.google.android.horologist:horologist-datalayer:_&#10;version.google.horologist"
            style="text-decoration: underline;">
            dataLayer
        </span>
        - 
        <span
            title="Google.horologist.media&#10;com.google.android.horologist:horologist-media:_&#10;version.google.horologist"
            style="text-decoration: underline;">
            media
        </span>
        - 
        <span
            title="Google.horologist.networkAwareness&#10;com.google.android.horologist:horologist-network-awareness:_&#10;version.google.horologist"
            style="text-decoration: underline;">
            networkAwareness
        </span>
        - 
        <span
            title="Google.horologist.tiles&#10;com.google.android.horologist:horologist-tiles:_&#10;version.google.horologist"
            style="text-decoration: underline;">
            tiles
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.horologist.audio</b></td><td>
        <span
            title="Google.horologist.audio.ui&#10;com.google.android.horologist:horologist-audio-ui:_&#10;version.google.horologist"
            style="text-decoration: underline;">
            ui
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.horologist.compose</b></td><td>
        <span
            title="Google.horologist.compose.layout&#10;com.google.android.horologist:horologist-compose-layout:_&#10;version.google.horologist"
            style="text-decoration: underline;">
            layout
        </span>
        - 
        <span
            title="Google.horologist.compose.tools&#10;com.google.android.horologist:horologist-compose-tools:_&#10;version.google.horologist"
            style="text-decoration: underline;">
            tools
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.horologist.media</b></td><td>
        <span
            title="Google.horologist.media.data&#10;com.google.android.horologist:horologist-media-data:_&#10;version.google.horologist"
            style="text-decoration: underline;">
            data
        </span>
        - 
        <span
            title="Google.horologist.media.ui&#10;com.google.android.horologist:horologist-media-ui:_&#10;version.google.horologist"
            style="text-decoration: underline;">
            ui
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.horologist.media3</b></td><td>
        <span
            title="Google.horologist.media3.backend&#10;com.google.android.horologist:horologist-media3-backend:_&#10;version.google.horologist"
            style="text-decoration: underline;">
            backend
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.mlKit</b></td><td>
        <span
            title="Google.mlKit.playStoreDynamicFeatureSupport&#10;com.google.mlkit:playstore-dynamic-feature-support:_&#10;version.google.mlkit.playstore-dynamic-feature-support"
            style="text-decoration: underline;">
            playStoreDynamicFeatureSupport
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.mlKit.naturalLanguage</b></td><td>
        <span
            title="Google.mlKit.naturalLanguage.entityExtraction&#10;com.google.mlkit:entity-extraction:_&#10;version.google.mlkit.entity-extraction"
            style="text-decoration: underline;">
            entityExtraction
        </span>
        - 
        <span
            title="Google.mlKit.naturalLanguage.languageIdentification&#10;com.google.mlkit:language-id:_&#10;version.google.mlkit.language-id"
            style="text-decoration: underline;">
            languageIdentification
        </span>
        - 
        <span
            title="Google.mlKit.naturalLanguage.smartReply&#10;com.google.mlkit:smart-reply:_&#10;version.google.mlkit.smart-reply"
            style="text-decoration: underline;">
            smartReply
        </span>
        - 
        <span
            title="Google.mlKit.naturalLanguage.translate&#10;com.google.mlkit:translate:_&#10;version.google.mlkit.translate"
            style="text-decoration: underline;">
            translate
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.mlKit.vision</b></td><td>
        <span
            title="Google.mlKit.vision.barcodeScanning&#10;com.google.mlkit:barcode-scanning:_&#10;version.google.mlkit.barcode-scanning"
            style="text-decoration: underline;">
            barcodeScanning
        </span>
        - 
        <span
            title="Google.mlKit.vision.digitalInkRecognition&#10;com.google.mlkit:digital-ink-recognition:_&#10;version.google.mlkit.digital-ink-recognition"
            style="text-decoration: underline;">
            digitalInkRecognition
        </span>
        - 
        <span
            title="Google.mlKit.vision.faceDetection&#10;com.google.mlkit:face-detection:_&#10;version.google.mlkit.face-detection"
            style="text-decoration: underline;">
            faceDetection
        </span>
        - 
        <span
            title="Google.mlKit.vision.imageLabeling&#10;com.google.mlkit:image-labeling:_&#10;version.google.mlkit.image-labeling"
            style="text-decoration: underline;">
            imageLabeling
        </span>
        - 
        <span
            title="Google.mlKit.vision.linkFirebase&#10;com.google.mlkit:linkfirebase:_&#10;version.google.mlkit.linkfirebase"
            style="text-decoration: underline;">
            linkFirebase
        </span>
        - 
        <span
            title="Google.mlKit.vision.objectDetection&#10;com.google.mlkit:object-detection:_&#10;version.google.mlkit.object-detection"
            style="text-decoration: underline;">
            objectDetection
        </span>
        - 
        <span
            title="Google.mlKit.vision.poseDetection&#10;com.google.mlkit:pose-detection:_&#10;version.google.mlkit.pose-detection"
            style="text-decoration: underline;">
            poseDetection
        </span>
        - 
        <span
            title="Google.mlKit.vision.selfieSegmentation&#10;com.google.mlkit:segmentation-selfie:_&#10;version.google.mlkit.segmentation-selfie"
            style="text-decoration: underline;">
            selfieSegmentation
        </span>
        - 
        <span
            title="Google.mlKit.vision.textRecognition&#10;com.google.mlkit:text-recognition:_&#10;version.google.mlkit.text-recognition"
            style="text-decoration: underline;">
            textRecognition
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.mlKit.vision.imageLabeling</b></td><td>
        <span
            title="Google.mlKit.vision.imageLabeling.custom&#10;com.google.mlkit:image-labeling-custom:_&#10;version.google.mlkit.image-labeling-custom"
            style="text-decoration: underline;">
            custom
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.mlKit.vision.objectDetection</b></td><td>
        <span
            title="Google.mlKit.vision.objectDetection.custom&#10;com.google.mlkit:object-detection-custom:_&#10;version.google.mlkit.object-detection-custom"
            style="text-decoration: underline;">
            custom
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.mlKit.vision.poseDetection</b></td><td>
        <span
            title="Google.mlKit.vision.poseDetection.accurate&#10;com.google.mlkit:pose-detection-accurate:_&#10;version.google.mlkit.pose-detection-accurate"
            style="text-decoration: underline;">
            accurate
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.mlKit.vision.textRecognition</b></td><td>
        <span
            title="Google.mlKit.vision.textRecognition.chinese&#10;com.google.mlkit:text-recognition-chinese:_&#10;version.google.mlkit.text-recognition-chinese"
            style="text-decoration: underline;">
            chinese
        </span>
        - 
        <span
            title="Google.mlKit.vision.textRecognition.devanagari&#10;com.google.mlkit:text-recognition-devanagari:_&#10;version.google.mlkit.text-recognition-devanagari"
            style="text-decoration: underline;">
            devanagari
        </span>
        - 
        <span
            title="Google.mlKit.vision.textRecognition.japanese&#10;com.google.mlkit:text-recognition-japanese:_&#10;version.google.mlkit.text-recognition-japanese"
            style="text-decoration: underline;">
            japanese
        </span>
        - 
        <span
            title="Google.mlKit.vision.textRecognition.korean&#10;com.google.mlkit:text-recognition-korean:_&#10;version.google.mlkit.text-recognition-korean"
            style="text-decoration: underline;">
            korean
        </span>
            
    </td></tr>
            
    <tr><td><b>Google.modernStorage</b></td><td>
        <span
            title="Google.modernStorage.bom&#10;com.google.modernstorage:modernstorage-bom:_&#10;version.google.modernstorage"
            style="text-decoration: underline;">
            bom
        </span>
        - 
        <span
            title="Google.modernStorage.permissions&#10;com.google.modernstorage:modernstorage-permissions:_&#10;version.google.modernstorage"
            style="text-decoration: underline;">
            permissions
        </span>
        - 
        <span
            title="Google.modernStorage.photoPicker&#10;com.google.modernstorage:modernstorage-photopicker:_&#10;version.google.modernstorage"
            style="text-decoration: underline;">
            photoPicker
        </span>
        - 
        <span
            title="Google.modernStorage.storage&#10;com.google.modernstorage:modernstorage-storage:_&#10;version.google.modernstorage"
            style="text-decoration: underline;">
            storage
        </span>
            
    </td></tr>
            
</table>
            

## [Http4k.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Http4k.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>Http4k</b></td><td>
        <span
            title="Http4k.aws&#10;org.http4k:http4k-aws:_&#10;version.http4k"
            style="text-decoration: underline;">
            aws
        </span>
        - 
        <span
            title="Http4k.bom&#10;org.http4k:http4k-bom:_&#10;version.http4k"
            style="text-decoration: underline;">
            bom
        </span>
        - 
        <span
            title="Http4k.cloudnative&#10;org.http4k:http4k-cloudnative:_&#10;version.http4k"
            style="text-decoration: underline;">
            cloudnative
        </span>
        - 
        <span
            title="Http4k.contract&#10;org.http4k:http4k-contract:_&#10;version.http4k"
            style="text-decoration: underline;">
            contract
        </span>
        - 
        <span
            title="Http4k.core&#10;org.http4k:http4k-core:_&#10;version.http4k"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="Http4k.graphql&#10;org.http4k:http4k-graphql:_&#10;version.http4k"
            style="text-decoration: underline;">
            graphql
        </span>
        - 
        <span
            title="Http4k.incubator&#10;org.http4k:http4k-incubator:_&#10;version.http4k"
            style="text-decoration: underline;">
            incubator
        </span>
        - 
        <span
            title="Http4k.jsonrpc&#10;org.http4k:http4k-jsonrpc:_&#10;version.http4k"
            style="text-decoration: underline;">
            jsonrpc
        </span>
        - 
        <span
            title="Http4k.metricsMicrometer&#10;org.http4k:http4k-metrics-micrometer:_&#10;version.http4k"
            style="text-decoration: underline;">
            metricsMicrometer
        </span>
        - 
        <span
            title="Http4k.multipart&#10;org.http4k:http4k-multipart:_&#10;version.http4k"
            style="text-decoration: underline;">
            multipart
        </span>
        - 
        <span
            title="Http4k.opentelemetry&#10;org.http4k:http4k-opentelemetry:_&#10;version.http4k"
            style="text-decoration: underline;">
            opentelemetry
        </span>
        - 
        <span
            title="Http4k.realtimeCore&#10;org.http4k:http4k-realtime-core:_&#10;version.http4k"
            style="text-decoration: underline;">
            realtimeCore
        </span>
        - 
        <span
            title="Http4k.resilience4j&#10;org.http4k:http4k-resilience4j:_&#10;version.http4k"
            style="text-decoration: underline;">
            resilience4j
        </span>
        - 
        <span
            title="Http4k.securityOauth&#10;org.http4k:http4k-security-oauth:_&#10;version.http4k"
            style="text-decoration: underline;">
            securityOauth
        </span>
            
    </td></tr>
            
    <tr><td><b>Http4k.client</b></td><td>
        <span
            title="Http4k.client.apacheAsync&#10;org.http4k:http4k-client-apache-async:_&#10;version.http4k"
            style="text-decoration: underline;">
            apacheAsync
        </span>
        - 
        <span
            title="Http4k.client.apache4Async&#10;org.http4k:http4k-client-apache4-async:_&#10;version.http4k"
            style="text-decoration: underline;">
            apache4Async
        </span>
        - 
        <span
            title="Http4k.client.apache4&#10;org.http4k:http4k-client-apache4:_&#10;version.http4k"
            style="text-decoration: underline;">
            apache4
        </span>
        - 
        <span
            title="Http4k.client.apache&#10;org.http4k:http4k-client-apache:_&#10;version.http4k"
            style="text-decoration: underline;">
            apache
        </span>
        - 
        <span
            title="Http4k.client.jetty&#10;org.http4k:http4k-client-jetty:_&#10;version.http4k"
            style="text-decoration: underline;">
            jetty
        </span>
        - 
        <span
            title="Http4k.client.okhttp&#10;org.http4k:http4k-client-okhttp:_&#10;version.http4k"
            style="text-decoration: underline;">
            okhttp
        </span>
        - 
        <span
            title="Http4k.client.websocket&#10;org.http4k:http4k-client-websocket:_&#10;version.http4k"
            style="text-decoration: underline;">
            websocket
        </span>
            
    </td></tr>
            
    <tr><td><b>Http4k.format</b></td><td>
        <span
            title="Http4k.format.argo&#10;org.http4k:http4k-format-argo:_&#10;version.http4k"
            style="text-decoration: underline;">
            argo
        </span>
        - 
        <span
            title="Http4k.format.core&#10;org.http4k:http4k-format-core:_&#10;version.http4k"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="Http4k.format.gson&#10;org.http4k:http4k-format-gson:_&#10;version.http4k"
            style="text-decoration: underline;">
            gson
        </span>
        - 
        <span
            title="Http4k.format.jacksonXml&#10;org.http4k:http4k-format-jackson-xml:_&#10;version.http4k"
            style="text-decoration: underline;">
            jacksonXml
        </span>
        - 
        <span
            title="Http4k.format.jacksonYaml&#10;org.http4k:http4k-format-jackson-yaml:_&#10;version.http4k"
            style="text-decoration: underline;">
            jacksonYaml
        </span>
        - 
        <span
            title="Http4k.format.jackson&#10;org.http4k:http4k-format-jackson:_&#10;version.http4k"
            style="text-decoration: underline;">
            jackson
        </span>
        - 
        <span
            title="Http4k.format.klaxon&#10;org.http4k:http4k-format-klaxon:_&#10;version.http4k"
            style="text-decoration: underline;">
            klaxon
        </span>
        - 
        <span
            title="Http4k.format.kotlinxSerialization&#10;org.http4k:http4k-format-kotlinx-serialization:_&#10;version.http4k"
            style="text-decoration: underline;">
            kotlinxSerialization
        </span>
        - 
        <span
            title="Http4k.format.moshi&#10;org.http4k:http4k-format-moshi:_&#10;version.http4k"
            style="text-decoration: underline;">
            moshi
        </span>
        - 
        <span
            title="Http4k.format.xml&#10;org.http4k:http4k-format-xml:_&#10;version.http4k"
            style="text-decoration: underline;">
            xml
        </span>
            
    </td></tr>
            
    <tr><td><b>Http4k.server</b></td><td>
        <span
            title="Http4k.server.apache4&#10;org.http4k:http4k-server-apache4:_&#10;version.http4k"
            style="text-decoration: underline;">
            apache4
        </span>
        - 
        <span
            title="Http4k.server.apache&#10;org.http4k:http4k-server-apache:_&#10;version.http4k"
            style="text-decoration: underline;">
            apache
        </span>
        - 
        <span
            title="Http4k.server.jetty&#10;org.http4k:http4k-server-jetty:_&#10;version.http4k"
            style="text-decoration: underline;">
            jetty
        </span>
        - 
        <span
            title="Http4k.server.ktorcio&#10;org.http4k:http4k-server-ktorcio:_&#10;version.http4k"
            style="text-decoration: underline;">
            ktorcio
        </span>
        - 
        <span
            title="Http4k.server.ktornetty&#10;org.http4k:http4k-server-ktornetty:_&#10;version.http4k"
            style="text-decoration: underline;">
            ktornetty
        </span>
        - 
        <span
            title="Http4k.server.netty&#10;org.http4k:http4k-server-netty:_&#10;version.http4k"
            style="text-decoration: underline;">
            netty
        </span>
        - 
        <span
            title="Http4k.server.ratpack&#10;org.http4k:http4k-server-ratpack:_&#10;version.http4k"
            style="text-decoration: underline;">
            ratpack
        </span>
        - 
        <span
            title="Http4k.server.undertow&#10;org.http4k:http4k-server-undertow:_&#10;version.http4k"
            style="text-decoration: underline;">
            undertow
        </span>
            
    </td></tr>
            
    <tr><td><b>Http4k.serverless</b></td><td>
        <span
            title="Http4k.serverless.alibaba&#10;org.http4k:http4k-serverless-alibaba:_&#10;version.http4k"
            style="text-decoration: underline;">
            alibaba
        </span>
        - 
        <span
            title="Http4k.serverless.azure&#10;org.http4k:http4k-serverless-azure:_&#10;version.http4k"
            style="text-decoration: underline;">
            azure
        </span>
        - 
        <span
            title="Http4k.serverless.gcf&#10;org.http4k:http4k-serverless-gcf:_&#10;version.http4k"
            style="text-decoration: underline;">
            gcf
        </span>
        - 
        <span
            title="Http4k.serverless.lambdaRuntime&#10;org.http4k:http4k-serverless-lambda-runtime:_&#10;version.http4k"
            style="text-decoration: underline;">
            lambdaRuntime
        </span>
        - 
        <span
            title="Http4k.serverless.lambda&#10;org.http4k:http4k-serverless-lambda:_&#10;version.http4k"
            style="text-decoration: underline;">
            lambda
        </span>
        - 
        <span
            title="Http4k.serverless.openwhisk&#10;org.http4k:http4k-serverless-openwhisk:_&#10;version.http4k"
            style="text-decoration: underline;">
            openwhisk
        </span>
        - 
        <span
            title="Http4k.serverless.tencent&#10;org.http4k:http4k-serverless-tencent:_&#10;version.http4k"
            style="text-decoration: underline;">
            tencent
        </span>
            
    </td></tr>
            
    <tr><td><b>Http4k.template</b></td><td>
        <span
            title="Http4k.template.core&#10;org.http4k:http4k-template-core:_&#10;version.http4k"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="Http4k.template.dust&#10;org.http4k:http4k-template-dust:_&#10;version.http4k"
            style="text-decoration: underline;">
            dust
        </span>
        - 
        <span
            title="Http4k.template.freemarker&#10;org.http4k:http4k-template-freemarker:_&#10;version.http4k"
            style="text-decoration: underline;">
            freemarker
        </span>
        - 
        <span
            title="Http4k.template.handlebars&#10;org.http4k:http4k-template-handlebars:_&#10;version.http4k"
            style="text-decoration: underline;">
            handlebars
        </span>
        - 
        <span
            title="Http4k.template.jade4j&#10;org.http4k:http4k-template-jade4j:_&#10;version.http4k"
            style="text-decoration: underline;">
            jade4j
        </span>
        - 
        <span
            title="Http4k.template.pebble&#10;org.http4k:http4k-template-pebble:_&#10;version.http4k"
            style="text-decoration: underline;">
            pebble
        </span>
        - 
        <span
            title="Http4k.template.thymeleaf&#10;org.http4k:http4k-template-thymeleaf:_&#10;version.http4k"
            style="text-decoration: underline;">
            thymeleaf
        </span>
            
    </td></tr>
            
    <tr><td><b>Http4k.testing</b></td><td>
        <span
            title="Http4k.testing.approval&#10;org.http4k:http4k-testing-approval:_&#10;version.http4k"
            style="text-decoration: underline;">
            approval
        </span>
        - 
        <span
            title="Http4k.testing.chaos&#10;org.http4k:http4k-testing-chaos:_&#10;version.http4k"
            style="text-decoration: underline;">
            chaos
        </span>
        - 
        <span
            title="Http4k.testing.hamkrest&#10;org.http4k:http4k-testing-hamkrest:_&#10;version.http4k"
            style="text-decoration: underline;">
            hamkrest
        </span>
        - 
        <span
            title="Http4k.testing.kotest&#10;org.http4k:http4k-testing-kotest:_&#10;version.http4k"
            style="text-decoration: underline;">
            kotest
        </span>
        - 
        <span
            title="Http4k.testing.servirtium&#10;org.http4k:http4k-testing-servirtium:_&#10;version.http4k"
            style="text-decoration: underline;">
            servirtium
        </span>
        - 
        <span
            title="Http4k.testing.strikt&#10;org.http4k:http4k-testing-strikt:_&#10;version.http4k"
            style="text-decoration: underline;">
            strikt
        </span>
        - 
        <span
            title="Http4k.testing.webdriver&#10;org.http4k:http4k-testing-webdriver:_&#10;version.http4k"
            style="text-decoration: underline;">
            webdriver
        </span>
            
    </td></tr>
            
</table>
            

## [JakeWharton.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/JakeWharton.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>JakeWharton</b></td><td>
        <span
            title="JakeWharton.confundusGradlePlugin&#10;com.jakewharton.confundus:confundus-gradle:_&#10;version.jakewharton.confundus"
            style="text-decoration: underline;">
            confundusGradlePlugin
        </span>
        - 
        <span
            title="JakeWharton.picnic&#10;com.jakewharton.picnic:picnic:_&#10;version.jakewharton.picnic"
            style="text-decoration: underline;">
            picnic
        </span>
        - 
        <span
            title="JakeWharton.rxBinding3&#10;com.jakewharton.rxbinding3:rxbinding:_&#10;version.jakewharton.rxbinding3"
            style="text-decoration: underline;">
            rxBinding3
        </span>
        - 
        <span
            title="JakeWharton.rxBinding4&#10;com.jakewharton.rxbinding4:rxbinding:_&#10;version.jakewharton.rxbinding4"
            style="text-decoration: underline;">
            rxBinding4
        </span>
        - 
        <span
            title="JakeWharton.rxRelay2&#10;com.jakewharton.rxrelay2:rxrelay:_&#10;version.jakewharton.rxrelay2"
            style="text-decoration: underline;">
            rxRelay2
        </span>
        - 
        <span
            title="JakeWharton.rxRelay3&#10;com.jakewharton.rxrelay3:rxrelay:_&#10;version.jakewharton.rxrelay3"
            style="text-decoration: underline;">
            rxRelay3
        </span>
        - 
        <span
            title="JakeWharton.timber&#10;com.jakewharton.timber:timber:_&#10;version.jakewharton.timber"
            style="text-decoration: underline;">
            timber
        </span>
        - 
        <span
            title="JakeWharton.wormholeGradlePlugin&#10;com.jakewharton.wormhole:wormhole-gradle:_&#10;version.jakewharton.wormhole"
            style="text-decoration: underline;">
            wormholeGradlePlugin
        </span>
            
    </td></tr>
            
    <tr><td><b>JakeWharton.moshi</b></td><td>
        <span
            title="JakeWharton.moshi.shimo&#10;com.jakewharton.moshi:shimo:_&#10;version.jakewharton.moshi.shimo"
            style="text-decoration: underline;">
            shimo
        </span>
            
    </td></tr>
            
    <tr><td><b>JakeWharton.retrofit2.converter</b></td><td>
        <span
            title="JakeWharton.retrofit2.converter.kotlinxSerialization&#10;com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:_&#10;version.jakewharton.retrofit2-kotlinx-serialization-converter"
            style="text-decoration: underline;">
            kotlinxSerialization
        </span>
            
    </td></tr>
            
    <tr><td><b>JakeWharton.rxBinding3</b></td><td>
        <span
            title="JakeWharton.rxBinding3.appcompat&#10;com.jakewharton.rxbinding3:rxbinding-appcompat:_&#10;version.jakewharton.rxbinding3"
            style="text-decoration: underline;">
            appcompat
        </span>
        - 
        <span
            title="JakeWharton.rxBinding3.core&#10;com.jakewharton.rxbinding3:rxbinding-core:_&#10;version.jakewharton.rxbinding3"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="JakeWharton.rxBinding3.drawerLayout&#10;com.jakewharton.rxbinding3:rxbinding-drawerlayout:_&#10;version.jakewharton.rxbinding3"
            style="text-decoration: underline;">
            drawerLayout
        </span>
        - 
        <span
            title="JakeWharton.rxBinding3.leanback&#10;com.jakewharton.rxbinding3:rxbinding-leanback:_&#10;version.jakewharton.rxbinding3"
            style="text-decoration: underline;">
            leanback
        </span>
        - 
        <span
            title="JakeWharton.rxBinding3.material&#10;com.jakewharton.rxbinding3:rxbinding-material:_&#10;version.jakewharton.rxbinding3"
            style="text-decoration: underline;">
            material
        </span>
        - 
        <span
            title="JakeWharton.rxBinding3.recyclerview&#10;com.jakewharton.rxbinding3:rxbinding-recyclerview:_&#10;version.jakewharton.rxbinding3"
            style="text-decoration: underline;">
            recyclerview
        </span>
        - 
        <span
            title="JakeWharton.rxBinding3.slidingPaneLayout&#10;com.jakewharton.rxbinding3:rxbinding-slidingpanelayout:_&#10;version.jakewharton.rxbinding3"
            style="text-decoration: underline;">
            slidingPaneLayout
        </span>
        - 
        <span
            title="JakeWharton.rxBinding3.swipeRefreshLayout&#10;com.jakewharton.rxbinding3:rxbinding-swiperefreshlayout:_&#10;version.jakewharton.rxbinding3"
            style="text-decoration: underline;">
            swipeRefreshLayout
        </span>
        - 
        <span
            title="JakeWharton.rxBinding3.viewPager2&#10;com.jakewharton.rxbinding3:rxbinding-viewpager2:_&#10;version.jakewharton.rxbinding3"
            style="text-decoration: underline;">
            viewPager2
        </span>
        - 
        <span
            title="JakeWharton.rxBinding3.viewPager&#10;com.jakewharton.rxbinding3:rxbinding-viewpager:_&#10;version.jakewharton.rxbinding3"
            style="text-decoration: underline;">
            viewPager
        </span>
            
    </td></tr>
            
    <tr><td><b>JakeWharton.rxBinding4</b></td><td>
        <span
            title="JakeWharton.rxBinding4.appcompat&#10;com.jakewharton.rxbinding4:rxbinding-appcompat:_&#10;version.jakewharton.rxbinding4"
            style="text-decoration: underline;">
            appcompat
        </span>
        - 
        <span
            title="JakeWharton.rxBinding4.core&#10;com.jakewharton.rxbinding4:rxbinding-core:_&#10;version.jakewharton.rxbinding4"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="JakeWharton.rxBinding4.drawerLayout&#10;com.jakewharton.rxbinding4:rxbinding-drawerlayout:_&#10;version.jakewharton.rxbinding4"
            style="text-decoration: underline;">
            drawerLayout
        </span>
        - 
        <span
            title="JakeWharton.rxBinding4.leanback&#10;com.jakewharton.rxbinding4:rxbinding-leanback:_&#10;version.jakewharton.rxbinding4"
            style="text-decoration: underline;">
            leanback
        </span>
        - 
        <span
            title="JakeWharton.rxBinding4.material&#10;com.jakewharton.rxbinding4:rxbinding-material:_&#10;version.jakewharton.rxbinding4"
            style="text-decoration: underline;">
            material
        </span>
        - 
        <span
            title="JakeWharton.rxBinding4.recyclerview&#10;com.jakewharton.rxbinding4:rxbinding-recyclerview:_&#10;version.jakewharton.rxbinding4"
            style="text-decoration: underline;">
            recyclerview
        </span>
        - 
        <span
            title="JakeWharton.rxBinding4.slidingPaneLayout&#10;com.jakewharton.rxbinding4:rxbinding-slidingpanelayout:_&#10;version.jakewharton.rxbinding4"
            style="text-decoration: underline;">
            slidingPaneLayout
        </span>
        - 
        <span
            title="JakeWharton.rxBinding4.swipeRefreshLayout&#10;com.jakewharton.rxbinding4:rxbinding-swiperefreshlayout:_&#10;version.jakewharton.rxbinding4"
            style="text-decoration: underline;">
            swipeRefreshLayout
        </span>
        - 
        <span
            title="JakeWharton.rxBinding4.viewPager2&#10;com.jakewharton.rxbinding4:rxbinding-viewpager2:_&#10;version.jakewharton.rxbinding4"
            style="text-decoration: underline;">
            viewPager2
        </span>
        - 
        <span
            title="JakeWharton.rxBinding4.viewPager&#10;com.jakewharton.rxbinding4:rxbinding-viewpager:_&#10;version.jakewharton.rxbinding4"
            style="text-decoration: underline;">
            viewPager
        </span>
            
    </td></tr>
            
</table>
            

## [JetBrains.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/JetBrains.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>JetBrains.exposed</b></td><td>
        <span
            title="JetBrains.exposed.core&#10;org.jetbrains.exposed:exposed-core:_&#10;version.jetbrains.exposed"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="JetBrains.exposed.dao&#10;org.jetbrains.exposed:exposed-dao:_&#10;version.jetbrains.exposed"
            style="text-decoration: underline;">
            dao
        </span>
        - 
        <span
            title="JetBrains.exposed.jdbc&#10;org.jetbrains.exposed:exposed-jdbc:_&#10;version.jetbrains.exposed"
            style="text-decoration: underline;">
            jdbc
        </span>
            
    </td></tr>
            
    <tr><td><b>JetBrains.ktor</b></td><td>
        <span
            title="JetBrains.ktor.server&#10;io.ktor:ktor-server:_&#10;version.ktor"
            style="text-decoration: underline;">
            server
        </span>
        - 
        <span
            title="JetBrains.ktor.testDispatcher&#10;io.ktor:ktor-test-dispatcher:_&#10;version.ktor"
            style="text-decoration: underline;">
            testDispatcher
        </span>
        - 
        <span
            title="JetBrains.ktor.utils&#10;io.ktor:ktor-utils:_&#10;version.ktor"
            style="text-decoration: underline;">
            utils
        </span>
            
    </td></tr>
            
    <tr><td><b>JetBrains.ktor.client</b></td><td>
        <span
            title="JetBrains.ktor.client.android&#10;io.ktor:ktor-client-android:_&#10;version.ktor"
            style="text-decoration: underline;">
            android
        </span>
        - 
        <span
            title="JetBrains.ktor.client.apache&#10;io.ktor:ktor-client-apache:_&#10;version.ktor"
            style="text-decoration: underline;">
            apache
        </span>
        - 
        <span
            title="JetBrains.ktor.client.auth&#10;io.ktor:ktor-client-auth:_&#10;version.ktor"
            style="text-decoration: underline;">
            auth
        </span>
        - 
        <span
            title="JetBrains.ktor.client.cio&#10;io.ktor:ktor-client-cio:_&#10;version.ktor"
            style="text-decoration: underline;">
            cio
        </span>
        - 
        <span
            title="JetBrains.ktor.client.contentNegotiationTests&#10;io.ktor:ktor-client-content-negotiation-tests:_&#10;version.ktor"
            style="text-decoration: underline;">
            contentNegotiationTests
        </span>
        - 
        <span
            title="JetBrains.ktor.client.contentNegotiation&#10;io.ktor:ktor-client-content-negotiation:_&#10;version.ktor"
            style="text-decoration: underline;">
            contentNegotiation
        </span>
        - 
        <span
            title="JetBrains.ktor.client.core&#10;io.ktor:ktor-client-core:_&#10;version.ktor"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="JetBrains.ktor.client.curl&#10;io.ktor:ktor-client-curl:_&#10;version.ktor"
            style="text-decoration: underline;">
            curl
        </span>
        - 
        <span
            title="JetBrains.ktor.client.darwin&#10;io.ktor:ktor-client-darwin:_&#10;version.ktor"
            style="text-decoration: underline;">
            darwin
        </span>
        - 
        <span
            title="JetBrains.ktor.client.encoding&#10;io.ktor:ktor-client-encoding:_&#10;version.ktor"
            style="text-decoration: underline;">
            encoding
        </span>
        - 
        <span
            title="JetBrains.ktor.client.gson&#10;io.ktor:ktor-client-gson:_&#10;version.ktor"
            style="text-decoration: underline;">
            gson
        </span>
        - 
        <span
            title="JetBrains.ktor.client.jackson&#10;io.ktor:ktor-client-jackson:_&#10;version.ktor"
            style="text-decoration: underline;">
            jackson
        </span>
        - 
        <span
            title="JetBrains.ktor.client.java&#10;io.ktor:ktor-client-java:_&#10;version.ktor"
            style="text-decoration: underline;">
            java
        </span>
        - 
        <span
            title="JetBrains.ktor.client.jetty&#10;io.ktor:ktor-client-jetty:_&#10;version.ktor"
            style="text-decoration: underline;">
            jetty
        </span>
        - 
        <span
            title="JetBrains.ktor.client.jsonTests&#10;io.ktor:ktor-client-json-tests:_&#10;version.ktor"
            style="text-decoration: underline;">
            jsonTests
        </span>
        - 
        <span
            title="JetBrains.ktor.client.json&#10;io.ktor:ktor-client-json:_&#10;version.ktor"
            style="text-decoration: underline;">
            json
        </span>
        - 
        <span
            title="JetBrains.ktor.client.logging&#10;io.ktor:ktor-client-logging:_&#10;version.ktor"
            style="text-decoration: underline;">
            logging
        </span>
        - 
        <span
            title="JetBrains.ktor.client.mock&#10;io.ktor:ktor-client-mock:_&#10;version.ktor"
            style="text-decoration: underline;">
            mock
        </span>
        - 
        <span
            title="JetBrains.ktor.client.okHttp&#10;io.ktor:ktor-client-okhttp:_&#10;version.ktor"
            style="text-decoration: underline;">
            okHttp
        </span>
        - 
        <span
            title="JetBrains.ktor.client.resources&#10;io.ktor:ktor-client-resources:_&#10;version.ktor"
            style="text-decoration: underline;">
            resources
        </span>
        - 
        <span
            title="JetBrains.ktor.client.serialization&#10;io.ktor:ktor-client-serialization:_&#10;version.ktor"
            style="text-decoration: underline;">
            serialization
        </span>
        - 
        <span
            title="JetBrains.ktor.client.tests&#10;io.ktor:ktor-client-tests:_&#10;version.ktor"
            style="text-decoration: underline;">
            tests
        </span>
            
    </td></tr>
            
    <tr><td><b>JetBrains.ktor.plugins</b></td><td>
        <span
            title="JetBrains.ktor.plugins.events&#10;io.ktor:ktor-events:_&#10;version.ktor"
            style="text-decoration: underline;">
            events
        </span>
        - 
        <span
            title="JetBrains.ktor.plugins.http&#10;io.ktor:ktor-http:_&#10;version.ktor"
            style="text-decoration: underline;">
            http
        </span>
        - 
        <span
            title="JetBrains.ktor.plugins.io&#10;io.ktor:ktor-io:_&#10;version.ktor"
            style="text-decoration: underline;">
            io
        </span>
        - 
        <span
            title="JetBrains.ktor.plugins.networkTlsCertificates&#10;io.ktor:ktor-network-tls-certificates:_&#10;version.ktor"
            style="text-decoration: underline;">
            networkTlsCertificates
        </span>
        - 
        <span
            title="JetBrains.ktor.plugins.networkTls&#10;io.ktor:ktor-network-tls:_&#10;version.ktor"
            style="text-decoration: underline;">
            networkTls
        </span>
        - 
        <span
            title="JetBrains.ktor.plugins.network&#10;io.ktor:ktor-network:_&#10;version.ktor"
            style="text-decoration: underline;">
            network
        </span>
        - 
        <span
            title="JetBrains.ktor.plugins.resources&#10;io.ktor:ktor-resources:_&#10;version.ktor"
            style="text-decoration: underline;">
            resources
        </span>
        - 
        <span
            title="JetBrains.ktor.plugins.serialization&#10;io.ktor:ktor-serialization:_&#10;version.ktor"
            style="text-decoration: underline;">
            serialization
        </span>
        - 
        <span
            title="JetBrains.ktor.plugins.websocketSerialization&#10;io.ktor:ktor-websocket-serialization:_&#10;version.ktor"
            style="text-decoration: underline;">
            websocketSerialization
        </span>
        - 
        <span
            title="JetBrains.ktor.plugins.websockets&#10;io.ktor:ktor-websockets:_&#10;version.ktor"
            style="text-decoration: underline;">
            websockets
        </span>
            
    </td></tr>
            
    <tr><td><b>JetBrains.ktor.plugins.http</b></td><td>
        <span
            title="JetBrains.ktor.plugins.http.cio&#10;io.ktor:ktor-http-cio:_&#10;version.ktor"
            style="text-decoration: underline;">
            cio
        </span>
            
    </td></tr>
            
    <tr><td><b>JetBrains.ktor.plugins.serialization</b></td><td>
        <span
            title="JetBrains.ktor.plugins.serialization.gson&#10;io.ktor:ktor-serialization-gson:_&#10;version.ktor"
            style="text-decoration: underline;">
            gson
        </span>
        - 
        <span
            title="JetBrains.ktor.plugins.serialization.jackson&#10;io.ktor:ktor-serialization-jackson:_&#10;version.ktor"
            style="text-decoration: underline;">
            jackson
        </span>
        - 
        <span
            title="JetBrains.ktor.plugins.serialization.kotlinx&#10;io.ktor:ktor-serialization-kotlinx:_&#10;version.ktor"
            style="text-decoration: underline;">
            kotlinx
        </span>
            
    </td></tr>
            
    <tr><td><b>JetBrains.ktor.plugins.serialization.kotlinx</b></td><td>
        <span
            title="JetBrains.ktor.plugins.serialization.kotlinx.cbor&#10;io.ktor:ktor-serialization-kotlinx-cbor:_&#10;version.ktor"
            style="text-decoration: underline;">
            cbor
        </span>
        - 
        <span
            title="JetBrains.ktor.plugins.serialization.kotlinx.json&#10;io.ktor:ktor-serialization-kotlinx-json:_&#10;version.ktor"
            style="text-decoration: underline;">
            json
        </span>
        - 
        <span
            title="JetBrains.ktor.plugins.serialization.kotlinx.tests&#10;io.ktor:ktor-serialization-kotlinx-tests:_&#10;version.ktor"
            style="text-decoration: underline;">
            tests
        </span>
        - 
        <span
            title="JetBrains.ktor.plugins.serialization.kotlinx.xml&#10;io.ktor:ktor-serialization-kotlinx-xml:_&#10;version.ktor"
            style="text-decoration: underline;">
            xml
        </span>
            
    </td></tr>
            
    <tr><td><b>JetBrains.ktor.server</b></td><td>
        <span
            title="JetBrains.ktor.server.auth&#10;io.ktor:ktor-server-auth:_&#10;version.ktor"
            style="text-decoration: underline;">
            auth
        </span>
        - 
        <span
            title="JetBrains.ktor.server.autoHeadResponse&#10;io.ktor:ktor-server-auto-head-response:_&#10;version.ktor"
            style="text-decoration: underline;">
            autoHeadResponse
        </span>
        - 
        <span
            title="JetBrains.ktor.server.cachingHeaders&#10;io.ktor:ktor-server-caching-headers:_&#10;version.ktor"
            style="text-decoration: underline;">
            cachingHeaders
        </span>
        - 
        <span
            title="JetBrains.ktor.server.callId&#10;io.ktor:ktor-server-call-id:_&#10;version.ktor"
            style="text-decoration: underline;">
            callId
        </span>
        - 
        <span
            title="JetBrains.ktor.server.callLogging&#10;io.ktor:ktor-server-call-logging:_&#10;version.ktor"
            style="text-decoration: underline;">
            callLogging
        </span>
        - 
        <span
            title="JetBrains.ktor.server.cio&#10;io.ktor:ktor-server-cio:_&#10;version.ktor"
            style="text-decoration: underline;">
            cio
        </span>
        - 
        <span
            title="JetBrains.ktor.server.compression&#10;io.ktor:ktor-server-compression:_&#10;version.ktor"
            style="text-decoration: underline;">
            compression
        </span>
        - 
        <span
            title="JetBrains.ktor.server.conditionalHeaders&#10;io.ktor:ktor-server-conditional-headers:_&#10;version.ktor"
            style="text-decoration: underline;">
            conditionalHeaders
        </span>
        - 
        <span
            title="JetBrains.ktor.server.contentNegotiation&#10;io.ktor:ktor-server-content-negotiation:_&#10;version.ktor"
            style="text-decoration: underline;">
            contentNegotiation
        </span>
        - 
        <span
            title="JetBrains.ktor.server.core&#10;io.ktor:ktor-server-core:_&#10;version.ktor"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="JetBrains.ktor.server.cors&#10;io.ktor:ktor-server-cors:_&#10;version.ktor"
            style="text-decoration: underline;">
            cors
        </span>
        - 
        <span
            title="JetBrains.ktor.server.dataConversion&#10;io.ktor:ktor-server-data-conversion:_&#10;version.ktor"
            style="text-decoration: underline;">
            dataConversion
        </span>
        - 
        <span
            title="JetBrains.ktor.server.defaultHeaders&#10;io.ktor:ktor-server-default-headers:_&#10;version.ktor"
            style="text-decoration: underline;">
            defaultHeaders
        </span>
        - 
        <span
            title="JetBrains.ktor.server.doubleReceive&#10;io.ktor:ktor-server-double-receive:_&#10;version.ktor"
            style="text-decoration: underline;">
            doubleReceive
        </span>
        - 
        <span
            title="JetBrains.ktor.server.forwardedHeader&#10;io.ktor:ktor-server-forwarded-header:_&#10;version.ktor"
            style="text-decoration: underline;">
            forwardedHeader
        </span>
        - 
        <span
            title="JetBrains.ktor.server.freeMarker&#10;io.ktor:ktor-server-freemarker:_&#10;version.ktor"
            style="text-decoration: underline;">
            freeMarker
        </span>
        - 
        <span
            title="JetBrains.ktor.server.hostCommon&#10;io.ktor:ktor-server-host-common:_&#10;version.ktor"
            style="text-decoration: underline;">
            hostCommon
        </span>
        - 
        <span
            title="JetBrains.ktor.server.hsts&#10;io.ktor:ktor-server-hsts:_&#10;version.ktor"
            style="text-decoration: underline;">
            hsts
        </span>
        - 
        <span
            title="JetBrains.ktor.server.htmlBuilder&#10;io.ktor:ktor-server-html-builder:_&#10;version.ktor"
            style="text-decoration: underline;">
            htmlBuilder
        </span>
        - 
        <span
            title="JetBrains.ktor.server.httpRedirect&#10;io.ktor:ktor-server-http-redirect:_&#10;version.ktor"
            style="text-decoration: underline;">
            httpRedirect
        </span>
        - 
        <span
            title="JetBrains.ktor.server.jetty&#10;io.ktor:ktor-server-jetty:_&#10;version.ktor"
            style="text-decoration: underline;">
            jetty
        </span>
        - 
        <span
            title="JetBrains.ktor.server.jte&#10;io.ktor:ktor-server-jte:_&#10;version.ktor"
            style="text-decoration: underline;">
            jte
        </span>
        - 
        <span
            title="JetBrains.ktor.server.locations&#10;io.ktor:ktor-server-locations:_&#10;version.ktor"
            style="text-decoration: underline;">
            locations
        </span>
        - 
        <span
            title="JetBrains.ktor.server.methodOverride&#10;io.ktor:ktor-server-method-override:_&#10;version.ktor"
            style="text-decoration: underline;">
            methodOverride
        </span>
        - 
        <span
            title="JetBrains.ktor.server.metricsMicrometer&#10;io.ktor:ktor-server-metrics-micrometer:_&#10;version.ktor"
            style="text-decoration: underline;">
            metricsMicrometer
        </span>
        - 
        <span
            title="JetBrains.ktor.server.metrics&#10;io.ktor:ktor-server-metrics:_&#10;version.ktor"
            style="text-decoration: underline;">
            metrics
        </span>
        - 
        <span
            title="JetBrains.ktor.server.mustache&#10;io.ktor:ktor-server-mustache:_&#10;version.ktor"
            style="text-decoration: underline;">
            mustache
        </span>
        - 
        <span
            title="JetBrains.ktor.server.netty&#10;io.ktor:ktor-server-netty:_&#10;version.ktor"
            style="text-decoration: underline;">
            netty
        </span>
        - 
        <span
            title="JetBrains.ktor.server.partialContent&#10;io.ktor:ktor-server-partial-content:_&#10;version.ktor"
            style="text-decoration: underline;">
            partialContent
        </span>
        - 
        <span
            title="JetBrains.ktor.server.pebble&#10;io.ktor:ktor-server-pebble:_&#10;version.ktor"
            style="text-decoration: underline;">
            pebble
        </span>
        - 
        <span
            title="JetBrains.ktor.server.resources&#10;io.ktor:ktor-server-resources:_&#10;version.ktor"
            style="text-decoration: underline;">
            resources
        </span>
        - 
        <span
            title="JetBrains.ktor.server.servlet&#10;io.ktor:ktor-server-servlet:_&#10;version.ktor"
            style="text-decoration: underline;">
            servlet
        </span>
        - 
        <span
            title="JetBrains.ktor.server.sessions&#10;io.ktor:ktor-server-sessions:_&#10;version.ktor"
            style="text-decoration: underline;">
            sessions
        </span>
        - 
        <span
            title="JetBrains.ktor.server.statusPages&#10;io.ktor:ktor-server-status-pages:_&#10;version.ktor"
            style="text-decoration: underline;">
            statusPages
        </span>
        - 
        <span
            title="JetBrains.ktor.server.testHost&#10;io.ktor:ktor-server-test-host:_&#10;version.ktor"
            style="text-decoration: underline;">
            testHost
        </span>
        - 
        <span
            title="JetBrains.ktor.server.testSuites&#10;io.ktor:ktor-server-test-suites:_&#10;version.ktor"
            style="text-decoration: underline;">
            testSuites
        </span>
        - 
        <span
            title="JetBrains.ktor.server.thymeleaf&#10;io.ktor:ktor-server-thymeleaf:_&#10;version.ktor"
            style="text-decoration: underline;">
            thymeleaf
        </span>
        - 
        <span
            title="JetBrains.ktor.server.tomcat&#10;io.ktor:ktor-server-tomcat:_&#10;version.ktor"
            style="text-decoration: underline;">
            tomcat
        </span>
        - 
        <span
            title="JetBrains.ktor.server.velocity&#10;io.ktor:ktor-server-velocity:_&#10;version.ktor"
            style="text-decoration: underline;">
            velocity
        </span>
        - 
        <span
            title="JetBrains.ktor.server.webjars&#10;io.ktor:ktor-server-webjars:_&#10;version.ktor"
            style="text-decoration: underline;">
            webjars
        </span>
        - 
        <span
            title="JetBrains.ktor.server.websockets&#10;io.ktor:ktor-server-websockets:_&#10;version.ktor"
            style="text-decoration: underline;">
            websockets
        </span>
            
    </td></tr>
            
    <tr><td><b>JetBrains.ktor.server.auth</b></td><td>
        <span
            title="JetBrains.ktor.server.auth.jwt&#10;io.ktor:ktor-server-auth-jwt:_&#10;version.ktor"
            style="text-decoration: underline;">
            jwt
        </span>
        - 
        <span
            title="JetBrains.ktor.server.auth.ldap&#10;io.ktor:ktor-server-auth-ldap:_&#10;version.ktor"
            style="text-decoration: underline;">
            ldap
        </span>
            
    </td></tr>
            
</table>
            

## [Kodein.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Kodein.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>Kodein.di</b></td><td>
        <span
            title="Kodein.di.configurableJS&#10;org.kodein.di:kodein-di-conf-js:_&#10;version.kodein.di"
            style="text-decoration: underline;">
            configurableJS
        </span>
        - 
        <span
            title="Kodein.di.configurableJvm&#10;org.kodein.di:kodein-di-conf-jvm:_&#10;version.kodein.di"
            style="text-decoration: underline;">
            configurableJvm
        </span>
        - 
        <span
            title="Kodein.di.androidCore&#10;org.kodein.di:kodein-di-framework-android-core:_&#10;version.kodein.di"
            style="text-decoration: underline;">
            androidCore
        </span>
        - 
        <span
            title="Kodein.di.androidSupport&#10;org.kodein.di:kodein-di-framework-android-support:_&#10;version.kodein.di"
            style="text-decoration: underline;">
            androidSupport
        </span>
        - 
        <span
            title="Kodein.di.androidx&#10;org.kodein.di:kodein-di-framework-android-x:_&#10;version.kodein.di"
            style="text-decoration: underline;">
            androidx
        </span>
        - 
        <span
            title="Kodein.di.ktor&#10;org.kodein.di:kodein-di-framework-ktor-server-jvm:_&#10;version.kodein.di"
            style="text-decoration: underline;">
            ktor
        </span>
        - 
        <span
            title="Kodein.di.tornadofx&#10;org.kodein.di:kodein-di-framework-tornadofx-jvm:_&#10;version.kodein.di"
            style="text-decoration: underline;">
            tornadofx
        </span>
        - 
        <span
            title="Kodein.di.js&#10;org.kodein.di:kodein-di-js:_&#10;version.kodein.di"
            style="text-decoration: underline;">
            js
        </span>
        - 
        <span
            title="Kodein.di.jsr330&#10;org.kodein.di:kodein-di-jxinject-jvm:_&#10;version.kodein.di"
            style="text-decoration: underline;">
            jsr330
        </span>
            
    </td></tr>
            
</table>
            

## [Koin.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Koin.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>Koin</b></td><td>
        <span
            title="Koin.androidCompat&#10;io.insert-koin:koin-android-compat:_&#10;version.koin"
            style="text-decoration: underline;">
            androidCompat
        </span>
        - 
        <span
            title="Koin.android&#10;io.insert-koin:koin-android:_&#10;version.koin"
            style="text-decoration: underline;">
            android
        </span>
        - 
        <span
            title="Koin.compose&#10;io.insert-koin:koin-androidx-compose:_&#10;version.koin"
            style="text-decoration: underline;">
            compose
        </span>
        - 
        <span
            title="Koin.navigation&#10;io.insert-koin:koin-androidx-navigation:_&#10;version.koin"
            style="text-decoration: underline;">
            navigation
        </span>
        - 
        <span
            title="Koin.workManager&#10;io.insert-koin:koin-androidx-workmanager:_&#10;version.koin"
            style="text-decoration: underline;">
            workManager
        </span>
        - 
        <span
            title="Koin.core&#10;io.insert-koin:koin-core:_&#10;version.koin"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="Koin.ktor&#10;io.insert-koin:koin-ktor:_&#10;version.koin"
            style="text-decoration: underline;">
            ktor
        </span>
        - 
        <span
            title="Koin.slf4j&#10;io.insert-koin:koin-logger-slf4j:_&#10;version.koin"
            style="text-decoration: underline;">
            slf4j
        </span>
        - 
        <span
            title="Koin.junit4&#10;io.insert-koin:koin-test-junit4:_&#10;version.koin"
            style="text-decoration: underline;">
            junit4
        </span>
        - 
        <span
            title="Koin.junit5&#10;io.insert-koin:koin-test-junit5:_&#10;version.koin"
            style="text-decoration: underline;">
            junit5
        </span>
        - 
        <span
            title="Koin.test&#10;io.insert-koin:koin-test:_&#10;version.koin"
            style="text-decoration: underline;">
            test
        </span>
            
    </td></tr>
            
</table>
            

## [Kotlin.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Kotlin.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>Kotlin</b></td><td>
        <span
            title="Kotlin.scriptRuntime&#10;org.jetbrains.kotlin:kotlin-script-runtime:_&#10;version.kotlin"
            style="text-decoration: underline;">
            scriptRuntime
        </span>
        - 
        <span
            title="Kotlin.stdlib&#10;org.jetbrains.kotlin:kotlin-stdlib:_&#10;version.kotlin"
            style="text-decoration: underline;">
            stdlib
        </span>
        - 
        <span
            title="Kotlin.test&#10;org.jetbrains.kotlin:kotlin-test:_&#10;version.kotlin"
            style="text-decoration: underline;">
            test
        </span>
            
    </td></tr>
            
    <tr><td><b>Kotlin.stdlib</b></td><td>
        <span
            title="Kotlin.stdlib.common&#10;org.jetbrains.kotlin:kotlin-stdlib-common:_&#10;version.kotlin"
            style="text-decoration: underline;">
            common
        </span>
        - 
        <span
            title="Kotlin.stdlib.jdk7&#10;org.jetbrains.kotlin:kotlin-stdlib-jdk7:_&#10;version.kotlin"
            style="text-decoration: underline;">
            jdk7
        </span>
        - 
        <span
            title="Kotlin.stdlib.jdk8&#10;org.jetbrains.kotlin:kotlin-stdlib-jdk8:_&#10;version.kotlin"
            style="text-decoration: underline;">
            jdk8
        </span>
        - 
        <span
            title="Kotlin.stdlib.js&#10;org.jetbrains.kotlin:kotlin-stdlib-js:_&#10;version.kotlin"
            style="text-decoration: underline;">
            js
        </span>
            
    </td></tr>
            
    <tr><td><b>Kotlin.test</b></td><td>
        <span
            title="Kotlin.test.annotationsCommon&#10;org.jetbrains.kotlin:kotlin-test-annotations-common:_&#10;version.kotlin"
            style="text-decoration: underline;">
            annotationsCommon
        </span>
        - 
        <span
            title="Kotlin.test.common&#10;org.jetbrains.kotlin:kotlin-test-common:_&#10;version.kotlin"
            style="text-decoration: underline;">
            common
        </span>
        - 
        <span
            title="Kotlin.test.jsRunner&#10;org.jetbrains.kotlin:kotlin-test-js-runner:_&#10;version.kotlin"
            style="text-decoration: underline;">
            jsRunner
        </span>
        - 
        <span
            title="Kotlin.test.js&#10;org.jetbrains.kotlin:kotlin-test-js:_&#10;version.kotlin"
            style="text-decoration: underline;">
            js
        </span>
        - 
        <span
            title="Kotlin.test.junit5&#10;org.jetbrains.kotlin:kotlin-test-junit5:_&#10;version.kotlin"
            style="text-decoration: underline;">
            junit5
        </span>
        - 
        <span
            title="Kotlin.test.junit&#10;org.jetbrains.kotlin:kotlin-test-junit:_&#10;version.kotlin"
            style="text-decoration: underline;">
            junit
        </span>
        - 
        <span
            title="Kotlin.test.testng&#10;org.jetbrains.kotlin:kotlin-test-testng:_&#10;version.kotlin"
            style="text-decoration: underline;">
            testng
        </span>
            
    </td></tr>
            
</table>
            

## [KotlinX.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/KotlinX.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>KotlinX</b></td><td>
        <span
            title="KotlinX.dataframe&#10;org.jetbrains.kotlinx:dataframe:_&#10;version.kotlinx.dataframe"
            style="text-decoration: underline;">
            dataframe
        </span>
        - 
        <span
            title="KotlinX.cli&#10;org.jetbrains.kotlinx:kotlinx-cli:_&#10;version.kotlinx.cli"
            style="text-decoration: underline;">
            cli
        </span>
        - 
        <span
            title="KotlinX.datetime&#10;org.jetbrains.kotlinx:kotlinx-datetime:_&#10;version.kotlinx.datetime"
            style="text-decoration: underline;">
            datetime
        </span>
        - 
        <span
            title="KotlinX.html&#10;org.jetbrains.kotlinx:kotlinx-html:_&#10;version.kotlinx.html"
            style="text-decoration: underline;">
            html
        </span>
        - 
        <span
            title="KotlinX.nodeJs&#10;org.jetbrains.kotlinx:kotlinx-nodejs:_&#10;version.kotlinx.nodejs"
            style="text-decoration: underline;">
            nodeJs
        </span>
        - 
        <span
            title="KotlinX.lincheck&#10;org.jetbrains.kotlinx:lincheck:_&#10;version.kotlinx.lincheck"
            style="text-decoration: underline;">
            lincheck
        </span>
            
    </td></tr>
            
    <tr><td><b>KotlinX.collections</b></td><td>
        <span
            title="KotlinX.collections.immutableJvmOnly&#10;org.jetbrains.kotlinx:kotlinx-collections-immutable-jvm:_&#10;version.kotlinx.collections.immutable"
            style="text-decoration: underline;">
            immutableJvmOnly
        </span>
        - 
        <span
            title="KotlinX.collections.immutable&#10;org.jetbrains.kotlinx:kotlinx-collections-immutable:_&#10;version.kotlinx.collections.immutable"
            style="text-decoration: underline;">
            immutable
        </span>
            
    </td></tr>
            
    <tr><td><b>KotlinX.coroutines</b></td><td>
        <span
            title="KotlinX.coroutines.android&#10;org.jetbrains.kotlinx:kotlinx-coroutines-android:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            android
        </span>
        - 
        <span
            title="KotlinX.coroutines.bom&#10;org.jetbrains.kotlinx:kotlinx-coroutines-bom:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            bom
        </span>
        - 
        <span
            title="KotlinX.coroutines.core&#10;org.jetbrains.kotlinx:kotlinx-coroutines-core:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="KotlinX.coroutines.debug&#10;org.jetbrains.kotlinx:kotlinx-coroutines-debug:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            debug
        </span>
        - 
        <span
            title="KotlinX.coroutines.guava&#10;org.jetbrains.kotlinx:kotlinx-coroutines-guava:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            guava
        </span>
        - 
        <span
            title="KotlinX.coroutines.javaFx&#10;org.jetbrains.kotlinx:kotlinx-coroutines-javafx:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            javaFx
        </span>
        - 
        <span
            title="KotlinX.coroutines.jdk8&#10;org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            jdk8
        </span>
        - 
        <span
            title="KotlinX.coroutines.jdk9&#10;org.jetbrains.kotlinx:kotlinx-coroutines-jdk9:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            jdk9
        </span>
        - 
        <span
            title="KotlinX.coroutines.playServices&#10;org.jetbrains.kotlinx:kotlinx-coroutines-play-services:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            playServices
        </span>
        - 
        <span
            title="KotlinX.coroutines.reactive&#10;org.jetbrains.kotlinx:kotlinx-coroutines-reactive:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            reactive
        </span>
        - 
        <span
            title="KotlinX.coroutines.reactor&#10;org.jetbrains.kotlinx:kotlinx-coroutines-reactor:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            reactor
        </span>
        - 
        <span
            title="KotlinX.coroutines.rx2&#10;org.jetbrains.kotlinx:kotlinx-coroutines-rx2:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            rx2
        </span>
        - 
        <span
            title="KotlinX.coroutines.rx3&#10;org.jetbrains.kotlinx:kotlinx-coroutines-rx3:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            rx3
        </span>
        - 
        <span
            title="KotlinX.coroutines.slf4j&#10;org.jetbrains.kotlinx:kotlinx-coroutines-slf4j:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            slf4j
        </span>
        - 
        <span
            title="KotlinX.coroutines.swing&#10;org.jetbrains.kotlinx:kotlinx-coroutines-swing:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            swing
        </span>
        - 
        <span
            title="KotlinX.coroutines.test&#10;org.jetbrains.kotlinx:kotlinx-coroutines-test:_&#10;version.kotlinx.coroutines"
            style="text-decoration: underline;">
            test
        </span>
            
    </td></tr>
            
    <tr><td><b>KotlinX.dataframe</b></td><td>
        <span
            title="KotlinX.dataframe.arrow&#10;org.jetbrains.kotlinx:dataframe-arrow:_&#10;version.kotlinx.dataframe"
            style="text-decoration: underline;">
            arrow
        </span>
        - 
        <span
            title="KotlinX.dataframe.core&#10;org.jetbrains.kotlinx:dataframe-core:_&#10;version.kotlinx.dataframe"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="KotlinX.dataframe.excel&#10;org.jetbrains.kotlinx:dataframe-excel:_&#10;version.kotlinx.dataframe"
            style="text-decoration: underline;">
            excel
        </span>
        - 
        <span
            title="KotlinX.dataframe.dataframe&#10;org.jetbrains.kotlinx:dataframe:_&#10;version.kotlinx.dataframe"
            style="text-decoration: underline;">
            dataframe
        </span>
            
    </td></tr>
            
    <tr><td><b>KotlinX.deeplearning</b></td><td>
        <span
            title="KotlinX.deeplearning.api&#10;org.jetbrains.kotlinx:kotlin-deeplearning-api:_&#10;version.kotlinx.deeplearning"
            style="text-decoration: underline;">
            api
        </span>
        - 
        <span
            title="KotlinX.deeplearning.onnx&#10;org.jetbrains.kotlinx:kotlin-deeplearning-onnx:_&#10;version.kotlinx.deeplearning"
            style="text-decoration: underline;">
            onnx
        </span>
        - 
        <span
            title="KotlinX.deeplearning.visualization&#10;org.jetbrains.kotlinx:kotlin-deeplearning-visualization:_&#10;version.kotlinx.deeplearning"
            style="text-decoration: underline;">
            visualization
        </span>
            
    </td></tr>
            
    <tr><td><b>KotlinX.io</b></td><td>
        <span
            title="KotlinX.io.jvm&#10;org.jetbrains.kotlinx:kotlinx-io-jvm:_&#10;version.kotlinx.io"
            style="text-decoration: underline;">
            jvm
        </span>
            
    </td></tr>
            
    <tr><td><b>KotlinX.lincheck</b></td><td>
        <span
            title="KotlinX.lincheck.jvm&#10;org.jetbrains.kotlinx:lincheck-jvm:_&#10;version.kotlinx.lincheck"
            style="text-decoration: underline;">
            jvm
        </span>
            
    </td></tr>
            
    <tr><td><b>KotlinX.multik</b></td><td>
        <span
            title="KotlinX.multik.api&#10;org.jetbrains.kotlinx:multik-api:_&#10;version.kotlinx.multik"
            style="text-decoration: underline;">
            api
        </span>
        - 
        <span
            title="KotlinX.multik.default&#10;org.jetbrains.kotlinx:multik-default:_&#10;version.kotlinx.multik"
            style="text-decoration: underline;">
            default
        </span>
        - 
        <span
            title="KotlinX.multik.jvm&#10;org.jetbrains.kotlinx:multik-jvm:_&#10;version.kotlinx.multik"
            style="text-decoration: underline;">
            jvm
        </span>
        - 
        <span
            title="KotlinX.multik.native&#10;org.jetbrains.kotlinx:multik-native:_&#10;version.kotlinx.multik"
            style="text-decoration: underline;">
            native
        </span>
            
    </td></tr>
            
    <tr><td><b>KotlinX.reflect</b></td><td>
        <span
            title="KotlinX.reflect.lite&#10;org.jetbrains.kotlinx:kotlinx.reflect.lite:_&#10;version.kotlinx.reflect.lite"
            style="text-decoration: underline;">
            lite
        </span>
            
    </td></tr>
            
    <tr><td><b>KotlinX.serialization</b></td><td>
        <span
            title="KotlinX.serialization.bom&#10;org.jetbrains.kotlinx:kotlinx-serialization-bom:_&#10;version.kotlinx.serialization"
            style="text-decoration: underline;">
            bom
        </span>
        - 
        <span
            title="KotlinX.serialization.cbor&#10;org.jetbrains.kotlinx:kotlinx-serialization-cbor:_&#10;version.kotlinx.serialization"
            style="text-decoration: underline;">
            cbor
        </span>
        - 
        <span
            title="KotlinX.serialization.core&#10;org.jetbrains.kotlinx:kotlinx-serialization-core:_&#10;version.kotlinx.serialization"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="KotlinX.serialization.hocon&#10;org.jetbrains.kotlinx:kotlinx-serialization-hocon:_&#10;version.kotlinx.serialization"
            style="text-decoration: underline;">
            hocon
        </span>
        - 
        <span
            title="KotlinX.serialization.json&#10;org.jetbrains.kotlinx:kotlinx-serialization-json:_&#10;version.kotlinx.serialization"
            style="text-decoration: underline;">
            json
        </span>
        - 
        <span
            title="KotlinX.serialization.properties&#10;org.jetbrains.kotlinx:kotlinx-serialization-properties:_&#10;version.kotlinx.serialization"
            style="text-decoration: underline;">
            properties
        </span>
        - 
        <span
            title="KotlinX.serialization.protobuf&#10;org.jetbrains.kotlinx:kotlinx-serialization-protobuf:_&#10;version.kotlinx.serialization"
            style="text-decoration: underline;">
            protobuf
        </span>
            
    </td></tr>
            
    <tr><td><b>KotlinX.serialization.json</b></td><td>
        <span
            title="KotlinX.serialization.json.okio&#10;org.jetbrains.kotlinx:kotlinx-serialization-json-okio:_&#10;version.kotlinx.serialization"
            style="text-decoration: underline;">
            okio
        </span>
            
    </td></tr>
            
</table>
            

## [Ktor.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Ktor.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>Ktor</b></td><td>
        <span
            title="Ktor.server&#10;io.ktor:ktor-server:_&#10;version.ktor"
            style="text-decoration: underline;">
            server
        </span>
        - 
        <span
            title="Ktor.testDispatcher&#10;io.ktor:ktor-test-dispatcher:_&#10;version.ktor"
            style="text-decoration: underline;">
            testDispatcher
        </span>
        - 
        <span
            title="Ktor.utils&#10;io.ktor:ktor-utils:_&#10;version.ktor"
            style="text-decoration: underline;">
            utils
        </span>
            
    </td></tr>
            
    <tr><td><b>Ktor.client</b></td><td>
        <span
            title="Ktor.client.android&#10;io.ktor:ktor-client-android:_&#10;version.ktor"
            style="text-decoration: underline;">
            android
        </span>
        - 
        <span
            title="Ktor.client.apache&#10;io.ktor:ktor-client-apache:_&#10;version.ktor"
            style="text-decoration: underline;">
            apache
        </span>
        - 
        <span
            title="Ktor.client.auth&#10;io.ktor:ktor-client-auth:_&#10;version.ktor"
            style="text-decoration: underline;">
            auth
        </span>
        - 
        <span
            title="Ktor.client.cio&#10;io.ktor:ktor-client-cio:_&#10;version.ktor"
            style="text-decoration: underline;">
            cio
        </span>
        - 
        <span
            title="Ktor.client.contentNegotiationTests&#10;io.ktor:ktor-client-content-negotiation-tests:_&#10;version.ktor"
            style="text-decoration: underline;">
            contentNegotiationTests
        </span>
        - 
        <span
            title="Ktor.client.contentNegotiation&#10;io.ktor:ktor-client-content-negotiation:_&#10;version.ktor"
            style="text-decoration: underline;">
            contentNegotiation
        </span>
        - 
        <span
            title="Ktor.client.core&#10;io.ktor:ktor-client-core:_&#10;version.ktor"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="Ktor.client.curl&#10;io.ktor:ktor-client-curl:_&#10;version.ktor"
            style="text-decoration: underline;">
            curl
        </span>
        - 
        <span
            title="Ktor.client.darwin&#10;io.ktor:ktor-client-darwin:_&#10;version.ktor"
            style="text-decoration: underline;">
            darwin
        </span>
        - 
        <span
            title="Ktor.client.encoding&#10;io.ktor:ktor-client-encoding:_&#10;version.ktor"
            style="text-decoration: underline;">
            encoding
        </span>
        - 
        <span
            title="Ktor.client.gson&#10;io.ktor:ktor-client-gson:_&#10;version.ktor"
            style="text-decoration: underline;">
            gson
        </span>
        - 
        <span
            title="Ktor.client.jackson&#10;io.ktor:ktor-client-jackson:_&#10;version.ktor"
            style="text-decoration: underline;">
            jackson
        </span>
        - 
        <span
            title="Ktor.client.java&#10;io.ktor:ktor-client-java:_&#10;version.ktor"
            style="text-decoration: underline;">
            java
        </span>
        - 
        <span
            title="Ktor.client.jetty&#10;io.ktor:ktor-client-jetty:_&#10;version.ktor"
            style="text-decoration: underline;">
            jetty
        </span>
        - 
        <span
            title="Ktor.client.jsonTests&#10;io.ktor:ktor-client-json-tests:_&#10;version.ktor"
            style="text-decoration: underline;">
            jsonTests
        </span>
        - 
        <span
            title="Ktor.client.json&#10;io.ktor:ktor-client-json:_&#10;version.ktor"
            style="text-decoration: underline;">
            json
        </span>
        - 
        <span
            title="Ktor.client.logging&#10;io.ktor:ktor-client-logging:_&#10;version.ktor"
            style="text-decoration: underline;">
            logging
        </span>
        - 
        <span
            title="Ktor.client.mock&#10;io.ktor:ktor-client-mock:_&#10;version.ktor"
            style="text-decoration: underline;">
            mock
        </span>
        - 
        <span
            title="Ktor.client.okHttp&#10;io.ktor:ktor-client-okhttp:_&#10;version.ktor"
            style="text-decoration: underline;">
            okHttp
        </span>
        - 
        <span
            title="Ktor.client.resources&#10;io.ktor:ktor-client-resources:_&#10;version.ktor"
            style="text-decoration: underline;">
            resources
        </span>
        - 
        <span
            title="Ktor.client.serialization&#10;io.ktor:ktor-client-serialization:_&#10;version.ktor"
            style="text-decoration: underline;">
            serialization
        </span>
        - 
        <span
            title="Ktor.client.tests&#10;io.ktor:ktor-client-tests:_&#10;version.ktor"
            style="text-decoration: underline;">
            tests
        </span>
            
    </td></tr>
            
    <tr><td><b>Ktor.plugins</b></td><td>
        <span
            title="Ktor.plugins.events&#10;io.ktor:ktor-events:_&#10;version.ktor"
            style="text-decoration: underline;">
            events
        </span>
        - 
        <span
            title="Ktor.plugins.http&#10;io.ktor:ktor-http:_&#10;version.ktor"
            style="text-decoration: underline;">
            http
        </span>
        - 
        <span
            title="Ktor.plugins.io&#10;io.ktor:ktor-io:_&#10;version.ktor"
            style="text-decoration: underline;">
            io
        </span>
        - 
        <span
            title="Ktor.plugins.networkTlsCertificates&#10;io.ktor:ktor-network-tls-certificates:_&#10;version.ktor"
            style="text-decoration: underline;">
            networkTlsCertificates
        </span>
        - 
        <span
            title="Ktor.plugins.networkTls&#10;io.ktor:ktor-network-tls:_&#10;version.ktor"
            style="text-decoration: underline;">
            networkTls
        </span>
        - 
        <span
            title="Ktor.plugins.network&#10;io.ktor:ktor-network:_&#10;version.ktor"
            style="text-decoration: underline;">
            network
        </span>
        - 
        <span
            title="Ktor.plugins.resources&#10;io.ktor:ktor-resources:_&#10;version.ktor"
            style="text-decoration: underline;">
            resources
        </span>
        - 
        <span
            title="Ktor.plugins.serialization&#10;io.ktor:ktor-serialization:_&#10;version.ktor"
            style="text-decoration: underline;">
            serialization
        </span>
        - 
        <span
            title="Ktor.plugins.websocketSerialization&#10;io.ktor:ktor-websocket-serialization:_&#10;version.ktor"
            style="text-decoration: underline;">
            websocketSerialization
        </span>
        - 
        <span
            title="Ktor.plugins.websockets&#10;io.ktor:ktor-websockets:_&#10;version.ktor"
            style="text-decoration: underline;">
            websockets
        </span>
            
    </td></tr>
            
    <tr><td><b>Ktor.plugins.http</b></td><td>
        <span
            title="Ktor.plugins.http.cio&#10;io.ktor:ktor-http-cio:_&#10;version.ktor"
            style="text-decoration: underline;">
            cio
        </span>
            
    </td></tr>
            
    <tr><td><b>Ktor.plugins.serialization</b></td><td>
        <span
            title="Ktor.plugins.serialization.gson&#10;io.ktor:ktor-serialization-gson:_&#10;version.ktor"
            style="text-decoration: underline;">
            gson
        </span>
        - 
        <span
            title="Ktor.plugins.serialization.jackson&#10;io.ktor:ktor-serialization-jackson:_&#10;version.ktor"
            style="text-decoration: underline;">
            jackson
        </span>
        - 
        <span
            title="Ktor.plugins.serialization.kotlinx&#10;io.ktor:ktor-serialization-kotlinx:_&#10;version.ktor"
            style="text-decoration: underline;">
            kotlinx
        </span>
            
    </td></tr>
            
    <tr><td><b>Ktor.plugins.serialization.kotlinx</b></td><td>
        <span
            title="Ktor.plugins.serialization.kotlinx.cbor&#10;io.ktor:ktor-serialization-kotlinx-cbor:_&#10;version.ktor"
            style="text-decoration: underline;">
            cbor
        </span>
        - 
        <span
            title="Ktor.plugins.serialization.kotlinx.json&#10;io.ktor:ktor-serialization-kotlinx-json:_&#10;version.ktor"
            style="text-decoration: underline;">
            json
        </span>
        - 
        <span
            title="Ktor.plugins.serialization.kotlinx.tests&#10;io.ktor:ktor-serialization-kotlinx-tests:_&#10;version.ktor"
            style="text-decoration: underline;">
            tests
        </span>
        - 
        <span
            title="Ktor.plugins.serialization.kotlinx.xml&#10;io.ktor:ktor-serialization-kotlinx-xml:_&#10;version.ktor"
            style="text-decoration: underline;">
            xml
        </span>
            
    </td></tr>
            
    <tr><td><b>Ktor.server</b></td><td>
        <span
            title="Ktor.server.auth&#10;io.ktor:ktor-server-auth:_&#10;version.ktor"
            style="text-decoration: underline;">
            auth
        </span>
        - 
        <span
            title="Ktor.server.autoHeadResponse&#10;io.ktor:ktor-server-auto-head-response:_&#10;version.ktor"
            style="text-decoration: underline;">
            autoHeadResponse
        </span>
        - 
        <span
            title="Ktor.server.cachingHeaders&#10;io.ktor:ktor-server-caching-headers:_&#10;version.ktor"
            style="text-decoration: underline;">
            cachingHeaders
        </span>
        - 
        <span
            title="Ktor.server.callId&#10;io.ktor:ktor-server-call-id:_&#10;version.ktor"
            style="text-decoration: underline;">
            callId
        </span>
        - 
        <span
            title="Ktor.server.callLogging&#10;io.ktor:ktor-server-call-logging:_&#10;version.ktor"
            style="text-decoration: underline;">
            callLogging
        </span>
        - 
        <span
            title="Ktor.server.cio&#10;io.ktor:ktor-server-cio:_&#10;version.ktor"
            style="text-decoration: underline;">
            cio
        </span>
        - 
        <span
            title="Ktor.server.compression&#10;io.ktor:ktor-server-compression:_&#10;version.ktor"
            style="text-decoration: underline;">
            compression
        </span>
        - 
        <span
            title="Ktor.server.conditionalHeaders&#10;io.ktor:ktor-server-conditional-headers:_&#10;version.ktor"
            style="text-decoration: underline;">
            conditionalHeaders
        </span>
        - 
        <span
            title="Ktor.server.contentNegotiation&#10;io.ktor:ktor-server-content-negotiation:_&#10;version.ktor"
            style="text-decoration: underline;">
            contentNegotiation
        </span>
        - 
        <span
            title="Ktor.server.core&#10;io.ktor:ktor-server-core:_&#10;version.ktor"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="Ktor.server.cors&#10;io.ktor:ktor-server-cors:_&#10;version.ktor"
            style="text-decoration: underline;">
            cors
        </span>
        - 
        <span
            title="Ktor.server.dataConversion&#10;io.ktor:ktor-server-data-conversion:_&#10;version.ktor"
            style="text-decoration: underline;">
            dataConversion
        </span>
        - 
        <span
            title="Ktor.server.defaultHeaders&#10;io.ktor:ktor-server-default-headers:_&#10;version.ktor"
            style="text-decoration: underline;">
            defaultHeaders
        </span>
        - 
        <span
            title="Ktor.server.doubleReceive&#10;io.ktor:ktor-server-double-receive:_&#10;version.ktor"
            style="text-decoration: underline;">
            doubleReceive
        </span>
        - 
        <span
            title="Ktor.server.forwardedHeader&#10;io.ktor:ktor-server-forwarded-header:_&#10;version.ktor"
            style="text-decoration: underline;">
            forwardedHeader
        </span>
        - 
        <span
            title="Ktor.server.freeMarker&#10;io.ktor:ktor-server-freemarker:_&#10;version.ktor"
            style="text-decoration: underline;">
            freeMarker
        </span>
        - 
        <span
            title="Ktor.server.hostCommon&#10;io.ktor:ktor-server-host-common:_&#10;version.ktor"
            style="text-decoration: underline;">
            hostCommon
        </span>
        - 
        <span
            title="Ktor.server.hsts&#10;io.ktor:ktor-server-hsts:_&#10;version.ktor"
            style="text-decoration: underline;">
            hsts
        </span>
        - 
        <span
            title="Ktor.server.htmlBuilder&#10;io.ktor:ktor-server-html-builder:_&#10;version.ktor"
            style="text-decoration: underline;">
            htmlBuilder
        </span>
        - 
        <span
            title="Ktor.server.httpRedirect&#10;io.ktor:ktor-server-http-redirect:_&#10;version.ktor"
            style="text-decoration: underline;">
            httpRedirect
        </span>
        - 
        <span
            title="Ktor.server.jetty&#10;io.ktor:ktor-server-jetty:_&#10;version.ktor"
            style="text-decoration: underline;">
            jetty
        </span>
        - 
        <span
            title="Ktor.server.jte&#10;io.ktor:ktor-server-jte:_&#10;version.ktor"
            style="text-decoration: underline;">
            jte
        </span>
        - 
        <span
            title="Ktor.server.locations&#10;io.ktor:ktor-server-locations:_&#10;version.ktor"
            style="text-decoration: underline;">
            locations
        </span>
        - 
        <span
            title="Ktor.server.methodOverride&#10;io.ktor:ktor-server-method-override:_&#10;version.ktor"
            style="text-decoration: underline;">
            methodOverride
        </span>
        - 
        <span
            title="Ktor.server.metricsMicrometer&#10;io.ktor:ktor-server-metrics-micrometer:_&#10;version.ktor"
            style="text-decoration: underline;">
            metricsMicrometer
        </span>
        - 
        <span
            title="Ktor.server.metrics&#10;io.ktor:ktor-server-metrics:_&#10;version.ktor"
            style="text-decoration: underline;">
            metrics
        </span>
        - 
        <span
            title="Ktor.server.mustache&#10;io.ktor:ktor-server-mustache:_&#10;version.ktor"
            style="text-decoration: underline;">
            mustache
        </span>
        - 
        <span
            title="Ktor.server.netty&#10;io.ktor:ktor-server-netty:_&#10;version.ktor"
            style="text-decoration: underline;">
            netty
        </span>
        - 
        <span
            title="Ktor.server.partialContent&#10;io.ktor:ktor-server-partial-content:_&#10;version.ktor"
            style="text-decoration: underline;">
            partialContent
        </span>
        - 
        <span
            title="Ktor.server.pebble&#10;io.ktor:ktor-server-pebble:_&#10;version.ktor"
            style="text-decoration: underline;">
            pebble
        </span>
        - 
        <span
            title="Ktor.server.resources&#10;io.ktor:ktor-server-resources:_&#10;version.ktor"
            style="text-decoration: underline;">
            resources
        </span>
        - 
        <span
            title="Ktor.server.servlet&#10;io.ktor:ktor-server-servlet:_&#10;version.ktor"
            style="text-decoration: underline;">
            servlet
        </span>
        - 
        <span
            title="Ktor.server.sessions&#10;io.ktor:ktor-server-sessions:_&#10;version.ktor"
            style="text-decoration: underline;">
            sessions
        </span>
        - 
        <span
            title="Ktor.server.statusPages&#10;io.ktor:ktor-server-status-pages:_&#10;version.ktor"
            style="text-decoration: underline;">
            statusPages
        </span>
        - 
        <span
            title="Ktor.server.testHost&#10;io.ktor:ktor-server-test-host:_&#10;version.ktor"
            style="text-decoration: underline;">
            testHost
        </span>
        - 
        <span
            title="Ktor.server.testSuites&#10;io.ktor:ktor-server-test-suites:_&#10;version.ktor"
            style="text-decoration: underline;">
            testSuites
        </span>
        - 
        <span
            title="Ktor.server.thymeleaf&#10;io.ktor:ktor-server-thymeleaf:_&#10;version.ktor"
            style="text-decoration: underline;">
            thymeleaf
        </span>
        - 
        <span
            title="Ktor.server.tomcat&#10;io.ktor:ktor-server-tomcat:_&#10;version.ktor"
            style="text-decoration: underline;">
            tomcat
        </span>
        - 
        <span
            title="Ktor.server.velocity&#10;io.ktor:ktor-server-velocity:_&#10;version.ktor"
            style="text-decoration: underline;">
            velocity
        </span>
        - 
        <span
            title="Ktor.server.webjars&#10;io.ktor:ktor-server-webjars:_&#10;version.ktor"
            style="text-decoration: underline;">
            webjars
        </span>
        - 
        <span
            title="Ktor.server.websockets&#10;io.ktor:ktor-server-websockets:_&#10;version.ktor"
            style="text-decoration: underline;">
            websockets
        </span>
            
    </td></tr>
            
    <tr><td><b>Ktor.server.auth</b></td><td>
        <span
            title="Ktor.server.auth.jwt&#10;io.ktor:ktor-server-auth-jwt:_&#10;version.ktor"
            style="text-decoration: underline;">
            jwt
        </span>
        - 
        <span
            title="Ktor.server.auth.ldap&#10;io.ktor:ktor-server-auth-ldap:_&#10;version.ktor"
            style="text-decoration: underline;">
            ldap
        </span>
            
    </td></tr>
            
</table>
            

## [Orchid.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Orchid.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>Orchid</b></td><td>
        <span
            title="Orchid.core&#10;io.github.javaeden.orchid:OrchidCore:_&#10;version.orchid"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="Orchid.test&#10;io.github.javaeden.orchid:OrchidTest:_&#10;version.orchid"
            style="text-decoration: underline;">
            test
        </span>
            
    </td></tr>
            
    <tr><td><b>Orchid.bundles</b></td><td>
        <span
            title="Orchid.bundles.all&#10;io.github.javaeden.orchid:OrchidAll:_&#10;version.orchid"
            style="text-decoration: underline;">
            all
        </span>
        - 
        <span
            title="Orchid.bundles.blog&#10;io.github.javaeden.orchid:OrchidBlog:_&#10;version.orchid"
            style="text-decoration: underline;">
            blog
        </span>
        - 
        <span
            title="Orchid.bundles.docs&#10;io.github.javaeden.orchid:OrchidDocs:_&#10;version.orchid"
            style="text-decoration: underline;">
            docs
        </span>
        - 
        <span
            title="Orchid.bundles.languagePack&#10;io.github.javaeden.orchid:OrchidLanguagePack:_&#10;version.orchid"
            style="text-decoration: underline;">
            languagePack
        </span>
            
    </td></tr>
            
    <tr><td><b>Orchid.plugins</b></td><td>
        <span
            title="Orchid.plugins.asciidoc&#10;io.github.javaeden.orchid:OrchidAsciidoc:_&#10;version.orchid"
            style="text-decoration: underline;">
            asciidoc
        </span>
        - 
        <span
            title="Orchid.plugins.azure&#10;io.github.javaeden.orchid:OrchidAzure:_&#10;version.orchid"
            style="text-decoration: underline;">
            azure
        </span>
        - 
        <span
            title="Orchid.plugins.bible&#10;io.github.javaeden.orchid:OrchidBible:_&#10;version.orchid"
            style="text-decoration: underline;">
            bible
        </span>
        - 
        <span
            title="Orchid.plugins.bitbucket&#10;io.github.javaeden.orchid:OrchidBitbucket:_&#10;version.orchid"
            style="text-decoration: underline;">
            bitbucket
        </span>
        - 
        <span
            title="Orchid.plugins.changelog&#10;io.github.javaeden.orchid:OrchidChangelog:_&#10;version.orchid"
            style="text-decoration: underline;">
            changelog
        </span>
        - 
        <span
            title="Orchid.plugins.diagrams&#10;io.github.javaeden.orchid:OrchidDiagrams:_&#10;version.orchid"
            style="text-decoration: underline;">
            diagrams
        </span>
        - 
        <span
            title="Orchid.plugins.forms&#10;io.github.javaeden.orchid:OrchidForms:_&#10;version.orchid"
            style="text-decoration: underline;">
            forms
        </span>
        - 
        <span
            title="Orchid.plugins.github&#10;io.github.javaeden.orchid:OrchidGithub:_&#10;version.orchid"
            style="text-decoration: underline;">
            github
        </span>
        - 
        <span
            title="Orchid.plugins.gitlab&#10;io.github.javaeden.orchid:OrchidGitlab:_&#10;version.orchid"
            style="text-decoration: underline;">
            gitlab
        </span>
        - 
        <span
            title="Orchid.plugins.groovydoc&#10;io.github.javaeden.orchid:OrchidGroovydoc:_&#10;version.orchid"
            style="text-decoration: underline;">
            groovydoc
        </span>
        - 
        <span
            title="Orchid.plugins.javadoc&#10;io.github.javaeden.orchid:OrchidJavadoc:_&#10;version.orchid"
            style="text-decoration: underline;">
            javadoc
        </span>
        - 
        <span
            title="Orchid.plugins.kss&#10;io.github.javaeden.orchid:OrchidKSS:_&#10;version.orchid"
            style="text-decoration: underline;">
            kss
        </span>
        - 
        <span
            title="Orchid.plugins.kotlindoc&#10;io.github.javaeden.orchid:OrchidKotlindoc:_&#10;version.orchid"
            style="text-decoration: underline;">
            kotlindoc
        </span>
        - 
        <span
            title="Orchid.plugins.netlify&#10;io.github.javaeden.orchid:OrchidNetlify:_&#10;version.orchid"
            style="text-decoration: underline;">
            netlify
        </span>
        - 
        <span
            title="Orchid.plugins.netlifyCMS&#10;io.github.javaeden.orchid:OrchidNetlifyCMS:_&#10;version.orchid"
            style="text-decoration: underline;">
            netlifyCMS
        </span>
        - 
        <span
            title="Orchid.plugins.pages&#10;io.github.javaeden.orchid:OrchidPages:_&#10;version.orchid"
            style="text-decoration: underline;">
            pages
        </span>
        - 
        <span
            title="Orchid.plugins.pluginDocs&#10;io.github.javaeden.orchid:OrchidPluginDocs:_&#10;version.orchid"
            style="text-decoration: underline;">
            pluginDocs
        </span>
        - 
        <span
            title="Orchid.plugins.posts&#10;io.github.javaeden.orchid:OrchidPosts:_&#10;version.orchid"
            style="text-decoration: underline;">
            posts
        </span>
        - 
        <span
            title="Orchid.plugins.presentations&#10;io.github.javaeden.orchid:OrchidPresentations:_&#10;version.orchid"
            style="text-decoration: underline;">
            presentations
        </span>
        - 
        <span
            title="Orchid.plugins.search&#10;io.github.javaeden.orchid:OrchidSearch:_&#10;version.orchid"
            style="text-decoration: underline;">
            search
        </span>
        - 
        <span
            title="Orchid.plugins.sourceDoc&#10;io.github.javaeden.orchid:OrchidSourceDoc:_&#10;version.orchid"
            style="text-decoration: underline;">
            sourceDoc
        </span>
        - 
        <span
            title="Orchid.plugins.swagger&#10;io.github.javaeden.orchid:OrchidSwagger:_&#10;version.orchid"
            style="text-decoration: underline;">
            swagger
        </span>
        - 
        <span
            title="Orchid.plugins.swiftdoc&#10;io.github.javaeden.orchid:OrchidSwiftdoc:_&#10;version.orchid"
            style="text-decoration: underline;">
            swiftdoc
        </span>
        - 
        <span
            title="Orchid.plugins.syntaxHighlighter&#10;io.github.javaeden.orchid:OrchidSyntaxHighlighter:_&#10;version.orchid"
            style="text-decoration: underline;">
            syntaxHighlighter
        </span>
        - 
        <span
            title="Orchid.plugins.taxonomies&#10;io.github.javaeden.orchid:OrchidTaxonomies:_&#10;version.orchid"
            style="text-decoration: underline;">
            taxonomies
        </span>
        - 
        <span
            title="Orchid.plugins.wiki&#10;io.github.javaeden.orchid:OrchidWiki:_&#10;version.orchid"
            style="text-decoration: underline;">
            wiki
        </span>
        - 
        <span
            title="Orchid.plugins.writersBlocks&#10;io.github.javaeden.orchid:OrchidWritersBlocks:_&#10;version.orchid"
            style="text-decoration: underline;">
            writersBlocks
        </span>
            
    </td></tr>
            
    <tr><td><b>Orchid.themes</b></td><td>
        <span
            title="Orchid.themes.bsDoc&#10;io.github.javaeden.orchid:OrchidBsDoc:_&#10;version.orchid"
            style="text-decoration: underline;">
            bsDoc
        </span>
        - 
        <span
            title="Orchid.themes.copper&#10;io.github.javaeden.orchid:OrchidCopper:_&#10;version.orchid"
            style="text-decoration: underline;">
            copper
        </span>
        - 
        <span
            title="Orchid.themes.editorial&#10;io.github.javaeden.orchid:OrchidEditorial:_&#10;version.orchid"
            style="text-decoration: underline;">
            editorial
        </span>
        - 
        <span
            title="Orchid.themes.futureImperfect&#10;io.github.javaeden.orchid:OrchidFutureImperfect:_&#10;version.orchid"
            style="text-decoration: underline;">
            futureImperfect
        </span>
            
    </td></tr>
            
</table>
            

## [ReactiveX.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/ReactiveX.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>ReactiveX</b></td><td>
        <span
            title="ReactiveX.rxJava2&#10;io.reactivex.rxjava2:rxjava:_&#10;version.rxjava2.rxjava"
            style="text-decoration: underline;">
            rxJava2
        </span>
        - 
        <span
            title="ReactiveX.rxJava3&#10;io.reactivex.rxjava3:rxjava:_&#10;version.rxjava3.rxjava"
            style="text-decoration: underline;">
            rxJava3
        </span>
            
    </td></tr>
            
    <tr><td><b>ReactiveX.rxJava2</b></td><td>
        <span
            title="ReactiveX.rxJava2.rxAndroid&#10;io.reactivex.rxjava2:rxandroid:_&#10;version.rxjava2.rxandroid"
            style="text-decoration: underline;">
            rxAndroid
        </span>
        - 
        <span
            title="ReactiveX.rxJava2.rxKotlin&#10;io.reactivex.rxjava2:rxkotlin:_&#10;version.rxjava2.rxkotlin"
            style="text-decoration: underline;">
            rxKotlin
        </span>
            
    </td></tr>
            
    <tr><td><b>ReactiveX.rxJava3</b></td><td>
        <span
            title="ReactiveX.rxJava3.rxAndroid&#10;io.reactivex.rxjava3:rxandroid:_&#10;version.rxjava3.rxandroid"
            style="text-decoration: underline;">
            rxAndroid
        </span>
        - 
        <span
            title="ReactiveX.rxJava3.rxKotlin&#10;io.reactivex.rxjava3:rxkotlin:_&#10;version.rxjava3.rxkotlin"
            style="text-decoration: underline;">
            rxKotlin
        </span>
            
    </td></tr>
            
</table>
            

## [RickClephas.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/RickClephas.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>RickClephas.nativeCoroutines</b></td><td>
        <span
            title="RickClephas.nativeCoroutines.annotations&#10;com.rickclephas.kmp:kmp-nativecoroutines-annotations:_&#10;version.rickclephas.nativecoroutines"
            style="text-decoration: underline;">
            annotations
        </span>
        - 
        <span
            title="RickClephas.nativeCoroutines.compilerEmbeddable&#10;com.rickclephas.kmp:kmp-nativecoroutines-compiler-embeddable:_&#10;version.rickclephas.nativecoroutines"
            style="text-decoration: underline;">
            compilerEmbeddable
        </span>
        - 
        <span
            title="RickClephas.nativeCoroutines.compiler&#10;com.rickclephas.kmp:kmp-nativecoroutines-compiler:_&#10;version.rickclephas.nativecoroutines"
            style="text-decoration: underline;">
            compiler
        </span>
        - 
        <span
            title="RickClephas.nativeCoroutines.core&#10;com.rickclephas.kmp:kmp-nativecoroutines-core:_&#10;version.rickclephas.nativecoroutines"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="RickClephas.nativeCoroutines.gradlePlugin&#10;com.rickclephas.kmp:kmp-nativecoroutines-gradle-plugin:_&#10;version.rickclephas.nativecoroutines"
            style="text-decoration: underline;">
            gradlePlugin
        </span>
            
    </td></tr>
            
</table>
            

## [RussHWolf.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/RussHWolf.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>RussHWolf</b></td><td>
        <span
            title="RussHWolf.multiplatformSettings&#10;com.russhwolf:multiplatform-settings:_&#10;version.multiplatform-settings"
            style="text-decoration: underline;">
            multiplatformSettings
        </span>
            
    </td></tr>
            
    <tr><td><b>RussHWolf.multiplatformSettings</b></td><td>
        <span
            title="RussHWolf.multiplatformSettings.coroutinesNativeMt&#10;com.russhwolf:multiplatform-settings-coroutines-native-mt:_&#10;version.multiplatform-settings"
            style="text-decoration: underline;">
            coroutinesNativeMt
        </span>
        - 
        <span
            title="RussHWolf.multiplatformSettings.coroutines&#10;com.russhwolf:multiplatform-settings-coroutines:_&#10;version.multiplatform-settings"
            style="text-decoration: underline;">
            coroutines
        </span>
        - 
        <span
            title="RussHWolf.multiplatformSettings.dataStore&#10;com.russhwolf:multiplatform-settings-datastore:_&#10;version.multiplatform-settings"
            style="text-decoration: underline;">
            dataStore
        </span>
        - 
        <span
            title="RussHWolf.multiplatformSettings.noArg&#10;com.russhwolf:multiplatform-settings-no-arg:_&#10;version.multiplatform-settings"
            style="text-decoration: underline;">
            noArg
        </span>
        - 
        <span
            title="RussHWolf.multiplatformSettings.serialization&#10;com.russhwolf:multiplatform-settings-serialization:_&#10;version.multiplatform-settings"
            style="text-decoration: underline;">
            serialization
        </span>
        - 
        <span
            title="RussHWolf.multiplatformSettings.test&#10;com.russhwolf:multiplatform-settings-test:_&#10;version.multiplatform-settings"
            style="text-decoration: underline;">
            test
        </span>
        - 
        <span
            title="RussHWolf.multiplatformSettings.settings&#10;com.russhwolf:multiplatform-settings:_&#10;version.multiplatform-settings"
            style="text-decoration: underline;">
            settings
        </span>
            
    </td></tr>
            
</table>
            

## [Splitties.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Splitties.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>Splitties</b></td><td>
        <span
            title="Splitties.activities&#10;com.louiscad.splitties:splitties-activities:_&#10;version.splitties"
            style="text-decoration: underline;">
            activities
        </span>
        - 
        <span
            title="Splitties.alertdialogAppcompatCoroutines&#10;com.louiscad.splitties:splitties-alertdialog-appcompat-coroutines:_&#10;version.splitties"
            style="text-decoration: underline;">
            alertdialogAppcompatCoroutines
        </span>
        - 
        <span
            title="Splitties.alertdialogAppcompat&#10;com.louiscad.splitties:splitties-alertdialog-appcompat:_&#10;version.splitties"
            style="text-decoration: underline;">
            alertdialogAppcompat
        </span>
        - 
        <span
            title="Splitties.alertdialogMaterial&#10;com.louiscad.splitties:splitties-alertdialog-material:_&#10;version.splitties"
            style="text-decoration: underline;">
            alertdialogMaterial
        </span>
        - 
        <span
            title="Splitties.alertdialog&#10;com.louiscad.splitties:splitties-alertdialog:_&#10;version.splitties"
            style="text-decoration: underline;">
            alertdialog
        </span>
        - 
        <span
            title="Splitties.appctx&#10;com.louiscad.splitties:splitties-appctx:_&#10;version.splitties"
            style="text-decoration: underline;">
            appctx
        </span>
        - 
        <span
            title="Splitties.archLifecycle&#10;com.louiscad.splitties:splitties-arch-lifecycle:_&#10;version.splitties"
            style="text-decoration: underline;">
            archLifecycle
        </span>
        - 
        <span
            title="Splitties.archRoom&#10;com.louiscad.splitties:splitties-arch-room:_&#10;version.splitties"
            style="text-decoration: underline;">
            archRoom
        </span>
        - 
        <span
            title="Splitties.bitflags&#10;com.louiscad.splitties:splitties-bitflags:_&#10;version.splitties"
            style="text-decoration: underline;">
            bitflags
        </span>
        - 
        <span
            title="Splitties.bundle&#10;com.louiscad.splitties:splitties-bundle:_&#10;version.splitties"
            style="text-decoration: underline;">
            bundle
        </span>
        - 
        <span
            title="Splitties.checkedlazy&#10;com.louiscad.splitties:splitties-checkedlazy:_&#10;version.splitties"
            style="text-decoration: underline;">
            checkedlazy
        </span>
        - 
        <span
            title="Splitties.collections&#10;com.louiscad.splitties:splitties-collections:_&#10;version.splitties"
            style="text-decoration: underline;">
            collections
        </span>
        - 
        <span
            title="Splitties.coroutines&#10;com.louiscad.splitties:splitties-coroutines:_&#10;version.splitties"
            style="text-decoration: underline;">
            coroutines
        </span>
        - 
        <span
            title="Splitties.dimensions&#10;com.louiscad.splitties:splitties-dimensions:_&#10;version.splitties"
            style="text-decoration: underline;">
            dimensions
        </span>
        - 
        <span
            title="Splitties.exceptions&#10;com.louiscad.splitties:splitties-exceptions:_&#10;version.splitties"
            style="text-decoration: underline;">
            exceptions
        </span>
        - 
        <span
            title="Splitties.fragmentargs&#10;com.louiscad.splitties:splitties-fragmentargs:_&#10;version.splitties"
            style="text-decoration: underline;">
            fragmentargs
        </span>
        - 
        <span
            title="Splitties.fragments&#10;com.louiscad.splitties:splitties-fragments:_&#10;version.splitties"
            style="text-decoration: underline;">
            fragments
        </span>
        - 
        <span
            title="Splitties.initprovider&#10;com.louiscad.splitties:splitties-initprovider:_&#10;version.splitties"
            style="text-decoration: underline;">
            initprovider
        </span>
        - 
        <span
            title="Splitties.intents&#10;com.louiscad.splitties:splitties-intents:_&#10;version.splitties"
            style="text-decoration: underline;">
            intents
        </span>
        - 
        <span
            title="Splitties.lifecycleCoroutines&#10;com.louiscad.splitties:splitties-lifecycle-coroutines:_&#10;version.splitties"
            style="text-decoration: underline;">
            lifecycleCoroutines
        </span>
        - 
        <span
            title="Splitties.mainhandler&#10;com.louiscad.splitties:splitties-mainhandler:_&#10;version.splitties"
            style="text-decoration: underline;">
            mainhandler
        </span>
        - 
        <span
            title="Splitties.mainthread&#10;com.louiscad.splitties:splitties-mainthread:_&#10;version.splitties"
            style="text-decoration: underline;">
            mainthread
        </span>
        - 
        <span
            title="Splitties.materialColors&#10;com.louiscad.splitties:splitties-material-colors:_&#10;version.splitties"
            style="text-decoration: underline;">
            materialColors
        </span>
        - 
        <span
            title="Splitties.materialLists&#10;com.louiscad.splitties:splitties-material-lists:_&#10;version.splitties"
            style="text-decoration: underline;">
            materialLists
        </span>
        - 
        <span
            title="Splitties.permissions&#10;com.louiscad.splitties:splitties-permissions:_&#10;version.splitties"
            style="text-decoration: underline;">
            permissions
        </span>
        - 
        <span
            title="Splitties.preferences&#10;com.louiscad.splitties:splitties-preferences:_&#10;version.splitties"
            style="text-decoration: underline;">
            preferences
        </span>
        - 
        <span
            title="Splitties.resources&#10;com.louiscad.splitties:splitties-resources:_&#10;version.splitties"
            style="text-decoration: underline;">
            resources
        </span>
        - 
        <span
            title="Splitties.snackbar&#10;com.louiscad.splitties:splitties-snackbar:_&#10;version.splitties"
            style="text-decoration: underline;">
            snackbar
        </span>
        - 
        <span
            title="Splitties.stethoInit&#10;com.louiscad.splitties:splitties-stetho-init:_&#10;version.splitties"
            style="text-decoration: underline;">
            stethoInit
        </span>
        - 
        <span
            title="Splitties.systemservices&#10;com.louiscad.splitties:splitties-systemservices:_&#10;version.splitties"
            style="text-decoration: underline;">
            systemservices
        </span>
        - 
        <span
            title="Splitties.toast&#10;com.louiscad.splitties:splitties-toast:_&#10;version.splitties"
            style="text-decoration: underline;">
            toast
        </span>
        - 
        <span
            title="Splitties.typesaferecyclerview&#10;com.louiscad.splitties:splitties-typesaferecyclerview:_&#10;version.splitties"
            style="text-decoration: underline;">
            typesaferecyclerview
        </span>
        - 
        <span
            title="Splitties.viewsAppcompat&#10;com.louiscad.splitties:splitties-views-appcompat:_&#10;version.splitties"
            style="text-decoration: underline;">
            viewsAppcompat
        </span>
        - 
        <span
            title="Splitties.viewsCardview&#10;com.louiscad.splitties:splitties-views-cardview:_&#10;version.splitties"
            style="text-decoration: underline;">
            viewsCardview
        </span>
        - 
        <span
            title="Splitties.viewsCoroutinesMaterial&#10;com.louiscad.splitties:splitties-views-coroutines-material:_&#10;version.splitties"
            style="text-decoration: underline;">
            viewsCoroutinesMaterial
        </span>
        - 
        <span
            title="Splitties.viewsCoroutines&#10;com.louiscad.splitties:splitties-views-coroutines:_&#10;version.splitties"
            style="text-decoration: underline;">
            viewsCoroutines
        </span>
        - 
        <span
            title="Splitties.viewsDslAppcompat&#10;com.louiscad.splitties:splitties-views-dsl-appcompat:_&#10;version.splitties"
            style="text-decoration: underline;">
            viewsDslAppcompat
        </span>
        - 
        <span
            title="Splitties.viewsDslConstraintlayout&#10;com.louiscad.splitties:splitties-views-dsl-constraintlayout:_&#10;version.splitties"
            style="text-decoration: underline;">
            viewsDslConstraintlayout
        </span>
        - 
        <span
            title="Splitties.viewsDslCoordinatorlayout&#10;com.louiscad.splitties:splitties-views-dsl-coordinatorlayout:_&#10;version.splitties"
            style="text-decoration: underline;">
            viewsDslCoordinatorlayout
        </span>
        - 
        <span
            title="Splitties.viewsDslMaterial&#10;com.louiscad.splitties:splitties-views-dsl-material:_&#10;version.splitties"
            style="text-decoration: underline;">
            viewsDslMaterial
        </span>
        - 
        <span
            title="Splitties.viewsDslRecyclerview&#10;com.louiscad.splitties:splitties-views-dsl-recyclerview:_&#10;version.splitties"
            style="text-decoration: underline;">
            viewsDslRecyclerview
        </span>
        - 
        <span
            title="Splitties.viewsDsl&#10;com.louiscad.splitties:splitties-views-dsl:_&#10;version.splitties"
            style="text-decoration: underline;">
            viewsDsl
        </span>
        - 
        <span
            title="Splitties.viewsMaterial&#10;com.louiscad.splitties:splitties-views-material:_&#10;version.splitties"
            style="text-decoration: underline;">
            viewsMaterial
        </span>
        - 
        <span
            title="Splitties.viewsRecyclerview&#10;com.louiscad.splitties:splitties-views-recyclerview:_&#10;version.splitties"
            style="text-decoration: underline;">
            viewsRecyclerview
        </span>
        - 
        <span
            title="Splitties.viewsSelectableAppcompat&#10;com.louiscad.splitties:splitties-views-selectable-appcompat:_&#10;version.splitties"
            style="text-decoration: underline;">
            viewsSelectableAppcompat
        </span>
        - 
        <span
            title="Splitties.viewsSelectableConstraintlayout&#10;com.louiscad.splitties:splitties-views-selectable-constraintlayout:_&#10;version.splitties"
            style="text-decoration: underline;">
            viewsSelectableConstraintlayout
        </span>
        - 
        <span
            title="Splitties.viewsSelectable&#10;com.louiscad.splitties:splitties-views-selectable:_&#10;version.splitties"
            style="text-decoration: underline;">
            viewsSelectable
        </span>
        - 
        <span
            title="Splitties.views&#10;com.louiscad.splitties:splitties-views:_&#10;version.splitties"
            style="text-decoration: underline;">
            views
        </span>
            
    </td></tr>
            
    <tr><td><b>Splitties.pack</b></td><td>
        <span
            title="Splitties.pack.appCompatWithViewsDsl&#10;com.louiscad.splitties:splitties-fun-pack-android-appcompat-with-views-dsl:_&#10;version.splitties"
            style="text-decoration: underline;">
            appCompatWithViewsDsl
        </span>
        - 
        <span
            title="Splitties.pack.appCompat&#10;com.louiscad.splitties:splitties-fun-pack-android-appcompat:_&#10;version.splitties"
            style="text-decoration: underline;">
            appCompat
        </span>
        - 
        <span
            title="Splitties.pack.androidBaseWithViewsDsl&#10;com.louiscad.splitties:splitties-fun-pack-android-base-with-views-dsl:_&#10;version.splitties"
            style="text-decoration: underline;">
            androidBaseWithViewsDsl
        </span>
        - 
        <span
            title="Splitties.pack.androidBase&#10;com.louiscad.splitties:splitties-fun-pack-android-base:_&#10;version.splitties"
            style="text-decoration: underline;">
            androidBase
        </span>
        - 
        <span
            title="Splitties.pack.androidMdcWithViewsDsl&#10;com.louiscad.splitties:splitties-fun-pack-android-material-components-with-views-dsl:_&#10;version.splitties"
            style="text-decoration: underline;">
            androidMdcWithViewsDsl
        </span>
        - 
        <span
            title="Splitties.pack.androidMdc&#10;com.louiscad.splitties:splitties-fun-pack-android-material-components:_&#10;version.splitties"
            style="text-decoration: underline;">
            androidMdc
        </span>
            
    </td></tr>
            
</table>
            

## [Spring.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Spring.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>Spring</b></td><td>
        <span
            title="Spring.rabbitTest&#10;org.springframework.amqp:spring-rabbit-test:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            rabbitTest
        </span>
        - 
        <span
            title="Spring.batchTest&#10;org.springframework.batch:spring-batch-test:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            batchTest
        </span>
        - 
        <span
            title="Spring.geode&#10;org.springframework.geode:spring-geode-starter:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            geode
        </span>
        - 
        <span
            title="Spring.kafkaTest&#10;org.springframework.kafka:spring-kafka-test:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            kafkaTest
        </span>
        - 
        <span
            title="Spring.kafka&#10;org.springframework.kafka:spring-kafka:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            kafka
        </span>
        - 
        <span
            title="Spring.springRestdocsWebtestclient&#10;org.springframework.restdocs:spring-restdocs-webtestclient:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            springRestdocsWebtestclient
        </span>
            
    </td></tr>
            
    <tr><td><b>Spring.boms</b></td><td>
        <span
            title="Spring.boms.dependencies&#10;org.springframework.boot:spring-boot-dependencies:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            dependencies
        </span>
        - 
        <span
            title="Spring.boms.springCloud&#10;org.springframework.cloud:spring-cloud-dependencies:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            springCloud
        </span>
        - 
        <span
            title="Spring.boms.geode&#10;org.springframework.geode:spring-geode-bom:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            geode
        </span>
            
    </td></tr>
            
    <tr><td><b>Spring.boot</b></td><td>
        <span
            title="Spring.boot.configurationProcessor&#10;org.springframework.boot:spring-boot-configuration-processor:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            configurationProcessor
        </span>
        - 
        <span
            title="Spring.boot.devTools&#10;org.springframework.boot:spring-boot-devtools:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            devTools
        </span>
        - 
        <span
            title="Spring.boot.activemq&#10;org.springframework.boot:spring-boot-starter-activemq:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            activemq
        </span>
        - 
        <span
            title="Spring.boot.actuator&#10;org.springframework.boot:spring-boot-starter-actuator:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            actuator
        </span>
        - 
        <span
            title="Spring.boot.amqp&#10;org.springframework.boot:spring-boot-starter-amqp:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            amqp
        </span>
        - 
        <span
            title="Spring.boot.artemis&#10;org.springframework.boot:spring-boot-starter-artemis:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            artemis
        </span>
        - 
        <span
            title="Spring.boot.batch&#10;org.springframework.boot:spring-boot-starter-batch:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            batch
        </span>
        - 
        <span
            title="Spring.boot.cache&#10;org.springframework.boot:spring-boot-starter-cache:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            cache
        </span>
        - 
        <span
            title="Spring.boot.freemarker&#10;org.springframework.boot:spring-boot-starter-freemarker:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            freemarker
        </span>
        - 
        <span
            title="Spring.boot.groovyTemplates&#10;org.springframework.boot:spring-boot-starter-groovy-templates:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            groovyTemplates
        </span>
        - 
        <span
            title="Spring.boot.hateoas&#10;org.springframework.boot:spring-boot-starter-hateoas:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            hateoas
        </span>
        - 
        <span
            title="Spring.boot.integration&#10;org.springframework.boot:spring-boot-starter-integration:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            integration
        </span>
        - 
        <span
            title="Spring.boot.jdbc&#10;org.springframework.boot:spring-boot-starter-jdbc:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            jdbc
        </span>
        - 
        <span
            title="Spring.boot.jersey&#10;org.springframework.boot:spring-boot-starter-jersey:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            jersey
        </span>
        - 
        <span
            title="Spring.boot.jooq&#10;org.springframework.boot:spring-boot-starter-jooq:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            jooq
        </span>
        - 
        <span
            title="Spring.boot.mail&#10;org.springframework.boot:spring-boot-starter-mail:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            mail
        </span>
        - 
        <span
            title="Spring.boot.mustache&#10;org.springframework.boot:spring-boot-starter-mustache:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            mustache
        </span>
        - 
        <span
            title="Spring.boot.oauth2Client&#10;org.springframework.boot:spring-boot-starter-oauth2-client:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            oauth2Client
        </span>
        - 
        <span
            title="Spring.boot.oauth2ResourceServer&#10;org.springframework.boot:spring-boot-starter-oauth2-resource-server:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            oauth2ResourceServer
        </span>
        - 
        <span
            title="Spring.boot.quartz&#10;org.springframework.boot:spring-boot-starter-quartz:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            quartz
        </span>
        - 
        <span
            title="Spring.boot.rsocket&#10;org.springframework.boot:spring-boot-starter-rsocket:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            rsocket
        </span>
        - 
        <span
            title="Spring.boot.security&#10;org.springframework.boot:spring-boot-starter-security:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            security
        </span>
        - 
        <span
            title="Spring.boot.test&#10;org.springframework.boot:spring-boot-starter-test:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            test
        </span>
        - 
        <span
            title="Spring.boot.thymeleaf&#10;org.springframework.boot:spring-boot-starter-thymeleaf:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            thymeleaf
        </span>
        - 
        <span
            title="Spring.boot.validation&#10;org.springframework.boot:spring-boot-starter-validation:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            validation
        </span>
        - 
        <span
            title="Spring.boot.webServices&#10;org.springframework.boot:spring-boot-starter-web-services:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            webServices
        </span>
        - 
        <span
            title="Spring.boot.web&#10;org.springframework.boot:spring-boot-starter-web:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            web
        </span>
        - 
        <span
            title="Spring.boot.webflux&#10;org.springframework.boot:spring-boot-starter-webflux:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            webflux
        </span>
        - 
        <span
            title="Spring.boot.websocket&#10;org.springframework.boot:spring-boot-starter-websocket:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            websocket
        </span>
            
    </td></tr>
            
    <tr><td><b>Spring.boot.data</b></td><td>
        <span
            title="Spring.boot.data.cassandraReactive&#10;org.springframework.boot:spring-boot-starter-data-cassandra-reactive:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            cassandraReactive
        </span>
        - 
        <span
            title="Spring.boot.data.cassandra&#10;org.springframework.boot:spring-boot-starter-data-cassandra:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            cassandra
        </span>
        - 
        <span
            title="Spring.boot.data.couchbase_reactive&#10;org.springframework.boot:spring-boot-starter-data-couchbase-reactive:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            couchbase_reactive
        </span>
        - 
        <span
            title="Spring.boot.data.couchbase&#10;org.springframework.boot:spring-boot-starter-data-couchbase:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            couchbase
        </span>
        - 
        <span
            title="Spring.boot.data.elasticsearch&#10;org.springframework.boot:spring-boot-starter-data-elasticsearch:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            elasticsearch
        </span>
        - 
        <span
            title="Spring.boot.data.jdbc&#10;org.springframework.boot:spring-boot-starter-data-jdbc:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            jdbc
        </span>
        - 
        <span
            title="Spring.boot.data.jpa&#10;org.springframework.boot:spring-boot-starter-data-jpa:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            jpa
        </span>
        - 
        <span
            title="Spring.boot.data.ldap&#10;org.springframework.boot:spring-boot-starter-data-ldap:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            ldap
        </span>
        - 
        <span
            title="Spring.boot.data.mongodbReactive&#10;org.springframework.boot:spring-boot-starter-data-mongodb-reactive:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            mongodbReactive
        </span>
        - 
        <span
            title="Spring.boot.data.mongodb&#10;org.springframework.boot:spring-boot-starter-data-mongodb:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            mongodb
        </span>
        - 
        <span
            title="Spring.boot.data.neo4j&#10;org.springframework.boot:spring-boot-starter-data-neo4j:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            neo4j
        </span>
        - 
        <span
            title="Spring.boot.data.r2dbc&#10;org.springframework.boot:spring-boot-starter-data-r2dbc:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            r2dbc
        </span>
        - 
        <span
            title="Spring.boot.data.redis_reactive&#10;org.springframework.boot:spring-boot-starter-data-redis-reactive:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            redis_reactive
        </span>
        - 
        <span
            title="Spring.boot.data.redis&#10;org.springframework.boot:spring-boot-starter-data-redis:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            redis
        </span>
        - 
        <span
            title="Spring.boot.data.rest&#10;org.springframework.boot:spring-boot-starter-data-rest:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            rest
        </span>
        - 
        <span
            title="Spring.boot.data.solr&#10;org.springframework.boot:spring-boot-starter-data-solr:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            solr
        </span>
            
    </td></tr>
            
    <tr><td><b>Spring.cloud</b></td><td>
        <span
            title="Spring.cloud.bus&#10;org.springframework.cloud:spring-cloud-bus:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            bus
        </span>
        - 
        <span
            title="Spring.cloud.cloudfoundry_discovery&#10;org.springframework.cloud:spring-cloud-cloudfoundry-discovery:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            cloudfoundry_discovery
        </span>
        - 
        <span
            title="Spring.cloud.config_server&#10;org.springframework.cloud:spring-cloud-config-server:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            config_server
        </span>
        - 
        <span
            title="Spring.cloud.function_web&#10;org.springframework.cloud:spring-cloud-function-web:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            function_web
        </span>
        - 
        <span
            title="Spring.cloud.gcp_starter_pubsub&#10;org.springframework.cloud:spring-cloud-gcp-starter-pubsub:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            gcp_starter_pubsub
        </span>
        - 
        <span
            title="Spring.cloud.gcp_starter_storage&#10;org.springframework.cloud:spring-cloud-gcp-starter-storage:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            gcp_starter_storage
        </span>
        - 
        <span
            title="Spring.cloud.gcp_starter&#10;org.springframework.cloud:spring-cloud-gcp-starter:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            gcp_starter
        </span>
        - 
        <span
            title="Spring.cloud.circuitbreakerReactorResilience4J&#10;org.springframework.cloud:spring-cloud-starter-circuitbreaker-reactor-resilience4j:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            circuitbreakerReactorResilience4J
        </span>
        - 
        <span
            title="Spring.cloud.config&#10;org.springframework.cloud:spring-cloud-starter-config:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            config
        </span>
        - 
        <span
            title="Spring.cloud.consulConfig&#10;org.springframework.cloud:spring-cloud-starter-consul-config:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            consulConfig
        </span>
        - 
        <span
            title="Spring.cloud.consulDiscovery&#10;org.springframework.cloud:spring-cloud-starter-consul-discovery:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            consulDiscovery
        </span>
        - 
        <span
            title="Spring.cloud.contractStubRunner&#10;org.springframework.cloud:spring-cloud-starter-contract-stub-runner:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            contractStubRunner
        </span>
        - 
        <span
            title="Spring.cloud.contractVerifier&#10;org.springframework.cloud:spring-cloud-starter-contract-verifier:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            contractVerifier
        </span>
        - 
        <span
            title="Spring.cloud.gateway&#10;org.springframework.cloud:spring-cloud-starter-gateway:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            gateway
        </span>
        - 
        <span
            title="Spring.cloud.loadbalancer&#10;org.springframework.cloud:spring-cloud-starter-loadbalancer:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            loadbalancer
        </span>
        - 
        <span
            title="Spring.cloud.oauth2&#10;org.springframework.cloud:spring-cloud-starter-oauth2:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            oauth2
        </span>
        - 
        <span
            title="Spring.cloud.openServiceBroker&#10;org.springframework.cloud:spring-cloud-starter-open-service-broker:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            openServiceBroker
        </span>
        - 
        <span
            title="Spring.cloud.openfeign&#10;org.springframework.cloud:spring-cloud-starter-openfeign:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            openfeign
        </span>
        - 
        <span
            title="Spring.cloud.security&#10;org.springframework.cloud:spring-cloud-starter-security:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            security
        </span>
        - 
        <span
            title="Spring.cloud.sleuth&#10;org.springframework.cloud:spring-cloud-starter-sleuth:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            sleuth
        </span>
        - 
        <span
            title="Spring.cloud.task&#10;org.springframework.cloud:spring-cloud-starter-task:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            task
        </span>
        - 
        <span
            title="Spring.cloud.vault_config&#10;org.springframework.cloud:spring-cloud-starter-vault-config:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            vault_config
        </span>
        - 
        <span
            title="Spring.cloud.zipkin&#10;org.springframework.cloud:spring-cloud-starter-zipkin:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            zipkin
        </span>
        - 
        <span
            title="Spring.cloud.zookeeperConfig&#10;org.springframework.cloud:spring-cloud-starter-zookeeper-config:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            zookeeperConfig
        </span>
        - 
        <span
            title="Spring.cloud.zookeeperDiscovery&#10;org.springframework.cloud:spring-cloud-starter-zookeeper-discovery:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            zookeeperDiscovery
        </span>
        - 
        <span
            title="Spring.cloud.starter&#10;org.springframework.cloud:spring-cloud-starter:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            starter
        </span>
        - 
        <span
            title="Spring.cloud.streamBinderRabbit&#10;org.springframework.cloud:spring-cloud-stream-binder-rabbit:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            streamBinderRabbit
        </span>
            
    </td></tr>
            
    <tr><td><b>Spring.cloud.aws</b></td><td>
        <span
            title="Spring.cloud.aws.jdbc&#10;org.springframework.cloud:spring-cloud-starter-aws-jdbc:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            jdbc
        </span>
        - 
        <span
            title="Spring.cloud.aws.messaging&#10;org.springframework.cloud:spring-cloud-starter-aws-messaging:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            messaging
        </span>
        - 
        <span
            title="Spring.cloud.aws.aws&#10;org.springframework.cloud:spring-cloud-starter-aws:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            aws
        </span>
            
    </td></tr>
            
    <tr><td><b>Spring.cloud.netflix</b></td><td>
        <span
            title="Spring.cloud.netflix.eurekaClient&#10;org.springframework.cloud:spring-cloud-starter-netflix-eureka-client:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            eurekaClient
        </span>
        - 
        <span
            title="Spring.cloud.netflix.eurekaServer&#10;org.springframework.cloud:spring-cloud-starter-netflix-eureka-server:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            eurekaServer
        </span>
        - 
        <span
            title="Spring.cloud.netflix.hystrixDashboard&#10;org.springframework.cloud:spring-cloud-starter-netflix-hystrix-dashboard:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            hystrixDashboard
        </span>
        - 
        <span
            title="Spring.cloud.netflix.hystrix&#10;org.springframework.cloud:spring-cloud-starter-netflix-hystrix:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            hystrix
        </span>
        - 
        <span
            title="Spring.cloud.netflix.ribbon&#10;org.springframework.cloud:spring-cloud-starter-netflix-ribbon:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            ribbon
        </span>
        - 
        <span
            title="Spring.cloud.netflix.turbineStream&#10;org.springframework.cloud:spring-cloud-starter-netflix-turbine-stream:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            turbineStream
        </span>
        - 
        <span
            title="Spring.cloud.netflix.turbine&#10;org.springframework.cloud:spring-cloud-starter-netflix-turbine:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            turbine
        </span>
        - 
        <span
            title="Spring.cloud.netflix.zuul&#10;org.springframework.cloud:spring-cloud-starter-netflix-zuul:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            zuul
        </span>
            
    </td></tr>
            
    <tr><td><b>Spring.cloud.stream</b></td><td>
        <span
            title="Spring.cloud.stream.binderKafkaStreams&#10;org.springframework.cloud:spring-cloud-stream-binder-kafka-streams:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            binderKafkaStreams
        </span>
        - 
        <span
            title="Spring.cloud.stream.binderKafka&#10;org.springframework.cloud:spring-cloud-stream-binder-kafka:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            binderKafka
        </span>
        - 
        <span
            title="Spring.cloud.stream.stream&#10;org.springframework.cloud:spring-cloud-stream:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            stream
        </span>
            
    </td></tr>
            
    <tr><td><b>Spring.data</b></td><td>
        <span
            title="Spring.data.halExplorer&#10;org.springframework.data:spring-data-rest-hal-explorer:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            halExplorer
        </span>
            
    </td></tr>
            
    <tr><td><b>Spring.integration</b></td><td>
        <span
            title="Spring.integration.amqp&#10;org.springframework.integration:spring-integration-amqp:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            amqp
        </span>
        - 
        <span
            title="Spring.integration.gemfire&#10;org.springframework.integration:spring-integration-gemfire:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            gemfire
        </span>
        - 
        <span
            title="Spring.integration.jdbc&#10;org.springframework.integration:spring-integration-jdbc:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            jdbc
        </span>
        - 
        <span
            title="Spring.integration.jms&#10;org.springframework.integration:spring-integration-jms:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            jms
        </span>
        - 
        <span
            title="Spring.integration.jpa&#10;org.springframework.integration:spring-integration-jpa:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            jpa
        </span>
        - 
        <span
            title="Spring.integration.kafka&#10;org.springframework.integration:spring-integration-kafka:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            kafka
        </span>
        - 
        <span
            title="Spring.integration.mail&#10;org.springframework.integration:spring-integration-mail:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            mail
        </span>
        - 
        <span
            title="Spring.integration.mongodb&#10;org.springframework.integration:spring-integration-mongodb:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            mongodb
        </span>
        - 
        <span
            title="Spring.integration.r2dbc&#10;org.springframework.integration:spring-integration-r2dbc:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            r2dbc
        </span>
        - 
        <span
            title="Spring.integration.redis&#10;org.springframework.integration:spring-integration-redis:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            redis
        </span>
        - 
        <span
            title="Spring.integration.rsocket&#10;org.springframework.integration:spring-integration-rsocket:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            rsocket
        </span>
        - 
        <span
            title="Spring.integration.security&#10;org.springframework.integration:spring-integration-security:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            security
        </span>
        - 
        <span
            title="Spring.integration.stomp&#10;org.springframework.integration:spring-integration-stomp:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            stomp
        </span>
        - 
        <span
            title="Spring.integration.test&#10;org.springframework.integration:spring-integration-test:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            test
        </span>
        - 
        <span
            title="Spring.integration.webflux&#10;org.springframework.integration:spring-integration-webflux:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            webflux
        </span>
        - 
        <span
            title="Spring.integration.websocket&#10;org.springframework.integration:spring-integration-websocket:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            websocket
        </span>
        - 
        <span
            title="Spring.integration.ws&#10;org.springframework.integration:spring-integration-ws:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            ws
        </span>
            
    </td></tr>
            
    <tr><td><b>Spring.reactor</b></td><td>
        <span
            title="Spring.reactor.test&#10;io.projectreactor:reactor-test:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            test
        </span>
        - 
        <span
            title="Spring.reactor.kotlin&#10;io.projectreactor.kotlin:reactor-kotlin-extensions:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            kotlin
        </span>
            
    </td></tr>
            
    <tr><td><b>Spring.security</b></td><td>
        <span
            title="Spring.security.spring_security_messaging&#10;org.springframework.security:spring-security-messaging:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            spring_security_messaging
        </span>
        - 
        <span
            title="Spring.security.spring_security_rsocket&#10;org.springframework.security:spring-security-rsocket:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            spring_security_rsocket
        </span>
        - 
        <span
            title="Spring.security.spring_security_test&#10;org.springframework.security:spring-security-test:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            spring_security_test
        </span>
            
    </td></tr>
            
    <tr><td><b>Spring.session</b></td><td>
        <span
            title="Spring.session.dataRedis&#10;org.springframework.session:spring-session-data-redis:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            dataRedis
        </span>
        - 
        <span
            title="Spring.session.jdbc&#10;org.springframework.session:spring-session-jdbc:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            jdbc
        </span>
            
    </td></tr>
            
    <tr><td><b>Spring.springCloud</b></td><td>
        <span
            title="Spring.springCloud.circuitBreaker&#10;io.pivotal.spring.cloud:spring-cloud-services-starter-circuit-breaker:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            circuitBreaker
        </span>
        - 
        <span
            title="Spring.springCloud.configClient&#10;io.pivotal.spring.cloud:spring-cloud-services-starter-config-client:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            configClient
        </span>
        - 
        <span
            title="Spring.springCloud.serviceRegistry&#10;io.pivotal.spring.cloud:spring-cloud-services-starter-service-registry:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            serviceRegistry
        </span>
            
    </td></tr>
            
</table>
            

## [Square.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Square.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>Square</b></td><td>
        <span
            title="Square.kotlinPoet&#10;com.squareup:kotlinpoet:_&#10;version.kotlinpoet"
            style="text-decoration: underline;">
            kotlinPoet
        </span>
        - 
        <span
            title="Square.logcat&#10;com.squareup.logcat:logcat:_&#10;version.logcat"
            style="text-decoration: underline;">
            logcat
        </span>
        - 
        <span
            title="Square.moshi&#10;com.squareup.moshi:moshi:_&#10;version.moshi"
            style="text-decoration: underline;">
            moshi
        </span>
        - 
        <span
            title="Square.okHttp3&#10;com.squareup.okhttp3:okhttp:_&#10;version.okhttp3"
            style="text-decoration: underline;">
            okHttp3
        </span>
        - 
        <span
            title="Square.okio&#10;com.squareup.okio:okio:_&#10;version.okio"
            style="text-decoration: underline;">
            okio
        </span>
        - 
        <span
            title="Square.picasso&#10;com.squareup.picasso:picasso:_&#10;version.picasso"
            style="text-decoration: underline;">
            picasso
        </span>
        - 
        <span
            title="Square.retrofit2&#10;com.squareup.retrofit2:retrofit:_&#10;version.retrofit2"
            style="text-decoration: underline;">
            retrofit2
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.kotlinPoet</b></td><td>
        <span
            title="Square.kotlinPoet.metadataSpecs&#10;com.squareup:kotlinpoet-metadata-specs:_&#10;version.kotlinpoet"
            style="text-decoration: underline;">
            metadataSpecs
        </span>
        - 
        <span
            title="Square.kotlinPoet.metadata&#10;com.squareup:kotlinpoet-metadata:_&#10;version.kotlinpoet"
            style="text-decoration: underline;">
            metadata
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.leakCanary</b></td><td>
        <span
            title="Square.leakCanary.androidInstrumentation&#10;com.squareup.leakcanary:leakcanary-android-instrumentation:_&#10;version.leakcanary"
            style="text-decoration: underline;">
            androidInstrumentation
        </span>
        - 
        <span
            title="Square.leakCanary.androidProcess&#10;com.squareup.leakcanary:leakcanary-android-process:_&#10;version.leakcanary"
            style="text-decoration: underline;">
            androidProcess
        </span>
        - 
        <span
            title="Square.leakCanary.android&#10;com.squareup.leakcanary:leakcanary-android:_&#10;version.leakcanary"
            style="text-decoration: underline;">
            android
        </span>
        - 
        <span
            title="Square.leakCanary.deobfuscationGradlePlugin&#10;com.squareup.leakcanary:leakcanary-deobfuscation-gradle-plugin:_&#10;version.leakcanary"
            style="text-decoration: underline;">
            deobfuscationGradlePlugin
        </span>
        - 
        <span
            title="Square.leakCanary.objectWatcher&#10;com.squareup.leakcanary:leakcanary-object-watcher:_&#10;version.leakcanary"
            style="text-decoration: underline;">
            objectWatcher
        </span>
        - 
        <span
            title="Square.leakCanary.plumber&#10;com.squareup.leakcanary:plumber-android:_&#10;version.leakcanary"
            style="text-decoration: underline;">
            plumber
        </span>
        - 
        <span
            title="Square.leakCanary.shark&#10;com.squareup.leakcanary:shark:_&#10;version.leakcanary"
            style="text-decoration: underline;">
            shark
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.leakCanary.objectWatcher</b></td><td>
        <span
            title="Square.leakCanary.objectWatcher.android&#10;com.squareup.leakcanary:leakcanary-object-watcher-android:_&#10;version.leakcanary"
            style="text-decoration: underline;">
            android
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.leakCanary.shark</b></td><td>
        <span
            title="Square.leakCanary.shark.android&#10;com.squareup.leakcanary:shark-android:_&#10;version.leakcanary"
            style="text-decoration: underline;">
            android
        </span>
        - 
        <span
            title="Square.leakCanary.shark.cli&#10;com.squareup.leakcanary:shark-cli:_&#10;version.leakcanary"
            style="text-decoration: underline;">
            cli
        </span>
        - 
        <span
            title="Square.leakCanary.shark.graph&#10;com.squareup.leakcanary:shark-graph:_&#10;version.leakcanary"
            style="text-decoration: underline;">
            graph
        </span>
        - 
        <span
            title="Square.leakCanary.shark.hprof&#10;com.squareup.leakcanary:shark-hprof:_&#10;version.leakcanary"
            style="text-decoration: underline;">
            hprof
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.moshi</b></td><td>
        <span
            title="Square.moshi.adapters&#10;com.squareup.moshi:moshi-adapters:_&#10;version.moshi"
            style="text-decoration: underline;">
            adapters
        </span>
        - 
        <span
            title="Square.moshi.kotlinCodegen&#10;com.squareup.moshi:moshi-kotlin-codegen:_&#10;version.moshi"
            style="text-decoration: underline;">
            kotlinCodegen
        </span>
        - 
        <span
            title="Square.moshi.kotlinReflect&#10;com.squareup.moshi:moshi-kotlin:_&#10;version.moshi"
            style="text-decoration: underline;">
            kotlinReflect
        </span>
        - 
        <span
            title="Square.moshi.javaReflect&#10;com.squareup.moshi:moshi:_&#10;version.moshi"
            style="text-decoration: underline;">
            javaReflect
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.okHttp3</b></td><td>
        <span
            title="Square.okHttp3.loggingInterceptor&#10;com.squareup.okhttp3:logging-interceptor:_&#10;version.okhttp3"
            style="text-decoration: underline;">
            loggingInterceptor
        </span>
        - 
        <span
            title="Square.okHttp3.mockWebServer3&#10;com.squareup.okhttp3:mockwebserver3:_&#10;version.okhttp3"
            style="text-decoration: underline;">
            mockWebServer3
        </span>
        - 
        <span
            title="Square.okHttp3.mockWebServer&#10;com.squareup.okhttp3:mockwebserver:_&#10;version.okhttp3"
            style="text-decoration: underline;">
            mockWebServer
        </span>
        - 
        <span
            title="Square.okHttp3.android&#10;com.squareup.okhttp3:okhttp-android:_&#10;version.okhttp3"
            style="text-decoration: underline;">
            android
        </span>
        - 
        <span
            title="Square.okHttp3.bom&#10;com.squareup.okhttp3:okhttp-bom:_&#10;version.okhttp3"
            style="text-decoration: underline;">
            bom
        </span>
        - 
        <span
            title="Square.okHttp3.brotli&#10;com.squareup.okhttp3:okhttp-brotli:_&#10;version.okhttp3"
            style="text-decoration: underline;">
            brotli
        </span>
        - 
        <span
            title="Square.okHttp3.coroutines&#10;com.squareup.okhttp3:okhttp-coroutines:_&#10;version.okhttp3"
            style="text-decoration: underline;">
            coroutines
        </span>
        - 
        <span
            title="Square.okHttp3.dnsOverHttps&#10;com.squareup.okhttp3:okhttp-dnsoverhttps:_&#10;version.okhttp3"
            style="text-decoration: underline;">
            dnsOverHttps
        </span>
        - 
        <span
            title="Square.okHttp3.sse&#10;com.squareup.okhttp3:okhttp-sse:_&#10;version.okhttp3"
            style="text-decoration: underline;">
            sse
        </span>
        - 
        <span
            title="Square.okHttp3.tls&#10;com.squareup.okhttp3:okhttp-tls:_&#10;version.okhttp3"
            style="text-decoration: underline;">
            tls
        </span>
        - 
        <span
            title="Square.okHttp3.urlConnection&#10;com.squareup.okhttp3:okhttp-urlconnection:_&#10;version.okhttp3"
            style="text-decoration: underline;">
            urlConnection
        </span>
        - 
        <span
            title="Square.okHttp3.okHttp&#10;com.squareup.okhttp3:okhttp:_&#10;version.okhttp3"
            style="text-decoration: underline;">
            okHttp
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.okHttp3.mockWebServer3</b></td><td>
        <span
            title="Square.okHttp3.mockWebServer3.junit4&#10;com.squareup.okhttp3:mockwebserver3-junit4:_&#10;version.okhttp3"
            style="text-decoration: underline;">
            junit4
        </span>
        - 
        <span
            title="Square.okHttp3.mockWebServer3.junit5&#10;com.squareup.okhttp3:mockwebserver3-junit5:_&#10;version.okhttp3"
            style="text-decoration: underline;">
            junit5
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.picasso</b></td><td>
        <span
            title="Square.picasso.pollexor&#10;com.squareup.picasso:picasso-pollexor:_&#10;version.picasso"
            style="text-decoration: underline;">
            pollexor
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.retrofit2</b></td><td>
        <span
            title="Square.retrofit2.mock&#10;com.squareup.retrofit2:retrofit-mock:_&#10;version.retrofit2"
            style="text-decoration: underline;">
            mock
        </span>
        - 
        <span
            title="Square.retrofit2.retrofit&#10;com.squareup.retrofit2:retrofit:_&#10;version.retrofit2"
            style="text-decoration: underline;">
            retrofit
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.retrofit2.adapter</b></td><td>
        <span
            title="Square.retrofit2.adapter.java8&#10;com.squareup.retrofit2:adapter-java8:_&#10;version.retrofit2"
            style="text-decoration: underline;">
            java8
        </span>
        - 
        <span
            title="Square.retrofit2.adapter.rxJava2&#10;com.squareup.retrofit2:adapter-rxjava2:_&#10;version.retrofit2"
            style="text-decoration: underline;">
            rxJava2
        </span>
        - 
        <span
            title="Square.retrofit2.adapter.rxJava3&#10;com.squareup.retrofit2:adapter-rxjava3:_&#10;version.retrofit2"
            style="text-decoration: underline;">
            rxJava3
        </span>
        - 
        <span
            title="Square.retrofit2.adapter.rxJava1&#10;com.squareup.retrofit2:adapter-rxjava:_&#10;version.retrofit2"
            style="text-decoration: underline;">
            rxJava1
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.retrofit2.converter</b></td><td>
        <span
            title="Square.retrofit2.converter.gson&#10;com.squareup.retrofit2:converter-gson:_&#10;version.retrofit2"
            style="text-decoration: underline;">
            gson
        </span>
        - 
        <span
            title="Square.retrofit2.converter.jackson&#10;com.squareup.retrofit2:converter-jackson:_&#10;version.retrofit2"
            style="text-decoration: underline;">
            jackson
        </span>
        - 
        <span
            title="Square.retrofit2.converter.moshi&#10;com.squareup.retrofit2:converter-moshi:_&#10;version.retrofit2"
            style="text-decoration: underline;">
            moshi
        </span>
        - 
        <span
            title="Square.retrofit2.converter.scalars&#10;com.squareup.retrofit2:converter-scalars:_&#10;version.retrofit2"
            style="text-decoration: underline;">
            scalars
        </span>
        - 
        <span
            title="Square.retrofit2.converter.simpleXml&#10;com.squareup.retrofit2:converter-simplexml:_&#10;version.retrofit2"
            style="text-decoration: underline;">
            simpleXml
        </span>
        - 
        <span
            title="Square.retrofit2.converter.wire&#10;com.squareup.retrofit2:converter-wire:_&#10;version.retrofit2"
            style="text-decoration: underline;">
            wire
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.sqlDelight</b></td><td>
        <span
            title="Square.sqlDelight.gradlePlugin&#10;com.squareup.sqldelight:gradle-plugin:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            gradlePlugin
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.sqlDelight.drivers</b></td><td>
        <span
            title="Square.sqlDelight.drivers.android&#10;com.squareup.sqldelight:android-driver:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            android
        </span>
        - 
        <span
            title="Square.sqlDelight.drivers.jdbc&#10;com.squareup.sqldelight:jdbc-driver:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            jdbc
        </span>
        - 
        <span
            title="Square.sqlDelight.drivers.native&#10;com.squareup.sqldelight:native-driver:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            native
        </span>
        - 
        <span
            title="Square.sqlDelight.drivers.jdbcSqlite&#10;com.squareup.sqldelight:sqlite-driver:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            jdbcSqlite
        </span>
        - 
        <span
            title="Square.sqlDelight.drivers.sqlJs&#10;com.squareup.sqldelight:sqljs-driver:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            sqlJs
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.sqlDelight.extensions</b></td><td>
        <span
            title="Square.sqlDelight.extensions.androidPaging&#10;com.squareup.sqldelight:android-paging-extensions:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            androidPaging
        </span>
        - 
        <span
            title="Square.sqlDelight.extensions.androidPaging3&#10;com.squareup.sqldelight:android-paging3-extensions:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            androidPaging3
        </span>
        - 
        <span
            title="Square.sqlDelight.extensions.coroutines&#10;com.squareup.sqldelight:coroutines-extensions:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            coroutines
        </span>
        - 
        <span
            title="Square.sqlDelight.extensions.rxJava2&#10;com.squareup.sqldelight:rxjava2-extensions:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            rxJava2
        </span>
        - 
        <span
            title="Square.sqlDelight.extensions.rxJava3&#10;com.squareup.sqldelight:rxjava3-extensions:_&#10;version.sqldelight"
            style="text-decoration: underline;">
            rxJava3
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.wire</b></td><td>
        <span
            title="Square.wire.gradlePlugin&#10;com.squareup.wire:wire-gradle-plugin:_&#10;version.wire"
            style="text-decoration: underline;">
            gradlePlugin
        </span>
        - 
        <span
            title="Square.wire.runtime&#10;com.squareup.wire:wire-runtime:_&#10;version.wire"
            style="text-decoration: underline;">
            runtime
        </span>
            
    </td></tr>
            
    <tr><td><b>Square.wire.grpc</b></td><td>
        <span
            title="Square.wire.grpc.client&#10;com.squareup.wire:wire-grpc-client:_&#10;version.wire"
            style="text-decoration: underline;">
            client
        </span>
            
    </td></tr>
            
</table>
            

## [Testing.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Testing.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>Testing</b></td><td>
        <span
            title="Testing.mockK&#10;io.mockk:mockk:_&#10;version.mockk"
            style="text-decoration: underline;">
            mockK
        </span>
        - 
        <span
            title="Testing.junit4&#10;junit:junit:_&#10;version.junit.junit"
            style="text-decoration: underline;">
            junit4
        </span>
        - 
        <span
            title="Testing.hamcrest&#10;org.hamcrest:hamcrest:_&#10;version.hamcrest"
            style="text-decoration: underline;">
            hamcrest
        </span>
        - 
        <span
            title="Testing.robolectric&#10;org.robolectric:robolectric:_&#10;version.robolectric"
            style="text-decoration: underline;">
            robolectric
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.assertj</b></td><td>
        <span
            title="Testing.assertj.core&#10;org.assertj:assertj-core:_&#10;version.assertj.core"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="Testing.assertj.db&#10;org.assertj:assertj-db:_&#10;version.assertj.db"
            style="text-decoration: underline;">
            db
        </span>
        - 
        <span
            title="Testing.assertj.guava&#10;org.assertj:assertj-guava:_&#10;version.assertj.guava"
            style="text-decoration: underline;">
            guava
        </span>
        - 
        <span
            title="Testing.assertj.jodaTime&#10;org.assertj:assertj-joda-time:_&#10;version.assertj.joda-time"
            style="text-decoration: underline;">
            jodaTime
        </span>
        - 
        <span
            title="Testing.assertj.swing&#10;org.assertj:assertj-swing:_&#10;version.assertj.swing"
            style="text-decoration: underline;">
            swing
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.hamcrest</b></td><td>
        <span
            title="Testing.hamcrest.core&#10;org.hamcrest:hamcrest-core:_&#10;version.hamcrest"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="Testing.hamcrest.library&#10;org.hamcrest:hamcrest-library:_&#10;version.hamcrest"
            style="text-decoration: underline;">
            library
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.junit</b></td><td>
        <span
            title="Testing.junit.bom&#10;org.junit:junit-bom:_&#10;version.junit"
            style="text-decoration: underline;">
            bom
        </span>
        - 
        <span
            title="Testing.junit.jupiter&#10;org.junit.jupiter:junit-jupiter:_&#10;version.junit.jupiter"
            style="text-decoration: underline;">
            jupiter
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.junit.jupiter</b></td><td>
        <span
            title="Testing.junit.jupiter.api&#10;org.junit.jupiter:junit-jupiter-api:_&#10;version.junit.jupiter"
            style="text-decoration: underline;">
            api
        </span>
        - 
        <span
            title="Testing.junit.jupiter.engine&#10;org.junit.jupiter:junit-jupiter-engine:_&#10;version.junit.jupiter"
            style="text-decoration: underline;">
            engine
        </span>
        - 
        <span
            title="Testing.junit.jupiter.migrationSupport&#10;org.junit.jupiter:junit-jupiter-migrationsupport:_&#10;version.junit.jupiter"
            style="text-decoration: underline;">
            migrationSupport
        </span>
        - 
        <span
            title="Testing.junit.jupiter.params&#10;org.junit.jupiter:junit-jupiter-params:_&#10;version.junit.jupiter"
            style="text-decoration: underline;">
            params
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.kotest</b></td><td>
        <span
            title="Testing.kotest.core&#10;io.kotest:kotest-core:_&#10;version.kotest"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="Testing.kotest.propertyArrow&#10;io.kotest:kotest-property-arrow:_&#10;version.kotest"
            style="text-decoration: underline;">
            propertyArrow
        </span>
        - 
        <span
            title="Testing.kotest.property&#10;io.kotest:kotest-property:_&#10;version.kotest"
            style="text-decoration: underline;">
            property
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.kotest.assertions</b></td><td>
        <span
            title="Testing.kotest.assertions.arrow&#10;io.kotest:kotest-assertions-arrow:_&#10;version.kotest"
            style="text-decoration: underline;">
            arrow
        </span>
        - 
        <span
            title="Testing.kotest.assertions.compiler&#10;io.kotest:kotest-assertions-compiler:_&#10;version.kotest"
            style="text-decoration: underline;">
            compiler
        </span>
        - 
        <span
            title="Testing.kotest.assertions.core&#10;io.kotest:kotest-assertions-core:_&#10;version.kotest"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="Testing.kotest.assertions.json&#10;io.kotest:kotest-assertions-json:_&#10;version.kotest"
            style="text-decoration: underline;">
            json
        </span>
        - 
        <span
            title="Testing.kotest.assertions.jsoup&#10;io.kotest:kotest-assertions-jsoup:_&#10;version.kotest"
            style="text-decoration: underline;">
            jsoup
        </span>
        - 
        <span
            title="Testing.kotest.assertions.klock&#10;io.kotest:kotest-assertions-klock:_&#10;version.kotest"
            style="text-decoration: underline;">
            klock
        </span>
        - 
        <span
            title="Testing.kotest.assertions.konform&#10;io.kotest:kotest-assertions-konform:_&#10;version.kotest"
            style="text-decoration: underline;">
            konform
        </span>
        - 
        <span
            title="Testing.kotest.assertions.kotlinxDateTime&#10;io.kotest:kotest-assertions-kotlinx-time:_&#10;version.kotest"
            style="text-decoration: underline;">
            kotlinxDateTime
        </span>
        - 
        <span
            title="Testing.kotest.assertions.ktor&#10;io.kotest:kotest-assertions-ktor:_&#10;version.kotest"
            style="text-decoration: underline;">
            ktor
        </span>
        - 
        <span
            title="Testing.kotest.assertions.sql&#10;io.kotest:kotest-assertions-sql:_&#10;version.kotest"
            style="text-decoration: underline;">
            sql
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.kotest.extensions</b></td><td>
        <span
            title="Testing.kotest.extensions.allure&#10;io.kotest.extensions:kotest-extensions-allure:_&#10;version.kotest.extensions.allure"
            style="text-decoration: underline;">
            allure
        </span>
        - 
        <span
            title="Testing.kotest.extensions.embeddedKafka&#10;io.kotest.extensions:kotest-extensions-embedded-kafka:_&#10;version.kotest.extensions.embedded-kafka"
            style="text-decoration: underline;">
            embeddedKafka
        </span>
        - 
        <span
            title="Testing.kotest.extensions.gherkin&#10;io.kotest.extensions:kotest-extensions-gherkin:_&#10;version.kotest.extensions.gherkin"
            style="text-decoration: underline;">
            gherkin
        </span>
        - 
        <span
            title="Testing.kotest.extensions.koin&#10;io.kotest.extensions:kotest-extensions-koin:_&#10;version.kotest.extensions.koin"
            style="text-decoration: underline;">
            koin
        </span>
        - 
        <span
            title="Testing.kotest.extensions.mockServer&#10;io.kotest.extensions:kotest-extensions-mockserver:_&#10;version.kotest.extensions.mockserver"
            style="text-decoration: underline;">
            mockServer
        </span>
        - 
        <span
            title="Testing.kotest.extensions.pitest&#10;io.kotest.extensions:kotest-extensions-pitest:_&#10;version.kotest.extensions.pitest"
            style="text-decoration: underline;">
            pitest
        </span>
        - 
        <span
            title="Testing.kotest.extensions.robolectric&#10;io.kotest.extensions:kotest-extensions-robolectric:_&#10;version.kotest.extensions.robolectric"
            style="text-decoration: underline;">
            robolectric
        </span>
        - 
        <span
            title="Testing.kotest.extensions.spring&#10;io.kotest.extensions:kotest-extensions-spring:_&#10;version.kotest.extensions.spring"
            style="text-decoration: underline;">
            spring
        </span>
        - 
        <span
            title="Testing.kotest.extensions.testContainers&#10;io.kotest.extensions:kotest-extensions-testcontainers:_&#10;version.kotest.extensions.testcontainers"
            style="text-decoration: underline;">
            testContainers
        </span>
        - 
        <span
            title="Testing.kotest.extensions.wiremock&#10;io.kotest.extensions:kotest-extensions-wiremock:_&#10;version.kotest.extensions.wiremock"
            style="text-decoration: underline;">
            wiremock
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.kotest.extensions.property</b></td><td>
        <span
            title="Testing.kotest.extensions.property.arbs&#10;io.kotest.extensions:kotest-property-arbs:_&#10;version.kotest.extensions.property-arbs"
            style="text-decoration: underline;">
            arbs
        </span>
        - 
        <span
            title="Testing.kotest.extensions.property.datetime&#10;io.kotest.extensions:kotest-property-datetime:_&#10;version.kotest.extensions.property-datetime"
            style="text-decoration: underline;">
            datetime
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.kotest.framework</b></td><td>
        <span
            title="Testing.kotest.framework.api&#10;io.kotest:kotest-framework-api:_&#10;version.kotest"
            style="text-decoration: underline;">
            api
        </span>
        - 
        <span
            title="Testing.kotest.framework.datatest&#10;io.kotest:kotest-framework-datatest:_&#10;version.kotest"
            style="text-decoration: underline;">
            datatest
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.kotest.plugins</b></td><td>
        <span
            title="Testing.kotest.plugins.piTest&#10;io.kotest:kotest-plugins-pitest:_&#10;version.kotest"
            style="text-decoration: underline;">
            piTest
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.kotest.runner</b></td><td>
        <span
            title="Testing.kotest.runner.junit4&#10;io.kotest:kotest-runner-junit4:_&#10;version.kotest"
            style="text-decoration: underline;">
            junit4
        </span>
        - 
        <span
            title="Testing.kotest.runner.junit5&#10;io.kotest:kotest-runner-junit5:_&#10;version.kotest"
            style="text-decoration: underline;">
            junit5
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.mockK</b></td><td>
        <span
            title="Testing.mockK.android&#10;io.mockk:mockk-android:_&#10;version.mockk"
            style="text-decoration: underline;">
            android
        </span>
        - 
        <span
            title="Testing.mockK.common&#10;io.mockk:mockk-common:_&#10;version.mockk"
            style="text-decoration: underline;">
            common
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.mockito</b></td><td>
        <span
            title="Testing.mockito.kotlin&#10;com.nhaarman.mockitokotlin2:mockito-kotlin:_&#10;version.NO-RULE"
            style="text-decoration: underline;">
            kotlin
        </span>
        - 
        <span
            title="Testing.mockito.android&#10;org.mockito:mockito-android:_&#10;version.mockito"
            style="text-decoration: underline;">
            android
        </span>
        - 
        <span
            title="Testing.mockito.core&#10;org.mockito:mockito-core:_&#10;version.mockito"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="Testing.mockito.errorProne&#10;org.mockito:mockito-errorprone:_&#10;version.mockito"
            style="text-decoration: underline;">
            errorProne
        </span>
        - 
        <span
            title="Testing.mockito.inline&#10;org.mockito:mockito-inline:_&#10;version.mockito"
            style="text-decoration: underline;">
            inline
        </span>
        - 
        <span
            title="Testing.mockito.junitJupiter&#10;org.mockito:mockito-junit-jupiter:_&#10;version.mockito"
            style="text-decoration: underline;">
            junitJupiter
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.spek.dsl</b></td><td>
        <span
            title="Testing.spek.dsl.js&#10;org.spekframework.spek2:spek-dsl-js:_&#10;version.spek"
            style="text-decoration: underline;">
            js
        </span>
        - 
        <span
            title="Testing.spek.dsl.jvm&#10;org.spekframework.spek2:spek-dsl-jvm:_&#10;version.spek"
            style="text-decoration: underline;">
            jvm
        </span>
        - 
        <span
            title="Testing.spek.dsl.metadata&#10;org.spekframework.spek2:spek-dsl-metadata:_&#10;version.spek"
            style="text-decoration: underline;">
            metadata
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.spek.dsl.native</b></td><td>
        <span
            title="Testing.spek.dsl.native.linux&#10;org.spekframework.spek2:spek-dsl-native-linux:_&#10;version.spek"
            style="text-decoration: underline;">
            linux
        </span>
        - 
        <span
            title="Testing.spek.dsl.native.macos&#10;org.spekframework.spek2:spek-dsl-native-macos:_&#10;version.spek"
            style="text-decoration: underline;">
            macos
        </span>
        - 
        <span
            title="Testing.spek.dsl.native.windows&#10;org.spekframework.spek2:spek-dsl-native-windows:_&#10;version.spek"
            style="text-decoration: underline;">
            windows
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.spek.runner</b></td><td>
        <span
            title="Testing.spek.runner.junit5&#10;org.spekframework.spek2:spek-runner-junit5:_&#10;version.spek"
            style="text-decoration: underline;">
            junit5
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.spek.runtime</b></td><td>
        <span
            title="Testing.spek.runtime.jvm&#10;org.spekframework.spek2:spek-runtime-jvm:_&#10;version.spek"
            style="text-decoration: underline;">
            jvm
        </span>
        - 
        <span
            title="Testing.spek.runtime.metadata&#10;org.spekframework.spek2:spek-runtime-metadata:_&#10;version.spek"
            style="text-decoration: underline;">
            metadata
        </span>
            
    </td></tr>
            
    <tr><td><b>Testing.strikt</b></td><td>
        <span
            title="Testing.strikt.arrow&#10;io.strikt:strikt-arrow:_&#10;version.strikt"
            style="text-decoration: underline;">
            arrow
        </span>
        - 
        <span
            title="Testing.strikt.bom&#10;io.strikt:strikt-bom:_&#10;version.strikt"
            style="text-decoration: underline;">
            bom
        </span>
        - 
        <span
            title="Testing.strikt.core&#10;io.strikt:strikt-core:_&#10;version.strikt"
            style="text-decoration: underline;">
            core
        </span>
        - 
        <span
            title="Testing.strikt.gradle&#10;io.strikt:strikt-gradle:_&#10;version.strikt"
            style="text-decoration: underline;">
            gradle
        </span>
        - 
        <span
            title="Testing.strikt.jackson&#10;io.strikt:strikt-jackson:_&#10;version.strikt"
            style="text-decoration: underline;">
            jackson
        </span>
        - 
        <span
            title="Testing.strikt.javaTime&#10;io.strikt:strikt-java-time:_&#10;version.strikt"
            style="text-decoration: underline;">
            javaTime
        </span>
        - 
        <span
            title="Testing.strikt.mockk&#10;io.strikt:strikt-mockk:_&#10;version.strikt"
            style="text-decoration: underline;">
            mockk
        </span>
        - 
        <span
            title="Testing.strikt.protobuf&#10;io.strikt:strikt-protobuf:_&#10;version.strikt"
            style="text-decoration: underline;">
            protobuf
        </span>
        - 
        <span
            title="Testing.strikt.spring&#10;io.strikt:strikt-spring:_&#10;version.strikt"
            style="text-decoration: underline;">
            spring
        </span>
            
    </td></tr>
            
</table>
            

## [Touchlab.kt](https://github.com/jmfayard/refreshVersions/blob/main/plugins/dependencies/src/main/kotlin/dependencies/Touchlab.kt)


<table style="width: 100%; table-layout:fixed;">
    <thead><tr><th>Group</th> <th>Dependency Notations</th></tr></thead>
    <tr><td><b>Touchlab</b></td><td>
        <span
            title="Touchlab.kermit&#10;co.touchlab:kermit:_&#10;version.kermit"
            style="text-decoration: underline;">
            kermit
        </span>
            
    </td></tr>
            
    <tr><td><b>Touchlab.kermit</b></td><td>
        <span
            title="Touchlab.kermit.bugsnagTest&#10;co.touchlab:kermit-bugsnag-test:_&#10;version.kermit"
            style="text-decoration: underline;">
            bugsnagTest
        </span>
        - 
        <span
            title="Touchlab.kermit.bugsnag&#10;co.touchlab:kermit-bugsnag:_&#10;version.kermit"
            style="text-decoration: underline;">
            bugsnag
        </span>
        - 
        <span
            title="Touchlab.kermit.crashlyticsTest&#10;co.touchlab:kermit-crashlytics-test:_&#10;version.kermit"
            style="text-decoration: underline;">
            crashlyticsTest
        </span>
        - 
        <span
            title="Touchlab.kermit.crashlytics&#10;co.touchlab:kermit-crashlytics:_&#10;version.kermit"
            style="text-decoration: underline;">
            crashlytics
        </span>
        - 
        <span
            title="Touchlab.kermit.gradlePlugin&#10;co.touchlab:kermit-gradle-plugin:_&#10;version.kermit"
            style="text-decoration: underline;">
            gradlePlugin
        </span>
        - 
        <span
            title="Touchlab.kermit.test&#10;co.touchlab:kermit-test:_&#10;version.kermit"
            style="text-decoration: underline;">
            test
        </span>
            
    </td></tr>
            
    <tr><td><b>Touchlab.stately</b></td><td>
        <span
            title="Touchlab.stately.common&#10;co.touchlab:stately-common:_&#10;version.stately"
            style="text-decoration: underline;">
            common
        </span>
        - 
        <span
            title="Touchlab.stately.concurrency&#10;co.touchlab:stately-concurrency:_&#10;version.stately"
            style="text-decoration: underline;">
            concurrency
        </span>
        - 
        <span
            title="Touchlab.stately.isoCollections&#10;co.touchlab:stately-iso-collections:_&#10;version.stately"
            style="text-decoration: underline;">
            isoCollections
        </span>
        - 
        <span
            title="Touchlab.stately.isolate&#10;co.touchlab:stately-isolate:_&#10;version.stately"
            style="text-decoration: underline;">
            isolate
        </span>
            
    </td></tr>
            
</table>
            

