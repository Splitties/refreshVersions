@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import org.gradle.kotlin.dsl.IsNotADependency

object CashApp {

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
    const val turbine = "app.cash.turbine:turbine:_"

    /**
     * A content provider wrapper for reactive queries with Kotlin coroutines `Flow` or RxJava `Observable`.
     *
     * (Content Provider is an Android API)
     *
     * GitHub page: [cashapp/copper](https://github.com/cashapp/copper)
     */
    val copper = Copper

    object Copper : IsNotADependency {
        private const val artifactPrefix = "app.cash.copper:copper"

        const val flow = "$artifactPrefix-flow:_"
        const val rx2 = "$artifactPrefix-rx2:_"
        const val rx3 = "$artifactPrefix-rx3:_"
    }
}
