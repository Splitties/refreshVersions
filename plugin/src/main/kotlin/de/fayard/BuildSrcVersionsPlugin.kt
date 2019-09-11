package de.fayard

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import de.fayard.PluginConfig.isNonStable
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

open class BuildSrcVersionsPlugin : Plugin<Project> {

    override fun apply(project: Project) = project.configure()

    fun Project.configure() {
        BuildSrcVersionsTask.theProject = project
        extensions.create(BuildSrcVersionsExtension::class, PluginConfig.EXTENSION_NAME, BuildSrcVersionsExtensionImpl::class)

        if (PluginConfig.supportsTaskAvoidance()) {
            tasks.register("dependencyUpdates", DependencyUpdatesTask::class.java) {
                configureBenManesVersions()
            }
            tasks.register("buildSrcVersions", BuildSrcVersionsTask::class.java)

        } else {
            val dependencyUpdatesTask = tasks.maybeCreate("dependencyUpdates", DependencyUpdatesTask::class.java)
            dependencyUpdatesTask.configureBenManesVersions()
            tasks.create("buildSrcVersions", BuildSrcVersionsTask::class)
        }
    }
}


fun DependencyUpdatesTask.configureBenManesVersions() {
    rejectVersionIf { isNonStable(candidate.version) }
    checkForGradleUpdate = true
    outputFormatter = "json"
}
