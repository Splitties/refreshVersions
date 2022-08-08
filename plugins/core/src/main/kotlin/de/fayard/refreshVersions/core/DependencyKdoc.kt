package de.fayard.refreshVersions.core

import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi

@InternalRefreshVersionsApi
class DependencyKdoc(
    val title: String,
    val website: String?, // documentation website, not the GitHub URL
    val gitUrl: String?, // could also be called vcsUrl. alas, everybody uses git
    val changelogUrl: String? = null,
    val description: String = "",
)
