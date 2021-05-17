@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.internal.ArtifactVersionKeyRule
import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

/**
 * painless Kotlin dependency injection
 *
 * - [Official website here](https://kodein.org/di/)
 * - GitHub page: [Kodein-Framework/Kodein-DI](https://github.com/Kodein-Framework/Kodein-DI)
 * - [GitHub Releases here](https://github.com/Kodein-Framework/Kodein-DI/releases)
 */

@Incubating
object Kodein {

    private var useBom = false
    val bom: String get() = "org.kodein.di:kodein-bom:_".also { useBom = true }

    val di by lazy { DI(useBom) }

    class DI(usePlatformConstraints: Boolean) : DependencyGroup(
        group = "org.kodein.di",
        usePlatformConstraints = usePlatformConstraints,
        rule = ArtifactVersionKeyRule(
            artifactPattern = "  org.kodein.di:kodein-di(-*)".trimStart(),
            versionKeyPattern = "    ^^^^^^^^^              "
        )
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

val ALL_RULES = mutableListOf<ArtifactVersionKeyRule>()

open class DependencyGroup(
    val group: String,
    val rule: ArtifactVersionKeyRule? = null,
    val usePlatformConstraints: Boolean = false
) : IsNotADependency {
    init {
        if (rule != null) ALL_RULES.add(rule)
    }

    fun module(module: String): String {
        assert(module.trim() == module)
        assert(module.contains(":").not())
        return "$group:$module" + if (usePlatformConstraints) "" else ":_"
    }
}
