package extensions

import org.gradle.api.Project

fun Project.propertyOrEnv(key: String): String? {
    return findProperty(key) as String? ?: System.getenv(key)
}
