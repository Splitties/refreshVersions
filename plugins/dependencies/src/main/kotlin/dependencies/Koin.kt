@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import dependencies.DependencyNotationAndGroup
import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

@Incubating
/**
 *  Koin - a pragmatic lightweight dependency injection framework for Kotlin
 *
 * - [Official website here](https://insert-koin.io/)
 * - GitHub page: [InsertKoinIO/koin](https://github.com/InsertKoinIO/koin)
 * - [Changelog here](https://github.com/InsertKoinIO/koin/blob/master/CHANGELOG.md)
 */
object Koin {

    private const val group = "org.koin"

    const val core = "$group:koin-core:_"

    const val coreExt = "$group:koin-core-ext:_"

    const val test = "$group:koin-test:_"

    const val ktor = "$group:koin-ktor:_"

    val android = Android

    object Android: DependencyNotationAndGroup(group, "kotlin-android") {
        const val scope = "$group:koin-android-scope:_"
        const val viewModel = "$group:koin-android-viewmodel:_"
        const val ext = "$group:koin-android-ext:_"
    }

    val androidX = AndroidX

    object AndroidX: IsNotADependency {
        const val scope = "$group:koin-androidx-scope:_"
        const val viewmodel = "$group:koin-androidx-viewmodel:_"
        const val fragment = "$group:koin-androidx-fragment:_"
        const val wworkmanager = "$group:koin-androidx-workmanager:_"
        const val compose = "$group:koin-androidx-compose:_"
        const val ext = "$group:koin-androidx-ext:_"
    }
}
