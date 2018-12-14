package de.fayard

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

open class BuildSrcVersionsPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        val benManesVersions: DependencyUpdatesTask =
            project.tasks.maybeCreate("dependencyUpdates", DependencyUpdatesTask::class.java)

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


        project.tasks.create("buildSrcVersions", BuildSrcVersionsTask::class) {
            dependsOn(":dependencyUpdates")
            jsonInputPath = benManesVersions.outputDir + "/" + benManesVersions.reportfileName + ".json"
        }

    }
}
