@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

@Incubating
object Kodein {


    /**
     * painless Kotlin dependency injection
     *
     * - [Official website here](https://kodein.org/di/)
     * - GitHub page: [Kodein-Framework/Kodein-DI](https://github.com/Kodein-Framework/Kodein-DI)
     * - [GitHub Releases here](https://github.com/Kodein-Framework/Kodein-DI/releases)
     */
    val di = DI

    object DI : DependencyGroup(
        group = "org.kodein.di",
        rule = PrefixRule(versionKey = "version.kodein.di", prefix = "org.kodein.di:kodein-di")
    ) {
        val androidCore = module("kodein-di-framework-android-core")
        val androidSupport = module("kodein-di-framework-android-support")
        val androidx = module("kodein-di-framework-android-x")
        val configurableJS = module("kodein-di-conf-js")
        val configurableJvm = module("kodein-di-conf-jvm")
        val js = module("kodein-di")
        val jsr330 = module("kodein-di-jxinject-jvm")
        val ktor = module("kodein-di-framework-ktor-server-jvm")
        val tornadofx = module("kodein-di-framework-tornadofx-jvm")
    }
}


open class DependencyGroup(
    val group: String,
    val rule: Rule? = null,
    val usePlatformConstraints: Boolean = false
) : IsNotADependency {
    init {
        if (rule != null) Rule.ALL_RULES.add(rule)
    }


    fun module(module: String): String {
        assert(module.trim() == module)
        assert(module.contains(":").not())
        return "$group:$module" + if (usePlatformConstraints) "" else ":_"
    }
}

sealed class Rule(versionKey: String) {
    companion object {
        val ALL_RULES = mutableListOf<Rule>()
    }

    init {
        assert(versionKey.startsWith("version."))
        assert(versionKey.trim() == versionKey)
    }
}

data class PrefixRule(val versionKey: String, val prefix: String)
    : Rule(versionKey)

data class RegexRule(val versionKey: String, val regex: String)
    : Rule(versionKey)
