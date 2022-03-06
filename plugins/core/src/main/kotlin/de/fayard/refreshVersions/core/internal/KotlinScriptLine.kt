package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.ModuleId

internal data class KotlinScriptLine(val line: String) {
    val kind: KotlinScriptKind = when {
        line.startsWith("////") -> KotlinScriptKind.Delete
        line.startsWith("@file:DependsOn") -> KotlinScriptKind.Dependency
        line.startsWith("@file:Repository") -> KotlinScriptKind.Repository
        else -> KotlinScriptKind.Ignore
    }

    fun moduleId() : ModuleId.Maven? {
        if (kind != KotlinScriptKind.Dependency) return null

        val input = line.substringBefore("//").trim()
            .removePrefix("@file:DependsOn(\"")
            .removeSuffix("\")")
        val (group, name) = input.split(":")
        return ModuleId.Maven(group, name)
    }

    fun repository(): String? {
        if (kind != KotlinScriptKind.Repository) return null

        return line
            .removePrefix("@file:Repository(\"")
            .substringBefore(" ")
            .removeSuffix("\")")
            .removeSuffix("/") + "/"
    }

    fun availableSpace() : String {
        val alignIndex = line.indexOfLast { it == ':' } // 53
        val minus = "////# available:".length           // 16
        val difference = alignIndex - minus             // 37
        return List(difference) { ' ' }.joinToString("")
        /**
@file:DependsOn("io.rsocket.kotlin:rsocket-core-jvm:0.13.0-SNAPSHOT")
////                                    # available:0.14.0")
                                    ////# available:0.14.0")
**/
    }
}

internal enum class KotlinScriptKind {
    Dependency, Repository, Ignore, Delete,
}