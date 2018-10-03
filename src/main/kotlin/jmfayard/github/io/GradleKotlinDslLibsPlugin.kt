package jmfayard.github.io

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.create

open class GradleKotlinDslLibsPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        val benManesVersions: DependencyUpdatesTask =
            project.tasks.maybeCreate("dependencyUpdates", DependencyUpdatesTask::class.java)

        benManesVersions.outputFormatter = "json"
        benManesVersions.checkForGradleUpdate = true



        project.tasks.create("syncLibs", SyncLibs::class) {
            dependsOn(":dependencyUpdates")
            jsonInputPath = benManesVersions.outputDir + "/" + benManesVersions.reportfileName + ".json"
        }
    }
}
