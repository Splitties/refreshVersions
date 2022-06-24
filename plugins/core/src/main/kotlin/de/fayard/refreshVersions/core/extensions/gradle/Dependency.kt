package de.fayard.refreshVersions.core.extensions.gradle

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ExternalDependency

@InternalRefreshVersionsApi
fun Dependency.moduleId(): ModuleId? = when {
    this is ExternalDependency -> mavenModuleId()
    this::class.simpleName == "NpmDependency" -> npmModuleId()
    else -> null
}

internal fun Dependency.mavenModuleId(): ModuleId.Maven? {
    return ModuleId.Maven(group ?: return null, name)
}

internal fun Dependency.npmModuleId(): ModuleId.Npm {
    val scope: String? = name.substringBefore('/').substringAfter('@').takeUnless { it == name }
    val nameWithoutScope = name.substringAfter('/')
    return ModuleId.Npm(scope, nameWithoutScope)
}
