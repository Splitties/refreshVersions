package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.internal.Toml
import de.fayard.refreshVersions.core.internal.TomlLine
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.Available
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.Ignore
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.Libs
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.LibsUnderscore
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.LibsVersionRef
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.Plugin
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.PluginVersionRef
import de.fayard.refreshVersions.core.internal.TomlLine.Kind.Version
import io.kotest.core.spec.style.FunSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.shouldBe
import java.io.File

class TomlLineTest : FunSpec({


    test("Parsing kind for libraries") {

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
            Libs, Available, Ignore,
            Libs, Ignore,
            Libs, Ignore,
        )

        val testCases = lines.zip(expectedKinds)

        testCases.forAll { (line, expectedKind) ->
            TomlLine(TomlLine.Section.libraries, line).kind shouldBe expectedKind
        }
    }

    test("Parsing kind for versions") {

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
            Version, Available, Available, Ignore,
            Version, Version,
        )

        val testCases = lines.zip(expectedKinds)

        testCases.forAll { (line, expectedKind) ->
            TomlLine(TomlLine.Section.versions, line).kind shouldBe expectedKind
        }
    }

    test("Parsing kind for plugins") {

        val lines = """
            short-notation = "some.plugin.id:1.4"
            ##                  # available:"1.5"
            
            # yet another comment
            long-notation = { id = "some.plugin.id", version = "1.4" }
            reference-notation = { id = "some.plugin.id", version.ref = "common" }    
        """.trimIndent().lines()

        val expectedKinds: List<TomlLine.Kind> = listOf(
            Plugin, Available, Ignore, Ignore,
            Plugin, PluginVersionRef
        )

        val testCases = lines.zip(expectedKinds)

        testCases.forAll { (line, expectedKind) ->
            TomlLine(TomlLine.Section.plugins, line).kind shouldBe expectedKind
        }
    }

    test("Parsing libraries values") {
        fun map(group: String, name: String, version: String?, versionRef: String?) =
            listOfNotNull("group" to group, "name" to name, version?.let { "version" to it }, versionRef?.let { "version.ref" to it })
                .toMap()

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
            TomlLine(TomlLine.Section.libraries, line).map shouldBe map
        }

    }

    test("Parsing plugins values") {
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
            TomlLine(TomlLine.Section.plugins, line).map shouldBe map
        }
    }

    test("Parse whole file") {
        val fileText = File("src/test/resources/toml-happy-path/initial.libs.toml").readText()
        Toml.parseTomlInSection(fileText).forEach { (key, text) ->
            val section = try {
                TomlLine.Section.valueOf(key)
            } catch (e: IllegalArgumentException) {
                TomlLine.Section.others
            }
            text.lines().forAll { text ->
                val line = TomlLine(section, text)
                println(line)
            }
        }
    }


})


