@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import org.gradle.kotlin.dsl.IsNotADependency

/**
 * painless Kotlin dependency injection
 *
 * - [Official website here](https://kodein.org/di/)
 * - GitHub page: [Kodein-Framework/Kodein-DI](https://github.com/Kodein-Framework/Kodein-DI)
 * - [GitHub Releases here](https://github.com/Kodein-Framework/Kodein-DI/releases)
 */
object Kodein : IsNotADependency {

    val di = DI

    object DI : DependencyGroup(
        group = "org.kodein.di",
        usePlatformConstraints = false,
        rawRules = """
            org.kodein.di:kodein-di(-*)
                ^^^^^^^^^
        """.trimIndent()
    ) {
        val androidCore = module("kodein-di-framework-android-core")
        val androidSupport = module("kodein-di-framework-android-support")
        val androidx = module("kodein-di-framework-android-x")
        val configurableJS = module("kodein-di-conf-js")
        val configurableJvm = module("kodein-di-conf-jvm")
        val js = module("kodein-di-js")
        val jsr330 = module("kodein-di-jxinject-jvm")
        val ktor = module("kodein-di-framework-ktor-server-jvm")
        val tornadofx = module("kodein-di-framework-tornadofx-jvm")
    }
}
