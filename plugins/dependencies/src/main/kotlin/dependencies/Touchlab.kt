@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import org.gradle.api.Incubating

/**
 * Multithreaded Kotlin Multiplatform Utilities
 *
 * - GitHub page: [Stately](https://github.com/touchlab/Stately)
 * - [GitHub Releases here](https://github.com/touchlab/Stately/releases)
 */
@Incubating
object Touchlab {
    val stately = Stately

    object Stately : DependencyGroup(
        group = "co.touchlab",
        rawRule = """
        co.touchlab:stately-*
                    ^^^^^^^
        """.trimIndent()
    ) {
        val common = module("stately-common")
        val concurrency = module("stately-concurrency")
        val isolate = module("stately-isolate")
        val isoCollections = module("stately-iso-collections")
    }

    val kermit = Kermit

    object Kermit : DependencyGroup(
        group = "co.touchlab",
        rawRule = """
        co.touchlab:kermit*
                    ^^^^^^^
        """.trimIndent()
    ) {
        val kermit = module("kermit")
        val test = module("kermit-test")
        val crashlytics = module("kermit-crashlytics")
        val crashlyticsTest = module("kermit-crashlytics-test")
        val bugsnag = module("kermit-bugsnag")
        val bugsnagTest = module("kermit-bugsnag-test")
        val gradlePlugin = module("kermit-gradle-plugin")
    }
}

