package de.fayard.refreshVersions.rules

import AndroidX
import COIL
import KotlinX
import Ktor
import Orchid
import Splitties
import Testing
import de.fayard.refreshVersions.internal.getArtifactsFromDependenciesObject
import dependencies.DependencyNotationAndGroup
import org.gradle.api.artifacts.ModuleIdentifier

data class VersionKeyWithModules(
    val expectedVersionKey: String,
    val moduleIdentifiers: List<ModuleIdentifier>
) {
    init {
        require(expectedVersionKey.isNotBlank())
        require(moduleIdentifiers.isNotEmpty())
    }
}

private val kotlinX = listOf(
    versionKeyWithModules(expected = "kotlinx.coroutines", dependenciesObject = KotlinX.coroutines),
    versionKeyWithModules(expected = "kotlinx.serialization", dependenciesObject = KotlinX.serialization),
    versionKeyWithModules(expected = "kotlinx.collections.immutable", dependency = KotlinX.collections.immutable),
    versionKeyWithModules(expected = "kotlinx.collections.immutable", dependency = KotlinX.collections.immutableJvmOnly)
)

private val androidX = listOf(
    versionKeyWithModules(expected = "androidx.activity", dependenciesObject = AndroidX.activity),
    versionKeyWithModules(expected = "androidx.activity", dependency = AndroidX.activityKtx),
    versionKeyWithModules(expected = "androidx.annotation", dependency = AndroidX.annotation),
    versionKeyWithModules(expected = "androidx.annotation", dependency = AndroidX.annotationExperimental),
    versionKeyWithModules(expected = "androidx.appcompat", dependency = AndroidX.appCompat),
    versionKeyWithModules(expected = "androidx.appcompat", dependency = AndroidX.appCompatResources),
    versionKeyWithModules(expected = "androidx.appsearch", dependency = AndroidX.appSearch),
    versionKeyWithModules(expected = "androidx.arch.core", dependenciesObject = AndroidX.archCore),
    versionKeyWithModules(expected = "androidx.asynclayoutinflater", dependency = AndroidX.asyncLayoutInflater),
    versionKeyWithModules(expected = "androidx.autofill", dependency = AndroidX.autoFill),
    versionKeyWithModules(expected = "androidx.benchmark", dependenciesObject = AndroidX.benchmark),
    versionKeyWithModules(expected = "androidx.biometric", dependency = AndroidX.biometric),
    versionKeyWithModules(expected = "androidx.biometric-ktx", dependency = AndroidX.biometricKtx),
    versionKeyWithModules(expected = "androidx.browser", dependency = AndroidX.browser),
    versionKeyWithModules(expected = "androidx.camera.extensions", dependency = AndroidX.camera.extensions),
    versionKeyWithModules(expected = "androidx.camera.view", dependency = AndroidX.camera.view),
    versionKeyWithModules(expected = "androidx.camera", dependency = AndroidX.camera.camera2),
    versionKeyWithModules(expected = "androidx.camera", dependency = AndroidX.camera.core),
    versionKeyWithModules(expected = "androidx.car", dependency = AndroidX.car),
    versionKeyWithModules(expected = "androidx.car.app", dependency = AndroidX.carApp),
    versionKeyWithModules(expected = "androidx.car.app-testing", dependency = AndroidX.carApp.testing),
    versionKeyWithModules(expected = "androidx.car-cluster", dependency = AndroidX.carCluster),
    versionKeyWithModules(expected = "androidx.cardview", dependency = AndroidX.cardView),
    versionKeyWithModules(expected = "androidx.collection", dependency = AndroidX.collection),
    versionKeyWithModules(expected = "androidx.collection", dependency = AndroidX.collectionKtx),
    versionKeyWithModules(expected = "androidx.compose.animation", dependenciesObject = AndroidX.compose.animation),
    versionKeyWithModules(expected = "androidx.compose.compiler", dependency = AndroidX.compose.compiler),
    versionKeyWithModules(
        expected = "androidx.compose.foundation",
        dependenciesObject = AndroidX.compose.foundation,
        excludes = listOf(@Suppress("deprecation") AndroidX.compose.foundation.text)
    ),
    versionKeyWithModules(
        expected = "androidx.compose.foundation-text",
        dependency = @Suppress("deprecation") AndroidX.compose.foundation.text
    ),
    versionKeyWithModules(expected = "androidx.compose.runtime", dependenciesObject = AndroidX.compose.runtime),
    versionKeyWithModules(expected = "androidx.compose.ui", dependenciesObject = AndroidX.compose.ui),
    versionKeyWithModules(expected = "androidx.compose.material", dependenciesObject = AndroidX.compose.material),
    versionKeyWithModules(expected = "androidx.concurrent", dependenciesObject = AndroidX.concurrent),
    versionKeyWithModules(expected = "androidx.constraintlayout", dependency = AndroidX.constraintLayout),
    versionKeyWithModules(
        expected = "androidx.constraintlayout-compose",
        dependency = AndroidX.constraintLayoutCompose
    ),
    versionKeyWithModules(expected = "androidx.constraintlayout", dependency = AndroidX.constraintLayoutSolver),
    versionKeyWithModules(expected = "androidx.contentpager", dependency = AndroidX.contentPager),
    versionKeyWithModules(expected = "androidx.coordinatorlayout", dependency = AndroidX.coordinatorLayout),
    versionKeyWithModules(expected = "androidx.core", dependency = AndroidX.core),
    versionKeyWithModules(expected = "androidx.core", dependency = AndroidX.core.ktx),
    versionKeyWithModules(expected = "androidx.core-role", dependency = AndroidX.core.role),
    versionKeyWithModules(expected = "androidx.core-animation", dependency = AndroidX.core.animation),
    versionKeyWithModules(expected = "androidx.core-animation", dependency = AndroidX.core.animationTesting),
    versionKeyWithModules(expected = "androidx.core-google-shortcuts", dependency = AndroidX.core.googleShortcuts),
    versionKeyWithModules(expected = "androidx.cursoradapter", dependency = AndroidX.cursorAdapter),
    versionKeyWithModules(expected = "androidx.customview", dependency = AndroidX.customView),
    versionKeyWithModules(expected = "androidx.datastore", dependency = AndroidX.dataStore),
    versionKeyWithModules(expected = "androidx.documentfile", dependency = AndroidX.documentFile),
    versionKeyWithModules(expected = "androidx.drawerlayout", dependency = AndroidX.drawerLayout),
    versionKeyWithModules(expected = "androidx.dynamicanimation", dependency = AndroidX.dynamicAnimation),
    versionKeyWithModules(expected = "androidx.dynamicanimation-ktx", dependency = AndroidX.dynamicAnimationKtx),
    versionKeyWithModules(expected = "androidx.emoji", dependency = AndroidX.emoji),
    versionKeyWithModules(expected = "androidx.emoji", dependency = AndroidX.emojiAppCompat),
    versionKeyWithModules(expected = "androidx.emoji", dependency = AndroidX.emojiBundled),
    versionKeyWithModules(expected = "androidx.enterprise", dependenciesObject = AndroidX.enterprise),
    versionKeyWithModules(expected = "androidx.exifinterface", dependency = AndroidX.exifInterface),
    versionKeyWithModules(expected = "androidx.fragment", dependency = AndroidX.fragment),
    versionKeyWithModules(expected = "androidx.fragment", dependency = AndroidX.fragmentKtx),
    versionKeyWithModules(expected = "androidx.fragment", dependency = AndroidX.fragmentTesting),
    versionKeyWithModules(expected = "androidx.gridlayout", dependency = AndroidX.gridLayout),
    versionKeyWithModules(expected = "androidx.heifwriter", dependency = AndroidX.heifWriter),
    versionKeyWithModules(
        expected = "androidx.hilt",
        dependenciesObject = AndroidX.hilt,
        excludes = listOf(AndroidX.hilt.navigationCompose, @Suppress("deprecation") AndroidX.hilt.lifecycleViewModel)
    ),
    versionKeyWithModules(
        expected = "androidx.hilt-navigation-compose",
        dependency = AndroidX.hilt.navigationCompose
    ),
    versionKeyWithModules(
        expected = "androidx.hilt-lifecycle-viewmodel",
        dependency = @Suppress("deprecation") AndroidX.hilt.lifecycleViewModel
    ),
    versionKeyWithModules(expected = "androidx.interpolator", dependency = AndroidX.interpolator),
    versionKeyWithModules(expected = "androidx.leanback", dependency = AndroidX.leanback),
    versionKeyWithModules(
        expected = "androidx.leanback",
        dependency = @Suppress("deprecation") AndroidX.leanbackPreference
    ),
    versionKeyWithModules(
        expected = "androidx.leanback",
        dependenciesObject = AndroidX.leanback,
        excludes = listOf(AndroidX.leanback.paging, AndroidX.leanback.tab)
    ),
    versionKeyWithModules(
        expected = "androidx.leanback-paging",
        dependency = AndroidX.leanback.paging
    ),
    versionKeyWithModules(
        expected = "androidx.leanback-tab",
        dependency = AndroidX.leanback.tab
    ),
    versionKeyWithModules(expected = "androidx.legacy", dependenciesObject = AndroidX.legacy),
    versionKeyWithModules(
        expected = "androidx.lifecycle",
        dependenciesObject = AndroidX.lifecycle,
        excludes = listOf(AndroidX.lifecycle.viewModelCompose)
    ),
    versionKeyWithModules(
        expected = "androidx.lifecycle-viewmodel-compose",
        dependency = AndroidX.lifecycle.viewModelCompose
    ),
    versionKeyWithModules(expected = "androidx.loader", dependency = AndroidX.loader),
    @Suppress("DEPRECATION")
    versionKeyWithModules(expected = "androidx.localbroadcastmanager", dependency = AndroidX.localBroadcastManager),
    versionKeyWithModules(expected = "androidx.media", dependency = AndroidX.media),
    versionKeyWithModules(expected = "androidx.media-widget", dependency = "androidx.media:media-widget:_"),
    versionKeyWithModules(expected = "androidx.media2", dependenciesObject = AndroidX.media2),
    versionKeyWithModules(expected = "androidx.mediarouter", dependency = AndroidX.mediaRouter),
    versionKeyWithModules(expected = "androidx.multidex", dependency = AndroidX.multidex),
    versionKeyWithModules(expected = "androidx.multidex", dependency = AndroidX.multidexInstrumentation),
    versionKeyWithModules(
        expected = "androidx.navigation",
        dependenciesObject = AndroidX.navigation,
        excludes = listOf(AndroidX.navigation.compose)
    ),
    versionKeyWithModules(
        expected = "androidx.navigation-compose",
        dependency = AndroidX.navigation.compose
    ),
    versionKeyWithModules(
        expected = "androidx.paging",
        dependenciesObject = AndroidX.paging,
        excludes = listOf(AndroidX.paging.compose)
    ),
    versionKeyWithModules(
        expected = "androidx.paging-compose",
        dependency = AndroidX.paging.compose
    ),
    versionKeyWithModules(expected = "androidx.palette", dependency = AndroidX.palette),
    versionKeyWithModules(expected = "androidx.palette", dependency = AndroidX.paletteKtx),
    versionKeyWithModules(expected = "androidx.percentlayout", dependency = AndroidX.percentLayout),
    versionKeyWithModules(expected = "androidx.preference", dependency = AndroidX.preference),
    versionKeyWithModules(expected = "androidx.preference", dependency = AndroidX.preferenceKtx),
    versionKeyWithModules(expected = "androidx.print", dependency = AndroidX.print),
    versionKeyWithModules(expected = "androidx.recommendation", dependency = AndroidX.recommendation),
    versionKeyWithModules(expected = "androidx.recyclerview", dependency = AndroidX.recyclerView),
    versionKeyWithModules(expected = "androidx.recyclerview-selection", dependency = AndroidX.recyclerViewSelection),
    versionKeyWithModules(expected = "androidx.remotecallback", dependency = AndroidX.remoteCallback),
    versionKeyWithModules(expected = "androidx.remotecallback", dependency = AndroidX.remoteCallbackProcessor),
    versionKeyWithModules(
        expected = "androidx.room",
        dependenciesObject = AndroidX.room,
        excludes = @Suppress("deprecation") listOf(AndroidX.room.coroutines)
    ),
    versionKeyWithModules(
        expected = "androidx.room-coroutines",
        dependency = @Suppress("deprecation") AndroidX.room.coroutines
    ),
    versionKeyWithModules(expected = "androidx.savedstate", dependency = AndroidX.savedState),
    versionKeyWithModules(expected = "androidx.savedstate", dependency = AndroidX.savedStateKtx),
    versionKeyWithModules(
        expected = "androidx.security-app-authenticator",
        dependency = AndroidX.security.appAuthenticator
    ),
    versionKeyWithModules(expected = "androidx.security-crypto", dependency = AndroidX.security.crypto),
    versionKeyWithModules(expected = "androidx.security-crypto", dependency = AndroidX.security.cryptoKtx),
    versionKeyWithModules(
        expected = "androidx.security-identity-credential",
        dependency = AndroidX.security.identityCredential
    ),
    versionKeyWithModules(expected = "androidx.sharetarget", dependency = AndroidX.shareTarget),
    versionKeyWithModules(
        expected = "androidx.slice",
        dependenciesObject = AndroidX.slice,
        excludes = listOf(AndroidX.slice.buildersKtx)
    ),
    versionKeyWithModules(expected = "androidx.slice-builders-ktx", dependency = AndroidX.slice.buildersKtx),
    versionKeyWithModules(expected = "androidx.slidingpanelayout", dependency = AndroidX.slidingPaneLayout),
    versionKeyWithModules(expected = "androidx.sqlite", dependency = AndroidX.sqlite),
    versionKeyWithModules(expected = "androidx.sqlite", dependency = AndroidX.sqliteFramework),
    versionKeyWithModules(expected = "androidx.sqlite", dependency = AndroidX.sqliteKtx),
    versionKeyWithModules(expected = "androidx.swiperefreshlayout", dependency = AndroidX.swipeRefreshLayout),
    versionKeyWithModules(expected = "androidx.test.core", dependency = AndroidX.test.core),
    versionKeyWithModules(expected = "androidx.test.core", dependency = AndroidX.test.coreKtx),
    versionKeyWithModules(expected = "androidx.test", dependency = AndroidX.test.monitor),
    versionKeyWithModules(expected = "androidx.test", dependency = AndroidX.test.orchestrator),
    versionKeyWithModules(expected = "androidx.test", dependency = AndroidX.test.rules),
    versionKeyWithModules(expected = "androidx.test", dependency = AndroidX.test.runner),
    versionKeyWithModules(expected = "androidx.test.ext.junit", dependency = AndroidX.test.ext.junit),
    versionKeyWithModules(expected = "androidx.test.ext.junit", dependency = AndroidX.test.ext.junitKtx),
    versionKeyWithModules(expected = "androidx.test", dependency = AndroidX.test.ext.truth),
    versionKeyWithModules(expected = "androidx.test.services", dependency = AndroidX.test.services),
    versionKeyWithModules(expected = "androidx.test.janktesthelper", dependency = AndroidX.test.jankTestHelper),
    versionKeyWithModules(expected = "androidx.test.uiautomator", dependency = AndroidX.test.uiAutomator),
    versionKeyWithModules(expected = "androidx.test.espresso", dependenciesObject = AndroidX.test.espresso,
        excludes = getArtifactsFromDependenciesObject(AndroidX.test.espresso.idling)
            .map { "${it.group}:${it.name}" }
    ),
    versionKeyWithModules(
        expected = "androidx.test.espresso.idling",
        dependenciesObject = AndroidX.test.espresso.idling
    ),
    versionKeyWithModules(expected = "androidx.textclassifier", dependency = AndroidX.textClassifier),
    versionKeyWithModules(expected = "androidx.transition", dependency = AndroidX.transition),
    versionKeyWithModules(expected = "androidx.transition", dependency = AndroidX.transitionKtx),
    versionKeyWithModules(expected = "androidx.tvprovider", dependency = AndroidX.tvProvider),
    versionKeyWithModules(expected = "androidx.ui", dependenciesObject = AndroidX.ui),
    versionKeyWithModules(expected = "androidx.vectordrawable", dependency = AndroidX.vectorDrawable),
    versionKeyWithModules(expected = "androidx.vectordrawable-animated", dependency = AndroidX.vectorDrawableAnimated),
    versionKeyWithModules(expected = "androidx.vectordrawable-seekable", dependency = AndroidX.vectorDrawableSeekable),
    versionKeyWithModules(expected = "androidx.versionedparcelable", dependency = AndroidX.versionedParcelable),
    versionKeyWithModules(expected = "androidx.viewpager", dependency = AndroidX.viewPager),
    versionKeyWithModules(expected = "androidx.viewpager2", dependency = AndroidX.viewPager2),
    versionKeyWithModules(expected = "androidx.wear", dependency = AndroidX.wear),
    versionKeyWithModules(expected = "androidx.wear-input", dependency = AndroidX.wear.input),
    versionKeyWithModules(expected = "androidx.wear-input", dependency = AndroidX.wear.inputTesting),
    versionKeyWithModules(expected = "androidx.wear-watchface", dependency = AndroidX.wear.watchFace),
    versionKeyWithModules(expected = "androidx.wear-watchface-client", dependency = AndroidX.wear.watchFace.client),
    versionKeyWithModules(expected = "androidx.wear-watchface-complications-rendering", dependency = AndroidX.wear.watchFace.complicationsRendering),
    versionKeyWithModules(expected = "androidx.wear-watchface-data", dependency = AndroidX.wear.watchFace.data),
    versionKeyWithModules(expected = "androidx.wear-watchface-editor", dependency = AndroidX.wear.watchFace.editor),
    versionKeyWithModules(expected = "androidx.wear-watchface-style", dependency = AndroidX.wear.watchFace.style),
    versionKeyWithModules(expected = "androidx.wear-complications-data", dependency = AndroidX.wear.complications.data),
    versionKeyWithModules(expected = "androidx.wear-complications-provider", dependency = AndroidX.wear.complications.provider),
    versionKeyWithModules(expected = "androidx.wear-ongoing", dependency = AndroidX.wear.ongoing),
    versionKeyWithModules(expected = "androidx.wear-phone-interactions", dependency = AndroidX.wear.phoneInteractions),
    versionKeyWithModules(expected = "androidx.wear-remote-interactions", dependency = AndroidX.wear.remoteInteractions),
    versionKeyWithModules(expected = "androidx.wear.tiles", dependency = AndroidX.wear.tiles),
    versionKeyWithModules(expected = "androidx.webkit", dependency = AndroidX.webkit),
    versionKeyWithModules(expected = "androidx.work", dependenciesObject = AndroidX.work),
    versionKeyWithModules(expected = "androidx.window", dependency = AndroidX.window),
    versionKeyWithModules(expected = "androidx.window-extensions", dependency = AndroidX.window.extensions)
)

private val google = listOf(
    versionKeyWithModules(expected = "google.android.material", dependency = Google.android.material),
    versionKeyWithModules(
        expected = "google.android.material.compose-theme-adapter",
        dependency = Google.android.material.composeThemeAdapter
    ),
    versionKeyWithModules(expected = "google.dagger", dependenciesObject = Google.dagger),
    versionKeyWithModules(expected = "google.accompanist", dependenciesObject = Google.accompanist)
)

private val testing = listOf(
    versionKeyWithModules(expected = "junit.junit", dependency = Testing.junit4),
    versionKeyWithModules(expected = "junit", dependenciesObject = Testing.junit),
    versionKeyWithModules(expected = "kotest", dependenciesObject = Testing.kotest),
    versionKeyWithModules(expected = "spek", dependenciesObject = Testing.spek),
    versionKeyWithModules(expected = "strikt", dependenciesObject = Testing.strikt),
    versionKeyWithModules(expected = "robolectric", dependency = Testing.robolectric),
    versionKeyWithModules(expected = "mockk", dependenciesObject = Testing.mockK),
    versionKeyWithModules(expected = "mockito", dependenciesObject = Testing.mockito).let {
        it.copy(moduleIdentifiers = it.moduleIdentifiers.filter { moduleIdentifier ->
            moduleIdentifier.group == "org.mockito"
        })
    }
)

internal val bundledRules = kotlinX + androidX + google + testing + listOf(
    versionKeyWithModules(expected = "ktor", dependenciesObject = Ktor),
    versionKeyWithModules(expected = "splitties", dependenciesObject = Splitties),
    versionKeyWithModules(expected = "coil-kt", dependenciesObject = COIL),
    versionKeyWithModules(expected = "orchid", dependenciesObject = Orchid)
)


@Suppress("conflicting_overloads")
private fun versionKeyWithModules(expected: String, dependency: String): VersionKeyWithModules {
    return VersionKeyWithModules(
        expectedVersionKey = expected,
        moduleIdentifiers = listOf(moduleIdentifier(dependency))
    )
}

private fun versionKeyWithModules(expected: String, dependency: DependencyNotationAndGroup): VersionKeyWithModules {
    return VersionKeyWithModules(
        expectedVersionKey = expected,
        moduleIdentifiers = listOf(moduleIdentifier(dependency.backingString))
    )
}

private fun versionKeyWithModules(
    expected: String,
    dependenciesObject: Any,
    excludes: List<String> = emptyList()
): VersionKeyWithModules {
    require(dependenciesObject !is String) { "Replace parameter named `dependenciesObject` by `dependency`" }
    val modulesInObject =
        getArtifactsFromDependenciesObject(dependenciesObject)
    return VersionKeyWithModules(
        expectedVersionKey = expected,
        moduleIdentifiers = if (excludes.isEmpty()) {
            modulesInObject
        } else {
            val excludedModuleIdentifiers = excludes.map { moduleIdentifier(it) }
            modulesInObject.filterNot { module ->
                excludedModuleIdentifiers.any { excludedModule ->
                    excludedModule.group == module.group && excludedModule.name == module.name
                }
            }
        }
    )
}

private fun moduleIdentifier(dependencyNotation: String): ModuleIdentifier {
    val group = dependencyNotation.substringBefore(':')
    val name = dependencyNotation.substringAfter(':').substringBefore(':')
    return object : ModuleIdentifier {
        override fun getGroup(): String = group
        override fun getName(): String = name
    }
}

@JvmName("do_not_use_me")
@Suppress("conflicting_overloads")
@Deprecated(
    message = "Use dependency as a parameter name",
    replaceWith = ReplaceWith("versionKeyWithModules(expected = expected, dependency = dependenciesObject)"),
    level = DeprecationLevel.ERROR
)
private fun versionKeyWithModules(
    expected: String,
    dependenciesObject: String
): Nothing = throw UnsupportedOperationException()
