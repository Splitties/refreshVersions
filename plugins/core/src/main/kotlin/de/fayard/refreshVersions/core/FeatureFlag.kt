package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi

/**
 * A bug in refreshVersions can break people's builds.
 *
 * This is why we are prudent and hide new code behind feature flags
 * until the feature is deemed to be stable enough for production.
 *
 * The user can then opt-in to enable the in-development feature either on the command line
 *
 * ```bash
 * ./gradle refreshVersions --enable LIBS
 * ./gradle refreshVersions --disable GRADLE_UPDATES
 * ```
 *
 * Or permanently in the Settings file
 *
 * ```kotlin
 * refreshVersions {
 *      featureFlags {
 *          enable(LIBS)
 *          disable(GRADLE_UPDATES)
 *      }
 * }
 * ```
 *
 * Never delete a flag here since it would break the Settings file
 * Instead, mark it as @Deprecated("your message here")
 */
enum class FeatureFlag(private val enabledByDefault: Boolean?) {
    GRADLE_UPDATES(enabledByDefault = true),
    LIBS(enabledByDefault = false)
    ;

    companion object {
        /**
         * Where we store the settings coming from the command-line or the Settings file
         */
        @InternalRefreshVersionsApi
        val userSettings: MutableMap<FeatureFlag, Boolean> = mutableMapOf()
    }

    /**
     * Whether the flag is enabled once the user settings are set
     * Intended usage:
     * `if (GRADLE_UPDATES.isEnabled) lookupAvailableGradleVersions() else emptyList()`
     */
    internal val isEnabled: Boolean
        get() = when (enabledByDefault) {
            false -> userSettings[this] == true
            true -> userSettings[this] != false
            null -> false
        }
}
