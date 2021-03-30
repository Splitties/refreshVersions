package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.RefreshVersionsCorePlugin
import de.fayard.refreshVersions.core.bootstrapRefreshVersionsCore
import de.fayard.refreshVersions.core.bootstrapRefreshVersionsCoreForBuildSrc
import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.initialization.Settings
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType
import org.gradle.kotlin.dsl.register

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
            is Project -> Unit //TODO: Warn about misconfiguration?
        }
    }

    private fun bootstrap(settings: Settings) {
        RefreshVersionsConfigHolder.markSetupViaSettingsPlugin()
        settings.extensions.create<RefreshVersionsExtension>("refreshVersions")

        if (settings.isBuildSrc) {
            settings.bootstrapRefreshVersionsCoreForBuildSrc()
            addDependencyToBuildSrcForGroovyDsl(settings)
            return
        }
        settings.gradle.settingsEvaluated {

            val extension: RefreshVersionsExtension = extensions.getByType()

            bootstrapRefreshVersionsCore(
                artifactVersionKeyRules = if (extension.extraArtifactVersionKeyRules.isEmpty()) {
                    artifactVersionKeyRules // Avoid unneeded list copy.
                } else {
                    artifactVersionKeyRules + extension.extraArtifactVersionKeyRules
                },
                versionsPropertiesFile = extension.versionsPropertiesFile
                    ?: settings.rootDir.resolve("versions.properties")
            )
            gradle.rootProject {
                applyToProject(this)
            }
        }
    }

    private fun applyToProject(project: Project) {
        if (project != project.rootProject) return // We want the tasks only for the root project

        project.tasks.register<RefreshVersionsDependenciesMigrationTask>(
            name = "migrateToRefreshVersionsDependenciesConstants"
        ) {
            group = "help"
            description = "Assists migration from hardcoded dependencies to constants of " +
                    "the refreshVersions dependencies plugin"
            finalizedBy("refreshVersions")
        }

        project.tasks.register<DefaultTask>(
            name = "buildSrcVersions"
        ) {
            group = "help"
            description = "Update buildSrc/src/main/kotlin/Libs.kt"
            dependsOn("buildSrcLibs")
        }

        project.tasks.register<DefaultTask>(
            name = "refreshVersionsDependenciesMapping"
        ) {
            group = "help"
            description = "Shows the mapping of Gradle dependencies and their typesafe accessors"
            doLast {
                println(getArtifactNameToConstantMapping().joinToString("\n"))
            }
        }
    }

    private fun addDependencyToBuildSrcForGroovyDsl(settings: Settings) {
        require(settings.isBuildSrc)
        settings.gradle.rootProject {
            repositories.addAll(settings.pluginManagement.repositories)

            fun plugin(id: String, version: String): String {
                return "$id:$id.gradle.plugin:$version"
            }

            dependencies {
                "implementation"(plugin("de.fayard.refreshVersions", RefreshVersionsCorePlugin.currentVersion))
            }
        }
    }
}

