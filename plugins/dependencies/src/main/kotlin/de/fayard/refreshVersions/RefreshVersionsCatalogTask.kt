package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.addMissingEntriesInVersionsProperties
import de.fayard.refreshVersions.core.internal.Case
import de.fayard.refreshVersions.core.internal.Deps
import de.fayard.refreshVersions.core.internal.Library
import de.fayard.refreshVersions.core.internal.MEANING_LESS_NAMES
import de.fayard.refreshVersions.core.internal.OutputFile
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.core.internal.UsedPluginsTracker
import de.fayard.refreshVersions.core.internal.VersionsCatalogs
import de.fayard.refreshVersions.core.internal.VersionsCatalogs.LIBS_VERSIONS_TOML
import de.fayard.refreshVersions.core.internal.checkModeAndNames
import de.fayard.refreshVersions.core.internal.computeAliases
import de.fayard.refreshVersions.core.internal.findDependencies
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import org.gradle.api.DefaultTask
import org.gradle.api.GradleException
import org.gradle.api.artifacts.Dependency
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.gradle.util.GradleVersion

open class RefreshVersionsCatalogTask : DefaultTask() {

    @Input
    @Option(
        option = "versions-keep-placeholders",
        description = "Keep versions placeholders in $LIBS_VERSIONS_TOML and don't move versions into the catalog."
    )
    var keepVersionsPlaceholders: Boolean = false

    @Input
    @Option(
        option = "versions-move-to-catalog",
        description = "Move versions into $LIBS_VERSIONS_TOML instead of keeping versions placeholders."
    )
    var moveVersionsToCatalog: Boolean = false

    @Input
    @Option(
        option = "copy-built-in-dependency-notations-to-catalog",
        description = "Copy built-in dependency notations to $LIBS_VERSIONS_TOML"
    )
    var copyBuiltInDependencyNotationsToCatalog: Boolean = false

    private fun checkOptions() {
        if (keepVersionsPlaceholders == moveVersionsToCatalog) {
            val versionsPropertiesFile = RefreshVersionsConfigHolder.versionsPropertiesFile
            val errorMessage = """
                |You need to specify exactly one of the following 2 flags:
                |--versions-keep-placeholders, if you want to keep the versions in the ${versionsPropertiesFile.name} file
                |--versions-move-to-catalog, if you want to have all the versions in the $LIBS_VERSIONS_TOML file
            """.trimMargin()
            throw IllegalArgumentException(errorMessage)
        }
    }

    @TaskAction
    fun refreshVersionsCatalogAction() {
        if (VersionsCatalogs.isSupported().not()) {
            throw GradleException(
                """
                |Gradle versions catalogs are not supported in ${GradleVersion.current()}
                |Upgrade Gradle with this command
                |     ./gradlew wrapper --gradle-version ${VersionsCatalogs.minimumGradleVersion.version}
            """.trimMargin()
            )
        }
        checkOptions()

        // Update versions.properties
        addMissingEntriesInVersionsProperties(project)

        // Generate LIBS_VERSIONS_TOML
        val catalog = OutputFile.GRADLE_VERSIONS_CATALOG

        val builtInDependencies = getArtifactNameToConstantMapping()

        val allDependencies: List<Library> = project.findDependencies()

        val dependenciesToUse = when {
            copyBuiltInDependencyNotationsToCatalog -> allDependencies
            else -> allDependencies.filter {
                builtInDependencies.none { builtInDependency ->
                    builtInDependency.group == it.group && builtInDependency.artifact == it.name
                }
            }
        }

        val plugins = UsedPluginsTracker.usedPluginsWithoutEntryInVersionsFile +
            UsedPluginsTracker.read().map { it.first }

        val versionCatalogAliases: List<String> = dependenciesToUse.computeAliases(
            configured = emptyList(),
            byDefault = MEANING_LESS_NAMES
        )

        val deps: Deps = dependenciesToUse.checkModeAndNames(versionCatalogAliases, Case.`kebab-case`)
        val dependenciesAndNames: Map<Dependency, String> = deps.names.mapKeys { it.key.toDependency() }

        val currentText = if (catalog.existed) catalog.readText() else ""
        val newText = VersionsCatalogs.generateVersionsCatalogText(
            dependenciesAndNames = dependenciesAndNames,
            currentText = currentText,
            moveVersionsToCatalog = moveVersionsToCatalog,
            plugins = plugins
        )
        catalog.writeText(newText)
        catalog.logFileWasModified()

        println(
            """
            You can now automatically migrate your build.gradle/build.gradle.kts file with the command:

                $ANSI_GREEN./gradlew refreshVersionsMigrate$ANSI_RESET
        """.trimIndent()
        )
    }
}
