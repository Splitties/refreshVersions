package de.fayard.versions.extensions

import org.gradle.api.Project

internal val Project.isRootProject: Boolean get() = this == rootProject
internal val Project.isBuildSrc: Boolean get() = isRootProject && name == "buildSrc"
