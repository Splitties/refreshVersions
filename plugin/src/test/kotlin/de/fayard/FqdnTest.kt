package de.fayard

import de.fayard.internal.Dependency
import de.fayard.internal.PluginConfig
import de.fayard.internal.VersionMode
import de.fayard.internal.findCommonVersions
import io.kotlintest.matchers.collections.containExactlyInAnyOrder
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec

class FqdnTest: FreeSpec({
    val appCompat = "androidx.appcompat:appcompat:_".asDependency()
    val asyncLayoutInflater = "androidx.asynclayoutinflater:asynclayoutinflater:_".asDependency()
    val browser = "androidx.browser:browser:_".asDependency()
    val car = "androidx.car:car:1.0.1".asDependency()
    val carCluster = "androidx.car:car:1.1.0".asDependency()
    val sliceCore = "androidx.slice:slice-core:1.0.0".asDependency()
    val sliceView = "androidx.slice:slice-view:1.0.0".asDependency()
    val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.0.0".asDependency()
    val coroutinesCommon = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:1.0.0".asDependency()
    val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.13.0".asDependency()

    val ALL = listOf(appCompat, asyncLayoutInflater, browser, car, sliceCore, sliceView)
    val NO_DEFAULT = emptyList<String>()

    "findCommonVersions" - {

        fun commonGroups(vararg dependency: Dependency): Map<String, String> =
            dependency.map { it.copy() }
                .findCommonVersions()
                .filter { it.mode == VersionMode.GROUP }
                .associate { Pair(it.module, it.groupOrVirtualGroup()) }

        "no common group" {
            commonGroups(appCompat, browser, car, sliceCore) shouldBe emptyMap()
        }

        "same group, same version" {
            commonGroups(sliceCore, sliceView, browser) shouldBe mapOf("slice-core" to "androidx.slice", "slice-view" to "androidx.slice")
        }

        "same group but versions do not match" {
            commonGroups(car, carCluster) shouldBe emptyMap()
        }

        "virtual groups" {
            val kotlinX = "org.jetbrains.kotlinx"
            commonGroups(coroutinesCore, coroutinesCommon, kotlinxSerialization) shouldBe mapOf(
                coroutinesCore.module to "$kotlinX.kotlinx-coroutines",
                coroutinesCommon.module to "$kotlinX.kotlinx-coroutines",
                kotlinxSerialization.module to "$kotlinX.kotlinx-serialization"
            )
        }
    }

    with(PluginConfig) {

        "Nothing configured -> MEANINGLESS NAMES" {
            computeUseFqdnFor(emptyList(), emptyList()) shouldBe MEANING_LESS_NAMES.sorted()
        }

        "Using a dependency module" {
            val result = computeUseFqdnFor(ALL, listOf("car", "car", "browser", "slice-car"), listOf("browser", "gradle"))
            result shouldBe listOf("browser", "car", "gradle", "slice-car")
        }

        "Using a group" {
            computeUseFqdnFor(ALL, listOf("androidx.slice", "androidx.browser", "com.example"), NO_DEFAULT) shouldBe listOf("browser", "slice-core", "slice-view")
        }

        "Ambiguity in module names" {
            val annotations = listOf(
                "androidx.annotation:annotation:1.0.0".asDependency(),
                "com.example:annotation:1.0.0".asDependency()
            )
            computeUseFqdnFor(annotations, emptyList(), emptyList()) shouldBe listOf("annotation")
        }
    }
})
