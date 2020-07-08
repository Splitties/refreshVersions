package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.ModuleId
import okhttp3.Credentials
import okhttp3.OkHttpClient
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.repositories.MavenArtifactRepository

internal operator fun DependencyVersionsFetcher.Companion.invoke(
    httpClient: OkHttpClient,
    dependency: Dependency,
    repository: MavenArtifactRepository // TODO: Support Ivy repositories
): DependencyVersionsFetcher? {
    val group = dependency.group ?: return null // TODO: Support NPM dependencies from Kotlin/JS
    val name = dependency.name
    return MavenDependencyVersionsFetcher(
        httpClient = httpClient,
        moduleId = ModuleId(group, name),
        repoUrl = repository.url.toString().let { if (it.endsWith('/')) it else "$it/" },
        repoAuthorization = repository.credentials.username?.let { username ->
            Credentials.basic(
                username = username,
                password = repository.credentials.password ?: return@let null
            )
        }
    )
}
