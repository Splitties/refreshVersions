package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.core.internal.VersionsCatalogs.LIBS_VERSIONS_TOML
import de.fayard.refreshVersions.internal.generateVersionsCatalogFromCurrentDependencies
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option

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
        checkOptions()
        generateVersionsCatalogFromCurrentDependencies(
            project = project,
            keepVersionsPlaceholders = keepVersionsPlaceholders,
            copyBuiltInDependencyNotationsToCatalog = copyBuiltInDependencyNotationsToCatalog
        )
    }
}
