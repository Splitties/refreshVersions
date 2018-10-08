package jmfayard.github.io

import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.register

open class GradleKotlinDslLibsPlugin : Plugin<Project> {

    override fun apply(project: Project) {

        val benManesVersions: DependencyUpdatesTask =
            project.tasks.maybeCreate("dependencyUpdates", DependencyUpdatesTask::class.java)

        benManesVersions.outputFormatter = "json"
        benManesVersions.checkForGradleUpdate = true



        project.tasks.register("syncLibs", SyncLibsTask::class) {
            dependsOn(":dependencyUpdates")
            jsonInputPath = benManesVersions.outputDir + "/" + benManesVersions.reportfileName + ".json"
        }

    }
}
