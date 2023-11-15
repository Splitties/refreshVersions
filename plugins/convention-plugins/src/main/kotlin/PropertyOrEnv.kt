import org.gradle.api.Project

internal fun Project.propertyOrEnv(key: String): String {
    return findProperty(key) as String?
        ?: System.getenv(key)
        ?: error("Didn't find any value for the key \"$key\" in Project properties or environment variables.")
}

internal fun Project.propertyOrEnvOrNull(key: String): String? {
    return findProperty(key) as String? ?: System.getenv(key)
}
