package de.fayard.refreshVersions.core.extensions

import org.gradle.api.initialization.Settings

internal val Settings.isBuildSrc: Boolean get() = rootProject.name == "buildSrc"
