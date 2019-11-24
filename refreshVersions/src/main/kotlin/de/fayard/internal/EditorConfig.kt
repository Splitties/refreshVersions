package de.fayard.internal

import java.io.File

// TODO: remove
data class Section(val name: String, val lines: MutableList<Pair<String, String>>) {
    operator fun get(key: String): String? {
        return lines.firstOrNull { it.first == key }?.second
    }
}

// TODO: remove
object EditorConfig {
    const val NAME = ".editorconfig"
    const val INDENT_STYLE = "indent_style"
    const val INDENT_SIZE = "indent_size"
    const val SPACE = "space"
    const val TAB = "tab"

    fun findIndentForKotlin(fromDir: File) : String? {
        return findEditorConfig(fromDir)
            .flatMap { file -> file.parseSections() }
            .findIndentForKotlinFiles()
    }

    fun List<Section>.findIndentForKotlinFiles() : String? {
        val section = this.sortedByDescending { it.priority() }
            .filter { s ->
                s[INDENT_STYLE] != null && s.priority() > 0
            }.firstOrNull()
        return section?.findIndent()
    }

    fun Section.findIndent() : String? {
        val size = this[INDENT_SIZE]?.toIntOrNull()
        val style = this[INDENT_STYLE]
        return when {
            style == TAB -> "\t"
            style == SPACE && size != null -> List(size) { " " }.joinToString(separator = "")
            else -> null
        }
    }


    fun File.isRootEditorConfig() : Boolean = when {
        name != NAME -> false
        this.exists().not() -> false
        else -> readLines().any { it.replace(" ", "").contains("root=true") }
    }

    fun Section.priority() = when {
        name.contains("kt") -> 4
        name.contains("gradle") -> 3
        name.contains("java") -> 2
        name == "*" -> 1
        else -> -1
    }

    fun File.parseSections(): List<Section> {
        assert(name == NAME)
        assert(canRead())
        val result = mutableListOf<Section>()
        var currentSection: Section? = null
        readLines().map { line ->
            line.substringBefore("#").substringBefore(";")
        }.forEach { line ->
            val section = line.parseSection()
            val split = line.split("=").map { it.trim() }
            when {
                section != null -> {
                    currentSection = Section(section, mutableListOf())
                    result.add(currentSection!!)
                }
                currentSection == null || line.isBlank() || split.size != 2 -> { }
                else -> currentSection!!.lines.add(split.first() to split.last())
            }
        }
        return result
    }

    fun String.parseSection() : String? {
        val section = this.substringAfter("[", "").substringBefore("]", "").trim()
        return if (section.isBlank()) null else section
    }

    fun findEditorConfig(fromDir: File) : List<File> {
        val result =  mutableListOf<File>()
        var current: File? = fromDir
        while (current != null) {
            val ec = current.resolve(NAME)
            if (ec.exists()) {
                assert(ec.name == NAME)
                result += ec
            }
            if (ec.isRootEditorConfig()) {
                break
            }
            current = current.parentFile
        }
        return result
    }
}
