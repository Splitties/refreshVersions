package de.fayard

import de.fayard.internal.PluginConfig
import de.fayard.internal.UpdateVersionsOnly
import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec

class GradlePropertiesTest : FreeSpec({

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
                    |version.gradle-versions-plugin=0.22.0
                    |#                  # available=0.25.0
                """.trimMargin()
            }

            "guava" {
                val guava = "com.google.guava:guava:15.0".asDependency()
                guava.asGradleProperty() shouldBe "version.guava=15.0"
            }

            "fully qualified domain name" {
                val guava = "com.google.guava:guava:15.0".asDependency(fqdn = true)
                guava.asGradleProperty() shouldBe "version.com.google.guava..guava=15.0"
            }

            "plugin buildSrcVersions" {
                val bsv = "de.fayard.buildSrcVersions:de.fayard.buildSrcVersions.gradle.plugin:0.5.0 // 0.6.0".asDependency()
                bsv.asGradleProperty() shouldBe ("""
                    |plugin.de.fayard.buildSrcVersions=0.5.0
                    |#                     # available=0.6.0""").trimMargin()
            }

            "plugin com.gradle.plugin-publish"  {
                val publish = "com.gradle.plugin-publish:com.gradle.plugin-publish.gradle.plugin:1.0".asDependency()
                publish.asGradleProperty() shouldBe "plugin.com.gradle.plugin-publish=1.0"
            }
        }
    }
})

