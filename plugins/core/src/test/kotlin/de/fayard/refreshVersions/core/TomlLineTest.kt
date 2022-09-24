package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.ConfigurationLessDependency
import de.fayard.refreshVersions.core.internal.TomlLine
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.Deletable
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.Ignore
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.Libs
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.LibsUnderscore
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.LibsVersionRef
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.Plugin
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.PluginVersionRef
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.Version
import de.fayard.refreshVersions.core.internal.TomlSection
import io.kotest.assertions.assertSoftly
import io.kotest.inspectors.forAll
import io.kotest.inspectors.forAny
import io.kotest.matchers.shouldBe
import kotlin.test.Test

class TomlLineTest {


    @Test
    fun `Parsing kind for libraries`() {

        val lines = """
            groovy-core = { module = "org.codehaus.groovy:groovy", version.ref = "groovy" }
            groovy-json = { module = "org.codehaus.groovy:groovy-json", version.ref = "groovy" }
            groovy-nio = { module = "org.codehaus.groovy:groovy-nio", version.ref = "groovy" }

            # another comment

            refreshVersionsLib = "de.fayard:lib:_"

            my-lib = "com.mycompany:mylib:1.4"
            ##                # available:1.5"

            my-other-lib = { module = "com.mycompany:other", version = "1.4" }

            my-other-lib2 = { group = "com.mycompany", name = "alternate", version = "1.4" }
            ##
        """.trimIndent().lines()

        val expectedKinds: List<TomlLine.Kind> = listOf(
            LibsVersionRef, LibsVersionRef, LibsVersionRef,
            Ignore, Ignore, Ignore,
            LibsUnderscore, Ignore,
            Libs, Deletable, Ignore,
            Libs, Ignore,
            Libs, Deletable,
        )

        val testCases = lines.zip(expectedKinds)

        testCases.forAll { (line, expectedKind) ->
            TomlLine(TomlSection.Libraries, line).kind shouldBe expectedKind
        }
    }

    @Test
    fun `Parsing kind for versions`() {

        val lines = """
            # some comment

            groovy = "3.0.5"
            ## available: "1.5"
            ## available: "1.6"

            checkstyle = "8.37"
            common = "3.4"
        """.trimIndent().lines()

        val expectedKinds: List<TomlLine.Kind> = listOf(
            Ignore, Ignore,
            Version, Deletable, Deletable, Ignore,
            Version, Version,
        )

        val testCases = lines.zip(expectedKinds)

        testCases.forAll { (line, expectedKind) ->
            TomlLine(TomlSection.Versions, line).kind shouldBe expectedKind
        }
    }

    @Test
    fun `Parsing kind for plugins`() {

        val lines = """
            short-notation = "some.plugin.id:1.4"
            ##                  # available:"1.5"

            # yet another comment
            long-notation = { id = "some.plugin.id", version = "1.4" }
            reference-notation = { id = "some.plugin.id", version.ref = "common" }
        """.trimIndent().lines()

        val expectedKinds: List<TomlLine.Kind> = listOf(
            Plugin, Deletable, Ignore, Ignore,
            Plugin, PluginVersionRef
        )

        val testCases = lines.zip(expectedKinds)

        testCases.forAll { (line, expectedKind) ->
            TomlLine(TomlSection.Plugins, line).kind shouldBe expectedKind
        }
    }

    @Test
    fun `Parsing libraries values`() {
        fun map(
            group: String,
            name: String,
            version: String?,
            versionRef: String?
        ) = listOfNotNull(
            "group" to group,
            "name" to name,
            version?.let { "version" to it },
            versionRef?.let { "version.ref" to it }
        ).toMap()

        val lines = """
            ##                # available:1.5"
            # comment
            refreshVersionsLib = "de.fayard:lib:_"
            my-lib = "com.mycompany:mylib:1.4"
            my-other-lib = { module = "com.mycompany:other", version = "1.4" }
            my-other-lib2 = { group = "com.mycompany", name = "alternate", version = "1.4" }
            groovy-nio = { module = "org.codehaus.groovy:groovy-nio", version.ref = "groovy" }
            groovy-io = { group = "org.codehaus.groovy", name = "io", version.ref = "groovy" }
        """.trimIndent().lines()

        val expected = listOf(
            emptyMap(),
            emptyMap(),
            emptyMap(),
            map("com.mycompany", "mylib", "1.4", null),
            map("com.mycompany", "other", "1.4", null),
            map("com.mycompany", "alternate", "1.4", null),
            map("org.codehaus.groovy", "groovy-nio", null, "groovy"),
            map("org.codehaus.groovy", "io", null, "groovy"),
        )

        val testCases = lines.zip(expected)

        testCases.forAll { (line, map) ->
            TomlLine(TomlSection.Libraries, line).map shouldBe map
        }

    }

    @Test
    fun `Parsing plugins values`() {
        fun map(id: String, version: String?, versionRef: String?) =
            listOfNotNull("id" to id, version?.let { "version" to it }, versionRef?.let { "version.ref" to it })
                .toMap()

        val lines = """
            # yet another comment
            ##                  # available:"1.5"

            short-notation = "some.plugin.id:1.4"
            long-notation = { id = "some.plugin.id", version = "1.4" }
            reference-notation = { id = "some.plugin.id", version.ref = "common" }
        """.trimIndent().lines()

        val expected = listOf(
            emptyMap(),
            emptyMap(),
            emptyMap(),
            map("some.plugin.id", "1.4", null),
            map("some.plugin.id", "1.4", null),
            map("some.plugin.id", null, "common"),
        )

        val testCases = lines.zip(expected)

        testCases.forAll { (line, map) ->
            TomlLine(TomlSection.Plugins, line).map shouldBe map
        }
    }

    @Test
    fun `Parsing for warning or error messages`() {
        val lines = """
            ## error: something happened
            ## warning: just a warning
            ## unused
        """.trimIndent().lines()

        lines.forAny {
            TomlLine(TomlSection.Libraries, it).kind shouldBe Deletable
        }
    }

    @Test
    fun `Constructors for TomlLine`() {
        assertSoftly {
            TomlLine(TomlSection.Plugins, "org-jetbrains-kotlin-jvm", mapOf("id" to "org.jetbrains.kotlin.jvm", "version" to "1.6.10"))
                .text shouldBe """org-jetbrains-kotlin-jvm = { id = "org.jetbrains.kotlin.jvm", version = "1.6.10" }"""

            TomlLine(TomlSection.Libraries, "my-lib", "com.example:name:1.0")
                .text shouldBe """my-lib = "com.example:name:1.0""""

            val d = ConfigurationLessDependency("com.example:name:1.0")
            TomlLine(TomlSection.Libraries, "my-lib", d)
                .text shouldBe """my-lib = "com.example:name:1.0""""

            val noVersion = ConfigurationLessDependency("com.example:name")
            TomlLine(TomlSection.Libraries, "my-lib", noVersion)
                .text shouldBe """my-lib = { module = "com.example:name" }"""
        }
    }
}
