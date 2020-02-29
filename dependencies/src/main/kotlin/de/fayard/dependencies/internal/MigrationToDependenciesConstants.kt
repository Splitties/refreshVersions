package de.fayard.dependencies.internal

import de.fayard.versions.internal.ArtifactVersionKeyReader
import de.fayard.versions.internal.cli.AnsiColor
import de.fayard.versions.internal.cli.CliGenericUi
import de.fayard.versions.internal.getVersionProperties
import de.fayard.versions.internal.hasHardcodedVersion
import de.fayard.versions.internal.retrieveVersionKeyReader
import kotlinx.coroutines.*
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ExternalDependency
import kotlin.coroutines.coroutineContext

internal fun Configuration.shouldBeIgnored(): Boolean {
    return name.startsWith(prefix = "_internal") // Real-life example: _internal_aapt2_binary (introduced by AGP)
        || name in ignoredConfigurationNames || name.startsWith('-')
    //TODO: If unwanted configurations still get through, we can filter to known ones here, like
    // implementation, api, compileOnly, runtimeOnly, kapt, plus test, MPP and MPP test variants.
}

private val ignoredConfigurationNames = listOf(
    "kotlinCompilerPluginClasspath",
    "kotlinKaptWorkerDependencies",
    "lintClassPath"
)

//TODO: Ignore the following dependency: org.jetbrains.kotlin:kotlin-android-extensions-runtime

internal fun Configuration.countDependenciesWithHardcodedVersions(
    versionsProperties: Map<String, String>,
    versionKeyReader: ArtifactVersionKeyReader
): Int = dependencies.count { dependency ->
    dependency is ExternalDependency && dependency.hasHardcodedVersion(versionsProperties, versionKeyReader)
}

internal fun Project.countDependenciesWithHardcodedVersions(versionsProperties: Map<String, String>): Int {
    val versionKeyReader = retrieveVersionKeyReader()
    return configurations.sumBy { configuration ->
        if (configuration.shouldBeIgnored()) 0 else {
            configuration.countDependenciesWithHardcodedVersions(versionsProperties, versionKeyReader)
        }
    }
}

internal fun promptProjectSelection(rootProject: Project): Project? {
    require(rootProject == rootProject.rootProject) { "Expected a rootProject but got $rootProject" }
    val versionsProperties = rootProject.getVersionProperties()
    val projectsWithHardcodedDependenciesVersions: List<Pair<Project, Int>> = rootProject.allprojects.mapNotNull {
        val hardcodedDependenciesVersionsCount = it.countDependenciesWithHardcodedVersions(versionsProperties)
        if (hardcodedDependenciesVersionsCount > 0) {
            it to hardcodedDependenciesVersionsCount
        } else null
    }
    val cliGenericUi = CliGenericUi()
    val index = cliGenericUi.showMenuAndGetIndexOfChoice(
        header = "All the following modules have hardcoded dependencies versions",
        footer = "Type the number of the Gradle module you want to migrate first:",
        numberedEntries = projectsWithHardcodedDependenciesVersions.map { (project, hardcodedVersionsCount) ->
            "${project.path} ($hardcodedVersionsCount)"
        } + "Exit"
    )
    return projectsWithHardcodedDependenciesVersions.getOrNull(index.value)?.first
}

internal suspend fun runInteractiveMigrationToDependenciesConstants(project: Project) {
    val versionsProperties = project.rootProject.getVersionProperties()
    while (coroutineContext.isActive) {
        val selectedConfiguration = project.promptConfigurationSelection(versionsProperties) ?: return
        runConfigurationDependenciesMigration(
            project,
            versionsProperties,
            selectedConfiguration
        )
    }
}

private fun Project.promptConfigurationSelection(versionsProperties: Map<String, String>): Configuration? {
    @Suppress("UnstableApiUsage")
    val versionKeyReader = retrieveVersionKeyReader()
    val configurationsWithHardcodedDependenciesVersions = configurations.mapNotNull { configuration ->
        if (configuration.shouldBeIgnored()) return@mapNotNull null
        val count = configuration.countDependenciesWithHardcodedVersions(versionsProperties, versionKeyReader)
        return@mapNotNull if (count == 0) null else configuration to count
    }
    val cliGenericUi = CliGenericUi()
    val index = cliGenericUi.showMenuAndGetIndexOfChoice(
        header = "${AnsiColor.WHITE.boldHighIntensity}${AnsiColor.GREEN.background}" +
            "You selected project $path" +
            "${AnsiColor.RESET}\n" +
            "The following configurations have dependencies with hardcoded versions",
        footer = "Type the number of the configuration you want to migrate first:",
        numberedEntries = configurationsWithHardcodedDependenciesVersions.map { (configuration, count) ->
            "${configuration.name} ($count)"
        } + "Back"
    )
    return configurationsWithHardcodedDependenciesVersions.getOrNull(index.value)?.first
}
