package de.fayard.refreshVersions

import de.fayard.refreshVersions.core.*
import de.fayard.refreshVersions.core.extensions.gradle.isBuildSrc
import de.fayard.refreshVersions.core.internal.removals_replacement.RemovedDependencyNotationsReplacementInfo
import de.fayard.refreshVersions.core.internal.skipConfigurationCache
import de.fayard.refreshVersions.internal.getArtifactNameToConstantMapping
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

        internal val artifactVersionKeyRulesFileNames: List<String> = listOf(
            "androidx-version-alias-rules.txt",
            "cashapp-version-alias-rules.txt",
            "dependency-groups-alias-rules.txt",
            "google-version-alias-rules.txt",
            "jakewharton-version-alias-rules.txt",
            "kotlin(x)-version-alias-rules.txt",
            "reactivex-version-alias-rules.txt",
            "square-version-alias-rules.txt",
            "testing-version-alias-rules.txt"
        )

        @JvmStatic
        val artifactVersionKeyRules: List<String> = artifactVersionKeyRulesFileNames.map {
            getBundledResourceAsStream("refreshVersions-rules/$it")!!
                .bufferedReader().use { reader -> reader.readText() }
        }

        private fun removalsRevision(): Int {
            val currentVersion = RefreshVersionsCorePlugin.currentVersion
            return if (currentVersion.endsWith("-SNAPSHOT")) {
                getBundledResourceAsStream("snapshot-dpdc-rm-rev.txt")!!.bufferedReader().useLines {
                    it.first()
                }.toInt()
            } else {
                removalsRevision(refreshVersionsRelease = currentVersion)
            }
        }

        private fun removalsRevision(refreshVersionsRelease: String): Int {
            require(refreshVersionsRelease.endsWith("-SNAPSHOT").not())
            return getBundledResourceAsStream("version-to-removals-revision-mapping.txt")!!.bufferedReader().useLines {
                val prefix = "$refreshVersionsRelease->"
                it.firstOrNull { line -> line.startsWith(prefix) }?.substringAfter(prefix)?.toInt()
            } ?: 0
        }
    }


    override fun apply(target: Any) {
        null.checkGradleVersionIsSupported() // Check early to avoid confusing compat-related errors.
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

    private fun getRemovedDependenciesVersionsKeys(): Map<ModuleId.Maven, String> {
        return getBundledResourceAsStream("removed-dependencies-versions-keys.txt")
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

    private fun getRemovedDependencyNotationsReplacementInfo(): RemovedDependencyNotationsReplacementInfo {
        return RemovedDependencyNotationsReplacementInfo(
            readRevisionOfLastRefreshVersionsRun = { lastVersion, snapshotRevision ->
                snapshotRevision ?: lastVersion.let {
                    if (it.endsWith("-SNAPSHOT")) 0 else removalsRevision(lastVersion)
                }
            },
            currentRevision = removalsRevision(),
            removalsListingResource = getBundledResourceAsStream("removals-revisions-history.md")!!
        )
    }

    private fun bootstrap(settings: Settings) {
        if (settings.extensions.findByName(RefreshVersionsExtension.EXTENSION_NAME) == null) {
            // If using legacy bootstrap, the extension has already been created.
            settings.extensions.create<RefreshVersionsExtension>(
                RefreshVersionsExtension.EXTENSION_NAME
            )
        }

        if (settings.isBuildSrc) {
            settings.bootstrapRefreshVersionsCoreForBuildSrc(
                getRemovedDependenciesVersionsKeys = ::getRemovedDependenciesVersionsKeys
            )
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
                getDependenciesMapping = ::getArtifactNameToConstantMapping,
                getRemovedDependenciesVersionsKeys = ::getRemovedDependenciesVersionsKeys,
                getRemovedDependencyNotationsReplacementInfo = ::getRemovedDependencyNotationsReplacementInfo
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
                registerDependenciesTask(this)
            }
        }
    }

    private fun registerDependenciesTask(project: Project) {
        if (project != project.rootProject) return // We want the tasks only for the root project

        project.tasks.register<RefreshVersionsDependenciesMigrationTask>(
            RefreshVersionsDependenciesMigrationTask.TASK_NAME
        ) {
            description = RefreshVersionsDependenciesMigrationTask.DESCRIPTION
            group = RefreshVersionsCorePlugin.GROUP
            finalizedBy(RefreshVersionsTask.TASK_NAME)
            skipConfigurationCache()
        }

        project.tasks.register<RefreshVersionsDependenciesMappingTask>(
            name = RefreshVersionsDependenciesMappingTask.TASK_NAME
        ) {
            description = RefreshVersionsDependenciesMappingTask.DESCRIPTION
            group = RefreshVersionsCorePlugin.GROUP
        }

        project.tasks.register<RefreshVersionsCatalogTask>(
            name = RefreshVersionsCatalogTask.TASK_NAME
        ) {
            description = RefreshVersionsCatalogTask.DESCRIPTION
            group = RefreshVersionsCorePlugin.GROUP
            outputs.upToDateWhen { false }
            skipConfigurationCache()
        }

        project.tasks.register<RefreshVersionsMigrateTask>(
            name = RefreshVersionsMigrateTask.TASK_NAME
        ) {
            description = RefreshVersionsMigrateTask.DESCRIPTION
            group = RefreshVersionsCorePlugin.GROUP
            skipConfigurationCache()
        }

        project.tasks.register<RefreshDependenciesDocTask>(
            name = RefreshDependenciesDocTask.TASK_NAME
        ) {
            description = RefreshDependenciesDocTask.DESCRIPTION
            group = RefreshVersionsCorePlugin.GROUP
            skipConfigurationCache()
        }
    }

    private fun addDependencyToBuildSrcForGroovyDsl(settings: Settings) {
        require(settings.isBuildSrc)
        settings.gradle.rootProject {
            afterEvaluate {
                if (configurations.none { it.name == "implementation" }) {
                    apply(plugin = "java")
                }
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
}
