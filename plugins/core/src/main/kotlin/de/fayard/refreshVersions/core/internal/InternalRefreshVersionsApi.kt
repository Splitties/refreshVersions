package de.fayard.refreshVersions.core.internal

/**
 * Marks declarations that are **internal** to refreshVersions, which means it should not be used outside of
 * the refreshVersions and its companion dependencies plugins, because their signatures and semantics can **(will)
 * change** between future releases without any warnings and without providing any migration aids.
 */
@RequiresOptIn
annotation class InternalRefreshVersionsApi
