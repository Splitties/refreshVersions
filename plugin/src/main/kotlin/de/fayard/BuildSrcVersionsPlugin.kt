package de.fayard

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import de.fayard.PluginConfig.isNonStable
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

open class BuildSrcVersionsPlugin : Plugin<Project> {

    override fun apply(project: Project) = project.configure()

    fun Project.configure() {
        extensions.create(BuildSrcVersionsExtension::class, PluginConfig.EXTENSION_NAME, BuildSrcVersionsExtensionImpl::class)

        if (PluginConfig.supportsTaskAvoidance()) {
            val provider = tasks.register("dependencyUpdates", DependencyUpdatesTask::class.java)
            PluginConfig.configureGradleVersions = { operation -> provider.configure(operation) }
            tasks.register("buildSrcVersions", BuildSrcVersionsTask::class.java)

        } else {
            val dependencyUpdatesTask = tasks.maybeCreate("dependencyUpdates", DependencyUpdatesTask::class.java)
            PluginConfig.configureGradleVersions = { operation -> dependencyUpdatesTask.operation() }
            tasks.create("buildSrcVersions", BuildSrcVersionsTask::class)
        }

        (PluginConfig.configureGradleVersions)(DependencyUpdatesTask::configureBenManesVersions)
    }
}


fun DependencyUpdatesTask.configureBenManesVersions() {
    rejectVersionIf { isNonStable(candidate.version) }
    checkForGradleUpdate = true
    outputFormatter = "json"
}
