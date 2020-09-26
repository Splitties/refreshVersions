@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import dependencies.DependencyNotationAndGroup
import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency
import java.util.function.DoubleBinaryOperator

@Incubating
object Kodein {

    private const val prefix = "org.kodein.di:kodein"

    /**
     * painless Kotlin dependency injection
     *
     * - [Official website here](https://kodein.org/di/)
     * - GitHub page: [Kodein-Framework/Kodein-DI](https://github.com/Kodein-Framework/Kodein-DI)
     * - [GitHub Releases here](https://github.com/Kodein-Framework/Kodein-DI/releases)
     */
    val di = DI

    object DI: DependencyNotationAndGroup(group = "org.kodein.di", name = "kodein-di") {
        const val js = "$prefix-di-js:_"

        const val androidCore = "$prefix-di-framework-android-core:_"
        const val androidSupport = "$prefix-di-framework-android-support:_"
        const val androidx = "$prefix-di-framework-android-x:_"
        const val ktor = "$prefix-di-framework-ktor-server-jvm:_"
        const val tornadofx = "$prefix-di-framework-tornadofx-jvm:_"
        const val configurableJvm = "$prefix-di-conf-jvm:_"
        const val configurableJS = "$prefix-di-conf-js:_"
        const val jsr330 = "$prefix-di-jxinject-jvm:_"
    }

}
