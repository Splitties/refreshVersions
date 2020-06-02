package de.fayard.refreshVersions.rules

import Ktor
import Splitties
import de.fayard.dependencies.internal.getArtifactsFromDependenciesObject
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
    versionKeyWithModules(expected = "androidx.activity", dependency = AndroidX.activity),
    versionKeyWithModules(expected = "androidx.activity", dependency = AndroidX.activityKtx),
    versionKeyWithModules(expected = "androidx.annotation", dependency = AndroidX.annotation),
    versionKeyWithModules(expected = "androidx.annotation", dependency = AndroidX.annotationExperimental),
    versionKeyWithModules(expected = "androidx.appcompat", dependency = AndroidX.appCompat),
    versionKeyWithModules(expected = "androidx.appcompat", dependency = AndroidX.appCompatResources),
    versionKeyWithModules(expected = "androidx.arch.core", dependenciesObject = AndroidX.archCore),
    versionKeyWithModules(expected = "androidx.asynclayoutinflater", dependency = AndroidX.asyncLayoutInflater),
    versionKeyWithModules(expected = "androidx.autofill", dependency = AndroidX.autoFill),
    versionKeyWithModules(expected = "androidx.benchmark", dependenciesObject = AndroidX.benchmark),
    versionKeyWithModules(expected = "androidx.biometric", dependency = AndroidX.biometric),
    versionKeyWithModules(expected = "androidx.browser", dependency = AndroidX.browser),
    versionKeyWithModules(expected = "androidx.camera.extensions", dependency = AndroidX.camera.extensions),
    versionKeyWithModules(expected = "androidx.camera.view", dependency = AndroidX.camera.view),
    versionKeyWithModules(expected = "androidx.camera", dependency = AndroidX.camera.camera2),
    versionKeyWithModules(expected = "androidx.camera", dependency = AndroidX.camera.core),
    versionKeyWithModules(expected = "androidx.car", dependency = AndroidX.car),
    versionKeyWithModules(expected = "androidx.car-cluster", dependency = AndroidX.carCluster),
    versionKeyWithModules(expected = "androidx.cardview", dependency = AndroidX.cardView),
    versionKeyWithModules(expected = "androidx.collection", dependency = AndroidX.collection),
    versionKeyWithModules(expected = "androidx.collection", dependency = AndroidX.collectionKtx),
    versionKeyWithModules(expected = "androidx.compose", dependenciesObject = AndroidX.compose),
    versionKeyWithModules(expected = "androidx.concurrent", dependenciesObject = AndroidX.concurrent),
    versionKeyWithModules(expected = "androidx.constraintlayout", dependency = AndroidX.constraintLayout),
    versionKeyWithModules(expected = "androidx.constraintlayout", dependency = AndroidX.constraintLayoutSolver),
    versionKeyWithModules(expected = "androidx.contentpager", dependency = AndroidX.contentPager),
    versionKeyWithModules(expected = "androidx.coordinatorlayout", dependency = AndroidX.coordinatorLayout),
    versionKeyWithModules(expected = "androidx.core", dependency = AndroidX.core),
    versionKeyWithModules(expected = "androidx.core", dependency = AndroidX.coreKtx),
    versionKeyWithModules(expected = "androidx.core-role", dependency = AndroidX.coreRole),
    versionKeyWithModules(expected = "androidx.cursoradapter", dependency = AndroidX.cursorAdapter),
    versionKeyWithModules(expected = "androidx.customview", dependency = AndroidX.customView),
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
    versionKeyWithModules(expected = "androidx.interpolator", dependency = AndroidX.interpolator),
    versionKeyWithModules(expected = "androidx.leanback", dependency = AndroidX.leanback),
    versionKeyWithModules(expected = "androidx.leanback", dependency = AndroidX.leanbackPreference),
    versionKeyWithModules(expected = "androidx.legacy", dependenciesObject = AndroidX.legacy),
    versionKeyWithModules(expected = "androidx.lifecycle", dependenciesObject = AndroidX.lifecycle),
    versionKeyWithModules(expected = "androidx.loader", dependency = AndroidX.loader),
    @Suppress("DEPRECATION")
    versionKeyWithModules(expected = "androidx.localbroadcastmanager", dependency = AndroidX.localBroadcastManager),
    versionKeyWithModules(expected = "androidx.media", dependency = AndroidX.media),
    versionKeyWithModules(expected = "androidx.media-widget", dependency = "androidx.media:media-widget:_"),
    versionKeyWithModules(expected = "androidx.media2", dependenciesObject = AndroidX.media2),
    versionKeyWithModules(expected = "androidx.mediarouter", dependency = AndroidX.mediaRouter),
    versionKeyWithModules(expected = "androidx.multidex", dependency = AndroidX.multidex),
    versionKeyWithModules(expected = "androidx.multidex", dependency = AndroidX.multidexInstrumentation),
    versionKeyWithModules(expected = "androidx.navigation", dependenciesObject = AndroidX.navigation),
    versionKeyWithModules(expected = "androidx.paging", dependenciesObject = AndroidX.paging),
    versionKeyWithModules(expected = "androidx.palette", dependency = AndroidX.palette),
    versionKeyWithModules(expected = "androidx.palette", dependency = AndroidX.paletteKtx),
    versionKeyWithModules(expected = "androidx.percentlayout", dependency = AndroidX.percentLayout),
    versionKeyWithModules(expected = "androidx.preference", dependency = AndroidX.preference),
    versionKeyWithModules(expected = "androidx.preference", dependency = AndroidX.preferenceKtx),
    versionKeyWithModules(expected = "androidx.print", dependency = AndroidX.print),
    versionKeyWithModules(expected = "androidx.recommendation", dependency = AndroidX.recommendation),
    versionKeyWithModules(expected = "androidx.recyclerview", dependency = AndroidX.recyclerView),
    versionKeyWithModules(expected = "androidx.recyclerview", dependency = AndroidX.recyclerViewSelection),
    versionKeyWithModules(expected = "androidx.remotecallback", dependency = AndroidX.remoteCallback),
    versionKeyWithModules(expected = "androidx.remotecallback", dependency = AndroidX.remoteCallbackProcessor),
    versionKeyWithModules(expected = "androidx.room", dependenciesObject = AndroidX.room),
    versionKeyWithModules(expected = "androidx.savedstate", dependency = AndroidX.savedState),
    versionKeyWithModules(expected = "androidx.security", dependenciesObject = AndroidX.security),
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
    versionKeyWithModules(expected = "androidx.test.jank", dependency = AndroidX.test.jankTestHelper),
    versionKeyWithModules(expected = "androidx.test.uiautomator", dependency = AndroidX.test.uiAutomator),
    versionKeyWithModules(expected = "androidx.test.espresso", dependenciesObject = AndroidX.test.espresso,
        excludes = getArtifactsFromDependenciesObject(AndroidX.test.espresso.idling).map { "${it.group}:${it.name}" }
    ),
    versionKeyWithModules(
        expected = "androidx.test.espresso.idling",
        dependenciesObject = AndroidX.test.espresso.idling
    ),
    versionKeyWithModules(expected = "androidx.textclassifier", dependency = AndroidX.textClassifier),
    versionKeyWithModules(expected = "androidx.transition", dependency = AndroidX.transition),
    versionKeyWithModules(expected = "androidx.tvprovider", dependency = AndroidX.tvProvider),
    versionKeyWithModules(expected = "androidx.ui", dependenciesObject = AndroidX.ui),
    versionKeyWithModules(expected = "androidx.vectordrawable", dependency = AndroidX.vectorDrawable),
    versionKeyWithModules(expected = "androidx.vectordrawable", dependency = AndroidX.vectorDrawableAnimated),
    versionKeyWithModules(expected = "androidx.versionedparcelable", dependency = AndroidX.versionedParcelable),
    versionKeyWithModules(expected = "androidx.viewpager", dependency = AndroidX.viewPager),
    versionKeyWithModules(expected = "androidx.viewpager2", dependency = AndroidX.viewPager2),
    versionKeyWithModules(expected = "androidx.wear", dependency = AndroidX.wear),
    versionKeyWithModules(expected = "androidx.webkit", dependency = AndroidX.webkit),
    versionKeyWithModules(expected = "androidx.work", dependenciesObject = AndroidX.work)
)


internal val bundledRules = kotlinX + androidX + listOf(
    versionKeyWithModules(expected = "ktor", dependenciesObject = Ktor),
    versionKeyWithModules(expected = "splitties", dependenciesObject = Splitties),
    versionKeyWithModules(expected = "junit.junit", dependency = Testing.junit4)
)


@Suppress("conflicting_overloads")
private fun versionKeyWithModules(expected: String, dependency: String): VersionKeyWithModules {
    return VersionKeyWithModules(
        expectedVersionKey = expected,
        moduleIdentifiers = listOf(moduleIdentifier(dependency))
    )
}

private fun versionKeyWithModules(
    expected: String,
    dependenciesObject: Any,
    excludes: List<String> = emptyList()
): VersionKeyWithModules {
    require(dependenciesObject !is String) { "Replace parameter named `dependenciesObject` by `dependency`" }
    val modulesInObject = getArtifactsFromDependenciesObject(dependenciesObject)
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
