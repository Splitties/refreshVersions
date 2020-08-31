package de.fayard.refreshVersions.core.internal

import org.gradle.api.initialization.Settings

@InternalRefreshVersionsApi
fun Settings.currentVersionOfRefreshVersions(): String {
    return "0.9.6-SNAPSHOT"
}
