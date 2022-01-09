@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import org.gradle.kotlin.dsl.IsNotADependency

object Splitties : DependencyGroup(
    "com.louiscad.splitties",
    rawRules = """
    com.louiscad.splitties:splitties-*
                 ^^^^^^^^^
    """.trimIndent()
) {

    val pack = Packs

    object Packs : IsNotADependency {
        val androidBase = module("splitties-fun-pack-android-base")
        val androidBaseWithViewsDsl = module("splitties-fun-pack-android-base-with-views-dsl")
        val appCompat = module("splitties-fun-pack-android-appcompat")
        val appCompatWithViewsDsl = module("splitties-fun-pack-android-appcompat-with-views-dsl")
        val androidMdc = module("splitties-fun-pack-android-material-components")
        val androidMdcWithViewsDsl = module("splitties-fun-pack-android-material-components-with-views-dsl")
    }

    val activities = module("splitties-activities")
    val alertdialog = module("splitties-alertdialog")
    val alertdialogAppcompat = module("splitties-alertdialog-appcompat")
    val alertdialogAppcompatCoroutines = module("splitties-alertdialog-appcompat-coroutines")
    val alertdialogMaterial = module("splitties-alertdialog-material")
    val appctx = module("splitties-appctx")
    val archLifecycle = module("splitties-arch-lifecycle")
    val archRoom = module("splitties-arch-room")
    val bitflags = module("splitties-bitflags")
    val bundle = module("splitties-bundle")
    val checkedlazy = module("splitties-checkedlazy")
    val collections = module("splitties-collections")
    val coroutines = module("splitties-coroutines")
    val dimensions = module("splitties-dimensions")
    val exceptions = module("splitties-exceptions")
    val fragments = module("splitties-fragments")
    val fragmentargs = module("splitties-fragmentargs")
    val initprovider = module("splitties-initprovider")
    val intents = module("splitties-intents")
    val lifecycleCoroutines = module("splitties-lifecycle-coroutines")
    val mainhandler = module("splitties-mainhandler")
    val mainthread = module("splitties-mainthread")
    val materialColors = module("splitties-material-colors")
    val materialLists = module("splitties-material-lists")
    val permissions = module("splitties-permissions")
    val preferences = module("splitties-preferences")
    val resources = module("splitties-resources")
    val snackbar = module("splitties-snackbar")
    val stethoInit = module("splitties-stetho-init")
    val systemservices = module("splitties-systemservices")
    val toast = module("splitties-toast")
    val typesaferecyclerview = module("splitties-typesaferecyclerview")
    val views = module("splitties-views")
    val viewsAppcompat = module("splitties-views-appcompat")
    val viewsCardview = module("splitties-views-cardview")
    val viewsCoroutines = module("splitties-views-coroutines")
    val viewsCoroutinesMaterial = module("splitties-views-coroutines-material")
    val viewsDsl = module("splitties-views-dsl")
    val viewsDslAppcompat = module("splitties-views-dsl-appcompat")
    val viewsDslConstraintlayout = module("splitties-views-dsl-constraintlayout")
    val viewsDslCoordinatorlayout = module("splitties-views-dsl-coordinatorlayout")
    val viewsDslMaterial = module("splitties-views-dsl-material")
    val viewsDslRecyclerview = module("splitties-views-dsl-recyclerview")
    val viewsMaterial = module("splitties-views-material")
    val viewsRecyclerview = module("splitties-views-recyclerview")
    val viewsSelectable = module("splitties-views-selectable")
    val viewsSelectableAppcompat = module("splitties-views-selectable-appcompat")
    val viewsSelectableConstraintlayout = module("splitties-views-selectable-constraintlayout")
}
