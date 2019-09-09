package de.fayard

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import com.github.benmanes.gradle.versions.updates.resolutionstrategy.ComponentSelectionWithCurrent
import de.fayard.PluginConfig.isNonStable
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

open class BuildSrcVersionsPlugin : Plugin<Project> {

    override fun apply(project: Project) = project.run {

        val extension = extensions.create(BuildSrcVersionsExtension::class, PluginConfig.EXTENSION_NAME, BuildSrcVersionsExtensionImpl::class)
        (extension as BuildSrcVersionsExtensionImpl).upstream = configureBenManesVersions()
        extension.rejectVersionIf { current: ComponentSelectionWithCurrent ->
            isNonStable(current.candidate.version)
        }

        tasks.create("buildSrcVersions", BuildSrcVersionsTask::class) {
            group = "Help"
            description = "Update buildSrc/src/main/kotlin/{Versions.kt,Libs.kt}"
            dependsOn(":dependencyUpdates")
            outputs.upToDateWhen { false }
        }
        Unit
    }

    fun Project.configureBenManesVersions(): DependencyUpdatesTask =
        tasks.maybeCreate("dependencyUpdates", DependencyUpdatesTask::class.java).also { task: DependencyUpdatesTask ->
            task.checkForGradleUpdate = true
            task.outputFormatter = "json"
        }
}
