@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup

/**
 * A pragmatic lightweight dependency injection framework for Kotlin developers.
 * Koin is a DSL, a light container and a pragmatic API
 *
 * - [Official website here](https://insert-koin.io/)
 * - GitHub page: [Koin](https://github.com/InsertKoinIO/koin)
 * - [GitHub Releases here](https://github.com/InsertKoinIO/koin/releases)
 */
object Koin : DependencyGroup(
    group = "io.insert-koin",
    rawRules = """
        io.insert-koin:koin-*
                  ^^^^
    """.trimIndent()
) {
    // See https://insert-koin.io/docs/setup/v3.2

    val core = module("koin-core")
    val test = module("koin-test")
    val junit4 = module("koin-test-junit4")
    val junit5 = module("koin-test-junit5")
    val android = module("koin-android")
    val androidCompat = module("koin-android-compat")
    val workManager = module("koin-androidx-workmanager")
    val navigation = module("koin-androidx-navigation")
    val compose = module("koin-androidx-compose")
    val ktor = module("koin-ktor")
    val slf4j = module("koin-logger-slf4j")
}
