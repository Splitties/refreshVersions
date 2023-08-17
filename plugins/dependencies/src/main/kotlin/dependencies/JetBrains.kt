@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import org.gradle.kotlin.dsl.IsNotADependency

object JetBrains : IsNotADependency {

    /**
     * Exposed is an ORM framework for Kotlin
     *
     * - [Wiki](https://github.com/JetBrains/Exposed/wiki)
     * - GitHub page: [JetBrains/Exposed](https://github.com/JetBrains/Exposed)
     * - [GitHub releases](https://github.com/JetBrains/Exposed/releases)
     */
    val exposed = Exposed

    object Exposed : DependencyGroup(
        group = "org.jetbrains.exposed",
        rawRules = """
            org.jetbrains.exposed:exposed-*
                ^^^^^^^^^.^^^^^^^
        """.trimIndent()
    ) {

        val core = module("exposed-core")
        val dao = module("exposed-dao")
        val jdbc = module("exposed-jdbc")
    }

    /**
     * Ktor: Create asynchronous client and server applications.
     * Anything from microservices to multiplatform HTTP client apps in a simple way. Open Source, free, and fun!
     *
     * - [Complete KDoc here](https://api.ktor.io/latest/).
     * - [Website here](https://ktor.io/).
     */
    val ktor = Ktor
}
