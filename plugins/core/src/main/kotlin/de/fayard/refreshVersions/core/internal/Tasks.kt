package de.fayard.refreshVersions.core.internal

import org.gradle.api.Task
import kotlin.reflect.full.memberFunctions

@InternalRefreshVersionsApi
fun Task.skipConfigurationCache() {
    this::class.memberFunctions
        .firstOrNull { it.name == "notCompatibleWithConfigurationCache" }
        ?.call(this, "Task $name does not support Configuration Cache")
        ?: run {
            println("warning: task $name not compatible with the configuration cache.")
        }
}