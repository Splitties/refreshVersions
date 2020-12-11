package de.fayard.refreshVersions.core

/**
 * A bug in refreshVersions can break people's builds.
 *
 * This is why we are prudent and hide new code behind feature flags
 * until the feature is deemed to be stable enough for production.
 *
 * The user can then opt-in to enable the in-development feature either on the commmand line
 *
 * ```bash
 * ./gradle refreshVersions --enable BUILD_SRC_LIBS
 * ./gradle refreshVersions --disable GRADLE_UPDATES
 * ```
 *
 * Or permanently in the Settings file
 *
 * ```kotlin
 * refreshVersions {
 *      featureFlags {
 *          enable(BUILD_SRC_LIBS)
 *          disable(GRADLE_UPDATES)
 *      }
 * }
 * ```
 *
 * Never delete a flag here since it would break the Settings file
 */
enum class FeatureFlag(val state: State) {
    GRADLE_UPDATES(State.ENABLED_BY_DEFAULT),
    BUILD_SRC_LIBS(State.DISABLED_BY_DEFAULT)
    ;

    companion object {
        val userSettings: MutableMap<FeatureFlag, Boolean> = mutableMapOf()
    }

    /**
     * Lifecycle of a feature flag:
     * it starts disabled by default as to not break people's build with an unstable feature
     * if it appears to work reliably, after discussion, it becomes enabled by default
     * when the feature is flag it can be always enabled, at this point we delete the old implementation
     * if the feature appeared to be a dead-end, we remove its implementation and mark it as always disabled
     */
    enum class State {
        DISABLED_BY_DEFAULT,
        ENABLED_BY_DEFAULT,
        ENABLED_ALWAYS,
        DISABLED_ALWAYS,
        ;
    }

    val isEnabled: Boolean
        get() = when (state) {
            State.DISABLED_BY_DEFAULT -> userSettings[this] == true
            State.ENABLED_BY_DEFAULT -> userSettings[this] != false
            State.ENABLED_ALWAYS -> true
            State.DISABLED_ALWAYS -> false
        }
}
