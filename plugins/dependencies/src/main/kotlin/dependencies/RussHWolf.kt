@file:Suppress("PackageDirectoryMismatch", "SpellCheckingInspection", "unused")

import de.fayard.refreshVersions.core.DependencyNotationAndGroup
import org.gradle.kotlin.dsl.IsNotADependency

/**
 * This is a Kotlin library for Multiplatform apps, so that common code can persist key-value data.
 *
 * - GitHub page: [MultiplatformSettings](https://github.com/russhwolf/multiplatform-settings)
 * - [GitHub Releases here](https://github.com/russhwolf/multiplatform-settings/releases)
 */
object RussHWolf : IsNotADependency {

    val multiplatformSettings = MultiplatformSettings

    object MultiplatformSettings : DependencyNotationAndGroup(
        group = "com.russhwolf",
        rawRules = """
        com.russhwolf:multiplatform-settings(-*)
                      ^^^^^^^^^^^^^^^^^^^^^^
    """.trimIndent(),
        name = "multiplatform-settings"
    ) {
        val coroutines = module("multiplatform-settings-coroutines")
        val coroutinesNativeMt = module("multiplatform-settings-coroutines-native-mt")
        val dataStore = module("multiplatform-settings-datastore")
        val noArg = module("multiplatform-settings-no-arg")
        val serialization = module("multiplatform-settings-serialization")
        val settings = module("multiplatform-settings")
        val test = module("multiplatform-settings-test")
    }
}
