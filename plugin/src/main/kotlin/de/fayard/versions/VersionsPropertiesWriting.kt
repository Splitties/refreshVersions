package de.fayard.versions

import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ModuleIdentifier

internal fun Project.updateVersionsProperties(dependenciesWithUpdate: Sequence<Pair<Dependency, String?>>) {
    val file = file("versions.properties")
    if (file.exists().not()) file.createNewFile()

    val properties: Map<String, String> = getVersionProperties()

    val newFileContent = buildString {
        appendln("File header")//TODO: Use constant with actual content.
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
            //TODO: Sort in a readability friendly way.
            .forEach {
                it.key.padStart(available.length + 1)
                val currentVersionSpacing = if (it.key.length < available.length) {
                    " "
                } else ""
                val currentVersionLine = "${it.key}=${it.currentVersion}"
                appendln(currentVersionLine)
                it.availableUpdateVersion?.let { newVersion ->
                    TODO("Have a first comment starter (#) before the pad and $available and the newVersion")
                }
            }
    }
    TODO("Define the parameters")
}

private const val available = "# available"

private class VersionWithUpdateIfAvailable(
    val key: String,
    val currentVersion: String,
    val availableUpdateVersion: String?
)

private val Dependency.moduleIdentifier: ModuleIdentifier?
    get() {
        val group = group ?: return null
        val name = name ?: return null
        return object : ModuleIdentifier {
            override fun getGroup(): String = group
            override fun getName(): String = name
        }
    }
