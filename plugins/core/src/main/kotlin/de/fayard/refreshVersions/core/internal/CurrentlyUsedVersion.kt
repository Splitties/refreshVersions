package de.fayard.refreshVersions.core.internal

import org.gradle.api.initialization.Settings

@InternalRefreshVersionsApi
fun Settings.currentVersionOfRefreshVersions(): String {
    return buildscript.configurations.flatMap { it.dependencies }.single {
        it.group == "de.fayard.refreshVersions"
    }.version!!
}
