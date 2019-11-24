package de.fayard.dependencies.internal

import org.gradle.api.Action
import org.gradle.api.Task
import org.gradle.api.tasks.TaskContainer
import org.gradle.util.GradleVersion

internal inline fun <reified T : Task> TaskContainer.registerOrCreate(name: String) {
    if (supportsTaskRegistration) register(name, T::class.java) else create(name, T::class.java)
}

internal inline fun <reified T : Task> TaskContainer.registerOrCreate(name: String, action: Action<T>) {
    if (supportsTaskRegistration) register(name, T::class.java, action) else create(name, T::class.java, action)
}

private val supportsTaskRegistration = GradleVersion.current() >= GradleVersion.version("4.9")
