package de.fayard.refreshVersions

import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import de.fayard.refreshVersions.core.extensions.gradle.registerOrCreate
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project

open class RefreshVersionsPlugin : Plugin<Project> {

    companion object {
        @JvmStatic
        val artifactVersionKeyRules: List<String> = listOf(
            "androidx-version-alias-rules",
            "google-version-alias-rules",
            "kotlin(x)-version-alias-rules",
            "square-version-alias-rules",
            "other-version-alias-rules",
            "testing-version-alias-rules"
        ).map {
            RefreshVersionsPlugin::class.java.getResourceAsStream("/refreshVersions-rules/$it.txt")
                .bufferedReader()
                .readText()
        }
    }


    override fun apply(project: Project) {
        if (project != project.rootProject) return // We want the tasks only for the root project

        project.tasks.registerOrCreate<RefreshVersionsDependenciesMigrationTask>(
            name = "migrateToRefreshVersionsDependenciesConstants"
        ) {
            group = "help"
            description = "Assists migration from hardcoded dependencies to constants of " +
                "the refreshVersions dependencies plugin"
            finalizedBy("refreshVersions")
        }

        project.tasks.registerOrCreate<BuildSrcVersionsTask>(
            name = "buildSrcVersions"
        ) {
            group = "help"
            description = "Update buildSrc/src/main/kotlin/Libs.kt"
            outputs.upToDateWhen { false }
        }

        project.tasks.registerOrCreate<DefaultTask>(
            name = "refreshVersionDependenciesMapping"
        ) {
            group = "help"
            description = "Shows the mapping of Gradle dependencies and their typesafe accessors"
            doLast {
                println(getArtifactNameToConstantMapping().joinToString("\n"))
            }
        }
    }
}

