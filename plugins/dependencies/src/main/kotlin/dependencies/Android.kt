@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused", "MemberVisibilityCanBePrivate")

import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.kotlin.dsl.IsNotADependency

object Android : IsNotADependency {

    val billingClient = BillingClient

    object BillingClient : DependencyNotationAndGroup(
        group = "com.android.billingclient",
        name = "billing",
        rawRule = """
            com.android.billingclient:billing(-*)
                ^^^^^^^^^^^^^^^^^^^^^
        """.trimIndent()
    ) {
        val ktx = module("billing-ktx")
    }
}
