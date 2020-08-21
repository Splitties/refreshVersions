package de.fayard.refreshVersions.core.extensions.gradle

import de.fayard.refreshVersions.core.ModuleId
import org.gradle.api.artifacts.ModuleIdentifier

internal val ModuleIdentifier.isGradlePlugin: Boolean
    get() = name.endsWith(".gradle.plugin")

@Suppress("nothing_to_inline")
internal inline fun ModuleIdentifier.toModuleId() = ModuleId(group = group, name = name)

internal fun ModuleId.toModuleIdentifier(): ModuleIdentifier = object : ModuleIdentifier {
    override fun getGroup(): String = this@toModuleIdentifier.group!!
    override fun getName(): String = this@toModuleIdentifier.name
}
