@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused", "MemberVisibilityCanBePrivate")

import de.fayard.refreshVersions.core.DependencyGroup
import de.fayard.refreshVersions.core.DependencyNotation
import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.kotlin.dsl.IsNotADependency

object Android : IsNotADependency {

    val billingClient = BillingClient

    object BillingClient : DependencyNotationAndGroup(
        group = "com.android.billingclient",
        name = "billing",
        rawRules = """
            com.android.billingclient:billing(-*)
                ^^^^^^^^^^^^^^^^^^^^^
        """.trimIndent()
    ) {
        val ktx = module("billing-ktx")
    }

    val installReferrer = DependencyNotation(group = "com.android.installreferrer", name = "installreferrer")

    val tools = Tools

    object Tools : DependencyGroup(
        group = "com.android.tools",
        rawRules = """
            com.android.tools:*
                ^^^^^^^^^^^^^.^
        """.trimIndent()
    ) {

        val build = Build

        object Build : DependencyGroup(group = "com.android.tools.build") {
            val gradlePlugin = module("gradle")
        }

        val r8 = module("r8")

        /**
         * Guide: [Use Java 8 language features and APIs](https://developer.android.com/studio/write/java8-support)
         *
         * ## Important:
         *
         * When adding the dependency, instead of `api` or `implementation`,
         * **use the `coreLibraryDesugaring` configuration**, and make sure that you also enabled
         * core library desugaring in compileOptions as follows:
         *
         * ```kts
         * android {
         *     compileOptions {
         *         isCoreLibraryDesugaringEnabled = true
         *     }
         * }
         * ```
         */
        val desugarJdkLibs = module("desugar_jdk_libs")
    }
}
