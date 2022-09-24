@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import de.fayard.refreshVersions.core.DependencyNotation

object CashApp {

    /**
     * A Gradle plugin which validates the licenses of your dependency graph match what you expect, or it fails your build!
     *
     * GitHub page: [cashapp/licensee](https://github.com/cashapp/licensee)
     *
     * Id of the Gradle plugin: **`app.cash.licensee`**
     */
    val licenseeGradlePlugin = DependencyNotation("app.cash.licensee", "licensee-gradle-plugin")

    /**
     * SQLDelight generates typesafe kotlin APIs from your SQL statements.
     *
     * Official Website: [cashapp.github.io/sqldelight](https://cashapp.github.io/sqldelight/)
     *
     * [Change log](https://cashapp.github.io/sqldelight/changelog/)
     *
     * GitHub page: [cashapp/sqldelight](https://github.com/cashapp/sqldelight)
     */
    val sqlDelight = Square.sqlDelight

    /**
     * Turbine is a small testing library for kotlinx.coroutines [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/).
     *
     * [GitHub releases](https://github.com/cashapp/turbine/releases)
     *
     * GitHub page: [cashapp/turbine](https://github.com/cashapp/turbine)
     */
    val turbine = DependencyNotation("app.cash.turbine", "turbine")

    /**
     * A content provider wrapper for reactive queries with Kotlin coroutines `Flow` or RxJava `Observable`.
     *
     * (Content Provider is an Android API)
     *
     * GitHub page: [cashapp/copper](https://github.com/cashapp/copper)
     */
    val copper = Copper

    object Copper : DependencyGroup(group = "app.cash.copper") {
        val flow = module("copper-flow")
        val rx2 = module("copper-rx2")
        val rx3 = module("copper-rx3")
    }

    /**
     * Build a `StateFlow` or `Flow` stream using Jetpack Compose.
     *
     * [GitHub Releases](https://github.com/cashapp/molecule/releases)
     *
     * GitHub page: [cashapp/molecule](https://github.com/cashapp/molecule)
     */
    val molecule = Molecule

    object Molecule : DependencyGroup(group = "app.cash.molecule") {
        val gradlePlugin = module("molecule-gradle-plugin")
        val runtime = module("molecule-runtime")
    }
}
