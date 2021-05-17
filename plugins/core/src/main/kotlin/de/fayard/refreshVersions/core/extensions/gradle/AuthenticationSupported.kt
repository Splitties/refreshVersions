package de.fayard.refreshVersions.core.extensions.gradle

import org.gradle.api.artifacts.repositories.AuthenticationSupported
import org.gradle.api.artifacts.repositories.PasswordCredentials
import org.gradle.internal.artifacts.repositories.AuthenticationSupportedInternal

val AuthenticationSupported.passwordCredentials: PasswordCredentials?
    //TODO: Remove this workaround for newer Gradle versions when the following issue is fixed:
    // https://github.com/gradle/gradle/issues/14694
    get() = runCatching {
        // We use runCatching to avoid crashing the build if the internal APIs change.
        (this as AuthenticationSupportedInternal?)?.configuredCredentials?.orNull as? PasswordCredentials
    }.getOrNull()
