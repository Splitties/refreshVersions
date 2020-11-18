package de.fayard.refreshVersions.core

/**
 * A bug in refreshVersions can break people's builds.
 *
 * This is why we are prudent and hide new code behind feature flags
 * until the feature is deemed to be stable enough for production.
 *
 * The user can then opt-in to enable the in-development feature either on the commmand line
 *    $ ./gradle refreshVersions --enable FOO_EXPERIMENTAL
 *    $ ./gradle refreshVersions --disable FOO_OKISH
 *
 * Or permanently in the Settings file
 *   refreshVersions {
 *      enableFlags(FOO_EXPERIMENTAL, FOO_OKISH)
 *      disableFlags(FOO_STABLE)
 *   }
 *
 * Never delete a flag here since it would break the Settings file
 */
enum class FeatureFlag(val state: State) {
    // TODO: remove FOO_FLAGS before merging
    FOO_EXPERIMENTAL(State.DISABLED_BY_DEFAULT),
    FOO_OKISH(State.ENABLED_BY_DEFAULT),
    FOO_STABLE(State.ENABLED_ALWAYS),
    FOO_DELETED(State.DISABLED_ALWAYS),
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

    fun <T: Any> ifEnabled(block: () -> T) : T? {
        val shouldRun : Boolean = when(state) {
            State.DISABLED_BY_DEFAULT -> userSettings[this] == true
            State.ENABLED_BY_DEFAULT -> userSettings[this] != false
            State.ENABLED_ALWAYS -> true
            State.DISABLED_ALWAYS -> false
        }
        return if (shouldRun) block() else null
    }
}
