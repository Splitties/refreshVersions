package de.fayard

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import de.fayard.internal.PluginConfig
import de.fayard.internal.PluginConfig.isNonStable
import de.fayard.internal.PluginsSetup
import de.fayard.internal.RefreshVersionsExtensionImpl
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.ModuleVersionSelector
import org.gradle.api.tasks.TaskProvider
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.create
import java.util.Properties


open class RefreshVersionsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        check(project == project.rootProject) { "ERROR: plugins de.fayard.refreshVersions must be applied to the root build.gradle(.kts)" }
        project.apply(plugin = PluginConfig.GRADLE_VERSIONS_PLUGIN_ID)
        project.configure()
        project.useVersionsFromProperties()
    }

    fun Project.configure() = with(PluginConfig) {
        PluginsSetup.copyPluginsGradleKtsIfNeeded(project)

        extensions.create(RefreshVersionsExtension::class, EXTENSION_NAME, RefreshVersionsExtensionImpl::class)

        if (supportsTaskAvoidance()) {
            val provider: TaskProvider<DependencyUpdatesTask> = when {
                tasks.findByPath(DEPENDENCY_UPDATES_PATH) == null -> tasks.register(DEPENDENCY_UPDATES_PATH, DependencyUpdatesTask::class.java)
                else -> tasks.named(DEPENDENCY_UPDATES, DependencyUpdatesTask::class.java)
            }

            configureGradleVersions = { operation -> provider.configure(operation) }
            configureGradleVersions(DependencyUpdatesTask::configureBenManesVersions)

            tasks.register(REFRESH_VERSIONS, RefreshVersionsTask::class.java, RefreshVersionsTask::configureRefreshVersions)

        } else {
            val dependencyUpdatesTask = tasks.maybeCreate(DEPENDENCY_UPDATES, DependencyUpdatesTask::class.java)
            configureGradleVersions = { operation -> dependencyUpdatesTask.operation() }
            configureGradleVersions(DependencyUpdatesTask::configureBenManesVersions)

            tasks.create(REFRESH_VERSIONS, RefreshVersionsTask::class, RefreshVersionsTask::configureRefreshVersions)
        }
    }
}

fun Project.useVersionsFromProperties() {
    @Suppress("UNCHECKED_CAST")
    val properties: Map<String, String> = Properties().apply {
        val propertiesFile = listOf("versions.properties", "gradle.properties").firstOrNull { project.file(it).canRead() } ?: return
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


fun DependencyUpdatesTask.configureBenManesVersions() {
    rejectVersionIf { isNonStable(candidate.version) }
    checkForGradleUpdate = true
    outputFormatter = "json"
}

fun RefreshVersionsTask.configureRefreshVersions() {
    group = "Help"
    description = "Search for available dependencies updates and update gradle.properties"
    dependsOn(PluginConfig.DEPENDENCY_UPDATES_PATH)
    outputs.upToDateWhen { false }
    configure {
        propertiesFile = PluginConfig.DEFAULT_PROPERTIES_FILE
        alignVersionsForGroups = mutableListOf()
    }
}
