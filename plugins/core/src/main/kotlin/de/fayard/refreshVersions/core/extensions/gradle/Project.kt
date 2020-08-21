package de.fayard.refreshVersions.core.extensions.gradle

import org.gradle.api.Project

internal val Project.isRootProject: Boolean get() = this == rootProject
internal val Project.isBuildSrc: Boolean get() = isRootProject && name == "buildSrc"
