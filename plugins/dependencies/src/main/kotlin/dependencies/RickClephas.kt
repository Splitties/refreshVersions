@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyGroup
import org.gradle.kotlin.dsl.IsNotADependency

/**
 * A library to use Kotlin Coroutines from Swift and JS code in KMP apps.
 *
 * - GitHub page: [NativeCoroutines](https://github.com/rickclephas/KMP-NativeCoroutines)
 * - [GitHub Releases here](https://github.com/rickclephas/KMP-NativeCoroutines/releases)
 */
object RickClephas : IsNotADependency {

    val nativeCoroutines = NativeCoroutines

    object NativeCoroutines : DependencyGroup(
        group = "com.rickclephas.kmp",
        rawRules = """
        com.rickclephas.kmp:kmp-nativecoroutines(-*)
            ^^^^^^^^^^^^        ^^^^^^^^^^^^^^^^
    """.trimIndent()
    ) {
        val core = module("kmp-nativecoroutines-core")
        val annotations = module("kmp-nativecoroutines-annotations")
        val compiler = module("kmp-nativecoroutines-compiler")
        val compilerEmbeddable = module("kmp-nativecoroutines-compiler-embeddable")
        val gradlePlugin = module("kmp-nativecoroutines-gradle-plugin")
    }
}
