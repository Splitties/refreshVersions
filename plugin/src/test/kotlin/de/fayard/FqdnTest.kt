package de.fayard

import de.fayard.internal.PluginConfig
import io.kotlintest.matchers.collections.containExactlyInAnyOrder
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec

class FqdnTest: FreeSpec({
    val appCompat = "androidx.appcompat:appcompat:_".asDependency()
    val asyncLayoutInflater = "androidx.asynclayoutinflater:asynclayoutinflater:_".asDependency()
    val browser = "androidx.browser:browser:_".asDependency()
    val car = "androidx.car:car:_".asDependency()
    val sliceCore = "androidx.slice:slice-core:_".asDependency()
    val sliceView = "androidx.slice:slice-view:_".asDependency()

    val ALL = listOf(appCompat, asyncLayoutInflater, browser, car, sliceCore, sliceView)
    val NO_DEFAULT = emptyList<String>()

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
                "androidx.annotation:annotation:_".asDependency(),
                "com.example:annotation:_".asDependency()
            )
            computeUseFqdnFor(annotations, emptyList(), emptyList()) shouldBe listOf("annotation")
        }
    }
})
