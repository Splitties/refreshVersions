package de.fayard.refreshVersions.core.extensions.gradle

import de.fayard.refreshVersions.core.ModuleId
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ModuleIdentifier

internal val Dependency.moduleId: ModuleId get() = ModuleId(group, name)

internal val Dependency.moduleIdentifier: ModuleIdentifier?
    get() {
        val group = group ?: return null
        val name = name
        return object : ModuleIdentifier {
            override fun getGroup(): String = group
            override fun getName(): String = name
            override fun toString(): String = "${getGroup()}:${getName()}"
        }
    }

internal val Dependency.isGradlePlugin: Boolean
    get() = name.endsWith(".gradle.plugin")
