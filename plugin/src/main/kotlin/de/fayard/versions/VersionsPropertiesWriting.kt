package de.fayard.versions

import de.fayard.versions.extensions.moduleIdentifier
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency

internal fun Project.updateVersionsProperties(dependenciesWithUpdate: Sequence<Pair<Dependency, String?>>) {
    val file = file("versions.properties")
    if (file.exists().not()) file.createNewFile()

    val properties: Map<String, String> = getVersionProperties()

    val newFileContent = buildString {
        appendln("# File header") //TODO: Use constant with actual content.
        appendln()
        dependenciesWithUpdate
            .mapNotNull { (dependency, lastVersionOrNull) ->
                dependency.moduleIdentifier?.getVersionPropertyName()?.let {
                    VersionWithUpdateIfAvailable(
                        key = it,
                        currentVersion = resolveVersion(properties, it) ?: return@mapNotNull null,
                        availableUpdateVersion = lastVersionOrNull
                    )
                }
            }
            .distinctBy { it.key }
            .sortedBy { it.key }
            .forEach {
                it.key.padStart(available.length + 1)
                val currentVersionLine = "${it.key}=${it.currentVersion}"
                appendln(currentVersionLine)
                it.availableUpdateVersion?.let { newVersion ->
                    append('#'); append(available.padStart(it.key.length - 1))
                    append('='); appendln(newVersion)
                }
            }
    }
    file.writeText(newFileContent)
}

private const val available = "# available"

private class VersionWithUpdateIfAvailable(
    val key: String,
    val currentVersion: String,
    val availableUpdateVersion: String?
)
