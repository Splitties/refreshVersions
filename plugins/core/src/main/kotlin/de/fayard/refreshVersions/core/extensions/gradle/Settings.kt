package de.fayard.refreshVersions.core.extensions.gradle

import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
import org.gradle.api.initialization.Settings

@InternalRefreshVersionsApi
val Settings.isBuildSrc: Boolean get() = rootProject.name == "buildSrc"
