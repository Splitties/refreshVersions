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
    const val ktor = "$group:koin-ktor:_"
    const val test = "$group:koin-test:_"

    val android = Android

    object Android: DependencyNotationAndGroup(group, "koin-android") {
        const val ext = "$group:koin-android-ext:_"
        const val scope = "$group:koin-android-scope:_"
        const val viewModel = "$group:koin-android-viewmodel:_"
    }

    val androidX = AndroidX

    object AndroidX: IsNotADependency {
        const val compose = "$group:koin-androidx-compose:_"
        const val ext = "$group:koin-androidx-ext:_"
        const val fragment = "$group:koin-androidx-fragment:_"
        const val scope = "$group:koin-androidx-scope:_"
        const val viewModel = "$group:koin-androidx-viewmodel:_"
        const val workManager = "$group:koin-androidx-workmanager:_"
    }
}
