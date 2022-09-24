package de.fayard.refreshVersions.core.internal

import de.fayard.refreshVersions.core.DependencyVersionsFetcher
import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.extensions.gradle.passwordCredentials
import de.fayard.refreshVersions.core.internal.npm.NpmDependencyVersionsFetcherHttp
import okhttp3.Credentials
import okhttp3.OkHttpClient
import org.gradle.api.artifacts.repositories.MavenArtifactRepository

internal fun DependencyVersionsFetcher.Companion.forMaven(
    httpClient: OkHttpClient,
    moduleId: ModuleId.Maven?,
    repository: MavenArtifactRepository // TODO: Support Ivy repositories
): DependencyVersionsFetcher? {
    moduleId ?: return null
    return when (repository.url.scheme) {
        "https", "http" -> MavenDependencyVersionsFetcherHttp(
            httpClient = httpClient,
            moduleId = moduleId,
            repoUrl = repository.url.toString().let { if (it.endsWith('/')) it else "$it/" },
            repoAuthorization = repository.passwordCredentials?.let { credentials ->
                Credentials.basic(
                    username = credentials.username ?: return@let null,
                    password = credentials.password ?: return@let null
                )
            }
        )
        "file" -> MavenDependencyVersionsFetcherFile(
            moduleId = moduleId,
            repoUrl = repository.url.toString().let { if (it.endsWith('/')) it else "$it/" }
        )
        "gcs" -> MavenDependencyVersionsFetcherGoogleCloudStorage(
            moduleId = moduleId,
            repoUrl = repository.url.toString()
        )
        else -> null //TODO: Support more transport protocols. Here's what Gradle supports:
        // https://docs.gradle.org/current/userguide/declaring_repositories.html#sec:supported_transport_protocols
        // We should trigger a warning that it's not supported yet, with link to relevant issue,
        // and we should report any transport protocol not known to be supported by Gradle (but not crash
        // in case a future Gradle version adds more of them and we're not updated yet to support it).
    }
}


internal fun DependencyVersionsFetcher.Companion.forNpm(
    httpClient: OkHttpClient,
    moduleId: ModuleId.Npm,
    npmRegistry: String = "https://registry.npmjs.org/"
): DependencyVersionsFetcher {
    return NpmDependencyVersionsFetcherHttp(
        httpClient = httpClient,
        moduleId = moduleId,
        repoUrl = npmRegistry
    )
}
