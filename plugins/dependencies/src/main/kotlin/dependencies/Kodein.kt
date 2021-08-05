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
        val androidCore by module("kodein-di-framework-android-core")
        val androidSupport by module("kodein-di-framework-android-support")
        val androidx by module("kodein-di-framework-android-x")
        val configurableJS by module("kodein-di-conf-js")
        val configurableJvm by module("kodein-di-conf-jvm")
        val js by module("kodein-di-js")
        val jsr330 by module("kodein-di-jxinject-jvm")
        val ktor by module("kodein-di-framework-ktor-server-jvm")
        val tornadofx by module("kodein-di-framework-tornadofx-jvm")
    }
}
