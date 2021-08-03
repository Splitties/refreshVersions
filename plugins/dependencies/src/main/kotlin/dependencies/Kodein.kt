@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.internal.DependencyGroup
import org.gradle.api.Incubating

/**
 * painless Kotlin dependency injection
 *
 * - [Official website here](https://kodein.org/di/)
 * - GitHub page: [Kodein-Framework/Kodein-DI](https://github.com/Kodein-Framework/Kodein-DI)
 * - [GitHub Releases here](https://github.com/Kodein-Framework/Kodein-DI/releases)
 */

@Incubating
object Kodein {

    val di = DI()

    class DI : DependencyGroup(
        group = "org.kodein.di",
        rawRule = """
            org.kodein.di:kodein-di(-*)
                ^^^^^^^^^
        """.trimIndent()
    ) {
        val androidCore get() = module("kodein-di-framework-android-core")
        val androidSupport get() = module("kodein-di-framework-android-support")
        val androidx get() = module("kodein-di-framework-android-x")
        val configurableJS get() = module("kodein-di-conf-js")
        val configurableJvm get() = module("kodein-di-conf-jvm")
        val js get() = module("kodein-di-js")
        val jsr330 get() = module("kodein-di-jxinject-jvm")
        val ktor get() = module("kodein-di-framework-ktor-server-jvm")
        val tornadofx get() = module("kodein-di-framework-tornadofx-jvm")
    }
}
