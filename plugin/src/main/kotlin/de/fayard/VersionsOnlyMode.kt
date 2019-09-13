package de.fayard

import de.fayard.UpdateVersionsOnly.doubleQuote
import de.fayard.UpdateVersionsOnly.singleQuote
import de.fayard.UpdateVersionsOnly.slashslash

enum class VersionsOnlyMode {
    KOTLIN_VAL,
    KOTLIN_OBJECT,
    GROOVY_DEF,
    GROOVY_EXT,
    GRADLE_PROPERTIES;

    val quote: String get() = when(this) {
        KOTLIN_VAL,
        KOTLIN_OBJECT -> doubleQuote
        GROOVY_DEF,
        GROOVY_EXT -> singleQuote
        GRADLE_PROPERTIES -> ""
    }

    fun suggestedFilename(): String = when(this) {
        KOTLIN_VAL -> "build.gradle.kts"
        KOTLIN_OBJECT -> "Versions.kt"
        GROOVY_DEF -> "build.gradle"
        GROOVY_EXT -> "build.gradle"
        GRADLE_PROPERTIES -> "gradle.properties"
    }

    val comment: String get() = when(this) {
        GRADLE_PROPERTIES -> "#"
        KOTLIN_VAL,
        GROOVY_DEF,
        KOTLIN_OBJECT,
        GROOVY_EXT -> slashslash
    }

    val defaultIndent: String get() = when(this) {
        GRADLE_PROPERTIES -> PluginConfig.SPACES0
        GROOVY_EXT,
        KOTLIN_VAL,
        KOTLIN_OBJECT,
        GROOVY_DEF -> PluginConfig.SPACES4
    }

    fun beforeAfter(): Pair<String?, String?> = when(this) {
        GROOVY_EXT -> Pair("ext {", "}")
        KOTLIN_VAL,
        KOTLIN_OBJECT,
        GROOVY_DEF,
        GRADLE_PROPERTIES -> Pair(null, null)
    }
}

