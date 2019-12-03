package de.fayard

import de.fayard.internal.PluginConfig
import de.fayard.internal.RefreshVersionsExtensionImpl
import de.fayard.versions.RefreshVersionsPropertiesTask
import de.fayard.versions.extensions.isBuildSrc
import de.fayard.versions.extensions.isRootProject
import de.fayard.versions.extensions.registerOrCreate
import de.fayard.versions.internal.setupVersionPlaceholdersResolving
import de.fayard.versions.internal.writeUsedDependencies
import de.fayard.versions.internal.writeUsedRepositories
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.ModuleVersionSelector
import org.gradle.kotlin.dsl.create
import java.util.*

open class RefreshVersionsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        check(project.isRootProject) {
            "ERROR: plugins de.fayard.refreshVersions must be applied to the root build.gradle(.kts)"
        }
        project.extensions.create(RefreshVersionsExtension::class, PluginConfig.EXTENSION_NAME, RefreshVersionsExtensionImpl::class)

        project.tasks.registerOrCreate<RefreshVersionsPropertiesTask>(name = PluginConfig.REFRESH_VERSIONS_UPDATER) {
            group = "Help"
            description = "Search for new dependencies versions and update versions.properties"
        }
        project.setupVersionPlaceholdersResolving()

        project.tasks.registerOrCreate<RefreshVersionsTask>(PluginConfig.REFRESH_VERSIONS) {
            group = "Help"
            description = "Search for available dependencies updates and update gradle.properties"
            outputs.upToDateWhen { false }
            dependsOn(PluginConfig.REFRESH_VERSIONS_UPDATER)
            configure {

            }
        }

        if (project.isBuildSrc) project.afterEvaluate {
            writeUsedDependencies()
            writeUsedRepositories()
        }
    }

}

private fun Project.useVersionsFromProperties() {
    @Suppress("UNCHECKED_CAST")
    val properties: Map<String, String> = Properties().apply {
        val propertiesFile = PluginConfig.VERSIONS_PROPERTIES
        if (file(propertiesFile).canRead().not()) return
        load(project.file(propertiesFile).reader())
    } as Map<String, String>

    val resolutionStrategyConfig = project.findProperty("resolutionStrategyConfig") as? String
    if (resolutionStrategyConfig == "false") return
    allprojects {
        val project: Project = this
        project.configurations.all {
            val configurationName = this.name
            if (configurationName.contains("copy")) return@all
            resolutionStrategy {
                eachDependency {
                    val candidate: ModuleVersionSelector = this.requested
                    val gradleProperty = PluginConfig.considerGradleProperties(candidate.group, candidate.name)
                        .firstOrNull { it in properties } ?: return@eachDependency
                    val message =
                        "ResolutionStrategy selected version=${properties[gradleProperty]} from property=$gradleProperty with for dependency=${candidate.group}:${candidate.name} in $configurationName"
                    if (resolutionStrategyConfig == "verbose") println(message)
                    useVersion(properties[gradleProperty] ?: error(message))
                }
            }
        }
    }
}
