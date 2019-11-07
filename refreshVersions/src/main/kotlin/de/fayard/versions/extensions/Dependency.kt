package de.fayard.versions.extensions

import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ModuleIdentifier

internal val Dependency.moduleIdentifier: ModuleIdentifier?
    get() {
        val group = group ?: return null
        val name = name ?: return null
        return object : ModuleIdentifier {
            override fun getGroup(): String = group
            override fun getName(): String = name
        }
    }
