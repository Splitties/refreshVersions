package de.fayard.refreshVersions.internal

import de.fayard.refreshVersions.core.internal.*
import de.fayard.refreshVersions.core.internal.cli.AnsiColor
import de.fayard.refreshVersions.core.internal.cli.CliGenericUi
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.ExternalDependency
import org.gradle.api.artifacts.ModuleIdentifier

internal fun runConfigurationDependenciesMigration(
    project: Project,
    versionsMap: Map<String, String>,
    configuration: Configuration
) {
    configuration.dependencies.forEach { dependency ->
        if (dependency !is ExternalDependency) return@forEach
        project.attemptDependencyMigration(versionsMap, dependency)
    }
}

private val artifactNameToConstantMapping: List<DependencyMapping> by lazy {
    getArtifactNameToConstantMapping()
}

private fun DependencyMapping.matches(dependency: ExternalDependency): Boolean {
    return group == dependency.group && artifact == dependency.name
}

private fun Project.attemptDependencyMigration(
    versionsMap: Map<String, String>,
    dependency: ExternalDependency
) {
    val versionKeyReader = RefreshVersionsConfigHolder.versionKeyReader

    if (dependency.hasHardcodedVersion(versionsMap, versionKeyReader).not()) return
    val currentVersion = dependency.version ?: return

    val availableDependenciesConstants = artifactNameToConstantMapping.mapNotNull { dependencyMapping ->
        if (dependencyMapping.matches(dependency)) {
            dependencyMapping.constantName
        } else null
    }
    val done: Boolean = when (availableDependenciesConstants.size) {
        0 -> offerReplacingHardcodedVersionWithPlaceholder(dependency.module)
        else -> offerReplacingHardcodedVersionWithConstantOrPlaceholder(
            moduleIdentifier = dependency.module,
            constants = availableDependenciesConstants
        )
    }
    if (done.not()) return
    val versionKey = getVersionPropertyName(dependency.module, versionKeyReader)
    writeCurrentVersionInProperties(
        versionKey = versionKey,
        currentVersion = currentVersion
    )
    logAddedVersionsKey(versionKey)
}

private fun offerReplacingHardcodedVersionWithPlaceholder(moduleIdentifier: ModuleIdentifier): Boolean {
    val genericUi = CliGenericUi()
    val group = moduleIdentifier.group
    val name = moduleIdentifier.name
    val stringLiteralWithVersionPlaceholder = "\"$group:$name:_\""
    println()
    println("    $stringLiteralWithVersionPlaceholder")
    return genericUi.askBinaryQuestion(
        question = "Please, replace the hardcoded version with the version placeholder in dependency declaration,\n" +
            "i.e. the underscore (_).",
        trueChoice = "Done",
        falseChoice = "Skip"
    )
}

private fun offerReplacingHardcodedVersionWithConstantOrPlaceholder(
    moduleIdentifier: ModuleIdentifier,
    constants: List<String>
): Boolean {
    require(constants.isNotEmpty())
    val genericUi = CliGenericUi()
    val group = moduleIdentifier.group
    val name = moduleIdentifier.name
    val stringLiteralWithVersionPlaceholder = "\"$group:$name:_\""
    println()
    (constants + stringLiteralWithVersionPlaceholder).forEach {
        println("    $it")
    }
    println()
    return genericUi.askBinaryQuestion(
        question = "Please, copy paste one of the expressions above in place of the previous dependency declaration.",
        trueChoice = "Done",
        falseChoice = "Skip"
    )
}

private fun logAddedVersionsKey(versionKey: String) {
    val versionsFileName = RefreshVersionsConfigHolder.versionsPropertiesFile.name
    println("Moved the current version to the $versionsFileName file under the following key:")
    print(AnsiColor.WHITE.boldHighIntensity)
    print(AnsiColor.YELLOW.background)
    print(versionKey)
    println(AnsiColor.RESET)
}

private fun Project.attemptAutoReplaceDependencyInBuildFile(
    moduleIdentifier: ModuleIdentifier,
    replacement: String
): Boolean {
    val buildFileText = buildFile.readText()
    if (buildFile.extension == "kts") {
        TODO("Kotlin DSL")
    } else {
        TODO("Groovy DSL")
    }
    val expectedDependencyNotation: String =
        TODO("Support multiple styles, including version variables with different styles?")
    buildFileText.contains(expectedDependencyNotation)
}
