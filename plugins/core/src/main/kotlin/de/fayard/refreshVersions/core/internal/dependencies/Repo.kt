package de.fayard.refreshVersions.core.internal.dependencies

import de.fayard.refreshVersions.core.extensions.gradle.passwordCredentials
import org.gradle.api.artifacts.repositories.MavenArtifactRepository
import java.net.URI

internal sealed class Repo {
    data class Maven(
        val url: URI,
        val passwordCredentials: PasswordCredentials?
    ) : Repo() {

        constructor(mavenArtifactRepository: MavenArtifactRepository) : this(
            url = mavenArtifactRepository.url,
            passwordCredentials = mavenArtifactRepository.passwordCredentials?.let {
                PasswordCredentials(
                    username = it.username ?: return@let null,
                    password = it.password ?: return@let null
                )
            }
        )

        data class PasswordCredentials(
            val username: String,
            val password: String
        )
    }
    data class Npm(
        val npmRegistry: String
    ) : Repo()
}
