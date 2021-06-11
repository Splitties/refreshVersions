package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.RefreshVersionsCorePlugin
import de.fayard.refreshVersions.core.bootstrapRefreshVersionsCore
import de.fayard.refreshVersions.core.bootstrapRefreshVersionsCoreForBuildSrc
import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfig
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.kotlin.dsl.*

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
            RefreshVersionsPlugin::class.java.getResourceAsStream("/refreshVersions-rules/$it.txt")!!
                .bufferedReader()
                .readText()
        }
    }


    override fun apply(target: Any) {
        require(target is Settings) {
            val notInExtraClause: String = when (target) {
                is Project -> when (target) {
                    target.rootProject -> ", not in build.gradle(.kts)"
                    else -> ", not in a build.gradle(.kts) file."
                }
                is Gradle -> ", not in an initialization script."
                else -> ""
            }
            """
            plugins.id("de.fayard.refreshVersions") must be configured in settings.gradle(.kts)$notInExtraClause.
            See https://jmfayard.github.io/refreshVersions/setup/
            """.trimIndent()
        }
        bootstrap(target)
    }

    private fun bootstrap(settings: Settings) {
        if (settings.extensions.findByName("refreshVersions") == null) {
            // If using legacy bootstrap, the extension has already been created.
            settings.extensions.create<RefreshVersionsExtension>("refreshVersions")
        }

        if (settings.isBuildSrc) {
            settings.bootstrapRefreshVersionsCoreForBuildSrc()
            addDependencyToBuildSrcForGroovyDsl(settings)
            return
        }
        settings.gradle.settingsEvaluated {

            val extension: RefreshVersionsExtension = extensions.getByType()

           val config = bootstrapRefreshVersionsCore(
                artifactVersionKeyRules = if (extension.extraArtifactVersionKeyRules.isEmpty()) {
                    artifactVersionKeyRules // Avoid unneeded list copy.
                } else {
                    artifactVersionKeyRules + extension.extraArtifactVersionKeyRules
                },
                versionsPropertiesFile = extension.versionsPropertiesFile
                    ?: settings.rootDir.resolve("versions.properties")
            )
            config.markSetupViaSettingsPlugin()
            if (extension.isBuildSrcLibsEnabled) gradle.beforeProject {
                if (project != project.rootProject) return@beforeProject

                fun plugin(id: String, version: String) = "$id:$id.gradle.plugin:$version"

                buildscript.repositories.addAll(settings.pluginManagement.repositories)
                val dependencyNotation = plugin(
                    id = "de.fayard.buildSrcLibs",
                    version = RefreshVersionsCorePlugin.currentVersion
                )
                buildscript.dependencies.add("classpath", dependencyNotation)

                afterEvaluate {
                    apply(plugin = "de.fayard.buildSrcLibs")
                }
            }
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

