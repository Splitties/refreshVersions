@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused") // 1

import de.fayard.refreshVersions.core.DependencyGroup
import org.gradle.api.Incubating

/**                                                             // 2
 * A pragmatic lightweight dependency injection framework for Kotlin developers.
 * Koin is a DSL, a light container and a pragmatic API
 *
 * - [Official website here](https://insert-koin.io/)
 * - GitHub page: [Koin](https://github.com/InsertKoinIO/koin)
 * - [GitHub Releases here](https://github.com/InsertKoinIO/koin/releases)
 */
@Incubating
object Koin : DependencyGroup(
    group = "io.insert-koin"
) {
    val core = module("koin-core")
    val test = module("koin-test")
    val junit4 = module("koin-test-junit4")
    val junit5 = module("koin-test-junit5")
    val android = module("koin-android")
    val androidCompat = module("koin-android-compat")
    val workManager = module("koin-androidx-workmanager")
    val compose = module("koin-androidx-compose")
    val ktor = module("koin-ktor")
    val slf4j = module("koin-logger-slf4j")
}
