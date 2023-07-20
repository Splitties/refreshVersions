package de.fayard.refreshVersions.core.internal

/**
 * For refactorings we don't want to do right now but want to keep track of
 */
@InternalRefreshVersionsApi
annotation class NeedsRefactoring(
    val issueAndMessage: String,
)
