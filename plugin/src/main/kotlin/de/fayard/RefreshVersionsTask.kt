package de.fayard

import de.fayard.internal.Dependency
import de.fayard.internal.DependencyGraph
import de.fayard.internal.PluginConfig
import de.fayard.internal.RefreshVersionsExtensionImpl
import de.fayard.internal.UpdateProperties
import de.fayard.internal.logFileWasModified
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
open class RefreshVersionsTask : DefaultTask() {

    @Input
    @Option(description = "Update all versions, I will check git diff afterwards")
    var update: Boolean = false

    @Input
    @Optional
    @Option(description = "Tabs or Spaces?")
    var indent: String? = null

    @TaskAction
    fun taskActionGradleProperties() {
        val extension: RefreshVersionsExtensionImpl = extension()
        val updateGradleProperties = UpdateProperties(extension)

        val specialDependencies =
            listOf(PluginConfig.gradleVersionsPlugin, PluginConfig.gradleRefreshVersions, PluginConfig.gradleLatestVersion(dependencyGraph))

        val dependencies = (unsortedParsedDependencies + specialDependencies)
            .sortedBeautifullyBy(extension.orderBy) { it.versionProperty }
            .distinctBy { it.versionProperty }

        val propertiesFile = project.file(extension.propertiesFile!!)
        val existed = propertiesFile.canRead()
        updateGradleProperties.generateVersionProperties(propertiesFile, dependencies)
        logFileWasModified(propertiesFile.name, existed)
    }

    private val dependencyGraph: DependencyGraph by lazy {
        val extension: RefreshVersionsExtensionImpl = extension()

        val message = with(PluginConfig) {
            """
                |Running plugins.id("$PLUGIN_ID").version("$PLUGIN_VERSION") with configuration: $extension
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
    private lateinit var _extension: RefreshVersionsExtensionImpl

    fun configure(action: Action<RefreshVersionsExtension>) {
        val projectExtension = project.extensions.getByType<RefreshVersionsExtension>() as RefreshVersionsExtensionImpl
        this._extension = projectExtension.defensiveCopy()
        action.execute(this._extension)
        // TODO: use DEFAULT_MAPPING instead of ALIGN_VERSION_GROUPS
        PluginConfig.ALIGN_VERSION_GROUPS.clear()
        PluginConfig.ALIGN_VERSION_GROUPS.addAll(_extension.alignVersionsForGroups)
    }


    private fun extension(): RefreshVersionsExtensionImpl = _extension

}
