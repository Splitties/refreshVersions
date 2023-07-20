@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import org.gradle.kotlin.dsl.IsNotADependency

/**
 * A static code analysis tool for the Kotlin programming language.
 *
 * - [Project website](https://detekt.dev/)
 * - [Documentation](https://detekt.dev/docs/intro)
 * - Github page: [Detekt](https://github.com/detekt/detekt)
 * - [Github releases here](https://github.com/detekt/detekt/releases)
 */
object Detekt : DependencyGroup(
    group = "io.gitlab.arturbosch.detekt",
    rawRules = """
        io.gitlab.arturbosch.detekt:detekt(-*)
                             ^^^^^^
    """.trimIndent()
) {

    val gradlePlugin = module("detekt-gradle-plugin")

    /**
     * Wrapper over ktlint
     */
    val formatting = module("detekt-formatting")

    object Rules : IsNotADependency {

        /**
         *  Rules mostly useful for Library Authors.
         */
        val libraries = module("detekt-rules-libraries")

        /**
         * Rules for Rule Authors to enforce best practices on Detekt rules.
         */
        val ruleauthors = module("detekt-rules-ruleauthors")
    }
}
