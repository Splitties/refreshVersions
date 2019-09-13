package de.fayard

import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec

class GradlePropertiesTest : FreeSpec({

    val updater = UpdateGradleProperties(BuildSrcVersionsExtensionImpl(), emptyList())

    "spaces" {
        PluginConfig.spaces(-2) shouldBe ""
        PluginConfig.spaces(0) shouldBe ""
        PluginConfig.spaces(4) shouldBe "    "
    }


    "Versions Properties" - {
        with(UpdateVersionsOnly) {

            "gradle-versions-plugin" {
                val gvp = "com.github.ben-manes:gradle-versions-plugin:0.22.0 // 0.25.0".asDependency()
                gvp.asGradleProperty() shouldBe """
                    |version.gradle.versions.plugin=0.22.0
                    |               # \-- available=0.25.0
                """.trimMargin()
            }

            "guava" {
                val guava = "com.google.guava:guava:15.0".asDependency()
                guava.asGradleProperty() shouldBe "version.guava=15.0"
            }

            "fully qualified domain name" {
                val guava = "com.google.guava:guava:15.0".asDependency(fqdn = true)
                guava.asGradleProperty() shouldBe "version.com.google.guava.guava=15.0"
            }
        }
    }
})

