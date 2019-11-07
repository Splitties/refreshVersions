package de.fayard

import de.fayard.internal.EditorConfig
import de.fayard.internal.Section
import io.kotlintest.matchers.haveSize
import io.kotlintest.matchers.withClue
import io.kotlintest.should
import io.kotlintest.shouldBe
import io.kotlintest.specs.FreeSpec
import io.kotlintest.tables.forAll
import io.kotlintest.tables.headers
import io.kotlintest.tables.row
import io.kotlintest.tables.table
import java.io.File

class EditorConfigTest: FreeSpec({
    val testFolder = File("src/test/resources").absoluteFile

    "Project Dir" {
        withClue(testFolder.absolutePath) {
            testFolder.name shouldBe "resources"
        }
    }

    "Find files" {
        val table = table(
            headers("path", "expected size"),
            row("a/b/c", 3),
            row("a/b", 2),
            row("a", 1),
            row("libs", 2)
        )

        table.forAll { path, expectedSize ->
            val list = EditorConfig.findEditorConfig(testFolder.resolve(path))
            withClue(list.toString()) { list should haveSize(expectedSize) }
        }
    }

    "Sections" {
        with(EditorConfig) {
            val sections = testResourceFile("a/b/.editorconfig").parseSections()
            withClue(sections.toString()) {
                sections.size shouldBe 2
                val (kotlin, python) = sections
                python shouldBe Section("*.py", mutableListOf("indent_style" to "tab", "insert_final_newline" to "true"))
                kotlin shouldBe Section("*.kt", mutableListOf("indent_style" to "space", "indent_size" to "4"))
                kotlin[INDENT_STYLE] shouldBe "space"
                kotlin[INDENT_SIZE] shouldBe "4"
                kotlin["insert_final_newline"] shouldBe null
            }
        }
    }

    with(EditorConfig) {
        "Parse Indents" - {
            "Tabs" {
                val section = Section("*", mutableListOf(INDENT_STYLE to TAB, "key" to "value"))
                section.findIndent() shouldBe "\t"
            }
            "Four spaces" {
                val section = Section("*", mutableListOf(INDENT_STYLE to SPACE, "key" to "value", INDENT_SIZE to "4"))
                section.findIndent() shouldBe "    "
            }
            "Two spaces" {
                val section = Section("*", mutableListOf(INDENT_STYLE to SPACE, "key" to "value", INDENT_SIZE to "2"))
                section.findIndent() shouldBe "  "
            }
            "space no size: invalid" {
                val section = Section("*", mutableListOf(INDENT_STYLE to SPACE, "key" to "value"))
                section.findIndent() shouldBe null
            }
            "no information" {
                val section = Section("*", mutableListOf("key" to "value"))
                section.findIndent() shouldBe null
            }
        }
    }

    "Find indentation for Kotlin" {
        val table = table(
            headers("path", "indentation"),
            row("libs", "  "),
            row("a", "   "),
            row("a/b", "    "),
            row("a/b/c", "    "),
            row("maven", null)
        )
        table.forAll { path, expectedIndent ->
            EditorConfig.findIndentForKotlin(testFolder.resolve(path)) shouldBe expectedIndent
        }
    }
})
