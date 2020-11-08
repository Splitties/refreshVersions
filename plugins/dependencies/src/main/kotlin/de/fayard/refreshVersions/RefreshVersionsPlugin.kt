package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.bootstrapRefreshVersionsCoreForBuildSrc
import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import de.fayard.refreshVersions.core.extensions.gradle.registerOrCreate
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.getByType

open class RefreshVersionsPlugin : Plugin<Any> {

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


    override fun apply(target: Any) {
        when (target) {
            is Settings -> bootstrap(target)
            is Project -> applyToProject(target)
        }
    }

    private fun bootstrap(settings: Settings) {
        settings.extensions.create<RefreshVersionsExtension>("refreshVersions")

        if (settings.isBuildSrc) {
            settings.bootstrapRefreshVersionsCoreForBuildSrc()
            return
        }
        settings.gradle.settingsEvaluated {

            val extension: RefreshVersionsExtension = extensions.getByType()

            bootstrapRefreshVersions(
                extraArtifactVersionKeyRules = extension.extraArtifactVersionKeyRules,
                versionsPropertiesFile = extension.versionsPropertiesFile
                    ?: settings.rootDir.resolve("versions.properties")
            )
        }
    }

    private fun applyToProject(project: Project) {
        if (project != project.rootProject) return // We want the tasks only for the root project

        project.tasks.registerOrCreate<RefreshVersionsDependenciesMigrationTask>(
            name = "migrateToRefreshVersionsDependenciesConstants"
        ) {
            group = "help"
            description = "Assists migration from hardcoded dependencies to constants of " +
                    "the refreshVersions dependencies plugin"
            finalizedBy("refreshVersions")
        }

        project.tasks.registerOrCreate<DefaultTask>(
            name = "buildSrcVersions"
        ) {
            group = "help"
            description = "Update buildSrc/src/main/kotlin/Libs.kt"
            dependsOn("buildSrcLibs")
        }

        project.tasks.registerOrCreate<DefaultTask>(
            name = "refreshVersionsDependenciesMapping"
        ) {
            group = "help"
            description = "Shows the mapping of Gradle dependencies and their typesafe accessors"
            doLast {
                println(getArtifactNameToConstantMapping().joinToString("\n"))
            }
        }
    }
}

