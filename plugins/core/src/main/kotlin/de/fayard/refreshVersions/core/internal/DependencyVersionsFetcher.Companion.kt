package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.extensions.gradle.passwordCredentials
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
    when (repository.url.scheme) {
        "https", "file" -> Unit // Supported
        "gcs" -> return MavenDependencyVersionsFetcherGCS(
            moduleId = ModuleId(group, name),
            repoUrl = repository.url.toString()
        )
        "http" -> return null //TODO: Show non fatal error that http is not supported for security reasons.
        else -> return null //TODO: Support more transport protocols. Here's what Gradle supports:
        // https://docs.gradle.org/current/userguide/declaring_repositories.html#sec:supported_transport_protocols
        // We should trigger a warning that it's not supported yet, with link to relevant issue,
        // and we should report any transport protocol not known to be supported by Gradle (but not crash
        // in case a future Gradle version adds more of them and we're not updated yet to support it).
    }
    return MavenDependencyVersionsFetcher(
        httpClient = httpClient,
        moduleId = ModuleId(group, name),
        repoUrl = repository.url.toString().let { if (it.endsWith('/')) it else "$it/" },
        repoAuthorization = when (repository.name) {
            "MavenLocal" -> null // MavenLocal never needs authorization.
            // Getting credentials on mavenLocal results in insanely
            // hard to  diagnose error that happens later with an unrelated stacktrace.
            // See this issue: https://github.com/jmfayard/refreshVersions/issues/222
            // The error message would be the following:
            // "Authentication scheme 'all'(Authentication) is not supported by protocol 'file'"
            else -> repository.passwordCredentials?.let { credentials ->
                Credentials.basic(
                    username = credentials.username ?: return@let null,
                    password = credentials.password ?: return@let null
                )
            }
        }
    )
}
