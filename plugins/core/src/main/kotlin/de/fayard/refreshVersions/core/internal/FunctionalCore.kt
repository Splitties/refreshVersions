package de.fayard.refreshVersions.core.internal

/**
 * FunctionalCore/ImperativeShell is a simple but powerful testing strategy
 * where you express your business logic as pure functions
 * and you use the tested functions in a simple imperative shell that actually do things.
 *
 * In practical terms, a function annotated by @FunctionalCore must  be tested
 *
 * See https://www.destroyallsoftware.com/screencasts/catalog/functional-core-imperative-shell
 */
@InternalRefreshVersionsApi
annotation class FunctionalCore(
    val testName: String,
)
