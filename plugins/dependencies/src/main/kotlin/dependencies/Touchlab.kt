@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.kotlin.dsl.IsNotADependency

object Touchlab : IsNotADependency {

    /**
     * Stately is a state utility library to facilitate state management in Kotlin Multiplatform.
     *
     * [GitHub releases](https://github.com/touchlab/Stately/releases)
     *
     * GitHub repo: [touchlab/Stately](https://github.com/touchlab/Stately)
     */
    val stately = Stately

    object Stately : DependencyGroup(
        group = "co.touchlab",
        rawRules = """
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

    /**
     * Kermit is a Kotlin Multiplatform logging utility with composable log outputs.
     * The library provides prebuilt loggers for outputting to platform logging tools
     * such as Logcat and NSLog.
     *
     * [GitHub Releases](https://github.com/touchlab/Kermit/releases)
     *
     * GitHub repo: [touchlab/Kermit](https://github.com/touchlab/Kermit)
     *
     * [1.0 release blog](https://medium.com/@kpgalligan/kermit-kmp-logging-1-0-196bf799b738)
     */
    object Kermit : DependencyNotationAndGroup(
        group = "co.touchlab",
        name = "kermit",
        rawRules = """
        co.touchlab:kermit(-*)
                    ^^^^^^
        """.trimIndent()
    ) {
        val test = module("kermit-test")
        val crashlytics = module("kermit-crashlytics")
        val crashlyticsTest = module("kermit-crashlytics-test")
        val bugsnag = module("kermit-bugsnag")
        val bugsnagTest = module("kermit-bugsnag-test")
        val gradlePlugin = module("kermit-gradle-plugin")
    }
}

