package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.ModuleId
import de.fayard.refreshVersions.core.RefreshVersionsCorePlugin
import de.fayard.refreshVersions.core.bootstrapRefreshVersionsCore
import de.fayard.refreshVersions.core.bootstrapRefreshVersionsCoreForBuildSrc
import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.internal.RefreshVersionsConfigHolder
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.initialization.Settings
import org.gradle.api.invocation.Gradle
import org.gradle.kotlin.dsl.*
import java.io.InputStream

open class RefreshVersionsPlugin : Plugin<Any> {

    companion object {

        private fun getBundledResourceAsStream(relativePath: String): InputStream? {
            return RefreshVersionsPlugin::class.java.getResourceAsStream("/$relativePath")
        }

        @JvmStatic
        val artifactVersionKeyRules: List<String> = listOf(
            "androidx-version-alias-rules",
            "google-version-alias-rules",
            "kotlin(x)-version-alias-rules",
            "square-version-alias-rules",
            "testing-version-alias-rules",
            "dependency-groups-alias-rules"
        ).map {
            getBundledResourceAsStream("refreshVersions-rules/$it.txt")!!
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
        RefreshVersionsConfigHolder.markSetupViaSettingsPlugin()
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

            bootstrapRefreshVersionsCore(
                artifactVersionKeyRules = if (extension.extraArtifactVersionKeyRules.isEmpty()) {
                    artifactVersionKeyRules // Avoid unneeded list copy.
                } else {
                    artifactVersionKeyRules + extension.extraArtifactVersionKeyRules
                },
                versionsPropertiesFile = extension.versionsPropertiesFile
                    ?: settings.rootDir.resolve("versions.properties"),
                getRemovedDependenciesVersionsKeys = {
                    getBundledResourceAsStream("removed-dependencies-versions-keys.txt")
                        ?.bufferedReader()
                        ?.useLines { sequence ->
                            sequence.filter { it.isNotEmpty() }.associate {
                                val groupNameSeparator = ".."
                                val group = it.substringBefore(groupNameSeparator)
                                val postGroupPart = it.substring(startIndex = group.length + groupNameSeparator.length)
                                val name = postGroupPart.substringBefore('=')
                                val versionKey = postGroupPart.substring(startIndex = name.length + 1)
                                ModuleId.Maven(group, name) to versionKey
                            }
                        } ?: emptyMap()
                }
            )
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
            group = "refreshVersions"
            description = "Assists migration from hardcoded dependencies to constants of " +
                "the refreshVersions dependencies plugin"
            finalizedBy("refreshVersions")
        }

        project.tasks.register<DefaultTask>(
            name = "refreshVersionsDependenciesMapping"
        ) {
            group = "refreshVersions"
            description = "Shows the mapping of Gradle dependencies and their typesafe accessors"
            doLast {
                println(getArtifactNameToConstantMapping().joinToString("\n"))
            }
        }

        /* // TODO: Find out whether we want to expose the task or not.
        project.tasks.register<MissingEntriesTask>(
            name = "refreshVersionsMissingEntries"
        ) {
            group = "refreshVersions"
            description = "Add missing entries to 'versions.properties'"
            outputs.upToDateWhen { false }
        }
        */
        project.tasks.register<RefreshVersionsMigrateTask>(
            name = "refreshVersionsMigrate"
        ) {
            group = "refreshVersions"
            description = "Migrate build to refreshVersions"
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

