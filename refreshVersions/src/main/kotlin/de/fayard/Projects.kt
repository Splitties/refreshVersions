@file:JvmName("Projects")

package de.fayard

import org.gradle.api.Project

fun Project.envOrProperty(environment: String, property: String): String =
    System.getenv(environment) ?: findProperty(property) as String

