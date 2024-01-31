package de.fayard.refreshVersions.core.internal

import org.gradle.api.Task

@InternalRefreshVersionsApi
fun Task.skipConfigurationCache() {
    try {
        notCompatibleWithConfigurationCache("Task $name does not support Configuration Cache")
//        println("warning: task $name not compatible with the configuration cache.")
    } catch (t: Throwable) {
        // Catch in case the function is renamed/removed in future Gradle versions.
        println(t)
    }
}
