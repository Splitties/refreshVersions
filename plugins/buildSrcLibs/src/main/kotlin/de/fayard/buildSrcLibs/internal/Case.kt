package de.fayard.buildSrcLibs.internal

internal enum class Case {
    camelCase, snake_case; //, PascalCase, `kebab-case`

    companion object {
        internal fun toCamelCase(input: String): String = buildString {
            var wasWordBreak = false
            val wordBreaks = setOf(' ', '-', '_')
            for (c in input) {
                when {
                    c in wordBreaks -> {
                    }
                    wasWordBreak -> append(c.toUpperCase())
                    else -> append(c)
                }
                wasWordBreak = c in wordBreaks
            }
        }
    }
}
