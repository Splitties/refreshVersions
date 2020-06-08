package de.fayard.refreshVersions.core.extensions

import org.gradle.api.artifacts.ModuleIdentifier

internal val ModuleIdentifier.isGradlePlugin: Boolean
    get() = name.endsWith(".gradle.plugin")
