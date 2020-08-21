package de.fayard.refreshVersions.core.extensions.gradle

import org.gradle.api.artifacts.repositories.AuthenticationSupported
import org.gradle.api.artifacts.repositories.PasswordCredentials

val AuthenticationSupported.passwordCredentials: PasswordCredentials?
    get() = try {
        credentials
    } catch (e: IllegalStateException) {
        null
    }
