package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.RefreshVersionsConfig

open class VersionExtension(private val config: RefreshVersionsConfig) {
    fun versionFor(versionKey: String): String {
        // This function is overloaded to allow named parameter usage in Kotlin.
        // However, no check is performed here because we cannot detect if
        // the function wasn't called with named argument.
        return retrieveVersionFor(config = config, dependencyNotationOrVersionKey = versionKey)
    }

    fun versionFor(dependencyNotation: CharSequence): String {
        // This function is overloaded to allow named parameter usage in Kotlin.
        // However, no check is performed here because we cannot detect if
        // the function wasn't called with named argument.
        return retrieveVersionFor(config = config, dependencyNotationOrVersionKey = dependencyNotation)
    }
}

