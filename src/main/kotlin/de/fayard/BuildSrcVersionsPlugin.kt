package de.fayard

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

open class BuildSrcVersionsPlugin : Plugin<Project> {

    /**
     * The name of the extension for configuring the runtime behavior of the plugin.
     *
     * @see org.gradle.plugins.site.SitePluginExtension
     */
    val EXTENSION_NAME = "buildSrcVersions"

    override fun apply(project: Project) = project.run {

        val benManesVersions: DependencyUpdatesTask = configureBenManesVersions()
        val pluginExtension = extensions.create(EXTENSION_NAME, BuildSrcVersionsExtension::class.java, project)
        pluginExtension.useFdqnFor.set(emptyList())

        tasks.create("buildSrcVersions", BuildSrcVersionsTask::class) {
            group = "Help"
            description = "Update buildSrc/src/main/kotlin/{Versions.kt,Libs.kt}"
            dependsOn(":dependencyUpdates")
            jsonInputPath = benManesVersions.outputDir + "/" + benManesVersions.reportfileName + ".json"
            useFdqnFor.set(pluginExtension.useFdqnFor)
        }


        Unit
    }

    fun Project.configureBenManesVersions(): DependencyUpdatesTask {
        val benManesVersions: DependencyUpdatesTask =
            tasks.maybeCreate("dependencyUpdates", DependencyUpdatesTask::class.java)

        benManesVersions.outputFormatter = "json"
        benManesVersions.checkForGradleUpdate = true
        benManesVersions.resolutionStrategy {
            componentSelection {
                all {
                    val rejected = listOf("alpha", "beta", "rc", "cr", "m", "preview")
                        .map { qualifier -> Regex("(?i).*[.-]$qualifier[.\\d-]*") }
                        .any { it.matches(candidate.version) }
                    if (rejected) {
                        reject("Release candidate")
                    }
                }
            }

        }
        return benManesVersions
    }
}
