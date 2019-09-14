package de.fayard

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import de.fayard.internal.BuildSrcVersionsExtensionImpl
import de.fayard.internal.PluginConfig
import de.fayard.internal.PluginConfig.escapeVersionName
import de.fayard.internal.PluginConfig.isNonStable
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.extra


open class BuildSrcVersionsPlugin : Plugin<Project> {

    override fun apply(project: Project) {
        check(project == project.rootProject) { "ERROR: plugins de.fayard.buildSrcVersions must be applied to the root build.gradle(.kts)" }
        project.tasks.removeIf { it.name == PluginConfig.DEPENDENCY_UPDATES }
        project.apply(plugin = PluginConfig.GRADLE_VERSIONS_PLUGIN_ID)
        project.configure()
        project.useVersionsFromGradleProperties()
    }

    fun Project.configure() = with(PluginConfig) {
        extensions.create(BuildSrcVersionsExtension::class, EXTENSION_NAME, BuildSrcVersionsExtensionImpl::class)

        if (supportsTaskAvoidance()) {
            val provider = tasks.named(DEPENDENCY_UPDATES, DependencyUpdatesTask::class.java)
            configureGradleVersions = { operation -> provider.configure(operation) }
            configureGradleVersions(DependencyUpdatesTask::configureBenManesVersions)

            tasks.register(BUILD_SRC_VERSIONS, BuildSrcVersionsTask::class.java, BuildSrcVersionsTask::configureBuildSrcVersions)
            tasks.register(REFRESH_VERSIONS, BuildSrcVersionsTask::class.java, BuildSrcVersionsTask::configureRefreshVersions)

        } else {
            val dependencyUpdatesTask = tasks.maybeCreate(DEPENDENCY_UPDATES, DependencyUpdatesTask::class.java)
            configureGradleVersions = { operation -> dependencyUpdatesTask.operation() }
            configureGradleVersions(DependencyUpdatesTask::configureBenManesVersions)

            tasks.create(BUILD_SRC_VERSIONS, BuildSrcVersionsTask::class, BuildSrcVersionsTask::configureBuildSrcVersions)
            tasks.create(REFRESH_VERSIONS, BuildSrcVersionsTask::class, BuildSrcVersionsTask::configureRefreshVersions)
        }
    }
}

fun Project.useVersionsFromGradleProperties() {
    val extra = this.extra.properties
    println("extra=$extra")
    allprojects {
        val projectName = name
        configurations.all {
            val configurationName = name
            println("Resolving dependency $group:$name in project=$projectName")
            if (name.contains("copy").not()) {
                resolutionStrategy.eachDependency {
                    println("Resolving dependency $group:$name in project=$projectName and configuration=$configurationName")
                    val gradleProperties = listOf(
                        escapeVersionName("version.$group.$name"),
                        escapeVersionName("version.$group"),
                        escapeVersionName("version.$name")
                    )
                    val versionFromGradleProperties: String = gradleProperties
                        .firstOrNull { key -> key in extra }
                        ?.let { key -> extra[key] as String }
                        ?: return@eachDependency
                    println("useVersion($versionFromGradleProperties) for $group:$name")
                    useVersion(versionFromGradleProperties)
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

fun BuildSrcVersionsTask.configureRefreshVersions() {
    group = "Help"
    description = "Search for available dependencies updates and update gradle.properties"
    dependsOn(PluginConfig.DEPENDENCY_UPDATES_PATH)
    outputs.upToDateWhen { false }
    configure {
        versionsOnlyMode = VersionsOnlyMode.GRADLE_PROPERTIES
        versionsOnlyFile = "gradle.properties"
    }
}

fun BuildSrcVersionsTask.configureBuildSrcVersions() {
    group = "Help"
    description = "Update buildSrc/src/main/kotlin/{Versions.kt,Libs.kt}"
    dependsOn(PluginConfig.DEPENDENCY_UPDATES_PATH)
    outputs.upToDateWhen { false }
    configure {
        versionsOnlyMode = null
        versionsOnlyFile = null
    }
}
