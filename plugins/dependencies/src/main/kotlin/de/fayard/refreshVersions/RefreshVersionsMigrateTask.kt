package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.addMissingEntriesInVersionsProperties
import de.fayard.refreshVersions.core.extensions.gradle.getVersionsCatalog
import de.fayard.refreshVersions.core.internal.InternalRefreshVersionsApi
import de.fayard.refreshVersions.core.internal.VersionsCatalogs
import de.fayard.refreshVersions.core.internal.associateShortestByMavenCoordinate
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.intellij.lang.annotations.Language
import java.io.File

open class RefreshVersionsMigrateTask : DefaultTask() {

    @InternalRefreshVersionsApi
    companion object {
        const val TASK_NAME = "refreshVersionsMigrate"
        const val DESCRIPTION = "Migrate build to refreshVersions"
    }

    @Input
    @Option(option = "toml", description = "Use libraries from ${VersionsCatalogs.LIBS_VERSIONS_TOML} before built-in dependency notations")
    var tomlFirst: Boolean = false

    @TaskAction
    fun refreshVersionsMissingEntries() {
        addMissingEntriesInVersionsProperties(project)
    }

    @TaskAction
    fun migrateBuild() {
        val versionsCatalogMapping: Map<ModuleId.Maven, String> =
            VersionsCatalogs.dependencyAliases(project.getVersionsCatalog())

        val builtInDependenciesMapping: Map<ModuleId.Maven, String> = getArtifactNameToConstantMapping()
            .associateShortestByMavenCoordinate()

        val dependencyMapping = if (tomlFirst) {
            builtInDependenciesMapping + versionsCatalogMapping
        } else {
            versionsCatalogMapping + builtInDependenciesMapping
        }

        val findFiles = findFilesWithDependencyNotations(project.rootDir).toSet()
        findFiles.forEach { buildFile ->
            migrateFileIfNeeded(buildFile, dependencyMapping)
        }
        project.allprojects {
            if (buildFile !in findFiles) {
                migrateFileIfNeeded(buildFile, dependencyMapping)
            }
        }
        println()
        println("""
            To find available updates, run this:

                $ANSI_GREEN./gradlew refreshVersions$ANSI_RESET
            """.trimIndent())
    }

}


//TODO: Don't replace random versions in build.gradle(.kts) files to avoid breaking plugins.
//TODO: Don't rely on a regex to extract the version so we detect absolutely any version string literal.
//TODO: Use CharSequence.findSymbolsRanges(…) to find the plugins block.
//TODO: Use CharSequence.findSymbolsRanges(…) to find the dependencies blocks.
//TODO: Use CharSequence.findSymbolsRanges(…) to find the implementation/api/etc calls.
// Might imply a function name matcher to support dynamic configuration names.
// Also, what about string based configuration names like: "iosApi"("blabla:blabla:1.0.0")?
//TODO: Use CharSequence.findRanges(…) to find the string literals to lookup for maven coordinates and versions.
//TODO: Consider bringing semi-automations for things we detect might need manual edits.
// Possible solutions for semi-automation:
// - Interactive task
// - separate CLI tool
// - FIXME/TODO comments insertion

internal fun migrateFileIfNeeded(file: File, dependencyMapping: Map<ModuleId.Maven, String>) {
    if (file.canRead().not()) return

    val isBuildFile = file.name.removeSuffix(".kts").endsWith(".gradle")
    val oldContent = file.readText()
    val newContent = oldContent.lines()
        .detectPluginsBlock()
        .joinToString(separator = "\n") { (line, isInsidePluginsBlock) ->
            withVersionPlaceholder(line, isInsidePluginsBlock, isBuildFile, dependencyMapping) ?: line
        }
    if (newContent != oldContent) {
        println("$ANSI_BLUE        modified:   $file$ANSI_RESET")
        file.writeText(newContent)
    }
}

internal const val ANSI_RESET = "\u001B[0m"
private const val ANSI_BLUE = "\u001B[34m"
internal const val ANSI_GREEN = "\u001B[36m"

@Language("RegExp")
private val versionRegex =
    "(['\":])(?:\\d+\\.){1,2}\\d+(?:[.-]?(?:alpha|beta|rc|eap|ALPHA|BETA|RC|EAP|RELEASE|Final|M)[-.]?\\d*)?([\"'])".toRegex()

@Language("RegExp")
private val variableRegex =
    "(['\":])(?:\\\$\\{?\\w+ersion}?|\\\$\\w*VERSION|\\\$\\{?(?:versions|rootProject)\\.\\w+}?)(?:[-.]?\\d*)?([\"'])".toRegex()

private val pluginVersionRegex =
    "[. ]version[. (]['\"](\\d+\\.){1,2}\\d+['\"]\\)?".toRegex()


/**
 * Returns the line with versions replaced with the version placeholder (`_`),
 * or null if no replacement is needed.
 */
internal fun withVersionPlaceholder(
    line: String,
    isInsidePluginsBlock: Boolean,
    isBuildFile: Boolean,
    dependencyMapping: Map<ModuleId.Maven, String> = emptyMap(),
): String? = when {
    isInsidePluginsBlock -> line.replace(pluginVersionRegex, "")
    isBuildFile -> when {
        mavenCoordinateRegex.containsMatchIn(line) -> {
            val coordinate = extractCoordinate(line)
            if (coordinate in dependencyMapping) {
                line.replace(mavenCoordinateRegex, dependencyMapping[coordinate]!!)
            } else {
                line.replace(mavenCoordinateRegex, "\$1:_\$2")
            }
        }
        else -> null
    }
    versionRegex.containsMatchIn(line) -> line.replace(versionRegex, "\$1_\$2")
    variableRegex.containsMatchIn(line) -> line.replace(variableRegex, "\$1_\$2")
    else -> null
}

private fun extractCoordinate(line: String): ModuleId.Maven {
    val coordinate = mavenCoordinateRegex.find(line)!!.value
        .replace("'", "")
        .replace("\"", "")

    val (group, name) = coordinate.split(":")

    return ModuleId.Maven(group = group, name = name)
}

private const val mavenChars = "[a-zA-Z0-9_.-]"
private const val versionChars = "[a-zA-Z0-9_.{}$-]"

@Language("RegExp")
private val mavenCoordinateRegex =
    "(['\"]$mavenChars{4,}:$mavenChars{2,})(?:|:_|:$versionChars{3,})([\"'])".toRegex()

internal fun findFilesWithDependencyNotations(fromDir: File): List<File> {
    require(fromDir.isDirectory) { "Expected a directory, got ${fromDir.absolutePath}" }
    val expectedNames = listOf("build", "build.gradle", "deps", "dependencies", "libs", "libraries", "versions")
    val expectedExtensions = listOf("gradle", "kts", "groovy", "kt")
    return fromDir.walkBottomUp()
        .onEnter { dir -> dir.name !in listOf("resources", "build") }
        .filter {
            it.extension in expectedExtensions && it.nameWithoutExtension.toLowerCase() in expectedNames
        }.toList()
}

/**
 * Takes a list of lines as input.
 * Returns the same lines, along with a boolean indicating if that line is inside a plugins block.
 */
internal fun List<String>.detectPluginsBlock(): List<Pair<String, Boolean>> {
    val result = mutableListOf<Pair<String, Boolean>>()
    var inBlock = false
    for (line in this) {
        if (line.replace("\\s+".toRegex(), " ").contains("plugins {")) {
            result += line to false
            inBlock = true
        } else if (inBlock && line.contains('}')) {
            result += line to false
            inBlock = false
        } else if (inBlock) {
            result += line to true
        } else {
            result += line to false
        }
    }
    return result
}
