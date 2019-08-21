package de.fayard

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getByType

open class BuildSrcVersionsPlugin : Plugin<Project> {

    override fun apply(project: Project) = project.run {

        val benManesVersions: DependencyUpdatesTask = configureBenManesVersions()
        extensions.create(BuildSrcVersionsExtension::class, PluginConfig.EXTENSION_NAME, BuildSrcVersionsExtensionImpl::class)

        tasks.create("buildSrcVersions", BuildSrcVersionsTask::class) {
            group = "Help"
            description = "Update buildSrc/src/main/kotlin/{Versions.kt,Libs.kt}"
            dependsOn(":dependencyUpdates")
            jsonInputPath = benManesVersions.outputDir + "/" + benManesVersions.reportfileName + ".json"
        }

        Unit
    }



    fun Project.configureBenManesVersions(): DependencyUpdatesTask {
        val rejectedKeywords: List<String> by lazy {
            project.extensions.getByType<BuildSrcVersionsExtension>().rejectedVersionKeywords
        }

        val benManesVersions: DependencyUpdatesTask =
            tasks.maybeCreate("dependencyUpdates", DependencyUpdatesTask::class.java)

        benManesVersions.outputFormatter = "json"
        benManesVersions.checkForGradleUpdate = true
        benManesVersions.resolutionStrategy {

            componentSelection {
                all {
                    val rejected = rejectedKeywords
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
