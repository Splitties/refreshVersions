package de.fayard.refreshVersions.core.extensions.gradle

import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
import org.gradle.api.Project
import org.gradle.api.UnknownDomainObjectException
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal val Project.isRootProject: Boolean get() = this == rootProject

internal val Project.isBuildSrc: Boolean get() = isRootProject && name == "buildSrc"

@InternalRefreshVersionsApi
fun Project.getVersionsCatalog() = try {
    project.extensions.getByType<VersionCatalogsExtension>().named("libs")
} catch (e: UnknownDomainObjectException) {
    null
}