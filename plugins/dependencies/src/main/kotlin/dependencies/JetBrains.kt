@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import org.gradle.api.Incubating
import org.gradle.kotlin.dsl.IsNotADependency

@Incubating
object JetBrains : IsNotADependency {

    /**
     * Exposed is an ORM framework for Kotlin
     *
     * - [Wiki](https://github.com/JetBrains/Exposed/wiki)
     * - GitHub page: [JetBrains/Exposed](https://github.com/JetBrains/Exposed)
     * - [GitHub releases](https://github.com/JetBrains/Exposed/releases)
     */
    val exposed = Exposed

    object Exposed : DependencyGroup("org.jetbrains.exposed") {

        val core = module("exposed-core")
        val dao = module("exposed-dao")
        val jdbc = module("exposed-jdbc")
    }
}
