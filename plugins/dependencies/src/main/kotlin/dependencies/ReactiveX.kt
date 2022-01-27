@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused", "MemberVisibilityCanBePrivate")

import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.kotlin.dsl.IsNotADependency

object ReactiveX : IsNotADependency {

    val rxJava2 = RxJava2

    object RxJava2 : DependencyNotationAndGroup(group = "io.reactivex.rxjava2", name = "rxjava") {

        val rxAndroid = module("rxandroid")

        val rxKotlin = module("rxkotlin")
    }

    val rxJava3 = RxJava3

    object RxJava3 : DependencyNotationAndGroup(group = "io.reactivex.rxjava3", name = "rxjava") {

        val rxAndroid = module("rxandroid")

        val rxKotlin = module("rxkotlin")
    }
}
