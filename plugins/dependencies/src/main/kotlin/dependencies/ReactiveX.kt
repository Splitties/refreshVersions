@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused", "MemberVisibilityCanBePrivate")

import de.fayard.refreshVersions.core.DependencyNotation
import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

@Incubating
object ReactiveX : IsNotADependency {

    val rxJava1 = DependencyNotation(group = "io.reactivex", name = "rxjava")

    val rxKotlin1 = DependencyNotation(group = "io.reactivex", name = "rxkotlin")

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
