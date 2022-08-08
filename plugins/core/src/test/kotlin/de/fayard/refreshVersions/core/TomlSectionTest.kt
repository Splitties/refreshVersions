package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.VersionsCatalogs
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class TomlSectionTest {

    private val toml = """
        ## This is a great comment

        [versions]
        groovy = "2.5.14"
        guava = "30.0-jre"
        jupiter = "5.7.1"

        [libraries]
        guava = { module="com.google.guava:guava", version.ref="guava" }
        junit-jupiter = { module="org.junit.jupiter:junit-jupiter-api", version.ref="jupiter" }
        junit-engine = { module="org.junit.jupiter:junit-jupiter-engine" }

        groovy-core = { module="org.codehaus.groovy:groovy", version.ref="groovy" }
        groovy-json = { module="org.codehaus.groovy:groovy-json", version.ref="groovy" }

        [bundles]
        testDependencies = ["junit-jupiter", "junit-engine"]
        """.trimIndent()

    @Test
    fun `Parse Toml in Sections`() {

        val (a, b, c, d) = toml.split(
            "[versions]\n",
            "[libraries]\n",
            "[bundles]\n"
        )

        val expected = mapOf("root" to a, "versions" to b, "libraries" to c, "bundles" to d)
        VersionsCatalogs.parseTomlInSections(toml) shouldBe expected
    }
}
