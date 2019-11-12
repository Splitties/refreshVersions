package de.fayard.versions.extensions

import org.gradle.api.Project

internal inline val Project.isBuildSrc: Boolean get() = name == "buildSrc"
