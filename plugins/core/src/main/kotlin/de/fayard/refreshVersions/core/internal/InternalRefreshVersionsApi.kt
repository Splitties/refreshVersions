package de.fayard.refreshVersions.core.internal

/**
 * Marks declarations that are **internal** to refreshVersions, which means they should not be used outside
 * refreshVersions modules, because their signatures and semantics can **(will) change** between future releases,
 * without any warnings and without providing any migration aids.
 */
@RequiresOptIn
annotation class InternalRefreshVersionsApi
