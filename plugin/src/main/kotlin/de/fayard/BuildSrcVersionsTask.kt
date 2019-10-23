package de.fayard

import de.fayard.internal.BuildSrcVersionsExtensionImpl
import de.fayard.internal.Dependency
import de.fayard.internal.DependencyGraph
import de.fayard.internal.OutputFile
import de.fayard.internal.PluginConfig
import de.fayard.internal.UpdateGradleProperties
import de.fayard.internal.parseGraph
import de.fayard.internal.sortedBeautifullyBy
import org.gradle.api.Action
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.Optional
import org.gradle.api.tasks.TaskAction
import org.gradle.api.tasks.options.Option
import org.gradle.kotlin.dsl.getByType

@Suppress("UnstableApiUsage")
open class BuildSrcVersionsTask : DefaultTask() {

    @Input
    @Option(description = "Update all versions, I will check git diff afterwards")
    var update: Boolean = false

    @Input
    @Optional
    @Option(description = "Tabs or Spaces?")
    var indent: String? = null

    @TaskAction
    fun taskActionGradleProperties() {
        val extension: BuildSrcVersionsExtensionImpl = extension()
        val updateGradleProperties = UpdateGradleProperties(extension)

        val specialDependencies =
            listOf(PluginConfig.gradleVersionsPlugin, PluginConfig.gradleRefreshVersions, PluginConfig.gradleLatestVersion(dependencyGraph))

        val dependencies = (unsortedParsedDependencies + specialDependencies)
            .sortedBeautifullyBy(extension.orderBy) { it.versionProperty }
            .distinctBy { it.versionProperty }

        updateGradleProperties.generateVersionProperties(project.file("gradle.properties"), dependencies)
        OutputFile.GRADLE_PROPERTIES.logFileWasModified()

    }

    private val dependencyGraph: DependencyGraph by lazy {
        val extension: BuildSrcVersionsExtensionImpl = extension()

        val message = with(PluginConfig) {
            """
                |Running plugins.id("$PLUGIN_ID").version("$PLUGIN_VERSION") with useRefreshVersions=${useRefreshVersions} and extension: $extension
                |See documentation at $issue53PluginConfiguration
                |
            """.trimMargin()

        }
        println(message)

        val jsonInput = project.file(PluginConfig.BENMANES_REPORT_PATH)

        return@lazy PluginConfig.readGraphFromJsonFile(jsonInput)
    }

    private val unsortedParsedDependencies: List<Dependency> by lazy {
        parseGraph(dependencyGraph, extension().useFqqnFor)
            .map { d -> d.maybeUpdate(update || extension().alwaysUpdateVersions) }
    }

    @Input @Optional @Transient
    private lateinit var _extension: BuildSrcVersionsExtensionImpl

    fun configure(action: Action<BuildSrcVersionsExtension>) {
        val projectExtension = project.extensions.getByType<BuildSrcVersionsExtension>() as BuildSrcVersionsExtensionImpl
        this._extension = projectExtension.defensiveCopy()
        action.execute(this._extension)
        PluginConfig.useRefreshVersions = project.hasProperty("plugin.de.fayard.buildSrcVersions") || project.hasProperty("plugin.de.refreshVersions")
    }


    private fun extension(): BuildSrcVersionsExtensionImpl = _extension

}
